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
			var sfzh = document.getElementById('sfzh').value;
			var xslxdh = document.getElementById('xslxdh').value;
			var rxcj = document.getElementById('rxcj').value;
			var sxqpm = document.getElementById('rxcj').value;
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
			var jtzz = document.getElementById('jtzz').value;
			var rcbx = document.getElementById('rcbx').value;
			var jcqk = document.getElementById('jcqk').value;
			var jtjjknzk = document.getElementById('jtjjknzk').value;
			var xnxf = document.getElementById('xnxf').value;
			var jtfybzs = document.getElementById('jtfybzs').value;
			var hjxf = document.getElementById('hjxf').value;
			var bxqdks = document.getElementById('bxqdks').value;
			var bz = document.getElementById('bz').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if((sfzh != null) && (sfzh != "") && (!checkSfzh(sfzh))){
				alert("身份证号格式不正确!");
				return false;
			}
			if((xslxdh != null) && (xslxdh != "") && (!isNumber(xslxdh))){
				alert("学生联系电话必须为整数!");
				return false;
			}
			if((rxcj != null) && (rxcj != "") && (!isNumber(rxcj))){
				alert("入学成绩必须为整数!");
				return false;
			}
			if((sxqpm != null) && (sxqpm != "") && (!isNumber(sxqpm))){
				alert("上学期班内排名必须为整数!");
				return false;
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
			if(jtzz != null){
	         	if(jtzz.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("家庭地址不能大于100个字符");
	          		 return false;
	       		 }
			}
			if(rcbx != null){
	         	if(rcbx.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("日常表现不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if(jcqk != null){
	         	if(jcqk.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("奖惩情况不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if(jtjjknzk != null){
	         	if(jtjjknzk.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("家庭经济困难情况不能大于1000个字符");
	          		 return false;
	       		 }
	       	}
	       	if((xnxf != null) && (xnxf != "") && (!isNumber(xnxf))){
				alert("全学年学费必须为整数!");
				return false;
			}
			if((jtfybzs != null) && (jtfybzs != "") && (!isNumber(jtfybzs))){
				alert("家庭提供各种费用不足数必须为整数!");
				return false;
			}
			if((hjxf != null) && (hjxf != "") && (!isNumber(hjxf))){
				alert("缓缴学费必须为整数!");
				return false;
			}
			if((bxqdks != null) && (bxqdks != "") && (!isNumber(bxqdks))){
				alert("本学期拟贷款数必须为整数!");
				return false;
	       	}
	       	if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("备注不能大于200个字符");
	          		 return false;
	       		 }
			}
			
			document.forms[0].action = "/xgxt/jsxx_gjzxdk.do?doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(titName){//输出相应的打印页面
			document.forms[0].action = "/xgxt/jsxx_gjzxdksqb.do";
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
	</script>
</head>

<body onload="loadPage()">
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 国家助学金申请
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="jsxx_gjzxdk.do" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="url" name="url"
				value="/jsxx_gjzxdk.do?jxjlbType=jsxx_gjzxdk" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj"/>">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj"/>">
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd"/>">
			<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj"/>">

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
			<div class="xxk">
				<logic:notEmpty name="pages">
					<logic:iterate id="card" name="pages" scope="request">
						<ul>
							<li id="<bean:write name='card' property='en'/>l"
								class="xxk_off_l"></li>
							<li id="<bean:write name='card' property='en'/>m"
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

			<table class="tbstyle" width="90%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="right" colspan="2">
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
						<td align="right" colspan="2">
							<font color="red">*</font>学号：
						</td>
						<td align="left" colspan="2">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td width="16%">
						<div align="center">
							姓名
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
							出生年月
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="csrq" readonly="readonly" name="csrq"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csrq"/>">
					</td>
					<td>
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<input type="text" id="xb" name="xb" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="sfzh" maxlength="18" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
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
							系
						</div>
					</td>
					<td colspan="2">
						<input type="text" readonly="readonly" id="zymc" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<td>
						<div align="center">
							班
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" readonly="readonly" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							学生联系电话
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="xslxdh" maxlength="15" name="xslxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xslxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							入学成绩
						</div>
					</td>
					<td>
						<input type="text" id="rxcj" name="rxcj" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxcj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							上学期班内排名
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="sxqpm" maxlength="5" name="sxqpm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sxqpm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							课程成绩平均分
						</div>
					</td>
					<td>
						<input type="text" id="pjcj" name="pjcj" maxlength="5"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="pjcj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭住址
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="jtzz" rows='2' style="width:100%" onblur="yzdx(this,'jtzz')"/>
						<input type="hidden" id="jtzz" name="jtzz" value="">
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6">
						<div align="center">
							家
							<br>
							庭
							<br>
							成
							<br>
							员
							<br>
							情
							<br>
							况
						</div>
					</td>
					<td width="12%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="9%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="25%">
						<div align="center">
							年收入
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							工作单位及职务
						</div>
					</td>
				</tr>
				<tr>
					<td width="12%">
						<input type="text" id="jtcy1_xm" maxlength="40" name="jtcy1_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy1_nl" name="jtcy1_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy1_nsr" name="jtcy1_nsr" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_nsr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy1_gzdwjzw" maxlength="80"
							name="jtcy1_gzdwjzw" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_gzdwjzw" />">
					</td>
				</tr>
				<tr>
					<td width="12%">
						<input type="text" id="jtcy2_xm" name="jtcy2_xm" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy2_nl" name="jtcy2_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy2_nsr" name="jtcy2_nsr" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_nsr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy2_gzdwjzw" maxlength="80"
							name="jtcy2_gzdwjzw" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_gzdwjzw" />">
					</td>
				</tr>
				<tr>
					<td width="12%">
						<input type="text" id="jtcy3_xm" name="jtcy3_xm" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy3_nl" name="jtcy3_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy3_nsr" name="jtcy3_nsr" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_nsr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy3_gzdwjzw" maxlength="80"
							name="jtcy3_gzdwjzw" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_gzdwjzw" />">
					</td>
				</tr>
				<tr>
					<td width="12%">
						<input type="text" id="jtcy4_xm" name="jtcy4_xm" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy4_nl" name="jtcy4_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy4_nsr" name="jtcy4_nsr" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_nsr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy4_gzdwjzw" maxlength="80"
							name="jtcy4_gzdwjzw" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_gzdwjzw" />">
					</td>
				</tr>
				<tr>
					<td width="12%">
						<input type="text" id="jtcy5_xm" name="jtcy5_xm" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy5_nl" name="jtcy5_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_nl" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<input type="text" id="jtcy5_nsr" name="jtcy5_nsr" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_nsr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<input type="text" id="jtcy5_gzdwjzw" maxlength="80"
							name="jtcy5_gzdwjzw" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_gzdwjzw" />">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							日常表现
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="rcbx" rows='3' style="width:100%" onblur="yzdx(this,'rcbx')"/>
						<input type="hidden" id="rcbx" name="rcbx" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							奖惩情况
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="jcqk" rows='5' style="width:100%" onblur="yzdx(this,'jcqk')"/>
						<input type="hidden" id="jcqk" name="jcqk" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭经济困难状况
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="jtjjknzk" rows='10' style="width:100%" onblur="yzdx(this,'jtjjknzk')"/>
						<input type="hidden" id="jtjjknzk" name="jtjjknzk" value="">
					</td>
				</tr>
				<tr>
					<td colspan="6" width="100%">
						<div align="center">
							全学年学费&nbsp;&nbsp;
							<input name="xnxf" id="xnxf" maxlength="10" type="text"
								style="width:20%;heigh:100%"
								value="<bean:write name="rs" property="xnxf"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							&nbsp;元， 家庭提供培养费生活费不足数：&nbsp;&nbsp;
							<input type="text" id="jtfybzs" name="jtfybzs" maxlength="10"
								style="width:20%;heigh:100%"
								value="<bean:write name="rs" property="jtfybzs"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							&nbsp;元
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							缓缴学费
						</div>
					</td>
					<td colspan="2">
						<input name="hjxf" id="hjxf" maxlength="10" type="text"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hjxf"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							本学期拟贷款数
						</div>
					</td>
					<td>
						<input type="text" id="bxqdks" name="bxqdks" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bxqdks"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							计划还款开始时间
						</div>
					</td>
					<td colspan="2">
						<input type="text" readonly style="cursor:hand;width:90%"
							onclick="return showCalendar('jhhkkssj','y');"
							value="<bean:write name='rs' property="jhhkkssj" />"
							name="jhhkkssj" id="jhhkkssj" />
						&nbsp;&nbsp;年
					</td>
					<td>
						<div align="center">
							计划还款截止时间
						</div>
					</td>
					<td>
						<input type="text" readonly style="cursor:hand;width:90%"
							onclick="return showCalendar('jhhkjssj','y');"
							value="<bean:write name='rs' property="jhhkjssj" />"
							name="jhhkjssj" id="jhhkjssj" />
						&nbsp;&nbsp;年
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							备注
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="bz" rows='3' style="width:100%" onblur="yzdx(this,'bz')"/>
						<input type="hidden" id="bz" name="bz" value="">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
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
	</logic:equal>

		</html:form>
</body>
</html:html>
