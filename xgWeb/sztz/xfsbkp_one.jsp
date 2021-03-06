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
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：素质拓展 - 申报审核与考评 - 考评 - 单个考评
				</div>
			</div>
			<input type="hidden" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />	
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							单个学分申报考评
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						学号：
					</td>
					<td align="left">
					<html:text name="rs"  property="xh" styleId="xh" disabled="true"/>				
					</td>
					<td align="right">
						年度：
					</td>
					<td align="left">
						<html:text name="rs"  property="nd" styleId="nd" disabled="true"/>	
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
					<html:text name="rs"  property="xm" styleId="xm" disabled="true"/>	
					</td>
					<td align="right">
						学年：
					</td>
					<td align="left">
					<html:text name="rs"  property="xn" styleId="xn" disabled="true"/>	
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						性别：
					</td>
					<td align="left">
					<html:text name="rs"  property="xb" styleId="xb" disabled="true"/>	
					</td>
					<td align="right">
						学期：
					</td>
				    <td align="left">
					<html:text name="rs"  property="xq" styleId="xq" disabled="true"/>											
					</td>				
				</tr>
				<tr style="height:22px">
					<td align="right">
						年级：
					</td>
					<td align="left">
					<html:text name="rs"  property="nj" styleId="nj" disabled="true"/>						
					</td>
					<td align="right">
						拓展活动(项目)：
					</td>
					<td align="left">
					<html:text name="rs"  property="xmmc" styleId="xmmc"   style="width:80% " disabled="true"/>						
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
					<html:text name="rs"  property="xymc" styleId="xymc" disabled="true"/>						
					</td>
					<td align="right">
						所属科目：
					</td>
					<td align="left">
					<html:text name="rs"  property="kmm" styleId="kmm" disabled="true"/>						
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
					<html:text name="rs"  property="zymc" styleId="zymc" disabled="true"/>						
					</td>
					<td align="right">
						级别：
					</td>
					<td align="left">
					<html:text name="rs"  property="jb" styleId="jb" disabled="true"/>												
					</td>
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						班级：
					</td>
					<td align="left">
					<html:text name="rs"  property="bjmc" styleId="bjmc" disabled="true"/>						
					</td>
					<td align="right">
						性质：
					</td>
					<td align="left">
					<html:text name="rs"  property="xz" styleId="xz" disabled="true"/>						
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						获奖级别：
					</td>
					<td align="left">
						<html:select name="rs" property="hjjb" styleId="hjjb"
							style="width:90px;background-color:#FFFFFF">
							<html:options collection="hjjbList" property="en"
								labelProperty="cn" />
							<html:option value=""></html:option>
						</html:select>
					</td>
					<td align="right">
						学分：
					</td>
					<td align="left">
						<html:text name="rs" property="xf" styleId="pxjssj"
							onkeypress="return sztzNumInputValue(this,4,event)"
							onblur="chkInput(this,event)" maxlength="4" style="width:90px" />
					</td>
				</tr>
				<tr align="left" valign="top">
					<td colspan="4">
						备注：
						<br />
						<html:textarea name="rs" property="bz" rows="6" style="width:95% "></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="refreshForm('/xgxt/sztz_xfsb_sh.do?act=save');Close();window.dialogArguments.document.getElementById('search_go').click();"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
			<logic:equal value="view" name="result">
			<script>
				alert("操作成功!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:equal>
		</html:form>
	</body>
</html>
