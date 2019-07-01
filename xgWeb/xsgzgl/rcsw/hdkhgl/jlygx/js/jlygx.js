
/**
 * 查询
 * @return
 */

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}



//学生参与活动的记录与感想
function XsJlGxWh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else if(rows[0]["sfcj"] == '否' || rows[0]["sfcj"] == '' || rows[0]["sfcj"] == null ){
		showAlertDivLayer("请选择一条您已参加的记录！");
		return false;
	}
	else {
		var url = 'hdkhgl_jlygx.do?method=XsJlGxWh&hdjgid=' + rows[0]["hdjgid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "记录与感想";
		showDialog(title, 500, 200, url);
	}
}

//保存活动的记录与感想
function saveXsjlgx(){
	var obj = jQuery("#jlygx");
	if(jQuery(obj).val() == ''){
		showAlert("记录与感想不可为空！");
		return false;
	}else if(checkzsonsubmit(obj) == false){
		return false;
	}
	var url = "hdkhgl_jlygx.do?method=saveXsjlgx";
	ajaxSubFormWithFun("HdkhglForm", url, function(data) {
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

//验证字数
function checkzsonsubmit(obj){
	if(jQuery(obj).val().length > 500){
		showAlert("最大字数为500，现已经超过，请确认！");
		return false;
	}
}

function xhLink(cellValue, rowObject) {
	 if(rowObject["sfcj"] == '否' || rowObject["sfcj"] == '' || rowObject["sfcj"] == null ){
		 return cellValue;
	 }else{
		 return "<a href='javascript:void(0);' class='name' onclick='JlgxView(\""
			+ rowObject["hdjgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
	 }
	 
}

//查看学生ajaxurl跳转
function JlgxView(hdjgid,xh) {
	//如果非点击链接查询，说明点的是按钮，xh必然为空，重新取值
	if(!xh){
		var rows = jQuery("#dataTable").getSeletRow();
		hdjgid = rows[0]['hdjgid'];
		xh = rows[0]['xh'];
	}
	showDialog("记录与感想查看", 700, 490, "hdkhgl_jlygx.do?method=XsJlGxWhview&hdjgid="
			+ hdjgid + "&xh=" + xh);
}