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
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src='/xgxt/dwr/interface/jxglNblg.js'></script>
		<html:form action="/jxgl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ѵ���� - ��Ϣά�� - ������ʦ��Ϣά��
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
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType"/>" />
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<fieldset>
					<legend>
						������ʦ��Ϣ
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>��ʦ���ţ�
							</td>
							<td align="left">
							<logic:equal value="modi" name="doType">
								<html:text name="rs" property="jsdm" styleId="jsdm" readonly="true"></html:text>
							</logic:equal>
							<logic:notEqual value="modi" name="doType">
								<html:text name="rs" property="jsdm" styleId="jsdm" onchange="searchLs(this)"></html:text>
								<button type="button" onclick="jsghInfoTo()"class="button2" id="buttonFindJsgh">
										ѡ��
							</button>
							</logic:notEqual>
							</td>
							<td align="right">
								��ʦ������
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<html:select name="rs" property="xb" styleId="xb" disabled="true">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="Ů">Ů</html:option>
								</html:select>
								<input type="hidden" name="xbV" value="<bean:write name="xbV" scope="request"/>"/>
							</td>
							<td align="right">
								����<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<html:select name="rs" property="xydm" styleId="xy" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
								</html:select>
								<input type="hidden" name="xydmV" value="<bean:write name="rs" property="xydm"/>"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								���壺
							</td>
							<td align="left">
								<html:select name="rs" property="mzdm" styleId="mz" disabled="true">
									<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
								</html:select>
								<input type="hidden" name="mzdmV" value="<bean:write name="rs" property="mzdm"/>"/>
							</td>
							<td align="right">
								�칫�绰��
							</td>
							<td align="left">
								<html:text name="rs" property="lxdh" styleId="lxdh"></html:text>
							</td>
						</tr>
						<tr>
							<td align="right">
								סլ�绰��
							</td>
							<td align="left">
								<html:text name="rs" property="zzdh" styleId="zzdh"></html:text>
							</td>
							<td align="right">
								�ֻ���
							</td>
							<td align="left">
								<html:text name="rs" property="sjhm" styleId="sjhm"></html:text>
							</td>
						</tr>
						<tr>
							<td align="right">
								ְ��	
							</td>
							<td align="left">
								<html:text name="rs" property="zw" styleId="zw"></html:text>
							</td>
							<td align="right">
								��ѵ��ݣ�
							</td>
							<td align="left">
								<html:select property="jxnd" name="rs" styleId="jxnd">
								<html:options collection="xnList" labelProperty="nd" property="nd"/>
								</html:select>
							</td>
						</tr>
						<tr>
						<td align="right">
								���������ӣ�
							</td>
							<td colspan="3" align="left">
								<html:textarea name="rs" property="sfzld" style="width:90%" rows="3" disabled="true"></html:textarea>
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
						onclick="Savedata('jsdm','TeacherInfoSave.do');"
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
