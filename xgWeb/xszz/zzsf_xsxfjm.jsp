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
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var bz = document.getElementById('bz').value;
			var jzxm = document.getElementById('jzxm').value;
			var jtdh = document.getElementById('jtdh').value;
			var jtdz = document.getElementById('jtdz').value;
			var yzbm = document.getElementById('yzbm').value;
			var ssdh = document.getElementById('ssdh').value;
			var ssdz = document.getElementById('ssdz').value;
			var jtjjqk = document.getElementById('jtjjqk').value;
			var yxknbzqk = document.getElementById('yxknbzqk').value;
			var qggw = document.getElementById('qggw').value;
			var xxcj = document.getElementById('xxcj').value;
			var sqje = document.getElementById('sqje').value;
			var psbxqk = document.getElementById('psbxqk').value;
			var gehjqk = document.getElementById('gehjqk').value;

			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((jzxm == null) || (jzxm == "")){
				alert("�ҳ���������Ϊ��!");
				return false;
			}
			if((jtdh == null) || (jtdh == "")){
				alert("��ͥ�绰����Ϊ��!");
				return false;
			}
			if((jtdz == null) || (jtdz == "")){
				alert("��ͥ��ַ����Ϊ��!");
				return false;
			}
			if((yzbm == null) || (yzbm == "")){
				alert("�������벻��Ϊ��!");
				return false;
			}
			if((ssdh == null) || (ssdh == "")){
				alert("����绰����Ϊ��!");
				return false;
			}
			if((ssdz == null) || (ssdz == "")){
				alert("�����ַ����Ϊ��!");
				return false;
			}
			if((jtjjqk == null) || (jtjjqk == "")){
				alert("��ͥ�����������Ϊ��!");
				return false;
			}
			if((yxknbzqk == null) || (yxknbzqk == "")){
				alert("Ժ��ϵ���Ѳ����������Ϊ��!");
				return false;
			}
			if((qggw == null) || (qggw == "")){
				alert("�ڹ���λ����Ϊ��!");
				return false;
			}
			if((xxcj == null) || (xxcj == "")){
				alert("ѧϰ�ɼ�����Ϊ��!");
				return false;
			}
			if((sqje == null) || (sqje == "")){
				alert("�������Ϊ��!");
				return false;
			}
			if((psbxqk == null) || (psbxqk == "")){
				alert("ƽʱ�����������Ϊ��!");
				return false;
			}
			if((gehjqk == null) || (gehjqk == "")){
				alert("���˻��������Ϊ��!");
				return false;
			}
			if((jtdh != null) && (jtdh != "") && (!isNumber(jtdh))){
				alert("��ͥ�绰����Ϊ����!");
				return false;
			}
			if((yzbm != null) && (yzbm != "") && (!isNumber(yzbm))){
				alert("�����������Ϊ����!");
				return false;
			}
			if((ssdh != null) && (ssdh != "") && (!isNumber(ssdh))){
				alert("����绰����Ϊ����!");
				return false;
			}
			if(jtjjqk != null){
	         	if(jtjjqk.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("��ͥ����������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(yxknbzqk != null){
	         	if(yxknbzqk.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���Ѳ���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if((sqje != null) && (sqje != "") && (!isNumber(sqje))){
				alert("���������Ϊ����!");
				return false;
	       	}
	       	if(psbxqk != null){
	         	if(psbxqk.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("ƽʱ����������ܴ���200���ַ�");
	          		 return false;
	       		 }
			}
			if(gehjqk != null){
	         	if(gehjqk.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("���˻�������ܴ���200���ַ�");
	          		 return false;
	       		 }
			}
			if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("��ע���ܴ���100���ַ�");
	          		 return false;
	       		 }
			}
			
			document.forms[0].action = "/xgxt/zzsf_xsxfjm.do?doType=add";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/zzsf_xsxfjmsqb.do";
			document.forms[0].submit();
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ѧ��ѧ�Ѽ�������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
		<html:form action="zzsf_xsxfjm.do" method="post">
			<input type="hidden" id="url" name="url"
				value="/zzsf_xsxfjm.do?jxjlbType=zzsf_gjzxdk" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj"/>">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj"/>">
			<input type="hidden" id="tyjmjedx" name="tyjmjedx"
				value="<bean:write name="rs" property="tyjmjedx"/>">
			<input type="hidden" id="tyjmje" name="tyjmje"
				value="<bean:write name="rs" property="tyjmje"/>">
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd"/>">
			<input type="hidden" id="xslbmc" name="xslbmc"
				value="<bean:write name="rs" property="xslbmc"/>">
			<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj"/>">
			<input type="hidden" id="isKNS" name="isKNS"
				value="<bean:write name="isKNS" scope="request" />">

			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         	alert("��ͨ��ѧУ��ˣ������ٴ����룡");
	         	</script>
				</logic:match>
			</logic:present>
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

			<table class="tbstyle">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" colspan="3">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="readonly"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" colspan="3">
							<input type="text" id="xh" name="xh" readonly="readonly"
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
						<input type="text" id="xm" name="xm" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��Դ��
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="syd" readonly="readonly" name="syd"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="syd"/>">
					</td>
					<td>
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						<input type="text" id="xb" name="xb" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							������ò
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="zzmm" readonly="readonly" name="zzmm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmm"/>">
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
					<td colspan="2">
						<div align="center">
							ѧУ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xxmc" readonly="readonly" name="xxmc"
							style="width:100%;heigh:100%" value="<bean:write name="xxmc"/>">
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
					<td colspan="2">
						<div align="center">
							רҵ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="zymc" name="zymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<td>
						<div align="center">
							��
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
							��������
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="csrq" readonly="readonly" name="csrq"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csrq"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>ѧ�����
						</div>
					</td>
					<td>
						<html:select name="rs" property="xslb">
							<html:options collection="xslbList" property="xslb"
								labelProperty="xslbmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>�ҳ�����
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jzxm" name="jzxm" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzxm"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��ͥ�绰
						</div>
					</td>
					<td>
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
							<font color="red">*</font>��ͥ��ַ
						</div>
					</td>
					<td colspan="5">
						<input type="text" id="jtdz" maxlength="50" name="jtdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdz"/>">
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
							<font color="red">*</font>����绰
						</div>
					</td>
					<td>
						<input type="text" id="ssdh" name="ssdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="ssdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>�����ַ
						</div>
					</td>
					<td colspan="5">
						<input type="text" id="ssdz" maxlength="50" name="ssdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="ssdz"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>��ͥ�������(�˾�������)
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="jtjjqk" rows='5' style="width:100%" onblur="yzdx(this,'jtjjqk')"/>
						<input type="hidden" id="jtjjqk" name="jtjjqk" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>Ժ��ϵ���Ѳ������
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="yxknbzqk" rows='5' style="width:100%" onblur="yzdx(this,'yxknbzqk')"/>
						<input type="hidden" id="yxknbzqk" name="yxknbzqk" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>�ڹ���λ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="qggw" maxlength="25" name="qggw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qggw"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>ѧϰ�ɼ�(�༶����)
						</div>
					</td>
					<td>
						<input type="text" id="xxcj" name="xxcj" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xxcj"/>">
					</td>
				</tr>
				<tr>
					<td colspan="7">
						<div align="right">
							<font color="red">ע������ѧϰ�ɼ���д�߿��������༶������</font>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>��������
						</div>
					</td>
					<td colspan="3" align="center">
						<logic:present name="rs" property="sqzl">
							<logic:match value="����" name="rs" property="sqzl">
								<input type="radio" name="sqzl" value="����" checked>&nbsp;&nbsp;����
							         	<input type="radio" name="sqzl" value="�⽻">&nbsp;&nbsp;�⽻
							         </logic:match>
							<logic:match value="�⽻" name="rs" property="sqzl">
								<input type="radio" name="sqzl" value="����">&nbsp;&nbsp;����
							         	<input type="radio" name="sqzl" value="�⽻" checked>&nbsp;&nbsp;�⽻
							         </logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sqzl">
							<input type="radio" name="sqzl" value="����" checked>&nbsp;&nbsp;����
							         <input type="radio" name="sqzl" value="�⽻">&nbsp;&nbsp;�⽻
						         </logic:notPresent>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>������
						</div>
					</td>
					<td>
						<input type="text" id="sqje" name="sqje" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>ƽʱ�������
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="psbxqk" rows='5' style="width:100%" onblur="yzdx(this,'psbxqk')"/>
						<input type="hidden" id="psbxqk" name="psbxqk" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>���˻����
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="gehjqk" rows='5' style="width:100%" onblur="yzdx(this,'gehjqk')"/>
						<input type="hidden" id="gehjqk" name="gehjqk" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ע
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="bz" rows='3' style="width:100%" onblur="yzdx(this,'bz')"/>
						<input type="hidden" id="bz" name="bz" value="">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2" id="tjbut"
					onClick="yz();">
					�ύ����
				</button>
				<button class="button2"
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
