<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title><%=session.getAttribute("xxmc")%>就业网</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="杭州正方电子工程有限公司 hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />

		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>
        <script type="text/javascript">
        
        function changepassword(){
		  var ymm = document.getElementById("ymm").value;
		  var xmm = document.getElementById("xmm").value;
		  var xmm2 = document.getElementById("xmm2").value;
		
		if(ymm==""){
		  alert("请输入原密码！");
		  return false;
       	}
       	
		if(xmm.length<6){
	     alert("密码长度不得少于6位！");
	     return false;
	    }

	    if(xmm!=xmm2){
	      alert("密码输入前后不符，请重新输入！");
	      return false;
	    }
		
		if(ymm==xmm){
		  alert("密码并为发生改变！");
		  return false;
		}
		  document.forms[0].action = "changepassword.do?method=changepassword&doType=change&jytype=jyweb";
		  document.forms[0].submit();	 
		}
        
        
        </script>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<jsp:include flush="true" page="head.jsp"></jsp:include>
       <html:form action="/changepassword" method="post">
		<div class="mimax" >
		<h1></h1>
			<table width="45%" height="150" cellspacing="0" cellpadding="0">
				<tr>
					<td align="right">
						原密码：
					</td>
					<td>
						<input type="password" name="ymm" maxlength="16">
					</td>

				</tr>
				<tr>
					<td align="right">
						输入新密码：
					</td>
					<td>
						<input type="password" name="xmm" maxlength="16">
					</td>
				</tr>
				<tr>
					<td align="right">
						确认新密码：
					</td>
					<td>
						<input type="password" name="xmm2" maxlength="16" onkeypress="if(window.event.keyCode==13) changepassword();">
					</td>
				</tr>

				<tr align="center">
					<td colspan="2">
						<button onclick="changepassword();">
							修改密码
						</button>
						&nbsp;
						<button type="reset">
							取消重填
						</button>
					</td>
				</tr>
			</table>
			<h3></h3>		
		</div>
       </html:form>

		<jsp:include flush="true" page="foot.jsp"></jsp:include>
	</body>
</html>
