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
<base target="_self">
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="正方软件 zfsoft" />
</head>
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
<body>
	<html:form action="lyjszxjsqb.do" method="post">
		<p align="center">
			中国银行
			<bean:write name="rs" property="zhmc" />
		</p>
		<div align="center">
			<p>
				国家助学贷款 毕业生（借款人）资料确认书
			</p>

			<table width="100%" class="tbstyle" id="theTable">
				<tr>
					<td colspan="7" scope="col">
						<div align="left">
							<strong> 借款人基本资料 </strong>
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row" width="13%">
						<div align="right">
							借款人姓名：
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xm" />
					</td>
					<td width="13%">
						<div align="right">
							性别：
						</div>
					</td>
					<td width="17%">
						<bean:write name="rs" property="xb" />
					</td>
					<td width="13%">
						<div align="right">
							出生日期：
						</div>
					</td>
					<td width="17%">
						<bean:write name="rs" property="csrq" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							毕业学校：
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
					<td>
						<div align="right">
							系：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xmc" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							身份证号码：
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sfzh" />
					</td>
					<td>
						<div align="right">
							学历：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xl" />
					</td>
					<td>
						<div align="right">
							家庭联系电话：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtlxdh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							家庭住址：
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtxxzz" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							婚姻状况：
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="hyzk" />
					</td>
					<td>
						<div align="right">
							配偶姓名：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="poxm" />
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
				<tr>
					<td scope="row">
						<div align="right">
							工作单位：
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="gzdw" />
					</td>
					<td>
						<div align="right">
							单位电话：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="dwdh" />
					</td>
					<td>
						<div align="right">
							邮政编码：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="dwyzbm" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							单位地址：
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="dwdz" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							移动电话：
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="yddh" />
					</td>
					<td>
						<div align="right">
							E-MAIL地址：
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="email" />
					</td>
				</tr>
				<tr>
					<td colspan="7" scope="row">
						<div align="left">
							<strong> 联系人基本资料 </strong>
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							联系人姓名：
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="lxrxm" />
					</td>
					<td>
						<div align="right">
							性别：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="lxrxb" />
					</td>
					<td>
						<div align="right">
							出生日期：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="lxrcsrq" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							与借款人关系：
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="lxrgx" />
					</td>
					<td>
						<div align="right">
							联系电话：
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="lxrdh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							单位 /地址：
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="lxrdwdz" />
					</td>
				</tr>
				<tr>
					<td colspan="4" scope="row">
						<div align="left">
							<strong> 家庭基本情况 </strong>
						</div>
					</td>
					<td colspan="3" rowspan="7">
						<br>
						<br>
						<p>
							&nbsp;&nbsp;&nbsp;&nbsp;本人在此承诺所填资料真实无误，如有变动，本人承诺在变动后一周内将变动资料邮寄至中国银行
							分/支行。如提供虚假资料或未能及时寄送变更资料，贵行有权认定本人违约，并可按照《中国银行国家助学贷款借款合同》中的相关约定追究本人的违约责任。
						</p>
						<p align="right">
							承诺人(签章)：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
						</p>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							家庭详细住址：
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtxxzz" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							电话：
						</div>
					</td>
					<td width="19%">
						<bean:write name="rs" property="jtlxdh" />
					</td>
					<td width="11%">
						<div align="right">
							邮编：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtyzbm" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							父亲姓名：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="fqxm" />
					</td>
					<td>
						<div align="right">
							职业：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="fqzy" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							父亲身份证：
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="fqsfzh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							母亲姓名：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mqxm" />
					</td>
					<td>
						<div align="right">
							职业：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mqzy" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							母亲身份证：
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="mqsfzh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							备注：
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="bz" />
					</td>
				</tr>
			</table>
		</div>
	</html:form>
	<div align=center>
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','中国银行上海市分行国家助学贷款毕业生（借款人）资料确认书')" />
	</div>
</body>
</html:html>
