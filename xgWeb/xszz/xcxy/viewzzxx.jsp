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
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：
			<bean:write name="tips" scope="request" />
		</div>
	</div>
	<html:form action="xcxyXszz.do?method=getDgView" method="post">
		<input type="hidden" name="userType" id="userType"
			value="<bean:write name="userType"/>">
		<input type="hidden" name="xmdm" id="xmdm"
			value="<bean:write name="rs" property="xmdm"/>">
		<input type="hidden" name="temp" id="temp"
			value="<bean:write name="pk"/>">
		<table class="tbstyle" style="width:100%">
			<tr>
				<td align="right" width="16%" colspan="2">
					学号：
				</td>
				<td align="left" width="34%" colspan="2">
					<input type="hidden" name="xh" id="xh" readonly="true"
						value="<bean:write name='rs' property="xh" />" />
					<bean:write name='rs' property="xh" />
				</td>
				<td width="16%">
					<div align="right">
						姓名：
					</div>
				</td>
				<td width="34%">
					<bean:write name="rs" property="xm" />
				</td>
			</tr>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						出生年月：
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="csrq" />
				</td>
				<td>
					<div align="right">
						性别：
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xb" />
				</td>
			</tr>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						入学时间：
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="rxrq" />
				</td>
				<td>
					<div align="right">
						民族：
					</div>
				</td>
				<td>
					<bean:write name="rs" property="mzmc" />
				</td>
			</tr>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						大学：
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="xxmc" />
				</td>
				<td>
					<div align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xymc" />
				</td>
			</tr>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						专业：
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="zymc" />
				</td>
				<td>
					<div align="right">
						班级：
					</div>
				</td>
				<td>
					<bean:write name="rs" property="bjmc" />
				</td>
			</tr>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						学制：
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="xz" />
				</td>
				<td>
					<div align="right">
						政治面貌：
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zzmmmc" />
				</td>
			</tr>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						宿舍编号：
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="ssbh" />
				</td>
				<td>
					<div align="right">
						身份证号：
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sfzh" />
				</td>
			</tr>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						籍贯：
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="jg" />
				</td>
				<td>
					<div align="right">
						学历：
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xl" />
				</td>
			</tr>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						银行卡号：
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="yhkh" />
				</td>
				<td>
					<div align="right">
						一卡通号：
					</div>
				</td>
				<td>
					<bean:write name="rs" property="ykth" />
				</td>
			</tr>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						饭卡号：
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="fkh" />
				</td>
				<td>
					<div align="right">
						联系电话：
					</div>
				</td>
				<td>
					<bean:write name="rs" property="lxdh" />
				</td>
			</tr>
			<logic:notEqual value="国家奖学金" name="rs" property="xmlc">
				<tr height="28">
					<td colspan="2">
						<div align="right">
							家庭户口：
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="hkxz" />
					</td>
					<td>
						<div align="right">
							家庭户口人数：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtzrs" />
					</td>
				</tr>
				<tr height="28">
					<td colspan="2">
						<div align="right">
							家庭月总收入：
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtysr" />
					</td>
					<td>
						<div align="right">
							家庭人均收入：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="rjysr" />
					</td>
				</tr>
				<tr height="28">
					<td colspan="2">
						<div align="right">
							家庭收入来源：
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="srly" />
					</td>
					<td>
						<div align="right">
							邮政编码：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="yzbm" />
					</td>
				</tr>
			</logic:notEqual>
			<tr height="28">
				<td colspan="2">
					<div align="right">
						家庭地址：
					</div>
				</td>
				<td colspan="4">
					<bean:write name="rs" property="jtdz" />
				</td>
			</tr>
			<logic:equal value="国家助学金" name="rs" property="xmlc">
				<tr height="28">
					<td width="4%" rowspan="5">
						<div align="center">
							家
							<br>
							庭
							<br>
							成
							<br>
							员
							<br>
							情
							<br>
							况
						</div>
					</td>
					<td width="12%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="9%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="25%">
						<div align="center">
							与本人关系
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							工作或学习单位
						</div>
					</td>
				</tr>
				<tr height="28">
					<td width="12%">
						<bean:write name="rs" property="jtcy1_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_nl" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_gx" />
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy1_dw" />
					</td>
				</tr>
				<tr height="28">
					<td width="12%">
						<bean:write name="rs" property="jtcy2_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_nl" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_gx" />
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy2_dw" />
					</td>
				</tr>
				<tr height="28">
					<td width="12%">
						<bean:write name="rs" property="jtcy3_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_nl" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_gx" />
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy3_dw" />
					</td>
				</tr>
				<tr height="28">
					<td width="12%">
						<bean:write name="rs" property="jtcy4_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_nl" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_gx" />
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy4_dw" />
					</td>
				</tr>
			</logic:equal>
			<tr>
				<td colspan="2">
					<div align="center">
						曾获何种
					</div>
					<p align="center">
						奖励
						<br>
						<font color="red">(300个字以内)</font>
				</td>
				<td colspan="4">
					<html:textarea name="rs" property="chjl" rows='6'
						style="width:100%" readonly="true" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div style="vertical-align: middle;text-align: right">
						本学年所获资助：
					</div>
				</td>
				<td colspan="4" style="vertical-align: text-top;">
					<logic:iterate id="map" name="xnxszzList">
						<bean:write name="map" property="xn" />学年  获得<bean:write
							name="map" property="mc" />；
						</logic:iterate>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div style="vertical-align: middle;text-align: right">
						考核学年所受处分：
					</div>
				</td>
				<td colspan="4" style="vertical-align: text-top;">
					<logic:iterate id="map" name="xnxswjcfList">
						<bean:write name="map" property="xn" />学年  <bean:write name="map"
							property="cfyymc" />
						<bean:write name="map" property="cflbmc" />；
						</logic:iterate>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<br>
					申请理由
					<br>
					<br>
					<font color="red">(1000个字以内)</font>
				</td>
				<td colspan="4">
					<html:textarea name="rs" property="sqly" rows='6'
						style="width:100%" readonly="true" />
				</td>
			</tr>
			<tr>
				<td colspan="2" style="vertical-align: middle;text-align: right;">
					贫困证明下载：
				</td>
				<td align="left" colspan="4">
					<logic:present name="rs" property="wjsclj">
						<a
							href="/xgxt/xcxyXszz.do?method=DownLoadFile&wjsclj=<bean:write name="rs" property="wjsclj"/>"
							target="_blank"><bean:write name="rs" property="wjmc" /> </a>
					</logic:present>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="right">
						<font color="red"><bean:message key="lable.xsgzyxpzxy" />审核：</font>
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="xysh" />
				</td>
				<td>
					<div align="right">
						<font color="red">学校审核：</font>
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xxsh" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="right">
						<font color="red">资助金额： </font>
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="zzje" />
					&nbsp;元
				</td>
				<td>
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<br>
					<bean:message key="lable.xsgzyxpzxy" />审核意见
					<br>
					<br>
				</td>
				<td colspan="4">
					<textarea name="rs" property="xyshyj" rows='6' style="width:100%"
						readonly="true"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<br>
					学校审核意见
					<br>
					<br>
				</td>
				<td colspan="4">
					<html:textarea name="rs" property="xxshyj" rows='6'
						style="width:100%" readonly="true" />
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
