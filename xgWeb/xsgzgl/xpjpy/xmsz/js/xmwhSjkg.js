jQuery(function() {
	onShow();
		var kgbz = jQuery("#kgbz").val();
		kgbz = kgbz.replaceAll("<br/>","\n");
		jQuery("#kgbz").html(kgbz);
});

function onShow() {

}

function saveForm() {
	var sqkssj = jQuery("#sqkssj").val();
	var sqjssj = jQuery("#sqjssj").val();
	if(sqkssj != "" && sqjssj != "" && sqkssj > sqjssj){
		showAlert("���뿪ʼʱ�䲻�ܴ����������ʱ�䣡");
		return false;
	}
	
	var kgbz = jQuery("#kgbz").val();
	if(kgbz.length > 100) {
		showAlert("����������д˵������100�֣�");
		return false;	
	}
	
	var shkssj = jQuery("#shkssj").val();
	var shjssj = jQuery("#shjssj").val();
	if(shkssj != "" && shjssj != "" && shkssj > shjssj){
		showAlert("��˿�ʼʱ�䲻�ܴ�����˽���ʱ�䣡");
		return false;
	}

	var url = "xpj_xmwh.do?method=xmwhSjkg&type=update";
	ajaxSubFormWithFun("form1", url, function(data) {
		showAlert(data["message"],{},{"clkFun":  function(tag) {
			if (tag == "ok") {
				refershParent();
			}
		}});
	});


}