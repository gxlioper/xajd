<%@ page language="java" import="java.util.*,xgxt.utils.String.StringUtils" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/qgzx_xsgwsh.do?method=xsgwSh&type=save" method="post" styleId="demoForm">
			<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
					<tr>
						<th colspan="4">
							<span>工资发放信息</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<td colspan="4">
							<style>

								#shlccx_table th{text-align: center;}
								#shlccx_table tr{text-align: center;}
							</style>
							<div class="con_overlfow">
								<table id="shlccx_table" width="100%" class="formlist" >
									<logic:notEmpty name="gzffList">
										<tr>
											<th>序号</th>
											<th>用人单位</th>
											<th>岗位名称</th>
											<th>年份</th>
											<th>月份</th>
											<th>工时</th>
											<th>工资</th>
										</tr>
										<logic:iterate id="i" name="gzffList">
											<tr>
												<td>${i.r}</td>
												<td>${i.yrdwmc}</td>
												<td>${i.gwmc}</td>
												<td>${i.nf}</td>
												<td>${i.yf}</td>
												<td>${i.gs}</td>
												<td>${i.je}</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="gzffList">
										<tr><td>暂无工资发放信息</td></tr>
									</logic:empty>
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
							<td colspan="4" >
								<div class="btn">
									<button type="button" name="关 闭" onclick="iFClose();">
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

