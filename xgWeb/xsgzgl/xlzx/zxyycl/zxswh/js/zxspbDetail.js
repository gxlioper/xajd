function getSelectedZgh(){
     		var zghs = "";
			var flag = false;
			jQuery("input[type='checkbox'][name=zxsBoxList]:checked").each(function(index){
				if(flag){
					zghs += ",";
				}else{
					flag = true;
				}
				zghs += jQuery(this).val();
			});
     		return zghs;
     }
     
        
     function saveZxsPbInfo(){
     	var zghs = getSelectedZgh();
		if(zghs==""){
			  if(jQuery("#doType").val()=="add"){
				  showAlert("请安排至少一位咨询师！");
				  return false;
			  }else if(jQuery("#doType").val()=="update"){//取消所有的咨询师排班，则删除当前日期的排班信息。
				  var pbid = jQuery("#pbid").val();
				  var url = "xlzx_zxspb.do?method=delZxspbById";
				  var parameter ={id:pbid};
				  saveData(url,parameter);
			  }
		}
		var parameter ={};
	     var url=''; 
	     if(jQuery("#doType").val()=="add"){
	     	 //验证同一天只能新增排班一次
		     if(getPbInfoByDate()=="true"){//已排班
	     		return false;
	     	}
	     	if(jQuery("input[type='radio'][name=sfCopyPb][value=1]").prop("checked")==true){//批量排班
	     		if(jQuery("#pbjssj").val()==''){
	     			showAlert("截止时间不得为空！");
	     			return false;
	     		}
	     		var pbqssj = new Date((jQuery("#pbqssj").val()).replace(/\-/gi,"/")).getTime();
	     		var pbjssj = new Date((jQuery("#pbjssj").val()).replace(/\-/gi,"/")).getTime();
	     		if(pbqssj>=pbjssj){
	     			showAlert("不得早于当前排班日期！");
	     			return false;
	     		}	
	     		url="xlzx_zxspb.do?method=saveBatchZxspbInfo&doType=add&startDate="+jQuery("#pbqssj").val()+"&endDate="+jQuery("#pbjssj").val();
	     	}else{
	     		url="xlzx_zxspb.do?method=saveZxspbInfo&doType=add";
	     	}
	     }else if(jQuery("#doType").val()=="update"){
	     	url="xlzx_zxspb.do?method=updateZxspbInfo&doType=update";
	     	parameter["id"]=jQuery("#pbid").val();
	     }
	     parameter["bz"]=jQuery("#bz").val();
	     parameter["datazt"]=1;
	     parameter["pbdate"]=jQuery("#date").val();
	     parameter["zgh"]=zghs;
	     saveData(url,parameter);
     }
     
     
     function saveData(url,parameter){
    	 jQuery.ajaxSetup({async : false});
			jQuery.post(url,parameter,function(data){
					if(data=="true"){
								showAlert("保存成功！",{},{"clkFun":function(){
										frameElement.api.opener.loadData();
										iFClose();
								}});
						}else{
							showAlert("保存失败！");
							return false;
						}
			});
		 jQuery.ajaxSetup({async : true});
     } 
     
     function getPbInfoByDate(){
     	var result = '';
     	var url="xlzx_zxspb.do?method=getPbInfoByDate";
     	 jQuery.ajaxSetup({async : false});
			jQuery.post(url,{pbdate:$("date").value},function(data){
						result = data;
			},'json');
		 jQuery.ajaxSetup({async : true});
		 return result;
	}
		 
	//加载咨询师信息
	function loadZxsxx(){
		var zxsZgh=document.getElementsByName('zxsZgh');
		var zxsXm=document.getElementsByName('zxsXm');
		var zxsXb=document.getElementsByName('zxsXb');
		var zxsBmmc=document.getElementsByName('zxsBmmc');
		var zxsCheckboxzt=document.getElementsByName('zxsCheckboxzt');
		var checkFlag = false;
		var len=zxsZgh.length;
		var html="";
		for(i=1;i<=len;i++){
			html+="<div class='demo_data2'>";
			html+="<input type='checkbox' "+zxsCheckboxzt[i-1].value+" name='zxsBoxList' id='zxs_"+i+"'  value='"+zxsZgh[i-1].value+"'  ><label style='cursor:pointer' for='zxs_"+i+"'>"+zxsXm[i-1].value;
			if(zxsXb[i-1].value!=null && zxsXb[i-1].value!=''){
				html+="["+zxsXb[i-1].value+"]";
			}
			if(zxsBmmc[i-1].value!=null && zxsBmmc[i-1].value!=''){
				html+="["+zxsBmmc[i-1].value+"]";
			}
			html+="["+zxsZgh[i-1].value+"]";
			html+="</label></div>";
		}
		$("zxsInfos").innerHTML=html;
	}
	

	function init(){
		sfqyCopyPb();
		loadZxsxx();
		if($("doType").value!="add"){
			var zghs = $("zghs").value;
			var zghList = zghs.split(",");
			for(var i=0;i<zghList.length;i++){
				jQuery("input[type='checkbox'][name=zxsBoxList][value='"+zghList[i]+"']").attr("checked",true);
			}
			if($("doType").value=="view"){
				jQuery("input[type='checkbox'][name=zxsBoxList]").attr("disabled",true);
			}
		}
		
		if($("doType").value=="view"){
			jQuery("#buttonSave").hide();
		}
	}
	
	function sfqyCopyPb(){
		if(jQuery("input[type='radio'][name=sfCopyPb][value=1]").prop("checked")==true){
			jQuery("th[name=copyQzrqName]").show();
			jQuery("td[name=copyQzrqName]").show();
		}else if(jQuery("input[type='radio'][name=sfCopyPb][value=2]").prop("checked")==true){
			jQuery("th[name=copyQzrqName]").hide();
			jQuery("td[name=copyQzrqName]").hide();
		}
	}
		