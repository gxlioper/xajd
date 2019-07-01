<%@ page language="java" pageEncoding="GB2312"
	contentType="text/html;charset=GBK"%>

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

	<meta http-equiv="pragma" content="No-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%
			response.setHeader("Prama", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var qsdh = document.getElementById('qsdh').value;
			var yzbm = document.getElementById('yzbm').value;
			var sfzh = document.getElementById('sfzh').value;
			var jtcy1_ysr = document.getElementById('jtcy1_ysr').value;
			var jtcy2_ysr = document.getElementById('jtcy2_ysr').value;
			var jtcy3_ysr = document.getElementById('jtcy3_ysr').value;
			var jtcy4_ysr = document.getElementById('jtcy4_ysr').value;
			var jtcy5_ysr = document.getElementById('jtcy5_ysr').value;
			var jttgje = document.getElementById('jttgje').value;
			var zxje = document.getElementById('zxje').value;
			var jxje = document.getElementById('jxje').value;
			var qgzxje = document.getElementById('qgzxje').value;
			var xnwxdkje = document.getElementById('xnwxdkje').value;
			var qtsrje = document.getElementById('qtsrje').value;
			var zxdkje = document.getElementById('zxdkje').value;
			var yffzxdkje = document.getElementById('yffzxdkje').value;
			var zzff1qsje = document.getElementById('zzff1qsje').value;
			var zzff1jsje = document.getElementById('zzff1jsje').value;
			var sqzzly = document.getElementById('sqzzly').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((sfzh != null) && (sfzh != "") && (!checkSfzh(sfzh))){
				return false;
			}
			if(sqzzly != null){
	         	if(sqzzly.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("�����������ɲ��ܴ���100���ַ�");
	          		 return false;
	       		 }
			}
			
			document.forms[0].action = "/xgxt/xndxjsq.do?doType=save";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(titName){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/bzsqb.do";
			document.forms[0].submit();
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		
		function checkSfzh(sfzh) {
   			sfzh=sfzh.toLowerCase()
			var OldID;
			if(sfzh.length == 15){
				OldID = sfzh;
				return true;
			}else if(sfzh.length == 18){
				OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
			}else{
				alert("��������ȷ�����֤���룡","",function(){
					obj.select();
					obj.focus();
				});
				return false;
			}
			var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
			var A = new Array("1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2");
			var i, j, S;
			var NewID, ID, strNF;
			NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
			S = 0;
			for( i = 0; i <= 16; i++){
				j = NewID.substring(i, i+1) * W[i];
				S = S + j;
			}
			S = S % 11;
			if(sfzh != NewID + A[S]){
				alert("��������ȷ�����֤���룡","",function(){
					obj.select();
					obj.focus();
				});
				return false;
			}
			return true;
		}
	</script>
</head>

<body>
	<html:form action="xndxjsq.do" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:write name="tips" scope="request" />
			</div>
		</div>
		<input type="hidden" id="title" name="title" value="xndxj" />
		<input type="hidden" id="url" name="url" value="/xszz/xndxjSq.jsp" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-ssbh-qsdh-xz-xymc-bjmc" />
		<input type="hidden" id="pk" name="pk" value="xh||bzdm||nd" />
		<input type="hidden" id="realTable" name="realTable" value="xsbzb" />
		<input type="hidden" id="bzlb" name="bzlb" value="xndxj" />
		<logic:present name="ok">
			<logic:equal name="ok" value="true">
				<script type="text/javascript">
	      			alert("����ɹ���");
	      		</script>
			</logic:equal>
			<logic:equal name="ok" value="false">
				<script type="text/javascript">
	      			alert("����ʧ�ܣ�");
	      		</script>
			</logic:equal>
		</logic:present>
		<logic:present name="isPASS">
			<logic:equal name="isPASS" value="is">
				<script type="text/javascript">
	      			alert("��ͨ����ˣ��������룡");
	      		</script>
			</logic:equal>
		</logic:present>
		<logic:equal value="no" name="sfksq">
			<div align="center">
				<p>
					��ǰ��������ʱ��
				</p>
			</div>
		</logic:equal>
			<table width="100%" class="tbstyle">
				<tr>
					<td height="31" colspan="2">
						<font color="red">*</font>ѧ��
					</td>
					<logic:equal value="student" name="userOnLine" scope="session">
						<td width="26%">
							<input name="xh" id="xh" type="text"
								value="<bean:write  name="rs" property="xh"/>" width="100%"
								readonly="readonly">
						</td>
					</logic:equal>
					<logic:notEqual value="student" name="userOnLine" scope="session">
						<td width="26%">
							<html:text name="rs" property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:notEqual>
					<td height="31" colspan="2">
						<font color="red">*</font>����
					</td>
					<td width="28%">
						<input name="xm" id="xm" type="text"
							value="<bean:write  name="rs" property="xm"/>" width="100%"
							readonly="readonly">
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						�Ա�
					</td>
					<td height="31">
						<input name="xb" id="xb" readonly="readonly" type="text"
							value="<bean:write  name="rs" property="xb"/>" width="100%">
					</td>
					<td height="31" colspan="2">
						����
					</td>
					<td height="31">
						<input name="mzmc" id="mzmc" maxlength="10" type="text"
							width="100%" value="<bean:write  name="rs" property="mzmc"/>">
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						��ҵʱ��
					</td>
					<td height="31">
						<input type="text" readonly style="cursor:hand;width:100%"
							onclick="return showCalendar('bysj','y-mm-dd');"
							value="<bean:write name='rs' property="bysj" />" name="bysj"
							id="bysj" />
					</td>
					<td height="31" colspan="2">
						רҵ�꼶
					</td>
					<td height="31">
						<input type="text" width="100%" maxlength="10" name="zynj"
							id="zynj" value="<bean:write  name="rs" property="zynj"/>">
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						���Һ���
					</td>
					<td height="31">
						<input type="text" width="100%" name="qsh" maxlength="10" id="qsh"
							value="<bean:write name="rs" property="qsh"/>">
					</td>
					<td height="31" colspan="2">
						���ҵ绰
					</td>
					<td height="31">
						<input type="text" width="100%" name="qsdh" maxlength="12"
							id="qsdh" value="<bean:write name="rs" property="qsdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						��ͥ��ַ
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtdz" maxlength="50"
							id="jtdz" value="<bean:write  name="rs" property="jtdz"/>">
					</td>
					<td height="31" colspan="2">
						�ʱ�
					</td>
					<td height="31">
						<input type="text" width="100%" name="yzbm" maxlength="6"
							id="yzbm" value="<bean:write  name="rs" property="yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						���֤����
					</td>
					<td height="31">
						<input type="text" width="100%" name="sfzh" maxlength="18"
							id="sfzh" value="<bean:write  name="rs" property="sfzh"/>">
					</td>
					<td height="31" colspan="2">
						&nbsp;
					</td>
					<td height="31">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						��ͥ��Ա1����
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy1_xm" maxlength="50"
							id="jtcy1_xm"
							value="<bean:write  name="rs" property="jtcy1_xm"/>">
					</td>
					<td height="31" colspan="2">
						���ͥ��Ա1��ϵ
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy1_gx" maxlength="50"
							id="jtcy1_gx"
							value="<bean:write  name="rs" property="jtcy1_gx"/>">
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						��ͥ��Ա1������λ
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy1_gzdw" maxlength="100"
							id="jtcy1_gzdw"
							value="<bean:write  name="rs" property="jtcy1_gzdw"/>">
					</td>
					<td height="31" colspan="2">
						��ͥ��Ա1������
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy1_ysr" maxlength="6"
							id="jtcy1_ysr"
							value="<bean:write  name="rs" property="jtcy1_ysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						��ͥ��Ա2����
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy2_xm" maxlength="50"
							id="jtcy2_xm"
							value="<bean:write  name="rs" property="jtcy2_xm"/>">
					</td>
					<td height="31" colspan="2">
						���ͥ��Ա2��ϵ
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy2_gx" maxlength="50"
							id="jtcy2_xm"
							value="<bean:write  name="rs" property="jtcy2_xm"/>">
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						��ͥ��Ա2������λ
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy2_gzdw" maxlength="100"
							id="jtcy2_gzdw"
							value="<bean:write  name="rs" property="jtcy2_gzdw"/>">
					</td>
					<td height="31" colspan="2">
						��ͥ��Ա2������
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy2_ysr" maxlength="6"
							id="jtcy2_ysr"
							value="<bean:write  name="rs" property="jtcy2_ysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						��ͥ��Ա3����
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy3_xm" maxlength="50"
							id="jtcy3_xm"
							value="<bean:write  name="rs" property="jtcy3_xm"/>">
					</td>
					<td height="31" colspan="2">
						���ͥ��Ա3��ϵ
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy3_gx" maxlength="50"
							id="jtcy3_gx"
							value="<bean:write  name="rs" property="jtcy3_gx"/>">
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						��ͥ��Ա3������λ
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy3_gzdw" maxlength="100"
							id="jtcy3_gzdw"
							value="<bean:write  name="rs" property="jtcy3_gzdw"/>">
					</td>
					<td height="31" colspan="2">
						��ͥ��Ա3������
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy3_ysr" maxlength="6"
							id="jtcy3_ysr"
							value="<bean:write  name="rs" property="jtcy3_ysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						��ͥ��Ա4����
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy4_xm" maxlength="50"
							id="jtcy4_xm"
							value="<bean:write  name="rs" property="jtcy4_xm"/>">
					</td>
					<td height="31" colspan="2">
						���ͥ��Ա4��ϵ
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy4_gx" maxlength="50"
							id="jtcy4_gx"
							value="<bean:write  name="rs" property="jtcy4_gx"/>">
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						��ͥ��Ա4������λ
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy4_gzdw" maxlength="100"
							id="jtcy4_gzdw"
							value="<bean:write  name="rs" property="jtcy4_gzdw"/>">
					</td>
					<td height="31" colspan="2">
						��ͥ��Ա4������
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy4_ysr" maxlength="6"
							id="jtcy4_ysr"
							value="<bean:write  name="rs" property="jtcy4_ysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						��ͥ��Ա5����
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy5_xm" maxlength="50"
							id="jtcy5_xm"
							value="<bean:write  name="rs" property="jtcy5_xm"/>">
					</td>
					<td height="31" colspan="2">
						���ͥ��Ա5��ϵ
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy4_gx" maxlength="50"
							id="jtcy4_gx"
							value="<bean:write  name="rs" property="jtcy4_gx"/>">
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						��ͥ��Ա5������λ
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy5_gzdw" maxlength="100"
							id="jtcy5_gzdw"
							value="<bean:write  name="rs" property="jtcy5_gzdw"/>">
					</td>
					<td height="31" colspan="2">
						��ͥ��Ա5������
					</td>
					<td height="31">
						<input type="text" width="100%" name="jtcy5_ysr" maxlength="6"
							id="jtcy5_ysr"
							value="<bean:write  name="rs" property="jtcy5_ysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						��ѧ���ͥ�ṩ
					</td>
					<td height="31">
						<input type="text" width="100%" maxlength="6" name="jttgje"
							id="jttgje" value="<bean:write  name="rs" property="jttgje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						Ԫ /��
					</td>
					<td height="31" colspan="2">
						��ѧ����ѧ��
					</td>
					<td height="31">
						<input type="text" width="100%" name="zxje" maxlength="6"
							id="zxje" value="<bean:write  name="rs" property="zxje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						Ԫ
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						��ѧ�꽱ѧ��
					</td>
					<td height="31">
						<input type="text" width="100%" maxlength="6" name="jxje"
							id="jxje" value="<bean:write  name="rs" property="jxje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						Ԫ
					</td>
					<td height="31" colspan="2">
						��ѧ���ڹ���ѧ����
					</td>
					<td height="31">
						<input type="text" width="100%" maxlength="6" name="qgzxje"
							id="qgzxje" value="<bean:write  name="rs" property="qgzxje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						Ԫ
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						��ѧ��У����Ϣ��ѧ��
					</td>
					<td height="31">
						<input type="text" width="100%" maxlength="6" name="xnwxdkje"
							id="xnwxdkje"
							value="<bean:write  name="rs" property="xnwxdkje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						Ԫ
					</td>
					<td height="31" colspan="2">
						��ѧ����������
					</td>
					<td height="31">
						<input type="text" width="100%" maxlength="6" name="qtsrje"
							id="qtsrje" value="<bean:write  name="rs" property="qtsrje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						Ԫ
					</td>
				</tr>
				<tr>
					<td width="9%" height="31" rowspan="2">
						��ѧ����
					</td>
					<td width="11%">
						������
					</td>
					<td width="26%">
						<input type="text" width="100%" maxlength="6" name="zxdkje"
							id="zxdkje" value="<bean:write  name="rs" property="zxdkje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						Ԫ
					</td>
					<td width="12%" rowspan="2">
						��ѧ����
					</td>
					<td width="14%">
						�ѷ��Ž��
					</td>
					<td width="28%" height="31">
						<input type="text" maxlength="6" width="100%" name="yffzxdkje"
							id="yffzxdkje"
							value="<bean:write name="rs" property="yffzxdkje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						Ԫ
					</td>
				</tr>
				<tr>
					<td width="11%">
						ʱ��
					</td>
					<td>
						<input type="text" readonly style="cursor:hand;width:100%"
							onclick="return showCalendar('zxdksj','y-mm-dd');"
							value="<bean:write name='rs' property="zxdksj" />" name="zxdksj"
							id="zxdksj" />
					</td>
					<td height="31">
						ʱ��
					</td>
					<td height="31">
						<input type="text" readonly style="cursor:hand;width:100%"
							onclick="return showCalendar('yffzxdksj','y-mm-dd');"
							value="<bean:write name='rs' property="yffzxdksj" />"
							name="yffzxdksj" id="yffzxdksj" />
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						������������
					</td>
					<td height="31" colspan="4">
						<html:textarea name="rs" property="sqzzly" rows='5' style="width:100%" onblur="yzdx(this,'sqzzly')"/>
						<input type="hidden" id="sqzzly" name="sqzzly" value="">
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						�������ͣ�
					</td>
					<td height="31" colspan="4">
						&nbsp;&nbsp;&nbsp; У�ڴ�ѧ��
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						��һ־Ը
					</td>
					<td height="31" colspan="2">
						��ʼ���
						<input type="text" width="100%" name="zzff1qsje" maxlength="5"
							id="zzff1qsje"
							value="<bean:write name="rs" property="zzff1qsje" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						Ԫ
					</td>
					<td height="31" colspan="2">
						�������
						<input type="text" width="100%" name="zzff1jsje" maxlength="5"
							id="zzff1jsje"
							value="<bean:write name="rs" property="zzff1jsje" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						Ԫ
					</td>
				</tr>
				<%---<tr>
			    <td height="31" colspan="2">�ڶ�־Ը</td>
			    <td height="31" colspan="2">��ʼ���
			        <input type="text" width="100%" name="yffzxdksj" id="yffzxdksj">
			        Ԫ </td>
			    <td height="31" colspan="2">�������
			        <input type="text" width="100%" name="yffzxjksj" id="yffzxjksj">
			        Ԫ </td>
			  </tr>--%>
			</table>
			<br>
			<font color="red">����ʱ�����Ϣ���ڴ�ӡ�ı�����д</font>

	<logic:equal value="yes" name="sfksq">
		<center>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;width:100%">
				<button class="button2" onclick="yz();" style="width:80px">
					�ύ����
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2"
					onclick="toPrintOut(document.forms[0].bzlb.value);"
					style="width:80px">
					�����ӡ
				</button>
			</div>
		</center>
	</logic:equal>
	</html:form>

</body>
<script language="javascript">

if(!window.dialogArguments && document.getElementById("btn") ){
	document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
	document.getElementById("btn").style.width = "96%";
	window.setInterval("initBTNTool('btn')",1);
}
</script>
</html:html>
