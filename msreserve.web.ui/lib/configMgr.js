/**
 * http://usejsdoc.org/
 */
var client= require('cloud-config-client');

module.exports.load= function(name, url, profile, fn) {
	client
		.load({
			name: name
			, profiles: profile
			, endpoint : url
		}).then(fn);
};