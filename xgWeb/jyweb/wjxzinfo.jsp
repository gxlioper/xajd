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

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<jsp:include flush="true" page="head.jsp"></jsp:include>
				<div class="mainframe">
  <div class="newscon">
		<h1>
			文件号：<font color="#0F03AF"><bean:write name="rs" property="wjh" /></font>&nbsp;&nbsp;&nbsp;&nbsp;
			文件名：<font color="#0F03AF"><bean:write name="rs" property="wjm" /></font>
		</h1>
		
			上传部门：
			<bean:write name="rs" property="wjffbm" />
			&nbsp;&nbsp;&nbsp;&nbsp; 上传时间：
			<bean:write name="rs" property="wjffsj" />
			&nbsp;&nbsp;&nbsp;&nbsp; 上传人：
			<bean:write name="rs" property="ffr" /><h4></h4>
			
<h3>

			<table width="83%" align="left" border="0">
				<tr>
					<td>
						<b>相关说明</b>：
					</td>
				</tr>
				<tr>
					<td align="left">
						<input type="hidden"
							value="<bean:write name="rs" property="wjscsm"/>" id="cont" />
						<script language="javascript">
						document.write(cont.value);
		                   </script>
					</td>
				</tr>
			</table>
			</h3>
			<hr style="width:83%" height="1">
			<table width="83%" align="center" border="0">
				<tr align="right">
					<td>
						<a
							href="downwj.do?method=downwj&jytype=jyweb&wjsclj=<bean:write name="rs" property="wjsclj"/>&wjhID=<bean:write name="rs" property="wjh" />">
							<font color="red">文件下载:&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="filename" /> </font></a>
					</td>
				</tr>
			</table>
		<h2>
		</h2>
		</div>
   </div>
		<jsp:include flush="true" page="foot.jsp"></jsp:include>
	</body>
</html>
