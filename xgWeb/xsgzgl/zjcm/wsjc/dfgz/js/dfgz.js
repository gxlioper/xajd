function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 修改
 * @return
 */
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else {
		var url = 'cjWsfDfgz.do?method=updateDfgz&dfszid=' + rows[0]["dfszid"];
		var title = "修改";
		showDialog(title, 600, 300, url);
	}

}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("cjWsfDfgz.do?method=delGzsz", {
					values : ids.toString()
				},
						function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}

function saveForm(type){
	
	if(check("ccny-wwsj-wwzzsj") == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	
	var url = "cjWsfDfgz.do?method=saveForm&type=" +type;
	ajaxSubFormWithFun("DfgzForm", url, function(data) {
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
			return false;
		}
	}
	return true;
}

function addQqxs() {
	var number = parseInt(jQuery("#tbody_gzxx").find("tr:last").find("td:eq(0)").children().attr("id").substr(9,1)) + 1;
	var html = "<tr><td><input type='checkbox' id='checkbox_" + number
	+ "'</td>";
	html+="<td>"
	html+= jQuery("#tbody_gzxx").find("tr:first").find("td:eq(1)").html();
	html+="</td><td>"
	html+= jQuery("#tbody_gzxx").find("tr:first").find("td:eq(2)").html();
	html+="</td>"
	html+="<td><input type='checkbox' id='checkbox_" + number + "' name='bybyb'/></td>"
	
//	if(jQuery("#tbody_gzxx").find("tr").length > 0){
//		var number = jQuery("#tbody_gzxx").find("tr:last").firstChild.attr('id').substr(9,1).parseInt() + 1;
//		var html = "<tr><td><input type='checkbox' id='checkbox_" + number
//		+ "'</td>";
//		html+="<td><html:select property='pfzid' styleId='pfzid'><html:options collection='pfzList' property='pfzid' labelProperty='pfzmc'/></html:select></td>";
//		html+="<td><html:text property='ccbl' styleId='ccbl' onkeyup='value=value.replace(/[^\d]/g,'');' />%</td>";
//		html+="<td><input type='checkbox' id='checkbox_" + number
//		+ "' name='bybyb'/></td>"
//	}else{
//		var number = 0;
//		var html = "<tr><td><input type='checkbox' id='checkbox_" + number
//		+ "'/></td>";
//		html+="<td><html:select property='pfzid' styleId='pfzid'><html:options collection='pfzList' property='pfzid' labelProperty='pfzmc'/></html:select></td>";
//		html+="<td><html:text property='ccbl' styleId='ccbl' onkeyup='value=value.replace(/[^\d]/g,'');' />%</td>";
//		html+="<td><input type='checkbox' id='checkbox_" + number
//		+ "' name='bybyb'/></td>"
//	}
	jQuery("#tbody_gzxx").append(html);
}

function changeNy(){
	var ny = jQuery("#ccny").val();
	var wwsj = ny + "-01";
	var nys = ny.split("-");
	var wwzzsj = ny +"-"+getDaysInMonth(nys[0],nys[1]);
	jQuery("#wwsj").val(wwsj);
	jQuery("#wwzzsj").val(wwzzsj);
}

/**
 * 获取 当前月的天数
 * @param year
 * @param month
 * @return
 */
function getDaysInMonth(year,month){ 
	month = parseInt(month,10); //parseInt(number,type)这个函数后面如果不跟第2个参数来表示进制的话，默认是10进制。 
	var temp = new Date(year,month,0); 
	return temp.getDate(); 
}

function selectAll(obj) {
	jQuery('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
}

function gzsd(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您需要操作的记录！");
		return false;
	}
	if("0"!=rows[0]["ytjqs"]){
		showAlertDivLayer("该月份已有评分记录，不允许设置！");
		return false;
	}
	var url = 'cjWsfDfgz.do?method=gzsd&dfszid=' + rows[0]["dfszid"];
	var title = "规则设置";
	showDialog(title, 600, 400, url);
}


function gzszLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='szck(\""+rowObject["dfszid"]+"\");'>"+cellValue+"</a>";
}

//规则查看
function szck(dfszid) {
	showDialog("查看", 600, 400, "cjWsfDfgz.do?method=viewGzsz&dfszid=" + dfszid);
}

