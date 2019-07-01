<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ page import="xgxt.form.*"%>

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
<head>
	<base target="_self" />

	<title><bean:message key="lable.title" />
	</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
			response.setHeader("Prama", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var wszxjdm = document.getElementById("wszxj").value;
			var qsdh = document.getElementById('qsdh').value;
			var jcqk = document.getElementById('jcqk').value;
			var yzbm = document.getElementById('yzbm').value;
			var jtdz = document.getElementById('jtdz').value;
			var jtcy1_nl = document.getElementById('jtcy1_nl').value;
			var jtcy1_ysr = document.getElementById('jtcy1_ysr').value;
			var jtcy2_nl = document.getElementById('jtcy2_nl').value;
			var jtcy2_ysr = document.getElementById('jtcy2_ysr').value;
			var jtcy3_nl = document.getElementById('jtcy3_nl').value;
			var jtcy3_ysr = document.getElementById('jtcy3_ysr').value;
			var jtcy4_nl = document.getElementById('jtcy4_nl').value;
			var jtcy4_ysr = document.getElementById('jtcy4_ysr').value;
			var jtcy5_nl = document.getElementById('jtcy5_nl').value;
			var jtcy5_ysr = document.getElementById('jtcy5_ysr').value;
			var drshgzqk = document.getElementById('drshgzqk').value;
			var sqzzly = document.getElementById('sqzzly').value;
			
			if((xh == "") || (xh == null)){
				alert("ѧ��ѧ�Ų���Ϊ��!");
				return false;
			}
			if( wszxjdm == "" || wszxjdm == " "){
				alert("û��ѡ����Ӧ��������ѧ��");
				return false;
			}
			if((qsdh != null) && (qsdh != "") && (!isNumber(qsdh))){
				alert("���ҵ绰����Ϊ����!");
				return false;
			}
			if(jcqk != null){
	         	if(jcqk.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("����������ܴ���100���ַ�");
	          		 return false;
	       		 } 	        
			}
			if((yzbm != null) && (yzbm != "") && (!isNumber(yzbm))){
				alert("�����������Ϊ����!");
				return false;
			}
			if(jtdz != null){
	         	if(jtdz.replace(/[^\u0000-\u00ff]/g, "**").length > 50){	         
	          		 alert("��ͥ��ַ���ܴ���50���ַ�");
	          		 return false;
	       		 } 	
	       	}
	       	if((jtcy1_nl != null) && (jtcy1_nl != "") && (!isNumber(jtcy1_nl))){
				alert("��ͥ��Ա1�������Ϊ����!");
				return false;
			}
			if((jtcy1_ysr != null) && (jtcy1_ysr != "") && (!isNumber(jtcy1_ysr))){
				alert("��ͥ��Ա1���������Ϊ����!");
				return false;
			}
			if((jtcy2_nl != null) && (jtcy2_nl != "") && (!isNumber(jtcy2_nl))){
				alert("��ͥ��Ա2�������Ϊ����!");
				return false;
			}
			if((jtcy2_ysr != null) && (jtcy2_ysr != "") && (!isNumber(jtcy2_ysr))){
				alert("��ͥ��Ա2���������Ϊ����!");
				return false;
			}
			if((jtcy3_nl != null) && (jtcy3_nl != "") && (!isNumber(jtcy3_nl))){
				alert("��ͥ��Ա3�������Ϊ����!");
				return false;
			}
			if((jtcy3_ysr != null) && (jtcy3_ysr != "") && (!isNumber(jtcy3_ysr))){
				alert("��ͥ��Ա3���������Ϊ����!");
				return false;
			}
			if((jtcy4_nl != null) && (jtcy4_nl != "") && (!isNumber(jtcy4_nl))){
				alert("��ͥ��Ա4�������Ϊ����!");
				return false;
			}
			if((jtcy4_ysr != null) && (jtcy4_ysr != "") && (!isNumber(jtcy4_ysr))){
				alert("��ͥ��Ա4���������Ϊ����!");
				return false;
			}
			if((jtcy5_nl != null) && (jtcy5_nl != "") && (!isNumber(jtcy5_nl))){
				alert("��ͥ��Ա5�������Ϊ����!");
				return false;
			}
			if((jtcy5_ysr != null) && (jtcy5_ysr != "") && (!isNumber(jtcy5_ysr))){
				alert("��ͥ��Ա5���������Ϊ����!");
				return false;
			}
			if(drshgzqk != null){
	         	if(drshgzqk.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("������Ṥ��������ܴ���100���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(sqzzly != null){
	         	if(sqzzly.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("�������ɲ��ܴ���100���ַ�");
	          		 return false;
	       		 }
			}
			
			document.forms[0].action = "/xgxt/szxx_wszxjsq.do?doType=save";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(titName){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/szxx_wszxjsqb.do";
			document.forms[0].submit();
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
	</script>	
</head>

<body>
	<html:form action="szxx_wszxjsq.do" method="post">
		<input type="hidden" id="title" name="title" value="szxx_wszxjsq" />
		<input type="hidden" id="url" name="url" value="/xszz/szxx_wszxjsq.jsp" />
		<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj"/>">
		<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj"/>">
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-ssbh-qsdh-xz-xymc-bjmc" />
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã�ѧ������ - ���� - ������ѧ������
			</div>
		</div>
		<logic:present name="ok">
			<logic:equal value="true" name="ok">
				<script type="text/javascript">
				alert("����ɹ���");
			</script>
			</logic:equal>
			<logic:notEqual value="true" name="ok">
				<script type="text/javascript">
				alert("����ʧ�ܣ�");
			</script>
			</logic:notEqual>
		</logic:present>
		<logic:present name="isPASS">
			<logic:equal value="is" name="isPASS">
				<script type="text/javascript">
					alert("��ͨ����ˣ��������룡");
				</script>
			</logic:equal>
		</logic:present>
		

		<table width="100%" class="tbstyle">
			
			<tr>
				<td colspan="5" align="center">
					ѡ��������ѧ�����
				</td>
				<td colspan="3">
					<logic:present name="wszxjdm">
						<%
							XszzForm zzForm = new XszzForm();
							zzForm.setWszxjdm(request.getAttribute("wszxjdm").toString());
						%>
					</logic:present>
					
					<html:select name="rs" property="wszxjdm" style="width:100%" styleId="wszxj">
						<html:option value=" " />
						<html:options collection="wszxjList" property="wszxjdm"
							labelProperty="wszxjmc" />
					</html:select>
				</td>
			</tr>
			<tr>

				<logic:notEqual name="userOnLine" value="student" scope="session">
					<td align="center" colspan="2">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" colspan="3">
						<html:text name="rs" property="xh" styleId="xh"
							onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true"/>
						<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
					</td>
				</logic:notEqual>
				<logic:equal name="userOnLine" value="student" scope="session">
					<td align="center" colspan="2">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" colspan="3">
						<input id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />"
							readonly="readonly" />
					</td>
				</logic:equal>


				<td width="16%">
					<div align="center">
						����
					</div>
				</td>
				<td width="34%">
					<input type="text" style="width:100%" id="xm" name="xm"
						value="<bean:write name="rs" property="xm"/>" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��������
					</div>
				</td>
				<td colspan="3">
					<input type="text" readonly style="cursor:hand;width:100%"
										onclick="return showCalendar('csny','y-mm-dd');"
										value="<bean:write name='rs' property="csny" />"
										name="csny" id="csny" />
				</td>
				<td>
					<div align="center">
						�Ա�
					</div>
				</td>
				<td colspan="2">
					<input type="text" style="width:100%" id="xb" name="xb" readonly="readonly"
						value="<bean:write name="rs" property="xb"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						����
					</div>
				</td>
				<td colspan="3">
					<input id="jg" name="jg" type="text" maxlength="40" style="width:100%" value="<bean:write name="rs" property="jg"/>">
				</td>
				<td>
					<div align="center">
						����
					</div>
				</td>
				<td colspan="2">
					<input type="text" style="width:100%" id="mzmc" name="mzmc" maxlength="50"
						value="<bean:write name="rs" property="mzmc"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						ѧ��
					</div>
				</td>
				<td colspan="3">
					<input type="text" name="xz" id="xz" 
						value="<bean:write name="rs" property="xz" />" readonly="readonly">
				</td>
				<td>
					<div align="center">
						֤������
					</div>
				</td>
				<td colspan="2">
					<html:select name="rs" property="zmcldm" style="width:70%">
										<html:option value="">
										----------��ѡ��----------
										</html:option>
										<html:options collection="zmclList" property="zmcldm"
											labelProperty="zmclmc" />
									</html:select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						���Һ�
					</div>
				</td>
				<td colspan="3">
					<input type="text" name="qsh" id="qsh" style="width:100%" maxlength="8"
						value="<bean:write name="rs" property="qsh"/>">
				</td>
				<td align="center">
					���ҵ绰
				</td>
				<td>
					<input type="text" name="qsdh" id="qsdh" style="width:100%" maxlength="12"
						value="<bean:write name="rs" property="qsdh"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						�������
					</div>
				</td>
				<td colspan="6">
					<html:textarea name="rs" property="jcqk" rows='5' style="width:100%" onblur="yzdx(this,'jcqk')"/>
					<input type="hidden" id="jcqk" name="jcqk" value="">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						������ò
					</div>
				</td>
				<td colspan="3">
					<input type="text" style="width:100%" id="zzmm" name="zzmm" maxlength="20"
						value="<bean:write name="rs" property="zzmm"/>">
				</td>
				<td>
					<div align="center">
						��������
					</div>
				</td>
				<td colspan="2">
					<input name="yzbm" id="yzbm" type="text" style="width:100%" maxlength="6"
						value="<bean:write name="rs" property="yzbm"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��ͥסַ
					</div>
				</td>
				<td colspan="6">
					<html:textarea name="rs" property="jtdz" rows='3' style="width:100%" onblur="yzdx(this,'jtdz')"/>
					<input type="hidden" id="jtdz" name="jtdz" value="">
				</td>
			</tr>
			<tr>
				<td width="4%" rowspan="6">
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
				<td width="12%">
					<div align="center">
						����
					</div>
				</td>
				<td width="9%">
					<div align="center">
						����
					</div>
				</td>
				<td width="13%">
					<div align="center">
						�뱾�˹�ϵ
					</div>
				</td>
				<td width="12%">
					<div align="center">
						������
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						������ѧϰ��λ
					</div>
				</td>
			</tr>
			<tr>
				<td width="12%">
					<input type="text" style="width:100%" id="jtcy1_xm" name="jtcy1_xm" maxlength="50"
						value="<bean:write name="rs" property="jtcy1_xm" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy1_nl" name="jtcy1_nl" maxlength="3"
						value="<bean:write name="rs" property="jtcy1_nl" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy1_gx" name="jtcy1_gx" maxlength="50"
						value="<bean:write name="rs" property="jtcy1_gx" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy1_ysr"
						name="jtcy1_ysr" maxlength="6"
						value="<bean:write name="rs" property="jtcy1_ysr" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="3">
					<input type="text" style="width:100%" id="jtcy1_gzdw"
						name="jtcy1_gzdw" maxlength="100"
						value="<bean:write name="rs" property="jtcy1_gzdw" />">
				</td>
			</tr>
			<tr>
				<td width="12%">
					<input type="text" style="width:100%" id="jtcy2_xm" name="jtcy2_xm" maxlength="50"
						value="<bean:write name="rs" property="jtcy2_xm" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy2_nl" name="jtcy2_nl" maxlength="3"
						value="<bean:write name="rs" property="jtcy2_nl" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy2_gx" name="jtcy2_gx" maxlength="50"
						value="<bean:write name="rs" property="jtcy2_gx" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy2_ysr"
						name="jtcy2_ysr" maxlength="6"
						value="<bean:write name="rs" property="jtcy2_ysr" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="3">
					<input type="text" style="width:100%" id="jtcy2_gzdw"
						name="jtcy2_gzdw" maxlength="100"
						value="<bean:write name="rs" property="jtcy2_gzdw" />">
				</td>
			</tr>
			<tr>
				<td width="12%">
					<input type="text" style="width:100%" id="jtcy3_xm" name="jtcy3_xm" maxlength="50"
						value="<bean:write name="rs" property="jtcy3_xm" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy3_nl" name="jtcy3_nl" maxlength="3"
						value="<bean:write name="rs" property="jtcy3_nl" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy3_gx" name="jtcy3_gx" maxlength="50"
						value="<bean:write name="rs" property="jtcy3_gx" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy3_ysr"
						name="jtcy3_ysr" maxlength="6"
						value="<bean:write name="rs" property="jtcy3_ysr" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="3">
					<input type="text" style="width:100%" id="jtcy3_gzdw"
						name="jtcy3_gzdw" maxlength="100"
						value="<bean:write name="rs" property="jtcy3_gzdw" />">
				</td>
			</tr>
			<tr>
				<td width="12%">
					<input type="text" style="width:100%" id="jtcy4_xm" name="jtcy4_xm" maxlength="50"
						value="<bean:write name="rs" property="jtcy4_xm" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy4_nl" name="jtcy4_nl" maxlength="3"
						value="<bean:write name="rs" property="jtcy4_nl" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy4_gx" name="jtcy4_gx" maxlength="50"
						value="<bean:write name="rs" property="jtcy4_gx" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy4_ysr"
						name="jtcy4_ysr" maxlength="6"
						value="<bean:write name="rs" property="jtcy4_ysr" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="3">
					<input type="text" style="width:100%" id="jtcy4_gzdw"
						name="jtcy4_gzdw" maxlength="100"
						value="<bean:write name="rs" property="jtcy4_gzdw" />">
				</td>
			</tr>
			<tr>
				<td width="12%">
					<input type="text" style="width:100%" id="jtcy5_xm" name="jtcy5_xm" maxlength="50"
						value="<bean:write name="rs" property="jtcy5_xm" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy5_nl" name="jtcy5_nl" maxlength="3"
						value="<bean:write name="rs" property="jtcy5_nl" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy5_gx" name="jtcy5_gx" maxlength="50"
						value="<bean:write name="rs" property="jtcy5_gx" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy5_ysr"
						name="jtcy5_ysr" maxlength="6"
						value="<bean:write name="rs" property="jtcy5_ysr" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="3">
					<input type="text" style="width:100%" id="jtcy5_gzdw"
						name="jtcy5_gzdw" maxlength="100"
						value="<bean:write name="rs" property="jtcy5_gzdw" />">
				</td>
			</tr>
			<tr>
				<td height="108">
					������Ṥ�����
				</td>
				<td colspan="7">
					<html:textarea name="rs" property="drshgzqk" rows='7' style="width:100%" onblur="yzdx(this,'drshgzqk')"/>
					<input type="hidden" id="drshgzqk" name="drshgzqk" value="">
				</td>
			</tr>
			<tr>
				<td>
					<br>
					����
					<br>
					����
				</td>
				<td colspan="7">
					��������ͥ���������ѧ����Դ������Ʒ�С� ѧϰ��� ��
					<html:textarea name="rs" property="sqzzly" rows='5' style="width:100%" onblur="yzdx(this,'sqzzly')"/>
					<input type="hidden" id="sqzzly" name="sqzzly" value="">
				</td>
			</tr>
			<tr>
				<td height="108">
					<br>
					��
					<br>
					��
					<br>
					��
					<br>
					ŵ
				</td>
				<td colspan="7">
					<input type="text" style="width:100%" id="brcn"
						name="brcn" maxlength="100"
						value="����Ը����밮�����ţ��������μӸ�����ṫ������ʵ���ж��ر����" />
				</td>
			</tr>
			<logic:equal name="writeable" value="yes">
				<tr>
					<td>
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />
							<br>
							���
							<br>
							���
						</div>
					</td>
					<td colspan="7">
							<bean:write name="rs" property="xyshyj" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="left">
							ѧУ
							<br>
							���
							<br>
							���
						</div>
					</td>
					<td colspan="7">
							<bean:write name="rs" property="xxshyj" />
					</td>
				</tr>
			</logic:equal>
		</table>

		<center>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;width:100%">
				<button class="button2"
					onclick="yz();"
					style="width:80px">
					�ύ����
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2"
					onclick="toPrintOut(document.getElementById('title').value);"
					style="width:80px">
					�����ӡ
				</button>
			</div>
		</center>

	</html:form>
	<script language="javascript">
if(document.getElementById("btn") && !window.dialogArguments){
	document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
	document.getElementById("btn").style.width = "96%";
	window.setInterval("initBTNTool('btn')",1);
}
</script>
</body>
</html:html>
