
/**
 * Module dependencies.
 */
var express = require('express')
  , http = require('http')
  , path = require('path')
  , cookieParser = require('cookie-parser')
  , i18n= require('i18n');

/**
 * Module routes.
 */
var main= require('./routes/main');

var app = express();

i18n.configure({
	locales: ['en', 'ko']
	, directory: __dirname + '/messages'
	, defaultLocale: 'ko'
	, cookie: 'lang'
	, queryParameter: 'lang'
});

app.configure(function() {
	app.use(i18n.init);
});

// all environments
app.set('port', process.env.PORT || 8080);
app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');

app.use(cookieParser());
app.use(i18n);
app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.bodyParser());
app.use(express.methodOverride());	// Get, Post 외의 메소드 사용시 설정 필요
app.use(app.router);
app.use(express.static(path.join(__dirname, 'public')));

// development only
if ('development' == app.get('env')) {
  app.use(express.errorHandler());
}

app.get('/', main);

http.createServer(app).listen(app.get('port'), function(){
  console.log('Server listening on port ' + app.get('port'));
});
