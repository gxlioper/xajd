jQuery(document).ready(function(){ 
	
	changeSqkg();
	changeBjpykg();
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
	
	// ����ѧԺ  ���ж�
	if("11354" != jQuery("#xxdm").val() && sqkglength==0){
		showAlertDivLayer("���������뿪��!");
		return false;
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
	
	var bjpykglength = jQuery("[name=bjpykg]:checked").length;
	
	if(bjpykglength==0){
		showAlertDivLayer("�����ð༶���鿪��!");
		return false;
	}
	var bjpykg = jQuery("[name=bjpykg]:checked").val();
	var xzrsxx = jQuery.trim(jQuery("#xzrsxx").val());
	if("1"==bjpykg && xzrsxx == ''){
		showAlertDivLayer("С���������޲���Ϊ��!");
		return false;
	}
	var url = "xszz_knsjcszbjpy.do?method=saveJcsz";
	ajaxSubFormWithFun("jcszForm",url,function(data){
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
		jQuery("select", jQuery("#sq_table")).attr("disabled",false);
		jQuery("input:not(input:radio[name=sqkg])", jQuery("#sq_table")).attr("disabled",false);
		
	}else if("0"==sqkg){
		jQuery("select", jQuery("#sq_table")).attr("disabled","disabled");
		jQuery("input:not(input:radio[name=sqkg])", jQuery("#sq_table")).attr("disabled","disabled");
		
	}
}

function changeBjpykg(){
	var bjpykg = jQuery("[name=bjpykg]:checked").val();
	if("1"==bjpykg){
		jQuery("input:not(input:radio[name=bjpykg])", jQuery("#bjpy_table")).attr("disabled",false);
		
	}else if("0"==bjpykg){
		jQuery("input:not(input:radio[name=bjpykg])", jQuery("#bjpy_table")).attr("disabled","disabled");
		
	}
}

/*��֤�Ƿ�����������*/
function sfysz(){
	jQuery.post('xszz_knsjcszbjpy.do?method=yzSfszrs',{'rskzfw':jQuery("#rskzfw").val()},function(data){
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
	jQuery.post('xszz_knsjcszbjpy.do?method=getShgw',{'splc':splc},function(data){
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
	var xn=jQuery("#xn").val();
	var xq=jQuery("#xq").val();
	if(rskzfw==null||rskzfw==''){
		showAlertDivLayer('����ѡ���������Ʒ�Χ��');
		return false;
	}
	showDialog("��������������",780,550,"xszz_knsjcszbjpy.do?method=knsRssz&rskzfw="+rskzfw+"&rskznj="+rskznj+"&xn="+xn+"&xq="+xq,{close:function(){sfysz();}});
}

//�޸�������Χ�ֶΣ���ʱ����
function saveKzfw(){
	var rskzfw = jQuery('#rskzfw').val();
	jQuery.post('xszz_knsjcszbjpy.do?method=changeRskzfw',{'rskzfw':rskzfw},function(data){
		if(data.message=='true'){
			jQuery('#sfysz').css('color','red');
			jQuery('#sfysz').text('δ����');
		}
	},'json');
}

