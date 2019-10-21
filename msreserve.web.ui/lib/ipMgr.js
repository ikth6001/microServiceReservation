// 외부 사이트에 get 요청을 보내서 public ip를 알아내는 방식인데..
var http= require('http');
var ip= require('ip');

var options= {
	host: 'api.ipify.org'
	, port: 80
	, path: '/'
}

//module.exports.getIp= function(fnc) {
//	http.get(options, function(res) {
//		res.on('data', function(chunk) {
//			fnc(chunk);
//		});
//	});
//};
module.exports.getIp= function(fnc) {
	// docker인 경우와 host인 경우의 구별이 필요한듯.. 추가로 클라우드에 올라갈 경우 public 이어야 하는데...
	// 이 부분은 profile을 통해서 구별하도록 기능 개선이 필요할 듯. 일단은 하드코딩
//	fnc('192.168.123.103');
//	fnc(ip.address());
	fnc('192.168.0.2');
}