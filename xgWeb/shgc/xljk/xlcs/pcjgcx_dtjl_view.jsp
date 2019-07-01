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
	<body onload="">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/xljk_xlcs_pcjgcx" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						答题记录
					</legend>
					<table width="99%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>试题类型:
							</td>
							<td align="left">
								<html:text name='rs' property="stlxmc" style="width: 120px"
									styleId="stfz" readonly="true"/>
							</td>
							<td align="right">
								试题难度级别：
							</td>
							<td align="left">
								<html:text name='rs' property="stndjbmc" style="width: 120px"
									styleId="stfz" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>试题计分方式：
							</td>
							<td align="left">
									<html:text name='rs' property="stjffs" style="width: 120px"
									styleId="stfz" readonly="true"/>
							</td>
							<td align="right">
								试题分值：
							</td>
							<td align="left">
								<html:text name='rs' property="stfz" style="width: 120px"
									styleId="stfz" onblur="" readonly="true"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								<font color="red">*</font>试题答案：
							</td>
							<td align="left">
								<html:text name='rs' property="stda" style="width: 120px"
									styleId="stda" readonly="true"/>
							</td>
							<td align="right">
								是否显示：
							</td>
							<td align="left">
								<html:text name='rs' property="stxsbj" style="width: 120px"
									styleId="STXSBJ" readonly="true"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								试题所属类别：
							</td>
							<td align="left">
								<html:text name='rs' property="sslxmc" style="width: 120px"
									styleId="stda" readonly="true"/>
							</td>
							<td align="right">
							
							</td>
							<td align="left">
								
							</td>
						</tr>
						
						<tr align="left">
							<td align="right">
								<font color="red">*</font>试题内容：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='stnr' style="width:95%"
									rows='6' readonly="true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								试题答案解释：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='stdajs' style="width:95%"
									rows='5' readonly="true"/>
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
