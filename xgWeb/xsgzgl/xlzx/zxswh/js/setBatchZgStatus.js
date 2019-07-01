function  saveForm(){
	if(jQuery("#status").val()==""){
		showAlert("请选择在岗状态！");
		return false;
	}
    var dealZgh = jQuery("#dealZgh").val();
    var zghs = dealZgh.split(",");
	var statu  = jQuery("#status").val()=="1"?"在岗":"不在岗";
	
	showConfirm("请确认是否要将<font color='red'>"+zghs.length+"</font>位咨询师的在岗状态设为：<font color='red'>"+ statu +"</font>？",{"okFun":function(){
		// 得到JSON对象
		var parameter ={status:jQuery("#status").val()};
		var url = "xlzx_zxs.do?method=setZgStatus&doType=update";
	 	jQuery("#form").ajaxSubmit({
			url:url,			
			type:"post",
			dataType:"json",
			success:function(data){
		 		 if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
						//frameElement.api.opener.refreshForm("xlzx_zxs_zxs.do");
						//iFClose();
	        			if (parent.window){
	        				 refershParent();
	           			}
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
		
		