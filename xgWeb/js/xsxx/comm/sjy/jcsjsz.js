//改变操作项目
function changeCzxm(xmdm){
	var url	="/xgxt/sjyJcsjcsh.do?method=jcsjcshManage";
		url+="&czxm="+xmdm;			
		refreshForm(url);
}

//同步数据
function tbData(){
	if (confirm("请确认是否要从接口同步数据？\n注：原有记录将被重置，请谨慎操作")) {
		saveUpdate('/xgxt/sjyJcsjcsh.do?method=jcsjcshManage&doType=tb','');
	}
}

//显示规则
function showRule(){
	var czxm = $("czxm").value;
	var url = "/xgxt/sjyJcsjcsh.do?method=ruleManage";
		url+="&czxm="+czxm;	

	showTopWin(url,600,550);
}

//提交部门信息
function submitBmInfo(lx){

	var message = "";
	
	if(lx == "all"){
	
		message +="请确认是否要将信息整体提交到正式库？\n\n";
		message +="注：原有相关信息将重置，请谨慎操作，\n";
		message +="    如果有不符合规则的记录将不会被提\n";
		message +="    交，请在“规则制定”进行确认。";
		
		if (confirm(message)) {
			if($("div_tsxx")){
				if($("div_toolbox")){
					$("div_toolbox").style.display="none";
				}
				if($("div_main_box")){
					$("div_main_box").style.display="none";
				}
				if($("div_tsxx")){
					$("div_tsxx").style.display="";
				}
				refreshForm("/xgxt/sjyJcsjcsh.do?method=jcsjcshManage&doType=allSubmit");
			}else{
				saveUpdate('/xgxt/sjyJcsjcsh.do?method=jcsjcshManage&doType=allSubmit','');
			}
		}
	}else{
	
		var num =  document.getElementsByName("primarykey_checkVal").length;
		var flag = false;
		
		for(var i=0;i<num;i++){
			var obj = document.getElementsByName("primarykey_checkVal")[i];
			if(obj.checked){
				flag = true;
				break;
			}
		}
		
		if(flag){
			message +="请确认是否要将勾选记录提交到正式库？\n\n";
			message +="注：原有相关信息将重置，请谨慎操作，\n";
			message +="    如果有不符合规则的记录将不会被提\n";
			message +="    交，请在“规则制定”进行确认。";
			
			if (confirm(message)) {
				saveUpdate('/xgxt/sjyJcsjcsh.do?method=jcsjcshManage&doType=submit','');
			}
		}else{
			alert("请勾选需要提交到正式库的数据");
			return false;
		}
	}
}