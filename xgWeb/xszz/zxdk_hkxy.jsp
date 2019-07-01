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
		function yz(){
			var xh = document.getElementById('xh').value;
			var sfzh = 	document.getElementById('sfzh').value;
			var lxdh = 	document.getElementById('lxdh').value;
			var yddh = 	document.getElementById('yddh').value;
			var email = document.getElementById('email').value;
			var lxrdh = document.getElementById('lxrdh').value;
			var jtxxzz = document.getElementById('jtxxzz').value;
			var jtlxdh = document.getElementById('jtlxdh').value;
			var jtyzbm = document.getElementById('jtyzbm').value;
			var fqxm = document.getElementById('fqxm').value;
			var mqxm = document.getElementById('mqxm').value;
			var fqzy = document.getElementById('fqzy').value;
			var mqzy = document.getElementById('mqzy').value;
			var fqsfzh = document.getElementById('fqsfzh').value;
			var mqsfzh = document.getElementById('mqsfzh').value;
			var hkcs = document.getElementById('hkcs').value;
			var hkqx = document.getElementById('hkqx').value;
			var zdhkqx = document.getElementById('zdhkqx').value;
			var hth = document.getElementById('hth').value;
			var zffm = document.getElementById('zffm').value;
			var zfh = document.getElementById('zfh').value;
			var hkkssj = document.getElementById('hkkssj').value;
			var hkjssj = document.getElementById('hkjssj').value;
			
			if(xh == ""){
				alert("学号不能为空！");
				return false;
			}
			if(!checkSfzh(sfzh)){
				return false;
			}
			if(lxdh == ""){
				alert("联系电话不能为空!");
				return false;
			}
			if(!isNumber(lxdh)){
				alert("联系电话必须为整数!");
				return false;
			}
			if((yddh != null) && (yddh != "") && (!isNumber(yddh))){
				alert("移动电话必须为整数!");
				return false;
			}
			if((email != null) && (email != "") && (!isEmail(email))){
				alert("E-MAIL格式不正确!");
				return false;
			}
			if((lxrdh != null) && (lxrdh != "") && (!isNumber(lxrdh))){
				alert("联系人电话必须为整数!");
				return false;
			}
			if(jtxxzz == ""){
				alert("家庭详细住址不能为空!");
				return false;
			}
			if(jtlxdh == ""){
				alert("家庭联系电话不能为空!");
				return false;
			}
			if(jtyzbm == ""){
				alert("家庭邮政编码不能为空!");
				return false;
			}
			if(fqxm == ""){
				alert("父亲姓名不能为空!");
				return false;
			}
			if(mqxm == ""){
				alert("母亲姓名不能为空!");
				return false;
			}
			if(fqzy == ""){
				alert("父亲职业不能为空!");
				return false;
			}
			if(mqzy == ""){
				alert("母亲职业不能为空!");
				return false;
			}
			if(fqsfzh == ""){
				alert("父亲身份证号不能为空!");
				return false;
			}
			if(mqsfzh == ""){
				alert("母亲身份证号不能为空!");
				return false;
			}
			if(hkcs == ""){
				alert("还款期数不能为空!");
				return false;
			}
			if(!isNumber(hkcs)){
				alert("还款期数必须为整数!");
				return false;
			}
			if(hkqx == ""){
				alert("还款期限不能为空!");
				return false;
			}
			if(!isNumber(hkqx)){
				alert("还款期限必须为整数!");
				return false;
			}
			if(Math.round(hkqx) > Math.round(zdhkqx)){
				alert("还款期限不能大于最大还款期限!");
				return false;
			}
			if(hth == ""){
				alert("合同号不能为空!");
				return false;
			}
			if(zffm == ""){
				alert("帐户户名不能为空!");
				return false;
			}
			if(zfh == ""){
				alert("帐户号不能为空!");
				return false;
			}
			if(!isNumber(zfh)){
				alert("帐户号必须为整数!");
				return false;
			}
			if(hkkssj == ""){
				alert("还款开始时间不能为空!");
				return false;
			}
			if(hkjssj == ""){
				alert("还款结束时间不能为空!");
				return false;
			}
			if(hkkssj > hkjssj){
				alert("还款开始时间不能晚于还款结束时间!");
				return false;
			}
			
			document.forms[0].action = "/xgxt/hkxy.do?doType=add";
			document.forms[0].submit();
		}
		
		
		function ht(){
			document.forms[0].action = "/xgxt/hkxy.do?doType=htxx";
			document.forms[0].submit();
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		
		function isEmail(s){
			s = trim(s); 
 			var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
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
				alert("请输入正确的身份证号码！","",function(){
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
				alert("请输入正确的身份证号码！","",function(){
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
			当前所在位置：学生资助 - 还款协议
		</div>
	</div>
	<logic:equal name="sfSD" value="no">
		<center>
			<p>
				还款协议的相关设定尚未设置，现在不能申请！！
			</p>
		</center>
	</logic:equal>
	<logic:equal name="sfSD" value="is">
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="hkxy.do" method="post">
			<input type="hidden" id="url" name="url" value="/hkxy.do" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="zdhkqx" name="zdhkqx"
				value="<bean:write name="rs" property="zdhkqx"/>">

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

			<logic:present name="notFK">
				<logic:match value="is" name="notFK">
					<script language="javascript">
	         	alert("该合同并未放款，不需要填写还款协议！");
	         	</script>
				</logic:match>
			</logic:present>

			<logic:notEmpty name="isNULL">
				<logic:equal name="isNULL" value="is">
					<script language="javascript">
	         	alert("没找到该学生信息或该学生没有合同信息！");
	         	</script>
				</logic:equal>
			</logic:notEmpty>

			<table width="100%" border="1" class="tbstyle">
				<tr>
					<td align="center" scope="col" colspan="4">
						<strong>个人信息</strong>
					</td>
				</tr>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="right" width="16%" scope="col">
							<font color="red">*</font>学号：
						</td>
						<td align="left" width="34%" scope="col">
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
						<td align="right" width="16%" scope="col">
							<font color="red">*</font>学号：
						</td>
						<td align="left" width="34%" scope="col">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="xh" />">
						</td>
					</logic:equal>
					<td width="16%" scope="col">
						<div align="right">
							姓名：
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
					<td>
						<div align="right">
							性别：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xb" name="xb"
								style="width:100%;heigh:100%" readonly="true"
								value="<bean:write name='rs' property="xb" />">
						</div>
					</td>
					<td scope="row">
						<div align="right">
							<font color="red">*</font>身份证号：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="sfzh" name="sfzh"
								style="width:100%;heigh:100%" maxlength="18"
								value="<bean:write name='rs' property="sfzh" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							出生日期：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="csrq" name="csrq"
								style="width:100%;heigh:100%" readonly="true"
								value="<bean:write name='rs' property="csrq" />">
						</div>
					</td>
					<td scope="row">
						<div align="right">
							学历：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xl" name="xl"
								style="width:100%;heigh:100%" readonly="true"
								value="<bean:write name='rs' property="xl" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							学校名称：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xxmc" name="xxmc"
								style="width:100%;heigh:100%" readonly="true"
								value="<bean:write name='rs' property="xxmc" />">
						</div>
					</td>
					<td>
						<div align="right">
							<bean:message key="lable.xsgzyxpzxy" />名称：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xymc" name="xymc"
								style="width:100%;heigh:100%" readonly="true"
								value="<bean:write name='rs' property="xymc" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							系名称：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="xmc" name="xmc"
								style="width:100%;heigh:100%" readonly="true"
								value="<bean:write name='rs' property="xmc" />">
						</div>
					</td>
					<td>
						<div align="right">
							<font color="red">*</font>联系电话：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="lxdh" name="lxdh"
								style="width:100%;heigh:100%" maxlength="12"
								value="<bean:write name='rs' property="lxdh" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							婚姻状况：
						</div>
					</td>
					<td>
						<div align="center">
							<logic:present name="rs" property="hyzk">
								<logic:match value="未婚" name="rs" property="hyzk">
									<input type="radio" name="hyzk" value="未婚" checked>&nbsp;&nbsp;未婚
							         <input type="radio" name="hyzk" value="已婚">&nbsp;&nbsp;已婚
							         <input type="radio" name="hyzk" value="离婚">&nbsp;&nbsp;离婚
							         <input type="radio" name="hyzk" value="丧偶">&nbsp;&nbsp;丧偶
							         </logic:match>
								<logic:match value="已婚" name="rs" property="hyzk">
									<input type="radio" name="hyzk" value="未婚">&nbsp;&nbsp;未婚
							         <input type="radio" name="hyzk" value="已婚" checked>&nbsp;&nbsp;已婚
							         <input type="radio" name="hyzk" value="离婚">&nbsp;&nbsp;离婚
							         <input type="radio" name="hyzk" value="丧偶">&nbsp;&nbsp;丧偶
							         </logic:match>
								<logic:match value="离婚" name="rs" property="hyzk">
									<input type="radio" name="hyzk" value="未婚">&nbsp;&nbsp;未婚
							         <input type="radio" name="hyzk" value="已婚">&nbsp;&nbsp;已婚
							         <input type="radio" name="hyzk" value="离婚" checked>&nbsp;&nbsp;离婚
							         <input type="radio" name="hyzk" value="丧偶">&nbsp;&nbsp;丧偶
							         </logic:match>
								<logic:match value="丧偶" name="rs" property="hyzk">
									<input type="radio" name="hyzk" value="未婚">&nbsp;&nbsp;未婚
							         <input type="radio" name="hyzk" value="已婚">&nbsp;&nbsp;已婚
							         <input type="radio" name="hyzk" value="离婚">&nbsp;&nbsp;离婚
							         <input type="radio" name="hyzk" value="丧偶" checked>&nbsp;&nbsp;丧偶
							         </logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="hyzk">
								<input type="radio" name="hyzk" value="未婚" checked>&nbsp;&nbsp;未婚
							         <input type="radio" name="hyzk" value="已婚">&nbsp;&nbsp;已婚
							         <input type="radio" name="hyzk" value="离婚">&nbsp;&nbsp;离婚
							         <input type="radio" name="hyzk" value="丧偶">&nbsp;&nbsp;丧偶
						         </logic:notPresent>
						</div>
					</td>
					<td>
						<div align="right">
							配偶姓名：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="poxm" name="poxm"
								style="width:100%;heigh:100%" maxlength="30"
								value="<bean:write name='rs' property="poxm" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							工作单位：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="gzdw" name="gzdw"
								style="width:100%;heigh:100%" readonly="true"
								value="<bean:write name='rs' property="gzdw" />">
						</div>
					</td>
					<td>
						<div align="right">
							单位电话：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="dwdh" name="dwdh" readonly="true"
								style="width:100%;heigh:100%" maxlength="12"
								value="<bean:write name='rs' property="dwdh" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							单位地址：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="dwdz" name="dwdz"
								style="width:100%;heigh:100%" readonly="true"
								value="<bean:write name='rs' property="dwdz" />">
						</div>
					</td>
					<td>
						<div align="right">
							单位邮政编码：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="dwyzbm" name="dwyzbm" readonly="true"
								style="width:100%;heigh:100%" maxlength="6"
								value="<bean:write name='rs' property="dwyzbm" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							移动电话：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="yddh" name="yddh"
								style="width:100%;heigh:100%" maxlength="11"
								value="<bean:write name='rs' property="yddh" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="right">
							E-MAIL地址：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="email" name="email"
								style="width:100%;heigh:100%" maxlength="30"
								value="<bean:write name='rs' property="email" />">
						</div>
					</td>
				</tr>
				<tr>
					<td align="center" scope="col" colspan="4">
						<strong>联系人信息</strong>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							联系人姓名：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="lxrxm" name="lxrxm"
								style="width:100%;heigh:100%" maxlength="30"
								value="<bean:write name='rs' property="lxrxm" />">
						</div>
					</td>
					<td>
						<div align="right">
							联系人性别：
						</div>
					</td>
					<td>
						<div align="center">
							<logic:present name="rs" property="lxrxb">
								<logic:match value="男" name="rs" property="lxrxb">
									<input type="radio" name="lxrxb" value="男" checked>&nbsp;&nbsp;男
							         	<input type="radio" name="lxrxb" value="女">&nbsp;&nbsp;女
							         </logic:match>
								<logic:match value="女" name="rs" property="lxrxb">
									<input type="radio" name="lxrxb" value="男">&nbsp;&nbsp;男
							         	<input type="radio" name="lxrxb" value="女" checked>&nbsp;&nbsp;女
							         </logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="lxrxb">
								<input type="radio" name="lxrxb" value="男" checked>&nbsp;&nbsp;男
							         <input type="radio" name="lxrxb" value="女">&nbsp;&nbsp;女
						         </logic:notPresent>
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							联系人出生日期：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('lxrcsrq','y-mm-dd');"
								value="<bean:write name='rs' property="lxrcsrq" />"
								name="lxrcsrq" id="lxrcsrq" />
						</div>
					</td>
					<td>
						<div align="right">
							与借款人关系：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="lxrgx" name="lxrgx"
								style="width:100%;heigh:100%" maxlength="20"
								value="<bean:write name='rs' property="lxrgx" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							联系人电话：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="lxrdh" name="lxrdh"
								style="width:100%;heigh:100%" maxlength="13"
								value="<bean:write name='rs' property="lxrdh" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="right">
							联系人单位/地址：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="lxrdwdz" name="lxrdwdz"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="lxrdwdz" />">
						</div>
					</td>
				</tr>
				<tr>
					<td align="center" scope="col" colspan="4">
						<strong>家庭信息</strong>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							<font color="red">*</font>家庭详细住址：
						</div>
					</td>
					<td colspan="3">
						<div align="left">
							<input type="text" id="jtxxzz" name="jtxxzz"
								style="width:100%;heigh:100%" maxlength="60"
								value="<bean:write name='rs' property="jtxxzz" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							<font color="red">*</font>家庭邮编：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="jtyzbm" name="jtyzbm"
								style="width:100%;heigh:100%" maxlength="6"
								value="<bean:write name='rs' property="jtyzbm" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="right">
							<font color="red">*</font>家庭联系电话：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="jtlxdh" name="jtlxdh"
								style="width:100%;heigh:100%" maxlength="13"
								value="<bean:write name='rs' property="jtlxdh" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							<font color="red">*</font>父亲姓名：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="fqxm" name="fqxm"
								style="width:100%;heigh:100%" maxlength="30"
								value="<bean:write name='rs' property="fqxm" />">
						</div>
					</td>
					<td>
						<div align="right">
							<font color="red">*</font>母亲姓名：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="mqxm" name="mqxm"
								style="width:100%;heigh:100%" maxlength="30"
								value="<bean:write name='rs' property="mqxm" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							<font color="red">*</font>父亲职业：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="fqzy" name="fqzy"
								style="width:100%;heigh:100%" maxlength="30"
								value="<bean:write name='rs' property="fqzy" />">
						</div>
					</td>
					<td>
						<div align="right">
							<font color="red">*</font>母亲职业：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="mqzy" name="mqzy"
								style="width:100%;heigh:100%" maxlength="30"
								value="<bean:write name='rs' property="mqzy" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							<font color="red">*</font>父亲身份证号码：
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
						<div align="right">
							<font color="red">*</font>母亲身份证号码：
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
					<td align="center" scope="col" colspan="4">
						<strong>其它信息</strong>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							<font color="red">*</font>合同号：
						</div>
					</td>
					<td>
						<logic:equal name="isStu" value="is">
							<div align="left">
								<html:select name="rs" property="hth" style="width:100%"
									onchange="ht();">
									<html:option value="">
										<bean:write name='rs' property="hth" />
									</html:option>
									<html:options collection="hthList" property="hth"
										labelProperty="hth"></html:options>
								</html:select>
							</div>
						</logic:equal>
					</td>
					<logic:notEqual name="isGD" value="is">
						<td>
							<div align="right">
								<font color="red">*</font>还款方式2：
							</div>
						</td>
						<td>
							<div align="left">
								<html:select name="rs" property="hkfsdm" style="width:100%">
									<html:option value=""></html:option>
									<html:options collection="hkList" property="hkfsdm"
										labelProperty="hkfs"></html:options>
								</html:select>
							</div>
						</td>
					</logic:notEqual>
					<logic:notEqual name="isGD" value="no">
						<td scope="row">
							<div align="right">
								<font color="red">*</font>还款方式2：
							</div>
						</td>
						<td>
							<div align="left">
								<html:select name="rs" property="hkfsdm" style="width:100%"
									disabled="true">
									<html:option value="">
										<bean:write name='rs' property="hkfs" />
									</html:option>
									<html:options collection="hkList" property="hkfsdm"
										labelProperty="hkfs"></html:options>
								</html:select>
							</div>
						</td>
					</logic:notEqual>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							<font color="red">*</font>还款方式1：
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<logic:present name="rs" property="hkfs1">
								<logic:match value="等额本息还款法" name="rs" property="hkfs1">
									<input type="radio" name="hkfs1" value="等额本息还款法" checked>&nbsp;&nbsp;等额本息还款法
							         	<input type="radio" name="hkfs1" value="等额本金还款法">&nbsp;&nbsp;等额本金还款法
							         </logic:match>
								<logic:match value="等额本金还款法" name="rs" property="hkfs1">
									<input type="radio" name="hkfs1" value="等额本息还款法">&nbsp;&nbsp;等额本息还款法
							         	<input type="radio" name="hkfs1" value="等额本金还款法"
										checked>&nbsp;&nbsp;等额本金还款法
							         </logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="hkfs1">
								<input type="radio" name="hkfs1" value="等额本息还款法" checked>&nbsp;&nbsp;等额本息还款法
							         <input type="radio" name="hkfs1" value="等额本金还款法">&nbsp;&nbsp;等额本金还款法
						         </logic:notPresent>
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							最后放款日期：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="zhfkrq" name="zhfkrq"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="zhfkrq" />">
						</div>
					</td>
					<td>
						<div align="right">
							放款总金额：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="fkzje" name="fkzje"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="fkzje" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							离校时间：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="lxsj" name="lxsj"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="lxsj" />">
						</div>
					</td>
					<td>
						<div align="right">
							离校原因：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="lxyy" name="lxyy"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="lxyy" />">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							<font color="red">*</font>还款开始时间：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="hkkssj" name="hkkssj"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="hkkssj" />">
						</div>
					</td>
					<td>
						<div align="right">
							<font color="red">*</font>还款结束时间：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" readonly style="cursor:hand;width:100%" onblur="ht();"
								onclick="return showCalendar('hkjssj','y-mm-dd');"
								value="<bean:write name='rs' property="hkjssj" />" name="hkjssj"
								id="hkjssj" />
								<br />
								<font color="red">(注：最长期限为入学后十年，如超过十年将按十年算)</font>
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="4">
						<div align="center">
							分
							<input type="text" id="hkcs" name="hkcs"
								style="width:10%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="hkcs" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							期归还贷款本息，还款期限共
							<input type="text" id="hkqx" name="hkqx"
								style="width:10%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="hkqx" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							月
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							<font color="red">*</font>账户户名：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="zffm" name="zffm"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="zffm" />">
						</div>
					</td>
					<td>
						<div align="right">
							<font color="red">*</font>账户号：
						</div>
					</td>
					<td>
						<div align="left">
							<input type="text" id="zfh" name="zfh" maxlength="30"
								style="width:100%;heigh:100%" maxlength="20"
								value="<bean:write name='rs' property="zfh" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							备注：
						</div>
					</td>
					<td colspan="3">
						<div align="left">
							<input type="text" id="bz" name="bz"
								style="width:100%;heigh:100%" maxlength="200"
								value="<bean:write name='rs' property="bz" />">
						</div>
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn"
				style="position: absolute;width:100%">
				<button class="button2" onClick="yz();">
					保&nbsp;&nbsp;&nbsp;&nbsp;存
				</button>
				<button class="button2" type="reset">
					取&nbsp;&nbsp;&nbsp;&nbsp;消
				</button>
			</div>
	</logic:equal>

		</html:form>
		</logic:equal>
</body>
</html:html>
