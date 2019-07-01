<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/tsqktbgl/jg/js/tsqkjg.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/tsqktbgl_jg" method="post" styleId="tsqkjgForm" onsubmit="return false;">
		<html:hidden property="jgid" styleId="jgid"/>
		<html:hidden property="xh" styleId="xh"/>
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
					<thead>
						<tr>
							<th colspan="4">
								<span>通报信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学年
							</th>
							<td width="30%">
								${rs.xn}
							</td>
							</td>
							<th>学期</th>
							<td>
								${rs.xqmc}
							</td>
						</tr>
						<tr>
					    	<th>学情分类一</th>
					    	<td>
								${rs.xqmc1}
							</td>							
							<th>学情分类二</th>
							<td>
								${rs.xqmc2}
							</td>
						</tr>
						<tr>
							<th>通报时间</th>
					    	<td>
								${rs.tbsj}
						    </td>
						    <th>处理层级</th>
					    	<td>
								${rs.clcjmc}
						    </td>
						</tr>
					<logic:equal name="xxdm" value="10026">
						<tr>
							<th>问题紧急程度</th>
					    	<td>
								${rs.wtjjcd}
						   </td>
						 </tr>
					</logic:equal>
								<tr>
									<th>
										<logic:notEqual name="xxdm" value="10026">
											特殊学情
										</logic:notEqual>
										<logic:equal name="xxdm" value="10026">
											该生状况简述
										</logic:equal>
									</th>
									<td colspan="3">${rs.tsxq}</td>
								</tr>
						<tr>
							<th>
							<logic:notEqual name="xxdm" value="10026">
								特殊学情干预情况
							</logic:notEqual>
							<logic:equal name="xxdm" value="10026">
								已采取措施及后续跟进措施
							</logic:equal>
							</th>
							<td colspan="3">
								${rs.tsxqgyqk}
							</td>
			      		</tr>						
					</tbody>
				 </table>			
				</div>
			  <div style="height:30px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

