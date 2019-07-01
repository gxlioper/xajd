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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<html:form action="/pjpyahjgwh" method="post">
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/xxjsadd.do" />
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:message key="pjpy_ahjg_xxjswh" bundle="pjpyahjg" />
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							学习竞赛单个增加
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right">
						<font color="red">*</font>学号：
					</td>
					<td align="left">
						<html:text name="rs" property="xh" styleId="xh" styleClass="inputtext"
							readonly="true"></html:text>
					</td>
					<td align="right">
						<font color="red">*</font>学年：
					</td>
					<td align="left">
						<html:select name="rs" disabled="true" property="xn" styleId="xn" style="width:90px" styleClass="select">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<td align="right">
						<font color="red">*</font>年度：
					</td>
					<td align="left">
						<html:select name="rs" disabled="true" property="nd" styleId="nd" style="width:90px" styleClass="select">
							<html:options collection="xnList" property="nd"
								labelProperty="nd" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td align="right">
						<font color="red">*</font>学习竞赛：
					</td>
					<td align="">
						<html:select name="rs" disabled="true" property="xxjsdm" styleId="xxjsdm"
							style="width:151px" styleClass="select">
							<html:option value=""></html:option>
							<html:options collection="xxjsList" property="xxjsdm"
								labelProperty="xxjsmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc" />
					</td>
					<td align="right">
						获奖时间：
					</td>
					<td align="left">
						<html:text name="rs" disabled="true" property="hjsj" styleId="hjsj" style="cursor:hand;"
							styleClass="inputtext;"
							onclick="return showCalendar('hjsj','y-mm-dd');"
							onblur="dateFormatChg(this)" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td align="right">
						班级：
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td align="right">
						&nbsp;
					</td>
					<td align="left">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td align="right">
						备注：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<br />
						长度不超过400
					</td>
					<td colspan="3">
						<html:textarea name="rs" disabled="true" property="bz" styleId="bz" rows="7"
							style="width:98%" styleClass="inputtext"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>

		</html:form>
		<logic:notEmpty name="inserted">
			<logic:equal value="yes" name="inserted">
			<script language="javascript">
alert("操作成功！");
Close();
window.dialogArguments.document.getElementById('search_go').click();   
</script>
		</logic:equal>
		<logic:equal value="no" name="inserted">
			<script language="javascript">
  alert("操作失败！原因可能是该信息在数据库中已存在! \n （请仔细核对!）");
Close();
window.dialogArguments.document.getElementById('search_go').click();   
</script>
		</logic:equal>
		</logic:notEmpty>
	</body>
</html>
