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
		        if("11527" == jQuery("#xxdm").val()){
		        	 var sclydms = getSclydms();
		        	 parameter["sclydm"]=sclydms;
		        }
		       
				parameter["zgh"]=jQuery("#zgh").val();
				
				parameter["status"]=jQuery("#status").val();
				
				parameter["lxdh"]=jQuery("#lxdh").val();
				
				parameter["address"]=jQuery("#address").val();
				
				parameter["kjdrs"]=jQuery("#kjdrs").val();
				
				parameter["zxszg"]=jQuery("#zxszg").val();
				
				parameter["zxsjj"]=jQuery("#zxsjj").val();
				
				parameter["datazt"]=1;
				
				
				var url = "xlzx_zxs.do?method=saveZxsInfo&doType=add";
				jQuery.ajaxSetup({async:false});
				jQuery.post(url,parameter,
					function(result){
						if(result=="true"){
							showAlert("����ɹ���",{},{"clkFun":function(){
								//		frameElement.api.opener.refreshForm("xlzx_zxs_zxs.do");
								//		window.close();
			        			if (parent.window){
			        				 refershParent();
			           			}
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
		        if("11527" == jQuery("#xxdm").val()){
		        	 var sclydms = getSclydms();
		        	 parameter["sclydm"]=sclydms;
		        }
		        
				parameter["zgh"]=jQuery("#zgh").val();
				
				parameter["status"]=jQuery("#status").val();
								
			//	parameter["lxdh"]=jQuery("#lxdh").val();
				
				parameter["address"]=jQuery("#address").val();
				
				parameter["kjdrs"]=jQuery("#kjdrs").val();
				
				parameter["zxszg"]=jQuery("#zxszg").val();
				
				parameter["zxsjj"]=jQuery("#zxsjj").val();
								
				parameter["datazt"]=1;
				
				
				var url = "xlzx_zxs.do?method=updateZxsInfo";
				jQuery.ajaxSetup({async:false});
				jQuery.post(url,parameter,
					function(result){
						if(result=="true"){
							showAlert("����ɹ���",{},{"clkFun":function(){
								//	frameElement.api.opener.refreshForm("xlzx_zxs_zxs.do");
								//	iFClose();
			        			if (parent.window){
			        				 refershParent();
			           			}
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
			if($("doType").value!="add"){
				if($("doType").value=="view"){
					jQuery("#buttonSave").hide();
				}else{
					if("11527" == jQuery("#xxdm").val()){
						var sclydm = $("sclydm").value;;
						var sclyList = sclydm.split(",");
						for(var i=0;i<sclyList.length;i++){
							jQuery("input[type='checkbox'][name=sclyBoxList][value='"+sclyList[i]+"']").attr("checked",true);
						}
					}
				}
				
			}
			
		}
		
		function getSclydms(){
			var sclydms = "";
			 var size = jQuery("input[type='checkbox'][name='sclyBoxList']:checked").size();
			if(size > 0){
				var flag = false;
				jQuery("input[type='checkbox'][name=sclyBoxList]:checked").each(function(index){
					if(flag){
						sclydms += ",";
					}else{
						flag = true;
					}
					sclydms += jQuery(this).val();
				});
			}
			return sclydms;
		}