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
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/dwjlgl.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script language="javascript" src="js/dwjlFunction.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：对外交流 - 审核 - 对外交流奖学金审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal"
				value="<bean:write name="xn||nd||xh||jlxmdm"/>" />
			<input type="hidden" name="xh" id="xh" value="<bean:write name="XH" />" />
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							单个对外交流奖学金审核
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						学号：
					</td>
					<td align="left">
						<bean:write name="XH" />
					</td>
					<td align="right">
						年度：
					</td>
					<td align="left">
						<bean:write name="ND" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<bean:write name="XM" />
					</td>
					<td align="right">
						学年：
					</td>
					<td align="left">
						<bean:write name="XN" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						性别：
					</td>
					<td align="left">
						<bean:write name="XB" />
					</td>
					<td align="right">
						交流项目：
					</td>
					<td align="left">
						<bean:write name="DWJLXMMC" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						年级：
					</td>
					<td align="left">
						<bean:write name="NJ" />
					</td>
					<td align="right">
						交流方式：
					</td>
					<td align="left">
						<bean:write name='DWJLFSMC' />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name="XYMC" />
					</td>
					<td align="right">
						交流类别：
					</td>
					<td align="left">
						<bean:write name="DWJLLBMC" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name="ZYMC" />
					</td>
					<td align="right">
						申请时间：
					</td>
					<td align="left">
						<bean:write name="SQSJ" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						班级：
					</td>
					<td align="left">
						<bean:write name="BJMC" />
					</td>
					<td align="right">
						申请金额：
					</td>
					<td align="left">
						${SQJE }
					</td>
				</tr>
				<tr style="height:22px">
					<td></td>
					<td></td>
					<td align="right">
						审核：
					</td>
					<td align="left" colspan="">
						<html:select property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<%--浙江大学宁波理工<bean:message key="lable.xsgzyxpzxy" />--%>
				<logic:equal value="13022" name="xxdm">
					<tr>
					<td colspan="4" align="center" bgcolor="#CCCCCC" style="color:blue;cursor:hand"
					onclick="document.all.child.style.display=(document.all.child.style.display =='none')?'':'none';getDwjljxjInfo();">资助历史信息</td>
					</tr>
					<tr id="child" style="display='none'">
					<td colspan="4">
						<TABLE>
						<THEAD>
						<TR>
								<TD>年度</TD>
								<TD>学年</TD>
								<TD>学期</TD>
								<TD>交流项目</TD>
								<TD>交流方式</TD>
								<TD>交流类别</TD>
								<TD>最终批准金额</TD>
						</TR>
						</THEAD>
						<tbody id="dwjljxjInfo">
						</tbody>
						</TABLE>
					</td>
					</tr>
				</logic:equal>
			</table>
			<logic:equal value="yes" name="writeAble">
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="refreshForm('/xgxt/commMonChkOne.do?act=save');Close();window.dialogArguments.document.getElementById('search_go').click();"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
			</logic:equal>
		</html:form>
	</body>
</html>
