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
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/nbzympgc" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					�ճ�����-��Ƭ����-��ְԺ��Լ��Ϣ¼��
				</div>
			</div>
			<logic:present name = "noqx">
				<div align="center">
				��ģ��ֻ��<bean:message key="lable.xsgzyxpzxy" />�û�����ʹ��
				</div>
			</logic:present>
			<logic:notPresent name = "noqx">
				<fieldset>
					<legend>
						��ְԺ��Լ��Ϣ¼��
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>��̸��Ŀ��
							</td>
							<td align="left" colspan="3">
								<html:text  property="fttm" styleId="fttm" style="width:90%"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>��̸ʱ�䣺
							</td>
							<td align="left">
								<html:text  property="ftsj" styleId="ftsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('ftsj','y-mm-dd');" />
							</td>
							<td align="right">
								<font color="red">*</font>��̸�ص㣺
							</td>
							<td align="left">
								<html:text  property="ftdd" styleId="ftdd" style="width:300"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>�����ˣ�
							</td>
							<td align="left">
								<html:text  property="zcr" styleId="zcr"/>
							</td>
							<td align="right">
								��Ŀ������:
							</td>
							<td align="left">
								<html:text  property="jmfzr" styleId="jmfzr" style="width:300"/>
							</td>
						</tr>
						<tr>
							<td align="right" rowspan="4">
								�ܷüα���Ϣ��
							</td>
							<td align="center">
								����
							</td>
							<td align="center">
								ְ��
							</td>
							<td align="center">
								�绰
							</td>
						</tr>
						<tr>
							<td align="right">
								<html:text  property="sfjbf" style="width:100%"/>
							</td>
							<td align="left">
								<html:text  property="sfjbfzw" style="width:100%"/>
							</td>
							<td align="right">
								<html:text  property="sfjbfdh" style="width:100%"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<html:text  property="sfjbs" style="width:100%"/>
							</td>
							<td align="left">
								<html:text  property="sfjbszw" style="width:100%"/>
							</td>
							<td align="right">
								<html:text  property="sfjbsdh" style="width:100%"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<html:text  property="sfjbt" style="width:100%"/>
							</td>
							<td align="left">
								<html:text  property="sfjbtzw" style="width:100%"/>
							</td>
							<td align="right">
								<html:text  property="sfjbtdh" style="width:100%"/>
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								<font color="red">*</font>��̸��������
							</td>
							<td colspan="3">
								<html:textarea  property='ftjbnr' style="width:99%" styleId="ftjbnr"
									rows='5' />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								���첿�����
							</td>
							<td colspan="3">
								<html:textarea  property='zbbmyj' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								��ע
							</td>
							<td colspan="3">
								<html:textarea  property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="szsxDataDoSave('nbzympgc.do?method=saveXyftjmdj','fttm-ftsj-ftdd-zcr-ftjbnr');"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
				</div>
			</logic:notPresent>
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("�ύ�ɹ���");
	Close()
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("�ύʧ��,�벻Ҫ�ظ�����");
    </script>
				</logic:equal>
			</logic:present>
			
		</html:form>
	</body>
</html>
