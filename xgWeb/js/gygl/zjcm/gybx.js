
//单个材料总计
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

//材料总计
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

//保存报修审核
function saveBxSh(shzt){

	if(checkNrNull() && checkNrCf()){
		var url = "/xgxt/zjcmGygl.do?method=gybxSh&doType=save";
			url+= "&shzt="+shzt;
		saveUpdate(url,'sfsf-wxfy');	
	}
}

//保存报修信息修改
function saveBxEdit(){
	var shzt = $("shzt").value;
	var url = "/xgxt/zjcmGygl.do?method=gybxSh&doType=save";
		url+= "&shzt="+shzt;
	saveUpdate(url,'sfsf-wxfy');	
}

//检测内容是否为空
function checkNrNull(){

	var flag = false;
	
	for(var i=0;i<max+1;i++){
		//材料类型
		if($("cllx"+i)){
			if($("cllx"+i).value !=""){
				flag = true;
			}else{
				alert("请确认所有材料的材料类型不为空！");
				return false;
			}
		}
		
		//材料名称
		if($("clmc"+i)){
			if($("clmc"+i).value !=""){
				flag = true;
			}else{
				alert("请确认所有材料的材料名称不为空！");
				return false;
			}
		}
		
		//材料数量
		if($("clsl"+i)){
			if($("clsl"+i).value !=""){
				flag = true;
			}else{
				alert("请确认所有材料的材料数量不为空！");
				return false;
			}
		}
		
		//材料单价
		if($("cldj"+i)){
			if($("cldj"+i).value !=""){
				flag = true;
			}else{
				alert("请确认所有材料的材料单价不为空！");
				return false;
			}
		}
	}
	
	if(!flag){
		//alert("请至少填写一份材料！");
		//return false;
	}
	
	return true;
}

//检测内容是否重复
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
						alert("不能有材料的类型和名称相同的材料，请确认！");
						return false;
					}
				}
			}
		}
	}
	
	return true;
}

//设置公寓报修材料
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