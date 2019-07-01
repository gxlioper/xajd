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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("ѧУ���������ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/nblg_xszz.do?method=chzxjshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/nblg_xszz.do?method=chzxjshSave" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - �ʺ������ѧ����� - �������
				</div>
			</div>
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
				</logic:match>
			</logic:present>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="6" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" colspan="2">
						ѧ��
					</td>
					<td align="left" colspan="2">
						<bean:write name='rs' property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
						<input type="hidden" id="xn" name="xn"
							value="<bean:write name='rs' property="xn" />" />
					</td>
					<td width="16%">
						<div align="center">
							����
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xb"/>
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfzh"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�꼶
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="nj"/>
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							רҵ����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zymc"/>
					</td>
					<td>
						<div align="center">
							�༶����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="csny"/>
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jg"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥסַ
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtzz"/>
					</td>
					<td>
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td>
						<bean:write name="rs" property="lxdh"/>
					</td>
				</tr>
				<!--��������һ-->
				<logic:notEqual value="13742" name="xxdm">
				<tr>
					<td width="4%" rowspan="6">
						<div align="center">
							��
							<br />
							ͥ
							<br />
							��
							<br />
							Ա
							<br />
							��
							<br />
							��
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td width="17%">
						<div align="center">
							��ν
						</div>
					</td>
					<td width="17%">
						<div align="center">
							������
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							ְҵ�͹���(ѧϰ)��λ
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy1_xm" />
					</td>
					<td>
						<bean:write name='rs' property="jtcy1_cw" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy1_sr" />&nbsp;
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy1_gz" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy2_xm" />
					</td>
					<td>
						<bean:write name='rs' property="jtcy2_cw" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy2_sr" />&nbsp;
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy2_gz" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy3_xm" />
					</td>
					<td>
						<bean:write name='rs' property="jtcy3_cw" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy3_sr" />&nbsp;
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy3_gz" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy4_xm" />
					</td>
					<td>
						<bean:write name='rs' property="jtcy4_cw" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy4_sr" />&nbsp;
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy4_gz" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy5_xm" />
					</td>
					<td>
						<bean:write name='rs' property="jtcy5_cw" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy5_sr" />&nbsp;
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy5_gz" />
					</td>
				</tr>
				</logic:notEqual>
				
				<!--������һ-->
				<logic:equal value="13742" name="xxdm">
					<tr>
						<td width="4%" rowspan="4">
							<div align="center">
								��
								<br />
								ͥ
								<br />
								��
								<br />
								Ա
								<br />
								��
								<br />
								��
							</div>
						</td>
						<td width="12%">
							<div align="center">
								����
							</div>
						</td>
						<td width="17%">
							<div align="center">
								��ν
							</div>
						</td>
						<td width="17%">
							<div align="center">
								������
							</div>
						</td>
						<td colspan="2">
							<div align="center">
								ְҵ�͹���(ѧϰ)��λ
							</div>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;${rs.jtcy1_xm}
						</td>
						<td>
							&nbsp;${rs.jtcy1_gx}
						</td>
						<td>
							&nbsp;${rs.jtcy1_ysr}
						</td>
						<td colspan="2">
							&nbsp;${rs.jtcy1_gzdwmc}
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;${rs.jtcy2_xm}
						</td>
						<td>
							&nbsp;${rs.jtcy2_gx}
						</td>
						<td>
							&nbsp;${rs.jtcy2_ysr}
						</td>
						<td colspan="2">
							&nbsp;${rs.jtcy2_gzdwmc}
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;${rs.jtcy3_xm}
						</td>
						<td>
							&nbsp;${rs.jtcy3_gx}
						</td>
						<td>
							&nbsp;${rs.jtcy3_ysr}
						</td>
						<td colspan="2">
							&nbsp;${rs.jtcy3_gzdwmc}
						</td>
					</tr>
				</logic:equal>
				<!--end������һ-->
				<tr>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="4">
						<bean:write name='rs' property="sqly" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sqsj" />
					</td>
					<td>
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xn" />
					</td>
				</tr>
				<logic:equal name="userType" value="xy">
					<tr>
						<td colspan="2">
							<div align="center">
								���ѵȼ�
							</div>
						</td>
						<td colspan="2">
							<bean:write name='rs' property="kndj" />
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />���
							</div>
						</td>
						<td>
							<html:select name="rs" property="xysh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							<input type="hidden" id="xxsh" name="xxsh"
								value="<bean:write name='rs' property="xxsh" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />������<br />(��ѧ��Ʒ�º�ѧҵ�ɼ�����)
							</div>
						</td>
						<td colspan="4">
							<html:textarea name="rs" property="xyshyj" rows='3'
								style="width:100%" onblur="yzdx(this,'xyshyj')" />
							<input type="hidden" id="xyshyj" name="xyshyj" value="">
							<input type="hidden" id="xxshyj" name="xxshyj"
								value="<bean:write name='rs' property="xxshyj" />" />
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="admin">
					<tr>
						<td colspan="2">
							<div align="center">
								���ѵȼ�
							</div>
						</td>
						<td colspan="2">
							<bean:write name='rs' property="kndj" />
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />���
							</div>
						</td>
						<td>
							<bean:write name='rs' property="xysh" />
							<input type="hidden" id="xysh" name="xysh"
								value="<bean:write name='rs' property="xysh" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								ѧУ���
							</div>
						</td>
						<td colspan="2">
							<html:select name="rs" property="xxsh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td colspan="2">
							<div align="center">
								&nbsp;
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />������<br />(��ѧ��Ʒ�º�ѧҵ�ɼ�����)
							</div>
						</td>
						<td colspan="4">
							<bean:write name='rs' property="xyshyj" />
							<input type="hidden" id="xyshyj" name="xyshyj"
								value="<bean:write name='rs' property="xyshyj" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								ѧУ������
							</div>
						</td>
						<td colspan="4">
							<html:textarea name="rs" property="xxshyj" rows='3'
								style="width:100%" onblur="yzdx(this,'xxshyj')" />
							<input type="hidden" id="xxshyj" name="xxshyj" value="">
						</td>
					</tr>
				</logic:equal>
			</table>
			<div class="buttontool" align="center">
				<logic:notEqual name="userType" value="admin">
					<logic:equal name="rs" property="xxsh" value="δ���">
						<button type="button" class="button2" onclick="yz()" style="width:80px"
							id="buttonSave">
							�� ��
						</button>
					</logic:equal>
					<logic:notEqual name="rs" property="xxsh" value="δ���">
						<button type="button" class="button2" onclick="yz()" style="width:80px"
							id="buttonSave" disabled="disabled">
							�� ��
						</button>
					</logic:notEqual>
				</logic:notEqual>
				<logic:equal name="userType" value="admin">
					<button type="button" class="button2" onclick="yz()" style="width:80px"
						id="buttonSave">
						�� ��
					</button>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2"
					onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"
					style="width:80px" id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
