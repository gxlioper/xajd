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
	<html:form action="/wmqspb_result" method="post">		
			<logic:empty name="rs">
				<div align="center"  style="font:17px;">
				ѧ����Ԣ�����������Ƚ����<bean:write name="commanForm" property="nd" />��<bean:write name="commanForm" property="yf" />�·ݣ�
			    </div>
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
				<div id="rsTable">
					<logic:iterate name="rs" id="s">
						<logic:iterate id="v" name="s" property="xyList">
								<br />
								<br />
								<div align="center" style="font:17px;">
									ѧ����Ԣ�����������Ƚ����
									<bean:write name="commanForm" property="nd" />
									��
									<bean:write name="commanForm" property="yf" />
									�·ݣ�
								</div>
								<table width="99%" class="tbstyle">
									<tr>
										<td colspan="5" align="center">
											<bean:write name="v" property="xymc" />
										</td>
									</tr>
									<tr align="center" style="cursor:hand">
										<td>
											¥��
										</td>
										<td>
											����
										</td>
										<td>
											��Ա
										</td>
										<td>
											�༶
										</td>
										<td>
											���
										</td>
									</tr>
									<logic:iterate id="b" name="s" property="wmpsList">
										<tr>
											<td>
												<bean:write name="b" property="ldmc" />
											</td>
											<td>
												<bean:write name="b" property="qsh" />
											</td>
											<td>
												<bean:write name="b" property="cy" />
											</td>
											<td>
												<bean:write name="b" property="bjmc" />
											</td>
											<td>
												<bean:write name="b" property="lbmc" />
											</td>
										</tr>
									</logic:iterate>
									<tr>
										<td colspan="5" align="right">
											��Ԣ����� <bean:write name="sysData" scope="request"/>
										</td>
									</tr>
									<tr>
										<td colspan="5" align="right">
											���д�©���뷴��
										</td>
									</tr>
								</table>							
						</logic:iterate>
					</logic:iterate>
					</div>
				</fieldset>
				<div class="buttontool" align="center">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="��ӡ" name="button_print"
						onclick="expTab('rsTable','','')">
				</div>
			</logic:notEmpty>
		</html:form>				
</body>
</html>		

		
