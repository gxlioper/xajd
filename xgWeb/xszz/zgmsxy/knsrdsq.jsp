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
			var sqly = document.getElementById('sqly').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("ѧ�������������ɲ��ܳ���2000���ַ���");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zgmsxy_xszz.do?method=knsrdsqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut1(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/zgmsxy_xszz.do?method=jtqkdcsqb";
			document.forms[0].submit();
		}
		function toPrintOut2(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/zgmsxy_xszz.do?method=knsrdsqb";
			document.forms[0].submit();
		}
		function je(){
			var jtrks = document.getElementById('jtrks').value;
			var jtrjnsr = document.getElementById('jtrjnsr').value;
			
			if((jtrks == null) || (jtrks == "")){
				jtrks = "0";
			}
			if((jtrjnsr == null) || (jtrjnsr == "")){
				jtrjnsr = "0";
			}
			var jtqnsr = Math.round(jtrks) * Math.round(jtrjnsr);
			
			document.getElementById('jtqnsr').value = jtqnsr;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã������� - ����������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
		<html:form action="zgmsxy_xszz.do?method=knsrdsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zgmsxy_xszz.do?method=knsrdsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id="mzpyjg" name="mzpyjg"
				value="<bean:write name="rs" property="mzpyjg" />">
			<input type="hidden" id="csly" name="csly"
				value="<bean:write name="rs" property="csly" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">

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
						<td align="center" colspan="3">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="readonly"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��1
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" colspan="3">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" colspan="2">
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
					<td colspan="3">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="xb" readonly="readonly" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="csny" name="csny" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csny"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="mzmc" readonly="readonly" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
					<td>
						<div align="center">
							������ò
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmmmc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							�꼶
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="nj" readonly="readonly" name="nj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />����
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							רҵ����
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<td>
						<div align="center">
							�༶����
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="bjmc" name="bjmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							���֤��
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="sfzh" readonly="readonly" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
					<td>
						<div align="center">
							��ѧǰ����
						</div>
					</td>
					<td colspan="3" align="center">
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
					<td colspan="3">
						<div align="center">
							��ҵѧУ
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="byxx" name="byxx" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="byxx"/>">
					</td>
					<td>
						<div align="center">
							�����س�
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="grtc" name="grtc" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="grtc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="qs" name="qs" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qs"/>">
					</td>
					<td>
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xslxdh" name="xslxdh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xslxdh"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							��ͥ�˿���
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jtrks" name="jtrks" maxlength="5"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="jtrks"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							��ͥ�˾�������
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtrjnsr" name="jtrjnsr" maxlength="5"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="jtrjnsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							��ͥȫ������
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jtqnsr" name="jtqnsr" maxlength="10"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name="rs" property="jtqnsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							�Ƿ�²�
						</div>
					</td>
					<td colspan="3" align="center">
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
					<td colspan="3">
						<div align="center">
							�Ƿ���
						</div>
					</td>
					<td colspan="2" align="center">
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
					<td>
						<div align="center">
							�Ƿ���ʿ��Ů
						</div>
					</td>
					<td colspan="3" align="center">
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
					<td colspan="3">
						<div align="center">
							��ͥ��������
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jt_yzbm" name="jt_yzbm" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jt_yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							��ͥ��ϵ�绰
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jt_lxdh" name="jt_lxdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jt_lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							��ͥ��ϸͨѶ��ַ
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jt_xxtxdz" name="jt_xxtxdz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jt_xxtxdz"/>">
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="7">
						<div align="center">
							��
							<br>
							ͥ
							<br>
							��
							<br>
							Ա
							<br>
							��
							<br>
							��
						</div>
					</td>
					<td width="8%">
						<div align="center">
							����
						</div>
					</td>
					<td width="8%">
						<div align="center">
							����
						</div>
					</td>
					<td width="9%">
						<div align="center">
							��ѧ����ϵ
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							����(ѧϰ)��λ
						</div>
					</td>
					<td width="12%">
						<div align="center">
							ְҵ
						</div>
					</td>
					<td width="11%">
						<div align="center">
							������(Ԫ)
						</div>
					</td>
					<td width="11%">
						<div align="center">
							����״��
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy1_xm" name="jtcy1_xm" maxlength="50"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy1_xm"/>">
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy1_nl" name="jtcy1_nl" maxlength="5"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy1_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_gx" name="jtcy1_gx" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy1_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy1_gzdw" name="jtcy1_gzdw"
								maxlength="100" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy1_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_zy" name="jtcy1_zy" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy1_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_sr" name="jtcy1_sr" maxlength="10"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy1_sr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_jkzk" name="jtcy1_jkzk"
								maxlength="50" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy1_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy2_xm" name="jtcy2_xm" maxlength="50"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy2_xm"/>">
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy2_nl" name="jtcy2_nl" maxlength="5"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy2_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_gx" name="jtcy2_gx" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy2_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy2_gzdw" name="jtcy2_gzdw"
								maxlength="100" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy2_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_zy" name="jtcy2_zy" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy2_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_sr" name="jtcy2_sr" maxlength="10"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy2_sr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_jkzk" name="jtcy2_jkzk"
								maxlength="50" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy2_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy3_xm" name="jtcy3_xm" maxlength="50"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy3_xm"/>">
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy3_nl" name="jtcy3_nl" maxlength="5"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy3_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_gx" name="jtcy3_gx" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy3_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy3_gzdw" name="jtcy3_gzdw"
								maxlength="100" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy3_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_zy" name="jtcy3_zy" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy3_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_sr" name="jtcy3_sr" maxlength="10"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy3_sr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_jkzk" name="jtcy3_jkzk"
								maxlength="50" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy3_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy4_xm" name="jtcy4_xm" maxlength="50"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy4_xm"/>">
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy4_nl" name="jtcy4_nl" maxlength="5"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy4_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_gx" name="jtcy4_gx" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy4_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy4_gzdw" name="jtcy4_gzdw"
								maxlength="100" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy4_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_zy" name="jtcy4_zy" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy4_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_sr" name="jtcy4_sr" maxlength="10"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy4_sr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_jkzk" name="jtcy4_jkzk"
								maxlength="50" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy4_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy5_xm" name="jtcy5_xm" maxlength="50"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy5_xm"/>">
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy5_nl" name="jtcy5_nl" maxlength="5"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy5_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_gx" name="jtcy5_gx" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy5_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy5_gzdw" name="jtcy5_gzdw"
								maxlength="100" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy5_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_zy" name="jtcy5_zy" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy5_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_sr" name="jtcy5_sr" maxlength="10"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy5_sr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_jkzk" name="jtcy5_jkzk"
								maxlength="50" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy5_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy6_xm" name="jtcy6_xm" maxlength="50"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy6_xm"/>">
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<input type="text" id="jtcy6_nl" name="jtcy6_nl" maxlength="5"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy6_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy6_gx" name="jtcy6_gx" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy6_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy6_gzdw" name="jtcy6_gzdw"
								maxlength="100" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy6_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy6_zy" name="jtcy6_zy" maxlength="20"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy6_zy"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy6_sr" name="jtcy6_sr" maxlength="10"
								style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy6_sr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy6_jkzk" name="jtcy6_jkzk"
								maxlength="50" style="width:100%;heigh:100%" align="middle"
								value="<bean:write name="rs" property="jtcy6_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							���صͱ���׼
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="dddbbz" name="dddbbz" maxlength="5"
							style="width:70%;heigh:100%" 
							value="<bean:write name="rs" property="dddbbz"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							Ԫ/��.��
					</td>
					<td>
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xn" name="xn" readonly="readonly"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="xn"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							ѧ����ѧ���ѻ��������
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="xybxnyhzzqk" name="xybxnyhzzqk"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xybxnyhzzqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							��ͥ������Ȼ�ֺ����
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jtzszrzhqk" name="jtzszrzhqk"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzszrzhqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							��ͥ����ͻ�������¼�
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jtzstfywsj" name="jtzstfywsj"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzstfywsj"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							��ͥ��Ա��м����������Ͷ����������
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jtcyycjssldl" name="jtcyycjssldl"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcyycjssldl"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							��ͥ��Աʧҵ���
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jtcysyqk" name="jtcysyqk"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcysyqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							��ͥǷծ���
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jtqzqk" name="jtqzqk"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtqzqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							�������
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="qtqk" name="qtqk"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qtqk"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							����������������
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="mzbm_yzbm" name="mzbm_yzbm" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							����������ϵ�绰
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="mzbm_lxdh" name="mzbm_lxdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							����������ϸͨѶ��ַ
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="mzbm_xxtxdz" name="mzbm_xxtxdz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_xxtxdz"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							ѧ��������������
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="sqly" rows='8' style='width:100%' onblur="yzdx(this,'sqly')"/>
						<input type="hidden" id="sqly" name="sqly" value="">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2" onClick="yz();">
					�ύ����
				</button>
				<button type="button" class="button2" onClick="toPrintOut1();">
					��ӡ��ͥ��������
				</button>
				<button type="button" class="button2" onClick="toPrintOut2();">
					��ӡ�������϶������
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
