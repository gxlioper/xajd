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
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<html:form action="/viewArmyStu" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�޼�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<fieldset>
					<legend>
						�̹���Ϣ
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>�̹ٱ�ţ�
							</td>
							<td align="left">
							<logic:equal value="modi" name="doType">
								<html:text name="rs" property="jgbh" styleId="jgbh" readonly="true"></html:text>
							</logic:equal>
							<logic:notEqual value="modi" name="doType">
								<html:text name="rs" property="jgbh" styleId="jgbh"></html:text>
							</logic:notEqual>
							</td>
							<td align="right">
								<font color="red">*</font>�̹�������
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<html:select name="rs" property="xb" styleId="xb">
									<html:options collection="xbList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
							<td align="right">
								���壺
							</td>
							<td align="left">
								<html:select name="rs" property="mzdm" styleId="mz">
									<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								��ϵ�绰��
							</td>
							<td align="left">
								<html:text name="rs" property="lxdh" styleId="lxdh"></html:text>
							</td>
							<td align="right">
								�������ӣ�
							</td>
							<td align="left">
								<html:select name="rs" property="sfzld" styleId="sfzld">
									<html:option value=""></html:option>
									<html:options collection="bzList" property="bz" labelProperty="bz"/>
								</html:select>
							</td>
						</tr>
						<%--�㽭��ѧ������<bean:message key="lable.xsgzyxpzxy" />--%>
						<logic:equal value="13022" name="xxdm">
							<tr>
							<td align="right">
								��ѵ��ݣ�
							</td>
							<td align="left">
								<html:select property="jxnd" styleId="jxnd" name="rs">
								<html:options collection="xnList" labelProperty="nd" property="nd"/>
								</html:select>
							</td>
							<td align="right">
								ְ��
							</td>
							<td align="left">
								<html:text name='rs' property="zw" styleId="zw" maxlength="50"/>
							</td>
						</tr>	
						</logic:equal>
						<%--end�㽭��ѧ������<bean:message key="lable.xsgzyxpzxy" />--%>
						<tr>
							<td align="right">
								���Ӵ�����
							</td>
							<td align="left">
								<html:text name='rs' property="ddcs" styleId="ddcs" />
							</td>
							<td align="right">
							</td>
							<td align="left">
							</td>
						</tr>				
						<tr>
						<td align="right">
							��ע��
						</td>
						<td colspan="3" align="left">
							<html:textarea name="rs" property="bz" style="width:90%" rows="3"></html:textarea>
						</td>
					</tr>
				</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="if(chkTeacherCode('jgbh')){Savedata('jgbh-xm','DrillmasterInfoSave.do')};"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("�����ɹ�!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ��!");
			</script>
		</logic:equal>
	</body>
</html>
