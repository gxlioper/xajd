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
		
		
		
		</script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<fieldset>
			<legend >
			<font color="red"><b>问卷调查结果</b></font>
			</legend>
			<table>
				<tr>
					<td colspan="3" align="center">
						<font color="black"><b><bean:write name="bt" property="bt" />
						</b></font>
					</td>
				</tr>
				<tr align="center" >
					<td>
						<font color="black">选项</font>
					</td>
					<td>
						<font color="black">人数</font>
					</td>
					<td>
						<font color="black">比例</font>
					</td>
				</tr>
				<logic:iterate name="wjdclist" id="v">
					<tr height="25">
						<td width="45%">
							<bean:write name="v" property="choice" />
						</td>
						<td width="20%" align="center">
							<bean:write name="v" property="times" />
						</td>
						<td width="35%">
							<img src="jyweb/images/bl02.gif"
								width="<bean:write name="v" property="bili"/>" height="10px" />
							<font color="red"><bean:write name="v" property="bili" />
							%</font>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</fieldset>
	</body>
</html>
