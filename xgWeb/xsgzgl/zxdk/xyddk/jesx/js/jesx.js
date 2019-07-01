//查询
function searchRs() {
	var map = {};
	map['xlccmc'] = encodeURI(encodeURI(jQuery("#xlccmc").val()));
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
function xlccLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["xlccdm"] + "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function View(xlccdm) {
	showDialog("贷款上限维护查看", 450, 250, "gjdk_jesx.do?method=jesxCk&xlccdm="
			+ xlccdm);
}

//贷款上限维护
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'gjdk_jesx.do?method=jesxPlwh&values=' + ids.toString();
		var title = "贷款上限维护";
		showDialog(title, 450, 300, url);
	}
}

//保存结果
function saveRs(){
	var url = "gjdk_jesx.do?method=saveRs";
	//检验数据
	var rs = true;
	jQuery("[name='jesx']").each(function(i,row){
		if(this.value == null || this.value == ""){
			rs = false;
			return rs;
		}
	})
	if(!rs){
		 showAlert("金额上限不可为空！");
		 return false;
	}
	ajaxSubFormWithFun("JesxForm", url, function(data) {
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