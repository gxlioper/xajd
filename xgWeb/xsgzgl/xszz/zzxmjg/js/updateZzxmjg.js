function saveForm(){
	//����֤ѧ��
	var xh = jQuery("#xh").val();
	var pdxn = jQuery("#pdxn").val();
	var pdxq = jQuery("#pdxq option:selected").text();
	var lbdm = jQuery("#lbdm").val();
	var xmmc = jQuery("#xmmc").val();

	
	if (jQuery.trim(xh) == ""){
		showAlert("����ѡ��ѧ����");
		return false;
	}
	if (jQuery.trim(lbdm) == ""){
		showAlert("����ѡ����Ŀ���");
		return false;
	}
	if (jQuery.trim(xmmc) == ""){
		showAlert("����������Ŀ���ƣ�");
		return false;
	}
	if (jQuery.trim(pdxn) == ""){
		showAlert("����ѡ����Ŀ����ѧ�꣡");
		return false;
	}
		
     var url = "xszz_zzxmjg.do?method=updateZzxmjg&type=update";
     ajaxSubFormWithFun("zzxmjgForm",url,function(data){
   	  
	   	  if (data["success"] == "false"){
    		  if(jQuery.trim(pdxq) == ""){

        		  showAlert("��ѧ����"+jQuery.trim(pdxn)+"ѧ�꣬�ѻ�á�"+jQuery.trim(xmmc)+"����");
    		  }else{

        		  showAlert("��ѧ����"+jQuery.trim(pdxn)+"ѧ�꣬"+jQuery.trim(pdxq)+"ѧ�������ѻ�á�"+jQuery.trim(xmmc)+"����");  
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