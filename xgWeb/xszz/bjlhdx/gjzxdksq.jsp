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
			var jtjjqk = document.getElementById('jtjjqk').value;
			var szdkze = document.getElementById('szdkze').value;
			var dkze = document.getElementById('dkze').value;
			
			if(xh == null || xh == ""){
				alert("学号不能为空!");
				return false;
			}
	       	if(jtjjqk != null){
	         	if(jtjjqk.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("家庭经济情况及原因不能大于1000个字符！");
	          		 return false;
	       		 }
	       	}
	       	if(Math.round(dkze) > Math.round(szdkze)){
				alert("贷款总金额不能大于" + szdkze + "元！");
				return false;
			}
			document.forms[0].action = "/xgxt/bjlhdx_xszz.do?method=gjzxdksq&doType=save";
			document.forms[0].submit();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function je(){
			var xfdkze = document.getElementById('xfdkze').value;
			var shfdkze = document.getElementById('shfdkze').value;
			var szdkze = document.getElementById('szdkze').value;
			var dkze = '0';
			
			if(xfdkze == null || xfdkze == ""){
				document.getElementById('xfdkze').value = '0';
				xfdkze = 0;
			}
			if(shfdkze == null || shfdkze == ""){
				document.getElementById('shfdkze').value = '0';
				shfdkze = 0;
			}
			if(szdkze == null || szdkze == ""){
				document.getElementById('szdkze').value = '0';
				szdkze = 0;
			}
			dkze = Math.round(xfdkze) + Math.round(shfdkze);
			document.getElementById('dkze').value = dkze;
			if(Math.round(dkze) > Math.round(szdkze)){
				alert("贷款总金额不能大于" + szdkze + "元！");
				shfdkze = Math.round(shfdkze) - (Math.round(dkze) - Math.round(szdkze));
				document.getElementById('shfdkze').value = shfdkze;
				dkze = Math.round(xfdkze) + Math.round(shfdkze);
				document.getElementById('dkze').value = dkze;
			}
		}
		
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/bjlhdx_xszz.do?method=gjzxdksqb";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 国家助学贷款申请
		</div>
	</div>

	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内,不能申请！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="bjlhdx_xszz.do?method=gjzxdksq" method="post">
			<input type="hidden" id="url" name="url"
				value="/bjlhdx_xszz.do?method=gjzxdksq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd" />">
			<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" />">
			<input type="hidden" id="xyshsj" name="xyshsj"
				value="<bean:write name="rs" property="xyshsj" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" />">
			<input type="hidden" id="xxshsj" name="xxshsj"
				value="<bean:write name="rs" property="xxshsj" />">
			<input type="hidden" id="fdyshyj" name="fdyshyj"
				value="<bean:write name="rs" property="fdyshyj" />">
			<input type="hidden" id="fdyshsj" name="fdyshsj"
				value="<bean:write name="rs" property="fdyshsj" />">
			<input type="hidden" id="szdkze" name="szdkze"
				value="<bean:write name='rs' property="szdkze" />">
			<input type="hidden" id="szxfje" name="szxfje"
				value="<bean:write name='rs' property="szxfje" />">
			<input type="hidden" id="isQuery" name="isQuery"
				value="<bean:write name="isQuery" scope="request"/>" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="pkVal" scope="request"/>" />

			<logic:equal name="sfksq" value="-2">
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
	         			alert("已通过审核，只能修改学生家庭信息！");
	         		</script>
						<logic:present name="tBol">
							<logic:match value="true" name="tBol">
								<script language="javascript">
	         					alert("保存成功！");
	         				</script>
							</logic:match>
							<logic:match value="false" name="tBol">
								<script language="javascript">
	         					alert("保存失败！");
	         				</script>
							</logic:match>
						</logic:present>
					</logic:match>
				</logic:present>
				<logic:present name="notKNS">
					<logic:match value="notKNS" name="notKNS">
						<script language="javascript">
	         	alert("必需是困难生才能申请！");
	         	</script>
					</logic:match>
				</logic:present>

				<table class="tbstyle" width="90%">
					<tr>
						<td colspan="4" align="left">
							&nbsp;&nbsp;<font color="red">现在不是申请时间，只能修改学生家庭信息!</font>
						</td>
					</tr>
					<tr>
						<td align="center" width="16%">
							学号
						</td>
						<td align="left" width="34%">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
						<td width="16%" scope="col">
							<div align="center">
								姓名
							</div>
						</td>
						<td width="34%" scope="col">
							<input type="text" id="xm" name="xm"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xm" />" readonly="true">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								性别
							</div>
						</td>
						<td>
							<input type="text" id="xb" name="xb"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xb" />" readonly="true">
						</td>
						<td>
							<div align="center">
								身份证号
							</div>
						</td>
						<td>
							<input type="text" id="sfzh" name="sfzh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="sfzh" />" readonly="true">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />
							</div>
						</td>
						<td>
							<input type="text" id="xymc" name="xymc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xymc" />" readonly="true">
						</td>
						<td>
							<div align="center">
								专业
							</div>
						</td>
						<td>
							<input type="text" id="zymc" name="zymc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="zymc" />" readonly="true">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								班级
							</div>
						</td>
						<td>
							<input type="text" id="bjmc" name="bjmc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="bjmc" />" readonly="true">
						</td>
						<td>
							<div align="center">
								学制
							</div>
						</td>
						<td>
							<input type="text" id="xz" name="xz"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xz" />" readonly="true">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								入学年月
							</div>
						</td>
						<td>
							<input type="text" id="rxny" name="rxny"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="rxny" />" readonly="true">
						</td>
						<td>
							<div align="center">
								毕业年月
							</div>
						</td>
						<td>
							<input type="text" id="byny" name="byny"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="byny" />" readonly="true">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								移动电话
							</div>
						</td>
						<td>
							<input type="text" id="yddh" name="yddh"
								style="width:100%;heigh:100%" maxlength="15"
								value="<bean:write name='rs' property="yddh" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td>
							<div align="center">
								固定电话
							</div>
						</td>
						<td>
							<input type="text" id="gddh" name="gddh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="gddh" />" maxlength="15"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								培养层次
							</div>
						</td>
						<td>
							<input type="text" id="pycc" name="pycc"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="pycc" />">
						</td>
						<td>
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
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								家庭住址
							</div>
						</td>
						<td colspan="3">
							<input type="text" id="jtzz" name="jtzz"
								style="width:100%;heigh:100%" maxlength="200"
								value="<bean:write name='rs' property="jtzz" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								父亲姓名
							</div>
						</td>
						<td>
							<input type="text" id="fqxm" name="fqxm"
								style="width:100%;heigh:100%" maxlength="20"
								value="<bean:write name='rs' property="fqxm" />">
						</td>
						<td>
							<div align="center">
								父亲联系电话
							</div>
						</td>
						<td>
							<input type="text" id="fqlxdh" name="fqlxdh"
								style="width:100%;heigh:100%" maxlength="15"
								value="<bean:write name='rs' property="fqlxdh" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								父亲身份证号
							</div>
						</td>
						<td>
							<input type="text" id="fqsfzh" name="fqsfzh"
								style="width:100%;heigh:100%" maxlength="18"
								value="<bean:write name='rs' property="fqsfzh" />">
						</td>
						<td scope="row">
							<div align="center">
								父亲工作单位
							</div>
						</td>
						<td>
							<input type="text" id="fqgzdw" name="fqgzdw"
								style="width:100%;heigh:100%" maxlength="200"
								value="<bean:write name='rs' property="fqgzdw" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								母亲姓名
							</div>
						</td>
						<td>
							<input type="text" id="mqxm" name="mqxm"
								style="width:100%;heigh:100%" maxlength="20"
								value="<bean:write name='rs' property="mqxm" />">
						</td>
						<td>
							<div align="center">
								母亲联系电话
							</div>
						</td>
						<td>
							<input type="text" id="mqlxdh" name="mqlxdh"
								style="width:100%;heigh:100%" maxlength="15"
								value="<bean:write name='rs' property="mqlxdh" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								母亲身份证号
							</div>
						</td>
						<td>
							<input type="text" id="mqsfzh" name="mqsfzh"
								style="width:100%;heigh:100%" maxlength="18"
								value="<bean:write name='rs' property="mqsfzh" />">
						</td>
						<td scope="row">
							<div align="center">
								母亲工作单位
							</div>
						</td>
						<td>
							<input type="text" id="mqgzdw" name="mqgzdw"
								style="width:100%;heigh:100%" maxlength="200"
								value="<bean:write name='rs' property="mqgzdw" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								家庭经济情况
							</div>
						</td>
						<td colspan="3">
							<input type="text" id="jtjjqk" name="jtjjqk"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="jtjjqk" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								学费贷款金额
							</div>
						</td>
						<td>
							<input type="text" id="xfdkze" name="xfdkze"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="xfdkze" />">
						</td>
						<td>
							<div align="center">
								生活费贷款金额
							</div>
						</td>
						<td>
							<input type="text" id="shfdkze" name="shfdkze"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="shfdkze" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								贷款总额
							</div>
						</td>
						<td>
							<input type="text" id="dkze" name="dkze"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name="rs" property="dkze" />">
						</td>
						<td>
							<div align="center">
								申请时间
							</div>
						</td>
						<td>
							<input type="text" id="sqsj" name="sqsj"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sqsj" />">
						</td>
					</tr>
					<tr>
						<td scope="row" colspan="4">
							<div align="center">
								上&nbsp;学&nbsp;年&nbsp;学&nbsp;习&nbsp;成&nbsp;绩
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
						<td>
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
						<td scope="row">
							<input type="text" id="sxncj1_mc" name="sxncj1_mc"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj1_mc" />">
						</td>
						<td>
							<input type="text" id="sxncj1_cj" name="sxncj1_cj"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj1_cj" />">
						</td>
						<td>
							<input type="text" id="sxncj2_mc" name="sxncj2_mc"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj2_mc" />">
						</td>
						<td>
							<input type="text" id="sxncj2_cj" name="sxncj2_cj"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj2_cj" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<input type="text" id="sxncj3_mc" name="sxncj3_mc"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj3_mc" />">
						</td>
						<td>
							<input type="text" id="sxncj3_cj" name="sxncj3_cj"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj3_cj" />">
						</td>
						<td>
							<input type="text" id="sxncj4_mc" name="sxncj4_mc"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj4_mc" />">
						</td>
						<td>
							<input type="text" id="sxncj4_cj" name="sxncj4_cj"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj4_cj" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<input type="text" id="sxncj5_mc" name="sxncj5_mc"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj5_mc" />">
						</td>
						<td>
							<input type="text" id="sxncj5_cj" name="sxncj5_cj"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj5_cj" />">
						</td>
						<td>
							<input type="text" id="sxncj6_mc" name="sxncj6_mc"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj6_mc" />">
						</td>
						<td>
							<input type="text" id="sxncj6_cj" name="sxncj6_cj"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj6_cj" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<input type="text" id="sxncj7_mc" name="sxncj7_mc"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj7_mc" />">
						</td>
						<td>
							<input type="text" id="sxncj7_cj" name="sxncj7_cj"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj7_cj" />">
						</td>
						<td>
							<input type="text" id="sxncj8_mc" name="sxncj8_mc"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj8_mc" />">
						</td>
						<td>
							<input type="text" id="sxncj8_cj" name="sxncj8_cj"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj8_cj" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<input type="text" id="sxncj9_mc" name="sxncj9_mc"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj9_mc" />">
						</td>
						<td>
							<input type="text" id="sxncj9_cj" name="sxncj9_cj"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj9_cj" />">
						</td>
						<td>
							<input type="text" id="sxncj10_mc" name="sxncj10_mc"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj10_mc" />">
						</td>
						<td>
							<input type="text" id="sxncj10_cj" name="sxncj10_cj"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sxncj10_cj" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								入学以来不及格科目
							</div>
						</td>
						<td>
							<input type="text" id="rxlbjgkm" name="rxlbjgkm"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="rxlbjgkm" />">
						</td>
						<td>
							<div align="center">
								入学以来补考通过科目
							</div>
						</td>
						<td>
							<input type="text" id="rxlbktgkm" name="rxlbktgkm"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="rxlbktgkm" />">
						</td>
					</tr>
					<logic:equal name="isQuery" value="is">
					<logic:equal name="userOnLine" value="student" scope="session">
					<tr>
						<td scope="row">
							<div align="center">
								合同号
							</div>
						</td>
						<td>
							<input type="text" id="dkhth" name="dkhth"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="dkhth" />">
						</td>
						<td colspan="2">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								经办银行
							</div>
						</td>
						<td>
							<input type="text" id="jbyh" name="jbyh"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="jbyh" />">
						</td>
						<td>
							<div align="center">
								分支机构
							</div>
						</td>
						<td>
							<input type="text" id="fzjgmc" name="fzjgmc"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="fzjgmc" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								批准日期
							</div>
						</td>
						<td>
							<input type="text" id="pzrq" name="pzrq"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="pzrq" />">
						</td>
						<td>
							<div align="center">
								经办员
							</div>
						</td>
						<td>
							<input type="text" id="jby" name="jby"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="jby" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								还款开始日期
							</div>
						</td>
						<td>
							<input type="text" id="hkksrq" name="hkksrq"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="hkksrq" />">
						</td>
						<td>
							<div align="center">
								还款截止日期
							</div>
						</td>
						<td>
							<input type="text" id="hkjzrq" name="hkjzrq"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="hkjzrq" />">
						</td>
					</tr>
					</logic:equal>
					<logic:equal name="userOnLine" value="teacher" scope="session">
					<tr>
						<td scope="row">
							<div align="center">
								合同号
							</div>
						</td>
						<td>
							<input type="text" id="dkhth" name="dkhth"
								style="width:100%;heigh:100%" maxlength="30"
								value="<bean:write name='rs' property="dkhth" />">
						</td>
						<td colspan="2">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								经办银行
							</div>
						</td>
						<td>
							<input type="text" id="jbyh" name="jbyh"
								style="width:100%;heigh:100%" maxlength="50"
								value="<bean:write name='rs' property="jbyh" />">
						</td>
						<td>
							<div align="center">
								分支机构
							</div>
						</td>
						<td>
							<input type="text" id="fzjgmc" name="fzjgmc"
								style="width:100%;heigh:100%" maxlength="50"
								value="<bean:write name='rs' property="fzjgmc" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								批准日期
							</div>
						</td>
						<td>
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('pzrq','y-mm-dd');"
								value="<bean:write name='rs' property="pzrq" />" name="pzrq"
								id="pzrq" />
						</td>
						<td>
							<div align="center">
								经办员
							</div>
						</td>
						<td>
							<input type="text" id="jby" name="jby"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="jby" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								还款开始日期
							</div>
						</td>
						<td>
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('hkksrq','y-mm-dd');"
								value="<bean:write name='rs' property="hkksrq" />" name="hkksrq"
								id="hkksrq" />
						</td>
						<td>
							<div align="center">
								还款截止日期
							</div>
						</td>
						<td>
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('hkjzrq','y-mm-dd');"
								value="<bean:write name='rs' property="hkjzrq" />" name="hkjzrq"
								id="hkjzrq" />
						</td>
					</tr>
					</logic:equal>
					</logic:equal>
				</table>
			</logic:equal>
			<logic:equal name="sfksq" value="1">

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
	         			alert("已通过审核，只能修改学生家庭信息！");
	         		</script>
						<logic:present name="tBol">
							<logic:match value="true" name="tBol">
								<script language="javascript">
	         					alert("保存成功！");
	         				</script>
							</logic:match>
							<logic:match value="false" name="tBol">
								<script language="javascript">
	         					alert("保存失败！");
	         				</script>
							</logic:match>
						</logic:present>
					</logic:match>
				</logic:present>
				<logic:present name="notKNS">
					<logic:match value="notKNS" name="notKNS">
						<script language="javascript">
	         	alert("必需是困难生才能申请！");
	         	</script>
					</logic:match>
				</logic:present>

				<table class="tbstyle" width="90%">
					<tr>
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="center" width="16%">
								<font color="red">*</font>学号
							</td>
							<td align="left" width="34%">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="center" width="16%">
								<font color="red">*</font>学号
							</td>
							<td align="left" width="34%">
								<input type="text" id="xh" name="xh"
									style="width:100%;heigh:100%"
									value="<bean:write name='rs' property="xh" />" readonly="true">
							</td>
						</logic:equal>
						<td width="16%" scope="col">
							<div align="center">
								姓名
							</div>
						</td>
						<td width="34%" scope="col">
							<input type="text" id="xm" name="xm"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xm" />" readonly="true">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								性别
							</div>
						</td>
						<td>
							<input type="text" id="xb" name="xb"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xb" />" readonly="true">
						</td>
						<td>
							<div align="center">
								身份证号
							</div>
						</td>
						<td>
							<input type="text" id="sfzh" name="sfzh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="sfzh" />" readonly="true">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />
							</div>
						</td>
						<td>
							<input type="text" id="xymc" name="xymc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xymc" />" readonly="true">
						</td>
						<td>
							<div align="center">
								专业
							</div>
						</td>
						<td>
							<input type="text" id="zymc" name="zymc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="zymc" />" readonly="true">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								班级
							</div>
						</td>
						<td>
							<input type="text" id="bjmc" name="bjmc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="bjmc" />" readonly="true">
						</td>
						<td>
							<div align="center">
								学制
							</div>
						</td>
						<td>
							<input type="text" id="xz" name="xz"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xz" />" readonly="true">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								入学年月
							</div>
						</td>
						<td>
							<input type="text" id="rxny" name="rxny"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="rxny" />" readonly="true">
						</td>
						<td>
							<div align="center">
								毕业年月
							</div>
						</td>
						<td>
							<input type="text" id="byny" name="byny"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="byny" />" readonly="true">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								移动电话
							</div>
						</td>
						<td>
							<input type="text" id="yddh" name="yddh"
								style="width:100%;heigh:100%" maxlength="15"
								value="<bean:write name='rs' property="yddh" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td>
							<div align="center">
								固定电话
							</div>
						</td>
						<td>
							<input type="text" id="gddh" name="gddh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="gddh" />" maxlength="15"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								培养层次
							</div>
						</td>
						<td>
							<div align="center">
								<logic:present name="rs" property="pycc">
									<logic:match value="本科" name="rs" property="pycc">
										<input type="radio" name="pycc" value="本科" checked>&nbsp;&nbsp;本科
							    	<input type="radio" name="pycc" value="专科">&nbsp;&nbsp;专科
							    	<input type="radio" name="pycc" value="专接本">&nbsp;&nbsp;专接本
								</logic:match>
									<logic:match value="专科" name="rs" property="pycc">
										<input type="radio" name="pycc" value="本科">&nbsp;&nbsp;本科
							    	<input type="radio" name="pycc" value="专科" checked>&nbsp;&nbsp;专科
							    	<input type="radio" name="pycc" value="专接本">&nbsp;&nbsp;专接本
								</logic:match>
									<logic:match value="专接本" name="rs" property="pycc">
										<input type="radio" name="pycc" value="本科">&nbsp;&nbsp;本科
							    	<input type="radio" name="pycc" value="专科">&nbsp;&nbsp;专科
							    	<input type="radio" name="pycc" value="专接本" checked>&nbsp;&nbsp;专接本
								</logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="pycc">
									<input type="radio" name="pycc" value="本科" checked>&nbsp;&nbsp;本科
								<input type="radio" name="pycc" value="专科">&nbsp;&nbsp;专科
								<input type="radio" name="pycc" value="专接本">&nbsp;&nbsp;专接本
							</logic:notPresent>
							</div>
						</td>
						<td>
							<div align="center">
								邮政编码
							</div>
						</td>
						<td>
							<input type="text" id="yzbm" name="yzbm"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="yzbm" />" maxlength="6"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								家庭住址
							</div>
						</td>
						<td colspan="3">
							<input type="text" id="jtzz" name="jtzz"
								style="width:100%;heigh:100%" maxlength="200"
								value="<bean:write name='rs' property="jtzz" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								父亲姓名
							</div>
						</td>
						<td>
							<input type="text" id="fqxm" name="fqxm"
								style="width:100%;heigh:100%" maxlength="20"
								value="<bean:write name='rs' property="fqxm" />">
						</td>
						<td>
							<div align="center">
								父亲联系电话
							</div>
						</td>
						<td>
							<input type="text" id="fqlxdh" name="fqlxdh"
								style="width:100%;heigh:100%" maxlength="15"
								value="<bean:write name='rs' property="fqlxdh" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								父亲身份证号
							</div>
						</td>
						<td>
							<input type="text" id="fqsfzh" name="fqsfzh"
								style="width:100%;heigh:100%" maxlength="18"
								value="<bean:write name='rs' property="fqsfzh" />">
						</td>
						<td scope="row">
							<div align="center">
								父亲工作单位
							</div>
						</td>
						<td>
							<input type="text" id="fqgzdw" name="fqgzdw"
								style="width:100%;heigh:100%" maxlength="200"
								value="<bean:write name='rs' property="fqgzdw" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								母亲姓名
							</div>
						</td>
						<td>
							<input type="text" id="mqxm" name="mqxm"
								style="width:100%;heigh:100%" maxlength="20"
								value="<bean:write name='rs' property="mqxm" />">
						</td>
						<td>
							<div align="center">
								母亲联系电话
							</div>
						</td>
						<td>
							<input type="text" id="mqlxdh" name="mqlxdh"
								style="width:100%;heigh:100%" maxlength="15"
								value="<bean:write name='rs' property="mqlxdh" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								母亲身份证号
							</div>
						</td>
						<td>
							<input type="text" id="mqsfzh" name="mqsfzh"
								style="width:100%;heigh:100%" maxlength="18"
								value="<bean:write name='rs' property="mqsfzh" />">
						</td>
						<td scope="row">
							<div align="center">
								母亲工作单位
							</div>
						</td>
						<td>
							<input type="text" id="mqgzdw" name="mqgzdw"
								style="width:100%;heigh:100%" maxlength="200"
								value="<bean:write name='rs' property="mqgzdw" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								家庭经济情况
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="jtjjqk" rows='5'
								style="width:100%" onblur="yzdx(this,'jtjjqk')" />
							<input type="hidden" id="jtjjqk" name="jtjjqk" value="">
						</td>
					</tr>
					<logic:equal name="equ" value="equ">
						<tr>
							<td scope="row">
								<div align="center">
									学费贷款金额
								</div>
							</td>
							<td>
								<logic:empty name="rs" property="xfdkze">
									<input type="text" id="xfdkze" name="xfdkze"
										style="width:100%;heigh:100%" readonly="readonly"
										value="<bean:write name="rs" property="szxfje" />">
								</logic:empty>
								<logic:notEmpty name="rs" property="xfdkze">
									<input type="text" id="xfdkze" name="xfdkze"
										style="width:100%;heigh:100%" readonly="readonly"
										value="<bean:write name='rs' property="xfdkze" />">
								</logic:notEmpty>
							</td>
							<td>
								<div align="center">
									生活费贷款金额
								</div>
							</td>
							<td>
								<logic:empty name="rs" property="shfdkze">
									<input type="text" id="shfdkze" name="shfdkze"
										style="width:100%;heigh:100%" readonly="readonly" value="0">
								</logic:empty>
								<logic:notEmpty name="rs" property="shfdkze">
									<input type="text" id="shfdkze" name="shfdkze"
										style="width:100%;heigh:100%" readonly="readonly"
										value="<bean:write name='rs' property="shfdkze" />">
								</logic:notEmpty>
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="equ" value="notEqu">
						<tr>
							<td scope="row">
								<div align="center">
									学费贷款金额
								</div>
							</td>
							<td>
								<logic:empty name="rs" property="xfdkze">
									<input type="text" id="xfdkze" name="xfdkze"
										style="width:100%;heigh:100%" readonly="readonly"
										value="<bean:write name="rs" property="szxfje" />">
								</logic:empty>
								<logic:notEmpty name="rs" property="xfdkze">
									<input type="text" id="xfdkze" name="xfdkze"
										style="width:100%;heigh:100%" readonly="readonly"
										value="<bean:write name='rs' property="xfdkze" />">
								</logic:notEmpty>
							</td>
							<td>
								<div align="center">
									生活费贷款金额
								</div>
							</td>
							<td>
								<input type="text" id="shfdkze" name="shfdkze"
									style="width:100%;heigh:100%" maxlength="5" onblur="je();"
									value="<bean:write name='rs' property="shfdkze" />"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
						</tr>
					</logic:equal>
					<tr>
						<td scope="row">
							<div align="center">
								贷款总额
							</div>
						</td>
						<td>
							<input type="text" id="dkze" name="dkze"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="dkze" />">
						</td>
						<td>
							<div align="center">
								申请时间
							</div>
						</td>
						<td>
							<input type="text" id="sqsj" name="sqsj"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="sqsj" />">
						</td>
					</tr>
<%--					<tr>--%>
<%--						<td scope="row" colspan="4">--%>
<%--							<div align="center">--%>
<%--								上&nbsp;学&nbsp;年&nbsp;学&nbsp;习&nbsp;成&nbsp;绩--%>
<%--							</div>--%>
<%--						</td>--%>
<%--					</tr>--%>
<%--					<tr>--%>
<%--						<td scope="row">--%>
<%--							<div align="center">--%>
<%--								课程名称--%>
<%--							</div>--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<div align="center">--%>
<%--								成绩--%>
<%--							</div>--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<div align="center">--%>
<%--								课程名称--%>
<%--							</div>--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<div align="center">--%>
<%--								成绩--%>
<%--							</div>--%>
<%--						</td>--%>
<%--					</tr>--%>
<%--					<tr>--%>
<%--						<td scope="row">--%>
<%--							<input type="text" id="sxncj1_mc" name="sxncj1_mc"--%>
<%--								style="width:100%;heigh:100%" maxlength="200"--%>
<%--								value="<bean:write name='rs' property="sxncj1_mc" />">--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<input type="text" id="sxncj1_cj" name="sxncj1_cj"--%>
<%--								style="width:100%;heigh:100%" maxlength="20"--%>
<%--								value="<bean:write name='rs' property="sxncj1_cj" />">--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<input type="text" id="sxncj2_mc" name="sxncj2_mc"--%>
<%--								style="width:100%;heigh:100%" maxlength="200"--%>
<%--								value="<bean:write name='rs' property="sxncj2_mc" />">--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<input type="text" id="sxncj2_cj" name="sxncj2_cj"--%>
<%--								style="width:100%;heigh:100%" maxlength="20"--%>
<%--								value="<bean:write name='rs' property="sxncj2_cj" />">--%>
<%--						</td>--%>
<%--					</tr>--%>
<%--					<tr>--%>
<%--						<td scope="row">--%>
<%--							<input type="text" id="sxncj3_mc" name="sxncj3_mc"--%>
<%--								style="width:100%;heigh:100%" maxlength="200"--%>
<%--								value="<bean:write name='rs' property="sxncj3_mc" />">--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<input type="text" id="sxncj3_cj" name="sxncj3_cj"--%>
<%--								style="width:100%;heigh:100%" maxlength="20"--%>
<%--								value="<bean:write name='rs' property="sxncj3_cj" />">--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<input type="text" id="sxncj4_mc" name="sxncj4_mc"--%>
<%--								style="width:100%;heigh:100%" maxlength="200"--%>
<%--								value="<bean:write name='rs' property="sxncj4_mc" />">--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<input type="text" id="sxncj4_cj" name="sxncj4_cj"--%>
<%--								style="width:100%;heigh:100%" maxlength="20"--%>
<%--								value="<bean:write name='rs' property="sxncj4_cj" />">--%>
<%--						</td>--%>
<%--					</tr>--%>
<%--					<tr>--%>
<%--						<td scope="row">--%>
<%--							<input type="text" id="sxncj5_mc" name="sxncj5_mc"--%>
<%--								style="width:100%;heigh:100%" maxlength="200"--%>
<%--								value="<bean:write name='rs' property="sxncj5_mc" />">--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<input type="text" id="sxncj5_cj" name="sxncj5_cj"--%>
<%--								style="width:100%;heigh:100%" maxlength="20"--%>
<%--								value="<bean:write name='rs' property="sxncj5_cj" />">--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<input type="text" id="sxncj6_mc" name="sxncj6_mc"--%>
<%--								style="width:100%;heigh:100%" maxlength="200"--%>
<%--								value="<bean:write name='rs' property="sxncj6_mc" />">--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<input type="text" id="sxncj6_cj" name="sxncj6_cj"--%>
<%--								style="width:100%;heigh:100%" maxlength="20"--%>
<%--								value="<bean:write name='rs' property="sxncj6_cj" />">--%>
<%--						</td>--%>
<%--					</tr>--%>
<%--					<tr>--%>
<%--						<td scope="row">--%>
<%--							<input type="text" id="sxncj7_mc" name="sxncj7_mc"--%>
<%--								style="width:100%;heigh:100%" maxlength="200"--%>
<%--								value="<bean:write name='rs' property="sxncj7_mc" />">--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<input type="text" id="sxncj7_cj" name="sxncj7_cj"--%>
<%--								style="width:100%;heigh:100%" maxlength="20"--%>
<%--								value="<bean:write name='rs' property="sxncj7_cj" />">--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<input type="text" id="sxncj8_mc" name="sxncj8_mc"--%>
<%--								style="width:100%;heigh:100%" maxlength="200"--%>
<%--								value="<bean:write name='rs' property="sxncj8_mc" />">--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<input type="text" id="sxncj8_cj" name="sxncj8_cj"--%>
<%--								style="width:100%;heigh:100%" maxlength="20"--%>
<%--								value="<bean:write name='rs' property="sxncj8_cj" />">--%>
<%--						</td>--%>
<%--					</tr>--%>
<%--					<tr>--%>
<%--						<td scope="row">--%>
<%--							<input type="text" id="sxncj9_mc" name="sxncj9_mc"--%>
<%--								style="width:100%;heigh:100%" maxlength="200"--%>
<%--								value="<bean:write name='rs' property="sxncj9_mc" />">--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<input type="text" id="sxncj9_cj" name="sxncj9_cj"--%>
<%--								style="width:100%;heigh:100%" maxlength="20"--%>
<%--								value="<bean:write name='rs' property="sxncj9_cj" />">--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<input type="text" id="sxncj10_mc" name="sxncj10_mc"--%>
<%--								style="width:100%;heigh:100%" maxlength="200"--%>
<%--								value="<bean:write name='rs' property="sxncj10_mc" />">--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<input type="text" id="sxncj10_cj" name="sxncj10_cj"--%>
<%--								style="width:100%;heigh:100%" maxlength="20"--%>
<%--								value="<bean:write name='rs' property="sxncj10_cj" />">--%>
<%--						</td>--%>
<%--					</tr>--%>
<%--					<tr>--%>
<%--						<td scope="row">--%>
<%--							<div align="center">--%>
<%--								入学以来不及格科目--%>
<%--							</div>--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<input type="text" id="rxlbjgkm" name="rxlbjgkm"--%>
<%--								style="width:100%;heigh:100%" maxlength="2"--%>
<%--								value="<bean:write name='rs' property="rxlbjgkm" />"--%>
<%--								onkeyup="value=value.replace(/[^\d]/g,'') "--%>
<%--								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<div align="center">--%>
<%--								入学以来补考通过科目--%>
<%--							</div>--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<input type="text" id="rxlbktgkm" name="rxlbktgkm"--%>
<%--								style="width:100%;heigh:100%"--%>
<%--								value="<bean:write name='rs' property="rxlbktgkm" />"--%>
<%--								maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'') "--%>
<%--								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">--%>
<%--						</td>--%>
<%--					</tr>--%>
					<logic:equal name="isQuery" value="is">
					<logic:equal name="userOnLine" value="student" scope="session">
					<tr>
						<td scope="row">
							<div align="center">
								合同号
							</div>
						</td>
						<td>
							<input type="text" id="dkhth" name="dkhth"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="dkhth" />">
						</td>
						<td scope="row">
							<div align="center">
								合同总金额
							</div>
						</td>
						<td>
							<input type="text" id="htzje" name="htzje"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="htzje" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								经办银行
							</div>
						</td>
						<td>
							<input type="text" id="jbyh" name="jbyh"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="jbyh" />">
						</td>
						<td>
							<div align="center">
								分支机构
							</div>
						</td>
						<td>
							<input type="text" id="fzjgmc" name="fzjgmc"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="fzjgmc" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								批准日期
							</div>
						</td>
						<td>
							<input type="text" id="pzrq" name="pzrq"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="pzrq" />">
						</td>
						<td>
							<div align="center">
								经办员
							</div>
						</td>
						<td>
							<input type="text" id="jby" name="jby"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="jby" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								还款开始日期
							</div>
						</td>
						<td>
							<input type="text" id="hkksrq" name="hkksrq"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="hkksrq" />">
						</td>
						<td>
							<div align="center">
								还款截止日期
							</div>
						</td>
						<td>
							<input type="text" id="hkjzrq" name="hkjzrq"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name='rs' property="hkjzrq" />">
						</td>
					</tr>
					</logic:equal>
					<logic:equal name="userOnLine" value="teacher" scope="session">
					<tr>
						<td scope="row">
							<div align="center">
								合同号
							</div>
						</td>
						<td>
							<input type="text" id="dkhth" name="dkhth"
								style="width:100%;heigh:100%" maxlength="30"
								value="<bean:write name='rs' property="dkhth" />">
						</td>
						<td scope="row">
							<div align="center">
								合同总金额
							</div>
						</td>
						<td>
							<input type="text" id="htzje" name="htzje"
								style="width:100%;heigh:100%" maxlength="6"
								value="<bean:write name='rs' property="htzje" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								经办银行
							</div>
						</td>
						<td>
							<input type="text" id="jbyh" name="jbyh"
								style="width:100%;heigh:100%" maxlength="50"
								value="<bean:write name='rs' property="jbyh" />">
						</td>
						<td>
							<div align="center">
								分支机构
							</div>
						</td>
						<td>
							<input type="text" id="fzjgmc" name="fzjgmc"
								style="width:100%;heigh:100%" maxlength="50"
								value="<bean:write name='rs' property="fzjgmc" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								批准日期
							</div>
						</td>
						<td>
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('pzrq','y-mm-dd');"
								value="<bean:write name='rs' property="pzrq" />" name="pzrq"
								id="pzrq" />
						</td>
						<td>
							<div align="center">
								经办员
							</div>
						</td>
						<td>
							<input type="text" id="jby" name="jby"
								style="width:100%;heigh:100%" maxlength="40"
								value="<bean:write name='rs' property="jby" />">
						</td>
					</tr>
					<tr>
						<td scope="row">
							<div align="center">
								还款开始日期
							</div>
						</td>
						<td>
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('hkksrq','y-mm-dd');"
								value="<bean:write name='rs' property="hkksrq" />" name="hkksrq"
								id="hkksrq" />
						</td>
						<td>
							<div align="center">
								还款截止日期
							</div>
						</td>
						<td>
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('hkjzrq','y-mm-dd');"
								value="<bean:write name='rs' property="hkjzrq" />" name="hkjzrq"
								id="hkjzrq" />
						</td>
					</tr>
					</logic:equal>
					</logic:equal>
				</table>
			</logic:equal>
	<logic:notEqual name="sfksq" value="-1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2" onClick="yz();">
					提交申请
				</button>
				<button class="button2" onClick="toPrintOut();">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
			</div>
	</logic:notEqual>
		</html:form>
</body>
<script language="javascript">
			var xfdkze = document.getElementById('xfdkze').value;
			var shfdkze = document.getElementById('shfdkze').value;
			var szdkze = document.getElementById('szdkze').value;
			var dkze = '0';
			
			if(xfdkze == null || xfdkze == ""){
				document.getElementById('xfdkze').value = '0';
				xfdkze = 0;
			}
			if(shfdkze == null || shfdkze == ""){
				document.getElementById('shfdkze').value = '0';
				shfdkze = 0;
			}
			if(szdkze == null || szdkze == ""){
				document.getElementById('szdkze').value = '0';
				szdkze = 0;
			}
			dkze = Math.round(xfdkze) + Math.round(shfdkze);
			document.getElementById('dkze').value = dkze;
	</script>
</html:html>
