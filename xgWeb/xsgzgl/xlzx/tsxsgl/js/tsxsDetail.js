jQuery(function(){
	if("11527" == jQuery("#xxdm").val()){
		var gzzt = jQuery("#gzzt").val();
		if(gzzt != "0"){
			jQuery("select[name='yyms']").val("");
			jQuery("#yymsTh").hide();
			jQuery("#yymsTd").hide();
		}else{
			jQuery("#yymsTh").show();
			jQuery("#yymsTd").show();
		}
	}else{
		jQuery("select[name='yyms']").val("");
		jQuery("#yymsTh").hide();
		jQuery("#yymsTd").hide();
	}
});
function init(){
			if($("doType").value!="add"){
				var knlxdm = $("knlxdm").value;;
				var knlxList = knlxdm.split(",");
				for(var i=0;i<knlxList.length;i++){
					jQuery("input[type='checkbox'][name=knlxBoxList][value='"+knlxList[i]+"']").attr("checked",true);
				}
				if($("doType").value=="view"){
					jQuery("input[type='checkbox'][name=knlxBoxList]").attr("disabled",true);
				}
			}
			
			if($("doType").value=="view"){
				jQuery("#buttonSave").hide();
			}
		}
	function getKnlxdms(){
		 var size = jQuery("input[type='checkbox'][name='knlxBoxList']:checked").size();
		 if(size == 0){
			showAlert('请选择困难类型！');
			return "";
		}
		var knlxdms = "";
		var flag = false;
		jQuery("input[type='checkbox'][name=knlxBoxList]:checked").each(function(index){
			if(flag){
				knlxdms += ",";
			}else{
				flag = true;
			}
			knlxdms += jQuery(this).val();
		});
		return knlxdms;
	}

	function save(){
     	if( jQuery("#xh").val()=="" || jQuery("#gzzt").val()=="" ||
     			jQuery("input[type='checkbox'][name='knlxBoxList']:checked").size()==0 || getKnlxdms()==""){
     		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
     	}
		var knlxdms = getKnlxdms();
        var parameter ={};
		
		var url = "";
		if($("doType").value=="add" || $("doType").value==""){
		 	url = "xlzx_tsxs.do?method=saveTsxsInfo&doType=add";
		 	parameter["xh"]=jQuery("#xh").val();
			
		}else if($("doType").value=="update"){
			url= "xlzx_tsxs.do?method=updateTsxsInfo";
			parameter["id"]=jQuery("#id").val();;
		}
		parameter["knlxdm"]=knlxdms;
		parameter["sjzt"]=1;
		parameter["qksm"]= jQuery("#qksm").val();
		parameter["gzzt"]= jQuery("#gzzt").val();
		
		//西安科技大学添加处理措施
		if("10704" == jQuery("#xxdm").val()){
			parameter["clcs"]= jQuery("#clcs").val();
			parameter["lrsj"]= jQuery("#lrsj").val();
			parameter["gzsj"]= jQuery("#gzsj").val();
			parameter["gznr"]= jQuery("#gznr").val();
			parameter["bz"]= jQuery("#bz").val();
			parameter["jbqkms"]= jQuery("#jbqkms").val();
			parameter["fjid"]= jQuery("#fjid").val();
		}
		//湖南城市学院
		if("11527" == jQuery("#xxdm").val()){
			parameter["fjid"]= jQuery("#fjid").val();
			parameter["zc"]= jQuery("#zc").val();
			parameter["yyms"]= jQuery("#yyms").val();
		}
		jQuery.ajaxSetup({async:false});
		jQuery.post(url,parameter,
			function(result){
				if(result=true){
					showAlert("保存成功！",parameter,{"clkFun":function(){
								frameElement.api.opener.refreshForm("xlzx_tsxs_tsxs.do");
								window.close();
						}});
				}else{
					showAlert("保存失败！");
					return false;
				}
			}
		);
		
		jQuery.ajaxSetup({async:true});
	}
		
	function gz(){
		var gzzt = jQuery("#gzzt").val();
		if(gzzt == "0"){
			jQuery("#yymsTh").show();
			jQuery("#yymsTd").show();
		}else{
			jQuery("select[name='yyms']").val("");
			jQuery("#yymsTh").hide();
			jQuery("#yymsTd").hide();
		}
	}	