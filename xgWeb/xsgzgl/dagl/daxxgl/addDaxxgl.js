function  saveForm(){
		if(!validate()){
			return false;
		}
		// �õ�JSON����
		var url = "daxxgl.do?method=addDaxxgl&type=save";

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


function validate(){
	if(!check("xh-dazrsj-zrfsbh")){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}	
	return true;
}
