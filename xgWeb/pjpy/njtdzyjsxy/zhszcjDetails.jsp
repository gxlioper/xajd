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
	<body>
		<script language="javascript" src="js/function.js"></script>
       <html:form action="/njtdzhszcj" method="post">		
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优- 信息维护 - 综合评价成绩详细信息
				</div>
			</div>
			<table width="100%" align="center" class="tbstyle">
				<logic:iterate name="rs" id="s"  length="1">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="left">
						学号<bean:write name="s" property="xh"/> ，<bean:write name="s" property="xn"/>学年，
						第<bean:write name="s" property="xq"/>学期&nbsp;&nbsp;&nbsp;&nbsp;综合素质评价成绩详细信息
						</td>
					</tr>
				</thead>				
				<tr style="height:22px">
					<td width="25%" align="right">
						综合素质评价总成绩：
					</td>
					<td  colspan="3" >
						<bean:write name="s" property="zhszzf"/>
					</td>					
				</tr>
				<tr style="height:22px">
					<td align="right">
						思想道德素质：
					</td>
					<td  colspan="3">
						<bean:write name="s" property="ssddsz"/>
					</td>					
				</tr>
				<tr style="height:22px">
					<td align="right">
						科学文化素质：
					</td>
					<td  colspan="3">
						<bean:write name="s" property="kxwhsz"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						职业技能素质：
					</td>
					<td  colspan="3">
						<bean:write name="s" property="zyjnsz"/>
					</td>					
				</tr>
				<tr style="height:22px">
					<td align="right">
						身心素质：
					</td>
					<td  colspan="3">
						<bean:write name="s" property="sxsz"/>
					</td>					
				</tr>
				<tr style="height:22px">
					<td align="right">
						拓展素质：
					</td>
					<td>
						<bean:write name="s" property="tzsz"/>
					</td>					
				</tr>				
				</logic:iterate>
     		</table>
			<div class="buttontool" align="center">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>			
		</html:form>
	</body>
</html>
