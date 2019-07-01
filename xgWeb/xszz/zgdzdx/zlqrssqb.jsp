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
	<title><bean:message key="lable.title" /></title>
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
<script language="javascript">

	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable" height="90%">
			<tr>
				<td scope="col">
					<div align="center">
						<strong>中国银行湖北省分行</strong>
					</div>
					<div align="center">
						<strong>国家助学贷款毕业生(借款人)资料确认书</strong>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					&nbsp;<br />&nbsp;
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle" height="60%">
						<tr>
							<td colspan="3">
								<strong>借款人基本资料</strong>
							</td>
						</tr>
						<tr>
							<td width="40%">
								借款人姓名&nbsp;
								<bean:write name="rs" property="xm" />
							</td>
							<td width="30%">
								性别&nbsp;&nbsp;
								<logic:empty name="rs" property="xh">
								□&nbsp;男&nbsp;&nbsp;□&nbsp;女
								</logic:empty>
								<logic:notEmpty name="rs" property="xh">
									<logic:equal name="rs" property="xb" value="男">
									√
									</logic:equal>
									<logic:notEqual name="rs" property="xb" value="男">
									□
									</logic:notEqual>
									&nbsp;男&nbsp;&nbsp;
									<logic:equal name="rs" property="xb" value="女">
									√
									</logic:equal>
									<logic:notEqual name="rs" property="xb" value="女">
									□
									</logic:notEqual>
									&nbsp;女
								</logic:notEmpty>
							</td>
							<td width="30%">
								出生日期&nbsp;&nbsp;
								<logic:equal name="rs" property="csrqYear" value="">
								&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:equal>
								<logic:notEqual name="rs" property="csrqYear" value="">
								<bean:write name="rs" property="csrqYear" />
								</logic:notEqual>
								年
								<logic:equal name="rs" property="csrqMon" value="">
								&nbsp;&nbsp;
								</logic:equal>
								<logic:notEqual name="rs" property="csrqMon" value="">
								<bean:write name="rs" property="csrqMon" />
								</logic:notEqual>
								月
								<logic:equal name="rs" property="csrqDay" value="">
								&nbsp;&nbsp;
								</logic:equal>
								<logic:notEqual name="rs" property="csrqDay" value="">
								<bean:write name="rs" property="csrqDay" />
								</logic:notEqual>
								日
							</td>
						</tr>
						<tr>
							<td>
								毕业学校&nbsp;
								中国地质大学(武汉)
							</td>
							<td>
								身份证号码&nbsp;
								<bean:write name="rs" property="sfzh" />
							</td>
							<td>
								学历&nbsp;
								<bean:write name="rs" property="xl" />
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />&nbsp;
								<bean:write name="rs" property="xymc" />
							</td>
							<td>
								行政班号&nbsp;
								<bean:write name="rs" property="bjdm" />
							</td>
							<td>
								学号&nbsp;
								<bean:write name="rs" property="xh" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								家庭住址&nbsp;
								<bean:write name="rs" property="jtxxzz" />
							</td>
							<td>
								家庭联系电话&nbsp;
								<bean:write name="rs" property="jtdh" />
							</td>
						</tr>
						<tr>
							<td>
								婚姻状况&nbsp;
								<logic:notEmpty name="rs" property="xh">
									<bean:write name="rs" property="hyzk" />
								</logic:notEmpty>
							</td>
							<td>
								配偶姓名&nbsp;
								<bean:write name="rs" property="pomc" />
							</td>
							<td>
								联系电话&nbsp;
								<bean:write name="rs" property="polxdh" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								单位地址&nbsp;
								<bean:write name="rs" property="dwdz" />
							</td>
							<td>
								民族&nbsp;
								<bean:write name="rs" property="mzmc" />
							</td>
						</tr>
						<tr>
							<td>
								工作单位&nbsp;
								<bean:write name="rs" property="gzdw" />
							</td>
							<td>
								单位电话&nbsp;
								<bean:write name="rs" property="dwdh" />
							</td>
							<td>
								单位邮编&nbsp;
								<bean:write name="rs" property="dwyb" />
							</td>
						</tr>
						<tr>
							<td>
								借款合同编号&nbsp;
								<bean:write name="rs" property="htbh" />
							</td>
							<td>
								Email及QQ&nbsp;
								<bean:write name="rs" property="emailqq" />
							</td>
							<td>
								移动电话&nbsp;
								<bean:write name="rs" property="yddh" />
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<strong>联系人基本资料</strong>
							</td>
						</tr>
						<tr>
							<td>
								联系人姓名&nbsp;
								<bean:write name="rs" property="lxrxm" />
							</td>
							<td>
								性别&nbsp;&nbsp;
								<logic:empty name="rs" property="lxrxm">
								□&nbsp;男&nbsp;&nbsp;□&nbsp;女
								</logic:empty>
								<logic:notEmpty name="rs" property="lxrxm">
									<logic:equal name="rs" property="lxrxb" value="男">
									√
									</logic:equal>
									<logic:notEqual name="rs" property="lxrxb" value="男">
									□
									</logic:notEqual>
									&nbsp;男&nbsp;&nbsp;
									<logic:equal name="rs" property="lxrxb" value="女">
									√
									</logic:equal>
									<logic:notEqual name="rs" property="lxrxb" value="女">
									□
									</logic:notEqual>
									&nbsp;女
								</logic:notEmpty>
							</td>
							<td>
								出生日期&nbsp;&nbsp;
								<logic:equal name="rs" property="lxrcsrqYear" value="">
								&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:equal>
								<logic:notEqual name="rs" property="lxrcsrqYear" value="">
								<bean:write name="rs" property="lxrcsrqYear" />
								</logic:notEqual>
								年
								<logic:equal name="rs" property="lxrcsrqMon" value="">
								&nbsp;&nbsp;
								</logic:equal>
								<logic:notEqual name="rs" property="lxrcsrqMon" value="">
								<bean:write name="rs" property="lxrcsrqMon" />
								</logic:notEqual>
								月
								<logic:equal name="rs" property="lxrcsrqDay" value="">
								&nbsp;&nbsp;
								</logic:equal>
								<logic:notEqual name="rs" property="lxrcsrqDay" value="">
								<bean:write name="rs" property="lxrcsrqDay" />
								</logic:notEqual>
								日
							</td>
						</tr>
						<tr>
							<td>
								与借款人关系&nbsp;
								<bean:write name="rs" property="lxrgx" />
							</td>
							<td colspan="2">
								联系电话&nbsp;
								<bean:write name="rs" property="lxrlxdh" />
							</td>
						</tr>
						<tr>
							<td colspan="3">
								单位/地址&nbsp;
								<bean:write name="rs" property="lxrdwdz" />
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td width="50%">
								<strong>家庭基本情况</strong>
								<br />
								家庭详细住址：&nbsp;
								<bean:write name="rs" property="jtxxzz" />
								<br />
								邮编：&nbsp;
								<bean:write name="rs" property="jtyb" />
								<br />
								电话：&nbsp;
								<bean:write name="rs" property="jtdh" />
								<br />
								父亲姓名：&nbsp;
								<logic:empty name="rs" property="fqxm" >
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="fqxm" >
								<bean:write name="rs" property="fqxm" />
								</logic:notEmpty>
								&nbsp;职业：&nbsp;
								<bean:write name="rs" property="fqzy" />
								<br />
								父亲身份证号码：&nbsp;
								<bean:write name="rs" property="fqsfzh" />
								<br />
								母亲姓名：&nbsp;
								<logic:empty name="rs" property="mqxm" >
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="mqxm" >
								<bean:write name="rs" property="mqxm" />
								</logic:notEmpty>
								&nbsp;职业：&nbsp;
								<bean:write name="rs" property="mqzy" />
								<br />
								母亲身份证号码：&nbsp;
								<bean:write name="rs" property="mqsfzh" />
								<br />
							</td>
							<td width="50%">
								&nbsp;&nbsp;&nbsp;&nbsp;本人在此承诺所填资料真实无误，如有变动，本人承诺在变动都一周内将变动资料邮寄至中国银行&nbsp;&nbsp;
								<strong>东湖开发区</strong>&nbsp;分/支行。如提供虚假资料或未能及时寄送变更资料，贵行有权认定本人违约，并可按照《中国银行国家助学贷款借款合同》中的相关约定追究本人的违约责任。
								<br />
								<br />
								<br />
								<div align="center">
									承诺人(签章)：
								</div>
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								备注:<br />
								<logic:empty name="rs" property="bz" >
								<br /><br />
								</logic:empty>
								<logic:notEmpty name="rs" property="bz" >
								&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="bz" />
								</logic:notEmpty>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
	</div>
</body>
</html:html>
