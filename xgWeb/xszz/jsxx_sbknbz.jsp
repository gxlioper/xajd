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
	<meta name="Copyright" content="正方软件 zfsoft" />

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
				alert("学号不能为空!");
				return false;
			}
			if((sbsj == null) || (sbsj == "")){
				alert("伤、病时间不能为空!");
				return false;
			}
			if((lxdh != null) && (lxdh != "") && (!isNumber(lxdh))){
				alert("联系电话必须为整数!");
				return false;
			}
			if(jtdz != null){
	         	if(jtdz.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("家庭地址不能大于100个字符");
	          		 return false;
	       		 }
			}
			if((jtcy1_nl != null) && (jtcy1_nl != "") && (!isNumber(jtcy1_nl))){
				alert("家庭成员1年龄必须为整数!");
				return false;
			}
			if((jtcy1_nsr != null) && (jtcy1_nsr != "") && (!isNumber(jtcy1_nsr))){
				alert("家庭成员1年收入必须为整数!");
				return false;
			}
			if((jtcy2_nl != null) && (jtcy2_nl != "") && (!isNumber(jtcy2_nl))){
				alert("家庭成员2年龄必须为整数!");
				return false;
			}
			if((jtcy2_nsr != null) && (jtcy2_nsr != "") && (!isNumber(jtcy2_nsr))){
				alert("家庭成员2年收入必须为整数!");
				return false;
			}
			if((jtcy3_nl != null) && (jtcy3_nl != "") && (!isNumber(jtcy3_nl))){
				alert("家庭成员3年龄必须为整数!");
				return false;
			}
			if((jtcy3_nsr != null) && (jtcy3_nsr != "") && (!isNumber(jtcy3_nsr))){
				alert("家庭成员3年收入必须为整数!");
				return false;
			}
			if((jtcy4_nl != null) && (jtcy4_nl != "") && (!isNumber(jtcy4_nl))){
				alert("家庭成员4年龄必须为整数!");
				return false;
			}
			if((jtcy4_nsr != null) && (jtcy4_nsr != "") && (!isNumber(jtcy4_nsr))){
				alert("家庭成员4年收入必须为整数!");
				return false;
			}
			if((jtcy5_nl != null) && (jtcy5_nl != "") && (!isNumber(jtcy5_nl))){
				alert("家庭成员5年龄必须为整数!");
				return false;
			}
			if((jtcy5_nsr != null) && (jtcy5_nsr != "") && (!isNumber(jtcy5_nsr))){
				alert("家庭成员5年收入必须为整数!");
				return false;
			}
			if(xxqk != null){
	         	if(xxqk.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("学习情况不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if(psbx != null){
	         	if(psbx.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("平时表现不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if(jcqk != null){
	         	if(jcqk.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("奖惩情况不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if((mnjttgfy != null) && (mnjttgfy != "") && (!isNumber(mnjttgfy))){
				alert("每年家庭提供费用必须为整数!");
				return false;
			}
			if((mnqphytgfy != null) && (mnqphytgfy != "") && (!isNumber(mnqphytgfy))){
				alert("亲朋好友等提供费用必须为整数!");
				return false;
			}
			if((mnhjtgfy != null) && (mnhjtgfy != "") && (!isNumber(mnhjtgfy))){
				alert("每年合计提供费用必须为整数!");
				return false;
			}
			if((mnyjgzfy != null) && (mnyjgzfy != "") && (!isNumber(mnyjgzfy))){
				alert("每年应缴纳各种费用必须为整数!");
				return false;
			}
			if((mypjshf != null) && (mypjshf != "") && (!isNumber(mypjshf))){
				alert("每月平均生活费必须为整数!");
				return false;
			}
			if((mnhjfy != null) && (mnhjfy != "") && (!isNumber(mnhjfy))){
				alert("每年合计费用必须为整数!");
				return false;
			}
			if((mncjynqgzxbt != null) && (mncjynqgzxbt != "") && (!isNumber(mncjynqgzxbt))){
				alert("每年参加院内勤工助学获补贴必须为整数!");
				return false;
			}
			if((cjywqgzxbc != null) && (cjywqgzxbc != "") && (!isNumber(cjywqgzxbc))){
				alert("参加院外勤工助学获报酬金额必须为整数!");
				return false;
			}
			if(ywshhjbgk != null){
	         	if(ywshhjbgk.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("以外上海和疾病概况不能大于1000个字符");
	          		 return false;
	       		 }
	       	}
	       	if((yyf != null) && (yyf != "") && (!isNumber(yyf))){
				alert("医药费必须为整数!");
				return false;
			}
			if((qtfy != null) && (qtfy != "") && (!isNumber(qtfy))){
				alert("其它费用必须为整数!");
				return false;
			}
			if((jtbxlpk != null) && (jtbxlpk != "") && (!isNumber(jtbxlpk))){
				alert("家庭保险理赔款必须为整数!");
				return false;
			}
			if((xybxlpk != null) && (xybxlpk != "") && (!isNumber(xybxlpk))){
				alert("<bean:message key="lable.xsgzyxpzxy" />保险理赔款必须为整数!");
				return false;
			}
			if(jtjjzkjsqsbbzly != null){
	         	if(jtjjzkjsqsbbzly.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("家庭紧急状况及申请伤、病困难补助的理由不能大于1000个字符");
	          		 return false;
	       		 }
	       	}
	       	if((nsqbzje != null) && (nsqbzje != "") && (!isNumber(nsqbzje))){
				alert("拟申请补助金额必须为整数!");
				return false;
	       	}
			
			document.forms[0].action = "/xgxt/jsxx_sbknbz.do?doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(titName){//输出相应的打印页面
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
			当前所在位置：学生资助 - 伤、病困难补助申请表
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
	         	alert("保存成功！");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	</script>
				</logic:match>
			</logic:present>
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("已通过审核，不能申请！");
	         		</script>
				</logic:match>
			</logic:present>
			<table class="tbstyle" width="100%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>学号：
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="readonly"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>学号：
						</td>
						<td align="left" colspan="2">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td>
						<div align="center">
							姓名
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
							出生年月
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="csrq" readonly="readonly" name="csrq"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csrq"/>">
					</td>
					<td width="26%">
						<div align="center">
							性别
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
							系
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
							班级
						</div>
					</td>
					<td colspan="2">
						<input type="text" readonly="readonly" id="bjmc" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
					<td>
						<div align="center">
							家庭联系电话
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
							家庭住址
						</div>
					</td>
					<td colspan="5">
						<html:textarea id="jtdz" name="rs" property="jtdz" rows='3' style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td rowspan="6" scope="row" width="4%">
						<div align="center">
							家
							<br>
							庭
							<br>
							成
							<br>
							员
						</div>
					</td>
					<td width="20%">
						<div align="center">
							称谓
						</div>
					</td>
					<td width="12%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="12%">
						<div align="center">
							身体状况
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							工作单位及职务
						</div>
					</td>
					<td width="12%">
						<div align="center">
							年收入
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
							是否是烈军属
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
						<logic:present name="rs" property="sfljs">
							<logic:match value="是" name="rs" property="sfljs">
								<input type="radio" name="sfljs" value="是" checked>&nbsp;&nbsp;是
							         	<input type="radio" name="sfljs" value="否">&nbsp;&nbsp;否
							         </logic:match>
							<logic:match value="否" name="rs" property="sfljs">
								<input type="radio" name="sfljs" value="是">&nbsp;&nbsp;是
							         	<input type="radio" name="sfljs" value="否" checked>&nbsp;&nbsp;否
							         </logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfljs">
							<input type="radio" name="sfljs" value="是">&nbsp;&nbsp;是
							         <input type="radio" name="sfljs" value="否" checked>&nbsp;&nbsp;否
						         </logic:notPresent>
					</td>
					<td scope="col">
						<div align="center">
							是否是单亲
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
						<logic:present name="rs" property="sfdq">
							<logic:match value="是" name="rs" property="sfdq">
								<input type="radio" name="sfdq" value="是" checked>&nbsp;&nbsp;是
							         	<input type="radio" name="sfdq" value="否">&nbsp;&nbsp;否
							         </logic:match>
							<logic:match value="否" name="rs" property="sfdq">
								<input type="radio" name="sfdq" value="是">&nbsp;&nbsp;是
							         	<input type="radio" name="sfdq" value="否" checked>&nbsp;&nbsp;否
							         </logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfdq">
							<input type="radio" name="sfdq" value="是">&nbsp;&nbsp;是 
							         <input type="radio" name="sfdq" value="否" checked>&nbsp;&nbsp;否
						         </logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="col">
						<div align="center">
							父母是否是双下岗
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
						<logic:present name="rs" property="sfsxg">
							<logic:match value="是" name="rs" property="sfsxg">
								<input type="radio" name="sfsxg" value="是" checked>&nbsp;&nbsp;是
							         	<input type="radio" name="sfsxg" value="否">&nbsp;&nbsp;否
							         </logic:match>
							<logic:match value="否" name="rs" property="sfsxg">
								<input type="radio" name="sfsxg" value="是">&nbsp;&nbsp;是
							         	<input type="radio" name="sfsxg" value="否" checked>&nbsp;&nbsp;否
							         </logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfsxg">
							<input type="radio" name="sfsxg" value="是">&nbsp;&nbsp;是 
							         <input type="radio" name="sfsxg" value="否" checked>&nbsp;&nbsp;否
						         </logic:notPresent>
					</td>
					<td scope="col">
						<div align="center">
							父母是否有残疾
						</div>
					</td>
					<td colspan="2" scope="col" align="center">
						<logic:present name="rs" property="sfcj">
							<logic:match value="是" name="rs" property="sfcj">
								<input type="radio" name="sfcj" value="是" checked>&nbsp;&nbsp;是
							         	<input type="radio" name="sfcj" value="否">&nbsp;&nbsp;否
							         </logic:match>
							<logic:match value="否" name="rs" property="sfcj">
								<input type="radio" name="sfcj" value="是">&nbsp;&nbsp;是
							         	<input type="radio" name="sfcj" value="否" checked>&nbsp;&nbsp;否
							         </logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfcj">
							<input type="radio" name="sfcj" value="是">&nbsp;&nbsp;是 
							         <input type="radio" name="sfcj" value="否" checked>&nbsp;&nbsp;否
						         </logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							学习情况
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
							补考后有无不及格课程
						</div>
					</td>
					<td colspan="2" align="center">
						<logic:present name="rs" property="bkhywbjgkc">
							<logic:match value="有" name="rs" property="bkhywbjgkc">
								<input type="radio" name="bkhywbjgkc" value="有" checked>&nbsp;&nbsp;有
							         	<input type="radio" name="bkhywbjgkc" value="无">&nbsp;&nbsp;无
							         </logic:match>
							<logic:match value="无" name="rs" property="bkhywbjgkc">
								<input type="radio" name="bkhywbjgkc" value="有">&nbsp;&nbsp;有
							         	<input type="radio" name="bkhywbjgkc" value="无" checked>&nbsp;&nbsp;无
							         </logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="bkhywbjgkc">
							<input type="radio" name="bkhywbjgkc" value="有">&nbsp;&nbsp;有 
							         <input type="radio" name="bkhywbjgkc" value="无" checked>&nbsp;&nbsp;无
						         </logic:notPresent>
					</td>
					<td>
						<div align="center">
							操行等第
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
							平时表现
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
							奖惩情况
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
							&nbsp;&nbsp;&nbsp;每年家庭提供费用
							<input type="text" id="mnjttgfy" maxlength="10" name="mnjttgfy"
								style="width:10%;heigh:100%" onblur="jehj1();" 
								value="<bean:write name="rs" property="mnjttgfy"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							&nbsp;元， 亲朋好友等提供
							<input type="text" id="mnqphytgfy" maxlength="10" onblur="jehj1();" 
								name="mnqphytgfy" style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="mnqphytgfy"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							&nbsp;元， 合计每年提供
							<input type="text" id="mnhjtgfy" maxlength="10" name="mnhjtgfy"
								style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="mnhjtgfy"/>"
								readonly="readonly">
							&nbsp;元
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="7">
						<div align="left">
							每年应缴纳各种费用
							<input type="text" id="mnyjgzfy" maxlength="6" name="mnyjgzfy"
								style="width:10%;heigh:100%" onblur="jehj2();" 
								value="<bean:write name="rs" property="mnyjgzfy"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							&nbsp;元， 每月平均生活费
							<input type="text" id="mypjshf" maxlength="5" name="mypjshf"
								style="width:10%;heigh:100%" onblur="jehj2();"
								value="<bean:write name="rs" property="mypjshf"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							&nbsp;元， 合计每年费用
							<input type="text" id="mnhjfy" maxlength="10" name="mnhjfy"
								style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="mnhjfy"/>"
								readonly="readonly">
							&nbsp;元
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="7">
						<div align="left">
							每年参加院内勤工助学获补贴
							<input type="text" id="mncjynqgzxbt" maxlength="10"
								name="mncjynqgzxbt" style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="mncjynqgzxbt"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							&nbsp;元，
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							参加院外勤工助学活动获报酬
							<input type="text" id="cjywqgzxbc" maxlength="10"
								name="cjywqgzxbc" style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="cjywqgzxbc"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							&nbsp;元
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							意外伤害和疾病概况
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
							诊治医院
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
							<font color="red">*</font>伤、病时间
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
							是否欠费
						</div>
					</td>
					<td colspan="2" align="center">
						<logic:present name="rs" property="sfqf">
							<logic:match value="是" name="rs" property="sfqf">
								<input type="radio" name="sfqf" value="是" checked>&nbsp;&nbsp;是
							         	<input type="radio" name="sfqf" value="否">&nbsp;&nbsp;否
							         </logic:match>
							<logic:match value="否" name="rs" property="sfqf">
								<input type="radio" name="sfqf" value="是">&nbsp;&nbsp;是
							         	<input type="radio" name="sfqf" value="否" checked>&nbsp;&nbsp;否
							         </logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfqf">
							<input type="radio" name="sfqf" value="是">&nbsp;&nbsp;是 
							         <input type="radio" name="sfqf" value="否" checked>&nbsp;&nbsp;否
						         </logic:notPresent>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							医药费
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
							其它费用
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
							家庭保险理赔款
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
							<bean:message key="lable.xsgzyxpzxy" />保险理赔款
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
							家庭经济状况及申请伤、病困难补助的理由
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
							有无家庭所在地村、镇(居委、街办)出具的有关证明：&nbsp;&nbsp;
							<logic:present name="rs" property="ywzm">
								<logic:match value="有" name="rs" property="ywzm">
									<input type="radio" name="ywzm" value="有" checked>&nbsp;&nbsp;有
							         	<input type="radio" name="ywzm" value="无">&nbsp;&nbsp;无
							         </logic:match>
								<logic:match value="无" name="rs" property="ywzm">
									<input type="radio" name="ywzm" value="有">&nbsp;&nbsp;有
							         	<input type="radio" name="ywzm" value="无" checked>&nbsp;&nbsp;无
							         </logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="ywzm">
								<input type="radio" name="ywzm" value="有">&nbsp;&nbsp;有 
							         <input type="radio" name="ywzm" value="无" checked>&nbsp;&nbsp;无
						         </logic:notPresent>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 拟申请补助金额:
							<input type="text" id="nsqbzje" maxlength="10" name="nsqbzje"
								style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="nsqbzje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							&nbsp;元
						</div>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2"
					onClick="yz(document.getElementById('titName').value);">
					提交申请
				</button>
				<button class="button2"
					onClick="toPrintOut(document.getElementById('titName').value);">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>

		</html:form>
</body>
</html:html>
