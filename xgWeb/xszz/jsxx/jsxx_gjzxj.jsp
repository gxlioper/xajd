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
		function yz(titName){
			var xh = document.getElementById('xh').value;
			var sqly = document.getElementById('sqly').value;
			var bz = document.getElementById('bz').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("申请助学金理由、金额及家庭经济困难情况不能大于1000个字符");
	          		 return false;
	       		 }
	       	}
	       	if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 400){	         
	          		 alert("备注不能大于400个字符");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/jsxx_gjzxj.do?doType=add&titName=" + titName;
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(titName){//输出相应的打印页面
			document.forms[0].action = "/xgxt/jsxx_gjzxjb.do";
			document.forms[0].submit();
		}
		
		function xfhj(){
			var nyyjngzfy = document.getElementById('nyyjngzfy').value;
			var jtmntg = document.getElementById('jtmntg').value;
			if((nyyjngzfy == null) || (nyyjngzfy == "")){
				nyyjngzfy = "0";
			}
			if((jtmntg == null) || (jtmntg == "")){
				jtmntg = "0";
			}
			var hjmnsqfy = Math.round(nyyjngzfy) - Math.round(jtmntg);
			document.getElementById('hjmnsqfy').value=hjmnsqfy;
		}
		
		function getJe(){
			var temp = $('xssqdjdm').options[$('xssqdjdm').selectedIndex].innerHTML;
			msgArray = new Array();
			msgArray = temp.split('||');
			var xssqdjje = "";
			if (msgArray.length > 1) {
				xssqdjje = msgArray[1];
			}
			document.getElementById('xssqdjje').value=xssqdjje;
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 国家助学金申请
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间！
			</p>
		</center>
	</logic:equal>
		<html:form action="jsxx_gjzxj.do" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="url" name="url" value="/jsxx_gjzxj.do" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xytysqje" name="xytysqje"
				value="<bean:write name="rs" property="xytysqje" scope="request" />">
			<input type="hidden" id="xxtyshje" name="xxtyshje"
				value="<bean:write name="rs" property="xxtyshje" scope="request" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" scope="request" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" scope="request" />">
			<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj" scope="request" />">
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd" scope="request" />">

			<logic:present name="notSQ">
				<logic:match value="is" name="notSQ">
					<script language="javascript">
	         	alert("必须是困难生才能申请！");
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
						<td align="center" colspan="2" width="16%">
							<font color="red">*</font>学号
						</td>
						<td align="left" colspan="3" width="34%">
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
							<font color="red">*</font>学号
						</td>
						<td align="left" colspan="3">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="2">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>">
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
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="sfzh" name="sfzh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							民族
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="mzmc" readonly="readonly" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmmmc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xymc" readonly="readonly" name="xymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
					<td>
						<div align="center">
							专业
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zymc" name="zymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							班级
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="bjmc" readonly="readonly" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
					<td>
						<div align="center">
							班团职务
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="btzw" name="btzw" maxlength="60"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="btzw"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							学生电话
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="xsdh" readonly="readonly" name="xsdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xsdh"/>">
					</td>
					<td>
						<div align="center">
							家庭电话
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jtdh" name="jtdh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdh"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭地址
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="jtdz" readonly="readonly" name="jtdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdz"/>">
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
							<input type="text" id="jtcy1_xm" readonly="readonly"
								name="jtcy1_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_cw" readonly="readonly"
								name="jtcy1_cw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_cw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_nl" readonly="readonly"
								name="jtcy1_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_nl"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_jkzk" readonly="readonly"
								name="jtcy1_jkzk" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_jkzk"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy1_gzdwjzw" readonly="readonly"
								name="jtcy1_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy1_nsr" readonly="readonly"
								name="jtcy1_nsr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy1_nsr"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_xm" readonly="readonly"
								name="jtcy2_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_cw" readonly="readonly"
								name="jtcy2_cw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_cw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_nl" readonly="readonly"
								name="jtcy2_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_nl"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_jkzk" readonly="readonly"
								name="jtcy2_jkzk" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_jkzk"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy2_gzdwjzw" readonly="readonly"
								name="jtcy2_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy2_nsr" readonly="readonly"
								name="jtcy2_nsr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy2_nsr"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_xm" readonly="readonly"
								name="jtcy3_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_cw" readonly="readonly"
								name="jtcy3_cw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_cw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_nl" readonly="readonly"
								name="jtcy3_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_nl"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_jkzk" readonly="readonly"
								name="jtcy3_jkzk" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_jkzk"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy3_gzdwjzw" readonly="readonly"
								name="jtcy3_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy3_nsr" readonly="readonly"
								name="jtcy3_nsr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy3_nsr"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_xm" readonly="readonly"
								name="jtcy4_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_cw" readonly="readonly"
								name="jtcy4_cw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_cw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_nl" readonly="readonly"
								name="jtcy4_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_nl"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_jkzk" readonly="readonly"
								name="jtcy4_jkzk" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_jkzk"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy4_gzdwjzw" readonly="readonly"
								name="jtcy4_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy4_nsr" readonly="readonly"
								name="jtcy4_nsr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy4_nsr"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_xm" readonly="readonly"
								name="jtcy5_xm" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_xm"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_cw" readonly="readonly"
								name="jtcy5_cw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_cw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_nl" readonly="readonly"
								name="jtcy5_nl" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_nl"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_jkzk" readonly="readonly"
								name="jtcy5_jkzk" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_jkzk"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="text" id="jtcy5_gzdwjzw" readonly="readonly"
								name="jtcy5_gzdwjzw" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_gzdwjzw"/>">
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" id="jtcy5_nsr" readonly="readonly"
								name="jtcy5_nsr" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtcy5_nsr"/>">
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
						<bean:write name="rs" property="sfgr"/>
						<input type="hidden" id="sfgr" name="sfgr"
							value="<bean:write name="rs" property="sfgr" scope="request" />">
					</td>
					<td>
						<div align="center">
							烈士子女
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sflszn"/>
						<input type="hidden" id="sflszn" name="sflszn"
							value="<bean:write name="rs" property="sflszn" scope="request" />">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							无收入户
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfwsrh"/>
						<input type="hidden" id="sfwsrh" name="sfwsrh"
							value="<bean:write name="rs" property="sfwsrh" scope="request" />">
					</td>
					<td>
						<div align="center">
							重病户
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sfzbh"/>
						<input type="hidden" id="sfzbh" name="sfzbh"
							value="<bean:write name="rs" property="sfzbh" scope="request" />">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							低保户
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfdbh"/>
						<input type="hidden" id="sfdbh" name="sfdbh"
							value="<bean:write name="rs" property="sfdbh" scope="request" />">
					</td>
					<td>
						<div align="center">
							父母双下岗
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sffmsxg"/>
						<input type="hidden" id="sffmsxg" name="sffmsxg"
							value="<bean:write name="rs" property="sffmsxg" scope="request" />">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							纯农户
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfcnh"/>
						<input type="hidden" id="sfcnh" name="sfcnh"
							value="<bean:write name="rs" property="sfcnh" scope="request" />">
					</td>
					<td>
						<div align="center">
							低收入(家庭收入不足以支付就学基本费用)
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sfdsr"/>
						<input type="hidden" id="sfdsr" name="sfdsr"
							value="<bean:write name="rs" property="sfdsr" scope="request" />">
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
						<input type="hidden" id="jtjjknqk" name="jtjjknqk"
							value="<bean:write name="rs" property="jtjjknqk" scope="request" />">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							每年应缴纳各种费用
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="nyyjngzfy" name="nyyjngzfy" maxlength="10"
							style="width:100%;heigh:100%" onblur="xfhj();"
							value="<bean:write name="rs" property="nyyjngzfy"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							家庭每年提供
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="jtmntg" name="jtmntg" maxlength="10"
							style="width:100%;heigh:100%" onblur="xfhj();"
							value="<bean:write name="rs" property="jtmntg"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							合计每年尚缺费用
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="hjmnsqfy" name="hjmnsqfy"
							readonly="readonly" style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hjmnsqfy"/>">
					</td>
					<td>
						<div align="center">
							欠缴学费数
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="qjxfs" name="qjxfs" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qjxfs"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							已贷款种类及金额
						</div>
					</td>
					<td colspan="6">
						<input type="text" id="yhdkzljje" maxlength="200" name="yhdkzljje"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yhdkzljje"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							申请等级
						</div>
					</td>
					<td colspan="3">
						<html:select name="rs" property="xssqdjdm" styleId="xssqdjdm" onchange="getJe();">
							<html:option value="">------请选择------</html:option>
							<html:options collection="gjzxjList" property="djdm"
								labelProperty="djmc" />
						</html:select>
					</td>
					<td>
						<div align="center">
							申请金额
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="xssqdjje" readonly="readonly" name="xssqdjje"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs"  property="xssqdjje"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							申请助学金的理由
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="sqly" rows='6' style="width:100%" onblur="yzdx(this,'sqly')"/>
						<input type="hidden" id="sqly" name="sqly" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							备注
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="bz" rows='3' style="width:100%" onblur="yzdx(this,'bz')"/>
						<input type="hidden" id="bz" name="bz" value="">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2"
					onClick="yz(document.getElementById('titName').value);">
					提交申请
				</button>
				<button type="button" class="button2"
					onClick="toPrintOut(document.getElementById('titName').value);">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>
	</logic:equal>

		</html:form>
</body>
</html:html>
