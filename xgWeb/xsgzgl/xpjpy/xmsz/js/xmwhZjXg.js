var defaultShlc;// 修改模式，已保存审核流程
jQuery(function() {
    var xmxz = jQuery("#xmxz").val();
    if(xmxz==2)
    {
        jQuery("#xz").text("表彰奖励");
    }
    else{
        jQuery("#xz").text("奖学金");
    }

    onShow();

});

function onShow() {
	jQuery("[name=shlc]").change(function() {// 人数控制级别，兼得控制级别，根据审核流程进行显示
				setKzlc(jQuery(this).val());
			});
	defaultShlc = jQuery("[name=shlc]:checked").val();
	jQuery("[name=shlc]").change();
//	setKzlc(defaultShlc);


	
	// 人数分配方式赋值
	jQuery(
			"input:radio[name=rsfpfsView][value='" + jQuery("#rsfpfs").val()
					+ "']").attr("checked", "checked");
}


/*
 * 根据审核状态，设置部分项
 * 
 */
function setSpzt(){
	var spzt = jQuery("#spzt").val();
	if(spzt == "true"){
		jQuery(".prompt").css("display","");
		jQuery("table input,select").not(jQuery("#xsxh")).not(jQuery("#xmywmc")).attr(
				"disabled", "disabled");
	}
}

function setKzlc(value) {
	if (value == "") {
		jQuery("#rskzjbTd").html("");
		return;
	}
	var url = "xpj_xmwh.do?method=xmwhShfw";
	jQuery.post(url, {
		value : value
	}, function(data) {
		var sHtml = "";
		var radio1 = "";
		if (data != null) {
			for ( var i = 0; i < data.length; i++) {
				var o = data[i];
				radio1 += "<label><input type='radio' name='rskzjbView' value='"
						+ o.spgwdm + "'/>";
				radio1 += o.spgwmc;
				radio1 += "</label>";
				if (i != data.length - 1) {
					radio1 += "<br/>";
				}
			}
		}
		sHtml += radio1;
		jQuery("#rskzjbTd").html(sHtml);
		//人数控制级别
		jQuery(
				"input:radio[name=rskzjbView][value=" + jQuery("#rskzjb").val()
						+ "]").attr("checked", "checked");	
		if (defaultShlc == value) {

		}
		setSpzt();//设置审批状态
	}, 'json');
}

function saveForm() {
	var xmmc = jQuery.trim(jQuery("#xmmc").val());
	var lxdm = jQuery("#lxdm").val();
	var xzdm = jQuery("#xmxz").val();
	var xmje = jQuery.trim(jQuery("#xmje").val());
	var xsxh = jQuery.trim(jQuery("#xsxh").val());
    var pycc = jQuery("#pycc").val();
	
	// 人数开关级别设置值，以便提交用
	var rskzjb = jQuery("input:radio[name=rskzjbView]:checked").val();
	jQuery("#rskzjb").val(rskzjb);
	
	var shlc = jQuery("#shlc").val();
	if (xmmc == "" || lxdm == null || xsxh==null || xsxh=="" ||xzdm == null || lxdm == "" ||
		xzdm == "" || rskzjb == null || rskzjb == "" || shlc == "" || pycc=="" ||pycc == null ) {
		showAlert("请将带*的项目填写完整！");
		return false;
	}
	if (xmje != "" && !checkMoney2(xmje)) {
		showAlert("[项目金额]格式不符！");
		return false;
	}

	var xmsm = jQuery.trim(jQuery("#xmsm").val());
	if (xmsm.length > 500) {
		showAlert("[项目说明]最大长度为500！");
		return false;
	}

	// 人数分配方式设置值，以便提交用
	var rsfpfs = jQuery("input:radio[name=rsfpfsView]:checked").val();
	if (rsfpfs == null || rsfpfs == "") {
		showAlert("[人数分配方式]不允许为空！");
		return false;
	}
	jQuery("#rsfpfs").val(rsfpfs);
	
	
	var rskzjb = jQuery("input:radio[name=rskzjbView]:checked").val();
	jQuery("#rskzjb").val(rskzjb);
	
		
	
	var url = "xpj_xmwh.do?method=xmwhZjXg";
	if (jQuery("#xmdm").val() === "") {
		url += "&type=save";
	} else {
		url += "&type=update";
	}
	
	jQuery("table input,select").attr(
			"disabled",false);
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