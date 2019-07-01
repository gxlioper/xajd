function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//阶段维护设置
function jdwhsz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(rows[0]['rdzt']=='已认定'){
			showAlertDivLayer("该项目学分已认定，不能进行操作！");
			return false;
		}
		if(rows[0]['csmsmc'] == '个人'){
			var url = "grttxm_jdsz.do?method=getJdszGrList&xmmc="+rows[0]['xmmc']+"&jdmc="+rows[0]['jdmc']+"&jdid="+rows[0]['jdid']+"&xmdm="+rows[0]['xmdm'];
			document.location.href = url;
		}else{
			var url = "grttxm_jdsz.do?method=getJdszTtList&xmmc="+rows[0]['xmmc']+"&jdmc="+rows[0]['jdmc']+"&jdid="+rows[0]['jdid']+"&xmdm="+rows[0]['xmdm'];
			document.location.href = url;
		}
		
	}
	
}

/**
 * xmmc查看
 * @return
 */
function xmmcLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='xmmcView(\""
	+ rowObject["jdmc"] + "\",\"" + rowObject["jdid"] + "\",\"" + rowObject["xmdm"] + "\",\"" + rowObject["csmsmc"] + "\",\"" + cellValue + "\");'>" + cellValue
	+ "</a>";
}

//查看学生ajaxurl跳转
function xmmcView(jdmc,jdid,xmdm,csmsmc,xmmc) {
	if(csmsmc == '个人'){
		var url = "grttxm_jdsz.do?method=getJdszGrList&xmmc="+xmmc+"&jdmc="+jdmc+"&jdid="+jdid+"&xmdm="+xmdm+"&flag=view";
		showDialog("查看", 770, 450, url);
	}else{
		var url = "grttxm_jdsz.do?method=getJdszTtList&xmmc="+xmmc+"&jdmc="+jdmc+"&jdid="+jdid+"&xmdm="+xmdm+"&flag=view";
		showDialog("查看", 770, 450, url);
	}
}

/*查询*/
function doQuery(){
	var map = {};
	map['jdid']=jQuery('#jdid').val();
	map['xh']=jQuery('#xh').val();
	jQuery("#dataTable").reloadGrid(map);
}	

//备注
function bzLink(cellValue,rowObject){
	var bz = cellValue == null ? "" : cellValue;
	var html = "<textarea name='bz'  data-jdwhid ='"+rowObject["jdwhid"]+"'  onblur='saveThisRow(this)' style='width:80%;height:30px' />";
	var obj = jQuery(html);
	jQuery(obj).text(bz);
	return obj;
}

//活动时长
function hdscLink(cellValue,rowObject){
	var hdsc = cellValue == null ? "" : cellValue;
	var html = "<input name='hdsc' maxlength = '25' value = '"+hdsc+"' data-jdwhid ='"+rowObject["jdwhid"]+"'  style='width:80%'  onblur='saveThisRow(this)' />";
	var obj = jQuery(html);
	return obj;
}

//阶段分
function jbfLink(cellValue,rowObject){
	var html = "<input name='jbf' onkeyup='checkInputNum(this)' maxlength = '3' value = '"+cellValue+"'  data-jdwhid ='"+rowObject["jdwhid"]+"' onblur='saveThisRow(this)'  style='width:80%;' />";
	var obj = jQuery(html);
	return obj;
}

//导入阶段维护成员
function drjdwhcy(){
	
}

//修改
function saveThisRow(obj){
	var xh = jQuery(obj).attr("data-xh");
	var jdwhid = jQuery(obj).attr("data-jdwhid");
	var nameflag = obj.name;
	var inputvalue = obj.value;
    var jsonPara = null;
	if(nameflag == 'jbf'){
		if(jQuery.trim(inputvalue) == "" || inputvalue == null){
			showAlertDivLayer("阶段分不可为空!");
			return false;
		}
		if(parseFloat(inputvalue) > parseFloat(jQuery("#jdf").val())){
			showAlertDivLayer("阶段分上限为"+jQuery("#jdf").val()+"分!");
			return false;
		}
		 jsonPara = {jbf:inputvalue,jdwhid:jdwhid};
	}
	if(nameflag == 'bz'){
		if(inputvalue.length > 500){
			showAlertDivLayer("备注不得超过500字!");
			return false;
		}
		 jsonPara = {bz:inputvalue,jdwhid:jdwhid};
	}
	
	if(nameflag == 'hdsc'){
		 jsonPara = {hdsc:inputvalue,jdwhid:jdwhid};
	}
	
	jQuery.post("grttxm_jdsz.do?method=updateJdszCy",jsonPara, function(data) {
		if(data["message"] == '保存成功！'){
			//jQuery("#dataTable").reloadGrid();
		}else{
			showAlertDivLayer(data["message"]);
		}
		
		
	}, 'json');
	
}

//添加个人项目成员
function addGrcy(){
	 var xmdm = jQuery("#xmdm").val();
    var url = "grttxm_jdsz.do?method=getStu&jdid="+jQuery("#jdid").val()+"&xmdm="+xmdm;
    var title = "个人项目成员添加";
	showDialog(title, 770, 550, url);
}

//删除阶段维护成员
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("grttxm_jdsz.do?method=del",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//添加团体项目成员
function addTtcy(){
    var xmdm = jQuery("#xmdm").val();
    var url = "grttxm_jdsz.do?method=getTtcy&jdid="+jQuery("#jdid").val()+"&xmdm="+xmdm;
    var title = "团体项目成员添加";
	showDialog(title, 770, 550, url);
}

//进入导入页面方法
function drjdwhcy(){
	var url = "grttxm_jdsz.do?method=drPrepare";
	var title = "导入";
	showDialog(title, 770, 350, url);
}

//将文件名称赋值到input框
function attachfilename(obj){
	var filefullpath = getFullPath(obj);
//	jQuery(obj).parent().find("input[name='wjmc']").val(filefullpath);
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


//保存

	function  saveRr(){
		var url = "grttxm_jdsz.do?method=SaveDrForm";
		/**/
		if(!checkNotNull("xmdm")){
			showAlert("请将带<font color='red'>*</font>的项目填写完整！");
			return false;
		}
		if(jQuery("#drmb").val() == "" ||　jQuery("#drmb").val() == null){
			showAlert("请选择导入文件！");
			return false;
		}
		ajaxSubFormWithFun("JdwhSzForm", url, function(data) {
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
	   				      if(data["message"] == "导入失败,请仔细核对【error.xls】！"){
	   				    	  if(data["gid"] != "" && data["gid"] != null && data["gid"] != "null"){
	   				    		  jQuery("#errortr").show();
	   				    		  jQuery("#errora").attr("data_file","grttxm_jdsz.do?method=downloadFileError&filename="+data["gid"]);
	   				    		  
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




//下载
function downloadxzmb(){
	window.open("grttxm_jdsz.do?method=downloadFile");
}

//错误数据下载
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}


//自定义导出 功能
function exportConfigGr() {
	//DCCLBH导出功能编号,执行导出函数 
	var DCCLBH = "grttxm_jdsz.do?method=getXsGrxmCx";
	customExport(DCCLBH, grxmExportData);
}

//导出方法
function grxmExportData() {
	var DCCLBH = "grttxm_jdsz.do?method=getXsGrxmCx";
	setSearchTj();//设置高级查询条件
	var url = "grttxm_jdsz.do?method=exportDataGr&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//自定义导出 功能
function exportConfigTt() {
	//DCCLBH导出功能编号,执行导出函数 
	var DCCLBH = "grttxm_jdsz.do?method=getXsTtxmCx";
	customExport(DCCLBH, ttxmExportData);
}

//导出方法
function ttxmExportData() {
	var DCCLBH = "grttxm_jdsz.do?method=getXsTtxmCx";
	setSearchTj();//设置高级查询条件
	var url = "grttxm_jdsz.do?method=exportDataTt&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='SqjgView(\""
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//查看学生链接
function xssqLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='SqjgView(\""
			+ rowObject["xsjgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function SqjgView(jgid, xh) {
	showDialog("查看", 770, 450, "xmsqgl_xmjg.do?method=XmjgView&jgid="
			+ jgid + "&xh=" + xh);
}

//查看团队名称
function tdmcLink(cellValue, rowObject) {
	var jgid = rowObject["jgid"] || rowObject["xsjgid"] 
	return "<a href='javascript:void(0);' class='name' onclick='SqjgView(\""
			+ jgid + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function SqjgView(jgid, xh) {
	showDialog("查看", 770, 450, "xmsqgl_xmjg.do?method=XmjgView&jgid="
			+ jgid + "&xh=" + xh);
}

//查看团队链接
function ttsqLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='TtsqView(\""
			+ rowObject["ttjgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function TtsqView(ttjgid, tdmc) {
	showDialog("团体拓展项目结果查看", 770, 450, "ttxm_jg.do?method=TtsqView&ttjgid="
			+ ttjgid);
}

