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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(){
			var xmdm = document.getElementById('xmdm').value;
			var zddm = document.getElementById('zddm').value;
			var zddx = document.getElementById('zddx').value;
			if((xmdm == null) || (xmdm == "")){
				alert("��ѡ��������Ŀ!");
				return false;
			}
			if((zddm == null) || (zddm == "")){
				alert("�ֶδ��벻��Ϊ��!");
				return false;
			}
			if((zddx == null) || (zddx == "")){
				zddx = "0";
			}
			if(Math.round(zddx) >= 4000){
				alert("�ֶδ�С���ܳ���4000���ַ�!");
				return false;
			}
			document.forms[0].action = "/xgxt/new_common_xszz.do?method=zzbbzdyzdEdit&acDo=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ��������ά�� - �����Զ����ֶ�ά��
		</div>
	</div>
	<html:form action="shgc_kns.do" method="post">
		<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
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
		<logic:present name="have">
			<logic:match value="have" name="have">
				<script language="javascript">
	         	alert("�ֶδ����Ѿ����ڣ�");
	         	</script>
			</logic:match>
		</logic:present>
		<table class="tbstyle" width="100%">
			<tr>
				<td width="50%">
					<div align="center">
						������Ŀ
					</div>
				</td>
				<td width="50%">
					<logic:equal name="act" value="add">
						<html:select name="rs" property="xmdm"
							style="width:100%;heigh:100%">
							<html:option value=""></html:option>
							<html:options collection="zzxmList" property="xmdm"
								labelProperty="xmmc" />
						</html:select>
					</logic:equal>
					<logic:notEqual name="act" value="add">
						<input type="hidden" id="xmdm" name="xmdm"
							value="<bean:write name="rs" property="xmdm"/>" />
						<input type="text" id="xmmc" name="xmmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xmmc"/>">
					</logic:notEqual>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�ֶδ���
					</div>
				</td>
				<td>
					<logic:equal name="act" value="add">
						<input type="text" id="zddm" name="zddm" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zddm"/>">
					</logic:equal>
					<logic:notEqual name="act" value="add">
						<input type="text" id="zddm" name="zddm" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zddm"/>">
					</logic:notEqual>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�ֶ�����
					</div>
				</td>
				<td>
					<input type="text" id="zdmc" name="zdmc" maxlength="100"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zdmc"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�ֶ�����
					</div>
				</td>
				<td align="center">
					<logic:present name="rs" property="zdlx">
						<logic:match value="һ���ı�" name="rs" property="zdlx">
							<input type="radio" name="zdlx" value="һ���ı�" checked>&nbsp;&nbsp;һ���ı�
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="zdlx" value="�ı���">&nbsp;&nbsp;�ı���
						</logic:match>
						<logic:match value="�ı���" name="rs" property="zdlx">
							<input type="radio" name="zdlx" value="һ���ı�">&nbsp;&nbsp;һ���ı�
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="zdlx" value="�ı���" checked>&nbsp;&nbsp;�ı���
						</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="zdlx">
						<input type="radio" name="zdlx" value="һ���ı�" checked>&nbsp;&nbsp;һ���ı�
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="zdlx" value="�ı���">&nbsp;&nbsp;�ı���
					</logic:notPresent>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�ֶδ�С(��λ���ַ�)
					</div>
				</td>
				<td>
					<logic:equal name="act" value="add">
					<input type="text" id="zddx" name="zddx" maxlength="5"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zddx"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</logic:equal>
					<logic:notEqual name="act" value="add">
					<input type="text" id="zddx" name="zddx" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zddx"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</logic:notEqual>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�Ƿ����Ϊ����
					</div>
				</td>
				<td align="center">
					<logic:present name="rs" property="bxwsz">
						<logic:match value="��" name="rs" property="bxwsz">
							<input type="radio" name="bxwsz" value="��" checked>&nbsp;&nbsp;��
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="bxwsz" value="��">&nbsp;&nbsp;��
						</logic:match>
						<logic:match value="��" name="rs" property="bxwsz">
							<input type="radio" name="bxwsz" value="��">&nbsp;&nbsp;��
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="bxwsz" value="��" checked>&nbsp;&nbsp;��
						</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="bxwsz">
						<input type="radio" name="bxwsz" value="��" checked>&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="bxwsz" value="��">&nbsp;&nbsp;��
					</logic:notPresent>
				</td>
			</tr>
		</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button class="button2" onClick="yz();">
					��&nbsp;&nbsp;&nbsp;&nbsp;��
				</button>
				<button class="button2"
					onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
					��&nbsp;&nbsp;&nbsp;&nbsp;��
				</button>
			</div>
	</html:form>
</body>
</html:html>
