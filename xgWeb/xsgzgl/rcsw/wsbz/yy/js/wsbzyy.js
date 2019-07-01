function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

var DCCLBH = "rcsw_wsbz_jg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, wsbzExportData);
}

//导出方法
function wsbzExportData() {
	setSearchTj();//设置高级查询条件
	var url = "wsbz_yy.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//增加
function add(){
	var flag = "";
	jQuery.post("wsbz_yy.do?method=isHaveQx",{type:'add',sqsj:''},function(data){
		if(data["message"] == '1'){
			var url = "wsbz_yy.do?method=add";
			var title = "预约申请";
			showDialog(title, 700, 552, url);
		}else{
			showAlertDivLayer(data["message"]);
			return false;
		}
	},'json');
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	}
    var sqsj = rows[0]['yyrq'];
    var xh = rows[0]['xh'];
    var sqid = rows[0]['sqid'];
	jQuery.post("wsbz_yy.do?method=isHaveQx",{type:'update',sqsj:sqsj},function(data){
		if(data["message"] == '1'){
			var url = "wsbz_yy.do?method=udpate&sqid="+sqid+"&xh="+xh;
			var title = "预约申请修改";
			showDialog(title, 700, 552, url);
		}else{
			showAlertDivLayer(data["message"]);
			return false;
		}
	},'json');
}
function fdmcOnchange(){
	jQuery("#fddm").change(function(){
	if(jQuery("#xh").val() == ""){
		jQuery("#fddm").val("");
		showAlert("请先选择学生！");
		return false;
	}
	var fddm = jQuery("#fddm").val();
	if(jQuery("#input").css('display') == 'block'){
		jQuery("#yyrq").val(jQuery("#yyrqzc").val());
	}else{
		jQuery("#yyrq").val(jQuery("#yyrqday").val());
	}
	var yyrq = jQuery("#yyrq").val();
	if(jQuery.trim(fddm) == ""){
		showAlert("分队不可为空！");
		return false;
	}
	jQuery.post("wsbz_yy.do?method=fdmcChange",{fddm:fddm,yyrq:yyrq},function(data){
		if(data['hdpl'] == '1'){
			jQuery("#input").show();
			jQuery("#select").hide();
			jQuery("#yyrq").val(jQuery("#yyrqday").val());
			jQuery("#rs").text(data['rs']);
			jQuery("#syrs").text(data['syrs']);
			//jQuery("#flag").val("t");
		}else{
			jQuery("#input").hide();
			jQuery("#select").show();
			jQuery("#yyrq").val(jQuery("#yyrqzc").val());
			jQuery("#rs").text(data['rs']);
			jQuery("#syrs").text(data['syrs']);
			if(data['syrsflag'] == '1'){
				jQuery("#syrs").text(data['syrs']);
			}
			//jQuery("#flag").val("z");
			
		}
		jQuery("#sj").text(data['sj']);
		jQuery("#dd").text(data['dd']);
		jQuery("#rs").text(data['rs']);
		jQuery("#gzzz").text(data['gzzz']);
		jQuery("#fwyq").text(data['fwyq']);
	},'json');})
}

function zcOnChange(){
	jQuery("#yyrqzc").change(function(){
		jQuery("#yyrq").val(jQuery("#yyrqzc").val());
		var syrs = "";
		var fddm = jQuery("#fddm").val();
		var yyrq = jQuery("#yyrq").val();
		if(jQuery.trim(fddm) == ""){
			showAlert("分队不可为空！");
			return false;
		}
		jQuery.post("wsbz_yy.do?method=zcChange",{fddm:fddm,yyrq:yyrq},function(data){
			jQuery("#syrs").text(data['syrs']);
		},'json');
	});
}

function dayOnChange(){
	jQuery("#yyrqday").change(function(){
		jQuery("#yyrq").val(jQuery("#yyrqday").val());
		var syrs = "";
		var fddm = jQuery("#fddm").val();
		var yyrq = jQuery("#yyrq").val();
		if(jQuery.trim(fddm) == ""){
			showAlert("分队不可为空！");
			return false;
		}
		jQuery.post("wsbz_yy.do?method=zcChange",{fddm:fddm,yyrq:yyrq},function(data){
			jQuery("#syrs").text(data['syrs']);
		},'json');
	});
}

function saveData(type){
	//var flag = jQuery("#flag").val();
	var ids = "xh"+"-"+"fddm"+"-"+"yyrq";
	if(!checkNotNull(ids)){
		showAlert("请将必填项填写完整！");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	var url = "wsbz_yy.do?method=saveData&type=" + type;
	ajaxSubFormWithFun("WsbzYyForm", url, function(data) {
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

function checkzs(){
	if(jQuery.trim(jQuery("#sqly").val()).length > 500){
		return false;
	}else{
		return true;
	}
}

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='jgView(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function jgView(sqbh, xh) {
	showDialog("预约查看", 700, 450, "wsbz_yy.do?method=ck&sqid="
			+ sqbh + "&xh=" + xh);
}

//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("wsbz_yy.do?method=del",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 删除，学生
 * @return
 */
function delxs() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var sqsj = rows[0]['yyrq'];
	if (ids.length != 1){
		showAlertDivLayer("请选择一条记录进行删除！");
	} else {
	  jQuery.post("wsbz_yy.do?method=isHaveQxDel",{type:'del',sqsj:sqsj},function(data){
		if(data["message"] == 'true'){
			showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("wsbz_yy.do?method=del",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}else{
			showAlertDivLayer(data["message"]);
			return false;
		}
		
		},'json');
	 }
}



