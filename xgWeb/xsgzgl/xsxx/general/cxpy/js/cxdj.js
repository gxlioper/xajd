function saveForm(){
		  var cxdjdm=jQuery("#cxdjdm").val();
		  if(cxdjdm==""){
			  showAlert("请将带*的项目填写完整！");
				return false;
		  }
		  var cxdjmc=jQuery("#cxdjmc").val();
		  if(cxdjmc==""){
			  showAlert("请将带*的项目填写完整！");
				return false;
		  }
		  if(!validateNUM(cxdjdm)){
			  showAlert("代码只能输入数字0-9！");
				return false;
		  }
		     var url = "xsxx_gygl_cxcxdj.do?method=addCxdj&type=save";
		      ajaxSubFormWithFun("cxdjForm",url,function(data){
		    	 if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
		    	 }else{
		    		 showAlert(data["message"]);
		    		 
		    	 }
				});
	  	}

function saveUpdate(){
	var cxdjmc=jQuery("#cxdjmc").val();
	  if(cxdjmc==""){
		  showAlert("请将带*的项目填写完整！");
			return false;
	  }
	     var url = "xsxx_gygl_cxcxdj.do?method=updateCxdj&type=update";
	      ajaxSubFormWithFun("cxdjForm",url,function(data){
	    	 if(data["message"]=="保存成功！"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
	    	 }else{
	    		 showAlert(data["message"]);
	    		 
	    	 }
		 });
	
}