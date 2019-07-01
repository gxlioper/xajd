function checkBj(){
	var bjdm = jQuery("#bjdm").val();
	if(bjdm == '' || bjdm == null){
		return false;
	}else{
		return true;
	}
}

function addLxxs(lx) {
	if(!checkBj()){
		showAlert("请先选择班级！");
		return false;
	}else{		
		var xhArr = new Array();
		var trs = jQuery("#tbody_"+lx+"ryxx").find("tr");
		jQuery.each(trs,function(i,n){
			var xh= jQuery(n).find("td").eq(1).text();
			xhArr.push(xh);
		});
		var url = 'bfjsgl_bfjsglwh.do?method=getStu&bjdm='+jQuery("#bjdm").val()+'&lx='+lx+'&xhArr='+xhArr;
		
		showDialog('请选择学生', 800, 550, url);
	}
	return false;
}




function qqxsZj() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("请至少选择一个学生！");
		return false;
	}
	var lx = jQuery("#lx").val();
	
	setQqxs(rows,lx);	
}

function xhLink(xh) {
	return "<a href='javascript:void(0);' class='name' onclick='xhView(\""
			+ xh+"\");'>" + xh
			+ "</a>";
}

function setQqxs(rows,jclx) {
	var html = "";
	
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
	
	for ( var int = 0; int < rows.length; int++) {
		html += "<tr><td><input type='checkbox' id='checkbox_" + int
				+ "' name='"+jclx+"'/></td>";
		html += "<td name='xh'>" + rows[int]["xh"] + "</td>";
		//html += "<td name='xh'>" + xhLink(rows[int]["xh"]) + "</td>";
		html += "<td name='xm'>" + rows[int]["xm"] + "</td>";
		
		html += "<td><select name='qqlxdm' onchange='changeKqlx(\"" + jclx + "\",this)'>";
		html += "<option></option>";
		html += "<option value='qj'>请假</option>"	;			
		html += "<option value='qq'>缺勤</option>";
		html += "<option value='cd'>迟到</option>"	;
		html += "<option value='zt'>早退</option>"	;
		html += "</td>"	
	}
	
	W.qqxszj(jclx,html);

	iFClose();
}

//改变考勤类型
function changeKqlx(jclx,obj) {
	var kqlx = jQuery(obj).val();
	var xh = jQuery(obj).parents("tr:eq(0)").find("td:eq(1)").text().trim();
	if(kqlx == '' || kqlx == null){
		jQuery(obj).siblings("input").remove();
	}else{
		var jclxdm,kqlxdm;
		jclxdm = setJclxdm(jclx);
		kqlxdm = setKqlxdm(kqlx);
		if(jQuery(obj).siblings("input").length < 1){
			jQuery(obj).after("<input type='checkbox' style='display: none' checked='checked' name='"+kqlx+"s' value='"+xh+"_"+jclxdm+"_"+kqlxdm+"'/>")
		}else{
			jQuery(obj).siblings("input").remove();//删除已有的checkbox
			jQuery(obj).after("<input type='checkbox' style='display: none' checked='checked' name='"+kqlx+"s' value='"+xh+"_"+jclxdm+"_"+kqlxdm+"'/>")
		}
	}
}

function setJclxdm(jclx){
	var jclxdm;
	if(jclx == 'zc'){
		jclxdm = '1';
	}else if(jclx == 'zd'){
		jclxdm = '2';
	}else if(jclx == 'sk'){
		jclxdm = '3';
	}else{
		jclxdm = '4';
	}
	return jclxdm;
}

function setKqlxdm(kqlx){
	var kqlxdm;
	if(kqlx == 'qj'){
		kqlxdm = '1';
	}else if(kqlx == 'qq'){
		kqlxdm = '2';
	}else if(kqlx == 'cd'){
		kqlxdm = '3';
	}else{
		kqlxdm = '4';
	}
	return kqlxdm;
}

function delLxxs(jclx) {	
	var checkbox = jQuery("#tbody_"+jclx+"ryxx").find('input[type=checkbox]:checked[name="'+jclx+'"]');
	if (checkbox.length == 0) {
		showAlertDivLayer("请选择需要删除学生！");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
		jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
}

function delLxxsForUpdate(jclx) {	
	var checkbox = jQuery("#tbody_"+jclx+"ryxx").find('input[type=checkbox]:checked[name="'+jclx+'"]');
	if (checkbox.length == 0) {
		showAlertDivLayer("请选择需要删除学生！");
		return false;
	}
	
	var jclxdm = setJclxdm(jclx);
	var jcid = jQuery("#jcid").val();
	
	for ( var i = 0; i < checkbox.length; i++) {
		var xh = jQuery(checkbox[i]).parents("tr:eq(0)").find("td:eq(1)").text().trim();
		jQuery("#tbody_"+jclx+"ryxx").append("<input type='checkbox' style='display: none' checked='checked' name='dels' value='"+jcid+"_"+jclxdm+"_"+xh+"'/>");
		jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	
}

function selectAll(obj,jclx) {
	var checkboxs = jQuery("#tbody_"+jclx+"ryxx").find('input[name="'+jclx+'"]');
	if(jQuery(obj).prop("checked")){
		jQuery.each(checkboxs,function(i,n){
			jQuery(n).attr("checked",true);
		});
	}else{
		jQuery.each(checkboxs,function(i,n){
			jQuery(n).attr("checked",false);
		});
	}	
}

function xhView(xh) {
	showDialog("学生信息查询", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh);
}

