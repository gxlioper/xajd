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
	<html:form action="/hngydx_gygl" method="post">		
			<logic:empty name="rs">
				<div align="center"  style="font:17px;">
				ѧ����Ԣ
			    </div>
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<br />
					<br />
					<div align="center" style="font:25px;">
						�� �� �� ҵ �� ѧ �� �� �� ס �� ��
					</div>
					<div id="rsTable">
						<table width="99%" class="tbstyle">
							<tr align="center" style="cursor:hand">
								<td>
									¥����
								</td>
								<td>
									���Ҽ���
								</td>
								<td>
									ÿ������
								</td>
								<td>
									��λ��
								</td>
								<td>
									��ס����
								</td>
								<td>
									��������
								</td>
							</tr>
							<logic:iterate name="rs" id="s">
								<tr>
									<td>
										<bean:write name="s" property="ldmc" />
									</td>
									<td>
										<bean:write name="s" property="fjzs" />
									</td>
									<td>
										<bean:write name="s" property="mfjrs" />
									</td>
									<td>
										<bean:write name="s" property="cwzs" />
									</td>
									<td>
										<bean:write name="s" property="rzrs" />
									</td>
									<td>
										<bean:write name="s" property="kyrs" />
									</td>
								</tr>
							</logic:iterate>						
							<tr align="center">
									<td >
										�ϼ�
									</td>
									<td>
										<bean:write name="fjzs_hj" scope="request"/>
									</td>
									<td>
										<bean:write name="mfjrs_hj" scope="request"/>
									</td>
									<td>
										<bean:write name="cwzs_hj" scope="request"/>
									</td>
									<td>
										<bean:write name="rzrs_hj" scope="request"/>
									</td>
									<td>
										<bean:write name="kyrs_hj" scope="request"/>
									</td>
								</tr>							
						</table>
					</div>
				</fieldset>
				<div class="buttontool" align="center">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="��ӡ" name="button_print"
						onclick="expTab('rsTable','�� �� �� ҵ �� ѧ �� �� �� ס �� ��','')">
				</div>
			</logic:notEmpty>
		</html:form>				
</body>
</html>		

		

