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
		<script type="text/javascript" src="xsgzgl/szdw/xsgbgl/js/zwlx.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/rcsw_xsxxgl" method="post" styleId="demoForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�鿴ѧ���ɲ�ְ������</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							
							<th width="16%">
							     ְ����������
							</th>
							<td width="34%" colspan="3">
								${zwlx.lxmc}
							</td>
						</tr>
						
						<tr>
							<th width='16%'>
								
								��������
							</th>	
							<td width="34%">
								${splc}
							</td>
						</tr>
						
						<tr>
							<th width="16%">
								��ע
							</th>
							<td width="34%" colspan="3">
								${zwlx.bz}
							</td>
						</tr>
						
						
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" id= "but_close" onclick="iFClose();">
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

