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
		function yz(){
			var xyshyj = document.getElementById('xyshyj').value;
			var fdyshyj = document.getElementById('fdyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if((("一般困难" == xxsh) || ("困难" == xxsh) || ("特殊困难" == xxsh)) && (isXX != "is")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
	       	if(fdyshyj != null){
	         	if(fdyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("评议小组审核意见意见不能大于200个字符");
	          		 return false;
	       		 }
	       	}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("系审核意见不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("学校审核意见不能大于200个字符");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/auditing_zzsf_kns_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 -家庭经济困难学生基本情况表审核 - 单个审核
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
						<td colspan="8" align="center">
						</td>
					</tr>
				</thead>
				<tr>
						<td align="center" colspan="2">
							学号
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xh" />
						</td>
					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="2">
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
							出生年月
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="csrq"/>
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
					<td colspan="2">
						<bean:write name="rs" property="zzmmmc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sfzh"/>
					</td>
					<td>
						<div align="center">
							系别
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							专业
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zymc"/>
					</td>
					<td>
						<div align="center">
							班级
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="bjmc"/>
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
							学生类别
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xslb"/>
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
					<td>
						<div align="center">
							家庭人均年收入
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtrjnsr"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							申请理由
						</div>
					</td>
					<td colspan="5">
							<bean:write name="rs" property="sqly" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							申请时间
						</div>
					</td>
					<td align="left" colspan="2">
						<bean:write name="rs" property="sqsj" />
					</td>
					<td>
						<div align="center">
							审核结果
						</div>
					</td>
					<td colspan="2">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<logic:equal name="userType1" value="xx">
				<tr>
					<td colspan="2" align="center">
					评议小组审核结果
					</td>
					<td align="left" colspan="2">
						<bean:write name="rs" property="fdysh" />
					</td>
					<td>
						<div align="center">
							系部审核结果
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xysh" />
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="userType1" value="xy">
				<tr>
					<td colspan="2">
					评议小组审核结果
					</td>
					<td align="left" colspan="2">
						<bean:write name="rs" property="fdysh" />
					</td>
					<td colspan="3">
					</td>
				</tr>
				</logic:equal>
				<tr>
					<td colspan="2">
						<div align="center">
							评议小组意见
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="fdyshyj" rows='5' style="width:100%" onblur="yzdx(this,'fdyshyj')"/>
						<input type="hidden" id="fdyshyj" name="fdyshyj" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							系审核意见
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
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
