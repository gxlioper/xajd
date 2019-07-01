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
	function getRqVal(name)
			{
			var rq=document.getElementById(name).value;
			if (rq!="")
			{
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++)
				{
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
			}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/stuPunishAuditOneByFsj" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：违纪处分 - 审核 - 校党委审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal"
				value="<bean:write name="xh||cfrq||cfwh"/>" />
			<input type="hidden" name="cflbmc" value="<bean:write name="cflbmc"/>">
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="5" align="center">
							单个审核
						</td>
					</tr>
				</thead>
				<tr style="height:22px;" >
					<td align="right" style="width:17%">
						学号：
					</td>
					<td align="left" style="width:35%">
						<bean:write name="XH" />
					</td>
					<td align="right">
						年度：
					</td>
					<td align="left">
						<bean:write name="ND" />
					</td>
					<td rowspan="5" align="center" width="15%">
						<img align="center" border="0" style="height:133px;width:100px;"
								src="/xgxt/pictures/<bean:write name="XH" />.jpg" id="pic">
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
						处分类别：
					</td>
					<td align="left">
						<bean:write name="cflbmc" />
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
						处分原因：
					</td>
					<td align="left">
						<bean:write name="cfyymc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name="XYMC" />
					</td>
					<td align="right">违纪时间：
						</td>
						<td align="left"><bean:write name="wjsj"/>
						</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name="ZYMC" />
					</td>
					<td align="right">校党委审核：
						</td>
						<td align="left" colspan="2"><html:select property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
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
						审核时间：
					</td>
					<td align="left" colspan="2">
						<input type="text" name="xdwshsj" id="xdwshsj" value="<bean:write name="xdwshsj"/>" onclick="return showCalendar('xdwshsj','y-mm-dd');" onblur="getRqVal('xdwshsj')">
					</td>		
				</tr>
				<tr>
				</tr>
				<tr>
					<td align="right">
						校党委意见：
					</td>
					<td colspan="4" align="left"><html:textarea rows="4"  style="width:98%" property="xdwyj"  styleId="a"/>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="refreshForm('/xgxt/stuPunishAuditOneByFsj.do?act=save');"
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
		<logic:equal value="view" name="result">
			<script>
				alert("操作成功");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
