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
		<html:form action="nbtyJtjjkns" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��������� - ��� - �����ƺ���� 
				</div>
			</div>
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="url" name="url" value="/rcsw_xwzswh.do?method=xwzsAdd" />
				<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}" />
				<input type ="hidden" id="save_xn" name="save_xn" value="${rs.xn}"/>
				<input type ="hidden" id="save_xh" name="save_xh" value="${rs.xh}"/>
				<input type ="hidden" id="save_xq" name="save_xq" value="${rs.xq}"/>
				<input type ="hidden" id="save_nd" name="save_nd" value="${rs.nd}"/>
				<input type ="hidden" id="save_bzlx" name="save_bzlx" value="${rs.bzlx}"/>
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
							<b>��ͥ��������ѧ���������</b>
						</td>
					</tr>
				</thead>
				<tr>
				<td align="right" style="width: 10%">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left">
							<bean:write name='rs' property="save_xh" />
						</td>
					<td align="right" style="width: 10%">
						ѧ�꣺
					</td>
					<td align="left" style="width: 40%">
						<bean:write name="rs" property="save_xn" />	
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name='rs' property="xm" />
					</td>
					<td align="right">
						ѧ�ڣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="save_xq" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<bean:write name='rs' property="xb" />
					</td>
					<td align="right">
						<font color="red"></font>��ȣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="save_nd" />
					</td>		
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="xymc" />
					</logic:notEmpty>
					</td>
					<td align="right">
						���벹����
					</td>
					<td align="left">
						<bean:write name='rs' property="bzmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="zymc" />
					</logic:notEmpty>
					</td>
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name='rs' property="bzje" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="bjmc" />
					</logic:notEmpty>
					</td>
					<td align="right">
						Ʒ�µȵڣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="pddd" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�꼶��
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="nj" />
					</logic:notEmpty>	
					</td>
					<td align="right">
						�������ŷ�ʽ��
					</td>
					<td align="left">
						<html:text name="rs" property="save_fffs"/>
					</td>
					</tr>
				<tr style="height:22px">
					<td align="right">
						��ˣ�
					</td>
					<td align="left">
							<logic:equal value="true" name="isFdy">
							<html:select property="save_fdysh" style="width:90px">
							     <html:option value="��ͨ��" >��ͨ��</html:option>
								 <html:option value="ͨ��" >ͨ��</html:option>
								 <html:option value="δ���" >δ���</html:option>
							   </html:select>
						</logic:equal>
						<logic:notEqual name="isFdy" value="true">
						<logic:equal value="xy" name="userType">
							<html:select property="save_xysh" style="width:90px" >
							     <html:option value="��ͨ��" >��ͨ��</html:option>
								 <html:option value="ͨ��" >ͨ��</html:option>
								 <html:option value="δ���" >δ���</html:option>
							   </html:select>
						</logic:equal>
						<logic:equal value="xx" name="userType">
							<html:select property="save_xxsh" style="width:90px">
							     <html:option value="��ͨ��" >��ͨ��</html:option>
								 <html:option value="ͨ��" >ͨ��</html:option>
								 <html:option value="δ���" >δ���</html:option>
							   </html:select>
						</logic:equal>
						<logic:equal value="admin" name="userType">
							<html:select property="save_xxsh" style="width:90px" >
							     <html:option value="��ͨ��" >��ͨ��</html:option>
								 <html:option value="ͨ��" >ͨ��</html:option>
								 <html:option value="δ���" >δ���</html:option>
							   </html:select>
						</logic:equal>
						</logic:notEqual>
					</td>
					<td align="right">
						
					</td>
					<td align="left">
						
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�������ɣ�
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="sqly" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��ע��
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="bz" onblur="chLeng(this,400)"/>
					</td>
				</tr>
			</table>
					<div class="buttontool" align="center">
					    <logic:equal name="write" value="yes">
							<button type="button" class="button2" onclick="saveData('nbtyJtjjkns.do?method=shOne&doType=modi','shjg')"
								style="width:80px" id="buttonSave">
								�� ��
							</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							�� ��
						</button>
					</div>
				</fieldset>
		</html:form>
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
