/**
 * http://usejsdoc.org/
 */
function setup(format) {
	var regexp= /:(\w+)/g;
	
	return function logger(req, res, next) {
		var str= format.replace(regexp, function(match, property) {
			return req[property];
		});
		
		console.log(str);
		next();
	};
}

// node의 export, module.exports에 대한 좋은 설명 링크
// https://jongmin92.github.io/2016/08/25/Node/module-exports_exports/
module.exports= setup;