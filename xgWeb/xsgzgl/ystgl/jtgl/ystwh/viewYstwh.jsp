<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/ystglYstwh" method="post" styleId="YstwhForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��������Ŀ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								����������
							</th>
							<td  colspan="3">
								${rs.ystxmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								���������
							</th>
							<td width="30%">
								${rs.ystlbmc}
							</td>
							<th width="20%">
								��Ŀ���
							</th>
							<td width="30%">
								${rs.xmlbmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��ϵ�绰
							</th>
							<td width="30%">
								${rs.lxdh}
							</td>
							<th width="20%">
								�ҿ���λ
							</th>
							<td width="30%">
								${rs.gkdwmc}
							</td>
						</tr>
						
						<tr>
							<th width="20%">
								���������
							</th>
							<td width="30%">
								${rs.fzrlb}
							</td>
							<th width="20%">
								������
							</th>
							<td width="30%">
								${rs.fzrxm}
							</td>
						</tr>
						<tr>
							<th width="20%">
								ָ����ʦ
							</th>
							<td width="30%" >
								${rs.zdlsxm}
							</td>
							<th width="20%">
								ָ����ʦְ��
							</th>
							<td width="30%" >
								${rs.zcmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								ָ����ʦ��ϵ��ʽ
							</th>
							<td width="30%" >
								${rs.zdlslxfs}
							</td>
							<th width="20%">
								��������
							</th>
							<td width="30%" >
								${rs.ssbmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�����ų���ʱ��
							</th>
							<td width="30%" >
								${rs.ystclsj}
							</td>
							<th width="20%">
								����ʱ��
							</th>
							<td width="30%">
								${rs.sqsj}
							</td>	
						</tr>
						<tr>
							<th width="20%">
								�����������
							</th>
							<td colspan="3">
								${splcname}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�����ż��
							</th>
							<td colspan="3">
								${rs.ystjj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�����Ż����
							</th>
							<td colspan="3">
								${rs.ysthjqk}
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

