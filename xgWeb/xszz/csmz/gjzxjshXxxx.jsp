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
			var user = document.getElementById('user').value;
			var xxsh = document.getElementById('xxsh').value;
			var xysh = document.getElementById('xysh').value;
			var fdyshyj = document.getElementById('fdyshyj').value;
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			if((("贫困生" == xysh)||("特困生" == xysh)) && (user == "fdy")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			if((("贫困生" == xxsh)||("特困生" == xxsh)) && (user == "xy")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			if(fdyshyj != null){
	         	if(fdyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("辅导员审核意见不能大于200个字符！");
	          		 return false;
	       		 }
	       	}
	       	if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于200个字符！");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("学校审核意见不能大于200个字符！");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/csmz_xszz.do?method=gjzxjshXxxx&actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
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
			<input type="hidden" name="xxsh" value="<bean:write name='rs' property='xxsh' />" />
			<input type="hidden" name="xysh" value="<bean:write name='rs' property='xysh' />" />
			<input type="hidden" name="user" value="<bean:write name="user" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="6" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							学年
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="xn" />
					</td>
					<td width="16%">
						<div align="center">
							申请时间
						</div>
					</td>
					<td width="34%">
						<bean:write name='rs' property="sqsj" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						学号
					</td>
					<td align="left" colspan="2">
						<bean:write name='rs' property="xh" />
					</td>
					<td scope="col">
						<div align="center">
							姓名
						</div>
					</td>
					<td scope="col">
						<bean:write name='rs' property="xm" />
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="xb" />
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<bean:write name='rs' property="sfzh" />
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="xymc" />
					</td>
					<td>
						<div align="center">
							专业
						</div>
					</td>
					<td>
						<bean:write name='rs' property="zymc" />
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							班级
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="bjmc" />
					</td>
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td>
						<bean:write name='rs' property="nj" />
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							入学年月
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="rxny" />
					</td>
					<td>
						<div align="center">
							出生年月
						</div>
					</td>
					<td>
						<bean:write name='rs' property="csny" />
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							民族
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="mzmc" />
					</td>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
						<bean:write name='rs' property="lxdh" />
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							家庭户口
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jthk" />
					</td>
					<td>
						<div align="center">
							家庭人口总数
						</div>
					</td>
					<td>
						<bean:write name='rs' property="jtzrks" />
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							人均月收入
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="rjysr" />
					</td>
					<td>
						<div align="center">
							家庭月总收入
						</div>
					</td>
					<td>
						<bean:write name='rs' property="jtyzsr" />
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							收入来源
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="srly" />
					</td>
					<td>
						<div align="center">
							邮政编码
						</div>
					</td>
					<td>
						<bean:write name='rs' property="yzbm" />
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							家庭住址
						</div>
					</td>
					<td colspan="4">
						<bean:write name='rs' property="jtzz" />
					</td>
				</tr>
				<tr>
					<td rowspan="6" width="4%">
						<div align="center">
							家<br />庭<br />成<br />员<br />情<br />况
						</div>
					</td>
					<td width="12%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="17%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="17%">
						<div align="center">
							与本人关系
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							工作或学习单位
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy1_xm" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy1_nl" />&nbsp;
					</td>
					<td>
						<bean:write name='rs' property="jtcy1_gx" />
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy1_gzdw" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy2_xm" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy2_nl" />&nbsp;
					</td>
					<td>
						<bean:write name='rs' property="jtcy2_gx" />
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy2_gzdw" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy3_xm" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy3_nl" />&nbsp;
					</td>
					<td>
						<bean:write name='rs' property="jtcy3_gx" />
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy3_gzdw" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy4_xm" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy4_nl" />&nbsp;
					</td>
					<td>
						<bean:write name='rs' property="jtcy4_gx" />
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy4_gzdw" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy5_xm" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy5_nl" />&nbsp;
					</td>
					<td>
						<bean:write name='rs' property="jtcy5_gx" />
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy5_gzdw" />
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							申请理由
						</div>
					</td>
					<td colspan="4">
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
						<td colspan="2">
							<div align="center">
								&nbsp;
							</div>
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
						<td>
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
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<logic:equal name="user" value="fdy">
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							辅导员审核意见
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="fdyshyj" rows='3' style="width:100%" onblur="yzdx(this,'fdyshyj')"/>
						<input type="hidden" id="fdyshyj" name="fdyshyj" value="">
						<input type="hidden" name="xyshyj" value="<bean:write name='rs' property='xyshyj' />" />
						<input type="hidden" name="xxshyj" value="<bean:write name='rs' property='xxshyj' />" />
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="user" value="xy">
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							辅导员审核意见
						</div>
					</td>
					<td colspan="4">
						<bean:write name='rs' property='fdyshyj' />
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="xyshyj" rows='3' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
						<input type="hidden" name="fdyshyj" value="<bean:write name='rs' property='fdyshyj' />" />
						<input type="hidden" name="xxshyj" value="<bean:write name='rs' property='xxshyj' />" />
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="user" value="xx">
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							辅导员审核意见
						</div>
					</td>
					<td colspan="4">
						<bean:write name='rs' property='fdyshyj' />
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="4">
						<bean:write name='rs' property='xyshyj' />
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="2">
						<div align="center">
							学校审核意见
						</div>
					</td>
					<td colspan="4">
						<html:textarea name="rs" property="xxshyj" rows='3' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
						<input type="hidden" name="fdyshyj" value="<bean:write name='rs' property='fdyshyj' />" />
						<input type="hidden" name="xyshyj" value="<bean:write name='rs' property='xyshyj' />" />
					</td>
				</tr>
				</logic:equal>
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
