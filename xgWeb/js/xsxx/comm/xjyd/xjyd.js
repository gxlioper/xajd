function checkForm(url){
	var sqrfw = "";
	var sqr = jQuery('input[type=checkbox]:checked');
	
	if (sqr.length==5){
		sqrfw = "ȫ��";
	} else {
		for(var i = 0 ; i < sqr.length ; i++){
			sqrfw+=sqr[i].value;
			
			if (i != sqr.length-1){
				sqrfw+=',';
			}
		}
	}
	
	jQuery('#sqrfw').val(sqrfw);
	saveUpdate(url,'ydlbm-ydlbmc-xjzt-sfzx-shlcid');
}


function initYdhZyList(){
	var xydm = "";
	var query = "";
	var userName = "";
	var userType = "";	
	var nj = "";
	var isFdy="";
	var isBzr="";
	if($("isFdy")){isFdy = $("isFdy").value;}
	if($("isBzr")){isBzr = $("isBzr").value;}
	if($("ydhxy")){xydm = $("ydhxy").value};	
	if($("userName")){userName = $("userName").value};
	if($("userType")){userType = $("userType").value};
	if("bzr"==userType){isFdy = "true";}
	if($("ydhnj") && $("ydhnj")!=null){nj = $("ydhnj").value;}
	GetListData.getZyList(xydm,nj,userName,isFdy,isBzr,function initTjList(data){
		var objId = "ydhzy";
		if($(objId) && $(objId).tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions(objId);			
			DWRUtil.addOptions(objId,data,"dm","mc");
			$(objId).options[0].value = "";
			if(objId + "V"){
				if($(objId +"V").value != "" && $(objId + "V").value != null){
					for(var i = 0;i < $(objId).options.length; i++){
						if($(objId).options[i].value == $(objId +"V").value){
							$(objId).options[i].selected = true;								
							return true;
						}
					}
				}					
			}
		}else{
		
		}
	});
}



function initYdhBjList(){
	var xydm ="";
	var zydm ="";
	var nj ="";
	var query = "";
	var userName = "";
	var userType = "";
	var isFdy="false";
	var isBzr="false";
	if($("userName")){userName = $("userName").value};
	if($("ydhxy")){xydm = $("ydhxy").value};
	if($("ydhzy")){zydm = $("ydhzy").value};
	if($("ydhnj")){nj = $("ydhnj").value};
	if($("isFdy")){isFdy=$("isFdy").value};
	if($("userType")&&$("userType")!=null){userType = $("userType").value};
	if("bzr"==userType){isFdy = "true";}
	if($("isBzr")){isBzr = $("isBzr").value};
	if(xydm == null || xydm == ""){
		xydm = "%";
	} else{
		xydm = "%" + xydm + "%";
	}
	if(zydm == null || zydm == ""){
		zydm = "%";
	} else{
		zydm = "%" + zydm + "%";
	}
	if(nj == null || nj == ""){
		nj = "%";
	} else{
		nj = "%" + nj + "%";
	}	
	query += xydm;
	query += "!!-!!";
	query += zydm;
	query += "!!-!!";
	query += nj;	
		GetListData.getBjList(query,userName,isFdy,isBzr,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "ydhbj";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){				   
					DWRUtil.removeAllOptions(objId);
					DWRUtil.addOptions(objId,data,"dm","mc");
					if($("ysbjdm")){//ԭʼ�༶
						DWRUtil.removeAllOptions("ysbjdm");
						DWRUtil.addOptions("ysbjdm",data,"dm","mc");
						$("ysbjdm").options[0].value = "";
					}
					if($("mkbjdm")){//ģ��༶
						DWRUtil.removeAllOptions("mkbjdm");
						DWRUtil.addOptions("mkbjdm",data,"dm","mc");						
						$("mkbjdm").options[0].value = "";
					}
					$(objId).options[0].value = "";
					
					if(objId + "V"){
					if($(objId +"V").value != "" && $(objId + "V").value != null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
                           }
						}
					}
					}
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
		});
}


function doSubmit(checkboxName,url){
	var pkValues = document.getElementsByName(checkboxName);
	var tempArr=new Array(),n=0 ;
	
	for (var i = 0 ; i < pkValues.length ; i++){
		 if (pkValues[i].checked){
		 	if (pkValues[i].alt=="disabled" || pkValues[i].alt=='wsh'){
				alert("����ѡ�ļ�¼�������ύ��δ��˵����ݣ���ȷ��");
				return false;
			} 
			tempArr[n] = pkValues[i];
			n++;
		}
	}
	
	if (tempArr.length == 0){
		alert('��ѡ����Ҫ�ύ������');
		return false;
	} else {
		batchData(checkboxName,url,'del')
	}
}