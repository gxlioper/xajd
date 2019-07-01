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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var userType = document.getElementById('userType').value;
			var xxsh = document.getElementById('xxsh').value;
			var xysh = document.getElementById('xysh').value;
			var fdyshyj = document.getElementById('fdyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var xyshyj = document.getElementById('xyshyj').value;
			
			if(("未审核" != xxsh ) && (userType == "xy")){
				alert("学校已审核，不能再修改审核结果!");
	          	return false;
			}
			if(("未审核" != xysh ) && (userType == "fdy")){
				alert("<bean:message key="lable.xsgzyxpzxy" />已审核，不能再修改审核结果!");
	          	return false;
			}
			if(fdyshyj != null){
	         	if(fdyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("辅导员审核意见不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("学校审核意见不能大于200个字符");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/n05_xszz.do?method=gjzxj1shSave');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function changeZxj(){
			var zxjdm = document.getElementById('zxjdm').value;
			
			if (zxjdm == null || zxjdm == "") {
				document.getElementById('zxjdj').value = "";
				document.getElementById('zxjje').value = "";
			} else {
				var sT = document.getElementById('zxjdm')[document.getElementById('zxjdm').selectedIndex].text;
				var sList = sT.split("||");
				
				document.getElementById('zxjdj').value = sList[0];
				document.getElementById('zxjje').value = sList[1];
			}
		}
		function changesh(){
			var userType = document.getElementById('userType').value;
			var zxjdmT = document.getElementById('zxjdmT').value;
			var shjg = "";
			if ("xx" == userType) {
				shjg = document.getElementById('xxsh').value;
			}
			if ("xy" == userType) {
				shjg = document.getElementById('xysh').value;
			}
			if ("fdy" == userType) {
				shjg = document.getElementById('fdysh').value;
			}
			if (shjg == "通过") {
				document.getElementById("zxjdm").disabled=false;
				if (zxjdmT != null && zxjdmT != ""){
					document.getElementById("zxjdm").value=zxjdmT;
				} else {
					document.getElementById("zxjdm").value="";
				}
				changeZxj();
			} else {
				document.getElementById("zxjdm").value = "";
				document.getElementById('zxjdj').value = "";
				document.getElementById('zxjje').value = "";
				document.getElementById("zxjdm").disabled=true;
			}
		}
		</script>
		<html:form action="/n05_xszz" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 国家助学金审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" />" />
			<input type="hidden" id="shjb" name="shjb" value="<bean:write name="shjb" />" />
			<input type="hidden" id="zxjdmT" name="zxjdmT" value="<bean:write name='rs' property="zxjdm" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="6" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" colspan="2">
							学号
					</td>
					<td colspan="2">
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
						<input type="hidden" id="xn" name="xn"
							value="<bean:write name='rs' property="xn" />" />
						<bean:write name='rs' property="xh" />
					</td>
					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="xm"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xb"/>
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfzh"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							民族
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="mzmc"/>
					</td>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zzmmmc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							出生日期
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="csrq"/>
					</td>
					<td>
						<div align="center">
							入学时间
						</div>
					</td>
					<td>
						<bean:write name="rs" property="rxrq"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							年级
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="nj"/>
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />名称
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							专业名称
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zymc"/>
					</td>
					<td>
						<div align="center">
							班级名称
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							学年
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xn"/>
					</td>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="lxdh"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							曾获何种奖励
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="chhzjl"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭户口
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jthk"/>
					</td>
					<td>
						<div align="center">
							家庭人口总数
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtzrks"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							人均月收入
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="rjysr"/>
					</td>
					<td>
						<div align="center">
							家庭月总收入
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtyzsr"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							收入来源
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="srly"/>
					</td>
					<td>
						<div align="center">
							邮政编码
						</div>
					</td>
					<td>
						<bean:write name="rs" property="yzbm"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭住址
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="jtzz"/>
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6">
						<div align="center">
							家<br />庭<br />成<br />员<br />情<br />况
						</div>
					</td>
					<td width="12%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="17%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="17%">
						<div align="center">
							与本人关系
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							工作单位
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy1_xm"/>
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy1_nl"/>&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_gx"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy1_dw"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy2_xm"/>
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy2_nl"/>&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_gx"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy2_dw"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy3_xm"/>
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy3_nl"/>&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_gx"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy3_dw"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy4_xm"/>
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy4_nl"/>&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_gx"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy4_dw"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy5_xm"/>
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy5_nl"/>&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy5_gx"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy5_dw"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							申请理由
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="sqly"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							助学金
						</div>
					</td>
					<td colspan="2">
						<html:select name="rs" property="zxjdm" onchange="changeZxj();" styleId="zxjdm">
							<html:option value=""></html:option>
							<html:options collection="gjzxjList" property="dm"
										labelProperty="mc" />
						</html:select>
					</td>
					<td>
						<div align="center">
							助学金等级
						</div>
					</td>
					<td>
						<input type="text" id="zxjdj" name="zxjdj" readonly="readonly"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="zxjdj"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							助学金金额
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zxjje" name="zxjje" readonly="readonly"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="zxjje"/>">
					</td>
					<td>
						<div align="center">
							申请时间
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sqsj"/>
					</td>
				</tr>
				<logic:equal name="shjb" value="3">
					<logic:equal name="userType" value="fdy">
						<tr>
							<td colspan="2">
								<div align="center">
									辅导员审核
								</div>
							</td>
							<td colspan="2">
								<html:select name="rs" property="fdysh" onchange="changesh()" styleId="fdysh">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
								<input type="hidden" id="xysh" name="xysh"
									value="<bean:write name="rs" property="xysh"/>">
								<input type="hidden" id="xxsh" name="xxsh"
									value="<bean:write name="rs" property="xxsh"/>">
							</td>
							<td>
								<div align="center">
									审核时间
								</div>
							</td>
							<td>
								<bean:write name="rs" property="fdyshsj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									辅导员审核意见
								</div>
							</td>
							<td colspan="4">
								<html:textarea name="rs" property="fdyshyj" rows='3' style="width:100%" onblur="yzdx(this,'fdyshyj')"/>
								<input type="hidden" id="fdyshyj" name="fdyshyj" value="">
								<input type="hidden" id="xyshyj" name="xyshyj"
									value="<bean:write name="rs" property="xyshyj"/>">
								<input type="hidden" id="xxshyj" name="xxshyj"
									value="<bean:write name="rs" property="xxshyj"/>">
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="userType" value="xy">
						<tr>
							<td colspan="2">
								<div align="center">
									辅导员审核
								</div>
							</td>
							<td colspan="2">
								<bean:write name="rs" property="fdysh"/>
							</td>
							<td>
								<div align="center">
									辅导员审核时间
								</div>
							</td>
							<td>
								<bean:write name="rs" property="fdyshsj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									辅导员审核意见
								</div>
							</td>
							<td colspan="4">
								<bean:write name="rs" property="fdyshyj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />审核
								</div>
							</td>
							<td colspan="2">
								<html:select name="rs" property="xysh" onchange="changesh()" styleId="xysh">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
								<input type="hidden" id="xxsh" name="xxsh"
									value="<bean:write name="rs" property="xxsh"/>">
							</td>
							<td>
								<div align="center">
									审核时间
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xyshsj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />审核意见
								</div>
							</td>
							<td colspan="4">
								<html:textarea name="rs" property="xyshyj" rows='3' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
								<input type="hidden" id="xyshyj" name="xyshyj" value="">
								<input type="hidden" id="fdyshyj" name="fdyshyj"
									value="<bean:write name="rs" property="fdyshyj"/>">
								<input type="hidden" id="xxshyj" name="xxshyj"
									value="<bean:write name="rs" property="xxshyj"/>">
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="userType" value="xx">
						<tr>
							<td colspan="2">
								<div align="center">
									辅导员审核
								</div>
							</td>
							<td colspan="2">
								<bean:write name="rs" property="fdysh"/>
							</td>
							<td>
								<div align="center">
									辅导员审核时间
								</div>
							</td>
							<td>
								<bean:write name="rs" property="fdyshsj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									辅导员审核意见
								</div>
							</td>
							<td colspan="4">
								<bean:write name="rs" property="fdyshyj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />审核
								</div>
							</td>
							<td colspan="2">
								<bean:write name="rs" property="xysh"/>
							</td>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />审核时间
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xyshsj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />审核意见
								</div>
							</td>
							<td colspan="4">
								<bean:write name="rs" property="xyshyj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									学校审核
								</div>
							</td>
							<td colspan="2">
								<html:select name="rs" property="xxsh" onchange="changesh()" styleId="xxsh">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
								<input type="hidden" id="xysh" name="xysh"
									value="<bean:write name="rs" property="xysh"/>">
							</td>
							<td>
								<div align="center">
									审核时间
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xxshsj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									学校审核意见
								</div>
							</td>
							<td colspan="4">
								<html:textarea name="rs" property="xxshyj" rows='3' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
								<input type="hidden" id="xxshyj" name="xxshyj" value="">
								<input type="hidden" id="fdyshyj" name="fdyshyj"
									value="<bean:write name="rs" property="fdyshyj"/>">
								<input type="hidden" id="xyshyj" name="xyshyj"
									value="<bean:write name="rs" property="xyshyj"/>">
							</td>
						</tr>
					</logic:equal>
				</logic:equal>
				<logic:notEqual name="shjb" value="3">
					<logic:equal name="userType" value="xy">
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />审核
								</div>
							</td>
							<td colspan="2">
								<html:select name="rs" property="xysh" onchange="changesh()" styleId="xysh">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
								<input type="hidden" id="xxsh" name="xxsh"
									value="<bean:write name="rs" property="xxsh"/>">
							</td>
							<td>
								<div align="center">
									审核时间
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xyshsj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />审核意见
								</div>
							</td>
							<td colspan="4">
								<html:textarea name="rs" property="xyshyj" rows='3' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
								<input type="hidden" id="xyshyj" name="xyshyj" value="">
								<input type="hidden" id="fdyshyj" name="fdyshyj"
									value="<bean:write name="rs" property="fdyshyj"/>">
								<input type="hidden" id="xxshyj" name="xxshyj"
									value="<bean:write name="rs" property="xxshyj"/>">
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="userType" value="xx">
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />审核
								</div>
							</td>
							<td colspan="2">
								<bean:write name="rs" property="xysh"/>
							</td>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />审核时间
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xyshsj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />审核意见
								</div>
							</td>
							<td colspan="4">
								<bean:write name="rs" property="xyshyj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									学校审核
								</div>
							</td>
							<td colspan="2">
								<html:select name="rs" property="xxsh" onchange="changesh()" styleId="xxsh">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
								<input type="hidden" id="xysh" name="xysh"
									value="<bean:write name="rs" property="xysh"/>">
							</td>
							<td>
								<div align="center">
									审核时间
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xxshsj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									学校审核意见
								</div>
							</td>
							<td colspan="4">
								<html:textarea name="rs" property="xxshyj" rows='3' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
								<input type="hidden" id="xxshyj" name="xxshyj" value="">
								<input type="hidden" id="fdyshyj" name="fdyshyj"
									value="<bean:write name="rs" property="fdyshyj"/>">
								<input type="hidden" id="xyshyj" name="xyshyj"
									value="<bean:write name="rs" property="xyshyj"/>">
							</td>
						</tr>
					</logic:equal>
				</logic:notEqual>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
				<logic:equal name="xxdm" value="11647">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" id="btn_cjprint" 
					onclick="showTopWin('pjpy_shcbys_cjprint.do?xh='+document.getElementById('xh').value,700,600)">
					学生成绩单
				</button>
				</logic:equal>
			</div>
		</html:form>
	</body>
	<script language="javascript">
		var userType = document.getElementById('userType').value;
		var shjb = document.getElementById('shjb').value;
		var zxjdmT = document.getElementById('zxjdmT').value;
		var shjg = "";
		
		if ("xx" == userType) {
			shjg = document.getElementById('xxsh').value;
		}
		if ("xy" == userType) {
			shjg = document.getElementById('xysh').value;
		}
		if ("fdy" == userType) {
			shjg = document.getElementById('fdysh').value;
		}
		
		if (shjb == "3") {
			if (shjg == "通过") {
				document.getElementById("zxjdm").disabled=false;
				if (zxjdmT != null && zxjdmT != ""){
					document.getElementById("zxjdm").value=zxjdmT;
				} else {
					document.getElementById("zxjdm").value="";
				}
				changeZxj();
			} else if ("fdy" == userType){
				document.getElementById("zxjdm").value = "";
				document.getElementById('zxjdj').value = "";
				document.getElementById('zxjje').value = "";
				document.getElementById("zxjdm").disabled=true;
			} else {
				document.getElementById("zxjdm").disabled=true;
			}
		} else {
			if (shjg == "通过") {
				document.getElementById("zxjdm").disabled=false;
				if (zxjdmT != null && zxjdmT != ""){
					document.getElementById("zxjdm").value=zxjdmT;
				} else {
					document.getElementById("zxjdm").value="";
				}
				changeZxj();
			} else if ("xy" == userType){
				document.getElementById("zxjdm").value = "";
				document.getElementById('zxjdj').value = "";
				document.getElementById('zxjje').value = "";
				document.getElementById("zxjdm").disabled=true;
			} else {
				document.getElementById("zxjdm").disabled=true;
			}
		}
	</script>
</html>
