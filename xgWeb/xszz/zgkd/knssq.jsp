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


	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			document.forms[0].action = "/xgxt/zgkydx_xszz.do?method=knssqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/zgkydx_xszz.do?method=knssqb";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 困难生
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="zgkydx_xszz.do?method=knssq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zgkydx_xszz.do?method=knssq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id="pyrs" name="pyrs"
				value="<bean:write name="rs" property="pyrs" />">
			<input type="hidden" id="adrs" name="adrs"
				value="<bean:write name="rs" property="adrs" />">
			<input type="hidden" id="bdrs" name="bdrs"
				value="<bean:write name="rs" property="bdrs" />">
			<input type="hidden" id="cdrs" name="cdrs"
				value="<bean:write name="rs" property="cdrs" />">
			<input type="hidden" id="btyrs" name="btyrs"
				value="<bean:write name="rs" property="btyrs" />">
			<input type="hidden" id="csbyjdrs" name="csbyjdrs"
				value="<bean:write name="rs" property="csbyjdrs" />">
			<input type="hidden" id="byjdjtqk" name="byjdjtqk"
				value="<bean:write name="rs" property="byjdjtqk" />">
			<input type="hidden" id="dcfyqk" name="dcfyqk"
				value="<bean:write name="rs" property="dcfyqk" />">
			<input type="hidden" id="bjpyjg" name="bjpyjg"
				value="<bean:write name="rs" property="bjpyjg" />">
			<input type="hidden" id="xbshjg" name="xbshjg"
				value="<bean:write name="rs" property="xbshjg" />">
			<input type="hidden" id="xxshjg" name="xxshjg"
				value="<bean:write name="rs" property="xxshjg" />">

			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("已通过审核，不能申请！");
	         		</script>
				</logic:match>
			</logic:present>
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
			<table class="tbstyle" width="90%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>学号：
						</td>
						<td align="left" colspan="3">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="readonly"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" colspan="2">
							<font color="red">*</font>学号：
						</td>
						<td align="left" colspan="3">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td colspan="2">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="30%">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xb" readonly="readonly" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<td colspan="2">
						<div align="center">
							学制
						</div>
					</td>
					<td>
						<input type="text" id="xz" name="xz" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xz"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							年级
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="nj" readonly="readonly" name="nj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
					<td colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />名称
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							专业名称
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<td colspan="2">
						<div align="center">
							班级名称
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							年度
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="nd" readonly="readonly" name="nd"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nd"/>">
					</td>
					<td colspan="2">
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" readonly="readonly" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							宿舍
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="ss" name="ss" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="ss"/>">
					</td>
					<td colspan="2">
						<div align="center">
							电话
						</div>
					</td>
					<td>
						<input type="text" id="dh" name="dh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭所在地
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jtszd" name="jtszd" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtszd"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							父亲单位电话
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="fqdwlxdh" name="fqdwlxdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="fqdwlxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td colspan="2">
						<div align="center">
							母亲单位电话
						</div>
					</td>
					<td>
						<input type="text" id="mqdwlxdh" name="mqdwlxdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mqdwlxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭电话及邮编
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtlxdhjyzbm" name="jtlxdhjyzbm"
							maxlength="40" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtlxdhjyzbm"/>">
					</td>
					<td colspan="2">
						<div align="center">
							所在基层组织电话
						</div>
					</td>
					<td>
						<input type="text" id="szjclxdh" name="szjclxdh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="szjclxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td width="6%" rowspan="8">
						<div align="center">
							家
							<br />
							庭
							<br />
							成
							<br />
							员
						</div>
					</td>
					<td width="14%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="10%">
						<div align="center">
							性别
						</div>
					</td>
					<td width="10%">
						<div align="center">
							与本人
							<br />
							关系
						</div>
					</td>
					<td width="8%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="12%">
						<div align="center">
							户口
							<br />
							类型
						</div>
					</td>
					<td width="10%">
						<div align="center">
							从业
							<br />
							状态
						</div>
					</td>
					<td>
						<div align="center">
							工作单位(就读学校)
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy1_xm" name="jtcy1_xm" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy1_xb" name="jtcy1_xb" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_xb"/>">
					</td>
					<td>
						<input type="text" id="jtcy1_gx" name="jtcy1_gx" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_gx"/>">
					</td>
					<td>
						<input type="text" id="jtcy1_nl" name="jtcy1_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td align="center">
						<logic:present name="rs" property="jtcy1_hklx">
							<logic:match value="A" name="rs" property="jtcy1_hklx">
								<input type="radio" name="jtcy1_hklx" value="A" checked>&nbsp;A
							<input type="radio" name="jtcy1_hklx" value="B">&nbsp;B
						</logic:match>
							<logic:match value="B" name="rs" property="jtcy1_hklx">
								<input type="radio" name="jtcy1_hklx" value="A">&nbsp;A
							<input type="radio" name="jtcy1_hklx" value="B" checked>&nbsp;B
						</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="jtcy1_hklx">
							<input type="radio" name="jtcy1_hklx" value="A">&nbsp;A
						<input type="radio" name="jtcy1_hklx" value="B" checked>&nbsp;B
					</logic:notPresent>
					</td>
					<td>
						<input type="text" id="jtcy1_cyzt" name="jtcy1_cyzt"
							maxlength="20" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_cyzt"/>">
					</td>
					<td>
						<input type="text" id="jtcy1_gzdw" name="jtcy1_gzdw"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_gzdw"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy2_xm" name="jtcy2_xm" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy2_xb" name="jtcy2_xb" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_xb"/>">
					</td>
					<td>
						<input type="text" id="jtcy2_gx" name="jtcy2_gx" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_gx"/>">
					</td>
					<td>
						<input type="text" id="jtcy2_nl" name="jtcy2_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td align="center">
						<logic:present name="rs" property="jtcy2_hklx">
							<logic:match value="A" name="rs" property="jtcy2_hklx">
								<input type="radio" name="jtcy2_hklx" value="A" checked>&nbsp;A
							<input type="radio" name="jtcy2_hklx" value="B">&nbsp;B
						</logic:match>
							<logic:match value="B" name="rs" property="jtcy2_hklx">
								<input type="radio" name="jtcy2_hklx" value="A">&nbsp;A
							<input type="radio" name="jtcy2_hklx" value="B" checked>&nbsp;B
						</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="jtcy2_hklx">
							<input type="radio" name="jtcy2_hklx" value="A">&nbsp;A
						<input type="radio" name="jtcy2_hklx" value="B" checked>&nbsp;B
					</logic:notPresent>
					</td>
					<td>
						<input type="text" id="jtcy2_cyzt" name="jtcy2_cyzt"
							maxlength="20" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_cyzt"/>">
					</td>
					<td>
						<input type="text" id="jtcy2_gzdw" name="jtcy2_gzdw"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_gzdw"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy3_xm" name="jtcy3_xm" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy3_xb" name="jtcy3_xb" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_xb"/>">
					</td>
					<td>
						<input type="text" id="jtcy3_gx" name="jtcy3_gx" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_gx"/>">
					</td>
					<td>
						<input type="text" id="jtcy3_nl" name="jtcy3_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td align="center">
						<logic:present name="rs" property="jtcy3_hklx">
							<logic:match value="A" name="rs" property="jtcy3_hklx">
								<input type="radio" name="jtcy3_hklx" value="A" checked>&nbsp;A
							<input type="radio" name="jtcy3_hklx" value="B">&nbsp;B
						</logic:match>
							<logic:match value="B" name="rs" property="jtcy3_hklx">
								<input type="radio" name="jtcy3_hklx" value="A">&nbsp;A
							<input type="radio" name="jtcy3_hklx" value="B" checked>&nbsp;B
						</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="jtcy3_hklx">
							<input type="radio" name="jtcy3_hklx" value="A">&nbsp;A
						<input type="radio" name="jtcy3_hklx" value="B" checked>&nbsp;B
					</logic:notPresent>
					</td>
					<td>
						<input type="text" id="jtcy3_cyzt" name="jtcy3_cyzt"
							maxlength="20" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_cyzt"/>">
					</td>
					<td>
						<input type="text" id="jtcy3_gzdw" name="jtcy3_gzdw"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_gzdw"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy4_xm" name="jtcy4_xm" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy4_xb" name="jtcy4_xb" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_xb"/>">
					</td>
					<td>
						<input type="text" id="jtcy4_gx" name="jtcy4_gx" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_gx"/>">
					</td>
					<td>
						<input type="text" id="jtcy4_nl" name="jtcy4_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td align="center">
						<logic:present name="rs" property="jtcy4_hklx">
							<logic:match value="A" name="rs" property="jtcy4_hklx">
								<input type="radio" name="jtcy4_hklx" value="A" checked>&nbsp;A
							<input type="radio" name="jtcy4_hklx" value="B">&nbsp;B
						</logic:match>
							<logic:match value="B" name="rs" property="jtcy4_hklx">
								<input type="radio" name="jtcy4_hklx" value="A">&nbsp;A
							<input type="radio" name="jtcy4_hklx" value="B" checked>&nbsp;B
						</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="jtcy4_hklx">
							<input type="radio" name="jtcy4_hklx" value="A">&nbsp;A
						<input type="radio" name="jtcy4_hklx" value="B" checked>&nbsp;B
					</logic:notPresent>
					</td>
					<td>
						<input type="text" id="jtcy4_cyzt" name="jtcy4_cyzt"
							maxlength="20" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_cyzt"/>">
					</td>
					<td>
						<input type="text" id="jtcy4_gzdw" name="jtcy4_gzdw"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_gzdw"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy5_xm" name="jtcy5_xm" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy5_xb" name="jtcy5_xb" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_xb"/>">
					</td>
					<td>
						<input type="text" id="jtcy5_gx" name="jtcy5_gx" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_gx"/>">
					</td>
					<td>
						<input type="text" id="jtcy5_nl" name="jtcy5_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td align="center">
						<logic:present name="rs" property="jtcy5_hklx">
							<logic:match value="A" name="rs" property="jtcy5_hklx">
								<input type="radio" name="jtcy5_hklx" value="A" checked>&nbsp;A
							<input type="radio" name="jtcy5_hklx" value="B">&nbsp;B
						</logic:match>
							<logic:match value="B" name="rs" property="jtcy5_hklx">
								<input type="radio" name="jtcy5_hklx" value="A">&nbsp;A
							<input type="radio" name="jtcy5_hklx" value="B" checked>&nbsp;B
						</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="jtcy5_hklx">
							<input type="radio" name="jtcy5_hklx" value="A">&nbsp;A
						<input type="radio" name="jtcy5_hklx" value="B" checked>&nbsp;B
					</logic:notPresent>
					</td>
					<td>
						<input type="text" id="jtcy5_cyzt" name="jtcy5_cyzt"
							maxlength="20" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_cyzt"/>">
					</td>
					<td>
						<input type="text" id="jtcy5_gzdw" name="jtcy5_gzdw"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_gzdw"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy6_xm" name="jtcy6_xm" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy6_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy6_xb" name="jtcy6_xb" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy6_xb"/>">
					</td>
					<td>
						<input type="text" id="jtcy6_gx" name="jtcy6_gx" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy6_gx"/>">
					</td>
					<td>
						<input type="text" id="jtcy6_nl" name="jtcy6_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy6_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td align="center">
						<logic:present name="rs" property="jtcy6_hklx">
							<logic:match value="A" name="rs" property="jtcy6_hklx">
								<input type="radio" name="jtcy6_hklx" value="A" checked>&nbsp;A
							<input type="radio" name="jtcy6_hklx" value="B">&nbsp;B
						</logic:match>
							<logic:match value="B" name="rs" property="jtcy6_hklx">
								<input type="radio" name="jtcy6_hklx" value="A">&nbsp;A
							<input type="radio" name="jtcy6_hklx" value="B" checked>&nbsp;B
						</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="jtcy6_hklx">
							<input type="radio" name="jtcy6_hklx" value="A">&nbsp;A
						<input type="radio" name="jtcy6_hklx" value="B" checked>&nbsp;B
					</logic:notPresent>
					</td>
					<td>
						<input type="text" id="jtcy6_cyzt" name="jtcy6_cyzt"
							maxlength="20" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy6_cyzt"/>">
					</td>
					<td>
						<input type="text" id="jtcy6_gzdw" name="jtcy6_gzdw"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy6_gzdw"/>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" id="jtcy7_xm" name="jtcy7_xm" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy7_xm"/>">
					</td>
					<td>
						<input type="text" id="jtcy7_xb" name="jtcy7_xb" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy7_xb"/>">
					</td>
					<td>
						<input type="text" id="jtcy7_gx" name="jtcy7_gx" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy7_gx"/>">
					</td>
					<td>
						<input type="text" id="jtcy7_nl" name="jtcy7_nl" maxlength="3"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy7_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td align="center">
						<logic:present name="rs" property="jtcy7_hklx">
							<logic:match value="A" name="rs" property="jtcy7_hklx">
								<input type="radio" name="jtcy7_hklx" value="A" checked>&nbsp;A
							<input type="radio" name="jtcy7_hklx" value="B">&nbsp;B
						</logic:match>
							<logic:match value="B" name="rs" property="jtcy7_hklx">
								<input type="radio" name="jtcy7_hklx" value="A">&nbsp;A
							<input type="radio" name="jtcy7_hklx" value="B" checked>&nbsp;B
						</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="jtcy7_hklx">
							<input type="radio" name="jtcy7_hklx" value="A">&nbsp;A
						<input type="radio" name="jtcy7_hklx" value="B" checked>&nbsp;B
					</logic:notPresent>
					</td>
					<td>
						<input type="text" id="jtcy7_cyzt" name="jtcy7_cyzt"
							maxlength="20" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy7_cyzt"/>">
					</td>
					<td>
						<input type="text" id="jtcy7_gzdw" name="jtcy7_gzdw"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy7_gzdw"/>">
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<font color="red"> 注:1.家庭成员指除学生本人之外共同生活的直属血亲及兄弟姐妹； <br />
							&nbsp;&nbsp;&nbsp;2.户口类型分：A&nbsp;(农业户口)&nbsp;&nbsp;&nbsp;B&nbsp;(非农业户口)；
							<br />
							&nbsp;&nbsp;&nbsp;3.从业状态一栏仅填写工作、务农、下岗、打工、退休、上学(大学、中专、高中、初中、小学)等。
						</font>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						申请贫困生具体理由
						<font color="red">(请在符合的情况后面简要说明)</font>
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							来自国家级贫困县的农村地区
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sqly_lzgjjpkxdncdq"
							name="sqly_lzgjjpkxdncdq" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqly_lzgjjpkxdncdq"/>">
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							属于享受最低生活保障的城镇家庭
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sqly_syxszdshbzdczjt"
							name="sqly_syxszdshbzdczjt" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqly_syxszdshbzdczjt"/>">
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							孤儿或经济困难的单亲家庭
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sqly_gehjjknddqjt" name="sqly_gehjjknddqjt"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqly_gehjjknddqjt"/>">
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							父母一方或双方下岗(失业)
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sqly_fmyfhsfxg" name="sqly_fmyfhsfxg"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqly_fmyfhsfxg"/>">
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							家庭成员中无18-55岁的青壮劳动力
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sqly_jtcywqzndl" name="sqly_jtcywqzndl"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqly_jtcywqzndl"/>">
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							家庭成员因残疾或疾病而丧失劳动能力
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sqly_jtcyycjhjbrssndl"
							name="sqly_jtcyycjhjbrssndl" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqly_jtcyycjhjbrssndl"/>">
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							家庭成员因患重大疾病需支付大额医疗费用
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sqly_jtcyyzdjbxzfdefy"
							name="sqly_jtcyyzdjbxzfdefy" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqly_jtcyyzdjbxzfdefy"/>">
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							家庭中有两个或以上成员正接受非义务教育
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sqly_jtcyzylghyscyzjsfywjy"
							name="sqly_jtcyzylghyscyzjsfywjy" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqly_jtcyzylghyscyzjsfywjy"/>">
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							家庭及成员因遭遇自然灾害或其他突发灾变
							<br />
							造成人身及财产的重大损失
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sqly_jtcyzszrzh" name="sqly_jtcyzszrzh"
							maxlength="200" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqly_jtcyzszrzh"/>">
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							其他导致家庭经济困难情况
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sqly_qtqk" name="sqly_qtqk" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqly_qtqk"/>">
					</td>
				</tr>
				<tr>
					<td rowspan="8">
						<div align="center">
							家庭
							<br />
							经济
							<br />
							情况
							<br />
							证明
							<br />
							材料
							<br />
							清单
						</div>
					</td>
					<td rowspan="2">
						<div align="center">
							材料一
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							材料名称
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="zmcl1_mc" name="zmcl1_mc" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zmcl1_mc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							出具材料机关及联系电话
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zmcl1_jg" name="zmcl1_jg" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zmcl1_jg"/>">
					</td>
					<td>
						<input type="text" id="zmcl1_dh" name="zmcl1_dh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zmcl1_dh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							材料二
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							材料名称
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="zmcl2_mc" name="zmcl2_mc" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zmcl2_mc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							出具材料机关及联系电话
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zmcl2_jg" name="zmcl2_jg" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zmcl2_jg"/>">
					</td>
					<td>
						<input type="text" id="zmcl2_dh" name="zmcl2_dh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zmcl2_dh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							材料三
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							材料名称
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="zmcl3_mc" name="zmcl3_mc" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zmcl3_mc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							出具材料机关及联系电话
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zmcl3_jg" name="zmcl3_jg" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zmcl3_jg"/>">
					</td>
					<td>
						<input type="text" id="zmcl3_dh" name="zmcl3_dh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zmcl3_dh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							材料四
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							材料名称
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="zmcl4_mc" name="zmcl4_mc" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zmcl4_mc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							出具材料机关及联系电话
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zmcl4_jg" name="zmcl4_jg" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zmcl4_jg"/>">
					</td>
					<td>
						<input type="text" id="zmcl4_dh" name="zmcl4_dh" maxlength="15"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zmcl4_dh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2" onClick="yz();">
					提交申请
				</button>
				<button type="button" class="button2" onClick="toPrintOut();">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>
	</logic:equal>
		</html:form>
</body>
</html:html>
