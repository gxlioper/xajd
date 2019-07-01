/**
 * @喻鑫源
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add() {
	var url = "gyglnew_gyjldmgl.do?method=addZjlydm";
	var title = "增加代码";
	showDialog(title, 470, 200, url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'gyglnew_gyjldmgl.do?method=updateZjlydm&gyjllbdldm=' + rows[0]["gyjllbdldm"];
		var title = "修改代码";
		showDialog(title, 470, 220, url);
	}
}

//增加修改结果保存
function saveFormjldm(type){
	var ids = "gyjllbdldm"+"-"+"gyjllbdlmc"+"-"+"lb"+"-"+"fz";
	if(checkNotNull(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = "gyglnew_gyjldmgl.do?method=saveZjlydm&type=" + type;
	ajaxSubFormWithFun("GyjldmglForm", url, function(data) {
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

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='dmView(\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function dmView(gyjllbdldm) {
	showDialog("代码查看", 470, 220, "gyglnew_gyjldmgl.do?method=ckZjlydm&gyjllbdldm="
			+ gyjllbdldm );
}
//删除住宿结果
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("gyglnew_gyjldmgl.do?method=delZjlydm",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}