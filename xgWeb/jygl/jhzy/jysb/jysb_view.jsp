<%@ include file="/syscommon/pagehead.ini"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<html>
	<head>
		<title>就业管理信息系统</title>
				<%
				response.setHeader("Pragma","No-cache");
				response.setHeader("Cache-Control","no-cache");
				response.setDateHeader("Expires", 0);
				%>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<script language="javascript">
	</script>
	<body>
		<html:form action="/jhzyjysb" method="post">
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>就业上报查看</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />名称
					</td>
					<td colspan="1">
						${rs.xydm }
					</td>
					<td align="right">
						专业名称
					</td>
					<td>
						${rs.zydm }
					</td>
				</tr>
				<tr>
					<td align="right">
						毕业年份
					</td>
					<td>
						${rs.bynf }
					</td>
					<td align="right">
						上报时间点
					</td>
					<td>
						${rs.sbsjd }
					</td>
				</tr>
				<tr>
					<td align="right">
						学历
					</td>
					<td>
						${rs.xl }
					</td>
					<td align="right">
						毕业人数
					</td>
					<td>
						${rs.byrs }
					</td>
				</tr>
				<tr>
					<td align="right">
						签约人数
					</td>
					<td>
						${rs.qyrs }
					</td>
					<td align="right">
						签约率
					</td>
					<td>
						${rs.qyl }%
					</td>
				</tr>
				<tr>
					<td align="right">
						应聘人数
					</td>
					<td>
						${rs.yprs }
					</td>
					<td align="right">
						应聘率
					</td>
					<td>
						${rs.ypl }%
					</td>
				</tr>
				<tr>
					<td align="right">
						就业人数
					</td>
					<td>
						${rs.jyrs }
					</td>
					<td align="right">
						就业率
					</td>
					<td>
						${rs.jyl }%
					</td>
				</tr>
				<tr>
					<td align="right">
						灵活人数
					</td>
					<td>
						${rs.lhrs }
					</td>
					<td align="right" nowrap="nowrap"">
						出国和升学人数
					</td>
					<td>
						${rs.cghsxrs }
					</td>
				</tr>
				
			</table>
		</html:form>
	</body>
</html>

