<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/mrgzkhKhjg" method="post" styleId="GzkhKhjgForm">
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
								<span>留校名单信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>留校项目名称</th>
							<td>
								${zsjgxx.xmmc}
							</td>
							<th>留校起止时间</th>
							<td>
								${zsjgxx.lxkssj}&nbsp;至&nbsp;${zsjgxx.lxjssj}
							</td>
						</tr>
						<tr>
							<th>留校项目说明</th>
							<td colspan="3">
								${zsjgxx.lxxmsm}
							</td>
						</tr>
						<tr>
							<th>留校情况说明</th>
							<td colspan="3">
								${rs.lxqksm}
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