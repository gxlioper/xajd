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
			var sqyy = document.getElementById('sqyy').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(sqyy != null){
	         	if(sqyy.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("����ԭ���ܴ���2000���ַ�");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/n05_xszz.do?method=wszxj2sqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/n05_xszz.do?method=wszxj2sqb";
			document.forms[0].submit();
		}
		function chengJe(){
			var xmje = document.getElementById('xmje').value;
			
			document.getElementById('sqje').value = xmje;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - <bean:write name="xmmc"/>
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<p align="center">
			<font color="red">Ŀǰ��������ʱ�䷶Χ�ڣ��ݲ��������룡</font>
		</p>
	</logic:equal>
	<html:form action="n05_xszz.do?method=wszxj2sq" method="post">
		<input type="hidden" id="url" name="url"
			value="/n05_xszz.do?method=wszxj2sq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc" />
		<input type="hidden" id="pkVal" name="pkVal"
			value="<bean:write name="rs" property="pkVal" />">
		<input type="hidden" id="zxjdm" name="zxjdm"
			value="<bean:write name="rs" property="zxjdm" />">
		<input type="hidden" id="xmdm" name="xmdm"
			value="<bean:write name="xmdm" />">
		<input type="hidden" id="xn" name="xn"
			value="<bean:write name="rs" property="xn" />">
		<input type="hidden" id="message" name="message" value="${message }">
			
		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
				var message = $("message").value;
				if(message != ""){
					alert(message);
				}else{
	         		alert("����ʧ�ܣ�");
	         	}
	         	</script>
			</logic:match>
		</logic:present>
		<logic:present name="isPASS">
			<logic:match value="is" name="isPASS">
				<script language="javascript">
	         			alert("��ͨ��ѧУ��ˣ��������룡");
	         		</script>
			</logic:match>
		</logic:present>
		<table class="tbstyle" width="90%">
			<tr>
				<logic:equal name="userOnLine" value="teacher" scope="session">
					<td align="center" colspan="2">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" colspan="3">
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
					<td align="left" colspan="3">
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
				<td colspan="3">
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
				<td colspan="3">
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
				<td colspan="3">
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
						��ͥ��ַ
					</div>
				</td>
				<td colspan="5">
					<input type="text" id="jtdz" name="jtdz" maxlength="100"
						style="width:100%;heigh:100%" 
						value="<bean:write name="rs" property="jtdz"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��������
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="yzbm" name="yzbm" maxlength="6"
						style="width:100%;heigh:100%" 
						value="<bean:write name="rs" property="yzbm"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td>
					<input type="text" id="lxdh" name="lxdh" maxlength="20"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="lxdh"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��ѧ���ú�������
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="bxnhdhzzz" name="bxnhdhzzz" maxlength="100"
						style="width:100%;heigh:100%" 
						value="<bean:write name="rs" property="bxnhdhzzz"/>">
				</td>
				<td>
					<div align="center">
						��������
					</div>
				</td>
				<td>
					<input type="text" id="bkks" name="bkks" maxlength="5"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="bkks"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��ѧ��༶�ۺϲ�������
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="sxnbjzhcppm" name="sxnbjzhcppm" maxlength="3"
						style="width:100%;heigh:100%" 
						value="<bean:write name="rs" property="sxnbjzhcppm"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<div align="center">
						��У�Ƿ��ܹ�����
					</div>
				</td>
				<td>
					<html:select name="rs" property="zxfscf">
						<html:option value=""></html:option>
						<html:option value="��">��</html:option>
						<html:option value="��">��</html:option>
					</html:select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��ͥ�˿�
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="jtrk" name="jtrk" maxlength="3"
						style="width:100%;heigh:100%" 
						value="<bean:write name="rs" property="jtrk"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<div align="center">
						��ͥ�˾�������
					</div>
				</td>
				<td>
					<input type="text" id="jtrjysr" name="jtrjysr" maxlength="6"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtrjysr"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td rowspan="6" width="4%">
					<div align="center">
						��<br />ͥ<br />��<br />Ҫ<br />��<br />Ա
					</div>
				</td>
				<td width="12%">
					<div align="center">
						����
					</div>
				</td>
				<td width="8%">
					<div align="center">
						����
					</div>
				</td>
				<td width="14%">
					<div align="center">
						�뱾�˹�ϵ
					</div>
				</td>
				<td width="12%">
					<div align="center">
						������(Ԫ)
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						ְҵ
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" id="jtcy1_xm" name="jtcy1_xm" maxlength="30"
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
				<td>
					<input type="text" id="jtcy1_sr" name="jtcy1_sr" maxlength="6"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy1_sr"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="2">
					<input type="text" id="jtcy1_zy" name="jtcy1_zy" maxlength="50"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy1_zy"/>">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" id="jtcy2_xm" name="jtcy2_xm" maxlength="30"
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
				<td>
					<input type="text" id="jtcy2_sr" name="jtcy2_sr" maxlength="6"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy2_sr"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="2">
					<input type="text" id="jtcy2_zy" name="jtcy2_zy" maxlength="50"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy2_zy"/>">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" id="jtcy3_xm" name="jtcy3_xm" maxlength="30"
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
				<td>
					<input type="text" id="jtcy3_sr" name="jtcy3_sr" maxlength="6"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy3_sr"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="2">
					<input type="text" id="jtcy3_zy" name="jtcy3_zy" maxlength="50"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy3_zy"/>">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" id="jtcy4_xm" name="jtcy4_xm" maxlength="30"
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
				<td>
					<input type="text" id="jtcy4_sr" name="jtcy4_sr" maxlength="6"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy4_sr"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="2">
					<input type="text" id="jtcy4_zy" name="jtcy4_zy" maxlength="50"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy4_zy"/>">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" id="jtcy5_xm" name="jtcy5_xm" maxlength="30"
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
				<td>
					<input type="text" id="jtcy5_sr" name="jtcy5_sr" maxlength="6"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy5_sr"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="2">
					<input type="text" id="jtcy5_zy" name="jtcy5_zy" maxlength="50"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtcy5_zy"/>">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						����ԭ��
					</div>
				</td>
				<td colspan="5">
					<html:textarea name="rs" property="sqyy" rows='10' style="width:100%" onblur="yzdx(this,'sqyy')"/>
					<input type="hidden" id="sqyy" name="sqyy" value="">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						������ѧ��
					</div>
				</td>
				<td colspan="3">
					<html:select property="xmje" onchange="chengJe()">
						<html:option value=""></html:option>
						<html:options collection="wszxjJeList" property="xmje"
									labelProperty="zxjmc" />
					</html:select>
					&nbsp;&nbsp;
					<input type="text" id="sqje" name="sqje" readonly="readonly"
						style="width:80px;heigh:100%"
						value="<bean:write name="rs" property="sqje"/>">��
				</td>
				<td>
					<div align="center">
						����ʱ��
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sqsj"/>
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
