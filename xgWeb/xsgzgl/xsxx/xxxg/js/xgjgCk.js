var gndm = "xsxx_update";// 功能代码
jQuery(function() {
	onShow("ck");
	xsGkPic();
	if("10125" == jQuery("#xxdm").val()){//山西财经
		jQuery("#zdybdcon_table_xsxx_update_jnzs").children()
		.append("<tr><th>证书附件</th><td colspan='3'><input type='hidden' name='zd6' value='' id='fjid'/><div id='commonfileupload-list-q' style='padding: 5px;'></div>" +
				"</td></tr>");
		getfj();
	}
	if("10704" == jQuery("#xxdm").val()){/*西安科技大学个性化附件上传*/
		jQuery("#zdybdfl_xsxx_update_fjxx_10704")
		.append("<table width='100%' border='0' style='margin-bottom: 5px' class='formlist'><tbody><tr><th>附件信息</th><td colspan='4'><input type='hidden' name='zd6' id='fjid'/><span id='tips' style='color: red'></span>" +
				"<span id='tips' style='color: red'></span><div id='commonfileupload-list-q' style='padding: 5px;'><span class='loading' id='loading' style='font-size: 10px; margin-left: 0; display: none;' >处理中...</span></div>" +
				"</td></tr></tbody></table>");
		getfj();
	}
});
//华中师范高考照片个性化
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
	var sqid = jQuery("#sqid").val();
	var flag="sh";
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
