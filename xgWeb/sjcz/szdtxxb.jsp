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
	<body onload="checkWinType();dataManLoad();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
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
				<input type="hidden" id="disableEle" name="disableEle"
					value="" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xymc-dtsj" />
				<input type="hidden" id="url" name="url"
					value="/sjcz/szdtxxb.jsp" />
				<fieldset>
					<legend>
						������̬
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td colspan="5" align="center">
									˫�ܶ�̬��Ϣ
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right"> 
								<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />���ƣ� 
							</td>
							<td align=left>
								<html:select name="rs" property="xymc" style="width:120px"
														styleId="xy">
														<html:option value=""></html:option>
														<html:options collection="xyList" property="xydm"
															labelProperty="xymc" />
													</html:select>
							</td>
							<td align="right"> 
								<font color="red">*</font>ʱ�䣺 
							</td>
							<td align=left>
								<html:text name='rs' property="dtsj" styleId="dtsj" onclick="return showCalendar('dtsj','y-mm-dd');"  />
							</td>
						</tr>
						<tr>
							<td align="right"> 
								<font color="red">*</font>ѧ�꣺ 
							</td>
							<td align=left>
									<html:select name="rs" property="xn" style="width:90px" styleId="xn"
													onchange="">
													<html:options collection="xnList" property="xn"
														labelProperty="xn" />
												</html:select>
							</td>
							<td align="right"> 
								<font color="red">*</font>ѧ�ڣ� 
							</td>
							<td align=left>
								<html:select name="rs" property="xq" style="width:60px" styleId="xq">
														<html:option value=""></html:option>
														<html:options collection="xqList" property="xqdm"
															labelProperty="xqmc" />
													</html:select>
							</td>
						</tr>
						<tr>
							<td align="right"> 
								<font color="red">*</font>�ܴΣ� 
							</td>
							<td align=left>
								<html:select name="rs" property="zc" style="width:120px"
														styleId="zc">
														<html:option value=""></html:option>
														<html:option value="1~2��"></html:option>
														<html:option value="3~4��"></html:option>
														<html:option value="5~6��"></html:option>
														<html:option value="7~8��"></html:option>
														<html:option value="9~10��"></html:option>
														<html:option value="11~12��"></html:option>
														<html:option value="13~14��"></html:option>
														<html:option value="15~16��"></html:option>
														<html:option value="17~18��"></html:option>
														<html:option value="19~20��"></html:option>
													</html:select>
							</td>
						</tr>
					<tr>
							<td align="left">
								���꼶ѧ������̬��
							</td>
							<td align="right" colspan="3">
								<html:textarea name='rs' property="xldt" styleId="xldt"  style="width:99%"
								rows="3"/>
							</td>
						</tr>
						<tr>
							<td align="left">
								<bean:message key="lable.xsgzyxpzxy" />��������������վ������
							</td>
							<td align="right" colspan="3">
								<html:textarea name='rs' property="jkjygz" styleId="jkjygz"  style="width:99%"
								rows="3"/>
							</td>
						</tr>
						<tr>
							<td align="left">
								�ص��עѧ�������
							</td>
							<td align="right" colspan="3">
								<html:textarea name='rs' property="zdgzqk" styleId="zdgzqk"  style="width:99%"
								rows="3"/>
							</td>
						</tr>
						<tr>
							<td align="left">
								��Ҫ��ͨ���������ˣ�
							</td>
							<td align="right" colspan="3">
								<html:textarea name='rs' property="gtjcl" styleId="gtjcl"  style="width:99%"
								rows="3"/>
							</td>
						</tr>
						<tr>
							<td align="left">
								������
							</td>
							<td align="right" colspan="3">
								<html:textarea name='rs' property="qt" styleId="qt"  style="width:99%"
								rows="3"/>
							</td>
						</tr>
					</table>
					<div class="buttontool" align="center">
						<button type="button" class="button2" onclick="dataCanModi(true)"
							style="display:none; width:80px" id="buttonModi">
							�� ��
						</button>
						<button type="button" class="button2" onclick="dataDoSave('xymc-dtsj')"
							style="width:80px" id="buttonSave">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							�� ��
						</button>
					</div>
				</fieldset>
			</logic:notEmpty>
			<jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
		</html:form>
	</body>
</html>
