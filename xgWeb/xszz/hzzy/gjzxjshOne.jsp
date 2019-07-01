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
			var userType = document.getElementById('userType').value;
			var xxsh = document.getElementById('xxsh').value;
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			if(("δ���" != xxsh ) && (userType != "xx")){
				alert("ѧУ����ˣ��������޸���˽��!");
	          	return false;
			}
	       	if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("ѧУ���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
			 refreshForm('/xgxt/hzzyjsxy_xszz.do?method=gjzxjshSave');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ������ѧ����� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="6" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td colspan="2">
						<div align="center">
							��ѧ��ȼ�
						</div>
					</td>
					<td colspan="2">
						<logic:present name="rs" property="zxjdj">
							<logic:match value="һ����ѧ��" name="rs" property="zxjdj">
								<input type="radio" name="zxjdj" value="һ����ѧ��" checked>&nbsp;&nbsp;һ����ѧ��
							    <input type="radio" name="zxjdj" value="������ѧ��">&nbsp;&nbsp;������ѧ��
							</logic:match>
							<logic:match value="������ѧ��" name="rs" property="zxjdj">
								<input type="radio" name="zxjdj" value="һ����ѧ��">&nbsp;&nbsp;һ����ѧ��
							    <input type="radio" name="zxjdj" value="������ѧ��" checked>&nbsp;&nbsp;������ѧ��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="zxjdj">
							<input type="radio" name="zxjdj" value="һ����ѧ��" checked>&nbsp;&nbsp;һ����ѧ��
							<input type="radio" name="zxjdj" value="������ѧ��">&nbsp;&nbsp;������ѧ��
						</logic:notPresent>
					</td>
					<td colspan="2">
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						ѧ��
					</td>
					<td align="left" colspan="2">
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
						<bean:write name='rs' property="xh" />
					</td>
					<td width="16%">
						<div align="center">
							����
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="xm"/>
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
							��ѧ����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="rxny"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="mzmc"/>
					</td>
					<td>
						<div align="center">
							������ò
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zzmmmc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�꼶
						</div>
					</td>
					<td>
						<bean:write name="rs" property="nj"/>
					</td>
					<td colspan="2">
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
							���֤��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sfzh"/>
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="csrq"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							���
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" id="nd" name="nd"
							value="<bean:write name='rs' property="nd" />" />
						<bean:write name="rs" property="nd"/>
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
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="rxqhk"/>
					</td>
					<td>
						<div align="center">
							��ͥ�˿�����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtrkzs"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�˾�������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="rjsr"/>
					</td>
					<td>
						<div align="center">
							��ͥ��������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtyzsr"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							������Դ
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="srly"/>
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="yzbm"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥסַ
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="jtzz"/>
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6">
						<div align="center">
							��<br />ͥ<br />��<br />Ա<br />��<br />��
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td width="22%">
						<div align="center">
							�뱾�˹�ϵ
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							��&nbsp;��&nbsp;��&nbsp;λ
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy1_xm"/>
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_nl"/>
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_ybrgx"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy1_gzdw"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy2_xm"/>
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_nl"/>
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_ybrgx"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy2_gzdw"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy3_xm"/>
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_nl"/>
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_ybrgx"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy3_gzdw"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy4_xm"/>
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_nl"/>
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_ybrgx"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy4_gzdw"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy5_xm"/>
					</td>
					<td>
						<bean:write name="rs" property="jtcy5_nl"/>
					</td>
					<td>
						<bean:write name="rs" property="jtcy5_ybrgx"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy5_gzdw"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="sqly"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sqsj"/>
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
					</td>
				</tr>
				<tr>
					<logic:equal name="userType" value="xx">
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
					</logic:equal>
					<logic:notEqual name="userType" value="xx">
					<td colspan="4">
						<input type="hidden" id="xxsh" name="xxsh"
							value="<bean:write name="rs" property="xxsh"/>">
					</td>
					</logic:notEqual>
					<td colspan="2">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />������
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="xyshyj" rows='4' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<logic:equal name="userType" value="xx">
					<td colspan="2">
						<div align="center">
							ѧУ������
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="xxshyj" rows='4' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
					</td>
					</logic:equal>
					<logic:notEqual name="userType" value="xx">
					<td colspan="6">
						<input type="hidden" id="xxshyj" name="xxshyj" value="<bean:write name="rs" property="xxshyj"/>">
					</td>
					</logic:notEqual>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
