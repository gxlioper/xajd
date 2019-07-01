var sfList = ["sfgc","lszn","sfdq","sfdb","sfydk","sxqsfqg","bxqsfqg","bxnsfdk",
			  "bxnsfjm","bxnsfqg","bxnsfjxj","bxnsfbz","bxnsfszz","sfjf","sfdk",
			  "sfge","sfcj","sfpkdqssmz","sfyfjt","sfdz","sfqz","sfpkx","sfbgb",
			  "ywzm","sfbk","sfhdqtzz","sxzhpm"];
			  
var djList = ["pdbx","cjdj"];

//设置初始值(申请)
function setXmsqSelectList(){
	
	var xxdm = $("xxdm").value;
		
	dwr.engine.setAsync(false);
	
	setSfList(xxdm);

	//是否列表
	if(sfList != null && sfList.length > 0){
	
		for(var i=0;i<sfList.length;i++){
			var id = sfList[i];	
			if($(id)){
			
				var options = [{dm:"是",mc:"是"},{dm:"否",mc:"否"}];
					DWRUtil.removeAllOptions(id);
					DWRUtil.addOptions(id,options,"dm","mc");
					
				var selectId = "select_"+id;
				$(id).value = $(selectId).value;
			}		
		}
	}
	
	//等级列表
	if(djList != null && djList.length > 0){
	
		for(var i=0;i<djList.length;i++){
			var id = djList[i];	
			if($(id)){
			
				var msg = "----请选择----";
				var options = [{dm:"",mc:msg},{dm:"优秀",mc:"优秀"},{dm:"良好",mc:"良好"},{dm:"中等",mc:"中等"},{dm:"一般",mc:"一般"},{dm:"较差",mc:"较差"}];
					DWRUtil.removeAllOptions(id);
					DWRUtil.addOptions(id,options,"dm","mc");
					
				var selectId = "select_"+id;
				$(id).value = $(selectId).value;
			}		
		}
	}
	
	//德育评定等级
	if($("dypddj")){

		var tableName = "hndx_xszz_dydjb"; 
		var dm = "dm"; 
		var mc = "mc";
		var msg = "----请选择----";
		var pk = "";
		var pkValue = "";
		var id = "dypddj";
		
		if(pkValue == ""){
			pk = "";
		}
			
		getXszzInfo.getXszzList(tableName, dm, mc, msg, pk, pkValue,function(data){
			if(data!=null){
				DWRUtil.removeAllOptions(id);
				DWRUtil.addOptions(id,data,"dm","mc");
				$(id).options[0].value = "";
			}
		});
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
		
	//学生类别
	if($("xslb")) {	
		var id = "xslb";
		var options = [{dm:"",mc:msg},{dm:"非师本科",mc:"非师本科"},{dm:"师范本科",mc:"师范本科"},{dm:"高职本科",mc:"高职本科"},{dm:"高职专科",mc:"高职专科"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//家庭户口
	if($('jthk')) {
		
		var id = "jthk";
		var options = [{dm:"",mc:msg},{dm:"城镇",mc:"城镇"},{dm:"农村",mc:"农村"}];
		if("10477" == xxdm){//信阳师范学院
			options = [{dm:"",mc:msg},{dm:"农业户口",mc:"农业户口"},{dm:"非农业户口",mc:"非农业户口"},{dm:"未知",mc:"未知"}];
		}
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//天津工业大学-----性别
	if($('kzzd1') && xxdm == '10058') {
		
		var id = "kzzd1";
		var options = [{dm:"",mc:msg},{dm:"男",mc:"男"},{dm:"女",mc:"女"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//家庭年总收入
	if($("jtnzsr")){
		var id = "jtnzsr";
		var options = [{dm:"",mc:msg},{dm:"2000元以下",mc:"2000元以下"},{dm:"2000-3000元",mc:"2000-3000元"},
		               {dm:"3000-5000元",mc:"3000-5000元"},{dm:"5000元以上",mc:"5000元以上"}];
		if ($("jtnzsr").tagName.toLowerCase() =="select"){
			DWRUtil.removeAllOptions(id);
			DWRUtil.addOptions(id,options,"dm","mc");
			
			var selectId = "select_"+id;
			$(id).value = $(selectId).value;
		}		
	}
	//家庭月总收入
	if($("jtyzsr")){
		var id = "jtyzsr";
		var options = [{dm:"",mc:msg},{dm:"300元以下",mc:"300元以下"},{dm:"300-600元",mc:"300-600元"},
		               {dm:"600-1000元",mc:"600-1000元"},{dm:"1000元以上",mc:"1000元以上"}];
		if ($("jtyzsr").tagName.toLowerCase() =="select"){
			DWRUtil.removeAllOptions(id);
			DWRUtil.addOptions(id,options,"dm","mc");
			
			var selectId = "select_"+id;
			$(id).value = $(selectId).value;
		}		
	}
	//家中读大学人数
	if($("jtddxrs")){
		var id = "jtddxrs";
		var options = [{dm:"",mc:msg},{dm:"4",mc:"4"},{dm:"3",mc:"3"},
		               {dm:"2",mc:"2"},{dm:"1",mc:"1"}];
		if ($("jtddxrs").tagName.toLowerCase() =="select"){
			DWRUtil.removeAllOptions(id);
			DWRUtil.addOptions(id,options,"dm","mc");
			
			var selectId = "select_"+id;
			$(id).value = $(selectId).value;
		}		
	}
	//父母健康
	if($('fmjk')){
		var id = "fmjk";
		var options = [{dm:"无",mc:"无"},{dm:"父亲有",mc:"父亲有"},{dm:"母亲有",mc:"母亲有"},
		               {dm:"父母皆有",mc:"父母皆有"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//健康状况 
	if($('jkzk')) {
		var id = "jkzk";
		var options = [{dm:"",mc:msg},{dm:"良好",mc:"良好"},{dm:"一般",mc:"一般"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//父母健在情况
	if($('fmjz')){
		var id = "fmjz";
		var options = [{dm:"父母双全",mc:"父母双全"},{dm:"父母双亡",mc:"父母双亡"},{dm:"父亡母在",mc:"父亡母在"},
		               {dm:"父在母亡",mc:"父在母亡"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//贫困等级
	if($('pkxjb')){
		var id = "pkxjb";
		var options = [{dm:"国家级贫困县",mc:"国家级贫困县"},{dm:"省级贫困县",mc:"省级贫困县"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}

	// 省市县
	if($('szsheng') && $('szshi') && $('szxian')){
		setHjxfHndx('szsheng','szshi','szxian');
	}

	//申请缴费方式
	if($('sqjffs')) {
		var id = "sqjffs";
		var options = [{dm:"",mc:msg},{dm:"减免",mc:"减免"},{dm:"贷款",mc:"贷款"},{dm:"缓交",mc:"缓交"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//學習情況評價
	if($('xxqkpj')) {
		var id = "xxqkpj";
		var options = ["   ","优","良","一般","差"];
		
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options);
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//学费减免申请类别
	if($('sqlb')) {
		var id = "sqlb";
		var options = ["   ","全免","部分免"];
		
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options);
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//有无不良信用记录
	if($('sfblxy')) {
		var id = "sfblxy";
		var options = ["   ","有","无"];
		
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options);
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//校区
	if($("xiaoqudm")){		
		var tableName = "dm_zju_xq"; 
		var dm = "dm"; 
		var mc = "xqmc";
		var msg = "----请选择----";
		var pk = "";
		var pkValue = "";
		var id = "xiaoqudm";
		
		if(pkValue == ""){
			pk = "";
		}
			
		getXszzInfo.getXszzList(tableName, dm, mc, msg, pk, pkValue,function(data){
			if(data!=null){
				DWRUtil.removeAllOptions(id);
				DWRUtil.addOptions(id,data,"dm","mc");
				$(id).options[0].value = "";
			}
			var selectId = "select_"+id;
			$(id).value = $(selectId).value;
		});
	}
	
	//健康状况 
	if($('qy')) {
		var msg = "----请选择----";
		var id = "qy";
		var options = [{dm:"",mc:msg},{dm:"东部",mc:"东部"},{dm:"西部",mc:"西部"},{dm:"中部",mc:"中部"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	//是否有高等学校学生家庭情况认定表
	if($('kzzd8') && xxdm == '10488') {
		
		var id = "kzzd8";
		var options = [{dm:"是",mc:"是"},{dm:"否",mc:"否"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	//是否有高高等学校学生家庭情况调查表
	if($('kzzd9') && xxdm == '10488') {
		
		var id = "kzzd9";
		var options = [{dm:"是",mc:"是"},{dm:"否",mc:"否"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	dwr.engine.setAsync(true);
}


function setSfList(xxdm){
	if(xxdm=="88888"){
		sfList = ["sfgc","lszn","sfdq","sfdb","sfydk","sxqsfqg","bxqsfqg","bxnsfdk",
			  "bxnsfjm","bxnsfqg","bxnsfjxj","bxnsfbz","bxnsfszz","sfjf","sfdk",
			  "sfge","sfcj","sfpkdqssmz","sfyfjt","sfdz","sfqz","sfpkx","sfbgb",
			  "ywzm","sfbk","sfhdqtzz","sxzhpm","kzzd1","kzzd2","kzzd3","kzzd4",
			  "kzzd5","kzzd6","kzzd7"];
	}else if(xxdm=="10488"){
		sfList = ["sfgc","lszn","sfdq","sfdb","sfydk","sxqsfqg","bxqsfqg","bxnsfdk",
					  "bxnsfjm","bxnsfqg","bxnsfjxj","bxnsfbz","bxnsfszz","sfjf","sfdk",
					  "sfge","sfcj","sfpkdqssmz","sfyfjt","sfdz","sfqz","sfpkx","sfbgb",
					  "ywzm","sfbk","sfhdqtzz","sxzhpm","kzzd8","kzzd9"];
	}else if(xxdm=="10504"){
		sfList = ["sfgc","lszn","sfdq","sfdb","sfydk","sxqsfqg","bxqsfqg","bxnsfdk",
			  "bxnsfjm","bxnsfqg","bxnsfjxj","bxnsfbz","bxnsfszz","sfjf","sfdk",
			  "sfge","sfcj","sfpkdqssmz","sfyfjt","sfdz","sfqz","sfpkx","sfbgb",
			  "ywzm","sfbk","sfhdqtzz","sxzhpm", "kzzd4","kzzd7","kzzd8"];
	}
}