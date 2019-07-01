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
	<script language="javascript">
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		function yz1(titName,sr1,sr2,sr3,sr4,sr5){
			var xh = document.getElementById('xh').value;
			var lxdh = document.getElementById('lxdh').value;
			var sqly = document.getElementById('sqly').value;
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((lxdh != null) && (lxdh != "") && (!isNumber(lxdh))){
				alert("��ϵ�绰����Ϊ����!");
				return false;
			}
			if((sr1 != "") && (!isNumber(sr1))){
				alert("��ͥ��Ա1����������������!");
				return false;
			}
			if((sr2 != "") && (!isNumber(sr2))){
				alert("��ͥ��Ա2����������������2!");
				return false;
			}
			if((sr3 != "") && (!isNumber(sr3))){
				alert("��ͥ��Ա3����������������3!");
				return false;
			}
			if((sr4 != "") && (!isNumber(sr4))){
				alert("��ͥ��Ա4����������������4!");
				return false;
			}
			if((sr5 != "") && (!isNumber(sr5))){
				alert("��ͥ��Ա5����������������5!");
				return false;
			}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("�������ɲ��ܴ���1000���ַ�");
	          		 return false;
	       		 }
			}
			document.forms[0].action = "/xgxt/lstdsq.do?jxjlbType=lstdsq&doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function yz2(titName){
			var xh = document.getElementById('xh').value;
			var sfzh = document.getElementById('sfzh').value;
			var jtrks = document.getElementById('jtrks').value;
			var jtyzbm = document.getElementById('jtyzbm').value;
			var jtlxdh = document.getElementById('jtlxdh').value;
			var jtcy1_nl = document.getElementById('jtcy1_nl').value;
			var jtcy1_nsr = document.getElementById('jtcy1_nsr').value;
			var jtcy2_nl = document.getElementById('jtcy2_nl').value;
			var jtcy2_nsr = document.getElementById('jtcy2_nsr').value;
			var jtcy3_nl = document.getElementById('jtcy3_nl').value;
			var jtcy3_nsr = document.getElementById('jtcy3_nsr').value;
			var jtcy4_nl = document.getElementById('jtcy4_nl').value;
			var jtcy4_nsr = document.getElementById('jtcy4_nsr').value;
			var jtcy5_nl = document.getElementById('jtcy5_nl').value;
			var jtcy5_nsr = document.getElementById('jtcy5_nsr').value;
			var jtrjnsr = document.getElementById('jtrjnsr').value;
			var mzbmyzbm = document.getElementById('mzbmyzbm').value;
			var mzbmlxdh = document.getElementById('mzbmlxdh').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(!checkSfzh(sfzh)){
				return false;
			}
			if((jtrks != "") && (!isNumber(jtrks))){
				alert("��ͥ�˿�������Ϊ����!");
				return false;
			}
			if((jtyzbm != "") && (!isNumber(jtyzbm))){
				alert("��ͥ�����������Ϊ����!");
				return false;
			}
			if((jtlxdh != "") && (!isNumber(jtlxdh))){
				alert("��ͥ��ϵ�绰����Ϊ����!");
				return false;
			}
			if((jtcy1_nl != "") && (!isNumber(jtcy1_nl))){
				alert("��ͥ��Ա1�������Ϊ����!");
				return false;
			}
			if((jtcy1_nsr != "") && (!isNumber(jtcy1_nsr))){
				alert("��ͥ��Ա1���������Ϊ����!");
				return false;
			}
			if((jtcy2_nl != "") && (!isNumber(jtcy2_nl))){
				alert("��ͥ��Ա2�������Ϊ����!");
				return false;
			}
			if((jtcy2_nsr != "") && (!isNumber(jtcy2_nsr))){
				alert("��ͥ��Ա2���������Ϊ����!");
				return false;
			}
			if((jtcy3_nl != "") && (!isNumber(jtcy3_nl))){
				alert("��ͥ��Ա3�������Ϊ����!");
				return false;
			}
			if((jtcy3_nsr != "") && (!isNumber(jtcy3_nsr))){
				alert("��ͥ��Ա3���������Ϊ����!");
				return false;
			}
			if((jtcy4_nl != "") && (!isNumber(jtcy4_nl))){
				alert("��ͥ��Ա4�������Ϊ����!");
				return false;
			}
			if((jtcy4_nsr != "") && (!isNumber(jtcy4_nsr))){
				alert("��ͥ��Ա4���������Ϊ����!");
				return false;
			}
			if((jtcy5_nl != "") && (!isNumber(jtcy5_nl))){
				alert("��ͥ��Ա5�������Ϊ����!");
				return false;
			}
			if((jtcy5_nsr != "") && (!isNumber(jtcy5_nsr))){
				alert("��ͥ��Ա5���������Ϊ����!");
				return false;
			}
			if((jtrjnsr != "") && (!isNumber(jtrjnsr))){
				alert("��ͥ�˾����������Ϊ����!");
				return false;
			}
			if((mzbmyzbm != "") && (!isNumber(mzbmyzbm))){
				alert("�������������������Ϊ����!");
				return false;
			}
			if((mzbmlxdh != "") && (!isNumber(mzbmlxdh))){
				alert("����������ϵ�绰����Ϊ����!");
				return false;
			}
			document.forms[0].action = "/xgxt/lstdsq.do?jxjlbType=lstdsq&doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		
		function toPrintOut(titName){//�����Ӧ�Ĵ�ӡҳ��
			if(titName == "xnmz_lstd" ){
				document.forms[0].action = "/xgxt/xnmz_lstd.do";
				document.forms[0].submit();
			}
			if(titName == "lstdsq" ){
				document.forms[0].action = "/xgxt/lstdsqb.do";
				document.forms[0].submit();
			}
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

<body onload="loadPage()">
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ��ɫͨ������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
		<html:form action="lstdsq.do" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="url" name="url"
				value="/lstdsq.do?jxjlbType=lstdsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj"/>">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj"/>">


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
			<logic:present name="kns">
				<logic:match value="no" name="kns">
					<script language="javascript">
	         			alert("�������������������룡");
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

			<logic:equal name="isXNMZ" value="is">
				<table class="tbstyle">
					<tr>
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right" colspan="3">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" colspan="2">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)"
									readonly="true" />
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right" colspan="3">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" colspan="2">
								<input type="text" id="xh" name="xh"
									style="width:100%;heigh:100%"
									value="<bean:write name='rs' property="xh" />" readonly="true">
							</td>
						</logic:equal>
						<td width="16%">
							<div align="right">
								������
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="xm" name="xm"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xm"/>"
								readonly="readonly">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								ѧУ���ƣ�
							</div>
						</td>
						<td colspan="2" align="left">
							<input type="text" id="xxmc" name="xxmc" readonly="readonly"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xxmc"/>">
						</td>
						<td>
							<div align="right">
								<bean:message key="lable.xsgzyxpzxy" />���ƣ�
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="xymc" readonly="readonly" name="xymc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xymc"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								רҵ���ƣ�
							</div>
						</td>
						<td colspan="2" align="left">
							<input type="text" id="xmc" name="xmc" readonly="readonly"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xmc"/>">
						</td>
						<td>
							<div align="right">
								�꼶��
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="nj" readonly="readonly" name="nj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="nj"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								�Ա�
							</div>
						</td>
						<td colspan="2" align="left">
							<input type="text" id="xb" name="xb" readonly="readonly"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xb"/>">
						</td>
						<td>
							<div align="right">
								�������£�
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('csny','y-mm');"
								value="<bean:write name='rs' property="csny" />" name="csny"
								id="csny" />
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								�������ƣ�
							</div>
						</td>
						<td colspan="2" align="left">
							<input type="text" id="mzmc" name="mzmc"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name="rs" property="mzmc"/>">
						</td>
						<td>
							<div align="right">
								���֤�ţ�
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="sfzh" maxlength="18" name="sfzh"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="sfzh"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								������ò��
							</div>
						</td>
						<td colspan="2" align="left">
							<input type="text" id="zzmm" name="zzmm" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zzmm"/>">
						</td>
						<td>
							<div align="right">
								��ѧǰ���ڣ�
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="rxqhk" name="rxqhk" maxlength="40"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="rxqhk"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								��ҵѧУ��
							</div>
						</td>
						<td colspan="2" align="left">
							<input type="text" id="byxx" name="byxx" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="byxx"/>">
						</td>
						<td>
							<div align="right">
								�����س���
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="grtc" name="grtc" maxlength="60"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="grtc"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								�Ƿ�²У�
							</div>
						</td>
						<td colspan="2" align="left">
							<logic:present name="rs" property="sfgc">
								<logic:match value="��" name="rs" property="sfgc">
									<input type="radio" name="sfgc" value="��" checked>&nbsp;&nbsp;��
							         	<input type="radio" name="sfgc" value="��">&nbsp;&nbsp;��
							         </logic:match>
								<logic:match value="��" name="rs" property="sfgc">
									<input type="radio" name="sfgc" value="��">&nbsp;&nbsp;��
							         	<input type="radio" name="sfgc" value="��" checked>&nbsp;&nbsp;��
							         </logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="sfgc">
								<input type="radio" name="sfgc" value="��">&nbsp;&nbsp;��
							         <input type="radio" name="sfgc" value="��" checked>&nbsp;&nbsp;��
						         </logic:notPresent>
						</td>
						<td>
							<div align="right">
								�Ƿ��ף�
							</div>
						</td>
						<td colspan="3" align="left">
							<logic:present name="rs" property="sfdq">
								<logic:match value="��" name="rs" property="sfdq">
									<input type="radio" name="sfdq" value="��" checked>&nbsp;&nbsp;��
							         	<input type="radio" name="sfdq" value="��">&nbsp;&nbsp;��
							         </logic:match>
								<logic:match value="��" name="rs" property="sfdq">
									<input type="radio" name="sfdq" value="��">&nbsp;&nbsp;��
							         	<input type="radio" name="sfdq" value="��" checked>&nbsp;&nbsp;��
							         </logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="sfdq">
								<input type="radio" name="sfdq" value="��">&nbsp;&nbsp;��
							         <input type="radio" name="sfdq" value="��" checked>&nbsp;&nbsp;��
						         </logic:notPresent>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								�Ƿ���ʿ��Ů��
							</div>
						</td>
						<td colspan="2" align="left">
							<logic:present name="rs" property="sflszn">
								<logic:match value="��" name="rs" property="sflszn">
									<input type="radio" name="sflszn" value="��" checked>&nbsp;&nbsp;��
							         	<input type="radio" name="sflszn" value="��">&nbsp;&nbsp;��
							         </logic:match>
								<logic:match value="��" name="rs" property="sflszn">
									<input type="radio" name="sflszn" value="��">&nbsp;&nbsp;��
							         	<input type="radio" name="sflszn" value="��" checked>&nbsp;&nbsp;��
							         </logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="sflszn">
								<input type="radio" name="sflszn" value="��">&nbsp;&nbsp;��
							         <input type="radio" name="sflszn" value="��" checked>&nbsp;&nbsp;��
						         </logic:notPresent>
						</td>
						<td>
							<div align="right">
								��ͥ�˿�����
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="jtrks" name="jtrks" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtrks"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								��ͥ�������룺
							</div>
						</td>
						<td colspan="2" align="left">
							<input type="text" id="jtyzbm" name="jtyzbm" maxlength="6"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtyzbm"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td>
							<div align="right">
								��ͥ��ϵ�绰��
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="jtlxdh" name="jtlxdh" maxlength="15"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtlxdh"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								��ͥ��ϸͨѶ��ַ��
							</div>
						</td>
						<td colspan="6" align="left">
							<input type="text" id="jtxxtxdz" name="jtxxtxdz" maxlength="100"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtxxtxdz"/>">
						</td>
					</tr>
					<tr>
						<td width="4%" rowspan="6">
							<div align="center">
								��
								<br>
								ͥ
								<br>
								��
								<br>
								Ա
								<br>
								��
								<br>
								��
							</div>
						</td>
						<td width="8%">
							<div align="center">
								����
							</div>
						</td>
						<td width="8%">
							<div align="center">
								����
							</div>
						</td>
						<td width="9%">
							<div align="center">
								��ѧ����ϵ
							</div>
						</td>
						<td colspan="2">
							<div align="center">
								������ѧϰ��λ
							</div>
						</td>
						<td width="12%">
							<div align="center">
								ְҵ
							</div>
						</td>
						<td width="11%">
							<div align="center">
								������(Ԫ)
							</div>
						</td>
						<td width="11%">
							<div align="center">
								����״��
							</div>
						</td>
					</tr>
					<tr>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy1_xm" name="jtcy1_xm" maxlength="40"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy1_xm"/>">
							</div>
						</td>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy1_nl" name="jtcy1_nl" maxlength="5"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy1_nl"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy1_gx" name="jtcy1_gx" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy1_gx"/>">
							</div>
						</td>
						<td colspan="2">
							<div align="center">
								<input type="text" id="jtcy1_gzdw" name="jtcy1_gzdw"
									maxlength="100" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy1_gzdw"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy1_zy" name="jtcy1_zy" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy1_zy"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy1_nsr" name="jtcy1_nsr"
									maxlength="10" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy1_nsr"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy1_jkzk" name="jtcy1_jkzk"
									maxlength="20" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy1_jkzk"/>">
							</div>
						</td>
					</tr>
					<tr>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy2_xm" name="jtcy2_xm" maxlength="40"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy2_xm"/>">
							</div>
						</td>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy2_nl" name="jtcy2_nl" maxlength="5"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy2_nl"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy2_gx" name="jtcy2_gx" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy2_gx"/>">
							</div>
						</td>
						<td colspan="2">
							<div align="center">
								<input type="text" id="jtcy2_gzdw" name="jtcy2_gzdw"
									maxlength="100" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy2_gzdw"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy2_zy" name="jtcy2_zy" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy2_zy"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy2_nsr" name="jtcy2_nsr"
									maxlength="10" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy2_nsr"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy2_jkzk" name="jtcy2_jkzk"
									maxlength="20" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy2_jkzk"/>">
							</div>
						</td>
					</tr>
					<tr>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy3_xm" name="jtcy3_xm" maxlength="40"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy3_xm"/>">
							</div>
						</td>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy3_nl" name="jtcy3_nl" maxlength="5"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy3_nl"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy3_gx" name="jtcy3_gx" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy3_gx"/>">
							</div>
						</td>
						<td colspan="2">
							<div align="center">
								<input type="text" id="jtcy3_gzdw" name="jtcy3_gzdw"
									maxlength="100" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy3_gzdw"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy3_zy" name="jtcy3_zy" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy3_zy"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy3_nsr" name="jtcy3_nsr"
									maxlength="10" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy3_nsr"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy3_jkzk" name="jtcy3_jkzk"
									maxlength="20" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy3_jkzk"/>">
							</div>
						</td>
					</tr>
					<tr>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy4_xm" name="jtcy4_xm" maxlength="40"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy4_xm"/>">
							</div>
						</td>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy4_nl" name="jtcy4_nl" maxlength="5"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy4_nl"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy4_gx" name="jtcy4_gx" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy4_gx"/>">
							</div>
						</td>
						<td colspan="2">
							<div align="center">
								<input type="text" id="jtcy4_gzdw" name="jtcy4_gzdw"
									maxlength="100" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy4_gzdw"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy4_zy" name="jtcy4_zy" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy4_zy"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy4_nsr" name="jtcy4_nsr"
									maxlength="10" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy4_nsr"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy4_jkzk" name="jtcy4_jkzk"
									maxlength="20" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy4_jkzk"/>">
							</div>
						</td>
					</tr>
					<tr>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy5_xm" name="jtcy5_xm" maxlength="40"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy5_xm"/>">
							</div>
						</td>
						<td width="6%">
							<div align="center">
								<input type="text" id="jtcy5_nl" name="jtcy5_nl" maxlength="5"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy5_nl"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy5_gx" name="jtcy5_gx" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy5_gx"/>">
							</div>
						</td>
						<td colspan="2">
							<div align="center">
								<input type="text" id="jtcy5_gzdw" name="jtcy5_gzdw"
									maxlength="100" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy5_gzdw"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy5_zy" name="jtcy5_zy" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy5_zy"/>">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy5_nsr" name="jtcy5_nsr"
									maxlength="10" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy5_nsr"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
						<td>
							<div align="center">
								<input type="text" id="jtcy5_jkzk" name="jtcy5_jkzk"
									maxlength="20" style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="jtcy5_jkzk"/>">
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								��ͥ�˾������룺
							</div>
						</td>
						<td colspan="2" align="left">
							<input type="text" id="jtrjnsr" name="jtrjnsr" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtrjnsr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td>
							<div align="right">
								��ͥǷծ�����
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="jtqzqk" name="jtqzqk" maxlength="100"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtqzqk"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								ѧ���ѻ����������
							</div>
						</td>
						<td colspan="6" align="left">
							<input type="text" id="xsyhhjqk" name="xsyhhjqk" maxlength="200"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xsyhhjqk"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								��ͥ������Ȼ�ֺ������
							</div>
						</td>
						<td colspan="6" align="left">
							<input type="text" id="jtzszrzhqk" name="jtzszrzhqk"
								maxlength="200" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtzszrzhqk"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								��ͥ����ͻ�������¼���
							</div>
						</td>
						<td colspan="6" align="left">
							<input type="text" id="jtzstfywsj" name="jtzstfywsj"
								maxlength="200" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtzstfywsj"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								��ͥ��Ա��м����������Ͷ����������
							</div>
						</td>
						<td colspan="6" align="left">
							<input type="text" id="jtcyndlrqk" name="jtcyndlrqk"
								maxlength="200" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcyndlrqk"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								��ͥ��Աʧҵ�����
							</div>
						</td>
						<td colspan="6" align="left">
							<input type="text" id="jtcysyqk" name="jtcysyqk" maxlength="200"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcysyqk"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								���������
							</div>
						</td>
						<td colspan="6" align="left">
							<input type="text" id="qtqk" name="qtqk" maxlength="200"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="qtqk"/>">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								���������������룺
							</div>
						</td>
						<td colspan="2" align="left">
							<input type="text" id="mzbmyzbm" name="mzbmyzbm" maxlength="6"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="mzbmyzbm"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td>
							<div align="right">
								����������ϵ�绰��
							</div>
						</td>
						<td colspan="3" align="left">
							<input type="text" id="mzbmlxdh" name="mzbmlxdh" maxlength="15"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="mzbmlxdh"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="right">
								����������ϸͨѶ��ַ��
							</div>
						</td>
						<td colspan="6" align="left">
							<input type="text" id="mzbmxxtxdz" name="mzbmxxtxdz"
								maxlength="100" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="mzbmxxtxdz"/>">
						</td>
					</tr>
				</table>
			</logic:equal>
			<logic:equal name="isXNMZ" value="no">
				<logic:notEmpty name="isSHGCKNS">
					<logic:equal name="isSHGCKNS" value="no">
						<table class="tbstyle">
							<tr>
								<logic:equal name="userOnLine" value="teacher" scope="session">
									<td align="right" colspan="2">
										<font color="red">*</font>ѧ�ţ�
									</td>
									<td align="left" colspan="2">
										<html:text name='rs' property="xh" styleId="xh"
											readonly="true"
											onkeypress="autoFillStuInfo(event.keyCode,this)" />
										<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
									</td>
								</logic:equal>
								<logic:equal name="userOnLine" value="student" scope="session">
									<td align="right" colspan="2">
										<font color="red">*</font>ѧ�ţ�
									</td>
									<td align="left" colspan="2">
										<input type="text" id="xh" name="xh"
											style="width:100%;heigh:100%"
											value="<bean:write name='rs' property="xh" />"
											readonly="true">
									</td>
								</logic:equal>
								<td width="16%">
									<div align="right">
										������
									</div>
								</td>
								<td width="34%" align="left">
									<input type="text" id="xm" name="xm"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="xm"/>"
										readonly="readonly">
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div align="right">
										�Ա�
									</div>
								</td>
								<td colspan="2" align="left">
									<input type="text" id="xb" name="xb" readonly="readonly"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="xb"/>">
								</td>
								<td>
									<div align="right">
										���壺
									</div>
								</td>
								<td align="left">
									<input type="text" id="mzmc" readonly="readonly" name="mzmc"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="mzmc"/>">
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div align="right">
										��ϵ�绰��
									</div>
								</td>
								<td colspan="2" align="left">
									<input type="text" id="lxdh" name="lxdh"
										style="width:100%;heigh:100%" maxlength="12"
										value="<bean:write name="rs" property="lxdh"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</td>
								<td>
									<div align="right">
										<bean:message key="lable.xsgzyxpzxy" />��
									</div>
								</td>
								<td align="left">
									<input type="text" id="xymc" readonly="readonly" name="xymc"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="xymc"/>">
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div align="right">
										רҵ��
									</div>
								</td>
								<td colspan="2" align="left">
									<input type="text" id="xmc" name="xmc" readonly="readonly"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="xmc"/>">
								</td>
								<td>
									<div align="right">
										�ࣺ
									</div>
								</td>
								<td align="left">
									<input type="text" id="bjmc" name="bjmc" readonly="readonly"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="bjmc"/>">
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div align="right">
										���ɽ�
									</div>
								</td>
								<td colspan="2" align="left">
									<input type="text" id="hjje" name="hjje" maxlength="8"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="hjje"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</td>
								<td>
									<div align="right">
										�������ޣ�
									</div>
								</td>
								<td align="left">
									<input type="text" id="hjqx" name="hjqx" maxlength="10"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="hjqx"/>">
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div align="right">
										���֤�ţ�
									</div>
								</td>
								<td colspan="2" align="left">
									<input type="text" id="sfzh" name="sfzh" readonly="readonly"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="sfzh"/>">
								</td>
								<td>
									<div align="right">
										��ͥ��ϵ�绰��
									</div>
								</td>
								<td align="left">
									<input type="text" id="jtlxdh" name="jtlxdh" maxlength="15"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtlxdh"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div align="right">
										��ͥסַ��
									</div>
								</td>
								<td colspan="4" align="left">
									<input type="text" id="jtzz" name="jtzz" maxlength="100"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtzz"/>">
								</td>
							</tr>
							<tr>
								<td width="4%" rowspan="6">
									<div align="center">
										��
										<br>
										ͥ
										<br>
										��
										<br>
										Ա
										<br>
										��
										<br>
										��
									</div>
								</td>
								<td width="12%">
									<div align="center">
										����
									</div>
								</td>
								<td width="9%">
									<div align="center">
										�뱾�˹�ϵ
									</div>
								</td>
								<td width="25%">
									<div align="center">
										����(Ԫ/��)
									</div>
								</td>
								<td colspan="2">
									<div align="center">
										������ѧϰ��λ
									</div>
								</td>
							</tr>
							<tr>
								<td width="12%">
									<input type="text" id="JTCY1_XM" name="JTCY1_XM"
										style="width:100%;heigh:100%" maxlength="30"
										value="<bean:write name="rs" property="JTCY1_XM"/>">
								</td>
								<td>
									<input type="text" id="JTCY1_GX" name="JTCY1_GX"
										style="width:100%;heigh:100%" maxlength="10"
										value="<bean:write name="rs" property="JTCY1_GX" />">
								</td>
								<td>
									<input type="text" id="JTCY1_ysr" name="JTCY1_ysr"
										style="width:100%;heigh:100%" maxlength="10"
										value="<bean:write name="rs" property="JTCY1_ysr" />"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</td>
								<td colspan="2">
									<input type="text" id="JTCY1_GZDZ" name="JTCY1_GZDZ"
										style="width:100%;heigh:100%" maxlength="30"
										value="<bean:write name="rs" property="JTCY1_GZDZ" />">
								</td>
							</tr>
							<tr>
								<td width="12%">
									<input type="text" id="JTCY2_XM" name="JTCY2_XM"
										style="width:100%;heigh:100%" maxlength="30"
										value="<bean:write name="rs" property="JTCY2_XM"/>">
								</td>
								<td>
									<input type="text" id="JTCY2_GX" name="JTCY2_GX"
										style="width:100%;heigh:100%" maxlength="10"
										value="<bean:write name="rs" property="JTCY2_GX" />">
								</td>
								<td>
									<input type="text" id="JTCY2_ysr" name="JTCY2_ysr"
										style="width:100%;heigh:100%" maxlength="10"
										value="<bean:write name="rs" property="JTCY2_ysr" />"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</td>
								<td colspan="2">
									<input type="text" id="JTCY2_GZDZ" name="JTCY2_GZDZ"
										style="width:100%;heigh:100%" maxlength="30"
										value="<bean:write name="rs" property="JTCY2_GZDZ" />">
								</td>
							</tr>
							<tr>
								<td width="12%">
									<input type="text" id="JTCY3_XM" name="JTCY3_XM"
										style="width:100%;heigh:100%" maxlength="30"
										value="<bean:write name="rs" property="JTCY3_XM"/>">
								</td>
								<td>
									<input type="text" id="JTCY3_GX" name="JTCY3_GX"
										style="width:100%;heigh:100%" maxlength="10"
										value="<bean:write name="rs" property="JTCY3_GX" />">
								</td>
								<td>
									<input type="text" id="JTCY3_ysr" name="JTCY3_ysr"
										style="width:100%;heigh:100%" maxlength="10"
										value="<bean:write name="rs" property="JTCY3_ysr" />"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</td>
								<td colspan="2">
									<input type="text" id="JTCY3_GZDZ" name="JTCY3_GZDZ"
										style="width:100%;heigh:100%" maxlength="30"
										value="<bean:write name="rs" property="JTCY3_GZDZ" />">
								</td>
							</tr>
							<tr>
								<td width="12%">
									<input type="text" id="JTCY4_XM" name="JTCY4_XM"
										style="width:100%;heigh:100%" maxlength="30"
										value="<bean:write name="rs" property="JTCY4_XM"/>">
								</td>
								<td>
									<input type="text" id="JTCY4_GX" name="JTCY4_GX"
										style="width:100%;heigh:100%" maxlength="10"
										value="<bean:write name="rs" property="JTCY4_GX" />">
								</td>
								<td>
									<input type="text" id="JTCY4_ysr" name="JTCY4_ysr"
										style="width:100%;heigh:100%" maxlength="10"
										value="<bean:write name="rs" property="JTCY4_ysr" />"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</td>
								<td colspan="2">
									<input type="text" id="JTCY4_GZDZ" name="JTCY4_GZDZ"
										style="width:100%;heigh:100%" maxlength="30"
										value="<bean:write name="rs" property="JTCY4_GZDZ" />">
								</td>
							</tr>
							<tr>
								<td width="12%">
									<input type="text" id="JTCY5_XM" name="JTCY5_XM"
										style="width:100%;heigh:100%" maxlength="30"
										value="<bean:write name="rs" property="JTCY5_XM"/>">
								</td>
								<td>
									<input type="text" id="JTCY5_GX" name="JTCY5_GX"
										style="width:100%;heigh:100%" maxlength="10"
										value="<bean:write name="rs" property="JTCY5_GX" />">
								</td>
								<td>
									<input type="text" id="JTCY5_ysr" name="JTCY5_ysr"
										style="width:100%;heigh:100%" maxlength="10"
										value="<bean:write name="rs" property="JTCY5_ysr" />"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</td>
								<td colspan="2">
									<input type="text" id="JTCY5_GZDZ" name="JTCY5_GZDZ"
										style="width:100%;heigh:100%" maxlength="30"
										value="<bean:write name="rs" property="JTCY5_GZDZ" />">
								</td>
							</tr>
							<tr>
								<td>
									<br>
									����
									<br>
									����
								</td>
								<td colspan="5">
									<html:textarea name="rs" property="sqly" rows='5' style="width:100%" onblur="yzdx(this,'sqly')"/>
									<input type="hidden" id="sqly" name="sqly" value="">
								</td>
							</tr>
							<tr>
								<td colspan="6">
									<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(���˱�֤��������������ʵ����)</font>
								</td>
							</tr>
						</table>
					</logic:equal>
					<logic:equal name="isSHGCKNS" value="is">
						<table class="tbstyle">
							<tr>
								<logic:equal name="userOnLine" value="teacher" scope="session">
									<td align="right" colspan="2">
										<font color="red">*</font>ѧ�ţ�
									</td>
									<td align="left" colspan="2">
										<html:text name='rs' property="xh" styleId="xh"
											readonly="true"
											onkeypress="autoFillStuInfo(event.keyCode,this)" />
										<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
									</td>
								</logic:equal>
								<logic:equal name="userOnLine" value="student" scope="session">
									<td align="center" colspan="2">
										<font color="red">*</font>ѧ�ţ�
									</td>
									<td align="left" colspan="2">
										<input type="text" id="xh" name="xh"
											style="width:100%;heigh:100%"
											value="<bean:write name='rs' property="xh" />"
											readonly="true">
									</td>
								</logic:equal>
								<td width="16%">
									<div align="center">
										����
									</div>
								</td>
								<td width="34%">
									<input type="text" id="xm" name="xm"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="xm"/>"
										readonly="readonly">
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div align="center">
										�Ա�
									</div>
								</td>
								<td colspan="2">
									<input type="text" id="xb" name="xb" readonly="readonly"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="xb"/>">
								</td>
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
							</tr>
							<tr>
								<td colspan="2">
									<div align="center">
										��ϵ�绰
									</div>
								</td>
								<td colspan="2">
									<input type="text" id="lxdh" name="lxdh"
										style="width:100%;heigh:100%" maxlength="12"
										value="<bean:write name="rs" property="lxdh"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</td>
								<td>
									<div align="center">
										<bean:message key="lable.xsgzyxpzxy" />
									</div>
								</td>
								<td>
									<input type="text" id="xymc" readonly="readonly" name="xymc"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="xymc"/>">
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div align="center">
										רҵ
									</div>
								</td>
								<td colspan="2">
									<input type="text" id="xmc" name="xmc" readonly="readonly"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="xmc"/>">
								</td>
								<td>
									<div align="center">
										��
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
									<div align="right">
										���֤�ţ�
									</div>
								</td>
								<td colspan="2" align="left">
									<input type="text" id="sfzh" name="sfzh" readonly="readonly"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="sfzh"/>">
								</td>
								<td>
									<div align="right">
										��ͥ��ϵ�绰��
									</div>
								</td>
								<td align="left">
									<input type="text" id="jtlxdh" name="jtlxdh" maxlength="15"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtlxdh"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div align="right">
										��ͥסַ��
									</div>
								</td>
								<td colspan="4" align="left">
									<input type="text" id="jtzz" name="jtzz" maxlength="100"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtzz"/>">
								</td>
							</tr>
							<tr>
								<td width="4%" rowspan="6">
									<div align="center">
										��
										<br>
										ͥ
										<br>
										��
										<br>
										Ա
										<br>
										��
										<br>
										��
									</div>
								</td>
								<td width="12%">
									<div align="center">
										����
									</div>
								</td>
								<td width="9%">
									<div align="center">
										�뱾�˹�ϵ
									</div>
								</td>
								<td width="25%">
									<div align="center">
										����(Ԫ/��)
									</div>
								</td>
								<td colspan="2">
									<div align="center">
										������ѧϰ��λ
									</div>
								</td>
							</tr>
							<tr>
								<td width="12%">
									<input type="text" id="JTCY1_XM" readonly="readonly"
										name="JTCY1_XM" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY1_XM"/>">
								</td>
								<td>
									<input type="text" id="JTCY1_GX" readonly="readonly"
										name="JTCY1_GX" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY1_GX" />">
								</td>
								<td>
									<input type="text" id="JTCY1_ysr" readonly="readonly"
										name="JTCY1_ysr" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY1_ysr" />">
								</td>
								<td colspan="2">
									<input type="text" id="JTCY1_GZDZ" readonly="readonly"
										name="JTCY1_GZDZ" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY1_GZDZ" />">
								</td>
							</tr>
							<tr>
								<td width="12%">
									<input type="text" id="JTCY2_XM" readonly="readonly"
										name="JTCY2_XM" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY2_XM"/>">
								</td>
								<td>
									<input type="text" id="JTCY2_GX" readonly="readonly"
										name="JTCY2_GX" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY2_GX" />">
								</td>
								<td>
									<input type="text" id="JTCY2_ysr" readonly="readonly"
										name="JTCY2_ysr" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY2_ysr" />">
								</td>
								<td colspan="2">
									<input type="text" id="JTCY2_GZDZ" readonly="readonly"
										name="JTCY2_GZDZ" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY2_GZDZ" />">
								</td>
							</tr>
							<tr>
								<td width="12%">
									<input type="text" id="JTCY3_XM" readonly="readonly"
										name="JTCY3_XM" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY3_XM"/>">
								</td>
								<td>
									<input type="text" id="JTCY3_GX" readonly="readonly"
										name="JTCY3_GX" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY3_GX" />">
								</td>
								<td>
									<input type="text" id="JTCY3_ysr" readonly="readonly"
										name="JTCY3_ysr" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY3_ysr" />">
								</td>
								<td colspan="2">
									<input type="text" id="JTCY3_GZDZ" readonly="readonly"
										name="JTCY3_GZDZ" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY3_GZDZ" />">
								</td>
							</tr>
							<tr>
								<td width="12%">
									<input type="text" id="JTCY4_XM" readonly="readonly"
										name="JTCY4_XM" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY4_XM"/>">
								</td>
								<td>
									<input type="text" id="JTCY4_GX" readonly="readonly"
										name="JTCY4_GX" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY4_GX" />">
								</td>
								<td>
									<input type="text" id="JTCY4_ysr" readonly="readonly"
										name="JTCY4_ysr" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY4_ysr" />">
								</td>
								<td colspan="2">
									<input type="text" id="JTCY4_GZDZ" readonly="readonly"
										name="JTCY4_GZDZ" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY4_GZDZ" />">
								</td>
							</tr>
							<tr>
								<td width="12%">
									<input type="text" id="JTCY5_XM" readonly="readonly"
										name="JTCY5_XM" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY5_XM"/>">
								</td>
								<td>
									<input type="text" id="JTCY5_GX" readonly="readonly"
										name="JTCY5_GX" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY5_GX" />">
								</td>
								<td>
									<input type="text" id="JTCY5_ysr" readonly="readonly"
										name="JTCY5_ysr" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY5_ysr" />">
								</td>
								<td colspan="2">
									<input type="text" id="JTCY5_GZDZ" readonly="readonly"
										name="JTCY5_GZDZ" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="JTCY5_GZDZ" />">
								</td>
							</tr>
							<tr>
								<td>
									<br>
									����
									<br>
									����
								</td>
								<td colspan="5">
									<html:textarea name="rs" property="sqly" rows='5' style="width:100%" onblur="yzdx(this,'sqly')"/>
									<input type="hidden" id="sqly" name="sqly" value="">
								</td>
							</tr>
						</table>
					</logic:equal>
				</logic:notEmpty>
			</logic:equal>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<logic:equal name="isXNMZ" value="no">
					<button class="button2"
						onClick="yz1(document.getElementById('titName').value,document.getElementById('JTCY1_ysr').value,document.getElementById('JTCY2_ysr').value,document.getElementById('JTCY3_ysr').value,document.getElementById('JTCY4_ysr').value,document.getElementById('JTCY5_ysr').value);">
						�ύ����
					</button>
					<button class="button2"
						onClick="toPrintOut(document.getElementById('titName').value);">
						��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
					</button>
				</logic:equal>
				<logic:equal name="isXNMZ" value="is">
					<button class="button2"
						onClick="yz2(document.getElementById('titName').value);">
						�ύ����
					</button>
					<button class="button2" onClick="toPrintOut('xnmz_lstd');">
						��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
					</button>
				</logic:equal>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
