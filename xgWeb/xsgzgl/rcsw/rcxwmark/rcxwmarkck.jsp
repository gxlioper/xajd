<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/rcxwmark/js/rcxwmark.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">

		</script>
		<style type = "text/css">
		
		</style>
	</head>
	<body>
		<html:form action="/rcsw_rcxwmark" method="post" styleId="RcxwmarkForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">学号</th>
							<td width="30%">${rs.xh}</td>
							<th width="20%">姓名</th>
							<td width="30%">${rs.xm}</td>
						</tr>
						<tr>
							<th width="20%">学院</th>
							<td width="30%">${rs.xymc}</td>
							<th width="20%">年级</th>
							<td width="30%">${rs.nj}</td>
						</tr>
						<tr>
							<th width="20%">专业</th>
							<td width="30%">${rs.zymc}</td>
							<th width="20%">班级</th>
							<td width="30%">${rs.bjmc}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>专业奖学金、新生奖学金设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:equal value="yclcx" name="type">
							<tr>
								<th>行为大类</th>
								<td colspan="3">${rs.rcxwlbdlmc}</td>
							</tr>
							<tr>
								<th>行为类别</th>
								<td colspan="3">${rs.rcxwlbmc}</td>
							</tr>
							<tr>
								<th>分值</th>
								<td colspan="3">${rs.fz}</td>
							</tr>
							<tr>
								<th>奖项设置</th>
								<td colspan="3">
									${rs.xmmc}
								</td >
							</tr>
							<tr>
								<th>评奖年度</th>
								<td colspan="3">
									${rs.xn}
								</td >
							</tr>
							<tr>
								<th>备注</th>
								<td colspan="3">
									${rs.bz}
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual value="yclcx" name="type">
							<tr>
								<th>行为大类</th>
								<td colspan="3">${rs.rcxwlbdlmc}</td>
							</tr>
							<tr>
								<th>行为类别</th>
								<td colspan="3">${rs.rcxwlbmc}</td>
							</tr>
							<tr>
								<th>学年</th>
								<td colspan="3">
									${rs.xn}
								</td>
							</tr>
							<tr>
								<th>分值</th>
								<td colspan="3">${rs.fz}</td>
							</tr>
						</logic:notEqual>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
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