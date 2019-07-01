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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/commanFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var tyjmje = document.getElementById('tyjmje').value;
			var xyshyj = document.getElementById('xyshyj').value;
			var xyzzfzryj = document.getElementById('xyzzfzryj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("通过" == xxsh) && (isXX != "is")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			if((tyjmje != "") && !isNumber(tyjmje)){
				 alert("减免金额格式不对！");
				 return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于100个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xyzzfzryj != null){
	         	if(xyzzfzryj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />总支负责人意见不能大于100个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("学校审核意见不能大于100个字符");
	          		 return false;
	       		 }
			 }
			 refreshForm('/xgxt/auditing_zzsf_xsxfjm_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function getJe(){
			var temp = $('zzdjmc').options[$('zzdjmc').selectedIndex].innerHTML;
			msgArray = new Array();
			msgArray = temp.split('||');
			var zzdjje = "";
			if (msgArray.length > 1) {
				zzdjje = msgArray[1];
			}
			document.getElementById('zzdjje').value=zzdjje;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 学生学费减免审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" id="xxsh" name="xxsh" value="<bean:write name="rs" property="xxsh" />" />
			<input type="hidden" id="isXX" name="isXX" value="<bean:write name="isXX" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="7" align="center">
						</td>
					</tr>
				</thead>
				<tr>
						<td align="right" colspan="2">
							学号：
						</td>
						<td align="left" colspan="3">
							<bean:write name="rs" property="xh"/>
						</td>
					<td width="16%">
						<div align="right">
							姓名：
						</div>
					</td>
					<td width="34%" align="left">
						<bean:write name="rs" property="xm"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="right">
							生源地：
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="syd"/>
					</td>
					<td>
						<div align="right">
							性别：
						</div>
					</td>
					<td align="left">
						<bean:write name="rs" property="xb"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="right">
							政治面貌：
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="zzmm"/>
					</td>
					<td>
						<div align="right">
							身份证号：
						</div>
					</td>
					<td align="left">
						<bean:write name="rs" property="sfzh"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="right">
							年度：
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="nd"/>
					</td>
					<td>
						<div align="right">
							系别：
						</div>
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="right">
							专业：
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="zymc"/>
					</td>
					<td>
						<div align="right">
							班：
						</div>
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="right">
							出生年月：
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="csrq"/>
					</td>
					<td>
						<div align="right">
							学生类别：
						</div>
					</td>
					<td align="left">
						<bean:write name="rs" property="xslb"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="right">
							家长姓名：
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="jzxm"/>
					</td>
					<td>
						<div align="right">
							家庭电话：
						</div>
					</td>
					<td align="left">
						<bean:write name="rs" property="jtdh"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="right">
							家庭地址：
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="jtdz"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="right">
							邮政编码：
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="yzbm"/>
					</td>
					<td>
						<div align="right">
							宿舍电话：
						</div>
					</td>
					<td align="left">
						<bean:write name="rs" property="ssdh"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="right">
							宿舍地址：
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="ssdz"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭经济情况(人均月收入)
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="jtjjqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							院、系困难补助情况
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="yxknbzqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="right">
							勤工岗位：
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="qggw"/>
					</td>
					<td>
						<div align="right">
							学习成绩(班级排名)：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xxcj"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="right">
							申请种类：
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sqzl"/>
					</td>
					<td>
						<div align="right">
							申请金额：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sqje"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							平时表现情况
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="psbxqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							个人获奖情况
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="gehjqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							备注
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="bz" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							同意减免金额：
						</div>
					</td>
					<td colspan="4" align="center">
						<input name="tyjmje" maxlength="8" id="tyjmje" type="text" style="width:100%;heigh:100%" value="<bean:write name="rs" property="tyjmje"/>" onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
						<bean:write name="rs" property="tyjmjedx"/>
					</td>
					<td>
						<div align="center">
							审核结果
						</div>
					</td>
					<td>
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
	<!-- 			<tr>
					<logic:equal name="isXX" value="no">
					<td colspan="2">
						<div align="right">
							学生资助等级
						</div>
					</td>
					<td align="left" colspan="3">
						<html:select name="rs" property="zzdjdm" styleId="zzdjmc" onchange="getJe();">
							<html:option value="">------请选择------</html:option>
							<html:options collection="xszzDjList" property="zzdjdm"
								labelProperty="zzdjmc" />
						</html:select>
					</td>
					</logic:equal>
					<logic:equal name="isXX" value="is">
					<td colspan="2">
						<div align="right">
							学生资助等级
						</div>
					</td>
					<td align="left" colspan="3">
						<html:select name="rs" property="zzdjdm" styleId="zzdjmc" disabled="true">
							<html:option value="">------请选择------</html:option>
							<html:options collection="xszzDjList" property="zzdjdm"
								labelProperty="zzdjmc" />
						</html:select>
					</td>
					</logic:equal>
					<td>
						<div align="right">
							资助金额
						</div>
					</td>
					<td>
						<input type="text" id="zzdjje" readonly="readonly" name="zzdjje"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzdjje"/>">
					</td>
				</tr>-->
				<tr>
					<td colspan="2">
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />总支负责人意见
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="xyzzfzryj" rows='5' style="width:100%" onblur="yzdx(this,'xyzzfzryj')"/>
						<input type="hidden" id="xyzzfzryj" name="xyzzfzryj" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="left">
							学校审核意见
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="xxshyj" rows='5' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
