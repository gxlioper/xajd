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
	<body onload="checkWinType();initSetPriseOne();"
		onunload="ableAllSel('xn-nd')">
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
		<script language="javascript" src="../style/function.js"></script>
		<html:form action="/chgPriseOne" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：奖学金参数批量设置
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td height="25" align="center" colspan="2">
							奖学金参数批量设置
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="25%">
						学年：
					</td>
					<td align="left">
						<input type="text" name="xn" id="xn" style="color:#666666"
							readonly />
					</td>
				</tr>
				<tr>
					<td align="right">
						年度：
					</td>
					<td align="left">
						<input type="text" name="nd" id="nd" style="color:#666666"
							readonly />
					</td>
				</tr>
				<tr>
					<td align="right">
						年级：
					</td>
					<td align="left">
						<input type="text" name="njT" id="njT" style="color:#666666"
							readonly />
						<input type="hidden" name="nj" id="nj" value="" />
					</td>
				</tr>
				<tr>
					<td align="right">
						部门：
					</td>
					<td align="left">
						<input type="text" name="bmT" id="bmT" style="color:#666666"
							readonly />
						<input type="hidden" name="bm" id="bm" value="" />
					</td>
				</tr>
				<tr>
					<td align="right">
						奖学金：
					</td>
					<td align="left">
						<input type="text" name="jxjdmT" id="jxjdmT" style="color:#666666"
							readonly />
						<input type="hidden" name="jxjdm" id="jxjdm" value="" />
					</td>
				</tr>
				<tr>
					<td align="right">
						建议人数：
					</td>
					<td align="left">
						<input type="text" name="jyrs" style="color:#666666" readonly />
						<font color="red">人</font>
					</td>
				</tr>
				<tr>
					<td align="right">
						人数调整为：
					</td>
					<td align="left">
						<input type="text" name="rstz"
							onkeypress="return numInputValue(this,2,event)" />
						<font color="red">人<br />"调整人数"范围应为("建议人数"-1)～("建议人数"+1)</font>
					</td>
				</tr>
				<tr>
					<td align="right">
						总额限制：
					</td>
					<td align="left">
						<input type="text" name="jxjze" style="color:#666666" readonly />
						<font color="red">元</font>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="if(confirm('确定要保存吗？')){document.forms[0].submit();alert('保存成功！');Close();ableAllSel('xn-nd');window.dialogArguments.document.getElementById('search_go').click();return true;}return false;">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
