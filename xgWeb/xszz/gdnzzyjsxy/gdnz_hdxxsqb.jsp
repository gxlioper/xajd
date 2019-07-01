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
		<p align="center" style="font-size:24px">
			还贷基本信息
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
						工作单位名称
					</div>
				</td>
				<td>
					<bean:write name='rs' property="gzdwmc" />
				</td>
				<td>
					<div align="center">
						工作单位邮编
					</div>
				</td>
				<td>
					<bean:write name='rs' property="gzdwyb" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						工作单位地址
					</div>
				</td>
				<td>
					<bean:write name='rs' property="gzdwdz" />
				</td>
				<td>
					<div align="center">
						工作单位电话
					</div>
				</td>
				<td>
					<bean:write name='rs' property="dwdh" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						合同号
					</div>
				</td>
				<td>
					<bean:write name='rs' property="hth" />
				</td>
				<td>
					<div align="center">
						合同金额
					</div>
				</td>
				<td>
					<bean:write name='rs' property="htje" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						合同总金额
					</div>
				</td>
				<td>
					<bean:write name='rs' property="htzje" />
				</td>
				<td>
					<div align="center">
						贷款期限
					</div>
				</td>
				<td>
					<bean:write name='rs' property="dkqx" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						自付息具体日期
					</div>
				</td>
				<td>
					<bean:write name='rs' property="zfxjtrq" />
				</td>
				<td>
					<div align="center">
						贷款年利率
					</div>
				</td>
				<td>
					<bean:write name='rs' property="nll" />
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
						还款时间
					</div>
				</td>
				<td>
					<bean:write name='rs' property="hksj" />
				</td>
				<td>
					<div align="center">
						已还款金额
					</div>
				</td>
				<td>
					<bean:write name='rs' property="yhkje" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						逾期期数
					</div>
				</td>
				<td>
					<bean:write name='rs' property="yqqs" />
				</td>
				<td>
					<div align="center">
						逾期本金
					</div>
				</td>
				<td>
					<bean:write name='rs' property="yqbj" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						逾期罚息
					</div>
				</td>
				<td>
					<bean:write name='rs' property="yqfx" />
				</td>
				<td>
					<div align="center">
						逾期原因
					</div>
				</td>
				<td>
					<bean:write name='rs' property="yhyy" />
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
		</table>
	</html:form>
	<div align=center>
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','还贷基本信息')" />
	</div>
</body>
</html:html>
