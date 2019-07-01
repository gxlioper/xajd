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
			var bjrshyj = document.getElementById('bjrshyj').value;
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			if(("未审核" != xxsh ) && (userType == "xy")){
				alert("学校已审核，不能再修改审核结果!");
	          	return false;
			}
			if(("未审核" != xysh ) && (userType == "bjr")){
				alert("系已审核，不能再修改审核结果!");
	          	return false;
			}
			if(bjrshyj != null){
	         	if(bjrshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("保荐人审核意见不能大于100个字");
	          		 return false;
	       		 }
	       	}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("系审核意见不能大于100个字");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("学校审核意见不能大于100个字");
	          		 return false;
	       		 }
	       	}
			 refreshForm('/xgxt/nbzyjsxy_xszz.do?method=syjjshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function je(){
			var userType = document.getElementById('userType').value;
			var shjg;
			if (userType == "xx"){
				shjg = document.getElementById('xxsh').value;
			}
			if (userType == "xy"){
				shjg = document.getElementById('xysh').value;
			}
			if (userType == "bjr"){
				shjg = document.getElementById('bjrsh').value;
			}
			var spjeT = document.getElementById('spjeT').value;
			
			if (shjg == "通过"){
				if (spjeT == null || spjeT == ""){
					spjeT = "0";
				}
				document.getElementById('spje').value = spjeT;
				document.getElementById('spje').disabled = false;
			} else {
				document.getElementById('spje').value = "";
				document.getElementById('spje').disabled = true;
			}
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 思源基金审核 - 单个审核
				</div>
			</div>
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
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />
			<input type="hidden" name="spjeT" id="spjeT" value="<bean:write name='rs' property="spje"/>" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="6" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td colspan="2">
						<div align="center">
							学号
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="xh" />
						<input type="hidden" id="guid" name="guid"
							value="<bean:write name='rs' property="guid" />" readonly="true">
					</td>
					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="xm" />
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
							民族
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mzmc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							出生日期
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="csny"/>
					</td>
					<td>
						<div align="center">
							入学时间
						</div>
					</td>
					<td>
						<bean:write name="rs" property="rxny"/>
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
							家庭月均收入
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtyjsr"/>
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
					<td colspan="2">
						<div align="center">
							联系电话
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="lxdh"/>
					</td>
					<td colspan="2">
						<div align="center">
							&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							申请金额
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sqje"/>
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
				<tr>
					<td colspan="2">
						<div align="center">
							捐款次数
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jkcs"/>
					</td>
					<td>
						<div align="center">
							捐款总金额
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jkzje"/>
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
							工作或学习单位
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy1_nl"/>&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_gx"/>
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy1_gzdw"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy2_nl"/>&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_gx"/>
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy2_gzdw"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy3_nl"/>&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_gx"/>
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy3_gzdw"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy4_nl"/>&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_gx"/>
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy4_gzdw"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy5_nl"/>&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_gx"/>
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy5_gzdw"/>
						</div>
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
				<logic:notEmpty name="syjjList">
				<tr>
					<td colspan="6">
						<div align="center">
							该学生获得所有思源基金记录
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<table align="center" style="width:100%" id="rsT" class="tbstyle">
							<thead>
								<tr>
									<td nowrap="nowrap" align="center">
										行号
									</td>
									<td nowrap="nowrap" align="center">
										申请时间
									</td>
									<td nowrap="nowrap" align="center">
										申请金额
									</td>
									<td nowrap="nowrap" align="center">
										审批金额
									</td>
								</tr>
							</thead>
							<tbody class="tbstyle">
								<logic:iterate name="syjjList" id="s">
									<tr>
										<td align="center">
											<bean:write name="s" property="hh" />
										</td>
										<td align="center">
											<bean:write name="s" property="sqsj" />
										</td>
										<td align="center">
											<bean:write name="s" property="sqje" />
										</td>
										<td align="center">
											<bean:write name="s" property="spje" />
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</td>
				</tr>
				</logic:notEmpty>
				<tr>
					<td colspan="2">
						<div align="center">
							审批金额
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="spje" name="spje" maxlength="8"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="spje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<logic:equal name="userType" value="bjr">
						<td>
							<div align="center">
								保荐人审核
							</div>
						</td>
						<td>
							<html:select name="rs" property="bjrsh" onchange="je();" styleId="bjrsh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							<input type="hidden" id="xysh" name="xysh"
								value="<bean:write name='rs' property="xysh" />" />
							<input type="hidden" id="xxsh" name="xxsh"
								value="<bean:write name='rs' property="xxsh" />" />
						</td>
					</logic:equal>
					<logic:equal name="userType" value="xy">
						<td>
							<div align="center">
								保荐人审核
							</div>
						</td>
						<td>
							<bean:write name="rs" property="bjrsh" />
							<input type="hidden" id="bjrsh" name="bjrsh"
								value="<bean:write name='rs' property="bjrsh" />" />
							<input type="hidden" id="xxsh" name="xxsh"
								value="<bean:write name='rs' property="xxsh" />" />
						</td>
					</logic:equal>
					<logic:equal name="userType" value="xx">
						<td>
							<div align="center">
								保荐人审核
							</div>
						</td>
						<td>
							<bean:write name="rs" property="bjrsh" />
							<input type="hidden" id="bjrsh" name="bjrsh"
								value="<bean:write name='rs' property="bjrsh" />" />
							<input type="hidden" id="xysh" name="xysh"
								value="<bean:write name='rs' property="xysh" />" />
						</td>
					</logic:equal>
				</tr>
				<tr>
					<logic:equal name="userType" value="bjr">
						<td colspan="2">
							<div align="center">
								保荐人姓名
							</div>
						</td>
						<td colspan="2">
							<input type="text" id="bjrxm" name="bjrxm" maxlength="50"
									style="width:100%;heigh:100%" readonly="readonly"
									value="<bean:write name="rs" property="bjrxm"/>">
						</td>
						<td>
							<div align="center">
								保荐人电话
							</div>
						</td>
						<td>
							<input type="text" id="bjrdh" name="bjrdh" maxlength="20"
									style="width:100%;heigh:100%"
									value="<bean:write name="rs" property="bjrdh"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</logic:equal>
					<logic:notEqual name="userType" value="bjr">
						<td colspan="2">
							<div align="center">
								保荐人姓名
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="bjrxm"/>
							<input type="hidden" id="bjrxm" name="bjrxm"
								value="<bean:write name='rs' property="bjrxm" />" />
						</td>
						<td>
							<div align="center">
								保荐人电话
							</div>
						</td>
						<td>
							<bean:write name="rs" property="bjrdh"/>
							<input type="hidden" id="bjrdh" name="bjrdh"
								value="<bean:write name='rs' property="bjrdh" />" />
						</td>
					</logic:notEqual>
				</tr>
				<logic:equal name="userType" value="xy">
					<tr>
						<td colspan="2">
							<div align="center">
								保荐人审批金额
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="spje"/>
						</td>
						<td>
							<div align="center">
								系审核
							</div>
						</td>
						<td>
							<html:select name="rs" property="xysh" onchange="je();" styleId="xysh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="xx">
					<tr>
						<td colspan="2">
							<div align="center">
								系审核
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="xysh" />
						</td>
						<td>
							<div align="center">
								系审批金额
							</div>
						</td>
						<td>
							<bean:write name="rs" property="spje"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								学校审核
							</div>
						</td>
						<td colspan="2">
							<html:select name="rs" property="xxsh" onchange="je();" styleId="xxsh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td colspan="3">
							<div align="center">
								&nbsp;
							</div>
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="bjr">
					<tr>
						<td colspan="2">
							<div align="center">
								保荐人审核意见
							</div>
						</td>
						<td colspan="4">
							<html:textarea name="rs" property="bjrshyj" rows='4'
								style="width:100%" onblur="yzdx(this,'bjrshyj')" />
							<input type="hidden" id="bjrshyj" name="bjrshyj" value="">
							<input type="hidden" id="xyshyj" name="xyshyj"
								value="<bean:write name='rs' property="xyshyj" />" />
							<input type="hidden" id="xxshyj" name="xxshyj"
								value="<bean:write name='rs' property="xxshyj" />" />
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="xy">
					<tr>
						<td colspan="2">
							<div align="center">
								保荐人审核意见
							</div>
						</td>
						<td colspan="4">
							<bean:write name='rs' property="bjrshyj" />
							<input type="hidden" id="bjrshyj" name="bjrshyj"
								value="<bean:write name='rs' property="bjrshyj" />" />
							<input type="hidden" id="xxshyj" name="xxshyj"
								value="<bean:write name='rs' property="xxshyj" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								系审核意见
							</div>
						</td>
						<td colspan="4">
							<html:textarea name="rs" property="xyshyj" rows='4'
								style="width:100%" onblur="yzdx(this,'xyshyj')" />
							<input type="hidden" id="xyshyj" name="xyshyj" value="">
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="xx">
					<tr>
						<td colspan="2">
							<div align="center">
								保荐人审核意见
							</div>
						</td>
						<td colspan="4">
							<bean:write name='rs' property="bjrshyj" />
							<input type="hidden" id="bjrshyj" name="bjrshyj"
								value="<bean:write name='rs' property="bjrshyj" />" />
							<input type="hidden" id="xyshyj" name="xyshyj"
								value="<bean:write name='rs' property="xyshyj" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								系审核意见
							</div>
						</td>
						<td colspan="4">
							<bean:write name='rs' property="xyshyj" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								学校审核意见
							</div>
						</td>
						<td colspan="4">
							<html:textarea name="rs" property="xxshyj" rows='4'
								style="width:100%" onblur="yzdx(this,'xxshyj')" />
							<input type="hidden" id="xxshyj" name="xxshyj" value="">
						</td>
					</tr>
				</logic:equal>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
<script language="javascript">
var userType = document.getElementById('userType').value;
var shjg;
var spjeT = document.getElementById('spjeT').value;
if (userType == "xx"){
	shjg = document.getElementById('xxsh').value;
}
if (userType == "xy"){
	shjg = document.getElementById('xysh').value;
}
if (userType == "bjr"){
	shjg = document.getElementById('bjrsh').value;
}
if (shjg == "通过"){
	if (spjeT == null || spjeT == ""){
		spjeT = "0";
	}
	document.getElementById('spje').value = spjeT;
	document.getElementById('spje').disabled = false;
} else {
	document.getElementById('spje').value = "";
	document.getElementById('spje').disabled = true;
}
</script>
</html>
