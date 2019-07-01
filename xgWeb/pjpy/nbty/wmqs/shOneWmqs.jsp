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
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript">
	
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();">		
		<html:form action="nbtyWmqs" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��������� - ��� - ����������� 
				</div>
			</div>
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}" />
				<input type ="hidden" id="save_xn" name="save_xn" value="${rs.xn}"/>
				<input type ="hidden" id="save_lddm" name="save_lddm" value="${rs.lddm}"/>
				<input type ="hidden" id="save_qsh" name="save_qsh" value="${rs.qsh}"/>
				<logic:equal name="isFdy" value="true">
					<input type="hidden" id="save_fdyshsj" name="save_fdyshsj" value="${nowTime}" />
				</logic:equal>
				<logic:notEqual name="isFdy" value="true">
				<logic:equal name="userType" value="xy">
					<input type="hidden" id="save_xyshsj" name="save_xyshsj" value="${nowTime}" />
				</logic:equal>
				<logic:equal name="userType" value="xx">
					<input type="hidden" id="save_xxshsj" name="save_xxshsj" value="${nowTime}" />
				</logic:equal>
				</logic:notEqual>
				<fieldset>
						<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>�����������</b>
						</td>
					</tr>
				</thead>
				<tr>
				<td align="right" style="width: 10%">
						<font color="red">*</font>ѧ�꣺
					</td>
					<td>
					<bean:write name="rs" property="save_xn"/>
					<html:hidden property="save_xn" value="${xn}"/>
					</td>
					
					<td align="right" style="width: 10%">
						<font color="red">*</font>¥�����ƣ�
					</td>
					<td align="left" style="width: 40%">
						<bean:write name="rs" property="ldmc"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>���Һţ�
					</td>
					<td align="left" style="width: 40%">
						<bean:write name="rs" property="qsh"/>
					</td>
					<td align="right">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<html:text name="rs" property="xymc"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>רҵ��
					</td>
					<td align="left">
						<html:text name="rs" property="zymc"/>
					</td>
					<td align="right">
						<font color="red">*</font>�༶��
					</td>
					<td align="left">
						<html:text name="rs" property="bjmc"/>
					</td>		
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>��ˣ�
					</td>
					<td align="left">
					<!-- �ǰ����� -->
								<logic:equal name="isBzr" value="true">
									<html:select property="save_bzrsh" style="width:180px">
											<html:option value=""></html:option>
											<html:option value="δ���"></html:option>
											<html:option value="ͨ��"></html:option>
											<html:option value="��ͨ��"></html:option>
									</html:select>	
								</logic:equal>
								<logic:notEqual name="isBzr" value="true">
									<logic:equal name="isFdy" value="true">
										<html:select property="save_fdysh" style="width:180px">
											<html:option value=""></html:option>
											<html:option value="δ���"></html:option>
											<html:option value="ͨ��"></html:option>
											<html:option value="��ͨ��"></html:option>
										</html:select>
									</logic:equal>
									<logic:notEqual name="isFdy" value="true">
										<logic:equal name="userType" value="xy">
											<html:select property="save_xysh" style="width:180px">
												<html:option value=""></html:option>
												<html:option value="δ���"></html:option>
												<html:option value="ͨ��"></html:option>
												<html:option value="��ͨ��"></html:option>
											</html:select>
										</logic:equal>
										<logic:notEqual name="userType" value="xy">
											<html:select property="save_xxsh" style="width:180px">
												<html:option value=""></html:option>
												<html:option value="δ���"></html:option>
												<html:option value="ͨ��"></html:option>
												<html:option value="��ͨ��"></html:option>
											</html:select>	
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
					</td>
					<td align="right">
						
					</td>
					<td align="left">
						
					</td>		
				</tr>
				<tr style="height:22px">
					<td align="right">
						��Ҫ�¼���
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" styleId="zysj" property="save_zysj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��ע��
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" styleId="bz" property="save_bz" onblur="chLeng(this,400)"/>
					</td>
				</tr>
			</table>
					<div class="buttontool" align="center">
						<button type="button" class="button2" onclick="saveData('nbtyWmqs.do?method=shOneWmqs&doType=modi','shjg')"
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
		</html:form>
		<logic:notEqual name="write" value="yes">
			<script>
				alert('�Բ����ϼ��û������ͨ�����������ٽ�����ˣ�');
			</script>
			</logic:notEqual>
		<logic:present name="result">
			<input type="hidden" id="message" value="${message}"/>
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</body>
</html>
