function queryXmmk(){
	var map = {};
	map["cxzd"] = jQuery("#cxzd").val();
	jQuery("#dataTable").reloadGrid(map);
}

function addXmmk(){
	var url = "zhf_xmmkwh.do?method=addZhfXmmk";
	var title = "增加项目模块";
	showDialog(title, 500, 350, url);
}

//保存项目模块
function saveXmmk(type){
	var xmmkdm = jQuery("#xmmkdm").val();
	var xmmkmc = jQuery("#xmmkmc").val();
	var xf = jQuery("#xf").val();
	var hgf = jQuery("#hgf").val();
	if(xmmkdm == null || xmmkdm == '' || xmmkmc == null || xmmkmc == '' || xf == null || xf == '' || hgf == null || hgf == ''){
		showAlertDivLayer("请填写必填项！");
		return false;
	}
	if(Number(hgf)>Number(xf)){
		showAlertDivLayer("合格分不能大于总分");
		return false;
	}
	var url = "zhf_xmmkwh.do?method=saveXmmk&type=" + type;
	ajaxSubFormWithFun("myForm", url, function(data) {
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

//修改项目模块
function updateXmmk(){
	var rows = jQuery('#dataTable').getSeletRow();
	if(rows.length!=1){
		showAlertDivLayer("请选择一条记录！");
		return false;
	}
	var xmmkdm = rows[0]["xmmkdm"];
	var url = "zhf_xmmkwh.do?method=updateZhfXmmk&xmmkdm="+xmmkdm;
	var title = "修改";
	showDialog(title, 500, 350, url);
}

//删除项目模块
function delXmmk(){
	var rows = jQuery('#dataTable').getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	}
	showConfirmDivLayer("您确定要删除选择的记录吗？", {
		"okFun" : function() {
			jQuery.post("zhf_xmmkwh.do?method=delZhfXmmk", {
				values : ids.toString()
			},
					function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
		}
	});	
}

//切换Tab页
function selectTab(obj, xmlx) {
	//jQuery("#shzt").val(shzt);
	if (xmlx == "xmmk") {
		jQuery("#xmmk").css("display", "");
		jQuery("#xmmkGjcx").css("display", "");		
		jQuery("#jfxm").css("display", "none");
		jQuery("#jfxmGjcx").css("display", "none");
		//var map = getSuperSearch();
		//map["shzt"]="dsh";
		//gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#xmmk").css("display", "none");
		jQuery("#xmmkGjcx").css("display", "none");	
		jQuery("#jfxm").css("display", "");
		jQuery("#jfxmGjcx").css("display", "");		
		//var map = getSuperSearch();
		//map["shzt"]="ysh";
		//gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

function searchRs() {
	var map = getSuperSearch();	
	jQuery("#dataTable").reloadGrid(map);	
}

//增加计分项目
function addJfxm() {
	var url = "zhf_jfxmwh.do?method=addZhfJfxm";
	var title = "增加计分项目";
	showDialog(title, 600, 350, url);
}

//保存计分项目
function saveJfxm(type) {
	var xmmkdm = jQuery("#xmmkdm").val();
	var jfxmmc = jQuery("#jfxmmc").val();
	var khyd = jQuery("#khyd").val();
	var fs = jQuery("#fs").val();
	var sfbt = jQuery("input[name='sfbt']:checked").val();
	var sfxf = jQuery("input[name='sfxf']:checked").val();
	if(sfxf == '1'){
		var xdfs = jQuery("#xdfs").val();
		if(xdfs == '' || xdfs == null){
			showAlertDivLayer("请填写必填项！");
			return false;
		}
	}
	if(xmmkdm == null || xmmkdm == '' || jfxmmc == null || jfxmmc == '' || khyd == null || khyd == '' || sfbt == null || sfbt == '' || sfxf == null || sfxf == ''){
		showAlertDivLayer("请填写必填项！");
		return false;
	}
	var url = "zhf_jfxmwh.do?method=saveJfxm&type=" + type;
	ajaxSubFormWithFun("myForm", url, function(data) {
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

//修改计分项目
function updateJfxm(){
	var rows = jQuery('#dataTable').getSeletRow();
	if(rows.length!=1){
		showAlertDivLayer("请选择一条记录！");
		return false;
	}
	var jfxmdm = rows[0]["jfxmdm"];
	var url = "zhf_jfxmwh.do?method=updateZhfJfxm&jfxmdm="+jfxmdm;
	var title = "修改";
	showDialog(title, 600, 350, url);
}

//显示限分
function xs(obj){
	var sfbt = jQuery(obj).val();
	if(sfbt=='1'){
		jQuery("#xdfstr").css("display","");
	}else{
		jQuery("#xdfstr").css("display","none");
	}
}

//删除项目模块
function delJfxm(){
	var rows = jQuery('#dataTable').getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	}
	showConfirmDivLayer("您确定要删除选择的记录吗？", {
		"okFun" : function() {
			jQuery.post("zhf_jfxmwh.do?method=delZhfJfxm", {
				values : ids.toString()
			},
					function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
		}
	});	
}

//部门授权
function bmsq(){
	var rows = jQuery('#dataTable').getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要授权的记录！");
		return false;
	}
	var jfxms = ids.toString();
	var url = "zhf_jfxmwh.do?method=getBmList&jfxms="+jfxms;
	var title = "项目授权";
	showDialog(title, 700, 550, url);	
}

//切换Tab页
function selectSqxm(obj, shzt) {
	jQuery("#cxzt").val(shzt);
	if (shzt == "wsq") {
		document.getElementById("bcsq").style.display='block';
		document.getElementById("qxsq").style.display='none';
		//var map = getSuperSearch();
		//map["shzt"]="dsh";
		//gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		document.getElementById("qxsq").style.display='block';
		document.getElementById("bcsq").style.display='none';
		//var map = getSuperSearch();
		//map["shzt"]="ysq";
		//gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

//授权
function sq(){
	var jfxmss = jQuery("#jfxms").val();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要授权的部门！");
		return false;
	}
	var bmdms = ids.toString();
	showConfirmDivLayer("您确定要授权以上部门吗？", {
		"okFun" : function() {
			jQuery.post("zhf_jfxmwh.do?method=saveSq", {
				bmdms : ids.toString(),
				jfxms : jfxmss
			},
			function(data){
				if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	}
			}, 'json');
		}
	});
}
//取消授权
function qx(){
	var jfxmss = jQuery("#jfxms").val();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要取消授权的部门！");
		return false;
	}
	var bmdms = ids.toString();
	showConfirmDivLayer("您确定要取消授权以上部门吗？", {
		"okFun" : function() {
			jQuery.post("zhf_jfxmwh.do?method=cancelSq", {
				bmdms : ids.toString(),
				jfxms : jfxmss
			},
			function(data){
				if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	}
			}, 'json');
		}
	});
}

//搜索部门
function queryBm(){
	var cxmc = jQuery("#cxmc").val();
	var cxzd = jQuery("#cxzt").val();
	var map = {};
	map["cxmc"] = cxmc;
	map["cxzd"] = cxzd;
	jQuery("#dataTable").reloadGrid(map);
}

//兼得设置
function jdsz() {
	var url = "zhf_jfxmwh.do?method=jdsz";
	var title = "兼得设置";
	showDialog(title, 450, 300, url);
}

//保存兼得设置
function saveJdsz() {
	var jdsz = jQuery("#jdszContent").val();
	if(jdsz == null || jdsz == ""){
		showAlertDivLayer("请填写兼得设置！");
		return false;
	}else{
		var url = "zhf_jfxmwh.do?method=saveJdsz";
		ajaxSubFormWithFun("myForm", url, function(data) {
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
}

var DCCLBH = "zjly_zhf_jfxmwh.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, jfxmExportData);
}

//导出方法
function jfxmExportData() {
	setSearchTj();//设置高级查询条件
	var url = "zhf_jfxmwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

