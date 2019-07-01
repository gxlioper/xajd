var action="rcsw_txhd_xmjg.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
//点击序号跳转
function dcmcLink(cellValue, rowObject) {
	var guid = rowObject["guid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + guid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//查看信息
function ckxx(guid) {
	var query=jQuery("#query").val();
	var url = action+"?method=showView&guid=" + guid;
	var title = "活动结果信息";

	showDialog(title, 800, 430, url);
}
//申请
function add() {
		var url =action+"?method=add";
		var title = "活动增加";
		showDialog(title, 800, 490, url);
		jQuery("#dataTable").reloadGrid();
}

function save(url,checkId,sfjc){
	if(!check(checkId)){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
		 	jQuery("#form").ajaxSubmit({
				url:url,
				type:"post",
				dataType:"json",
				success:function(data){
			 		 if(data["message"]=="保存成功！"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    			}});
			    	 }else{
			    		 showAlert(data["message"]);
			    		
			    	 }
				},
				contentType:"application/x-www-form-urlencoded;charset=utf-8"
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
//修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(rows[0]["sjly"]=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
			return false;
		}
		var xh=rows[0]["xh"];
		var url = action+'?method=update&xh='+xh+'&guid=' + rows[0]["guid"];
		var title = "修改活动信息";
		showDialog(title, 800, 490, url);
		jQuery("#dataTable").reloadGrid();
	}
}
//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
function rcxwshLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {		
		showDialog("请假审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['qjsqid']+"&splc="+rows[0]['splcid']);
	}
}
//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport('rcsw_txhd_xmjgbase.do',exportData);
}

// 导出方法
function exportData(dcclbh) {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_txhd_xmjg.do?method=exportData&dcclbh=" + dcclbh;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//打印申请表
function printQjjgb(url){
	var qjjgid="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("请至少选择一条记录！");
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				qjjgid +=rows[i]["qjjgid"];
			}else{
				qjjgid +=rows[i]["qjjgid"]+",";
			}
		}
		var url = url + "&qjjgid=" +qjjgid;
		window.open(url);
	}
}

function getHdsbWord() {
	var guid="";
	var url=null;
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length ==0) {
		showAlertDivLayer("请选择需要打印的记录！");
		return false;
	}else if(rows.length ==1){
		guid = rows[0]["guid"];
	var url = "rcsw_txhd_xmjg.do?method=doPrintHdsbWord&&guid=" + guid;
	}
	else{
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				guid +=rows[i]["guid"];
			}else{
				guid +=rows[i]["guid"]+",";
			}
		}
		url = "rcsw_txhd_xmjg.do?method=doPrintHdsbWordZip&&guid=" + guid;
	}
	window.open(url);

}

//导入
function dr() {
	// 调用通用的导入function，参数是导入功能模块代码。
	toImportDataNew("IMPORT_XSHDSBB");
	return false;

}




