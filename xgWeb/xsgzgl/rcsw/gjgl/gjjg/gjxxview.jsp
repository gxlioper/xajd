<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src='js/calendar/calendar-setup.js'></script>
	</head>
	<body>
	<html:form action="/xsgjgl.do" styleId="gjjgForm" method="post"  onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
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
								<span>ѧ�������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						<th align="right" width="10%">
							�������
						</th>
						<td align="left">
							����
						</td>
						<th align="right">
							���췽������
						</th>
						<td align="left">
							${data.zbffzr }
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							��ٿ�ʼʱ��
						</th>
						<td align="left">
							${data.qjkssj }
						</td>
						<th align="right">
							��ٽ���ʱ��
						</th>
						<td align="left">
							${data.qjjssj }
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							��ٽڴ�
						</th>
						<td colspan="3">
							${data.qjjc}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							�Ƿ����
						</th>
						<td align="left" >
						${data.sfgq}
						</td>
						<th align="right">
							������ʱ��
						</th>
						<td align="left">
						${data.bgqsj }
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							�������
						</th>
						<td colspan="3">
							${data.sy}
						</td>
					</tr>
						<tr>
							<th width="16%" rowspan="4">
								��ע
								<br>
							</th>
							<td width="34%" colspan="3" rowspan="4">
							${data.bz }
							</td>
						</tr>
				</tbody>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="refreshParent2();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</table>	
		</html:form>
	</body>
</html>