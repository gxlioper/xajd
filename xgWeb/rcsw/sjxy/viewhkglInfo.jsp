<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="Content-Language" content="GBK" />
<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript">

	function loadnr(){
		document.getElementById('div').innerHTML=document.getElementById("content1").value;
	}

</script>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<body onload="loadnr()">
<FORM method="POST" id="myform" action=""> 
  <div class="title"> 
    <div class="title_img" id="title_m"> 相关信息 </div> 
  </div>  
  <INPUT type="hidden" name="content1" value="<bean:write name="info"/>" id="content1">  
  <TABLE class="tbstyle" width="100%"> 
    <TR> 
      <TD  width="100%"> 
   		<logic:present name="info">
   			<div id="div">
   				
   			</div>
   		</logic:present>
      </TD> 
    </TR> 
  </TABLE> 
</FORM> 
</body>
</html>
