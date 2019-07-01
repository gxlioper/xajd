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
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(titName){
			var xh = document.getElementById('xh').value;
			var sbsj = document.getElementById('sbsj').value;
			var lxdh = document.getElementById('lxdh').value;
			var jtdz = document.getElementById('jtdz').value;
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
			var xxqk = document.getElementById('xxqk').value;
			var psbx = document.getElementById('psbx').value;
			var jcqk = document.getElementById('jcqk').value;
			var mnjttgfy = document.getElementById('mnjttgfy').value;
			var mnqphytgfy = document.getElementById('mnqphytgfy').value;
			var mnhjtgfy = document.getElementById('mnhjtgfy').value;
			var mnyjgzfy = document.getElementById('mnyjgzfy').value;
			var mypjshf = document.getElementById('mypjshf').value;
			var mnhjfy = document.getElementById('mnhjfy').value;
			var mncjynqgzxbt = document.getElementById('mncjynqgzxbt').value;
			var cjywqgzxbc = document.getElementById('cjywqgzxbc').value;
			var ywshhjbgk = document.getElementById('ywshhjbgk').value;
			var yyf = document.getElementById('yyf').value;
			var qtfy = document.getElementById('qtfy').value;
			var jtbxlpk = document.getElementById('jtbxlpk').value;
			var xybxlpk = document.getElementById('xybxlpk').value;
			var jtjjzkjsqsbbzly = document.getElementById('jtjjzkjsqsbbzly').value;
			var nsqbzje = document.getElementById('nsqbzje').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((sbsj == null) || (sbsj == "")){
				alert("�ˡ���ʱ�䲻��Ϊ��!");
				return false;
			}
			if((lxdh != null) && (lxdh != "") && (!isNumber(lxdh))){
				alert("��ϵ�绰����Ϊ����!");
				return false;
			}
			if(jtdz != null){
	         	if(jtdz.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("��ͥ��ַ���ܴ���100���ַ�");
	          		 return false;
	       		 }
			}
			if((jtcy1_nl != null) && (jtcy1_nl != "") && (!isNumber(jtcy1_nl))){
				alert("��ͥ��Ա1�������Ϊ����!");
				return false;
			}
			if((jtcy1_nsr != null) && (jtcy1_nsr != "") && (!isNumber(jtcy1_nsr))){
				alert("��ͥ��Ա1���������Ϊ����!");
				return false;
			}
			if((jtcy2_nl != null) && (jtcy2_nl != "") && (!isNumber(jtcy2_nl))){
				alert("��ͥ��Ա2�������Ϊ����!");
				return false;
			}
			if((jtcy2_nsr != null) && (jtcy2_nsr != "") && (!isNumber(jtcy2_nsr))){
				alert("��ͥ��Ա2���������Ϊ����!");
				return false;
			}
			if((jtcy3_nl != null) && (jtcy3_nl != "") && (!isNumber(jtcy3_nl))){
				alert("��ͥ��Ա3�������Ϊ����!");
				return false;
			}
			if((jtcy3_nsr != null) && (jtcy3_nsr != "") && (!isNumber(jtcy3_nsr))){
				alert("��ͥ��Ա3���������Ϊ����!");
				return false;
			}
			if((jtcy4_nl != null) && (jtcy4_nl != "") && (!isNumber(jtcy4_nl))){
				alert("��ͥ��Ա4�������Ϊ����!");
				return false;
			}
			if((jtcy4_nsr != null) && (jtcy4_nsr != "") && (!isNumber(jtcy4_nsr))){
				alert("��ͥ��Ա4���������Ϊ����!");
				return false;
			}
			if((jtcy5_nl != null) && (jtcy5_nl != "") && (!isNumber(jtcy5_nl))){
				alert("��ͥ��Ա5�������Ϊ����!");
				return false;
			}
			if((jtcy5_nsr != null) && (jtcy5_nsr != "") && (!isNumber(jtcy5_nsr))){
				alert("��ͥ��Ա5���������Ϊ����!");
				return false;
			}
			if(xxqk != null){
	         	if(xxqk.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("ѧϰ������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(psbx != null){
	         	if(psbx.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("ƽʱ���ֲ��ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(jcqk != null){
	         	if(jcqk.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("����������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if((mnjttgfy != null) && (mnjttgfy != "") && (!isNumber(mnjttgfy))){
				alert("ÿ���ͥ�ṩ���ñ���Ϊ����!");
				return false;
			}
			if((mnqphytgfy != null) && (mnqphytgfy != "") && (!isNumber(mnqphytgfy))){
				alert("������ѵ��ṩ���ñ���Ϊ����!");
				return false;
			}
			if((mnhjtgfy != null) && (mnhjtgfy != "") && (!isNumber(mnhjtgfy))){
				alert("ÿ��ϼ��ṩ���ñ���Ϊ����!");
				return false;
			}
			if((mnyjgzfy != null) && (mnyjgzfy != "") && (!isNumber(mnyjgzfy))){
				alert("ÿ��Ӧ���ɸ��ַ��ñ���Ϊ����!");
				return false;
			}
			if((mypjshf != null) && (mypjshf != "") && (!isNumber(mypjshf))){
				alert("ÿ��ƽ������ѱ���Ϊ����!");
				return false;
			}
			if((mnhjfy != null) && (mnhjfy != "") && (!isNumber(mnhjfy))){
				alert("ÿ��ϼƷ��ñ���Ϊ����!");
				return false;
			}
			if((mncjynqgzxbt != null) && (mncjynqgzxbt != "") && (!isNumber(mncjynqgzxbt))){
				alert("ÿ��μ�Ժ���ڹ���ѧ��������Ϊ����!");
				return false;
			}
			if((cjywqgzxbc != null) && (cjywqgzxbc != "") && (!isNumber(cjywqgzxbc))){
				alert("�μ�Ժ���ڹ���ѧ�񱨳������Ϊ����!");
				return false;
			}
			if(ywshhjbgk != null){
	         	if(ywshhjbgk.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("�����Ϻ��ͼ����ſ����ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if((yyf != null) && (yyf != "") && (!isNumber(yyf))){
				alert("ҽҩ�ѱ���Ϊ����!");
				return false;
			}
			if((qtfy != null) && (qtfy != "") && (!isNumber(qtfy))){
				alert("�������ñ���Ϊ����!");
				return false;
			}
			if((jtbxlpk != null) && (jtbxlpk != "") && (!isNumber(jtbxlpk))){
				alert("��ͥ������������Ϊ����!");
				return false;
			}
			if((xybxlpk != null) && (xybxlpk != "") && (!isNumber(xybxlpk))){
				alert("<bean:message key="lable.xsgzyxpzxy" />������������Ϊ����!");
				return false;
			}
			if(jtjjzkjsqsbbzly != null){
	         	if(jtjjzkjsqsbbzly.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("��ͥ����״���������ˡ������Ѳ��������ɲ��ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if((nsqbzje != null) && (nsqbzje != "") && (!isNumber(nsqbzje))){
				alert("�����벹��������Ϊ����!");
				return false;
	       	}
			
			document.forms[0].action = "/xgxt/jsxx_sbknbz.do?doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(titName){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/jsxx_sbknbzsqb.do";
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
				return false;
			}
			return true;
		}
		
		function jehj1(){
			var mnjttgfy = document.getElementById('mnjttgfy').value;
			var mnqphytgfy = document.getElementById('mnqphytgfy').value;
			if((mnjttgfy == null) || (mnjttgfy == "")){
				mnjttgfy = "0";
			}
			if((mnqphytgfy == null) || (mnqphytgfy == "")){
				mnqphytgfy = "0";
			}
			var hj = Math.round(mnjttgfy) + Math.round(mnqphytgfy);
			document.getElementById('mnhjtgfy').value=hj;
		}
		
		function jehj2(){
			var mnyjgzfy = document.getElementById('mnyjgzfy').value;
			var mypjshf = document.getElementById('mypjshf').value;
			if((mnyjgzfy == null) || (mnyjgzfy == "")){
				mnyjgzfy = "0";
			}
			if((mypjshf == null) || (mypjshf == "")){
				mypjshf = "0";
			}
			var hj = Math.round(mnyjgzfy) + Math.round(mypjshf)*12;
			document.getElementById('mnhjfy').value=hj;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - �ˡ������Ѳ��������
		</div>
	</div>
		<html:form action="jsxx_sbknbz.do" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="url" name="url"
				value="/jsxx_sbknbz.do?jxjlbType=jsxx_sbknbz" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj"/>">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj"/>">
			<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj"/>">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh"/>">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh"/>">

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
			<table class="tbstyle" width="100%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" colspan="2">
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
						<td align="center" colspan="2">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" colspan="2">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
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
					<td width="26%">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="xb" name="xb" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="xymc" readonly="readonly" name="xymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
					<td>
						<div align="center">
							ϵ
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zymc" name="zymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�༶
						</div>
					</td>
					<td colspan="2">
						<input type="text" readonly="readonly" id="bjmc" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
					<td>
						<div align="center">
							��ͥ��ϵ�绰
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="lxdh" maxlength="15" name="lxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥסַ
						</div>
					</td>
					<td colspan="5">
						<html:textarea id="jtdz" name="rs" property="jtdz" rows='3' style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td rowspan="6" scope="row" width="4%">
						<div align="center">
							��
							<br>
							ͥ
							<br>
							��
							<br>
							Ա
						</div>
					</td>
					<td width="20%">
						<div align="center">
							��ν
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����״��
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							������λ��ְ��
						</div>
					</td>
					<td width="12%">
						<div align="center">
							������
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy1_cw" maxlength="40" name="jtcy1_cw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_cw"/>">
					</td>
					<td>
						<input type="text" id="jtcy1_nl" name="jtcy1_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy1_stzk" name="jtcy1_stzk"
							maxlength="40" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_stzk" />">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy1_gzdwjzw" maxlength="100"
							name="jtcy1_gzdwjzw" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_gzdwjzw" />">
					</td>
					<td>
						<input type="text" id="jtcy1_nsr" maxlength="10" name="jtcy1_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_nsr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy2_cw" maxlength="40" name="jtcy2_cw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_cw"/>">
					</td>
					<td>
						<input type="text" id="jtcy2_nl" name="jtcy2_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy2_stzk" name="jtcy2_stzk"
							maxlength="40" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_stzk" />">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy2_gzdwjzw" maxlength="100"
							name="jtcy2_gzdwjzw" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_gzdwjzw" />">
					</td>
					<td>
						<input type="text" id="jtcy2_nsr" maxlength="10" name="jtcy2_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_nsr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy3_cw" maxlength="40" name="jtcy3_cw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_cw"/>">
					</td>
					<td>
						<input type="text" id="jtcy3_nl" name="jtcy3_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy3_stzk" name="jtcy3_stzk"
							maxlength="40" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_stzk" />">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy3_gzdwjzw" maxlength="100"
							name="jtcy3_gzdwjzw" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_gzdwjzw" />">
					</td>
					<td>
						<input type="text" id="jtcy3_nsr" maxlength="10" name="jtcy3_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_nsr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy4_cw" maxlength="40" name="jtcy4_cw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_cw"/>">
					</td>
					<td>
						<input type="text" id="jtcy4_nl" name="jtcy4_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy4_stzk" name="jtcy4_stzk"
							maxlength="40" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_stzk" />">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy4_gzdwjzw" maxlength="100"
							name="jtcy4_gzdwjzw" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_gzdwjzw" />">
					</td>
					<td>
						<input type="text" id="jtcy4_nsr" maxlength="10" name="jtcy4_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_nsr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy5_cw" maxlength="40" name="jtcy5_cw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_cw"/>">
					</td>
					<td>
						<input type="text" id="jtcy5_nl" name="jtcy5_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy5_stzk" name="jtcy5_stzk"
							maxlength="40" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_stzk" />">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy5_gzdwjzw" maxlength="100"
							name="jtcy5_gzdwjzw" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_gzdwjzw" />">
					</td>
					<td>
						<input type="text" id="jtcy5_nsr" maxlength="10" name="jtcy5_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_nsr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="col">
						<div align="center">
							�Ƿ����Ҿ���
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
						<logic:present name="rs" property="sfljs">
							<logic:match value="��" name="rs" property="sfljs">
								<input type="radio" name="sfljs" value="��" checked>&nbsp;&nbsp;��
							         	<input type="radio" name="sfljs" value="��">&nbsp;&nbsp;��
							         </logic:match>
							<logic:match value="��" name="rs" property="sfljs">
								<input type="radio" name="sfljs" value="��">&nbsp;&nbsp;��
							         	<input type="radio" name="sfljs" value="��" checked>&nbsp;&nbsp;��
							         </logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfljs">
							<input type="radio" name="sfljs" value="��">&nbsp;&nbsp;��
							         <input type="radio" name="sfljs" value="��" checked>&nbsp;&nbsp;��
						         </logic:notPresent>
					</td>
					<td scope="col">
						<div align="center">
							�Ƿ��ǵ���
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
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
					<td colspan="2" scope="col">
						<div align="center">
							��ĸ�Ƿ���˫�¸�
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
						<logic:present name="rs" property="sfsxg">
							<logic:match value="��" name="rs" property="sfsxg">
								<input type="radio" name="sfsxg" value="��" checked>&nbsp;&nbsp;��
							         	<input type="radio" name="sfsxg" value="��">&nbsp;&nbsp;��
							         </logic:match>
							<logic:match value="��" name="rs" property="sfsxg">
								<input type="radio" name="sfsxg" value="��">&nbsp;&nbsp;��
							         	<input type="radio" name="sfsxg" value="��" checked>&nbsp;&nbsp;��
							         </logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfsxg">
							<input type="radio" name="sfsxg" value="��">&nbsp;&nbsp;�� 
							         <input type="radio" name="sfsxg" value="��" checked>&nbsp;&nbsp;��
						         </logic:notPresent>
					</td>
					<td scope="col">
						<div align="center">
							��ĸ�Ƿ��вм�
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
						<logic:present name="rs" property="sfcj">
							<logic:match value="��" name="rs" property="sfcj">
								<input type="radio" name="sfcj" value="��" checked>&nbsp;&nbsp;��
							         	<input type="radio" name="sfcj" value="��">&nbsp;&nbsp;��
							         </logic:match>
							<logic:match value="��" name="rs" property="sfcj">
								<input type="radio" name="sfcj" value="��">&nbsp;&nbsp;��
							         	<input type="radio" name="sfcj" value="��" checked>&nbsp;&nbsp;��
							         </logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfcj">
							<input type="radio" name="sfcj" value="��">&nbsp;&nbsp;�� 
							         <input type="radio" name="sfcj" value="��" checked>&nbsp;&nbsp;��
						         </logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧϰ���
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="xxqk" rows='3' style="width:100%" onblur="yzdx(this,'xxqk')"/>
						<input type="hidden" id="xxqk" name="xxqk" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							���������޲�����γ�
						</div>
					</td>
					<td colspan="2" align="center">
						<logic:present name="rs" property="bkhywbjgkc">
							<logic:match value="��" name="rs" property="bkhywbjgkc">
								<input type="radio" name="bkhywbjgkc" value="��" checked>&nbsp;&nbsp;��
							         	<input type="radio" name="bkhywbjgkc" value="��">&nbsp;&nbsp;��
							         </logic:match>
							<logic:match value="��" name="rs" property="bkhywbjgkc">
								<input type="radio" name="bkhywbjgkc" value="��">&nbsp;&nbsp;��
							         	<input type="radio" name="bkhywbjgkc" value="��" checked>&nbsp;&nbsp;��
							         </logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="bkhywbjgkc">
							<input type="radio" name="bkhywbjgkc" value="��">&nbsp;&nbsp;�� 
							         <input type="radio" name="bkhywbjgkc" value="��" checked>&nbsp;&nbsp;��
						         </logic:notPresent>
					</td>
					<td>
						<div align="center">
							���еȵ�
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="cxdd" maxlength="10" name="cxdd"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="cxdd"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ƽʱ����
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="psbx" rows='3' style="width:100%" onblur="yzdx(this,'psbx')"/>
						<input type="hidden" id="psbx" name="psbx" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�������
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="jcqk" rows='3' style="width:100%" onblur="yzdx(this,'jcqk')"/>
						<input type="hidden" id="jcqk" name="jcqk" value="">
					</td>
				</tr>
				<tr>
					<td colspan="7">
						<div align="left">
							&nbsp;&nbsp;&nbsp;ÿ���ͥ�ṩ����
							<input type="text" id="mnjttgfy" maxlength="10" name="mnjttgfy"
								style="width:10%;heigh:100%" onblur="jehj1();" 
								value="<bean:write name="rs" property="mnjttgfy"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							&nbsp;Ԫ�� ������ѵ��ṩ
							<input type="text" id="mnqphytgfy" maxlength="10" onblur="jehj1();" 
								name="mnqphytgfy" style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="mnqphytgfy"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							&nbsp;Ԫ�� �ϼ�ÿ���ṩ
							<input type="text" id="mnhjtgfy" maxlength="10" name="mnhjtgfy"
								style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="mnhjtgfy"/>"
								readonly="readonly">
							&nbsp;Ԫ
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="7">
						<div align="left">
							ÿ��Ӧ���ɸ��ַ���
							<input type="text" id="mnyjgzfy" maxlength="6" name="mnyjgzfy"
								style="width:10%;heigh:100%" onblur="jehj2();" 
								value="<bean:write name="rs" property="mnyjgzfy"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							&nbsp;Ԫ�� ÿ��ƽ�������
							<input type="text" id="mypjshf" maxlength="5" name="mypjshf"
								style="width:10%;heigh:100%" onblur="jehj2();"
								value="<bean:write name="rs" property="mypjshf"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							&nbsp;Ԫ�� �ϼ�ÿ�����
							<input type="text" id="mnhjfy" maxlength="10" name="mnhjfy"
								style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="mnhjfy"/>"
								readonly="readonly">
							&nbsp;Ԫ
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="7">
						<div align="left">
							ÿ��μ�Ժ���ڹ���ѧ����
							<input type="text" id="mncjynqgzxbt" maxlength="10"
								name="mncjynqgzxbt" style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="mncjynqgzxbt"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							&nbsp;Ԫ��
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							�μ�Ժ���ڹ���ѧ��񱨳�
							<input type="text" id="cjywqgzxbc" maxlength="10"
								name="cjywqgzxbc" style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="cjywqgzxbc"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							&nbsp;Ԫ
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�����˺��ͼ����ſ�
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="ywshhjbgk" rows='3' style="width:100%" onblur="yzdx(this,'ywshhjbgk')"/>
						<input type="hidden" id="ywshhjbgk" name="ywshhjbgk" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����ҽԺ
						</div>
					</td>
					<td colspan="5">
						<input type="text" id="zzyy" maxlength="50" name="zzyy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzyy"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<font color="red">*</font>�ˡ���ʱ��
						</div>
					</td>
					<td colspan="2">
						<input type="text" readonly style="cursor:hand;width:100%"
							onclick="return showCalendar('sbsj','y-mm-dd');"
							value="<bean:write name='rs' property="sbsj" />" name="sbsj"
							id="sbsj" />
					</td>
					<td>
						<div align="center">
							�Ƿ�Ƿ��
						</div>
					</td>
					<td colspan="2" align="center">
						<logic:present name="rs" property="sfqf">
							<logic:match value="��" name="rs" property="sfqf">
								<input type="radio" name="sfqf" value="��" checked>&nbsp;&nbsp;��
							         	<input type="radio" name="sfqf" value="��">&nbsp;&nbsp;��
							         </logic:match>
							<logic:match value="��" name="rs" property="sfqf">
								<input type="radio" name="sfqf" value="��">&nbsp;&nbsp;��
							         	<input type="radio" name="sfqf" value="��" checked>&nbsp;&nbsp;��
							         </logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfqf">
							<input type="radio" name="sfqf" value="��">&nbsp;&nbsp;�� 
							         <input type="radio" name="sfqf" value="��" checked>&nbsp;&nbsp;��
						         </logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ҽҩ��
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="yyf" maxlength="10" name="yyf"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yyf"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2" align="center">
						<input type="text" id="qtfy" maxlength="10" name="qtfy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qtfy"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ���������
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jtbxlpk" maxlength="10" name="jtbxlpk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtbxlpk"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />���������
						</div>
					</td>
					<td colspan="2" align="center">
						<input type="text" id="xybxlpk" maxlength="10" name="xybxlpk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xybxlpk"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ����״���������ˡ������Ѳ���������
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="jtjjzkjsqsbbzly" rows='5' style="width:100%" onblur="yzdx(this,'jtjjzkjsqsbbzly')"/>
						<input type="hidden" id="jtjjzkjsqsbbzly" name="jtjjzkjsqsbbzly" value="">
					</td>
				</tr>
				<tr>
					<td colspan="7">
						<div align="left">
							���޼�ͥ���ڵش塢��(��ί���ְ�)���ߵ��й�֤����&nbsp;&nbsp;
							<logic:present name="rs" property="ywzm">
								<logic:match value="��" name="rs" property="ywzm">
									<input type="radio" name="ywzm" value="��" checked>&nbsp;&nbsp;��
							         	<input type="radio" name="ywzm" value="��">&nbsp;&nbsp;��
							         </logic:match>
								<logic:match value="��" name="rs" property="ywzm">
									<input type="radio" name="ywzm" value="��">&nbsp;&nbsp;��
							         	<input type="radio" name="ywzm" value="��" checked>&nbsp;&nbsp;��
							         </logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="ywzm">
								<input type="radio" name="ywzm" value="��">&nbsp;&nbsp;�� 
							         <input type="radio" name="ywzm" value="��" checked>&nbsp;&nbsp;��
						         </logic:notPresent>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �����벹�����:
							<input type="text" id="nsqbzje" maxlength="10" name="nsqbzje"
								style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="nsqbzje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							&nbsp;Ԫ
						</div>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2"
					onClick="yz(document.getElementById('titName').value);">
					�ύ����
				</button>
				<button class="button2"
					onClick="toPrintOut(document.getElementById('titName').value);">
					��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
				</button>
			</div>

		</html:form>
</body>
</html:html>
