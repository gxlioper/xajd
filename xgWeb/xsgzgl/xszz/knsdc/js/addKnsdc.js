function saveForm(){
	  var dcmc=jQuery.trim(jQuery("#dcmc").val());
	  var xmsm=jQuery("#xmsm").val();
	  if(dcmc==""){
		  showAlert("�뽫��*����Ŀ��д������");
		  return false;
	  }

	  if(xmsm.length > 500){
		  showAlert("���������������Ϊ500,���Ѿ���������ȷ�ϣ�����");
		  return false;
	  }
     var url = "xszz_knsdc.do?method=addKnsdc&type=save";
      ajaxSubFormWithFun("knsdcForm",url,function(data){
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