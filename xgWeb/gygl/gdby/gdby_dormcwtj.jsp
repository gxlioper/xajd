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
		<html:form action="/XsGyGlLogic.do?method=gdby_dormCwtj" method="post">						
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
						<center>
							�㶫����<bean:message key="lable.xsgzyxpzxy" />
						</center>
						<p align="center">
							<font size="5"><b> ѧ�����ᴲλ��������� </b></font>
						</p>
						<table width="99%" class="tbstyle">
						<thead>

								<tr align="center" style="cursor:hand">
									<td rowspan="2" align="center">
										����¥
									</td>
									<td colspan="2" align="center">
										����Դ
									</td>
									<td colspan="4" align="center">
										ʹ�ô�λ
									</td>								
									<td rowspan="2" align="center">
										ռ�ô�λ
									</td>
									<td rowspan="2" align="center">
										ʣ�ലλ
									</td>
								</tr>
								<tr>
									<td align="center">
										����
									</td>
									<td align="center">
										��λ
									</td>
									<td align="center">
										<bean:write name="nj1"/>��
									</td>
									<td align="center">
										<bean:write name="nj2"/>��
									</td>
									<td align="center">
										<bean:write name="nj3"/>��
									</td>	
									<td align="center">
										С��
									</td>									
								</tr>
							</thead>
						<logic:iterate name="rs" id="s">
							<tr style="cursor:hand" >																
									<td nowrap align="center">
										<bean:write name="s" property="ldmc"/>
									</td>
									<td nowrap>
										<bean:write name="s" property="zss"/>
									</td>
									<td nowrap>
										<bean:write name="s" property="zcw"/>
									</td>
																		
									<td nowrap>
										<bean:write name="s" property="coutxh1"/>
									</td>
									<td nowrap>
										<bean:write name="s" property="coutxh2"/>
									</td>
									<td nowrap>
										<bean:write name="s" property="coutxh3"/>
									</td>
									<td nowrap>
										<bean:write name="s" property="sumcws"/>
									</td>
									<td nowrap>
										<bean:write name="s" property="ycw"/>
									</td>
									<td nowrap>
										<bean:write name="s" property="sycw"/>
									</td>								
							</tr>							
						</logic:iterate>
						<tr style="cursor:hand" >
							<td nowrap align="center">
									�ϼ�
									</td>
									<td nowrap>
									<bean:write name="hjzss"/>
									</td>
									<td nowrap>
									<bean:write name="hjzcw"/>
									</td>
									<td nowrap>
									<bean:write name="hjcoutxh1"/>
									</td>
									<td nowrap>
									<bean:write name="hjcoutxh2"/>
									</td>
									<td nowrap>
									<bean:write name="hjcoutxh3"/>
									</td>
									<td nowrap>
									<bean:write name="hjsumcws"/>
									</td>
									<td nowrap>
									<bean:write name="hjycw"/>
									</td>
									<td nowrap>
									<bean:write name="hjsycw"/>
									</td>
							</tr>
							<tr style="cursor:hand" >
							<td nowrap align="center">
							��ע
							</td>
							<td colspan="8" height="250px">
							
							</td>
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

		
