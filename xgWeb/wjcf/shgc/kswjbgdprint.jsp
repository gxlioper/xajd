<%@ page language="java" contentType="text/html; charset=GBK"%>

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
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<style type="text/css">
		.tbstyle {
					border-collapse: collapse;
				}
		.tbstyle td {
	border: 1px #97B7DB solid;
	padding: 3px;
}		
	</style>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/shgc/td.css" type="text/css" media="all" />
  <body>
  <center>
	<table width="100%" class="tbstyle" border="1">
		<tr>
			<td colspan="8" align="center">
				<font size="5"><strong>ѧ���������ֲ��ϲ��鱾�˵���������</strong></font>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right"><logic:present name="rs"><bean:write name="rs" property="xymc"/></logic:present>&nbsp;ϵ</td>
			<td colspan="3" align="right"><logic:present name="rs"><bean:write name="rs" property="zymc"/></logic:present>&nbsp;רҵ</td>
			<td colspan="1">��ѧ��&nbsp;<logic:present name="rs"><bean:write name="rs" property="xh"/></logic:present></td>
			<td width="23%">������ò&nbsp;<logic:present name="rs"><bean:write name="rs" property="zzmm"/></logic:present></td>
		</tr>
  		<tr>
  			<td colspan="2" width="17%">����&nbsp;<logic:present name="rs"><bean:write name="rs" property="xm"/></logic:present></td>
  			<td width="13%">�Ա�&nbsp;<logic:present name="rs"><bean:write name="rs" property="xb"/></logic:present></td>
  			<td colspan="3" width="23%">��������&nbsp;<logic:present name="rs"><bean:write name="rs" property="csrq"/></logic:present></td>
  			<td colspan="1">����&nbsp;<logic:present name="rs"><bean:write name="rs" property="jg"/></logic:present></td>
  			<td>����&nbsp;<logic:present name="rs"><bean:write name="rs" property="mz"/></logic:present></td>
    	</tr>
    	<tr>
    		<td colspan="8">
    			ԭ���ֵȼ���ԭ��
    		</td>
    	</tr>
    	<tr>
    		<td colspan="8">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="cflbmc"/></logic:present>--%></p>
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="cfyymc"/></logic:present>--%></p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="8">
    			���ֺ���ʵ���֣�
    		</td>
    	</tr>
    	<tr>
    		<td colspan="8">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="cfhbx"/></logic:present>--%></p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="8">
    			�������ͼ�¼��
    		</td>
    	</tr>
    	<tr>
    		<td colspan="8">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="jcjl"/></logic:present>--%></p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    		</td>
    	</tr>
    	<tr>	
    		<td colspan="8">
    			��ע��
    		</td>
    	</tr>
    	<tr>
    		<td colspan="8">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="bz"/></logic:present>--%></p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    		</td>
    	</tr>
	</table>
	<p align="center">&nbsp;</p>
	<p align="center">&nbsp;</p><p align="center">&nbsp;</p><p align="center">&nbsp;</p>
	<table width="100%" class="tbstyle" border="1">
    	<tr>
    		<td colspan="9">
    			����Ա��������
    		</td>
    	</tr>
    	<tr>
    		<td colspan="9">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="bzryj"/></logic:present>--%></p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			</td>
    	</tr>
    	<tr>
    		<td colspan="6" rowspan="2">&nbsp;</td>
    		<td colspan="3">
    			<p align="right">����Աǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    		<td>
    	</tr>
    	<tr>
    		<td colspan="3">		
    			<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="9">
    			<bean:message key="lable.xsgzyxpzxy" />��������
    		</td>
    	</tr>
    	<tr>
    		<td colspan="9">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="xyyj"/></logic:present>--%></p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="5" rowspan="2">&nbsp;</td>
    		<td colspan="4">
    			<p align="right">������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="4">
    			<p align="right">Ժ��ϵ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="3">
    			��첿����������
    		</td>
    		<td colspan="3">
    			ѧ������������
    		</td>
    		<td colspan="3">
    			У��������
    		</td>
    	</tr>
    	<tr>
    		<td colspan="3">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="kbbmyj"/></logic:present>--%></p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    		</td>    	
    		<td colspan="3">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="xscyj"/></logic:present>--%></p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    		</td>
    		<td colspan="3">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%--<logic:present name="rs"><bean:write name="rs" property="xxyj"/></logic:present>--%></p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    			<p align="center">&nbsp;</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="3">
    			<p align="center">ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    		</td>
    		<td colspan="3">
    			<p align="center">ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    		</td>
    		<td colspan="3">
    			<p align="center">ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="3">
    			<p align="left">����&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��</p>
    		</td>
    		<td colspan="3">
    			<p align="left">����&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��</p>
    		</td>
    		<td colspan="3">
    			<p align="left">����&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="9">
    			<p align="left">˵����1.����У�йع涨,ѧ����������,���ϲ��鱾�˵������������ڱ�ҵѧ�ڽ���.</p>			  	
    		</td>
    	</tr>
    	<tr>
    		<td colspan="9">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.��<bean:message key="lable.xsgzyxpzxy" />����ִ�д��ֲ��ϲ��鱾�˵�����������Ԥ���,��ѧ������ȡ��������.</p>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="9">
    			<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.ѧ���������Ҽ���,С���������A4ֽ�򱨸�ֽ��.</p>	
    		</td>
    	</tr>
	</table>
	</center>
  </body>
</html:html>
