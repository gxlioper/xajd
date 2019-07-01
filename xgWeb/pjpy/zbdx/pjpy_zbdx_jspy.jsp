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
    <base targer="_self" />
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
         <bean:message bundle="pjpy" key="pjpy_zbdx_jspy" />
       </div>
    </div>
    <html:form action="/pjpy_zbdx_xspy" method="post">
    <fieldset style="width:80%">
    	<legend>
    		选择评选班级：
    	</legend>
    	<table class="tbstyle" style="">
    	<thead>
    	<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;班级列表：&nbsp;&nbsp;&nbsp;&nbsp;</td>
    	<td>
    	<html:select property="bjdm" styleId="bjdm" onchange="document.forms[0].submit()" style="">
    		<html:option value=""></html:option>
    		<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
    	</html:select>
    	&nbsp;&nbsp;&nbsp;&nbsp;
    	<button class="button2" onclick="if(document.forms[0].bjdm.value==''){return false;}document.forms[0].submit()">查询</button>
    	</td></tr>
    	</thead>
    	</table>
    </fieldset>
    <logic:present name="doresult">
      <logic:equal name="doresult" value="true">
      	<script type="text/javascript">
      		alert("保存成功！");
      	</script>
      </logic:equal>
      <logic:equal name="doresult" value="false">
      	<script type="text/javascript">
      		alert("保存失败！");
      	</script>
      </logic:equal>
    </logic:present>
    <fieldset style="width:80%">
    <legend>
    	思想品德－教师评议&nbsp;&nbsp;&nbsp;&nbsp;记录数：${rsNum }
    </legend>
    <logic:notPresent name="rs">
    	<span style="font-size:14px">选择相应的班级，获得列表!如果是管理员用户则不能选择辅导员班级</span>
    </logic:notPresent>
     <logic:present name="rs">
      <table class="tbstyle">
         <thead>
         <tr>
           <logic:iterate name="title" id="s">
           	  <td>
           	  	${s}
           	  </td>
           </logic:iterate>
         </tr>
         </thead>
         <tbody>
		      <logic:iterate name="rs" id="v">
		      	<tr>
		      	    <td><input type="hidden" name="xh" id="xh"  value="${v.xh }"/>${v.xh }</td>
		      	    <td>${v.xm }</td>
		      	    <td>${v.xymc }</td>
		      	    <td>${v.zymc }</td>
		      	    <td>${v.bjmc }</td>
		      	    <td><input type="text" name="jspdcj" id="jspdcj" maxlength="3" onblur="checkCj(this)" onkeyup="onlyNum(this)" value="${v.jspdcj}"></td>
		      	</tr>
		      </logic:iterate>
		  </tbody>    
	  </table>
	  <br />
	  <div style="buttontool" align="center">
	     <button class="button2" onclick="jsSubmitResult()" style="width:10%">提交评议</button>
	     <button class="button2" onclick="jsResetResult()" style="width:10%">重置输入</button>
	  </div>
	  </logic:present>
	  </fieldset>	      
    </html:form>
  </body>
</html:html>
