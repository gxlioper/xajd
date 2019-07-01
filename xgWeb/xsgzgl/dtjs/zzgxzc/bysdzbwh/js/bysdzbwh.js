var gridSetting = {
	caption:"毕业生党支部维护",
	pager:"pager",
	url:"dtjs_bysdzbwh.do?method=dzbwhList&type=query",
	colList:[
	   {label:'党支部代码',name:'dzbdm', index: 'dzbdm',width:'20%',key:true},
	   {label:'党支部名称',name:'dzbmc', index: 'dzbmc',width:'80%'}
	],
	sortname: "dzbdm",
 	sortorder: "asc"
}


//增加
function add(){
	var url = "dtjs_bysdzbwh.do?method=dzbwhAdd";
	var title = "增加党支部";
	showDialog(title,380,200,url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'dtjs_bysdzbwh.do?method=dzbwhUpdate&dzbdm='+rows[0]["dzbdm"];
		var title = "修改党支部";
		showDialog(title,380,200,url);
	}
}

//删除
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("dtjs_bysdzbwh.do?method=dzbwhDelete",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}

//查询
function query(){
	var map = {};
	map["dzbmc"] = jQuery("#dzbmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//保存
function saveForm(url){
	 if (!checkNull("dzbdm-dzbmc")) {
			 return false;
		}
     ajaxSubFormWithFun("bysdzbwhForm",url,function(data){
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

/**
 * 导入导出
 */
var DCGLBH = "dtjs_bysdzbwh.do";//dcglbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCGLBH, xshdglExportData);
}

//导出方法
function xshdglExportData() {
	//setSearchTj();//设置高级查询条件
	var url = "dtjs_bysdzbwh.do?method=dzbwhExport&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
	//url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_BYSDZBWH");
	return false;
}