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
		function updatemm(){
		 var mm =document.getElementById("mm").value;
	     var mm2 =document.getElementById("mm2").value;
	     
	     if(mm.length<6){
	     alert("密码长度不得少于6位！");
	     return false;
	     }
	     
	     
	     if(mm!=mm2){
	      alert("密码输入前后不符，请重新输入！");
	      return false;
	     }

		 document.forms[0].action = "changeweb.do?method=dwyhmmchange&doType=update&jytype=jyweb";
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
		<html:form action="/changeweb" method="post">
			<br>
			<br>
			<h1>
				单位用户密码修改
			</h1>
			<div align="center">
				<img src="jyweb/images/caozuo_ok.gif" border="0" align="absmiddle">
			</div>
			<br>
			<div align="center">
				请输入新密码：
				<input type="password" name="mm" maxlength="16" />
				<br>
				请重复新密码：
				<input type="password" name="mm2" maxlength="16" />
				<br>
				<button onclick="updatemm();">
					提交
				</button>
			</div>

		</html:form>
		<div>
			<h3>

			</h3>
		</div>
		<jsp:include flush="true" page="foot.jsp"></jsp:include>


		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("修改成功！");
                </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("修改失败！");
                </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
