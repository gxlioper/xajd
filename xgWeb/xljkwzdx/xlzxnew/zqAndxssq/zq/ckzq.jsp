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
		<script type="text/javascript" defer="defer">

		</script>
	</head>
	<body>
		<html:form action="/xlzxnew_zqrcgl" method="post" styleId="ZqszForm">
			<html:hidden property="zbid"/>
			<div style='tab;width:100%;height:330px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>周报详情</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right">
								学年
							</th>
							<td align="left">
								${rs.xn}
							</td>
							<th align="right">
								学期
							</th>
							<td align="left">
								${xqmc}
							</td>
						</tr>
						<tr>
							<th>
								周次
							</th>
							<td colspan="3">
								${rs.zbzc}
							</td>
						</tr>
						<tr>
							<th>
								起止日期
							</th>
							<td colspan="3">
								${rs.zbksrq}
								至
								${rs.zbjsrq}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="关 闭" onclick="iFClose();">
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

