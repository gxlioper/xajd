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
	if(!check("daqdcl_mc")){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	if(resumeFlag()=="1"){
		showAlert("�õ����嵥���������Ѵ��ڣ�");
		return false;
	}
	// �õ�JSON����
	var url = "daqdcl.do?method=updateDaqdcl&type=save";

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
	jQuery.post("daqdcl.do?method=resumeFlag",{daqdcl_mc:jQuery("#daqdcl_mc").val(),daqdcl_id:jQuery("#daqdcl_id").val()},function(data){
		flag = data;
	},'json');
	jQuery.ajaxSetup({async:true});
	return flag;
	
}