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
	         	if(fdyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 400){	         
	          		 alert("辅导员审核意见不能大于400个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 400){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于400个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 400){	         
	          		 alert("学校审核意见不能大于400个字符");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/jhzyjsxy_xszz.do?method=bkzxjjshSave');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/jhzyjsxy_xszz" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 困难生审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" width="16%">
							学号
					</td>
					<td width="34%">
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
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
					<td>
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xb"/>
					</td>
					<td>
						<div align="center">
							出生日期
						</div>
					</td>
					<td>
						<bean:write name="rs" property="csrq"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							民族
						</div>
					</td>
					<td>
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
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td>
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
					<td>
						<div align="center">
							专业名称
						</div>
					</td>
					<td>
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
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfzh"/>
					</td>
					<td>
						<div align="center">
							年度
						</div>
					</td>
					<td>
						<input type="hidden" id="nd" name="nd"
							value="<bean:write name="rs" property="nd"/>">
						<bean:write name='rs' property="nd" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭住址及邮编
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtzzjyb" maxlength="100" name="jtzzjyb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzzjyb"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭年经济纯收入
						</div>
					</td>
					<td>
						<input type="text" id="jtnjjcsr" maxlength="5" name="jtnjjcsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtnjjcsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							家庭人口数
						</div>
					</td>
					<td>
						<input type="text" id="jtrks" name="jtrks" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrks"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							德育
						</div>
					</td>
					<td>
						<input type="text" id="dy" maxlength="100" name="dy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dy"/>">
					</td>
					<td>
						<div align="center">
							学费缴纳情况
						</div>
					</td>
					<td>
						<input type="text" id="xfjnqk" maxlength="100" name="xfjnqk" style="width:100%"
						value="<bean:write name="rs" property="xfjnqk"/>"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							助学贷款及金额
						</div>
					</td>
					<td>
						<html:textarea name="rs" property="zxdkjje" rows='5' style="width:100%"/>
					</td>
					<td>
						<div align="center">
							获国家、校奖学金金额
						</div>
					</td>
					<td>
						<html:textarea name="rs" property="gjxjxjje" rows='5' style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							助学贷款及金额获国家励志、助学金及金额
						</div>
					</td>
					<td>
						<html:textarea name="rs" property="gjlzzxjjje" rows='5' style="width:100%"/>
					</td>
					<td>
						<div align="center">
							本学年获困难补助及金额
						</div>
					</td>
					<td>
						<html:textarea name="rs" property="bxnhknbzjje" rows='5' style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							学习总体情况
						</div>
					</td>
					<td	colspan="3">
						<html:textarea name="rs" property="xxztqk" rows='5' style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							曾受何种奖励
						</div>
					</td>
					<td	colspan="3">
						<html:textarea name="rs" property="cshzjl" rows='5' style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							曾受何种违纪处分
						</div>
					</td>
					<td	colspan="3">
						<html:textarea name="rs" property="cshzwjcf" rows='5' style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							助学基金资助等级及金额
						</div>
					</td>
					<td colspan="3">
						<html:select name="rs" property="zxjjdm" styleId="zxjjdm"
										onchange="">
							<html:option value=""></html:option>
							<html:options collection="zzdjList" property="dm"
											labelProperty="dj" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							申请理由
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="sqly" rows='5' style="width:100%" onblur="yzdx(this,'sqly')"/>
						<input type="hidden" id="sqly" name="sqly" value="">
					</td>
				</tr>
				<logic:equal name="userType" value="fdy">
					<tr>
						<td>
							<div align="center">
								辅导员审核
							</div>
						</td>
						<td>
							<html:select name="rs" property="fdysh">
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
						<td>
							<div align="center">
								辅导员审核意见<br />
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="fdyshyj" rows='5' style="width:100%" onblur="yzdx(this,'fdyshyj');chLeng(this,100);"/>
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
						<td>
							<div align="center">
								辅导员审核
							</div>
						</td>
						<td>
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
						<td>
							<div align="center">
								辅导员审核意见
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="fdyshyj" rows='5' style="width:100%" onblur="yzdx(this,'fdyshyj');chLeng(this,100);"/>
							<input type="hidden" id="fdyshyj" name="fdyshyj" value="">
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核
							</div>
						</td>
						<td>
							<html:select name="rs" property="xysh">
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
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核意见
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj');chLeng(this,100);"/>
							<input type="hidden" id="xyshyj" name="xyshyj" value="">
							<input type="hidden" id="xxshyj" name="xxshyj"
								value="<bean:write name="rs" property="xxshyj"/>">
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="xx">
					<tr>
						<td>
							<div align="center">
								辅导员审核
							</div>
						</td>
						<td>
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
						<td>
							<div align="center">
								辅导员审核意见
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="fdyshyj" rows='5' style="width:100%" onblur="yzdx(this,'fdyshyj');chLeng(this,100);"/>
							<input type="hidden" id="fdyshyj" name="fdyshyj" value="">
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核
							</div>
						</td>
						<td>
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
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核意见
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj');chLeng(this,100);"/>
							<input type="hidden" id="xyshyj" name="xyshyj" value="">
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								学校审核
							</div>
						</td>
						<td>
							<html:select name="rs" property="xxsh">
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
						<td>
							<div align="center">
								学校审核意见
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="xxshyj" rows='5' style="width:100%" onblur="yzdx(this,'xxshyj');chLeng(this,100);"/>
							<input type="hidden" id="xxshyj" name="xxshyj" value="">
							<input type="hidden" id="xyshyj" name="xyshyj"
								value="<bean:write name="rs" property="xyshyj"/>">
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
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
