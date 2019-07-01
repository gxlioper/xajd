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
	<body onload="shReadOnly();">
		<script language="javascript" src="js/function.js"></script>
        <script language="javascript" src="js/sztzFunction.js"></script>		
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/tzbj_sh.do" method="post">		
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:write name="tips" scope="request"/>
				</div>
			</div>
			<input type="hidden" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
			<input type="hidden" name="shType"
				value="<bean:write name="shType" scope="request"/>" />	
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							单个审核
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						学号：
					</td>
					<td align="left">
						<bean:write name="rs" property="xh"/>
					</td>
					<td align="right">
						年度：
					</td>
					<td align="left">
						<bean:write name="rs" property="nd"/>
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
						<bean:write name="rs" property="xb"/>
					</td>
					<td align="right">
						学期：
					</td>
					<td align="left">
						<bean:write name="rs" property="xqmc"/>
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
						素质拓展班级：
					</td>
					<td align="left">
						<bean:write name="rs" property="mc"/>
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
						 申请时间：
					</td> 
					<td align="left">
						<bean:write name="rs" property="sqsj"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc"/>
					</td>
					<td align="right">
						 审核：
					</td>
					<td align="left">
						<html:select property="yesNo">
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
						<bean:write name="rs" property="bjmc"/>
					</td>
					<td align="right">
						
					</td>
					<td align="left">
					   
					</td>				
				</tr>
				<tr style="height:22px">
					<td align="right">
						申请理由：
					</td>
					<td align="left"colspan="3" style="width:88%">
						<bean:write name="rs" property="sqly"/>
					</td>
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						辅导员意见：
					</td>
					<td align="left"colspan="3">
						<html:textarea name='rs' property='fdyyj' styleId="fdyyj"
								style="width:99%" rows='5'/>
					</td>
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />意见：
					</td>
					<td align="left"colspan="3">
						<html:textarea name='rs' property='xyyj' styleId="xyyj"
								style="width:99%" rows='5' />
					</td>
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						学校意见：
					</td>
					<td align="left"colspan="3">
						<html:textarea name='rs' property='xxyj' styleId="xxyj"
								style="width:99%" rows='5' />
					</td>					
				</tr>
     		</table>
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="refreshForm('/xgxt/tzbj_sh.do?doType=save');"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
			<logic:equal value="yes" name="done">
			<script>
				alert("操作成功!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:equal>
		</html:form>
	</body>
</html>
