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
			var sqly = document.getElementById('sqly').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("�������ɲ��ܳ���2000���ַ���");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=chzxjsqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=chzxjsqb";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ���� - �ʺ������ѧ��
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
		<html:form action="nblg_xszz.do?method=chzxjsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/nblg_xszz.do?method=chzxjsq" />
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
						<td align="center" colspan="2">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" colspan="2">
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
					<td colspan="2">
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
					<td colspan="2">
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
					<td>
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
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
					<td colspan="2">
						<input type="text" id="csny" readonly="readonly" name="csny"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csny"/>">
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<input type="text" id="jg" name="jg" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jg"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥסַ
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jtzz" maxlength="200" name="jtzz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzz"/>">
					</td>
					<td>
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="lxdh" name="lxdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>				
				<!--��������һ-->
				<logic:notEqual value="13742" name="xxdm">
				<tr>
					<td width="4%" rowspan="6">
						<div align="center">
							��
							<br />
							ͥ
							<br />
							��
							<br />
							Ա
							<br />
							��
							<br />
							��
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td width="17%">
						<div align="center">
							��ν
						</div>
					</td>
					<td width="17%">
						<div align="center">
							������
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							ְҵ�͹���(ѧϰ)��λ
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy1_xm" name="jtcy1_xm"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy1_xm" />">
					</td>
					<td>
						<input type="text" id="jtcy1_cw" name="jtcy1_cw"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy1_cw" />">
					</td>
					<td>
						<input type="text" id="jtcy1_sr" name="jtcy1_sr"
							style="width:100%;heigh:100%" maxlength="7"
							value="<bean:write name='rs' property="jtcy1_sr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy1_gz" name="jtcy1_gz"
							style="width:100%;heigh:100%" maxlength="200"
							value="<bean:write name='rs' property="jtcy1_gz" />">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy2_xm" name="jtcy2_xm"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy2_xm" />">
					</td>
					<td>
						<input type="text" id="jtcy2_cw" name="jtcy2_cw"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy2_cw" />">
					</td>
					<td>
						<input type="text" id="jtcy2_sr" name="jtcy2_sr"
							style="width:100%;heigh:100%" maxlength="7"
							value="<bean:write name='rs' property="jtcy2_sr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy2_gz" name="jtcy2_gz"
							style="width:100%;heigh:100%" maxlength="200"
							value="<bean:write name='rs' property="jtcy2_gz" />">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy3_xm" name="jtcy3_xm"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy3_xm" />">
					</td>
					<td>
						<input type="text" id="jtcy3_cw" name="jtcy3_cw"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy3_cw" />">
					</td>
					<td>
						<input type="text" id="jtcy3_sr" name="jtcy3_sr"
							style="width:100%;heigh:100%" maxlength="7"
							value="<bean:write name='rs' property="jtcy3_sr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy3_gz" name="jtcy3_gz"
							style="width:100%;heigh:100%" maxlength="200"
							value="<bean:write name='rs' property="jtcy3_gz" />">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy4_xm" name="jtcy4_xm"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy4_xm" />">
					</td>
					<td>
						<input type="text" id="jtcy4_cw" name="jtcy4_cw"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy4_cw" />">
					</td>
					<td>
						<input type="text" id="jtcy4_sr" name="jtcy4_sr"
							style="width:100%;heigh:100%" maxlength="7"
							value="<bean:write name='rs' property="jtcy4_sr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy4_gz" name="jtcy4_gz"
							style="width:100%;heigh:100%" maxlength="200"
							value="<bean:write name='rs' property="jtcy4_gz" />">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy5_xm" name="jtcy5_xm"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy5_xm" />">
					</td>
					<td>
						<input type="text" id="jtcy5_cw" name="jtcy5_cw"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtcy5_cw" />">
					</td>
					<td>
						<input type="text" id="jtcy5_sr" name="jtcy5_sr"
							style="width:100%;heigh:100%" maxlength="7"
							value="<bean:write name='rs' property="jtcy5_sr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy5_gz" name="jtcy5_gz"
							style="width:100%;heigh:100%" maxlength="200"
							value="<bean:write name='rs' property="jtcy5_gz" />">
					</td>
				</tr>
				</logic:notEqual>
				<!--end��������һ-->
				<!--������һ-->
				<logic:equal value="13742" name="xxdm">
				<tr>
					<td width="4%" rowspan="4">
						<div align="center">
							��
							<br />
							ͥ
							<br />
							��
							<br />
							Ա
							<br />
							��
							<br />
							��
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td width="17%">
						<div align="center">
							��ν
						</div>
					</td>
					<td width="17%">
						<div align="center">
							������
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							ְҵ�͹���(ѧϰ)��λ
						</div>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;${rs.jtcy1_xm}
					</td>
					<td>
						&nbsp;${rs.jtcy1_gx}
					</td>
					<td>
						&nbsp;${rs.jtcy1_ysr}
					</td>
					<td colspan="2">
						&nbsp;${rs.jtcy1_gzdwmc}
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;${rs.jtcy2_xm}
					</td>
					<td>
						&nbsp;${rs.jtcy2_gx}
					</td>
					<td>
						&nbsp;${rs.jtcy2_ysr}
					</td>
					<td colspan="2">
						&nbsp;${rs.jtcy2_gzdwmc}
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;${rs.jtcy3_xm}
					</td>
					<td>
						&nbsp;${rs.jtcy3_gx}
					</td>
					<td>
						&nbsp;${rs.jtcy3_ysr}
					</td>
					<td colspan="2">
						&nbsp;${rs.jtcy3_gzdwmc}
					</td>
				</tr>
				</logic:equal>
				<!--end������һ-->
				<tr>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="sqly" rows='12'
							style='width:100%' onblur="yzdx(this,'sqly')" />
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
