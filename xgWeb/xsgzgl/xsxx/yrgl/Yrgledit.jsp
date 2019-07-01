<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src='js/calendar/calendar-setup.js'></script>
		<script type="text/javascript" src="xsgzgl/xsxx/yrgl/js/yrgl.js"></script>
	</head>
	<body>
	<html:form action="/xsxx_yrgl.do" styleId="yrglForm" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="guid"/>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
							<th style="width:15%">学号</th>
							<td style="width:35%">
								<html:hidden property="xh" styleId="xh"/>
								<bean:write name="jbxx" property="xh"/>
							</td>
							<th style="width:15%">姓名</th>
							<td style="width:35%">
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xm"/>
								</logic:present>
							</td>
						</tr>
						
						<tr>
							<th>年级</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="nj"/>
								</logic:present>
							</td>
							<th>学院</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xymc"/>
								</logic:present>
							</td>
						</tr>
						
						<tr>
							<th>专业</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="zymc"/>
								</logic:present>
							</td>
							<th>班级</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="bjmc"/>
								</logic:present>
							</td>
						</tr>
						</tbody>
						<thead>
							<tr>
								<th colspan="4">
									<span>蕴瑞成员修改</span>
								</th>
							</tr>
						</thead>
				<tbody>	
						<tr>
							<th><span class="red">*</span>申请学年</th>
							<td>
								<html:select  property="xn" styleId="xn" style="width:130px">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
						<tr>
						<tr>
							<th>
								申请理由
								<br /><font color="red">&lt;限400字&gt;</font>
							</th>
							<td width="34%" colspan="3" rowspan="4">
								<html:textarea property="sqly" onblur="chLengs(this,400);" styleId="sqly" rows="4" style="width:99%" ></html:textarea>
							</td>
						</tr>
				</tbody>
				<table border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveUpdate();">
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
		</html:form>
	</body>
</html>