var xswjbxMsg = '请详细描述该学生的异常表现'; // 学生危机表现
var xyclgcMsg = '请描述学院自身的处理过程，若没有处理，则填写无'; // 学院处理过程
var zyqkMsg = '学院老师填写何时、何地、哪家医院、诊断、治疗、出院医嘱等'; // 住院情况
//初始化提示信息
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
//清空提示信息
function clearData(id, msg){
	var obj = jQuery("#" + id);	
	var v = jQuery.trim(obj.val());
	if (v == msg) {
		obj.val("");
	}
}
//是否显示住院情况
function changeZyqk(){
	var ywzyls = jQuery("[name=ywzyls]:checked").val();
	if("1"==ywzyls){
		jQuery("#zyqk_tr").show();
	}else{
		jQuery("#zyqk_tr").hide();
	}
}
