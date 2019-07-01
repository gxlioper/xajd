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
			var chhzjl = document.getElementById('chhzjl').value;
			var lxdh = document.getElementById('lxdh').value;
			var sxnzhcpzpm = document.getElementById('sxnzhcpzpm').value;
			var sxnxxcjzpm = document.getElementById('sxnxxcjzpm').value;
			var jthk = document.getElementById('jthk').value;
			var jtzrks = document.getElementById('jtzrks').value;
			var rjysr = document.getElementById('rjysr').value;
			var jtyzsr = document.getElementById('jtyzsr').value;
			var srly = document.getElementById('srly').value;
			var yzbm = document.getElementById('yzbm').value;
			var jtzz = document.getElementById('jtzz').value;
			var sqly = document.getElementById('sqly').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((chhzjl == null) || (chhzjl == "")){
				alert("������ֽ�������Ϊ��!");
				return false;
			}
			if((lxdh == null) || (lxdh == "")){
				alert("��ϵ�绰����Ϊ��!");
				return false;
			}
			if((sxnzhcpzpm == null) || (sxnzhcpzpm == "")){
				alert("��ѧ���ۺϲ�������������Ϊ��!");
				return false;
			}
			if((sxnxxcjzpm == null) || (sxnxxcjzpm == "")){
				alert("��ѧ��ѧϰ�ɼ�����������Ϊ��!");
				return false;
			}
			if((jthk == null) || (jthk == "")){
				alert("��ͥ���ڲ���Ϊ��!");
				return false;
			}
			if((jtzrks == null) || (jtzrks == "")){
				alert("��ͥ���˿�������Ϊ��!");
				return false;
			}
			if((rjysr == null) || (rjysr == "")){
				alert("�˾������벻��Ϊ��!");
				return false;
			}
			if((jtyzsr == null) || (jtyzsr == "")){
				alert("��ͥ�������벻��Ϊ��!");
				return false;
			}
			if((srly == null) || (srly == "")){
				alert("������Դ����Ϊ��!");
				return false;
			}
			if((yzbm == null) || (yzbm == "")){
				alert("�������벻��Ϊ��!");
				return false;
			}
			if((jtzz == null) || (jtzz == "")){
				alert("��ͥסַ����Ϊ��!");
				return false;
			}
			if((sqly == null) || (sqly == "")){
				alert("�������ɲ���Ϊ��!");
				return false;
			}
			if(chhzjl != null){
	         	if(chhzjl.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("������ֽ������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(jtzz != null){
	         	if(jtzz.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("��ͥסַ���ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("�������ɲ��ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zzsf_gjlzjxj.do?doType=add";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/zzsf_gjlzjxjb.do";
			document.forms[0].submit();
		}
		
		function je(){
			var jtzrks = document.getElementById('jtzrks').value;
			var rjysr = document.getElementById('rjysr').value;
			if((jtzrks == null) || (jtzrks == "")){
				jtzrks = "0";
			}
			if((rjysr == null) || (rjysr == "")){
				rjysr = "0";
			}
			var jtyzsr = Math.round(jtzrks) * Math.round(rjysr);
			document.getElementById('jtyzsr').value=jtyzsr;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ������־��ѧ������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ�䣡
			</p>
		</center>
	</logic:equal>
		<html:form action="zzsf_gjlzjxj.do" method="post">
			<input type="hidden" id="url" name="url" value="/zzsf_gjlzjxj.do" />
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
			<input type="hidden" id="xyzzfzryj" name="xyzzfzryj"
				value="<bean:write name="rs" property="xyzzfzryj" scope="request" />">
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd" scope="request" />">
			<input type="hidden" id="isKNS" name="isKNS"
				value="<bean:write name="isKNS" scope="request" />">

			<logic:present name="notKns">
				<logic:match value="is" name="notKns">
					<script language="javascript">
	         			alert("�������������������룡");
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
							<font color="red">*</font>������ֽ���
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="chhzjl" rows='3' style="width:100%" onblur="yzdx(this,'chhzjl')"/>
						<input type="hidden" id="chhzjl" name="chhzjl" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��ͥ����
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="jthk">
									<logic:match value="����" name="rs" property="jthk">
										<input type="radio" name="jthk" value="����" checked>&nbsp;&nbsp;A  ����
							         	<input type="radio" name="jthk" value="ũ��">&nbsp;&nbsp;B ũ��
							         </logic:match>
									<logic:match value="ũ��" name="rs" property="jthk">
										<input type="radio" name="jthk" value="����">&nbsp;&nbsp;A  ����
							         	<input type="radio" name="jthk" value="ũ��" checked>&nbsp;&nbsp;B ũ��
							         </logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="jthk">
									<input type="radio" name="jthk" value="����" checked>&nbsp;&nbsp;A  ���� 
							         <input type="radio" name="jthk" value="ũ��">&nbsp;&nbsp;B ũ��
						         </logic:notPresent>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��ͥ�˿�����
						</div>
					</td>
					<td>
						<input type="text" id="jtzrks" maxlength="3" name="jtzrks"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="jtzrks"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��ͥ�˾�������
						</div>
					</td>
					<td>
						<input type="text" id="rjysr" maxlength="5" name="rjysr"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="rjysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��ͥ��������
						</div>
					</td>
					<td>
						<input type="text" id="jtyzsr" maxlength="10" name="jtyzsr" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtyzsr"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>������Դ
						</div>
					</td>
					<td>
						<input type="text" id="srly" maxlength="25" name="srly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="srly"/>">
					</td>
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
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��ͥ��ַ
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="jtzz" rows='2' style="width:100%" onblur="yzdx(this,'jtzz')"/>
						<input type="hidden" id="jtzz" name="jtzz" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��ѧ���ۺϲ���������
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sxnzhcpzpm" maxlength="10" name="sxnzhcpzpm"
							style="width:60%;heigh:100%"
							value="<bean:write name="rs" property="sxnzhcpzpm"/>">
							������/��רҵ�꼶��������
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>��ѧ��ѧϰ�ɼ�������
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sxnxxcjzpm" maxlength="10" name="sxnxxcjzpm"
							style="width:60%;heigh:100%"
							value="<bean:write name="rs" property="sxnxxcjzpm"/>">
							������/��רҵ�꼶��������
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
				<button type="button" class="button2" id="tjbut"
					onClick="yz();">
					�ύ����
				</button>
				<button type="button" class="button2"
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
