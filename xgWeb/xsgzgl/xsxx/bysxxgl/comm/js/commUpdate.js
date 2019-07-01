jQuery(function() { 
	if ("40030" == jQuery("#xxdm").val()) {
		onChange();
		if("isStu"==jQuery("#isStu").val()){
		jQuery("#qtyy").attr("readonly","readonly");
		jQuery("#qtyy").attr("title","该部分由班主任填写");
		}
	}else if ("14073" == jQuery("#xxdm").val()) {
		onChange_14073();
	}else if ("11458" == jQuery("#xxdm").val()) {
		if("isStu"==jQuery("#isStu").val()){
			jQuery("#byxx").parents("tr").hide();
		}
		jQuery("#zsjj").css("margin-top", "5px");
		var helpMsg = '东部地区包括北京、天津、河北、辽宁、上海、江苏、浙江、福建、山东、广东和海南等11个省市；\n';
		helpMsg += '中部地区包括山西、吉林、黑龙江、安徽、江西、河南、湖北、湖南等8省；\n';
		helpMsg += '西部地区包括重庆、四川、贵州、云南、西藏、陕西、甘肃、青海、宁夏、新疆、广西、内蒙古等12个省、自治区。';
		var helpHtml = '<div class="tab_cur" style="display: inline;background-image: none;" title="'+helpMsg+'">';
		helpHtml += '<p class="help" style="margin-right:50px;">';
		helpHtml += '<a href="#" onclick="return false;" onmousedown ="showHelpDiv();" >帮助</a>';
		helpHtml += '</p>';
		helpHtml += '</div>';
		jQuery("#zsjj").parent().append(helpHtml);
	}
	xsGkPic();

});
//北京经济管理职业学院
function onChange_14073() {
	jQuery("#sfzsb").change(function() {
		if ("退出服役" == jQuery(this).val()) {
			jQuery("#sfzfx").parents("tr").show();
		}else{
			jQuery("#sfzfx").val("");
			jQuery("#sfzfx").parents("tr").hide();
		}
	});
	jQuery("#sfzsb").change();
}
// 北京商贸学校学生保险投保、军训个性化
function onChange() {
	if ("是" == jQuery("#zd1").val()) {
		jQuery("#zd3").parents("tr").show();
		jQuery("#zd2").parents("tr").show();
	} else {
		jQuery("#zd3").parents("tr").hide();
		jQuery("#zd2").parents("tr").hide();
	}
	if ("否" == jQuery("#sftb").val()) {
		jQuery("#tbsj").parents("tr").hide();
		jQuery("#sfyqrzs").parents("tr").show();
	} else {
		jQuery("#tbsj").parents("tr").show();
		jQuery("#sfyqrzs").parents("tr").hide();
	}
	jQuery("#sftb").change(function() {
		if ("否" == jQuery("#sftb").val()) {
			jQuery("#tbsj").val("");
			jQuery("#bxxz").val("");
			jQuery("#tbsj").parents("tr").hide();
			jQuery("#sfyqrzs").parents("tr").show();
		} else {
			jQuery("#qtyy").val("");
			jQuery("#sfyqrzs").val("");
			jQuery("#tbsj").val("yyyy-mm-dd至yyyy-mm-dd");
			jQuery("#tbsj").css("color", "#7D7D7D");
			jQuery("#tbsj").parents("tr").show();
			jQuery("#sfyqrzs").parents("tr").hide();
		}
	});
	jQuery("#zd1").change(function() {
		if ("是" == jQuery("#zd1").val()) {
			jQuery("#zd3").val("yyyy-mm-dd至yyyy-mm-dd");
			jQuery("#zd3").css("color", "#7D7D7D");
			jQuery("#zd3").parents("tr").show();
			jQuery("#zd2").parents("tr").show();
			
		} else {
			jQuery("#zd3").val("");
			jQuery("#zd2").val("");
			jQuery("#zd3").parents("tr").hide();
			jQuery("#zd2").parents("tr").hide();
		}
	});
	jQuery("#tbsj").focus(function() {
		if ("yyyy-mm-dd至yyyy-mm-dd" == jQuery("#tbsj").val()) {
			jQuery("#tbsj").val("");
			jQuery("#tbsj").css("color", "");
		}
	});
	jQuery("#tbsj").blur(function() {
		if ("" == jQuery("#tbsj").val()) {
			jQuery("#tbsj").val("yyyy-mm-dd至yyyy-mm-dd");
			jQuery("#tbsj").css("color", "#7D7D7D");
		}
	});
	if ("" == jQuery("#tbsj").val()) {
		jQuery("#tbsj").val("yyyy-mm-dd至yyyy-mm-dd");
		jQuery("#tbsj").css("color", "#7D7D7D");
	}
	jQuery("#zd3").focus(function() {
		if ("yyyy-mm-dd至yyyy-mm-dd" == jQuery("#zd3").val()) {
			jQuery("#zd3").val("");
			jQuery("#zd3").css("color", "");
		}
	});
	jQuery("#zd3").blur(function() {
		if ("" == jQuery("#zd3").val()) {
			jQuery("#zd3").val("yyyy-mm-dd至yyyy-mm-dd");
			jQuery("#zd3").css("color", "#7D7D7D");
		}
	});
	if ("" == jQuery("#zd3").val()) {
		jQuery("#zd3").val("yyyy-mm-dd至yyyy-mm-dd");
		jQuery("#zd3").css("color", "#7D7D7D");
	}

}
// 华中师范高考照片个性化
function xsGkPic() {
	if ("10511" != jQuery("#xxdm").val()) {
		jQuery("#addGkPic").css("display", "none");
		jQuery("#stuGkImg").css("display", "none");
		jQuery("#gkzpscbtn").css("display", "none");
	}
	if("10511" == jQuery("#xxdm").val()&&"isStu"==jQuery("#isStu").val()){
		jQuery("#gkzpscbtn").css("display", "none");
	}
}
//北京商贸个性化投保，军训字段值重置
function initParam(){
	if ("40030" == jQuery("#xxdm").val()) {
		if ("yyyy-mm-dd至yyyy-mm-dd" == jQuery("#tbsj").val()) {
			jQuery("#tbsj").val("");

		}
		if ("yyyy-mm-dd至yyyy-mm-dd" == jQuery("#zd3").val()) {
			jQuery("#zd3").val("");
		}
	}
	
}