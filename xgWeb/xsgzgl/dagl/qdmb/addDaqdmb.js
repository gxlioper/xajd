/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

function  saveForm(){

	if(!check("daqdmb_mc")){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}

	if(resumeFlag()=="1"){
		showAlert("�õ����嵥ģ�������Ѵ��ڣ�");
		return false;
	}
	if(jQuery("input[type=radio][name=qyztName][value=1]").prop("checked")==true){
		jQuery("input[type=radio][name=qyztName][value=0]").removeAttr('checked');
		jQuery("#qyzt").val('1');
	}else if(jQuery("input[type=radio][name=qyztName][value=0]").prop("checked")){
		jQuery("input[type=radio][name=qyztName][value=1]").removeAttr('checked');
		jQuery("#qyzt").val('0');
	}
	var daqdclIds = '';
	jQuery("ul[id=selectUl]").find("input[name=selectCol]").each(function(i){
		daqdclIds +=jQuery(this).val()+',';
	});
	if(daqdclIds==""){
		showAlert("��ѡ��󶨲��ϣ�");
		return false;
	}
	daqdclIds = daqdclIds.substring(0, daqdclIds.length-1);
	// �õ�JSON����
	var parameter ={daqdmb_mc:jQuery("#daqdmb_mc").val(),qyzt:jQuery("#qyzt").val()};
	var url = "daqdmb.do?method=addDaqdmb&type=save&daqdclIds="+daqdclIds;

    ajaxSubFormWithFun("form",url,function(data){
  	 if(data["message"]=="����ɹ���"){
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

function resumeFlag(){
	var flag="";
	jQuery.ajaxSetup({async:false});
	jQuery.post("daqdmb.do?method=resumeFlag",{daqdmb_mc:jQuery("#daqdmb_mc").val()},function(data){
		flag = data;
	},'json');
	jQuery.ajaxSetup({async:true});
	return flag;
}


jQuery(function() {
	jQuery("#unselectUl, #selectUl").dragsort({
		dragSelector : "label",
		dragBetween : true,
		dragEnd : saveOrder,
		placeHolderTemplate : "<li><label class='college_li college_checkbox' style='border:#155FBE 1px dotted;background:#CBE4F8;height:20px;line-height:20px!important;*height:28px;padding:3px 0px;text-indent: 15px;'></label></li>"
	});
});

//�϶���
function saveOrder() {
	isModify = true;
	jQuery("#selectUl").find(":input").attr("name","selectCol");
	
	var unspan = jQuery("#unselectUl").find(".choose_yx");
	unspan.parent().append("<span class='choose_wx' onclick='select(this)'></span>");
	unspan.remove();
	
	var span = jQuery("#selectUl").find(".choose_wx");
	span.parent().append("<span class='choose_yx' onclick='unselect(this)'></span>");
	span.remove();
};

function select(obj){
	var li = jQuery(obj).parent();
	jQuery(obj).parent().appendTo(jQuery("#selectUl"));
	jQuery(obj).remove();
	li.append("<span class='choose_yx' onclick='unselect(this)'></span>");
	saveOrder();
}


function unselect(obj){
	var li = jQuery(obj).parent();
	jQuery(obj).parent().appendTo(jQuery("#unselectUl"));
	jQuery(obj).remove();
	li.append("<span class='choose_wx' onclick='select(this)'></span>");
	saveOrder();
}
