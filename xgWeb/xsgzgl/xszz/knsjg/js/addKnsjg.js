function saveForm(){
	//����֤ѧ��
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var rddc = jQuery("#rddc").val();

	
	if (jQuery.trim(xh) == ""){
		showAlert("����ѡ��ѧ����");
		return false;
	}
	if (jQuery.trim(xn) == ""){
		showAlert("����ѡ��ѧ�꣡");
		return false;
	}
	if (jQuery.trim(xq) == ""){
		showAlert("����ѡ��ѧ�ڣ�");
		return false;
	}
	
	if (jQuery.trim(rddc) == ""){
		showAlert("����ѡ���϶����Σ�");
		return false;
	}
	//�������ո��Ի��ֶ��п�
	if(jQuery("#xxdm").val() == "13871"){
		if(jQuery.trim(jQuery("#knpx").val()) == ""){
			showAlert("����������");
			return false;
		}
	}
	if("10742"==jQuery("#xxdm").val()) {		
		var sqlydmIds = "";
		var checkSqlydm = "";
		jQuery("input[name=sqlydm]:checked").each(function(){
			sqlydmIds += jQuery(this).val()+ "," ;
		});
		if(sqlydmIds.length>0){
			sqlydmIds = sqlydmIds.substring(0, sqlydmIds.length-1);
			checkSqlydm ="1";
		}
		if (jQuery.trim(checkSqlydm) == ""){
			showAlert("�뽫��������д������");
			return false;
		}
	}
	
	if(jQuery("#xxdm").val()=='12861' || '10335'==jQuery("#xxdm").val()){
		if(jQuery(".MultiFile-label").length<=0){
			showAlertDivLayer("���ϴ�������");
			return false;
		}
	}
	
	//����ʦ����ѧ���Ի�
	if(jQuery("#xxdm").val() == '10346'){
		var ylzd4 = jQuery("#ylzd4").val();
		var ylzd5 = jQuery("#ylzd5").val();
		if(ylzd4 == null || ylzd4 == '' || ylzd5 == null || ylzd5 == ''){
			showAlert("��Ѵ�*������д������");
			return false;
		}
	}
	/**
	if("10335"==jQuery("#xxdm").val()&&(jQuery("#ylzd3").val() == "")){
		showAlertDivLayer("��ѡ���޳�������");
		return false;
	}*/
	
	if("10742"==jQuery("#xxdm").val()) {
		var url = "xszz_knsjg.do?method=addKnsjg&type=save&sqlydm="+sqlydmIds;
	}else if("10277"==jQuery("#xxdm").val()){
		var values=pjyydm();
		var url = "xszz_knsjg.do?method=addKnsjg&type=save&ylzd5="+values;
	}else{
		var url = "xszz_knsjg.do?method=addKnsjg&type=save";
	}
      ajaxSubFormWithFun("knsjgForm",url,function(data){
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

//ƴ��ԭ�����
function pjyydm(){
	var values=[];
	jQuery("input[name=ylzd5]:checked").each(function(){
		values.push(jQuery(this).val());
	});
	return values.join(",");
}


