<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>	
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script>
		
		</script>
	</head>
	<body>
		<html:form action="/xszz_zzdyzzbtff" method="post"
			styleId="ZzdyBtffForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;height:565px;'>
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>历史发放记录</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
					<td colspan="4">
					<div class="con_overlfow">
					<table class="dateline" width="100%">
						<thead>
						<tr>
							<th width="30%">
								项目名称
							</th>
							<th width="30%">
								发放年月
							</th>
							<th width="40%">
								发放金额
							</th>
						</thead>
						<tbody>
						<logic:iterate id="i" name="ffjlList" indexId="index01">
							<tr>
							<td>${i.xmmc}</td>
							<td>${i.ffyf}</td>
							<td>${i.ffje}</td>
							</tr>
						</logic:iterate>
						<logic:empty name="ffjlList">
						<tr >
							<td colspan="3" align="center">无发放记录!</td>
							</tr>
						</logic:empty>
						</tbody>
						</table>
						</div>
						</td>
						</tr>
					</tbody>
					
					</table>
			      </div>
			      <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" type="button"
										onclick="iFClose();return false;">
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

