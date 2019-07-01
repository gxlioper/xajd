<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xlzx/xlsc/js/xlscjg.js"></script>
		<script type="text/javascript" >
			jQuery(function(){
				jQuery("#sfxyyt").val('${sfxyyt}');
				jQuery("#sfyyt").val('${sfyyt}');
			})
		</script>
	</head>
	<body>
		<html:form action="/xlzx_xlscjg" method="post" styleId="xlscjgForm">
		<html:hidden property="id"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@include file="/xsgzgl/comm/bdpz/selectStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="4">
								<span>筛查详细信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="18%" style="text-align:center;"><font color="red">*</font>筛查日期</th>
				    		<td>
								<html:text property="scrq" styleId="scrq" onfocus="return showCalendar(this.id,'yyyy-MM-dd');" maxlength="7" style="width:150px"></html:text>
							</td>
							<th width="16%" style="text-align:center;"><font color="red">*</font>SCL90结果</th>
							<td>
								 <html:text property="scl" styleId="scl" style="width:150px" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th width="18%" style="text-align:center;"><font color="red">*</font>SDS结果</th>
							<td>
								 <html:text property="sds" styleId="sds" style="width:150px" maxlength="50"></html:text>
							</td>
							<th width="16%" style="text-align:center;"><font color="red">*</font>SAS结果</th>
							<td>
								 <html:text property="sas" styleId="sas" style="width:150px" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th width="18%" style="text-align:center;"><font color="red">*</font>贝克抑郁结果</th>
							<td>
								 <html:text property="bkyy" styleId="bkyy" style="width:150px" maxlength="50"></html:text>
							</td>
							<th width="16%" style="text-align:center;"><font color="red">*</font>贝克焦虑结果</th>
							<td>
								 <html:text property="bkjl" styleId="bkjl" style="width:150px" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
					    	<th width="18%" style="text-align:center;"><font color="red">*</font>是否需要参加约谈</th>
				    		<td>
				    			<html:select property="sfxyyt" styleId="sfxyyt" style="width:150px">
				    				<option value="">--请选择--</option>
				    				<option value="1">是</option>
				    				<option value="0">否</option>
				    			</html:select>
				    		</td>
				    		<th width="16%" style="text-align:center;"><font color="red">*</font>是否已参加约谈</th>
				    		<td>
				    			<html:select property="sfyyt" styleId="sfyyt" style="width:150px">
				    				<option value="">--请选择--</option>
				    				<option value="1">是</option>
				    				<option value="0">否</option>
				    			</html:select>
				    		</td>
					    </tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" id="bc" onclick="updateSave();">
										保存
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