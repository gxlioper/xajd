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
    <html:form action="/dxjxjsp">
      <div align="center" style="font-size:18px;font:'����' ">${title }</div>
<br/>

<table width="98%" class="printstyle">
  <tr>
    <th height="35" colspan="2" width="15%" scope="col">ѧ���</th>
    <td height="30" colspan="2" style="width:10%" scope="col" align="center">&nbsp;<bean:write name='rs' property="xymc"/></td>
    <th height="30" width="11%" scope="col">��  ��</th>
    <td height="30" colspan="2" scope="col" style="width:15%" align="center">&nbsp;<bean:write name='rs' property="bjmc"/></td>
    <th height="30" width="10%" colspan="2" scope="col">����</th>
    <td height="30" colspan="" scope="col" align="center">&nbsp;<bean:write name='rs' property="xn"/></td>
    <td rowspan="3" scope="col" width="15%" align="center">��Ƭ</td>
  </tr>
  <tr>
    <th height="35" colspan="2" scope="row">�걨��ѧ��ȼ�</th>
    <td colspan="2" align="center">&nbsp;<bean:write name='rs' property="bzr"/></td>
    <th><div align="center">ѧ  ��</div></th>
    <td colspan="2" align="center">&nbsp;<bean:write name='rs' property="bzxm"/></td>
    <th colspan="2"><div align="center">���Һ�</div></th>
    <td colspan="" height="30" width="13%" align="center">&nbsp;<bean:write name='rs' property="tzs"/></td>
    
  </tr>
  <tr>
  	<th height="35" colspan="4" scope="row">ְҵ����������</th>
    <td colspan="1" align="center">&nbsp;<bean:write name='rs' property="bzr"/></td>
    <th colspan="2" align="center">��  ��</th>
    <th height="30" colspan="3" scope="row">ȫ�๲&nbsp;&nbsp;&nbsp;���ŵ�&nbsp;&nbsp;&nbsp;��</th>
  </tr>
  <tr>
  	<th height="30" colspan="4" scope="row">ְҵ��������������</th>
    <td colspan="1" align="center">&nbsp;<bean:write name='rs' property="bzr"/></td>
    <th colspan="4" align="center">�ɳ�����չ���ʲ�����</th>
    <th height="30" colspan="2" scope="row">&nbsp;</th>
  </tr>
  <tr>
  	<th height="30" colspan="4" scope="row">�ۺϲ����ܷ�</th>
    <td colspan="1" align="center">&nbsp;</td>
    <th colspan="2" align="center">��  ��</th>
    <th height="30" colspan="4" scope="row">ȫ�๲&nbsp;&nbsp;&nbsp;���ŵ�&nbsp;&nbsp;&nbsp;��</th>
  </tr>
    <tr>
    <th scope="row" colspan="2" style="width:15%">
    <p align="center">
     ������<br/>��
    </p>
    </th>
    <th scope="row" colspan="9" >
    <br/><br/><br/><br/><br/>
    <p align="right">ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  	</tr>
  <tr>
    <th scope="row" colspan="2" style="width:14%">
    <p align="center">
      �༶<br/>����<br/>С��<br/>���
    </p>
    </th>
    <th scope="row" colspan="9" >
    <br/><br/><br/><br/><br/>
    <p align="right">������ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  	</tr>
  	<tr>
    <th scope="row" colspan="2" style="width:14%">
    <p align="center">
     <bean:message key="lable.xsgzyxpzxy" /><br/>�ۺ�<br/>����<br/>С��<br/>���
    </p>
    </th>
    <th scope="row" colspan="9" >
    <br/><br/><br/><br/><br/>
    <p align="right">(<bean:message key="lable.xsgzyxpzxy" />����֧����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  	</tr>
  	<tr>
    <th scope="row" colspan="2" style="width:14%">
    <p align="center">
     ѧ����<br/>���
    </p>
    </th>
    <th scope="row" colspan="9" >
    <br/><br/><br/><br/><br/>
    <p align="right">(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  	</tr>
  	<tr>
    <th scope="row" colspan="2" style="width:14%">
    <p align="center">
     <bean:message key="lable.xsgzyxpzxy" /><br/>���
    </p>
    </th>
    <th scope="row" colspan="9" >
    <br/><br/><br/><br/><br/>
    <p align="right">(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  	</tr>
	</table>
    </html:form>
  </body>
</html:html>
