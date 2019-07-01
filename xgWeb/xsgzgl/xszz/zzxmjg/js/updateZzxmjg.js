function saveForm(){
	//先验证学号
	var xh = jQuery("#xh").val();
	var pdxn = jQuery("#pdxn").val();
	var pdxq = jQuery("#pdxq option:selected").text();
	var lbdm = jQuery("#lbdm").val();
	var xmmc = jQuery("#xmmc").val();

	
	if (jQuery.trim(xh) == ""){
		showAlert("请先选择学生！");
		return false;
	}
	if (jQuery.trim(lbdm) == ""){
		showAlert("请先选择项目类别！");
		return false;
	}
	if (jQuery.trim(xmmc) == ""){
		showAlert("请先输入项目名称！");
		return false;
	}
	if (jQuery.trim(pdxn) == ""){
		showAlert("请先选择项目评定学年！");
		return false;
	}
		
     var url = "xszz_zzxmjg.do?method=updateZzxmjg&type=update";
     ajaxSubFormWithFun("zzxmjgForm",url,function(data){
   	  
	   	  if (data["success"] == "false"){
    		  if(jQuery.trim(pdxq) == ""){

        		  showAlert("该学生在"+jQuery.trim(pdxn)+"学年，已获得【"+jQuery.trim(xmmc)+"】。");
    		  }else{

        		  showAlert("该学生在"+jQuery.trim(pdxn)+"学年，"+jQuery.trim(pdxq)+"学期申请已获得【"+jQuery.trim(xmmc)+"】。");  
    		  }
	   	  } else {
	   		  showAlert(data["message"],{},{"clkFun":function(){
	       			if (parent.window){
	    				 refershParent();
	       			}
	     	}});
	   	  }
     });
  }