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
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(titName,dkze,xfdks,zsfdks,shfdkz){
			var xh = document.getElementById('xh').value;
			var sfzh = 	document.getElementById('sfzh').value;
			var ssdh = 	document.getElementById('ssdh').value;
			var jtrjsr = document.getElementById('jtrjsr').value;
			var jtsr = 	document.getElementById('jtsr').value;
			var jtzz = 	document.getElementById('jtzz').value;
			var yzbm = 	document.getElementById('yzbm').value;
			var dh = 	document.getElementById('dh').value;
			var fqxm = document.getElementById('fqxm').value;
			var mqxm = document.getElementById('mqxm').value;
			var fqzy = document.getElementById('fqzy').value;
			var mqzy = document.getElementById('mqzy').value;
			var fqsfzh = document.getElementById('fqsfzh').value;
			var mqsfzh = document.getElementById('mqsfzh').value;
			
			if((xh == null) || (xh == "")){
				alert('ѧ�Ų���Ϊ��!');
				return false;
			}
			if((ssdh != null) && (ssdh != "") && (!isNumber(ssdh))){
				alert("����绰����Ϊ����!");
				return false;
			}
			if(dkze == ""){
				alert('�����ܶ��Ϊ��!');
				return false;
			}
			if(xfdks == ""){
				alert('ѧ�Ѵ���������Ϊ��!');
				return false;
			}
			if(zsfdks == ""){
				alert('ס�޷Ѵ���������Ϊ��!');
				return false;
			}
			if(shfdkz == ""){
				alert('����Ѵ���������Ϊ��!');
				return false;
			}
			if(!isNumber(dkze)){
				alert('�����ܶ����Ϊ������!');
				return false;
			}
			if(!isNumber(xfdks)){
				alert('ѧ�Ѵ���������Ϊ������!');
				return false;
			}
			if(!isNumber(zsfdks)){
				alert('ס�޷Ѵ���������Ϊ������!');
				return false;
			}
			if(!isNumber(shfdkz)){
				alert('����Ѵ���������Ϊ������!');
				return false;
			}
			if(dkze != (Math.round(xfdks)+Math.round(zsfdks)+Math.round(shfdkz))){
				alert('�����ܶ�͸�������֮�Ͳ����!');
				return false;
			}
			if(jtrjsr == ""){
				alert('��ͥ�˾����벻��Ϊ��!');
				return false;
			}
			if(jtsr == ""){
				alert('��ͥ�����벻��Ϊ��!');
				return false;
			}
			if(jtzz == ""){
				alert('��ͥסַ����Ϊ��!');
				return false;
			}
			if(yzbm == ""){
				alert('�������벻��Ϊ��!');
				return false;
			}
			if(dh == ""){
				alert('��ϵ�绰����Ϊ��!');
				return false;
			}
			if(fqxm == ""){
				alert('������������Ϊ��!');
				return false;
			}
			if(mqxm == ""){
				alert('ĸ����������Ϊ��!');
				return false;
			}
			if(fqzy == null){
				alert('����ְҵ����Ϊ��!');
				return false;
			}
			if(mqzy == ""){
				alert('ĸ��ְҵ����Ϊ��!');
				return false;
			}
			if(fqzy == ""){
				alert('�������֤�Ų���Ϊ��!');
				return false;
			}
			if(mqzy == ""){
				alert('ĸ�����֤�Ų���Ϊ��!');
				return false;
			}
			document.forms[0].action = "/xgxt/zxdksq.do?jxjlbType=zxdksq&doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		
		function toPrintOut(titName){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/zxdksqb.do";
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
		
		function xfhj(){
			var xfdks = document.getElementById('xfdks').value;
			var zsfdks = document.getElementById('zsfdks').value;
			var shfdks = document.getElementById('shfdks').value;
			if((xfdks == null) || (xfdks == "")){
				xfdks = "0";
			}
			if((zsfdks == null) || (zsfdks == "")){
				zsfdks = "0";
			}
			if((shfdks == null) || (shfdks == "")){
				shfdks = "0";
			}
			var dkze = Math.round(xfdks) + Math.round(zsfdks) + Math.round(shfdks);
			document.getElementById('dkze').value=dkze;
		}
	</script>
</head>

<body onload="loadPage()">
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ��ѧ��������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
		<html:form action="zxdksq.do" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="url" name="url"
				value="/zxdksq.do?jxjlbType=zxdksq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />


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
			<div class="xxk">
				<logic:notEmpty name="pages">
					<logic:iterate id="card" name="pages" scope="request">
						<ul>
							<li id="<bean:write name='card' property='en'/>l"
								class="xxk_off_l"></li>
							<li id="<bean:write name='card' property='en'/>m" onclick=""
								class="xxk_off_m">
								&nbsp;
								<bean:write name='card' property='cn' />
								&nbsp;
							</li>
							<li id="<bean:write name='card' property='en'/>r"
								class="xxk_off_r"></li>
						</ul>
					</logic:iterate>
				</logic:notEmpty>
			</div>

			<logic:notEmpty name="isSHGCKNS">
			<logic:equal name="isSHGCKNS" value="no">
			<table width="100%" border="1" class="tbstyle">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" width="16%" scope="col">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="34%" scope="col">
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
						<td align="center" width="16%" scope="col">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="34%" scope="col">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								readonly="readonly"
								value="<bean:write name='rs' property="xh" />" >
						</td>
					</logic:equal>
					<td width="16%" scope="col">
						<div align="center">
							����
						</div>
					</td>
					<td width="34%" scope="col">
						<div align="left">
							<input type="text" id="xm" name="xm"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xm" />" readonly="true">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xb" readonly="readonly" name="xb"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xb" />" readonly="true">
						</div>
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="csny" readonly="readonly" name="csny"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="csny" />"
								readonly="readonly">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xz" readonly="readonly" name="xz"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xz" />"
								readonly="readonly">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xl" readonly="readonly" name="xl"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xl" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							ѧУ����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xxmc" readonly="readonly" name="xxmc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xxmc" />" readonly="true">
						</div>
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="sfzh" name="sfzh"
								style="width:100%;heigh:100%" maxlength="18"
								value="<bean:write name='rs' property="sfzh" />" >
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xymc" readonly="readonly" name="xymc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xymc" />" readonly="true">
						</div>
					</td>
					<td>
						<div align="center">
							רҵ����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xmc" readonly="readonly" name="xmc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xmc" />" readonly="true">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							����绰
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="ssdh" name="ssdh"
								style="width:100%;heigh:100%" maxlength="12"
								value="<bean:write name='rs' property="ssdh" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="kh" readonly="readonly" name="kh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="kh" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�����ܽ��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="dkze" name="dkze" 
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="dkze" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>ѧ�Ѵ�����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xfdks" name="xfdks" onblur="xfhj();"
								style="width:100%;heigh:100%" maxlength="6"
								value="<bean:write name='rs' property="xfdks" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>ס�޷Ѵ�����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="zsfdks" name="zsfdks" onblur="xfhj();"
								style="width:100%;heigh:100%" readonly="readonly"
								value="0"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>����Ѵ�����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="shfdks" name="shfdks" onblur="xfhj();"
								style="width:100%;heigh:100%" readonly="readonly"
								value="0"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="dkqxkssj" name="dkqxkssj"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="dkqxkssj" />">
						</div>
					</td>
					<td>
						<div align="center">
							��
						</div>
					</td>
					<td>
						<div>
							<input type="text" readonly style="cursor:hand;width:80px"
								onclick="return showCalendar('dkqxjssj','y-mm-dd');"
								value="<bean:write name='rs' property="dkqxjssj" />"
								name="dkqxjssj" id="dkqxjssj" />
								<br />
								<font color="red">(ע�������Ϊ��ѧ��ʮ�꣬�糬��ʮ�꽫��ʮ����)</font>
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>��ͥ�˾�����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="jtrjsr" name="jtrjsr"
								style="width:100%;heigh:100%" maxlength="10"
								value="<bean:write name='rs' property="jtrjsr" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��ͥ������
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="jtsr" name="jtsr"
								style="width:100%;heigh:100%" maxlength="10"
								value="<bean:write name='rs' property="jtsr" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>��ͥסַ
						</div>
					</td>
					<td colspan="3">
						<div align="left">
							<input type="text" id="jtzz" name="jtzz"
								style="width:100%;heigh:100%" maxlength="50"
								value="<bean:write name='rs' property="jtzz" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>��������
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="yzbm" name="yzbm"
								style="width:100%;heigh:100%" maxlength="6"
								value="<bean:write name='rs' property="yzbm" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��ϵ�绰
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="dh" name="dh"
								style="width:100%;heigh:100%" maxlength="12"
								value="<bean:write name='rs' property="dh" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>��������
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="fqxm" name="fqxm"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="fqxm" />">
						</div>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>ĸ������
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="mqxm" name="mqxm"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="mqxm" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>����ְҵ
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="fqzy" name="fqzy"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="fqzy" />">
						</div>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>ĸ��ְҵ
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="mqzy" name="mqzy"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="mqzy" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>�������֤��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="fqsfzh" name="fqsfzh"
								style="width:100%;heigh:100%" maxlength="18"
								value="<bean:write name='rs' property="fqsfzh" />">
						</div>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>ĸ�����֤��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="mqsfzh" name="mqsfzh"
								style="width:100%;heigh:100%" maxlength="18"
								value="<bean:write name='rs' property="mqsfzh" />">
						</div>
					</td>
				</tr>
			</table>
			</logic:equal>
			<logic:equal value="is" name="isSHGCKNS">
			<table width="100%" border="1" class="tbstyle">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" width="16%" scope="col">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="34%" scope="col">
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
						<td align="center" width="16%" scope="col">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="34%" scope="col">
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
					<td width="34%" scope="col">
						<div align="left">
							<input type="text" id="xm" name="xm"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xm" />" readonly="true">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xb" readonly="readonly" name="xb"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xb" />" readonly="true">
						</div>
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="csny" readonly="readonly" name="csny"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="csny" />"
								readonly="readonly">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" readonly="readonly" id="xz" name="xz"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xz" />"
								readonly="readonly">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" readonly="readonly" id="xl" name="xl"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xl" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							ѧУ����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xxmc" readonly="readonly" name="xxmc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xxmc" />" readonly="true">
						</div>
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="sfzh" name="sfzh"
								style="width:100%;heigh:100%" maxlength="18"
								value="<bean:write name='rs' property="sfzh" />" >
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xymc" readonly="readonly" name="xymc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xymc" />" readonly="true">
						</div>
					</td>
					<td>
						<div align="center">
							רҵ����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xmc"  readonly="readonly" name="xmc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xmc" />" readonly="true">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							����绰
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="ssdh" name="ssdh"
								style="width:100%;heigh:100%" maxlength="12"
								value="<bean:write name='rs' property="ssdh" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td scope="row">
						<div align="center">
							����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="kh" readonly="readonly" name="kh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="kh" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�����ܽ��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="dkze" name="dkze"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="dkze" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>ѧ�Ѵ�����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xfdks" name="xfdks" onblur="xfhj();"
								style="width:100%;heigh:100%" maxlength="6"
								value="<bean:write name='rs' property="xfdks" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>ס�޷Ѵ�����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="zsfdks" name="zsfdks" onblur="xfhj();"
								style="width:100%;heigh:100%" readonly="readonly"
								value="0"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>����Ѵ�����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="shfdks" name="shfdks" onblur="xfhj();"
								style="width:100%;heigh:100%" readonly="readonly"
								value="0"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" readonly style="cursor:hand;width:80px"
								onclick="return showCalendar('dkqxkssj','y-mm-dd');"
								value="<bean:write name='rs' property="dkqxkssj" />"
								name="dkqxkssj" id="dkqxkssj" />
						</div>
					</td>
					<td>
						<div align="center">
							��
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" readonly style="cursor:hand;width:80px"
								onclick="return showCalendar('dkqxjssj','y-mm-dd');"
								value="<bean:write name='rs' property="dkqxjssj" />"
								name="dkqxjssj" id="dkqxjssj" />
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>��ͥ�˾�����
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="jtrjsr" maxlength="5" name="jtrjsr"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="jtrjsr" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��ͥ������
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="jtsr" maxlength="5" name="jtsr"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="jtsr" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>��ͥסַ
						</div>
					</td>
					<td colspan="3">
						<div align="left">
							<input type="text" id="jtzz" maxlength="50" name="jtzz"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="jtzz" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>��������
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" readonly="readonly" id="yzbm" name="yzbm"
								style="width:100%;heigh:100%" maxlength="6"
								value="<bean:write name='rs' property="yzbm" />">
						</div>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>��ϵ�绰
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="dh" name="dh"
								style="width:100%;heigh:100%" maxlength="12"
								value="<bean:write name='rs' property="dh" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>��������
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="fqxm" name="fqxm"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="fqxm" />">
						</div>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>ĸ������
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="mqxm" name="mqxm"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="mqxm" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>����ְҵ
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="fqzy" name="fqzy"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="fqzy" />">
						</div>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>ĸ��ְҵ
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="mqzy" name="mqzy"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="mqzy" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>�������֤��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="fqsfzh" name="fqsfzh"
								style="width:100%;heigh:100%" maxlength="18"
								value="<bean:write name='rs' property="fqsfzh" />">
						</div>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>ĸ�����֤��
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="mqsfzh" name="mqsfzh"
								style="width:100%;heigh:100%" maxlength="18"
								value="<bean:write name='rs' property="mqsfzh" />">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<font color="red">
							ע����*�ű�ǵ�Ϊ��������и�ĸ�����������������������ڸ��׻�ĸ�׵�������ְҵ�����֤������д��ĸ��ǰ�����
						</font>
					</td>
				</tr>
			</table>
			</logic:equal>.
			</logic:notEmpty>
			
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2"
					onClick="yz(document.getElementById('titName').value,document.getElementById('dkze').value,document.getElementById('xfdks').value,document.getElementById('zsfdks').value,document.getElementById('shfdks').value);">
					�ύ����
				</button>
				<button class="button2"
					onClick="toPrintOut(document.getElementById('titName').value);">
					��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
