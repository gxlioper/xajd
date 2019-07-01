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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
    </script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<div class="title">
			<div class="title_img" id="title_m">
				日常考核
			</div>
		</div>

		<div>
			<div align="center">
				<font size="5"><bean:write name="rs" property="bt" />
				</font>
			</div>
			<hr style="width:70%">
			<div align="center">
				<b>文件类型</b>：
				<bean:write name="rs" property="type" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<b>发布时间</b>：
				<bean:write name="rs" property="fbsj" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<b>发布人</b>：
				<bean:write name="rs" property="fbr" />
			</div>
			<br>
			<table width="80%" align="center" border="0">
				<tr>
					<td align="left">
						<bean:write name="nr" filter="false" />
					</td>
				</tr>
			</table>
		</div>

	</body>
</html>
