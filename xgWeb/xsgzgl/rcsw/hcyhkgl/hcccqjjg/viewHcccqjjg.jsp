<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>

	</head>
	<body>

		<html:form action="/rcsw_rcxwwh_rcxwxxwhgl" method="post"
			styleId="rcxwxxwhForm">
			<div style='tab;width:100%;height:400px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>火车乘车区间信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
						<td colspan="4">
							<div style="height: 120px; overflow-x: hidden; overflow-y: auto;">
								<table class="formList" width="100%">
									<thead>
										<tr align="left">
											<td align="center">
												学年
											</td>
											<td align="center">
												学期
											</td>
											<td align="center">
												乘车起始站
											</td>
											<logic:equal name="xxdm" value="10351">
												<td align="center">
													申请原因
												</td>
										    </logic:equal>
											<td align="center">
												备注
											</td>
										</tr>
									</thead>
									<logic:empty name="rsArrList">
										<tr>
											<td align="left" colspan="6">
												该学生无火车乘车区间结果！
											</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="rsArrList">
										<logic:iterate name="rsArrList" id="s">
											<tr>
												<!-- 显示信息 -->
												<logic:iterate id="v" name="s">
													<td align="center">
														${v}
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
							</div>
						</td>
			      		</tr>
					</tbody>
				</table>
			</div>
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
		</table>
		</html:form>
	</body>
</html>

