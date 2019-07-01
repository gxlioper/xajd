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
			var yhzzqk = document.getElementById('yhzzqk').value;
			var sqly = document.getElementById('sqly').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(chhzjl != null){
	         	if(chhzjl.replace(/[^\u0000-\u00ff]/g, "**").length > 800){	         
	          		 alert("������ֽ������ܳ���800���ַ���");
	          		 return false;
	       		 }
	       	}
	       	if(yhzzqk != null){
	         	if(yhzzqk.replace(/[^\u0000-\u00ff]/g, "**").length > 800){	         
	          		 alert("�ѻ�����������ܳ���800���ַ���");
	          		 return false;
	       		 }
	       	}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("ѧ�������������ɲ��ܳ���2000���ַ���");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=lsknbzsqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=lsknbzsqb";
			document.forms[0].submit();
		}
		function rks(){
			var jtzrks = document.getElementById('jtzrks').value;
			var rjysr = document.getElementById('rjysr').value;
			
			if((jtzrks == null) || (jtzrks == "")){
				jtzrks = "0";
			}
			if((rjysr == null) || (rjysr == "")){
				rjysr = "0";
			}
			
			document.getElementById('jtyzsr').value = Math.round(jtzrks)*Math.round(rjysr);
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ���� - ��ʱ���Ѳ���
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
		<html:form action="nblg_xszz.do?method=lsknbzsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/nblg_xszz.do?method=lsknbzsq" />
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
							����
						</div>
					</td>
					<td>
						<input type="text" id="mzmc" name="mzmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
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
						<input type="text" id="rxny" name="rxny" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxny"/>">
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
							������ֽ���
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="chhzjl" rows='5' style='width:100%' onblur="yzdx(this,'chhzjl')"/>
						<input type="hidden" id="chhzjl" name="chhzjl" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�ѻ��������
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="yhzzqk" rows='5' style='width:100%' onblur="yzdx(this,'yhzzqk')"/>
						<input type="hidden" id="yhzzqk" name="yhzzqk" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							������
						</div>
					</td>
					<td>
						<input type="text" id="sqje" name="sqje" maxlength="5"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="sqje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<input type="text" id="xn" name="xn" readonly="readonly"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="xn"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ͥ����
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="jthk">
							<logic:match value="����" name="rs" property="jthk">
								<input type="radio" name="jthk" value="����" checked>&nbsp;&nbsp;����
							    <input type="radio" name="jthk" value="ũ��">&nbsp;&nbsp;ũ��
							</logic:match>
							<logic:match value="ũ��" name="rs" property="jthk">
								<input type="radio" name="jthk" value="����">&nbsp;&nbsp;����
							    <input type="radio" name="jthk" value="ũ��" checked>&nbsp;&nbsp;ũ��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="jthk">
							<input type="radio" name="jthk" value="����" checked>&nbsp;&nbsp;����
							<input type="radio" name="jthk" value="ũ��">&nbsp;&nbsp;ũ��
						</logic:notPresent>
					</td>
					<td>
						<div align="center">
							��ͥ�˿�����
						</div>
					</td>
					<td>
						<input type="text" id="jtzrks" name="jtzrks" maxlength="3"
							style="width:100%;heigh:100%" onblur="rks();"
							value="<bean:write name="rs" property="jtzrks"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ͥ�˾�������
						</div>
					</td>
					<td>
						<input type="text" id="rjysr" name="rjysr" maxlength="5"
							style="width:100%;heigh:100%" onblur="rks();"
							value="<bean:write name="rs" property="rjysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							��ͥ��������
						</div>
					</td>
					<td>
						<input type="text" id="jtyzsr" name="jtyzsr" maxlength="10"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name="rs" property="jtyzsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							������Դ
						</div>
					</td>
					<td>
						<input type="text" id="srly" name="srly" maxlength="100"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="srly"/>">
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
							��ͥסַ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtzz" name="jtzz" maxlength="200"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="jtzz"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="sqly" rows='12' style='width:100%' onblur="yzdx(this,'sqly')"/>
						<input type="hidden" id="sqly" name="sqly" value="">
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
