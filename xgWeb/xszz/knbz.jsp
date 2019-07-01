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
			
			if(xh == null){
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
			
			document.forms[0].action = "/xgxt/knbz.do?doType=save";
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


	<logic:equal value="false" name="sfksq">
	         	非申请时间!! 
	   </logic:equal>
		<logic:present name="aa">
			<script>
		
			alert("确定修改？！");
		</script>
		</logic:present>
		<html:form action="/knbz.do" method="post">
			<input type="hidden" name="bzlb" id="bzlb"
				value="<bean:write name="bzlb" />">
			<input type="hidden" id="url" name="url" value="/xszz/knbz.jsp" />
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
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:write name="tips" scope="request" />
				</div>
			</div>



			<%--
		      <table width="100%" class="tbstyle">
				  <tr>
				  	<td rowspan="2" align="center">选择补助类型：</td>
				    <td height="36" colspan="3" align="center">
				    <input name="bzlb" id="lsknbz" type="radio" value="lsknbz"  onclick="knbzChange(this);">
				      <strong id="a" onclick="knbzChange2(this);">&nbsp;临时困难补助</strong>
				    </td>
				     
				    <td colspan="4">选择相应的临时困难补助
				       <html:select property="lsknbzdm">
				       		<html:option value=" "></html:option>
				       		<html:options collection="lsknbzList" property="lsknbzdm" labelProperty="lsknbzmc"/>
				       </html:select>
				    </td>
				    
				    <td height="32" colspan="4" rowspan="2">选择相应的专项补助:  &nbsp;&nbsp;&nbsp;&nbsp;
				    	<html:select property="zxbzdm" styleId="zxbzdm" style="width:50%">
				    		<html:option value=" " />
				    		<html:options collection="zxbzList" property="zxbzdm" labelProperty="zxbzmc" />
				    	</html:select>
				    </td>
				  </tr>
				  
				  <tr>
				  	 <td height="32" colspan="3" align="center">
					    <input name="bzlb" id="zxbz" type="radio" value="zxbz" onclick="knbzChange(this);">
					    <strong id="b" onclick="knbzChange2(this);">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专项补助&nbsp;&nbsp;
						</strong>
					</td> 
				  </tr>
			 </table>
			 --%>
			<table width="100%" class="tbstyle">
				<tr>

					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td width="11%" height="31">
							<div align="left">
								<font color="red">*</font>学号：
							</div>
						</td>
						<td colspan="2">
							<div align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</div>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td width="11%" height="31">
							<div align="center">
								<font color="red">*</font>学号：
							</div>
						</td>
						<td colspan="2">
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
					<td colspan="2">
						<input type="text" width="100%" id="xm" name="xm"
							value="<bean:write name="rs" property="xm"/>">
					</td>
				</tr>
				<tr>
					<td height="31">
						性别
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" readonly="readonly" id="xb"
							name="xb" value="<bean:write name="rs" property="xb"/>">
					</td>
					<td height="31" colspan="2">
						民族
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="10" name="mzmc"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
				</tr>
				<tr>
					<td height="31">
						毕业时间
					</td>
					<td height="31" colspan="2">
						<input type="text" readonly style="cursor:hand;width:100%"
							onclick="return showCalendar('bysj','y-mm-dd');"
							value="<bean:write name='rs' property="bysj" />" name="bysj"
							id="bysj" />
					</td>
					<td height="31" colspan="2">
						专业年级
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="10" name="zynj"
							id="zynj" value="<bean:write name="rs" property="zynj"/>">
					</td>
				</tr>
				<tr>
					<td height="31">
						寝室号码
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="10" name="qsh" id="qsh"
							value="<bean:write name="rs" property="qsh"/>">
					</td>
					<td height="31" colspan="2">
						寝室电话
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="12" name="qsdh"
							id="qsdh" value="<bean:write name="rs" property="qsdh"/>">
					</td>
				</tr>
				<tr>
					<td height="31">
						家庭地址
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="50" name="jtdz"
							id="jtdz" value="<bean:write name="rs" property="jtdz"/>">
					</td>
					<td height="31" colspan="2">
						邮编
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="6" name="yzbm"
							id="yzbm" value="<bean:write name="rs" property="yzbm"/>">
					</td>
				</tr>
				<tr>
					<td height="31">
						身份证号码
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="18" name="sfzh"
							id="sfzh" value="<bean:write name="rs" property="sfzh"/>">
					</td>
					<td height="31" colspan="2">
						&nbsp;
					</td>
					<td height="31" colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="31">
						家庭成员1姓名
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="50" name="jtcy1_xm"
							id="jtcy1_xm" value="<bean:write name="rs" property="jtcy1_xm"/>">
					</td>
					<td height="31" colspan="2">
						与家庭成员1关系
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="50" name="jtcy1_gx"
							id="jtcy1_gx" value="<bean:write name="rs" property="jtcy1_gx"/>">
					</td>
				</tr>
				<tr>
					<td height="31">
						家庭成员1工作单位
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="100" name="jtcy1_gzdw"
							id="jtcy1_gzdw"
							value="<bean:write name="rs" property="jtcy1_gzdw"/>">
					</td>
					<td height="31" colspan="2">
						家庭成员1月收入
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="6" name="jtcy1_ysr"
							id="jtcy1_ysr"
							value="<bean:write name="rs" property="jtcy1_ysr"/>">
					</td>
				</tr>
				<tr>
					<td height="31">
						家庭成员2姓名
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="50" name="jtcy2_xm"
							id="jtcy2_xm" value="<bean:write name="rs" property="jtcy2_xm"/>">
					</td>
					<td height="31" colspan="2">
						与家庭成员2关系
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="50" name="jtcy2_gx"
							id="jtcy2_gx" value="<bean:write name="rs" property="jtcy2_gx"/>">
					</td>
				</tr>
				<tr>
					<td height="31">
						家庭成员2工作单位
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="100" name="jtcy2_gzdw"
							id="jtcy2_gzdw"
							value="<bean:write name="rs" property="jtcy2_gzdw"/>">
					</td>
					<td height="31" colspan="2">
						家庭成员2月收入
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="6" name="jtcy2_ysr"
							id="jtcy2_ysr"
							value="<bean:write name="rs" property="jtcy2_ysr"/>">
					</td>
				</tr>
				<tr>
					<td height="31">
						家庭成员3姓名
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="50" name="jtcy3_xm"
							id="jtcy3_xm" value="<bean:write name="rs" property="jtcy3_xm"/>">
					</td>
					<td height="31" colspan="2">
						与家庭成员3关系
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="50" name="jtcy3_gx"
							id="jtcy3_gx" value="<bean:write name="rs" property="jtcy3_gx"/>">
					</td>
				</tr>
				<tr>
					<td height="48">
						家庭成员3工作单位
					</td>
					<td height="48" colspan="2">
						<input type="text" width="100%" maxlength="100" name="jtcy3_gzdw"
							id="jtcy3_gzdw"
							value="<bean:write name="rs" property="jtcy3_gzdw"/>">
					</td>
					<td height="48" colspan="2">
						家庭成员3月收入
					</td>
					<td height="48" colspan="2">
						<input type="text" width="100%" maxlength="6" name="jtcy3_ysr"
							id="jtcy3_ysr"
							value="<bean:write name="rs" property="jtcy3_ysr"/>">
					</td>
				</tr>
				<tr>
					<td height="48">
						家庭成员4姓名
					</td>
					<td height="48" colspan="2">
						<input type="text" width="100%" maxlength="50" name="jtcy4_xm"
							id="jtcy4_xm" value="<bean:write name="rs" property="jtcy4_xm"/>">
					</td>
					<td height="48" colspan="2">
						与家庭成员4关系
					</td>
					<td height="48" colspan="2">
						<input type="text" width="100%" maxlength="50" name="jtcy4_gx"
							id="jtcy4_gx" value="<bean:write name="rs" property="jtcy4_gx"/>">
					</td>
				</tr>
				<tr>
					<td height="48">
						家庭成员4工作单位
					</td>
					<td height="48" colspan="2">
						<input type="text" width="100%" maxlength="100" name="jtcy4_gzdw"
							id="jtcy4_gzdw"
							value="<bean:write name="rs" property="jtcy4_gzdw"/>">
					</td>
					<td height="48" colspan="2">
						家庭成员4月收入
					</td>
					<td height="48" colspan="2">
						<input type="text" width="100%" maxlength="6" name="jtcy4_ysr"
							id="jtcy4_ysr"
							value="<bean:write name="rs" property="jtcy4_ysr"/>">
					</td>
				</tr>
				<tr>
					<td height="48">
						家庭成员5姓名
					</td>
					<td height="48" colspan="2">
						<input type="text" width="100%" maxlength="50" name="jtcy5_xm"
							id="jtcy5_xm" value="<bean:write name="rs" property="jtcy5_xm"/>">
					</td>
					<td height="48" colspan="2">
						与家庭成员5关系
					</td>
					<td height="48" colspan="2">
						<input type="text" width="100%" maxlength="50" name="jtcy5_gx"
							id="jtcy5_gx" value="<bean:write name="rs" property="jtcy5_gx"/>">
					</td>
				</tr>
				<tr>
					<td height="48">
						家庭成员5工作单位
					</td>
					<td height="48" colspan="2">
						<input type="text" width="100%" maxlength="100" name="jtcy5_gzdw"
							id="jtcy5_gzdw"
							value="<bean:write name="rs" property="jtcy5_gzdw"/>">
					</td>
					<td height="48" colspan="2">
						家庭成员5月收入
					</td>
					<td height="48" colspan="2">
						<input type="text" width="100%" maxlength="6" name="jtcy5_ysr"
							id="jtcy5_ysr"
							value="<bean:write name="rs" property="jtcy5_ysr"/>">
					</td>
				</tr>
				<tr>
					<td height="48">
						本学年家庭提供
					</td>
					<td height="48" colspan="2">
						<input type="text" width="100%" maxlength="6" name="jttgje"
							id="jttgje" value="<bean:write name="rs" property="jttgje"/>">
						元 /月
					</td>
					<td height="48" colspan="2">
						本学年助学金
					</td>
					<td height="48" colspan="2">
						<input type="text" width="100%" maxlength="6" name="zxje"
							id="zxje" value="<bean:write name="rs" property="zxje"/>">
						元
					</td>
				</tr>
				<tr>
					<td height="31">
						本学年奖学金
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="6" name="jxje"
							id="jxje" value="<bean:write name="rs" property="jxje"/>">
						元
					</td>
					<td height="31" colspan="2">
						本学年勤工助学收入
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="6" name="qgzxje"
							id="qgzxje" value="<bean:write name="rs" property="qgzxje"/>">
						元
					</td>
				</tr>
				<tr>
					<td height="31">
						本学年校内无息贷学金
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="6" name="xnwxdkje"
							id="xnwxdkje" value="<bean:write name="rs" property="xnwxdkje"/>">
						元
					</td>
					<td height="31" colspan="2">
						本学年其他收入
					</td>
					<td height="31" colspan="2">
						<input type="text" width="100%" maxlength="6" name="qtsrje"
							id="qtsrje" value="<bean:write name="rs" property="qtsrje"/>">
						元
					</td>
				</tr>
				<tr>
					<td width="11%" height="31" rowspan="2">
						助学贷款
					</td>
					<td width="8%">
						申请金额
					</td>
					<td width="25%">
						<input type="text" width="100%" maxlength="6" name="zxdkje"
							id="zxdkje" value="<bean:write name="rs" property="zxdkje"/>">
						元
					</td>
					<td colspan="2" rowspan="2">
						助学贷款
					</td>
					<td width="15%" height="31">
						已发放金额
					</td>
					<td width="24%">
						<input type="text" width="100%" maxlength="6" name="yffzxdkje"
							id="yffzxdkje"
							value="<bean:write name="rs" property="yffzxdkje"/>">
						元
					</td>
				</tr>
				<tr>
					<td>
						时间
					</td>
					<td>
						<input type="text" readonly style="cursor:hand;width:100%"
							onclick="return showCalendar('zxdksj','y-mm-dd');"
							value="<bean:write name='rs' property="zxdksj" />" name="zxdksj"
							id="zxdksj" />
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
					<td height="31">
						申请资助理由
					</td>
					<td height="31" colspan="6">
						<html:textarea name="rs" property="sqzzly" rows='5' style="width:100%" onblur="yzdx(this,'sqzzly')"/>
						<input type="hidden" id="sqzzly" name="sqzzly" value="">
					</td>
				</tr>
				<tr>
					<td height="31">
						补助类型：
					</td>
					<td height="31" colspan="6">
						临时困难补助
					</td>
				</tr>
				<tr>
					<td height="48">
						第一志愿
					</td>
					<td height="48" colspan="3">
						起始金额
						<input type="text" maxlength="6" width="100%" name="zzff1qsje"
							id="zzff1qsje"
							value="<bean:write name="rs" property="zzff1qsje"/>">
						元
					</td>
					<td height="48" colspan="3">
						结束金额
						<input type="text" maxlength="6" width="100%" name="zzff1jsje"
							id="zzff1jsje"
							value="<bean:write name="rs" property="zzff1jsje"/>">
						元
					</td>
				</tr>
				<%--
				  <tr>
				    <td height="48">第二志愿</td>
				    <td height="48" colspan="2">起始金额<input type="text" width="100%" name="yffzxdksj" id="yffzxdksj">元 </td>
				    <td height="48" colspan="2">结束金额<input type="text" width="100%" name="yffzxjksj" id="yffzxjksj">元 </td>
				  </tr> --%>

			</table>
	<logic:equal value="true" name="sfksq">
			<center>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;width:100%">
					<button class="button2" onclick="yz();" style="width:80px">
						提交申请
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="toPrintOut();" style="width:80px">
						输出打印
					</button>
				</div>
			</center>

	</logic:equal>

		</html:form>
		<script src="js/bottomButton.js"></script>
		<script language="javascript">
if(document.getElementById("btn") && !window.dialogArguments){
	document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
	document.getElementById("btn").style.width = "96%";
	window.setInterval("initBTNTool('btn')",1);
}
</script>
</body>
</html:html>
