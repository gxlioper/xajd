//��������
function changeKg(div,obj){
	var val=jQuery(obj).val();
	jQuery("#rzsqkg").val(val);
	if(val=="1"){
		jQuery("#"+div).show();
	}else{
		jQuery("#"+div).hide();
	}
}
//�����������
//���Ż��˴���
function save_cssz(){
	var fdyrz = yz_fdyrzsq();
	var fdypx = yz_fdypx();
	var zwsq = yz_zwsq();
	//���渨��Ա��ְ����
	if(fdyrz && fdypx && zwsq){
		var param=getRzParam();
		jQuery.post("szdw_cssz.do?method=cssz&type=save", param, function(result){
			
			 if(result["success"]!="true"){
				 alertInfo(result["message"]);
			 }else{
				//���渨��Ա��ѵ����
				 var param2=getPxParam();
				 jQuery.post("szdw_cssz.do?method=cssz&type=save", param2, function(result2){
					 
					 if(result2["success"]!="true"){
						 alertInfo(result2["message"]);
					 }else{
						//���渨��Աְ�����
						 var param_zw=getZwParam();
						jQuery.post("szdw_cssz.do?method=cssz&type=save", param_zw, function(result_zw){
							 alertInfo(result_zw["message"]);
						},"json");
					 }
					 
				},"json");
			 }
			 
		},"json");
	}
}

//���渨��Ա��������
function saveFdycssz(){
	var fdyrz = yz_fdyrzsq();
	//���渨��Ա��ְ����
	if(fdyrz){
		var param=getRzParam();
		jQuery.post("szdw_cssz.do?method=fdycssz&type=save", param, function(result){
			 alertInfo(result["message"]);
		},"json");
	}
}

//������ʱ���������
function saveBbsjCssz(){
	var bbsjkg =jQuery("#bbsjkg").val();
	var bbsjkssj =jQuery("#bbsjkssj").val();
	var bbsjjssj = jQuery("#bbsjjssj").val();
	if(bbsjkg==null || bbsjkg==""){
		alertInfo("��ѡ����ʱ�俪��");	
		return false;	
	}
	var param ={key:"szdw_bbsj",kg:bbsjkg,kssj:bbsjkssj,jssj:bbsjjssj};
	
	jQuery.post("szdw_cssz.do?method=bbsjCssz&type=save", param, function(result){
		alertInfo(result["message"]);
	},"json");
}

//�����ɲ���������
function saveBgbcssz(){
	var zwsq = yz_zwsq();
	if(zwsq){
		//����ְ�����
		 var param_zw=getZwParam();
		jQuery.post("szdw_cssz.do?method=bgbcssz&type=save", param_zw, function(result_zw){
			 alertInfo(result_zw["message"]);
		},"json");
	}
}
function getRzParam(){
	var rzsqkg =jQuery("#rzsqkg").val();
	var rzsqkssj =jQuery("#rzsqkssj").val();
	var rzsqjssj = jQuery("#rzsqjssj").val();
	var rzsqSplc = jQuery("#rzsqSplc").val();
	var param ={key:"szdw_fdyrzsq",kg:rzsqkg,kssj:rzsqkssj,jssj:rzsqjssj,splc:rzsqSplc};
	return param;
}
function getPxParam(){
	var fdypxSplc = jQuery("#fdypxSplc").val();
	var param ={key:"szdw_fdypxsq",splc:fdypxSplc};
	return param;
}
function getZwParam(){
	var zwsqSplc = jQuery("#zwsqSplc").val();
	var param ={key:"szdw_xsgbzwsq",splc:zwsqSplc};
	return param;
}
//��֤����Ա��ְ����
function yz_fdyrzsq(){
	var rzsqkg =jQuery("#rzsqkg").val();
	var rzsqkssj =jQuery("#rzsqkssj").val();
	var rzsqjssj = jQuery("#rzsqjssj").val();
	var rzsqSplc = jQuery("#rzsqSplc").val();
	if(rzsqkg==null || rzsqkg==""){
		alertInfo("��ѡ�񸨵�Ա��ְ����");	
		return false;	
	}else if(rzsqkg=="1"){
		
		//2014-7-3 ȡ���������õ���ʱ�������
//		if(rzsqkssj==null || rzsqkssj==""){
//			alertInfo("����Ա��ְ���뿪ʼʱ�䲻��Ϊ��");	
//			return false;	
//		}else if(rzsqjssj==null || rzsqjssj==""){
//			alertInfo("����Ա��ְ�������ʱ�䲻��Ϊ��");		
//			return false;	
//		}else 
		if(!(rzsqjssj==null || rzsqjssj=="")&&rzsqjssj < rzsqkssj){
			alertInfo("����Ա��ְ���뿪ʼʱ�䲻��С�ڽ���ʱ��");		
			return false;	
		}		
	}if(rzsqSplc==null || rzsqSplc==""){
		alertInfo("��ѡ�񸨵�Ա��ְ��������");	
		return false;	
	}
	return true;
}
function yz_fdypx(){
	var fdypxSplc = jQuery("#fdypxSplc").val();
	if(fdypxSplc==null || fdypxSplc==""){
		alertInfo("��ѡ�񸨵�Ա��ѵ��������");	
		return false;	
	}
	return true;
}
function yz_zwsq(){
	var zwsqSplc = jQuery("#zwsqSplc").val();
	if(zwsqSplc==null || zwsqSplc==""){
		alertInfo("��ѡ��ѧ���ɲ�ְ��������������");	
		return false;	
	}
	return true;
}


//���渨��Ա��ѵ��������
function saveFdypxCssz(){
	var fdypx = yz_fdypx();
	//���渨��Ա��ְ����
	if(fdypx){
		var param =getPxParam();
		jQuery.post("szdw_cssz.do?method=fdypxCssz&type=save", param, function(result){
			 alertInfo(result["message"]);
		},"json");
	}
}