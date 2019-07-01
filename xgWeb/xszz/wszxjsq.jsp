<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="xgxt.form.*"%>

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

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
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
		function yz(){
			var xh = document.getElementById('xh').value;
			var qsdh = document.getElementById('qsdh').value;
			var jcqk = document.getElementById('jcqk').value;
			var yzbm = document.getElementById('yzbm').value;
			var jtdz = document.getElementById('jtdz').value;
			var jtcy1_nl = document.getElementById('jtcy1_nl').value;
			var jtcy1_ysr = document.getElementById('jtcy1_ysr').value;
			var jtcy2_nl = document.getElementById('jtcy2_nl').value;
			var jtcy2_ysr = document.getElementById('jtcy2_ysr').value;
			var jtcy3_nl = document.getElementById('jtcy3_nl').value;
			var jtcy3_ysr = document.getElementById('jtcy3_ysr').value;
			var jtcy4_nl = document.getElementById('jtcy4_nl').value;
			var jtcy4_ysr = document.getElementById('jtcy4_ysr').value;
			var jtcy5_nl = document.getElementById('jtcy5_nl').value;
			var jtcy5_ysr = document.getElementById('jtcy5_ysr').value;
			var drshgzqk = document.getElementById('drshgzqk').value;
			var sqzzly = document.getElementById('sqzzly').value;
			
			if((xh == "") || (xh == null)){
				alert("学生学号不能为空!");
				return false;
			}
			if((qsdh != null) && (qsdh != "") && (!isNumber(qsdh))){
				alert("寝室电话必须为整数!");
				return false;
			}
			if(jcqk != null){
	         	if(jcqk.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("奖惩情况不能大于100个字符");
	          		 return false;
	       		 } 	        
			}
			if((yzbm != null) && (yzbm != "") && (!isNumber(yzbm))){
				alert("邮政编码必须为整数!");
				return false;
			}
			if(jtdz != null){
	         	if(jtdz.replace(/[^\u0000-\u00ff]/g, "**").length > 50){	         
	          		 alert("家庭地址不能大于50个字符");
	          		 return false;
	       		 } 	
	       	}
	       	if((jtcy1_nl != null) && (jtcy1_nl != "") && (!isNumber(jtcy1_nl))){
				alert("家庭成员1年龄必须为整数!");
				return false;
			}
			if((jtcy1_ysr != null) && (jtcy1_ysr != "") && (!isNumber(jtcy1_ysr))){
				alert("家庭成员1月收入必须为整数!");
				return false;
			}
			if((jtcy2_nl != null) && (jtcy2_nl != "") && (!isNumber(jtcy2_nl))){
				alert("家庭成员2年龄必须为整数!");
				return false;
			}
			if((jtcy2_ysr != null) && (jtcy2_ysr != "") && (!isNumber(jtcy2_ysr))){
				alert("家庭成员2月收入必须为整数!");
				return false;
			}
			if((jtcy3_nl != null) && (jtcy3_nl != "") && (!isNumber(jtcy3_nl))){
				alert("家庭成员3年龄必须为整数!");
				return false;
			}
			if((jtcy3_ysr != null) && (jtcy3_ysr != "") && (!isNumber(jtcy3_ysr))){
				alert("家庭成员3月收入必须为整数!");
				return false;
			}
			if((jtcy4_nl != null) && (jtcy4_nl != "") && (!isNumber(jtcy4_nl))){
				alert("家庭成员4年龄必须为整数!");
				return false;
			}
			if((jtcy4_ysr != null) && (jtcy4_ysr != "") && (!isNumber(jtcy4_ysr))){
				alert("家庭成员4月收入必须为整数!");
				return false;
			}
			if((jtcy5_nl != null) && (jtcy5_nl != "") && (!isNumber(jtcy5_nl))){
				alert("家庭成员5年龄必须为整数!");
				return false;
			}
			if((jtcy5_ysr != null) && (jtcy5_ysr != "") && (!isNumber(jtcy5_ysr))){
				alert("家庭成员5月收入必须为整数!");
				return false;
			}
			if(drshgzqk != null){
	         	if(drshgzqk.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("担任社会工作情况不能大于100个字符");
	          		 return false;
	       		 }
	       	}
	       	if(sqzzly != null){
	         	if(sqzzly.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("申请理由不能大于100个字符");
	          		 return false;
	       		 }
			}
			
			var wszxjdm = document.getElementById("wszxj").value;
			if( wszxjdm == "" || wszxjdm == " "){
				alert("没有选择相应的外设助学金！");
				return false;
			}
			document.forms[0].action = "/xgxt/wszxj.do?doType=save";
			document.forms[0].submit();
		}
		
		function toPrintOut(titName){//输出相应的打印页面
			document.forms[0].action = "/xgxt/wszxjsqb.do";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
	</script>	
</head>

<body>
	<html:form action="wszxj.do" method="post">
		<input type="hidden" id="title" name="title" value="wszxj" />
		<input type="hidden" id="url" name="url" value="/xszz/wszxjsq.jsp" />
		<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj"/>">
		<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj"/>">
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-ssbh-qsdh-xz-xymc-bjmc" />
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:write name="tips" scope="request" />
			</div>
		</div>
		<logic:present name="ok">
			<logic:equal value="true" name="ok">
				<script type="text/javascript">
				alert("保存成功！");
			</script>
			</logic:equal>
			<logic:notEqual value="true" name="ok">
				<script type="text/javascript">
				alert("保存失败！");
			</script>
			</logic:notEqual>
		</logic:present>
		<logic:present name="isPASS">
			<logic:equal value="is" name="isPASS">
				<script type="text/javascript">
					alert("已通过审核，不能申请！");
				</script>
			</logic:equal>
		</logic:present>
		

		<table width="100%" class="tbstyle">
			
			<tr>
				<td colspan="2" align="center"  width="50%">
					选择外设助学金类别：
				</td>
				<td colspan="2">
					<logic:present name="wszxjdm" >
						<%
							XszzForm zzForm = new XszzForm();
							zzForm.setWszxjdm(request.getAttribute("wszxjdm").toString());
						%>
					</logic:present>
					
					<html:select property="wszxjdm" style="width:100%" styleId="wszxj">
						<html:option value=" " />
						<html:options collection="wszxjList" property="wszxjdm"
							labelProperty="wszxjmc" />
					</html:select>
				</td>
			</tr>
			<tr>

				<logic:notEqual name="userOnLine" value="student" scope="session">
					<td align="center" width="20%">
						<font color="red">*</font>学号：
					</td>
					<td align="left">
						<html:text name="rs" property="xh" styleId="xh"
							onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true"/>
						<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
					</td>
				</logic:notEqual>
				<logic:equal name="userOnLine" value="student" scope="session">
					<td align="center" >
						<font color="red">*</font>学号：
					</td>
					<td align="left">
						<input id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />"
							readonly="readonly" />
					</td>
				</logic:equal>


				<td width="16%">
					<div align="center">
						姓名
					</div>
				</td>
				<td width="34%">
					<input type="text" style="width:100%" id="xm" name="xm"
						value="<bean:write name="rs" property="xm"/>" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td >
					<div align="center">
						出生年月
					</div>
				</td>
				<td >
					<input type="text" readonly style="cursor:hand;width:100%"
										onclick="return showCalendar('csny','y-mm-dd');"
										value="<bean:write name='rs' property="csny" />"
										name="csny" id="csny" />
				</td>
				<td>
					<div align="center">
						性别
					</div>
				</td>
				<td >
					<input type="text" style="width:100%" id="xb" name="xb" readonly="readonly"
						value="<bean:write name="rs" property="xb"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						籍贯
					</div>
				</td>
				<td >
					<input id="jg" name="jg" type="text" maxlength="40" style="width:100%" value="<bean:write name="rs" property="jg"/>">
				</td>
				<td>
					<div align="center">
						民族
					</div>
				</td>
				<td>
					<input type="text" style="width:100%" id="mzmc" name="mzmc" maxlength="50"
						value="<bean:write name="rs" property="mzmc"/>">
				</td>
			</tr>
			<tr>
				<td >
					<div align="center">
						学制
					</div>
				</td>
				<td>
					<input type="text" name="xz" id="xz" 
						value="<bean:write name="rs" property="xz" />" readonly="readonly">
				</td>
				<td align="center">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						寝室号
					</div>
				</td>
				<td>
					<input type="text" name="qsh" id="qsh" style="width:100%" maxlength="8"
						value="<bean:write name="rs" property="qsh"/>">
				</td>
				<td align="center">
					寝室电话
				</td>
				<td>
					<input type="text" name="qsdh" id="qsdh" style="width:100%" maxlength="12"
						value="<bean:write name="rs" property="qsdh"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td colspan="4" style="padding:0;">
					<%@ include file="/xszz/zjjd/yhkh.jsp"%>
				</td>
			</tr>			
			<tr>
				<td>
					<div align="center">
						奖惩情况
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rs" property="jcqk" rows='5'  style="width:100%" onblur="yzdx(this,'jcqk')"/>
					<input type="hidden" id="jcqk" name="jcqk" value="">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						政治面貌
					</div>
				</td>
				<td >
					<input type="text" style="width:100%" id="zzmm" name="zzmm" maxlength="20"
						value="<bean:write name="rs" property="zzmm"/>">
				</td>
				<td>
					<div align="center">
						邮政编码
					</div>
				</td>
				<td>
					<input name="yzbm" id="yzbm" type="text" style="width:100%" maxlength="6"
						value="<bean:write name="rs" property="yzbm"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						家庭住址
					</div>
				</td>
				<td  colspan="3" >
					<html:textarea name="rs" property="jtdz" rows='3'style="width:100%" onblur="yzdx(this,'jtdz')"/>
					<input type="hidden" id="jtdz" name="jtdz" value="">
				</td>
			</tr>
			<tr>
				<td colspan="4">
				<table width="100%" class="tbstyle">
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
				<td width="13%">
					<div align="center">
						与本人关系
					</div>
				</td>
				<td width="12%">
					<div align="center">
						月收入
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						工作或学习单位
					</div>
				</td>
			</tr>
			<tr>
				<td width="12%">
					<input type="text" style="width:100%" id="jtcy1_xm" name="jtcy1_xm" maxlength="50"
						value="<bean:write name="rs" property="jtcy1_xm" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy1_nl" name="jtcy1_nl" maxlength="3"
						value="<bean:write name="rs" property="jtcy1_nl" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy1_gx" name="jtcy1_gx" maxlength="50"
						value="<bean:write name="rs" property="jtcy1_gx" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy1_ysr"
						name="jtcy1_ysr" maxlength="6"
						value="<bean:write name="rs" property="jtcy1_ysr" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="3">
					<input type="text" style="width:100%" id="jtcy1_gzdw"
						name="jtcy1_gzdw" maxlength="100"
						value="<bean:write name="rs" property="jtcy1_gzdw" />">
				</td>
			</tr>
			<tr>
				<td width="12%">
					<input type="text" style="width:100%" id="jtcy2_xm" name="jtcy2_xm" maxlength="50"
						value="<bean:write name="rs" property="jtcy2_xm" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy2_nl" name="jtcy2_nl" maxlength="3"
						value="<bean:write name="rs" property="jtcy2_nl" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy2_gx" name="jtcy2_gx" maxlength="50"
						value="<bean:write name="rs" property="jtcy2_gx" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy2_ysr"
						name="jtcy2_ysr" maxlength="6"
						value="<bean:write name="rs" property="jtcy2_ysr" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="3">
					<input type="text" style="width:100%" id="jtcy2_gzdw"
						name="jtcy2_gzdw" maxlength="100"
						value="<bean:write name="rs" property="jtcy2_gzdw" />">
				</td>
			</tr>
			<tr>
				<td width="12%">
					<input type="text" style="width:100%" id="jtcy3_xm" name="jtcy3_xm" maxlength="50"
						value="<bean:write name="rs" property="jtcy3_xm" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy3_nl" name="jtcy3_nl" maxlength="3"
						value="<bean:write name="rs" property="jtcy3_nl" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy3_gx" name="jtcy3_gx" maxlength="50"
						value="<bean:write name="rs" property="jtcy3_gx" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy3_ysr"
						name="jtcy3_ysr" maxlength="6"
						value="<bean:write name="rs" property="jtcy3_ysr" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="3">
					<input type="text" style="width:100%" id="jtcy3_gzdw"
						name="jtcy3_gzdw" maxlength="100"
						value="<bean:write name="rs" property="jtcy3_gzdw" />">
				</td>
			</tr>
			<tr>
				<td width="12%">
					<input type="text" style="width:100%" id="jtcy4_xm" name="jtcy4_xm" maxlength="50"
						value="<bean:write name="rs" property="jtcy4_xm" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy4_nl" name="jtcy4_nl" maxlength="3"
						value="<bean:write name="rs" property="jtcy4_nl" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy4_gx" name="jtcy4_gx" maxlength="50"
						value="<bean:write name="rs" property="jtcy4_gx" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy4_ysr"
						name="jtcy4_ysr" maxlength="6"
						value="<bean:write name="rs" property="jtcy4_ysr" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="3">
					<input type="text" style="width:100%" id="jtcy4_gzdw"
						name="jtcy4_gzdw" maxlength="100"
						value="<bean:write name="rs" property="jtcy4_gzdw" />">
				</td>
			</tr>
			<tr>
				<td width="12%">
					<input type="text" style="width:100%" id="jtcy5_xm" name="jtcy5_xm" maxlength="50"
						value="<bean:write name="rs" property="jtcy5_xm" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy5_nl" name="jtcy5_nl" maxlength="3"
						value="<bean:write name="rs" property="jtcy5_nl" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy5_gx" name="jtcy5_gx" maxlength="50"
						value="<bean:write name="rs" property="jtcy5_gx" />">
				</td>
				<td>
					<input type="text" style="width:100%" id="jtcy5_ysr"
						name="jtcy5_ysr" maxlength="6"
						value="<bean:write name="rs" property="jtcy5_ysr" />"
						onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td colspan="3">
					<input type="text" style="width:100%" id="jtcy5_gzdw"
						name="jtcy5_gzdw" maxlength="100"
						value="<bean:write name="rs" property="jtcy5_gzdw" />">
				</td>
				</tr>
				</table>
			</tr>
			<tr>
				<td height="108">
					担任社会工作情况
				</td>
				<td colspan="3">
					<html:textarea name="rs" property="drshgzqk" rows='7' style="width:100%" onblur="yzdx(this,'drshgzqk')"/>
					<input type="hidden" id="drshgzqk" name="drshgzqk" value="">
				</td>
			</tr>
			<tr>
				<td >
					<br>
					申请
					<br>
					理由
				</td>
				<td colspan="3">
					（包括家庭经济情况，学费来源，个人品行、 学习情况 ）
					<html:textarea name="rs" property="sqzzly" rows='5' style="width:100%" onblur="yzdx(this,'sqzzly')"/>
					<input type="hidden" id="sqzzly" name="sqzzly" value="">
				</td>
			</tr>
			<tr>
				<td height="108">
					<br>
					本
					<br>
					人
					<br>
					承
					<br>
					诺
				</td>
				<td colspan="3">
					<input type="text" style="width:100%" id="brcn"
						name="brcn" maxlength="100"
						value="本人愿意加入爱心社团，并积极参加各类社会公益活动，以实际行动回报社会">
				</td>
			</tr>
			<logic:equal name="writeable" value="yes">
				<tr>
					<td>
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />
							<br>
							审核
							<br>
							意见
						</div>
					</td>
					<td colspan="7">
							<bean:write name="rs" property="xyshyj" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="left">
							学校
							<br>
							审核
							<br>
							意见
						</div>
					</td>
					<td colspan="7">
							<bean:write name="rs" property="xxshyj" />
					</td>
				</tr>
			</logic:equal>
		</table>

		<center>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;width:100%">
				<button class="button2"
					onclick="yz();"
					style="width:80px">
					提交申请
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2"
					onclick="toPrintOut(document.getElementById('title').value);"
					style="width:80px">
					输出打印
				</button>
			</div>
		</center>

	</html:form>
	<script language="javascript">
if(document.getElementById("btn") && !window.dialogArguments){
	document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
	document.getElementById("btn").style.width = "96%";
	window.setInterval("initBTNTool('btn')",1);
}
</script>
</body>
</html:html>
