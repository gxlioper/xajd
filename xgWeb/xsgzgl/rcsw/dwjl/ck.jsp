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
	</head>
	<body>
		<html:form action="/xg_dwjl_dwjl" method="post" styleId="dwjlForm" onsubmit="return false;">
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
								<span>���⽻����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>����ѧУ</th>
							<td>
								${dwjlForm.jlxx }
							</td>
							<th>����רҵ</th>
							<td>
							${dwjlForm.jlzy }
							</td>
						</tr>
						<tr>
							<th>������ʼʱ��</th>
							<td>
							${dwjlForm.jlkssj }
							</td>
							<th>��������ʱ��</th>
							<td>
							${dwjlForm.jljssj }
							</td>
						</tr>
						<tr>
							<th>��ע</th>
							<td colspan="3">
							${dwjlForm.bz }
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