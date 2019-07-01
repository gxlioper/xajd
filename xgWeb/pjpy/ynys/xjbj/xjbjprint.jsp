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
<logic:notEmpty name="rs"><div align="right"><bean:write name="rs" property="tbrq"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></logic:notEmpty>
<logic:empty name="rs"><div align="right">������ڣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;  ��  &nbsp;&nbsp;  ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></logic:empty>
<table width="98%" class="printstyle">
  <tr>
    <th height="25" colspan="2" style="width:8%" scope="col">Ժ (ϵ)</th>
    <td height="25" colspan="3" style="width:20%" scope="col" align="center">&nbsp;<bean:write name="rs" property="xymc"/></td>
    <th height="25" width="8%" scope="col">��  ��</th>
    <td height="25" colspan="4" scope="col" style="width:18%" align="center">&nbsp;<bean:write name="rs" property="nj"/></td>
    <th height="25" width="10%" colspan="2" scope="col">��&nbsp;&nbsp;&nbsp;��</th>
    <td height="25" colspan="3" scope="col" align="center">&nbsp;<bean:write name="rs" property="bjmc"/></td>
  </tr>
  <tr>
  	<th height="25" colspan="2" style="width:8%" scope="col">ר &nbsp;&nbsp; ҵ</th>
    <td height="25" colspan="4" style="width:20%" scope="col" align="center">&nbsp;<bean:write name="rs" property="zymc"/></td>
    <th height="25" colspan="2" scope="row" width="9%" >�༶����</th>
    <td colspan="2"  align="center" style="width:5%">&nbsp;<bean:write name="rs" property="bjrs"/></td>
    <th colspan="2"><div align="center">������</div></th>
    <td colspan="3" align="center">&nbsp;<bean:write name="rs" property="bzr"/></td>
  </tr>
  <tr>
    <th colspan="2" scope="row" style="width:8%">
    <p>��</p>
    <p>��</p>
    <p>��</p>
    <p>��</p>
    <p>��</p>
    <p>��</p>
    </th>
    <th colspan="13" scope="row"><p  align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="bjdbqk"/></p>
   </th>
  </tr>
  <tr>
    <th colspan="2" scope="row" style="width:8%" >
    <p>������</p>
    <p>��&nbsp;&nbsp;&nbsp;��</p>
    </th>
    <th colspan="13" scope="row">
    <p>&nbsp;</p><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="bzryj"/></div><p>&nbsp;</p>
    <p align="right">������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           �� &nbsp;&nbsp;  ��  &nbsp;&nbsp;  ��&nbsp;&nbsp;</p>
   </th>
  </tr>
  <tr>
    <th colspan="2" scope="row" style="width:8%" >
    <p>����Ա</p>
    <p>��&nbsp;&nbsp;&nbsp;��</p>
    </th>
    <th colspan="13" scope="row">
    <p>&nbsp;</p><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="fdyyj"/></div><p>&nbsp;</p>
    <p align="right">����Աǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           �� &nbsp;&nbsp;  ��  &nbsp;&nbsp;  ��&nbsp;&nbsp;</p>
   </th>
  </tr>
  <tr>
    <th colspan="2" scope="row" style="width:8%" >
    <p>Ժ(ϵ)</p>
    <p>��&nbsp;&nbsp;&nbsp;��</p>
    </th>
    <th colspan="13" scope="row">
    <p>&nbsp;</p><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="yxyj"/></div>
<p>&nbsp;</p>
    <p align="right">������ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           �� &nbsp;&nbsp;  ��  &nbsp;&nbsp;  ��&nbsp;&nbsp;</p>
   </th>
  </tr>
  <tr>
    <th colspan="2" scope="row" style="width:8%" >
    <p>ѧ����</p>
    <p>��&nbsp;&nbsp;&nbsp;��</p>
    </th>
    <th colspan="13" scope="row">
    <p>&nbsp;</p><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xxyj"/></div>
    <p>&nbsp;</p>
    <p align="right">������ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           �� &nbsp;&nbsp;  ��  &nbsp;&nbsp;  ��&nbsp;&nbsp;</p>
   </th>
  </tr>
  <tr>
    <th colspan="2" scope="row" style="width:8%" >

    <p>ѧ&nbsp;&nbsp;&nbsp;Ժ</p>
    <p>��&nbsp;&nbsp;&nbsp;��</p>

    </th>
    <th colspan="13" scope="row">
    <p>&nbsp;</p><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xyyj"/></div>
    <p>&nbsp;</p>
    <p align="right">������ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           �� &nbsp;&nbsp;  ��  &nbsp;&nbsp;  ��&nbsp;&nbsp;</p>
   </th>
  </tr>
  <tr>
    <th colspan="2" scope="row" style="width:8%" >
    	<p>˵&nbsp;&nbsp;&nbsp;��</p>
    </th>
    <th colspan="13" scope="row" align="center">
    <p align="left">�༶������һ��������������<bean:message key="lable.xsgzyxpzxy" />ѧ����ѧ�������취�������µڶ�ʮһ����һ��д��</p>
   </th>
  </tr>
</table>
 	<div align="right">��������<bean:message key="lable.xsgzyxpzxy" />ѧ�����Ʊ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
    </html:form>
  </body>
</html:html>
