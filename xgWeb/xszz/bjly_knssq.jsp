<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.Iterator" />
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
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		function yz(titName){
			var xh = document.getElementById('xh').value;
			var jtcy1_sfzh = document.getElementById('jtcy1_sfzh').value;
			var jtcy2_sfzh = document.getElementById('jtcy2_sfzh').value;
			var jtcy3_sfzh = document.getElementById('jtcy3_sfzh').value;
			var jtcy4_sfzh = document.getElementById('jtcy4_sfzh').value;
			var jtcy5_sfzh = document.getElementById('jtcy5_sfzh').value;
			var sqyy = document.getElementById('sqyy').value.trim();
			var bz = document.getElementById('bz').value.trim();
			if(xh == null){
				alert("学号不能为空!");
				return false;
			}
			if(jtcy1_sfzh != "" && !checkSfzh(jtcy1_sfzh)){
				return false;
			}
			if(jtcy2_sfzh != "" && !checkSfzh(jtcy2_sfzh)){
				return false;
			}
			if(jtcy3_sfzh != "" && !checkSfzh(jtcy3_sfzh)){
				return false;
			}
			if(jtcy4_sfzh != "" && !checkSfzh(jtcy4_sfzh)){
				return false;
			}
			if(jtcy5_sfzh != "" && !checkSfzh(jtcy5_sfzh)){
				return false;
			}
			if(sqyy != null){
	         	if((sqyy.replace(/[^\u0000-\u00ff]/g, "**").length > 1600) || (sqyy.replace(/[^\u0000-\u00ff]/g, "**").length < 600)){	         
	          		 alert("申请原因不能小于300字或大于800字！");
	          		 return false;
	       		 }
	       	}
	       	if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("备注不能大于50字！");
	          		 return false;
	       		 }
			}
			document.forms[0].action = "/xgxt/bjly_knssq.do?doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		
		function toPrintOut(titName){//输出相应的打印页面
			document.forms[0].action = "/xgxt/bjly_knssqb.do";
			document.forms[0].submit();
		}
		
		function checkSfzh(sfzh) {
   			sfzh=sfzh.toLowerCase()
			var OldID;
			if(sfzh.length == 15){
				OldID = sfzh;
				return true;
			}else if(sfzh.length == 18){
				OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
			}else{
				alert("请输入正确的身份证号码！","",function(){
					obj.select();
					obj.focus();
				});
				return false;
			}
			var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
			var A = new Array("1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2");
			var i, j, S;
			var NewID, ID, strNF;
			NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
			S = 0;
			for( i = 0; i <= 16; i++){
				j = NewID.substring(i, i+1) * W[i];
				S = S + j;
			}
			S = S % 11;
			if(sfzh != NewID + A[S]){
				alert("请输入正确的身份证号码！","",function(){
					obj.select();
					obj.focus();
				});
				return false;
			}
			return true;
		}
		
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 困难生申请
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="bjly_knssq.do" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="url" name="url" value="/bjly_knssq.do" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj"/>">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj"/>">
			<input type="hidden" id="con" name="con"
				value="<bean:write name="con"/>">


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
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
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
						<td align="left" colspan="3">
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
						<td align="left" colspan="3">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td width="14%" scope="col">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="3" scope="col">
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
					<td colspan="3">
						<input type="text" id="xb" name="xb" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xb" />" readonly="true">
					</td>
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="nj" name="nj" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="nj" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="sfzh" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="sfzh" />" readonly="true">
					</td>
					<td>
						<div align="center">
							年度
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="nd" name="nd" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="nd" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xymc" name="xymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xymc" />" readonly="true">
					</td>
					<td>
						<div align="center">
							专业
						</div>
					</td>
					<td colspan="3">
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
					<td colspan="3">
						<input type="text" id="bjmc" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="bjmc" />" readonly="true">
					</td>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="lxdh" name="lxdh"
							style="width:100%;heigh:100%" maxlength="15"
							value="<bean:write name='rs' property="lxdh" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6" scope="row">
						<div align="center">
							家
							<br>
							庭
							<br>
							成
							<br>
							员
							<br>
							信
							<br>
							息
						</div>
					</td>
					<td width="10%">
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
					<td width="16%">
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							工作单位
						</div>
					</td>
					<td width="10%">
						<div align="center">
							月收入
						</div>
					</td>
					<td width="10%">
						<div align="center">
							健康状况
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_xm" name="jtcy1_xm"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="jtcy1_xm" />">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_cw" name="jtcy1_cw"
								style="width:100%;heigh:100%" maxlength="20"
								value="<bean:write name='rs' property="jtcy1_cw" />">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_nl" name="jtcy1_nl"
								style="width:100%;heigh:100%" maxlength="3"
								value="<bean:write name='rs' property="jtcy1_nl" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_sfzh" name="jtcy1_sfzh"
								style="width:100%;heigh:100%" maxlength="18"
								value="<bean:write name='rs' property="jtcy1_sfzh" />">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy1_gzdw" name="jtcy1_gzdw"
								style="width:100%;heigh:100%" maxlength="50"
								value="<bean:write name='rs' property="jtcy1_gzdw" />">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_ysr" name="jtcy1_ysr"
								style="width:100%;heigh:100%" maxlength="5"
								value="<bean:write name='rs' property="jtcy1_ysr" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_jkzk" name="jtcy1_jkzk"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="jtcy1_jkzk" />">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_xm" name="jtcy2_xm"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="jtcy2_xm" />">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_cw" name="jtcy2_cw"
								style="width:100%;heigh:100%" maxlength="20"
								value="<bean:write name='rs' property="jtcy2_cw" />">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_nl" name="jtcy2_nl"
								style="width:100%;heigh:100%" maxlength="3"
								value="<bean:write name='rs' property="jtcy2_nl" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_sfzh" name="jtcy2_sfzh"
								style="width:100%;heigh:100%" maxlength="18"
								value="<bean:write name='rs' property="jtcy2_sfzh" />">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy2_gzdw" name="jtcy2_gzdw"
								style="width:100%;heigh:100%" maxlength="50"
								value="<bean:write name='rs' property="jtcy2_gzdw" />">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_ysr" name="jtcy2_ysr"
								style="width:100%;heigh:100%" maxlength="5"
								value="<bean:write name='rs' property="jtcy2_ysr" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_jkzk" name="jtcy2_jkzk"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="jtcy2_jkzk" />">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_xm" name="jtcy3_xm"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="jtcy3_xm" />">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_cw" name="jtcy3_cw"
								style="width:100%;heigh:100%" maxlength="20"
								value="<bean:write name='rs' property="jtcy3_cw" />">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_nl" name="jtcy3_nl"
								style="width:100%;heigh:100%" maxlength="3"
								value="<bean:write name='rs' property="jtcy3_nl" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_sfzh" name="jtcy3_sfzh"
								style="width:100%;heigh:100%" maxlength="18"
								value="<bean:write name='rs' property="jtcy3_sfzh" />">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy3_gzdw" name="jtcy3_gzdw"
								style="width:100%;heigh:100%" maxlength="50"
								value="<bean:write name='rs' property="jtcy3_gzdw" />">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_ysr" name="jtcy3_ysr"
								style="width:100%;heigh:100%" maxlength="5"
								value="<bean:write name='rs' property="jtcy3_ysr" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_jkzk" name="jtcy3_jkzk"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="jtcy3_jkzk" />">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_xm" name="jtcy4_xm"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="jtcy4_xm" />">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_cw" name="jtcy4_cw"
								style="width:100%;heigh:100%" maxlength="20"
								value="<bean:write name='rs' property="jtcy4_cw" />">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_nl" name="jtcy4_nl"
								style="width:100%;heigh:100%" maxlength="3"
								value="<bean:write name='rs' property="jtcy4_nl" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_sfzh" name="jtcy4_sfzh"
								style="width:100%;heigh:100%" maxlength="18"
								value="<bean:write name='rs' property="jtcy4_sfzh" />">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy4_gzdw" name="jtcy4_gzdw"
								style="width:100%;heigh:100%" maxlength="50"
								value="<bean:write name='rs' property="jtcy4_gzdw" />">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_ysr" name="jtcy4_ysr"
								style="width:100%;heigh:100%" maxlength="5"
								value="<bean:write name='rs' property="jtcy4_ysr" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_jkzk" name="jtcy4_jkzk"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="jtcy4_jkzk" />">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_xm" name="jtcy5_xm"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="jtcy5_xm" />">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_cw" name="jtcy5_cw"
								style="width:100%;heigh:100%" maxlength="20"
								value="<bean:write name='rs' property="jtcy5_cw" />">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_nl" name="jtcy5_nl"
								style="width:100%;heigh:100%" maxlength="3"
								value="<bean:write name='rs' property="jtcy5_nl" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_sfzh" name="jtcy5_sfzh"
								style="width:100%;heigh:100%" maxlength="18"
								value="<bean:write name='rs' property="jtcy5_sfzh" />">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy5_gzdw" name="jtcy5_gzdw"
								style="width:100%;heigh:100%" maxlength="50"
								value="<bean:write name='rs' property="jtcy5_gzdw" />">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_ysr" name="jtcy5_ysr"
								style="width:100%;heigh:100%" maxlength="5"
								value="<bean:write name='rs' property="jtcy5_ysr" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_jkzk" name="jtcy5_jkzk"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="jtcy5_jkzk" />">
						</div>
					</td>
				</tr>
				<logic:equal name="isNULL" value="is">
					<tr>
						<td rowspan="<bean:write name='con' />">
							<div align="center">
								收
								<br>
								入
								<br>
								来
								<br>
								源
								<br>
								情
								<br>
								况
							</div>
						</td>
						<td colspan="8">
							<div align="center">
								无收入来源项目!
							</div>
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="isNULL" value="no">
					<tr>
						<td rowspan="<bean:write name='con' />">
							<div align="center">
								收
								<br>
								入
								<br>
								来
								<br>
								源
								<br>
								情
								<br>
								况
							</div>
						</td>
						<td colspan="4">
							<div align="center">
								项目
							</div>
						</td>
						<td colspan="4">
							<div align="center">
								收入(元)
							</div>
						</td>
					</tr>
					<%
						ArrayList srlyList = (ArrayList) request
							.getAttribute("srlyList");
						String srlyName = "";

						for (Iterator it = srlyList.iterator(); it.hasNext();) {
							String[] temp = (String[]) it.next();
							srlyName = "srly" + temp[0];
					%>
					<tr>
						<td colspan="4">
							<div align="center">
								<%=temp[1]%>
							</div>
						</td>
						<td colspan="4">
							<div align="center">
								<input type="text" id="<%=srlyName%>" name="<%=srlyName%>"
									style="width:100%;heigh:100%" maxlength="5"
									value="<bean:write name='rs' property="<%=srlyName%>" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
						</td>
					</tr>
					<%
						}
					%>
				</logic:equal>
				<tr>
					<td colspan="2">
						<div align="center">
							申请原因(字数:300~800)
						</div>
					</td>
					<td colspan="7">
						<html:textarea name='rs' property='sqyy' style="width:99%"
							rows='10' />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							备注
						</div>
					</td>
					<td colspan="7">
						<html:textarea name='rs' property='bz' style="width:99%" rows='3' />
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2"
					onClick="yz(document.getElementById('titName').value);">
					提交申请
				</button>
				<button class="button2"
					onClick="toPrintOut(document.getElementById('titName').value);">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
