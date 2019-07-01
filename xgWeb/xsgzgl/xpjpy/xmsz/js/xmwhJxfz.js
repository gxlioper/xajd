jQuery(function() {
	onShow();
});

function onShow() {
	var url = 'xpj_xmwh.do?method=xmwhJxfz&type=query';
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
		var dm = o.dm;
		var mc = o.mc;
		if(dm != null && dm != ""){
			sHtml += "<option value='"+dm+"'>"+mc+"</option>";
		}
	}
	jQuery("#jxfznd").html(sHtml);
}

function saveForm() {
	var jxfznd = jQuery("#jxfznd").val();
	if(jxfznd == null || jxfznd == ""){
		showAlert("请选择复制来源年度！");
		return false;
	}
	
	var url = 'xpj_xmwh.do?method=xmwhJxfz&type=save';
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