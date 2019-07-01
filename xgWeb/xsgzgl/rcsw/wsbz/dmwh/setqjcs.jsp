<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/wsbz/dmwh/js/dmwh.js"></script>
	</head>
	<body >
		<html:form action="/wsbz_dmwh" method="post" styleId="WsbzDmwhForm" onsubmit="return false;">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>
										全局参数维护
									</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>报名次数
								</th>
								<td>
								<input type="text" id="bmcs" value="${cs.bmcs }" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>截止天数
								</th>
								<td>
									<input type="text" id="jzts" value="${cs.jzts}" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>截止时间
								</th>
								<td>
									<input type="text" id="jzsj" value="${cs.jzsj}" onfocus="return showCalendar('jzsj','HH:mm:ss');" />
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" name="保存" onclick="setQjcs();return false;">
											保 存
										</button>
										<button type="button" name="关 闭" onclick="iFClose();return false;">
											关 闭
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

