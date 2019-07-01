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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>		
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<body>
		<html:form action="/qgzx_work_adjustInfo" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<%--长沙民政--%>
					<logic:equal value="10827" name="xxdm">
						当前位置: 学生义工 - 审核 - 岗位调整详细信息
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						当前位置: 勤工助学 - 审核 - 岗位调整详细信息
					</logic:notEqual>
				</div>
			</div>

			<table width="100%" class="tbstyle">
				<tr style="height:22px">
					<td colspan="4">
						<div class="buttontool">
							<center>
								<b>岗位调整详细信息</b>
							</center>
						</div>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						学号：
					</td>
					<td align="left">
						${rs.xh}
					</td>
					<td align="right">
						姓名：
					</td>
					<td>
						${rs.xm}
					</td>
				</tr>
				<tr style="height:20px">
					<td>
						<div align="right">
							年级：
						</div>
					</td>
					<td>
						${rs.nj}
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td>
						${rs.xymc}
					</td>
				</tr>
				<tr style="height:22px">
					<td>
						<div align="right">
							专业：
						</div>
					</td>
					<td>
						${rs.zymc}
					</td>
					<td align="right">
						班级：
					</td>
					<td>
						${rs.bjmc}
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						调整原因：
					</td>
					<td>
						${rs.tzyy}
					</td>
					<td align="right">
						调整时间：
					</td>
					<td>
						${rs.tzsj}
					</td>
				</tr>
			</table>

			<table class="tbstyle" width="100%" height="220px">
				<tr style="height:22px">
					<td colspan="2">
						<div class="buttontool">
							<center>
								<b>异动学生岗位信息</b>
							</center>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<fieldset>
							<legend>
								异动前信息
							</legend>
							<table style="width:300px" class="tbstyle">
								<tr>
									<td width="22%" align="right">
										学年：
									</td>
									<td>
										${rs.tzqgzxn}
									</td>
								</tr>
								<tr>
									<td width="22%" align="right">
										年度：
									</td>
									<td width="33%">
										${rs.tzqgznd}
									</td>
								</tr>
								<tr>
									<td width="22%" align="right">
										学期：
									</td>
									<td width="33%">
										${rs.tzqgzxq}
									</td>
								</tr>
								<tr>
									<td width="22%" align="right">
										岗位名称：
									</td>
									<td width="33%">
										${rs.tzqgw}
									</td>
								</tr>
								<tr>
									<td width="22%" align="right">
										岗位发布时间：
									</td>
									<td width="33%">
										${rs.tzqgwsbsj}
									</td>
								</tr>
								<tr>
									<td width="22%" align="right">
										可参加工作时间：
									</td>
									<td>
										${rs.tzqkcjqgzxsj}
									</td>
								</tr>
							</table>
						</fieldset>
					</td>

					<td>
						<fieldset>
							<legend>
								异动后信息
							</legend>
							<table width="300px" class="tbstyle">
								<tr>
									<td width="22%" align="right">
										学年：
									</td>
									<td>
										${rs.tzhgzxn}
									</td>
								</tr>
								<tr>
									<td width="22%" align="right">
										年度：
									</td>
									<td width="33%">
										${rs.tzhgznd}
									</td>
								</tr>
								<tr>
									<td width="22%" align="right">
										学期：
									</td>
									<td width="33%">
										${rs.tzhgzxq}
									</td>
								</tr>
								<tr>
									<td width="22%" align="right">
										岗位名称：
									</td>
									<td width="33%">
										${rs.tzhgw}
									</td>
								</tr>
								<tr>
									<td width="22%" align="right">
										岗位发布时间：
									</td>
									<td width="33%">
										${rs.tzhgwsbsj}
									</td>
								</tr>
								<tr>
									<td width="22%" align="right" >
										可参加工作时间：
									</td>
									<td>
										${rs.tzhkcjqgzxsj}
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
			</table>
			<div>
				<div class="buttontool" id="btu" width="100%">
					<button type="button" class="button2"
						onclick="Close();return false;"
						style="width:80px" id="btu_close">
						关 闭
					</button>
				</div>
			</div>
		</html:form>
	</body>
</html>
