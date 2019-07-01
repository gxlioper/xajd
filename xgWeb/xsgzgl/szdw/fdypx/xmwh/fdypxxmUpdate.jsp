<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/fdypx/js/fdypxxmwh.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//为button注册事件
				jQuery("#but_save").click(function(){save("fdypxxmUpdate")});
				//jQuery("#but_close").click();
				
			});
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/rcsw_xsxxgl" method="post" styleId="demoForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>修改辅导员培训项目</span>
								<html:hidden property="xmdm" styleId="xmdm" name="model" />
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							
							<th width="16%">
								<font color="red">*</font>项目名称
							</th>
							<td width="34%">
								<html:text property="xmmc" styleId="xmmc" name="model" maxlength="20"></html:text>
							</td>
							<th width="16%">
								<font color="red">*</font>培训地点
							</th>
							<td width="34%" >
								<html:text property="pxdd"  styleId="pxdd"  name="model" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>申请开关
							</th>
							<td width="34%">
								<logic:equal value="1" property="sqkg" name="model">
									<input type="radio" name="sqkg" value="1" checked="checked">开</input>
									<input type="radio" name="sqkg" value="0">关</input>
								</logic:equal>
								<logic:equal value="0" property="sqkg" name="model">
									<input type="radio" name="sqkg" value="1">开</input>
									<input type="radio" name="sqkg" value="0" checked="checked">关</input>
								</logic:equal>
								<logic:empty property="sqkg" name="model">
									<input type="radio" name="sqkg" value="1">开</input>
									<input type="radio" name="sqkg" value="0">关</input>
								</logic:empty>
							</td>
							<th width="16%">
								申请时间
							</th>
							<td width="34%" >
							<html:text property="sqkssj" styleId="sqkssj"  name="model" style="width: 80px;"  onclick="return showCalendar('sqkssj','yyyy-MM-dd');" readonly="true" ></html:text>至
							<html:text property="sqjssj" styleId="sqjssj"  name="model" style="width: 80px;"  onclick="return showCalendar('sqjssj','yyyy-MM-dd');" readonly="true" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>培训时间
							</th>
							<td width="34%">
							<html:text property="pxsj"  styleId="pxsj"  name="model" style="width: 80px;" onclick="return showCalendar('pxsj','yyyy-MM-dd');" readonly="true"></html:text>
							</td>
							<th width="16%">
								<font color="red">*</font>培训学时
							</th>
							<td width="34%">
								<html:text property="pxxs"  styleId="pxxs"  name="model" style="width: 80px;" ></html:text>
							</td>

						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>组织部门
							</th>
							<td width="34%" colspan="3">
								<html:select property="bmdm" styleId="bmdm" name="model">
									<html:options collection="bmList" property="bmdm" labelProperty="bmmc"></html:options>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								培训简介
							</th>
							<td width="34%" colspan="3">
								<html:textarea property="pxjj" styleId="pxjj"   name="model" style="width: 500px;height: 100px;"></html:textarea>
							</td>
						</tr>
						
						
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" id = "but_save" >
										保 存
									</button>
									<button type="button" type="button" id= "but_close" onclick="iFClose();">
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

