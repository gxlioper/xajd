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
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
		<input type="hidden" id="pk" name="pk" value="<bean:write name="pk"/>"/>			
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"> ѧ����Ϣ - ѧ��������Ϣ - ��Ϣ�����޸� </span>
				</div>
			</div>						
			<fieldset>			
				<table width="100%" class="tbstyle" id="rsT">						
					<tr>
						<td height="27" align="center">
							�Ƿ��ڷ�У��
						</td>
						<td align="center">
							<html:select property="sfzfx" styleId="sfzfx" style="width:120px">
								<html:option value=""></html:option>
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>								
							</html:select>
						</td>									
				</table>
			</fieldset>
			<div class="buttontool">				
				<button class="button2" onclick="BatAlert.showTips('����ִ�в�������ȴ�...');refreshForm('');"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;				
				<button class="button2" onclick="Close();return false;">
					�� ��
				</button>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
				<script>	
					alert("�����ɹ���");
					Close();
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>	
						alert("����ʧ�ܣ�");
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>

