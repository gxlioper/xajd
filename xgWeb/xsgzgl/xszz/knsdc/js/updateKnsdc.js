 function saveForm(){
	 var dcdm=jQuery.trim(jQuery("#dcdm").val());
	 var dcmc=jQuery.trim(jQuery("#dcmc").val());
	 var xmsm=jQuery("#xmsm").val();
	  if(dcdm=="" || dcmc==""){
		  showAlert("�뽫��*����Ŀ��д������");
			return false;
	  }

	  if(xmsm.length > 500){
		  showAlert("���������������Ϊ500,���Ѿ���������ȷ�ϣ�����");
		  return false;
	  }
	  var oldDcdm = jQuery("#oldDcdm").val();
		var url = "xszz_knsdc.do?method=updateKnsdc&type=update&oldDcdm="+oldDcdm;
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