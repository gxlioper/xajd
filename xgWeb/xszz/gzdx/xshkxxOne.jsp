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


	<title><bean:message key="lable.title" />
	</title>
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
	</script>
</head>

<body>
	<html:form action="gzdx_xszz.do?method=xshkxxOne" method="post">
		<table class="tbstyle" width="100%">
			<tr>
				<td align="center" width="16%">
					合同号
				</td>
				<td width="34%">
					<bean:write name="rs" property="htbh"/>
				</td>
				<td width="16%">
					<div align="center">
						贷款次数
					</div>
				</td>
				<td width="34%">
					<bean:write name="rs" property="dkcs"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						贷款学年
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xn"/>
				</td>
				<td>
					<div align="center">
						贷款银行
					</div>
				</td>
				<td>
					<bean:write name="rs" property="dkyh"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						学号
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xh"/>
				</td>
				<td>
					<div align="center">
						姓名
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xm"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xymc"/>
				</td>
				<td>
					<div align="center">
						专业
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zymc"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						班级
					</div>
				</td>
				<td>
					<bean:write name="rs" property="bjmc"/>
				</td>
				<td>
					<div align="center">
						年级
					</div>
				</td>
				<td>
					<bean:write name="rs" property="nj"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						银行卡号
					</div>
				</td>
				<td>
					<bean:write name="rs" property="yhkh"/>
				</td>
				<td>
					<div align="center">
						贷款总额
					</div>
				</td>
				<td>
					<bean:write name="rs" property="dkze"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						贷款年级
					</div>
				</td>
				<td>
					<bean:write name="rs" property="dknj"/>
				</td>
				<td>
					<div align="center">
						贷款发放日
					</div>
				</td>
				<td>
					<bean:write name="rs" property="dkffr"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						贷款到期日
					</div>
				</td>
				<td>
					<bean:write name="rs" property="dkdqr"/>
				</td>
				<td>
					<div align="center">
						贴息终止日
					</div>
				</td>
				<td>
					<bean:write name="rs" property="txzzr"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						展期原因
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="zqyy"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						展期到期日期
					</div>
				</td>
				<td>
					<bean:write name='rs' property="zqdqrq" />
				</td>
				<td>
					<div align="center">
						展期贴息终止日期
					</div>
				</td>
				<td>
					<bean:write name='rs' property="zqtxzzrq" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						提前还款时间
					</div>
				</td>
				<td>
					<bean:write name='rs' property="tqhksj" />
				</td>
				<td>
					<div align="center">
						提前还款金额
					</div>
				</td>
				<td>
					<bean:write name="rs" property="tqhkje"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						按合同正常还款
					</div>
				</td>
				<td>
					<div align="center">
						第1年利息归还情况
					</div>
				</td>
				<td>
					<bean:write name="rs" property="lxghqk_1"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						第2年利息归还情况
					</div>
				</td>
				<td>
					<bean:write name="rs" property="lxghqk_2"/>
				</td>
				<td>
					<div align="center">
						第3年利息归还情况
					</div>
				</td>
				<td>
					<bean:write name="rs" property="lxghqk_3"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						第4年利息归还情况
					</div>
				</td>
				<td>
					<bean:write name="rs" property="lxghqk_4"/>
				</td>
				<td>
					<div align="center">
						第5年利息归还情况
					</div>
				</td>
				<td>
					<bean:write name="rs" property="lxghqk_5"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						第6年利息归还情况
					</div>
				</td>
				<td>
					<bean:write name="rs" property="lxghqk_6"/>
				</td>
				<td>
					<div align="center">
						第7年本息归还情况
					</div>
				</td>
				<td>
					<bean:write name="rs" property="lxghqk_7"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						违约时间
					</div>
				</td>
				<td>
					<bean:write name='rs' property="wysj" />
				</td>
				<td>
					<div align="center">
						违约金额
					</div>
				</td>
				<td>
					<bean:write name="rs" property="wyje"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						违约原因
					</div>
				</td>
				<td>
					<bean:write name="rs" property="wyyy"/>
				</td>
				<td>
					<div align="center">
						是否全部还清贷款
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sfqbhqdk"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						备注
					</div>
				</td>
				<td colspan="3">
					<bean:write name='rs' property="bz" />
				</td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<button class="button2" onClick="Close();" id="buttonPrint">
				关&nbsp;&nbsp;&nbsp;&nbsp;闭
			</button>
		</div>
	</html:form>
</body>
</html:html>
