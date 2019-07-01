<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    
    <title>gdnz_dormFpb.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<style media='print'>
		.noPrin{display:none}
	</style>
  	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
  	<script language="javascript" src="js/function.js"></script>
<body>
	<html:form action="/gdnz_dormFpb">
		<p align="center">
			<font style="font-weight:bold" size="2">广东女子职业技术<bean:message key="lable.xsgzyxpzxy" />女生宿舍分配表</font>
		</p>
		<table class="tbstyle" align="center" width="90%">
			<tr>
				<td colspan="2" height="40">
					<div align="center">
						项目
					</div>
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="xymc"/></div>
					</td>
				</logic:iterate>
				<td width="66">
					<div align="center">
						合计
					</div>
				</td>
				<td width="127">
					<div align="center">
						备注
					</div>
				</td>
			</tr>
			<tr>
				<td width="77" rowspan="3">
					<div align="center">
						学生人数
					</div>
				</td>
				<td width="200" height="36">
					<bean:write name="rs" property="newstu"/>新生人数
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="newsturs"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="36">
					<bean:write name="rs" property="oldstu"/>级学生人数<font color="red">（非<bean:write name="rs" property="newstu"/>届）</font>
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="oldsturs"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="36">
					9月份全系人数合计
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="zsturs"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td rowspan="4">
					<div align="center">
						学生住宿分布情况
					</div>
				</td>
				<td height="36">
					全系应住6人间人数
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="xyyzrs6"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="36">
					全系庆住10人间人数
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="xyyzrs10"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="36">
					<bean:write name="rs" property="oldstu"/>级已住6人间人数
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="yzrs6"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="36">
					<bean:write name="rs" property="oldstu"/>级已住10人间人数
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="yzrs10"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td rowspan="4">
					<div align="center">
						应补充床位(宿舍)数
					</div>
				</td>
				<td height="36">
					应补充6人间床位(宿舍)数
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="ybccws6"/>(<bean:write name="fpxx" property="ybcss6"/>)间</div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="36">
					应补充10人间床位(宿舍)数
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="ybccws10"/>(<bean:write name="fpxx" property="ybcss10"/>)间</div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="36">
					<bean:write name="rs" property="oldstu"/>级六人间混空床位数
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="hkcws6"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="36">
					<bean:write name="rs" property="oldstu"/>级十人间混空床位数
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="hkcws10"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td rowspan="2">
					<div align="center">
						补充床位(宿舍)使用分布
					</div>
				</td>
				<td height="36">
					应预留新生6人间床位(宿舍)数
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="ylcws6"/>间</div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="36">
					<bean:write name="rs" property="oldstu"/>级学生可调整至6人间床位(宿舍)
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="ktzcw6"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
		<p align="center">
			<font style="font-weight:bold" size="2">各系应补充宿舍安排</font>
		</p>
		<table class="tbstyle" align="center" width="90%">
			<tr>
				<td width="89" height="57">
					<div align="center">
						系别宿舍
					</div>
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="xymc"/></div>
					</td>
				</logic:iterate>
				<td width="110">
					<div align="center">
						备注
					</div>
				</td>
			</tr>
			<tr>
				<td height="57">
					<div align="center">
						6人间
					</div>
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="rs6"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="57">
					<div align="center">
						10人间
					</div>
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="rs10"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
