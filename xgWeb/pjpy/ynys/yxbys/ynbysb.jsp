<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

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
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>	

	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  </head>
  <body>
    <html:form action="/pjpyynyswh" method="post">
      <div align="center" style="font-size:18px;font:'����' ">${tit }</div>
<br/>
<div>
<table width="98%" class="printstyle">
  <tr>
    <th height="30" colspan="2" width="10%" scope="col">��  ��</th>
    <td height="30" colspan="2" style="width:15%" scope="col" align="center">&nbsp;<bean:write name="rs" property="xm"/></td>
    <th height="30" width="11%" scope="col">��  ��</th>
    <td height="30" colspan="2" scope="col" style="width:10%" align="center">&nbsp;<bean:write name="rs" property="xb"/></td>
    <th height="30" width="10%" scope="col">��  ��</th>
    <td height="30" colspan="2" scope="col" align="center">&nbsp;<bean:write name="rs" property="mz"/></td>
    <th height="30" width="10%" scope="col">��������</th>
    <td height="30" colspan="2" style="width:15%" scope="col" align="center">&nbsp;<bean:write name="rs" property="csrq"/></td>
  </tr>
  <tr>
  	<th height="30" colspan="2" width="14%" scope="col">��Դ��</th>
    <td height="30" colspan="3" style="width:20%" scope="col" align="center">&nbsp;<bean:write name="rs" property="syd"/></td>
    <th height="30" colspan="2" scope="row">������ò</th>
    <td colspan="3" align="center">&nbsp;<bean:write name="rs" property="zzmm"/></td>
    <th><div align="center">����״��</div></th>
    <td colspan="2" align="center">&nbsp;<bean:write name="rs" property="jkzk"/></td>
  </tr>
  <tr>
    <th height="30" colspan="2" scope="row">��ѧרҵ</th>
    <td colspan="5" align="center">&nbsp;<bean:write name="rs" property="zymc"/></td>
    <th height="30" colspan="2" scope="row">��ѧʱ��</th>
    <td colspan="4" align="center">&nbsp;<bean:write name="rs" property="rxrq"/></td>
  </tr>
  <tr>
    <th height="30" colspan="3" scope="row">��ͥ��ϸ��ַ</th>
    <td colspan="10" align="center">&nbsp;<bean:write name="rs" property="jtdz"/></td>
  </tr>
  <tr>
    <th scope="row" style="width:6%">
    <p>&nbsp;</p>
    <p>��</p>
    <p>��</p>
    <p>��</p>
    <p>��</p>
    <p>&nbsp;</p>
    </th>
    <th colspan="12" scope="row"><p  align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="yxsj"/></p>
   </th>
  </tr>
  <tr>
    <th scope="row" style="width:6%" >
    <p>��</p>
    <p>��</p>
    <p>��</p>
    <p>��</p>
    </th>
    <th colspan="12" scope="row">
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="bjyj"/></div>
    <p>&nbsp;</p>
    <p  align="right">�����Σ�ǩ������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           �� &nbsp;&nbsp;  ��  &nbsp;&nbsp;  ��&nbsp;&nbsp;</p>
   </th>
  </tr>
  <tr>
  	<th scope="row" style="width:6%">
  		<p>Ժ</p>
    	<p>ϵ</p>
    	<p>��</p>
    	<p>��</p>
    	<p>��</p>
    	<p>��</p>
  	</th>
  	<th colspan="6">
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
  		<div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="yxyj"/></div>
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
    	<p align="right">������ǩ�֣����£���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    	<p align="right">�� &nbsp;&nbsp;  ��  &nbsp;&nbsp;  ��&nbsp;&nbsp;</p>
  	</th>
  	<th scope="row" style="width:6%">
  		<p>ѧ</p>
    	<p>У</p>
    	<p>��</p>
    	<p>��</p>
    	<p>��</p>
    	<p>��</p>
  	</th>
  	<th colspan="5">
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
  		<div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xxyj"/></div>
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
    	<p align="right">ѧУ�����£���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    	<p align="right">�� &nbsp;&nbsp;  ��  &nbsp;&nbsp;  ��&nbsp;&nbsp;</p>
  	</th>
  </tr>
  <tr>
    <th scope="row" style="width:6%">
    <p>ʡ</p>
    <p>��</p>
    <p>��</p>
    <p>��</p>
    <p>��</p>
    <p>��</p>
    </th>
    <th colspan="12" scope="row">
    <p>&nbsp;</p>
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
  		<div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="jytyj"/></div>
  		<p>&nbsp;</p>
    <p  align="right">���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           �� &nbsp;&nbsp;  ��  &nbsp;&nbsp;  ��&nbsp;&nbsp;</p>
    
   </th>
  </tr>
</table>
 	&nbsp;&nbsp;����ʡ������ѧ�����Ʊ�    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
 	<logic:notEmpty name="rs">
 		�������: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="tbrq"/>
 	</logic:notEmpty>
 	<logic:empty name="rs">
 		�������: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;  ��  &nbsp;&nbsp;  ��&nbsp;&nbsp;
 	</logic:empty>
 	</div>
    </html:form>
  </body>
</html:html>
