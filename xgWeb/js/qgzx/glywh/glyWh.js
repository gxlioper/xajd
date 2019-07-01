
function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}


function searchRs(){
	var url = "qgzx_glygl_ajax.do?method=glyWh";
	var ie = 'ie';
	var otherValue = [ie];
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","1000")
}


//删除
function glySc(){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len>=1){
		var array = jQuery("[name=div_pkValue]:checked");
		var str = "";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
			str += pkValue+"!!@@!!";
		}
		confirmInfo("是否确定删除勾选的记录？",function(tag){
			if(tag=="ok"){
				var parameter={}
				var url="qgzx_glygl_ajax.do?method=glySc";	
				parameter["pkValue"]=str;							
				jQuery.ajaxSetup({async:false});	
				jQuery.post(url,
					parameter,
					function(result){
						alertInfo(result,function(tag){
							if(tag=="ok"){
								searchRs();
							}
						});
					}
				);
				jQuery.ajaxSetup({async:true});
			}
		});
	}else{
		alertInfo("请勾选需要删除的数据！",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
	}
}