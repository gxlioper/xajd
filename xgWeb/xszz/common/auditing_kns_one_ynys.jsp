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
		<title><bean:message key="lable.title" />
		</title>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
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
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yzt(){
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("通过" == xxsh) && (isXX != "is")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			
			refreshForm('/xgxt/auditing_kns_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 困难生审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" id="xxsh" name="xxsh" value="<bean:write name="xxsh" />" />
			<input type="hidden" id="isXX" name="isXX" value="<bean:write name="isXX" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">	
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						学号：
					</td>
					<td align="left">
						<bean:write name="XH" />
						<input type="hidden" name="tName"
							value="<bean:write name="tName" />" />
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
						学期：
					</td>
					<td align="left">
						<bean:write name="XQ" />
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
						联系电话：
					</td>
					<td align="left">
						<bean:write name='LXDH' />
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
						申请时间：
					</td>
					<td align="left">
						<bean:write name="SQSJ" />
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
						班级：
					</td>
					<td align="left">
						<bean:write name="BJMC" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						民族：
					</td>
					<td align="left">
						<bean:write name="MZMC" />
					</td>
					<td align="right">
						政治面貌：
					</td>
					<td align="left">
						<bean:write name="ZZMMMC" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						身份证号：
					</td>
					<td align="left">
						<bean:write name="SFZH" />
					</td>
					<td align="right">
						家庭人均年收入：
					</td>
					<td align="left">
						<bean:write name="JTRJSR" />
					</td>
				</tr>
				<tr>
					<td align="right">
						困难生等级：
					</td>
					<td colspan="3">
						<html:select name="rs" property="kncddm">
							<html:option value=""></html:option>
							<html:options collection="knsdjList" property="dm"
								labelProperty="mc" />
						</html:select>
					</td>
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						审核结果：
					</td>
					<td align="left">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				<logic:equal name="isXX" value="is">	
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />审核结果：
					</td>
					<td align="left">
						<bean:write name="XYSH" />
					</td>
				</logic:equal>
				<logic:equal name="isXX" value="no">
					<td align="left" colspan="2">
					</td>
				</logic:equal>
				</tr>
				<tr style="height:22px">
					<td align="right">
						申请理由：
					</td>
					<td align="left" colspan="3">
						<bean:write name="SQYY" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						备注：
					</td>
					<td align="left" colspan="3">
						<bean:write name="BZ" />
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="yzt();"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
