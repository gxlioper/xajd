<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/rcxwwhnew/rcxwjg/rcxwjg.js"></script>
		<script type="text/javascript">
		
        </script>

	</head>
	<body>

		<html:form action="/rcsw_rcxwwhnew_rcxwxxwhgl" method="post"
			styleId="rcxwxxwhForm">
			<html:hidden property="id" styleId="id" />
			<input type="hidden" id="zq" value="${zq}"/>
			<div style="height:480px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwhnew/comm/viewStudent.jsp"%>

					<thead>
						<tr>
							<th colspan="4">
								<span>
									日常行为记录
								</span>
							</th>
						</tr>
					</thead>

					<tr>
						<td colspan="5">
							<div>
								<table class="formList" width="100%" id="tab_rcxw">
									<thead>
										<tr align="left">
											<td align="center">
												行为记录学年
											</td>
											<logic:equal value="0" name="zq">
											<td align="center">
												行为记录学期
											</td>
											</logic:equal>
											<td align="center">
												行为记录类别
											</td>
											<td align="center">
												行为记录总分
											</td>
											<td align="center" >
												学分
											</td>
										</tr>
									</thead>
									<logic:empty name="rsArrList">
										<tr>
											<td align="center" colspan="5">
												该学生无历史记录！
											</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="rsArrList">
										<logic:iterate name="rsArrList" id="v">
											<tr>
													<td align="center">
														${v.rdnd}
													</td>
													<logic:equal value="0" name="zq">
													<td align="center">
														${v.xqmc}
													</td>
													</logic:equal>
													<td align="center">
														${v.rcxwlbmc}<a data="${v.xh}_${v.xqdm}_${v.rdnd}_${v.rcxwlbdm}" onclick = "showInfo(this);" class="up" href="javascript:void(0);" value="${v.xh}-${v.xqdm}-${v.rdnd}-${v.rcxwlbdm}"/>
													</td>
													<td align="center">
														${v.fz}
													</td>
													<td align="center">
														${v.xf}
													</td>
											</tr>
											<tr>
												<td colspan="5" data="${v.xh}_${v.xqdm}_${v.rdnd}_${v.rcxwlbdm}" type="detail-data" style="display: none;" >
														<table class="formList" width="100%" id="tab_rcxw">
															<thead>
																<tr align="left">
																	<td align="center" width="32%">
																		行为记录大类
																	</td>
																	<td align="center" width="15%">
																		行为记录小类
																	</td>
																	<td align="center" width="20%">
																		行为记录发生时间
																	</td>
																	<td align="center" width="13%">
																		行为记录分值
																	</td>
																</tr>
															</thead>
															<tbody class="tbody_rcxw">
															</tbody>
														</table>
												</td>	
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
									</tbody>
									<%--<tfoot>
										<tr>
											<td colspan="5">
												<div class="btn">
													<button type="button" type="button" onclick="iFClose();">
														关 闭
													</button>
												</div>
											</td>
										</tr>
									</tfoot>
								--%></table>
							</div>
							
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="5">
							<div class="btn">
								<button type="button" type="button" onclick="iFClose();">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		</html:form>
	</body>
</html>

