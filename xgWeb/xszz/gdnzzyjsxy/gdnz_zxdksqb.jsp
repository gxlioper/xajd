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
<body>
	<html:form action="lyjszxjsqb.do" method="post">
		<p align="center" style="font-size:24px">
			国家助学贷款
		</p>

		<table width="100%" class="tbstyle" id="theTable">
			<tr>
				<td align="center" width="16%">
					学号
				</td>
				<td align="left" width="34%">
					<bean:write name='rs' property="xh" />
				</td>
				<td width="16%" scope="col">
					<div align="center">
						姓名
					</div>
				</td>
				<td width="34%" scope="col">
					<bean:write name='rs' property="xm" />
				</td>
			</tr>
			<tr>
				<td width="16%" scope="row">
					<div align="center">
						性别
					</div>
				</td>
				<td width="34%">
					<bean:write name='rs' property="xb" />
				</td>
				<td>
					<div align="center">
						年级
					</div>
				</td>
				<td>
					<bean:write name='rs' property="nj" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						身份证号
					</div>
				</td>
				<td>
					<bean:write name='rs' property="sfzh" />
				</td>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td>
					<bean:write name='rs' property="xymc" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						专业
					</div>
				</td>
				<td>
					<bean:write name='rs' property="zymc" />
				</td>
				<td>
					<div align="center">
						班级
					</div>
				</td>
				<td>
					<bean:write name='rs' property="bjmc" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						年度
					</div>
				</td>
				<td>
					<bean:write name='rs' property="nd" />
				</td>
				<td>
					<div align="center">
						申请时间
					</div>
				</td>
				<td>
					<bean:write name='rs' property="sqsj" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						学制
					</div>
				</td>
				<td>
					<bean:write name='rs' property="xz" />
				</td>
				<td>
					<div align="center">
						个人联系电话
					</div>
				</td>
				<td>
					<bean:write name='rs' property="grlxdh" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						邮箱地址
					</div>
				</td>
				<td>
					<bean:write name='rs' property="yxdz" />
				</td>
				<td>
					<div align="center">
						户籍所在地
					</div>
				</td>
				<td>
					<bean:write name='rs' property="hjszd" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						家庭居住地址
					</div>
				</td>
				<td colspan="3">
					<bean:write name='rs' property="jtjzdz" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						邮政编码
					</div>
				</td>
				<td>
					<bean:write name='rs' property="yzbm" />
				</td>
				<td>
					<div align="center">
						家庭联系电话
					</div>
				</td>
				<td>
					<bean:write name='rs' property="jtlxdh" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						家庭人口数
					</div>
				</td>
				<td>
					<bean:write name='rs' property="jtrk" />
				</td>
				<td>
					<div align="center">
						家庭人均月收入
					</div>
				</td>
				<td>
					<bean:write name='rs' property="jtrjysr" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						家庭年总收入
					</div>
				</td>
				<td>
					<bean:write name='rs' property="jtnzsr" />
				</td>
				<td>
					<div align="center">
						家庭所在街道\
						<br />
						村委会联系电话
					</div>
				</td>
				<td>
					<bean:write name='rs' property="jtszjwhdh" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						父亲姓名
					</div>
				</td>
				<td>
					<bean:write name='rs' property="fqxm" />
				</td>
				<td>
					<div align="center">
						母亲姓名
					</div>
				</td>
				<td>
					<bean:write name='rs' property="mqxm" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						父亲身份证号
					</div>
				</td>
				<td>
					<bean:write name='rs' property="fqsfzh" />
				</td>
				<td>
					<div align="center">
						母亲身份证号
					</div>
				</td>
				<td>
					<bean:write name='rs' property="mqsfzh" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						父亲工作单位
					</div>
				</td>
				<td>
					<bean:write name='rs' property="fqgzdw" />
				</td>
				<td>
					<div align="center">
						母亲工作单位
					</div>
				</td>
				<td>
					<bean:write name='rs' property="mqgzdw" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						父亲职业
					</div>
				</td>
				<td>
					<bean:write name='rs' property="fqzy" />
				</td>
				<td>
					<div align="center">
						母亲职业
					</div>
				</td>
				<td>
					<bean:write name='rs' property="mqzy" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						父亲联系电话
					</div>
				</td>
				<td>
					<bean:write name='rs' property="fqlxdh" />
				</td>
				<td>
					<div align="center">
						母亲联系电话
					</div>
				</td>
				<td>
					<bean:write name='rs' property="mqlxdh" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						贷款类型
					</div>
				</td>
				<td>
					<bean:write name='rs' property="dklxmc" />
				</td>
				<td>
					<div align="center">
						贷款金额
					</div>
				</td>
				<td>
					<bean:write name='rs' property="dkje" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						贷款期限
					</div>
				</td>
				<td>
					<bean:write name='rs' property="dkqx" />
				</td>
				<td>
					<div align="center">
						贷款年利率
					</div>
				</td>
				<td>
					<bean:write name='rs' property="nll" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						还款帐户类型
					</div>
				</td>
				<td>
					<bean:write name='rs' property="hkzhlx" />
				</td>
				<td>
					<div align="center">
						还款帐户号码
					</div>
				</td>
				<td>
					<bean:write name='rs' property="hkzhhm" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						合同编号
					</div>
				</td>
				<td>
					<bean:write name='rs' property="htbh" />
				</td>
				<td>
					<div align="center">
						审批表编号
					</div>
				</td>
				<td>
					<bean:write name='rs' property="spbbh" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						备注
					</div>
				</td>
				<td colspan="3">
					<bean:write name='rs' property="bz" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />审核意见
					</div>
				</td>
				<td colspan="3">
					认定为:
					<bean:write name="rs" property="xysh" />
					<br />
					<bean:write name="rs" property="xyshyj" />
					<div align="right">
						签字:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						学校审核意见
					</div>
				</td>
				<td colspan="3">
					认定为:
					<bean:write name="rs" property="xxsh" />
					<br />
					<bean:write name="rs" property="xxshyj" />
					<div align="right">
						签字:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','国家助学贷款')" />
	</div>
</body>
</html:html>
