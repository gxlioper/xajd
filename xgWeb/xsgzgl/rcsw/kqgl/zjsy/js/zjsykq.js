function addKq(){
	var url = "zjsy_kqgl.do?method=addKqdj";
	var title = "增加考勤记录信息";
	showDialog(title,800,430,url);
}

function cshKq(){
	var url = "zjsy_kqgl.do?method=cshKqdj";
	var title = "初始化考勤信息";
	showDialog(title,400,200,url);
}

function modKq(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else {
		var url = "zjsy_kqgl.do?method=updateKqdj&id="+rows[0]["id"];
		var title = "修改考勤记录信息";
		showDialog(title,800,430,url);
	}
}

function saveForm(method,type){

	 if (!checkNull("xn-xq-zc-yf-cqrs")) {
		 return false;
	 }
	 var url = "zjsy_kqgl.do?method="+method+"&type="+type;
    ajaxSubFormWithFun("ZjsyKqForm",url,function(data){ 	  
   	  if (data["success"] == "false"){
   		  showAlert("该班级的考勤记录在本月本周已记录!" );
   	  } else {
   		  showAlert(data["message"],{},{"clkFun":function(){
       			if (parent.window){
    				 refershParent();
       			}
     		  }});
   	  }
    });
}

function saveKqInfo(type) {
	 if (!checkNull("xn-xq-zc-yf-cqrs-bjcs-sjcs-kkjs")) {
		 return false;
	 }

	var objArr= [];
	jQuery.each(jQuery("#tbody_qqryxx tr"),function(i,n){
		var obj = {};
	    var xh= jQuery(n).find("td").eq(1).text();
		var bjcs = jQuery(n).find("input[name=bjcs]").val();
		var sjcs = jQuery(n).find("input[name=sjcs]").val();
		var kkjs = jQuery(n).find("input[name=kkjs]").val();
		obj.xh=xh;
		obj.bjcs=bjcs;
		obj.sjcs=sjcs;
		obj.kkjs=kkjs;
		objArr.push(obj);
	});
	var objStr = JSON.stringify(objArr);
	jQuery("#objStr").val(objStr);
	var url = "zjsy_kqgl.do?method=updateKqdj&type=" + type;
	ajaxSubFormWithFun("ZjsyKqForm", url, function(data) {
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

//删除
function delKq(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("zjsy_kqgl.do?method=delKqdj",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function viewKq(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要查看的记录！");
	}else {
		var url = "zjsy_kqgl.do?method=viewKqdj&id="+rows[0]["id"];
		var title = "查看考勤记录信息";
		showDialog(title,800,430,url);
	}
}

function exportWithMonthAndXyData(){
	setSearchTj();//设置高级查询条件
	if(!checkSearchWithXy()){
		return false;
	}
	if(checkSearch()){
		var url = "zjsy_kqgl.do?method=exportData&type=monthwithxy";//dcclbh,导出功能编号
		url = addSuperSearchParams(url);//设置高级查询参数
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
}

function exportWithMonthAndXxData(){
	setSearchTj();//设置高级查询条件
	if(checkSearch()){
		var url = "zjsy_kqgl.do?method=exportData&type=monthwithxx";//dcclbh,导出功能编号
		url = addSuperSearchParams(url);//设置高级查询参数
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
}
function exportWithZcAndXyData(){
	setSearchTj();//设置高级查询条件
	if(!checkSearchWithXy()){
		return false;
	}
	if(checkSearch('zc')){
		var url = "zjsy_kqgl.do?method=exportData&type=zcwithxy";//dcclbh,导出功能编号
		url = addSuperSearchParams(url);//设置高级查询参数
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
}
function exportWithZcAndXxData(){
	setSearchTj();//设置高级查询条件
	if(checkSearch('zc')){
		var url = "zjsy_kqgl.do?method=exportData&type=zcwithxx";//dcclbh,导出功能编号
		url = addSuperSearchParams(url);//设置高级查询参数
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
}

//检验可否导出
function checkSearch(type){
	var flag = true;
	var xn_num =  jQuery("a[name=a_name_xn]").length;
	var xq_num =  jQuery("a[name=a_name_xq]").length;
	var yf_num = jQuery("a[name=a_name_yf]").length;
	var zc_num = jQuery("a[name=a_name_zjsyzc]").length;
	if(xn_num != "1" || xq_num !="1"){
		alertError("请选择一个学年和学期!");
		flag = false;
		return flag;
	}else if(yf_num !=1){
		alertError("请选择一个月份！");
		flag = false;
		return flag;
	}
	if(type=="zc"){
		if(zc_num !=1){
			alertError("请选择一个周次！");
			flag = false;
			return flag;
		}
	}
	return flag;
}

//检验可否导出
function checkSearchWithXy(){
	var flag = true;
	var xy_num =  jQuery("a[name=a_name_xy]").length;
	if(xy_num != 1){
			alertError("请选择需要导出的学院！");
			flag = false;
			return flag;
	}
	return flag;
}

//增加行
function addQqxs() {
	var bjdm = jQuery("#bjdm").val();
	if ("" == bjdm || null == bjdm) {
		showAlert("请先选择班级！");
		return false;
	}
	var xhArr = new Array();
	jQuery.each(jQuery("#tbody_qqryxx tr"),function(i,n){
			var xh= jQuery(n).find("td").eq(1).text();
			xhArr.push(xh);
	});
	var url = 'zjsy_kqgl.do?method=getStu&bjdm=' + bjdm+'&xhArr='+xhArr;
	showDialog('', 800, 550, url);
	return false;
}

function setQqxs(rows) {

	var html = "";
	var qqxsTrLen = jQuery("#tbody_qqryxx tr").length;
	for ( var int = 0; int < rows.length; int++) {
		html += "<tr><td><input type='checkbox' id='checkbox_" + int
				+ "'/></td>";
		html += "<td name='xh'>" + rows[int]["xh"] + "</td>";
		html += "<td name='xm'>" + rows[int]["xm"] + "</td>";
		html += "<td><input id='bjcs_" + int
				+ "' type='text' name='bjcs'";
		html += "maxlength='2' onkeyup=\"value=value.replace(/[^\\d]/g,\'\');\"  onblur=\"if(value != '') {value=parseInt(value,10)}\" value='0' /></td>";
		html += "<td><input id='sjcs_" + int
		+ "' type='text' name='sjcs'";
		html += "maxlength='2' onkeyup=\"value=value.replace(/[^\\d]/g,\'\');\" onblur=\"if(value != '') {value=parseInt(value,10)}\" value='0' /> </td>";
		html += "<td><input id='kkjs_" + int
				+ "' type='text' name='kkjs'";
		html += "maxlength='2' onkeyup=\"value=value.replace(/[^\\d]/g,\'\');\" onblur=\"if(value != '') {value=parseInt(value,10)}\"   value='0'/></td></tr>";
		jQuery("#tbody_qqryxx").append(html);
	}
	var W;
	var api = frameElement.api;
	if (api) {
		if (api.get('childDialog')) {
			W = api.get('parentDialog')
		} else {
			W = api.opener;
		}
	} else if (parent.window) {
		W = parent.window;
	}
	W.qqxszj(html);

	iFClose();
}

function qqxsZj() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("请至少选择一个学生！");
		return false;
	}
   setQqxs(rows);
}

function delQqxs() {
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length == 0) {
		showAlertDivLayer("请选择需要删除学生！");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
		jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	jQuery('input[type=checkbox][name=th]').attr('checked', false);
}
function selectAll(obj) {
	jQuery('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
}