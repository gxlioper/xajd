jQuery(document).ready(function(){ 
	
	changeSqkg();
	//�����������̼����������Ƽ���
	initRskzjb();
	//���¼�
	bandeven();
	//�����������Ʋ����Ƿ���ʾ
	var rssfkz=jQuery("input:radio[name=rssfkz]:checked").val();
	initRskzcs(rssfkz);
	sfysz();
});
function saveJcsz(){
	var sqkglength = jQuery("[name=sqkg]:checked").length;
	
	if(sqkglength==0){
		showAlertDivLayer("���������뿪��!");
		return false;
	}
	
	if("14008" == jQuery("#xxdm").val()) {
		var shkglength = jQuery("[name=shkg]:checked").length;
		if(shkglength==0){
			showAlertDivLayer("��������˿���!");
			return false;
		}
	}
	
	var splc = jQuery("#splc").val();
	var sqkg = jQuery("[name=sqkg]:checked").val();
	
	if ("1"==sqkg && (splc == "" || splc == null)){
		showAlertDivLayer("��ѡ���������!");
		return false;
	}
	
//	if("1"==sqkg && (jQuery("#sqjssj").val()=="" || jQuery("#sqkssj").val()=="")){
//		showAlertDivLayer("����ʱ��ͽ���ʱ�������д!");		
//		return false;
//	}
	var rssfkz = jQuery('input:radio[name=rssfkz]:checked').val();
	if(rssfkz=='1'){
		var rskzjb = jQuery('input:radio[name=rskzjbView]:checked').val();
		if(rskzjb==null||rskzjb==''){
			showAlertDivLayer("��ѡ���������Ƽ���");
			return false;
		}
	}
	
	var url = "xszz_jcsz.do?method=saveJcsz";
	ajaxSubFormWithFun("xszzKnsJcszForm",url,function(data){
		showAlertDivLayer(data["message"]);
	});
			
}

//���¼�
function bandeven(){
	//�����Ƿ�����ֶθı䣬��ʾ�������������Ʋ���
	jQuery("input:radio[name=rssfkz]").change(function(){
		var rssfkz=jQuery(this).val();
		initRskzcs(rssfkz);
	});
}

//��ʾ�������������Ʋ���
function initRskzcs(rssfkz){
	if(rssfkz=='1'){
		jQuery('.rskzcs').css('display','');
	}else{
		jQuery('.rskzcs').css('display','none');
	}
}

//���¸�λ����
function changeSqkg(){
	var sqkg = jQuery("[name=sqkg]:checked").val();
	if("1"==sqkg){
		jQuery("table select,input:not(input:radio[name=sqkg])").attr("disabled",false);
		
	}else if("0"==sqkg){
		jQuery("table select,input:not(input:radio[name=sqkg])").attr("disabled","disabled");	
		if("14008" == jQuery("#xxdm").val()) {
			jQuery("table div select,input:radio[name=shkg]").attr("disabled",false);
			jQuery("table div select,input:text[name=shkssj]").attr("disabled",false);
			jQuery("table div select,input:text[name=shjssj]").attr("disabled",false);
		}
	}
}

/*��֤�Ƿ�����������*/
function sfysz(){
	jQuery.post('xszz_jcsz.do?method=yzSfszrs',{'rskzfw':jQuery("#rskzfw").val()},function(data){
		if(data.message=='true'){
			jQuery('#sfysz').css('color','#004400');
			jQuery('#sfysz').text('������');
		}else{
			jQuery('#sfysz').css('color','red');
			jQuery('#sfysz').text('δ����');
		}
	},'json');
}

//�����������Ƽ���
function initRskzjb(){
	var splc=jQuery("#splc").val()
	jQuery.post('xszz_jcsz.do?method=getShgw',{'splc':splc},function(data){
		var radio1 = "";
		if(data != null){
			for ( var i = 0; i < data.length; i++) {
				var o = data[i];
				radio1 += "<label><input type='radio' name='rskzjbView' value='"
						+ o.spgwdm + "'/>";
				radio1 += o.spgwmc;
				radio1 += "</label>";
				if (i != data.length - 1) {
					radio1 += "<br/>";
				}
			}
		}
		jQuery("#rskzjbTd").html(radio1);
		jQuery(
				"input:radio[name=rskzjbView][value="
						+ jQuery("#rskzjb").val() + "]").attr("checked",
				"checked");
		
	},'json');
}

//��������������
function rssz(){
	var rskzfw=jQuery("#rskzfw").val();
	var rskznj=jQuery("#rskznj").val();
	var xn=jQuery("#rdxn").val();
	var xq=jQuery("#rdxq").val();
	if(rskzfw==null||rskzfw==''){
		showAlertDivLayer('����ѡ���������Ʒ�Χ��');
		return false;
	}
	showDialog("��������������",780,550,"xszz_jcsz.do?method=knsRssz&rskzfw="+rskzfw+"&rskznj="+rskznj+"&xn="+xn+"&xq="+xq,{close:function(){sfysz();}});
}

//�޸�������Χ�ֶΣ���ʱ����
function saveKzfw(){
	var rskzfw = jQuery('#rskzfw').val();
	jQuery.post('xszz_jcsz.do?method=changeRskzfw',{'rskzfw':rskzfw},function(data){
		if(data.message=='true'){
			jQuery('#sfysz').css('color','red');
			jQuery('#sfysz').text('δ����');
		}
	},'json');
}

