function saveForm(){
	//����֤ѧ��
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	var xfbcsj = jQuery("#xfbcsj").val();
	var xfbcje = jQuery("#xfbcje").val();

	
	if (jQuery.trim(xh) == ""){
		showAlert("����ѡ��ѧ����");
		return false;
	}
	if (jQuery.trim(xn) == ""){
		showAlert("����ѡ��ѧ�꣡");
		return false;
	}
	if (jQuery.trim(xfbcsj) == ""){
		showAlert("����ѡ������ѧ�Ѳ���ʱ�䣡");
		return false;
	}
	
	if (jQuery.trim(xfbcje) == ""){
		showAlert("����������ѧ�Ѳ�����");
		return false;
	}
	
	if (parseFloat(xfbcje) > parseFloat(99999)){
		showAlert("����ѧ�Ѳ������ܳ���99999Ԫ��");
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
    		  showAlert("��ѧ����"+jQuery.trim(xn)+"ѧ�꣬��������ѧ�Ѳ�����Ϣ���ڡ�");
    	  } else {
    		  showAlert(data["message"],{},{"clkFun":function(){
        			if (parent.window){
     				 refershParent();
        			}
      		  }});
    	  }
    	  
      });
  }