<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function back() {
			document.forms[0].action = "/xgxt/zzlgdx_rcsw.do?method=cgjDate";
			document.forms[0].submit();
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			showTopWin("/xgxt/zzlgdx_rcsw.do?method=cgjDy&pkVal="+document.getElementById("guid").value,650,450);
		}
	</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�ճ����� - ���� - ����(��)������ϸ��Ϣ</a>
			</p>
		</div>
		<html:form action="zzlgdx_rcsw.do?method=cgjQuery" method="post">
			<input type="hidden" id="guid" name="guid"
				value="<bean:write name="rs" property="guid" />" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>����(��)������ϸ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:equal name="rs" property="xxsh" value="ͨ��">
										<button type="button" onclick="toPrintOut();">
											��ӡ
										</button>
									</logic:equal>
									<logic:equal name="userOnLine" value="teacher" scope="session">
										<button type="button" onclick="Close();return false;">
											�ر�
										</button>
									</logic:equal>
									<logic:notEqual name="userOnLine" value="teacher"
										scope="session">
										<button type="button" onclick="back();">
											����
										</button>
									</logic:notEqual>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								<bean:write name='rs' property="xh" />
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">
								<bean:write name="rs" property="xm" />
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								<bean:write name="rs" property="xb" />
							</td>
							<th>
								���֤��
							</th>
							<td>
								<bean:write name="rs" property="sfzh" />
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td>
								<bean:write name="rs" property="nj" />
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								����
							</th>
							<td>
								<bean:write name="rs" property="xymc" />
							</td>
						</tr>
						<tr>
							<th>
								רҵ����
							</th>
							<td>
								<bean:write name="rs" property="zymc" />
							</td>
							<th>
								�༶����
							</th>
							<td>
								<bean:write name="rs" property="bjmc" />
							</td>
						</tr>
						<tr>
							<th>
								������ϵ��ʽ
							</th>
							<td>
								<bean:write name="rs" property="lxfs_br" />
							</td>
							<th>
								�ҳ���ϵ��ʽ
							</th>
							<td>
								<bean:write name="rs" property="lxfs_jz" />
							</td>
						</tr>
						<tr>
							<th>
								����ʱ��
							</th>
							<td>
								<bean:write name="rs" property="sqsj" />
							</td>
							<th>
								����ȥ��
							</th>
							<td>
								<bean:write name="rs" property="sqqx" />
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								<bean:write name='rs' property="cgrq" />
							</td>
							<th>
								��������
							</th>
							<td>
								<bean:write name='rs' property="fhrq" />
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
								<html:textarea property="sqly" style="100%;word-break:break-all;" cols="100" rows="3" value="${rs.sqly}"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								���
							</th>
							<td>
								<bean:write name='rs' property="xysh" />
							</td>
							<th>
								ѧУ���
							</th>
							<td>
								<bean:write name='rs' property="xxsh" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								������
							</th>
							<td colspan="3">
								<bean:write name='rs' property="xyshyj" />
							</td>
						</tr>
						<tr>
							<th>
								ѧУ������
							</th>
							<td colspan="3">
								<bean:write name='rs' property="xxshyj" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
