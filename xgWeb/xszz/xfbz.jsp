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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(realTable){
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
				alert("学号不能为空!");
				return false;
			}
			if((qsdh != null) && (qsdh != "") && (!isNumber(qsdh))){
				alert("寝室电话必须为整数!");
				return false;
			}
			if((yzbm != null) && (yzbm != "") && (!isNumber(yzbm))){
				alert("邮政编码必须为整数!");
				return false;
			}
			if((sfzh != null) && (sfzh != "") && (!checkSfzh(sfzh))){
				return false;
			}
			if((jtcy1_ysr != null) && (jtcy1_ysr != "") && (!isNumber(jtcy1_ysr))){
				alert("家庭成员1月收入必须为整数!");
				return false;
			}
			if((jtcy2_ysr != null) && (jtcy2_ysr != "") && (!isNumber(jtcy2_ysr))){
				alert("家庭成员2月收入必须为整数!");
				return false;
			}
			if((jtcy3_ysr != null) && (jtcy3_ysr != "") && (!isNumber(jtcy3_ysr))){
				alert("家庭成员3月收入必须为整数!");
				return false;
			}
			if((jtcy4_ysr != null) && (jtcy4_ysr != "") && (!isNumber(jtcy4_ysr))){
				alert("家庭成员4月收入必须为整数!");
				return false;
			}
			if((jtcy5_ysr != null) && (jtcy5_ysr != "") && (!isNumber(jtcy5_ysr))){
				alert("家庭成员5月收入必须为整数!");
				return false;
			}
			if((jttgje != null) && (jttgje != "") && (!isNumber(jttgje))){
				alert("本学年家庭提供金额必须为整数!");
				return false;
			}
			if((zxje != null) && (zxje != "") && (!isNumber(zxje))){
				alert("本学年助学金必须为整数!");
				return false;
			}
			if((jxje != null) && (jxje != "") && (!isNumber(jxje))){
				alert("本学年奖学金必须为整数!");
				return false;
			}
			if((qgzxje != null) && (qgzxje != "") && (!isNumber(qgzxje))){
				alert("本学年勤工助学收入必须为整数!");
				return false;
			}
			if((xnwxdkje != null) && (xnwxdkje != "") && (!isNumber(xnwxdkje))){
				alert("本学年校内无息贷学金必须为整数!");
				return false;
			}
			if((qtsrje != null) && (qtsrje != "") && (!isNumber(qtsrje))){
				alert("本学年其他收入必须为整数!");
				return false;
			}
			if((zxdkje != null) && (zxdkje != "") && (!isNumber(zxdkje))){
				alert("助学贷款申请金额必须为整数!");
				return false;
			}
			if((yffzxdkje != null) && (yffzxdkje != "") && (!isNumber(yffzxdkje))){
				alert("助学贷款已发放金额必须为整数!");
				return false;
			}
			if((zzff1qsje != null) && (zzff1qsje != "") && (!isNumber(zzff1qsje))){
				alert("第一志愿起始金额必须为整数!");
				return false;
			}
			if((zzff1jsje != null) && (zzff1jsje != "") && (!isNumber(zzff1jsje))){
				alert("第一志愿结束金额必须为整数!");
				return false;
			}
			if(sqzzly != null){
	         	if(sqzzly.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("申请资助理由不能大于100个字符");
	          		 return false;
	       		 }
			}
			
			document.forms[0].action = "/xgxt/xfbz.do?doType=save";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//输出相应的打印页面
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

	<logic:present name="sfksq">
		<logic:match value="false" name="sfksq">
	         	非申请时间!! 
	         </logic:match>
		<logic:match value="true" name="sfksq">
		<logic:present name="aa">
<script>
		
			alert("确定修改？！");
		</script>
</logic:present>
			<html:form action="xfbz.do" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						<bean:write name="tips" scope="request" />
					</div>
				</div>
				<input type="hidden" id="pk" name="pk" value="xh||bzdm||nd" />
				<input type="hidden" id="realTable" name="realTable" value="xsbzb" />
				<input type="hidden" id="bzlb" name="bzlb" value="xfbz" />

				<input type="hidden" id="url" name="url" value="/xfbz.do" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-bjmc" />

				<logic:present name="doresult">
					<logic:match value="yes" name="doresult">
						<script language="javascript">
	         	alert("保存成功！");
	         	</script>
					</logic:match>
					<logic:match value="no" name="doresult">
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

				<table width="100%" class="tbstyle">
					<%-- 
			  <tr>
			    <td height="36" colspan="3"><input name="aa" type="radio" value="lsknbz">
			&nbsp;临时困难补助</td>
			    <td colspan="3">选择相应的临时困难补助
			     <html:select property="lsknbzdm">
			         <html:option value=" "></html:option> 
			         <html:options collection="lsknbzList" property="lsknbzdm" labelProperty="lsknbzmc"/> 
			         </html:select> 
			    </td>
			  </tr>
			  <tr>
			    <td height="32" colspan="3"><input name="aa" type="radio" value="zxbz">
			&nbsp;专项补助</td>
			    <td height="32" colspan="3">选择相应的专项补助 
			    <html:select property="zxbzdm"> 
			          <html:option value=" "></html:option> 
			          <html:options collection="zxbzList" property="zxbzdm" labelProperty="zxbzmc" /> 
			          </html:select> 
			    </td>
			  </tr>--%>
					<tr>


						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td height="31" colspan="2">
								<div align="left">
									<font color="red">*</font>学号：
								</div>
							</td>
							<td width="26%">
								<div align="left">
									<html:text name='rs' property="xh" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true"/>
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</div>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td height="31" colspan="2">
								<div align="center">
									<font color="red">*</font>学号：
								</div>
							</td>
							<td width="26%">
								<div align="left">
									<input type="text" id="xh" name="xh"
										style="width:100%;heigh:100%"
										value="<bean:write name='rs' property="xh" />" readonly="true">
								</div>
							</td>
						</logic:equal>

						<td height="31" colspan="2">
							姓名
						</td>
						<td width="28%">
							<input type="text" name="xm" id="xm" width="100%" readonly="readonly"
								value="<bean:write  name="rs" property="xm"/>">
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							性别
						</td>
						<td height="31">
							<input type="text" name="xb" id="xb" width="100%" readonly="readonly"
								value="<bean:write name="rs" property="xb" />">
						</td>
						<td height="31" colspan="2">
							民族
						</td>
						<td height="31">
							<input type="text" name="mzmc" id="mzmc" width="100%" readonly="readonly"
								value="<bean:write name="rs" property="mzmc" />">
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							毕业时间
						</td>
						<td height="31">
							<input type="text" readonly style="cursor:hand;width:100%"
										onclick="return showCalendar('bysj','y-mm-dd');"
										value="<bean:write name='rs' property="bysj" />"
										name="bysj" id="bysj" />
						</td>
						<td height="31" colspan="2">
							专业年级
						</td>
						<td height="31">
							<input type="text" name="zynj" id="zynj" width="100%" readonly="readonly"
								value="<bean:write name="rs" property="zynj" />">
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							寝室号码
						</td>
						<td height="31">
							<input type="text" name="qsh" id="qsh" width="100%" maxlength="20"
								value="<bean:write name="rs" property="qsh" />">
						</td>
						<td height="31" colspan="2">
							寝室电话
						</td>
						<td height="31">
							<input type="text" name="qsdh" id="qsdh" width="100%" maxlength="12"
								value="<bean:write name="rs" property="qsdh" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							家庭地址
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtdz" id="jtdz" maxlength="50"
								value="<bean:write  name="rs" property="jtdz"/>">
						</td>
						<td height="31" colspan="2">
							邮编
						</td>
						<td height="31">
							<input type="text" width="100%" name="yzbm" id="yzbm" maxlength="6"
								value="<bean:write  name="rs" property="yzbm"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							身份证号码
						</td>
						<td height="31">
							<input type="text" name="sfzh" id="sfzh" width="100%" maxlength="18"
								value="<bean:write name="rs" property="sfzh" />" readonly="true">
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
							家庭成员1姓名
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy1_xm" id="jtcy1_xm" maxlength="50"
								value="<bean:write  name="rs" property="jtcy1_xm"/>">
						</td>
						<td height="31" colspan="2">
							与家庭成员1关系
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy1_gx" id="jtcy1_gx" maxlength="50"
								value="<bean:write  name="rs" property="jtcy1_gx"/>">
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							家庭成员1工作单位
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy1_gzdw" id="jtcy1_gzdw" maxlength="100"
								value="<bean:write  name="rs" property="jtcy1_gzdw"/>">
						</td>
						<td height="31" colspan="2">
							家庭成员1月收入
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy1_ysr" id="jtcy1_ysr" maxlength="6"
								value="<bean:write  name="rs" property="jtcy1_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							家庭成员2姓名
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy2_xm" id="jtcy2_xm" maxlength="50"
								value="<bean:write  name="rs" property="jtcy2_xm"/>">
						</td>
						<td height="31" colspan="2">
							与家庭成员2关系
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy2_gx" id="jtcy2_gx" maxlength="50"
								value="<bean:write  name="rs" property="jtcy2_gx"/>">
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							家庭成员2工作单位
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy2_gzdw" id="jtcy2_gzdw" maxlength="100"
								value="<bean:write  name="rs" property="jtcy2_gzdw"/>">
						</td>
						<td height="31" colspan="2">
							家庭成员2月收入
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy2_ysr" id="jtcy2_ysr" maxlength="6"
								value="<bean:write  name="rs" property="jtcy2_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							家庭成员3姓名
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy3_xm" id="jtcy3_xm" maxlength="50"
								value="<bean:write  name="rs" property="jtcy3_xm"/>">
						</td>
						<td height="31" colspan="2">
							与家庭成员3关系
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy3_gx" id="jtcy3_gx" maxlength="50"
								value="<bean:write  name="rs" property="jtcy3_gx"/>">
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							家庭成员3工作单位
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy3_gzdw" id="jtcy3_gzdw" maxlength="100"
								value="<bean:write  name="rs" property="jtcy3_gzdw"/>">
						</td>
						<td height="31" colspan="2">
							家庭成员3月收入
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy3_ysr" id="jtcy3_ysr" maxlength="6"
								value="<bean:write  name="rs" property="jtcy3_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							家庭成员4姓名
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy4_xm" id="jtcy4_xm" maxlength="50"
								value="<bean:write  name="rs" property="jtcy4_xm"/>">
						</td>
						<td height="31" colspan="2">
							与家庭成员4关系
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy4_gx" id="jtcy4_gx" maxlength="50"
								value="<bean:write  name="rs" property="jtcy4_gx"/>">
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							家庭成员4工作单位
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy4_gzdw" id="jtcy4_gzdw" maxlength="100"
								value="<bean:write  name="rs" property="jtcy4_gzdw"/>">
						</td>
						<td height="31" colspan="2">
							家庭成员4月收入
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy4_ysr" id="jtcy4_ysr" maxlength="6"
								value="<bean:write  name="rs" property="jtcy4_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							家庭成员5姓名
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy5_xm" id="jtcy5_xm" maxlength="50"
								value="<bean:write  name="rs" property="jtcy5_xm"/>">
						</td>
						<td height="31" colspan="2">
							与家庭成员5关系
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy5_gx" id="jtcy5_gx" maxlength="50"
								value="<bean:write  name="rs" property="jtcy5_gx"/>">
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							家庭成员5工作单位
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy5_gzdw" id="jtcy5_gzdw" maxlength="100"
								value="<bean:write  name="rs" property="jtcy5_gzdw"/>">
						</td>
						<td height="31" colspan="2">
							家庭成员5月收入
						</td>
						<td height="31">
							<input type="text" width="100%" name="jtcy5_ysr" id="jtcy5_ysr" maxlength="6"
								value="<bean:write  name="rs" property="jtcy5_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							本学年家庭提供
						</td>
						<td height="31">
							<input type="text" width="100%" name="jttgje" id="jttgje" maxlength="6"
								value="<bean:write  name="rs" property="jttgje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元 /月
						</td>
						<td height="31" colspan="2">
							本学年助学金
						</td>
						<td height="31">
							<input type="text" width="100%" name="zxje" id="zxje" maxlength="6"
								value="<bean:write  name="rs" property="zxje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							本学年奖学金
						</td>
						<td height="31">
							<input type="text" width="100%" name="jxje" id="jxje" maxlength="6"
								value="<bean:write  name="rs" property="jxje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元
						</td>
						<td height="31" colspan="2">
							本学年勤工助学收入
						</td>
						<td height="31">
							<input type="text" width="100%" name="qgzxje" id="qgzxje" maxlength="6"
								value="<bean:write  name="rs" property="qgzxje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							本学年校内无息贷学金
						</td>
						<td height="31">
							<input type="text" width="100%" name="xnwxdkje" id="xnwxdkje" maxlength="6"
								value="<bean:write  name="rs" property="xnwxdkje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元
						</td>
						<td height="31" colspan="2">
							本学年其他收入
						</td>
						<td height="31">
							<input type="text" width="100%" name="qtsrje" id="qtsrje" maxlength="6"
								value="<bean:write  name="rs" property="qtsrje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元
						</td>
					</tr>
					<tr>
						<td width="9%" height="31" rowspan="2">
							助学贷款
						</td>
						<td width="11%">
							申请金额
						</td>
						<td width="26%">
							<input type="text" width="100%" name="zxdkje" id="zxdkje" maxlength="6"
								value="<bean:write  name="rs" property="zxdkje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元
						</td>
						<td width="12%" rowspan="2">
							助学贷款
						</td>
						<td width="14%">
							已发放金额
						</td>
						<td width="28%" height="31">
							<input type="text" width="100%" name="yffzxdkje" id="yffzxdkje" maxlength="6"
								value="<bean:write name="rs" property="yffzxdkje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元
						</td>
					</tr>
					<tr>
						<td width="11%">
							时间
						</td>
						<td>
							<input type="text" readonly style="cursor:hand;width:100%"
										onclick="return showCalendar('zxdksj','y-mm-dd');"
										value="<bean:write name='rs' property="zxdksj" />"
										name="zxdksj" id="zxdksj" />
						</td>
						<td height="31">
							时间
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
							申请资助理由
						</td>
						<td height="31" colspan="4">
							<html:textarea name="rs" property="sqzzly" rows='5' style="width:100%" onblur="yzdx(this,'sqzzly')"/>
							<input type="hidden" id="sqzzly" name="sqzzly" value="">
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							补助类型：
						</td>
						<td height="31" colspan="4">
							&nbsp;&nbsp;&nbsp; 学费补助
						</td>
					</tr>
					<tr>
						<td height="31" colspan="2">
							第一志愿
						</td>
						<td height="31" colspan="2">
							起始金额
							<input type="text" width="100%" name="zzff1qsje" id="zzff1qsje" maxlength="6"
								value="<bean:write name="rs" property="zzff1qsje" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元
						</td>
						<td height="31" colspan="2">
							结束金额
							<input type="text" width="100%" name="zzff1jsje" id="zzff1jsje" maxlength="6"
								value="<bean:write name="rs" property="zzff1jsje" />"
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元
						</td>
					</tr>
					<%---<tr>
			    <td height="31" colspan="2">第二志愿</td>
			    <td height="31" colspan="2">起始金额
			        <input type="text" width="100%" name="yffzxdksj" id="yffzxdksj">
			        元 </td>
			    <td height="31" colspan="2">结束金额
			        <input type="text" width="100%" name="yffzxjksj" id="yffzxjksj">
			        元 </td>
			  </tr>--%>
				</table>
				<br>
				<font color="red">还款时间的信息请在打印的表中填写</font>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:0px;width:100%">
						<button class="button2"
							onclick="yz(document.forms[0].realTable.value);"
							style="width:80px">
							提交申请
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2"
							onclick="toPrintOut();"
							style="width:80px">
							输出打印
						</button>
					</div>
				</center>

			</html:form>
			<script language="javascript" src="js/bottomButton.js"></script>
		</logic:match>
	</logic:present>
	<script language="javascript">
if(document.getElementById("btn") && !window.dialogArguments){
	document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
	document.getElementById("btn").style.width = "96%";
	window.setInterval("initBTNTool('btn')",1);
}
</script>
</body>
</html:html>
