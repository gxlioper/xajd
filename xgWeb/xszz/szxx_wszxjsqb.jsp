<%@ page language="java" contentType="text/html;charset=GBK"%>

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

	<title><bean:message key="lable.title" />
	</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />
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

<body>
	<html:form action="/szxx_wszxjsqb" method="post">
		<p align="center">
			<strong><bean:write name="rs" property="xxmc"/> </strong><strong>（ </strong><strong>奖
			</strong><strong>） </strong><strong>助学金 </strong>
		</p>
		<p align="center">
			<strong>申请推荐表 </strong>
		</p>
		<p>
			&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专业、年级：
		</p>
		<table  class="tbstyle" width="100%" id="theTable">
			<tr>
				<td width="44" valign="middle">
					<div align="center">
						学号
					</div>
				</td>
				<td width="65" valign="middle">
					<div align="center"><bean:write name="rs" property="xh" /></div>
				</td>
				<td width="44" valign="middle">
					<div align="center">
						性别
					</div>
				</td>
				<td height="31" colspan="2" valign="top">
					<bean:write name="rs" property="xb" />
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						民族
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="mzmc" />
				</td>
				<td width="148">
					<p align="center">
						出生年月
					</p>
				</td>
				<td width="111">
					<bean:write name="rs" property="csny" />
				</td>
			</tr>
			<tr>
				<td width="44" valign="middle">
					<div align="center">
						姓名
					</div>
				</td>
				<td width="65" valign="middle">
					<div align="center"><bean:write name="rs" property="csny" /></div>
				</td>
				<td width="44" valign="middle">
					<div align="center">
						学制
					</div>
				</td>
				<td height="31" colspan="2">
					<bean:write name="rs" property="xz" />
				</td>
				<td colspan="4">
					<p align="left">
						寝室号（及电话）
					</p>
				</td>
				<td width="148">
					<bean:write name="rs" property="qsh" />&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="qsdh" />
				</td>
				<td width="111" rowspan="3">
					<p align="center">
						照片
					</p>
				</td>
			</tr>
			<tr>
				<td width="44" valign="middle">
					<div align="center">
						<br>
						政治
						<br>
						<br>
						面貌
					</div>
				</td>
				<td width="65" valign="middle">
					<bean:write name="rs" property="zzmm" />
				</td>
				<td width="44" valign="middle">
					<div align="center">
						<br>
						籍贯
					</div>
				</td>
				<td colspan="4">
					<bean:write name="rs" property="jg" />
				</td>
				<td colspan="2">
					<div align="center">
						邮编
					</div>
				</td>
				<td>
					<bean:write name="rs" property="yzbm" />
				</td>
			</tr>
			<tr>
				<td width="44" valign="top">
					<p>
						家庭
					</p>
					<p>
						地址
					</p>
				</td>
				<td colspan="9" valign="top">
					<bean:write name="rs" property="jtdz" />
				</td>
			</tr>
			<tr>
				<td width="44" rowspan="7" valign="middle">
					<div align="center">
						家庭
						<br>
						<br>
						主要
						<br>
						<br>
						成员
					</div>
				</td>
				<td width="65" valign="top">
					<p align="center">
						姓 名
					</p>
				</td>
				<td colspan="2" valign="top">
					<p>
						称谓
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						年龄
					</p>
				</td>
				<td colspan="4">
					<p align="center">
						工 作 单 位
					</p>
				</td>
				<td width="111">
					<p align="center">
						月 收 入
					</p>
				</td>
			</tr>
			<tr>
				<td width="65">
					<bean:write name="rs" property="jtcy1_xm" />
				</td>
				<td colspan="2">
					<bean:write name="rs" property="jtcy1_gx" />
				</td>
				<td colspan="2">
					&nbsp;<bean:write name="rs" property="jtcy1_nl" />&nbsp;
				</td>
				<td height="31" colspan="4">
					<bean:write name="rs" property="jtcy1_gzdw" />
				</td>
				<td width="111">
					<bean:write name="rs" property="jtcy1_ysr" />
				</td>
			</tr>
			<tr>
				<td width="65">
					<bean:write name="rs" property="jtcy2_xm" />
				</td>
				<td colspan="2">
					<bean:write name="rs" property="jtcy2_gx" />
				</td>
				<td colspan="2">
					&nbsp;<bean:write name="rs" property="jtcy2_nl" />&nbsp;
				</td>
				<td height="31" colspan="4">
					<bean:write name="rs" property="jtcy2_gzdw" />
				</td>
				<td width="111">
					<bean:write name="rs" property="jtcy2_ysr" />
				</td>
			</tr>
			<tr>
				<td width="65">
					<bean:write name="rs" property="jtcy3_xm" />
				</td>
				<td colspan="2">
					<bean:write name="rs" property="jtcy3_gx" />
				</td>
				<td colspan="2">
					&nbsp;<bean:write name="rs" property="jtcy3_nl" />&nbsp;
				</td>
				<td height="31" colspan="4">
					<bean:write name="rs" property="jtcy3_gzdw" />
				</td>
				<td width="111">
					<bean:write name="rs" property="jtcy3_ysr" />
				</td>
			</tr>
			<tr>
				<td width="65">
					<bean:write name="rs" property="jtcy4_xm" />
				</td>
				<td colspan="2">
					<bean:write name="rs" property="jtcy4_gx" />
				</td>
				<td colspan="2">
					&nbsp;<bean:write name="rs" property="jtcy4_nl" />&nbsp;
				</td>
				<td height="31" colspan="4">
					<bean:write name="rs" property="jtcy4_gzdw" />
				</td>
				<td width="111">
					<bean:write name="rs" property="jtcy4_ysr" />
				</td>
			</tr>
			<tr>
				<td width="65">
					<bean:write name="rs" property="jtcy5_xm" />
				</td>
				<td colspan="2">
					<bean:write name="rs" property="jtcy5_gx" />
				</td>
				<td colspan="2">
					&nbsp;<bean:write name="rs" property="jtcy5_nl" />&nbsp;
				</td>
				<td height="31" colspan="4">
					<bean:write name="rs" property="jtcy5_gzdw" />
				</td>
				<td width="111">
					<bean:write name="rs" property="jtcy5_ysr" />
				</td>
			</tr>
			<tr>
				<td width="44">
					<div align="center">
						担任
						<br>
						<br>
						社会
						<br>
						<br>
						工作
						<br>
						<br>
						情况
					</div>
				</td>
				<td colspan="10">
					<bean:write name="rs" property="drshgzqk" />
				</td>
			</tr>
			<tr>
				<td width="44">
					<div align="center">
						奖惩
						<br>
						<br>
						情况
					</div>
				</td>
				<td colspan="10">
					<bean:write name="rs" property="jcqk" />
				</td>
			</tr>
			<tr>
				<td colspan="11" valign="top">
					<p>
						申请资助理由 （包括家庭经济情况，学费来源，个人品行、 学习情况 ）：
					</p>
					<bean:write name="rs" property="sqzzly" />
					<p>
						&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
					</p>
					<p>
						&nbsp;
					</p>
				</td>
			</tr>
		</table>
		<br>
		<table cellspacing="0" cellpadding="0" border="1" width="100%">

			<tr>
				<td width="43">
					<div align="center">
						<br>
						本
						<br>
						人
						<br>
						承
						<br>
						诺
					</div>
				</td>
				<td width="525">
					<p>
						&nbsp;
					</p>
					<p>
						本人愿意加入爱心社团，并积极参加各类社会公益活动，以实际行动回报社会
					</p>
					<p align="left">
						&nbsp;
					</p>
					<p align="left">
						学生本人签名：
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
			<tr>
				<td width="43">
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
				<td width="525">
					<p>
						<strong>（品行表现、家庭经济情况和其他推荐意见） </strong>
					</p>
					<p>
						<bean:write name="rs"  property="xyshyj" />
					</p>
					<p>
						负责人：（盖章）
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
			<tr>
				<td width="43">
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
				<td width="525" valign="bottom">
					<p>
					<p>
					<bean:write name="rs"  property="xxshyj" />
					<p>
						负责人：（盖章）
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
		</table>
		<p>
			本表一式二份（捐资单位 &lt; 个人 &gt; 、学生处各存一份）。
		</p>
	</html:form>
	<div align="center">
		<input  value="打印" onclick="expTab('theTable','深圳信息(奖)助学金申请推荐表')">
	</div>
</body>
</html:html>
