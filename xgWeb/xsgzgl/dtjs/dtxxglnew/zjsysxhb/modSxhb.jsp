<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/dtxxglnew/js/sxhb.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>	
	</head>
	<body>
		<html:form action="/zjsy_sxhb" method="post" styleId="zjsySxhbForm" onsubmit="return false;">
		    <html:hidden property="sxhbid" styleId="sxhbid"/>
			<html:hidden property="xh" styleId="xh"/>
			<html:hidden property="sjsj" styleId="sjsj"/>
			<div style='tab;width:100%;height:300px;overflow-x:hidden;overflow-y:auto;' >
				<table width="100%" border="1" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
						<span>学生基本信息</span>
						</th>
					</tr>
					</thead>
						<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
							<span>思想汇报信息信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th><span class="red">*</span>上交份数</th>
							<td>
								<html:text property="sjfs" styleId="sjfs" style="width:140px;" 
								maxlength="2" styleClass="text_nor" onblur="if(value != '') {value=parseInt(value,10)}" 
								onkeyup="checkInputData(this);return false;"></html:text>
							</td>
							<th>上交时间</th>
							<td>
							    <span >${zjsySxhbForm.sjsj}</span>
							</td>
					    </tr>
					</tbody>
				</table>
			</div>
			<div style="height: 15px"></div>
			<table width="100%" border="0" class="formlist"
				style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm('modSxhb','update');">
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