  function saveZxs(){
     	if(jQuery("#zgh").val()=="" || jQuery("#status").val()==""){
     		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
     	}
     	if(jQuery("#datazt").val()!=""){//�Ƿ��Ѿ�����ѯʦ
     		modiZxsInfo();
     	}else{
     		saveZxsInfo();
     	}
     }
     
     function saveZxsInfo(){
		        var parameter ={};
				
				parameter["zgh"]=jQuery("#zgh").val();
												
				parameter["status"]=jQuery("#status").val();
				
				parameter["lxdh"]=jQuery("#lxdh").val();
				
				parameter["address"]=jQuery("#address").val();
				
				parameter["kjdrs"]=jQuery("#kjdrs").val();
				
				parameter["zxszg"]=jQuery("#zxszg").val();
				
				parameter["zxsjj"]=jQuery("#zxsjj").val();
				
				parameter["datazt"]=1;
				
				if("10026" == jQuery("#xxdm").val()) {
					parameter["xq"]=jQuery("#xq").val();
				}
				
				var url = "xlzx_zxs.do?method=saveZxsInfo&doType=add";
				jQuery.ajaxSetup({async:false});
				jQuery.post(url,parameter,
					function(result){
						if(result=="true"){
							showAlert("����ɹ���",{},{"clkFun":function(){
										frameElement.api.opener.refreshForm("xlzx_zxs_zxs.do");
										window.close();
								}});
						}else{
							showAlert("����ѯʦ�Ƿ�������ѯʦ������ʧ�ܣ�");
							return false;
						}
					}
				);
				jQuery.ajaxSetup({async:true});
		}
		
		function modiZxsInfo(){
		        var parameter ={};
				
				parameter["zgh"]=jQuery("#zgh").val();
				
				parameter["status"]=jQuery("#status").val();
								
			//	parameter["lxdh"]=jQuery("#lxdh").val();
				
				parameter["address"]=jQuery("#address").val();
				
				parameter["kjdrs"]=jQuery("#kjdrs").val();
				
				parameter["zxszg"]=jQuery("#zxszg").val();
				
				parameter["zxsjj"]=jQuery("#zxsjj").val();
								
				parameter["datazt"]=1;
				
				if("10026" == jQuery("#xxdm").val()) {
					parameter["xq"]=jQuery("#xq").val();
				}
				
				var url = "xlzx_zxs.do?method=updateZxsInfo";
				jQuery.ajaxSetup({async:false});
				jQuery.post(url,parameter,
					function(result){
						if(result=="true"){
							showAlert("����ɹ���",{},{"clkFun":function(){
										frameElement.api.opener.refreshForm("xlzx_zxs_zxs.do");
										iFClose();
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
			if($("doType").value=="view"){
				jQuery("#buttonSave").hide();
			}
		}