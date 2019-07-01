function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}

//查询
function searchRs(){
	var url = "jxgl_jxxxwh_ajax.do?method=jxmdCx&pkValue="+jQuery("#pkValue").val();
	var ie = "10.0";
	var otherValue = [ie];
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","1000")
}



//名单生成
function jxmdsc(){
	getTJxrs();
	tipsWindown("参训名单生成","id:tempDiv","450","350","true","","true","id");
}

//获得军训人数
function getTJxrs(){
	jQuery.ajaxSetup({async:false});
	var array = jQuery("[name=cjnj]:checked");
	var str = "";
	var sfhx = jQuery("input[name=sfhx]:checked").val();
	var sfmx = jQuery("input[name=sfmx]:checked").val();
	for (var i=0;i<array.length;i++) {
		var pkValue = jQuery(array[i]).val();
		str += pkValue+"!!@@!!";
	}
	var parameter={};
	parameter["jxid"]=escape(jQuery("#pkValue").val());
	parameter["cjnj"]=escape(str);
	parameter["sfhx"]=escape(jQuery("input[name=sfhx]:checked").val());
	parameter["sfmx"]=escape(jQuery("input[name=sfmx]:checked").val());
	jQuery.ajaxSetup({cache:false});
	url = "jxgl_jxxxwh_ajax.do?method=getJxrs";
	jQuery.getJSON(url,parameter,function(data){
		if(data != null){
			jQuery('#njrs').text(data.njrs+"人");
			jQuery('#hxrs').text(data.hxrs+"人");
			jQuery('#mxrs').text(data.mxrs+"人");
			if("0"==data.njrs && "0"==data.hxrs && "0"==data.mxrs){
				jQuery("#bcBtn").hide();
			}else{
				jQuery("#bcBtn").show();
			}
			jQuery('#cjrs').text(data.cjrs+"人");
		}
	});
	jQuery.ajaxSetup({async:true});
}


//获得军训人数
function getJxrs(){
	var array = jQuery("[name=cjnj]:checked");
	var str = "";
	var sfhx = jQuery("[name=sfhx]:checked").val();
	var sfmx = jQuery("[name=sfmx]:checked").val();
	for (var i=0;i<array.length;i++) {
		var pkValue = jQuery(array[i]).val();
		str += pkValue+"!!@@!!";
	}
	var parameter={};
	parameter["jxid"]=escape(jQuery("#pkValue").val());
	parameter["cjnj"]=escape(str);
	parameter["sfhx"]=escape(jQuery("[name=sfhx]:checked").val());
	parameter["sfmx"]=escape(jQuery("[name=sfmx]:checked").val());
	jQuery.ajaxSetup({cache:false});
	jQuery.ajaxSetup({async:false});
	url = "jxgl_jxxxwh_ajax.do?method=getJxrs";
	jQuery.getJSON(url,parameter,function(data){
		if(data != null){
			jQuery('#njrs').text(data.njrs+"人");
			jQuery('#hxrs').text(data.hxrs+"人");
			jQuery('#mxrs').text(data.mxrs+"人");
			if("0"==data.njrs && "0"==data.hxrs && "0"==data.mxrs){
				jQuery("#bcBtn").hide();
			}else{
				jQuery("#bcBtn").show();
			}
			jQuery('#cjrs').text(data.cjrs+"人");
		}
	});
	jQuery.ajaxSetup({async:true});
}


function jxmdDivSave(){
	var len = jQuery("[name=cjnj]:checked").length;
	if(len>=1){
		var array = jQuery("[name=cjnj]:checked");
		var str = "";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).val();
			str += pkValue+"!!@@!!";
		}
		var parameter={};
		parameter["jxid"]=escape(jQuery("#pkValue").val());
		parameter["cjnj"]=escape(str);
		parameter["sfhx"]=escape(jQuery("[name=sfhx]:checked").val());
		parameter["sfmx"]=escape(jQuery("[name=sfmx]:checked").val());
		jQuery.ajaxSetup({async:false});
		url = "jxgl_jxxxwh_ajax.do?method=scJxmd";
		jQuery.post(url,
				parameter,
				function(result){
						alertInfo(result);
						closeWindown();
						searchRs();
				}
			);
		jQuery.ajaxSetup({async:true});
	}else{
		alertInfo("请至少勾选一个年级进行保存！");
		return false;
	}
}

//删除，缓训，免训，参训
function jxmdModi(type){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len>=1){
		var array = jQuery("[name=div_pkValue]:checked");
		var str = "";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
			str += pkValue+"!!@@!!";
		}
		
		
		var flag = checkScJxmd();
		if(!flag){
			alertInfo("学生名单已在军训建制中使用，不能再次删除或更改信息!",function(tag){
	 			if(tag=="ok"){
	 				return false;
	 			}
	 		});
	 		return false;
		}
		
		if("sc"==type){
			confirmInfo("是否确定取消勾选学生的军训资格？",function(tag){
				if(tag=="ok"){
					var parameter={}
					var url="jxgl_jxxxwh_ajax.do?method=jxmdSc";	
					parameter["pkValue"]=str;							
					jQuery.ajaxSetup({async:false});	
					jQuery.post(url,
						parameter,
						function(result){
								//alertInfo(result,function(tag){
								//	if(tag=="ok"){
								//		searchRs();
								//	}
								//});
							alertInfo(result);
							searchRs();
						}
					);
					jQuery.ajaxSetup({async:true});
				}
			});
		}else if("hx"==type || "mx"==type || "cx"==type){
			var parameter={}
			var url="jxgl_jxxxwh_ajax.do?method=cxqkCz";	
			parameter["pkValue"]=str;
			parameter["cxqk"]=type;
			jQuery.ajaxSetup({async:false});	
			jQuery.post(url,
				parameter,
				function(result){
						alertInfo(result);
						searchRs();
				}
			);
			jQuery.ajaxSetup({async:true});
		}
	}else{
		alertInfo("请勾选需要操作的数据！",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
	}
}

//删除，缓训，免训
function jxxx(cxz) {
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len == 1){
		var array = jQuery("[name=div_pkValue]:checked");
		var str = "";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
			str += pkValue+"!!@@!!";
		}
		var flag = checkScJxmd();
		if(!flag){
			alertInfo("学生名单已在军训建制中使用，不能再次删除或更改信息!",function(tag){
	 			if(tag=="ok"){
	 				return false;
	 			}
	 		});
	 		return false;
		}
	}else{
		alertInfo("请勾选一条需要操作的数据！",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	var pk = jQuery("#pkValue").val();
	var xh = jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(1).text();
	var url = "jxgl_jxxxwh.do?method=jxxx&xh="+xh+"&jxid="+pk+"&cxz="+cxz;
	if("hx" == cxz) {
		var title = "缓训信息";
	}else if("mx" == cxz) {
		var title = "免训信息";
	}else if("sc" == cxz) {
		var title = "取消军训信息";
	}
	showDialog(title, 800, 508, url);
}

// 查看
function viewJxxx() {
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len != 1){
		alertInfo("请勾选一条需要查看的数据！",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	var cxqk = jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(7).text();
	var pk = jQuery("#pkValue").val();
	var xh = jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(1).text();
	var url = "jxgl_jxxxwh.do?method=viewJxxx&xh="+xh+"&jxid="+pk+"&cxqk="+cxqk;
	var title = "军训信息";
	showDialog(title, 800, 508, url);
	
}



//验证删除信息
function checkScJxmd(){
	var flag = false;
	jQuery.ajaxSetup({async:false});
	var array = jQuery("[name=div_pkValue]:checked");
	var str = "";
	for (var i=0;i<array.length;i++) {
		var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
		str += pkValue+"!!@@!!";
	}
	var parameter={}
	var url="jxgl_jxxxwh_ajax.do?method=checkScJxmd";	
	parameter["pkValue"]=str;	
	jQuery.post(url,parameter,
		function(result){
			if("true"==result){
				flag = true;
			}
		});
	jQuery.ajaxSetup({async:true});
	return flag;
}

//导出
function exportConfig(){
	var DCCLBH='jxgl_jxxxwh.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH='jxgl_jxxxwh.do';
	setSearchTj();//设置高级查询条件
	var url = "jxgl_jxxxwh.do?method=exportData&dcclbh=" + DCCLBH+
	        "&pkValue="+jQuery("#pkValue").val();//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


// 缓训
function printHx() {
	var len = jQuery("[name=div_pkValue]:checked").length;
	var cxqk = jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(7).text();
	var pk = jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
	var array = jQuery("[name=div_pkValue]:checked");
	var str = "";
	for (var i=0;i<array.length;i++) {
		var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
		str += pkValue+",";
	}
	if (len == 1) {
		if("缓训" == cxqk) {
			var url = "jxgl_jxxxwh.do?method=printHx";
			url += "&pkValue=" + pk;
			window.open(url);
		}else {
			showAlertDivLayer("请选择缓训记录下载！");
			return false;
		}
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		for (var i=0;i<array.length;i++) {
			if("缓训" != jQuery(array[i]).parent().parent().find("td").eq(7).text()){
				showAlertDivLayer("请选择缓训记录下载！");
				return false;		
			}	
		}
		var url = "jxgl_jxxxwh.do?method=getHxbZip";
		url += "&value=" + str;
		window.open(url);
	}
}


// 免训
function printMx() {
	var len = jQuery("[name=div_pkValue]:checked").length;
	var cxqk = jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(7).text();
	var pk = jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
	var array = jQuery("[name=div_pkValue]:checked");
	var str = "";
	for (var i=0;i<array.length;i++) {
		var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
		str += pkValue+",";
	}
	if (len == 1) {
		if("免训" == cxqk) {
			var url = "jxgl_jxxxwh.do?method=printMx";
			url += "&pkValue=" + pk;
			window.open(url);
		}else {
			showAlertDivLayer("请选择免训记录下载！");
			return false;
		}
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		for (var i=0;i<array.length;i++) {
			if("免训" != jQuery(array[i]).parent().parent().find("td").eq(7).text()){
				showAlertDivLayer("请选择免训记录下载！");
				return false;		
			}	
		}
		var url = "jxgl_jxxxwh.do?method=getMxbZip";
		url += "&value=" + str;
		window.open(url);
	}
}


/**
 * 弹出模态窗口，替换原来的showTopWin
 * 
 * @param width
 * @param height
 * @param url
 * @param t
 * @param 其它参数
 */
function showDialog(t,width,height,url,other){
	var title = t||"学生工作管理系统";
	var setting = {
		title:title,
		width:width,
		height:height,
		lock:true,
		fixed:true,
		focus:true,
		min:false,
		zIndex: 1976,
		content:'url:'+url
		
	};
	var params = jQuery.extend(setting, other || {});
	if(frameElement&& frameElement.api){
		var api = frameElement.api;
		var W = api.opener;
		params["id"] = "childDialog";
		params["parent"] = api;
		params["zIndex"] = 1977;
		return W.lhgdialog(params);
	}else{
		params["id"] = "parentDialog";
		params["parent"] = window;
		return lhgdialog(params);
	}
	
	
}
/**
 * 关闭模态窗口
 */
function closeDialog(){
	var api = frameElement.api; 
	api.close();
}


/**
 * 弹出警告框
 */
function showAlertDivLayer() {
	var argumentsArr = Array.prototype.slice.call(arguments);
	if(argumentsArr[0] == null) return;
	
	var clickFun = null;
	
	if (argumentsArr.length == 3){
		clickFun = argumentsArr[2]["clkFun"];
	}
	ymPrompt.alert({
		title:"系统提示",
		useSlide:true,
		maskAlphaColor:"#FFFFFF",
		maskAlpha:0.3,
		message:argumentsArr[0],
		width:340,
		winPos:[180,160],
		height:150,
		// showMask:false,
		handler:clickFun
	});
	// setTimeout(function(){ymPrompt.doHandler();},3000);
}

