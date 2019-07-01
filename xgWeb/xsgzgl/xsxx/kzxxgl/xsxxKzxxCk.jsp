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

			jQuery(function() {
				jQuery('#ExtendDataContent').dataExtend( {
					"mid"      : jQuery('#xskzxxgl_module').val(),
					"dataId"   : jQuery('#xskzxxgl_jgid').val(),
					"dataType" : "1",
					"xh"       : jQuery('#xskzxxgl_xh').val(),
					"bdpzid"   : "xsxxgl",
					"naviBar"  :　true,
					"mode"     : "view",
					"actionBar": false
				});
			});
		
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
		<input type="hidden" name="xskzxxgl_jgid" value="${jgid}" id="xskzxxgl_jgid"/>
		<input type="hidden" name="xskzxxgl_module" value="${dataExtendModule}" id="xskzxxgl_module"/>
		<input type="hidden" name="xskzxxgl_xh" value="${xh}" id="xskzxxgl_xh"/>
		<div id="ExtendDataContent"></div>
	</body>
</html>
