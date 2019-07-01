//增加
function add(){
	var url = "zjsy_sxhb.do?method=addSxhb";
	var title = "增加思想汇报信息";
	showDialog(title,800,350,url);
}

//修改一条记录
function mod(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'zjsy_sxhb.do?method=modSxhb&sxhbid='+rows[0]["sxhbid"]+'&xh='+rows[0]["xh"];
		var title = "修改思想汇报信息";
		showDialog(title,800,430,url);
	}
}


//删除
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("zjsy_sxhb.do?method=delSxhb",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//保存
function saveForm(method,type){
	
	 if (!checkNull("xh-sjfs-sjsj")) {
		 return false;
	 }
	 var url = "zjsy_sxhb.do?method="+method+"&type="+type;
     ajaxSubFormWithFun("zjsySxhbForm",url,function(data){
     	  if (data["success"] == "false"){
    		  showAlert("学生当前阶段不存在或者当天数据已存在" );
    	  }else{
    		  showAlert(data["message"],{},{"clkFun":function(){
        			if(parent.window){
     				 refershParent();
        			}
      		  }});
    	  }
     });
}

//导出
function exportConfig(){
	var DCCLBH='zjsy_sxhb.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH='zjsy_sxhb.do';
	setSearchTj();//设置高级查询条件
	var url = "zjsy_sxhb.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_N022505_SXHB");
	return false;
}