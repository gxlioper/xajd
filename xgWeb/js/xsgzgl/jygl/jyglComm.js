//显示实习就业
function showSxjy(doType){
	var url = "general_jygl.do?method=sxjyUpdate";
	url+= "&doType="+doType;
		
	if(doType == "edit" || doType == "view"){
		var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
		if(num == "0"){
			alertError("请勾选一条您希望操作的记录");
			return false;
		}else if(num != "1"){
			alertError("只能勾选一条记录进行操作，请确认");
			return false;
		}

		var pkValue = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").eq(0).val();
		url+= "&pkValue="+pkValue;
	}
		
	showTopWin(url,"600","450");
}

//检测删除实习就业
function checkDelSxjy(){

	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	if(num == "0"){
		alertError("请勾选您希望删除的记录");
		return false;
	}
	
	confirmInfo("将要删除您勾选的记录，请确认",delSxjy);
}

//删除实习就业
function delSxjy(tag){
	if(tag == "ok"){
	
		var parameter ={};
		var pkValue = "";//主键
		var i=0;
		jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").each(function(){
			pkValue+=escape(jQuery(this).val())+"!!array!!";
		});
		//构建json对象
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