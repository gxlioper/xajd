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
  <body>
   		 <center><h2>Υ��ѧ���������ٱ�</h2></center>
	<p>&nbsp;<bean:write name="rs"  property="xymc"/>&nbsp;</p>
	<table width="100%" class="tbstyle" border="1">
  		<tr>
  			<td>��&nbsp;��</td>
  			<td align="center" style="width:15%">&nbsp;<bean:write name="rs" property="xm"/>&nbsp;</td>
  			<td>ר&nbsp;ҵ</td>
  			<td colspan="2" align="center" style="width:21%">&nbsp;<bean:write name="rs" property="zymc"/>&nbsp;</td>
  			<td>��&nbsp;��</td>
  			<td align="center" style="width:21%">&nbsp;<bean:write name="rs" property="bjmc"/>&nbsp;</td>
  			<td>��&nbsp;��</td>
  			<td align="center" style="width:10%">&nbsp;<bean:write name="rs" property="xb"/>&nbsp;</td>
  			<td>��&nbsp;��</td>
  			<td align="center">&nbsp;&nbsp;<bean:write name="rs" property="nn"/>&nbsp;&nbsp; </td>
  		</tr>
  		<tr>
  			<td colspan="2">�������</td>
  			<td colspan="3" align="center">&nbsp;<bean:write name="rs" property="cflbmc"/></td>
  			<td>����ʱ��</td>
  			<td colspan="2" align="center">&nbsp;<bean:write name="rs" property="cfsj"/> </td>
  			<td>��������</td>
  			<td colspan="2" >&nbsp; </td>
  		</tr>
  		<tr>
    		<td colspan="11" align="center">
    			��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;¼
    		</td>
  		</tr>
  		<tr>
  			<td width="10" align="center">
  				<p >&nbsp;</p>
  				<p >&nbsp;</p>
  				��<br>
  				һ<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				¼<br>
  				<p >&nbsp;</p>
  			
  			</td>
  			<td colspan="11" valign="bottom" align="right">
  				<p >��ϵ��ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</p>
  				<p >ѧ��ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</p>
  			</td>
  		</tr>
  		<tr>
  			<td width="10" align="center">
  				<p >&nbsp;</p>
  				<p >&nbsp;</p>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				¼<br>
  				<p >&nbsp;</p>
  				
  			</td>
  			<td colspan="11" valign="bottom" align="right">
  				<p >��ϵ��ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</p>
  				<p >ѧ��ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</p>
  			</td>
  		</tr>
  		</table>
  		<p >&nbsp;</p><p >&nbsp;</p><p >&nbsp;</p><p >&nbsp;</p>
  		<table width="100%" class="tbstyle" border="1">
  		<tr>
  			<td width="10" align="center">
  				<p >&nbsp;</p><p >&nbsp;</p>
  				��&nbsp;&nbsp;&nbsp;<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				¼<br>
  				<p >&nbsp;</p>
  			</td>
  			<td colspan="11" valign="bottom" align="right">
  				<p >��ϵ��ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</p>
  				<p >ѧ��ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</p>
  			</td>
  		</tr>
  		<tr>
  			<td width="10" align="center">
  				<p >&nbsp;</p><p >&nbsp;</p>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				��<br>
  				¼<br>
  				<p >&nbsp;</p>
  			</td>
  			<td colspan="11" valign="bottom" align="right">
  				<p >��ϵ��ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</p>
  				<p >ѧ��ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��</p>
  			</td>
  		</tr>
  		<tr>
  			<td width="10" align="center">
  				
  				ѧ<br>
  				Ժ<br>
  				��<br>
  				��<br>
  			
  			</td>
  			<td colspan="11" valign="bottom" align="right">
  				Ժ�쵼ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
  			</td>
  		</tr>
	</table>
  </body>
</html:html>
