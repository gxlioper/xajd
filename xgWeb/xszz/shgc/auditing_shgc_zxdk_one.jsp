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
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			var xyshyj = document.getElementById('XYSHYJ').value;
			var xxshyj = document.getElementById('XXSHYJ').value;
			
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
			if(("通过" == xxsh) && (isXX != "is")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			
			refreshForm('/xgxt/auditing_shgc_zxdk_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 助学贷款审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" id="xxsh" name="xxsh" value="<bean:write name="rs" property="xxsh" />" />
			<input type="hidden" id="isXX" name="isXX" value="<bean:write name="isXX" />" />
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="8" align="center">
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
						<input type="hidden" name="XH" value="<bean:write name="rs" property="XH" />" />
						<bean:write name="rs" property="XH" />
					</td>
					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="34%">
						<input type="hidden" name="XM" value="<bean:write name="rs" property="XM" />" />
						<bean:write name="rs" property="XM" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							出生年月
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="CSNY"
							value="<bean:write name="rs" property="CSNY" />" />
						<bean:write name="rs" property="CSNY" />
					</td>
					<td>
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<input type="hidden" name="XB" value="<bean:write name="rs" property="XB" />" />
						<bean:write name="rs" property="XB" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							学制
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="XZ" value="<bean:write name="rs" property="XZ" />" />
						<bean:write name="rs" property="XZ" />
					</td>
					<td>
						<div align="center">
							学历
						</div>
					</td>
					<td>
						<input type="hidden" name="XL" value="<bean:write name="rs" property="XL" />" />
						<bean:write name="rs" property="XL" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							大学
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="XXMC"
							value="<bean:write name="rs" property="XXMC" />" />
						<bean:write name="rs" property="XXMC" />
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<input type="hidden" name="XYMC"
							value="<bean:write name="rs" property="XYMC" />" />
						<bean:write name="rs" property="XYMC" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							系
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="XMC" value="<bean:write name="rs" property="XMC" />" />
						<bean:write name="rs" property="XMC" />
					</td>
					<td>
						<div align="center">
							宿舍电话
						</div>
					</td>
					<td>
						<input type="hidden" name="SSDH"
							value="<bean:write name="rs" property="SSDH" />" />
						<bean:write name="rs" property="SSDH" />
					</td>
				</tr>
				<logic:notEmpty name="isSHGC">
					<logic:equal name="isSHGC" value="is">
						<tr>
							<td colspan="2">
								<div align="center">
									卡号
								</div>
							</td>
							<td colspan="2">
								<bean:write name="rs" property="kh" />
							</td>
							<td>
								<div align="center"></div>
							</td>
							<td></td>
						</tr>
					</logic:equal>
					<logic:equal name="isSHGC" value="no">
					</logic:equal>
				</logic:notEmpty>
				<tr>
					<td colspan="2">
						<div align="center">
							贷款总额
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="DKZE"
							value="<bean:write name="rs" property="DKZE" />" />
						<bean:write name="rs" property="DKZE" />
					</td>
					<td>
						<div align="center">
							学费贷款金额
						</div>
					</td>
					<td>
						<input type="hidden" name="XFDKS"
							value="<bean:write name="rs" property="XFDKS" />" />
						<bean:write name="rs" property="XFDKS" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							生活费贷款金额
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="SHFDKS"
							value="<bean:write name="rs" property="SHFDKS" />" />
						<bean:write name="rs" property="SHFDKS" />
					</td>
					<td>
						<div align="center">
							住宿费贷款金额
						</div>
					</td>
					<td>
						<input type="hidden" name="ZSFDKS"
							value="<bean:write name="rs" property="ZSFDKS" />" />
						<bean:write name="rs" property="ZSFDKS" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							贷款期限开始时间
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="DKQXKSSJ"
							value="<bean:write name="rs" property="DKQXKSSJ" />" />
						<bean:write name="rs" property="DKQXKSSJ" />
					</td>
					<td>
						<div align="center">
							贷款期限结束时间
						</div>
					</td>
					<td>
						<input type="hidden" name="DKQXJSSJ"
							value="<bean:write name="rs" property="DKQXJSSJ" />" />
						<bean:write name="rs" property="DKQXJSSJ" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							年度
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="ND" value="<bean:write name="rs" property="ND" />" />
						<bean:write name="rs" property="ND" />
					</td>
					<td>
						<div align="center">
							申请时间
						</div>
					</td>
					<td>
						<input type="hidden" name="SQSJ"
							value="<bean:write name="rs" property="SQSJ" />" />
						<bean:write name="rs" property="SQSJ" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭住址
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="JTZZ"
							value="<bean:write name="rs" property="JTZZ" />" />
						<bean:write name="rs" property="JTZZ" />
					</td>
					<td>
						<div align="center">
							家庭人均收入
						</div>
					</td>
					<td>
						<input type="hidden" name="JTRJSR"
							value="<bean:write name="rs" property="JTRJSR" />" />
						<bean:write name="rs" property="JTRJSR" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							邮政编码
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="YZBM"
							value="<bean:write name="rs" property="YZBM" />" />
						<bean:write name="rs" property="YZBM" />
					</td>
					<td>
						<div align="center">
							电话
						</div>
					</td>
					<td>
						<input type="hidden" name="DH" value="<bean:write name="rs" property="DH" />" />
						<bean:write name="rs" property="DH" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							父亲姓名
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="FQXM"
							value="<bean:write name="rs" property="FQXM" />" />
						<bean:write name="rs" property="FQXM" />
					</td>
					<td>
						<div align="center">
							母亲姓名
						</div>
					</td>
					<td>
						<input type="hidden" name="MQXM"
							value="<bean:write name="rs" property="MQXM" />" />
						<bean:write name="rs" property="MQXM" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							父亲职业
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="FQZY"
							value="<bean:write name="rs" property="FQZY" />" />
						<bean:write name="rs" property="FQZY" />
					</td>
					<td>
						<div align="center">
							母亲职业
						</div>
					</td>
					<td>
						<input type="hidden" name="MQZY"
							value="<bean:write name="rs" property="MQZY" />" />
						<bean:write name="rs" property="MQZY" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							父亲身份证号
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="FQSFZH"
							value="<bean:write name="rs" property="FQSFZH" />" />
						<bean:write name="rs" property="FQSFZH" />
					</td>
					<td>
						<div align="center">
							母亲身份证号
						</div>
					</td>
					<td>
						<input type="hidden" name="MQSFZH"
							value="<bean:write name="rs" property="MQSFZH" />" />
						<bean:write name="rs" property="MQSFZH" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭收入
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="JTSR"
							value="<bean:write name="rs" property="JTSR" />" />
						<bean:write name="rs" property="JTSR" />
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
				<tr>
					<td colspan="2">
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="XYSHYJ" rows='5' style="width:100%" onblur="yzdx(this,'XYSHYJ')"/>
						<input type="hidden" id="XYSHYJ" name="XYSHYJ" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="left">
							学校审核意见
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="XXSHYJ" rows='5' style="width:100%" onblur="yzdx(this,'XXSHYJ')"/>
						<input type="hidden" id="XXSHYJ" name="XXSHYJ" value="">
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="yz();" style="width:80px"
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
