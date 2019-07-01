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
			<html:hidden property="lrr" value="${userName }"/>		
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
								<span>计分项目信息
									<a onclick="addRow();" href="javascript:void(0);">
										<img src="images/knsrd/jiahao.gif" />
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="8">
								<table class="dateline" width="100%">
									<thead>
										<tr>
											<td style="text-align: center;width:20%;"><font color="red">*</font>所属模块 </td>
											<td style="text-align: center;width:20%;""><font color="red">*</font>计分项目</td>
											<td style="text-align: center;width:5%">分数 </td>
											<td style="text-align: center;width:20%"><font color="red">*</font>事项名称</td>
											<td style="text-align: center;width:10%"><font color="red">*</font>参与活动时间</td>
											<td style="text-align: center;width:15%">佐证材料 </td>
											<td style="text-align: center;width:10%">操作 </td>
										</tr>
									</thead>
									<tr style="display:none;">
											<td id="xmmkdm">
												<html:select property="cxzd" styleId="cxzd" onchange="getJfxmList(this);" style="width:200px">
													<html:option value="">---请选择---</html:option>
													<html:options collection="xmmkList" property="xmmkdm" labelProperty="xmmkmc"/>
												</html:select>
											</td>
									</tr>
									<tbody id="dataList">
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										保 存
									</button>
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

