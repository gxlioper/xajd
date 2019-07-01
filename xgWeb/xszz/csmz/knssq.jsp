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
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var xzjtxxdz = document.getElementById('xzjtxxdz').value;
			var jtjjknqkjyy = document.getElementById('jtjjknqkjyy').value;
			var sqly = document.getElementById('sqly').value;
			if(xh == null){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(xzjtxxdz != null){
	         	if(xzjtxxdz.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("��ס��ͥ��ϸ��ַ���ܴ���100���ַ���");
	          		 return false;
	       		 }
	       	}
	       	if(jtjjknqkjyy != null){
	         	if(jtjjknqkjyy.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("��ͥ�������������ԭ���ܴ���1000���ַ���");
	          		 return false;
	       		 }
	       	}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("�������ɲ��ܴ���1000���ַ���");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/csmz_xszz.do?method=knssq&doType=save";
			document.forms[0].submit();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function toPrintOut1(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/csmz_xszz.do?method=jtjjqkdcb";
			document.forms[0].submit();
		}
		
		function toPrintOut2(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/csmz_xszz.do?method=pksdjb";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ƶ��������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڻ���������������
			</p>
		</center>
	</logic:equal>
		<html:form action="csmz_xszz.do?method=knssq" method="post">
			<input type="hidden" id="url" name="url"
				value="/csmz_xszz.do?method=knssq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd" />">
			<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" />">
			<input type="hidden" id="xyshsj" name="xyshsj"
				value="<bean:write name="rs" property="xyshsj" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" />">
			<input type="hidden" id="xxshsj" name="xxshsj"
				value="<bean:write name="rs" property="xxshsj" />">
			<input type="hidden" id="fdysh" name="fdysh"
				value="<bean:write name="rs" property="fdysh" />">
			<input type="hidden" id="fdyshsj" name="fdyshsj"
				value="<bean:write name="rs" property="fdyshsj" />">


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
						<td align="left" width="34%">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" width="34%">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td colspan="2" scope="col">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2" scope="col">
						<input type="text" id="xm" name="xm" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xm" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						<input type="text" id="xb" name="xb" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xb" />" readonly="true">
					</td>
					<td colspan="2">
						<div align="center">
							���֤��
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="sfzh" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="sfzh" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xymc" />" readonly="true">
					</td>
					<td colspan="2">
						<div align="center">
							רҵ
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zymc" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zymc" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							�༶
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="bjmc" />" readonly="true">
					</td>
					<td colspan="2">
						<div align="center">
							�꼶
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="nj" name="nj" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="nj" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							����
						</div>
					</td>
					<td>
						<input type="text" id="mzmc" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="mzmc" />" readonly="true">
					</td>
					<td colspan="2">
						<div align="center">
							������ò
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zzmmmc" name="zzmmmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zzmmmc" />"
							readonly="true">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ѧ����
						</div>
					</td>
					<td>
						<input type="text" id="rxny" name="rxny"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="rxny" />" readonly="true">
					</td>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="csrq" name="csrq"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="csrq" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td colspan="7" scope="row">
						<div align="center">
							ѧ����ͥ������������
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ס��ͥ��ϸ��ַ
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="xzjtxxdz" rows='2'
							style="width:100%" onblur="yzdx(this,'xzjtxxdz')" />
						<input type="hidden" id="xzjtxxdz" name="xzjtxxdz" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<input type="text" id="yzbm" name="yzbm"
							style="width:100%;heigh:100%" maxlength="6"
							value="<bean:write name='rs' property="yzbm" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<div align="center">
							��ͥ�绰
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jtdh" name="jtdh"
							style="width:100%;heigh:100%" maxlength="15"
							value="<bean:write name='rs' property="jtdh" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							ԭѧϰѧУ
						</div>
					</td>
					<td>
						<input type="text" id="yxxxx" name="yxxxx"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="yxxxx" />">
					</td>
					<td colspan="2">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jg" name="jg" style="width:100%;heigh:100%"
							maxlength="50" value="<bean:write name='rs' property="jg" />">
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6" scope="row">
						<div align="center">
							ֱ
							<br />
							ϵ
							<br />
							��
							<br />
							ͥ
							<br />
							��
							<br />
							Ա
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td>
						<div align="center">
							���ںδ�������ְ��
						</div>
					</td>
					<td width="8%">
						<div align="center">
							����
						</div>
					</td>
					<td width="8%">
						<div align="center">
							��ϵ
						</div>
					</td>
					<td width="14%">
						<div align="center">
							ÿ�¹���
							<br />
							����(Ԫ)
						</div>
					</td>
					<td width="20%">
						<div align="center">
							��ϵ�绰
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy1_xm" maxlength="40"
								name="zsjtcy1_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy1_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy1_gzdwjzw" maxlength="100"
								name="zsjtcy1_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy1_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy1_nl" maxlength="5"
								name="zsjtcy1_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy1_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy1_gx" maxlength="40"
								name="zsjtcy1_gx" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy1_gx"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy1_ysr" maxlength="10"
								name="zsjtcy1_ysr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy1_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy1_lxdh" maxlength="15"
								name="zsjtcy1_lxdh" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy1_lxdh"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy2_xm" maxlength="40"
								name="zsjtcy2_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy2_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy2_gzdwjzw" maxlength="100"
								name="zsjtcy2_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy2_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy2_nl" maxlength="5"
								name="zsjtcy2_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy2_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy2_gx" maxlength="40"
								name="zsjtcy2_gx" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy2_gx"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy2_ysr" maxlength="10"
								name="zsjtcy2_ysr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy2_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy2_lxdh" maxlength="15"
								name="zsjtcy2_lxdh" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy2_lxdh"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy3_xm" maxlength="40"
								name="zsjtcy3_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy3_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy3_gzdwjzw" maxlength="100"
								name="zsjtcy3_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy3_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy3_nl" maxlength="5"
								name="zsjtcy3_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy3_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy3_gx" maxlength="40"
								name="zsjtcy3_gx" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy3_gx"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy3_ysr" maxlength="10"
								name="zsjtcy3_ysr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy3_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy3_lxdh" maxlength="15"
								name="zsjtcy3_lxdh" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy3_lxdh"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy4_xm" maxlength="40"
								name="zsjtcy4_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy4_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy4_gzdwjzw" maxlength="100"
								name="zsjtcy4_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy4_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy4_nl" maxlength="5"
								name="zsjtcy4_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy4_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy4_gx" maxlength="40"
								name="zsjtcy4_gx" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy4_gx"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy4_ysr" maxlength="10"
								name="zsjtcy4_ysr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy4_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy4_lxdh" maxlength="15"
								name="zsjtcy4_lxdh" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy4_lxdh"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy5_xm" maxlength="40"
								name="zsjtcy5_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy5_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy5_gzdwjzw" maxlength="100"
								name="zsjtcy5_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy5_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy5_nl" maxlength="5"
								name="zsjtcy5_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy5_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy5_gx" maxlength="40"
								name="zsjtcy5_gx" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy5_gx"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy5_ysr" maxlength="10"
								name="zsjtcy5_ysr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy5_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy5_lxdh" maxlength="15"
								name="zsjtcy5_lxdh" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy5_lxdh"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td rowspan="2" scope="row">
						<div align="center">
							��ͥ
							<br />
							����
							<br />
							����
						</div>
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td colspan="5">
						<div align="left">
							ȫ��������
							<input type="text" id="jtjj_cz_qjnsr" maxlength="10"
								name="jtjj_cz_qjnsr" style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="jtjj_cz_qjnsr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							Ԫ���˾�������
							<input type="text" id="jtjj_cz_rjysr" maxlength="10"
								name="jtjj_cz_rjysr" style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="jtjj_cz_rjysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							Ԫ
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ũ��
						</div>
					</td>
					<td colspan="5">
						<div align="left">
							�����������ܼ�
							<input type="text" id="jtjj_nc_dnzsr" maxlength="10"
								name="jtjj_nc_dnzsr" style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="jtjj_nc_dnzsr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							Ԫ���˾�������
							<input type="text" id="jtjj_nc_rjnsr" maxlength="10"
								name="jtjj_nc_rjnsr" style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="jtjj_nc_rjnsr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							Ԫ
							<br />
							<font color="red">��ע����ũ��Ա�����ũ�������������ת��Ϊ�������룬���벻��Ϊ�㡣��</font>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ͥ�˿���
						</div>
					</td>
					<td>
						<input type="text" id="jtrks" name="jtrks"
							style="width:100%;heigh:100%" maxlength="3"
							value="<bean:write name='rs' property="jtrks" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<div align="center">
							���������������
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="ddzdshshbz" name="ddzdshshbz"
							style="width:80%;heigh:100%" maxlength="10"
							value="<bean:write name='rs' property="ddzdshshbz" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						(Ԫ/��)
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="5" scope="row">
						<div align="center">
							��
							<br />
							Ҫ
							<br />
							��
							<br />
							��
							<br />
							��
							<br />
							ϵ
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td>
						<div align="center">
							���ںδ�������ְ��
						</div>
					</td>
					<td width="8%">
						<div align="center">
							����
						</div>
					</td>
					<td width="8%">
						<div align="center">
							��ϵ
						</div>
					</td>
					<td width="14%">
						<div align="center">
							ÿ�¹���
							<br />
							����(Ԫ)
						</div>
					</td>
					<td width="20%">
						<div align="center">
							����Ҿ�����ϵ
							<br />
							�������
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="zyshgx1_xm" maxlength="40"
								name="zyshgx1_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx1_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx1_gzdwjzw" maxlength="100"
								name="zyshgx1_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx1_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx1_nl" maxlength="5"
								name="zyshgx1_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx1_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx1_gx" maxlength="40"
								name="zyshgx1_gx" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx1_gx"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx1_ysr" maxlength="10"
								name="zyshgx1_ysr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx1_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx1_ynjtjjlxhgyqk" maxlength="50"
								name="zyshgx1_ynjtjjlxhgyqk" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx1_ynjtjjlxhgyqk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="zyshgx2_xm" maxlength="40"
								name="zyshgx2_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx2_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx2_gzdwjzw" maxlength="100"
								name="zyshgx2_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx2_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx2_nl" maxlength="5"
								name="zyshgx2_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx2_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx2_gx" maxlength="40"
								name="zyshgx2_gx" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx2_gx"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx2_ysr" maxlength="10"
								name="zyshgx2_ysr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx2_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx2_ynjtjjlxhgyqk" maxlength="50"
								name="zyshgx2_ynjtjjlxhgyqk" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx2_ynjtjjlxhgyqk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="zyshgx3_xm" maxlength="40"
								name="zyshgx3_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx3_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx3_gzdwjzw" maxlength="100"
								name="zyshgx3_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx3_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx3_nl" maxlength="5"
								name="zyshgx3_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx3_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx3_gx" maxlength="40"
								name="zyshgx3_gx" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx3_gx"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx3_ysr" maxlength="10"
								name="zyshgx3_ysr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx3_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx3_ynjtjjlxhgyqk" maxlength="50"
								name="zyshgx3_ynjtjjlxhgyqk" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx3_ynjtjjlxhgyqk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="zyshgx4_xm" maxlength="40"
								name="zyshgx4_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx4_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx4_gzdwjzw" maxlength="100"
								name="zyshgx4_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx4_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx4_nl" maxlength="5"
								name="zyshgx4_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx4_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx4_gx" maxlength="40"
								name="zyshgx4_gx" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx4_gx"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx4_ysr" maxlength="10"
								name="zyshgx4_ysr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx4_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx4_ynjtjjlxhgyqk" maxlength="50"
								name="zyshgx4_ynjtjjlxhgyqk" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx4_ynjtjjlxhgyqk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ͥ�������������ԭ��
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="jtjjknqkjyy" rows='10'
							style="width:100%" onblur="yzdx(this,'jtjjknqkjyy')" />
						<input type="hidden" id="jtjjknqkjyy" name="jtjjknqkjyy" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							���׵�λ
						</div>
					</td>
					<td>
						<input type="text" id="fqdw" name="fqdw"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="fqdw" />">
					</td>
					<td colspan="2">
						<div align="center">
							ĸ�׵�λ
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="mqdw" name="mqdw"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="mqdw" />">
					</td>
				</tr>
				<tr>
					<td colspan="7" scope="row">
						<div align="center">
							ƶ��ѧ���ǼǱ�
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ס����
						</div>
					</td>
					<td>
						<input type="text" id="szqs" name="szqs"
							style="width:100%;heigh:100%" maxlength="20"
							value="<bean:write name='rs' property="szqs" />">
					</td>
					<td colspan="2">
						<div align="center">
							���ҵ绰
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="qsdh" name="qsdh"
							style="width:100%;heigh:100%" maxlength="15"
							value="<bean:write name='rs' property="qsdh" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							�س�
						</div>
					</td>
					<td>
						<input type="text" id="tc" name="tc" style="width:100%;heigh:100%"
							maxlength="100" value="<bean:write name='rs' property="tc" />">
					</td>
					<td colspan="2">
						<div align="center">
							�ͲͿ�����
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jckkh" name="jckkh"
							style="width:100%;heigh:100%" maxlength="20"
							value="<bean:write name='rs' property="jckkh" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ʱ�μӹ��ڹ���ѧ
						</div>
					</td>
					<td colspan="5">
						<input type="text" id="hscjgqgzx" name="hscjgqgzx"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="hscjgqgzx" />">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							����ѧ���ɲ����
						</div>
					</td>
					<td colspan="5">
						<input type="text" id="drxsgbqk" name="drxsgbqk"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="drxsgbqk" />">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��У�ڼ�����ʱ������ֽ�ѧ��
						</div>
					</td>
					<td colspan="5">
						<input type="text" id="zxqjhschghzjxj" name="zxqjhschghzjxj"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="zxqjhschghzjxj" />">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ͥ���
						</div>
					</td>
					<td align="right">
						�Ƿ�ƶ����
					</td>
					<td colspan="4">
						<div align="center">
							<logic:present name="rs" property="pkx">
								<logic:match value="��" name="rs" property="pkx">
									<input type="radio" name="pkx" value="��" checked>&nbsp;&nbsp;��
							    	<input type="radio" name="pkx" value="���Ҽ�ƶ����">&nbsp;&nbsp;���Ҽ�ƶ����
							    	<input type="radio" name="pkx" value="ʡ��ƶ����">&nbsp;&nbsp;ʡ��ƶ����
								</logic:match>
								<logic:match value="���Ҽ�ƶ����" name="rs" property="pkx">
									<input type="radio" name="pkx" value="��">&nbsp;&nbsp;��
							    	<input type="radio" name="pkx" value="���Ҽ�ƶ����" checked>&nbsp;&nbsp;���Ҽ�ƶ����
							    	<input type="radio" name="pkx" value="ʡ��ƶ����">&nbsp;&nbsp;ʡ��ƶ����
								</logic:match>
								<logic:match value="ʡ��ƶ����" name="rs" property="pkx">
									<input type="radio" name="pkx" value="��">&nbsp;&nbsp;��
							    	<input type="radio" name="pkx" value="���Ҽ�ƶ����">&nbsp;&nbsp;���Ҽ�ƶ����
							    	<input type="radio" name="pkx" value="ʡ��ƶ����" checked>&nbsp;&nbsp;ʡ��ƶ����
								</logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="pkx">
								<input type="radio" name="pkx" value="��" checked>&nbsp;&nbsp;��
								<input type="radio" name="pkx" value="���Ҽ�ƶ����">&nbsp;&nbsp;���Ҽ�ƶ����
								<input type="radio" name="pkx" value="ʡ��ƶ����">&nbsp;&nbsp;ʡ��ƶ����
							</logic:notPresent>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							����ְҵ
						</div>
					</td>
					<td>
						<input type="text" id="fqzy" name="fqzy"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="fqzy" />">
					</td>
					<td colspan="2">
						<div align="center">
							ĸ��ְҵ
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="mqzy" name="mqzy"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="mqzy" />">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							�����ͥ����
						</div>
					</td>
					<td>
						<input type="text" id="snjtsr" name="snjtsr"
							style="width:100%;heigh:100%" maxlength="5"
							value="<bean:write name='rs' property="snjtsr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<div align="center">
							��ͥ������Դ
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jtjjly" name="jtjjly"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtjjly" />">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ͥÿ���ṩ�����
						</div>
					</td>
					<td>
						<input type="text" id="jtmytgshf" name="jtmytgshf"
							style="width:100%;heigh:100%" maxlength="5"
							value="<bean:write name='rs' property="jtmytgshf" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<div align="center">
							�����Ƿ���Ƿծ
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<logic:present name="rs" property="jzsfyqz">
								<logic:match value="��" name="rs" property="jzsfyqz">
									<input type="radio" name="jzsfyqz" value="��" checked>&nbsp;&nbsp;��
							    	<input type="radio" name="jzsfyqz" value="��">&nbsp;&nbsp;��
								</logic:match>
								<logic:match value="��" name="rs" property="jzsfyqz">
									<input type="radio" name="jzsfyqz" value="��">&nbsp;&nbsp;��
							    	<input type="radio" name="jzsfyqz" value="��" checked>&nbsp;&nbsp;��
								</logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="jzsfyqz">
								<input type="radio" name="jzsfyqz" value="��" checked>&nbsp;&nbsp;��
								<input type="radio" name="jzsfyqz" value="��">&nbsp;&nbsp;��
							</logic:notPresent>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ĸ�Ƿ��в���м�
						</div>
					</td>
					<td>
						<input type="text" id="fmsfycj" name="fmsfycj"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="fmsfycj" />">
					</td>
					<td colspan="2">
						<div align="center">
							��ĸ�Ƿ���
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="fmsfjz" name="fmsfjz"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="fmsfjz" />">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="sqly" rows='6'
							style="width:100%" onblur="yzdx(this,'sqly')" />
						<input type="hidden" id="sqly" name="sqly" value="">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2" onClick="yz();">
					�ύ����
				</button>
				<button class="button2" onClick="toPrintOut1();">
					��ӡ��ͥ������������
				</button>
				<button class="button2" onClick="toPrintOut2();">
					��ӡƶ��ѧ���ǼǱ�
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
