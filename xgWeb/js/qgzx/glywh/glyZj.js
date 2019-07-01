
function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}
function searchRs(){
	var url = "qgzx_glygl_ajax.do?method=glyZj";
	var ie = 'ie';
	var otherValue = [ie];
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","1000")
}

//添加保存管理员
function zjbcGly(){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len>=1){
		var array = jQuery("[name=div_pkValue]:checked");
		var str = "";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
			str += pkValue+"!!@@!!";
		}
		var parameter={}
		var url="qgzx_glygl_ajax.do?method=glyZjbc";	
		parameter["pkValue"]=str;							
		jQuery.ajaxSetup({async:false});
		$("divWaiting").style.display="";
		$("divDisable").style.display="";
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				if("true"==result){
					 showAlert("添加成功",{},{"clkFun":function(){
			 				if (parent.window){
			 					refershParent();
			 				}
			 			}});
				}else{
					alertInfo(result,function(tag){
		     			if(tag=="ok"){
		     				return false;
		     			}
		     		});
		     		return false;
				}
			}
		);
		jQuery.ajaxSetup({async:true});
	}else{
		alertInfo("请勾选需要添加的数据！",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
	}
}