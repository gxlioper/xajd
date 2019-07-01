<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/kqsq.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/comm.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/zwzxkqKqjg" method="post" styleId="ZwzxKqjgForm" onsubmit="return false;">
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生缺勤信息查看</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学号
							</th>
							<td width="30%">
								${rs.xh}
							</td>
							<th width="20%">
								学年
							</th>
							<td width="30%">
								${rs.xn}
							</td>
						</tr>
						<tr>
							<th width="20%">
								姓名
							</th>
							<td width="30%">
								${rs.xm}
							</td>
							<th width="20%">
								学期
							</th>
							<td width="30%">
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								<bean:message key="lable.xb" />
							</th>
							<td width="30%">
								${rs.xymc}
							</td>
							<th width="20%">
								专业
							</th>
							<td width="30%">
								${rs.zymc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								班级
							</th>
							<td width="30%">
								${rs.bjmc}
							</td>
							<th width="20%">
								抽查日期
							</th>
							<td width="30%">
								${rs.ccrq}
							</td>
						</tr>
						<tr>
							<th width="20%">
								抽查类型
							</th>
							<td width="30%">
								${rs.cclxmc}
							</td>
							<th width="20%">
								缺勤类型
							</th>
							<td width="30%">
								${rs.qqlxmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								旷课节数
							</th>
							<td width="30%">
								${rs.kkjs}
							</td>
						    <th>
								备注
							</th>
							<td colspan="3">
								${rs.ylzd1}
							</td>
						</tr>
						<logic:equal value="11647" name="xxdm">
						<tr>
							<th>
								除缺勤外的违纪人数
								<br />
								<div align="center">
									(如:吃饭,睡觉等...)
								</div>
							</th>
							<td align="left">
								${rs.wjrs}
							</td>
							</tr>
							</logic:equal>
					</tbody>
				 </table>
				</div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

