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
			var lxdh = document.getElementById('lxdh').value;
			var gxnbxkcs = document.getElementById('gxnbxkcs').value;
			var yxkcs = document.getElementById('yxkcs').value;
			var lhkcs = document.getElementById('lhkcs').value;
			var cjpm = document.getElementById('cjpm').value;
			var zhkpcj = document.getElementById('zhkpcj').value;
			var zhkppm = document.getElementById('zhkppm').value;
			var hjqk = document.getElementById('hjqk').value;
			var hjqk_xj = document.getElementById('hjqk_xj').value;
			var hjqk_xxj = document.getElementById('hjqk_xxj').value;
			var hjqk_sj = document.getElementById('hjqk_sj').value;
			var sqly = document.getElementById('sqly').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((lxdh == null) || (lxdh == "")){
				alert("��ϵ�绰����Ϊ��!");
				return false;
			}
			if((gxnbxkcs == null) || (gxnbxkcs == "")){
				alert("��ѧ����޿γ�������Ϊ��!");
				return false;
			}
			if((yxkcs == null) || (yxkcs == "")){
				alert("����γ�������Ϊ��!");
				return false;
			}
			if((lhkcs == null) || (lhkcs == "")){
				alert("���ÿγ�������Ϊ��!");
				return false;
			}
			if((cjpm == null) || (cjpm == "")){
				alert("�ɼ���������Ϊ��!");
				return false;
			}
			if((zhkpcj == null) || (zhkpcj == "")){
				alert("�ۺϿ����ɼ�����Ϊ��!");
				return false;
			}
			if((zhkppm == null) || (zhkppm == "")){
				alert("�ۺϿ�����������Ϊ��!");
				return false;
			}
			if((hjqk == null) || (hjqk == "")){
				alert("���������Ϊ��!");
				return false;
			}
			if((hjqk_xj == null) || (hjqk_xj == "")){
				alert("ϵ�������Ϊ��!");
				return false;
			}
			if((hjqk_xxj == null) || (hjqk_xxj == "")){
				alert("У�������Ϊ��!");
				return false;
			}
			if((hjqk_sj == null) || (hjqk_sj == "")){
				alert("ʡ�������Ϊ��!");
				return false;
			}
			if((sqly == null) || (sqly == "")){
				alert("�������ɲ���Ϊ��!");
				return false;
			}
			if(hjqk != null){
	         	if(hjqk.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("��������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("�������ɲ��ܴ���2000���ַ�");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zzsf_gjjxj.do?doType=add";
			document.forms[0].submit();
		}
		
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/zzsf_gjjxjb.do";
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
			��ǰ����λ�ã�ѧ������ - ���ҽ�ѧ������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ�䣡
			</p>
		</center>
	</logic:equal>
		<html:form action="zzsf_gjjxj.do" method="post">
			<input type="hidden" id="url" name="url" value="/zzsf_gjjxj.do" />
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
			<input type="hidden" id="xydzzshyj" name="xydzzshyj"
				value="<bean:write name="rs" property="xydzzshyj" scope="request" />">
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
							��ѧ����
						</div>
					</td>
					<td>
						<input type="text" id="rxrq" readonly="readonly" name="rxrq"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxrq"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="lxdh" maxlength="15" name="lxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��ѧ����޿γ���
						</div>
					</td>
					<td>
						<input type="text" id="gxnbxkcs" maxlength="3" name="gxnbxkcs"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gxnbxkcs"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>����γ���
						</div>
					</td>
					<td>
						<input type="text" id="yxkcs" maxlength="3" name="yxkcs"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yxkcs"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>���ÿγ���
						</div>
					</td>
					<td>
						<input type="text" id="lhkcs" maxlength="3" name="lhkcs"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lhkcs"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>�ɼ�����<br />(רҵ���꼶)
						</div>
					</td>
					<td>
						<input type="text" id="cjpm" maxlength="10" name="cjpm"
							style="width:60%;heigh:100%"
							value="<bean:write name="rs" property="cjpm"/>">(����/������)
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>�ۺϿ����ɼ�
						</div>
					</td>
					<td>
						<input type="text" id="zhkpcj" maxlength="10" name="zhkpcj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zhkpcj"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>�ۺϿ�������
						</div>
					</td>
					<td>
						<input type="text" id="zhkppm" maxlength="10" name="zhkppm"
							style="width:60%;heigh:100%"
							value="<bean:write name="rs" property="zhkppm"/>">
						(����/������)
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>�����
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="hjqk" rows='3' style="width:100%" onblur="yzdx(this,'hjqk')"/>
						<input type="hidden" id="hjqk" name="hjqk" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>���ϵ������
						</div>
					</td>
					<td>
						<input type="text" id="hjqk_xj" maxlength="3" name="hjqk_xj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hjqk_xj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>���У������
						</div>
					</td>
					<td>
						<input type="text" id="hjqk_xxj" maxlength="3" name="hjqk_xxj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hjqk_xxj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>���ʡ������
						</div>
					</td>
					<td>
						<input type="text" id="hjqk_sj" maxlength="3" name="hjqk_sj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hjqk_sj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
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
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��������
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="sqly" rows='5' style="width:100%" onblur="yzdx(this,'sqly')"/>
						<input type="hidden" id="sqly" name="sqly" value="">
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
