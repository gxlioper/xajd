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
	<body onload="disableElement('xn-nd-xq-lddm-jxdm')">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<html:form action="/jxgl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
<%--			<logic:empty name="rs">--%>
<%--				<br />--%>
<%--				<br />--%>
<%--				<p align="center">--%>
<%--					无记录！--%>
<%--				</p>--%>
<%--			</logic:empty>--%>
<%--			<logic:notEmpty name="rs">--%>
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType"/>" />
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
<%--				<input type="hidden" id="pkValue" name="pkValue"--%>
<%--					value="<bean:write name="pkValue" scope="request"/>" />--%>
				<fieldset>
					<legend>
						军训团队获奖信息
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>学年：
							</td>
							<td align="left">
							<html:select property="xn" name="rs">
								<html:option value=""></html:option>
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
							</html:select>
							<html:hidden property="xn" name="rs"/>
							</td>
							<td align="right">
								<font color="red">*</font>连队：
							</td>
							<td align="left">
								<html:select property="lddm" name="rs">
								<html:option value=""></html:option>
								<html:options collection="ldList" labelProperty="dm" property="mc"/>
								</html:select>
								<html:hidden property="lddm" name="rs"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								<font color="red">*</font>年度：
							</td>
							<td align="left">
								<html:select property="nd" name="rs">
								<html:option value=""></html:option>
								<html:options collection="xnList" labelProperty="nd" property="nd"/>
								</html:select>
								<html:hidden property="nd" name="rs"/>
							</td>
							<td align="right">
								<font color="red">*</font>奖项：
							</td>
							<td align="left">
								<html:select property="jxdm" name="rs">
								<html:option value=""></html:option>
								<html:options collection="jxList" labelProperty="jxmc" property="jxdm"/>
								</html:select>
								<html:hidden property="jxdm" name="rs"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								<font color="red">*</font>学期：
							</td>
							<td align="left">
								<html:select name="rs" property="xq" styleId="xq">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
								<html:hidden property="xq" name="rs"/>
							</td>
							<td align="right">
								获奖时间：
							</td>
							<td align="left">
								<html:text name="rs" property="hjsj" styleId="hjsj" onclick="return showCalendar('hjsj','y-mm-dd');" readonly="true"></html:text>
							</td>
						</tr>						
				</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="Savedata('xn-nd-xq-lddm-jxdm','jxgl.do?method=saveJxtdhj');"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
<%--			</logic:notEmpty>--%>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<logic:present name="msg">
				<input type="hidden" id="msg" value="<bean:write name="msg"/>"/>
				<script>
					alert(document.getElementById('msg').value);
				</script>
			</logic:present>
			<logic:notPresent name="msg">
				<script>
					alert("操作失败!");
				</script>
			</logic:notPresent>
		</logic:equal>
	</body>
</html>
