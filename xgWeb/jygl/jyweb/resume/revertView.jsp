<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	</head>
	<body>
		<html:form action="/jyweb" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��λ��Ϣ�ظ��鿴</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="reset" onclick="Close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">
								��λ
							</th>
							<td>
								${rs.dwmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								ӦƸ��λ
							</th>
							<td>
								${rs.gwmc }
							</td>
						</tr>
						<tr>
							<th width="20%">
								�ظ�ʱ��
							</th>
							<td>
								${rs.sj }
							</td>
						</tr>
						<tr>
							<th width="20%">
								����
							</th>
							<td style="word-break:break-all;">
								<html:textarea property="hfxx" style="width:95%" rows="12"
									readonly="true" value="${rs.hfxx}"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
