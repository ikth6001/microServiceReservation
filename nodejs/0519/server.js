/**
 * very simple web server
 */

var http= require('http');
var parse= require('url').parse;
var join= require('path').join;
var fs= require('fs');
var qs= require('querystring');
var formidable= require('./node_modules/formidable');

var root= __dirname;	// 매직변수. 파일의 경로를 나타낸다.
var items= [];

function show(res) {
	
	var html=
		'<html><head><title>Todo List</title><head><body>' +
		'<ul>' +
		items.map(function(item) {
			return '<li>' + item + '</li>';
		}).join('') +
		'</ul>' +
		'<form method="post" action="/" enctype="multipart/form-data">' +
		'<p><input type="text" name="name"/></p>' +
		'<p><input type="file" name="file"/></p>' +
		'<p><input type="submit" value="Add Item"/></p>' +
		'</form></body></html>';

	res.setHeader('Content-Type', 'text/html');
	res.setHeader('Content-Length', Buffer.byteLength(html));
	res.end(html);
		
}

function notFound(res) {
	res.statusCode= 404;
	res.setHeader('Content-type', 'text/plain');
	res.end('Not found');
}

function badRequest(res) {
	res.statusCode= 400;
	res.setHeader('Content-type', 'text/plain');
	res.end('Bad Request');
}

function isFormData(req) {
	var type= req.headers['content-type'] || '';
	return 0 === type.indexOf('multipart/form-data');
}

function add(req, res) {
//	var body= '';
//	req.setEncoding('utf8');
//	req.on('data', function(chunk) {
//		body += chunk;
//	});
//	req.on('end', function() {
//		var obj= qs.parse(body);
//		items.push(obj.item);
//		show(res);
//	});
	
	if(isFormData(req)) {
		var form= new formidable.IncomingForm();
		
		form.on('progress', function(bytesReceived, bytesExpected) {
			var percent= Math.floor(bytesReceived / bytesExpected * 100);
			console.log(percent);
		});
		
		form.parse(req, function(err, fields, files) {
			console.log(fields);
			console.log(files);
			res.end('Upload complete!');
		});
	} else {
		res.statusCode= 400;
		res.end('Bas Request : expecting multipart/form-data');
	}
}

var server= http.createServer(function(req, res) {
	if('/' === req.url) {
		switch(req.method) {
			case 'GET':
				show(res);
				break;
			case 'POST':
				add(req, res);
				break;
			default:
				badRequest(res);
		}
	} else {
		notFound(res);
	}
});

server.listen(8080);
console.log('Server is listening on 8080');

