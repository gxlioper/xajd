  function saveZxs(){
     	if(jQuery("#zgh").val()=="" || jQuery("#status").val()==""){
     		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
     	}
     	if(jQuery("#datazt").val()!=""){//是否已经是咨询师
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
							showAlert("保存成功！",{},{"clkFun":function(){
										frameElement.api.opener.refreshForm("xlzx_zxs_zxs.do");
										window.close();
								}});
						}else{
							showAlert("该咨询师是否已是咨询师，保存失败！");
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
							showAlert("保存成功！",{},{"clkFun":function(){
										frameElement.api.opener.refreshForm("xlzx_zxs_zxs.do");
										iFClose();
								}});
						}else{
							showAlert("保存失败！");
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