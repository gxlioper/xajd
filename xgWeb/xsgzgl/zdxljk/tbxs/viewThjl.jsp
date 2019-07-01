<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/zdxljkTbxs" method="post" styleId="tbxsForm">
			<div style='overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;'>
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
								<span>谈话记录
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="dataTbody">
						
						<logic:notEmpty name="thjlList">
						<tr><td colspan="4"><table class="dateline" width="100%" ><tbody>
						<tr class="gzlxTr">
											<th width="16%">关注类型</th>
											<td colspan="3" style="text-align:left;">
												${model.gzlx}
											</td>
										</tr>
									
										<logic:notEmpty name="model" property="qxyy">
											<tr>
											<th width="16%">取消关注原因</th>
											<td colspan="3" style="text-align:left;">
												${model.qxyy}
											</td>
											</tr>
										</logic:notEmpty>
										</tbody></table></td></tr>
							<logic:iterate id="t" name="thjlList">
							<tr>
								<td colspan="4">
									<table class="dateline" width="100%" >
										<tbody>
											<tr>
												<th width="16%">操作时间</th>
												<td colspan="3" style="text-align:left;">
													${t.czsj}
												</td>
											</tr>
											<tr>
												<input type="hidden" name="canUpdateArr" value="N"/>
												<th width="16%">访谈人</th>
												<td style="text-align:left;">
													${t.ftr}
												</td>
												<th width="16%">谈话时间</th>
												<td>
													${t.thsj}
												</td>
											</tr>
											<tr>
												<th width="16%">学生类型</th>
												<td colspan="3">
													${t.gxlx}
												</td>
											</tr>
											<tr>
												<th width="16%">谈话内容</th>
												<td colspan="3">
													${t.thnr}
												</td>
											</tr>
											<tr>
												<th width="16%">帮扶措施</th>
												<td colspan="3">
													${t.cljg}
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							</logic:iterate>
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

