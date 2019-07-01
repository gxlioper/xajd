<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
	jQuery(function() {

		jQuery("#shlccx").load(
				"comm_spl.do?method=lccx&sqid=${model.sqid}&tt="
						+ new Date().getTime());
	});

</script>
	</head>
	<body>

		<html:form action="/mrgzkhKhsq" method="post" styleId="GzkhKhsqForm">
			<html:hidden property="sqid" styleId="sqid" />
			<div style="overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>

					<thead>
						<tr>
							<th colspan="4">
								<span>填写信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>用人单位</th>
							<td>
							${rs.yrdwmc}
							<th>学年</th>
							<td>
								${rs.xn}
							</td>
						</tr>
						<tr>
							<th>岗位</th>
							<td>
								${rs.gwmc}
							</td>
							<th>工作日期</th>
							<td>
								${rs.gzrq}
							</td>
						</tr>
						<tr>
							<th>工时</th>
							<td>
								${rs.gs}（小时）
							</td>
							<th>工作时间段</th>
							<td>
								${rs.gzkssj}到${rs.gzjssj}
							</td>
						</tr>

					</tbody>
				</table>
				<logic:notEqual value="无需审核" name="shztmc">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>审核信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4" id="shlccx">

								</td>
							</tr>

						</tbody>

					</table>
				</logic:notEqual>

				</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
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

