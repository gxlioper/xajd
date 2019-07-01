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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��������� - ��ѧ������ - ��д�����
				</div>
			</div>
				<div align="center" style="font: 15px"><b>${bjmc }�ۺ����ʲ����ɼ����������ܱ�</b></div>
				<logic:empty name="rs">
				<br/><br/>
				<p align="center">���κμ�¼!</p>
				</logic:empty>
				<logic:notEmpty name="rs">
				<br/>
					<table width="100%" align="center" class="tbstyle">
						<thead>
							<tr>
								<td align="center">
									�к�
								</td>
								<td align="center">
									ѧ��
								</td>
								<td align="center">
									ѧ��
								</td>
								<td align="center">
									ѧ��
								</td>
								<td align="center">
									����
								</td>
								<td align="center">
									�³ɼ�
								</td>
								<td align="center">
									�³ɼ�����
								</td>
								<td align="center">
									�ǳɼ�
								</td>
								<td align="center">
									�ǳɼ�����
								</td>
								<td align="center">
									��ɼ�
								</td>
								<td align="center">
									��ɼ�����
								</td>
								<td align="center">
									���ܼӷ�
								</td>
								<td align="center">
									���ܼӷ�����
								</td>
								<td align="center">
									�ۺϲ����ܷ�
								</td>
								<td align="center">
									�ۺϲ����ܷ�����
								</td>
							</tr>
						</thead>
						<logic:iterate id="s" name="rs">
						<tr>
							<logic:iterate id="v" name="s">
							<td nowrap="nowrap">
								${v }
							</td>
							</logic:iterate>
						</tr>
						</logic:iterate>
					</table>
				</logic:notEmpty>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="Close();return false;" style="width:90px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
