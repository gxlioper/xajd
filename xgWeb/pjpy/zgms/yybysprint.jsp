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
  <!-- ��ӡ�ؼ�begin -->
<object id="WebBrowser" width=0 height=0
	classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
<style media='print'>
		.noPrin{display:none;}
	</style>
<!-- end -->
  <body>
    <html:form action="/dxjxjsp">
      <div align="center" style="font-size:18px;font:'����' "><b>�й�����<bean:message key="lable.xsgzyxpzxy" />�����ҵ����ѡ�Ƽ���</b></div>
<br/>
<div>

<table width="600px" align="center" class="printstyle">
   &nbsp;&nbsp;&nbsp;Ժ��ϵ����&nbsp;${rs.xymc }&nbsp;רҵ��&nbsp;${rs.zymc }&nbsp; ѧ����&nbsp;${rs.pycc }&nbsp; &nbsp;${rs.y }&nbsp;�� &nbsp;${rs.m }&nbsp; �� &nbsp;${rs.r }&nbsp; ��
   <tr>
    <td width="74">  <div align="center"> ��&nbsp;&nbsp;&nbsp;��</div></td>
    <td width="75"><div align="center">${rs.xm }</div></td>
    <td width="43"><div align="center">�Ա�</div></td>
    <td width="55"><div align="center">${rs.xb }</div></td>
    <td width="55"><div align="center">����</div></td>
    <td colspan="2" width="200"><div align="center">${rs.mzmc }</div><div align="center"></div></td>
    <td width="69"><div align="center">��������</div></td>
    <td width="100"><div align="center">${rs.csrq }</div></td>
  </tr>
  <tr>
    <td height="26"><div align="center">��&nbsp;Դ&nbsp;��</div></td>
    <td><div align="center">${rs.syd }</div></td>
    <td><div align="center">ְ��</div></td>
    <td colspan="2"><div align="center">${rs.drzw }</div>      <div align="center"></div></td>
    <td width="90"><div align="center">������ò</div></td>
    <td colspan="3"><div align="center">${rs.zzmmmc }</div>      <div align="center"></div>      <div align="center"></div></td>
  </tr>
  <tr>
    <td height="25"><div align="center">��ͥ��ַ</div></td>
    <td colspan="3"><div align="center">${rs.jtdz }</div>      <div align="center"></div>      <div align="center"></div></td>
    <td colspan="2"><div align="center">��ϵ�绰</div>      <div align="center"></div></td>
    <td colspan="3"><div align="center">${rs.lxdh }</div>      <div align="center"></div>      <div align="center"></div></td>
  </tr>
  <tr>
    <td><p align="center">��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>˵<br/>��</p> <p align="center">&nbsp;</p></td>
    <td colspan="8"><p>&nbsp;</p><div align="center">&nbsp;&nbsp;&nbsp;&nbsp;${rs.mzpyqksm }</div> 
    <p align="center">&nbsp;</p><p>&nbsp;</p><p align="center">&nbsp;</p>
    <p align="right">�೤��   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    &nbsp;&nbsp;&nbsp;  �� &nbsp;&nbsp;  �� &nbsp;&nbsp;  ��&nbsp;&nbsp;</p>
    
    </td>
  </tr>
  <tr>
    <td><div align="center"><p align="center">&nbsp;</p>��<br/>
      ��<br/>
      ��<br/>
    ��<p align="center">&nbsp;</p><p align="center">&nbsp;</p></div></td>
    <td colspan="8"><div align="center">&nbsp;&nbsp;&nbsp;&nbsp;${rs.jcqk }</div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div></td>
  </tr>
  </table>
<%--  <p>&nbsp;</p>--%>
<%--  <p>&nbsp;</p>--%>
<%--  <p>&nbsp;</p>--%>
<%--  <p>&nbsp;</p>--%>
<%--  <p>&nbsp;</p>--%>
<div style='page-break-before:always;'>&nbsp;</div>
  <table width="600px" align="center" class="printstyle">
  <tr>
    <td td width="74"><div align="center">��<br/>
      У<br/>
      ��<br/>
      ��<br/>
      ��<br/>
      ��<br/>
      ��<br/>
      Ժ<br/>
      ϵ<br/>
      ��<br/>
      ��<br/>
      ��<br/>
    ��</div></td>
    <td colspan="8"><p>&nbsp;</p>
    <p>&nbsp;</p><p>&nbsp;</p>
    <div align="center">&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }</div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>
   	<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
   	<p align="right">�����ˣ�   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  ���£� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    &nbsp;&nbsp;&nbsp;  �� &nbsp;&nbsp;  �� &nbsp;&nbsp;  ��&nbsp;&nbsp;</p>
    </td>
  </tr>
  <tr>
    <td><div align="center">ѧ<br/>
      У<br/>
      ��<br/>
      ��<br/>
      ��<br/>
    ��</div></td>
    <td colspan="8"><p>&nbsp;</p><p>&nbsp;</p>
    <p>&nbsp;</p><p>&nbsp;</p>
    <div align="center">&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }</div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>
    <p>&nbsp;</p><p>&nbsp;</p>
    
    <p align="right">�����ˣ�   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  ���£� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    &nbsp;&nbsp;&nbsp;  �� &nbsp;&nbsp;  �� &nbsp;&nbsp;  ��&nbsp;&nbsp;</p>
    </td>
  </tr>
</table>
<div align="center" class='noPrin'>
			<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				ֱ�Ӵ�ӡ
			</button>
		</div>
		<p align="center">
ע��1���˱�һʽ���ݣ�ѧ�����˵�����ѧУ��һ�ݡ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
2���������ݿɴ�ӡ���øֱ���д���ּ��������Ժ��ϵ����ѧУ���£��쵼ǩ�ַ���Ч��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</p>
    </html:form>
  </body>
</html:html>
