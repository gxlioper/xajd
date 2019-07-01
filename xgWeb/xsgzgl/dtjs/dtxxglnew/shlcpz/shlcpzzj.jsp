<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/dtxxglnew/js/shlcpz.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				jQuery("[name=ksqkg]")[0].checked="true";
				jQuery("[name=ksqkg]").bind("click",function(){
					var val=jQuery(this).val();
					if(val=="1"){
						jQuery("#qzsj").show();
					}else{
						jQuery("#qzsj").hide();
					}
				});
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/shlcpz">
			<div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>增加审核流程配置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">* </font>阶段名称
							</th>
							<td>
								<html:select property="jddm" styleId="jddm" disabled="false" >
									<html:options collection="jdList" property="jddm"
										labelProperty="jdmc" />
								</html:select>							
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">* </font>审核流程
							</th>
							<td>
								<html:select property="splc" styleId="splc" disabled="false"
									style="width:280px;">
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>					
							</td>
						</tr>
						<tr>
							<th>
								可申请开关
							</th>
							<td>
								<html:radio property="ksqkg" value="1" styleId="ksqkg1"><label style='cursor:pointer'  for="ksqkg1">开启</label></html:radio>		
								<html:radio property="ksqkg" value="0" styleId="ksqkg2"><label style='cursor:pointer'  for="ksqkg2">关闭</label></html:radio>					
							</td>
						</tr>
						<tr id="qzsj">
							<th>
								起止时间
							</th>
							<td>
							<html:text property="ksqkssj" styleId="ksqkssj" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'ksqjssj');" />
							~
							<html:text property="ksqjssj" styleId="ksqjssj" onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'ksqkssj');" />
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
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button"
										onclick="save('shlcpz.do?method=add&type=save','jddm-splc');return false;"
										id="buttonSave">
										保 存
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
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
