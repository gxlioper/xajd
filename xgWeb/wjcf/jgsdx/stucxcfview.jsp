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
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="/xgxt/wjcf/shgc/shgcjs/shgcjs.js"></script>
		<html:form action="/wjcfjgsdxwh.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:message bundle="wjcfjgsdx" key="wjcf_jgsdx_cxcfview"/>
				</div>
			</div><%--
			<input type="hidden" name="pkVal"
				value="<bean:write name="pkVal"/>" />
			--%><table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							撤销处分详细信息
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" style="width: 20%">
						学号：
					</td>
					<td align="left" style="width: 30%">
						<bean:write name="rs"  property="xh"/>
					</td>
					<td align="right">
						年度：
					</td>
					<td align="left">
						<bean:write name="rs"  property="nd"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<bean:write name="rs" property="xm"/>
					</td>
					<td align="right">
						学年：
					</td>
					<td align="left">
						<bean:write name="rs" property="xn"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						性别：
					</td>
					<td align="left">
						<bean:write name="rs" property="xb" />
					</td>
					<td align="right">
						处分类别：
					</td>
					<td align="left">
						<bean:write name="rs" property="cflbmc"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						年级：
					</td>
					<td align="left">
						<bean:write name="rs" property="nj"/>
					</td>
					<td align="right">
						处分原因：
					</td>
					<td align="left">
						<bean:write name="rs" property="cfyymc"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc"/>
					</td>
					<td align="right">
					处分文号：
					<td align="left">
					<bean:write name="rs" property="cfwh"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name="rs"  property="zymc"/>
					</td>
						<td align="right">
						处分时间：
						</td>
						<td align="left">
						<bean:write name="rs" property="cfsj"/>
						</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						班级：
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc"/>
					</td>
						<td align="right">
						解除文号：
						</td>
						<td align="left">
						<bean:write name="rs" property="cxwh"/>
						</td>
				</tr>
				<tr style="height:22px">
					<td align="right">撤消申请时间：</td>
					<td align="left"><bean:write name="rs" property="cxsqsj"/></td>
					<td align="right">
						解除时间：
					</td>
					<td align="left">
						<bean:write name="rs" property="cxsj"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">学校审批：</td>
					<td align="left" colspan="3"><bean:write name="rs" property="spzt"/></td>
				</tr>
				<tr>
					<td align="right">
						撤消申请理由：
					</td>
					<td colspan="3" align="left"><html:textarea rows="7"  style="width:98%" name="rs" property="bz"  styleId="a" />
					</td>
				</tr>
				
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>	
			</div>
		</html:form>
	</body>
</html>
