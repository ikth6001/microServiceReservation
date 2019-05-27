/**
 * http://usejsdoc.org/
 */
var express= require('express');
var res= express.response;

res.message= function(msg, type) {
	type= type || 'info';
	var sess= this.req.session;
	sess.messages= sess.messages || [];
	sess.messages.push({type: type, string: msg});
};

module.exports= function(req, res, next) {
	res.locals.messages= req.session.messages || [];
	res.locals.removeMessages= function() {
		res.session.messages= [];
	};
	next();
};