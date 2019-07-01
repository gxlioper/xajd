<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/extend/zfsoft-dataExtend.js"></script>
		<script type="text/javascript">

		

	/**
	 * 保存或者是提交数据操作，具体代码自己实现，参数提供
	  data：表单数据
	  type：操作类型,save 或 submit
	 */
	jQuery(function() {
		var sfqy = '${sfqy}';
		var yxqkz = '${yxqkz}';
		var splsz = '${splsz}';
		var configError = '${configError}';
		
		function actionButtonFn(data, type) {
			var sqid = jQuery("#xskzxxgl_sqid").val();
			var shzt = jQuery("#xskzxxgl_shzt").val();
			var splc = jQuery("#xskzxxgl_splc").val();
			var moduleId = jQuery('#xskzxxgl_module').val();
			if(sfqy == '0'){
				showAlertDivLayer('当前功能未开放使用，请联系管理员！');
				return false;
			}
			if(yxqkz == '0'){
				showAlertDivLayer('当前时间段该功能未开放使用，请联系管理员！');
				return false;
			}
			if(splsz == '0'){
				showAlertDivLayer('审批流程设置不正确，请联系管理员！');
				return false;
			}
			//如果有数据正在审核中，不允许任何操作
			if(sqid!=""&&splc!=""&&shzt=="5"){
				showAlertDivLayer('您的数据正在审核中，暂不能保存或提交数据！');
				return false;
			}
			//如果是新申请，之前数据被审核不通过，之前数据审核通过现在需要新申请，那么新生成ID
			//如果数据未提交或者被退回，那么用来的申请ID
			
			var url = "xsxx_kzxxgl.do?method=xslrAction";
			var msg = '您确定要提交吗？';
			if(type=='save'){
				msg = '您确定要保存吗？';
			};
			showConfirmDivLayer(msg, {
				"okFun" : function() {
					jQuery.post(url, {
						"extendData" : JSON.stringify(data),
						"actionType" : type,
						"sqid"       : sqid,
						"shzt"       : shzt,
						"splc"       : splc,
						"exendDateModuleId": moduleId
					}, function(_data) {  
						showAlertDivLayer(_data['message'], {}, 
							{'clkFun': function(){
							window.location.reload();
						}});
					}, 'json');
				}
			});
			
		}
		
		jQuery('#ExtendDataContent').dataExtend( {
			"mid"      : jQuery('#xskzxxgl_module').val(),
			"dataId"   : jQuery('#xskzxxgl_sqid').val(),
			"dataType" : "2",
			"xh"       : jQuery('#xskzxxgl_xh').val(),
			"bdpzid"   : "xsxxgl",
			"actionFn" : actionButtonFn,
			"naviBar"  :　true,
			"mode"     : "edit"
		});
	});

	
	function showLcinfo(){
		var sqid = jQuery("#xskzxxgl_sqid").val();
		var shzt = jQuery("#xskzxxgl_shzt").val();
		var splc = jQuery("#xskzxxgl_splc").val();
		if(sqid == ""){
			showAlertDivLayer("您未提交任何数据，无相关流程信息！");
			return false;
		}
		if(splc=="" || splc=="wxsh"){
			showAlertDivLayer("您的数据无需审核，无相关流程信息！");
			return false;
		}
		if(shzt=="0"){
			showAlertDivLayer("您的数据未提交，无相关流程信息！");
			return false;
		}
		showDialog("学生扩展信息流程跟踪",600,400,'comm_spl.do?method=lcgz&sqid='+sqid+"&splc="+splc);
	}
		
	
</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">信息</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<logic:equal value="1" name="configError">
			<!-- 提示信息 START-->
			<div class="prompt" id="div_help">
				<h3>
					<span>错误信息：</span>
				</h3>
				<p>
					<span id="div_help" style="color:red;font-weight: bold;">
					<logic:equal value="0" name="sfqy" scope="request">
						拓展信息表单未启用,请联系管理员.</br>
					</logic:equal>
					<logic:equal value="0" name="yxqkz" scope="request">
						当前不在开放时间区间内,暂无法申请填写表单,请联系管理员.</br>
					</logic:equal>
					<logic:equal value="0" name="splsz" scope="request">
						审批流程配置不正确,请联系管理员.</br>
					</logic:equal>
					</span>
				</p>
				<a onclick="this.parentNode.style.display='none';" title="隐藏"
					class="close"></a>
			</div>
			<!-- 提示信息 end-->
		</logic:equal>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li>
					<a href="javascript:void(0);" onclick="showLcinfo();return false;" 
						   title="查看审核流程。"
						   class="btn_cs">流程跟踪</a>
					</li>
				</ul>
			</div
		</div>
		<input type="hidden" name="xskzxxgl_sqid" value="${sqid}" id="xskzxxgl_sqid"/>
		<input type="hidden" name="xskzxxgl_splc" value="${splc}" id="xskzxxgl_splc"/>
		<input type="hidden" name="xskzxxgl_shzt" value="${shzt}" id="xskzxxgl_shzt"/>
		<input type="hidden" name="xskzxxgl_module" value="${dataExtendModule}" id="xskzxxgl_module"/>
		<input type="hidden" name="xskzxxgl_xh" value="${xh}" id="xskzxxgl_xh"/>
		<div id="ExtendDataContent"></div>
	</body>
</html>
