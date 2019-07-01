//班级人数查看信息
function bjrsLink(cellValue, rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='bjrsview(\""
	+ rowObject["bjid"] + "\",\"" + rowObject["bjdm"] + "\");'>" + cellValue
	+ "</a>";
}
function bjrsview(bjid, bjdm) {
	showDialog("查看", 770, 510, "sxDaxxgl.do?method=getXsView&bjdms="+bjdm+"&bjid="+bjid);
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
//---------------维护
function wh(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length !=1) {
		alertInfo("请选择一条您要维护的记录！");
	
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		var url = "sxDaxxgl.do?method=sxdaxxwh&bjdms="+rows[0]['bjdm']+"&bjid="+ids;
		document.location.href = url;
	}
}

function doQuery(){
	var map = {};
	map['bjdms']=jQuery("#bjdms").val();
	map['xh']=jQuery('#xh').val();
	map['ywh']=jQuery('#ywh').val();
	jQuery("#dataTable").reloadGrid(map);
}	
//表单填写数据
function dhLink(cellValue,rowObject){
	var html = "<input name='dh' onkeyup='toNext(this,event);'  data-xsid ='"+rowObject["xsid"]+"' data-bjdm ='"+rowObject["bjdm"]+"' onblur='saveThisRow(this)' data-xh='"+rowObject["xh"]+"' style='width:98%;height:20px' />";
	var obj = jQuery(html);
	jQuery(obj).val(cellValue);
	return obj;
}
function dzLink(cellValue,rowObject){
	var html = "<input name='dz' onkeyup='toNext(this,event);'  data-xsid ='"+rowObject["xsid"]+"' data-bjdm ='"+rowObject["bjdm"]+"' onblur='saveThisRow(this)' data-xh='"+rowObject["xh"]+"' style='width:98%;height:20px' />";
	var obj = jQuery(html);
	jQuery(obj).val(cellValue);
	return obj;
}
function yjrLink(cellValue,rowObject){
	var html = "<input name='yjr' onkeyup='toNext(this,event);'  data-xsid ='"+rowObject["xsid"]+"' data-bjdm ='"+rowObject["bjdm"]+"' onblur='saveThisRow(this)' data-xh='"+rowObject["xh"]+"' style='width:98%;height:20px' />";
	var obj = jQuery(html);
	jQuery(obj).val(cellValue);
	return obj;
}
function sjLink(cellValue,rowObject){
	var html = "<input name='sj' id='sj_"+rowObject.xh+"' data-xsid ='"+rowObject["xsid"]+"' data-bjdm ='"+rowObject["bjdm"]+"' onblur='saveThisRow(this)' data-xh='"+rowObject["xh"]+"' style='width:98%;height:20px' />";
	var obj = jQuery(html);
	jQuery(obj).val(cellValue);
	return obj;
}


function bzLink(cellValue,rowObject){
	var html = "<textarea name='bz'  " +
			"data-xsid ='"+rowObject["xsid"]+"' data-bjdm ='"+rowObject["bjdm"]
			     +"' onblur='saveThisRow(this)' onkeyup='toNext(this,event);' data-xh='"+rowObject["xh"]+"' style='width:98%;height:30px' />";
	var obj = jQuery(html);
	jQuery(obj).val(cellValue);
	return obj;
}

/**
 * 输入框 上、下、左、右键事件
 * @param obj
 */
function toNext(obj,event){
	var event = event || window.event;
	//左   
	if (event.keyCode==37){
		var inputs = jQuery("#dataTable input:not(:checkbox)");
		var index = inputs.index(jQuery(obj));
		inputs.eq(index-1).select();
	}
	
	//上      
	if (event.keyCode==38){
		var tr = jQuery(obj).parents("tr");
		var index = jQuery("input:not(:checkbox)",tr).index(jQuery(obj));
		jQuery("input:not(:checkbox)",tr.prev()).eq(index).select();
	}
	
	//右   或者 回车
	if (event.keyCode==39 || event.keyCode==13){
		var inputs = jQuery("#dataTable input:not(:checkbox)");
		var index = inputs.index(jQuery(obj));
		inputs.eq(index+1).select();
	}
	
	//下 
	if (event.keyCode==40){
		var tr = jQuery(obj).parents("tr");
		var index = jQuery("input:not(:checkbox)",tr).index(jQuery(obj));
		jQuery("input:not(:checkbox)",tr.next()).eq(index).select();
	}
	
}

//保存数据
function saveThisRow(obj){
	//手动填写参数
	var dh = "";
	var dz = "";
	var yjr = "";
	var sj = "";
	var bz = "";
//------------------
	var xh = jQuery(obj).attr("data-xh");
	var bjdm = jQuery(obj).attr("data-bjdm");
	var xsid = jQuery(obj).attr("data-xsid");
	var bjid = jQuery("#bjid").val();
	var nameflag = obj.name;
	if(nameflag == 'bz'){
		bz = obj.value;
		dh = jQuery(obj).parent().parent().find("[name='dh']").val();
		dz = jQuery(obj).parent().parent().find("[name='dz']").val();
		yjr = jQuery(obj).parent().parent().find("[name='yjr']").val();
		sj = jQuery(obj).parent().parent().find("[name='sj']").val();
		if(jQuery.trim(bz).length > 100){
			showAlertDivLayer("备注不得超过100字！");
			jQuery(obj).val("");
			return false;
		}

	}else if(nameflag == 'dh'){
		dh = obj.value;
		dz = jQuery(obj).parent().parent().find("[name='dz']").val();
		yjr = jQuery(obj).parent().parent().find("[name='yjr']").val();
		sj = jQuery(obj).parent().parent().find("[name='sj']").val();
		bz = jQuery(obj).parent().parent().find("[name='bz']").val();

		if(jQuery.trim(dh).length > 30){
			showAlertDivLayer("单号长度不得超过30位，请重新输入！");
			jQuery(obj).val("");
			return false;
		}
		if(jQuery.trim(dh) == ""){
			return false;
		}
	}else if(nameflag == 'dz'){
		dz = obj.value;
		dh = jQuery(obj).parent().parent().find("[name='dh']").val();
		yjr = jQuery(obj).parent().parent().find("[name='yjr']").val();
		sj = jQuery(obj).parent().parent().find("[name='sj']").val();
		bz = jQuery(obj).parent().parent().find("[name='bz']").val();

		if(jQuery.trim(dz).length > 50){
			showAlertDivLayer("地址信息过长，请重新输入！");
			jQuery(obj).val("");
			return false;
		}
		if(jQuery.trim(dz) == ""){
			return false;
		}
	
	}else if(nameflag == 'yjr'){
		yjr = obj.value;
		dh = jQuery(obj).parent().parent().find("[name='dh']").val();
		dz = jQuery(obj).parent().parent().find("[name='dz']").val();
		sj = jQuery(obj).parent().parent().find("[name='sj']").val();
		bz = jQuery(obj).parent().parent().find("[name='bz']").val();

		if(jQuery.trim(yjr).length > 10){
			showAlertDivLayer("邮寄人信息过长，请重新输入！");
			jQuery(obj).val("");
			return false;
		}
		if(jQuery.trim(yjr) == ""){
			return false;
		}
	
	}else if(nameflag == 'sj'){
		sj = obj.value;
		dh = jQuery(obj).parent().parent().find("[name='dh']").val();
		dz = jQuery(obj).parent().parent().find("[name='dz']").val();
		yjr = jQuery(obj).parent().parent().find("[name='yjr']").val();
		bz = jQuery(obj).parent().parent().find("[name='bz']").val();
	
//		var p = /^\d{8}$/; 
		var p = /^(\d{4})-(\d{2})-(\d{2})$/; 
		if(!p.test(sj)&&sj!=""){
			showAlertDivLayer("日期格式不符合要求(yyyy-MM-dd)!");
			jQuery(obj).val("");
			return false;
		}
		if(jQuery.trim(sj) == ""){
			return false;
		}
	}

		jQuery.post("sxDaxxgl.do?method=saveData", {
			xh : xh,
			bjdm : bjdm,
			kddh:dh,
			yjdz:dz,
			yjr:yjr,
			sj:sj,
			bz:bz
		}, function(data) {
			if(data['message'] == '保存成功！'){
				jQuery("#dataTable").reloadGrid();
				//window.location.reload();
				getWhrs(bjdm);
			}
		}, 'json');
}

function getWhrs(bjdm){
	jQuery.post("sxDaxxgl.do?method=getwhrs", {
		bjdm : bjdm
	}, function(data) {
		jQuery("#ywhs").html(data['ywh']);
		jQuery("#wwhs").html(data['wwh']);
	
	}, 'json');
	
	
}

/* 导入 */
function importXX(){
	toImportDataNew("IMPORT_N710502_DAJG");
	return false;
}


//*********************维护导出相关 ********************************
function exportWH() {
	customExport('sxdaxxgl.do', exportDataWH);
}
function exportDataWH(){
	setSearchTj();// 设置高级查询条件
	var url = "sxDaxxgl.do?method=exportDataWH&dcclbh=" + 'sxdaxxgl.do';// dcclbh,导出功能编号,数据表字段
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//*********************结果导出相关 ********************************
function exportXX() {
	customExport('sxdaxxgljg.do', exportData);
}
function exportData(){
	setSearchTj();
	var url = "sxDaxxjg.do?method=exportData&dcclbh=" + 'sxdaxxgljg.do';
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//单个学生信息查看
function viewDajg(daxxid,cellValue){
	showDialog('学生档案信息',800,420,'sxDaxxjg.do?method=dajgView&daxxid='+daxxid+"&xh="+cellValue,null);
}

