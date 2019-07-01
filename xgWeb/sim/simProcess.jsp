<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.net.URLEncoder"%>
<%@ include file="common.jsp" %>

<html>
<head>
<%@ include file="/syscommon/jquery-1.11.1_migrate.ini"%>
<script language="javascript" src="simFormSubmit.js" type="text/javascript"></script>
</head>
<body>
<%
String targetUrl = request.getParameter("targetUrl");
String loginUrl = request.getParameter("loginUrl");

String isLogon = request.getParameter("isLogon");
if (CommonUtil.isBlank(isLogon) || isLogon.equals("")) {

} else if (isLogon.equals("false")) {
	loginUrl = CommonUtil.decodeServiceURI(loginUrl);
%>
<script language="javascript" type="text/javascript">
window.parent.location.href='<%=loginUrl%>';
</script>
<%
	return;
} else if (isLogon.equals("true")) {
	session.setAttribute("LOCAL_SESSION_IS_LOGON", "isLogon");
	targetUrl = CommonUtil.decodeServiceURI(targetUrl);
%>
<script language="javascript" type="text/javascript">
window.parent.location.href='<%=targetUrl%>';
</script>
<%
	return;
}

// 到CAS认证
if (session.getAttribute("CAS_SESSION_TICKET")==null 
		|| CommonUtil.isBlank(session.getAttribute("CAS_SESSION_TICKET").toString())) {
	String simUrl = "sim.jsp?targetUrl="+targetUrl+"&loginUrl="+loginUrl;
%>
<script language="javascript" type="text/javascript">
window.parent.location.href='<%=simUrl%>';
</script>
<%
	return;
}

String user = (String) session.getAttribute("CAS_SESSION_USER");
String pass = (String) session.getAttribute("CAS_SESSION_PASS");
String params = (String) session.getAttribute("CAS_SESSION_PARAMS");

String jsonParams = "";
if (params!=null && !params.equals("")) {
	String[] loginParamsKeyValue = params.split("&");
	for(int i=0;i<loginParamsKeyValue.length;i++) {
		String[] loginParamKeyValue = loginParamsKeyValue[i].split("=");
		String key = loginParamKeyValue[0];
		String value = loginParamKeyValue[1];
		
		jsonParams += jsonParams.equals("")?"":",";
		jsonParams += "\""+key+"\":\""+value+"\"";
	}
}
jsonParams = "{"+jsonParams+"}";

%>

<script language="javascript" type="text/javascript">
targetUrl = "<%=targetUrl%>";
loginUrl = "<%=loginUrl%>";

user = "<%=user%>";
pass = "<%=pass%>";
params = <%=jsonParams%>;

<%
loginUrl = CommonUtil.decodeServiceURI(loginUrl);
%>

function login_init() {
	var login = document.createElement("iframe");
	login.style.display = "";
	login.src = '<%=loginUrl%>';

	if (login.attachEvent){
		login.attachEvent("onload", function(){
			login_loaded(login);
	    });
	} else {
		login.onload = function(){
			login_loaded(login);
	    };
	}

	document.body.appendChild(login);
}

jQuery(document).ready(function(){login_init();});
</script>

</body>
</html>