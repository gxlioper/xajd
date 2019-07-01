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
		function yz(){
			var xh = document.getElementById('xh').value;
			var jg = document.getElementById('jg').value;
			var hjszd = document.getElementById('hjszd').value;
			var jtdh = document.getElementById('jtdh').value;
			var yddh = document.getElementById('yddh').value;
			var e_mail = document.getElementById('e_mail').value;
			var yzbm = document.getElementById('yzbm').value;
			var jtdz = document.getElementById('jtdz').value;
			var jzr1_xm = document.getElementById('jzr1_xm').value;
			var jzr1_xb = document.getElementById('jzr1_xb').value;
			var jzr1_zjlxjhm = document.getElementById('jzr1_zjlxjhm').value;
			var jzr1_yjkrgx = document.getElementById('jzr1_yjkrgx').value;
			var jzr1_lxdh = document.getElementById('jzr1_lxdh').value;
			var jzr1_txdz = document.getElementById('jzr1_txdz').value;
			var jzr2_xm = document.getElementById('jzr2_xm').value;
			var jzr2_xb = document.getElementById('jzr2_xb').value;
			var jzr2_zjlxjhm = document.getElementById('jzr2_zjlxjhm').value;
			var jzr2_yjkrgx = document.getElementById('jzr2_yjkrgx').value;
			var jzr2_lxdh = document.getElementById('jzr2_lxdh').value;
			var jzr2_txdz = document.getElementById('jzr2_txdz').value;
			var jkzje = document.getElementById('jkzje').value;
			var dkqx = document.getElementById('dkqx').value;
			var xfdj = document.getElementById('xfdj').value;
			var zsfdj = document.getElementById('zsfdj').value;
			var shfdj = document.getElementById('shfdj').value;
			var skrzhlxjzh = document.getElementById('skrzhlxjzh').value;
		
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if((jg == null) || (jg == "")){
				alert("籍贯不能为空!");
				return false;
			}
			if((hjszd == null) || (hjszd == "")){
				alert("户籍所在地不能为空!");
				return false;
			}
			if((jtdh == null) || (jtdh == "")){
				alert("家庭电话不能为空!");
				return false;
			}
			if((yddh == null) || (yddh == "")){
				alert("移动电话不能为空!");
				return false;
			}
			if((e_mail == null) || (e_mail == "")){
				alert("E-Mail地址不能为空!");
				return false;
			}
			if((yzbm == null) || (yzbm == "")){
				alert("邮政编码不能为空!");
				return false;
			}
			if((jtdz == null) || (jtdz == "")){
				alert("家庭地址不能为空!");
				return false;
			}
			if((jzr1_xm == null) || (jzr1_xm == "")){
				alert("见证人1_姓名不能为空!");
				return false;
			}
			if((jzr1_xb == null) || (jzr1_xb == "")){
				alert("见证人1_性别不能为空!");
				return false;
			}
			if((jzr1_zjlxjhm == null) || (jzr1_zjlxjhm == "")){
				alert("见证人1_证件类型及号码不能为空!");
				return false;
			}
			if((jzr1_yjkrgx == null) || (jzr1_yjkrgx == "")){
				alert("见证人1_与借款人关系不能为空!");
				return false;
			}
			if((jzr1_lxdh == null) || (jzr1_lxdh == "")){
				alert("见证人1_联系电话不能为空!");
				return false;
			}
			if((jzr1_txdz == null) || (jzr1_txdz == "")){
				alert("见证人1_通讯地址不能为空!");
				return false;
			}
			if((jzr2_xm == null) || (jzr2_xm == "")){
				alert("见证人2_姓名不能为空!");
				return false;
			}
			if((jzr2_xb == null) || (jzr2_xb == "")){
				alert("见证人2_性别不能为空!");
				return false;
			}
			if((jzr2_zjlxjhm == null) || (jzr2_zjlxjhm == "")){
				alert("见证人2_证件类型及号码不能为空!");
				return false;
			}
			if((jzr2_yjkrgx == null) || (jzr2_yjkrgx == "")){
				alert("见证人2_与借款人关系不能为空!");
				return false;
			}
			if((jzr2_lxdh == null) || (jzr2_lxdh == "")){
				alert("见证人2_联系电话不能为空!");
				return false;
			}
			if((jzr2_txdz == null) || (jzr2_txdz == "")){
				alert("见证人2_通讯地址不能为空!");
				return false;
			}
			if((jkzje == null) || (jkzje == "")){
				alert("借款总金额不能为空!");
				return false;
			}
			if((dkqx == null) || (dkqx == "")){
				alert("贷款期限不能为空!");
				return false;
			}
			if((xfdj == null) || (xfdj == "")){
				alert("学费单价不能为空!");
				return false;
			}
			if((zsfdj == null) || (zsfdj == "")){
				alert("住宿费单价不能为空!");
				return false;
			}
			if((shfdj == null) || (shfdj == "")){
				alert("生活费单价不能为空!");
				return false;
			}
			if((skrzhlxjzh == null) || (skrzhlxjzh == "")){
				alert("收款人帐户类型及帐号不能为空!");
				return false;
			}
			
			document.forms[0].action = "/xgxt/zzsf_gjzxdk.do?doType=add";
			document.forms[0].submit();
		}
		
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/zzsf_gjzxdkb.do";
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
				现在不在申请时间！
			</p>
		</center>
	</logic:equal>
		<html:form action="zzsf_gjzxdk.do" method="post">
			<input type="hidden" id="url" name="url" value="/zzsf_gjzxdk.do" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="isKNS" name="isKNS"
				value="<bean:write name="isKNS" scope="request" />">

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
						<td align="center" width="16%">
							<font color="red">*</font>学号
						</td>
						<td align="left" width="34%">
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
						<td align="center" width="16%">
							<font color="red">*</font>学号
						</td>
						<td align="left" width="34%">
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
					<td width="34%">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<input type="text" id="xb" readonly="readonly" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<td>
						<div align="center">
							出生日期
						</div>
					</td>
					<td>
						<input type="text" id="csrq" name="csrq" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csrq"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							民族
						</div>
					</td>
					<td>
						<input type="text" id="mzmc" readonly="readonly" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							系别
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
					<td>
						<div align="center">
							专业
						</div>
					</td>
					<td>
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							班级
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" readonly="readonly" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td>
						<input type="text" id="nj" readonly="readonly" name="nj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							入学日期
						</div>
					</td>
					<td>
						<input type="text" id="rxny" readonly="readonly" name="rxny"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxny"/>">
					</td>
					<td>
						<div align="center">
							毕业日期
						</div>
					</td>
					<td>
						<input type="text" id="byny" readonly="readonly" name="byny"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="byny"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							学制
						</div>
					</td>
					<td>
						<input type="text" id="xz" readonly="readonly" name="xz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xz"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>最高学历
						</div>
					</td>
					<td>
						<html:select name="rs" property="zgxl">
							<html:options collection="zgxlList" property="zgxl"
								labelProperty="zgxl" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>籍贯
						</div>
					</td>
					<td>
						<input type="text" id="jg" maxlength="25" name="jg"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jg"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>户籍所在地
						</div>
					</td>
					<td>
						<input type="text" id="hjszd" maxlength="25" name="hjszd"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hjszd"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>健康状况
						</div>
					</td>
					<td align="center">
						<logic:present name="rs" property="jkzk">
									<logic:match value="良好" name="rs" property="jkzk">
										<input type="radio" name="jkzk" value="良好" checked>&nbsp;&nbsp;良好
							         	<input type="radio" name="jkzk" value="一般">&nbsp;&nbsp;一般
							         	<input type="radio" name="jkzk" value="差">&nbsp;&nbsp;差
							         </logic:match>
									<logic:match value="一般" name="rs" property="jkzk">
										<input type="radio" name="jkzk" value="良好">&nbsp;&nbsp;良好
							         	<input type="radio" name="jkzk" value="一般" checked>&nbsp;&nbsp;一般
							         	<input type="radio" name="jkzk" value="差">&nbsp;&nbsp;差
							         </logic:match>
							         <logic:match value="差" name="rs" property="jkzk">
										<input type="radio" name="jkzk" value="良好">&nbsp;&nbsp;良好
							         	<input type="radio" name="jkzk" value="一般">&nbsp;&nbsp;一般
							         	<input type="radio" name="jkzk" value="差" checked>&nbsp;&nbsp;差
							         </logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="jkzk">
									<input type="radio" name="jkzk" value="良好" checked>&nbsp;&nbsp;良好
							         <input type="radio" name="jkzk" value="一般">&nbsp;&nbsp;一般
							         <input type="radio" name="jkzk" value="差">&nbsp;&nbsp;差
						         </logic:notPresent>
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>婚姻状况
						</div>
					</td>
					<td>
						<html:select name="rs" property="hyzk">
							<html:options collection="hyzkList" property="hyzk"
								labelProperty="hyzk" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>家庭电话
						</div>
					</td>
					<td>
						<input type="text" id="jtdh" maxlength="15" name="jtdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>移动电话
						</div>
					</td>
					<td>
						<input type="text" id="yddh" maxlength="15" name="yddh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yddh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>E-Mail地址
						</div>
					</td>
					<td>
						<input type="text" id="e_mail" maxlength="40" name="e_mail"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="e_mail"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>邮政编码
						</div>
					</td>
					<td>
						<input type="text" id="yzbm" maxlength="6" name="yzbm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>家庭地址
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtdz" maxlength="100" name="jtdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdz"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>见证人1_姓名
						</div>
					</td>
					<td>
						<input type="text" id="jzr1_xm" maxlength="20" name="jzr1_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr1_xm"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>见证人1_性别
						</div>
					</td>
					<td>
						<input type="text" id="jzr1_xb" maxlength="5" name="jzr1_xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr1_xb"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>见证人1_证件类型及号码
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jzr1_zjlxjhm" maxlength="50" name="jzr1_zjlxjhm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr1_zjlxjhm"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>见证人1_与借款人关系
						</div>
					</td>
					<td>
						<input type="text" id="jzr1_yjkrgx" maxlength="10" name="jzr1_yjkrgx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr1_yjkrgx"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>见证人1_联系电话
						</div>
					</td>
					<td>
						<input type="text" id="jzr1_lxdh" maxlength="15" name="jzr1_lxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr1_lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>见证人1_通讯地址
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jzr1_txdz" maxlength="100" name="jzr1_txdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr1_txdz"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>见证人2_姓名
						</div>
					</td>
					<td>
						<input type="text" id="jzr2_xm" maxlength="20" name="jzr2_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr2_xm"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>见证人2_性别
						</div>
					</td>
					<td>
						<input type="text" id="jzr2_xb" maxlength="5" name="jzr2_xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr2_xb"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>见证人2_证件类型及号码
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jzr2_zjlxjhm" maxlength="50" name="jzr2_zjlxjhm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr2_zjlxjhm"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>见证人2_与借款人关系
						</div>
					</td>
					<td>
						<input type="text" id="jzr2_yjkrgx" maxlength="10" name="jzr2_yjkrgx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr2_yjkrgx"/>">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>见证人2_联系电话
						</div>
					</td>
					<td>
						<input type="text" id="jzr2_lxdh" maxlength="15" name="jzr2_lxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr2_lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>见证人2_通讯地址
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jzr2_txdz" maxlength="100" name="jzr2_txdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jzr2_txdz"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>借款总金额
						</div>
					</td>
					<td>
						<input type="text" id="jkzje" maxlength="10" name="jkzje"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jkzje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>贷款期限
						</div>
					</td>
					<td>
						<input type="text" id="dkqx" maxlength="20" name="dkqx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkqx"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>还款方式
						</div>
					</td>
					<td align="left" colspan="3">
						<logic:present name="rs" property="hkfs">
									<logic:match value="等额本息还款法" name="rs" property="hkfs">
										<input type="radio" name="hkfs" value="等额本息还款法" checked>&nbsp;&nbsp;等额本息还款法
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							         	<input type="radio" name="hkfs" value="等额本金还款法">&nbsp;&nbsp;等额本金还款法
							         </logic:match>
									<logic:match value="等额本金还款法" name="rs" property="hkfs">
										<input type="radio" name="hkfs" value="等额本息还款法">&nbsp;&nbsp;等额本息还款法
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							         	<input type="radio" name="hkfs" value="等额本金还款法" checked>&nbsp;&nbsp;等额本金还款法
							         </logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="hkfs">
									<input type="radio" name="hkfs" value="等额本息还款法" checked>&nbsp;&nbsp;等额本息还款法
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							         <input type="radio" name="hkfs" value="等额本金还款法">&nbsp;&nbsp;等额本金还款法
						         </logic:notPresent>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>贷款种类
						</div>
					</td>
					<td align="left" colspan="3">
						<logic:present name="rs" property="dkzl">
									<logic:match value="中央财政贴息国家助学贷款" name="rs" property="dkzl">
										<input type="radio" name="dkzl" value="中央财政贴息国家助学贷款" checked>&nbsp;&nbsp;中央财政贴息国家助学贷款
										&nbsp;&nbsp;&nbsp;&nbsp;
							         	<input type="radio" name="dkzl" value="地方财政贴息国家助学贷款">&nbsp;&nbsp;地方财政贴息国家助学贷款
							         </logic:match>
									<logic:match value="地方财政贴息国家助学贷款" name="rs" property="dkzl">
										<input type="radio" name="dkzl" value="中央财政贴息国家助学贷款">&nbsp;&nbsp;中央财政贴息国家助学贷款
										&nbsp;&nbsp;&nbsp;&nbsp;
							         	<input type="radio" name="dkzl" value="地方财政贴息国家助学贷款" checked>&nbsp;&nbsp;地方财政贴息国家助学贷款
							         </logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="dkzl">
									<input type="radio" name="dkzl" value="中央财政贴息国家助学贷款" checked>&nbsp;&nbsp;中央财政贴息国家助学贷款
									&nbsp;&nbsp;&nbsp;&nbsp;
							         <input type="radio" name="dkzl" value="地方财政贴息国家助学贷款">&nbsp;&nbsp;地方财政贴息国家助学贷款
						         </logic:notPresent>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>学费单价(按年)
						</div>
					</td>
					<td>
						<input type="text" id="xfdj" maxlength="10" name="xfdj"
							style="width:90%;heigh:100%"
							value="<bean:write name="rs" property="xfdj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元
					</td>
					<td>
						<div align="center">
							<font color="red">*</font>住宿费单价(按年)
						</div>
					</td>
					<td>
						<input type="text" id="zsfdj" maxlength="10" name="zsfdj"
							style="width:90%;heigh:100%"
							value="<bean:write name="rs" property="zsfdj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>生活费单价
						</div>
					</td>
					<td>
						<input type="text" id="shfdj" maxlength="10" name="shfdj"
							style="width:90%;heigh:100%"
							value="<bean:write name="rs" property="shfdj"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							元
					</td>
					<td colspan="2">
						<div align="left">
							按&nbsp;&nbsp;&nbsp;&nbsp;
							<logic:present name="rs" property="shffs">
									<logic:match value="月" name="rs" property="shffs">
										<input type="radio" name="shffs" value="月" checked>&nbsp;&nbsp;月
										&nbsp;&nbsp;&nbsp;&nbsp;
							         	<input type="radio" name="shffs" value="季">&nbsp;&nbsp;季
							         </logic:match>
									<logic:match value="季" name="rs" property="shffs">
										<input type="radio" name="shffs" value="月">&nbsp;&nbsp;月
										&nbsp;&nbsp;&nbsp;&nbsp;
							         	<input type="radio" name="shffs" value="季" checked>&nbsp;&nbsp;季
							         </logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="shffs">
									<input type="radio" name="shffs" value="月" checked>&nbsp;&nbsp;月
									&nbsp;&nbsp;&nbsp;&nbsp;
							         <input type="radio" name="shffs" value="季">&nbsp;&nbsp;季
						         </logic:notPresent>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<font color="red">*</font>收款人帐户类型及帐号
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="skrzhlxjzh" maxlength="40" name="skrzhlxjzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="skrzhlxjzh"/>">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2" id="tjbut"
					onClick="yz();">
					提交申请
				</button>
				<button type="button" class="button2"
					onClick="toPrintOut();">
					打&nbsp;&nbsp;&nbsp;&nbsp;印
				</button>
				<logic:equal name="isKNS" value="no">
					<br /><font color="red">必须是困难生才能申请!</font>
				</logic:equal>
			</div>
	</logic:equal>

		</html:form>
</body>
<script language="javascript">
	var isKNS = document.getElementById('isKNS').value;
	if (isKNS == "is") {
		document.getElementById('tjbut').disabled = "";
	} else {
		document.getElementById('tjbut').disabled = "true";
	}
</script>
</html:html>
