<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
	</head>
	<body>
		<html:form action="/zxdkSyddk" method="post" styleId="xyddkForm">
			<input type="hidden" name="xz" id="xz" value="${jbxx.xz }"/>
			<html:hidden property="id" />
			
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
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
								<span>贷款信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>贷款学年</th>
							<td>
								${zxdkSyddkForm.xn }
							</td>
							<th>贷款银行</th>
							<td>
								${zxdkSyddkForm.yhmc }
							</td>
						</tr>
						<tr>
							<th>贷款金额（元）</th>
							<td>
								${zxdkSyddkForm.dkje }
							</td>
							<th>贷款期限（月）</th>
							<td>
								${zxdkSyddkForm.dkqx }
							</td>
						</tr>
						<logic:notEqual name="xxdm" value="10264">
							<tr>
								<th>住宿费应收数（元）</th>
								<td>
									${zxdkSyddkForm.zsfyss }
								</td>
								<th>学费应收数（元）</th>
								<td>
									${zxdkSyddkForm.xfyss }
								</td>
							</tr>
						</logic:notEqual>
						<tr>
							<th>合同编号</th>
							<td>
								${zxdkSyddkForm.htbh }
							</td>
							<th>贷款开始时间</th>
							<td>
								${zxdkSyddkForm.dkkssj }
							</td>
						</tr>
						<logic:equal name="xxdm" value="12389">
							<tr>
								<th>贷款次数</th>
								<td>
									${zxdkSyddkForm.dkcs }
								</td>
								<th>到期日期</th>
								<td>
									${zxdkSyddkForm.dqrq }
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th>回执检验码</th>
							<td colspan="3">
								${zxdkSyddkForm.hzjym }
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<table style="width:100%;text-align:center;" >
									<thead>
										<tr>
											<td align="center">学年</td>
											<td align="center">学费（元）</td>
											<td align="center">住宿费（元）</td>
											<td align="center">生活费（元）</td>
										</tr>
									</thead>
									<tbody id="dkxxTable">
										<logic:present name="dkxxList">
											<logic:iterate id="d" name="dkxxList">
												<tr>
													<td>
														${d.xn }
													</td>
													<td>
														${d.xf }
													</td>
													<td>
														${d.zsf }
													</td>
													<td>
														${d.shf }
													</td>
												</tr>
											</logic:iterate>
										</logic:present>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<th>申请理由</th>
							<td colspan="3">
								${zxdkSyddkForm.sqly }
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table class="formlist">
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
		</html:form>
	</body>
	
</html>