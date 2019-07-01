var action="jxkqjg.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
//点击序号跳转
function xhLink(cellValue, rowObject) {
	var kqid = rowObject["kqid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + kqid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//查看信息
function ckxx(kqid) {
	var query=jQuery("#query").val();
	var url = action+"?method=showView&kqid=" + kqid;
	var title = "军训考勤信息";
	showDialog(title, 800, 400, url);
}
//申请
function add() {
		var url =action+"?method=add";
		var title = "军训考勤增加";
		showDialog(title, 800, 420, url);
		jQuery("#dataTable").reloadGrid();
}
//修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var xh=rows[0]["xh"];
			var url = action+'?method=update&xh='+xh+'&kqid=' + rows[0]["kqid"];
			var title = "修改军训考勤信息";
			showDialog(title, 800, 420, url);
			jQuery("#dataTable").reloadGrid();

	}
}
function save(url,checkId,sfjc){
	if(!check(checkId)){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	if(jQuery("#kqqk").val().length>500){
		return showAlert("考勤情况最多输入500字！");
		
	}
	lock();
	ajaxSubFormWithFun("JxkqjgForm", url, function(data) {
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
function chkNumInputForThis(obj){
	//这里是共用js 处理不同页面 有些对象不存在的问题
	if(null==obj||null==$(obj)){
		return false;
	}
	return chkNumInput(obj);
}
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
			//alert(id[i]);
			return false;
		}
	}
	return true;
}

//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
//导入
function dr() {
	// 调用通用的导入function，参数是导入功能模块代码。
	toImportDataNew("IMPORT_N450501_JXKQJG");
	return false;

}
//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport('jxgl_jxkqgl_kqjggl.do',exportData);
}

// 导出方法
function exportData(dcclbh) {
	setSearchTj();//设置高级查询条件
	var url = "jxkqjg.do?method=exportData&dcclbh=" + dcclbh;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}




