function loadLdxx(aValue){
	jQuery.post('rcgl_qtwh.do?method=loadLdxx',{yqdm:aValue},function(data){
		jQuery('#bfwld').empty();
		jQuery('#bfwld').append("<option value=''></option>");
		jQuery('#bfwld').append(data);
	});
	loadCsxx("");
	loadQsxx("");
}

function loadCsxx(aValue){
	jQuery.post('rcgl_qtwh.do?method=loadCsxx',{lddm:aValue},function(data){
		jQuery('#bfwcs').empty();
		jQuery('#bfwcs').append("<option value=''></option>");
		jQuery('#bfwcs').append(data);
	});
	loadQsxx("");
}

function loadQsxx(aValue){
	var bValue = jQuery('#bfwld').val();
	jQuery.post('rcgl_qtwh.do?method=loadQsxx',{lddm:bValue,cs:aValue},function(data){
		jQuery('#bfwss').empty();
		jQuery('#bfwss').append("<option value=''></option>");
		jQuery('#bfwss').append(data);
	});
}

function setTrValue(){
	showDialog('请选择一个辅导员',720,470,'szdw_showJzyg.do');
}