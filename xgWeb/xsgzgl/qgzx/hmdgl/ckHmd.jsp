<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		
	</head>
	<body>
		<html:form method="post" styleId="hmdglForm" action="/qgzxhmdgl"
			enctype="multipart/form-data">
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��λ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="10%">
								��λ����
							</th>
							<td align="left">
								${rs.gwmc }
							</td>

							<th align="right">
								���˵�λ
							</th>
							<td align="left">
								${rs.yrdw }
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								�ϸ�ʱ��
							</th>
							<td align="left">
								${rs.sgsj}
							</td>

							<th align="right">
								���ʱ��
							</th>
							<td align="left">
								${rs.lgsj }
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								��ע&nbsp;
							</th>
							<td colspan="3">
								${rs.bz }
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:50px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
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
