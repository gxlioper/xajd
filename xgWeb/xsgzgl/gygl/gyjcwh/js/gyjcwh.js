/**
 * @author 喻鑫源 
 * @功能:住宿结果的js
 * @develop-date:2015-05-19
 * @lastupdate-date:2015-05-22
 */


function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add() {
	var url = "zjly_jcxxwh.do?method=add";
	var title = "公寓奖惩情况维护";
	showDialog(title, 770, 400, url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'zjly_jcxxwh.do?method=editJg&id=' + rows[0]["id"]
				+ '&xh=' + rows[0]["xh"];
		var title = "公寓奖惩情况修改";
		showDialog(title, 770, 400, url);
	}
}

//增加修改结果保存
function savejg(type){
	var ids = "xh"+"-"+"xn"+"-"+"xq"+"-"+"jcdm";
	if(checkNotNull(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = "zjly_jcxxwh.do?method=savejg&type=" + type;
	ajaxSubFormWithFun("GyjcWhForm", url, function(data) {
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
	return "<a href='javascript:void(0);' class='name' onclick='jgView(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function jgView(id, xh) {
	showDialog("公寓奖惩情况查看", 770, 400, "zjly_jcxxwh.do?method=jgView&id="
			+ id + "&xh=" + xh);
}
//删除住宿结果
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("zjly_jcxxwh.do?method=deljg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
var DCCLBH = "zjly_jcxxwh.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, jgExportData);
}

//导出方法
function jgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "zjly_jcxxwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_GYJCWH");
	return false;
}