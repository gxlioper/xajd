 function saveForm(){
	 var dcdm=jQuery.trim(jQuery("#dcdm").val());
	 var dcmc=jQuery.trim(jQuery("#dcmc").val());
	 var xmsm=jQuery("#xmsm").val();
	  if(dcdm=="" || dcmc==""){
		  showAlert("请将带*的项目填写完整！");
			return false;
	  }

	  if(xmsm.length > 500){
		  showAlert("该输入项最大字数为500,现已经超过，请确认！！！");
		  return false;
	  }
	  var oldDcdm = jQuery("#oldDcdm").val();
		var url = "xszz_knsdc.do?method=updateKnsdc&type=update&oldDcdm="+oldDcdm;
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