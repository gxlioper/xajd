<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    
    <title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<style media='print'>.noPrin{display:none}
	</style>
  	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<object id="WebBrowser" name="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
  	<script language="javascript" src="js/function.js"></script>
<body>
	<html:form action="/qgzxLogic">
	<div align="center">
	<table class="tbstyle" >
      <tr>
        <td height="42" colspan="6"><center>�ȷ�ʦ��<bean:message key="lable.xsgzyxpzxy" />�ڹ���ѧ��λ���˱�</center></td>
      </tr>
      <tr>
        <td width="91" height="42">����</td>
        <td width="74">Ժ��</td>
        <td width="99">�꼶רҵ</td>
        <td width="122">��ϵ�绰</td>
        <td colspan="2">��λ����</td>
      </tr>
      <tr>
        <td height="35">${map.xm}</td>
        <td>${map.xymc}</td>
        <td>${map.nj}${map.zymc}</td>
        <td>${map.lxdh}</td>
        <td colspan="2">${map.gwmc}</td>
      </tr>
      <tr>
        <td height="35">����</td>
        <td colspan="3">��������</td>
        <td width="151">����ʱ��</td>
        <td width="112">ѧ��ǩ��</td>
      </tr>
      <logic:iterate id="rs" name="rs">
	      <tr>
	        <td height="37"><bean:write name="rs" property="day"/> </td>
	        <td colspan="3"><bean:write name="rs" property="gznr"/> </td>
	        <td><bean:write name="rs" property="gzqssj"/> ��<bean:write name="rs" property="gzjssj"/> </td>
	        <td>&nbsp;</td>
	      </tr>
      </logic:iterate>
      <tr>
        <td height="33" colspan="6"><p>�ۺϿ��˳ɼ�: �� �� �ϸ� �� </p>
		<br/>
          <p>��λ������ǩ�֣�
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  ��λ������ǩ�֣������£�</p>
		  <br/>
        <p>��ע������λ����ÿ���µ׽��ÿ��˱����뽻��ѧ����������</p></td>
      </tr>
    </table>
    </div>
	</html:form>
			<div class='noPrin' align="center">
				<input type='button' class='button2' value='ҳ������' onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��' onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ' onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>	
	</body>
</html:html>
