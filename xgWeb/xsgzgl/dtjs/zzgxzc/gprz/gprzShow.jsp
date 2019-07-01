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
								<span>修改信息</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th>修改时间</th>
						<td>
							${gprzForm.xgsj }				
						</td>
						<th>修改人</th>
						<td>
							${gprzForm.xgr }
						</td>
					</tr>
					<tr style="height:80px;">
						<th>修改前信息</th>
						<td colspan="3">
							${gprzForm.xgqjl }
						</td>
					</tr>
					<tr style="height:80px;">
						<th>修改后信息</th>
						<td colspan="3">
							${gprzForm.xghjl }
						</td>
					</tr>
					<tr style="height:80px;">
						<th>改派原因</th>
						<td colspan="3">
							${gprzForm.gpyy }
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
