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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/pjpyjgsdxwh.do" method="post">
			
		<center> <div id="tips"><font size="4"> <b>井冈山大学${xn }学年度奖学金审批表 </b></font></div></center>
		<table width="100%" align="center" id="rsTable" class="tbstyle">
				<thead>
				<tr>
					<td><div align="center">院、系、班</div></td>
    				<td><div align="center">学 号</div></td>
				    <td><div align="center">姓 名</div></td>
				    <td><div align="center">综合素质测评排名</div></td>
				    <td><div align="center">学习成绩排名</div></td>
				    <td><div align="center">奖学金名称</div></td>
				    <td><div align="center">奖学金金额</div></td>
				    </tr>
				</thead>
				<logic:notEmpty name="rs">
				<logic:iterate id="s" name="rs">
					<tr align="center">
						<logic:iterate id="v" name="s" >
								<td align=center>
									<bean:write name="v" />
								</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
				</logic:notEmpty>
				<logic:empty name="rs">
				<tr>
				    <td align="center" colspan="7">未找到任何记录！</td>
  				</tr>
  				</logic:empty>
			</table>
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="expTab('rsTable',''+document.getElementById('tips').innerText,'')"
					style="width:80px" id="buttonPrint">
					打印/预览
				</button>
			</div>
			
		</html:form>
	</body>
</html>
