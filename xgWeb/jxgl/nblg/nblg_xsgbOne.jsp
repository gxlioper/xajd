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
					��ǰ����λ�ã���ѵ���� - ��Ϣά�� - ѧ����ѵ�ɲ�ά��
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
				<input type="hidden" id="url" name="url" value="/jxgl/nblg/nblg_xsgbOne.jsp" />
				<input type="hidden" name="getStuInfo" id="getStuInfo" value="xh-xm-xb-bjmc"/>
				<fieldset>
					<legend>
						�ɲ���Ϣ
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<logic:equal value="add" name="doType">
									<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu" >
										ѡ��
									</button>
								</logic:equal>
								<logic:equal value="modi" name="doType">
									<html:text name='rs' property="xh" readonly="readonly" styleId="xh"/>
									<html:hidden property="xh" name="rs"/>
								</logic:equal>
							</td>
							<td align="right">
								<font color="red">*</font>���ӣ�
							</td>
							<td align="left">
								<html:select property="fzlddm" name="rs" styleId="fzlddm">
								<html:option value=""></html:option>
								<html:options collection="ldList" labelProperty="bzmc" property="bzdm"/>
								</html:select>
								<html:hidden property="fzlddm" name="rs"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								������
							</td>
							<td align="left">
								<html:text property="xm" name="rs" disabled="true"/>
							</td>
							<td align="right">
								<font color="red">*</font>��ѵ��ȣ�
							</td>
							<td align="left">
								<html:select property="jxnd" name="rs" styleId="jxnd">
								<html:option value=""></html:option>
								<html:options collection="xnList" labelProperty="nd" property="nd"/>
								</html:select>
								<html:hidden property="jxnd" name="rs"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<html:text property="xb" name="rs" disabled="true"/>
							</td>
							<td align="right">
								סլ�绰��
							</td>
							<td align="left">
								<html:text property="zzdh" name="rs" styleId="zzdh"/>
							</td>
						</tr>		
						<tr>
							<td align="right">
								�༶���ƣ�
							</td>
							<td align="left">
								<html:text property="bjmc" name="rs" disabled="true"/>
							</td>
							<td align="right">
								�ֻ����룺
							</td>
							<td align="left">
								<html:text property="sjhm" name="rs" styleId="sjhm"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								ְ��
							</td>
							<td align="left" colspan="3">
								<html:text property="zw" name="rs" styleId="zw"/>
							</td>
						</tr>	
						
						<tr>
							<td align="right">
								��ע��
							</td>
							<td align="left" colspan="3">
								<html:textarea property="bz" name="rs" style="width:100%"/>
							</td>
						</tr>				
				</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="if(chkTeacherCode('xh')){Savedata('xh-fzlddm-jxnd','saveXscxgbInfo.do')};"
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
