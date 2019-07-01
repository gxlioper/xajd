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
		<script type="text/javascript">
			jQuery(function)(){
				
			})
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/hdkhgl_hddj" method="post" styleId="HdkhglForm">
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>活动信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td>${data.xn}</td>
							<th>学期</th>
							<td>${data.xqmc}</td>
						</tr>
						<tr>
							<th>活动名称</th>
							<td>${data.xmmc}</td>
							<th>活动时间</th>
							<td>${data.hdkssj}&nbsp;至&nbsp;${data.hdjssj }</td>
						</tr>
						<tr>
							<th>活动类别</th>
							<td>${data.lbmc }</td>
							<th>活动地点</th>
							<td>${data.hddd}</td>
						</tr>
						<tr >
							<th>是否参加</th>
							<td colspan="3">
								${xskqdj.sfcj}
							</td>
						</tr>
						<logic:equal value="否" name = "sfcj">
						<tr >
							<th>缺勤原因</th>
							<td colspan="3">
								${xskqdj.qqyy}
							</td>
						</tr>
						</logic:equal>
					</tbody>
				</table>
			</div>
	
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
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