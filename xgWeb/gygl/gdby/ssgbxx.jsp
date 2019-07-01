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
		<html:form action="/XsGyGlLogic.do?method=xsGySsGbxx" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�
					��Ԣ���� - ��Ϣά�� - �޹ܸɲ�ͨ��¼
				</div>
			</div>				
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">			  		  
				<fieldset>
				 <div id="rsTable">
			      <p align="center"><font size="6">�޹ܸɲ�ͨ��¼</font></p>	
					<table width="99%" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
									<td align="center">										
										�к�
									</td>
									<td align="center" >										
										ѧ��
									</td>
									<td align="center" >										
										����
									</td>
									<td align="center" >										
										�Ա�
									</td>
									<td align="center" >										
										�༶
									</td>
									<td align="center" >										
										¥������
									</td>
									<td align="center" >										
										���Һ�
									</td>
									<td align="center" >										
										����
									</td>
									<td align="center" >										
										ְ��
									</td>
									<td align="center" >										
										���ҵ绰
									</td>
									<td align="center" >										
										�ֻ�����
									</td>
									<td align="center" >										
										��ͥ��ַ
									</td>							
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr style="cursor:hand" >
								<td>									
									<logic:iterate id="v" name="s" offset="0" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					</div>
					<div class="buttontool" align="center">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="��ӡ" name="button_print"
							onclick="expTab('rsTable','')">															
				   </div>		
				</fieldset>				
			</logic:notEmpty>			
		</html:form>
</body>
</html>		

		
