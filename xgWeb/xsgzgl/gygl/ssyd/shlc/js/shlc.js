function save(url,checkId){
	jQuery("#form").ajaxSubmit({
		url:url,
		type:"post",
		dataType:"json",
		success:function(data){
	 		 if(data["message"]=="±£´æ³É¹¦£¡"){
	 			 showAlertDivLayer(data["message"],{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
	    	 }
	 		 else{
	 			showAlertDivLayer(data["message"]);
	    	 }
		},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});	
}
