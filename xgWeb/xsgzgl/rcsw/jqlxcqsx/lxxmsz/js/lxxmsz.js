//查询
function searchRs() {
	var map = {};
	map['xmmc'] = encodeURI(encodeURI(jQuery("#xmmc").val()));
	gridSetting["params"] = map;
	jQuery("#dataTable").reloadGrid(map);
}

//回车事件
function keydown_search(keyEvent){
	  if(keyEvent.keyCode == 13){
		  searchRs();
	  }else{
		  return false;
	  }
}

//查看学生链接
function xmmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["xmid"] + "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function View(xmid) {
	showDialog("留校项目设置查看", 450, 300, "jqlx_lxxmsz.do?method=ckLxxmsz&xmid="
			+ xmid);
}

function add(){
	showDialog("增加留校项目设置", 450, 300, "jqlx_lxxmsz.do?method=addLxxmsz"
			);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else {
		var url = 'jqlx_lxxmsz.do?method=updateLxxmsz&xmid=' + rows[0]['xmid'];
		var title = "修改留校项目设置";
		showDialog(title, 450, 300, url);
	}
}

//保存结果
function saveSzjg(type){
	var url = "jqlx_lxxmsz.do?method=saveSzjg&type="+type;
	//检验数据
	if(!checkNotNull("xmmc-lxkssj-lxxmsm")){
		 showAlert("请将带<font class='red'>*</font>的项目填写完整！");
		 return false;
	}
	if(jQuery("#lxxmsm").val().length > 500){
		 showAlert("留校项目说明不能大于500字！");
		 return false;
	}
	ajaxSubFormWithFun("LxxmszForm", url, function(data) {
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

//删除设置结果
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("jqlx_lxxmsz.do?method=delSzjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "xg_rcsw_cqsx_jqlx_lxxmsz.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, xyzsjgExportData);
}

//导出方法
function xyzsjgExportData() {
	var url = "jqlx_lxxmsz.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


