var gndm = "xsxx_update";// ���ܴ���
jQuery(function() {
	onShow("ck");
	xsGkPic();
	if("10125" == jQuery("#xxdm").val()){//ɽ���ƾ�
		jQuery("#zdybdcon_table_xsxx_update_jnzs").children()
		.append("<tr><th>֤�鸽��</th><td colspan='3'><input type='hidden' name='zd6' value='' id='fjid'/><div id='commonfileupload-list-q' style='padding: 5px;'></div>" +
				"</td></tr>");
		getfj();
	}
	if("10704" == jQuery("#xxdm").val()){/*�����Ƽ���ѧ���Ի������ϴ�*/
		jQuery("#zdybdfl_xsxx_update_fjxx_10704")
		.append("<table width='100%' border='0' style='margin-bottom: 5px' class='formlist'><tbody><tr><th>������Ϣ</th><td colspan='4'><input type='hidden' name='zd6' id='fjid'/><span id='tips' style='color: red'></span>" +
				"<span id='tips' style='color: red'></span><div id='commonfileupload-list-q' style='padding: 5px;'><span class='loading' id='loading' style='font-size: 10px; margin-left: 0; display: none;' >������...</span></div>" +
				"</td></tr></tbody></table>");
		getfj();
	}
});
//����ʦ���߿���Ƭ���Ի�
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
			otherParams.xs = true;// ȫ��ʾ״̬
		zdybdInit(gndm, data, otherParams,flag);

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
