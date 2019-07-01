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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/stuinfoFunction.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/stcygl" method="post">
			<div class="title">
				<div class="title_img">
					��ǰ����λ�ã�ѧ����֯�ɲ�-��������-ʱ������
				</div>
			</div>
			<fieldset>
				<legend>
					ʱ������
				</legend>
				<table width="80%" class="tbstyle" align="center">
					<thead>
						<tr align="center">
							<td colspan="2">
								ʱ������
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right" width="55%">
							<font color="red">*</font>��֯�ϱ���ʼʱ�䣺
						</td>
						<td align="left">
							<html:text name="rs" property="xmsbkssj" onclick="return showCalendar('xmsbkssj','y-mm-dd');" readonly="true" size="10" styleId="xmsbkssj" onblur="dateFormatChg(this)"></html:text>
						</td>
					</tr>
						<tr>
						<td align="right" width="55%">
							<font color="red">*</font>��֯�ϱ�����ʱ�䣺
						</td>
						<td align="left">
							<html:text name="rs" property="xmsbjssj" onclick="return showCalendar('xmsbjssj','y-mm-dd');" readonly="true" size="10" styleId="xmsbjssj" onblur="dateFormatChg(this)"></html:text>
						</td>
					</tr>
					<tr>
						<td align="right" width="55%">
							<font color="red">*</font>ѧ�����뿪ʼʱ�䣺
						</td>
						<td align="left">
							<html:text name="rs" property="xssqkssj" onclick="return showCalendar('xssqkssj','y-mm-dd');" readonly="true" size="10" styleId="xssqkssj" onblur="dateFormatChg(this)"></html:text>
						</td>
					</tr>
						<tr>
						<td align="right" width="55%">
							<font color="red">*</font>ѧ���������ʱ�䣺
						</td>
						<td align="left">
							<html:text name="rs" property="xssqjssj" onclick="return showCalendar('xssqjssj','y-mm-dd');" readonly="true" size="10" styleId="xssqjssj" onblur="dateFormatChg(this)"></html:text>
						</td>
					</tr>
							
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="2">
							<button name="Submit2" class="button2"
								onclick="szsxDataDoSave('/xgxt/stcygl.do?method=saveSjcssz','')">
								�� ��
							</button>
						 </td>
					</tr>
				</table>
			</fieldset>	
			<logic:notEmpty name="inserted">
				<logic:equal value="ok" name="inserted">
					<script>alert("����ɹ���");</script>
				</logic:equal>
				<logic:equal value="no" name="inserted">
					<script>alert("����ʧ�ܣ�");</script>
				</logic:equal>	
			</logic:notEmpty>				
		</html:form>
	</body>
</html>
