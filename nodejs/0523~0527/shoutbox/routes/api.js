/**
 * http://usejsdoc.org/
 */
var express= require('express');
var User= require('../lib/user');

exports.auth= express.basicAuth(User.authenticate);

exports.user= function(req, res, next) {
	User.get(req.params.id, function(err, user) {
		if(err) {
			return next(err);
		}
		if(!user.id) {
			return res.send(404);
		}

		// 클라이언트의 요청 MIME에 따른 적절한 응답
		res.format({
			'application/json': function() {
				res.json(user);
			},
			'application/xml': function() {
				// TODO
			}
		});
	});
};