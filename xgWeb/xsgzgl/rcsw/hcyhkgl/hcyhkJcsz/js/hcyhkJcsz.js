jQuery(document).ready(function(){ 
	changeSqkg();
});

//���¸�λ����
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
		showAlertDivLayer("���������뿪��!");
		return false;
	}
	
	var splc = jQuery("#splc").val();
	var sqkg = jQuery("[name=sqkg]:checked").val();
	
	if ("1"==sqkg && (splc == "" || splc == null)){
		showAlertDivLayer("��ѡ���������!");
		return false;
	}
	
//	2014.7.7 ȡ�����õ��е�ʱ�������
//	if("1"==sqkg && (jQuery("#sqjssj").val()=="" || jQuery("#sqkssj").val()=="")){
//		showAlertDivLayer("����ʱ��ͽ���ʱ�������д!");		
//		return false;
//	}

	
	var url = "rcsw_hcyhk_jcszgl.do?method=saveHcyhkJcsz";
	ajaxSubFormWithFun("hcyhkJcszForm",url,function(data){
		showAlertDivLayer(data["message"]);
	});
}



