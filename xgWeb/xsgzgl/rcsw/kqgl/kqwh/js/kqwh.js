
//检测申请审核开关
function checkKg(type){
	var result = false;
	jQuery.ajaxSetup({async:false});
		jQuery.post("zjsy_kqcssz.do?method=checkSqsh&type="+type,{},function(data){
			if("1" == data){
				result = true;
			}
		},'json');
	jQuery.ajaxSetup({async:true});
	return result;
}

function modKq(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	}else if(rows[0]['shzt'] != '0' && rows[0]['shzt'] != '3' && rows[0]['shzt'] != '' && rows[0]['shzt'] != null){
		showAlertDivLayer("该条记录为"+rows[0]['shztmc']+"，不能操作！");
		return false;
	}else {
		if(checkKg("sq")){
			var url = "zjsy_kqwh.do?method=updateKqwh&id="+rows[0]["id"];
    		var title = "考勤记录信息维护";
    		showDialog(title,800,430,url);
		}else{
			showAlertDivLayer("考情维护已关闭，请与管理员联系！");
			return false;
		}
	}
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
	var url = "zjsy_kqwh.do?method=updateKqwh&type=" + type;
	ajaxSubFormWithFun("KqwhForm", url, function(data) {
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


/**
 * 提交
 * @return
 */
function submit(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if(checkKg("sq")){
		var ids = jQuery("#dataTable").getSeletIds();
		var map = getSuperSearch();
		map['values']=ids.toString();
		
		jQuery.ajaxSetup({async:false});
			jQuery.post("zjsy_kqwh.do?method=checkSubmit",map,function(data){
				if(data>0){
					showConfirmDivLayer("您确定提交当前"+data+"条未提交记录？",{"okFun":function(){
						jQuery.post("zjsy_kqwh.do?method=submit",map,function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json'); 
					}});
				}else{
					showAlertDivLayer("当前结果集没有要提交的记录，请确认！")
				}
			});
		jQuery.ajaxSetup({async:true});
	}else{
		showAlertDivLayer("考情维护已关闭，请与管理员联系！");
		return false;
	}
}


/**
 * 流程跟踪
 */
function viewLcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	} else {
		if(rows[0]["shzt"]=="0" || rows[0]["shzt"]=="6" || rows[0]["shzt"]=="7"||rows[0]["shzt"]==null||rows[0]["shzt"]==""){
			showAlertDivLayer("无相关流程信息！");
			return false;
		}
		showDialog("流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splc']);
	}
}

/**
 * 取消提交
 * @return
 */
function cancleRst(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length >1 ) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		
		var rows = jQuery("#dataTable").getSeletRow();
		if(rows[0]['shzt']!='5'){
			showAlertDivLayer("只有审核中的记录才能被撤销！");
			return false;
		}
		
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("zjsy_kqwh.do?method=cancle", {
					values : ids.toString(),
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

function bjLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='kqshCk(\""
			+ rowObject["id"] + "\");'>" + cellValue
			+ "</a>";
}

function kqshCk(id) {
	showDialog("日常行为结果查询", 720, 520,
			"zjsy_kqsh.do?method=kqshCk&id=" + id);
}