// 以下变量将在simProcess.jsp中赋值
var targetUrl = "";
var loginUrl = "";
var user = "";		// 存放用户名
var pass = "";		// 存放密码
var params = {};	// 存放额外参数
// END

var successLoginTag = "已登录！";
var isSubmit = false;
function login_loaded(login) {
	var doc = login.contentDocument || login.contentWindow.document;
	var win = login.contentWindow || login.contentDocument.parentWindow;

	if (isSubmit){
		if ( doc.body.innerHTML.indexOf(successLoginTag)==-1) {
			alert("模拟登录失败！系统将返回到原始登录页");
			window.location.href = "simProcess.jsp?targetUrl="+targetUrl+"&loginUrl="+loginUrl+"&isLogon=false";
			return;
		} else {
			window.location.href = "simProcess.jsp?targetUrl="+targetUrl+"&loginUrl="+loginUrl+"&isLogon=true";
			return;
		}
	}
	doc.getElementById("username").value = user;
	doc.getElementById("password").value = pass;
	// 从params中获取额外登陆参数
	// doc.getElementById("sf").value = params.sf;

	isSubmit = true;

	doc.getElementById("submit").click();
}


