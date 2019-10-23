window.addEventListener('DOMContentLoaded', function() {
	init();
});


function init() {
	
	var btnLogin= document.getElementById('btnLogin');
	var txtId= document.getElementById('txtId');
	var txtPw= document.getElementById('txtPw');
	var areaMsg= document.getElementById('areaMsg');
	
	btnLogin.addEventListener("click", function() {
		var id= txtId.value;
		var pw= txtPw.value;
		
		if(id) {
			var idRegEx= /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]+/;
			if( !idRegEx.test(id) ) {
				printErrMsg('아이디의 형식이 잘못 되었습니다. 아이디는 이메일 형식으로 작성합니다.');				
				return;
			}
		} else {
			printErrMsg('아이디를 입력 하십시오.');
			return;
		}
		
		if( !pw ) {
			printErrMsg('비밀번호를 입력 하십시오.');
			return;
		}
		
		var req= new XMLHttpRequest();
		req.addEventListener("load", function() {
			if(this.status == 200) {
				var txt= this.responseText;
				var res= JSON.parse(txt);
				
				areaMsg.innerHTML= '';
//				document.cookie='Bearer=' + res.access_token;
				document.cookie='Bearer=' + res.access_token + '; path=/;';
				window.history.back();	
			} else if(this.status == 401) {
				printErrMsg('존재하지 않는 아이디이거나 암호가 틀렸습니다.')
			} else {
				printErrMsg('서버 에러 발생..');
			}
		});
		req.open("POST", '/api/auth/oauth/token?grant_type=password&username=' + id + '&password=' + pw + '&scope=read', true);
		req.setRequestHeader("Authorization", "Basic " + btoa("foo:bar"));
		req.send();
	});
}

function printErrMsg(msg) {
	var areaMsg= document.getElementById('areaMsg');
	areaMsg.innerHTML= '<font color=\'red\'>' + msg + '</font>';
	
}