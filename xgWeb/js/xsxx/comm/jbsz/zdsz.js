//页面初始化赋值（来源表）
function showLybList(){
	var num = document.getElementsByName("lrxs").length;
	
	for(var i=0;i<num;i++){
		var obj = document.getElementsByName("lrxs")[i];
		var id = obj.id;
		
		if(obj.value!=""){
			setLybList(id.replace("lrxs",""));
		}
	}
}

//查询结果
function searchRs(){
	allNotEmpThenGo('/xgxt/xsxxJbsz.do?method=xtzdsz');
}

//设置来源表列表
function setLybList(num){

	dwr.engine.setAsync(false);
	
	//录入形式
	var lrxs_id = "lrxs"+num;
	var lrxs = $(lrxs_id).value;
	
	//字段
	var zd_id = "zd"+num;
	var zd = $(zd_id).value;
	
	//来源表
	var lyb_id = "lyb"+num;
	
	//如果是以下两种录入形式的话，可以选择来源表
	if(lrxs == "下拉列表" || lrxs == "单选框" || lrxs == "特殊格式"){
		var tableName = "xg_xsxx_zdlyb";
		var colList = ["lyb","lybm"];
		var pk = "zd";
		var pkValue = zd;
		var query = "";
		
		getOtherData.getTableListInfo(tableName,colList,pk,pkValue,query,function(data){
			if(data != null && data.length > 0){
				if($(lyb_id)){
					DWRUtil.removeAllOptions(lyb_id);
					DWRUtil.addOptions(lyb_id,data,"lyb","lybm");
				}
			}
		});
	}else{
		if($(lyb_id)){
			DWRUtil.removeAllOptions(lyb_id);
			var options = [{dm:"",mc:"请先选择录入形式"}];
			DWRUtil.addOptions(lyb_id,options,"dm","mc");
		}
	}
	
	dwr.engine.setAsync(true);
}

//保存字段设置
function saveZdsz(){
	var hadRs = $("hadRs").value;
	if(hadRs == "yes"){
		if (confirm("保存页面显示的字段设置，请确认！")) {
			saveUpdate('/xgxt/xsxxJbsz.do?method=xtzdsz&doType=save','');
		}
	}else{
		alert("请先查询出需要设置的字段，再执行该操作！");
	}
}

//创建新视图
function creatNewView(){
	if (confirm("将要根据目前字段配置情况，\n创建新的学生信息视图。\n请确认是否执行该操作！")) {
		saveUpdate('/xgxt/xsxxJbsz.do?method=xtzdsz&doType=create','');
	}
}

//弹出批量设置层
function showPlszDiv(){

	var num = document.getElementsByName("primarykey_checkVal").length;
	var flag = false;
	
	for(var i=0;i<num;i++){
		var obj = document.getElementsByName("primarykey_checkVal")[i];
		if(obj.checked == true){
			flag = true;
			break;
		}
	}
	
	if(flag){
		viewTempDiv("字段设置","tmpdiv1",350,300);
		//var divHtml = $("tmpdiv1").innerHTML;
		//createOtherDiv("字段设置",divHtml,"plszDIV",350,350);
	}else{
		alert("请勾选需要设置的字段！");
	}
}

//设置批量设置
function setPlsz(){
	var num = document.getElementsByName("primarykey_checkVal").length;
	var flag = false;
	
	//页面显示 
	var ymxs = $("sz_ymxs_value").value;
	//数据源
	var sjy = $("sz_sjy_value").value;
	//学工为准
	var xgwz = $("sz_xgwz_value").value;
	//录入限制
	var lrxz = $("sz_lrxz_value").value;
	//为空限制
	var wkxz = $("sz_wkxz_value").value;
	//录入形式
	var lrxs = $("sz_lrxs_value").value;
	//是否启用
	var sfqy = $("sz_sfqy_value").value;
	
	for(var i=0;i<num;i++){
		var obj = document.getElementsByName("primarykey_checkVal")[i];
		if(obj.checked == true){
			//页面显示 
			if(ymxs != "不处理"){
				$("xsmc"+i).value = $("zdm"+i).value;
			}
			//数据源
			if(sjy != "不处理"){
				$("sjy"+i).value = sjy;
			}
			//学工为准
			if(xgwz != "不处理"){
				for(var j=0;j<document.getElementsByName("xgwz"+i).length;j++){
					if(document.getElementsByName("xgwz"+i)[j].value == xgwz){
						document.getElementsByName("xgwz"+i)[j].checked = true;
						break;
					}
				}
				$("hid_xgwz_"+i).value=xgwz;
			}
			//录入限制
			if(lrxz != "不处理"){
				$("lrxz"+i).value = lrxz;
			}
			//为空限制
			if(wkxz != "不处理"){
				$("wkxz"+i).value = wkxz;
			}
			//录入形式
			if(lrxs != "不处理"){
				$("lrxs"+i).value = lrxs;
			}
			//是否启用
			if(sfqy != "不处理"){
				for(var j=0;j<document.getElementsByName("sfqy"+i).length;j++){
					if(document.getElementsByName("sfqy"+i)[j].value == sfqy){
						document.getElementsByName("sfqy"+i)[j].checked = true;
						break;
					}
				}
				$("hid_sfqy_"+i).value=sfqy;
			}
		}
	}
	
	hiddenMessage(true,true);
}

//选择数据源
function selectSjy(num){

	var sjy_id = "sjy"+num;
	var isxg = false;
	var xgwz = $("hid_xgwz_"+num).value;

	if($(sjy_id).value == "学工"){
		isxg = true;
	}

	for(var i=0;i<document.getElementsByName("xgwz"+num).length;i++){
	
		var obj = document.getElementsByName("xgwz"+num)[i];

		//学工为准
		if(isxg){
			if(obj.value == "是"){
				obj.checked = true;
				xgwz = obj.value;
			}else{
				obj.disabled = true;
			}
		}else{
			if(xgwz.value == "是"){
				obj.checked = true;
			}else{
				obj.disabled = false;
			}		
		}
	}
	
	$("hid_xgwz_"+num).value=xgwz;
}

//页面初始化赋值（学工为准）
function showXgwz(){
	var num = document.getElementsByName("sjy").length;

	for(var i=0;i<num;i++){
		var obj = document.getElementsByName("sjy")[i];
		var id = obj.id;
		if(obj.value!=""){
			selectSjy(id.replace("sjy",""));
		}
	}
}