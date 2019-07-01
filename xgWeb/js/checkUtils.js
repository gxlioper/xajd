//检查电话号码
function checkPhone(obj) {
	var text = obj.value;
	
	if (null !== text && ''!= text) {
		if(text.match(/^\d{0,4}\-{0,1}\d{7,8}$/)==null){
		alert("非法的电话号码�!");
		 obj.focus();
		 return false;
	 }
	}
}


//检查金额
function checkMoney(obj) {
	var text = obj.value;
	
	if (null !== text && ''!= text) {
		
		if (!/^[0-9]\d*\.[0-9]{2}$|^[1-9]\d*\.[0-9]{1}$|^[0-9]\d*$/.test(text)) {
			alert("非法的金额!");
			 obj.focus();
			 return false;
		}
	}
}

//检查长度
function checkLeng(obj,leng){
	if(obj.value.length > leng){
		alert("该输入项最大字长度为"+leng+",现已经超过，请确认！！");
		obj.focus();
	}
}