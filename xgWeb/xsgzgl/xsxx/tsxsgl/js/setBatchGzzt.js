function  saveForm(){
	if(jQuery("#gzzt").val()==""){
		showAlert("��ѡ���ע״̬��");
		return false;
	}

    var ids = jQuery("#ids").val();
    var tsxs = ids.split(",");
	var gzzt  = jQuery("#gzzt").val()=="1"?"��ע":"ȡ����ע";
	
	showConfirm("��ȷ���Ƿ�Ҫ��<font color='red'>"+tsxs.length+"</font>λѧ���Ĺ�ע״̬��Ϊ��<font color='red'>"+ gzzt +"</font>��",
			{"okFun":function(){

		// �õ�JSON����
	    var parameter ={ids:jQuery("#ids").val(),gzzt:jQuery("#gzzt").val()};
		var url = "tsxs_tsxswh.do?method=setTsxsGzzt&doType=update";
	 	jQuery("#form").ajaxSubmit({
			url:url,			
			type:"post",
			dataType:"json",
			success:function(data){
		 		 if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							frameElement.api.opener.refreshForm("tsxsgl_tsxswh.do");
							iFClose();
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