<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		
		<script	type="text/javascript">
		jQuery(function(){
			_w_table_rowspan("#kcwxx",1);
			_w_table_rowspan_merge("#kcwxx",8,2);
			_w_table_rowspan_merge("#kcwxx",9,3); 
		});
		</script>
	</head>
	<body>
		<div style='width:100%;'>
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>��ϵ�մ�λ��Ϣ</span>
						</th>
					</tr>
				</thead>
			</table>
			<table id="kcwxx" width="100%" align="center" border="2" cellspacing="1">
				<tr align="center">
					<td >Ժϵ</td>
					<td >����¥</td>
					<td >¥��</td>
					<td >�����</td>
					<td >��ס�Ա�</td>
					<td >��λ��</td>
					<td >ʣ�ലλ</td>
				</tr>
				<logic:iterate id="kcwxx" name="gxkcwxx">
					<tr align="center">
						
						<td>${kcwxx.bmmc }</td>
						<td>${kcwxx.ldmc} </td>
						<td>${kcwxx.ch }</td>
						<td>${kcwxx.qsh}</td>
						<td>${kcwxx.qsxb}</td>
						<td>${kcwxx.cws}</td>
						<td>${kcwxx.sycw}</td>
						<td style="display: none;">${kcwxx.bmmc }${kcwxx.ldmc}</td>
						<td style="display: none;">${kcwxx.bmmc }${kcwxx.ldmc}${kcwxx.ch }</td>
					</tr>
				</logic:iterate>
			</table>
		</div>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>

