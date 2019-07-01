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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />	
	 <style media="print">
	     .tbstyle{border-color: black;border:hidden;}
	   </style>	
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/webPrint.js"></script>
		<div id="rsTable" style="font-size:50px;">
					<h4 align="center">
						证 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 明
					</h4>
			
					<h5 align="left">
						 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已批准
					   <INS> &nbsp;<bean:write name="rsm" property="xymc" />&nbsp;</INS>
						 学科部
						 <INS> &nbsp;<bean:write name="rsm" property="zymc" />&nbsp;</INS>
						 专业 
						 <INS>&nbsp;<bean:write name="rsm" property="bjmc" /> &nbsp; </INS>班
						 <INS>&nbsp;<bean:write name="rsm" property="xm" /> &nbsp;</INS>
						同学(学号:<bean:write name="rsm" property="xh" />)
						 <INS> &nbsp;<bean:write name="rsm" property="xn" /> </INS>
						学年因
						 <INS> &nbsp;<bean:write name="rsm" property="wzyy" /> &nbsp;</INS>
						原因校外住宿。
					</h5>
			
			<h5 align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特此证明</h5>
			
			<h5 align="right">科技<bean:message key="lable.xsgzyxpzxy" />学工处</h5>
			
			<h5 align="right">年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</h5>
		</div>
		<div class="buttontool" align="center">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="打印" name="button_print"
				onclick="expTab('rsTable','','')">
		</div>
	</body>
</html>


