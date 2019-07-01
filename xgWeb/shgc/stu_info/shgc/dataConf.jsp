<%@ page language="java" contentType="text/html; charset=gb2312"%>

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
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/studentMessage_conf" method="post">
			<div class="title">
				<div class="title_img">
					��ǰ����λ�ã�ѧ����Ϣ - ���ݳ�ʼ��</div>
			</div>
			<center>
			<fieldset>
				<legend>
					��ʼ������</legend>
				<table width="80%" class="tbstyle" align="center">
					<thead>
						<tr align="center">
							<td colspan="2">
								ѧ����ҵ���³�ʼ��</td>
						</tr>
					</thead>
					<tr>
						<td align="right">����ѧ���º�ѧ���Զ����㣺</td>
						<td align="">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						   <font color="red">������³�ʼ����ȷ��ѧ�ƺ���ѧ����׼ȷ��</font>
						   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button name="Submit2" class="button2"
								onclick="refreshForm('stu_info_add.do?method=dataConf&doType=automatism')">
								�� ʼ ��
							</button>
						</td>						
					</tr>
					<tr>
						<td align="right">����ȫ��ѧ���ı�ҵ����Ϊ��</td>
						<td align="left">	
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;						
							<input type="text" id="byny" name="byny"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button name="Submit2" class="button2"
								onclick="refreshForm('stu_info_add.do?method=dataConf&doType=manual')">
								�� ʼ ��
							</button>
						</td>						
					</tr>	
					<thead>
						<tr align="center">
							<td colspan="2">
							&nbsp;
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			</center>	
			<logic:notEmpty name="oper">
				<logic:equal value="true" name="result">
					<script>alert("����ɹ���");</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>alert("����ʧ�ܣ�");</script>
				</logic:equal>	
			</logic:notEmpty>				
		</html:form>
	</body>
</html>
