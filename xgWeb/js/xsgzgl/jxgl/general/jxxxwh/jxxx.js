function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}

function searchRs(){
	var url = "jxgl_jxxxwh_ajax.do?method=jxxxCx";
	var ie = "10.0";
	var otherValue = [ie];
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","1000")
}

//����
function jxxxZj(){
	jQuery("#jxid").val('');
	jQuery("#jxmc").val('');
	jQuery("#kssj").val('');
	jQuery("#jssj").val('');
	//��òμ��꼶
	getCjnj();
	tipsWindown("���Ӿ�ѵ��Ϣ","id:tempDiv","320","300","true","","true","id");
}

function drCxmd(){
	toImportDataNew("IMPORT_N450102_DRCXMD");
	return false;
}
//��ѵ��Ϣ����(ǰ̨��֤)
function jxxxDivSave(){
	
	if($("jxmc") && $("jxmc").value==""){
 		alertInfo("��ѵ���Ʋ���Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	
	var flag = checkJxxx();
	if(!flag){
		alertInfo("��ѵ���Ʋ����ظ�!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	if(jQuery("#kssj").val()==null || jQuery("#kssj").val()==""){
 		alertInfo("��ʼʱ�䲻��Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	if(jQuery("#jssj").val()==null || jQuery("#jssj").val()==""){
 		alertInfo("����ʱ�䲻��Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	
	if($("kssj").value!="" && $("jssj").value!="" && $("kssj").value>$("jssj").value){
 		alertInfo("��ʼʱ�䲻�ܴ��ڽ���ʱ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	
	if($("cjnj") && $("cjnj").value==""){
 		alertInfo("�μ��꼶����Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}

	jxxxSave();
}


//��ѵ��Ϣ��̨��֤
function checkJxxx(){
	var flag = false;
	jQuery.ajaxSetup({async:false});	
	// �õ�JSON����
    var parameter ={};	
    parameter["jxid"]=escape(jQuery("#jxid").val());
    parameter["jxmc"]=escape(jQuery("#jxmc").val());
    var url = "jxgl_jxxxwh_ajax.do?method=checkJxxx";
	jQuery.post(url,parameter,
		function(result){
			if("true"==result){
				flag = true;
			}
		});
	jQuery.ajaxSetup({async:true});
	return flag;
}



//��ѵ��Ϣ����
function jxxxSave(){
	jQuery.ajaxSetup({async:false});	
	// �õ�JSON����
    var parameter ={};	
    parameter["jxid"]=escape(jQuery("#jxid").val());
    parameter["jxmc"]=escape(jQuery("#jxmc").val());
	parameter["kssj"]=escape(jQuery("#kssj").val());
	parameter["jssj"]=escape(jQuery("#jssj").val());
	parameter["cjnj"]=escape(jQuery("#cjnj").val());
	parameter["xqrs"]=escape(jQuery("#xqrs").val());
	parameter["jxzt"]=escape(jQuery("input[name=jxzt]:checked").val());
	var url = "jxgl_jxxxwh_ajax.do?method=jxxxSave";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.post(url,parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			if("����ɹ�"==result && $("jxid") && $("jxid").value=="" && "start"==jQuery("input[name=jxzt]:checked").val()){
				closeWindown();
				searchRs();
				confirmInfo(jQuery("#jxmc").val()+"�ѳɹ��������Ƿ�������ɾ�ѵ������",function(tag){
					if(tag=="ok"){
						var array = jQuery("[name=div_pkValue]");
						var pkValue = jQuery(array[0]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
						refreshForm('jxgl_jxxxwh.do?method=jxmdCx&pkValue='+pkValue+'&cxqk=cx');
					}
				});
			}else{
				alertInfo(result);
				closeWindown();
				searchRs();
			}
		}
	);
	jQuery.ajaxSetup({async:true});
}


//�޸ģ����У�ֹͣ����ѵ����ά��
function jxxxModi(type){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len==1){
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		var jxzt = jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(7).text();
		if("update"==type){
			if("ֹͣ"==jxzt){
				alertInfo("�����޸�ֹͣ�ľ�ѵ��Ϣ!!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			/*
			getCjnj();
			jQuery.ajaxSetup({async:false});
			jQuery.ajaxSetup({cache:false});
			var url="jxgl_jxxxwh_ajax.do?method=jxxxXg";		
			url+="&pkValue="+pkValue;
			jQuery.getJSON(url,parameter,function(data){
				if(data != null){
					jQuery("#jxid").val(data.jxid);
					jQuery("#jxmc").val(data.jxmc);
					jQuery("#kssj").val(data.kssj);
					jQuery("#jssj").val(data.jssj);
					jQuery("#cjnj").val(data.cjnj);
					jQuery("[name=jxzt][type=radio][value="+data.jxzt+"]").click();
				}
			});
			jQuery.ajaxSetup({async:true});
			tipsWindown("�޸ľ�ѵ��Ϣ","id:tempDiv","320","300","true","","true","id");*/
			var url="jxgl_jxxxwh.do?method=jxxxXg&pkValue="+pkValue;
			showDialog("�޸ľ�ѵ��Ϣ", 400, 340,url);
		}else if("start"==type || "stop"==type){
			if("start"==type && "����"==jxzt){
				alertInfo("�������������еľ�ѵ��Ϣ!!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			if("stop"==type && "ֹͣ"==jxzt){
				alertInfo("����ֹͣ��ֹͣ�ľ�ѵ��Ϣ!!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			var parameter={}
			var url="jxgl_jxxxwh_ajax.do?method=jxxxCz";	
			parameter["jxid"]=pkValue;
			parameter["jxzt"]=type;
			jQuery.ajaxSetup({async:false});	
			jQuery.post(url,
				parameter,
				function(result){
						alertInfo(result,function(tag){
							if(tag=="ok"){
								searchRs();
							}
						});
				}
			);
			jQuery.ajaxSetup({async:true});
		}else if("cxmd"==type){
			refreshForm('jxgl_jxxxwh.do?method=jxmdCx&pkValue='+pkValue+'&cxqk=cx');
		}
	}else{
		alertInfo("�빴ѡһ����¼���в�����");
		return false;
	}
}



//ɾ��
function jxxxSc(){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len>=1){
		var array = jQuery("[name=div_pkValue]:checked");
		var str = "";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
			var jxzt = jQuery(array[i]).parent().parent().find("td").eq(7).text();
			if("����"==jxzt){
		 		alertInfo("����ɾ�������еľ�ѵ��Ϣ!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			str += pkValue+"!!@@!!";
		}
		
		var flag = checkScJxxx();
		if(!flag){
			alertInfo("��ѵ��Ϣ��¼���ھ�ѵ������ʹ�ã�����ɾ��!",function(tag){
	 			if(tag=="ok"){
	 				return false;
	 			}
	 		});
	 		return false;
		}
		
		confirmInfo("�Ƿ�ȷ��ɾ����ѡ�ľ�ѵ��Ϣ�����Ӧ������",function(tag){
			if(tag=="ok"){
				var array = jQuery("[name=div_pkValue]:checked");
				var str = "";
				for (var i=0;i<array.length;i++) {
					var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
					str += pkValue+"!!@@!!";
				}
				var parameter={}
				var url="jxgl_jxxxwh_ajax.do?method=jxxxSc";	
				parameter["pkValue"]=str;							
				jQuery.ajaxSetup({async:false});	
				jQuery.post(url,
					parameter,
					function(result){
							alertInfo(result,function(tag){
								if(tag=="ok"){
									searchRs();
								}
							});
					}
				);
				jQuery.ajaxSetup({async:true});
			}
		});
	}else{
		alertInfo("�빴ѡ��Ҫɾ�������ݣ�",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
	}
}


//��֤ɾ����Ϣ
function checkScJxxx(){
	var flag = false;
	jQuery.ajaxSetup({async:false});
	var array = jQuery("[name=div_pkValue]:checked");
	var str = "";
	for (var i=0;i<array.length;i++) {
		var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
		str += pkValue+"!!@@!!";
	}
	var parameter={}
	var url="jxgl_jxxxwh_ajax.do?method=checkScJxxx";	
	parameter["pkValue"]=str;	
	jQuery.post(url,parameter,
		function(result){
			if("true"==result){
				flag = true;
			}
		});
	jQuery.ajaxSetup({async:true});
	return flag;
}

//��òμ��꼶
function getCjnj(){
	var parameter={};
	jQuery.ajaxSetup({async:false});
	url = "jxgl_jxxxwh_ajax.do?method=getCjnj";
	jQuery.getJSON(url,parameter,function(data){
		jQuery('#cjnj').empty();
		if(data != null && data.length != 0){
			for(var i=0; i<data.length; i++){
				var option = "<option value=\"" + data[i].cjnj + "\">" + data[i].cjnj + "</option>";
				jQuery('#cjnj').append(option);
			}
		}
	});
	jQuery.ajaxSetup({async:true});
}

/**
 * ����ģ̬���ڣ��滻ԭ����showTopWin
 * 
 * @param width
 * @param height
 * @param url
 * @param t
 * @param ��������
 */
function showDialog(t,width,height,url,other){
	var title = t||"ѧ����������ϵͳ";
	var setting = {
		title:title,
		width:width,
		height:height,
		lock:true,
		fixed:true,
		focus:true,
		min:false,
		zIndex: 1976,
		content:'url:'+url
		
	};
	var params = jQuery.extend(setting, other || {});
	if(frameElement&& frameElement.api){
		var api = frameElement.api;
		var W = api.opener;
		params["id"] = "childDialog";
		params["parent"] = api;
		params["zIndex"] = 1977;
		return W.lhgdialog(params);
	}else{
		params["id"] = "parentDialog";
		params["parent"] = window;
		return lhgdialog(params);
	}
	
	
}
/**
 * �ر�ģ̬����
 */
function closeDialog(){
	var api = frameElement.api; 
	api.close();
}