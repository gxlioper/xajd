<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<html:form action="/ybgzzCywh" method="post" styleId="form">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�˳��װ๤��վ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								�˳�����
							</th>
							<td colspan="3">
								${ybgzzCyglModel.tcsj }
							</td>
						</tr>
						<tr>
							<th>
								�˳�ԭ��<br/>
							</th>
							<td colspan="3">
								${ybgzzCyglModel.tcyy }
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
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

