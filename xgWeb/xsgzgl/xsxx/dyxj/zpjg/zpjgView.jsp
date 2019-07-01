<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<html:form action="/xsxxDyxjZpjg" method="post" styleId="form">
			<html:hidden property="id"/>
			
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:280px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>自评信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td>
								${dyxjZpjgModel.xn}
							</td>
							<th>学期</th>
							<td>
								${dyxjZpjgModel.xqmc}
							</td>
						</tr>
						<tr>
							<th>评定等级</th>
							<td colspan="3">
								${dyxjZpjgModel.djmc}
							</td>
						</tr>
						<tr>
							<th>评议内容</th>
							<td colspan="3">
								${dyxjZpjgModel.zpnr}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table class="formlist">
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