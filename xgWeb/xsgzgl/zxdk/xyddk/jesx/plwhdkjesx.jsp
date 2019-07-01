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
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/jesx/js/jesx.js"></script>	
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/gjdk_jesx" method="post" styleId="JesxForm">
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>贷款上限维护</span> 
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="text-align:center">学历层次</th>
							<th style="text-align:center"><font class="red">*</font>贷款额度上限</th>
						</tr>
						<logic:iterate id="i" name="xljesxList">
							<tr>
								<td>${i.xlccmc}<input type="hidden" value="${i.xlccdm}" name="xlccdm" /></td>
								<td><input type="text" style="width:98%" name="jesx" maxlength="10" value="${i.jesx}" onkeyup="checkInput(this)" /></td>
							</tr>
						</logic:iterate>
						<tr>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
							<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveRs();">
										保    存
									</button>
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