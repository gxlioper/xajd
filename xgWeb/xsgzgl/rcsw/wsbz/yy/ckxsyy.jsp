<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/wsbz/yy/js/wsbzyy.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/wsbz_yy" method="post" styleId="WsbzYyForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
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
								<span>ԤԼ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>�������Ϸֶ�</th>
							<td>
								${fd.fdmc}
							</td>
							<th>�ලʱ��</th>
							<td>
								<label id="sj">${fd.sj}</label>
							</td>
						</tr>
						<tr>
							<th>ԤԼʱ��</th>
							<td>
								${yyrq}
							</td>
							<th>�ල�ص�</th>
							<td>
								<label id="dd">${fd.dd}</label>
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td>
								<label id="rs">${fd.rs}</label>
							</td>
							<th>��ԤԼ����</th>
							<td>
								<label id="syrs">${syrs}</label>
							</td>
						</tr>
						<tr >
							<th>����ʱ��</th>
							<td>
								${jg.sqsj}
							</td>
							<th></th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>����ְ��</th>
							<td colspan="3">
								<label id="gzzz">${fd.gzzz}</label>
							</td>
						</tr>
						<tr>
							<th>����Ҫ��</th>
							<td colspan="3">
								<label id="fwyq">${fd.fwyq}</label>
							</td>
						</tr>
						<tr>
							<th>ԤԼ����</th>
							<td colspan="3">
								${jg.sqly}
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
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