/**
 * http://usejsdoc.org/
 */
var connect= require('./node_modules/connect');
var myLogger= require('./my_modules/myLogger');
var errHandler= require('./my_modules/myErrHandle');

//function logger(req, res, next) {
//	console.log('%s %s', req.method, req.url);
//	next();
//}

function hello(req, res, next) {
	res.setHeader('Content-Type', 'text/plain');
	res.end('hello world!');
}

function restrict(req, res, next) {
	var authorization= req.headers.authorization;
	
	if(!authorization) {
		return next(new Error('Unauthorized'));	// 에러 핸들링 미들웨어가 실행되도록 설정
	}
	
	var parts= authorization.split(' ');
	var schema= parts[0];
	var auth= new Buffer(parts[1], 'base64').toString().split(':');
	var user= auth[0];
	var pass= auth[1];
	
	// TODO user auth  check
	next();
}

function admin(req, res, next) {
	switch(req.url) {
		case '/':	// connect가 알아서 /admin 방식 uri를 /로 마운팅 해준다.
			res.end('try /users');
			break;
		case '/users':
			res.setHeader('Content-Type', 'application/json');
			res.end(JSON.stringify(['tobi', 'loki', 'jane']));
			break;
	}
}

connect()
//	.use(logger)
	.use(myLogger(':method :url'))
	.use('/admin', restrict)	// URI가 /admin으로 시작하는 경우 동작하는 미들웨어
	.use('/admin', admin)
	.use(hello)
	.use(errHandler)
	.listen(8080);
