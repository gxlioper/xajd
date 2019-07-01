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
		<script type="text/javascript" src="xsgzgl/zjly/zhf/js/zhfsh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/zhf_sh" method="post" styleId="form">		
			<div style='overflow-x:hidden;overflow-y:auto;height:500px;margin-bottom: 0px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<logic:notEmpty name="ysdList">
						<thead>										
							<tr>
								<th colspan="4">
									<span>已审核计分项目信息</span>
								</th>
							</tr>					
						</thead>
					<tbody>
						<tr>
							<td colspan="8">
								<table class="dateline" width="100%">
									<thead>
										<tr>
											<td style="text-align: center;width:10%;">所属模块 </td>
											<td style="text-align: center;width:20%;"">计分项目</td>
											<td style="text-align: center;width:5%">分数 </td>
											<td style="text-align: center;width:20%">事项名称</td>
											<td style="text-align: center;width:10%">类别</td>
											<td style="text-align: center;width:10%">参与活动时间</td>
											<td style="text-align: center;width:15%">佐证材料 </td>
										</tr>
									</thead>
									<tbody>
									<logic:iterate id="y" name="ysdList">
										<tr>
											<td>
												${y.xmmkmc}
											</td>
											<td>
												${y.jfxmmc}
											</td>
											<td>
												${y.fs}
											</td>
											<td>
												${y.sxsm}
											</td>
											<td>
												${y.lb}
											</td>
											<td>
												${y.cysj}
											</td>
											<td>
												<a href="javascript:void(0);" onclick="window.open('zhf_sq.do?method=downloadFile&id=${y.id}');return false;" class="name">${y.fjmc}</a> 
											</td>
										</tr>
									</logic:iterate>							
									</tbody>
								</table>
							</td>
						</tr>				
					</tbody>
					</logic:notEmpty>
					<logic:notEmpty name="thList">
					<thead>
						<tr>
							<th colspan="4">
								<span>退回计分项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="8">
								<table class="dateline" width="100%">
									<thead>
										<tr>
											<td style="text-align: center;width:10%;">所属模块 </td>
											<td style="text-align: center;width:20%;"">计分项目</td>
											<td style="text-align: center;width:5%">分数 </td>
											<td style="text-align: center;width:20%">事项名称</td>
											<td style="text-align: center;width:10%">类别</td>
											<td style="text-align: center;width:10%">参与活动时间</td>
											<td style="text-align: center;width:15%">佐证材料 </td>
										</tr>
									</thead>
										<tbody id="ycz">			
										<logic:iterate id="t" name="thList">
										<tr>
											<input type="hidden" value="${t.id}"/>
											<td>
												${t.xmmkmc}
											</td>
											<td>
												${t.jfxmmc}
											</td>
											<td>
												${t.fs}
											</td>
											<td>
												${t.sxsm}
											</td>
											<td>
												${t.lb}
											</td>
											<td>
												${t.cysj}
											</td>
											<td>
												<a href="javascript:void(0);" onclick="window.open('zhf_sq.do?method=downloadFile&id=${w.id }');return false;" class="name">${w.fjmc}</a> 
											</td>											
										</tr>
									</logic:iterate>
									</tbody>	
								</table>
							</td>
						</tr>
					</tbody>
					</logic:notEmpty>
					<logic:notEmpty name="wsdList">
					<thead>
						<tr>
							<th colspan="4">
								<span>未审核计分项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="8">
								<table class="dateline" width="100%">
									<thead>
										<tr>
											<td style="text-align: center;width:10%;">所属模块 </td>
											<td style="text-align: center;width:20%;"">计分项目</td>
											<td style="text-align: center;width:5%">分数 </td>
											<td style="text-align: center;width:20%">事项名称</td>
											<td style="text-align: center;width:10%">类别</td>
											<td style="text-align: center;width:10%">参与活动时间</td>
											<td style="text-align: center;width:15%">佐证材料 </td>
										</tr>
									</thead>
										<tbody id="ycz">			
										<logic:iterate id="w" name="wsdList">
										<tr>
											<input type="hidden" value="${w.id}"/>
											<td>
												${w.xmmkmc}
											</td>
											<td>
												${w.jfxmmc}
											</td>
											<td>
												${w.fs}
											</td>
											<td>
												${w.sxsm}
											</td>
											<td>
												${w.lb}
											</td>
											<td>
												${w.cysj}
											</td>
											<td>
												<a href="javascript:void(0);" onclick="window.open('zhf_sq.do?method=downloadFile&id=${w.id }');return false;" class="name">${w.fjmc}</a> 
											</td>											
										</tr>
									</logic:iterate>
									</tbody>	
								</table>
							</td>
						</tr>
					</tbody>
					</logic:notEmpty>
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

