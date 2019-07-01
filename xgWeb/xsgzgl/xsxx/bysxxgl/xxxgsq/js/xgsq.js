var gndm = "xsxx_update";

jQuery(function() {
	onShow();
});

function onShow() {
	
	var url = "xsxx_bysxx_xxxgsq.do?method=bysXxXgSq&type=query";
	var xxdm = jQuery("#xxdm").val();
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
			zdybdInit(gndm, data);
			setXgzd();// ÉèÖÃÐÞ¸Ä×Ö¶Î
	}
	});
}
/**
 * ÉèÖÃÐÞ¸Ä×Ö¶Î
 * 
 * @return
 */
function setXgzd() {
	var sqid = jQuery("#sqid").val();
	var url = "xsxx_bysxx_xxxgsq.do?method=getXgzdList";
	url += "&timestamp=" + new Date().getTime();
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			sqid : sqid
		},
		dataType : "json",
		success : function(data) {
			zdybdReplaceZd(data);
		}
	});
}
