<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/xsgbgl/js/zwsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
				
				//为button注册事件
				//jQuery("#xzpxxm").load('szdw_fdypxxmwh.do?method=fdypxxmxzView&xmdm=')
				//jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
			});
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/rcsw_xsxxgl.do?method=zjXxgl" method="post" styleId="demoForm">
			<div style='tab'>
			<html:hidden property="sqid" name="model" styleId="sqid"/>
								<html:hidden property="shzt" name="model" styleId="shzt"/>
								<html:hidden property="splc" name="model" styleId="splc"/>
								<html:hidden property="shid" name="myForm" styleId="shid"/>
				<table width="100%" border="0" class="formlist">
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
								<span>学生干部职务信息</span> 
							</th>
						</tr>
					</thead>
					<tbody id="tbody_zwxx">
						<tr>
							<th width="16%">
								职务类型
							</th>
							<td>
								${zwlxmodel.lxmc }
							</td>
							<th width="16%">
								职务名称
							</th>
							<td>
								${zwmodel.zwmc}						
							</td>
						</tr>
						<tr>
							<th width="16%">
								职务职责
							</th>
							<td colspan="3" >
								${zwmodel.zwzz}
							</td>
						</tr>
						<tr>
							<th width="16%">
								备注
							</th>
							<td colspan="3">
								${zwmodel.bz}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_sqly">
						<tr>
							<th >
								申请时间
							</th>
							<td width="34%" colspan="3">
								${model.sqsj}
							</td>
						</tr>
						<tr>
							<th >
								申请理由
							</th>
							<td width="34%" colspan="3">
								${model.sqly }
							</td>
						</tr>
						
					</tbody>
					<thead>
				<tr>
					<th colspan="4">
						<span>审批信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="4" id="shlccx">
					
					</td>
				</tr>
			</tbody>
					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<div class="btn">
										
										<button type="button" name="关 闭" onclick="iFClose();">
											关 闭
										</button>
									</div>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

