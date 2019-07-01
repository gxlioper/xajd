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
			
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			
			if(("通过" == xxsh) && (isXX != "is")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			if($("xyshyj")){
			var xyshyj = document.getElementById('xyshyj').value;
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}
	       	}
	       	
	       	if($("xyzzfzryj")){
	       	var xyzzfzryj = document.getElementById('xyzzfzryj').value;
	       	if(xyzzfzryj != null){
	         	if(xyzzfzryj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />总支负责人意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}
	       	}
	       	
	       	if($("xxshyj")){
	       	var xxshyj = document.getElementById('xxshyj').value;
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("学校审核意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}
	       	}
			
			refreshForm('/xgxt/auditing_gjjzxj_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}

		function yz(){
			
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;

			if(("通过" == xxsh) && (isXX != "is")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			
			if($("xyshyj")){
			var xyshyj = document.getElementById('xyshyj').value;
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}
	       	}
	       	
	       	if($("xxshyj")){
	       	var xxshyj = document.getElementById('xxshyj').value;
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("学校审核意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}
	       	}
			
			refreshForm('/xgxt/xszz_ynys.do?method=jzxjshOne&actDo=save&lx='+$("titName").value);Close();window.dialogArguments.document.getElementById('search_go').click();
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
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){
			document.forms[0].action = "/xgxt/csmz_xszz.do?method=gjjzxpsb";
			document.forms[0].submit();
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:write name="title"/>
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" id="xxsh" name="xxsh" value="<bean:write name="rs" property="xxsh" />" />
			<logic:equal name="xxmc" value="isCSMZ">
			<input type="hidden" id="jzxjzwmc" name="jzxjzwmc" value="<bean:write name="rs" property="jzxjzwmc" />" />
			</logic:equal>
			<input type="hidden" id="isXX" name="isXX" value="<bean:write name="isXX" />" />
			<input type="hidden" id="titName" name=titName
				value="<bean:write name="titName" scope="request"/>" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="8" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td width="16%">
						<div align="center">
							学号
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="xh" />
						<input type="hidden" id="xh" name="xh"
								value="<bean:write name="rs" property="xh"/>">
					</td>
					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="xm" />
						<input type="hidden" id="xm" name="xm"
								value="<bean:write name="rs" property="xm"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							出生年月
						</div>
					</td>
					<td>
						<bean:write name="rs" property="csny" />
					</td>
					<td>
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xb" />
						<input type="hidden" id="xb" name="xb"
								value="<bean:write name="rs" property="xb"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							入学时间
						</div>
					</td>
					<td>
						<bean:write name="rs" property="rxny" />
						<input type="hidden" id="rxny" name="rxny"
								value="<bean:write name="rs" property="rxny"/>">
					</td>
					<td>
						<div align="center">
							民族
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mzmc" />
						<input type="hidden" id="mzmc" name="mzmc"
								value="<bean:write name="rs" property="mzmc"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							大学
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xxmc" />
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xy" />
						<input type="hidden" id="xymc" name="xymc"
								value="<bean:write name="rs" property="xy"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							系
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xmc" />
						<input type="hidden" id="zymc" name="zymc"
								value="<bean:write name="rs" property="xmc"/>">
					</td>
					<td>
						<div align="center">
							班
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc" />
						<input type="hidden" id="bjmc" name="bjmc"
								value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfzh" />
					</td>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zzmmmc" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="lxdh" />
					</td>
					<td>
						<div align="center">
							上学年成绩
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sxncj" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭户口
						</div>
					</td>
					<td align="center">
						<bean:write name="rs" property="radjthk" />
					</td>
					<td>
						<div align="center">
							家庭户口人数
						</div>
					</td>
					<td>
						<bean:write name="rs" property="hkrs" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭月总收入
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtyzsr" />
					</td>
					<td>
						<div align="center">
							家庭人均收入
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtrjsr" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭收入来源
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtsrly" />
					</td>
					<td>
						<div align="center">
							审核结果
						</div>
					</td>
					<td>
						<html:select name="rs" property="yesNo">
							<html:options name="rs" collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<logic:equal name="titName" value="xy">
				<tr>
					<td>
						<div align="center">
							奖学金级别
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jb" />
					</td>
				</tr>
				</logic:equal>
				<tr>
					<td>
						<div align="center">
							申请理由
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly" />
					</td>
				</tr>
				<logic:equal name="actDo" value="view">
				<tr>
					<td>
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="3">
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
						<html:textarea name="rs" property="xyshyj" rows='5' styleId="xyshyj" style="width:100%" onblur="yzdx(this,'xyshyj')"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="left">
							学校审核意见
						</div>
					</td>
					<td colspan="3">
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
						<html:textarea name="rs" property="xxshyj" rows='5' styleId="xxshyj" style="width:100%" onblur="yzdx(this,'xxshyj')"/>
					</td>
				</tr>
				</logic:equal>
				<logic:notEqual name="actDo" value="view">
				<logic:equal value="xy" name="userType">
				<tr>
					<td>
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="3">
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
						<html:textarea name="rs" property="xyshyj" rows='5' styleId="xyshyj" style="width:100%" onblur="yzdx(this,'xyshyj')"/>
					</td>
				</tr>
				</logic:equal>
				<logic:equal value="xx" name="userType">
				<tr>
					<td>
						<div align="left">
							学校审核意见
						</div>
					</td>
					<td colspan="3">
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
						<html:textarea name="rs" property="xxshyj" rows='5' styleId="xxshyj" style="width:100%" onblur="yzdx(this,'xxshyj')"/>
					</td>
				</tr>
				</logic:equal>
				</logic:notEqual>
			</table>
			<div class="buttontool" align="center">
				<logic:notEqual name="actDo" value="view">
				<button type="button" class="button2" onclick="yz();" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				</logic:notEqual>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
