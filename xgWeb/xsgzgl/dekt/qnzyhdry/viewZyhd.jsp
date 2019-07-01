<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">	
	
	</script>
</head>
<body>
	<html:form action="/zyhdry" method="post" styleId="qnzyryForm">
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
				 </table>
		
			<table width="100%" border="0" class="formlist">
			<thead>
					<tr>
						<th colspan="4">
							<span>活动信息</span>
						</th>
					</tr>
			</thead>				
			<tbody>
				<tr>
					<th width="15%">
						负责人
					</th>
					<td width="35%">
						${data.fzrxm}
					</td>
					<th width="15%">
						组织部门
					</th>
					<td width="35%">
						${data.zzbm}
					</td>
				</tr>
				<tr>
					<th>
						活动开始时间
					</th>
					<td>
						${data.hdkssj}
					</td>
					<th>
						活动结束时间
					</th>
					<td>
						${data.hdjssj}
					</td>
				</tr>
				<tr>
					<th>
						活动地点
					</th>
					<td colspan="3">
						${data.hddd}
					</td>
				</tr>			
			</tbody>
			<logic:notEqual value="0" name="data" property="gsshzt">
				<thead>
					<tr>
						<th colspan="4">
							<span>审核信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<tr>
							<th>
								服务工时
							</th>
							<td colspan="3">
								${data.gs}小时 
							</td>
						</tr>
					</tr>
					<tr>
						<tr>
							<th>
								审核结果
							</th>
							<td colspan="3">
								${data.gsshztmc}
							</td>
						</tr>
					</tr>
					<tr>
						<th width="20%">
							 审核意见
						</th>
						<td colspan="3">
							${data.gsshyj}
						</td>
					</tr>
				</tbody>
			</logic:notEqual>
					<!-- 杭州师范大学个性化 -->
					<logic:equal value="10346" name="xxdm">					
						<thead>
							<tr>
								<th colspan="4">
									<span>评价信息</span>
								</th>
							</tr>
						</thead>
						<tbody>					
							<tr>
								<th>评价结果</th>
								<td colspan="3" >
									${data.pjjg}
								</td>
							</tr>
							<tr>
								<th>
									评价备注
								</th>
								<td colspan="3">
									${data.pjbz}
								</td>
							</tr>
						</tbody>
					</logic:equal>				
			</table>
		</div>
		<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
