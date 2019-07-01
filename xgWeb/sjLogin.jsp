<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ page import="org.apache.axis.client.Call"%>
<%@ page import="org.apache.axis.client.Service"%>
<%@ page import="javax.xml.namespace.QName"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>系统提示</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="description" content="This is my page" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<%
		//获得请求者的用户ID
		String strUserID = null;
		String strTicket = null;
		String errMsg = null;
		javax.servlet.http.Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("sso_UserId")) {
					strUserID = cookies[i].getValue();
					System.out.println("current load cookie  " + cookies[i].getValue());
				}
				if (cookies[i].getName().equals("ticket")) {
					strTicket = cookies[i].getValue();
					System.out.println("current load cookie  "+ cookies[i].getValue());
				}
			}
		} else {
			System.out.println("No   cookies   found!");
		}

		System.out.println("userName:"+strUserID);
		System.out.println("ticket:"+strTicket);
		
		if (strUserID == null || strUserID.trim().equals("")) {
			errMsg = "该用户不存在，请确认";
		}
		if (strTicket == null || strTicket.trim().equals("")) {
			errMsg = "请求参数strTicket不合法";
		}
	%>
<script language="javascript">
function onCommit()
  	{   //提交给后台进行处理
    	var frm=document.getElementById("MAINFORM");
      	MAINFORM._REQUEST_TYPE.value ="C_REQUEST_LOGIN_REMOTE";
		frm.action="/xgxt/chkLoginDbCenter.do?userName="+document.getElementById("USER_ID").value+"&errMsg="+document.getElementById("errMsg").value;
		frm.method="post";
		frm.submit();
  	}

</script>
	<body onLoad="onCommit()" topmargin="40" leftmargin="10">
		<form id="MAINFORM" name="MAINFORM" method="post" action=""
			target="_self">
			<input type="hidden" name="_REQUEST_TYPE"
				value="C_REQUEST_LOGIN_REMOTE" />
			<input type="hidden" id="USER_ID" name="USER_ID" value="<%=strUserID%>" />
			<input type="hidden" id="TICKET" name="TICKET" value="<%=strTicket%>" />
			<input type="hidden" id="errMsg" name="errMsg" value="<%=errMsg%>" />
		</form>
	</body>
</html>
