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
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript">
			function yz(){
				var isAllInput = checkAllInput('xh!!jzr1!!sfzh1!!sxnpddd!!bjgbxkms!!dkje');
				if(isAllInput){
					document.forms[0].action = "/xgxt/nbty_xszz.do?method=gjzxdksq&type=add";
					document.forms[0].submit();
					return true;
				}
				return false;
			}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：助学贷款 - 国家助学贷款申请
		</div>
	</div>
	<html:form action="/nbty_xszz.do?method=gjzxdksq" method="post">
		<input type="hidden" id="url" name="url"
			value="/nbty_xszz.do?method=gjzxdksq" />
		<input type="hidden" name="save_sqsj" value="${sqsj }" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xh-xm" />
		<input type="hidden" id="delStuInfo" name="getStuInfo"
			value="xh-xm-xz-nj-xb-xymc-zymc-bjmc-csrq-sfzh-ssbh-qsdh-jtdh-jtyb-jtdz-lxdh" />
		<table class="tbstyle" width="90%">
			<tr>
				<logic:notEqual name="userType" value="stu">
					<td align="center" width="16%">
						<font color="red">*</font>学号
					</td>
					<td align="left" width="34%">
						<html:text property="save_xh" styleId="xh" readonly="readonly"
							onchange="checkXhExists($('delStuInfo').value);"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
					</td>
				</logic:notEqual>
				<logic:equal name="userType" value="stu">
					<td align="center" width="16%">
						<font color="red">*</font>学号
					</td>
					<td align="left" width="34%">
						<html:text styleId="xh" property="save_xh"
							style="width:100%;heigh:100%" value="${rs.xh}" readonly="true" />
					</td>
				</logic:equal>
				<td width="16%">
					<div align="center">
						姓名
					</div>
				</td>
				<td width="34%">
					<input type="text" readonly="readonly" id="xm" name="xm"
						style="width:100%;heigh:100%" value="${rs.xm }"
						readonly="readonly" />
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
						style="width:100%;heigh:100%" value="${rs.xb }" />
				</td>
				<td>
					<div align="center">
						出生日期
					</div>
				</td>
				<td>
					<input type="text" readonly style="cursor:hand;width:100%"
						onclick="return showCalendar('csrq','y-mm-dd');"
						value="${rs.csrq}" name="csrq" id="csrq" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<font color="red">*</font>学年
					</div>
				</td>
				<td>
					<html:text styleId="xn" property="save_xn"
						style="width:100%;heigh:100%" value="${rs.xn}" readonly="true"/>
				</td>
				<td>
					<div align="center">
						学制
					</div>
				</td>
				<td>
					<input type="text" id="xz" readonly="readonly" name="xz"
						style="width:100%;heigh:100%" value="${rs.xz}" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						身份证号
					</div>
				</td>
				<td>
					<input type="text" id="sfzh" name="sfzh" readonly="readonly"
						style="width:100%;heigh:100%" value="${rs.sfzh }" />
				</td>
				<td>
					<div align="center">
						本人电话
						<br />
					</div>
				</td>
				<td>
					<input type="text" id="lxdh" name="lxdh" maxlength="20"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.lxdh }" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						年级
					</div>
				</td>
				<td>
					<input type="text" id="nj" readonly="readonly" name="nj"
						style="width:100%;heigh:100%" value="${rs.nj }" />
				</td>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />名称
					</div>
				</td>
				<td>
					<input type="text" id="xymc" name="xymc" readonly="readonly"
						style="width:100%;heigh:100%" value="${rs.xymc }" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						专业名称
					</div>
				</td>
				<td>
					<input type="text" id="zymc" readonly="readonly" name="zymc"
						style="width:100%;heigh:100%" value="${rs.zymc }" />
				</td>
				<td>
					<div align="center">
						班级
					</div>
				</td>
				<td>
					<input type="text" id="bjmc" name="bjmc" readonly="readonly"
						style="width:100%;heigh:100%" value="${rs.bjmc }" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						寝室编号
					</div>
				</td>
				<td>
					<input type="text" id="ssbh" name="ssbh" readonly="readonly"
						style="width:100%;heigh:100%" value="${rs.ssbh }" />
				</td>
				<td>
					<div align="center">
						寝室电话
						<br />
					</div>
				</td>
				<td>
					<input type="text" id="qsdh" name="qsdh" maxlength="20"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.qsdh }" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						家庭详细地址
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="jtdz" name="jtdz" maxlength="200"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.jtszd }" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						家庭邮编
					</div>
				</td>
				<td>
					<input type="text" id="jtyb" maxlength="6" name="jtyb"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.jtyb }" />
				</td>
				<td>
					<div align="center">
						家庭电话
					</div>
				</td>
				<td>
					<input type="text" id="jtdh" name="jtdh" maxlength="20"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.lxdh1 }" />
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<font color="red">注：家庭电话请按“区号-电话号码”格式填写；父母亲已故的请填写“已故”；无职业的，在父母职业处填写“无”。</font>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						父亲姓名
					</div>
				</td>
				<td>
					<input type="text" id="fqxm" maxlength="40" name="fqxm"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.fqxm }">
				</td>
				<td>
					<div align="center">
						母亲姓名
					</div>
				</td>
				<td>
					<input type="text" id="mqxm" name="mqxm" maxlength="40"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.mqxm }" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						父亲单位
					</div>
				</td>
				<td>
					<input type="text" id="fqdw" maxlength="40" name="fqdw"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.fqdw }" />
				</td>
				<td>
					<div align="center">
						母亲单位
					</div>
				</td>
				<td>
					<input type="text" id="mqdw" name="mqdw" maxlength="40"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.mqdw }" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						父亲手机
					</div>
				</td>
				<td>
					<input type="text" id="fqsj" maxlength="18" name="fqsj"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.fqsj }" />
				</td>
				<td>
					<div align="center">
						母亲手机
					</div>
				</td>
				<td>
					<input type="text" id="mqsj" name="mqsj" maxlength="18"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.mqsj }" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<font color="red">*</font>见证人1
					</div>
				</td>
				<td>
					<html:text styleId="jzr1" maxlength="40" property="save_jzr1"
						style="width:100%;heigh:100%" />
				</td>
				<td>
					<div align="center">
						<font color="red">*</font>见证人1身份证号
					</div>
				</td>
				<td>
					<html:text styleId="sfzh1" property="save_sfzh1" maxlength="40"
						onblur="chkSfzh(this);" style="width:100%;heigh:100%" />
				</td>
			</tr>

			<tr>
				<td>
					<div align="center">
						见证人2
					</div>
				</td>
				<td>
					<html:text styleId="jzr2" maxlength="40" property="save_jzr2"
						style="width:100%;heigh:100%" />
				</td>
				<td>
					<div align="center">
						见证人2身份证号
					</div>
				</td>
				<td>
					<html:text styleId="sfzh2" property="save_sfzh2" maxlength="40"
						style="width:100%;heigh:100%" onblur="chkSfzh(this);"
						onkeyup="chkonlynum();" />
				</td>
			</tr>

			<tr>
				<td>
					<div align="center">
						<font color="red">*</font>上学年品德等第
					</div>
				</td>
				<td>
					<html:text styleId="sxnpddd" maxlength="40" property="save_sxnpddd"
						style="width:100%;heigh:100%" />
				</td>
				<td>
					<div align="center">
						<font color="red">*</font>就读期间累计不及格必修课目数
					</div>
				</td>
				<td>
					<html:text styleId="bjgbxkms" property="save_bjgbxkms"
						onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="40"
						style="width:100%;heigh:100%" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						有无违纪处分
				</td>
				<td>
					<input type="text" id="ywwjcf" maxlength="40" name="ywwjcf"
						style="width:100%;heigh:100%" readonly="readonly"
						value="${rs.ywwjcf }">
				</td>
				<td>
					<div align="center">
						毕业时间
					</div>
				</td>
				<td>
					<input type="text" id="bysj" readonly="readonly" name="bysj"
						style="width:100%;heigh:100%" value="${rs.byny}" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						就读期间学习情况总体评价
					</div>
				</td>
				<td>
					优：
					<html:radio property="save_xxqkztpj" value="优" />
					良：
					<html:radio property="save_xxqkztpj" value="良" />
					一般：
					<html:radio property="save_xxqkztpj" value="一般" />
					差：
					<html:radio property="save_xxqkztpj" value="差" />
				</td>
				<td>
					<div align="center">
						有无不良信用记录
					</div>
				</td>
				<td>
					有：
					<html:radio property="save_ywblxyjl" value="有" />
					无：
					<html:radio property="save_ywblxyjl" value="无" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<font color="red">*</font>贷款金额
					</div>
				</td>
				<td>
					<html:text styleId="dkje" maxlength="40" property="save_dkje"
						onkeyup="value=value.replace(/[^\d]/g,'')"
						style="width:100%;heigh:100%" />
				</td>
				<td>
					<div align="center">
						还款方式
					</div>
				</td>
				<td>
					等额本息还款法：
					<html:radio styleId="hkfs" property="save_hkfs" value="等额本息还款法" />
					等额本金还款法：
					<html:radio styleId="hkfs" property="save_hkfs" value="等额本金还款法" />
				</td>
			</tr>

		</table>
		<logic:equal name="isApply" value="true">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2" onClick="yz();">
					提交申请
				</button>
			</div>
		</logic:equal>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
		</script>
	</logic:present>
</body>
</html:html>
