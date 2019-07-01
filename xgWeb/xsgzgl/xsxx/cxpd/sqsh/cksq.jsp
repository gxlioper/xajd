<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<html:form action="/cxpdSqsh" method="post" styleId="cxpdForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
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
								<span>操行等级评定</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td>
								${cxpdSqshForm.xn }
							</td>
							<logic:notEqual name="xxdm" value="13943">
								<th>学期</th>
								<td>
									${cxpdSqshForm.xqmc }
								</td>
							</logic:notEqual>
							<logic:equal name="xxdm" value="13943">
								<th>评语日期</th>
								<td>
									${cxpdSqshForm.sqsj }
								</td>
							</logic:equal>					
						</tr>
						<tr>
							<th>操行等级</th>
							<td>
								${cxpdSqshForm.cxdjmc }
							</td>
							<th>班主任</th>
							<td>
								${cxpdSqshForm.bzr }
							</td>
						</tr>
						<tr>
							<th>
								操行评语<br/>
							</th>
							<td colspan="3">
								${cxpdSqshForm.cxpy }
							</td>
						</tr>
					</tbody>
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

