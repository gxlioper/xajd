function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add() {
	var url = "hjxf_jg.do?method=add";
	var title = "缓交学费结果增加";
	showDialog(title, 770, 552, url);
}

//增加修改申请保存
function saveHjxfJg(type){
	var ids = ""
	if(type == "update"){
	   ids = "pkdj"+"-"+"dkqk"+"-"+"wnqfje"+"-"+"yjje"+"-"+"hjje"+"-"+"jqjzsj"+"-"+"sqyy";
	}else{
		ids ="xh"+"-"+ "pkdj"+"-"+"dkqk"+"-"+"wnqfje"+"-"+"yjje"+"-"+"hjje"+"-"+"jqjzsj"+"-"+"sqyy";
	}
	
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	if(jQuery("#jqjzsj").val() > jQuery("#checksj").val() && jQuery.trim(jQuery("#checksj").val()) != ""){
		showAlert("超出截止时间"+jQuery("#checksj").val()+"！");
        return false;
	}
	var url = "hjxf_jg.do?method=saveHjxfjg&type=" + type;
	ajaxSubFormWithFun("HjxfJgForm", url, function(data) {
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

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
			return false;
		}
		var url = 'hjxf_jg.do?method=editHjxf&jgid=' + rows[0]["jgid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "缓交学费结果修改";
		showDialog(title, 770, 552, url);
	}
}

//删除申请
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("hjxf_jg.do?method=delHjxf",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "xszz_hjxf_jg.do";

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportData);
}

//导出方法
function ExportData() {
	setSearchTj();//设置高级查询条件
	var url = "hjxf_jg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='HjxfView(\""
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function HjxfView(sqbh, xh) {
	showDialog("缓交学费结果查看", 700, 450, "hjxf_jg.do?method=HjxfView&jgid="
			+ sqbh + "&xh=" + xh);
}

//汇总表导出
function exportHjxfhz(){
	var xnobj = jQuery("a[name='tj_xn'][class='selectedValue']");
	var xn = "";
	if(xnobj.length != 1){
	  showAlertDivLayer("请选择一个学年！");
	  return false;
	}else{
		xn = jQuery(xnobj).attr("id").replace("tj_xn_","");
		
	}

    var url = "hjxf_jg.do?method=getHzbexcel&xn="+xn ;
    window.open(url);  
   
}

//申请表
function printsqb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "hjxf_jg.do?method=getHjxfjg";
		url += "&jgid=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "hjxf_jg.do?method=gettHjxfTy";
		url += "&value=" + ids;
		window.open(url);
	}
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_HJXF");
	return false;
}
