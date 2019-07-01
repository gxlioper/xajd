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
	<body onload="checkWinType();checkLzf()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/xsgyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/init_page_info.do?doType=ssfb" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow">��ǰ����λ�ã� ��Ԣ���� - ������Ū - ¥�㳤������Ϣ</span>
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
				    	alert("�������ѧ����Ч!");
				    </script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/gygl/gygl_lczxxb.jsp" />
				<fieldset>
					<legend>
						¥�㳤��Ϣά��
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
							<td align="right">
								������
							</td>
							<td align="left">
								<bean:write name="rs" property="xm"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<bean:write name="rs" property="xb"/>
							</td>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<bean:write name="rs" property="nj"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<bean:write name="rs" property="xymc"/>
							</td>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<bean:write name="rs" property="zymc"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<bean:write name="rs" property="bjmc"/>
							</td>
							<td align="right">
								<font color="red">*</font>¥�����ƣ�
							</td>
							<td align="left">
								<html:select name="rs" property="lddm" styleId="lddm">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>���Һţ�
							</td>
							<td align="left">
								<html:text name="rs" property="qsh" styleId="qsh" />
							</td>
							<td align="right">
								�ֻ��ţ�
							</td>
							<td align="left">
								<html:text name="rs" property="sjh" styleId="sjh" maxlength="24"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								���ҵ绰��
							</td>
							<td align="left">
								<html:text name="rs" property="qsdh" styleId="qsdh" maxlength="13"/>
							</td>
							<td align="right">
								����ְ��
							</td>
							<td align="left">
								<html:text name="rs" property="drzw" styleId="drzw"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>����ʱ�䣺
							</td>
							<td align="left">
								<html:text name="rs" property="rmsj" styleId="rmsj" onclick="return showCalendar('rmsj','y-mm-dd');" readonly="true"/>
							</td>
							<td align="right">
								��ְʱ�䣺
							</td>
							<td align="left">
								<html:text name="rs" property="lzsj" styleId="lzsj" readonly="true" onclick="sflz();return showCalendar('lzsj','y-mm-dd');"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								��ְԭ��
							</td>
							<td align="left">
								<html:text name="rs" property="lzyy" styleId="lzyy"/>
							</td>
							<td align="right">
							</td>
							<td align="left">
							</td>
						</tr>
						</table>
						<table width="100%" class="tbstyle" id="sfgh" style="display: none;">
						<tr>
							<td align="center" colspan="4">
								������Ա��Ϣ
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="ghhxh" readonly="readonly"
									styleId="ghhxh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							</td>
							<td align="right">
								������
							</td>
							<td align="left">
								<html:text name="rs" property="ghhxbm" styleId="ghhxm" readonly="true"/>
							</td>
						 </tr>
						<tr>
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<html:text name="rs" property="ghhxb" styleId="ghhxb" readonly="true"/>
							</td>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<html:text name="rs" property="ghhbjmc" styleId="ghhbjmc" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>¥�����ƣ�
							</td>
							<td align="left">
								<html:select name="rs" property="ghhlddm" styleId="ghhlddm" style="width:60px">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
								</html:select>
							</td>
							<td align="right">
								<font color="red">*</font>���Һţ�
							</td>
							<td align="left">
								<html:text name="rs" property="ghhqsh" styleId="ghhqsh"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								�ֻ��ţ�
							</td>
							<td align="left">
								<html:text name="rs" property="ghhsjh" styleId="ghhsjh"/>
							</td>
							<td align="right">
								���ҵ绰��
							</td>
							<td align="left">
								<html:text name="rs" property="ghhqsdh" styleId="ghhqsdh"/>
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button class="button2"
						onclick="Savedata('xh-lddm-qsh-rmsj','/xgxt/lczxxSave.do');"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="view" name="result">
			<script>
				alert("�����ɹ�!");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
	</body>
</html>
