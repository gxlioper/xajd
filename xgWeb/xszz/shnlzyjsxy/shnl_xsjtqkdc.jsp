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
		function yz(titName){
			document.forms[0].action = "/xgxt/shnl_xsjtqkdc.do?doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		
		function toPrintOut(titName){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/shnl_xsjtqkdcb.do";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ѧ������ͥ��������
		</div>
	</div>
		<html:form action="shnl_xsjtqkdc.do" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="url" name="url" value="/shnl_xsjtqkdc.do" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />

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
					<td colspan="2">
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
					<td  colspan="3">
						<input type="text" id="xb" readonly="readonly" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td>
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
					<td colspan="2">
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
					<td colspan="2">
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
					<td colspan="2">
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
					<td colspan="2">
						<div align="center">
							���֤��
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sfzh" readonly="readonly" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
					<td colspan="2">
						<div align="center">
							��ѧǰ����
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="rxqhk">
							<logic:match value="����" name="rs" property="rxqhk">
								<input type="radio" name="rxqhk" value="����" checked>&nbsp;&nbsp;����
							    <input type="radio" name="rxqhk" value="ũ��">&nbsp;&nbsp;ũ��
							</logic:match>
							<logic:match value="ũ��" name="rs" property="rxqhk">
								<input type="radio" name="rxqhk" value="����">&nbsp;&nbsp;����
							    <input type="radio" name="rxqhk" value="ũ��" checked>&nbsp;&nbsp;ũ��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="rxqhk">
							<input type="radio" name="rxqhk" value="����" checked>&nbsp;&nbsp;����
							<input type="radio" name="rxqhk" value="ũ��">&nbsp;&nbsp;ũ��
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ�˿���
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtrks" maxlength="5" name="jtrks"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrks"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<div align="center">
							��ҵѧУ
						</div>
					</td>
					<td>
						<input type="text" id="byxx" maxlength="50" name="byxx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="byxx"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�����س�
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="grtc" maxlength="50" name="grtc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="grtc"/>">
					</td>
					<td colspan="2">
						<div align="center">
							�Ƿ�²�
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="sfgc">
							<logic:match value="��" name="rs" property="sfgc">
								<input type="radio" name="sfgc" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfgc" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sfgc">
								<input type="radio" name="sfgc" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfgc" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfgc">
							<input type="radio" name="sfgc" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sfgc" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ƿ���
						</div>
					</td>
					<td align="center" colspan="3">
						<logic:present name="rs" property="sfdq">
							<logic:match value="��" name="rs" property="sfdq">
								<input type="radio" name="sfdq" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfdq" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sfdq">
								<input type="radio" name="sfdq" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfdq" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfdq">
							<input type="radio" name="sfdq" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sfdq" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
					<td colspan="2">
						<div align="center">
							�Ƿ���ʿ��Ů
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="sflszn">
							<logic:match value="��" name="rs" property="sflszn">
								<input type="radio" name="sflszn" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sflszn" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sflszn">
								<input type="radio" name="sflszn" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sflszn" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sflszn">
							<input type="radio" name="sflszn" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sflszn" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ��ϸͨѶ��ַ
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jtxxtxdz" maxlength="150" name="jtxxtxdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtxxtxdz"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtyzbm" maxlength="6" name="jtyzbm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtyzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="jtlxdh1" maxlength="4" name="jtlxdh1"
							style="width:20%;heigh:100%"
							value="<bean:write name="rs" property="jtlxdh1"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						(����)-
						<input type="text" id="jtlxdh2" maxlength="11" name="jtlxdh2"
							style="width:60%;heigh:100%"
							value="<bean:write name="rs" property="jtlxdh2"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ�˾�������
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtrjnsr" maxlength="10" name="jtrjnsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrjnsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="3">
					</td>
				</tr>
				<tr>
					<td rowspan="6" width="4%">
						<div align="center">
							��ͥ��Ա���
						</div>
					</td>
					<td width="12%" align="center">
						����
					</td>
					<td width="10%" align="center">
						����
					</td>
					<td width="12%" align="center">
						��ѧ��<br />��ϵ
					</td>
					<td width="12%" align="center">
						ְҵ
					</td>
					<td width="8%" align="center">
						������<br />(Ԫ)
					</td>
					<td width="8%" align="center">
						����״��
					</td>
					<td align="center">
						����(ѧϰ)��λ
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="text" id="jtcy1_xm" maxlength="40" name="jtcy1_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_xm"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy1_nl" maxlength="3" name="jtcy1_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td align="center">
						<input type="text" id="jtcy1_gx" maxlength="20" name="jtcy1_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_gx"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy1_zy" maxlength="20" name="jtcy1_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_zy"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy1_nsr" maxlength="8" name="jtcy1_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_nsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td align="center">
						<input type="text" id="jtcy1_jkzk" maxlength="40" name="jtcy1_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_jkzk"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy1_gzdw" maxlength="200" name="jtcy1_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_gzdw"/>">
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="text" id="jtcy2_xm" maxlength="40" name="jtcy2_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_xm"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy2_nl" maxlength="3" name="jtcy2_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td align="center">
						<input type="text" id="jtcy2_gx" maxlength="20" name="jtcy2_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_gx"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy2_zy" maxlength="20" name="jtcy2_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_zy"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy2_nsr" maxlength="8" name="jtcy2_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_nsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td align="center">
						<input type="text" id="jtcy2_jkzk" maxlength="40" name="jtcy2_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_jkzk"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy2_gzdw" maxlength="200" name="jtcy2_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_gzdw"/>">
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="text" id="jtcy3_xm" maxlength="40" name="jtcy3_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_xm"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy3_nl" maxlength="3" name="jtcy3_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td align="center">
						<input type="text" id="jtcy3_gx" maxlength="20" name="jtcy3_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_gx"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy3_zy" maxlength="20" name="jtcy3_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_zy"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy3_nsr" maxlength="8" name="jtcy3_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_nsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td align="center">
						<input type="text" id="jtcy3_jkzk" maxlength="40" name="jtcy3_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_jkzk"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy3_gzdw" maxlength="200" name="jtcy3_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_gzdw"/>">
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="text" id="jtcy4_xm" maxlength="40" name="jtcy4_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_xm"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy4_nl" maxlength="3" name="jtcy4_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td align="center">
						<input type="text" id="jtcy4_gx" maxlength="20" name="jtcy4_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_gx"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy4_zy" maxlength="20" name="jtcy4_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_zy"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy4_nsr" maxlength="8" name="jtcy4_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_nsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td align="center">
						<input type="text" id="jtcy4_jkzk" maxlength="40" name="jtcy4_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_jkzk"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy4_gzdw" maxlength="200" name="jtcy4_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_gzdw"/>">
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="text" id="jtcy5_xm" maxlength="40" name="jtcy5_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_xm"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy5_nl" maxlength="3" name="jtcy5_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td align="center">
						<input type="text" id="jtcy5_gx" maxlength="20" name="jtcy5_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_gx"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy5_zy" maxlength="20" name="jtcy5_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_zy"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy5_nsr" maxlength="8" name="jtcy5_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_nsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td align="center">
						<input type="text" id="jtcy5_jkzk" maxlength="40" name="jtcy5_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_jkzk"/>">
					</td>
					<td align="center">
						<input type="text" id="jtcy5_gzdw" maxlength="200" name="jtcy5_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_gzdw"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧ����ѧ��<br />�ѻ��������
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="xsbxnyhzzqk" maxlength="150" name="xsbxnyhzzqk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xsbxnyhzzqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ����<br />��Ȼ�ֺ����
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jtzszrzhqk" maxlength="150" name="jtzszrzhqk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzszrzhqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ����<br />ͻ�������¼�
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jtzstfsjqk" maxlength="150" name="jtzstfsjqk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzstfsjqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ��Ա��м�������<br />���Ͷ����������
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jtcyycjnmrldnlrqk" maxlength="150" name="jtcyycjnmrldnlrqk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcyycjnmrldnlrqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ��Ա<br />ʧҵ���
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jtcysyqk" maxlength="150" name="jtcysyqk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcysyqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥǷծ���
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jtqzqk" maxlength="150" name="jtqzqk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtqzqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�������
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="qtqk" maxlength="150" name="qtqk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qtqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��������<br />��ϸͨѶ��ַ
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="mzbm_xxtxdz" maxlength="150" name="mzbm_xxtxdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_xxtxdz"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��������<br />��������
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="mzbm_yzbm" maxlength="6" name="mzbm_yzbm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<div align="center">
							��������<br />��ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="mzbm_lxdh1" maxlength="4" name="mzbm_lxdh1"
							style="width:20%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_lxdh1"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						(����)-
						<input type="text" id="mzbm_lxdh2" maxlength="11" name="mzbm_lxdh2"
							style="width:60%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_lxdh2"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2"
					onClick="yz(document.getElementById('titName').value);">
					�ύ����
				</button>
				<button type="button" class="button2"
					onClick="toPrintOut(document.getElementById('titName').value);">
					��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
				</button>
			</div>

		</html:form>
</body>
</html:html>
