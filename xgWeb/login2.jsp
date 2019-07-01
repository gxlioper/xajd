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
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
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
<link id="csss" rel="stylesheet" rev="stylesheet"
		href="style/login.css" type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<SCRIPT>  
<!--  
window.onresize  =  resetDIV;  
window.moveTo(0,0);
var sx = screen.availWidth;
var sy = screen.availHeight;
window.resizeTo(sx,sy);
function writeCookie(obj){
	var expire = "; expires=" + (new Date((new Date()).getTime() + 10 * 36000000)).toGMTString();
	if(obj != null){
		document.cookie = "style=" + obj.value + expire;
	}
	window.location.reload();
}

function initRedio(){
	var redi = document.getElementById("cssType");
	for(i = 0;i<redi.options.length;i++){
		if(redi.options[i].value == cookieValue){
			redi.options[i].selected = true;
		}
	}
	document.getElementById("userName").focus();
}
//-->
function getValue(s,n)      // 分解Cookie表达式s
{
var pairs=s.split("&");  // 分解成串数组
for (var i=0;i<pairs.length;i++)
{
var pairSplit=pairs[i].split("=");
if (pairSplit[0]==n)      // file://查找n
return pairSplit[1];  //  file://取到n的值
}
return "";
}
function getName()  // file://取Cookie中的姓名NAME
{
var s=document.cookie;  //  取所保存的Cookie
NAME0=getValue(s,"NAME");
$("userName").value=NAME0; 
password=getValue(s,"password");
$("password").value=password;  
}
function newCookie() // file://将Cookie中存姓名
{
var s=$("userName").value;
var v=$("password").value;
if (s!="" && s!=NAME0)   // 非空则保存
{
s="NAME="+s+"&password="+v;
s+="&;";
document.cookie=s;  // 将保存到客户机中
}
}  
</SCRIPT>
<body onload="initRedio()" SCROLLING=no> 

<a id="txt" href="login.jsp" target="_top" style="display:none">s</a> <html:form action="/chkLogin"> 
<input type ="hidden" name = "returnPage" id = "returnPage" value="notYz">
<div class="login"> 
  <div class=system_name></div> 
  <ul class="denglu"> 
    <li> 用户名：
      <input name="userName" id="userName" type="text" class="textbox" style="width:160px" maxlength="20" /> 
    </li> 
    <li> 密&nbsp;&nbsp; 码：
      <input name="password" id="password" type="password" class="textbox" style="width:160px" maxlength="20" /> 
    </li> 
    <input type ="hidden" name = "yzm" id = "yzm" value="" />
    <!--  <li> 验证码：
      <input name="yzm" type="text" class="textbox" style="width:90px" maxlength="4" onkeypress="if(event.keyCode==13)dl();" /> 
      <img src="yzm.jsp" border="0" align="absmiddle" /> </li> -->
    <li>
      <label> 
      <input type="radio" name="userType" value="teacher" checked /> 
      教师 </label> 
&nbsp;&nbsp;&nbsp;&nbsp; 
      <label> 
      <input type="radio" name="userType" value="student"/> 
      学生 </label> 
    </li> 
    <li> 
      <div class="login_dl" onclick="document.forms[0].submit();"></div> 
      <div class="login_gb" onclick="Close()"></div> 
    </li> 
    <li> 
      <select id="cssType" name="cssType" style="display:none" onchange="writeCookie(this)"> 
        <option value="skin1"> 风格1 </option> 
        <option value="skin2"> 风格2 </option> 
        <option value="skin3"> 风格3 </option> 
      </select> 
    </li> 
  </ul> 
  <div class="errors"> <logic:notEmpty name="commanForm" property="errMsg" scope="request"> <br /> 
    <bean:write name="commanForm" property="errMsg" scope="request" /> 
    <script> 
        txt.click();
        resetDIV();
        alert("<bean:write name="commanForm" property="errMsg" scope="request" />");
        </script> 
    </logic:notEmpty> </div> 
</div> 
</html:form> 
</body>
</html>
