<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/cssz/js/szdw_cssz.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var kg = '${rzsqModel.kg}';
				if(kg=="1"){
					jQuery("#div_rzsqsj").show();
				}else{
					jQuery("#rzsqkgg").attr("checked",true);
				}
				
				jQuery("#but_save").click(saveFdycssz);
			});
			
		</script>
	</head>
	<body style="width:100%">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>思政队伍-基础设置-辅导员参数设置</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/szdw_cssz.do?method=fdycssz" method="post" styleId="demoForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist" align="center">
					<thead>
						<tr>
							<th colspan="2">
								<span>辅导员任职参数设置</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>辅导员任职申请开关
							</td>
							<td align="left" style="width: 60%">
								<html:radio name="rzsqModel" property="kg" value="1" onclick="changeKg('div_rzsqsj',this);">开</html:radio>
								<html:radio name="rzsqModel" property="kg" value="0" onclick="changeKg('div_rzsqsj',this);" styleId="rzsqkgg">关</html:radio>
								<html:hidden name="rzsqModel" property="kg" styleId="rzsqkg"/>
							</td>
						</tr>
						<tr id="div_rzsqsj" style="display: none;">
							<td align="right" style="width: 35%">
								辅导员任职申请起止时间
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rzsqModel" property="kssj" styleId="rzsqkssj"   size="10"
									onclick="return showCalendar('rzsqkssj','y-mm-dd',true,'rzsqjssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
								-
								<html:text name="rzsqModel" property="jssj" styleId="rzsqjssj"   size="10"
									onclick="return showCalendar('rzsqjssj','y-mm-dd',false,'rzsqkssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
									
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>辅导员任职申请审批流
							</td>
							<td align="left" style="width: 60%">
								<html:select property="splc" name="rzsqModel" styleId="rzsqSplc">
									<html:option value=""></html:option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" id = "but_save" >
										保 存
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

