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
	          		 alert("�������ɲ��ܴ���2000���ַ�");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/n05_xszz.do?method=gjzxj3sqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/n05_xszz.do?method=gjzxj3sqb";
			document.forms[0].submit();
		}
		function changeSr(){
			var jtzrks = document.getElementById('jtzrks').value;
			var rjysr = document.getElementById('rjysr').value;
			
			if((jtzrks == null) || (jtzrks == "")){
				jtzrks = "0";
			}
			if((rjysr == null) || (rjysr == "")){
				rjysr = "0";
			}
			document.getElementById('jtyzsr').value = toInt(jtzrks) * toInt(rjysr);
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ������ѧ�������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<p align="center">
			<font color="red">Ŀǰ��������ʱ�䷶Χ�ڣ��ݲ��������룡</font>
		</p>
	</logic:equal>
	<html:form action="n05_xszz.do?method=gjzxj3sq" method="post">
		<input type="hidden" id="url" name="url"
			value="/n05_xszz.do?method=gjzxj3sq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc" />
		<input type="hidden" id="pkVal" name="pkVal"
			value="<bean:write name="rs" property="pkVal" />">

		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<logic:present name="isPASS">
					<logic:match value="is" name="isPASS">
						<script language="javascript">
			         			alert("��ͨ����ˣ��������룡");
			         		</script>
					</logic:match>
				</logic:present>
				<logic:notPresent name="isPASS">
					<script language="javascript">
		         		alert("����ʧ�ܣ�");
		         	</script>
				</logic:notPresent>				
			</logic:match>
		</logic:present>
		
		<table class="tbstyle" width="90%">
			<tr>
				<logic:equal name="userOnLine" value="teacher" scope="session">
					<td align="center" colspan="2">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" colspan="2">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<logic:notEqual name="type" value="modi">
								<logic:equal name="sfKns" value="no">
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:equal>
								<logic:notEqual name="sfKns" value="no">
									<button onclick="showTopWin('/xgxt/stu_info.do?kns=yes',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:notEqual>
							</logic:notEqual>
					</td>
				</logic:equal>
				<logic:equal name="userOnLine" value="student" scope="session">
					<td align="center" colspan="2">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" colspan="2">
						<input type="text" id="xh" name="xh" style="width:100%;heigh:100%"
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
				<td>
					<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zzmmmc"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��������
					</div>
				</td>
				<td colspan="2">
					<input type="text" id="csrq" readonly="readonly" name="csrq"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="csrq"/>">
				</td>
				<td>
					<div align="center">
						��ѧʱ��
					</div>
				</td>
				<td>
					<input type="text" id="rxrq" name="rxrq" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="rxrq"/>">
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
						��ϵ�绰
					</div>
				</td>
				<td colspan="2">
					<input type="text" id="lxdh" name="lxdh" maxlength="20"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="lxdh"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<div align="center">
						ѧ��������
					</div>
				</td>
				<td>
					<input type="text" id="xsyhm" name="xsyhm" maxlength="100"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xsyhm"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						ѧ������
					</div>
				</td>
				<td colspan="2">
					<input type="text" id="xshm" name="xshm" maxlength="50"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xshm"/>">
				</td>
				<td>
					<div align="center">
						ѧ���ʺ�
					</div>
				</td>
				<td>
					<input type="text" id="xszh" name="xszh" maxlength="40"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xszh"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						ѧ��
					</div>
				</td>
				<td colspan="2">
					<input type="text" id="xn" readonly="readonly" name="xn"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xn"/>">
				</td>
				<td>
					<div align="center">
						����ʱ��
					</div>
				</td>
				<td>
					<input type="text" id="sqsj" name="sqsj" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="sqsj"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��ͥ����
					</div>
				</td>
				<td colspan="2">
					<html:select name="rs" property="jthk">
						<html:option value=""></html:option>
						<html:option value="����">����</html:option>
						<html:option value="ũ��">ũ��</html:option>
					</html:select>
				</td>
				<td>
					<div align="center">
						��ͥ�˿�����
					</div>
				</td>
				<td>
					<input type="text" id="jtzrks" name="jtzrks" maxlength="3"
						style="width:100%;heigh:100%" onblur="changeSr()"
						value="<bean:write name="rs" property="jtzrks"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						�˾�������
					</div>
				</td>
				<td colspan="2">
					<input type="text" id="rjysr" name="rjysr" maxlength="6"
						style="width:100%;heigh:100%" onblur="changeSr()"
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
					<input type="text" id="jtyzsr" name="jtyzsr" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtyzsr"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						������Դ
					</div>
				</td>
				<td colspan="2">
					<input type="text" id="srly" name="srly" maxlength="50"
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
			<!-- �Ͼ���ʦ -->
			<logic:equal name="xxdm" value="8001">
				<tr>
					<td colspan="2">
						<div align="center">
							�Ƿ�ͱ�
						</div>
					</td>
					<td colspan="2">
						<html:select name="rs" property="sfdb">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
					<td>
						
					</td>
					<td>
						
					</td>
				</tr>
			</logic:equal>
				<tr>
				<td colspan="2">
					<div align="center">
						������ֽ���
					</div>
				</td>
				<td colspan="4">
					<input type="text" id="chjl" name="chjl" maxlength="100"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="chjl"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��ͥסַ
					</div>
				</td>
				<td colspan="4">
					<input type="text" id="jtzz" name="jtzz" maxlength="100"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtzz"/>">
				</td>
			</tr>
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
						����
					</div>
				</td>
				<td width="17%">
					<div align="center">
						�뱾�˹�ϵ
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						������λ
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" id="jtcy1_xm" name="jtcy1_xm" maxlength="25"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy1_xm"/>">
				</td>
				<td>
					<input type="text" id="jtcy1_nl" name="jtcy1_nl" maxlength="3"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy1_nl"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<input type="text" id="jtcy1_gx" name="jtcy1_gx" maxlength="10"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy1_gx"/>">
				</td>
				<td colspan="2">
					<input type="text" id="jtcy1_dw" name="jtcy1_dw" maxlength="100"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy1_dw"/>">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" id="jtcy2_xm" name="jtcy2_xm" maxlength="25"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy2_xm"/>">
				</td>
				<td>
					<input type="text" id="jtcy2_nl" name="jtcy2_nl" maxlength="3"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy2_nl"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<input type="text" id="jtcy2_gx" name="jtcy2_gx" maxlength="10"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy2_gx"/>">
				</td>
				<td colspan="2">
					<input type="text" id="jtcy2_dw" name="jtcy2_dw" maxlength="100"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy2_dw"/>">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" id="jtcy3_xm" name="jtcy3_xm" maxlength="25"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy3_xm"/>">
				</td>
				<td>
					<input type="text" id="jtcy3_nl" name="jtcy3_nl" maxlength="3"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy3_nl"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<input type="text" id="jtcy3_gx" name="jtcy3_gx" maxlength="10"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy3_gx"/>">
				</td>
				<td colspan="2">
					<input type="text" id="jtcy3_dw" name="jtcy3_dw" maxlength="100"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy3_dw"/>">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" id="jtcy4_xm" name="jtcy4_xm" maxlength="25"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy4_xm"/>">
				</td>
				<td>
					<input type="text" id="jtcy4_nl" name="jtcy4_nl" maxlength="3"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy4_nl"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<input type="text" id="jtcy4_gx" name="jtcy4_gx" maxlength="10"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy4_gx"/>">
				</td>
				<td colspan="2">
					<input type="text" id="jtcy4_dw" name="jtcy4_dw" maxlength="100"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy4_dw"/>">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" id="jtcy5_xm" name="jtcy5_xm" maxlength="25"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy5_xm"/>">
				</td>
				<td>
					<input type="text" id="jtcy5_nl" name="jtcy5_nl" maxlength="3"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy5_nl"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<input type="text" id="jtcy5_gx" name="jtcy5_gx" maxlength="10"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy5_gx"/>">
				</td>
				<td colspan="2">
					<input type="text" id="jtcy5_dw" name="jtcy5_dw" maxlength="100"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy5_dw"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��������
					</div>
				</td>
				<td colspan="4">
					<html:textarea name="rs" property="sqly" rows='10'
						style="width:100%" onblur="yzdx(this,'sqly')" />
					<input type="hidden" id="sqly" name="sqly" value="">
				</td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:90%">
			<button class="button2" id="buttonSave" onClick="yz();">
				�ύ����
			</button>
			<button class="button2" onClick="toPrintOut();" id="buttonPrint">
				��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
			</button>
		</div>
	</html:form>
</body>
	<logic:equal name="sfksq" value="-1">
		<script language="javascript">
		  $("buttonSave").disabled=true;
		</script>
	</logic:equal>
	<logic:notEqual name="sfksq" value="-1">
		<logic:equal name="isKns" value="false">
			<script language="javascript">
			  $("buttonSave").disabled=true;
			  alert("����Ŀǰ���������������ܽ�������!");
			</script>
		</logic:equal>
	</logic:notEqual>
</html:html>
