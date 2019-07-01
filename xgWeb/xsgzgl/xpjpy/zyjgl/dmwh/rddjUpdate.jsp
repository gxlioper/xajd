<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%><%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zyjgl/dmwh/js/dmwh.js"></script>
	</head>
	<body>
		<html:form action="/pjpy_zyjgldmwh" styleId="ZyjrddjwhForm" method="post">
		<html:hidden property="rddjdm" styleId="rddjdm"/>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>认定等级名称</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:30%">
								<font color="red">*</font>
								认定等级名称
							</th>
							<td style="width:70%">
								<html:text property="rddjmc" styleId="rddjmc" style="width:235px" maxlength="100"/>
							</td>
						</tr>
					</tbody>
				</table>	
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<font color="red">*</font>"为必填项
								</div>
								<div class="btn">
									<button class="button2" id="btn_bc" type="button" onclick="saveUpdate()">
										保 存
									</button>
									<button class="button2" type="button" onclick="Close()">
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
