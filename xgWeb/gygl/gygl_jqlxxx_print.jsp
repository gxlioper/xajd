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
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body onload="close();document.getElementById('print').click()">	
	<html:form action="/holiday_lsxx" method="post">
		<input type="hidden" name="printName" value="假期住宿安排" id="printName"/>
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
				<table width="100%" class="tbstyle" id="rsTable">
					<thead>
						<tr>
							<td colspan="4" align="center">
								假期留校学生信息
							</td>
						</tr>
					</thead>					
					<tr>
						<td align="right" width="15%">
							学号：
						</td>
						<td align="left">
							<bean:write name="rs" property="xh" />
						</td>
						<td align="right" width="20%">
							姓名：
						</td>
						<td align="left">
							<bean:write name="rs" property="xm" />
						</td>						
					</tr>					
					<tr>
						<td align="right">
							性别：
						</td>
						<td align="left">
							<bean:write name="rs" property="xb" />
						</td>
						<td align="right">
							学年：
						</td>
						<td align="left">
							<bean:write name="rs" property="xn" />
						</td>											
					</tr>
					<tr>
						<td align="right">
							年级：
						</td>
						<td align="left">
							<bean:write name="rs" property="nj" />
						</td>
						<td align="right">
							学期：
						</td>
						<td align="left">
							<bean:write name="rs" property="xqmc" />
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
							专业：
						</td>
						<td align="left">
							<bean:write name="rs" property="zymc" />
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
							联系电话：									
						</td>
						<td align="left">
							<bean:write name="rs" property="lxdh" />
						</td>
					</tr>
					<tr>
						<td align="right">
							留校宿舍：
						</td>
						<td align="left">
							<bean:write name="rs" property="ssbh" />
						</td>
						<td align="right">
							原住宿寝室：
						</td>
						<td align="left">
							<bean:write name="rs" property="yssbh" />
						</td>
					</tr>
					<tr>
						<td align="right">
							住宿开始时间：									
						</td>
						<td align="left">
							<bean:write name="rs" property="zskssj" />
						</td>
						<td align="right">
							住宿结束时间：									
						</td>
						<td align="left">
							<bean:write name="rs" property="zsjssj" />
						</td>
					</tr>
					<tr>
						<td align="right" height="100">
							留宿原因：					
						</td>
						<td align="left" colspan="3">
							<bean:write name="rs" property="lxyy" />
						</td>					
					</tr>
					<tr>
						<td align="right" height="100">
							备注：			
						</td>
						<td align="left" colspan="3">
							<bean:write name="rs" property="bz" />
						</td>					
					</tr>
					<input type="hidden" class="button2" name="button2" id="print"
						style="width:100px;display:none;" value="打 印 "
						onclick="expTab('rsTable',document.getElementById('printName').value,'')"/>
				</table>
			</logic:notEmpty>		
		</html:form>

  </body>
</html>
