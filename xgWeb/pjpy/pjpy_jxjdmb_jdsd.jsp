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
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<body>
	<div class="title">
	   <div class="title_img" id="title_m">
	      当前所在位置：评奖评优 - 参数设置 - 绩点标准设定
	   </div>
	</div>
		<html:form action="/pjpy_jxjdmb_jdsd">
	        <fieldset>
	        <legend>
	           绩点设置
	        </legend>
			<table class="tbstyle" align="center" style="width:98%">
			  <thead>
			    <tr>
				    <logic:iterate id="v" name="title" scope="request">
				         <td><bean:write name="v" /></td>
			   		</logic:iterate>
			   		<td>所需绩点标准</td>
		   		</tr>
			  </thead>
		      <tbody>
		        <logic:iterate id="v" name="rs">
		          <tr>
		            <logic:iterate id="s" name="v">
		              <td><bean:write name="s" /></td>
		            </logic:iterate>
		            <td><input type="text" name="<bean:write name="s" />"></td>
		          </tr>
		        </logic:iterate>
		      </tbody>
			</table>
			<div class="buttontool">
			   <button class="button2" onclick="pjpy_jdsd_save()">
			      保存
			   </button>
			   <button class="button2" onclick="window.close();return false;">
			     关闭
			   </button>
			</div>
			</fieldset>	
		</html:form>
	</body>
</html>
