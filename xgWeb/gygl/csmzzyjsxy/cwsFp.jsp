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
	<script language="javascript" src="js/function.js"></script>
    <script language="javascript" src="js/gygl/csmzGyglFunction.js"></script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body>	
		<html:form action="/csmz_gygl" method="post">
		    <input type="hidden" id="nj" name="nj"
							value="<bean:write name="nj" scope="request"/>" />
		    <input type="hidden" id="nj" name="doType"
							value="<bean:write name="doType" scope="request"/>" />	
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>						
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã� ��Ԣ���� - ������� - ����λ������
				</div>
			</div>
			<fieldset>
			  <legend>
			      ����λ��
			      <logic:equal value="add" name="doType"><font color="red">����</font></logic:equal>
			      <logic:equal value="del" name="doType"><font color="red">�ͷ�</font></logic:equal>
			  </legend>
			
			<table width="100%" class="tbstyle">				
					<tr>
						<td colspan="4" align="center">
						    <br>
							 ѡ&nbsp;��&nbsp;��&nbsp;λ&nbsp;��:
							 <html:select property="cwfps" style="width:70px">
							  <html:options collection="cwsList" labelProperty="cn" property="en"/>
							 </html:select>
							 <p>
							 <p>
						</td>
					</tr>				
			</table>
			</fieldset>
			<div class="buttontool" align="center">
				<button class="button2" onclick="sendCws()" style="width:60px"
					id="buttonSave">
					ȷ��
				</button>
				<button class="button2" onclick="Close();return false;" style="width:60px"
					id="buttonSave">
					�ر�
				</button>
			</div>			
		</html:form>		
  </body>
</html>

