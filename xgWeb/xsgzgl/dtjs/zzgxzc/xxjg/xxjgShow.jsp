<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>ת�������Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th>���ڵ�֧��</th>
						<td>
							${xxjgForm.szdzbmc }				
						</td>
						<th>�Ƿ�ʡ��</th>
						<td>
							${xxjgForm.sfsn }
						</td>
					</tr>
					<tr>
						<th>���ձ�����֯��ϵ�ĵ���֯</th>
						<td colspan="3">
							${xxjgForm.jsdzz }
						</td>
					</tr>
					<tr>
						<th>������֯��ϵ��ȥ��λ</th>
						<td colspan="3">
							${xxjgForm.sqdw }
						</td>
					</tr>
					<tr>
						<th>���ѽ�������</th>
						<td >
							${xxjgForm.dfjzrq }
						</td>
						<th>�Ƿ���Ҫ���߻���֤��</th>
						<td >
							${xxjgForm.sfkjhyzm}
						</td>
					</tr>
					<tr>
						<th>�����ű��</th>
						<td colspan="3">
							${xxjgForm.jsxbh }
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
