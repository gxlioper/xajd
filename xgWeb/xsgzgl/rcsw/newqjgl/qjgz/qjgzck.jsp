<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript"
			src="xsgzgl/rcsw/newqjgl/qjgz/js/qjgz.js"></script>
		<script type="text/javascript">
</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/qjgz">
			<div>
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th>
								所属学院
							</th>
							<td>
								${rs.ssxymc}
							</td>
						</tr>
						<tr>
							<th>
								请假区间
							</th>
							<td>
								${rs.kssj}~${rs.jssj}天
							</td>
						</tr>
						<tr>
							<th>
								请假类型
							</th>
							<td>
								${qjlxmc}
							</td>
						</tr>
						<tr>
							<th>
								审核流程
							</th>
							<td>
								${shlcmc}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="iFClose();" id="buttonClose">
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
