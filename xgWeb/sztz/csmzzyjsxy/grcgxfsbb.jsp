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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body>
		<script language="javascript" src="js/function.js"></script>
		<div id="rsTable">
			<p align="center" style="font-size: 18px;font-family:����">
				��ɳ����ְҵ����<bean:message key="lable.xsgzyxpzxy" />ѧ��������չѧ���걨��
			</p>
			<div align="center">
				<u>&nbsp;&nbsp;<bean:write name="rs" property="xn"/>&nbsp;&nbsp;</u>ѧ��
				��<u>&nbsp;&nbsp;<bean:write name="rs" property="xq"/> &nbsp;&nbsp;</u>ѧ�� 
				ϵ��<u>&nbsp;&nbsp;<bean:write name="rs" property="xymc"/>&nbsp;&nbsp;</u> 
				�༶��<u>&nbsp;&nbsp;<bean:write  name="rs" property="bjmc"/>&nbsp;&nbsp;</u> 
				 ѧ�ţ�<u>&nbsp;&nbsp;<bean:write name="rs" property="xh"/>&nbsp;&nbsp;</u> 
			</div>
			<table width="100%" border="0" class="tbstyle">
				<tr>
					<td colspan="8" align="center">
						<strong>˼������������������� 2 �֣� </strong>
					</td>
				</tr>
				<logic:notEmpty name="szList">
				<tr>
					<td>
						���
					</td>
					<td>
						����
					</td>
					<td>
						ʱ��
					</td>
					<td>
						�ɹ�
					</td>
					<td>
						����
					</td>
					<td>
						����
					</td>
					<td>
						<p align="center">
							����ѧ��
						</p>
					</td>
					<td>
						<p align="center">
							��֤ѧ��
						</p>
					</td>
				</tr>			
				<logic:iterate id="rs1" name="szList">
					<tr>
						<td>
							<bean:write name="rs1" property="hh" />
						</td>
						<td>
							<bean:write name="rs1" property="xmmc" />
						</td>
						<td>
							<bean:write name="rs1" property="zbsj" />
						</td>
						<td>
							<bean:write name="rs1" property="jxm" />
						</td>
						<td>
							<bean:write name="rs1" property="cyjs" />
						</td>
						<td>
							<bean:write name="rs1" property="xmjb" />
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<bean:write name="rs1" property="xf" />
						</td>
					</tr>
				</logic:iterate>
				</logic:notEmpty>
				<tr>
					<td colspan="8" align="center">
						<strong>���ʵ����־Ը������ 2 �֣� </strong>
					</td>
				</tr>
				<logic:notEmpty name="shsjList">
				<tr>
					<td>
						���
					</td>
					<td>
						����
					</td>
					<td>
						ʱ��
					</td>
					<td>
						�ɹ�
					</td>
					<td>
						����
					</td>
					<td>
						����
					</td>
					<td>
						<p align="center">
							����ѧ��
						</p>
					</td>
					<td>
						<p align="center">
							��֤ѧ��
						</p>
					</td>
				</tr>
				<logic:iterate id="rs2" name="shsjList">
					<tr>
						<td>
							<bean:write name="rs2" property="hh" />
						</td>
						<td>
							<bean:write name="rs2" property="xmmc" />
						</td>
						<td>
							<bean:write name="rs2" property="zbsj" />
						</td>
						<td>
							<bean:write name="rs2" property="jxm" />
						</td>
						<td>
							<bean:write name="rs2" property="cyjs" />
						</td>
						<td>
							<bean:write name="rs2" property="xmjb" />
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<bean:write name="rs2" property="xf" />
						</td>
					</tr>
				</logic:iterate>
				</logic:notEmpty>
				<tr>
					<td colspan="8" align="center">
						<strong>�Ƽ�ѧ���봴�´�ҵ���� 1 �֣� </strong>
					</td>
				</tr>
				<logic:notEmpty name="kjcxList">
				<tr>
					<td>
						���
					</td>
					<td>
						����
					</td>
					<td>
						ʱ��
					</td>
					<td>
						�ɹ�
					</td>
					<td>
						����
					</td>
					<td>
						����
					</td>
					<td>
						<p align="center">
							����ѧ��
						</p>
					</td>
					<td>
						<p align="center">
							��֤ѧ��
						</p>
					</td>
				</tr>
				<logic:iterate id="rs3" name="kjcxList">
					<tr>
						<td>
							<bean:write name="rs3" property="hh" />
						</td>
						<td>
							<bean:write name="rs3" property="xmmc" />
						</td>
						<td>
							<bean:write name="rs3" property="zbsj" />
						</td>
						<td>
							<bean:write name="rs3" property="jxm" />
						</td>
						<td>
							<bean:write name="rs3" property="cyjs" />
						</td>
						<td>
							<bean:write name="rs3" property="xmjb" />
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<bean:write name="rs3" property="xf" />
						</td>
					</tr>
				</logic:iterate>
				</logic:notEmpty>
				<tr>
					<td colspan="8" align="center">
						<strong>�Ļ����������ķ�չ���� 2 �֣� </strong>
					</td>
				</tr>
				<logic:notEmpty name="whsxList">
				<tr>
					<td>
						���
					</td>
					<td>
						����
					</td>
					<td>
						ʱ��
					</td>
					<td>
						�ɹ�
					</td>
					<td>
						����
					</td>
					<td>
						����
					</td>
					<td>
						<p align="center">
							����ѧ��
						</p>
					</td>
					<td>
						<p align="center">
							��֤ѧ��
						</p>
					</td>
				</tr>
				<logic:iterate id="rs4" name="whsxList">
					<tr>
						<td>
							<bean:write name="rs4" property="hh" />
						</td>
						<td>
							<bean:write name="rs4" property="xmmc" />
						</td>
						<td>
							<bean:write name="rs4" property="zbsj" />
						</td>
						<td>
							<bean:write name="rs4" property="jxm" />
						</td>
						<td>
							<bean:write name="rs4" property="cyjs" />
						</td>
						<td>
							<bean:write name="rs4" property="xmjb" />
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<bean:write name="rs4" property="xf" />
						</td>
					</tr>
				</logic:iterate>
				</logic:notEmpty>
				<tr>
					<td colspan="8" align="center">
						<strong>���Ż����Ṥ������ 1 �֣� </strong>
					</td>
				</tr>
				<logic:notEmpty name="sthdList">
				<tr>
					<td>
						���
					</td>
					<td>
						����
					</td>
					<td>
						ʱ��
					</td>
					<td>
						�ɹ�
					</td>
					<td>
						����
					</td>
					<td>
						����
					</td>
					<td>
						<p align="center">
							����ѧ��
						</p>
					</td>
					<td>
						<p align="center">
							��֤ѧ��
						</p>
					</td>
				</tr>
				<logic:iterate id="rs5" name="sthdList">
					<tr>
						<td>
							<bean:write name="rs5" property="hh" />
						</td>
						<td>
							<bean:write name="rs5" property="xmmc" />
						</td>
						<td>
							<bean:write name="rs5" property="zbsj" />
						</td>
						<td>
							<bean:write name="rs5" property="jxm" />
						</td>
						<td>
							<bean:write name="rs5" property="cyjs" />
						</td>
						<td>
							<bean:write name="rs5" property="xmjb" />
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<bean:write name="rs5" property="xf" />
						</td>
					</tr>
				</logic:iterate>
				</logic:notEmpty>
				<logic:notEmpty name="jnpxList">
				<tr>
					<td colspan="8" align="center">
						<strong>������ѵ������ </strong>
					</td>
				</tr>
				<tr>
					<td>
						���
					</td>
					<td>
						����
					</td>
					<td>
						ʱ��
					</td>
					<td>
						�ɹ�
					</td>
					<td>
						����
					</td>
					<td>
						����
					</td>
					<td>
						<p align="center">
							����ѧ��
						</p>
					</td>
					<td>
						<p align="center">
							��֤ѧ��
						</p>
					</td>
				</tr>
				<logic:iterate id="rs6" name="jnpxList">
					<tr>
						<td>
							<bean:write name="rs6" property="hh" />
						</td>
						<td>
							<bean:write name="rs6" property="xmmc" />
						</td>
						<td>
							<bean:write name="rs6" property="zbsj" />
						</td>
						<td>
							<bean:write name="rs6" property="jxm" />
						</td>
						<td>
							<bean:write name="rs6" property="cyjs" />
						</td>
						<td>
							<bean:write name="rs6" property="xmjb" />
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<bean:write name="rs6" property="xf" />
						</td>
					</tr>
				</logic:iterate>
				</logic:notEmpty>
			</table>
			<table width="100%" border="0" class="tbstyle">
				<tr>
					<td width="5%">
						<strong>����<br>�ܷ� </strong>
					</td>
					<td width="45%">
						&nbsp;
					</td>
					<td width="15%">
						<p align="center">
							<strong>��֤�ܷ� <br>���ɸ���Ա��д�� </strong>
						</p>
					</td>
					<td width="35%">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td rowspan="3">
						<p>
							��
						</p>
						<p>
							֤
						</p>
						<p>
							��
						</p>
						<p>
							��
						</p>
					</td>
					<td>
						<p>
							��������
						</p>
						<p align="left">
							&nbsp;
						</p>
						<p align="left">
							&nbsp;
						</p>
						<p align="left">
							��������չ��֤С�飨ǩ�֣���
						</p>
						<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��</p>
					</td>
					<td colspan="2" >
						<p align="left" >
							��������
						</p>
						<p align="left">
							&nbsp;
						</p>
						<p align="left">
							&nbsp;
						</p>
						<p align="left">
							����Աǩ�֣�
						</p>
						<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��</p>
					</td>
				</tr>
				<tr>
					<td height="80px" valign="top">					
						��������						
					</td>
					<td colspan="2" height="80px" valign="top">
						��������
					</td>
				</tr>
				<tr>
					<td>
						<p>
							&nbsp;
						</p>
						<p>
							ϵ������չ��֤���ģ����£�
						</p>
						<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��</p>
					</td>
					<td colspan="2">
					    <p>
							&nbsp;
						</p>
						<p>
							Ժ������չ��֤���ģ����£�
						</p>
						<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��</p>
					</td>
				</tr>
			</table>
		</div>
		<div class="buttontool" align="center">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="��ӡ" name="button_print"
				style="cursor:hand;" onclick="expTab('rsTable','')">
		</div>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>	

