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
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/jzbggl" method="post" styleId="jzbgForm" onsubmit="return false;">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>����������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>��������</th>
							<td>
									${jzbgForm.mc }
							</td>
							<th>�����ص�</th>
							<td>
									${jzbgForm.dd}
							</td>
						</tr>
						<tr>
							<th>����ʱ��</th>
							<td>
								${jzbgForm.sj }
							</td>
							<th>���쵥λ</th>
							<td>
								${jzbgForm.zbdw }
							</td>
						</tr>
						<tr>
							<th>������</th>
							<td>
									${jzbgForm.zjr }
							</td>
							<th>��������</th>
							<td>
									${jzbgForm.cyrs }
							</td>
						</tr>
						<tr>
							<th>������</th>
							<td>
									${jzbgForm.fbr }
							</td>
							<th>����ʱ��</th>
							<td>
									${jzbgForm.fbsj }
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td colspan="3">
									${jzbgForm.zt }
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
									"<span class="red">*</span>"Ϊ������
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