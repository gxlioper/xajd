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
			var xyzzfzryj = document.getElementById('xyzzfzryj').value;
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
	       	if(xyzzfzryj != null){
	         	if(xyzzfzryj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("系总支负责人意见不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("学校审核意见不能大于200个字符");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/auditing_zzsf_lstd_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
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
					当前所在位置：学生资助 - 审核 - 绿色通道审核 - 单个审核
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
						<td align="left" colspan="3">
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
					<td colspan="2">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xb"/>
					</td>
					<td>
						<div align="center">
							出生年月
						</div>
					</td>
					<td>
						<bean:write name="rs" property="csrq"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							民族
						</div>
					</td>
					<td colspan="3">
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
							系别
						</div>
					</td>
					<td colspan="3">
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
					<td colspan="2">
						<div align="center">
							班级
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="bjmc"/>
					</td>
					<td>
						<div align="center">
							家庭户口
						</div>
					</td>
					<td align="center">
						<bean:write name="rs" property="jthk"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭人口总数
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtzrks"/>
					</td>
					<td>
						<div align="center">
							年总收入
						</div>
					</td>
					<td>
						<bean:write name="rs" property="nzsr"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							联系电话
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="lxdh"/>
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
					<td colspan="5">
							<bean:write name="rs" property="jtzz" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭经济困难主要原因
						</div>
					</td>
					<td colspan="5">
							<bean:write name="rs" property="jtjjknzyyy" />
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6">
						<div align="center">
							家庭主要成员情况
						</div>
					</td>
					<td width="14%" align="center">
						姓名
					</td>
					<td width="14%" align="center">
						年龄
					</td>
					<td width="12%" align="center">
						称谓
					</td>
					<td width="12%" align="center">
						月收入
					</td>
					<td colspan="2" align="center">
						所在单位
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy1_xm"/>
					</td>
					<td align="center">
						&nbsp;<bean:write name="rs" property="jtcy1_nl"/>&nbsp;
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy1_cw"/>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy1_ysr"/>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="jtcy1_szdw"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy2_xm"/>
					</td>
					<td align="center">
						&nbsp;<bean:write name="rs" property="jtcy2_nl"/>&nbsp;
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy2_cw"/>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy2_ysr"/>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="jtcy2_szdw"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy3_xm"/>
					</td>
					<td align="center">
						&nbsp;<bean:write name="rs" property="jtcy3_nl"/>&nbsp;
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy3_cw"/>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy3_ysr"/>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="jtcy3_szdw"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy4_xm"/>
					</td>
					<td align="center">
						&nbsp;<bean:write name="rs" property="jtcy4_nl"/>&nbsp;
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy4_cw"/>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy4_ysr"/>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="jtcy4_szdw"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy5_xm"/>
					</td>
					<td align="center">
						&nbsp;<bean:write name="rs" property="jtcy5_nl"/>&nbsp;
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy5_cw"/>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy5_ysr"/>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="jtcy5_szdw"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							所欠学费
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="qfqk_xf"/>
					</td>
					<td>
						<div align="center">
							所欠住宿费
						</div>
					</td>
					<td>
						<bean:write name="rs" property="qfqk_zsf"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							所欠教材费
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="qfqk_jcf"/>
					</td>
					<td>
						<div align="center">
							欠费合计
						</div>
					</td>
					<td>
						<bean:write name="rs" property="qfqk_hj"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							缴费计划
						</div>
					</td>
					<td colspan="5">
							<bean:write name="rs" property="jfjh" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							父母所在单位(或居委会、村委会)电话
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="fmszdwdh"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							申请时间
						</div>
					</td>
					<td colspan="3">
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
				<tr>
					<logic:equal name="isXX" value="no">
					<td colspan="2">
						<div align="center">
							学生资助等级
						</div>
					</td>
					<td colspan="3">
						<html:select name="rs" property="zzdjdm" styleId="zzdjmc" onchange="getJe();">
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
					<td colspan="3">
						<html:select name="rs" property="zzdjdm" styleId="zzdjmc" disabled="true">
							<html:option value="">------请选择------</html:option>
							<html:options collection="xszzDjList" property="zzdjdm"
								labelProperty="zzdjmc" />
						</html:select>
					</td>
					</logic:equal>
					<td>
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
							系总支负责人意见
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="xyzzfzryj" rows='5' style="width:100%" onblur="yzdx(this,'xyzzfzryj')"/>
						<input type="hidden" id="xyzzfzryj" name="xyzzfzryj" value="">
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
