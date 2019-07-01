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
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName"
				value="<bean:write name="tName" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="8" align="center">	
						</td>
					</tr>
				</thead>
		
				<tr>
				    <td width="17%" height="31"><div align="center">学号</div></td>
				    <td width="33%" height="31"><div align="left" ><bean:write name="xh"/></div></td>
				    <td width="13%" height="31"><div align="center">姓名</div></td>
				    <td width="37%" height="31"><div align="left"><bean:write name="xm"/></div></td>
				  </tr>
				  <tr>
				    <td height="31"><div align="center">性别</div></td>
				    <td height="31"><div align="left"><bean:write name="xb"/></div></td>
				    <td height="31"><div align="center">出生日期</div></td>
				    <td height="31"><div align="left"><bean:write name="csrq"/></div></td>
				  </tr>
				  <tr>
				    <td height="31"><div align="center">身份证号码</div></td>
				    <td height="31"><div align="left"><bean:write name="sfzh"/></div></td>
				    <td height="31"><div align="center">年级</div></td>
				    <td height="31"><div align="left"><bean:write name="nj"/></div></td>
				  </tr>
				  <tr>
				    <td height="31"><div align="center"><bean:message key="lable.xsgzyxpzxy" /></div></td>
				    <td height="31"><div align="left"><bean:write name="xy" /></div></td>
				    <td height="31"><div align="center">专业</div></td>
				    <td height="31"><div align="left"><bean:write name="zymc" /></div></td>
				  </tr>
				  <tr>
				    <td height="31"><div align="center">宿舍电话</div></td>
				    <td height="31"><div align="left"><bean:write name="qsdh" /></div></td>
				    <td height="31"><div align="center">学制</div></td>
				    <td height="31"><div align="left"><bean:write name="xz" /></div></td>
				  </tr>
				  <tr>
				    <td height="31"><div align="center">申请贷款金额</div></td>
				    <td height="31"><div align="left"><bean:write name="sqdkje" /></div></td>
				    <td height="31"><div align="center">贷款期限</div></td>
				    <td height="31"><div align="left"><bean:write name="dkqx" /></div></td>
				  </tr>
						     <tr>
							     <td><div align="center">家庭电话</div></td>
							     <td><bean:write name="jtdh" /></td>
							     <td><div align="center">审核结果</div></td>
							     <td>
							     <html:select property="yesNo">
							          <html:options collection="chkList" property="en"
								         labelProperty="cn" />
						              </html:select>
							     </td>
						     </tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="refreshForm('/xgxt/assisChkOne.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
