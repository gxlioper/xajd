<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<html:form action="/ybgzzSqsh" method="post" styleId="jdqkForm">
			<input type="hidden" name="splcid" value="${cssz.splc }"/>
			<div style='tab'>
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
								<span>�װ๤��վ��Ա����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								�����������
							</th>
							<td colspan="3">
								${ybgzzCyglModel.sqjrsj }
							</td>
						</tr>
						<tr>
							<th>
								��������<br/>
							</th>
							<td colspan="3">
								${ybgzzCyglModel.sqly }
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

