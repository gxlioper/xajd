/**
 * ������������
 * @return
 */
function checkzs(){
	if(jQuery("#sqly").val().length > 500){
		showAlertDivLayer("���������������Ϊ500�����Ѿ���������ȷ�ϣ�");
		return false;
	}
}

/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery.trim(jQuery("#"+id[i]).val()) || jQuery.trim(jQuery("#"+id[i]).text());
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

//���У�鲻����0��ͷ
function ismoney(obj){
  if(obj.value.indexOf('0') == 0 && obj.value.length>=2){
		 showAlert("������0��ͷ��",{},{"clkFun":function(){
				jQuery(obj).val("");
				jQuery(obj).focus();
			}});
  }
}

