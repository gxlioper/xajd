
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
</head>
<body style="width:100%">
<html:form action="/xyfd_fqyy" method="post" styleId="demoForm">
	<input type="hidden" id="yyid" name="yyid" value="${model.yyid}"/>
	<div style='width:100%;height:500px;overflow-x:hidden;overflow-y:auto;'>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
					<span>ԤԼѧ����Ϣ</span>
				</th>
			</tr>
			</thead>
			<%@ include file="/xsgzgl/xyfd/jzppwh/fdyywh/selectStudentforFdyy.jsp" %>
			<thead>
			<tr>
				<th colspan="4">
					<span>ԤԼ�γ�</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="20%"><span class="red">*</span>�γ�����</th>
				<td width="30%">
					<input type="hidden" id="fdkc" name="fdkc" value="${fdkcxx.jgid}"/>
						${fdkcxx.kcmc}
				</td>
				<th width="20%">�����ص�</th>
				<td width="30%">
						${fdkcxx.fdsdd}
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>����ʱ��</th>
				<td colspan="3">
					${model.fdsj}
				</td>
			</tr>
			</tbody>
			<thead>
			<tr>
				<th colspan="4">
					<span>������ʦ��${fdkcxx.xm}�����Ű�</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td colspan="4">
					<table width="100%">
						<tbody>
						<tr>
							<th width="14%">��һ</th>
							<th width="14%">�ܶ�</th>
							<th width="14%">����</th>
							<th width="14%">����</th>
							<th width="14%">����</th>
							<th width="14%">����</th>
							<th width="14%">����</th>
						</tr>
						<tr>
							<td>${fdkcxx.mon}</td>
							<td>${fdkcxx.tues}</td>
							<td>${fdkcxx.wed}</td>
							<td>${fdkcxx.thur}</td>
							<td>${fdkcxx.fri}</td>
							<td>${fdkcxx.sat}</td>
							<td>${fdkcxx.sun}</td>
						</tr>
						</tbody>
					</table>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
	<div style="position:fixed;bottom:0;width: 100%">
		<table width="100%" border="0" class="formlist">
			<tfoot>
			<tr>
				<td colspan="4" >
					<div class="btn">
						<button type="button" name="�� ��" onclick="iFClose();">
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

