//���绰����
function checkPhone(obj) {
	var text = obj.value;
	
	if (null !== text && ''!= text) {
		if(text.match(/^\d{0,4}\-{0,1}\d{7,8}$/)==null){
		alert("�Ƿ��ĵ绰����1�7!");
		 obj.focus();
		 return false;
	 }
	}
}


//�����
function checkMoney(obj) {
	var text = obj.value;
	
	if (null !== text && ''!= text) {
		
		if (!/^[0-9]\d*\.[0-9]{2}$|^[1-9]\d*\.[0-9]{1}$|^[0-9]\d*$/.test(text)) {
			alert("�Ƿ��Ľ��!");
			 obj.focus();
			 return false;
		}
	}
}

//��鳤��
function checkLeng(obj,leng){
	if(obj.value.length > leng){
		alert("������������ֳ���Ϊ"+leng+",���Ѿ���������ȷ�ϣ���");
		obj.focus();
	}
}