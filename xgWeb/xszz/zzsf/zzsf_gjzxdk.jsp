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
			var jg = document.getElementById('jg').value;
			var hjszd = document.getElementById('hjszd').value;
			var jtdh = document.getElementById('jtdh').value;
			var yddh = document.getElementById('yddh').value;
			var e_mail = document.getElementById('e_mail').value;
			var yzbm = document.getElementById('yzbm').value;
			var jtdz = document.getElementById('jtdz').value;
			var jzr1_xm = document.getElementById('jzr1_xm').value;
			var jzr1_xb = document.getElementById('jzr1_xb').value;
			var jzr1_zjlxjhm = document.getElementById('jzr1_zjlxjhm').value;
			var jzr1_yjkrgx = document.getElementById('jzr1_yjkrgx').value;
			var jzr1_lxdh = document.getElementById('jzr1_lxdh').value;
			var jzr1_txdz = document.getElementById('jzr1_txdz').value;
			var jzr2_xm = document.getElementById('jzr2_xm').value;
			var jzr2_xb = document.getElementById('jzr2_xb').value;
			var jzr2_zjlxjhm = document.getElementById('jzr2_zjlxjhm').value;
			var jzr2_yjkrgx = document.getElementById('jzr2_yjkrgx').value;
			var jzr2_lxdh = document.getElementById('jzr2_lxdh').value;
			var jzr2_txdz = document.getElementById('jzr2_txdz').value;
			var jkzje = document.getElementById('jkzje').value;
			var dkqx = document.getElementById('dkqx').value;
			var xfdj = document.getElementById('xfdj').value;
			var zsfdj = document.getElementById('zsfdj').value;
			var shfdj = document.getElementById('shfdj').value;
			var skrzhlxjzh = document.getElementById('skrzhlxjzh').value;
		
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((jg == null) || (jg == "")){
				alert("���᲻��Ϊ��!");
				return false;
			}
			if((hjszd == null) || (hjszd == "")){
				alert("�������ڵز���Ϊ��!");
				return false;
			}
			if((jtdh == null) || (jtdh == "")){
				alert("��ͥ�绰����Ϊ��!");
				return false;
			}
			if((yddh == null) || (yddh == "")){
				alert("�ƶ��绰����Ϊ��!");
				return false;
			}
			if((e_mail == null) || (e_mail == "")){
				alert("E-Mail��ַ����Ϊ��!");
				return false;
			}
			if((yzbm == null) || (yzbm == "")){
				alert("�������벻��Ϊ��!");
				return false;
			}
			if((jtdz == null) || (jtdz == "")){
				alert("��ͥ��ַ����Ϊ��!");
				return false;
			}
			if((jzr1_xm == null) || (jzr1_xm == "")){
				alert("��֤��1_��������Ϊ��!");
				return false;
			}
			if((jzr1_xb == null) || (jzr1_xb == "")){
				alert("��֤��1_�Ա���Ϊ��!");
				return false;
			}
			if((jzr1_zjlxjhm == null) || (jzr1_zjlxjhm == "")){
				alert("��֤��1_֤�����ͼ����벻��Ϊ��!");
				return false;
			}
			if((jzr1_yjkrgx == null) || (jzr1_yjkrgx == "")){
				alert("��֤��1_�����˹�ϵ����Ϊ��!");
				return false;
			}
			if((jzr1_lxdh == null) || (jzr1_lxdh == "")){
				alert("��֤��1_��ϵ�绰����Ϊ��!");
				return false;
			}
			if((jzr1_txdz == null) || (jzr1_txdz == "")){
				alert("��֤��1_ͨѶ��ַ����Ϊ��!");
				return false;
			}
			if((jzr2_xm == null) || (jzr2_xm == "")){
				alert("��֤��2_��������Ϊ��!");
				return false;
			}
			if((jzr2_xb == null) || (jzr2_xb == "")){
				alert("��֤��2_�Ա���Ϊ��!");
				return false;
			}
			if((jzr2_zjlxjhm == null) || (jzr2_zjlxjhm == "")){
				alert("��֤��2_֤�����ͼ����벻��Ϊ��!");
				return false;
			}
			if((jzr2_yjkrgx == null) || (jzr2_yjkrgx == "")){
				alert("��֤��2_�����˹�ϵ����Ϊ��!");
				return false;
			}
			if((jzr2_lxdh == null) || (jzr2_lxdh == "")){
				alert("��֤��2_��ϵ�绰����Ϊ��!");
				return false;
			}
			if((jzr2_txdz == null) || (jzr2_txdz == "")){
				alert("��֤��2_ͨѶ��ַ����Ϊ��!");
				return false;
			}
			if((jkzje == null) || (jkzje == "")){
				alert("����ܽ���Ϊ��!");
				return false;
			}
			if((dkqx == null) || (dkqx == "")){
				alert("�������޲���Ϊ��!");
				return false;
			}
			if((xfdj == null) || (xfdj == "")){
				alert("ѧ�ѵ��۲���Ϊ��!");
				return false;
			}
			if((zsfdj == null) || (zsfdj == "")){
				alert("ס�޷ѵ��۲���Ϊ��!");
				return false;
			}
			if((shfdj == null) || (shfdj == "")){
				alert("����ѵ��۲���Ϊ��!");
				return false;
			}
			if((skrzhlxjzh == null) || (skrzhlxjzh == "")){
				alert("�տ����ʻ����ͼ��ʺŲ���Ϊ��!");
				return false;
			}
			
			document.forms[0].action = "/xgxt/zzsf_gjzxdk.do?doType=add";
			document.forms[0].submit();
		}
		
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/zzsf_gjzxdkb.do";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ������ѧ��������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ�䣡
			</p>
		</center>
	</logic:equal>
		<html:form action="zzsf_gjzxdk.do" method="post">
			<input type="hidden" id="url" name="url" value="/zzsf_gjzxdk.do" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
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
						<td align="center" width="16%">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" width="34%">
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
						<td align="center" width="16%">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" width="34%">
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
					<td>
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
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
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<input type="text" id="mzmc" readonly="readonly" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ϵ��
						</div>
					</td>
					<td>
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
					<td>
						<div align="center">
							�༶
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" readonly="readonly" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
					<td>
						<div align="center">
							�꼶
						</div>
					</td>
					<td>
						<input type="text" id="nj" readonly="readonly" name="nj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ѧ����
						</div>
					</td>
					<td>
						<input type="text" id="rxny" readonly="readonly" name="rxny"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxny"/>">
					</td>
					<td>
						<div align="center">
							��ҵ����
						</div>
					</td>
					<td>
						<input type="text" id="byny" readonly="readonly" name="byny"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="byny"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<input type="text" id="xz" readonly="readonly" name="xz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xz"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>���ѧ��
						</div>
					</td>
					<td>
						<html:select name="rs" property="zgxl">
							<html:options collection="zgxlList" property="zgxl"
								labelProperty="zgxl" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>����
						</div>
					</td>
					<td>
						<input type="text" id="jg" maxlength="25" name="jg"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jg"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>�������ڵ�
						</div>
					</td>
					<td>
						<input type="text" id="hjszd" maxlength="25" name="hjszd"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hjszd"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>����״��
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="jkzk">
									<logic:match value="����" name="rs" property="jkzk">
										<input type="radio" name="jkzk" value="����" checked>&nbsp;&nbsp;����
							         	<input type="radio" name="jkzk" value="һ��">&nbsp;&nbsp;һ��
							         	<input type="radio" name="jkzk" value="��">&nbsp;&nbsp;��
							         </logic:match>
									<logic:match value="һ��" name="rs" property="jkzk">
										<input type="radio" name="jkzk" value="����">&nbsp;&nbsp;����
							         	<input type="radio" name="jkzk" value="һ��" checked>&nbsp;&nbsp;һ��
							         	<input type="radio" name="jkzk" value="��">&nbsp;&nbsp;��
							         </logic:match>
							         <logic:match value="��" name="rs" property="jkzk">
										<input type="radio" name="jkzk" value="����">&nbsp;&nbsp;����
							         	<input type="radio" name="jkzk" value="һ��">&nbsp;&nbsp;һ��
							         	<input type="radio" name="jkzk" value="��" checked>&nbsp;&nbsp;��
							         </logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="jkzk">
									<input type="radio" name="jkzk" value="����" checked>&nbsp;&nbsp;����
							         <input type="radio" name="jkzk" value="һ��">&nbsp;&nbsp;һ��
							         <input type="radio" name="jkzk" value="��">&nbsp;&nbsp;��
						         </logic:notPresent>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>����״��
						</div>
					</td>
					<td>
						<html:select name="rs" property="hyzk">
							<html:options collection="hyzkList" property="hyzk"
								labelProperty="hyzk" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��ͥ�绰
						</div>
					</td>
					<td>
						<input type="text" id="jtdh" maxlength="15" name="jtdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>�ƶ��绰
						</div>
					</td>
					<td>
						<input type="text" id="yddh" maxlength="15" name="yddh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yddh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>E-Mail��ַ
						</div>
					</td>
					<td>
						<input type="text" id="e_mail" maxlength="40" name="e_mail"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="e_mail"/>">
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
					<td>
						<div align="center">
							<font color="red">*</font>��ͥ��ַ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtdz" maxlength="100" name="jtdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdz"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��֤��1_����
						</div>
					</td>
					<td>
						<input type="text" id="jzr1_xm" maxlength="20" name="jzr1_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr1_xm"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��֤��1_�Ա�
						</div>
					</td>
					<td>
						<input type="text" id="jzr1_xb" maxlength="5" name="jzr1_xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr1_xb"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��֤��1_֤�����ͼ�����
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jzr1_zjlxjhm" maxlength="50" name="jzr1_zjlxjhm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr1_zjlxjhm"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��֤��1_�����˹�ϵ
						</div>
					</td>
					<td>
						<input type="text" id="jzr1_yjkrgx" maxlength="10" name="jzr1_yjkrgx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr1_yjkrgx"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��֤��1_��ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="jzr1_lxdh" maxlength="15" name="jzr1_lxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr1_lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��֤��1_ͨѶ��ַ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jzr1_txdz" maxlength="100" name="jzr1_txdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr1_txdz"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��֤��2_����
						</div>
					</td>
					<td>
						<input type="text" id="jzr2_xm" maxlength="20" name="jzr2_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr2_xm"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��֤��2_�Ա�
						</div>
					</td>
					<td>
						<input type="text" id="jzr2_xb" maxlength="5" name="jzr2_xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr2_xb"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��֤��2_֤�����ͼ�����
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jzr2_zjlxjhm" maxlength="50" name="jzr2_zjlxjhm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr2_zjlxjhm"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��֤��2_�����˹�ϵ
						</div>
					</td>
					<td>
						<input type="text" id="jzr2_yjkrgx" maxlength="10" name="jzr2_yjkrgx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr2_yjkrgx"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��֤��2_��ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="jzr2_lxdh" maxlength="15" name="jzr2_lxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr2_lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��֤��2_ͨѶ��ַ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jzr2_txdz" maxlength="100" name="jzr2_txdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr2_txdz"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>����ܽ��
						</div>
					</td>
					<td>
						<input type="text" id="jkzje" maxlength="10" name="jkzje"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jkzje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��������
						</div>
					</td>
					<td>
						<input type="text" id="dkqx" maxlength="20" name="dkqx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkqx"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>���ʽ
						</div>
					</td>
					<td align="left" colspan="3">
						<logic:present name="rs" property="hkfs">
									<logic:match value="�ȶϢ���" name="rs" property="hkfs">
										<input type="radio" name="hkfs" value="�ȶϢ���" checked>&nbsp;&nbsp;�ȶϢ���
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							         	<input type="radio" name="hkfs" value="�ȶ�𻹿">&nbsp;&nbsp;�ȶ�𻹿
							         </logic:match>
									<logic:match value="�ȶ�𻹿" name="rs" property="hkfs">
										<input type="radio" name="hkfs" value="�ȶϢ���">&nbsp;&nbsp;�ȶϢ���
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							         	<input type="radio" name="hkfs" value="�ȶ�𻹿" checked>&nbsp;&nbsp;�ȶ�𻹿
							         </logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="hkfs">
									<input type="radio" name="hkfs" value="�ȶϢ���" checked>&nbsp;&nbsp;�ȶϢ���
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							         <input type="radio" name="hkfs" value="�ȶ�𻹿">&nbsp;&nbsp;�ȶ�𻹿
						         </logic:notPresent>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��������
						</div>
					</td>
					<td align="left" colspan="3">
						<logic:present name="rs" property="dkzl">
									<logic:match value="���������Ϣ������ѧ����" name="rs" property="dkzl">
										<input type="radio" name="dkzl" value="���������Ϣ������ѧ����" checked>&nbsp;&nbsp;���������Ϣ������ѧ����
										&nbsp;&nbsp;&nbsp;&nbsp;
							         	<input type="radio" name="dkzl" value="�ط�������Ϣ������ѧ����">&nbsp;&nbsp;�ط�������Ϣ������ѧ����
							         </logic:match>
									<logic:match value="�ط�������Ϣ������ѧ����" name="rs" property="dkzl">
										<input type="radio" name="dkzl" value="���������Ϣ������ѧ����">&nbsp;&nbsp;���������Ϣ������ѧ����
										&nbsp;&nbsp;&nbsp;&nbsp;
							         	<input type="radio" name="dkzl" value="�ط�������Ϣ������ѧ����" checked>&nbsp;&nbsp;�ط�������Ϣ������ѧ����
							         </logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="dkzl">
									<input type="radio" name="dkzl" value="���������Ϣ������ѧ����" checked>&nbsp;&nbsp;���������Ϣ������ѧ����
									&nbsp;&nbsp;&nbsp;&nbsp;
							         <input type="radio" name="dkzl" value="�ط�������Ϣ������ѧ����">&nbsp;&nbsp;�ط�������Ϣ������ѧ����
						         </logic:notPresent>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>ѧ�ѵ���(����)
						</div>
					</td>
					<td>
						<input type="text" id="xfdj" maxlength="10" name="xfdj"
							style="width:90%;heigh:100%"
							value="<bean:write name="rs" property="xfdj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							Ԫ
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>ס�޷ѵ���(����)
						</div>
					</td>
					<td>
						<input type="text" id="zsfdj" maxlength="10" name="zsfdj"
							style="width:90%;heigh:100%"
							value="<bean:write name="rs" property="zsfdj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							Ԫ
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>����ѵ���
						</div>
					</td>
					<td>
						<input type="text" id="shfdj" maxlength="10" name="shfdj"
							style="width:90%;heigh:100%"
							value="<bean:write name="rs" property="shfdj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							Ԫ
					</td>
					<td colspan="2">
						<div align="left">
							��&nbsp;&nbsp;&nbsp;&nbsp;
							<logic:present name="rs" property="shffs">
									<logic:match value="��" name="rs" property="shffs">
										<input type="radio" name="shffs" value="��" checked>&nbsp;&nbsp;��
										&nbsp;&nbsp;&nbsp;&nbsp;
							         	<input type="radio" name="shffs" value="��">&nbsp;&nbsp;��
							         </logic:match>
									<logic:match value="��" name="rs" property="shffs">
										<input type="radio" name="shffs" value="��">&nbsp;&nbsp;��
										&nbsp;&nbsp;&nbsp;&nbsp;
							         	<input type="radio" name="shffs" value="��" checked>&nbsp;&nbsp;��
							         </logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="shffs">
									<input type="radio" name="shffs" value="��" checked>&nbsp;&nbsp;��
									&nbsp;&nbsp;&nbsp;&nbsp;
							         <input type="radio" name="shffs" value="��">&nbsp;&nbsp;��
						         </logic:notPresent>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>�տ����ʻ����ͼ��ʺ�
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="skrzhlxjzh" maxlength="40" name="skrzhlxjzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="skrzhlxjzh"/>">
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
