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
		<html:form action="/ttgl_stgl" method="post" styleId="stglForm" onsubmit="return false;">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr class="h">
							<th colspan="4">
								<span>社团负责人</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr class="h">
							<th colspan="7">
								<table width="100%" >
									<thead>
										<tr>
											<th width='30%' style="text-align:center">学号</th>
											<th width='30%' style="text-align:center">姓名</th>
											<th width='30%' style="text-align:center">团内职务</th>
										</tr>
									</thead>
									<tbody id="tablebody">
									<logic:iterate id="i" name="cyList" indexId="index">
											<tr>
												<td style='text-align:center'><label name = 'xm'>${i.xh}</label></td>
												<td style='text-align:center'><label name = 'xm'>${i.xm}</label></td>
												<td style='text-align:center'><label name = 'symc'>${i.tnzw}</label></td>
											</tr>
									</logic:iterate>
									</tbody>
								</table>
							</th>
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
										关闭
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