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
			var xxshyj = document.getElementById('xxshyj').value;
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("通过" == xxsh) && (isXX != "is")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
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
			refreshForm('/xgxt/auditing_zzsf_gjzxdk_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 国家助学贷款审核 - 单个审核
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
						<td align="center" width="16%">
							学号
						</td>
						<td align="left" width="34%">
							<bean:write name="rs" property="xh" />
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
							身份证号
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfzh"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							系别
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
					<td>
						<div align="center">
							专业
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							班级
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td>
						<bean:write name="rs" property="nj"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							入学日期
						</div>
					</td>
					<td>
						<bean:write name="rs" property="rxny"/>
					</td>
					<td>
						<div align="center">
							毕业日期
						</div>
					</td>
					<td>
						<bean:write name="rs" property="byny"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							学制
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xz"/>
					</td>
					<td>
						<div align="center">
							最高学历
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zgxl"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							籍贯
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jg"/>
					</td>
					<td>
						<div align="center">
							户籍所在地
						</div>
					</td>
					<td>
						<bean:write name="rs" property="hjszd"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							健康状况
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jkzk"/>
					</td>
					<td>
						<div align="center">
							婚姻状况
						</div>
					</td>
					<td>
						<bean:write name="rs" property="hyzk"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtdh"/>
					</td>
					<td>
						<div align="center">
							移动电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="yddh"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							E-Mail地址
						</div>
					</td>
					<td>
						<bean:write name="rs" property="e_mail"/>
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
					<td>
						<div align="center">
							家庭地址
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtdz"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							见证人1_姓名
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jzr1_xm"/>
					</td>
					<td>
						<div align="center">
							见证人1_性别
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jzr1_xb"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							见证人1_证件类型及号码
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jzr1_zjlxjhm"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							见证人1_与借款人关系
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jzr1_yjkrgx"/>
					</td>
					<td>
						<div align="center">
							见证人1_联系电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jzr1_lxdh"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							见证人1_通讯地址
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jzr1_txdz"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							见证人2_姓名
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jzr2_xm"/>
					</td>
					<td>
						<div align="center">
							见证人2_性别
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jzr2_xb"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							见证人2_证件类型及号码
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jzr2_zjlxjhm"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							见证人2_与借款人关系
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jzr2_yjkrgx"/>
					</td>
					<td>
						<div align="center">
							见证人2_联系电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jzr2_lxdh"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							见证人2_通讯地址
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jzr2_txdz"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							借款总金额
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jkzje"/>
					</td>
					<td>
						<div align="center">
							贷款期限
						</div>
					</td>
					<td>
						<bean:write name="rs" property="dkqx"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							还款方式
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="hkfs"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							贷款种类
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="dkzl"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							学费单价(按年)
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xfdj"/>
							元
					</td>
					<td>
						<div align="center">
							住宿费单价(按年)
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zsfdj"/>
							元
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							生活费单价
						</div>
					</td>
					<td>
						<bean:write name="rs" property="shfdj"/>
							元
					</td>
					<td colspan="2">
						<div align="left">
							按&nbsp;&nbsp;&nbsp;&nbsp;
							<bean:write name="rs" property="shffs"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							收款人帐户类型及帐号
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="skrzhlxjzh"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							申请时间
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sqsj"/>
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
				<logic:equal name="userType" value="xx">
				<tr>
					<td>
						<div align="center">
							调查人
						</div>
					</td>
					<td>
						<input type="text" id="dcr" maxlength="50" name="dcr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dcr"/>">
					</td>
					<td>
						<div align="center">
							审查人
						</div>
					</td>
					<td>
						<input type="text" id="scr" maxlength="50" name="scr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="scr"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							签批人
						</div>
					</td>
					<td>
						<input type="text" id="qpr" maxlength="50" name="qpr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qpr"/>">
					</td>
					<td>
						<div align="center">
							&nbsp;
						</div>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				</logic:equal>
				<tr>
					<td>
						<div align="center">
							系审核意见
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							学校审核意见
						</div>
					</td>
					<td colspan="3">
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
