jQuery(document).ready(function(){ 
	changeSqkg();
});

//更新岗位申请
function changeSqkg(){
	var sqkg = jQuery("[name=sqkg]:checked").val();
	if("1"==sqkg){
		jQuery("table select,input:not(input:radio[name=sqkg])").attr("disabled",false);
		
	}else if("0"==sqkg){
		jQuery("table select,input:not(input:radio[name=sqkg])").attr("disabled","disabled");
		
	}
}

function saveJcsz(){
	var sqkglength = jQuery("[name=sqkg]:checked").length;
	
	if(sqkglength==0){
		showAlertDivLayer("请设置申请开关!");
		return false;
	}
	
	var splc = jQuery("#splc").val();
	var sqkg = jQuery("[name=sqkg]:checked").val();
	
	if ("1"==sqkg && (splc == "" || splc == null)){
		showAlertDivLayer("请选择审核流程!");
		return false;
	}
	
//	2014.7.7 取消设置当中的时间必填项
//	if("1"==sqkg && (jQuery("#sqjssj").val()=="" || jQuery("#sqkssj").val()=="")){
//		showAlertDivLayer("开放时间和结束时间必须填写!");		
//		return false;
//	}

	
	var url = "gfjyqk_jcsz.do?method=saveGfjyJcsz";
	ajaxSubFormWithFun("jcszForm",url,function(data){
		showAlertDivLayer(data["message"]);
	});
}



