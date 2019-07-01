//查询
function searchRs() {
	var map = {};
	map['xmlbmc'] =jQuery("#xmlbmc").val();
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

/**
 * 增加类别
 * @return
 */
function add(){
	showDialog("增加项目类别", 450, 300, "jskp_dmwh.do?method=addDmwh"
	);
}

/**
 * 修改类别
 * @return
 */
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else {
		var url = 'jskp_dmwh.do?method=editDmwh&xmlbdm=' + rows[0]['xmlbdm'];
		var title = "修改项目类别";
		showDialog(title, 450, 300, url);
	}
}

/**
 * 保存
 * @return
 */
function saveXmlbmc(){
	var url = "jskp_dmwh.do?method=saveDmwh";
	var xmlbmc = jQuery("#xmlbmc").val();
	//检验数据
	if(xmlbmc == null || jQuery.trim(xmlbmc) == ''){
		 showAlert("项目类别名称不能为空！");
		 return false;
	}
	ajaxSubFormWithFun("DmwhForm", url, function(data) {
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
 * 删除
 * @return
 */
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("jskp_dmwh.do?method=delXm",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}