var gndm = "xsxx_update";

jQuery(function() {
	onShow();
});
function onShow() {
	var url = "xsxx_bysxx_xxgl.do?method=bysXxCk&type=query";
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
			otherParams.xs = true;// ȫ��ʾ״̬
		zdybdInit(gndm, data, otherParams);

		setXgzd();// �����޸��ֶ�
	}
	});
}
/**
 * �����޸��ֶ�
 * 
 * @return
 */
function setXgzd() {
	var sqid = jQuery("#sqid").val();

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
			zdybdXgzd(data);
		}
	});
}

