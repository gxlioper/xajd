function  saveForm(){
	if(jQuery("#status").val()==""){
		showAlert("��ѡ���ڸ�״̬��");
		return false;
	}
    var dealZgh = jQuery("#dealZgh").val();
    var zghs = dealZgh.split(",");
	var statu  = jQuery("#status").val()=="1"?"�ڸ�":"���ڸ�";
	
	showConfirm("��ȷ���Ƿ�Ҫ��<font color='red'>"+zghs.length+"</font>λ��ѯʦ���ڸ�״̬��Ϊ��<font color='red'>"+ statu +"</font>��",{"okFun":function(){
		// �õ�JSON����
		var parameter ={status:jQuery("#status").val()};
		var url = "xlzx_zxs.do?method=setZgStatus&doType=update";
	 	jQuery("#form").ajaxSubmit({
			url:url,			
			type:"post",
			dataType:"json",
			success:function(data){
		 		 if(data["message"]=="����ɹ���"){
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
		
		