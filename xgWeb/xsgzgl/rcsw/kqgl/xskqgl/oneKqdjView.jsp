<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/kqgl/xskqgl/js/xskqgl.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		
	</head>
		
	<body>
		
		<html:form action="/rcsw_kqgl_xskqgl" method="post" styleId="KqdjForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;' >
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
							<span>考勤信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th>学年学期</th>
							<td>
								${rs.xn }
								${rs.xqmc }
							</td>
							<th>周次</th>
							<td>
								第${rs.zc }周
							</td>
					    </tr>
					    <tr>
							<th>考勤课程</th>
							<td>
								${rs.kqkc }
							</td>
							<th>上课地点</th>
							<td>
								${rs.dd }

							</td>
						</tr>
						<tr>
							<th>考勤时间</th>
							<td>
								${rs.kqsj }
							</td>
					    
							<th>考勤类型</th>
							<td>
								${rs.kqlxmc }
							</td>
						</tr>
					    <tr>
							<th>
								考勤情况
								<br />
							</th>
							<td colspan="3">
								${rs.kqqk }
							</td>
			      		</tr>
					</tbody>
				</table>
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

