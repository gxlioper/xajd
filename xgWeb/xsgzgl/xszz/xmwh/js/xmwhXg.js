function saveForm() {
	var xmmc = jQuery.trim(jQuery("#xmmc").val());
	var lbdm = jQuery("#lbdm").val();
	var je = jQuery.trim(jQuery("#je").val());
	var xmsm = jQuery("#xmsm").val();
	var xmyxg=false;//�ж���Ŀ�����Ƿ��޸�
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
	var xmmcxgq=jQuery("#xmmcxgq").val();//��Ŀ�����޸�ǰ��ֵ
	if(xmmc!=xmmcxgq){
		xmyxg=true;
	}
	

	var url = "xszz_xmwh.do?method=xmwhXg&type=save"+"&xmyxg="+xmyxg;
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
	var jesfxssq=jQuery("input:radio[name=jesfxssq]:checked").val();
	if(jesfxssq=='1'){
		jQuery('#jemc').text('�������');
	}else{
		jQuery('#jemc').text('�̶����');
	}
}
function changeJesfxssq(value){
	if(value=='1'){
		jQuery('#jemc').text('�������');
	}else{
		jQuery('#jemc').text('�̶����');
	}
}