<%@ page language="java" pageEncoding="GB2312" contentType="text/html;charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<style media='print'>.noPrin{display:none}
	</style>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="/style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commFunction.js"></script>
	<object id="WebBrowser" name="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
  </head>
<body>
<html:form action="/qgzxLogic.do" method="post">
<div align="center">
  <p><strong>ѧ���ڹ���ѧ�����
</strong>  
  <table width="647" height="582" class="tbstyle" id="rsTab">
    <tr>
      <td width="72" height="33"><strong>����</strong></td>
      <td width="72">${rs.xm}</td>
      <td width="46"><strong> �Ա�</strong></td>
      <td width="51">${rs.xb}</td>
      <td width="73"><strong>ϵ��</strong></td>
      <td width="89">${rs.xymc}</td>
      <td width="83"><strong>�༶</strong></td>
      <td width="125">${rs.bjmc}</td>
    </tr>
    <tr>
      <td height="41"><strong>��ͥ��Ҫ������Դ</strong></td>
      <td colspan="5">&nbsp;</td>
      <td><strong>�к��س�</strong></td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td height="39"><strong>��ͥ��Ҫ��Ա</strong></td>
      <td colspan="7"><div align="center"><strong>���¹��������������</strong></div></td>
    </tr>
    <tr>
      <td height="25">&nbsp;</td>
      <td colspan="7">&nbsp;</td>
    </tr>
    <tr>
      <td height="25">&nbsp;</td>
      <td colspan="7">&nbsp;</td>
    </tr>
    <tr>
      <td height="25">&nbsp;</td>
      <td colspan="7">&nbsp;</td>
    </tr>
    <tr>
      <td height="25">&nbsp;</td>
      <td colspan="7">&nbsp;</td>
    </tr>
    <tr>
      <td><strong>����ԭ��</strong></td>
      <td colspan="7"><p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p></td>
    </tr>
    <tr>
      <td><strong>����Ա�������Σ����</strong></td>
      <td colspan="7"><p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p></td>
    </tr>
    <tr>
      <td height="33"><strong>ϵ�������ң����</strong></td>
      <td colspan="7"><p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p></td>
    </tr>
    <tr>
      <td><strong>ѧ�������</strong></td>
      <td colspan="7"><p>&nbsp;</p>
        <p>&nbsp;</p>
      <p>&nbsp;</p></td>
    </tr>
    <tr>
      <td height="15"><strong>��ע</strong></td>
      <td colspan="7"><strong>������йؼ�ͥ�������ѵ����֤������</strong></td>
    </tr>
  </table>
  <p>   
 
</div>
 </html:form>			
</body>
			<div class='noPrin' align="center">
				<input type='button' class='button2' value='ҳ������' onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��' onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ' onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>	
</html:html>
