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
		
		<script language="javascript" src="js/sharedFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		
		<script language="javascript" src="js/pjpyFunction.js"></script>
		<script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<script language="javascript" src="pjpy/whlgdx/whlgdxjs.js"></script>
		<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/pjpy_whlgdx.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow">
					 ��ǰλ�ã��������� - �������� - ��������
					</span>
				</div>
			</div>
			<logic:empty name="fpbList">
			<br/>
			<br/>
			<center>�޼�¼��</center>
			</logic:empty>
			<logic:notEmpty name="fpbList">
			<p id="repTit" align="center"><bean:write name="ndxy" property="jxjsqxn"/>ѧ��(<bean:write name="ndxy" property="jxjsqnd"/>���)<b><bean:write name="ndxy" property="xymc"/></b> ��ѧ���������һ����</p>
				<table width="80%" class="tbstyle" align="center" id="rsTable">											
					<thead>
					<tr>
						<td align="center" width="50%">
							��ѧ��
						</td>
						<td align="center" width="50%">
							����
						</td>
					</tr>
					</thead>					
					<logic:iterate id="s" name="fpbList">
					<tr>
						<td align="center">
							<bean:write name="s" property="jxjmc"/>
						</td>
						<td align="center">
							<bean:write name="s" property="jxjrs"/>
						</td>
					</tr>
					</logic:iterate>
					<tr>
						<td align="center">
						<font color="red">������</font>
						</td>
						<td align="center">
						<font color="red"><bean:write name="jxjje"/></font>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
							<button class="button2"
							onclick="expTab('rsTable','','repTit')"
							style="width:80px" id="buttonPrint">
							�� ӡ
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							�� ��
						</button>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
	<logic:present name="inserted">
		<logic:equal value="yes" name="inserted">
			<script>
				alert("�����ɹ���");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="no" name="inserted">
			<script>
				alert("����ʧ�ܣ�");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
	</logic:present>
</html>
