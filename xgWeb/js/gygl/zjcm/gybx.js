
//���������ܼ�
function setClzj(num){
	var clslId = "clsl"+num;
	var cldjId = "cldj"+num;
	var zjId = "zj"+num;
	
	if($(clslId) && $(cldjId) && $(zjId)){
		$(zjId).value = $(clslId).value * $(cldjId).value;
		if($(zjId).value.length > 7){
			$(zjId).value = $(zjId).value.substr(0,7)
		}
		
	}
	
	setClfy();
}

//�����ܼ�
function setClfy(){

	var clfy = 0;
	
	for(var i=0;i<max+1;i++){
		if($("zj"+i)){
			var zj = $("zj"+i).value;
			clfy = parseFloat(clfy) + parseFloat(zj);
		}
	}
	
	$("clfy").value = clfy;
}

//���汨�����
function saveBxSh(shzt){

	if(checkNrNull() && checkNrCf()){
		var url = "/xgxt/zjcmGygl.do?method=gybxSh&doType=save";
			url+= "&shzt="+shzt;
		saveUpdate(url,'sfsf-wxfy');	
	}
}

//���汨����Ϣ�޸�
function saveBxEdit(){
	var shzt = $("shzt").value;
	var url = "/xgxt/zjcmGygl.do?method=gybxSh&doType=save";
		url+= "&shzt="+shzt;
	saveUpdate(url,'sfsf-wxfy');	
}

//��������Ƿ�Ϊ��
function checkNrNull(){

	var flag = false;
	
	for(var i=0;i<max+1;i++){
		//��������
		if($("cllx"+i)){
			if($("cllx"+i).value !=""){
				flag = true;
			}else{
				alert("��ȷ�����в��ϵĲ������Ͳ�Ϊ�գ�");
				return false;
			}
		}
		
		//��������
		if($("clmc"+i)){
			if($("clmc"+i).value !=""){
				flag = true;
			}else{
				alert("��ȷ�����в��ϵĲ������Ʋ�Ϊ�գ�");
				return false;
			}
		}
		
		//��������
		if($("clsl"+i)){
			if($("clsl"+i).value !=""){
				flag = true;
			}else{
				alert("��ȷ�����в��ϵĲ���������Ϊ�գ�");
				return false;
			}
		}
		
		//���ϵ���
		if($("cldj"+i)){
			if($("cldj"+i).value !=""){
				flag = true;
			}else{
				alert("��ȷ�����в��ϵĲ��ϵ��۲�Ϊ�գ�");
				return false;
			}
		}
	}
	
	if(!flag){
		//alert("��������дһ�ݲ��ϣ�");
		//return false;
	}
	
	return true;
}

//��������Ƿ��ظ�
function checkNrCf(){

	for(var i=0;i<max+1;i++){
	
		var cllx = "";
		var clmc = "";
		
		if($("cllx"+i) && $("clmc"+i)){
		
			cllx = $("cllx"+i).value;
			clmc = $("clmc"+i).value;
			
			var bjcllx = "";
			var bjclmc = "";
		
			for(var j=i+1; j<max+1; j++){
			
				if($("cllx"+j) && $("clmc"+j)){
				
					var bjcllx = $("cllx"+j).value;
					var bjclmc = $("clmc"+j).value;
					
					if(cllx == bjcllx && clmc == bjclmc){
						alert("�����в��ϵ����ͺ�������ͬ�Ĳ��ϣ���ȷ�ϣ�");
						return false;
					}
				}
			}
		}
	}
	
	return true;
}

//���ù�Ԣ���޲���
function setGybxclInfo(){

	var tableName="view_gygl_zjcm_bxcl";
	var pk = "xh||bxsj";
	var pkValue = $("xh").value + $("bxsj").value;
	var query =" ";
	var tbId = "nr";
	var btnId = "nrAdd";
	var mklx = $("mklx").value;
	
	if(mklx == "pj"){
		$("nrCtrl").style.display = "none";
	}
	
	setNr(tableName,pk,pkValue,query,tbId,btnId,"gybxcl");
	
	setClfy();
}