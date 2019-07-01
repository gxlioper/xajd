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
			var userType = document.getElementById('userType').value;
			var xxsh = document.getElementById('xxsh').value;
			var xysh = document.getElementById('xysh').value;
			var fdyshyj = document.getElementById('fdyshyj').value;
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			
			if(("未审核" != xysh ) && (userType == "fdy")){
				alert("系已审核，不能再修改审核结果!");
	          	return false;
			}
			if(("未审核" != xxsh ) && (userType == "xy")){
				alert("<bean:message key="lable.xsgzyxpzxy" />已审核，不能再修改审核结果!");
	          	return false;
			}
			if(fdyshyj != null){
	         	if(fdyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("班主任审核意见不能大于200个字符");
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
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于200个字符");
	          		 return false;
	       		 }
	       	}
			 refreshForm('/xgxt/zjjdzyjsxy_xszz.do?method=knsshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 困难生审核 - 单个审核
				</div>
			</div>
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	</script>
				</logic:match>
			</logic:present>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="9" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" colspan="3">
						学号
					</td>
					<td align="left" colspan="2">
						<bean:write name='rs' property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
					</td>
					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xb"/>
					</td>
					<td>
						<div align="center">
							年度
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="nd"/>
						<input type="hidden" id="nd" name="nd"
							value="<bean:write name='rs' property="nd" />" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
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
					<td colspan="3">
						<bean:write name="rs" property="zzmmmc"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							年级
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="nj"/>
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />名称
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							专业名称
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zymc"/>
					</td>
					<td>
						<div align="center">
							班级名称
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sfzh"/>
					</td>
					<td>
						<div align="center">
							出生日期
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="csrq"/>
					</td>
				</tr>
				<tr>
					<td colspan="9" style="padding:0;">
						<%@ include file="yhkhOne.jsp"%>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							家庭人口数
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtrks"/>
					</td>
					<td>
						<div align="center">
							是否孤残
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sfgc"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							是否单亲
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sfdq"/>
					</td>
					<td>
						<div align="center">
							是否烈士子女
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sflszn"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							家庭邮政编码
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="yzbm"/>
					</td>
					<td>
						<div align="center">
							家庭联系电话
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="lxdh"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							家庭详细通讯地址
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="xxtxdz"/>
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6">
						<div align="center">
							家
							<br>
							庭
							<br>
							成
							<br>
							员
							<br>
							情
							<br>
							况
						</div>
					</td>
					<td width="8%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="8%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="9%">
						<div align="center">
							与学生关系
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							工作或学习单位
						</div>
					</td>
					<td width="12%">
						<div align="center">
							职业
						</div>
					</td>
					<td width="11%">
						<div align="center">
							年收入(元)
						</div>
					</td>
					<td width="11%">
						<div align="center">
							健康状况
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy1_xm"/>
						</div>
					</td>
					<td width="6%">
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy1_nl"/>&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_gx"/>
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy1_gzdw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_zy"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_sr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_jkzk"/>
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy2_xm"/>
						</div>
					</td>
					<td width="6%">
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy2_nl"/>&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_gx"/>
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy2_gzdw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_zy"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_sr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_jkzk"/>
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy3_xm"/>
						</div>
					</td>
					<td width="6%">
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy3_nl"/>&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_gx"/>
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy3_gzdw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_zy"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_sr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_jkzk"/>
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy4_xm"/>
						</div>
					</td>
					<td width="6%">
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy4_nl"/>&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_gx"/>
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy4_gzdw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_zy"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_sr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_jkzk"/>
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy5_xm"/>
						</div>
					</td>
					<td width="6%">
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy5_nl"/>&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_gx"/>
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy5_gzdw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_zy"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_sr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_jkzk"/>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="9">
						<div align="center">
							已获资助情况
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							国家助学贷款
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="yhzzqk_gjzxdk"/>
					</td>
					<td>
						<div align="center">
							国家助学金
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="yhzzqk_gjzxj"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							国家励志奖学金
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="yhzzqk_gjlzjxj"/>
					</td>
					<td>
						<div align="center">
							国家奖学金
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="yhzzqk_gjjxj"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />奖学金
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="yhzzqk_xyjxj"/>
					</td>
					<td>
						<div align="center">
							勤工助学
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="yhzzqk_qgzx"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							学费减免
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="yhzzqk_xfjm"/>
					</td>
					<td>
						<div align="center">
							临时补助
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="yhzzqk_lxbz"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							当地政府、社会资助
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="yhzzqk_ddzfshjxj"/>
					</td>
					<td>
						<div align="center">
							合计
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="yhzzqk_hj"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							家庭遭受自然灾害情况
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtzszrzhqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							家庭遭受突发意外事件
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jttfywsj"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							家庭成员因残疾、年迈而劳动能力弱情况
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtcyycjnmndlqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							家庭成员失业情况
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtcysyqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							家庭欠债情况
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtqzqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							其他情况
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="qtqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							家庭详细经济情况说明
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtjjqkxxsm"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							民政部门邮政编码
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="mzbm_yzbm"/>
					</td>
					<td>
						<div align="center">
							民政部门联系电话
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="mzbm_lxdh"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							民政部门详细通讯地址
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="mzbm_xxtxdz"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							申请时间
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sqsj" />
					</td>
					<logic:equal name="userType" value="fdy">
						<td>
							<div align="center">
								班主任审核
							</div>
						</td>
						<td colspan="3">
							<html:select name="rs" property="fdysh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							<input type="hidden" id="xysh" name="xysh"
								value="<bean:write name='rs' property="xysh" />" />
							<input type="hidden" id="xxsh" name="xxsh"
								value="<bean:write name='rs' property="xxsh" />" />
						</td>
					</logic:equal>
					<logic:equal name="userType" value="xy">
						<td>
							<div align="center">
								班主任审核
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="fdysh" />
							<input type="hidden" id="fdysh" name="fdysh"
								value="<bean:write name='rs' property="fdysh" />" />
							<input type="hidden" id="xxsh" name="xxsh"
								value="<bean:write name='rs' property="xxsh" />" />
						</td>
					</logic:equal>
					<logic:equal name="userType" value="admin">
						<td>
							<div align="center">
								班主任审核
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="fdysh" />
							<input type="hidden" id="fdysh" name="fdysh"
								value="<bean:write name='rs' property="fdysh" />" />
							<input type="hidden" id="xysh" name="xysh"
								value="<bean:write name='rs' property="xysh" />" />
						</td>
					</logic:equal>
				</tr>
				<logic:equal name="userType" value="xy">
					<tr>
						<td colspan="3">
							<div align="center">
								系审核
							</div>
						</td>
						<td colspan="2">
							<html:select name="rs" property="xysh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td colspan="4">
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="admin">
					<tr>
						<td colspan="3">
							<div align="center">
								系审核
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="xysh" />
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核
							</div>
						</td>
						<td colspan="3">
							<html:select name="rs" property="xxsh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="fdy">
				<tr>
					<td colspan="3">
						<div align="center">
							班主任审核意见
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="fdyshyj" rows='5'
							style="width:100%" onblur="yzdx(this,'fdyshyj')" />
						<input type="hidden" id="fdyshyj" name="fdyshyj" value="">
						<input type="hidden" id="xyshyj" name="xyshyj"
								value="<bean:write name='rs' property="xyshyj" />" />
						<input type="hidden" id="xxshyj" name="xxshyj"
								value="<bean:write name='rs' property="xxshyj" />" />
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="userType" value="xy">
				<tr>
					<td colspan="3">
						<div align="center">
							班主任审核意见
						</div>
					</td>
					<td colspan="6">
						<bean:write name='rs' property="fdyshyj" />
						<input type="hidden" id="fdyshyj" name="fdyshyj"
								value="<bean:write name='rs' property="fdyshyj" />" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							系审核意见
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="xyshyj" rows='5'
							style="width:100%" onblur="yzdx(this,'xyshyj')" />
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
						<input type="hidden" id="xxshyj" name="xxshyj"
								value="<bean:write name='rs' property="xxshyj" />" />
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="userType" value="admin">
				<tr>
					<td colspan="3">
						<div align="center">
							班主任审核意见
						</div>
					</td>
					<td colspan="6">
						<bean:write name='rs' property="fdyshyj" />
						<input type="hidden" id="fdyshyj" name="fdyshyj"
								value="<bean:write name='rs' property="fdyshyj" />" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							系审核意见
						</div>
					</td>
					<td colspan="6">
						<bean:write name='rs' property="xyshyj" />
						<input type="hidden" id="xyshyj" name="xyshyj"
								value="<bean:write name='rs' property="xyshyj" />" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="xxshyj" rows='5'
							style="width:100%" onblur="yzdx(this,'xxshyj')" />
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
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
				<button type="button" class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
