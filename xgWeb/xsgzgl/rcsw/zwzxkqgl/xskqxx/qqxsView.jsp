<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/kqsq.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/comm.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/zwzxkqKqjg" method="post" styleId="ZwzxKqjgForm" onsubmit="return false;">
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��ȱ����Ϣ�鿴</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${rs.xh}
							</td>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${rs.xn}
							</td>
						</tr>
						<tr>
							<th width="20%">
								����
							</th>
							<td width="30%">
								${rs.xm}
							</td>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								<bean:message key="lable.xb" />
							</th>
							<td width="30%">
								${rs.xymc}
							</td>
							<th width="20%">
								רҵ
							</th>
							<td width="30%">
								${rs.zymc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�༶
							</th>
							<td width="30%">
								${rs.bjmc}
							</td>
							<th width="20%">
								�������
							</th>
							<td width="30%">
								${rs.ccrq}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�������
							</th>
							<td width="30%">
								${rs.cclxmc}
							</td>
							<th width="20%">
								ȱ������
							</th>
							<td width="30%">
								${rs.qqlxmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								���ν���
							</th>
							<td width="30%">
								${rs.kkjs}
							</td>
						    <th>
								��ע
							</th>
							<td colspan="3">
								${rs.ylzd1}
							</td>
						</tr>
						<logic:equal value="11647" name="xxdm">
						<tr>
							<th>
								��ȱ�����Υ������
								<br />
								<div align="center">
									(��:�Է�,˯����...)
								</div>
							</th>
							<td align="left">
								${rs.wjrs}
							</td>
							</tr>
							</logic:equal>
					</tbody>
				 </table>
				</div>
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

