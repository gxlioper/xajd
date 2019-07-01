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
<html:html>
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<base target="_self">	
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/pjpyFunction.js"></script>
<body style="font-size:14px" onload="pjpy_initCheck()" >
		<table width="100%" class="tbstyle">
					<thead>
					<div align="right">
						<logic:equal value="up" name="view">
							<input  id="up" value="上一条" disabled="true">
						</logic:equal>
						<logic:notEqual value="up" name="view">
							<input  id="up" value="上一条"
								onclick="showTips('刷新数据中，请稍候...');pjpy_jxjSh_ChangeRecord('up');">
						</logic:notEqual>
						&nbsp; &nbsp;
						<logic:equal value="next" name="view">
							<input  id="next" value="下一条" disabled="true">
						</logic:equal>
						<logic:notEqual value="next" name="view">
							<input  id="next" value="下一条"
								onclick="showTips('刷新数据中，请稍候...');pjpy_jxjSh_ChangeRecord('next');">
						</logic:notEqual>
						&nbsp; &nbsp;&nbsp; &nbsp;
						<logic:equal value="true" name="sel">
							<input type="checkbox" id="selected" onclick="pjpy_Shot(this);"
								checked="true" />&nbsp;选中
					    </logic:equal>
						 <logic:notEqual value="true" name="sel">
							<input type="checkbox" id="selected" onclick="pjpy_Shot(this);" />&nbsp;选中
					    </logic:notEqual>
					</div>					
				</thead>
     </table>
</body>

</html:html>
