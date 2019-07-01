//岗位性质div操作 增加、修改
var doType;
function gwxzDiv(type,topMsg){
	
	var textValue;
	var len=jQuery("[name=div_pkValue]:checked").length;
	$("gwxzdm").value="";
	$("gwxzmc").value="";
	doType=type;
	if(type=="update"){
		if(len==1){
			var pkValue = jQuery("[name=div_pkValue]:checked").val();
			var message = checkScInfo(pkValue);
			if("true"!=message){
				alertInfo(message,function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
			}
			$("gwxzdm").value=pkValue;
			$("gwxzmc").value=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(3).text();
			 textValue = $("gwxzmc").value;
		}else{
			alertInfo("请勾选一条记录进行修改！");
			return false;
		}
	}
	tipsWindown(topMsg,"id:tempDiv","350","120","true","","true","id");
	if(textValue != ""){
		jQuery("[name=gwxzmc]").val(textValue);
	}
	jQuery("#gwxzmc").focus();
}
//岗位性质div保存
function gwxzDivSave(){
	if($("gwxzmc").value.trim()==""){
		alertInfo("请输入岗位性质名称！");
		return false;
	}
	var parameter={}
	var url="qgzx_jcdmwh_ajax.do?method=gwxzBc&doType="+doType;
	parameter["gwxzdm"]=escape(jQuery("#gwxzdm").val());
	parameter["gwxzmc"]=escape(jQuery("#gwxzmc").val());						
	jQuery.ajaxSetup({async:false});	
	jQuery.post(url,
		parameter,
		function(result){
			if("保存成功"==result){
				alertInfo(result,function(tag){
					if(tag=="ok"){
						closeWindown();
						searchRs();
					}
				});
			}else{
				alertInfo(result);
			}
		}
	);
	jQuery.ajaxSetup({async:true});
}

//删除
function gwxzSc(){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len>=1){
		var array = jQuery("[name=div_pkValue]:checked");
		var str = "";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
			str += pkValue+"!!@@!!";
		}
		var message = checkScInfo(str);
		if("true"!=message){
			alertInfo(message,function(tag){
				if(tag=="ok"){
					return false;
				}
			});
			return false;
		}
		confirmInfo("是否确定删除勾选的记录？",function(tag){
			if(tag=="ok"){
				var parameter={}
				var url="qgzx_jcdmwh_ajax.do?method=gwxzSc";	
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
//验证修改删除信息
function checkScInfo(str){
	var data = "true";
	var parameter={}
	var url="qgzx_jcdmwh_ajax.do?method=checkScInfo";	
	parameter["pkValue"]=str;							
	jQuery.ajaxSetup({async:false});	
	jQuery.post(url,
		parameter,
		function(result){
			data = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return data;
}







//岗位性质div操作 增加、修改
var doType;
function yrdwDiv(type,topMsg){
	var textValue;
	var len=jQuery("[name=div_pkValue]:checked").length;
	$("yrdwdm").value="";
	$("yrdwmc").value="";
	doType=type;
	if(type=="update"){
		if(len==1){
			var pkValue = jQuery("[name=div_pkValue]:checked").val();
			var message = checkyrdwScInfo(pkValue);
			if("true"!=message){
				alertInfo(message,function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
			}
			$("yrdwdm").value=pkValue;
			$("yrdwmc").value=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(3).text();
			
			textValue = $("yrdwmc").value;
		}else{
			alertInfo("请勾选一条记录进行修改！");
			return false;
		}
	}
	tipsWindown(topMsg,"id:tempDiv","350","120","true","","true","id");

	if(textValue != ""){
			jQuery("[name=yrdwmc]").val(textValue);
		}
	jQuery("#yrdwmc").focus();
}
//岗位性质div保存
function yrdwDivSave(){
	if($("yrdwmc").value.trim()==""){
		alertInfo("请输入岗位性质名称！");
		return false;
	}
	var parameter={}
	var url="qgzx_jcdmwh_ajax.do?method=yrdwBc&doType="+doType;
	parameter["yrdwdm"]=escape(jQuery("#yrdwdm").val());
	parameter["yrdwmc"]=escape(jQuery("#yrdwmc").val());						
	jQuery.ajaxSetup({async:false});	
	jQuery.post(url,
		parameter,
		function(result){
			if("保存成功"==result){
				alertInfo(result,function(tag){
					if(tag=="ok"){
						closeWindown();
						searchRs();
					}
				});
			}else{
				alertInfo(result);
			}
		}
	);
	jQuery.ajaxSetup({async:true});
}

//删除
function yrdwSc(){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len>=1){
		var array = jQuery("[name=div_pkValue]:checked");
		var str = "";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
			str += pkValue+"!!@@!!";
		}
		var message = checkyrdwScInfo(str);
		if("true"!=message){
			alertInfo(message,function(tag){
				if(tag=="ok"){
					return false;
				}
			});
			return false;
		}
		confirmInfo("是否确定删除勾选的记录？",function(tag){
			if(tag=="ok"){
				var parameter={}
				var url="qgzx_jcdmwh_ajax.do?method=yrdwSc";	
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
//验证修改删除信息
function checkyrdwScInfo(str){
	var data = "true";
	var parameter={}
	var url="qgzx_jcdmwh_ajax.do?method=checkyrdwScInfo";	
	parameter["pkValue"]=str;							
	jQuery.ajaxSetup({async:false});	
	jQuery.post(url,
		parameter,
		function(result){
			data = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return data;
}