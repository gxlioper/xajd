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
	<script language="javascript">
	
	</script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/Wjcf_Xskqxx" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�Υ�ʹ��� - ѧ������ - ������Ϣά��
				</div>
			</div>
			<div class="rightcontent">

				<fieldset>
					<legend>
						������Ϣ
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									<bean:message key="lable.xsgzyxpzxy" />��
									<html:text property="xymc" styleId="xymc" readonly="true"></html:text>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;רҵ��
									<html:text property="zymc" styleId="zymc" readonly="true"></html:text>
									&nbsp;&nbsp;&nbsp;�༶��
									<html:text property="bjmc" styleId="bjmc" readonly="true"></html:text>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									ѧ�ţ�
									<html:text property="xh" styleId="xh" readonly="true"></html:text>
									&nbsp;&nbsp;&nbsp;
									������
									<html:text property="xm" styleId="xm" readonly="true"></html:text>
									&nbsp;&nbsp;&nbsp;�����·ݣ�
									<html:text  styleId="rq" property="rq" readonly="true"></html:text>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									�����ʣ�
									<html:text property="cql" styleId="cql" readonly="true"></html:text>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
					<p align="center">
						δ�ҵ��κμ�¼��
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							��¼����
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ��������ͷ��������</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="">
										<td>
											<bean:write name="s" property="xh" />
										</td>
										<td>
											<bean:write name="s" property="mc" />
										</td>
										<td>
											<bean:write name="s" property="kqjg" />
										</td>
										<td>
											<bean:write name="s" property="yf" />
										</td>
									</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
			</div>
		</html:form>
	</body>
</html>
