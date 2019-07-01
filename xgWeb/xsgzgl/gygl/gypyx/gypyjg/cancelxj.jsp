<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gypyx/gypyjg/js/gypyjg.js"></script>
		<%--<script type="text/javascript" src="xsgzgl/gygl/gypyx/gypysq/js/commUtil.js"></script>--%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form action="/gypynew_gypyjg" method="post" styleId="GypyJgForm">
		<html:hidden property="jgid" styleId="jgid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th><font class="red">*</font>撤星时间</th>
							<td>
								<html:text property="cxsj" styleId="cxsj" maxlength="10" onclick="return showCalendar('cxsj','y-mm-dd');" style="width:150px" />
							</td>
						</tr>
						<tr>
							<th>撤星原因</th>
							<td>
								<html:textarea property="cxyy" styleId="cxyy" onblur="checkLen(this,500)" style="width:90%" rows="10"/>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveCx();">
										保 存
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