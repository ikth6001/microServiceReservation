/**
 * http://usejsdoc.org/
 */
var client= require('cloud-config-client')
  , logger= require('./loggerFactory').getLogger('configMgr');

module.exports.load= function(name, url, profile, label, fn) {
	
	logger.debug('service name : ' + name);
	logger.debug('conf req url : ' + url);
	logger.debug('curr profile : ' + profile);
	
	client
		.load({
			name: name
			, profiles: profile
			, endpoint: url
			, label: label
		}).then(fn);
};