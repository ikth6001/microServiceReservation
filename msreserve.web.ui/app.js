var express = require('express')
  , http = require('http')
  , path = require('path')
  , cookieParser = require('cookie-parser')
  , Eureka= require('eureka-nodejs-client')
  , localeMgr= require('./lib/localeMgr')
  , configMgr= require('./lib/configMgr');

var main= require('./routes/main');
var app = express();
var i18n= localeMgr.load();
var configReqUrl= process.env.CONFIG_REQ_URL || 'http://192.168.99.100:8760';

configMgr.load('web-service'
				, configReqUrl
				, process.env.NODE_ENV || 'dev'
				, 'develop' // TODO
				, bootstrap);

function bootstrap(config) {
	
	console.log('loaded config [' + config + ']');
	
	//all environments
	const servPort= process.env.PORT || 8764
	const eureka= config.get('eureka.client.service-url.defaultZone');
	
	app.set('views', __dirname + '/views');
	app.set('view engine', 'ejs');
	
	var eurekaClient= new Eureka({
		eureka: {
			serviceUrl: [eureka]
			, registerWithEureka: true
		},
		instance: {
			app: 'web-service'
			, ipAddr: '192.168.99.100' // TODO docker 환경.. ip 어떻게 구하지
			, port: servPort
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

//	if ('dev' == app.get('env')) {
//	  app.use(express.errorHandler());
//	}

	app.get('/', main);

	http.createServer(app).listen(servPort, function(){
	  console.log('Server listening on port ' + servPort);
	});
}