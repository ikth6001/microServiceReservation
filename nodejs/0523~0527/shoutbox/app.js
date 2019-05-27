var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');

var register= require('./routes/register');
var entries= require('./routes/entries');
var login= require('./routes/login');
var api= require('./routes/api');
var error= require('./routes/error');

var user= require('./lib/middleware/user');
var validate= require('./lib/middleware/validate');
var page= require('./lib/middleware/page');

var message= require('./lib/message');
var Entry= require('./lib/entry');


var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use(express.session());
app.use('/api', api.auth);
app.use(user);
app.use(message);

app.get('/register', register.form);
app.post('/register', register.submit);

app.get('/login', login.form);
app.post('/login', login.submit);
app.get('/logout', login.logout);

//app.get('/', entries.list);
app.get('/'
			, page(Entry.count, 5) 
			, entries.list);
app.get('/post', entries.form);
//app.post('/post', entries.submit); 유연한 검증 미들웨어를 추가 해보자.
app.post('/post'
			, validate.required('entry[title]')
			, validate.lengthAbove('entry[title]', 4)
			, entries.submit);

app.get('/api/user/:id', api.user);

app.use(error.notfound);
app.use(error.error);

