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
		showAlert("��ѡ���ע״̬��");
		return false;
	}

    var dealTsxs = jQuery("#dealTsxs").val();
    var tsxs = dealTsxs.split(",");
	var gzzt  = jQuery("#gzzt option:selected").html();
	
	showConfirm("��ȷ���Ƿ�Ҫ��<font color='red'>"+tsxs.length+"</font>λѧ���Ĺ�ע״̬��Ϊ��<font color='red'>"+ gzzt +"</font>��",
			{"okFun":function(){

		// �õ�JSON����
	    var parameter ={dealTsxs:jQuery("#dealTsxs").val(),gzzt:jQuery("#gzzt").val(),yyms:jQuery("#yyms").val()};
		var url = "xlzx_tsxs.do?method=setTsxsGzzt&doType=update";
	 	jQuery("#form").ajaxSubmit({
			url:url,			
			type:"post",
			dataType:"json",
			success:function(data){
		 		 if(data["message"]=="����ɹ���"){
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