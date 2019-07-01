
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//上传附件页面
function toUpload(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xsxx_xsxxxg.do?method=uploadfjsc&hjid=' + rows[0]["hjid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "获奖附件信息上传";
		showDialog(title, 770, 552, url);
	}
}

function saveUpload(){
	var url = "xsxx_xsxxxg.do?method=uploadfjsc&type=update";
	ajaxSubFormWithFun("xsxxglModel", url, function(data) {
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
	return "<a href='javascript:void(0);' class='name' onclick='viewUpload(\""
			+cellValue+ "\",\"" + rowObject["hjid"]+ "\");'>" + cellValue
			+ "</a>";
}

//查看
function viewUpload(xh,hjid){
	var url = 'xsxx_xsxxxg.do?method=uploadfjsc&hjid=' + hjid
			+ '&xh=' + xh+"&type=view";
	var title = "获奖附件信息查看";
	showDialog(title, 770, 552, url);

}