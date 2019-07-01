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
			if((("通过" == xxsh)) && (isXX != "is")){
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
		
			refreshForm('/xgxt/new_common_xszz.do?method=gjzxdkshXxxx&actDo=save');
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
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
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 国家助学贷款审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" name="xxsh" value="<bean:write name='rs' property='xxsh' />" />
			<input type="hidden" name="isXX" value="<bean:write name="isXX" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td width="16%">
						<div align="center">
							年度
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="nd" />
					</td>
					<td width="16%" scope="col">
						<div align="center">
							申请时间
						</div>
					</td>
					<td width="34%" scope="col">
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							学号
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xh" />
					</td>
					<td scope="col">
						<div align="center">
							姓名
						</div>
					</td>
					<td scope="col">
						<bean:write name="rs" property="xm" />
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
							本/专/专接本
						</div>
					</td>
					<td align="center">
						<bean:write name='rs' property="xsxz" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							学制
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xz" />
					</td>
					<td colspan="2">
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
							出生日期
						</div>
					</td>
					<td>
						<bean:write name='rs' property="csrq" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							有效居住地址
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="yxjzdz"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
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
					<td scope="row">
						<div align="center">
							家庭经济情况
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtjjqk"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							应缴学费
						</div>
					</td>
					<td>
						<bean:write name="rs" property="yjxf"/>
					</td>
					<td>
						<div align="center">
							学费贷款
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xfdk"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							生活费贷款
						</div>
					</td>
					<td>
						<bean:write name="rs" property="shfdk"/>
					</td>
					<td>
						<div align="center">
							合计
						</div>
					</td>
					<td>
						<bean:write name="rs" property="hj"/>
					</td>
				</tr>
				<tr>
					<td scope="row" colspan="4">
						<div align="center">
						上学年成绩
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							课程名称
						</div>
					</td>
					<td>
						<div align="center">
							成绩
						</div>
					</td>
					<td scope="row">
						<div align="center">
							课程名称
						</div>
					</td>
					<td>
						<div align="center">
							成绩
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row" align="center">
						<bean:write name="rs" property="sxncj1_mc"/>
					</td>
					<td align="center">
						<bean:write name="rs" property="sxncj1_cj"/>
					</td>
					<td align="center">
						<bean:write name="rs" property="sxncj2_mc"/>
					</td>
					<td align="center">
						&nbsp;<bean:write name="rs" property="sxncj2_cj"/>&nbsp;
					</td>
				</tr>
				<tr>
					<td scope="row" align="center">
						<bean:write name="rs" property="sxncj3_mc"/>
					</td>
					<td align="center">
						<bean:write name="rs" property="sxncj3_cj"/>
					</td>
					<td align="center">
						<bean:write name="rs" property="sxncj4_mc"/>
					</td>
					<td align="center">
						&nbsp;<bean:write name="rs" property="sxncj4_cj"/>&nbsp;
					</td>
				</tr>
				<tr>
					<td scope="row" align="center">
						<bean:write name="rs" property="sxncj5_mc"/>
					</td>
					<td align="center">
						<bean:write name="rs" property="sxncj5_cj"/>
					</td>
					<td align="center">
						<bean:write name="rs" property="sxncj6_mc"/>
					</td>
					<td align="center">
						&nbsp;<bean:write name="rs" property="sxncj6_cj"/>&nbsp;
					</td>
				</tr>
				<tr>
					<td scope="row" align="center">
						<bean:write name="rs" property="sxncj7_mc"/>
					</td>
					<td align="center">
						<bean:write name="rs" property="sxncj7_cj"/>
					</td>
					<td align="center">
						<bean:write name="rs" property="sxncj8_mc"/>
					</td>
					<td align="center">
						&nbsp;<bean:write name="rs" property="sxncj8_cj"/>&nbsp;
					</td>
				</tr>
				<tr>
					<td scope="row" align="center">
						<bean:write name="rs" property="sxncj9_mc"/>
					</td>
					<td align="center">
						<bean:write name="rs" property="sxncj9_cj"/>
					</td>
					<td align="center">
						<bean:write name="rs" property="sxncj10_mc"/>
					</td>
					<td align="center">
						&nbsp;<bean:write name="rs" property="sxncj10_cj"/>&nbsp;
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							入学以来<br />不及格科目
						</div>
					</td>
					<td align="center">
						<bean:write name="rs" property="rxlbjgkm"/>
					</td>
					<td>
						<div align="center">
							入学以来<br />补考通过科目
						</div>
					</td>
					<td align="center">
						<bean:write name="rs" property="rxlbktgkm"/>
					</td>
				</tr>
				<logic:equal name="isXX" value="is">
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
						<bean:write name="rs" property="shsj" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
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
				<button class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
