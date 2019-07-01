var action="rcsw_bxgl_xsyybx.do";
var title="";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
	//autoTitleForGrid();
	//setTitle();
}
function reload(){
	jQuery("#dataTable").reloadGrid();
	//autoTitleForGrid();
	//setTitle();
}
function btn_close(){

	iFClose();
}

//增加
function add(){
	
	showDialog("填写",710,460,'rcsw_bxgl_xsyybx.do?method=yyxxAdd');
}
//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		
		showDialog("修改",710,460,'rcsw_bxgl_xsyybx.do?method=yyxxUpdate&yybxid='+rows[0]["yybxid"]+"&xh="+rows[0]["xh"],null);
	}
}
//删除
function deletes(){

	var ids = jQuery("#dataTable").getSeletIds();
	
	if (ids.length == 0){
		alertInfo("请选择您要删除的记录！");
	} else {
		confirmInfo("您确定要删除这些记录吗？",function(tp){
			if(tp=="ok"){
				jQuery.post("rcsw_bxgl_xsyybx.do?method=yyxxDel",{values:ids.toString()},function(data){
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
		
	}
}
//学号链接查看
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='ckYyxx(\""+rowObject["yybxid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}

function ckYyxx(yybxid,cellValue){
	
	showDialog("查看",710,350,'rcsw_bxgl_xsyybx.do?method=ckYyxx&yybxid='+yybxid+"&xh="+cellValue,null);

}
function saveForm(method){
	if(yzForm()){
		var url = "rcsw_bxgl_xsyybx.do?method="+method+"&type=save";
		ajaxSubFormWithFun("demoForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
 				if (parent.window){
 					refershParent();
 				}
 			}});

		});
	}
}

//验证表单提交信息
function yzForm(){
	var result =false;
	var xh = jQuery("#xh").val();
	var yysj = jQuery("#yysj").val();
	var blnr = jQuery("#blnr").val();
	if (!checkNull("xh-yysj-blnr")) {
		return false;
	} else if (blnr.length > 250) {
		showAlertDivLayer("备注最大字数不超过" + 250 + ",请确认！");
		return false;
	} else{
		result =true;
	}
	return result;
}

function exportConfig(){
	//DCCLBH导出功能编号,执行导出函数 
	customExport("rcsw_bxgl_xsyybxxx.do", yyxxExportData);
}

//导出
function yyxxExportData() {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_bxgl_xsyybx.do?method=yyxxExportData&dcclbh=" + "rcsw_bxgl_xsyybxxx.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}