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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">

	<script language="javascript">
</script>
	<body onload="checkWinType();initXnNdSel();">
		<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		%>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/chgPriseXn" method="post">
			<table width="99%" class="tbstyle">
				<thead>
					<tr>
						<td align="center" colspan="2">
							调整奖学金申请学年
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" style="width:50%">
						学年：
					</td>
					<td align="left">
						<span id="xnArea"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						年度：
					</td>
					<td align="left">
						<html:select property="nd" style="width:90px" styleId="nd">
							<html:options collection="ndList" property="nd"
								labelProperty="nd" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						学期：
					</td>
					<td align="left">
					<html:select property="xq" styleId="xq">
							<%--<html:option value=""></html:option>
							--%><html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
					</html:select>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<button type="button" class="button2"
							onclick="if(checkXnNd('xn','nd')){document.forms[0].submit();window.dialogArguments.document.forms[0].submit();alert('更改成功!');Close();}">
							更 改
						</button>
						<button type="button" class="button2" onclick="Close();return false;">
							关 闭
						</button>
					</td>
				</tr>
			</table>
			<br />
		</html:form>
	</body>
</html>
