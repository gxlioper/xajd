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
							<th colspan="2">
								<span>留校项目设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="30%" >留校项目名称</th>
							<td width="70%">
								${rs.xmmc}
							</td>
						</tr>
						<tr>
							<th>留校起止时间</th>
							<td>
								${rs.lxkssj}&nbsp;&nbsp;至&nbsp;&nbsp;${rs.lxjssj}
							</td>
						</tr>
						<tr>
							<th>留校项目说明</th>
							<td>
								${rs.lxxmsm}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
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