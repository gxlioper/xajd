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


	<title><bean:message key="lable.title" />
	</title>
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
			var xh = document.getElementById('xh').value;
			var zxjdjdm = document.getElementById('zxjdjdm').value;
			var jtzz = document.getElementById('jtzz').value;
			var sqly = document.getElementById('sqly').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((zxjdjdm == null) || (zxjdjdm == "")){
				alert("��ѡ����ѧ��ȼ�!");
				return false;
			}
	       	if(jtzz != null){
	         	if(jtzz.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("��ͥסַ���ܴ���500���ַ�");
	          		 return false;
	       		 }
	       	}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("�������ɲ��ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zjjdzyjsxy_xszz.do?method=gjzxjsqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/zjjdzyjsxy_xszz.do?method=gjzxjsqb";
			document.forms[0].submit();
		}
		function zxjdj(){
			var temp = document.getElementById('dm').value;
			msgArray = new Array();
			msgArray = temp.split('!!OneSpile!!');
			var zxjdjdm = "";
			var zxjdjmc = "";
			var zxjje = "";
			if (msgArray.length > 1) {
				zxjdjdm = msgArray[0];
				zxjdjmc = msgArray[1];
				zxjje = msgArray[2];
			}
			document.getElementById('zxjdjdm').value=zxjdjdm;
			document.getElementById('zxjdjmc').value=zxjdjmc;
			document.getElementById('zxjje').value=zxjje;
		}
		function je(){
			var jthkrs = document.getElementById('jthkrs').value;
			var jtrjysr = document.getElementById('jtrjysr').value;
			var jtyzsr = "0";
			
			if((jthkrs == null) || (jthkrs == "")){
				jthkrs = "0";
			}
			if((jtrjysr == null) || (jtrjysr == "")){
				jtrjysr = "0";
			}
			jtyzsr = Math.round(jthkrs) * Math.round(jtrjysr);
			
			document.getElementById('jthkrs').value = jthkrs;
			document.getElementById('jtrjysr').value = jtrjysr;
			document.getElementById('jtyzsr').value = jtyzsr;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ������ѧ��
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
		<html:form action="zjjdzyjsxy_xszz.do?method=gjzxjsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zjjdzyjsxy_xszz.do?method=gjzxjsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" />">

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
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("��ͨ����ˣ��������룡");
	         		</script>
				</logic:match>
			</logic:present>
			<table class="tbstyle" width="90%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="1" width="20%">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="30%">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="readonly"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" colspan="1" width="20%">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" colspan="1" width="30%">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td width="16%">
						<div align="center">
							����
						</div>
					</td>
					<td width="34%">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="1">
						<input type="text" id="xb" readonly="readonly" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" readonly="readonly" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="1">
						<input type="text" id="mzmc" readonly="readonly" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
					<td>
						<div align="center">
							������ò
						</div>
					</td>
					<td>
						<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmmmc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<div align="center">
							�꼶
						</div>
					</td>
					<td colspan="1">
						<input type="text" id="nj" readonly="readonly" name="nj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />����
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<div align="center">
							רҵ����
						</div>
					</td>
					<td colspan="1">
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<td>
						<div align="center">
							�༶����
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="1">
						<input type="text" id="csrq" readonly="readonly" name="csrq"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csrq"/>">
					</td>
					<td>
						<div align="center">
							��ѧ����
						</div>
					</td>
					<td>
						<input type="text" id="rxny" name="rxny" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxny"/>">
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<div align="center">
							<font color="red">*</font>��ѧ��ȼ�
						</div>
					</td>
					<td colspan="1">
						<html:select name="rs" property="dm" onchange="zxjdj();">
							<html:options collection="gjzxjList" property="dm"
								labelProperty="mc" />
						</html:select>
						<input type="hidden" id="zxjdjdm" name="zxjdjdm"
							value="<bean:write name='rs' property="zxjdjdm" />"
							readonly="true">
						<input type="hidden" id="zxjdjmc" name="zxjdjmc"
							value="<bean:write name='rs' property="zxjdjmc" />"
							readonly="true">
					</td>
					<td>
						<div align="center">
							��ѧ����
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zxjje" name="zxjje"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="zxjje" />"
								readonly="true">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<div align="center">
							���
						</div>
					</td>
					<td colspan="1">
						<input type="text" id="nd" readonly="readonly" name="nd"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nd"/>">
					</td>
					<td>
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="lxdh" name="lxdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="4" style="padding:0;">
						<%@ include file="yhkh.jsp"%>
					</td>
				</tr>	
				<tr>
					<td colspan="1">
						<div align="center">
							��ͥ����
						</div>
					</td>
					<td colspan="1">
						<div align="center">
						<logic:present name="rs" property="jthk">
							<logic:match value="����" name="rs" property="jthk">
								<input type="radio" name="jthk" value="����" checked>&nbsp;&nbsp;����
							    <input type="radio" name="jthk" value="ũ��">&nbsp;&nbsp;ũ��
							</logic:match>
							<logic:match value="ũ��" name="rs" property="jthk">
								<input type="radio" name="jthk" value="����">&nbsp;&nbsp;����
							    <input type="radio" name="jthk" value="ũ��" checked>&nbsp;&nbsp;ũ��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="jthk">
							<input type="radio" name="jthk" value="����" checked>&nbsp;&nbsp;����
							<input type="radio" name="jthk" value="ũ��">&nbsp;&nbsp;ũ��
						</logic:notPresent>
						</div>
					</td>
					<td>
						<div align="center">
							��ͥ��������
						</div>
					</td>
					<td>
						<input type="text" id="jthkrs" name="jthkrs" maxlength="3"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="jthkrs"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<div align="center">
							��ͥ�˾�������
						</div>
					</td>
					<td colspan="1">
						<input type="text" id="jtrjysr" maxlength="5" name="jtrjysr"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="jtrjysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							��ͥ��������
						</div>
					</td>
					<td>
						<input type="text" id="jtyzsr" name="jtyzsr" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtyzsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<div align="center">
							��ͥ������Դ
						</div>
					</td>
					<td colspan="1">
						<input type="text" id="jtsrly" maxlength="400" name="jtsrly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtsrly"/>">
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<input type="text" id="yzbm" name="yzbm" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<div align="center">
							��ͥסַ
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="jtzz" rows='3'
							style="width:100%" onblur="yzdx(this,'jtzz')" />
						<input type="hidden" id="jtzz" name="jtzz" value="">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						��ͥ��Ա
					</td>
				</tr>
				<tr>
					<td width="">
						<div align="center">
							����
						</div>
					</td>
					<td width="">
						<div align="center">
							����
						</div>
					</td>
					<td width="">
						<div align="center">
							�뱾�˹�ϵ
						</div>
					</td>
					<td>
						<div align="center">
							ѧϰ������λ
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_xm" name="jtcy1_xm" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_nl" name="jtcy1_nl" maxlength="3"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_gx" name="jtcy1_gx" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_gx"/>">
						</div>
					</td>
					<td colspan="1">
						<div align="center">
							<input type="text" id="jtcy1_gzdw" name="jtcy1_gzdw"
								maxlength="400" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_gzdw"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_xm" name="jtcy2_xm" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_nl" name="jtcy2_nl" maxlength="3"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_gx" name="jtcy2_gx" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_gx"/>">
						</div>
					</td>
					<td colspan="1">
						<div align="center">
							<input type="text" id="jtcy2_gzdw" name="jtcy2_gzdw"
								maxlength="400" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_gzdw"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_xm" name="jtcy3_xm" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_nl" name="jtcy3_nl" maxlength="3"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_gx" name="jtcy3_gx" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_gx"/>">
						</div>
					</td>
					<td colspan="1">
						<div align="center">
							<input type="text" id="jtcy3_gzdw" name="jtcy3_gzdw"
								maxlength="400" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_gzdw"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_xm" name="jtcy4_xm" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_nl" name="jtcy4_nl" maxlength="3"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_gx" name="jtcy4_gx" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_gx"/>">
						</div>
					</td>
					<td colspan="1">
						<div align="center">
							<input type="text" id="jtcy4_gzdw" name="jtcy4_gzdw"
								maxlength="400" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_gzdw"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_xm" name="jtcy5_xm" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_nl" name="jtcy5_nl" maxlength="3"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_gx" name="jtcy5_gx" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_gx"/>">
						</div>
					</td>
					<td colspan="1">
						<div align="center">
							<input type="text" id="jtcy5_gzdw" name="jtcy5_gzdw"
								maxlength="400" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_gzdw"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��
							<br />
							��
							<br />
							��
							<br />
							��
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="sqly" rows='10'
							style="width:100%" onblur="yzdx(this,'sqly')" />
						<input type="hidden" id="sqly" name="sqly" value="">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2" onClick="yz();">
					�ύ����
				</button>
				<button type="button" class="button2" onClick="toPrintOut();">
					��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
