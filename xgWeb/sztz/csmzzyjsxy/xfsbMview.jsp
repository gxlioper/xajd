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

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css'
		type='text/css' media='all' />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">

	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/csmzzy_sztz" method="post">
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<p align="center" id="tabTit"
					style="font-weight: bold;font-size: 20px">
					<u>&nbsp;&nbsp;<bean:write name="map" property="xn" />&nbsp;&nbsp;</u>
					ѧ���������չѧ����֤���ܱ�
				</p>
				<div id="rsTable">
					<div align="center">
						<u>&nbsp;&nbsp;<bean:write name="map" property="xymc" />&nbsp;&nbsp;</u>
						ϵ
						<u>&nbsp;&nbsp;<bean:write name="map" property="bjmc" />&nbsp;&nbsp;</u>
						��, ���ʱ�䣺
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> ��
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> ��
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> ��
					</div>
					<table width="99%" class="tbstyle">

						<tr align="center">
							<td>
								����
							</td>
							<td>
								ѧ��
							</td>
							<td>
								˼��
								<br>
								����
								<br>
								����
							</td>
							<td>
								־Ը
								<br>
								����
							</td>
							<td>
								�Ƽ�
								<br>
								ѧ��
							</td>
							<td>
								�Ļ�
								<br>
								����
							</td>
							<td>
								����
								<br>
								���
								<br>
								����
							</td>
							<td>
								����
								<br>
								��ѵ
							</td>
							<td>
								�ϼ�
								<br>
								ѧ��
							</td>
							<td>
								�༶
								<br>
								��֤
								<br>
								����
								<br>
								���
							</td>
							<td>
								ϵ��
								<br>
								��֤
								<br>
								����
								<br>
								���
							</td>
							<td>
								Ժ��
								<br>
								��֤
								<br>
								����
								<br>
								���
							</td>
						</tr>

						<logic:iterate name="rs" id="s">
							<tr>
								<logic:iterate id="v" name="s" offset="1">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<td>

								</td>
								<td>

								</td>
								<td>

								</td>
							</tr>
						</logic:iterate>
					</table>
					<div align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;ע���˱�һʽ���ݣ�����˺�Ժ��ϵ��һ�ݡ�
						<br>
						<p align="center">
							ϵ������չ���ģ�ǩ�£�

							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							Ժ��ѧ��������չ���ģ�ǩ�£�
						</p>
						<p align="center">
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> ��
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> ��
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> ��
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> ��
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> ��
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> ��

						</p>
					</div>
				</div>
				<br>
				<div class="buttontool" align="center">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="��ӡ" name="button_print"
						onclick="expTab('rsTable','','tabTit')">
				</div>

			</logic:notEmpty>
		</html:form>
	</body>
</html>
