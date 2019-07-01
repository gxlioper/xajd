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
<script language="javascript" src="js/stuinfoFunction.js"></script>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<body> 
<div class="title"> 
  <div class="title_img" id="title_m"> 相关信息</div> 
</div> 
<html:form action="/stu_info_add.do" method="post">
<p align="center"> 信息修改条款 </p> 
<hr style="width:60%"> 
<center>
    <br /> 
  <br /> 
  <table width="80%" align="center" border="1" class=""> 
    <tr> 
      <td align="left"><bean:write name="content" filter="false"/></td> 
    </tr>    
  </table>   
  <table width="80%" align="bottom" border="0" style=""> 
     <tr>
    <td >    	
    	<input type="radio" name="xsyj" value="agree" id="xsyj"><font color="blue">接受</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="radio" name="xsyj" value="disagree" id="xsyj" checked><font color="blue">不接受</font>
    </td>
    </tr>    
  </table>  
</center> 
<%--<logic:equal value="yes" name="writeAble">--%>
		<div class="buttontool" id="btn"
			style="position: absolute;left:1%;top:100px" width="100%">
			<button type="button" class="button2"
				onclick="nextModi()"
				style="width:80px">
				确 定
			</button>
		</div>
<%--</logic:equal>--%>
</html:form>
<script type="text/javascript" src="js/bottomButton.js"></script>
</body>
</html>
