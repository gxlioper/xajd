<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
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
								<span>转出结果信息</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th>所在党支部</th>
						<td>
							${xxjgForm.szdzbmc }				
						</td>
						<th>是否省内</th>
						<td>
							${xxjgForm.sfsn }
						</td>
					</tr>
					<tr>
						<th>接收本人组织关系的党组织</th>
						<td colspan="3">
							${xxjgForm.jsdzz }
						</td>
					</tr>
					<tr>
						<th>本人组织关系所去单位</th>
						<td colspan="3">
							${xxjgForm.sqdw }
						</td>
					</tr>
					<tr>
						<th>党费交至日期</th>
						<td >
							${xxjgForm.dfjzrq }
						</td>
						<th>是否需要开具婚姻证明</th>
						<td >
							${xxjgForm.sfkjhyzm}
						</td>
					</tr>
					<tr>
						<th>介绍信编号</th>
						<td colspan="3">
							${xxjgForm.jsxbh }
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
