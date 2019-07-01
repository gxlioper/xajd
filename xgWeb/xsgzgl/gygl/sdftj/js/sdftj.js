//查询
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add() {
	var url = "gygl_sdftj.do?method=addSdfTj";
	var title = "增加水电费维护";
	showDialog(title, 700, 450, url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'gygl_sdftj.do?method=editSdfTj&id=' + rows[0]["id"];
		var title = "修改水电费维护";
		showDialog(title, 700, 450, url);
	}
}

//删除住宿结果
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("gygl_sdftj.do?method=deljg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "xg_gygl_sdftj.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH,dcExportData);
}

//导出方法
function dcExportData() {
	setSearchTj();//设置高级查询条件
	var url = "gygl_sdftj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//增加修改结果保存
function saveData(type){
	var ids = "nd"+"-"+"jd"+"-"+"lddm"+"-"+"qsh"+"-"+"sf"+"-"+"df";
	if(checkNotNull(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = "gygl_sdftj.do?method=saveData&type=" + type;
	ajaxSubFormWithFun("sdfTjForm", url, function(data) {
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

//楼栋切换
function lddmChange(){
	jQuery("#lddm").change(function(){
		if(this.value == null || this.value == '' ){
			jQuery("#ch").empty();
			jQuery("#qsh").empty();
			return false;
		}
		var data = getData("ld");
    	var dataList = data["dataList"];
    	var flag = data['message'];
    	jQuery("#ch").empty();
		jQuery("#qsh").empty();
		if(flag && flag === 'true'){
			var html = "<option></option>";
			for(var i = 0;i < dataList.length;i++){
				html += "<option value='"+dataList[i]['ch']+"'>"+dataList[i]['ch']+"</option>";
			}
			jQuery("#ch").append(html);
		}
		
	})
}

//层次切换
function chChange(){
    jQuery("#ch").change(function(){
    	if(this.value == null || this.value == '' ){
			jQuery("#qsh").empty();
			return false;
		}
    	var data = getData("cc");
    	var dataList = data["dataList"];
    	var flag = data['message'];
    	jQuery("#qsh").empty();
		if(flag && flag === 'true'){
			var html = "<option></option>";
			for(var i = 0;i < dataList.length;i++){
				html += "<option value='"+dataList[i]['qsh']+"'>"+dataList[i]['qsh']+"</option>";
			}
			jQuery("#qsh").append(html);
		}
		
	})
}

//获取下拉框需要的数据
function getData(type){
	var url = "gygl_sdftj.do?method=changeSelect&type="+type;
	var data = null;
	var para = {lddm:jQuery("#lddm").val(),ch:jQuery("#ch").val()};
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:para,
		async: false,
		success:function(result){
		   data = result;
		}
		
	});
	return data;
}

//查看
function ck(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要查看的记录！");
	} else {
		var url = 'gygl_sdftj.do?method=ckSdfTj&id=' + rows[0]["id"];
		var title = "修改水电费维护";
		showDialog(title, 700, 450, url);
	}
}

//导入
function dr() {
	// 调用通用的导入function，参数是导入功能模块代码。
	toImportDataNew("IMPORT_GYGL_SDFTJ");
	return false;

}
