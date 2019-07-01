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
	<%
			response.setHeader("Pragma", "no-cache");
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
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var sqje = document.getElementById('sqje').value;
			var fmhjhr1_sfzh = document.getElementById('fmhjhr1_sfzh').value;
			var fmhjhr2_sfzh = document.getElementById('fmhjhr2_sfzh').value;
			var sqdknx = document.getElementById('sqdknx').value;
			var zddknx = document.getElementById('zddknx').value;
			
			if(xh == null || xh == ""){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
	       	if((fmhjhr1_sfzh != null) && (fmhjhr1_sfzh != "") && (!checkSfzh(fmhjhr1_sfzh))){
				return false;
			}
			if((fmhjhr2_sfzh != null) && (fmhjhr2_sfzh != "") && (!checkSfzh(fmhjhr2_sfzh))){
				return false;
			}
			if(sqje == null || sqje == ""){
				document.getElementById('sqje').value = '4000';
			}
			if(Math.round(sqje) > 4000){
				alert("������ܴ���4000Ԫ��");
				return false;
			}
			if(sqdknx == null || sqdknx == ""){
				document.getElementById('sqdknx').value = zddknx;
			}
			if(Math.round(sqdknx) > zddknx){
				alert("�������޲��ܳ���" + zddknx + "�꣡");
				return false;
			}
			document.forms[0].action = "/xgxt/csmz_xszz.do?method=gjzxdksq&doType=save";
			document.forms[0].submit();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function je(){
			var sqje = document.getElementById('sqje').value;
			if(sqje == null || sqje == ""){
				document.getElementById('sqje').value = '0';
				sqje = 0;
			}
			if(Math.round(sqje) > 4000){
				alert("������ܴ���4000Ԫ��");
				document.getElementById('sqje').value = '4000';
			}
		}
		
		function nx(){
			var sqdknx = document.getElementById('sqdknx').value;
			var zddknx = document.getElementById('zddknx').value;
			if(sqdknx == null || sqdknx == ""){
				document.getElementById('sqdknx').value = zddknx;
			}
			if(Math.round(sqdknx) > zddknx){
				alert("�������޲��ܳ���" + zddknx + "�꣡");
				document.getElementById('sqdknx').value = zddknx;
			}
		}
		
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/csmz_xszz.do?method=gjzxdksqb";
			document.forms[0].submit();
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
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ������ѧ��������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
		<html:form action="csmz_xszz.do?method=gjzxdksq" method="post">
			<input type="hidden" id="url" name="url"
				value="/csmz_xszz.do?method=gjzxdksq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd" />">
			<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xyshsj" name="xyshsj"
				value="<bean:write name="rs" property="xyshsj" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">
			<input type="hidden" id="xxshsj" name="xxshsj"
				value="<bean:write name="rs" property="xxshsj" />">
			<input type="hidden" id="fdysh" name="fdysh"
				value="<bean:write name="rs" property="fdysh" />">
			<input type="hidden" id="fdyshsj" name="fdyshsj"
				value="<bean:write name="rs" property="fdyshsj" />">
			<input type="hidden" id="xxdh" name="xxdh"
				value="<bean:write name="rs" property="xxdh" />">
			<input type="hidden" id="xydh" name="xydh"
				value="<bean:write name="rs" property="xydh" />">
			<input type="hidden" id="fdydh" name="fdydh"
				value="<bean:write name="rs" property="fdydh" />">
			<input type="hidden" id="zddknx" name="zddknx"
				value="<bean:write name="rs" property="zddknx" />">
			<input type="hidden" id="sqjedx" name="sqjedx"
				value="<bean:write name="rs" property="sqjedx" />">


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
			<logic:present name="have">
				<logic:match value="have" name="have">
					<script language="javascript">
	         			alert("��ͨ����ˣ��������룡");
	         		</script>
				</logic:match>
			</logic:present>
			<logic:present name="kns">
				<logic:match value="no" name="kns">
					<script language="javascript">
	         			alert("�������������������룡");
	         		</script>
				</logic:match>
			</logic:present>

			<table class="tbstyle" width="90%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" width="16%">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" width="16%">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" colspan="2">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td width="16%" scope="col">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2" scope="col">
						<input type="text" id="xm" name="xm" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xm" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="xb" name="xb" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xb" />" readonly="true">
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="sfzh" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="sfzh" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="xymc" name="xymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xymc" />" readonly="true">
					</td>
					<td>
						<div align="center">
							רҵ
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zymc" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zymc" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�༶
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="bjmc" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="bjmc" />" readonly="true">
					</td>
					<td>
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="xz" name="xz" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xz" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ѧ����
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="rxny" name="rxny"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="rxny" />" readonly="true">
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="csrq" name="csrq"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="csrq" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="szss" name="szss"
							style="width:100%;heigh:100%" maxlength="20"
							value="<bean:write name='rs' property="szss" />">
					</td>
					<td>
						<div align="center">
							���
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<logic:present name="rs" property="lb">
								<logic:match value="ר" name="rs" property="lb">
									<input type="radio" name="lb" value="ר" checked>&nbsp;&nbsp;ר
							    	<input type="radio" name="lb" value="��">&nbsp;&nbsp;��
							    	<input type="radio" name="lb" value="˶">&nbsp;&nbsp;˶
							    	<input type="radio" name="lb" value="��">&nbsp;&nbsp;��
								</logic:match>
								<logic:match value="��" name="rs" property="lb">
									<input type="radio" name="lb" value="ר">&nbsp;&nbsp;ר
							    	<input type="radio" name="lb" value="��" checked>&nbsp;&nbsp;��
							    	<input type="radio" name="lb" value="˶">&nbsp;&nbsp;˶
							    	<input type="radio" name="lb" value="��">&nbsp;&nbsp;��
								</logic:match>
								<logic:match value="˶" name="rs" property="lb">
									<input type="radio" name="lb" value="ר">&nbsp;&nbsp;ר
							    	<input type="radio" name="lb" value="��">&nbsp;&nbsp;��
							    	<input type="radio" name="lb" value="˶" checked>&nbsp;&nbsp;˶
							    	<input type="radio" name="lb" value="��">&nbsp;&nbsp;��
								</logic:match>
								<logic:match value="��" name="rs" property="lb">
									<input type="radio" name="lb" value="ר">&nbsp;&nbsp;ר
							    	<input type="radio" name="lb" value="��">&nbsp;&nbsp;��
							    	<input type="radio" name="lb" value="˶">&nbsp;&nbsp;˶
							    	<input type="radio" name="lb" value="��" checked>&nbsp;&nbsp;��
								</logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="lb">
								<input type="radio" name="lb" value="ר" checked>&nbsp;&nbsp;ר
								<input type="radio" name="lb" value="��">&nbsp;&nbsp;��
								<input type="radio" name="lb" value="˶">&nbsp;&nbsp;˶
								<input type="radio" name="lb" value="��">&nbsp;&nbsp;��
							</logic:notPresent>
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							ѧ���绰
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="xsdh" name="xsdh"
							style="width:100%;heigh:100%" maxlength="15"
							value="<bean:write name='rs' property="xsdh" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							���������QQ��
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="dzyxhqqh" name="dzyxhqqh"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="dzyxhqqh" />">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ͥ������ϸ��ַ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtfk" name="jtfk"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="jtfk" />">
					</td>
					<td width="17%">
						<div align="center">
							�ʱ�
						</div>
					</td>
					<td width="17%">
						<input type="text" id="jtfkyb" name="jtfkyb"
							style="width:100%;heigh:100%" maxlength="6"
							value="<bean:write name='rs' property="jtfkyb" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ͥ����ϸ��ַ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtxxxzz" name="jtxxxzz"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="jtxxxzz" />">
					</td>
					<td width="17%">
						<div align="center">
							�ʱ�
						</div>
					</td>
					<td width="17%">
						<input type="text" id="jtxxxzzyb" name="jtxxxzzyb"
							style="width:100%;heigh:100%" maxlength="6"
							value="<bean:write name='rs' property="jtxxxzzyb" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row" rowspan="3">
						<div align="center">
							��ĸ��໤��
						</div>
					</td>
					<td width="17%">
						<div align="center">
							����
						</div>
					</td>
					<td width="17%">
						<div align="center">
							��ν
						</div>
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<div align="center">
							�̶��绰
						</div>
					</td>
					<td>
						<div align="center">
							�ֻ��绰
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="fmhjhr1_xm" name="fmhjhr1_xm"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="fmhjhr1_xm" />">
					</td>
					<td>
						<input type="text" id="fmhjhr1_cw" name="fmhjhr1_cw"
							style="width:100%;heigh:100%" maxlength="30"
							value="<bean:write name='rs' property="fmhjhr1_cw" />">
					</td>
					<td>
						<input type="text" id="fmhjhr1_sfzh" name="fmhjhr1_sfzh"
							style="width:100%;heigh:100%" maxlength="18"
							value="<bean:write name='rs' property="fmhjhr1_sfzh" />">
					</td>
					<td>
						<input type="text" id="fmhjhr1_gddh" name="fmhjhr1_gddh"
							style="width:100%;heigh:100%" maxlength="15"
							value="<bean:write name='rs' property="fmhjhr1_gddh" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="fmhjhr1_sjhm" name="fmhjhr1_sjhm"
							style="width:100%;heigh:100%" maxlength="15"
							value="<bean:write name='rs' property="fmhjhr1_sjhm" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="fmhjhr2_xm" name="fmhjhr2_xm"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="fmhjhr2_xm" />">
					</td>
					<td>
						<input type="text" id="fmhjhr2_cw" name="fmhjhr2_cw"
							style="width:100%;heigh:100%" maxlength="30"
							value="<bean:write name='rs' property="fmhjhr2_cw" />">
					</td>
					<td>
						<input type="text" id="fmhjhr2_sfzh" name="fmhjhr2_sfzh"
							style="width:100%;heigh:100%" maxlength="18"
							value="<bean:write name='rs' property="fmhjhr2_sfzh" />">
					</td>
					<td>
						<input type="text" id="fmhjhr2_gddh" name="fmhjhr2_gddh"
							style="width:100%;heigh:100%" maxlength="15"
							value="<bean:write name='rs' property="fmhjhr2_gddh" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="fmhjhr2_sjhm" name="fmhjhr2_sjhm"
							style="width:100%;heigh:100%" maxlength="15"
							value="<bean:write name='rs' property="fmhjhr2_sjhm" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							������
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="sqje" name="sqje" onblur="je();"
							style="width:100%;heigh:100%" maxlength="4"
							value="<bean:write name='rs' property="sqje" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="sqdknx" name="sqdknx" onblur="nx();"
							style="width:100%;heigh:100%" maxlength="1"
							value="<bean:write name='rs' property="sqdknx" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2" onClick="yz();">
					�ύ����
				</button>
				<button class="button2" onClick="toPrintOut();">
					��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
