function saveForm(){
	//����֤ѧ��
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	
	var czjmylbxje = jQuery("#czjmylbxje").val();
	var sybxje = jQuery("#sybxje").val();
	var czjmylbxcbqsrq = jQuery("#czjmylbxcbqsrq").val();
	//var czjmylbxcbjsrq = jQuery("#czjmylbxcbjsrq").val();
	var sybxcbqsrq = jQuery("#sybxcbqsrq").val();
	//var sybxcbjsrq = jQuery("#sybxcbjsrq").val();
	var sfzqfjg = jQuery("#sfzqfjg").val();
	var sfzyxqxqsrq = jQuery("#sfzyxqxqsrq").val();
	var sfzyxqxjzrq = jQuery("#sfzyxqxjzrq").val();

	var jhrxm = jQuery("#jhrxm").val();
	var jhrsfzh = jQuery("#jhrsfzh").val();
	var txdz = jQuery("#txdz").val();

	
	if (jQuery.trim(xh) == ""){
		showAlert("����ѡ��ѧ����");
		return false;
	}
	if (jQuery.trim(xn) == ""){
		showAlert("����ѡ��ѧ�꣡");
		return false;
	}
	
	if (jQuery.trim(czjmylbxje) == ""){
		showAlert("������������ҽ�Ʊ��ս�");
		return false;
	}
	if (jQuery.trim(czjmylbxcbqsrq) == ""){
		showAlert("��ѡ��������ҽ�Ʊ��ղα���ʼ���ڣ�");
		return false;
	}
	//if (jQuery.trim(czjmylbxcbjsrq) == ""){
	//	showAlert("��ѡ��������ҽ�Ʊ��ղα��������ڣ�");
	//	return false;
	//}
	if (jQuery.trim(sybxje) == ""){
		showAlert("��������ҵ���ս�");
		return false;
	}
	if (jQuery.trim(sybxcbqsrq) == ""){
		showAlert("��ѡ����ҵ���ղα���ʼ���ڣ�");
		return false;
	}
	//if (jQuery.trim(sybxcbjsrq) == ""){
	//	showAlert("��ѡ����ҵ���ղα��������ڣ�");
	//	return false;
	//}
	if (jQuery.trim(sfzqfjg) == ""){
		showAlert("���������֤ǩ�����أ�");
		return false;
	}
	if (jQuery.trim(sfzyxqxqsrq) == ""){
		showAlert("��ѡ�����֤��Ч������ʼ���ڣ�");
		return false;
	}
	if (jQuery.trim(sfzyxqxjzrq) == ""){
		showAlert("��ѡ�����֤��Ч���޽�ֹ���ڣ�");
		return false;
	}
	//if (parseFloat(bxje) > parseFloat(99999)){
	//	showAlert("���ս��ܳ���99999Ԫ��");
	//	return false;
	//}
	
	if (jQuery.trim(txdz) == ""){
		showAlert("������ͨѶ��ַ��");
		return false;
	}
	
     var url = "rcsw_sybx.do?method=addSybx&type=save";
      ajaxSubFormWithFun("sybxForm",url,function(data){
    	  
    	  if (data["success"] == "false"){
    		  showAlert("��ѧ����"+jQuery.trim(xn)+"ѧ�꣬������ҵ������Ϣ���ڡ�");
    	  } else {
    		  showAlert(data["message"],{},{"clkFun":function(){
        			if (parent.window){
     				 refershParent();
        			}
      		  }});
    	  }
    	  
      });
  }

/**
 * ���㱣���ܽ��
 */
function changeBxje() {
	var czjmylbxje = jQuery("#czjmylbxje").val();
	var sybxje = jQuery("#sybxje").val();
	var czjmylbxjeV1 = parseFloat(czjmylbxje == "" ? 0 : czjmylbxje);
	var sybxjeV1 = parseFloat(sybxje == "" ? 0 : sybxje);
	var czjmylbxjeV = czjmylbxjeV1*10000;
	var sybxjeV =sybxjeV1*10000;
	var bxje = (czjmylbxjeV + sybxjeV)/10000; 
	jQuery("#bxje").val(bxje);
	jQuery("#bxjeTd").html(bxje);
}