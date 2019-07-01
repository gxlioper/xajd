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
			var jtknqk = document.getElementById('jtknqk').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(jtknqk != null){
	         	if(jtknqk.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("��ͥ����������ܴ���2000���ַ�");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/jhzyjsxy_xszz.do?method=knsdjbSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/jhzyjsxy_xszz.do?method=knsdjbdy";
			document.forms[0].submit();
		}
		function changeXfqk(){
			var hsf = document.getElementById('hsf').value;
			var qtfy = document.getElementById('qtfy').value;
			
			if (hsf == null || hsf == "") {
				hsf = "0";
			}
			if (qtfy == null || qtfy == "") {
				qtfy = "0";
			}
			var xzxfqk = toInt(hsf) + toInt(qtfy);
			document.getElementById('xzxfqk').value = xzxfqk;
		}
		function changeJtlx(){
			var jtlxT = document.getElementById('jtlxT').value;
			var jtlx = document.getElementById('jtlx').value;
			var jtlxList = jtlx.split(';');
			var b = true;
			
			for (var i = 0; i < jtlxList.length; i++) {
				if (jtlxList[i] == jtlxT) {
					b = false;
				}
			}
			if (b) {
				jtlx += jtlxT+";";
			}
			document.getElementById('jtlx').value = jtlx;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ��ͥ��������ѧ���ǼǱ�
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<p align="center">
			<font color="red">Ŀǰ��������ʱ�䷶Χ�ڣ��ݲ��������룡</font>
		</p>
	</logic:equal>
		<html:form action="jhzyjsxy_xszz.do?method=knsdjb" method="post">
			<input type="hidden" id="url" name="url" value="/jhzyjsxy_xszz.do?method=knsdjb" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj" />">

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
			<table class="tbstyle" width="90%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" colspan="3">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<logic:notEqual name="type" value="modi">
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do?kns=yes',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</logic:notEqual>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>ѧ�ţ�
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
					<td colspan="2">
						<div align="center">
							�꼶
						</div>
					</td>
					<td colspan="3">
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
					<td colspan="2">
						<div align="center">
							רҵ����
						</div>
					</td>
					<td colspan="3">
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
					<td colspan="2">
						<div align="center">
							��ѧʱ��
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="rxrq" readonly="readonly" name="rxrq"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxrq"/>">
					</td>
					<td>
						<div align="center">
							��ҵʱ��
						</div>
					</td>
					<td>
						<input type="text" id="byrq" name="byrq" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="byrq"/>">
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
							��дʱ��
						</div>
					</td>
					<td>
						<input type="text" id="sqsj" name="sqsj" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqsj"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							���п���<br />(ѧУ��)
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="yhkh" maxlength="30" name="yhkh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yhkh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							һ��ͨ��
						</div>
					</td>
					<td>
						<input type="text" id="ykthm" name="ykthm" maxlength="30"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="ykthm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥסַ���ʱ�
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtzzjyb" maxlength="100" name="jtzzjyb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzzjyb"/>">
					</td>
					<td>
						<div align="center">
							���Һ�
						</div>
					</td>
					<td>
						<input type="text" id="qsh" name="qsh" maxlength="30"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qsh"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧϰ�������
						</div>
					</td>
					<td colspan="3">
						<html:select name="rs" property="xxztqk">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
							<html:option value="һ��">һ��</html:option>
							<html:option value="�ϲ�">�ϲ�</html:option>
						</html:select>
					</td>
					<td>
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="lxdh" name="lxdh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����ֽ���
						</div>
					</td>
					<td colspan="5">
						<input type="text" id="hhzjl" name="hhzjl" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hhzjl"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							���ܺ��ִ���
						</div>
					</td>
					<td colspan="5">
						<input type="text" id="cshzcf" name="cshzcf" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="cshzcf"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							������ѧ����<br />�����
						</div>
					</td>
					<td colspan="5">
						<input type="text" id="ywzxdkjje" name="ywzxdkjje" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="ywzxdkjje"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							���������(��λ)<br />���������
						</div>
					</td>
					<td colspan="5">
						<input type="text" id="ywsshzzjhe" name="ywsshzzjhe" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="ywsshzzjhe"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧ�ѽ������
						</div>
					</td>
					<td colspan="5">
						<input type="text" id="xfjnqk" name="xfjnqk" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xfjnqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="7">
						<div align="center">
							�����������(Ԫ/��)
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ʳ��
						</div>
					</td>
					<td width="12%">
						<input type="text" id="hsf" maxlength="5" name="hsf"
							style="width:100%;heigh:100%" onblur="changeXfqk()"
							value="<bean:write name="rs" property="hsf"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td width="10%">
						<div align="center">
							��������
						</div>
					</td>
					<td width="12%">
						<input type="text" id="qtfy" maxlength="5" name="qtfy"
							style="width:100%;heigh:100%" onblur="changeXfqk()"
							value="<bean:write name="rs" property="qtfy"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							�ܶ�
						</div>
					</td>
					<td>
						<input type="text" id="xzxfqk" name="xzxfqk" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xzxfqk"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							���������Դ<br />(Ԫ/��)
						</div>
					</td>
					<td>
						<div align="center">
							��ͥ����
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="shf_jtgg" maxlength="5" name="shf_jtgg"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shf_jtgg"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							������Դ
						</div>
					</td>
					<td>
						<input type="text" id="shf_qtly" name="shf_qtly" maxlength="5"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="shf_qtly"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6">
						<div align="center">
							��<br />ͥ<br />��<br />Ա<br />��<br />��<br />��<br />��
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td>
						<div align="center">
							��ν
						</div>
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<div align="center">
							������(Ԫ)
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							��λ
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jicy1_xm" maxlength="25" name="jicy1_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy1_xm"/>">
					</td>
					<td>
						<input type="text" id="jicy1_cw" maxlength="10" name="jicy1_cw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy1_cw"/>">
					</td>
					<td>
						<input type="text" id="jicy1_nl" maxlength="3" name="jicy1_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy1_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jicy1_sr" maxlength="5" name="jicy1_sr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy1_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jicy1_dw" maxlength="100" name="jicy1_dw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy1_dw"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jicy2_xm" maxlength="25" name="jicy2_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy2_xm"/>">
					</td>
					<td>
						<input type="text" id="jicy2_cw" maxlength="10" name="jicy2_cw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy2_cw"/>">
					</td>
					<td>
						<input type="text" id="jicy2_nl" maxlength="3" name="jicy2_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy2_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jicy2_sr" maxlength="5" name="jicy2_sr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy2_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jicy2_dw" maxlength="100" name="jicy2_dw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy2_dw"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jicy3_xm" maxlength="25" name="jicy3_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy3_xm"/>">
					</td>
					<td>
						<input type="text" id="jicy3_cw" maxlength="10" name="jicy3_cw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy3_cw"/>">
					</td>
					<td>
						<input type="text" id="jicy3_nl" maxlength="3" name="jicy3_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy3_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jicy3_sr" maxlength="5" name="jicy3_sr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy3_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jicy3_dw" maxlength="100" name="jicy3_dw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy3_dw"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jicy4_xm" maxlength="25" name="jicy4_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy4_xm"/>">
					</td>
					<td>
						<input type="text" id="jicy4_cw" maxlength="10" name="jicy4_cw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy4_cw"/>">
					</td>
					<td>
						<input type="text" id="jicy4_nl" maxlength="3" name="jicy4_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy4_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jicy4_sr" maxlength="5" name="jicy4_sr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy4_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jicy4_dw" maxlength="100" name="jicy4_dw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy4_dw"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jicy5_xm" maxlength="25" name="jicy5_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy5_xm"/>">
					</td>
					<td>
						<input type="text" id="jicy5_cw" maxlength="10" name="jicy5_cw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy5_cw"/>">
					</td>
					<td>
						<input type="text" id="jicy5_nl" maxlength="3" name="jicy5_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy5_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jicy5_sr" maxlength="5" name="jicy5_sr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy5_sr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jicy5_dw" maxlength="100" name="jicy5_dw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jicy5_dw"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ�˿�����
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtzrks" maxlength="3" name="jtzrks"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzrks"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							�˾�������<br />
							(Ԫ/��.��)
						</div>
					</td>
					<td>
						<input type="text" id="rjnsr" name="rjnsr" maxlength="8"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rjnsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ����
						</div>
					</td>
					<td>
						<html:select onchange="changeJtlx()" name="rs" property="jtlxT">
							<html:option value=""></html:option>
							<html:option value="˫��">˫��</html:option>
							<html:option value="����">����</html:option>
							<html:option value="����">����</html:option>
							<html:option value="����">����</html:option>
							<html:option value="�¶�">�¶�</html:option>
						</html:select>
					</td>
					<td colspan="4">
						<input type="text" id="jtlx" name="jtlx" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtlx"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ�������
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="jtknqk" rows='10' style="width:100%" onblur="yzdx(this,'jtknqk')"/>
						<input type="hidden" id="jtknqk" name="jtknqk" value="">
					</td>
				</tr>
			</table>
				<div class="buttontool" id="btn" style="position: absolute;width:90%">
					<button type="button" class="button2" id="buttonSave"
						onClick="yz();">
						�ύ����
					</button>
					<button type="button" class="button2"
						onClick="toPrintOut();">
						��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
					</button>
				</div>
		</html:form>
	</body>
	<logic:equal name="sfksq" value="-1">
		<script language="javascript">
		  $("buttonSave").disabled=true;
		</script>
	</logic:equal>
	<logic:notEqual name="sfksq" value="-1">
		<logic:equal name="isKns" value="false">
			<script language="javascript">
			  $("buttonSave").disabled=true;
			  alert("����Ŀǰ���������������ܽ�������!");
			</script>
		</logic:equal>
	</logic:notEqual>
</html:html>
