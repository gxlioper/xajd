jQuery(document).ready(function(){ 
	changeSqkg();
});

//���뿪��
function changeSqkg(){
	var sqkg = jQuery("[name=sqkg]:checked").val();
	if("1"==sqkg){
		jQuery("#qzsjTr").show();
	}else if("0"==sqkg){
		jQuery("#qzsjTr").hide();
	}
}

//����
function saveJcsz(){
	
	var sqkglength = jQuery("[name=sqkg]:checked").length;
	if(sqkglength==0){
		showAlertDivLayer("���������뿪��!");
		return false;
	}
	var splc = jQuery("#splc").val();
	var sqkg = jQuery("[name=sqkg]:checked").val();
	
	if ("1"==sqkg && (splc == "" || splc == null)){
		showAlertDivLayer("��ѡ���������!");
		return false;
	}
	
	var url = "rcsw_lstd_jcszgl.do?method=saveLstdJcsz";
	ajaxSubFormWithFun("lstdJcszForm",url,function(data){
		showAlertDivLayer(data["message"]);
	});
	
}



