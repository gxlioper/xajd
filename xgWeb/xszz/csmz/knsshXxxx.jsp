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
			var user = document.getElementById('user').value;
			var xxsh = document.getElementById('xxsh').value;
			var xysh = document.getElementById('xysh').value;
			if((("贫困生" == xysh)||("特困生" == xysh)) && (user == "fdy")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			if((("贫困生" == xxsh)||("特困生" == xxsh)) && (user == "xy")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于500个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("学校审核意见不能大于500个字符");
	          		 return false;
	       		 }
	       	}
		
			refreshForm('/xgxt/csmz_xszz.do?method=knsshXxxx&actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 贫困生审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" name="xxsh" value="<bean:write name='rs' property='xxsh' />" />
			<input type="hidden" name="xysh" value="<bean:write name='rs' property='xysh' />" />
			<input type="hidden" name="user" value="<bean:write name="user" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="9" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" colspan="2">
						学号
					</td>
					<td align="left" width="34%">
						<bean:write name='rs' property="xh" />
					</td>
					<td colspan="2" scope="col">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="2" scope="col">
						<bean:write name='rs' property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xb" />
					</td>
					<td colspan="2">
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="sfzh" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xymc" />
					</td>
					<td colspan="2">
						<div align="center">
							专业
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="zymc" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							班级
						</div>
					</td>
					<td>
						<bean:write name='rs' property="bjmc" />
					</td>
					<td colspan="2">
						<div align="center">
							年级
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="nj" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							民族
						</div>
					</td>
					<td>
						<bean:write name='rs' property="mzmc" />
					</td>
					<td colspan="2">
						<div align="center">
							政治面貌
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="zzmmmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							入学年月
						</div>
					</td>
					<td>
						<bean:write name='rs' property="rxny" />
					</td>
					<td colspan="2">
						<div align="center">
							出生日期
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="csrq" />
					</td>
				</tr>
				<tr>
					<td colspan="7" scope="row">
						<div align="center">
							学生家庭经济情况调查表
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							现住家庭详细地址
						</div>
					</td>
					<td colspan="5">
						<bean:write name='rs' property="xzjtxxdz" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							邮政编码
						</div>
					</td>
					<td>
						<bean:write name='rs' property="yzbm" />
					</td>
					<td colspan="2">
						<div align="center">
							家庭电话
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtdh" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							原学习学校
						</div>
					</td>
					<td>
						<bean:write name='rs' property="yxxxx" />
					</td>
					<td colspan="2">
						<div align="center">
							籍贯
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jg" />
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6" scope="row">
						<div align="center">
							直
							<br />
							系
							<br />
							家
							<br />
							庭
							<br />
							成
							<br />
							员
						</div>
					</td>
					<td width="12%">
						<div align="center">
							姓名
						</div>
					</td>
					<td>
						<div align="center">
							现在何处工作及职务
						</div>
					</td>
					<td width="8%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="8%">
						<div align="center">
							关系
						</div>
					</td>
					<td width="14%">
						<div align="center">
							每月工作
							<br />
							收入(元)
						</div>
					</td>
					<td width="20%">
						<div align="center">
							联系电话
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy1_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy1_gzdwjzw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy1_nl"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy1_gx"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy1_ysr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy1_lxdh"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy2_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy2_gzdwjzw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy2_nl"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy2_gx"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy2_ysr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy2_lxdh"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy3_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy3_gzdwjzw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy3_nl"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy3_gx"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy3_ysr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy3_lxdh"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy4_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy4_gzdwjzw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy4_nl"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy4_gx"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy4_ysr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy4_lxdh"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy5_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy5_gzdwjzw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy5_nl"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy5_gx"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy5_ysr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy5_lxdh"/>
						</div>
					</td>
				</tr>
				<tr>
					<td rowspan="2" scope="row">
						<div align="center">
							家庭
							<br />
							经济
							<br />
							收入
						</div>
					</td>
					<td>
						<div align="center">
							城镇
						</div>
					</td>
					<td colspan="5">
						<div align="left">
							全家年收入
							<bean:write name="rs" property="jtjj_cz_qjnsr"/>
							元，人均月收入
							<bean:write name="rs" property="jtjj_cz_rjysr"/>
							元
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							农村
						</div>
					</td>
					<td colspan="5">
						<div align="left">
							当年总收入总计
							<bean:write name="rs" property="jtjj_nc_dnzsr"/>
							元，人均年收入
							<bean:write name="rs" property="jtjj_nc_rjnsr"/>
							元
							<br />
							<font color="red">（注：务农人员必须把农作物和其他收入转换为货币收入，收入不能为零。）</font>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭人口数
						</div>
					</td>
					<td>
						<bean:write name='rs' property="jtrks" />
					</td>
					<td colspan="2">
						<div align="center">
							当地最低社会生活保障
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="ddzdshshbz" />
						(元/年)
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="5" scope="row">
						<div align="center">
							主
							<br />
							要
							<br />
							社
							<br />
							会
							<br />
							关
							<br />
							系
						</div>
					</td>
					<td width="12%">
						<div align="center">
							姓名
						</div>
					</td>
					<td>
						<div align="center">
							现在何处工作及职务
						</div>
					</td>
					<td width="8%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="8%">
						<div align="center">
							关系
						</div>
					</td>
					<td width="14%">
						<div align="center">
							每月工作
							<br />
							收入(元)
						</div>
					</td>
					<td width="20%">
						<div align="center">
							与你家经济联系
							<br />
							或供养情况
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx1_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx1_gzdwjzw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx1_nl"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx1_gx"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx1_ysr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx1_ynjtjjlxhgyqk"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx2_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx2_gzdwjzw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx2_nl"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx2_gx"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx2_ysr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx2_ynjtjjlxhgyqk"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx3_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx3_gzdwjzw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx3_nl"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx3_gx"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx3_ysr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx3_ynjtjjlxhgyqk"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx4_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx4_gzdwjzw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx4_nl"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx4_gx"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx4_ysr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx4_ynjtjjlxhgyqk"/>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭经济困难情况及原因
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="jtjjknqkjyy"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							父亲单位
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fqdw" />
					</td>
					<td colspan="2">
						<div align="center">
							母亲单位
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="mqdw" />
					</td>
				</tr>
				<tr>
					<td colspan="7" scope="row">
						<div align="center">
							贫困学生登记表
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							所住寝室
						</div>
					</td>
					<td>
						<bean:write name='rs' property="szqs" />
					</td>
					<td colspan="2">
						<div align="center">
							寝室电话
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="qsdh" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							特长
						</div>
					</td>
					<td>
						<bean:write name='rs' property="tc" />
					</td>
					<td colspan="2">
						<div align="center">
							就餐卡卡号
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jckkh" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							何时参加过勤工助学
						</div>
					</td>
					<td colspan="5">
						<bean:write name='rs' property="hscjgqgzx" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							担任学生干部情况
						</div>
					</td>
					<td colspan="5">
						<bean:write name='rs' property="drxsgbqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							在校期间曾何时获过何种奖学金
						</div>
					</td>
					<td colspan="5">
						<bean:write name='rs' property="zxqjhschghzjxj" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭情况
						</div>
					</td>
					<td align="right">
						是否贫困县
					</td>
					<td colspan="4">
						<bean:write name='rs' property="pkx" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							父亲职业
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fqzy" />
					</td>
					<td colspan="2">
						<div align="center">
							母亲职业
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="mqzy" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							上年家庭收入
						</div>
					</td>
					<td>
						<bean:write name='rs' property="snjtsr" />
					</td>
					<td colspan="2">
						<div align="center">
							家庭经济来源
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtjjly" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭每月提供生活费
						</div>
					</td>
					<td>
						<bean:write name='rs' property="jtmytgshf" />
					</td>
					<td colspan="2">
						<div align="center">
							家中是否有欠债
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jzsfyqz" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							父母是否有病或残疾
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fmsfycj" />
					</td>
					<td colspan="2">
						<div align="center">
							父母是否健在
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="fmsfjz" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							申请理由
						</div>
					</td>
					<td colspan="5">
						<bean:write name='rs' property="sqly" />
					</td>
				</tr>
				<logic:equal name="user" value="xy">
					<tr>
						<td colspan="2">
							<div align="center">
								辅导员审核
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="fdysh" />
						</td>
						<td colspan="3">
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="user" value="xx">
					<tr>
						<td colspan="2">
							<div align="center">
								辅导员审核
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="fdysh" />
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="xysh" />
						</td>
					</tr>
				</logic:equal>
				<tr>
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
					<td>
						<div align="center">
							申请时间
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="left">
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
						<div align="left">
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
				<button class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
