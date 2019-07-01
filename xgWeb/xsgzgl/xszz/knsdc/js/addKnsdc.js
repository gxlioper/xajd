function saveForm(){
	  var dcmc=jQuery.trim(jQuery("#dcmc").val());
	  var xmsm=jQuery("#xmsm").val();
	  if(dcmc==""){
		  showAlert("请将带*的项目填写完整！");
		  return false;
	  }

	  if(xmsm.length > 500){
		  showAlert("该输入项最大字数为500,现已经超过，请确认！！！");
		  return false;
	  }
     var url = "xszz_knsdc.do?method=addKnsdc&type=save";
      ajaxSubFormWithFun("knsdcForm",url,function(data){
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