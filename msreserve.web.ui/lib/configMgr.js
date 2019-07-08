/**
 * http://usejsdoc.org/
 */
var client= require('cloud-config-client');

module.exports.load= function(name, url, profile, label, fn) {
	
	console.log('service name : ' + name);
	console.log('conf req url : ' + url);
	console.log('curr profile : ' + profile);
	
	client
		.load({
			name: name
			, profiles: profile
			, endpoint: url
			, label: label
		}).then(fn);
};