<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/ggwpgl/js/ggwpjy.js"></script>
		<script type="text/javascript">
					
		</script>
	</head>
	<body>
		<html:form action="/xgygl_ggwpjydj" method="post" styleId="ggwpjyForm">
			<html:hidden property="id" />
			<html:hidden property="xh" />
			<div style='overflow-x:hidden;overflow-y:auto;height:360px;margin-bottom: 0px;'>
				<table width="100%" border="0" class="formlist">
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
								<span>公共物品借用信息
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>设备分类</th>
							<td>
								<html:select property="fldm" styleId="fldm" onchange="getSbxxForUpdate(this);">
									<html:option value=""></html:option>
									<html:options collection="sbflList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th>
								<font color="red">*</font>设备名称
							</th>
							<td>
								<html:select property="sbdm" styleId="sbdm">
									<html:option value=""></html:option>
									<html:options collection="sbxxList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>借用时间</th>
							<td colspan="3">
								<html:text property="jysj" readonly="true" styleId="jysj"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"></html:text>
							</td>
<%--							<th>--%>
<%--								<font color="red">*</font>归还时间--%>
<%--							</th>--%>
<%--							<td>--%>
<%--								<html:text property="ghsj" readonly="true" styleId="ghsj"--%>
<%--										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"></html:text>--%>
<%--							</td>--%>
						</tr>
						<tr>
							<th>备注
							</th>
							<td colspan="3">
								<html:text property="bz" maxlength="50" style="width:85%;"></html:text>
							</td>
						</tr>
						<tr>
							<th>借用原因<br/>
								<font color="red">（限250字）</font>
							</th>
							<td colspan="3">
								<html:textarea property="jyyy" styleId="jyyy" 
											   onblur="checkLen(this,250);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveFormForUpdate();">
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
			</div>
		</html:form>
	</body>
</html>

