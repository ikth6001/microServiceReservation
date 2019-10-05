var express = require('express')
  , http = require('http')
  , path = require('path')
  , cookieParser = require('cookie-parser')
  , Eureka= require('eureka-nodejs-client')
  , localeMgr= require('./lib/localeMgr')
  , configMgr= require('./lib/configMgr')
  , logger= require('./lib/loggerFactory').getLogger('app')
  , ipMgr= require('./lib/ipMgr');

var main= require('./routes/main');
var app = express();
var i18n= localeMgr.load();
var configReqUrl= process.env.CONFIG_REQ_URL || 'http://192.168.99.100:8760';
var ipAddr;

const profile= process.env.NODE_ENV || 'dev';

ipMgr.getIp(function(data) {
	ipAddr= data.toString();
	configMgr.load('web-service'
			, configReqUrl
			, profile
			, profile === 'dev' ? 'develop' : 'master'
			, bootstrap);
});

function bootstrap(config) {
	
//	console.log('loaded config [' + config + ']');
	logger.debug('loaded config [%s]', config);
	
	//all environments
	const servPort= process.env.PORT || 8764
	const eureka= config.get('eureka.client.service-url.defaultZone');
//	console.log('My public ip address [' + publicIp + ']');
	logger.debug('My public ip address [%s]', ipAddr);
	
	app.set('views', __dirname + '/views');
	app.set('view engine', 'ejs');
	
	var eurekaClient= new Eureka({
		eureka: {
			serviceUrl: [eureka]
			, registerWithEureka: true
		},
		instance: {
			app: 'web-service'
			, ipAddr: ipAddr
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
	app.use(express.logger(profile));
	app.use(express.bodyParser());
	app.use(express.methodOverride());	// Get, Post 외의 메소드 사용시 설정 필요
	app.use(app.router);
	app.use(express.static(path.join(__dirname, 'public')));

//	if ('dev' == app.get('env')) {
//	  app.use(express.errorHandler());
//	}

	app.get('/', main);

	http.createServer(app).listen(servPort, function(){
//	  console.log('Server listening on port ' + servPort);
		logger.debug('Server listening on port [%s]', servPort);
	});
}