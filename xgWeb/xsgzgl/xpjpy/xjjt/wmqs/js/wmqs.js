//查询
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function add(){
	var url = "xpjpy_wmqs.do?method=addWmqs";
	var title = "增加文明寝室信息";
	showDialog(title,575,253,url);
}
//修改一条记录
function updateWmqs (){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xpjpy_wmqs.do?method=updateWmqs&guid='+rows[0]["guid"];
		var title = "修改文明寝室信息";
		showDialog(title,575,253,url);
	}
}


//保存
function saveForm(){
	var xn = jQuery("#xn").val();
	var xqmc = jQuery("#xqmc").val();
	var ldmc = jQuery("#ldmc").val();
	var qsh = jQuery("#qsh").val();
	var type = jQuery("#type").val();
	var guid = jQuery("#guid").val();
	if("" == xqmc){
		showAlert("校区不能为空");
		return false;
	}
	if("" == xn){
		showAlert("学年不能为空");
		return false;
	}
	if("" == qsh){
		showAlert("寝室号为空");
		return false;
	}
	if("" == ldmc){
		showAlert("楼栋不能为空不能为空");
		return false;
	}
	 var url = "xpjpy_wmqs.do?method=saveForm&type="+type+"&guid="+guid;
    ajaxSubFormWithFun("wmqsForm",url,function(data){
  	  
  	  if (data["success"] == "false"){
  		  if(data["message"] == "0"){
  			showAlert("请填写正确的楼栋！");
  		  }else{
	  		  var msg = "该班级在"+jQuery.trim(xn)+"学年，";
	  		  msg += "已获得文明寝室称号。";
	  		  showAlert(msg);
	  	  }
  		  
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
function delWmqs(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xpjpy_wmqs.do?method=delWmqs",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
/* 导入 */
function importWmqs(){
	toImportDataNew("IMPORT_XPJPY_WMQS");
	return false;
}

//导出
var DCCLBH='xpjpy_wmqs.do';
function exportConfig(){
	
	customExport(DCCLBH, exportData);
}
function exportData(){
	setSearchTj();//设置高级查询条件
	var url = "xpjpy_wmqs.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function changeXmmc(obj){
	var objvalue = obj.value;
	alert(objvalue);
}