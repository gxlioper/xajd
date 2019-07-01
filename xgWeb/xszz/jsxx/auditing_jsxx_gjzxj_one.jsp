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
			refreshForm('/xgxt/auditing_jsxx_gjzxj_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function getJe1(){
			var temp = $('xytydjdm').options[$('xytydjdm').selectedIndex].innerHTML;
			msgArray = new Array();
			msgArray = temp.split('||');
			var je = "";
			if (msgArray.length > 1) {
				je = msgArray[1];
			}
			document.getElementById('xytysqje').value=je;
		}
		
		function getJe2(){
			var temp = $('xxtydjdm').options[$('xxtydjdm').selectedIndex].innerHTML;
			msgArray = new Array();
			msgArray = temp.split('||');
			var je = "";
			if (msgArray.length > 1) {
				je = msgArray[1];
			}
			document.getElementById('xxtyshje').value=je;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 国家助学金审核 - 单个审核
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
					<td colspan="2">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xb" />
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sfzh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							民族
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="mzmc" />
					</td>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zzmmmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xymc" />
					</td>
					<td>
						<div align="center">
							专业
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							班级
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td>
						<div align="center">
							班团职务
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="btzw" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							学生电话
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xsdh" />
					</td>
					<td>
						<div align="center">
							家庭电话
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtdh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭地址
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtdz" />
					</td>
				</tr>
				<tr>
					<td rowspan="6" scope="row" width="4%">
						<div align="center">
							家
							<br />
							庭
							<br />
							主
							<br />
							要
							<br />
							成
							<br />
							员
							<br />
							情
							<br />
							况
						</div>
					</td>
					<td width="12%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="10%">
						<div align="center">
							称谓
						</div>
					</td>
					<td width="10%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="14%">
						<div align="center">
							身体状况
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							工作(学习)单位及职务
						</div>
					</td>
					<td width="12%">
						<div align="center">
							年收入(元)
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy1_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy1_nsr" />&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy2_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy2_nsr" />&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy3_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy3_nsr" />&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy4_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy4_nsr" />&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy5_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy5_nsr" />&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							孤儿
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfgr" />
					</td>
					<td>
						<div align="center">
							烈士子女
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sflszn" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							无收入户
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfwsrh" />
					</td>
					<td>
						<div align="center">
							重病户
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sfzbh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							低保户
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfdbh" />
					</td>
					<td>
						<div align="center">
							父母双下岗
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sffmsxg" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							纯农户
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfcnh" />
					</td>
					<td>
						<div align="center">
							低收入(家庭收入不足以支付就学基本费用)
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sfdsr" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							每年应缴纳各种费用
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="nyyjngzfy" />
					</td>
					<td>
						<div align="center">
							家庭每年提供
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtmntg" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							合计每年尚缺费用
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="hjmnsqfy" />
					</td>
					<td>
						<div align="center">
							欠缴学费数
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="qjxfs" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							已贷款种类及金额
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="yhdkzljje" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭经济困难具体情况
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtjjknqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							申请助学金的理由、金额
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="sqly" />
						<div align="right">
						申请金额:&nbsp;
						<bean:write name="rs" property="xssqdjje" />&nbsp;元&nbsp;&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							备注
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="bz" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							申请时间
						</div>
					</td>
					<td align="left" colspan="3">
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
				<logic:equal name="isXX" value="no">
					<tr>
						<td colspan="2">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />同意等级
							</div>
						</td>
						<td align="left" colspan="3">
							<html:select name="rs" property="xytydjdm" styleId="xytydjdm" onchange="getJe1();">
							<html:option value="">------请选择------</html:option>
							<html:options collection="gjzxjList" property="djdm"
								labelProperty="djmc" />
							</html:select>
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />同意金额
							</div>
						</td>
						<td colspan="2">
							<input type="text" id="xytysqje" readonly="readonly" name="xytysqje"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xytysqje"/>">
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="isXX" value="is">
					<tr>
						<td colspan="2">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />同意等级
							</div>
						</td>
						<td align="left" colspan="3">
							<bean:write name="rs" property="xytydjmc"/>
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />同意金额
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="xytysqje"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />同意申请金额
							</div>
						</td>
						<td align="left" colspan="3">
							<html:select name="rs" property="xxtydjdm" styleId="xxtydjdm" onchange="getJe2();">
							<html:option value="">------请选择------</html:option>
							<html:options collection="gjzxjList" property="djdm"
								labelProperty="djmc" />
							</html:select>
						</td>
						<td>
							<div align="center">
								学校同意申请金额
							</div>
						</td>
						<td colspan="2">
							<input type="text" id="xxtyshje" readonly="readonly" name="xxtyshje"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xxtyshje"/>">
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="6">
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
					<td colspan="6">
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
