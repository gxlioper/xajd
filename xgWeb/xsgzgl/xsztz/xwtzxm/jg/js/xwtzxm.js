/**
 * 查询
 * @return
 */
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add() {
	var url = "xwtzgl_xmjg.do?method=add";
	var title = "结果";
    showDialog(title, 770, 500, url);
	
}

//增加结果保存
function saveSqjg(type){
	var ids = "xh-xmmc-xmjbdm-xn-xq-sskmdm-zxf";
	if(check(ids) == false){ 
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	var url = "xwtzgl_xmjg.do?method=saveSqjg&type=" + type;
	ajaxSubFormWithFun("XwTzXmJgForm", url, function(data) {
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

function saveSqjgUpdate(type){
	if(jQuery("#xmmc").val() == ''){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	var url = "xwtzgl_xmjg.do?method=saveSqjg&type=" + type;
	ajaxSubFormWithFun("XwTzXmJgForm", url, function(data) {
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
	return "<a href='javascript:void(0);' class='name' onclick='SqjgView(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function SqjgView(sqid, xh) {
	showDialog("查看", 770, 450, "xwtzgl_xmjg.do?method=XmjgView&sqid="
			+ sqid + "&xh=" + xh);
}

//删除申请结果
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	}
	showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("xwtzgl_xmjg.do?method=delSqjl",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	
}


//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	}
		var url = 'xwtzgl_xmjg.do?method=editSqjg&sqid=' + rows[0]["sqid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "学生项目申请修改";
		showDialog(title, 770, 500, url);
}

var DCCLBH = "sztz_xwtzgl_xmjg.do";

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, xsxmsqJgExportData);
}

//导出方法
function xsxmsqJgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "xwtzgl_xmjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_XWXSTZXM");
	return false;
}



function checkzs(){
	if(jQuery("#sqly").val().length > 500){
		showAlertDivLayer("最大字数为500，现已经超过，请确认！！");
		return false;
	}
}

/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery.trim(jQuery("#"+id[i]).val());
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

