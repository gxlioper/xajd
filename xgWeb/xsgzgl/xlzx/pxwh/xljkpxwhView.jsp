<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/zjly_ylbx" method="post" styleId="form">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;' >
				<table width="100%" border="0" class="formlist" >
					<thead>
						<tr>
							<th colspan="4">
								<span>��ѵ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th  width="20%">
								��ѵ����
							</th>
							<td colspan="3">
								${pxwhForm.pxzt}
							</td>
						</tr>
						<tr>
						<tr>
							<th  width="20%">
								��ѵ�ص�
							</th>
							<td colspan="3">
								${pxwhForm.pxdd}
							</td>
						</tr>
						<tr>
							<th  width="20%">
								��ѵʱ��
							</th>
							<td width="30%">
								${pxwhForm.pxsj}
							</td>
							<th  width="20%">
								��ʦ
							</th>
							<td width="30%">
								${pxwhForm.js}
							</td>
						</tr>
						<tr>
							<th  width="20%">
								��ѵ��������
							</th>
							<td width="30%">
								${pxwhForm.sxrs}
							</td>
							<th  width="20%">
								�ѱ�������
							</th>
							<td width="30%">
								${pxwhForm.ybmrs}
							</td>
						</tr>
						<tr>
							<th  width="20%">
								��ѵ����
							</th>
							<td colspan="3">
								${pxwhForm.bmkg}
							</td>
						</tr>
						<tr>
							<th  width="20%">
								��������ʱ��
							</th>
							<td colspan="3">
								${pxwhForm.kssj}
							��
								${pxwhForm.jssj}
							</td>
						</tr>
						<tr>
							<th >
								��ѵ����
								<br><font color="red">��500������</br></font>
							</th>
							<td colspan="3" style="height:150px">
								${pxwhForm.pxnr}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
								<div class="btn">
									<button type="button" onclick="iFClose();">
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