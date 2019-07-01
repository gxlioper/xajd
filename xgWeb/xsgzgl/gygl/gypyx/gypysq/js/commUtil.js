var lddmList = null;
var qshList = null;
var chList = null;
//��ʼ��¥���������
function initGlobalVariable(){
	jQuery.ajaxSetup({async:false});
	jQuery.post("gypynew_gypysq.do?method=initLdChQsh",{},function(data){
		lddmList = data['ld'];
		qshList = data['qs'];
		chList = data['ch'];
	},'json');
	jQuery.ajaxSetup({async:true});
}

//��ʼ��������
function initSelect(){
	var flag = jQuery("#sqid").length == 1 ? true :false;
	var ldhtml = "<option value=''>--��ѡ��--</option>";
	if(lddmList != null){
		for ( var i = 0; i < lddmList.length; i++) {
			ldhtml += "<option value='"+lddmList[i]["lddm"]+"' >"+lddmList[i]["ldmc"]+"</option>";
		}
	}
	jQuery("#lddm").append(ldhtml);
	//�޸���Ҫ��ʼ��������������
	/*
	if(flag){
		var lddm = jQuery("#lddmN").val();
		jQuery("#lddm").val(lddm);
		var ch = jQuery("#chN").val();
		var chhtml = "<option>--��ѡ��--</option>";
		for ( var i = 0; i < chList.length; i++) {
			if(chList[i]['lddm'] == lddm){
				chhtml += "<option value='"+chList[i]["ch"]+"' >"+chList[i]["ch"]+"��</option>";
			}
		}
		jQuery("#ch").append(chhtml);
		jQuery("#ch").val(ch);
		var qsh = jQuery("#qshN").val();
		var qshhtml = "<option>--��ѡ��--</option>";
		for ( var i = 0; i < qshList.length; i++) {
			if(qshList[i]['lddm'] == lddm && qshList[i]['ch'] == ch){
				chhtml += "<option value='"+chList[i]["qsh"]+"' >"+chList[i]["qsh"]+"</option>";
			}
		}
		jQuery("#qsh").append(qshhtml);
		jQuery("#qsh").val(qsh);
	}*/
}

/**
 * ¥��������ѡ���¼�
 * @return
 */
function lddmChange(){
	var lddm = jQuery("#lddm").val();
	jQuery("#ch").empty();
	jQuery("#qsh").empty();
	if(lddm == ""){
		return;
	}
	var chhtml = "<option value=''>--��ѡ��--</option>";
	for ( var i = 0; i < chList.length; i++) {
		if(chList[i]['lddm'] == lddm){
			chhtml += "<option value='"+chList[i]["ch"]+"' >"+chList[i]["ch"]+"��</option>";
		}
	}
	jQuery("#ch").append(chhtml);
	var qshhtml = "<option value=''>--��ѡ��--</option>";
	for ( var i = 0; i < qshList.length; i++) {
		if(qshList[i]['lddm'] == lddm){
			qshhtml += "<option value='"+qshList[i]["qsh"]+"' >"+qshList[i]["qsh"]+"</option>";
		}
	}
	jQuery("#qsh").append(qshhtml);
}

/**
 * ���������ѡ���¼�
 * @return
 */
function chChange(){
	var ch = jQuery("#ch").val();
	var lddm = jQuery("#lddm").val();
	jQuery("#qsh").empty();
	var qshhtml = "<option value=''>--��ѡ��--</option>";
	if(ch == ""){
		for ( var i = 0; i < qshList.length; i++) {
			if(qshList[i]['lddm'] == lddm){
				qshhtml += "<option value='"+qshList[i]["qsh"]+"' >"+qshList[i]["qsh"]+"</option>";
			}
		}
	}else{
		for ( var i = 0; i < qshList.length; i++) {
			if(qshList[i]['lddm'] == lddm && qshList[i]['ch'] == ch){
				qshhtml += "<option value='"+qshList[i]["qsh"]+"' >"+qshList[i]["qsh"]+"</option>";
			}
		}
	}
	jQuery("#qsh").append(qshhtml);
}

jQuery(function(){
	initGlobalVariable();
	initSelect();
	jQuery("#lddm").change(lddmChange);
	jQuery("#ch").change(chChange);
})
