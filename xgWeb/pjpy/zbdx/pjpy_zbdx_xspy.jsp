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
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
  	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script type="text/javascript" src="js/sharedFunction.js"></script>
	<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/pjpyFunction.js"></script>
  </head>
  
  <body>
  	<div class="title">
  		<div class="title_img" id="title_m">
  		  <bean:message key="pjpy_zbdx_xspy" bundle="pjpy" />
  		</div>
  	</div>
    <html:form action="/pjpy_zbdx_xspy" method="post">
      <fieldset>
      	<legend>
      		思想品德－学生评议&nbsp;&nbsp;&nbsp;&nbsp;记录数：${rsNum }
      	</legend>
      	<table class="tbstyle" style="width:80%" id="pytable">
      	<thead align="center">
      	   <tr>
	      	<logic:iterate name="title" id="a" >
	      		<td><bean:write name="a" /></td>
	      	</logic:iterate>
	      	</tr>
      	</thead>
      	<tbody>
      		<logic:iterate name="rs" id="a">
      			<tr>
      			    <td>
      					<input type="hidden" name="xh" id="xh" value="${a.xh }">${a.xh }
      				</td>
      				<td>${a.xm }</td>
      				<td>${a.xymc }</td>
      				<td>${a.zymc }</td>
      				<td>${a.bjmc }</td>
      				<td><input type="text" name="xspdcj" id="xspdcj" maxlength="3" onblur="checkCj(this)" onkeyup="onlyNum(this)" value="${a.xspdcj }"/></td>
      			</tr>
      		</logic:iterate>
      	</tbody>
      	</table>
      </fieldset>
      <div class="buttontool" align="center">
         <button class="button2" onclick="submitResult()">
         	提交评议
         </button>
         <button class="button2" onclick="resetResult()">
              重置输入
         </button>
      </div>
    </html:form>
  </body>
</html:html>
