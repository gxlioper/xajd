
//打开新窗口
function showInfo(url,doType,w,h){
	if(doType == "update"){
		if(curr_row == null){
			alert('请选择要修改的数据！');
			return false;
		}
	}else if(doType == "sh"){
		if(curr_row == null){
			alert('请选择审核的数据！');
			return false;
		}
	}else if(doType == "sb"){
		if(curr_row == null){
			alert('请选择要申报的数据！');
			return false;
		}
	}else if(doType == "view"){
		if(curr_row == null){
			alert('请选择要查看的数据！');
			return false;
		}
	}
	var pk = curr_row.getElementsByTagName('input')[0].value;
	url+="&doType="+doType;
	url+="&pk="+pk;
	showTopWin(url,w,h);
}

//打开新页面
function openInfo(url){
	if(curr_row == null){
		alert('请选择要操作的数据！');
		return false;
	}
	var pk = curr_row.getElementsByTagName('input')[0].value;
	url+="&pk="+pk;
	window.open(url);
}

//批量提交
function sumitInfo(url,doType){
	var len=jQuery("input:checkbox[name=primarykey_checkVal]:checked").length;
	if(len > 0){
		//if(doType=="del"){
			url+="&doType="+doType;
			if (confirm("确定要操作所勾选的数据?")) {
				showTips('处理数据中，请等待......');
				refreshForm(url);
			}
		//}else{
			
		//}
	}else{
		alert("请勾选要处理的数据");
		return false;
	}
}

function sendXx(){
	var tableName=$("tableName").value;
	var lx="";if($("lx")){lx=$("lx").value};
	var zd="";if($("zd")){zd=$("zd").value};
	var va="";if($("va")){va=$("va").value};
	var url = "/xgxt/czxxDtjsDyxx.do?method=xsxxManage";
	url+="&tableName="+tableName;
	if(lx !=""){
		url+="&lx="+lx;
	}
	if(zd !=""){
		url+="&zd="+zd;
	}
	if(va !=""){
		url+="&va="+va;
	}
	showTopWin(url,800,600);
}

function checkNull(obj){
	if(obj.value == ""){
		obj.value = "0";
	}
}

function addBjP(){

	dwr.engine.setAsync(false);

	var fromIndx = $("bj").selectedIndex;
	var toIndx = $("bjP").options.length;
	if(fromIndx < 0){
		return false;
	}

	if($("bj").options[fromIndx].value == "" || $("bj").options[fromIndx].value == null || $("bj").options[fromIndx].value == "null"){
		return false;
	}
	
	for(var i=0;i<toIndx;i++){
		if($("bj").options[fromIndx].value == $("bjP").options[i].value){
			return false;
		}
	}
	$("bjP").options[$("bjP").options.length] = new Option($("bj").options[fromIndx].text,$("bj").options[fromIndx].value);
	$("bj").options[fromIndx] = null;
	if($("bj").options.length > 0){
		$("bj").options[0].selected = true;
		//$("delBj").disabled = false;
	}else{		
		//$("addBj").disabled = true;
	}
	if($("bjP").options.length > 0){
		$("bjP").options[0].selected = true;
	}
	
	dwr.engine.setAsync(true);
}

function delBjP(){

	dwr.engine.setAsync(false);
	
	var toIndx = $("bjP").selectedIndex;
	var fromIndx = $("bj").options.length;
	if(toIndx < 0){
		return false;
	}
	for(var i=0;i<fromIndx;i++){
		if($("bjP").options[toIndx].value == $("bj").options[i].value){
			$("bjP").options[toIndx] = null;
			return false;
		}
	}
	$("bj").options[$("bj").options.length] = new Option($("bjP").options[toIndx].text,$("bjP").options[toIndx].value);
	$("bjP").options[toIndx] = null;

	if($("bj").options.length > 0){
		$("bj").options[0].selected = true;
	}
	
	dwr.engine.setAsync(true);
}

//重置
function czData(zd){
	if (confirm("是否重置回复内容?")) {
		var id = zd.split("-");
		for (i = 0; i < id.length; i++) {
			if($(id[i])){
				if($(id[i]).value != ""){
					$(id[i]).value = "";
				}
			}
		}
	}
}