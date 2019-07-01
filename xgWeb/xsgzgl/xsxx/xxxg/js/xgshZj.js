var gndm = "xsxx_update";// 功能代码
jQuery(function() {
	onShow("sh");
	xsGkPic();
	var lcid = jQuery("#lcid").val();
	var shid = jQuery("#shid").val();
	var sqid = jQuery("#sqid").val();
	var ywid = jQuery("#ywid").val();
	jQuery("#shlccx").load(
			"comm_spl.do?method=lccx&sqid=" + ywid + "&tt="
					+ new Date().getTime());
	jQuery("#shjgSpan").load(
			"comm_spl.do?method=shth&lcid=" + lcid + "&shid=" + shid);
	if("10125" == jQuery("#xxdm").val()){//山西财经
		jQuery("#zdybdcon_table_xsxx_update_jnzs").children()
		.append("<tr><th>证书附件</th><td colspan='3'><input type='hidden' name='zd6' value='' id='fjid'/><div id='commonfileupload-list-q' style='padding: 5px;'></div>" +
				"</td></tr>");
		getfj();
	}
	if("10704" == jQuery("#xxdm").val()){/*西安科技大学个性化附件上传，2017-11-20，Meng.Wei*/
		jQuery("#zdybdfl_xsxx_update_fjxx_10704")
		.append("<table width='100%' border='0' style='margin-bottom: 5px' class='formlist'><tbody><tr><th>附件信息</th><td colspan='4'><input type='hidden' name='zd6' id='fjid'/><span id='tips' style='color: red'></span>" +
				"<span id='tips' style='color: red'></span><div id='commonfileupload-list-q' style='padding: 5px;'><span class='loading' id='loading' style='font-size: 10px; margin-left: 0; display: none;' >处理中...</span></div>" +
				"</td></tr></tbody></table>");
		getfj();
	}
});

function xsGkPic(){
	if("10511"!=jQuery("#xxdm").val()){
	jQuery("#stuGkImg").css("display","none");
	}
	
}

function onShow(flag) {
	var url = "xsxx_xsxxgl.do?method=xsxxglCk&type=query";
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			xh : jQuery("#xh").val()
		},
		dataType : "json",
		success : function(data) {
			var xm = data.xm;
			jQuery("#xmView").html(xm);
			var otherParams = {};
			otherParams.xs = true;// 全显示状态
		zdybdInit(gndm, data, otherParams,flag);

		setXgzd();// 设置修改字段
	}
	});
}

/**
 * 设置修改字段
 * 
 * @return
 */
function setXgzd() {
	var sqid = jQuery("#ywid").val();
	var flag = "sh";
	var url = "xsxx_xsxxxg.do?method=getXgzdList";
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			sqid : sqid
		},
		dataType : "json",
		success : function(data) {
			zdybdXgzd(data,flag);
		}
	});
}

/**
 * 审核保存
 * 
 * @return
 */
function save_sh() {
	if(jQuery("#xxdm").val() == "14008"){
		if (jQuery("#shjg").val() != "1" && jQuery("#shyj").val() == "") {
			showAlertDivLayer("请填写审核意见！");
			return false;
		}
	}else{
		if (jQuery("#shyj").val() == "") {
			showAlertDivLayer("请填写审核意见！");
			return false;
		}
	}
	if (jQuery("#shyj").val().length > 200) {
		showAlertDivLayer("审核意见不能超过200字");
		return false;
	}

	// 提交审核

	var url = "xsxx_xsxxxgsh.do?method=saveShlc";
	ajaxSubFormWithFun("form1", url, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				refershParent();
			}
		});
	});

}
