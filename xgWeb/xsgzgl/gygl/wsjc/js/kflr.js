function szFs(obj){
	checkFsForSyjs(obj);
	var inputs = jQuery(obj).siblings("input");
	jQuery(inputs).remove();
	var kfdh = jQuery(obj).parents("tr:first").find("td").eq(1).text().trim();
	if(jQuery(obj).val() != null && jQuery(obj).val() != ''){					
		var value = jQuery("#jcrcid").val()+'_'+jQuery("#lddm").val()+'_'+jQuery("#qsh").val()+'_'+kfdh+'_'+jQuery(obj).val().trim();
		var delvalue = jQuery("#jcrcid").val()+'_'+jQuery("#lddm").val()+'_'+jQuery("#qsh").val()+'_'+kfdh;
		jQuery(obj).parent().append("<input type='hidden' name='kfArr' value='"+value+"' />");
		jQuery(obj).parent().append("<input type='hidden' name='delArr' value='"+delvalue+"' />");
	}else{
		var delvalue = jQuery("#jcrcid").val()+'_'+jQuery("#lddm").val()+'_'+jQuery("#qsh").val()+'_'+kfdh;
		jQuery(obj).parent().append("<input type='hidden' name='delArr' value='"+delvalue+"' />");
	}				
}

//��֤������һλС��
function checkFsForSyjs(obj){
	//checkMoneyBykeyUpByone(obj);
	checkNumb(obj)
	if(obj.value == '0' || obj.value == '0.00'){
		showAlert("��������0��", {}, {
			"clkFun" : function() {
				obj.focus();
				return false;
			}
		});
	}
}


//�����为��
function checkNumb(obj){    
	
    //�Ȱѷ����ֵĶ��滻�����������ֺ�.��-
    obj.value = obj.value.replace(/[^\-?\d.]/g,'');    
    //���뱣֤��һ��Ϊ���ֶ�����.       
    obj.value = obj.value.replace(/^\./g,''); 
    //��ֻ֤�г���һ��.��û�ж��.       
    obj.value = obj.value.replace(/\.{2,}/g,'.');       
    //��֤.ֻ����һ�Σ������ܳ�����������       
    obj.value = obj.value.replace('.','$#$').replace(/\./g,'').replace('$#$','.'); 
    //��֤-ֻ���ڵ�һλ����   
    obj.value = obj.value.replace(/^\-/g,'$#$').replace(/\-/g,'').replace('$#$','-');
    //ȥ����ͷ��0����������С���㣩
    obj.value = obj.value.replace(/^0+(?=[0-9])/, '');
    //ֻ��������λС��  
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); 
    //�滻��-.�����
    obj.value = obj.value.replace('-.','');
    //�滻��ֻ��-�����
    if(1==obj.value.length){
    	obj.value = obj.value.replace('-','');
    }
} 


function bc(){
	var kfs = jQuery("[name='kfs']");
	var num = 0;
	var fz = 0;
	var bz = "";
	//var bcfs = jQuery("#sfbc").val();
	
	jQuery(kfs).each(function(i,n){
		if(jQuery(n).val() != null && (jQuery(n).val()).trim() != "") {
			fz+=Number(jQuery(n).val());
			bz+="("+(jQuery(n).parents("tr:first").find("td").eq(1).text().trim())+")"+jQuery(n).val()+",";
			num++;
		}
	})
	
	if(num == 0){
		showAlert("����д����");
		return false;
	}
	
//	if(parseFloat(fz).toFixed(1) == 0.0){
//		showAlert("����д����");
//		return false;
//	}
	
//	if(parseFloat(fz).toFixed(1) == 0.0){
//		showAlert("����д����");
//		return false;
//	}
//	
//	if(bcfs == "true"){
//		if(num == 0){
//			showAlert("����д����");
//			return false;
//		}
//	}
	var url = "gyglnew_kslr.do?method=saveKfmx";
	
	ajaxSubFormWithFun("kflrForm", url, function(data) {
		 if(data["message"]=="����ɹ���"){
    		 showAlert('ע�⣺�۷���ϸ����ɹ����������������ٴα���󣬲���Ч!',{},{"clkFun":function(){
    			 if(frameElement.api){
	    			 var qsh = jQuery("#qsh").val();
    				 var api = frameElement.api
    				 var W = api.opener;
    				 var checkboxs = jQuery(W.document).find("[name='div_pkValue']");
    				 jQuery(checkboxs).each(function(i,n){
						if((jQuery(checkboxs[i]).val()).indexOf(qsh) != -1){
							var trN = jQuery(checkboxs[i]).attr("id").split("_")[1];
							var mrf = 0;
							if("12721"==W.jQuery("#xxdm").val()){
								mrf = 80 //������ְҵ����ѧԺ Ĭ��80��
							}
							W.jQuery("#fz_"+trN).val((parseFloat(fz)+mrf).toFixed(2));
							W.jQuery("#kfyj_"+trN).val(bz.trim().substr(0,bz.trim().length-1));
							W.jQuery("#kfyj_"+trN).attr('title',bz.substr(0,bz.trim().length-1));
//							jQuery(checkboxs[i]).parents("tr:first").find("td").eq(6).find("input").val(parseFloat(fz).toFixed(2));
//							jQuery(checkboxs[i]).parents("tr:first").find("td").eq(7).find("input").val(bz.trim().substr(0,bz.trim().length-1));
//							jQuery(checkboxs[i]).parents("tr:first").find("td").eq(7).find("input").attr('title',bz.substr(0,bz.trim().length-1));
							return false;
						}
	    			 })
	    			 closeDialog();
	    		 }else{
	    			 var checkboxs = jQuery(parent.window.document).find("[name='div_pkValue']");
	    			 jQuery(checkboxs).each(function(i,n){
							if((jQuery(checkboxs[i]).val()).indexOf(qsh) != -1){
								var trN = jQuery(checkboxs[i]).attr("id").split("_")[1];
								parent.window.jQuery("#fz_"+trN).val(parseFloat(fz).toFixed(2));
								parent.window.jQuery("#kfyj_"+trN).val(bz.substr(0,bz.length-1));
								parent.window.jQuery("#kfyj_"+trN).val('title',bz.substr(0,bz.length-1));
								
//								jQuery(checkboxs[i]).parents("tr:first").find("td").eq(6).find("input").val(parseFloat(fz).toFixed(2));
//								jQuery(checkboxs[i]).parents("tr:first").find("td").eq(7).find("input").val(bz.substr(0,bz.length-1));
//								jQuery(checkboxs[i]).parents("tr:first").find("td").eq(7).find("input").val('title',bz.substr(0,bz.length-1));
								return false;
							}
		    		})
	    			iFClose();
		    	 }    		 
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
	});
}