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
		<script language="javascript" src="js/calendar/calendar.js"></script>
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

		<html:form action="/rcsw_lstd_sqgl" method="post"
			styleId="lstdshForm">
			<html:hidden property="sqid" styleId="sqid" />
			<input type="hidden" name="lxdm" id="lxdm" value="${rs.lxdm}" />

			<div style="overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/lstd/comm/viewStudent.jsp" %>

					<thead>
						<tr>
							<th colspan="4">
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th >申请理由</th>
							<td>
								${rs.lxmc}
							</td>
							<th>申请时间</th>
							<td colspan="3">
								${rs.sqsj}
							</td>
					    </tr>
					     <tr>
							<th>详情描述</th>
							</th>
							<td colspan="3">
								${rs.sqly}
							</td>
					    </tr>
			      		 <tr>
							<th>
							   	审核状态
							</th>
							<td colspan="3">
								${rs.shztmc}
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
				<div style="height: 15px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
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

