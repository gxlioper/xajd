<%@ page language="java" contentType="text/html; charset=GBK"%>

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
<html:html locale="true">
  <head>
    
    <title>MyJsp.jsp</title>

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
  	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
  <body>
    <html:form action="/qgzx_bb_gwsbb">
    <div id="rsT">
    <table align="center" width="65%">
    	<tr>
    		<td align="center"><STRONG><bean:write name="xxmc" scope="request"/>Ժ���ڹ���ѧ��λ��������� </STRONG></td>
    	</tr>
    	<tr>
    		<td align="left"><strong>����ʦ��<bean:message key="lable.xsgzyxpzxy" />ѧ�����Ʊ� </strong></td>
    	</tr>
    </table>
	<table class="tbstyle" width="70%" align="center">
  		<tr>
    		<td width="72"><p align="center">�� �� </p></td>
    		<td width="158"><bean:write name="rs" property="xm" /></td>
    		<td width="61" colspan="2"><p align="center">ѧ �� </p></td>
    		<td width="107" colspan="2"><bean:write name="rs" property="xh" /></td>
    		<td width="72"><p>�� �� </p></td>
    		<td width="154"><bean:write name="rs" property="xb" /></td>
  		</tr>
  		<tr>
    		<td width="72"><p align="center">����ϵ </p></td>
    		<td width="158"><bean:write name="rs" property="xymc" /></td>
    		<td width="61" colspan="2"><p align="center">�� �� </p></td>
    		<td width="107" colspan="2"><bean:write name="rs" property="nj" /></td>
    		<td width="72"><p>ר ҵ </p></td>
    		<td width="154"><bean:write name="rs" property="zymc" /></td>
  		</tr>
  		<tr>
    		<td width="72"><p align="center">�� �� </p></td>
   			<td width="168" colspan="2"></td>
    		<td width="110" colspan="2"><p align="center">�Ƿ��ͥ���� </p></td>
    		<td width="48"><bean:write name="rs" property="sfpks" /></td>
    		<td width="72"><p>��ϵ�绰 </p></td>
    		<td width="154"><bean:write name="rs" property="lxdh" /></td>
  		</tr>
  		<tr>
    		<td width="72"><p align="center">�����λ </p></td>
    		<td width="552" colspan="7"><bean:write name="rs" property="gwdm" /></td>
  		</tr>
  		<tr>
    		<td width="72" valign="top" align="center"><p>&nbsp;</p>����ϵ<br></br>����Ա<br></br>�����<br></br>��<br></br>����򹳣� </td>
    		<td width="552" colspan="7">
    			<p>&nbsp;</p>
    			1��&piv; �������������ʵ������������ͬ��μ�У���ڹ���ѧ��<br/>
        		2��&piv; ���������ϲμ�У���ڹ���ѧ�����������ɣ�
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p align="right">����Ա��ǩ�£���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;��&nbsp;&nbsp;�� </p>
        	</td>
  		</tr>
  		<tr>
    		<td width="72"><p align="center">�ù� </p>
        		<p align="center">��λ </p>
        		<p align="center">Ƹ�� </p>
        		<p align="center">��� </p>
        		<p align="center">����򹳣� </p></td>
    		<td width="552" colspan="7" valign="top">
    			<p>&nbsp;</p>
    			1��&piv; ͬ��Ƹ�á� <br/>
        		2��&piv; ��ͬ��Ƹ�á����ɣ�������ӦƸѧ�����ͣ���
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p align="right">�ֹ��쵼��ǩ�£���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;��&nbsp;&nbsp;�� </p></td>
  		</tr>
  		<tr>
    		<td width="72"><p align="center">��ѧ��<br></br>����顢<br></br>�����<br></br>������<br></br>�� </p></td>
    		<td width="552" colspan="7" valign="top"></td>
  		</tr>
	</table>
	<table width="68%" align="center">
		<tr>
			<td align="left"><strong>��ע�� </strong></td>
		</tr>
		<tr>
			<td align="left"><strong>����Ժ�������ظ����λ�����ù���λ��������ѧ���������벢����¼�ã��ù���λ��¼�õ�ѧ�������ѧ��������ͱ����� </strong></td>
		</tr>
		<tr>
			<td align="left"><strong>������ϵ����֧���ͳ�ĸ����λ�����ù���λ��������ѧ���������룬��ϵ��������¼�ã�����¼�õ�ѧ�����ܽ�ѧ����������</strong></td>
		</tr>
	</table>
    </div>
    <div class="buttontool" align="center">
		<button type="button" class="button2" onclick="expAppTab('rsT','<bean:write name="xxmc" scope="request"/>Ժ���ڹ���ѧ��λ��������� ','')">
				�� ӡ �� ��
		</button>
	</div>
	</html:form>
  </body>
</html:html>
