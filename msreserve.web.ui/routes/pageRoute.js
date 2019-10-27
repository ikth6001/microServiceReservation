/**
 * http://usejsdoc.org/
 */
module.exports.route= function(pageName) {
	return function(req, res) {
		res.render(pageName);
	}
};