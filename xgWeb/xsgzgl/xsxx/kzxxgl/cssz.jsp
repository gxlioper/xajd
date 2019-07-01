<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			//保存参数设置
			function save(){
				var action = "xsxx_kzxxgl.do?method=csszAction";
				var sfqy = jQuery('select[name="sfqy"]').val();
				var shlc = jQuery('select[name="shlc"]').val();
				var kssj = jQuery('input[name="kssj"]').val();
				var jssj = jQuery('input[name="jssj"]').val();
				var sfsh = (shlc=='wxsh'?'0':'1');
				var sjxz = (kssj||jssj)?'1':'0';
				var extendModuleID = jQuery('input[name="extendModuleID"]').val();
				var postData = {'sfqy':sfqy,'shlc':shlc,'kssj':kssj,'jssj':jssj,'sfsh':sfsh,'sjxz':sjxz,'extendModuleID':extendModuleID};
				jQuery.post(action,postData,function(data){
					showAlertDivLayer(data['message']);
				},'json');
			}

			jQuery(function(){
				//初始化
				var splc = '${extendModule.splc}';
				var sfqy = '${extendModule.sfqy}';
				jQuery('select[name="sfqy"]').val(sfqy);
				jQuery('select[name="shlc"]').val(splc);
			});
		</script>
	</head>
	<body>
		<form action="/extend_cssz.do" method="post" id="form1">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
				</p>
			</div>
			<!-- 提示信息 end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span> 请设置扩展信息模块的开放时间区间和审核流程！<br /> </span>
				</p>
				<a class="close" title="隐藏" onclick=this.parentNode.style.display= 'none';></a>
			</div>

			<input type="hidden" name="extendModuleID" value="${extendModule.id}"/>
			<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="2">
								<span>配置信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="30%">
								名称
							</th>
							<td width="70%">
								${extendModule.mc}
							</td>
						</tr>
						<tr>
							<th width="30%">
								说明信息
							</th>
							<td width="70%">
								${extendModule.sm}
							</td>
						</tr>
						<tr>
							<th width="30%">
								<span class="red">*</span>是否启用
							</th>
							<td width="70%">
								<select name="sfqy" style="width: 245px;" >
									<option value="1" selected="selected">是</option>
									<option value="0">否</option>
								</select>
							</td>
						</tr>
						<tr>
							<th width="30%">
								<span class="red">*</span>审核流程
							</th>
							<td width="70%">
								<select name="shlc" style="width:245px" >
									<option value="wxsh">无需审核</option>
									<logic:iterate id="splc" name="shlcList">
										<option value="${splc.splc}">${splc.lcxx}</option>
									</logic:iterate>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								开放时间
							</th>
							<td>
								<input type="text" id="kssj" name="kssj" size="10" value="${extendModule.kssj}" onfocus="showCalendar('kssj','y-mm-dd',true,'jssj');" readonly="readonly"/>
								-
								<input type="text" id="jssj" name="jssj" size="10" value="${extendModule.jssj}" onfocus="showCalendar('jssj','y-mm-dd',false,'kssj');" readonly="readonly"/>		
							</td>
						</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">	
								<logic:equal name="writeAble" value="yes">
									<button type="button" class="button2" id="btn_bc"
										onclick="save();return false;">
										保 存
									</button>
								</logic:equal>
         	 				</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<form>
	</body>
</html>
