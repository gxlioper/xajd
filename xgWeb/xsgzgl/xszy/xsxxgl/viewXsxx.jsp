<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script	type="text/javascript">
		jQuery(function(){
		});
		</script>
	</head>
	<body>
		<html:form action="/xszyXsxxgl" method="post" styleId="xszyXsxxForm">
			<html:hidden property="xh" styleId="xh" />
			<div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>����������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th width="20%">ѧ��</th>
							<td width="30%">
								${rs.xh }
							</td>
							<th width="20%">����</th>
							<td width="30%">
								${rs.xm }
							</td>
						</tr>
					    <tr>
							<th width="20%">�Ա�</th>
							<td width="30%">
								${rs.xb }
							</td>
							<th width="20%">�꼶</th>
							<td width="30%">
								${rs.nj }
							</td>
						</tr>
					    <tr>
							<th width="20%">����</th>
							<td width="30%">
								${rs.dl }
							</td>
							<th width="20%">�༶</th>
							<td width="30%">
								${rs.bjmc }
							</td>
						</tr>
					    <tr>
							<th width="20%">����</th>
							<td width="30%" colspan="3">
								${rs.jg }
							</td>
						</tr>
					    <tr>
							<th width="20%">��ϵ�绰</th>
							<td width="30%">
								${rs.lxdh }
							</td>
							<th width="20%">�̺�</th>
							<td width="30%">
								${rs.dh }
							</td>
						</tr>
						<tr>
							<th width="20%">¥��</th>
							<td width="30%">
								${rs.ldmc }
							</td>
							<th width="20%">���Һ�</th>
							<td width="30%">
								${rs.qsh }
							</td>
						</tr>
						<tr>
							<th width="20%">ѧ԰</th>
							<td width="30%">
								${rs.xymc }
							</td>
							<th width="20%">����Ժϵ</th>
							<td width="30%">
								${rs.ssyxmc }
							</td>
						</tr>
						<tr>
							<th width="20%">����</th>
							<td width="30%">
								${rs.mzdmmc }
							</td>
							<th width="20%">����</th>
							<td width="30%">
								${rs.dzyx }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th width="20%">����������</th>
							<td width="30%">
								${rs.bzrxm }
							</td>
							<th width="20%">����Ա����</th>
							<td width="30%">
								${rs.fdyxm }
							</td>
						</tr>
					    <tr>
							<th width="20%">��������ϵ�绰</th>
							<td width="30%">
								${rs.bzrlxdh }
							</td>
							<th width="20%">����Ա��ϵ�绰</th>
							<td width="30%">
								${rs.fdylxdh }
							</td>
						</tr>
					    <tr>
							<th width="20%">����������</th>
							<td width="30%">
								${rs.bzryx }
							</td>
							<th width="20%">����Ա����</th>
							<td width="30%">
								${rs.fdyyx }
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td align="left" colspan="3">
								${rs.bz }
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

