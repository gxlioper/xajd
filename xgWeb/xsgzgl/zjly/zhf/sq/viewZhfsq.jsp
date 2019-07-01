<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/zhf/js/zhfsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
						
		</script>
	</head>
	<body>
		<html:form action="/zhf_sq" method="post" styleId="form">
			<html:hidden property="id"/>
			<html:hidden property="xh"/>		
			<div style='overflow-x:hidden;overflow-y:auto;height:410px;margin-bottom: 0px;'>
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
								<span>计分项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								所属模块
							</th>
							<td>
								${model.xmmkmc}
							</td>
							<th>
								计分项目
							</th>
							<td>
								${model.jfxmmc}		
							</td>
						</tr>
						<tr>
							<th>
								考核要点
							</th>
							<td>
								${model.khyd}
							</td>
							<th>
								分数
							</th>
							<td>
								${model.fs}
							</td>
						</tr>
						<tr>
							<th>
								参与或参加时间
							</th>
							<td>
								${model.cysj}
							</td>
							<th>
								事项说明
							</th>
							<td>
								${model.sxsm}
							</td>
						</tr>
						<tr>
							<th>
								导入人
							</th>
							<td>
								${model.lrr}
							</td>
							<th>
								导入时间
							</th>
							<td>
								${model.lrsj}
							</td>
						</tr>
					<logic:notEmpty name="model" property="shr">
						<tr>
							<th>
								审核人
							</th>
							<td>
								${model.shr}
							</td>
							<th>
								审核时间
							</th>
							<td>
								${model.shsj}
							</td>
						</tr>
						</logic:notEmpty>
						<logic:notEmpty name="model" property="thyj">
							<tr>
								<th>
									退回意见
								</th>
								<td colspan="3">					    		
						    		${model.thyj}
						    	</td>
							</tr>
						</logic:notEmpty>	
						<logic:notEmpty name="model" property="fj">
							<tr>
								<th>
									佐证材料
								</th>
								<td colspan="3" id="fileTd" rowspan="5">					    		
						    		<a href="javascript:void(0);" onclick="window.open('zhf_sq.do?method=downloadFile&id=${model.id }');return false;" class="name">下载</a>&nbsp;${model.fjmc }
						    	</td>
							</tr>
						</logic:notEmpty>					
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
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

