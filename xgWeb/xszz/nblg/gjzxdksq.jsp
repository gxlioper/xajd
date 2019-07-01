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


	<title><bean:message key="lable.title" />
	</title>
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
			var sqje = document.getElementById('sqje').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((sqje == null) || (sqje == "")){
				sqje = "0";
			}
			if (6000 < Math.round(sqje)){
				alert("���������ô���6000Ԫ!");
				return false;
			}
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=gjzxdksqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function je(){
			var sqje = document.getElementById('sqje').value;
			if((sqje == null) || (sqje == "")){
				sqje = "0";
			}
			if (6000 < Math.round(sqje)){
				alert("���������ô���6000Ԫ!");
				document.getElementById('sqje').value = "6000";
			}
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=gjzxdksqb";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ���� - ������ѧ����
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
		<html:form action="nblg_xszz.do?method=gjzxdksq" method="post">
			<input type="hidden" id="url" name="url"
				value="/nblg_xszz.do?method=gjzxdksq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id=xn name="xn"
				value="<bean:write name="rs" property="xn" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">
			<input type="hidden" id="xz" name="xz"
				value="<bean:write name="rs" property="xz" />">

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
			<logic:present name="rs" property="notKns">
				<logic:match value="notKns" name="rs" property="notKns">
					<script language="javascript">
	         	alert("�������������������룡");
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
							�꼶
						</div>
					</td>
					<td>
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
					<td>
						<div align="center">
							רҵ����
						</div>
					</td>
					<td>
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
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<input type="text" id="csny" readonly="readonly" name="csny"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csny"/>">
					</td>
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
				</tr>
				<tr>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<input type="text" id="jg" name="jg" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jg"/>">
					</td>
					<td>
						<div align="center">
							�������ڵ�
						</div>
					</td>
					<td>
						<input type="text" id="hjszd" name="hjszd" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hjszd"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							E-Mail��ַ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="email" maxlength="400" name="email"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="email"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�ƶ��绰
						</div>
					</td>
					<td>
						<input type="text" id="yddh" name="yddh" maxlength="11"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yddh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							��ͥ�绰
						</div>
					</td>
					<td>
						<input type="text" id="jtdh" name="jtdh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							����״��
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="jkzk">
							<logic:match value="����" name="rs" property="jkzk">
								<input type="radio" name="jkzk" value="����" checked>&nbsp;&nbsp;����&nbsp;
							    <input type="radio" name="jkzk" value="һ��">&nbsp;&nbsp;һ��
							</logic:match>
							<logic:match value="һ��" name="rs" property="jkzk">
								<input type="radio" name="jkzk" value="����">&nbsp;&nbsp;����&nbsp;
							    <input type="radio" name="jkzk" value="һ��" checked>&nbsp;&nbsp;һ��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="jkzk">
							<input type="radio" name="jkzk" value="����" checked>&nbsp;&nbsp;����&nbsp;
							<input type="radio" name="jkzk" value="һ��">&nbsp;&nbsp;һ��
						</logic:notPresent>
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<input type="text" id="yzbm" name="yzbm" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ͥͨѶ��ַ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jttxdz" maxlength="400" name="jttxdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jttxdz"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							������
						</div>
					</td>
					<td>
						<input type="text" id="sqje" name="sqje" maxlength="4"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="sqje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							���ѵȼ�
						</div>
					</td>
					<td>
						<bean:write name="rs" property="kndj"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��֤��1���
						</div>
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<input type="text" id="jzr1_xm" name="jzr1_xm" maxlength="50"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr1_xm"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�Ա�
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="jzr1_xb">
							<logic:match value="��" name="rs" property="jzr1_xb">
								<input type="radio" name="jzr1_xb" value="��" checked>&nbsp;&nbsp;��&nbsp;
							    <input type="radio" name="jzr1_xb" value="Ů">&nbsp;&nbsp;Ů
							</logic:match>
							<logic:match value="Ů" name="rs" property="jzr1_xb">
								<input type="radio" name="jzr1_xb" value="��">&nbsp;&nbsp;��&nbsp;
							    <input type="radio" name="jzr1_xb" value="Ů" checked>&nbsp;&nbsp;Ů
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="jzr1_xb">
							<input type="radio" name="jzr1_xb" value="��" checked>&nbsp;&nbsp;��&nbsp;
							<input type="radio" name="jzr1_xb" value="Ů">&nbsp;&nbsp;Ů
						</logic:notPresent>
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<input type="text" id="jzr1_sfzh" name="jzr1_sfzh" maxlength="18"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr1_sfzh"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�����˹�ϵ
						</div>
					</td>
					<td>
						<input type="text" id="jzr1_gx" name="jzr1_gx" maxlength="50"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr1_gx"/>">
					</td>
					<td>
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="jzr1_lxdh" name="jzr1_lxdh" maxlength="20"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr1_lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��סַ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jzr1_xzz" name="jzr1_xzz" maxlength="200"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr1_xzz"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��֤��2���
						</div>
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<input type="text" id="jzr2_xm" name="jzr2_xm" maxlength="50"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr2_xm"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�Ա�
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="jzr2_xb">
							<logic:match value="��" name="rs" property="jzr2_xb">
								<input type="radio" name="jzr2_xb" value="��" checked>&nbsp;&nbsp;��&nbsp;
							    <input type="radio" name="jzr2_xb" value="Ů">&nbsp;&nbsp;Ů
							</logic:match>
							<logic:match value="Ů" name="rs" property="jzr2_xb">
								<input type="radio" name="jzr2_xb" value="��">&nbsp;&nbsp;��&nbsp;
							    <input type="radio" name="jzr2_xb" value="Ů" checked>&nbsp;&nbsp;Ů
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="jzr2_xb">
							<input type="radio" name="jzr2_xb" value="��" checked>&nbsp;&nbsp;��&nbsp;
							<input type="radio" name="jzr2_xb" value="Ů">&nbsp;&nbsp;Ů
						</logic:notPresent>
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<input type="text" id="jzr2_sfzh" name="jzr2_sfzh" maxlength="18"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr2_sfzh"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�����˹�ϵ
						</div>
					</td>
					<td>
						<input type="text" id="jzr2_gx" name="jzr2_gx" maxlength="50"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr2_gx"/>">
					</td>
					<td>
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="jzr2_lxdh" name="jzr2_lxdh" maxlength="20"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr2_lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��סַ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jzr2_xzz" name="jzr2_xzz" maxlength="200"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jzr2_xzz"/>">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">			
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2" onClick="yz();">
					�ύ����
				</button>
				<button type="button" class="button2" onClick="toPrintOut();">
					��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
