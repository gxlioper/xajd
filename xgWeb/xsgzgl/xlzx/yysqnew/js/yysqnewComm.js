var zyzllsMsg = '����У���ʱ���εء�������'; // סԺ������ʷ
//��ʼ����ʾ��Ϣ
function initData(id, msg){	
	jQuery("#" + id).focus( function() {
		var v = jQuery.trim(jQuery(this).val());
		if (v == msg) {
			jQuery(this).val("");
			jQuery(this).css("color", "");
		}
	});
	jQuery("#" + id).blur( function() {
		var v = jQuery.trim(jQuery(this).val());
		if (v == "") {
			jQuery(this).val(msg);
			jQuery(this).css("color", "#999");
		}
	});
	jQuery("#" + id).blur();
}
//�����ʾ��Ϣ
function clearData(id, msg){
	var obj = jQuery("#" + id);	
	var v = jQuery.trim(obj.val());
	if (v == msg) {
		obj.val("");
	}
}
//�Ƿ���ʾסԺ������ʷ
function changeZyzlls(){
	var ywyw = jQuery("[name=ywyw]:checked").val();
	var zxzt = jQuery("[name=zxzt]:checked").val();
	if("1"==ywyw && "3"!=zxzt){
		jQuery("#zyzlls_tr").show();
	}else{
		jQuery("#zyzlls_tr").hide();
	}
}
