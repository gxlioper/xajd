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
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/zcfcx" method="post" styleId="ZcfForm">
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
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
								<span>综测品行加减分列表</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						<td colspan="4" width="100%">
							<table width="100%">
								<tbody width="100%">
								 <tr width="100%">
								 	<td width="25%">学年</td>
									<td width="25%">学期</td>
									<td width="25%">项目名称</td>
									<td width="25%">加减分</td>
								 </tr>
								
								 	<logic:iterate  name="zcflist" id="i">
								 	 <tr width="100%">
								 		<td width="25%">${i.xn}</td>
								 		<td width="25%">${i.xqmc}</td>
								 		<td width="25%">${i.rcxwlbmc}</td>
								 		<td width="25%">${i.sumfz}</td>
								 		</tr>
									</logic:iterate>
								 
									
								</tbody>
							</table>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>综测分汇总信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>综测分</th>
							<td>${zcfinfo.zhszf}</td>
							<th>综测分排名</th>
							<td>${zcfinfo.zhszfpm}</td>
						</tr>
						<tr>
							<th>学年平均成绩</th>
							<td>${zcfinfo.xnpjcj}</td>
							<th>学年平均成绩排名</th>
							<td>${zcfinfo.xnpjcjpm}</td>
						</tr>
						<tr>
							<th>综测品行分加分</th>
							<td>${jkfinfo.jf}</td>
							<th>综测品行分减分</th>
							<td>${jkfinfo.kf}</td>
						</tr>
						<tr>
							<th>综测品行分总分</th>
							<td>${jkfinfo.zf}</td>
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
								<div class="bz">
								</div>
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