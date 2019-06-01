/**
 * http://usejsdoc.org/
 */
var i18n= require('i18n');

module.exports.load= function() {
	i18n.configure({
		locales: ['en', 'ko']
		, directory: __dirname + '/../messages'
		, defaultLocale: 'ko'
		, cookie: 'lang'
		, queryParameter: 'lang'
	});
	
	return i18n;
};