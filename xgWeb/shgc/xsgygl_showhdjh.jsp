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
</script>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<body> 
<FORM method="POST" name="myform" action="eWebEditor/submit.jsp"> 
  <div class="title"> 
    <div class="title_img" id="title_m"> 当前所在位置： 宿舍精神文明建设 - 查看活动计划 </div> 
  </div> 
  <fieldset> 
  <legend> &nbsp;&nbsp;活 动 列 表&nbsp;&nbsp; </legend> 
  <logic:notEmpty name="rs"> 
  <table border="0" id="rsTable" width="100%"> 
    <logic:iterate id="list" name="rs"> 
    <tr onmouseover="rowOnClick(this)"> 
      <td> <a href="hd_Content.do?hd_Id=<bean:write name="list" property="hd_Id"/>" target="_blank"> <bean:write name="list"
											property="hd_title" /> </a> </td> 
      <td width="140"> <bean:write name="list" property="hd_pubtime" /> </td> 
      <td width="80"> <bean:write name="list" property="hd_puber" /> </td> 
    </tr> 
    </logic:iterate> 
  </table> 
  </logic:notEmpty> <logic:empty name="rs"> 
  <center>
     暂无活动计划 
  </center> 
  </logic:empty> 
  </fieldset> 
</FORM> 
</body>
</html>
