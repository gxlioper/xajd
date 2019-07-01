/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

/**
 * 增加学籍异动类别
 * @return
 */
function add(){
	var url = "xjyd.do?method=xjydlbAdd";
	var title = "增加学籍异动类别";
	showDialog(title,470,220,url);
}

/**
 * 增加学籍异动类别审核配置
 * @return
 */
function addShpz(){
	var url = "xjyd.do?method=xjydlbShpzAdd";
	var title = "增加学籍异动类别审核配置";
	showDialog(title,580,330,url);
}

/**
 * 修改学籍异动类别
 * @return
 */
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else if(rows[0]["sfnz"]=='1') {
		showAlertDivLayer("系统内置类别不能修改！");
		return false;
	}else {
		var url = 'xjyd.do?method=xjydlbUpd&xjlbdm='+rows[0]["xjlbdm"];
		var title = "修改学籍异动类别";
		showDialog(title,470,220,url);
	}
}

/**
 * 修改学籍异动类别审核配置
 * @return
 */
function updateShpz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xjyd.do?method=xjydlbShpzUpd&xjlbdm='+rows[0]["xjlbdm"];
		var title = "修改学籍异动类别";
		showDialog(title,580,360,url);
	}
}


/**
 * 删除学籍异动类别
 * @return
 */
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else if(rows[0]["sfnz"]=='1') {
		showAlertDivLayer("系统内置类别不能删除！");
		return false;
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("xjyd.do?method=xjydlbDel",{values:ids.toString()},function(data){
					if(data["success"]=="false"){
						showAlertDivLayer("选中范围内有代码被使用，不允许删除");
					}else{
						showAlertDivLayer(data["message"]);
					}
					
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
	
}


/**
 * 删除学籍异动类别
 * @return
 */
function delShpz(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("xjyd.do?method=xjydlbShpzDel",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}