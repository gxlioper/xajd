<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body onload="">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<html:form action="/data_search" method="post">
			<input type="hidden" name="xxdm" id="xxdm"
				value="<bean:write name="xxdm" />" />
			
			<div class="tab">
				<table width="98%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生成绩 ${xn}学年</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th align="center">
							学期
						</th>
						<th align="center">
							课程
						</th>
						<th align="center">
							成绩
						</th>
						<th align="center">
							课程性质
						</th>
					</tr>
					<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand" align="left"
								ondblclick="getFdyInfo('view')">
								<td>
									<bean:write name="s" property="xqmc" />
								</td>
								<td>
									<bean:write name="s" property="kcmc" />
								</td>
								<td>
									<bean:write name="s" property="cj" />
								</td>
								<td>
									<bean:write name="s" property="kcxz" />
								</td>
							</tr>
					</tbody>
					</logic:iterate>
					</html:form>
	</body>
</html>
