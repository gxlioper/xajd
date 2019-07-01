function saveForm() {
	var xmmc = jQuery.trim(jQuery("#xmmc").val());
	var lbdm = jQuery("#lbdm").val();
	var je = jQuery.trim(jQuery("#je").val());
	var xmsm = jQuery("#xmsm").val();
	if (xmmc == "" || lbdm == "" || je == "") {
		showAlert("�뽫��*����Ŀ��д������");
		return false;
	}
	
	if(xmsm.length > 500) {
		showAlert("��Ŀ˵������500�֣�");
		return false;
	}
	
	if (je != "" && !checkMoney2(je)) {
		showAlert("[���]��ʽ������");
		 return false;
	}

	var url = "xszz_xmwh.do?method=xmwhZj&type=save";
	ajaxSubFormWithFun("form1", url, function(data) {
		if(data["success"] != undefined && data["success"] == 'false'){
			showAlert(data["message"]);
		}else{
			showAlert(data["message"],{},{"clkFun": function(tag) {
				if (tag == "ok") {
					refershParent();
				}
			}});
		}
	});


}

jQuery(function() {
	onShow();
});

function onShow() {
	jQuery("input:radio[name=jesfxssq]").change(function() {//�޸Ľ���Ƿ�ѧ������
		changeJesfxssq(jQuery(this).val());
	});
}
function changeJesfxssq(value){
	if(value=='1'){
		jQuery('#jemc').text('�������');
	}else{
		jQuery('#jemc').text('�̶����');
	}
}