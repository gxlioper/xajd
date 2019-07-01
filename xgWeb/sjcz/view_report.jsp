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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<logic:equal name="name" value="work_payput_sum">
				<fieldset>
					<legend align="center">
					<%--长沙民政--%>
					<logic:equal value="10827" name="xxdm">
						<bean:write name="xxmc" />
						<bean:write name="nd" />
						年
						<bean:write name="yf" />
						月学生义工报酬发放汇总单
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						<bean:write name="xxmc" />
						<bean:write name="nd" />
						年
						<bean:write name="yf" />
						月学生勤工助学报酬发放汇总单
					</logic:notEqual>						
					</legend>
					<table width="100%" border="0" align="center" id="rsT">
						<tr>
							<td>
								<center>
									<bean:write name="nd" />
									年
									<bean:write name="yf" />									
									月各<bean:message key="lable.xsgzyxpzxy" />报酬汇总
								</center>
								<table width="100%" class="tbstyle" align="center">
									<thead>
										<tr align="center">
											<td width="50%">
												<bean:message key="lable.xsgzyxpzxy" />
											</td>
											<td width="25%">
												用人工次
											</td>
											<td width="25%">
												发放金额
											</td>
										</tr>
									</thead>
									<logic:empty name="rs1">
										<tr align="center">
											<td colspan="3">
												无记录！
											</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="rs1">
										<logic:iterate name="rs1" id="s1">
											<tr onclick="rowOnClick(this)" style="cursor:hand">
												<logic:iterate id="v1" name="s1" offset="0">
													<td nowrap>
														<bean:write name="v1" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
								<br/>
								<center>
									<bean:write name="nd" />
									年
									<bean:write name="yf" />
									月各用人单位报酬发放统计
								</center>								
								<table width="100%" class="tbstyle" align="center">
									<thead>
										<tr align="center">
											<td width="10%">
												序号
											</td>
											<td width="40%">
												用人单位
											</td>
											<td width="25%">
												用人工次
											</td>
											<td width="25%">
												发放金额
											</td>
										</tr>
									</thead>
									<logic:empty name="rs">
										<tr align="center">
											<td colspan="4">
												无记录！
											</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="rs">
										<logic:iterate name="rs" id="s">
											<tr onclick="rowOnClick(this)" style="cursor:hand">
												<logic:iterate id="v" name="s" offset="0">
													<td nowrap>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
								<br/>
								总人次:&nbsp;
								<u><logic:notEmpty name="ygzrc">
										<bean:write name="ygzrc" />
									</logic:notEmpty>
								</u>&nbsp;人次&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总金额:&nbsp;
								<u><logic:notEmpty name="ffzje">
										<bean:write name="ffzje" />
									</logic:notEmpty>
								</u>&nbsp;元整
								<br/>
								领导签字(盖章):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;经办人:
							</td>
						</tr>
					</table>
				</fieldset>
			</logic:equal>
			
			<logic:equal name="name" value="work_payput_details">
				<fieldset>
				<%--长沙民政--%>
				<logic:equal value="10827" name="xxdm">
					<legend align="center">
						<bean:write name="xxmc" />
						<bean:write name="nd" />
						年
						<bean:write name="yf" />
						月学生义工报酬发放明细单
					</legend>
				</logic:equal>
				<logic:notEqual value="10827" name="xxdm">
					<legend align="center">
						<bean:write name="xxmc" />
						<bean:write name="nd" />
						年
						<bean:write name="yf" />
						月学生勤工助学报酬发放明细单
					</legend>
				</logic:notEqual>
					<table width="100%" border="0" align="center" id="rsT">
						<tr>
							<td>
								<center>
									1.固定岗位
								</center>
								<table width="100%" class="tbstyle" align="center">
									<thead>
										<tr align="center">
											<td width="10%">
												学号
											</td>
											<td width="10%">
												姓名
											</td>
											<td width="20%">
												<bean:message key="lable.xsgzyxpzxy" />
											</td>
											<td width="20%">
												岗位名称
											</td>
											<td width="15%">
												用人单位
											</td>
											<td width="10%">
												发放金额
											</td>
											<%--武汉理工大学--%>
											<logic:equal value="10497" name="xxdm">
											<td width="10%">
												考核等级
											</td>
											</logic:equal>
											<!--浙江传媒<bean:message key="lable.xsgzyxpzxy" />-->
											<logic:equal value="11647" name="xxdm">
											<td nowrap="nowrap">
												银行卡号
											</td>
											<td nowrap="nowrap">
												银行名称
											</td>
											</logic:equal>
											<!--end浙江传媒<bean:message key="lable.xsgzyxpzxy" />-->
											<td width="15%">
												备注
											</td>
										</tr>
									</thead>
									<logic:empty name="rs">
										<tr align="center">
											<td colspan="7">
												无记录！
											</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="rs">
										<logic:iterate name="rs" id="s">
											<tr onclick="rowOnClick(this)" style="cursor:hand">
												<logic:iterate id="v" name="s" offset="0">
													<td nowrap>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
								<br/>
								<center>
									2．校内临时
								</center>
								<table width="100%" class="tbstyle" align="center">
									<thead>
										<tr align="center">
											<td width="10%">
												学号
											</td>
											<td width="10%">
												姓名
											</td>
											<td width="20%">
												<bean:message key="lable.xsgzyxpzxy" />
											</td>
											<td width="20%">
												岗位名称
											</td>
											<td width="15%">
												用人单位
											</td>
											<td width="10%">
												发放金额
											</td>
											<!--浙江传媒<bean:message key="lable.xsgzyxpzxy" />-->
											<logic:equal value="11647" name="xxdm">
											<td nowrap="nowrap">
												银行卡号
											</td>
											<td nowrap="nowrap">
												银行名称
											</td>
											</logic:equal>
											<!--end浙江传媒<bean:message key="lable.xsgzyxpzxy" />-->
											<td width="15%">
												备注
											</td>
										</tr>
									</thead>
									<logic:empty name="rs1">
										<tr align="center">
											<td colspan="7">
												无记录！
											</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="rs1">
										<logic:iterate name="rs1" id="s1">
											<tr onclick="rowOnClick(this)" style="cursor:hand">
												<logic:iterate id="v1" name="s1" offset="0">
													<td nowrap>
														<bean:write name="v1" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
							</td>
						</tr>
					</table>
				</fieldset>
			</logic:equal>
			<div class="buttontool" id="btn">
				<button type="button" class="button2"
					onclick="expTab('rsT','<bean:write name="xxmc" /><bean:write name="nd" />年<bean:write name="yf" />月学生勤工助学报酬发放汇总单')">
					打 印 报 表
				</button>
			</div>
		</html:form>
	</body>
</html>

