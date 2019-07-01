<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<html:form action="/jddj_jdqk" method="post" styleId="jdqkForm">
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
								<span>等级鉴定情况</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td>
								${jdqkForm.xn }
							</td>
							<th>学期</th>
							<td>
								${jdqkForm.xqmc }
							</td>
						</tr>
						<tr>
							<th>项目</th>
							<td>
								${jdqkForm.xmmc }
							</td>
							<th>级别</th>
							<td>
								${jdqkForm.jbmc }
							</td>
						</tr>
						<tr>
							<th>证书编号</th>
							<td colspan="3">
								${jdqkForm.zsbh }
							</td>
						</tr>
					</tbody>
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
			</div>
		</html:form>
	</body>
</html>

