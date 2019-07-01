function dtjsCommonSave(url,yzdz,yzcd,mustFill){
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("请将带\"*\"号的项目输入完整！");
			return;
		}
	}
	var splitDz = yzdz.split("-");
	var splitCd = yzcd.split("-");
	if(splitDz[0]!=""){
	for (i = 0; i < splitDz.length; i++) {
		var dzsjcd = $(splitDz[i]).cells[1].getElementsByTagName('textarea')[0].value;
		var dzsjmc = $(splitDz[i]).cells[0].innerHTML;
		if (dzsjcd.length>splitCd[i]) {
			alert(dzsjmc+"不能超过"+splitCd[i]+"个字！");
			return;
		}
	}
	}
	document.forms[0].action = url;
	document.forms[0].submit();
}

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
			if (confirm("确定要删除所勾选的数据?")) {
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

 /**
 *   指定增加学号字段数据出处
 *   luojw
 *   2010-6-28
 */
function sendXx(){
	//页面存隐藏域，指定所查询数据出处，例如（<input type="hidden" id="tableName" name="tableName" value="view_ybdyxx"/>）
	var tableName=$("tableName").value;
	//数据源的描述，展示jsp的表头，例如（<input type="hidden" id="lx" name="lx" value="预备党员"/>）
	var lx="";
	//数据源的特殊条件，例如:
	//<input type="hidden" id="zd" name="zd" value="sfty-xxsh"/>
	//<input type="hidden" id="va" name="va" value="是-通过"/>
	var zd="";
	var va="";
	var url = "/xgxt/czxxDtjsDyxx.do?method=xsxxManage";
	
	if($("lx")){
		lx=$("lx").value
	};
	if($("zd")){
		zd=$("zd").value
	};
	if($("va")){
		va=$("va").value
	};
	
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
/**
*左边框的值得添加到右边框，添加成功同时，删除原有值得
*luojw
*20100628
*/
function addBjP(){

	dwr.engine.setAsync(false);
	//左边框空间命名为bj(可以考虑传入参数)
	var fromIndx = $("bj").selectedIndex;
	//右边框空间命名为bjP(可以考虑传入参数)
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

/**
*右边框的值得添加到右边框，添加成功同时，删除原有值得
*luojw
*20100628
*/
function delBjP(){

	dwr.engine.setAsync(false);
	//右边框空间命名为bjP(可以考虑传入参数)
	var toIndx = $("bjP").selectedIndex;
	//左边框空间命名为bj(可以考虑传入参数)
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

function setXylist(){

	var nj = $("nj").value;
	var objId = "xydm";
	
	getPjpyInfo.getXyList(nj,function(data){
		if(data !=null && data.length >0){
			if(data !=null && data.length >0){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"dm","mc");
			}
		}
	});
}

/**
*左边框的值得添加到右边框，添加成功同时，删除原有值得
*luojw
*20100628
*/
function addRightFrame(left,right){

	dwr.engine.setAsync(false);
	
	var fromIndx = $(left).selectedIndex;
	var toIndx = $(right).options.length;

	if(fromIndx < 0){
		return false;
	}

	if($(left).options[fromIndx].value == "" || $(left).options[fromIndx].value == null || $(left).options[fromIndx].value == "null"){
		return false;
	}
	
	for(var i=0;i<toIndx;i++){
		if($(left).options[fromIndx].value == $(right).options[i].value){
			return false;
		}
	}
	
	$(right).options[$(right).options.length] = new Option($(left).options[fromIndx].text,$(left).options[fromIndx].value);
	$(left).options[fromIndx] = null;
	if($(left).options.length > 0){
		$(left).options[0].selected = true;
		//$("delBj").disabled = false;
	}else{		
		//$("addBj").disabled = true;
	}
	if($(right).options.length > 0){
		$(right).options[0].selected = true;
	}
	
	dwr.engine.setAsync(true);
}

/**
*右边框的值得添加到左边框，添加成功同时，删除原有值得
*luojw
*20100628
*/
function addLeftFrame(left,right){

	dwr.engine.setAsync(false);
	
	var toIndx = $(right).selectedIndex;
	var fromIndx = $(left).options.length;
	if(toIndx < 0){
		return false;
	}
	for(var i=0;i<fromIndx;i++){
		if($(right).options[toIndx].value == $(left).options[i].value){
			$(right).options[toIndx] = null;
			return false;
		}
	}
	$(left).options[$(left).options.length] = new Option($(right).options[toIndx].text,$(right).options[toIndx].value);
	$(right).options[toIndx] = null;

	if($(left).options.length > 0){
		$(left).options[0].selected = true;
	}
	
	dwr.engine.setAsync(true);
}