<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/xszygl" method="post" styleId="XszyglForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>����֮����Ϣ�鿴</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								ְ����
							</th>
							<td width="30%">
								${rs.zgh }
							</td>
							<th width="20%">
								����
							</th>
							<td width="30%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th width="20%">
								�Ա�
							</th>
							<td width="30%">
								${rs.xb }
							</td>
							<th width="20%">
								ְ��ְ��
							</th>
							<td width="30%">
								${rs.zwzc }
							</td>
						</tr>
						<tr>
							<th width="20%">
								��λ
							</th>
							<td width="30%">
								${rs.dwmc }
							</td>
							<th width="20%">
								��ϵ�绰
							</th>
							<td width="30%">
								${rs.lxdh }
							</td>
						</tr>
						<tr>
							<th width="20%">
								������ò
							</th>
							<td width="30%">
								${rs.zzmmmc }
							</td>
							<th width="20%">
								����
							</th>
							<td width="30%">
								${rs.dzyx }
							</td>
						</tr>
						<tr>
							<th width="20%">
								����Ҫ��
							</th>
							<td width="30%" colspan="3">
								${rs.dlyq }
							</td>
						</tr>
						<tr>
						    <th>
								��ע</br><font color="red"></font>
							</th>
							<td colspan="3">
								${rs.bz }
							</td>
						</tr>
						
					</tbody>
				 </table>
				 </div>
				<div style="height:35px"></div>
			    <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>

