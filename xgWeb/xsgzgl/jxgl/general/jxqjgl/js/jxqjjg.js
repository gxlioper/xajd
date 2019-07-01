

var action="jxqjjg.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
//点击序号跳转
function dcmcLink(cellValue, rowObject) {
	var qjid = rowObject["qjid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + qjid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//查看信息
function ckxx(qjid) {
	var query=jQuery("#query").val();
	var url = action+"?method=showView&qjid=" + qjid;
	var title = "军训请假信息";
	showDialog(title, 800, 500, url);
}
//申请
function add() {
		var url =action+"?method=add";
		var title = "军训请假增加";
		showDialog(title, 800, 500, url);
		jQuery("#dataTable").reloadGrid();
}
//附件格式验证
function postfixCheck(){
	var wjm=jQuery("#formfile").val();
	if(wjm==""||wjm==null){
		return true;
	}
	var wjms=wjm.split(".");
	var hz=",bmp,jpg,jpeg,gif,png,pdf,doc,BMP,JPG,JPEG,GIF,PNG,PDF,DOC";
	if(hz.indexOf(wjms[wjms.length-1])<0){
		return false;
	}
	return true;
}
function save(url,checkId,sfjc){
	var jxkssj=jQuery("#jxkssj").val();
	var jxjssj=jQuery("#jxjssj").val();
	if(!check(checkId)){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	if(!checkQjsj(jxkssj,jxjssj)){
		return showAlert("请假时间不在军训时间"+jxkssj+"-"+jxjssj+"内！");
	}
	if(!postfixCheck()){
		return showAlert("请上传支持的附件格式！");
	}
	

	lock();
	var qjts=jQuery("#qjts").val();

	ajaxSubFormWithFun("JxqjjgForm", url, function(data) {
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
//判断请假时间是否在军训期间
function checkQjsj(jxkssj,jxjssj){
	var qjkssj=jQuery("#qjkssj").val().replace(/-/g,"");
	var qjjssj=jQuery("#qjjssj").val().replace(/-/g,"");
	var jxkssj=jQuery("#jxkssj").val().replace(/-/g,"");
	var jxjssj=jQuery("#jxjssj").val().replace(/-/g,"");
	if(null!=jxkssj&&""!=jxkssj&&""!=jxjssj&&null!=jxjssj&&(qjkssj<jxkssj||qjjssj>jxjssj)){
		return false;
	}
	return true;
	
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
//修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var xh=rows[0]["xh"];
		var sjly=rows[0]["sjly"];
		if(1==sjly||"1"==sjly){
			showAlertDivLayer("流程数据不能修改!");
		}else{
			var url = action+'?method=update&xh='+xh+'&qjid=' + rows[0]["qjid"];
			var title = "修改军训请假信息";
			showDialog(title, 800, 500, url);
			jQuery("#dataTable").reloadGrid();
		}
	}
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
	toImportDataNew("IMPORT_N450401_JXQJJG");
	return false;

}
//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport('jxgl_jxqjgl_qjjggl.do',exportData);
}

// 导出方法
function exportData(dcclbh) {
	setSearchTj();//设置高级查询条件
	var url = "jxqjjg.do?method=exportData&dcclbh=" + dcclbh;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//打印申请表
function printjxqjjgb(url){
	var qjid="";
	var xh="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("请至少选择一条记录！");
		return false;
	} else {
		if("14073" == jQuery("#xxdm").val()) {
			if(rows.length == 1){
				if(rows[0]["qjlxmc"] == "病假") {
					showAlertDivLayer("请下载病假单！");
					return false;
				}
			}else {
				for (var i = 0; i < rows.length; i++) {
					if(rows[i]["qjlxmc"] == "病假"){
						showAlertDivLayer("请下载病假单！");
						return false;			
					}
				}	
			}
		}
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				qjid +=rows[i]["qjid"];
				xh+=+rows[i]["xh"];
			}else{
				qjid +=rows[i]["qjid"]+",";
				xh+=+rows[i]["xh"]+",";
			}
		}
		var url = url + "&qjid=" +qjid+"&xh="+xh;
		window.open(url);
	}
}

//打印申请表
function printjxqjBjd(url){
	var qjid="";
	var xh="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("请至少选择一条记录！");
		return false;
	} else {
		
		if(rows.length == 1){
			if(rows[0]["qjlxmc"] != "病假") {
				showAlertDivLayer("非病假，无法下载病假单！");
				return false;
			}
		}else {
			for (var i = 0; i < rows.length; i++) {
				if(rows[i]["qjlxmc"] != "病假"){
					showAlertDivLayer("非病假，无法下载病假单！");
					return false;			
				}
			}	
		}
		
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				qjid +=rows[i]["qjid"];
				xh+=+rows[i]["xh"];
			}else{
				qjid +=rows[i]["qjid"]+",";
				xh+=+rows[i]["xh"]+",";
			}
		}
		var url = url + "&qjid=" +qjid+"&xh="+xh;
		window.open(url);
	}
}

