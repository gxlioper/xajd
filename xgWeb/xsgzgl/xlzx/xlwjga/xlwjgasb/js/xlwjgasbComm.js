var xswjbxMsg = '����ϸ������ѧ�����쳣����'; // ѧ��Σ������
var xyclgcMsg = '������ѧԺ����Ĵ�����̣���û�д�������д��'; // ѧԺ�������
var zyqkMsg = 'ѧԺ��ʦ��д��ʱ���εء��ļ�ҽԺ����ϡ����ơ���Ժҽ����'; // סԺ���
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
//�Ƿ���ʾסԺ���
function changeZyqk(){
	var ywzyls = jQuery("[name=ywzyls]:checked").val();
	if("1"==ywzyls){
		jQuery("#zyqk_tr").show();
	}else{
		jQuery("#zyqk_tr").hide();
	}
}
