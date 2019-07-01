function searchRs(){
	var map = getSuperSearch();
	var jtlb = jQuery("#jtlb").val();
	if (null != jtlb && jtlb != "") {
		map["jtlb"] = jtlb;
	}else{
		map["jtlb"] = "zc";
	}
	jQuery("#dataTable").reloadGrid(map);
}

function selectTab(obj, jtlb) {
	jQuery("#jtlb").val(jtlb);
	if (jtlb == "zc") {
		jQuery("#li_zc").css("display", "");
		jQuery("#li_gd").css("display", "none");
		jQuery("#bt").css("display", "");
		jQuery("#bt1").css("display", "none");
		var map = getSuperSearch();
		map["jtlb"]="zc";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_zc").css("display", "none");
		jQuery("#li_gd").css("display", "");
		jQuery("#bt1").css("display", "");
		jQuery("#bt").css("display", "none");
		var map = getSuperSearch();
		map["jtlb"]="gd";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

function addzcjtff(){
	var url = "jtff_jtmdwh.do?method=AddZcJtff";
	var title = "正常津贴教师名单维护";
	showDialog(title, 770, 450, url);
}

function addgdjtff(){
	var url = "jtff_jtmdwh.do?method=AddGdJtff";
	var title = "固定津贴教师名单维护";
	showDialog(title, 770, 450, url);
}


function savezcjt(type){
	var ids = "";
	if(type == "save"){
		ids = "zgh"+"-"+"gw";
	}else{
		ids = "gw";
	}
	if(!checkNotNull(ids)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	if(!checkzs()){
		return false;
	}
	var url = "";
	if(type == 'save'){
		 url = "jtff_jtmdwh.do?method=saveZcjt&type=" + type;
		
	}else{
		 url = "jtff_jtmdwh.do?method=saveZcjt&type=" + type;
	}
	ajaxSubFormWithFun("JtmdwhForm", url, function(data) {
		 if(data["message"]=="保存成功！"){
   		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
   	 }else{
   		 showAlert(data["message"]);
   		}
		});
}

function checkzs(){
	if(jQuery("#bz").val().length > 50){
		showAlertDivLayer("备注请限制在50字之内！");
		return false;
	}else{
		return true;
	}
}

function selectzghFirst(){
	jQuery(":input").not('#zgh').click(function(){
		if(jQuery.trim(jQuery("#zgh").val()) == ""){
			showAlert("请先选择教师！");
			return false;
		}
	});
}

function savegdjts(type){
	var ids = "";
	if(type == "save"){
		ids = "zgh"+"-"+"gdffje";
	}else{
		ids = "gdffje";
	}
	if(!checkNotNull(ids)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	if(!checkzs()){
		return false;
	}
	var url = "";
	if(type == 'save'){
		 url = "jtff_jtmdwh.do?method=saveGdjt&type=" + type;
		
	}else{
		 url = "jtff_jtmdwh.do?method=saveGdjt&type=" + type;
	}
	ajaxSubFormWithFun("JtmdwhForm", url, function(data) {
		 if(data["message"]=="保存成功！"){
   		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
   	 }else{
   		 showAlert(data["message"]);
   		}
		});
}

function initgdjtPara(){
	var zgh = jQuery.trim(jQuery("#zgh").val());
	var id = "";
	var gw = "";
	if(zgh != ""){
		var rs = null;
		var url = "jtff_util.do?method=getRywh";
		jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:'zgh='+zgh,
		async: false,
		success:function(result){
			if(result['id']==null||result['id']=="null"||result['id']=="" || result['id'] == "undifined"){
				
			}else{
				id = result['id'];
				gw = result['gw'];
			}
		 }
	    });
		  if(id == ""){
		    	showAlert("该教师不在正常津贴维护名单中!",{},{"clkFun":function(){
		    		jQuery("#savegdjt").hide();
					return false;
				}});
		    }else{
		    	jQuery("#id").val(id);
		    	jQuery("#gw").text(gw);
		    	jQuery("#savegdjt").show();
		    }
	}
}
   
	//正常津贴修改
	function updatezcjt(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要修改的记录！");
			return false;
		} else {
			var url = 'jtff_jtmdwh.do?method=UpdateZcJtff&id=' + rows[0]["id"]
					+ '&zgh=' + rows[0]["zgh"];
			var title = "正常津贴教师名单修改";
			showDialog(title, 770, 552, url);
		}
	}
	
	//固定津贴修改
	function updategdjt(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要修改的记录！");
			return false;
		} else {
			var url = 'jtff_jtmdwh.do?method=UpdateGdJtff&id=' + rows[0]["id"]
					+ '&zgh=' + rows[0]["zgh"];
			var title = "固定津贴教师名单修改";
			showDialog(title, 770, 552, url);
		}
	}
	
	//删除
	function delzcjt() {
		var ids = jQuery("#dataTable").getSeletIds();
		if (ids.length == 0){
			showAlertDivLayer("请选择您要删除的记录！");
		} else {
			var rows = jQuery("#dataTable").getSeletRow();
			showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("jtff_jtmdwh.do?method=DelZcjt",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}
	}
	
	//删除
	function delgdjt() {
		var ids = jQuery("#dataTable").getSeletIds();
		if (ids.length == 0){
			showAlertDivLayer("请选择您要删除的记录！");
		} else {
			var rows = jQuery("#dataTable").getSeletRow();
			showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("jtff_jtmdwh.do?method=DelGdjt",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}
	}
	
	//查看学生链接
	function xhLink(cellValue, rowObject) {
		return "<a href='javascript:void(0);' class='name' onclick='zcjtck(\""
				+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
				+ "</a>";
	}
	
	//查看学生链接
	function xhLink1(cellValue, rowObject) {
		return "<a href='javascript:void(0);' class='name' onclick='gdjtck(\""
				+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
				+ "</a>";
	}
	
	//正常津贴查看
	function zcjtck(id,zgh){
		showDialog("正常津贴教师名单查看", 650, 450, "jtff_jtmdwh.do?method=ZcjtCk&id="
				+ id + "&zgh=" + zgh);
	}
	
	
	//固定津贴查看
	function gdjtck(id,zgh){
		showDialog("固定津贴教师名单查看", 650, 450, "jtff_jtmdwh.do?method=GdjtCk&id="
				+ id + "&zgh=" + zgh);
	}
	
	var DCCLBH = "szdw_jtff_jtmdwh.do";
	//自定义导出 功能
	function exportConfig() {
		//DCCLBH导出功能编号,执行导出函数 
		if(jQuery("#jtlb").val() == 'zc'){
			DCCLBH = 'szdw_jtff_jtmdwh.do';
		}else{
			DCCLBH = 'szdw_jtff_jtmdwhs.do';
		}
		customExport(DCCLBH,ExportData);
	}

	//导出方法
	function ExportData() {
		setSearchTj();//设置高级查询条件
		var jtlb = "";
		if(jQuery("#jtlb").val() == 'zc'){
			jtlb = 'zc';
		}else{
			jtlb = 'gd';
		}
		var url = "jtff_jtmdwh.do?method=exportData&dcclbh=" + DCCLBH+"&jtlb="+jtlb;//dcclbh,导出功能编号
		url = addSuperSearchParams(url);//设置高级查询参数
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
	
	//导入
	function importConfig(){
		toImportDataNew("IMPORT_10026_JTMD");
		return false;
	}
