<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src='js/calendar/calendar-setup.js'></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xsxwkh/jbfgl/js/jbfgl.js"></script>
			<script type="text/javascript" >
			jQuery(function(){
				jQuery("#bzrcpdj").val('${bzrcpdj}');
				jQuery("#xscpdj").val('${xscpdj}');
			})
		</script>
	</head>
	<body>
	<html:form action="/xsxwkhJbfgl.do" styleId="jbfglForm" method="post"  onsubmit="return false;">
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
								<span>基本分信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						 <tr>
							<th width="16%" >学年 </th>
							
							<td width="34%" colspan="3">
							${xn}
							</td>
						</tr>
						 <tr>
							<th>班主任辅导员测评等级 </th>
							<td>
								<bean:write name="JbfglForm" property="bzrcpdj"/>
							</td>
							<th width="16%">
								班级学生测评等级
							</th>
							<td>
								<bean:write name="JbfglForm" property="xscpdj"/>
							</td>
						</tr>
						<tr >
							<th>总分 </th>
							<td>
								${zf}
							</td>
						
						</tr>
						<tr>
							<th width="16%" rowspan="4">
								备注
								<br>
							</th>
							<td width="34%" colspan="3" rowspan="4">
								${bz}
							</td>
						</tr>
				</tbody>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="refreshParent2();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</table>	
		</html:form>
	</body>
</html>