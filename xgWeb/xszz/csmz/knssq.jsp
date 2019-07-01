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
<html:html>
<base target="_self" />
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var xzjtxxdz = document.getElementById('xzjtxxdz').value;
			var jtjjknqkjyy = document.getElementById('jtjjknqkjyy').value;
			var sqly = document.getElementById('sqly').value;
			if(xh == null){
				alert("学号不能为空!");
				return false;
			}
			if(xzjtxxdz != null){
	         	if(xzjtxxdz.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("现住家庭详细地址不能大于100个字符！");
	          		 return false;
	       		 }
	       	}
	       	if(jtjjknqkjyy != null){
	         	if(jtjjknqkjyy.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("家庭经济困难情况及原因不能大于1000个字符！");
	          		 return false;
	       		 }
	       	}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("申请理由不能大于1000个字符！");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/csmz_xszz.do?method=knssq&doType=save";
			document.forms[0].submit();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function toPrintOut1(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/csmz_xszz.do?method=jtjjqkdcb";
			document.forms[0].submit();
		}
		
		function toPrintOut2(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/csmz_xszz.do?method=pksdjb";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 贫困生申请
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内或人数已满！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="csmz_xszz.do?method=knssq" method="post">
			<input type="hidden" id="url" name="url"
				value="/csmz_xszz.do?method=knssq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd" />">
			<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" />">
			<input type="hidden" id="xyshsj" name="xyshsj"
				value="<bean:write name="rs" property="xyshsj" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" />">
			<input type="hidden" id="xxshsj" name="xxshsj"
				value="<bean:write name="rs" property="xxshsj" />">
			<input type="hidden" id="fdysh" name="fdysh"
				value="<bean:write name="rs" property="fdysh" />">
			<input type="hidden" id="fdyshsj" name="fdyshsj"
				value="<bean:write name="rs" property="fdyshsj" />">


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
			<logic:present name="have">
				<logic:match value="have" name="have">
					<script language="javascript">
	         			alert("已通过审核，不能申请！");
	         		</script>
				</logic:match>
			</logic:present>

			<table class="tbstyle" width="90%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>学号
						</td>
						<td align="left" width="34%">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>学号
						</td>
						<td align="left" width="34%">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td colspan="2" scope="col">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="2" scope="col">
						<input type="text" id="xm" name="xm" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xm" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<input type="text" id="xb" name="xb" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xb" />" readonly="true">
					</td>
					<td colspan="2">
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="sfzh" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="sfzh" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xymc" />" readonly="true">
					</td>
					<td colspan="2">
						<div align="center">
							专业
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zymc" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zymc" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							班级
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="bjmc" />" readonly="true">
					</td>
					<td colspan="2">
						<div align="center">
							年级
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="nj" name="nj" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="nj" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							民族
						</div>
					</td>
					<td>
						<input type="text" id="mzmc" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="mzmc" />" readonly="true">
					</td>
					<td colspan="2">
						<div align="center">
							政治面貌
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zzmmmc" name="zzmmmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zzmmmc" />"
							readonly="true">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							入学年月
						</div>
					</td>
					<td>
						<input type="text" id="rxny" name="rxny"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="rxny" />" readonly="true">
					</td>
					<td colspan="2">
						<div align="center">
							出生日期
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="csrq" name="csrq"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="csrq" />" readonly="true">
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
						<html:textarea name="rs" property="xzjtxxdz" rows='2'
							style="width:100%" onblur="yzdx(this,'xzjtxxdz')" />
						<input type="hidden" id="xzjtxxdz" name="xzjtxxdz" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							邮政编码
						</div>
					</td>
					<td>
						<input type="text" id="yzbm" name="yzbm"
							style="width:100%;heigh:100%" maxlength="6"
							value="<bean:write name='rs' property="yzbm" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<div align="center">
							家庭电话
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jtdh" name="jtdh"
							style="width:100%;heigh:100%" maxlength="15"
							value="<bean:write name='rs' property="jtdh" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							原学习学校
						</div>
					</td>
					<td>
						<input type="text" id="yxxxx" name="yxxxx"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="yxxxx" />">
					</td>
					<td colspan="2">
						<div align="center">
							籍贯
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jg" name="jg" style="width:100%;heigh:100%"
							maxlength="50" value="<bean:write name='rs' property="jg" />">
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
							<input type="text" id="zsjtcy1_xm" maxlength="40"
								name="zsjtcy1_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy1_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy1_gzdwjzw" maxlength="100"
								name="zsjtcy1_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy1_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy1_nl" maxlength="5"
								name="zsjtcy1_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy1_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy1_gx" maxlength="40"
								name="zsjtcy1_gx" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy1_gx"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy1_ysr" maxlength="10"
								name="zsjtcy1_ysr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy1_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy1_lxdh" maxlength="15"
								name="zsjtcy1_lxdh" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy1_lxdh"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy2_xm" maxlength="40"
								name="zsjtcy2_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy2_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy2_gzdwjzw" maxlength="100"
								name="zsjtcy2_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy2_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy2_nl" maxlength="5"
								name="zsjtcy2_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy2_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy2_gx" maxlength="40"
								name="zsjtcy2_gx" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy2_gx"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy2_ysr" maxlength="10"
								name="zsjtcy2_ysr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy2_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy2_lxdh" maxlength="15"
								name="zsjtcy2_lxdh" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy2_lxdh"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy3_xm" maxlength="40"
								name="zsjtcy3_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy3_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy3_gzdwjzw" maxlength="100"
								name="zsjtcy3_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy3_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy3_nl" maxlength="5"
								name="zsjtcy3_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy3_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy3_gx" maxlength="40"
								name="zsjtcy3_gx" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy3_gx"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy3_ysr" maxlength="10"
								name="zsjtcy3_ysr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy3_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy3_lxdh" maxlength="15"
								name="zsjtcy3_lxdh" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy3_lxdh"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy4_xm" maxlength="40"
								name="zsjtcy4_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy4_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy4_gzdwjzw" maxlength="100"
								name="zsjtcy4_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy4_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy4_nl" maxlength="5"
								name="zsjtcy4_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy4_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy4_gx" maxlength="40"
								name="zsjtcy4_gx" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy4_gx"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy4_ysr" maxlength="10"
								name="zsjtcy4_ysr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy4_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy4_lxdh" maxlength="15"
								name="zsjtcy4_lxdh" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy4_lxdh"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy5_xm" maxlength="40"
								name="zsjtcy5_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy5_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy5_gzdwjzw" maxlength="100"
								name="zsjtcy5_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy5_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy5_nl" maxlength="5"
								name="zsjtcy5_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy5_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy5_gx" maxlength="40"
								name="zsjtcy5_gx" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy5_gx"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy5_ysr" maxlength="10"
								name="zsjtcy5_ysr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy5_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zsjtcy5_lxdh" maxlength="15"
								name="zsjtcy5_lxdh" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zsjtcy5_lxdh"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
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
							<input type="text" id="jtjj_cz_qjnsr" maxlength="10"
								name="jtjj_cz_qjnsr" style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="jtjj_cz_qjnsr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元，人均月收入
							<input type="text" id="jtjj_cz_rjysr" maxlength="10"
								name="jtjj_cz_rjysr" style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="jtjj_cz_rjysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
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
							<input type="text" id="jtjj_nc_dnzsr" maxlength="10"
								name="jtjj_nc_dnzsr" style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="jtjj_nc_dnzsr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元，人均年收入
							<input type="text" id="jtjj_nc_rjnsr" maxlength="10"
								name="jtjj_nc_rjnsr" style="width:10%;heigh:100%"
								value="<bean:write name="rs" property="jtjj_nc_rjnsr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
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
						<input type="text" id="jtrks" name="jtrks"
							style="width:100%;heigh:100%" maxlength="3"
							value="<bean:write name='rs' property="jtrks" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<div align="center">
							当地最低社会生活保障
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="ddzdshshbz" name="ddzdshshbz"
							style="width:80%;heigh:100%" maxlength="10"
							value="<bean:write name='rs' property="ddzdshshbz" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
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
							<input type="text" id="zyshgx1_xm" maxlength="40"
								name="zyshgx1_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx1_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx1_gzdwjzw" maxlength="100"
								name="zyshgx1_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx1_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx1_nl" maxlength="5"
								name="zyshgx1_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx1_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx1_gx" maxlength="40"
								name="zyshgx1_gx" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx1_gx"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx1_ysr" maxlength="10"
								name="zyshgx1_ysr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx1_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx1_ynjtjjlxhgyqk" maxlength="50"
								name="zyshgx1_ynjtjjlxhgyqk" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx1_ynjtjjlxhgyqk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="zyshgx2_xm" maxlength="40"
								name="zyshgx2_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx2_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx2_gzdwjzw" maxlength="100"
								name="zyshgx2_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx2_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx2_nl" maxlength="5"
								name="zyshgx2_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx2_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx2_gx" maxlength="40"
								name="zyshgx2_gx" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx2_gx"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx2_ysr" maxlength="10"
								name="zyshgx2_ysr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx2_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx2_ynjtjjlxhgyqk" maxlength="50"
								name="zyshgx2_ynjtjjlxhgyqk" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx2_ynjtjjlxhgyqk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="zyshgx3_xm" maxlength="40"
								name="zyshgx3_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx3_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx3_gzdwjzw" maxlength="100"
								name="zyshgx3_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx3_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx3_nl" maxlength="5"
								name="zyshgx3_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx3_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx3_gx" maxlength="40"
								name="zyshgx3_gx" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx3_gx"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx3_ysr" maxlength="10"
								name="zyshgx3_ysr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx3_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx3_ynjtjjlxhgyqk" maxlength="50"
								name="zyshgx3_ynjtjjlxhgyqk" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx3_ynjtjjlxhgyqk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="zyshgx4_xm" maxlength="40"
								name="zyshgx4_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx4_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx4_gzdwjzw" maxlength="100"
								name="zyshgx4_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx4_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx4_nl" maxlength="5"
								name="zyshgx4_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx4_nl"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx4_gx" maxlength="40"
								name="zyshgx4_gx" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx4_gx"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx4_ysr" maxlength="10"
								name="zyshgx4_ysr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx4_ysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="zyshgx4_ynjtjjlxhgyqk" maxlength="50"
								name="zyshgx4_ynjtjjlxhgyqk" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zyshgx4_ynjtjjlxhgyqk"/>">
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
						<html:textarea name="rs" property="jtjjknqkjyy" rows='10'
							style="width:100%" onblur="yzdx(this,'jtjjknqkjyy')" />
						<input type="hidden" id="jtjjknqkjyy" name="jtjjknqkjyy" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							父亲单位
						</div>
					</td>
					<td>
						<input type="text" id="fqdw" name="fqdw"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="fqdw" />">
					</td>
					<td colspan="2">
						<div align="center">
							母亲单位
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="mqdw" name="mqdw"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="mqdw" />">
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
						<input type="text" id="szqs" name="szqs"
							style="width:100%;heigh:100%" maxlength="20"
							value="<bean:write name='rs' property="szqs" />">
					</td>
					<td colspan="2">
						<div align="center">
							寝室电话
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="qsdh" name="qsdh"
							style="width:100%;heigh:100%" maxlength="15"
							value="<bean:write name='rs' property="qsdh" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							特长
						</div>
					</td>
					<td>
						<input type="text" id="tc" name="tc" style="width:100%;heigh:100%"
							maxlength="100" value="<bean:write name='rs' property="tc" />">
					</td>
					<td colspan="2">
						<div align="center">
							就餐卡卡号
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jckkh" name="jckkh"
							style="width:100%;heigh:100%" maxlength="20"
							value="<bean:write name='rs' property="jckkh" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							何时参加过勤工助学
						</div>
					</td>
					<td colspan="5">
						<input type="text" id="hscjgqgzx" name="hscjgqgzx"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="hscjgqgzx" />">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							担任学生干部情况
						</div>
					</td>
					<td colspan="5">
						<input type="text" id="drxsgbqk" name="drxsgbqk"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="drxsgbqk" />">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							在校期间曾何时获过何种奖学金
						</div>
					</td>
					<td colspan="5">
						<input type="text" id="zxqjhschghzjxj" name="zxqjhschghzjxj"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="zxqjhschghzjxj" />">
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
						<div align="center">
							<logic:present name="rs" property="pkx">
								<logic:match value="否" name="rs" property="pkx">
									<input type="radio" name="pkx" value="否" checked>&nbsp;&nbsp;否
							    	<input type="radio" name="pkx" value="国家级贫困县">&nbsp;&nbsp;国家级贫困县
							    	<input type="radio" name="pkx" value="省级贫困县">&nbsp;&nbsp;省级贫困县
								</logic:match>
								<logic:match value="国家级贫困县" name="rs" property="pkx">
									<input type="radio" name="pkx" value="否">&nbsp;&nbsp;否
							    	<input type="radio" name="pkx" value="国家级贫困县" checked>&nbsp;&nbsp;国家级贫困县
							    	<input type="radio" name="pkx" value="省级贫困县">&nbsp;&nbsp;省级贫困县
								</logic:match>
								<logic:match value="省级贫困县" name="rs" property="pkx">
									<input type="radio" name="pkx" value="否">&nbsp;&nbsp;否
							    	<input type="radio" name="pkx" value="国家级贫困县">&nbsp;&nbsp;国家级贫困县
							    	<input type="radio" name="pkx" value="省级贫困县" checked>&nbsp;&nbsp;省级贫困县
								</logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="pkx">
								<input type="radio" name="pkx" value="否" checked>&nbsp;&nbsp;否
								<input type="radio" name="pkx" value="国家级贫困县">&nbsp;&nbsp;国家级贫困县
								<input type="radio" name="pkx" value="省级贫困县">&nbsp;&nbsp;省级贫困县
							</logic:notPresent>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							父亲职业
						</div>
					</td>
					<td>
						<input type="text" id="fqzy" name="fqzy"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="fqzy" />">
					</td>
					<td colspan="2">
						<div align="center">
							母亲职业
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="mqzy" name="mqzy"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="mqzy" />">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							上年家庭收入
						</div>
					</td>
					<td>
						<input type="text" id="snjtsr" name="snjtsr"
							style="width:100%;heigh:100%" maxlength="5"
							value="<bean:write name='rs' property="snjtsr" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<div align="center">
							家庭经济来源
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jtjjly" name="jtjjly"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="jtjjly" />">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							家庭每月提供生活费
						</div>
					</td>
					<td>
						<input type="text" id="jtmytgshf" name="jtmytgshf"
							style="width:100%;heigh:100%" maxlength="5"
							value="<bean:write name='rs' property="jtmytgshf" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<div align="center">
							家中是否有欠债
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<logic:present name="rs" property="jzsfyqz">
								<logic:match value="无" name="rs" property="jzsfyqz">
									<input type="radio" name="jzsfyqz" value="无" checked>&nbsp;&nbsp;无
							    	<input type="radio" name="jzsfyqz" value="有">&nbsp;&nbsp;有
								</logic:match>
								<logic:match value="有" name="rs" property="jzsfyqz">
									<input type="radio" name="jzsfyqz" value="无">&nbsp;&nbsp;无
							    	<input type="radio" name="jzsfyqz" value="有" checked>&nbsp;&nbsp;有
								</logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="jzsfyqz">
								<input type="radio" name="jzsfyqz" value="无" checked>&nbsp;&nbsp;无
								<input type="radio" name="jzsfyqz" value="有">&nbsp;&nbsp;有
							</logic:notPresent>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							父母是否有病或残疾
						</div>
					</td>
					<td>
						<input type="text" id="fmsfycj" name="fmsfycj"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="fmsfycj" />">
					</td>
					<td colspan="2">
						<div align="center">
							父母是否健在
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="fmsfjz" name="fmsfjz"
							style="width:100%;heigh:100%" maxlength="100"
							value="<bean:write name='rs' property="fmsfjz" />">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							申请理由
						</div>
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="sqly" rows='6'
							style="width:100%" onblur="yzdx(this,'sqly')" />
						<input type="hidden" id="sqly" name="sqly" value="">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2" onClick="yz();">
					提交申请
				</button>
				<button class="button2" onClick="toPrintOut1();">
					打印家庭经济情况调查表
				</button>
				<button class="button2" onClick="toPrintOut2();">
					打印贫困学生登记表
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
