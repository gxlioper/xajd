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
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="js/jxglFunction.js"></script>	
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="disableElement('xh-fzlddm-jxnd')">
		
		<script type="text/javascript">
		 function checkLength(){
		 	var zw = document.getElementById("zw").value;
		 	var bz = document.getElementById("bz").value;
		 	
		 	if(zw != null){
	         	if(zw.replace(/[^\u0000-\u00ff]/g, "**").length > 150){	         
	          		 alert("ְ���ܴ���150���ַ�");
	          		 return false;
	       		 }
			}
			if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("��ע���ܴ���500���ַ�");
	          		 return false;
	       		 }
			}
			return true;
		 }
		</script>
		<html:form action="/jxgl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
<%--			<logic:empty name="rs">--%>
<%--				<br />--%>
<%--				<br />--%>
<%--				<p align="center">--%>
<%--					�޼�¼��--%>
<%--				</p>--%>
<%--			</logic:empty>--%>
<%--			<logic:notEmpty name="rs">--%>
				<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>" />
				<input type="hidden" id="doType" name="doType" value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="oper" name="oper" value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="url" name="url" value="/jxgl/nblg/xsjxcxgbxx.jsp" />
				<input type="hidden" name="getStuInfo" id="getStuInfo" value="xh-xm-xb-bjmc"/>
				<fieldset>
					<legend>
						��ѵѧ����ѵ�ɲ���Ϣ
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
								<html:options collection="ldList" labelProperty="mc" property="dm"/>
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
								<html:textarea property="zw" name="rs" style="width:100%"/>
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
						onclick="if(checkLength()){Savedata('xh-jxnd-fzlddm','jxgl.do?method=saveJxcxgbxx');}"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
<%--			</logic:notEmpty>--%>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<logic:present name="msg">
				<input type="hidden" id="msg" value="<bean:write name="msg"/>"/>
				<script>
					alert(document.getElementById('msg').value);
				</script>
			</logic:present>
			<logic:notPresent name="msg">
				<script>
					alert("����ʧ��!");
				</script>
			</logic:notPresent>
		</logic:equal>
	</body>
</html>
