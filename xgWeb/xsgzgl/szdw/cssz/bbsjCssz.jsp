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
			//开关设置
			function changeBbsjKg(div,obj){
				var val=jQuery(obj).val();
				jQuery("#bbsjkg").val(val);
				if(val=="1"){
					jQuery("#"+div).show();
				}else{
					jQuery("#"+div).hide();
				}
			}
			jQuery(function(){
				var kg = '${bbsjModel.kg}';
				if(kg=="1"){
					jQuery("#div_bbsjsj").show();
				}else{
					jQuery("#bbsjkgg").attr("checked",true);
				}
				
				jQuery("#but_save").click(saveBbsjCssz);
			});
			
		</script>
	</head>
	<body style="width:100%">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>思政队伍-基础设置-编班时间设置</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/szdw_cssz.do?method=bbsjCssz" method="post" styleId="demoForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist" align="center">
					<thead>
						<tr>
							<th colspan="2">
								<span>时间设置</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>编班开关
							</td>
							<td align="left" style="width: 60%">
								<html:radio name="bbsjModel" property="kg" value="1" onclick="changeBbsjKg('div_bbsjsj',this);">开</html:radio>
								<html:radio name="bbsjModel" property="kg" value="0" onclick="changeBbsjKg('div_bbsjsj',this);" styleId="bbsjkgg">关</html:radio>
								<html:hidden name="bbsjModel" property="kg" styleId="bbsjkg"/>
							</td>
						</tr>
						<tr id="div_bbsjsj" style="display: none;">
							<td align="right" style="width: 35%">
								编班起止时间
							</td>
							<td align="left" style="width: 60%">
								<html:text name="bbsjModel" property="kssj" styleId="bbsjkssj"   size="10"
									onclick="return showCalendar('bbsjkssj','y-mm-dd',true,'bbsjjssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
								-
								<html:text name="bbsjModel" property="jssj" styleId="bbsjjssj"   size="10"
									onclick="return showCalendar('bbsjjssj','y-mm-dd',false,'bbsjkssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
									
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

