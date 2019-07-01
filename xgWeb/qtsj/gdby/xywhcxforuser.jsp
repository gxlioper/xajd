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
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/qgzxFunction.js"></script>
		<html:form action="/data_search" method="post">
			<input type="hidden" id="realTable" value="${realTable}" />
			<input type="hidden" id="tableName" value="${tableName}" />

			<div class="title">
				<div class="title_img" id="title_m">
					${title }
				</div>
			</div>
			<logic:notPresent name="showbjlh">
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="center">
								�û���
								<bean:write name="userName" scope="session" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								������
								<bean:write name="userNameReal" scope="session" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</thead>
				</table>
			</logic:notPresent>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						У԰�Ļ������
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">


						<thead>
							<tr>
								<td align="center" colspan="6">
									��б�
								</td>
							</tr>
							<tr>
								<td align="center" width="10%">
									�����
								</td>
								
								<td align="center" width="20%">
									�����
								</td>
								
								<td align="center" width="10%">
									��ϵ�绰
								</td>
								<td align="center" width="25%">
									���볡��
								</td>
								<td align="center" width="25%">
									��������
								</td>
								<td align="center" width="10%">
									ѧУ���
								</td>
								
							</tr>
						</thead>
						<logic:iterate id="map" name="rs">
						<tr onclick="rowMoreClick(this,'',event);"
							style="cursor:hand">

							<td align="center">
								${map.hddm }
							</td>
							<td align="center">
								${map.hdmc }
							</td>
							<td align="center">
								${map.lxdh }
							</td>
							<td align="center">
								${map.cdmc }
							</td>
							<td align="center">
								${map.wzmc }
							</td>
							<td align="center">
								${map.xxsh }
							</td>
						</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>

		</html:form>
	</body>
</html>
