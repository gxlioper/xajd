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
			
			if(fdyshyj != null){
	         	if(fdyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("辅导员审核意见不能大于200个字符");
	          		 return false;
	       		 }
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
			
			if((("贫困生" == xysh)||("特困生" == xysh)) && (user == "fdy")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
			if((("贫困生" == xxsh)||("特困生" == xxsh)) && (user == "xy")){
				alert("学校审核已通过，不能再修改审核结果!");
	          	return false;
			}
		
			refreshForm('/xgxt/bjlhdx_xszz.do?method=gjzxdkshXxxx&actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
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
			<input type="hidden" name="xxsh"
				value="<bean:write name='rs' property='xxsh' />" />
			<input type="hidden" name="xysh"
				value="<bean:write name='rs' property='xysh' />" />
			<input type="hidden" name="user" value="<bean:write name="user" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="9" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td scope="row">
						<div align="center">
							学年
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xn" />
					</td>
					<td>
						<div align="center">
							申请时间
						</div>
					</td>
					<td>
						<bean:write name='rs' property="sqsj" />
					</td>
				</tr>
				<tr>
					<td align="center" width="16%">
						学号
					</td>
					<td align="left">
						<bean:write name='rs' property="xh" />
					</td>
					<td width="16%" scope="col">
						<div align="center">
							姓名
						</div>
					</td>
					<td scope="col">
						<bean:write name='rs' property="xm" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td>
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
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
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
					<td scope="row">
						<div align="center">
							班级
						</div>
					</td>
					<td>
						<bean:write name='rs' property="bjmc" />
					</td>
					<td>
						<div align="center">
							学制
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xz" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							入学年月
						</div>
					</td>
					<td>
						<bean:write name='rs' property="rxny" />
					</td>
					<td>
						<div align="center">
							毕业年月
						</div>
					</td>
					<td>
						<bean:write name='rs' property="byny" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							移动电话
						</div>
					</td>
					<td>
						<bean:write name='rs' property="yddh" />
					</td>
					<td>
						<div align="center">
							固定电话
						</div>
					</td>
					<td>
						<bean:write name='rs' property="gddh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							培养层次
						</div>
					</td>
					<td>
						<bean:write name='rs' property="pycc" />
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
					<td scope="row">
						<div align="center">
							家庭住址
						</div>
					</td>
					<td colspan="3">
						<bean:write name='rs' property="jtzz" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							父亲姓名
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fqxm" />
					</td>
					<td>
						<div align="center">
							父亲联系电话
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fqlxdh" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							父亲身份证号
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fqsfzh" />
					</td>
					<td scope="row">
						<div align="center">
							父亲工作单位
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fqgzdw" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							母亲姓名
						</div>
					</td>
					<td>
						<bean:write name='rs' property="mqxm" />
					</td>
					<td>
						<div align="center">
							母亲联系电话
						</div>
					</td>
					<td>
						<bean:write name='rs' property="mqlxdh" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							母亲身份证号
						</div>
					</td>
					<td>
						<bean:write name='rs' property="mqsfzh" />
					</td>
					<td scope="row">
						<div align="center">
							母亲工作单位
						</div>
					</td>
					<td>
						<bean:write name='rs' property="mqgzdw" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							家庭经济情况
						</div>
					</td>
					<td colspan="3">
						<bean:write name='rs' property="jtjjqk" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							学费贷款金额
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xfdkze" />
					</td>
					<td>
						<div align="center">
							生活费贷款金额
						</div>
					</td>
					<td>
						<bean:write name='rs' property="shfdkze" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							贷款总额
						</div>
					</td>
					<td>
						<bean:write name="rs" property="dkze" />
					</td>
					<td>
						<div align="center">
							申请时间
						</div>
					</td>
					<td>
						<bean:write name='rs' property="sqsj" />
					</td>
				</tr>
<%--				<tr>--%>
<%--					<td scope="row" colspan="4">--%>
<%--						<div align="center">--%>
<%--							上&nbsp;学&nbsp;年&nbsp;学&nbsp;习&nbsp;成&nbsp;绩--%>
<%--						</div>--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				<tr>--%>
<%--					<td scope="row">--%>
<%--						<div align="center">--%>
<%--							课程名称--%>
<%--						</div>--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<div align="center">--%>
<%--							成绩--%>
<%--						</div>--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<div align="center">--%>
<%--							课程名称--%>
<%--						</div>--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<div align="center">--%>
<%--							成绩--%>
<%--						</div>--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				<tr>--%>
<%--					<td scope="row">--%>
<%--						<bean:write name='rs' property="sxncj1_mc" />--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<bean:write name='rs' property="sxncj1_cj" />--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<bean:write name='rs' property="sxncj2_mc" />--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<bean:write name='rs' property="sxncj2_cj" />--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				<tr>--%>
<%--					<td scope="row">--%>
<%--						<bean:write name='rs' property="sxncj3_mc" />--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<bean:write name='rs' property="sxncj3_cj" />--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<bean:write name='rs' property="sxncj4_mc" />--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<bean:write name='rs' property="sxncj4_cj" />--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				<tr>--%>
<%--					<td scope="row">--%>
<%--						<bean:write name='rs' property="sxncj5_mc" />--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<bean:write name='rs' property="sxncj5_cj" />--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<bean:write name='rs' property="sxncj6_mc" />--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<bean:write name='rs' property="sxncj6_cj" />--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				<tr>--%>
<%--					<td scope="row">--%>
<%--						<bean:write name='rs' property="sxncj7_mc" />--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<bean:write name='rs' property="sxncj7_cj" />--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<bean:write name='rs' property="sxncj8_mc" />--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<bean:write name='rs' property="sxncj8_cj" />--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				<tr>--%>
<%--					<td scope="row">--%>
<%--						<bean:write name='rs' property="sxncj9_mc" />--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<bean:write name='rs' property="sxncj9_cj" />--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<bean:write name='rs' property="sxncj10_mc" />--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<bean:write name='rs' property="sxncj10_cj" />--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				<tr>--%>
<%--					<td scope="row">--%>
<%--						<div align="center">--%>
<%--							入学以来不及格科目--%>
<%--						</div>--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<bean:write name='rs' property="rxlbjgkm" />--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<div align="center">--%>
<%--							入学以来补考通过科目--%>
<%--						</div>--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<bean:write name='rs' property="rxlbktgkm" />--%>
<%--					</td>--%>
<%--				</tr>--%>
				<logic:equal name="user" value="xy">
					<tr>
						<td>
							<div align="center">
								辅导员审核
							</div>
						</td>
						<td>
							<bean:write name="rs" property="fdysh" />
						</td>
						<td>
							<div align="center">
								辅导员审核时间
							</div>
						</td>
						<td>
							<bean:write name="rs" property="fdyshsj" />
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="user" value="xx">
					<tr>
						<td>
							<div align="center">
								辅导员审核
							</div>
						</td>
						<td>
							<bean:write name="rs" property="fdysh" />
						</td>
						<td>
							<div align="center">
								辅导员审核时间
							</div>
						</td>
						<td>
							<bean:write name="rs" property="fdyshsj" />
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核
							</div>
						</td>
						<td>
							<bean:write name="rs" property="xysh" />
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核时间
							</div>
						</td>
						<td>
							<bean:write name="rs" property="xyshsj" />
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="htxx" value="no">
					<tr>
						<td>
							<div align="center">
								申请状态
							</div>
						</td>
						<td>
							<bean:write name="rs" property="sqzt" />
						</td>
						<td>
							<div align="center">
								放贷时间
							</div>
						</td>
						<td>
							<bean:write name='rs' property="dkfdsj" />
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								合同号
							</div>
						</td>
						<td>
							<bean:write name='rs' property="dkhth" />
						</td>
						<td>
							<div align="center">
								贷款到期时间
							</div>
						</td>
						<td>
							<bean:write name='rs' property="dkdqsj" />
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								经办银行
							</div>
						</td>
						<td colspan="3">
							<bean:write name='rs' property="jbyh" />
						</td>
					</tr>
				</logic:equal>
				<tr>
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
					<td>
						<div align="center">
							审核时间
						</div>
					</td>
					<td>
						<bean:write name='rs' property="shsj" />
					</td>
				</tr>
				<logic:equal name="htxx" value="is">
					<tr>
						<td>
							<div align="center">
								申请状态
							</div>
						</td>
						<td>
							<html:select name="rs" property="sqzt">
								<html:options collection="sqztList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td>
							<div align="center">
								放贷时间
							</div>
						</td>
						<td>
							<bean:write name='rs' property="dkfdsj" />
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								合同号
							</div>
						</td>
						<td>
							<input type="text" id="dkhth" name="dkhth"
								style="width:100%;heigh:100%" maxlength="50"
								value="<bean:write name='rs' property="dkhth" />">
						</td>
						<td>
							<div align="center">
								贷款到期时间
							</div>
						</td>
						<td>
							<input type="text" style="cursor:hand;width:80px"
								readonly="readonly"
								onclick="return showCalendar('dkdqsj','y-mm-dd');"
								value="<bean:write name='rs' property="dkdqsj" />" name="dkdqsj"
								id="dkdqsj" />
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								经办银行
							</div>
						</td>
						<td colspan="3">
							<input type="text" id="jbyh" name="jbyh"
								style="width:100%;heigh:100%" maxlength="200"
								value="<bean:write name='rs' property="jbyh" />">
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td>
						<div align="left">
							辅导员审核意见
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="fdyshyj" rows='3'
							style="width:100%" onblur="yzdx(this,'fdyshyj')" />
						<input type="hidden" id="fdyshyj" name="fdyshyj" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xyshyj" rows='3'
							style="width:100%" onblur="yzdx(this,'xyshyj')" />
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="left">
							学校审核意见
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xxshyj" rows='3'
							style="width:100%" onblur="yzdx(this,'xxshyj')" />
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
