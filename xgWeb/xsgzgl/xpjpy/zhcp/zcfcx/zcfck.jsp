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
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/zcfcx" method="post" styleId="ZcfForm">
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�۲�Ʒ�мӼ����б�</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						<td colspan="4" width="100%">
							<table width="100%">
								<tbody width="100%">
								 <tr width="100%">
								 	<td width="25%">ѧ��</td>
									<td width="25%">ѧ��</td>
									<td width="25%">��Ŀ����</td>
									<td width="25%">�Ӽ���</td>
								 </tr>
								
								 	<logic:iterate  name="zcflist" id="i">
								 	 <tr width="100%">
								 		<td width="25%">${i.xn}</td>
								 		<td width="25%">${i.xqmc}</td>
								 		<td width="25%">${i.rcxwlbmc}</td>
								 		<td width="25%">${i.sumfz}</td>
								 		</tr>
									</logic:iterate>
								 
									
								</tbody>
							</table>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�۲�ֻ�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>�۲��</th>
							<td>${zcfinfo.zhszf}</td>
							<th>�۲������</th>
							<td>${zcfinfo.zhszfpm}</td>
						</tr>
						<tr>
							<th>ѧ��ƽ���ɼ�</th>
							<td>${zcfinfo.xnpjcj}</td>
							<th>ѧ��ƽ���ɼ�����</th>
							<td>${zcfinfo.xnpjcjpm}</td>
						</tr>
						<tr>
							<th>�۲�Ʒ�зּӷ�</th>
							<td>${jkfinfo.jf}</td>
							<th>�۲�Ʒ�зּ���</th>
							<td>${jkfinfo.kf}</td>
						</tr>
						<tr>
							<th>�۲�Ʒ�з��ܷ�</th>
							<td>${jkfinfo.zf}</td>
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
										�ر�
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