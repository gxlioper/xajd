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
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于100个字符");
	          		 return false;
	       		 }
	       	}else if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("学校审核意见不能大于100个字符");
	          		 return false;
	       		 }
			 }
			 refreshForm('/xgxt/auditing_jsxx_sbknbz_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 伤、病困难补助审核 - 单个审核
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
					<td colspan="2">
						<div align="center">
							学号
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="xh" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="xm" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="xb" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							出生日期
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="csrq" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="xymc" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							系
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="zymc" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							班级
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="bjmc" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							家庭联系电话
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="lxdh" />
						</div>
					</td>
				</tr>
				<tr>
					<td scope="col" width="12%">
						<div align="center">
							家庭地址
						</div>
					</td>
					<td colspan="8" scope="col">
						<div align="left">
							<bean:write name="rs" property="jtdz" />
						</div>
					</td>
				</tr>
				<tr>
					<td rowspan="6" scope="row">
						<div align="center">
							家庭成员
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
					<td width="10%">
						<div align="center">
							身体状况
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							工作单位及职务
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							年收入
						</div>
					</td>
				</tr>
				<tr>
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
							<bean:write name="rs" property="jtcy1_stzk" />
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="jtcy1_gzdwjzw" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy1_nsr" />
						</div>
					</td>
				</tr>
				<tr>
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
							<bean:write name="rs" property="jtcy2_stzk" />
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="jtcy2_gzdwjzw" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy2_nsr" />
						</div>
					</td>
				</tr>
				<tr>
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
							<bean:write name="rs" property="jtcy3_stzk" />
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="jtcy3_gzdwjzw" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy3_nsr" />
						</div>
					</td>
				</tr>
				<tr>
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
							<bean:write name="rs" property="jtcy4_stzk" />
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="jtcy4_gzdwjzw" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy4_nsr" />
						</div>
					</td>
				</tr>
				<tr>
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
							<bean:write name="rs" property="jtcy5_stzk" />
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="jtcy5_gzdwjzw" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy5_nsr" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="9" scope="row">
						<div align="center">
							是否是烈军属:&nbsp;
							<bean:write name="rs" property="sfljs" />
							&nbsp;&nbsp;&nbsp;&nbsp; 是否是单亲:&nbsp;
							<bean:write name="rs" property="sfdq" />
							&nbsp;&nbsp;&nbsp;&nbsp; 父母是否是双下岗:&nbsp;
							<bean:write name="rs" property="sfsxg" />
							&nbsp;&nbsp;&nbsp;&nbsp; 父母是否有残疾:&nbsp;
							<bean:write name="rs" property="sfcj" />
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							学习情况
						</div>
					</td>
					<td colspan="8">
						<bean:write name="xxqk" />
						<br />
						<div align="right">
							(补考后有无不及格课程&nbsp;
							<bean:write name="rs" property="bkhywbjgkc" />
							&nbsp;)
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							操行等第
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="cxdd" />
						</div>
					</td>
					<td>
						<div align="center">
							平时表现
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="psbx" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							奖惩情况
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="jcqk" />
					</td>
				</tr>
				<tr>
					<td colspan="4" scope="row">
						每年家庭提供费用&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="mnjttgfy" />
						&nbsp;元
					</td>
					<td colspan="2">
						亲朋好友等提供&nbsp;
						<bean:write name="rs" property="mnqphytgfy" />
						&nbsp;元
					</td>
					<td colspan="3">
						合计每年提供&nbsp;
						<bean:write name="rs" property="mnhjtgfy" />
						&nbsp;元
					</td>
				</tr>
				<tr>
					<td colspan="4" scope="row">
						每年应缴纳各种费用&nbsp;
						<bean:write name="rs" property="mnyjgzfy" />
						&nbsp;元
					</td>
					<td colspan="2">
						每月平均生活费&nbsp;
						<bean:write name="rs" property="mypjshf" />
						&nbsp;元
					</td>
					<td colspan="3">
						合计每年费用&nbsp;
						<bean:write name="rs" property="mnhjfy" />
						&nbsp;元
					</td>
				</tr>
				<tr>
					<td colspan="9" scope="row">
						每年参加院内勤工助学获补贴&nbsp;
						<bean:write name="rs" property="mncjynqgzxbt" />
						&nbsp;元, &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 参加院外勤工助学活动获报酬&nbsp;
						<bean:write name="rs" property="cjywqgzxbc" />
						&nbsp;元
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							意外伤害和疾病概况
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="ywshhjbgk" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							伤、病时间
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="sbsj" />
						</div>
					</td>
					<td width="10%">
						<div align="center">
							诊治医院
						</div>
					</td>
					<td width="20%">
						<div align="center">
							<bean:write name="rs" property="zzyy" />
						</div>
					</td>
					<td width="10%">
						<div align="center">
							医药费
						</div>
					</td>
					<td width="10%">
						<div align="center">
							<bean:write name="rs" property="yyf" />
						</div>
					</td>
					<td width="10%">
						<div align="center">
							其他费用
						</div>
					</td>
					<td width="10%">
						<div align="center">
							<bean:write name="rs" property="qtfy" />
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							是否欠费
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="sfqf" />
						</div>
					</td>
					<td colspan="3">
						家庭保险理赔款&nbsp;
						<bean:write name="rs" property="jtbxlpk" />
						&nbsp;元
					</td>
					<td colspan="3">
						<bean:message key="lable.xsgzyxpzxy" />保险理赔款&nbsp;
						<bean:write name="rs" property="xybxlpk" />
						&nbsp;元
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							家庭经济状况及申请伤、病困难补助的理由
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="jtjjzkjsqsbbzly" />
					</td>
				</tr>
				<tr>
					<td colspan="5" scope="row">
						<div align="right">
							有无家庭所在地村、镇(居委、街办)出具的有关证明
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="ywzm" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							拟申请补助金额
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="nsqbzje" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							申请时间
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqsj" />
					</td>
					<td colspan="2">
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
				<tr>
					<td colspan="1">
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<div align="left">
							学校审核意见
						</div>
					</td>
					<td colspan="8">
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
