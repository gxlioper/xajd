<%@ include file="/syscommon/pagehead.ini"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<html>
	<head>
		<title>��ҵ������Ϣϵͳ</title>
				<%
				response.setHeader("Pragma","No-cache");
				response.setHeader("Cache-Control","no-cache");
				response.setDateHeader("Expires", 0);
				%>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta name="Copyright" content="������� zfsoft" />
	</head>
	<script language="javascript">
	</script>
	<body>
		<html:form action="/jhzyjysb" method="post">
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>��ҵ�ϱ��鿴</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />����
					</td>
					<td colspan="1">
						${rs.xydm }
					</td>
					<td align="right">
						רҵ����
					</td>
					<td>
						${rs.zydm }
					</td>
				</tr>
				<tr>
					<td align="right">
						��ҵ���
					</td>
					<td>
						${rs.bynf }
					</td>
					<td align="right">
						�ϱ�ʱ���
					</td>
					<td>
						${rs.sbsjd }
					</td>
				</tr>
				<tr>
					<td align="right">
						ѧ��
					</td>
					<td>
						${rs.xl }
					</td>
					<td align="right">
						��ҵ����
					</td>
					<td>
						${rs.byrs }
					</td>
				</tr>
				<tr>
					<td align="right">
						ǩԼ����
					</td>
					<td>
						${rs.qyrs }
					</td>
					<td align="right">
						ǩԼ��
					</td>
					<td>
						${rs.qyl }%
					</td>
				</tr>
				<tr>
					<td align="right">
						ӦƸ����
					</td>
					<td>
						${rs.yprs }
					</td>
					<td align="right">
						ӦƸ��
					</td>
					<td>
						${rs.ypl }%
					</td>
				</tr>
				<tr>
					<td align="right">
						��ҵ����
					</td>
					<td>
						${rs.jyrs }
					</td>
					<td align="right">
						��ҵ��
					</td>
					<td>
						${rs.jyl }%
					</td>
				</tr>
				<tr>
					<td align="right">
						�������
					</td>
					<td>
						${rs.lhrs }
					</td>
					<td align="right" nowrap="nowrap"">
						��������ѧ����
					</td>
					<td>
						${rs.cghsxrs }
					</td>
				</tr>
				
			</table>
		</html:form>
	</body>
</html>

