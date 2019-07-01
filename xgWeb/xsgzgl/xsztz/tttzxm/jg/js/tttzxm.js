function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add() {
	var url = "ttxm_jg.do?method=add";
	var title = "团体拓展项目结果";
	showDialog(title, 770, 550, url);
}


//增加修改团体结果保存
function saveTtxmSq(type){
	var ids = "xmmc"+"-"+"tdmc"+"-"+"sqly"+"-"+"dzxh";
	if(!checkNotNull(ids)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	if(jQuery("#usertype").val() != 'stu'){
		if(!checkDzIsSelect()){
			showAlert("请将带<font color='red'>*</font>的项目填写完整！");
			return false;
		}
	}
	if(!checkContentIsNull()){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	var url = "ttxm_jg.do?method=saveTtsq&type=" + type;
	ajaxSubFormWithFun("TttzxmJgForm", url, function(data) {
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


//查看团队链接
function ttsqLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='TtsqView(\""
			+ rowObject["ttjgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function TtsqView(ttjgid, tdmc) {
	showDialog("团体拓展项目结果查看", 770, 450, "ttxm_jg.do?method=TtsqView&ttjgid="
			+ ttjgid);
}

//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	}  
	var flag = true;
	for(var i=0;i<rows.length;i++){
		if(rows[i]['sjly']=='1'){
			showAlertDivLayer("审核流程过来的记录不能删除！");
			return false;
		}
		if((rows[0]['xfrdsqzt']!='0' && rows[0]['xfrdsqzt']!='3') || rows[0]['xfrdjgzt']!='0'){
			showAlertDivLayer("项目学分已认定，不能删除！");
			return false;
		}
	}
	showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("ttxm_jg.do?method=delTtsq",{values:ids.toString()},function(data){
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
	} else {
		if(rows[0]['sjly'] == '1'){
			showAlertDivLayer("审核流程过来的数据不能修改！");
			return false;
		}
		if((rows[0]['xfrdsqzt']!='0' && rows[0]['xfrdsqzt']!='3') || rows[0]['xfrdjgzt']!='0'){
			showAlertDivLayer("该项目学分已认定，不能修改！");
			return false;
		}
		var url = 'ttxm_jg.do?method=editTtsq&ttjgid=' + rows[0]["ttjgid"];
		var title = "团体拓展项目结果";
		showDialog(title, 770, 550, url);
	}
}

var DCCLBH = "sztz_ttxm_jg.do";

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ttsqExportData);
}

//导出方法
function ttsqExportData() {
	setSearchTj();//设置高级查询条件
	var url = "ttxm_jg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}



