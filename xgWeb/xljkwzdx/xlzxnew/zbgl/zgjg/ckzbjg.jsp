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
		<script type="text/javascript" src="xljkwzdx/xlzxnew/zbgl/comm/js/comm.js"></script>
		<script type="text/javascript" defer="defer">

		</script>
	</head>
	<body>
		<html:form action="/xlzxnew_zbjg" method="post" styleId="ZbjgForm">
			<html:hidden property="sbzbid"  value="${zbrc.zbid}"/>
			<%--<html:hidden property="splcid" value="${sbxx.splcid}"/>
			<html:hidden property="splcidpz" value="${sbxx.splcidpz}"/>
			--%><div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>上报信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
							<tr>
								<th width="20%">
									学年
								</th>
								<td width="30%">
									${zbrc.xn}<input type="hidden" name="xn" />
								</td>
								<th width="20%">
									学期
								</th>
								<td width="30%">
									${xqmc}<input type="hidden" name="xq" />
								</td>	
							</tr>
							<tr>
								<th>
									周次
								</th>
								<td>
									${zbrc.zbzc}
								</td>
								<th>
									起止日期
								</th>
								<td>
									${zbrc.zbksrq} 至  ${zbrc.zbjsrq}
								</td>
							</tr>
							<tr>
								<th>班级名称</th>
								<td>${bjxx.bjmc}
									<input type="hidden" name="bjdm" id="bjdm" value="${bjxx.bjdm}"/>
								</td>
								<th>班主任</th>
								<td>${bjxx.xm}</td>
							</tr>
						<tr>
							<th>
								本周内班级发生的<br />
								重大事件<br />
							</th>
							<td colspan="3" style="word-break: break-all;">
								${ZbjgForm.ztqk}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>个别情况描述</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th colspan="4">
								<table width="100%" >
									<thead>
										<tr>
											<th width='20%' style="text-align:center">学号</th>
											<th width='20%' style="text-align:center">姓名</th>
											<th width='20%' style="text-align:center">性别</th>
											<th width='40%' style="text-align:center">主要问题描述</th>
										</tr>
									</thead>
									<tbody id="tablebody">
										<logic:iterate id="i" name="wtryInfo">
											<tr>
												<td style="text-align:center">${i.xh}</td>
												<td style="text-align:center">${i.xm}</td>
												<td style="text-align:center">${i.xb}</td>
												<td style="text-align:center">${i.zbwtms}</td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
							</th>
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
								<div class="btn">
									<button type="button" name="关 闭" onclick="iFClose();">
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

