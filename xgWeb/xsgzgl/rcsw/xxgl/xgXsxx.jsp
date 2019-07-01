<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xxgl/xsxxgl.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//为button注册事件
				jQuery("#but_save").click(function(){
					saveForm('xgXxgl');
				});
				jQuery("#but_close").click(btn_close);
			});
		</script>
	</head>
	<body style="width:97%">
		<html:form action="/rcsw_xsxxgl.do" method="post" styleId="demoForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>修改学生献血信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>学年
							</th>
							<td width="34%" colspan="3">
								<html:select name="model" property="xn" styleId="xn" disabled="${rs.dis}" style="width:150px">
									<html:options collection="xnList" labelProperty="xn" property="xn" />
								</html:select>
							</td>
						</tr>
						
						<tr>
							<th width="16%">
								<font color="red">*</font>献血时间
							</th>
							<td width="34%" colspan="3">
								<html:hidden property="xxgldm" name="model"/>
								<html:text  name="model" property="xxsj" styleId="xxsj" onclick="return showCalendar('xxsj','y-mm-dd');" readonly="true" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								备注
								<br/>
								<font color="red">限1000字</font>
							</th>
							<td width="34%" colspan="3">
								<html:textarea name="model" property="bz" styleId="bz" style="height:100px;" cols="50" styleClass="text_nor"></html:textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" id="but_save">
										保 存
									</button>
									<button type="button" type="button" id="but_close" >
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

