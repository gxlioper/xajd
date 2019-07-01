<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>

		<script type="text/javascript">
		
	
		</script>

	</head>
	<body>
		<html:form action="/xszz_qxknsjlgl" method="post"
			styleId="qxknsjlForm">
			
			<div style="height:460px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>

					<thead>
						<tr>
							<th colspan="4">
								<span>困难生认定信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td>
								${knsjgrs.xn}						
							</td>
							<th>申请时间</th>
							<td>	
								${knsjgrs.sqsj}									
							</td>
					    </tr>
					    <tr>
							<th>认定档次</th>
							<td colspan="3">
								${knsjgrs.dcmc}										
							</td>
					    </tr>					  
			      		<tr>
							<th>
							   	认定理由
							</th>
							<td colspan="3">
								${knsjgrs.sqly}		
							</td>
			      		</tr>
					</tbody>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>取消信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>取消人</th>
							<td>
								${knsqxjlrs.czrxm}	
							</td>
							<th>取消时间</th>
							<td>
								${knsqxjlrs.czsj}	
							</td>
					    </tr>
					    <tr>
							<th>取消原因</th>
							<td colspan="3">
								${knsqxjlrs.qxyy}	
							</td>
					    </tr>					 			      		
				  </tbody>
				</table>
				

				</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
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
		</html:form>
	</body>
</html>

