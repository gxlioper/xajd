<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript">
		
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/kqsj" method="post" styleId="KqsjForm" onsubmit="return false;">
			<input type="hidden" id="type" value="${type}" />
			<html:hidden property="id"  styleId="id"/> 
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height:370px'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>

					<%@ include file="/xsgzgl/rcsw/hcyhkgl/comm/viewStudent.jsp" %>

					<thead>
						<tr>
							<th colspan="4">
								<span>考勤数据维护 </span>
							</th>
						</tr>
					</thead>					
						<tr>
							<th>学年</th>
							<td colspan="3">
								${rs.xn}
							</td>
						</tr>
						<tr>
							<th>旷课学时</th>
							<td>
								${rs.kkxs}
							</td>
							<th>迟到早退次数</th>
							<td>
								${rs.cdztcs}
							</td>
						</tr>
						<tr>
							<th>第一学期事假天数</th>
							<td>
								${rs.dyxqsjts}
							</td>
							<th>第二学期事假天数</th>
							<td>
								${rs.dexqsjts}
							</td>
						</tr>
						<tr>
							<th>第一学期病假天数</th>
							<td>
								${rs.dyxqbjts}
							</td>
							<th>第二学期病假天数</th>
							<td>
								${rs.dexqbjts}
							</td>
						</tr>						
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

