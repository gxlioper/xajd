<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
<script language="javascript" src="js/xgutil.js"></script>
<script language="javascript" src="js/moveDiv.js"></script>
<script language="javascript">
function onShow(){
	if(window.dialogArguments){
		window.close();
		window.dialogArguments.document.forms[0].action = "/xgxt/echoLogin.jsp";
		window.dialogArguments.document.forms[0].target = "_parent";
		window.dialogArguments.document.forms[0].submit();
	}else if(opener){
		window.close();
		opener.document.forms[0].action = "/xgxt/echoLogin.jsp";
		opener.document.forms[0].target = "_parent";
		opener.document.forms[0].submit();
	}else{
		parent.document.location = '/xgxt/echoLogin.jsp';
	}
}
</script>
	<body onload="onShow()">
		<html:form action="/xszz_forward">

		</html:form>
	</body>
</html>