// 有效时设置
function changeYxssz(){
	var yxssz = jQuery("[name=yxssz]:checked").val();
	if("xs"==yxssz){
		jQuery("#gwjssj_th").html('<span class="red">*</span>岗位结束日期');
		jQuery("#gwjssj").show();
		jQuery("#gwjssj_tr").show();
		jQuery("#gwkssj").unbind("click");
		jQuery("#gwkssj").bind("click", function(){ 
			return showCalendar('gwkssj','yyyyMMdd',true,'gwjssj');
		});
		jQuery("#gwjssj").unbind("click");
		jQuery("#gwjssj").bind("click", function(){ 
			return showCalendar('gwjssj','yyyyMMdd',false,'gwkssj');
		});
	}else{
		jQuery("#gwjssj_th").html('');
		jQuery("#gwjssj").hide();
		jQuery("#gwjssj_tr").hide();
		jQuery("#gwkssj").unbind("click");
		jQuery("#gwkssj").bind("click", function(){ 
			return showCalendar('gwkssj','yyyyMMdd');
		});
		jQuery("#gwjssj").unbind("click");
		jQuery("#gwjssj").bind("click", function(){ 
			return showCalendar('gwjssj','yyyyMMdd');
		});
		jQuery("#gwjssj").val("");
	}
}

//全选按钮
function xz(obj){
	var checkboxs = jQuery("input[name='sqxy']");
	if(jQuery(obj).prop("checked") == true){
		jQuery(checkboxs).each(function(i,n){
			if(jQuery(n).prop("checked") == false){
				jQuery(n).attr("checked",true);	
			}
		})
	}else{
		jQuery(checkboxs).each(function(i,n){
			if(jQuery(n).prop("checked") == true){
				jQuery(n).attr("checked",false);	
			}
		})
	}
}