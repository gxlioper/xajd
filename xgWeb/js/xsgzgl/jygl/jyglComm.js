//��ʾʵϰ��ҵ
function showSxjy(doType){
	var url = "general_jygl.do?method=sxjyUpdate";
	url+= "&doType="+doType;
		
	if(doType == "edit" || doType == "view"){
		var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
		if(num == "0"){
			alertError("�빴ѡһ����ϣ�������ļ�¼");
			return false;
		}else if(num != "1"){
			alertError("ֻ�ܹ�ѡһ����¼���в�������ȷ��");
			return false;
		}

		var pkValue = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").eq(0).val();
		url+= "&pkValue="+pkValue;
	}
		
	showTopWin(url,"600","450");
}

//���ɾ��ʵϰ��ҵ
function checkDelSxjy(){

	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	if(num == "0"){
		alertError("�빴ѡ��ϣ��ɾ���ļ�¼");
		return false;
	}
	
	confirmInfo("��Ҫɾ������ѡ�ļ�¼����ȷ��",delSxjy);
}

//ɾ��ʵϰ��ҵ
function delSxjy(tag){
	if(tag == "ok"){
	
		var parameter ={};
		var pkValue = "";//����
		var i=0;
		jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").each(function(){
			pkValue+=escape(jQuery(this).val())+"!!array!!";
		});
		//����json����
		parameter["array_pkValue"]=pkValue;
		
		var url = "general_jygl_sxjy_ajax.do?method=deleteSxjy";
		
	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.ajaxSetup({async:false});
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
				searchRs();
			}
		);

		jQuery.ajaxSetup({async:true});
	}
}