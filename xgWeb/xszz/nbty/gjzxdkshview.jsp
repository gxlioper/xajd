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
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
</head>
<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：助学贷款 - 国家助学贷款审核
		</div>
	</div>
	<html:form action="/nbty_xszz.do?method=gjzxdksq" method="post">
		<input type="hidden" id="url" name="url"
			value="/nbty_xszz.do?method=gjzxdksq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc" />
		<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }" />
		<table class="tbstyle" width="90%">
			<tr>

				<td align="center" width="16%">
					学号
				</td>
				<td align="left" width="34%">
					<html:text styleId="xh" property="xh" style="width:100%;heigh:100%"
						value="${rs.xh}" readonly="true" />
				</td>
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
					<input type="text" readonly="readonly"
						style="cursor:hand;width:100%" value="${rs.csrq}" name="csrq"
						id="csrq" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						学年
					</div>
				</td>
				<td>
					<input type="text" id="xn" readonly="readonly" name="xn"
						style="width:100%;heigh:100%" value="${rs.xn}" />
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
					<input type="text" id="lxdh" name="lxdh" readonly="readonly"
						maxlength="20" style="width:100%;heigh:100%" value="${rs.lxdh }" />
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
					<input type="text" id="qsdh" name="qsdh" readonly="readonly"
						maxlength="20" style="width:100%;heigh:100%" value="${rs.qsdh }" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						家庭详细地址
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="jtdz" name="jtdz" readonly="readonly"
						maxlength="200" style="width:100%;heigh:100%" value="${rs.jtszd }" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						家庭邮编
					</div>
				</td>
				<td>
					<input type="text" id="jtyb" maxlength="6" readonly="readonly"
						name="jtyb" style="width:100%;heigh:100%" value="${rs.jtyb }" />
				</td>
				<td>
					<div align="center">
						家庭电话
					</div>
				</td>
				<td>
					<input type="text" id="jtdh" name="jtdh" readonly="readonly"
						maxlength="20" style="width:100%;heigh:100%" value="${rs.lxdh1 }" />
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
					<input type="text" id="fqxm" maxlength="40" readonly="readonly"
						name="fqxm" style="width:100%;heigh:100%" value="${rs.fqxm }">
				</td>
				<td>
					<div align="center">
						母亲姓名
					</div>
				</td>
				<td>
					<input type="text" id="mqxm" name="mqxm" readonly="readonly"
						maxlength="40" style="width:100%;heigh:100%" value="${rs.mqxm }" />
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
					<input type="text" id="mqsj" name="mqsj" maxlength="40"
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
					<input type="text" id="jzr1" name="jzr1" maxlength="40"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.jzr1 }" />
				</td>
				<td>
					<div align="center">
						<font color="red">*</font>见证人1身份证号
					</div>
				</td>
				<td>
					<input type="text" id="sfzh1" name="sfzh1" maxlength="40"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.sfzh1 }" />
				</td>
			</tr>

			<tr>
				<td>
					<div align="center">
						见证人2
					</div>
				</td>
				<td>
					<input type="text" id="jzr2" maxlength="40" name="jzr2"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.jzr2}" />
				</td>
				<td>
					<div align="center">
						见证人2身份证号
					</div>
				</td>
				<td>
					<input type="text" id="sfzh2" maxlength="40" name="sfzh2"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.sfzh2}" />
				</td>
			</tr>

			<tr>
				<td>
					<div align="center">
						<font color="red">*</font>上学年品德得分
					</div>
				</td>
				<td>
					<input type="text" id="sxnpddf" maxlength="40" name="sxnpddf"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.sxnpddd}" />
				</td>
				<td>
					<div align="center">
						就读期间累计不及格必修课目数
					</div>
				</td>
				<td>
					<input type="text" id="bjgbxkms" maxlength="40" name="bjgbxkms"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.bjgbxkms}" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						有无违纪处分
				</td>
				<td>
					<input type="text" id="ywwjcf" maxlength="40" name="ywwjcf"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.ywwjcf }" />
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
					<input type="text" id="xxqkztpj" name="xxqkztpj" maxlength="40"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.xxqkztpj }" />
				</td>
				<td>
					<div align="center">
						有无不良信用记录
					</div>
				</td>
				<td>
					<input type="text" id="ywblxyjl" name="ywblxyjl" maxlength="40"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.ywblxyjl }" />
			</tr>
			<tr>
				<td>
					<div align="center">
						贷款金额
					</div>
				</td>
				<td>
					<input type="text" id="dkje" name="dkje" maxlength="40"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.dkje }" />
				</td>
				<td>
					<div align="center">
						还款方式
					</div>
				</td>
				<td>
					<input type="text" id="hkfs" name="hkfs" maxlength="40"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.hkfs }" />
				</td>
			</tr>
			<tr align="left">
				<td align="right">
					辅导员审核：
				</td>
				<td>


					<input type="text" id="fdysh" name="fdysh" maxlength="40"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.fdysh }" />

				</td>
				<td>
					<div align="right">
						<bean:message key="lable.xsgzyxpzxy" />审核
					</div>
				</td>
				<td>


					<input type="text" id="xysh" name="xysh" maxlength="40"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.xysh }" />

				</td>
			</tr>
			<tr align="left">
				<td align="right">
					学校审核：
				</td>
				<td>
					<input type="text" id="fdysh" name="xxsh" maxlength="40"
						readonly="readonly" style="width:100%;heigh:100%"
						value="${rs.xxsh }" />
				</td>
			</tr>
			<tr align="left">
				<td align="right">
					辅导员评语：
				</td>
				<td colspan="3">
					<html:textarea name='rs' property='save_fdypy' style="width:99%"
						rows='5' value="${rs.fdypy}" readonly="true"
						onblur="chLeng(this,250)" />
				</td>
			</tr>
			<tr align="left">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />审核意见：
				</td>
				<td colspan="3">
					<html:textarea name='rs' property='save_xyshyj' style="width:99%"
						rows='5' readonly="true" value="${rs.xyshyj}"
						onblur="chLeng(this,250)" />
				</td>
			</tr>
			<tr align="left">
				<td align="right">
					学校审核意见：
				</td>
				<td colspan="3">
					<html:textarea name='rs' property='save_xxshyj' style="width:99%"
						rows='5' readonly="true" value="${rs.xxshyj}"
						onblur="chLeng(this,250)" />
				</td>
			</tr>

		</table>
		<div class="buttontool" align="center">
			<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
				id="buttonClose">
				关 闭
			</button>
		</div>
	</html:form>
</body>
</html:html>
