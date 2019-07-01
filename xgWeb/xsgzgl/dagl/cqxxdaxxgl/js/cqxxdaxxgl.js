//班级人数查看信息
function bjrsLink(cellValue, rowObject){
	if(cellValue == null) cellValue="0" ;
	return "<a href='javascript:void(0);' class='name' onclick='bjrsview(\""
	+ rowObject["bjid"] + "\",\"" + rowObject["bjdm"] + "\");'>" + cellValue
	+ "</a>";
}
function wwhrsLink(cellValue, rowObject){
	if(cellValue == null) cellValue="0";
	return cellValue;
}
function bjrsview(bjid, bjdm) {
	showDialog("查看", 770, 510, "cqxxDaxxgl.do?method=getXsView&bjdms="+bjdm+"&bjid="+bjid);
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
		var url = "cqxxDaxxgl.do?method=cqxxdaxxwh&bjdms="+rows[0]['bjdm']+"&bjid="+ids;
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

	var id ="sj_"+rowObject.xh;
	var html = "<input name='sj' id='sj_"+rowObject.xh
		+"' data-xsid ='"+rowObject["xsid"]+"' data-bjdm ='"+rowObject["bjdm"]
		+"' onchange='saveThisRow(this)' data-xh='"
		+rowObject["xh"]+"' style='width:98%;height:20px' />";
	var obj = jQuery(html);
	jQuery(obj).attr("onclick","showCalendar('"+id+"','y-mm-dd');");
	jQuery(obj).val(cellValue);
	return obj;
}


function bzLink(cellValue,rowObject){
	var html = "<textarea name='bz'  " +
			"data-xsid ='"+rowObject["xsid"]+"' data-bjdm ='"+rowObject["bjdm"]
			     +"' onblur='saveThisRow(this)' onkeyup='toNext(this,event);'  onblur='saveThisRow(this)'  data-xh='"+rowObject["xh"]+"' style='width:98%;height:30px' />";
	var obj = jQuery(html);
	jQuery(obj).val(cellValue);
	return obj;
}
function xjztLink(cellValue,rowObject){
	var html = "<select name='xjzt'  " +
			"data-xsid ='"+rowObject["xsid"]+"' data-bjdm ='"+rowObject["bjdm"]
			     +"' onblur='saveThisRow(this)' onkeyup='toNext(this,event);' data-xh='"+rowObject["xh"]+"' style='width:98%;height:30px' >";
	html += "<option value=''></option><option value='已毕业'>已毕业</option><option value='未达到毕业条件'>未达到毕业条件</option><option value='退学'>退学</option><option value='休学'>休学</option><option value='当兵'>当兵</option><option value='其它'>其它</option></select>"
	
	var obj = jQuery(html);
	jQuery(obj).val(cellValue);
	return obj;
}
function dahhLink(cellValue,rowObject){
	var html = "<input name='dahh' id='dahh_"+rowObject.xh+"' data-xsid ='"+rowObject["xsid"]+"' data-bjdm ='"+rowObject["bjdm"]+"' onkeyup='toNext(this,event);'  onblur='saveThisRow(this)'  data-xh='"+rowObject["xh"]+"' style='width:98%;height:20px' />";
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
	var dahh = jQuery(obj).parent().parent().find("[name='dahh']").val();
	var xjzt = jQuery(obj).parent().parent().find("[name='xjzt']").val();
	if(xjzt != "已毕业" && dahh == "" && xjzt != ""){
		showAlertDivLayer("档案盒号不能为空!");
		return false;
	}
	//手动填写参数
	var dh = jQuery(obj).parent().parent().find("[name='dh']").val();
	var dz = jQuery(obj).parent().parent().find("[name='dz']").val();
	var yjr = jQuery(obj).parent().parent().find("[name='yjr']").val();
	var sj = jQuery(obj).parent().parent().find("[name='sj']").val();
	var bz = jQuery(obj).parent().parent().find("[name='bz']").val();
//------------------
	var xh = jQuery(obj).attr("data-xh");
	var bjdm = jQuery(obj).attr("data-bjdm");
	var xsid = jQuery(obj).attr("data-xsid");
	var bjid = jQuery("#bjid").val();
	var nameflag = obj.name;
	if(nameflag == 'bz'){
		bz = obj.value;
		if(jQuery.trim(bz).length > 100){
			showAlertDivLayer("备注不得超过100字！");
			jQuery(obj).val("");
			return false;
		}

	}else if(nameflag == 'dh'){
		dh = obj.value;
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
		if(jQuery.trim(yjr).length > 10){
			showAlertDivLayer("邮寄人信息过长，请重新输入！");
			jQuery(obj).val("");
			return false;
		}
		if(jQuery.trim(yjr) == ""){
			return false;
		}
	
/*	}else if(nameflag == 'sj'){
		sj = obj.value;
//		var p = /^\d{8}$/; 
		var p = /^(\d{4})-(\d{2})-(\d{2})$/; 
		if(!p.test(sj)&&sj!=""){
			showAlertDivLayer("日期格式不符合要求(yyyy-MM-dd)!");
			jQuery(obj).val("");
			return false;
		}
		if(jQuery.trim(sj) == ""){
			return false;
		}*/
	}else if(nameflag == 'dahh'){
		dahh = obj.value;
		if(jQuery.trim(dahh).length > 30){
			showAlertDivLayer("档案盒号过长，请重新输入！");
			jQuery(obj).val("");
			return false;
		}
		if(jQuery.trim(yjr) == ""){
			return false;
		}
	}else if(nameflag == 'xjzt'){
		xjzt = obj.value;
	}
	
		jQuery.post("cqxxDaxxgl.do?method=saveData", {
			xh : xh,
			bjdm : bjdm,
			kddh:dh,
			yjdz:dz,
			yjr:yjr,
			sj:sj,
			bz:bz,
			dahh:dahh,
			xjzt:xjzt
		}, function(data) {
			if(data['message'] == '保存成功！'){
				jQuery("#dataTable").reloadGrid();
				//window.location.reload();
				getWhrs(bjdm);
			}
		}, 'json');
}

function getWhrs(bjdm){
	jQuery.post("cqxxDaxxgl.do?method=getwhrs", {
		bjdm : bjdm
	}, function(data) {
		jQuery("#ywhs").html(data['ywh']);
		jQuery("#wwhs").html(data['wwh']);
	
	}, 'json');
	
	
}
/* 导入 */
function drDaxxgl(){
	toImportDataNew("IMPORT_CQXX_DAXXJG");
	return false;
}

//*********************维护导出相关 ********************************
function exportWH() {
	customExport('cqxxdaxxgl.do', exportDataWH);
}
function exportDataWH(){
	setSearchTj();// 设置高级查询条件
	var url = "cqxxDaxxgl.do?method=exportDataWH&dcclbh=" + 'cqxxdaxxgl.do';// dcclbh,导出功能编号,数据表字段
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//*********************结果导出相关 ********************************
function exportXX() {
	customExport('cqxxdaxxgljg.do', exportData);
}
function exportData(){
	setSearchTj();
	var url = "cqxxDaxxjg.do?method=exportData&dcclbh=" + 'cqxxdaxxgljg.do';
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//单个学生信息查看
function viewDajg(daxxid,cellValue){
	showDialog('学生档案信息',800,420,'cqxxDaxxjg.do?method=dajgView&daxxid='+daxxid+"&xh="+cellValue,null);
}

