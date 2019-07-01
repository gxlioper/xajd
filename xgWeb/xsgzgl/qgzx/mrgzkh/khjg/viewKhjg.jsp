<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/mrgzkh/khjg/khjg.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form action="/mrgzkhKhjg" method="post" styleId="GzkhKhjgForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:380px;margin-bottom: 0px;" >
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
								<span>填写信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>用人单位</th>
							<td>
								${gzkhKhjgXx.bmmc}
							</td>
							<th>工时</th>
							<td>
								${gzkhKhjgXx.gs}（小时）
							</td>
						</tr>
						<tr>
							<th>岗位</th>
							<td>
								${gzkhKhjgXx.gwmc}
							</td>
							<th>工作地点</th>
							<td>
								${gzkhKhjgXx.gzdd}
							</td>
						</tr>
						<tr>
						<th>工作日期及时间段</th>
							<td colspan="3">
								${gzkhKhjgXx.gzrq}&nbsp;${gzkhKhjgXx.gzkssj}时到${gzkhKhjgXx.gzjssj}时
							</td>
						</tr>
						<tr>
						
							<th>工作内容</th>
							<td colspan="3">
								${gzkhKhjgXx.gznr }
							</td>
						</tr>
						<tr>
							<th>记录时间</th>
							<td>
								${gzkhKhjgXx.cjsj}
							</td>
							<th>记录人员</th>
							<td>
								${gzkhKhjgXx.czyhxm}
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