<%@ page language="java" 	contentType="text/html;charset=GBK"%>

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
<head>
	<base target="_self">
	<title><bean:message key="lable.title" />
	</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Expires", "0");
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
</head>
<style media="print">
.brk{
page-break-after:always;
}
</style>
<body>
	<html:form action="bzsqb.do" method="post">
	<input type="hidden" name="bzlb" id="bzlb" value="<bean:write name="bzlb" />">

		<div align="center">
			<p>
				<strong><bean:write name="rs" property="xxmc"/>家庭经济困难学生资助申请表</strong>
			</p>
		</div>
		<p>
			<strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;校区
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专业
			</strong>
		</p>
		<table id="theTable" class="tbstyle" width="100%">
			<tr>
				<td height="29" colspan="2">
					<p align="center">
						学号
					</p>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name="rs" property="xh" />
					</div>
				</td>
				<td width="28">
					<p align="center">
						姓名
					</p>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name="rs" property="xm" />
					</div>
				</td>
				<td width="51">
					<p align="center">
						性別
					</p>
				</td>
				<td width="65">
					<div align="center"><bean:write name="rs" property="xb" /></div>
				</td>
				<td colspan="2">
					<p align="center">
						民族
					</p>
				</td>
				<td width="61">
					<div align="center"><bean:write name="rs" property="mzmc" /></div>
				</td>
				<td colspan="4">
					<p align="center">
						毕业时间
					</p>
				</td>
				<td width="98" valign="top">
					<bean:write name="rs" property="bysj" />
				</td>
			</tr>
			<tr>
				<td height="29" colspan="2">
					<p align="center">
						专业年级
					</p>
				</td>
				<td colspan="4">
					<bean:write name="rs" property="zynj" />
				</td>
				<td colspan="2">
					<p align="center">
						寝室号码
					</p>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="qsh" />
				</td>
				<td colspan="4">
					<p align="center">
						寝室电话
					</p>
				</td>
				<td colspan="2" valign="top">
					<bean:write name="rs" property="qsdh" />
				</td>
			</tr>
			<tr>
				<td height="30" colspan="2">
					<p align="center">
						家庭地址
					</p>
				</td>
				<td colspan="4">
					<bean:write name="rs" property="jtdz" />
				</td>
				<td colspan="2">
					<p align="center">
						邮编
					</p>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="yzbm" />
				</td>
				<td colspan="3">
					<p align="center">
						身份证号码
					</p>
				</td>
				<td colspan="4" valign="top">
					<bean:write name="rs" property="sfzh" />
				</td>
			</tr>
			<tr>
				<td width="52" rowspan="6">
					<div align="center">
						<br>
						家
						<br>
						庭
						<br>
						成
						<br>
						员
					</div>
				</td>
				<td height="31" colspan="2">
					<p align="center">
						姓 名
					</p>
				</td>
				<td colspan="3">
					<p align="center">
						称 谓
					</p>
				</td>
				<td colspan="8">
					<p align="center">
						工 作 单 位
					</p>
				</td>
				<td colspan="3">
					<p align="center">
						月 收 入
					</p>
				</td>
			</tr>
			<tr>
				<td height="30" colspan="2" valign="top">
					<bean:write name="rs" property="jtcy1_xm" />
				</td>
				<td colspan="3" valign="top">
					<bean:write name="rs" property="jtcy1_gx" />
				</td>
				<td colspan="8" valign="top">
					<bean:write name="rs" property="jtcy1_gzdw" />
				</td>
				<td colspan="3" valign="top">
					&nbsp;<bean:write name="rs" property="jtcy1_ysr" />&nbsp;
				</td>
			</tr>
			<tr>
				<td height="31" colspan="2" valign="top">
					<bean:write name="rs" property="jtcy2_xm" />
				</td>
				<td colspan="3" valign="top">
					<bean:write name="rs" property="jtcy2_gx" />
				</td>
				<td colspan="8" valign="top">
					<bean:write name="rs" property="jtcy2_gzdw" />
				</td>
				<td colspan="3" valign="top">
					&nbsp;<bean:write name="rs" property="jtcy2_ysr" />&nbsp;
				</td>
			</tr>
			<tr>
				<td height="29" colspan="2" valign="top">
					<bean:write name="rs" property="jtcy3_xm" />
				</td>
				<td colspan="3" valign="top">
					<bean:write name="rs" property="jtcy3_gx" />
				</td>
				<td colspan="8" valign="top">
					<bean:write name="rs" property="jtcy3_gzdw" />
				</td>
				<td colspan="3" valign="top">
					&nbsp;<bean:write name="rs" property="jtcy3_ysr" />&nbsp;
				</td>
			</tr>
			<tr>
				<td height="28" colspan="2" valign="top">
					<bean:write name="rs" property="jtcy4_xm" />
				</td>
				<td colspan="3" valign="top">
					<bean:write name="rs" property="jtcy4_gx" />
				</td>
				<td colspan="8" valign="top">
					<bean:write name="rs" property="jtcy4_gzdw" />
				</td>
				<td colspan="3" valign="top">
					&nbsp;<bean:write name="rs" property="jtcy4_ysr" />&nbsp;
				</td>
			</tr>
			<tr>
				<td colspan="2" valign="top">
					<bean:write name="rs" property="jtcy5_xm" />
				</td>
				<td height="31" colspan="3" valign="top">
					<bean:write name="rs" property="jtcy5_gx" />
				</td>
				<td colspan="8" valign="top">
					<bean:write name="rs" property="jtcy5_gzdw" />
				</td>
				<td colspan="3" valign="top">
					&nbsp;<bean:write name="rs" property="jtcy5_ysr" />&nbsp;
				</td>
			</tr>
			<tr>
				<td width="52">
					<div align="center">
						<br>
						申
						<br>
						请
						<br>
						人
						<br>
						经
						<br>
						济
						<br>
						情
						<br>
						况
					</div>
				</td>
				<td colspan="16">
					<p>
						本学年
					</p>
					<p align="center">
						家庭提供 <u>&nbsp;<bean:write  name="rs" property="jttgje"/> &nbsp;</u>元 /月 &nbsp;&nbsp;&nbsp;助 学 金<u>&nbsp; <bean:write  name="rs" property="zxje"/>&nbsp;</u>元
					</p>
					<p align="center">
						奖学金<u>&nbsp; <bean:write  name="rs" property="jxje"/>&nbsp;</u>元 &nbsp;&nbsp;勤工助学收入<u>&nbsp;<bean:write  name="rs" property="qgzxje"/>&nbsp;</u>元
					</p>
					<p align="center">
						校内无息贷学金<u>&nbsp;<bean:write  name="rs" property="xnwxdkje"/>&nbsp;</u>元 &nbsp;&nbsp;&nbsp;&nbsp;其 他 收 入<u>&nbsp;<bean:write  name="rs" property="qtsrje"/>&nbsp;</u>元
					</p>
					<p align="center">
						助学贷款：申请金额、时间<u>&nbsp;<bean:write  name="rs" property="zxdkje"/>&nbsp;&nbsp;<bean:write name="rs" property="zxdksj" />&nbsp; </u>已发放金额、时间<u>&nbsp;<bean:write name="rs" property="yffzxdkje"/>&nbsp;&nbsp;<bean:write name="rs" property="yffzxdksj" /></u>
					</p>
				</td>
			</tr>
			<tr>
				<td width="52">
					<div align="center">
						<br>
						申
						<br>
						请
						<br>
						资
						<br>
						助
						<br>
						理
						<br>
						由
					</div>
				</td>
				<td colspan="16" valign="top">
					<bean:write name="rs" property="sqzzly" />
				</td>
			</tr>
		</table>
		<div class="brk">
		<table cellspacing="0" cellpadding="0" class="tbstyle" width="100%">
			<tr>
				<td width="46">
					<div align="center">
						<br>
						本
						<br>
						人
						<br>
						意
						<br>
						愿
					</div>
				</td>
				<td width="508" valign="top">
					<p>
						&nbsp;
					</p>
					<p>
						(资助方法： ①学校贷学金 ②学费补助 ③临时困难补助)
					</p>
					<logic:equal value="xfbz" name="bzlb">
					<p>
						第一志愿：<u>&nbsp;&nbsp;②&nbsp;&nbsp;</u>金额&nbsp;<u>&nbsp;<bean:write name="rs" property="zzff1qsje" />&nbsp;&nbsp;</u>元至&nbsp;<u>&nbsp;<bean:write name="rs" property="zzff1jsje" />&nbsp;&nbsp;</u>元
					</p>
					</logic:equal>
					<logic:equal value="lsknbz" name="bzlb">
					<p>
						第一志愿：<u>&nbsp;&nbsp;③&nbsp;&nbsp;</u>金额&nbsp;<u>&nbsp;<bean:write name="rs" property="zzff1qsje" />&nbsp;&nbsp;</u>元至&nbsp;<u>&nbsp;<bean:write name="rs" property="zzff1jsje" />&nbsp;&nbsp;</u>元
					</p>
					</logic:equal>
					<logic:equal value="xndxj" name="bzlb">
					<p>
						第一志愿：<u>&nbsp;&nbsp;①&nbsp;&nbsp;</u>金额&nbsp;<u>&nbsp;<bean:write name="rs" property="zzff1qsje" />&nbsp;&nbsp;</u>元至&nbsp;<u>&nbsp;<bean:write name="rs" property="zzff1jsje" />&nbsp;&nbsp;</u>元
					</p>
					</logic:equal>
					<p>
						&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学生本人签名：＿＿＿＿＿＿＿＿＿＿＿＿
					</p>
					<p align="right">
						年 &nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
			<tr>
				<td width="46">
					<div align="center">
						<br>
						贷
						<br>
						学
						<br>
						金
						<br>
						还
						<br>
						款
						<br>
						计
						<br>
						划
						<br>
						：
					</div>
				</td>
				<td width="508" valign="top">
					<p align="left">
						&nbsp;
					</p>
					<p align="left">
						□毕业前一次性还款
					</p>
					<p align="left">
						□毕业后＿＿＿＿＿＿年内还清
					</p>
				</td>
			</tr>
			<tr>
				<td width="46">
					<div align="center">
						<br>
						学
						<br>
						院
						<br>
						意
						<br>
						见
					</div>
				</td>
				<td width="508" valign="top">
					<p>
						&nbsp;
					</p>
					<p>
						同意该同学申请
					</p>
					<p align="center">
						贷 学 金 ＿＿＿＿＿＿元
					</p>
					<p align="center">
						学 费 补 助＿＿＿＿＿＿元
					</p>
					<p align="center">
						临时困难补助＿＿＿＿＿＿元
					</p>
					<p align="center">
						其 他＿＿＿＿＿＿
					</p>
					<p align="right">
						负责人（公章）：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
			<tr>
				<td width="46">
					<div align="center">
						<br>
						学
						<br>
						生
						<br>
						工
						<br>
						作
						<br>
						处
						<br>
						意
						<br>
						见
					</div>
				</td>
				<td width="508" valign="top">
					<p>
						&nbsp;
					</p>
					<p>
						审核同意：
					</p>
					<p align="center">
						贷 学 金 ＿＿＿＿＿＿元
					</p>
					<p align="center">
						学 费 补 助＿＿＿＿＿＿元
					</p>
					<p align="center">
						其 他＿＿＿＿＿＿
					</p>
					<p align="right">
						审核人（公章）：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
		</table>
		<p align="left">
			申请学校贷学金和学费补助的，填写本表一式两份：由<bean:message key="lable.xsgzyxpzxy" />和学生处各留一份。申请临时困难补助的，填写本表一式一份，由<bean:message key="lable.xsgzyxpzxy" />留存。
		</p>
		</div>
	</html:form>
	<div align=center>
		<input  value="打印" onclick="expTab('theTable','浙江大学家庭经济困难学生资助申请表')"/>
	</div>
</body>
</html:html>
