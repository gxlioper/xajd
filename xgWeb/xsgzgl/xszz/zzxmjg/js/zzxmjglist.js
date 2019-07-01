var DCCLBH = "xszz_zzxmjg_cx.do";//dcclbh,导出功能编号        


function gridSetting(){
	
	var colList = [
	   {label:'guid',name:'guid', index: 'guid',width:'2%',key:true,hidden:true},
	   {label:'学号',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
	   {label:'姓名',name:'xm', index: 'xm',width:'7%'}
	];
	
	var xxdm = jQuery("#xxdm").val();
		
	if(xxdm=="10344"){
		colList.push({label:'身份证号',name:'sfzh', index:'sfzh',width:'20'});
	}else{
		colList.push({label:'性别',name:'xb', index: 'xb',width:'5%'});
		colList.push({label:'年级',name:'nj', index: 'nj',width:'5%'});
	}
   if(xxdm=="11483"){
	   colList.push({label:'所在大队',name:'ddmc', index: 'ddmc',width:'5%'});
	}
    colList.push({label:'书院',name:'symc', index: 'sydm',width:'15%'});
	colList.push({label:'行政班级',name:'bjmc', index: 'bjdm',width:'15%'});
    colList.push({label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'15%'});
	colList.push({label:'申请周期',name:'sqzq', index: 'sqzq',width:'12%'});
	if(xxdm=="11799"){
		colList.push({label:'审核通过时间',name:'lastshsj', index: 'lastshsj',width:'12%'});
	}else{
		colList.push({label:'项目评定周期',name:'pdzq', index: 'pdzq',width:'12%'});
	}
	colList.push({label:'项目名称',name:'xmmc', index: 'xmmc',width:'15%'});
	colList.push({label:'金额',name:'je', index: 'je',width:'6%'});
	colList.push({label:'sjly',name:'sjly', index: 'sjly',hidden:true});
	
	return {
		caption:"资助结果列表",
		pager:"pager",
		params:getSuperSearch(),
		url:"xszz_zzxmjg.do?method=getZzxmjgList&type=query",
		colList:colList,
		sortname: "xn,xq,sqsj",
	 	sortorder: "desc"
	};
}

   /**
* 资助项目单个结果调查--查看
* @param xh
* @return
*/
   function zzjgView(guid,xh){
		showDialog("资助结果查询",550,435,"xszz_zzxmjg.do?method=zzxmjgView&guid="+guid+"&xh="+xh);
   }
   
	function xhLink(cellValue,rowObject){
		return "<a href='javascript:void(0);' class='name' onclick='zzjgView(\""+rowObject["guid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

jQuery(function(){
	jQuery("#dataTable").initGrid(gridSetting());
});

function add(){
	var url = "xszz_zzxmjg.do?method=addZzxmjg";
	var title = "增加资助信息";
	showDialog(title,800,500,url);
}
function searchRs(){
	var map = getSuperSearch();

	jQuery("#dataTable").reloadGrid(map);
}

function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	var xxdm = jQuery("#xxdm").val();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
	   if(xxdm!="11799"){
		   if(rows[0]["sjly"]=='1'){
			   showAlertDivLayer("您选择的记录为审核过来的记录，不能修改，请重新选择！");
			   return false;
		   }
		}
		
		var url = 'xszz_zzxmjg.do?method=updateZzxmjg&guid='+rows[0]["guid"]+'&xh='+rows[0]["xh"];
		var title = "修改资助信息";
		showDialog(title,800,500,url);
	}
	
}
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']!='0'){
				showAlertDivLayer("所选的记录中包含审核过来的记录，不能删除，请重新选择！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xszz_zzxmjg.do?method=delZzxmjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
//打印报表
function printXmsq(url){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要打印的记录！");
	} else {

		var url= url+"&guid="+rows[0]["guid"]+"&xh="+rows[0]["xh"]+"&xmmc="+rows[0]["xmmc"]+"&xn="+rows[0]["xn"]+"&xq="+rows[0]["xq"]
		//判断该项目是否有报表
        jQuery.post("xszz_xmwh.do?method=getXszzdm",{"xmmc":rows[0]["xmmc"],"xn":rows[0]["xn"],"xq":rows[0]["xq"]},function(data){
			if(data["message"] != null && data["message"]!=""){
				window.open(url);
			}else{
				showAlertDivLayer("该项目尚未设置报表，请联系管理员！");
				return false;
			}
		},'json');
    }
}			


// 导出配置 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出的函数
	customExport(DCCLBH, exportData);
}

//导出配置 功能
function exportzzHzData() {
	//DCCLBH导出功能编号,执行导出的函数
	customExport("xszz_zzxmjg_hzdc.do", exportHzData);
}

// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "xszz_zzxmjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导出方法
function exportHzData() {
	//setSearchTj();//设置高级查询条件
	var url = "xszz_zzxmjg.do?method=exportHzData&dcclbh=xszz_zzxmjg_hzdc";//dcclbh,导出功能编号
	//url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//打印word
function getWord(){	
	var rows = jQuery("#dataTable").getSeletRow();	
	 if (rows.length == 0){
		showAlertDivLayer("请选择一条记录！");
	 } else if (rows.length > 1){
		 var guid = jQuery("#dataTable").getSeletIds();
		var url="xszz_zzxmjg.do?method=downloadZip";
//		var ids = jQuery("#dataTable").getSeletIds();
//		url += "&value="+ids;
//		window.open(url);
		post(url, {value:guid});
	 } else {
		var url="xszz_zzxmjg.do?method=downloadWord";		
		url += "&guid="+rows[0]["guid"];
		window.open(url);
     }
}

function post(URL, PARAMS) {        
    var temp = document.createElement("form");        
    temp.action = URL;        
    temp.method = "post";        
    temp.style.display = "none";        
    for (var x in PARAMS) {        
        var opt = document.createElement("textarea");        
        opt.name = x;        
        opt.value = PARAMS[x];        
        // alert(opt.name)         
        temp.appendChild(opt);        
    }        
    document.body.appendChild(temp);        
    temp.submit();        
    return temp;        
} 

//考核复制
function copy(){
	var iscanCopy=jQuery("#iscanCopy").val();
	if(iscanCopy=="0"){
		showAlertDivLayer("不存在其他学年的资助结果，不能复制。",{},{"clkFun":function(){
		}});
		return false;
	}

	var url ="xszz_zzxmjg.do?method=zzjgCopy";
	showDialog("考核复制", 400, 200, url);
}

function saveCopy(){
	var lyxn=jQuery("#lyxn").val();
	var mbxn=jQuery("#mbxn").val();
	jQuery.post("xszz_zzxmjg.do?method=saveCopy", {
		lyxn:lyxn,
		mbxn:mbxn
	}, function(data) {
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	}, 'json');
}

//山东畜牧兽医职业学院（社会助学金汇总表）
function getshzxjHzexcel(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	 if (rows.length == 0){
		showAlertDivLayer("请选择您要打印的记录！");
	 } else {
		var ids = jQuery("#dataTable").getSeletIds();
		var url="xszz_zzxmjg.do?method=getSdxm_shzxjhzexcel&value="+ids;
		window.open(url);
	 } 
}

//山东畜牧兽医职业学院（国家励志奖学金汇总表）
function getgjlzjHzexcel(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	 if (rows.length == 0){
		showAlertDivLayer("请选择您要打印的记录！");
	 } else {
		var ids = jQuery("#dataTable").getSeletIds();
		var url="xszz_zzxmjg.do?method=getSdxm_gjlzjhzexcel&value="+ids;
		window.open(url);
	 } 
}

//山东畜牧兽医职业学院（国家助学奖学金汇总表）
function getSdxm_gjzxjhzexcel(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	 if (rows.length == 0){
		showAlertDivLayer("请选择您要打印的记录！");
	 } else {
		var ids = jQuery("#dataTable").getSeletIds();
		var url="xszz_zzxmjg.do?method=getSdxm_gjzxjhzexcel&value="+ids;
		window.open(url);
	 } 
}

//山东畜牧兽医职业学院（国家励志奖汇总模板excel）
function getSdxm_gjlzjhzmbexcel(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	 if (rows.length == 0){
		showAlertDivLayer("请选择您要打印的记录！");
	 } else {
		var ids = jQuery("#dataTable").getSeletIds();
		var url="xszz_zzxmjg.do?method=getSdxm_gjlzjhzmbexcel&value="+ids;
		window.open(url);
	 } 
}

//浙江大学导入用新版，此方法也可作为通用导入
function zzjgdr(){
	toImportDataNew("IMPORT_N720305_ZZJG");
	return false;
}

//青岛酒店管理职业技术学院
function qdjddr(){
	showDialog("导入", 500, 200, "xszz_zzxmjg.do?method=drForQdjd");
}

//下载青岛酒店管理职业技术学院导入模板
function downloadDrmb(){
	window.open("xszz_zzxmjg.do?method=downloadTemplate");
}

//将文件名称赋值到input框
function attachfilename(obj){
	var filefullpath = getFullPath(obj);
	checkFileType(obj);
}

//获取input file的名称
function getFullPath(obj){ 
	if(obj) 
	{ 
		 if(window.navigator.userAgent.indexOf("Firefox")>=1) 
		 { 
			 if(obj.files) 
			 { 
				 return obj.files.item(0).getAsDataURL(); 
			 } 
			 return obj.value; 
		 } 
		 return obj.value; 
	 } 
} 

//文件类型控制
function checkFileType(obj){
	var type = obj.value.substr(obj.value.lastIndexOf(".")+1);
	var types = ["xls"];
	if (jQuery.inArray(type, types) == -1){
		/*如果不符合上传类型,清空input file，兼容性写法，兼容ie和chrome*/
		var file = jQuery(obj);
		file.after(file.clone().val("")); 
		file.remove(); 
		showAlert("只允许上传xls类型的文件。");
		return false;
	}
}

//青岛酒店管理职业技术学院个性化导入
function Dr() {
	var url = "xszz_zzxmjg.do?method=saveDrForQdjd";
	if(jQuery("#drmb").val() == "" ||　jQuery("#drmb").val() == null){
		showAlert("请选择导入文件！");
		return false;
	}
	ajaxSubFormWithFun("zzxmjgForm", url, function(data) {
		 if(data["message"]=="导入成功！"){
			 jQuery("#errortr").hide();
		    jQuery("#errora").attr("href","");
   		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
		 }else{  			   
   			 showAlert(data["message"],{},{"clkFun":function(){
   				      if(data["message"] == "导入失败,请仔细核对【出错记录.xls】！"){
   				    	  if(data["gid"] != "" && data["gid"] != null && data["gid"] != "null"){
   				    		  jQuery("#errortr").show();
   				    		  jQuery("#sl").html("导入数据"+data["cgs"]+"条，导入出错"+data["cws"]+"条");
   				    		  jQuery("#errora").attr("data_file","xszz_zzxmjg.do?method=downloadFileError&filename="+data["gid"]);
   				    	  }
   				      }else{
   				    	jQuery("#errortr").hide();
   				    	jQuery("#errora").attr("data_file","");
   				      }
   				      jQuery("#drmb").val("");
				}});
   		}
	});
}

//错误数据下载
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}

//关闭刷新
function gb(){
	if (parent.window){
		refershParent();
	}
}

// 国家奖学金导出
function gjjxjdc() {
	setSearchTj();//设置高级查询条件
	var url = "xszz_zzxmjg.do?method=gjjxdc&dcclbh=xg_xszz_gjjxz.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//国家助学金导出
function gjzxjdc() {
	setSearchTj();//设置高级查询条件
	var url = "xszz_zzxmjg.do?method=gjjxdc&dcclbh=xg_xszz_gjzxz.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//国家励志金导出
function gjlzjdc() {
	setSearchTj();//设置高级查询条件
	var url = "xszz_zzxmjg.do?method=gjjxdc&dcclbh=xg_xszz_gjlzj.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}