function cshMm() {
	/*
	if (jQuery("#mm1").val() != jQuery("#mm2").val()) {
		showAlert("确认密码不一致！");
		return false;
	}*/
	var bz = jQuery("[name='chk']:checked").val();
	if(bz == "sdsr"){
		if (!checkPsw(jQuery("#mm1").val())) {
			return false;
		}
	}
	var url = "xsxx_xsxxgl.do?method=mmcsh&type=update";
	var parameter = {};
	var array = new Array();
	var api = frameElement.api, W = api.opener;
	jQuery(W.document).find("input[name=grid_key]:checked").each(
			function(i, n) {
				array.push(jQuery(n).parent().parent().find("[name='key_xh']").val());
			});
	var ids = array.join(',');
	var mm1 = jQuery("#mm1").val();
	
	showConfirm("确定要将选中的用户的密码初始化吗？", {
		"okFun" : function() {
			jQuery.post(url, {
				values : ids,
				mm1 : mm1,
				bz : bz
			}, function(data) {
				showAlert(data["message"],{},{"clkFun":function(){
					closeDialog();
				}});
			}, 'json');
		}
	});
}



