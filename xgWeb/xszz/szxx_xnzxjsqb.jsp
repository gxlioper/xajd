<%@ page language="java" contentType="text/html;charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html >
<base target="_self" >
  <head>
    <title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
  </head>
<%
   response.setHeader("Pragma","no-cache");
   response.setHeader("Cache-Control","no-cache");
   response.setDateHeader("Expires", 0);
 %>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
  <body>
    <html:form action="szxx_xnzxjsqb.do" method="post">
			<p align="center" style="font-size:24px">У����ѧ�������</p>
			<table width="100%" class="tbstyle" id="theTable">
			 <tr>
							<td>
								<div align="center" width="16%">
									У����ѧ����Ŀ
								</div>
							</td>
							<td width="34%">
								<bean:write name="rs" property="xmmc"/>
							</td>
							<td width="16%">
								<div align="center">
									У����ѧ����
								</div>
							</td>
							<td width="34%">
								<bean:write name="rs" property="zxjje"/>
							</td>
						</tr>
						<tr>
								<td align="center">
									ѧ��
								</td>
								<td align="left">
									<bean:write name="rs" property="xh" />
								</td>
							<td width="16%">
								<div align="center">
									����
								</div>
							</td>
							<td width="34%">
								<bean:write name="rs" property="xm"/>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�Ա�
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xb"/>
							</td>
							<td>
								<div align="center">
									�꼶
								</div>
							</td>
							<td>
								<bean:write name="rs" property="nj"/>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xymc"/>
							</td>
							<td>
								<div align="center">
									רҵ
								</div>
							</td>
							<td>
								<bean:write name="rs" property="zymc"/>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�༶
								</div>
							</td>
							<td>
								<bean:write name="rs" property="bjmc"/>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<bean:write name="rs" property="kh"/>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									֤������
								</div>
							</td>
							<td>
								<bean:write name="rs" property="zmclmc"/>
							</td>
							<td colspan="2">
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ע
								</div>
							</td>
							<td colspan="3">
									<bean:write name="rs" property="bz" />
							</td>
						</tr>
			  <tr>
			    <td height="89" colspan="4" valign="top"><p ><bean:message key="lable.xsgzyxpzxy" />�������� </p>
			        <bean:write name="rs" property="xyshyj" />  
			        <br><br>
			        <p align="right">
			      ��ί�����ǩ���� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;�� </p></td>
			  </tr>
			  <tr>
			    <td colspan="4" valign="top"><p>ѧУ�������� </p>
			        <bean:write name="rs" property="xxshyj" />   
			        <br><br>
			         <p align="right">
			        �����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;�� </p></td>
			  </tr>
			</table>
    </html:form>
	<div align=center>
		<input  value="��ӡ" name="button_print" onclick="expTab('theTable','У����ѧ�������')" />
	</div>    
  </body>
</html:html>
