<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		function saveWbdyy(){
			var wbdyy = jQuery("#wbdyy").val();
			var wbdlbdm = jQuery("#wbdlbdm").val();
			var yjbdsj = jQuery("#yjbdsj").val();
			if(!checkNull("wbdyy-wbdlbdm")){
				return false;
			}
			var api = frameElement.api;
			W = api.opener;
			W.saveWbdyy(wbdyy,wbdlbdm,yjbdsj);
			closeDialog();
			}
		</script>
	</head>
	<body>
		<html:form action="/xsxx_xqbdzcgl" method="post" styleId="xqbdzcForm">
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="saveWbdyy();">
										确定 
									</button>
									
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
							<tr>
							<th align="right">
								<span class="red">*</span>未注册类别
							</th>
							<td align="left">
								<html:select property="wbdlbdm" styleId="wbdlbdm" disabled="false" style="width:125px;">
								<option value=''></option>
								<html:options collection="wbdlbList" property="wbdlbdm"
									labelProperty="wbdlbmc" />
								</html:select>
							</td>
							
							<th align="right" >预计报到时间 </th>
							<td width="34%" align="left">
								<html:text property="yjbdsj" styleId="yjbdsj" readonly="true" onclick="return showCalendar('yjbdsj','y-mm-dd');" style="width:70%"></html:text>
							</td>
							</tr>
						<tr>
							<th>
								<font color="red">*</font>
								未<bean:message key="lable.bdzc"/>原因
								<br/>
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/xsxx/bdzc/10511/cyyy.jsp?id=wbdyy" />
								<html:textarea property="wbdyy" style="width:98%;margin-top:5px;" rows="5"
											   onblur="checkLen(this,200);" styleId="wbdyy"
								></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>

