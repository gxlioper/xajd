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
				alert('学号不能为空!');
				return false;
			}
			if((ssdh != null) && (ssdh != "") && (!isNumber(ssdh))){
				alert("宿舍电话必须为整数!");
				return false;
			}
			if(dkze == ""){
				alert('贷款总额不能为空!');
				return false;
			}
			if(xfdks == ""){
				alert('学费贷款数不能为空!');
				return false;
			}
			if(zsfdks == ""){
				alert('住宿费贷款数不能为空!');
				return false;
			}
			if(shfdkz == ""){
				alert('生活费贷款数不能为空!');
				return false;
			}
			if(!isNumber(dkze)){
				alert('贷款总额必须为正整数!');
				return false;
			}
			if(!isNumber(xfdks)){
				alert('学费贷款数必须为正整数!');
				return false;
			}
			if(!isNumber(zsfdks)){
				alert('住宿费贷款数必须为正整数!');
				return false;
			}
			if(!isNumber(shfdkz)){
				alert('生活费贷款数必须为正整数!');
				return false;
			}
			if(dkze != (Math.round(xfdks)+Math.round(zsfdks)+Math.round(shfdkz))){
				alert('贷款总额和各贷款数之和不相等!');
				return false;
			}
			if(jtrjsr == ""){
				alert('家庭人均收入不能为空!');
				return false;
			}
			if(jtsr == ""){
				alert('家庭月收入不能为空!');
				return false;
			}
			if(jtzz == ""){
				alert('家庭住址不能为空!');
				return false;
			}
			if(yzbm == ""){
				alert('邮政编码不能为空!');
				return false;
			}
			if(dh == ""){
				alert('联系电话不能为空!');
				return false;
			}
			if(fqxm == ""){
				alert('父亲姓名不能为空!');
				return false;
			}
			if(mqxm == ""){
				alert('母亲姓名不能为空!');
				return false;
			}
			if(fqzy == null){
				alert('父亲职业不能为空!');
				return false;
			}
			if(mqzy == ""){
				alert('母亲职业不能为空!');
				return false;
			}
			if(fqzy == ""){
				alert('父亲身份证号不能为空!');
				return false;
			}
			if(mqzy == ""){
				alert('母亲身份证号不能为空!');
				return false;
			}
			document.forms[0].action = "/xgxt/zxdksq.do?jxjlbType=zxdksq&doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		
		function toPrintOut(titName){//输出相应的打印页面
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
			当前所在位置：学生资助 - 助学贷款申请
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内！！！
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
						<td align="center" width="16%" scope="col">
							<font color="red">*</font>学号：
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
							姓名
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
							性别
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
							出生年月
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
							学制
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
							学历
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
							学校名称
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
							身份证号
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
							<bean:message key="lable.xsgzyxpzxy" />名称
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
							专业名称
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
							宿舍电话
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
							卡号
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
							申请总金额
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
							<font color="red">*</font>学费贷款数
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
							<font color="red">*</font>住宿费贷款数
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
							<font color="red">*</font>生活费贷款数
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
							贷款期限
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
							至
						</div>
					</td>
					<td>
						<div>
							<input type="text" readonly style="cursor:hand;width:80px"
								onclick="return showCalendar('dkqxjssj','y-mm-dd');"
								value="<bean:write name='rs' property="dkqxjssj" />"
								name="dkqxjssj" id="dkqxjssj" />
								<br />
								<font color="red">(注：最长期限为入学后十年，如超过十年将按十年算)</font>
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>家庭人均收入
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
							<font color="red">*</font>家庭月收入
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
							<font color="red">*</font>家庭住址
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
							<font color="red">*</font>邮政编码
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
							<font color="red">*</font>联系电话
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
							<font color="red">*</font>父亲姓名
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
							<font color="red">*</font>母亲姓名
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
							<font color="red">*</font>父亲职业
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
							<font color="red">*</font>母亲职业
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
							<font color="red">*</font>父亲身份证号
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
							<font color="red">*</font>母亲身份证号
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
						<td align="center" width="16%" scope="col">
							<font color="red">*</font>学号：
						</td>
						<td align="left" width="34%" scope="col">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td width="16%" scope="col">
						<div align="center">
							姓名
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
							性别
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
							出身年月
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
							学制
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
							学历
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
							学校名称
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
							身份证号
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
							<bean:message key="lable.xsgzyxpzxy" />名称
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
							专业名称
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
							宿舍电话
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
							卡号
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
							申请总金额
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
							<font color="red">*</font>学费贷款数
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
							<font color="red">*</font>住宿费贷款数
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
							<font color="red">*</font>生活费贷款数
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
							贷款期限
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
							至
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
							<font color="red">*</font>家庭人均收入
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
							<font color="red">*</font>家庭月收入
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
							<font color="red">*</font>家庭住址
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
							<font color="red">*</font>邮政编码
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
							<font color="red">*</font>联系电话
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
							<font color="red">*</font>父亲姓名
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
							<font color="red">*</font>母亲姓名
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
							<font color="red">*</font>父亲职业
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
							<font color="red">*</font>母亲职业
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
							<font color="red">*</font>父亲身份证号
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
							<font color="red">*</font>母亲身份证号
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
							注：有*号标记的为必填项。如有父母离异或死亡等特殊情况，请在父亲或母亲的姓名、职业、身份证号上填写父母当前情况。
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
