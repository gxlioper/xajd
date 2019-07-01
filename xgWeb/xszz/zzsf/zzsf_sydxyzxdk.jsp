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
			var xxyy = document.getElementById('xxyy').value;
			sT = new Array("xh", "jtxxdz", "jtdh", "hjszd", "yzbm", "yddh", "gtjkr_xm", "gtjkr_gx", "gtjkr_gzdw", "gtjkr_sfzh", "gtjkr_zw", "gtjkr_jtxxdz", "gtjkr_jtdh", "gtjkr_hjszd", "gtjkr_yzbm", "gtjkr_yddh", "xxyy", "dknx", "dkzje", "fqffcsje", "jbyh", "sydxyzxdkhtbh");
			for (i = 0; i < sT.length; i++){
				var sV = document.getElementById(sT[i]).value;
				if((sV == null) || (sV == "")){
					alert("�뽫��*�ŵ���Ϣ��д����!");
					return false;
				}
			}
			if(xxyy != null){
	         	if(xxyy.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("��ϸԭ���ܴ���2000���ַ�");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zzsf_sydxyzxdk.do?doType=add";
			document.forms[0].submit();
		}
		
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/zzsf_sydxyzxdkb.do";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ��Դ��������ѧ����
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ�䣡
			</p>
		</center>
	</logic:equal>
		<html:form action="zzsf_sydxyzxdk.do" method="post">
			<input type="hidden" id="url" name="url" value="/zzsf_sydxyzxdk.do" />
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
			<input type="hidden" id="xn" name="xn"
				value="<bean:write name="rs" property="xn" scope="request" />">

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
							���֤��
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
					<td>
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<input type="text" id="xz" name="xz" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xz"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�꼶
						</div>
					</td>
					<td>
						<input type="text" id="nj" name="nj" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
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
				</tr>
				<tr>
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
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��ͥ��ϸ��ַ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtxxdz" maxlength="100" name="jtxxdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtxxdz"/>">
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
							<font color="red">*</font>�������ڵ�
						</div>
					</td>
					<td>
						<input type="text" id="hjszd" maxlength="40" name="hjszd"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hjszd"/>">
					</td>
				</tr>
				<tr>
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
					<td>
						<div align="center">
							<font color="red">*</font>�ƶ��绰
						</div>
					</td>
					<td>
						<input type="text" id="yddh" maxlength="11" name="yddh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yddh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div align="center">
							��ͬ�������Ϣ
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>����
						</div>
					</td>
					<td>
						<input type="text" id="gtjkr_xm" maxlength="50" name="gtjkr_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gtjkr_xm"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>�����˹�ϵ
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="gtjkr_gx">
							<logic:match value="��" name="rs" property="gtjkr_gx">
								<input type="radio" name="gtjkr_gx" value="��" checked>&nbsp;��
							    <input type="radio" name="gtjkr_gx" value="ĸ">&nbsp;ĸ
							    <input type="radio" name="gtjkr_gx" value="������">&nbsp;������
							    <input type="radio" name="gtjkr_gx" value="����">&nbsp;����
							</logic:match>
							<logic:match value="ĸ" name="rs" property="gtjkr_gx">
								<input type="radio" name="gtjkr_gx" value="��">&nbsp;��
							    <input type="radio" name="gtjkr_gx" value="ĸ" checked>&nbsp;ĸ
							    <input type="radio" name="gtjkr_gx" value="������">&nbsp;������
							    <input type="radio" name="gtjkr_gx" value="����">&nbsp;����
							</logic:match>
							<logic:match value="������" name="rs" property="gtjkr_gx">
								<input type="radio" name="gtjkr_gx" value="��">&nbsp;��
							    <input type="radio" name="gtjkr_gx" value="ĸ">&nbsp;ĸ
							    <input type="radio" name="gtjkr_gx" value="������" checked>&nbsp;������
							    <input type="radio" name="gtjkr_gx" value="����">&nbsp;����
							</logic:match>
							<logic:match value="����" name="rs" property="gtjkr_gx">
								<input type="radio" name="gtjkr_gx" value="��">&nbsp;��
							    <input type="radio" name="gtjkr_gx" value="ĸ">&nbsp;ĸ
							    <input type="radio" name="gtjkr_gx" value="������">&nbsp;������
							    <input type="radio" name="gtjkr_gx" value="����" checked>&nbsp;����
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="gtjkr_gx">
							<input type="radio" name="gtjkr_gx" value="��" checked>&nbsp;��
							<input type="radio" name="gtjkr_gx" value="ĸ">&nbsp;ĸ
							<input type="radio" name="gtjkr_gx" value="������">&nbsp;������
							<input type="radio" name="gtjkr_gx" value="����">&nbsp;����
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>������λ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="gtjkr_gzdw" maxlength="200" name="gtjkr_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gtjkr_gzdw"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>���֤��
						</div>
					</td>
					<td>
						<input type="text" id="gtjkr_sfzh" maxlength="18" name="gtjkr_sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gtjkr_sfzh"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>ְ��
						</div>
					</td>
					<td>
						<input type="text" id="gtjkr_zw" maxlength="20" name="gtjkr_zw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gtjkr_zw"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��ͥ��ϸ��ַ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="gtjkr_jtxxdz" maxlength="200" name="gtjkr_jtxxdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gtjkr_jtxxdz"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��ͥ�绰
						</div>
					</td>
					<td>
						<input type="text" id="gtjkr_jtdh" maxlength="18" name="gtjkr_jtdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gtjkr_jtdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>�������ڵ�
						</div>
					</td>
					<td>
						<input type="text" id="gtjkr_hjszd" maxlength="40" name="gtjkr_hjszd"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gtjkr_hjszd"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��������
						</div>
					</td>
					<td>
						<input type="text" id="gtjkr_yzbm" maxlength="6" name="gtjkr_yzbm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gtjkr_yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>�ƶ��绰
						</div>
					</td>
					<td>
						<input type="text" id="gtjkr_yddh" maxlength="11" name="gtjkr_yddh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gtjkr_yddh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div align="center">
							���������Ϣ
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��������
						</div>
					</td>
					<td colspan="3">
						������:
						<logic:present name="rs" property="knlx_dsr">
							<logic:match value="��" name="rs" property="knlx_dsr">
								<input type="radio" name="knlx_dsr" value="��" checked>��
							    <input type="radio" name="knlx_dsr" value="��">��
							</logic:match>
							<logic:match value="��" name="rs" property="knlx_dsr">
								<input type="radio" name="knlx_dsr" value="��">��
							    <input type="radio" name="knlx_dsr" value="��" checked>��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="knlx_dsr">
							<input type="radio" name="knlx_dsr" value="��">��
							<input type="radio" name="knlx_dsr" value="��" checked>��
						</logic:notPresent>
						&nbsp;
						��ũ��:
						<logic:present name="rs" property="knlx_cnh">
							<logic:match value="��" name="rs" property="knlx_cnh">
								<input type="radio" name="knlx_cnh" value="��" checked>��
							    <input type="radio" name="knlx_cnh" value="��">��
							</logic:match>
							<logic:match value="��" name="rs" property="knlx_cnh">
								<input type="radio" name="knlx_cnh" value="��">��
							    <input type="radio" name="knlx_cnh" value="��" checked>��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="knlx_cnh">
							<input type="radio" name="knlx_cnh" value="��">��
							<input type="radio" name="knlx_cnh" value="��" checked>��
						</logic:notPresent>
						&nbsp;&nbsp;
						˫�¸�:
						<logic:present name="rs" property="knlx_sxg">
							<logic:match value="��" name="rs" property="knlx_sxg">
								<input type="radio" name="knlx_sxg" value="��" checked>��
							    <input type="radio" name="knlx_sxg" value="��">��
							</logic:match>
							<logic:match value="��" name="rs" property="knlx_sxg">
								<input type="radio" name="knlx_sxg" value="��">��
							    <input type="radio" name="knlx_sxg" value="��" checked>��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="knlx_sxg">
							<input type="radio" name="knlx_sxg" value="��">��
							<input type="radio" name="knlx_sxg" value="��" checked>��
						</logic:notPresent>
						&nbsp;
						�ͱ���:
						<logic:present name="rs" property="knlx_dbh">
							<logic:match value="��" name="rs" property="knlx_dbh">
								<input type="radio" name="knlx_dbh" value="��" checked>��
							    <input type="radio" name="knlx_dbh" value="��">��
							</logic:match>
							<logic:match value="��" name="rs" property="knlx_dbh">
								<input type="radio" name="knlx_dbh" value="��">��
							    <input type="radio" name="knlx_dbh" value="��" checked>��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="knlx_dbh">
							<input type="radio" name="knlx_dbh" value="��">��
							<input type="radio" name="knlx_dbh" value="��" checked>��
						</logic:notPresent>
						�¶�:
						<logic:present name="rs" property="knlx_gr">
							<logic:match value="��" name="rs" property="knlx_gr">
								<input type="radio" name="knlx_gr" value="��" checked>��
							    <input type="radio" name="knlx_gr" value="��">��
							</logic:match>
							<logic:match value="��" name="rs" property="knlx_gr">
								<input type="radio" name="knlx_gr" value="��">��
							    <input type="radio" name="knlx_gr" value="��" checked>��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="knlx_gr">
							<input type="radio" name="knlx_gr" value="��">��
							<input type="radio" name="knlx_gr" value="��" checked>��
						</logic:notPresent>
						<br />
						�ز���:
						<logic:present name="rs" property="knlx_zbh">
							<logic:match value="��" name="rs" property="knlx_zbh">
								<input type="radio" name="knlx_zbh" value="��" checked>��
							    <input type="radio" name="knlx_zbh" value="��">��
							</logic:match>
							<logic:match value="��" name="rs" property="knlx_zbh">
								<input type="radio" name="knlx_zbh" value="��">��
							    <input type="radio" name="knlx_zbh" value="��" checked>��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="knlx_zbh">
							<input type="radio" name="knlx_zbh" value="��">��
							<input type="radio" name="knlx_zbh" value="��" checked>��
						</logic:notPresent>
						&nbsp;
						������:
						<logic:present name="rs" property="knlx_wsr">
							<logic:match value="��" name="rs" property="knlx_wsr">
								<input type="radio" name="knlx_wsr" value="��" checked>��
							    <input type="radio" name="knlx_wsr" value="��">��
							</logic:match>
							<logic:match value="��" name="rs" property="knlx_wsr">
								<input type="radio" name="knlx_wsr" value="��">��
							    <input type="radio" name="knlx_wsr" value="��" checked>��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="knlx_wsr">
							<input type="radio" name="knlx_wsr" value="��">��
							<input type="radio" name="knlx_wsr" value="��" checked>��
						</logic:notPresent>
						��ʿ��Ů:
						<logic:present name="rs" property="knlx_lszn">
							<logic:match value="��" name="rs" property="knlx_lszn">
								<input type="radio" name="knlx_lszn" value="��" checked>��
							    <input type="radio" name="knlx_lszn" value="��">��
							</logic:match>
							<logic:match value="��" name="rs" property="knlx_lszn">
								<input type="radio" name="knlx_lszn" value="��">��
							    <input type="radio" name="knlx_lszn" value="��" checked>��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="knlx_lszn">
							<input type="radio" name="knlx_lszn" value="��">��
							<input type="radio" name="knlx_lszn" value="��" checked>��
						</logic:notPresent>
						&nbsp;&nbsp;&nbsp;&nbsp;
						����:
						<logic:present name="rs" property="knlx_qt">
							<logic:match value="��" name="rs" property="knlx_qt">
								<input type="radio" name="knlx_qt" value="��" checked>��
							    <input type="radio" name="knlx_qt" value="��">��
							</logic:match>
							<logic:match value="��" name="rs" property="knlx_qt">
								<input type="radio" name="knlx_qt" value="��">��
							    <input type="radio" name="knlx_qt" value="��" checked>��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="knlx_qt">
							<input type="radio" name="knlx_qt" value="��">��
							<input type="radio" name="knlx_qt" value="��" checked>��
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��ϸԭ��
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xxyy" rows='3' style="width:100%" onblur="yzdx(this,'xxyy')"/>
						<input type="hidden" id="xxyy" name="xxyy" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��������
						</div>
					</td>
					<td>
						<input type="text" id="dknx" maxlength="2" name="dknx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dknx"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>�����ܽ��
						</div>
					</td>
					<td>
						<input type="text" id="dkzje" maxlength="6" name="dkzje"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkzje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>���ڷ��Ŵ���/���
						</div>
					</td>
					<td>
						<input type="text" id="fqffcsje" maxlength="10" name="fqffcsje"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="fqffcsje"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��������
						</div>
					</td>
					<td>
						<input type="text" id="jbyh" maxlength="40" name="jbyh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jbyh"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��Դ��������ѧ<br />�����ͬ���
						</div>
					</td>
					<td>
						<input type="text" id="sydxyzxdkhtbh" maxlength="50" name="sydxyzxdkhtbh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sydxyzxdkhtbh"/>">
					</td>
					<td>
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td>
						<input type="text" id="sqsj" readonly="readonly" name="sqsj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqsj"/>">
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
