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
		<title><bean:message key="lable.title" />
		</title>
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
		function yzt(){
			var xyshyj = document.getElementById('xyshyj').value;
			var xyzzfzryj = document.getElementById('xyzzfzryj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("通过" == xxsh) && (isXX != "is")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xyzzfzryj != null){
	         	if(xyzzfzryj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />总支负责人意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("学校审核意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}
			
			refreshForm('/xgxt/auditing_xndxj_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		
		function yzXNMZ(){
			var fdyshyj = document.getElementById('fdyshyj').value;
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("通过" == xxsh) && (isXX != "is")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			if(fdyshyj != null){
	         	if(fdyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("辅导员审核意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("学校审核意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}
			
			refreshForm('/xgxt/auditing_xndxj_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		
		function yz(){
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("通过" == xxsh) && (isXX != "is")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("学校审核意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}
			
			refreshForm('/xgxt/auditing_xndxj_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
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
					当前所在位置：学生资助 - 审核 - 校内贷学金审核 - 单个审核
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
						<td colspan="6" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td height="31" colspan="2">
						学号
					</td>
					<td width="26%">
						<bean:write name="rs" property="xh" />
						<input type="hidden" name="tName"
							value="<bean:write name="rs" property="tName" />" />
					</td>
					<td height="31" colspan="2">
						姓名
					</td>
					<td width="28%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						性别
					</td>
					<td height="31">
						<bean:write name="rs" property="xb" />
					</td>
					<td height="31" colspan="2">
						民族
					</td>
					<td height="31">
						<bean:write name="rs" property="mzmc" />
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						毕业时间
					</td>
					<td height="31">
						<bean:write name="rs" property="bysj" />
					</td>
					<td height="31" colspan="2">
						专业年级
					</td>
					<td height="31">
						<bean:write name="rs" property="zynj" />
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						本学年家庭提供
					</td>
					<td height="31">
						<bean:write name="rs" property="jttgje" />
						元 /月
					</td>
					<td height="31" colspan="2">
						本学年助学金
					</td>
					<td height="31">
						<bean:write name="rs" property="zxje" />
						元
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						本学年奖学金
					</td>
					<td height="31">
						<bean:write name="rs" property="jxje" />
						元
					</td>
					<td height="31" colspan="2">
						本学年勤工助学收入
					</td>
					<td height="31">
						<bean:write name="rs" property="qgzxje" />
						元
					</td>
				</tr>
				<tr>
					<td height="31" colspan="2">
						本学年校内无息贷学金
					</td>
					<td height="31">
						<bean:write name="rs" property="xnwxdkje" />
						元
					</td>
					<td height="31" colspan="2">
						本学年其他收入
					</td>
					<td height="31">
						<bean:write name="rs" property="qtsrje" />
						元
					</td>
				</tr>
				<tr>
					<td width="9%" height="31">
						助学贷款
					</td>
					<td width="11%">
						申请金额
					</td>
					<td width="26%">
						<bean:write name="rs" property="zxdkje" />
						元
					</td>
					<td width="12%">
						助学贷款
					</td>
					<td width="14%">
						已发放金额
					</td>
					<td width="28%" height="31">
						<bean:write name="rs" property="yffzxdkje" />
						元
					</td>
				</tr>
				<tr>
					<td height="31" colspan="3">
					</td>
					<td height="31" colspan="2">
						审核结果：
					</td>
					<td>
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<logic:equal name="isZZSF" value="is">
					<tr>
						<logic:equal name="isXX" value="no">
							<td colspan="2">
								<div align="center">
									学生资助等级
								</div>
							</td>
							<td align="left">
								<html:select name="rs" property="zzdjdm" styleId="zzdjmc"
									onchange="getJe();">
									<html:option value="">------请选择------</html:option>
									<html:options collection="xszzDjList" property="zzdjdm"
										labelProperty="zzdjmc" />
								</html:select>
							</td>
						</logic:equal>
						<logic:equal name="isXX" value="is">
							<td colspan="2">
								<div align="center">
									学生资助等级
								</div>
							</td>
							<td align="left">
								<html:select name="rs" property="zzdjdm" styleId="zzdjmc"
									disabled="true">
									<html:option value="">------请选择------</html:option>
									<html:options collection="xszzDjList" property="zzdjdm"
										labelProperty="zzdjmc" />
								</html:select>
							</td>
						</logic:equal>
						<td colspan="2">
							<div align="center">
								资助金额
							</div>
						</td>
						<td>
							<input type="text" id="zzdjje" readonly="readonly" name="zzdjje"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zzdjje"/>">
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td height="31" colspan="2">
						申请资助理由
					</td>
					<td height="31" colspan="4">
						<bean:write name="rs" property="sqzzly" />
					</td>
				</tr>
				<logic:equal name="isXNMZ" value="is">
					<tr>
						<td colspan="2">
							<div align="left">
								辅导员审核意见
							</div>
						</td>
						<td colspan="4">
							<html:textarea name="rs" property="fdyshyj" rows='5' style="width:100%" onblur="yzdx(this,'fdyshyj')"/>
							<input type="hidden" id="fdyshyj" name="fdyshyj" value="">
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td colspan="2">
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<logic:equal name="isZZSF" value="is">
					<tr>
						<td colspan="2">
							<div align="left">
								<bean:message key="lable.xsgzyxpzxy" />总支负责人意见
							</div>
						</td>
						<td colspan="4">
							<html:textarea name="rs" property="xyzzfzryj" rows='5' style="width:100%" onblur="yzdx(this,'xyzzfzryj')"/>
							<input type="hidden" id="xyzzfzryj" name="xyzzfzryj" value="">
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td colspan="2">
						<div align="left">
							学校审核意见
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="xxshyj" rows='5' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
					</td>
				</tr>
			</table>
			<logic:equal name="isZZSF" value="is">
				<div class="buttontool" align="center">
					<button class="button2" onclick="yzt();" style="width:80px"
						id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:equal>
			<logic:equal name="isZZSF" value="no">
			<logic:equal name="isXNMZ" value="is">
				<div class="buttontool" align="center">
					<button class="button2" onclick="yzXNMZ();" style="width:80px"
						id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:equal>
			<logic:equal name="isXNMZ" value="no">
				<div class="buttontool" align="center">
					<button class="button2" onclick="yz();" style="width:80px"
						id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:equal>
			</logic:equal>
		</html:form>
	</body>
</html>
