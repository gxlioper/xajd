<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	</head>
	<body>
		<html:form action="/jyweb" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>单位消息回复查看</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="reset" onclick="Close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">
								单位
							</th>
							<td>
								${rs.dwmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								应聘岗位
							</th>
							<td>
								${rs.gwmc }
							</td>
						</tr>
						<tr>
							<th width="20%">
								回复时间
							</th>
							<td>
								${rs.sj }
							</td>
						</tr>
						<tr>
							<th width="20%">
								内容
							</th>
							<td style="word-break:break-all;">
								<html:textarea property="hfxx" style="width:95%" rows="12"
									readonly="true" value="${rs.hfxx}"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
