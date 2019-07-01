var demoHtml = "";

jQuery(function() {
	onShow();
	changeZjfs();
});

function onShow() {
	var url = "xpj_tsxs.do?method=tsxsZj&type=query";
	jQuery.post(url, {
	}, function(data) {
		initTslx(data);//初始化特殊类型		
	}, 'json');
}

//隐藏奖项
function changeZjfs() {
	var zjfs = jQuery("input[name='zjfs']:checked").val();
	if ("0" == zjfs) {
		jQuery("#sdsrtr").hide();
		jQuery("#sdsrbtn").hide();
		jQuery("#plzjbtn").show();
	} else {
		jQuery("#sdsrtr").show();
		jQuery("#sdsrbtn").show();
		jQuery("#plzjbtn").hide();
		initData();
	}
}

function initData(){	
	demoHtml = "请按如下格式输入特殊学生学号\n\n";
	demoHtml += "例如：\n";
	demoHtml += "20110019\n20100019\n20090026";
	demoHtml += "\n或者：\n";
	demoHtml += "20110019 20100019 20090026";

	jQuery("#tsxsxh").val(demoHtml);
	jQuery("#tsxsxh").css("color", "#999");
	jQuery("#tsxsxh").focus( function() {
		if (jQuery(this).val() == demoHtml) {
			jQuery(this).val("");
			jQuery(this).css("color", "");
		}else{
			jQuery(this).css("color", "");
		}
	});

	jQuery("#tsxsxh").blur( function() {
		if (jQuery(this).val() == "") {
			jQuery(this).val(demoHtml);
			jQuery(this).css("color", "#999");
		}
	});
}

//初始化特殊类型		
function initTslx(data){
	if(data == null || data.length == 0	){
		return ;
	}
	var sHtml = "";
	sHtml += "<option value=''></option>";

	for ( var i = 0; i < data.length; i++) {
		var o = data[i];
		var dm = o.lxdm;
		var mc = o.lxmc;
		if(dm != null && dm != ""){
			sHtml += "<option value='"+dm+"'>"+mc+"</option>";
		}
	}
	jQuery("#lxdm").html(sHtml);
	
	jQuery("#lxdm").change(function() {//
		var lxdm = jQuery(this).val();
		if(lxdm === ""){
			jQuery("#lxsmTd").html("");
			jQuery("#lxsxTd").html("");
		}
		for ( var i = 0; i < data.length; i++) {
			var o = data[i];
			var dm = o.lxdm;
			var sm = o.lxsm;
			var sx = o.lxsx;
			var sxmc = "";
			if(sx === "1"){
				sxmc = "条件";
			}else if(sx === "2"){
				sxmc = "范围";
			}
			if(dm != null && dm != "" && lxdm === dm){
				jQuery("#lxsmTd").html(sm);
				jQuery("#lxsxTd").html(sxmc);
			}
		}
	});
	jQuery("#lxdm").change();	
}

function saveForm(mklx) {

	//判断是否已选择特殊人员类型
	if(jQuery("#lxdm").val() == null || jQuery.trim(jQuery("#lxdm").val()) == ""){
		showAlert("请选择特殊人员类型！");
		return false;
	}

	if (jQuery.trim(jQuery("#tsxsxh").val()) == "" || jQuery("#tsxsxh").val() === demoHtml){
		showAlert("请输入特殊学生学号！");
		return false;
	}
	jQuery("button").attr("disabled","disabled");
	var url = "xpj_tsxs.do?method=tsxsCreate&mklx="+mklx;
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