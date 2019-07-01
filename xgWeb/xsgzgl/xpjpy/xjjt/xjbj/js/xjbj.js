/**
 * 查询
 * @return
 */
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 增加
 * @return
 */
function add(){
	var url = "xpjpy_xjbj.do?method=addXjbj";
	var title = "增加先进班级信息";
	showDialog(title,600,300,url);
}


/**
 * 修改
 * @return
 */
function updateXjbj(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xpjpy_xjbj.do?method=updateXjbj&guid='+rows[0]["guid"];
		var title = "修改先进班级信息";
		showDialog(title,600,300,url);
	}
}



/**
 * 保存
 * @return
 */
function saveForm(){
	var xn = jQuery("#xn").val();
	var bjmc = jQuery("#bjmc").val();
	var lrsj = jQuery("#lrsj").val();
	var type = jQuery("#type").val();
	var guid = jQuery("#guid").val();
	
	if("" == lrsj){
		showAlert("录入时间不能为空");
		return false;
	}
	if("" == xn){
		showAlert("学年不能为空");
		return false;
	}
	if("" == bjmc){
		showAlert("先进班级名称不能为空");
		return false;
	}
	
	var url = "xpjpy_xjbj.do?method=saveForm&type="+type+"&guid="+guid;
    ajaxSubFormWithFun("xjbjForm",url,function(data){
  	  
  	  if (data["success"] == "false"){
  		  var msg = "该班级在"+jQuery.trim(xn)+"学年，";
  		  msg += "已获得先进班级称号。";
  		  showAlert(msg);
  	  } else {
  		  showAlert(data["message"],{},{"clkFun":function(){
      			if (parent.window){
   				 refershParent();
      			}
		  }});
  	  }
    });
}
//删除记录
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xpjpy_xjbj.do?method=delXjbj",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
/* 导入 */
function importXjbj(){
	toImportDataNew("IMPORT_XPJPY_XJBJ");
	return false;
}

//导出
var DCCLBH='xpjpy_xjbj.do';
function exportConfig(){
	
	customExport(DCCLBH, exportData);
}
function exportData(){
	setSearchTj();//设置高级查询条件
	var url = "xpjpy_xjbj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
