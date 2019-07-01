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
			document.forms[0].action = "/xgxt/shgc_xszz_new.do?method=knslskEditSave";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ��Ϣά�� - ��������ʷ����ϸ��Ϣ
		</div>
	</div>
	<html:form action="shgc_xszz_new.do?method=knslskEdit" method="post">
		<input type="hidden" id="url" name="url"
			value="/shgc_xszz_new.do?method=knslskEdit" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc" />
		<input type="hidden" id="pkVal" name="pkVal"
			value="<bean:write name="pkVal" />">
		<input type="hidden" id="act" name="act"
			value="<bean:write name="act" />">

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
	         			alert("�Ѵ������ݣ��������ӣ�");
	         		</script>
			</logic:match>
		</logic:present>
		<table class="tbstyle" width="100%">
			<tr>
				<td align="center" colspan="2">
					<font color="red">*</font>ѧ��
				</td>
				<td align="left" colspan="3">
					<logic:equal name="act" value="add">
						<html:text name='rs' property="xh" styleId="xh"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
					</logic:equal>
					<logic:notEqual name="act" value="add">
						<bean:write name="rs" property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name="rs" property="xh" />">
					</logic:notEqual>
				</td>
				<td colspan="2">
					<div align="center">
						����
					</div>
				</td>
				<td width="34%">
					<input type="text" id="xm" name="xm" maxlength="50"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xm"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						�Ա�
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="xb" maxlength="10" name="xb"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xb"/>">
				</td>
				<td colspan="2">
					<div align="center">
						���֤��
					</div>
				</td>
				<td>
					<input type="text" id="sfzh" name="sfzh" maxlength="20"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="sfzh"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						����
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="mzmc" maxlength="50" name="mzmc"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="mzmc"/>">
				</td>
				<td colspan="2">
					<div align="center">
						������ò
					</div>
				</td>
				<td>
					<input type="text" id="zzmmmc" name="zzmmmc" maxlength="50"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zzmmmc"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
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
						��Դ��
					</div>
				</td>
				<td colspan="3">
					<html:select name="rs" property="syd">
						<html:options collection="sydList" property="syd"
							labelProperty="syd" />
					</html:select>
				</td>
				<td colspan="3">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="lxdh" maxlength="15" name="lxdh"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="lxdh"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
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
						��ͥסַ
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
						��������
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="yzbm" maxlength="6" name="yzbm"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="yzbm"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="2">
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td>
					<input type="text" id="jtlxdh" maxlength="20" name="jtlxdh"
						style="width:20%;heigh:100%"
						value="<bean:write name="rs" property="jtlxdh"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						�Ƿ�Ը��μ�
						<br />
						���ƻ�־Ը�
					</div>
				</td>
				<td colspan="3" align="center">
					<logic:present name="rs" property="sfyycjcshzyhd">
						<logic:match value="��" name="rs" property="sfyycjcshzyhd">
							<input type="radio" name="sfyycjcshzyhd" value="��" checked>&nbsp;&nbsp;��
							<input type="radio" name="sfyycjcshzyhd" value="��">&nbsp;&nbsp;��
						</logic:match>
						<logic:match value="��" name="rs" property="sfyycjcshzyhd">
							<input type="radio" name="sfyycjcshzyhd" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sfyycjcshzyhd" value="��" checked>&nbsp;&nbsp;��
						</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="sfyycjcshzyhd">
						<input type="radio" name="sfyycjcshzyhd" value="��" checked>&nbsp;&nbsp;��
						<input type="radio" name="sfyycjcshzyhd" value="��">&nbsp;&nbsp;��
					</logic:notPresent>
				</td>
				<td colspan="2">
					<div align="center">
						�Ƿ�Ը���������
						<br />
						��ѧ������ڹ���ѧ
					</div>
				</td>
				<td align="center">
					<logic:present name="rs" property="sfyysqgjzxdkhqgzx">
						<logic:match value="��" name="rs" property="sfyysqgjzxdkhqgzx">
							<input type="radio" name="sfyysqgjzxdkhqgzx" value="��" checked>&nbsp;&nbsp;��
							<input type="radio" name="sfyysqgjzxdkhqgzx" value="��">&nbsp;&nbsp;��
						</logic:match>
						<logic:match value="��" name="rs" property="sfyysqgjzxdkhqgzx">
							<input type="radio" name="sfyysqgjzxdkhqgzx" value="��">&nbsp;&nbsp;��
							<input type="radio" name="sfyysqgjzxdkhqgzx" value="��" checked>&nbsp;&nbsp;��
						</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="sfyysqgjzxdkhqgzx">
						<input type="radio" name="sfyysqgjzxdkhqgzx" value="��" checked>&nbsp;&nbsp;��
						<input type="radio" name="sfyysqgjzxdkhqgzx" value="��">&nbsp;&nbsp;��
					</logic:notPresent>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��ͥ����
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="jtlx" maxlength="100" name="jtlx"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtlx"/>">
				</td>
				<td colspan="2">
					<div align="center">
						��ͥ�˾�������(Ԫ)
					</div>
				</td>
				<td>
					<input type="text" id="jtrjnsr" maxlength="6" name="jtrjnsr"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtrjnsr"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
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
					��ѧ��
					<br />
					��ϵ
				</td>
				<td width="12%" align="center">
					ְҵ
				</td>
				<td width="8%" align="center">
					������
					<br />
					(Ԫ)
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
					<input type="text" id="jtcy1_gzdw" maxlength="200"
						name="jtcy1_gzdw" style="width:100%;heigh:100%"
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
					<input type="text" id="jtcy2_gzdw" maxlength="200"
						name="jtcy2_gzdw" style="width:100%;heigh:100%"
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
					<input type="text" id="jtcy3_gzdw" maxlength="200"
						name="jtcy3_gzdw" style="width:100%;heigh:100%"
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
					<input type="text" id="jtcy4_gzdw" maxlength="200"
						name="jtcy4_gzdw" style="width:100%;heigh:100%"
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
					<input type="text" id="jtcy5_gzdw" maxlength="200"
						name="jtcy5_gzdw" style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy5_gzdw"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						ѧ���ڱ���
						<br />
						�������
					</div>
				</td>
				<td colspan="6">
					<input type="text" id="xszbdszqk" maxlength="150" name="xszbdszqk"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xszbdszqk"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��ͥ����
						<br />
						��Ȼ�ֺ����
					</div>
				</td>
				<td colspan="6">
					<input type="text" id="jtzszrzhqk" maxlength="150"
						name="jtzszrzhqk" style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtzszrzhqk"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��ͥ����
						<br />
						ͻ�������¼�
					</div>
				</td>
				<td colspan="6">
					<input type="text" id="jtzstfywsj" maxlength="150"
						name="jtzstfywsj" style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtzstfywsj"/>">
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
						��������
						<br />
						��ϸͨѶ��ַ
					</div>
				</td>
				<td colspan="6">
					<input type="text" id="mzbm_txdz" maxlength="150" name="mzbm_txdz"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="mzbm_txdz"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						����������������
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
						����������ϵ�绰
					</div>
				</td>
				<td>
					<input type="text" id="mzbm_lxdh" maxlength="20" name="mzbm_lxdh"
						style="width:20%;heigh:100%"
						value="<bean:write name="rs" property="mzbm_lxdh"/>">
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
						value="<bean:write name="rs" property="nd"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="2">
					<div align="center">
						�϶�ʱ��
					</div>
				</td>
				<td>
					<input type="text" readonly style="cursor:hand;width:100%"
						onclick="return showCalendar('rdsj','y-mm-dd');"
						value="<bean:write name='rs' property="rdsj" />" name="rdsj"
						id="rdsj" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						�����϶����
					</div>
				</td>
				<td colspan="3">
					<html:select property="knrdjg">
						<html:option value=""></html:option>
						<html:option value="δ���">δ���</html:option>
						<html:option value="һ������">һ������</html:option>
						<html:option value="�ر�����">�ر�����</html:option>
						<html:option value="����">����</html:option>
						<html:option value="��ͨ��">��ͨ��</html:option>
					</html:select>
				</td>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<button type="button" class="button2" onClick="yz();">
				��&nbsp;&nbsp;&nbsp;&nbsp;��
			</button>
			<button type="button" class="button2"
				onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
				��&nbsp;&nbsp;&nbsp;&nbsp;��
			</button>
		</div>
	</html:form>
</body>
</html:html>
