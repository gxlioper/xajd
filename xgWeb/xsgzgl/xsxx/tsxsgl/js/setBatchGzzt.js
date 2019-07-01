function  saveForm(){
	if(jQuery("#gzzt").val()==""){
		showAlert("请选择关注状态！");
		return false;
	}

    var ids = jQuery("#ids").val();
    var tsxs = ids.split(",");
	var gzzt  = jQuery("#gzzt").val()=="1"?"关注":"取消关注";
	
	showConfirm("请确认是否要将<font color='red'>"+tsxs.length+"</font>位学生的关注状态设为：<font color='red'>"+ gzzt +"</font>？",
			{"okFun":function(){

		// 得到JSON对象
	    var parameter ={ids:jQuery("#ids").val(),gzzt:jQuery("#gzzt").val()};
		var url = "tsxs_tsxswh.do?method=setTsxsGzzt&doType=update";
	 	jQuery("#form").ajaxSubmit({
			url:url,			
			type:"post",
			dataType:"json",
			success:function(data){
		 		 if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							frameElement.api.opener.refreshForm("tsxsgl_tsxswh.do");
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