function saveForm(){
		  var cxdjdm=jQuery("#cxdjdm").val();
		  if(cxdjdm==""){
			  showAlert("�뽫��*����Ŀ��д������");
				return false;
		  }
		  var cxdjmc=jQuery("#cxdjmc").val();
		  if(cxdjmc==""){
			  showAlert("�뽫��*����Ŀ��д������");
				return false;
		  }
		  if(!validateNUM(cxdjdm)){
			  showAlert("����ֻ����������0-9��");
				return false;
		  }
		     var url = "xsxx_gygl_cxcxdj.do?method=addCxdj&type=save";
		      ajaxSubFormWithFun("cxdjForm",url,function(data){
		    	 if(data["message"]=="����ɹ���"){
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
		  showAlert("�뽫��*����Ŀ��д������");
			return false;
	  }
	     var url = "xsxx_gygl_cxcxdj.do?method=updateCxdj&type=update";
	      ajaxSubFormWithFun("cxdjForm",url,function(data){
	    	 if(data["message"]=="����ɹ���"){
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