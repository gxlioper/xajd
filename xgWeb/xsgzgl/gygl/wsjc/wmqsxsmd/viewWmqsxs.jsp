<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/wsjc/wmqsxsmd/js/wmqsxsmd.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			
			
		})
		
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form method="post" styleId="wmqsxsmdForm" action="/gyglnew_wmqsxsmd_12688">
		<html:hidden property="id" styleId="id" />
		<html:hidden property="lrr" styleId="lrr"  />
		<html:hidden property="lrsj" styleId="lrsj" />
			<div	style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>文明寝室信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								学年
							</th>
							<td>
								${model.xn }
							</td>
							<th>
								学期
							</th>
							<td >
								${model.xq}
							</td>
						</tr>
						<tr>
							<th>
								录入人
							</th>
							<td >
								${model.lrr}
							</td>
							<th>
								录入时间
							</th>
							<td >
								${model.lrsj}
							</td>
						</tr>
						<tr>
							<th>
								校级分数
							</th>
							<td >
								${model.xjfs}
							</td>
							<th>
								院级分数
							</th>
							<td >
								${model.yjfs}
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3">
								${model.bz}
							</td>
						</tr>
							
					</tbody>
				</table>
			</div>
			<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
								<div class="btn">
									<%--<button type="button" onclick="save('edit');" id="buttonSave">
										保 存
									</button>
									--%><button type="button" onclick="iFClose();"  id="buttonClose">
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