<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/axcs/wpsz/js/wpsz.js"></script>
	</head>
	<body>
		<html:form action="/axcsWpsz" method="post" styleId="WpszForm"
			onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table align="center" class="formlist">
					<thead>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学年
							</th>
							<td width="50%">
								${rs.xn}
							</td>
							<td rowspan="3">
							<div id="zpHidDiv">
								<jsp:include flush="true" page="wpzpck.jsp"></jsp:include>
							</div>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<bean:message key="lable.axcswpmc" />
							</th>
							<td>
								${rs.xmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								<bean:message key="lable.axcswplb" />
							</th>
							<td>
								${rs.xmlbmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								申请条件
							</th>
							<td colspan="3">
								${rs.sqtj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								申请起始时间
							</th>
							<td colspan="3">
								${rs.sqsjfw}
							</td>
						</tr>
						<tr>
							<th width="20%">
								<bean:message key="lable.axcswpxxjs" />
							</th>
							<td colspan="3">
								${rs.xmxxjs}
							</td>
						</tr>

					</tbody>
						</table>
			</div>
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="3">
								<div class="btn">
									<button type="button" type="button"
										onclick="iFClose();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
			</table>

		</html:form>
	</body>
</html>

