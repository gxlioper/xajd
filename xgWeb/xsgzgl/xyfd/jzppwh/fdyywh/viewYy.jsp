
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
</head>
<body style="width:100%">
<html:form action="/xyfd_fqyy" method="post" styleId="demoForm">
	<input type="hidden" id="yyid" name="yyid" value="${model.yyid}"/>
	<div style='width:100%;height:500px;overflow-x:hidden;overflow-y:auto;'>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
					<span>预约学生信息</span>
				</th>
			</tr>
			</thead>
			<%@ include file="/xsgzgl/xyfd/jzppwh/fdyywh/selectStudentforFdyy.jsp" %>
			<thead>
			<tr>
				<th colspan="4">
					<span>预约课程</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="20%"><span class="red">*</span>课程名称</th>
				<td width="30%">
					<input type="hidden" id="fdkc" name="fdkc" value="${fdkcxx.jgid}"/>
						${fdkcxx.kcmc}
				</td>
				<th width="20%">辅导地点</th>
				<td width="30%">
						${fdkcxx.fdsdd}
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>辅导时间</th>
				<td colspan="3">
					${model.fdsj}
				</td>
			</tr>
			</tbody>
			<thead>
			<tr>
				<th colspan="4">
					<span>辅导教师【${fdkcxx.xm}】的排班</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td colspan="4">
					<table width="100%">
						<tbody>
						<tr>
							<th width="14%">周一</th>
							<th width="14%">周二</th>
							<th width="14%">周三</th>
							<th width="14%">周四</th>
							<th width="14%">周五</th>
							<th width="14%">周六</th>
							<th width="14%">周日</th>
						</tr>
						<tr>
							<td>${fdkcxx.mon}</td>
							<td>${fdkcxx.tues}</td>
							<td>${fdkcxx.wed}</td>
							<td>${fdkcxx.thur}</td>
							<td>${fdkcxx.fri}</td>
							<td>${fdkcxx.sat}</td>
							<td>${fdkcxx.sun}</td>
						</tr>
						</tbody>
					</table>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
	<div style="position:fixed;bottom:0;width: 100%">
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
	</div>
</html:form>
</body>
</html>

