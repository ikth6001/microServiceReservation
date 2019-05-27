/**
 * http://usejsdoc.org/
 */
exports.notfound= function(req, res) {
	res.status(404).format({
		html: function() {
			res.render('404');
		},
		json: function() {
			res.json({mesage: 'Resource not found'});
		}
	});
};

exports.error= function(err, req, res, next) {
	console.error(err.stack);
	var msg;
	
	switch(err.type) {
		case 'database':
			msg= 'Server Unavailable';
			res.statusCode= 503;
			break;
		default:
			msg= 'Internal Server Error';
			res.statusCode= 500;
			break;
	}
	
	res.format({
		html: function() {
			res.render('5xx', {msg: msg, status: res.statusCode});
		},
		json: function() {
			res.json({error: msg});
		},
	});
};