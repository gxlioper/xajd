     	function save(){
	        var parameter ={};
			var url = "";
			if(jQuery("#doType").val()=="add" || jQuery("#doType").val()==""){
				if( jQuery("#xh").val()==""){
					return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		     	}
			 	url = "xlzx_thjl.do?method=saveThjlInfo&doType=add";
			 	parameter["xh"]=jQuery("#xh").val();
			 	parameter["zgh"]=jQuery("#zgh").val();
			}else if(jQuery("#doType").val()=="update"){
				url= "xlzx_thjl.do?method=updateThjlInfo";
				parameter["id"]=jQuery("#id").val();
			}

			if(jQuery("#thsj").val()=="" ){
				return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	     	}
			if("11527" == jQuery("#xxdm").val()){
				parameter["fjid"]= jQuery("#fjid").val();
			}
			parameter["thsj"]=jQuery("#thsj").val();
			parameter["thnr"]=jQuery("#thnr").val();
			
			jQuery.ajaxSetup({async:false});
			jQuery.post(url,parameter,
				function(result){
					if(result=true){
						showAlert("����ɹ���",parameter,{"clkFun":function(){
									frameElement.api.opener.refreshForm("xlzx_thjl_thjl.do");
									window.close();
							}});
					}else{
						showAlert("����ʧ�ܣ�");
						return false;
					}
				}
			);
			jQuery.ajaxSetup({async:true});
		}
		
		function init(){
			
			if(jQuery("#doType").val()=="view"){
				jQuery("#buttonSave").hide();
			}
		}
		

		function selectXh(){
			var gotoPath = jQuery("#path").val();
			if(jQuery("#zgh").val()!=""){
				gotoPath +="$zgh="+jQuery("#zgh").val();
			}
			showDialog("��ѡ��һ��ѧ��",800,500,"xlzx_tsxs.do?method=getTsxsInfo&gotoPath="+gotoPath);
		}
		
		function selectZxs(){
			var gotoPath = jQuery("#path").val();
			if(jQuery("#xh").val()!=""){
				gotoPath +="$xh="+jQuery("#xh").val();
			}
			showDialog("��ѡ��һ����ѯʦ",800,500,"xlzx_thjl.do?method=getJsInfo&gotoPath="+gotoPath);
		}
		
		