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
			var jtrks = document.getElementById('jtrks').value;
			var jtyzsr = document.getElementById('jtyzsr').value;
			var jtzz = document.getElementById('jtzz').value;
			var yzbm = document.getElementById('yzbm').value;
			var jtdh = document.getElementById('jtdh').value;
			var jtzyjjly = document.getElementById('jtzyjjly').value;
			var bxqjcqk = document.getElementById('bxqjcqk').value;
			var bxqszzqk = document.getElementById('bxqszzqk').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((jtrks == null) || (jtrks == "")){
				alert("��ͥ�˿���������Ϊ��!");
				return false;
			}
			if((jtyzsr == null) || (jtyzsr == "")){
				alert("�������벻��Ϊ��!");
				return false;
			}
			if((jtzz == null) || (jtzz == "")){
				alert("��ͥסַ����Ϊ��!");
				return false;
			}
			if((yzbm == null) || (yzbm == "")){
				alert("�������벻��Ϊ��!");
				return false;
			}
			if((jtdh == null) || (jtdh == "")){
				alert("��ͥ�绰����Ϊ��!");
				return false;
			}
			if((jtzyjjly == null) || (jtzyjjly == "")){
				alert("��ͥ��Ҫ������Դ����Ϊ��!");
				return false;
			}
			if((bxqjcqk == null) || (bxqjcqk == "")){
				alert("��ѧ�ڽ����������Ϊ��!");
				return false;
			}
			if((bxqszzqk == null) || (bxqszzqk == "")){
				alert("��ѧ���������������Ϊ��!");
				return false;
			}
			if(jtzyjjly != null){
	         	if(jtzyjjly.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("��ͥ��Ҫ������Դ���ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(bxqjcqk != null){
	         	if(bxqjcqk.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("��ѧ�ڽ��������Դ���ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(bxqszzqk != null){
	         	if(bxqszzqk.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("��ѧ��������������ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zzsf_tkbz.do?doType=add";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/zzsf_tkbzb.do";
			document.forms[0].submit();
		}
		
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - У��������������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ�䣡
			</p>
		</center>
	</logic:equal>
		<html:form action="zzsf_tkbz.do" method="post">
			<input type="hidden" id="url" name="url" value="/zzsf_tkbz.do" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" scope="request" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" scope="request" />">
			<input type="hidden" id="isKNS" name="isKNS"
				value="<bean:write name="isKNS" scope="request" />">

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
						<td align="center" colspan="2" width="16%">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" colspan="3" width="34%">
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
						<td align="center" colspan="2" width="16%">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" colspan="3" width="34%">
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
					<td colspan="2">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xb" readonly="readonly" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="csny" name="csny" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csny"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="mzmc" readonly="readonly" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
					<td>
						<div align="center">
							������ò
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmmmc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							���
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="nd" readonly="readonly" name="nd"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nd"/>">
					</td>
					<td>
						<div align="center">
							ϵ��
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							רҵ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<td>
						<div align="center">
							�༶
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="bjmc" name="bjmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>ѧ�����
						</div>
					</td>
					<td colspan="6" align="center">
						<logic:present name="rs" property="xslb">
							<logic:match value="����ʦ��" name="rs" property="xslb">
								����:&nbsp;
								<input type="radio" name="xslb" value="����ʦ��" checked>&nbsp;&nbsp;ʦ��
							    <input type="radio" name="xslb" value="���Ʒ�ʦ">&nbsp;&nbsp;��ʦ
							    <input type="radio" name="xslb" value="���Ƹ�ְ">&nbsp;&nbsp;��ְ
							    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ר��:&nbsp;
							    <input type="radio" name="xslb" value="ר��ʦ��">&nbsp;&nbsp;ʦ��
							    <input type="radio" name="xslb" value="ר�Ƹ�ְ">&nbsp;&nbsp;��ְ
							</logic:match>
							<logic:match value="���Ʒ�ʦ" name="rs" property="xslb">
								����:&nbsp;
								<input type="radio" name="xslb" value="����ʦ��">&nbsp;&nbsp;ʦ��
							    <input type="radio" name="xslb" value="���Ʒ�ʦ" checked>&nbsp;&nbsp;��ʦ
							    <input type="radio" name="xslb" value="���Ƹ�ְ">&nbsp;&nbsp;��ְ
							    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ר��:&nbsp;
							    <input type="radio" name="xslb" value="ר��ʦ��">&nbsp;&nbsp;ʦ��
							    <input type="radio" name="xslb" value="ר�Ƹ�ְ">&nbsp;&nbsp;��ְ
							</logic:match>
							<logic:match value="���Ƹ�ְ" name="rs" property="xslb">
								����:&nbsp;
								<input type="radio" name="xslb" value="����ʦ��">&nbsp;&nbsp;ʦ��
							    <input type="radio" name="xslb" value="���Ʒ�ʦ">&nbsp;&nbsp;��ʦ
							    <input type="radio" name="xslb" value="���Ƹ�ְ" checked>&nbsp;&nbsp;��ְ
							    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ר��:&nbsp;
							    <input type="radio" name="xslb" value="ר��ʦ��">&nbsp;&nbsp;ʦ��
							    <input type="radio" name="xslb" value="ר�Ƹ�ְ">&nbsp;&nbsp;��ְ
							</logic:match>
							<logic:match value="ר��ʦ��" name="rs" property="xslb">
								����:&nbsp;
								<input type="radio" name="xslb" value="����ʦ��">&nbsp;&nbsp;ʦ��
							    <input type="radio" name="xslb" value="���Ʒ�ʦ">&nbsp;&nbsp;��ʦ
							    <input type="radio" name="xslb" value="���Ƹ�ְ">&nbsp;&nbsp;��ְ
							    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ר��:&nbsp;
							    <input type="radio" name="xslb" value="ר��ʦ��" checked>&nbsp;&nbsp;ʦ��
							    <input type="radio" name="xslb" value="ר�Ƹ�ְ">&nbsp;&nbsp;��ְ
							</logic:match>
							<logic:match value="ר�Ƹ�ְ" name="rs" property="xslb">
								����:&nbsp;
								<input type="radio" name="xslb" value="����ʦ��">&nbsp;&nbsp;ʦ��
							    <input type="radio" name="xslb" value="���Ʒ�ʦ">&nbsp;&nbsp;��ʦ
							    <input type="radio" name="xslb" value="���Ƹ�ְ">&nbsp;&nbsp;��ְ
							    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ר��:&nbsp;
							    <input type="radio" name="xslb" value="ר��ʦ��">&nbsp;&nbsp;ʦ��
							    <input type="radio" name="xslb" value="ר�Ƹ�ְ" checked>&nbsp;&nbsp;��ְ
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="xslb">
							����:&nbsp;
							<input type="radio" name="xslb" value="����ʦ��" checked>&nbsp;&nbsp;ʦ��
							<input type="radio" name="xslb" value="���Ʒ�ʦ">&nbsp;&nbsp;��ʦ
							<input type="radio" name="xslb" value="���Ƹ�ְ">&nbsp;&nbsp;��ְ
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ר��:&nbsp;
							<input type="radio" name="xslb" value="ר��ʦ��">&nbsp;&nbsp;ʦ��
							<input type="radio" name="xslb" value="ר�Ƹ�ְ">&nbsp;&nbsp;��ְ
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sqsj" readonly="readonly" name="sqsj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqsj"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��ͥ����
						</div>
					</td>
					<td colspan="2" align="center">
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
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>��ͥ�˿�����
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtrks" maxlength="5" name="jtrks"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrks"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��������
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jtyzsr" name="jtyzsr" maxlength="5"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtyzsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>��ͥסַ
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jtzz" maxlength="100" name="jtzz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzz"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>��������
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="yzbm" maxlength="6" name="yzbm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��ͥ�绰
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jtdh" name="jtdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>��ͥ��Ҫ������Դ
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="jtzyjjly" rows='3' style="width:100%" onblur="yzdx(this,'jtzyjjly')"/>
						<input type="hidden" id="jtzyjjly" name="jtzyjjly" value="">
					</td>
				</tr>
				<tr>
					<td rowspan="6" scope="row" width="4%">
						<div align="center">
							��
							<br />
							ͥ
							<br />
							��
							<br />
							Ҫ
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
					<td width="10%">
						<div align="center">
							����
						</div>
					</td>
					<td width="10%">
						<div align="center">
							��ν
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							���ڵ�λ
						</div>
					</td>
					<td width="10%">
						<div align="center">
							������
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_xm" maxlength="20" name="jtcy1_xm"
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
							<input type="text" id="jtcy1_cw" maxlength="10" name="jtcy1_cw"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_cw"/>">
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<input type="text" id="jtcy1_szdw" maxlength="50"
								name="jtcy1_szdw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_szdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_ysr" name="jtcy1_ysr" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_xm" maxlength="20" name="jtcy2_xm"
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
							<input type="text" id="jtcy2_cw" maxlength="10" name="jtcy2_cw"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_cw"/>">
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<input type="text" id="jtcy2_szdw" maxlength="50"
								name="jtcy2_szdw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_szdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_ysr" name="jtcy2_ysr" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_xm" maxlength="20" name="jtcy3_xm"
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
							<input type="text" id="jtcy3_cw" maxlength="10" name="jtcy3_cw"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_cw"/>">
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<input type="text" id="jtcy3_szdw" maxlength="50"
								name="jtcy3_szdw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_szdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_ysr" name="jtcy3_ysr" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_xm" maxlength="20" name="jtcy4_xm"
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
							<input type="text" id="jtcy4_cw" maxlength="10" name="jtcy4_cw"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_cw"/>">
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<input type="text" id="jtcy4_szdw" maxlength="50"
								name="jtcy4_szdw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_szdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_ysr" name="jtcy4_ysr" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_xm" maxlength="20" name="jtcy5_xm"
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
							<input type="text" id="jtcy5_cw" maxlength="10" name="jtcy5_cw"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_cw"/>">
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<input type="text" id="jtcy5_szdw" maxlength="50"
								name="jtcy5_szdw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_szdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_ysr" name="jtcy5_ysr" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>��ѧ�ڽ������
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="bxqjcqk" rows='5' style="width:100%" onblur="yzdx(this,'bxqjcqk')"/>
						<input type="hidden" id="bxqjcqk" name="bxqjcqk" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>��ѧ�����������
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="bxqszzqk" rows='5' style="width:100%" onblur="yzdx(this,'bxqszzqk')"/>
						<input type="hidden" id="bxqszzqk" name="bxqszzqk" value="">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2" id="tjbut"
					onClick="yz();">
					�ύ����
				</button>
				<button type="button" class="button2"
					onClick="toPrintOut();">
					��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
				</button>
				<logic:equal name="isKNS" value="no">
					<br /><font color="red">��������������������!</font>
				</logic:equal>
			</div>
	</logic:equal>
	</html:form>
</body>
<script language="javascript">
	var isKNS = document.getElementById('isKNS').value;
	if (isKNS == "is") {
		document.getElementById('tjbut').disabled = "";
	} else {
		document.getElementById('tjbut').disabled = "true";
	}
</script>
</html:html>
