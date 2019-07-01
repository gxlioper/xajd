<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <base target="_self" />
    
    <title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	
	<%
	response.setHeader("Pragma","no-cache");
	response.setHeader("cache-control","no-cache");
	response.setDateHeader("expires",0);
	 %>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	 <script type="text/javascript" src="js/function.js"></script>
	 
  </head>
  
  <body>
  	<div class="title"> 
    	<div class="title_img" id="title_m"> <bean:message key="tips.N15kk" /></div> 
  	</div>
  	<logic:notPresent name="rs">
  		<div align=center><p>没有相关旷课记录</p></div>
  	</logic:notPresent>
    <logic:present name="rs">
    	<table class="tbstyle" style="width:100%">
    		<thead>
	    		<tr align=center>
	    			<logic:iterate id="titlename" name="title">
	    				<td><bean:write name="titlename" /></td>
	    			</logic:iterate>
	    		</tr>
	    	</thead>
	    	<logic:iterate id="element" name="rs">
	    	 <tr align=center>
	    	 	<logic:iterate id="ele" name="element">
		    		<td>
		    			<bean:write name="ele" /> 
		    		</td>
		    	</logic:iterate>	
	    	 </tr>	
	    	</logic:iterate>	
    	</table>
    </logic:present>
  </body>
</html:html>
