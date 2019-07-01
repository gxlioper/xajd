function saveForm(){
	//先验证学号
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	var xfbcsj = jQuery("#xfbcsj").val();
	var xfbcje = jQuery("#xfbcje").val();

	
	if (jQuery.trim(xh) == ""){
		showAlert("请先选择学生！");
		return false;
	}
	if (jQuery.trim(xn) == ""){
		showAlert("请先选择学年！");
		return false;
	}
	if (jQuery.trim(xfbcsj) == ""){
		showAlert("请先选择入伍学费补偿时间！");
		return false;
	}
	
	if (jQuery.trim(xfbcje) == ""){
		showAlert("请输入入伍学费补偿金额！");
		return false;
	}
	
	if (parseFloat(xfbcje) > parseFloat(99999)){
		showAlert("入伍学费补偿金额不能超过99999元！");
		return false;
	}
	
     var url = "rwgl_rwxfbcgl.do";
     if(null!=jQuery("#guid").val() && ""!=jQuery("#guid").val() && "null"!=jQuery("#guid").val()){
    	 url+="?method=updateRwxfDgbc&type=update";
     }else{
    	 url+="?method=addRwxfDgbc&type=save";
     }
      ajaxSubFormWithFun("rwxfbcglForm",url,function(data){
    	  
    	  if (data["success"] == "false"){
    		  showAlert("该学生在"+jQuery.trim(xn)+"学年，已有入伍学费补偿信息存在。");
    	  } else {
    		  showAlert(data["message"],{},{"clkFun":function(){
        			if (parent.window){
     				 refershParent();
        			}
      		  }});
    	  }
    	  
      });
  }