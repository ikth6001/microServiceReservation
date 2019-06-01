
/**
 * Module dependencies.
 */
var express = require('express')
  , http = require('http')
  , path = require('path')
  , cookieParser = require('cookie-parser')
  , Eureka= require('eureka-nodejs-client')
  , localeMgr= require('./lib/localeMgr')
  , configMgr= require('./lib/configMgr');

/**
 * Module routes.
 */
var main= require('./routes/main');
var config;
var i18n= localeMgr.load();
var config= configMgr.load('reservation-service', 'http://localhost:8760', 'dev', function(data) { config= data; });
var app = express();

//all environments
app.set('port', process.env.PORT || 8763);
app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');

/**
 * TODO 
 * 1. Spring-config server..
 * 2. 전체적으로 spring config server로부터 데이터를 받은 후에 서버 initializing이 되도록 리팩토링 필요.
 */
var eurekaClient= new Eureka({
	eureka: {
		host: 'http://127.0.0.1:8761'
		, registerWithEureka: true
	},
	instance: {
		app: 'web-service'
		, ipAddr: '127.0.0.1'
		, port: app.get('port')
	}
});

app.configure(function() {
	eurekaClient.start();
	app.use(i18n.init);
});

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
