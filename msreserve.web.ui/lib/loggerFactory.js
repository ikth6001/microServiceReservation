var winston= require('winston');
module.exports.getLogger= function(file) {

	const { combine, timestamp, label, printf, splat, simple } = winston.format;
	const format= printf(({timestamp, level, message}) => {
		return `[${file}][${timestamp}][${level}]: ${message}`
	});
	
	/**
	 * TODO
	 * 로그 옵션을 외부 파일로부터 읽어올 수 있으면 더 좋을 듯... 
	 */
	const options= {
		file: {
			level: 'debug'
			, filename: './logs/web-server.log'
			, handleExceptions: true
			, maxsize: 5242880
			, maxFiels: 5
			, format: combine(
				splat()
				, simple()
				, timestamp()
				, format
			)
		},
		console: {
			level: 'debug'
			, handleExceptions: true
			, format: combine(
				splat()
				, simple()
				, timestamp()
				, format
			)
		}
	};
	
	return winston.createLogger({
		transports: [
			new winston.transports.Console(options.console)
			, new winston.transports.File(options.file)
		]
	});
};