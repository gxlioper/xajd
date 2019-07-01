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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link rel="stylesheet" rev="stylesheet" href="style/calendar.css"
		type="text/css" media="all" />
	<script language="javascript">
</script>
	<body bgcolor="#99CCFF">
		<script language="javascript" src="style/function.js"></script>
		<script type="text/javascript" src="style/calendar.js"></script>
		<script type="text/javascript" src="style/calendar-zh.js"></script>
		<script type="text/javascript" src="style/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div id="title">
				<div id="title_l"></div>
				<div id="title_m">
					<span id="tipFollow"></span>
				</div>
				<div id="title_r"></div>
			</div>
			<div id="main" style="heigth:100px;">
				<div id="search_m" style="heigth:100px;">
					<div id="result">
						<div class="searchcontent">
							<center>
								<font size="5"><b>简介说明图</b></font>
							</center>
							<br/>
							<center>
								<img src="/xgxt/images/xsxx/bxjj.jpg" style="width:800px;height:600px;top:5px"/>
							</center>
							<br/>
							<center>
								<font size="5"><b>保险流程说明图</b></font>
							</center>
							<br></br>
							<center><img src="/xgxt/images/xsxx/bxlc.jpg" style="width:800px;height:600px;top:5px"/></center>
							
						</div>
					</div>
				</div>
			</div>
		</html:form>
	</body>
</html>
