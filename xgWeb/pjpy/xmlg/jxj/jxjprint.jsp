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
	<!-- ��ӡ�ؼ�begin -->
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
		.noPrin{display:none;}
	</style>
	<!-- end -->	
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  </head>
  <body>
    <html:form action="/xmlgpjpy">
      <div align="center" style="font-size:22px;font:'����' "><b>${xxmc }&nbsp;${rs.xn }ѧ��<br/>��ѧ�������</b></div>
<br/>
<div>
<table width="100%" class="printstyle">
  <tr>
  	<th height="30" colspan="2" width="12%" scope="col">ѧ   &nbsp;&nbsp;&nbsp; ��</th>
    <td height="30" colspan="2" style="width:14%" scope="col" align="center">&nbsp;<bean:write name='rs' property="xh"/></td>
    <th height="30" colspan="2" width="14%" scope="col">��   &nbsp;&nbsp;&nbsp; ��</th>
    <td height="30" colspan="2" style="width:14%" scope="col" align="center">&nbsp;<bean:write name='rs' property="xm"/></td>
    <th height="30" width="11%" scope="col">��  ��</th>
    <td height="30" colspan="2" scope="col" style="width:12%" align="center">&nbsp;<bean:write name='rs' property="xb"/></td>
    <th height="30" width="12%" scope="col">��������</th>
    <td height="30" colspan="2" scope="col" width="12%" align="center">&nbsp;<bean:write name='rs' property="csrq"/></td>
  </tr>
  <tr>
    <th height="30" colspan="2" scope="row">ѧ   &nbsp;&nbsp;&nbsp; Ժ</th>
    <td colspan="5" align="center">&nbsp;<bean:write name='rs' property="xymc"/></td>
    <th colspan="2"><div align="center">רҵ�༶</div></th>
    <td colspan="5" width="13%" align="center">&nbsp;<bean:write name='rs' property="zymc"/><br/><bean:write name='rs' property="bjmc"/></td>
  </tr>
  <tr>
    <th height="30" colspan="2" width="14%" scope="col">�ۺϲ���<br/>ѧ������</th>
    <td height="30" colspan="5" style="width:20%" scope="col" align="center">&nbsp;${rs.xnpm }</td>
    <th height="30" width="11%" colspan="2" scope="col">ѧ������<br/>Υ�ʹ���</th>
    <td height="30" colspan="5" scope="col" style="width:15%" align="center">&nbsp;${rs.cf }</td>
  </tr>
  <tr>
    <th height="30" colspan="2" width="14%" scope="col">����ˮƽ</th>
    <td height="30" colspan="5" style="width:20%" scope="col" align="center">&nbsp;${rs.wysp }</td>
    <th height="30" width="11%" colspan="2" scope="col">�����ˮƽ</th>
    <td height="30" colspan="5" scope="col" style="width:15%" align="center">&nbsp;${rs.jsjsp }</td>
  </tr>
  <tr>
    <th colspan="4" align="center">���뽱ѧ��ȼ�</th>
    <td colspan="10" align="center">&nbsp;<bean:write name='rs' property="jxjmc"/></td>
  </tr>
  <tr>
    <th scope="row" style="width:6%">
    <p>��</p>
    <p>��</p>
    <p>��</p>
    <p>��</p>
    </th>
    <th colspan="13" scope="row">
    	<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.jlqk }</p>
   </th>
  </tr>
  <tr>
    <th scope="row" style="width:6%">
    <p>��</p>
    <p>��</p>
    <p>��</p>
    <p>��</p>
    </th>
    <th colspan="13" scope="row">
    	<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }</p>
   </th>
  </tr>
  <tr>
    <th scope="row" style="width:6%">
    <p>����</p>
    <p><bean:message key="lable.xsgzyxpzxy" /></p>
    <p>���</p>
    </th>
    <th colspan="13" scope="row">
    	<br/><br/><br/><br/>
    	<p align="center">${rs.xyshyj }</p>
    	<p align="right">(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  		<p align="right">��  &nbsp;&nbsp;&nbsp;��  &nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  </tr>
  <tr>
    <th scope="row" style="width:6%">
    <p>��ѧ</p>
    <p>����</p>
    <p>��ί</p>
    <p>Ա��</p>
    <p>���</p>
    </th>
    <th colspan="13" scope="row">
    	<br/><br/><br/><br/>
    	<p align="center">${rs.xxshyj }</p>
    	<p align="right">(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  		<p align="right">��  &nbsp;&nbsp;&nbsp;��  &nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  </tr>
</table>
<div>
</div>
<div align="center" class='noPrin'>
	<button type="button" class='button2'  onclick="WebBrowser.ExecWB(8,1);return false;">ҳ������</button>
    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(7,1);return false;">��ӡԤ��</button>
    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(6,6);return false;">ֱ�Ӵ�ӡ</button> 
    </div>
    </div>
   </html:form>
  </body>
</html:html>
