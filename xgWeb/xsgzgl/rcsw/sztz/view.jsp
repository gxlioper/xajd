<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/ahgf_sztz" method="post" styleId="form">
			<html:hidden property="id" />
			<div style='tab'>
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
								<span>查看学分明细</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								学年
							</th>
							<td>
								${sztzSzxfForm.xn }
							</td>
							<th>
								学期
							</th>
							<td>
								${sztzSzxfForm.xqmc }
							</td>
						</tr>
						<tr>
							<th>
								素质拓展项目
							</th>
							<td>
								${sztzSzxfForm.tzxm }
							</td>
							<th>
								级别
							</th>
							<td>
								${sztzSzxfForm.tzjb }
							</td>
						</tr>
						<tr>
							<th>
								名次
							</th>
							<td>
								${sztzSzxfForm.mc }
							</td>
							<th>
								确认学分
							</th>
							<td>
								${sztzSzxfForm.xf }
							</td>
						</tr>
						<tr>
							<th>
								确认人
							</th>
							<td>
								${sztzSzxfForm.qrr }
							</td>
							<th>
								确认日期
							</th>
							<td>
								${sztzSzxfForm.qrsj }
							</td>
						</tr>
						<tr>
							<th>
								审核人
							</th>
							<td>
								${sztzSzxfForm.shr }
							</td>
							<th>
								录入日期
							</th>
							<td>
								${sztzSzxfForm.lrsj }
							</td>
						</tr>
					</tbody>
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

