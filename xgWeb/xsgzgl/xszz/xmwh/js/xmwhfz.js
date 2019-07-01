jQuery(function() {
	onShow();
});

function onShow() {
	var url = 'xszz_xmwh.do?method=xmwhfz&type=query';
	jQuery.post(url, {
	}, function(data) {
		setInit(data);//设置初值
	}, 'json');
}

function setInit(data){
	if(data == null || data.length == 0	){
		return ;
	}
	var sHtml = "";
	for ( var i = 0; i < data.length; i++) {
		var o = data[i];
		var dm = o.zqdm;
		var mc = o.zqmc;
		if(dm != null && dm != ""){
			sHtml += "<option value='"+dm+"'>"+mc+"</option>";
		}
	}
	jQuery("#xmfznd").html(sHtml);
}

function saveForm() {
	var jxfznd = jQuery("#xmfznd").val();
	if(jxfznd == null || jxfznd == ""){
		showAlert("请选择复制来源年度！");
		return false;
	}
	
	var url = 'xszz_xmwh.do?method=xmwhfz&type=save';
	ajaxSubFormWithFun("form1", url, function(data) {
		if (data["success"] != undefined && (data["success"] == false || data["success"] == "false" )) {
			showAlert(data["message"]);
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function(tag) {
					if (tag == "ok") {
						refershParent();
					}
				}
			});
		}
	});


}