<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/xsgzgl/qgzx/yjscjffgl/js/yjscjffgl.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/qgzxJfglYjscjff" method="post" styleId="YjsCjffForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								${model.xh}
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">
								${model.xm}
							</td>
						</tr>
						</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�鿴��𷢷���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								${model.xn}
							</td>
							<th width="16%">
								���˲���
							</th>
							<td width="34%" >
								${model.yrbmmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��λ
							</th>
							<td width="34%">
								${model.gwmc}
							</td>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								${model.ffny}
							</td>
							
						</tr>
						<tr>
							<th width="16%">
								��ʱ
							</th>
							<td width="34%">
								${model.gs}
							</td>
							<th width="16%">
								���
							</th>
							<td width="34%">
								${model.je}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ע
							</th>
							<td width="34%" colspan="3">
								${model.gznr}
							</td>
						</tr>
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
			</html:form>
	</body>
</html>

