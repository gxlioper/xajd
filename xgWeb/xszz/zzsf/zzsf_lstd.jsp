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
			var xh = document.getElementById('xh').value;
			var jtzrks = document.getElementById('jtzrks').value;
			var nzsr = document.getElementById('nzsr').value;
			var lxdh = document.getElementById('lxdh').value;
			var yzbm = document.getElementById('yzbm').value;
			var jtzz = document.getElementById('jtzz').value;
			var jtjjknzyyy = document.getElementById('jtjjknzyyy').value;
			var qfqk_xf = document.getElementById('qfqk_xf').value;
			var qfqk_zsf = document.getElementById('qfqk_zsf').value;
			var qfqk_jcf = document.getElementById('qfqk_jcf').value;
			var qfqk_hj = document.getElementById('qfqk_hj').value;
			var jfjh = document.getElementById('jfjh').value;
			var fmszdwdh = document.getElementById('fmszdwdh').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((jtzrks == null) || (jtzrks == "")){
				alert("��ͥ�˿���������Ϊ��!");
				return false;
			}
			if((nzsr == null) || (nzsr == "")){
				alert("�������벻��Ϊ��!");
				return false;
			}
			if((lxdh == null) || (lxdh == "")){
				alert("��ϵ�绰����Ϊ��!");
				return false;
			}
			if((yzbm == null) || (yzbm == "")){
				alert("�������벻��Ϊ��!");
				return false;
			}
			if((jtzz == null) || (jtzz == "")){
				alert("��ͥסַ����Ϊ��!");
				return false;
			}
			if((jtjjknzyyy == null) || (jtjjknzyyy == "")){
				alert("��ͥ����������Ҫԭ����Ϊ��!");
				return false;
			}
			if((qfqk_xf == null) || (qfqk_xf == "")){
				alert("��Ƿѧ�Ѳ���Ϊ��!");
				return false;
			}
			if((qfqk_zsf == null) || (qfqk_zsf == "")){
				alert("��Ƿס�޷Ѳ���Ϊ��!");
				return false;
			}
			if((qfqk_jcf == null) || (qfqk_jcf == "")){
				alert("��Ƿ�̲ķѲ���Ϊ��!");
				return false;
			}
			if((qfqk_hj == null) || (qfqk_hj == "")){
				alert("Ƿ�ѺϼƲ���Ϊ��!");
				return false;
			}
			if((jfjh == null) || (jfjh == "")){
				alert("�ɷѼƻ�����Ϊ��!");
				return false;
			}
			if((fmszdwdh == null) || (fmszdwdh == "")){
				alert("��ĸ���ڵ�λ(���ί�ᡢ��ί��)�绰����Ϊ��!");
				return false;
			}
			if(jtzz != null){
	         	if(jtzz.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("��ͥסַ���ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
			if(jtjjknzyyy != null){
	         	if(jtjjknzyyy.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("��ͥ����������Ҫԭ���ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(jfjh != null){
	         	if(jfjh.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("�ɷѼƻ����ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zzsf_lstd.do?doType=add";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/zzsf_lstdb.do";
			document.forms[0].submit();
		}
		
		function je(){
			var qfqk_xf = document.getElementById('qfqk_xf').value;
			var qfqk_zsf = document.getElementById('qfqk_zsf').value;
			var qfqk_jcf = document.getElementById('qfqk_jcf').value;
			if((qfqk_xf == null) || (qfqk_xf == "")){
				qfqk_xf = "0";
			}
			if((qfqk_zsf == null) || (qfqk_zsf == "")){
				qfqk_zsf = "0";
			}
			if((qfqk_jcf == null) || (qfqk_jcf == "")){
				qfqk_jcf = "0";
			}
			var qfqk_hj = Math.round(qfqk_xf) + Math.round(qfqk_zsf) + Math.round(qfqk_jcf);
			document.getElementById('qfqk_hj').value=qfqk_hj;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ��ɫͨ������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ�䣡
			</p>
		</center>
	</logic:equal>
		<html:form action="zzsf_lstd.do" method="post">
			<input type="hidden" id="url" name="url" value="/zzsf_lstd.do" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" scope="request" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" scope="request" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" scope="request" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" scope="request" />">
			<input type="hidden" id="xyzzfzryj" name="xyzzfzryj"
				value="<bean:write name="rs" property="xyzzfzryj" scope="request" />">
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd" scope="request" />">

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
						<td align="center" colspan="2">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" colspan="3">
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
						<td align="center" colspan="2">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" colspan="3">
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
					<td>
						<input type="text" id="csrq" name="csrq" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csrq"/>">
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
					<td>
						<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmmmc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ϵ��
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
					<td>
						<div align="center">
							רҵ
						</div>
					</td>
					<td>
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�༶
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="bjmc" readonly="readonly" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��ͥ����
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="jthk">
									<logic:match value="����" name="rs" property="jthk">
										<input type="radio" name="jthk" value="����" checked>&nbsp;&nbsp;A  ����
							         	<input type="radio" name="jthk" value="ũ��">&nbsp;&nbsp;B ũ��
							         </logic:match>
									<logic:match value="ũ��" name="rs" property="jthk">
										<input type="radio" name="jthk" value="����">&nbsp;&nbsp;A  ����
							         	<input type="radio" name="jthk" value="ũ��" checked>&nbsp;&nbsp;B ũ��
							         </logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="jthk">
									<input type="radio" name="jthk" value="����" checked>&nbsp;&nbsp;A  ���� 
							         <input type="radio" name="jthk" value="ũ��">&nbsp;&nbsp;B ũ��
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
						<input type="text" id="jtzrks" name="jtzrks" maxlength="5"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzrks"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��������
						</div>
					</td>
					<td>
						<input type="text" id="nzsr" maxlength="10" name="nzsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nzsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>��ϵ�绰
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="lxdh" name="lxdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��������
						</div>
					</td>
					<td>
						<input type="text" id="yzbm" maxlength="6" name="yzbm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yzbm"/>"
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
					<td colspan="5">
						<html:textarea name="rs" property="jtzz" rows='2' style="width:100%" onblur="yzdx(this,'jtzz')"/>
						<input type="hidden" id="jtzz" name="jtzz" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>��ͥ����������Ҫԭ��
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="jtjjknzyyy" rows='5' style="width:100%" onblur="yzdx(this,'jtjjknzyyy')"/>
						<input type="hidden" id="jtjjknzyyy" name="jtjjknzyyy" value="">
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6">
						<div align="center">
							��ͥ��Ҫ��Ա���
						</div>
					</td>
					<td width="12%" align="center">
						����
					</td>
					<td width="10%" align="center">
						����
					</td>
					<td width="12%" align="center">
						��ν
					</td>
					<td width="12%" align="center">
						������
					</td>
					<td colspan="2" align="center">
						���ڵ�λ
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy1_xm" maxlength="20" name="jtcy1_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy1_nl" maxlength="3" name="jtcy1_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy1_cw" maxlength="10" name="jtcy1_cw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_cw"/>">
					</td>
					<td>
						<input type="text" id="jtcy1_ysr" maxlength="6" name="jtcy1_ysr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_ysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy1_szdw" maxlength="50" name="jtcy1_szdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_szdw"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy2_xm" maxlength="20" name="jtcy2_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy2_nl" maxlength="3" name="jtcy2_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy2_cw" maxlength="10" name="jtcy2_cw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_cw"/>">
					</td>
					<td>
						<input type="text" id="jtcy2_ysr" maxlength="6" name="jtcy2_ysr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_ysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy2_szdw" maxlength="50" name="jtcy2_szdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_szdw"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy3_xm" maxlength="20" name="jtcy3_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy3_nl" maxlength="3" name="jtcy3_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy3_cw" maxlength="10" name="jtcy3_cw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_cw"/>">
					</td>
					<td>
						<input type="text" id="jtcy3_ysr" maxlength="6" name="jtcy3_ysr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_ysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy3_szdw" maxlength="50" name="jtcy3_szdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_szdw"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy4_xm" maxlength="20" name="jtcy4_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy4_nl" maxlength="3" name="jtcy4_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy4_cw" maxlength="10" name="jtcy4_cw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_cw"/>">
					</td>
					<td>
						<input type="text" id="jtcy4_ysr" maxlength="6" name="jtcy4_ysr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_ysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy4_szdw" maxlength="50" name="jtcy4_szdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_szdw"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy5_xm" maxlength="20" name="jtcy5_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy5_nl" maxlength="3" name="jtcy5_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy5_cw" maxlength="10" name="jtcy5_cw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_cw"/>">
					</td>
					<td>
						<input type="text" id="jtcy5_ysr" maxlength="6" name="jtcy5_ysr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_ysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy5_szdw" maxlength="50" name="jtcy5_szdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_szdw"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>��Ƿѧ��
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="qfqk_xf" name="qfqk_xf" maxlength="6"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="qfqk_xf"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��Ƿס�޷�
						</div>
					</td>
					<td>
						<input type="text" id="qfqk_zsf" maxlength="6" name="qfqk_zsf"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="qfqk_zsf"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>��Ƿ�̲ķ�
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="qfqk_jcf" name="qfqk_jcf" maxlength="6"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="qfqk_jcf"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>Ƿ�Ѻϼ�
						</div>
					</td>
					<td>
						<input type="text" id="qfqk_hj" readonly="readonly" name="qfqk_hj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qfqk_hj"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>�ɷѼƻ�
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="jfjh" rows='5' style="width:100%" onblur="yzdx(this,'jfjh')"/>
						<input type="hidden" id="jfjh" name="jfjh" value="">
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							<font color="red">*</font>��ĸ���ڵ�λ(���ί�ᡢ��ί��)�绰
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="fmszdwdh" name="fmszdwdh" maxlength="15"
							style="width:50%;heigh:100%"
							value="<bean:write name="rs" property="fmszdwdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2"
					onClick="yz();">
					�ύ����
				</button>
				<button type="button" class="button2"
					onClick="toPrintOut();">
					��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
