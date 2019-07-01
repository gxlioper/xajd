jQuery(function(){
	if("11527" == jQuery("#xxdm").val()){
		var gzzt = jQuery("#gzzt").val();
		if(gzzt != "0"){
			jQuery("select[name='yyms']").val("");
			jQuery("#yymsTh").hide();
			jQuery("#yymsTd").hide();
		}
	}else{
		jQuery("select[name='yyms']").val("");
		jQuery("#yymsTh").hide();
		jQuery("#yymsTd").hide();
	}
	
});
function  saveForm(){
	if(jQuery("#gzzt").val()==""){
		showAlert("请选择关注状态！");
		return false;
	}

    var dealTsxs = jQuery("#dealTsxs").val();
    var tsxs = dealTsxs.split(",");
	var gzzt  = jQuery("#gzzt option:selected").html();
	
	showConfirm("请确认是否要将<font color='red'>"+tsxs.length+"</font>位学生的关注状态设为：<font color='red'>"+ gzzt +"</font>？",
			{"okFun":function(){

		// 得到JSON对象
	    var parameter ={dealTsxs:jQuery("#dealTsxs").val(),gzzt:jQuery("#gzzt").val(),yyms:jQuery("#yyms").val()};
		var url = "xlzx_tsxs.do?method=setTsxsGzzt&doType=update";
	 	jQuery("#form").ajaxSubmit({
			url:url,			
			type:"post",
			dataType:"json",
			success:function(data){
		 		 if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							frameElement.api.opener.refreshForm("xlzx_tsxs_tsxs.do");
							iFClose();
		    			}});
		    	 }
		 		 else{
		    		 showAlert(data["message"]);
		    	 }
			},
			contentType:"application/x-www-form-urlencoded;charset=utf-8"
		});	
	 	
	}});
}
function gz(){
	var gzzt = jQuery("#gzzt").val();
	if(gzzt == "0"){
		jQuery("#yymsTh").show();
		jQuery("#yymsTd").show();
	}else{
		jQuery("select[name='yyms']").val("");
		jQuery("#yymsTh").hide();
		jQuery("#yymsTd").hide();
	}
}	