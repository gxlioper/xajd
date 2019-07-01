
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
//点击序号跳转
function dcmcLink(cellValue, rowObject) {
	var zyszid = rowObject["zyszid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + zyszid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//查看信息
function ckxx(zyszid) {
	var query=jQuery("#query").val();
	var url = "zyszpjwh.do?method=showView&query="+query+"&zyszid=" + zyszid;
	var title = "职业素质评价信息";
	showDialog(title, 800, 525, url);
}
//打印
function dy() {
	var rows = jQuery("#dataTable").getSeletRow();
	var url = 'zyszpjwh.do?method=print';
	if (rows.length <= 0) {
		showAlertDivLayer("请选择您要打印的记录！");
	} else {
		var zyszid = "";
		for ( var i = 0; i < rows.length; i++) {
			zyszid += rows[i]["zyszid"] + ",";
		}
		url = url + "&zyszid=" + zyszid;
		window.open(url);
	}
}
//查询
function query() {
	var map = {};
	map["tbgxxslbmc"] = jQuery("#tbgxxslbmc").val();
	jQuery("#dataTable").reloadGrid(map);
}
//申请
function add() {
	jQuery.post("zyszpjwh.do?method=isCanAdd", {
		values : ""
	}, function(data) {
		if(data["success"]=="true"){
			var xh = jQuery("#xh").val();
			var url = "zyszpjwh.do?method=add&xh=" + xh;
			var title = "填写申请表";
			showDialog(title, 760, 525, url);
			jQuery("#dataTable").reloadGrid();
		}else{
			showAlertDivLayer(data["message"]);
		}
	}, 'json');
}
//修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var hpzt=rows[0]["hpzt"];
		if(hpzt=="已互评"){
			showAlertDivLayer("已被互评不能再做修改！");
			return false;
		}
		
		var spzt=rows[0]["spzt"];
		if(spzt=="已师评"){
			showAlertDivLayer("已被师评不能再做修改！");
			return false;
		}
		var url = 'zyszpjwh.do?method=update&pjlx=br&zyszid=' + rows[0]["zyszid"];
		var title = "修改职业素质评价信息";
		showDialog(title, 760, 525, url);
		jQuery("#dataTable").reloadGrid();
	}
}
//同学互评
function txhp() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要互评的记录！");
	} else {
		var url = 'zyszpjwh.do?method=zyszpj&pjlx=tx&zyszid=' + rows[0]["zyszid"];
		var title = "添加互评信息";
		showDialog(title, 800, 525, url);
		jQuery("#dataTable").reloadGrid();
	}
}
//师评
function sp() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要师评的记录！");
	} else {
		var url = 'zyszpjwh.do?method=zyszpj&pjlx=ls&zyszid=' + rows[0]["zyszid"];
		var title = "添加师评信息";
		showDialog(title, 800, 525, url);
		jQuery("#dataTable").reloadGrid();
	}
}
//删除
function del() {
	var rows=jQuery("#dataTable").getSeletRow();
	var candel=true;
	jQuery.each(rows,function(e){
		var hpzt=rows[e]["hpzt"];
		var spzt=rows[e]["spzt"];
		if(hpzt=="已互评"||spzt=="已师评"){
			candel=false;
			return false;
		}
	});
	if(!candel){
		showAlertDivLayer("包含已经互评或师评的数据不能删除！");
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("zyszpjwh.do?method=del", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
//添加一行子项目填写数据
function addTr() {
	jQuery('#tbody_add').append(jQuery('#addstr').html());
	return false;
}
//删除一行子项目填写数据
function delTr() {
	var obj = jQuery("input[name=zxm]:checked");
	if (obj.length <= 0) {
		return alertError("请选择要删除的数据！");
	}
	jQuery("input[name=zxm]:checked").each(function() {
		jQuery(this).parent().parent().remove();
	});
}
//检测长度
function checkLength(obj,len){
	var str=obj.value;
     	if(str.replace(/[^\u0000-\u00ff]/g, "**").length > len){	         
     		alertError("此项不能大于"+len+"个字符！");
      		 return false;
   		 }
}
//检测长度
function checkLength(){
	var xmlbid = jQuery("#tbody_add input[name='dd']");
	xmlbid.each(function(i) {
		alert(jQuery(this).val().length );
		if (jQuery(this).val().length > 30) {
			alertError("地点不能大于30个字符！");
			xmlbid.focus();
     		 return false;
		}
	});
	var xmlbid = jQuery("#tbody_add textarea[name='hdnr']");
	xmlbid.each(function(i) {
		if (jQuery(this).val().length > 100) {
			alertError("活动内容不能大于100个字符！");
			xmlbid.focus();
     		 return false;
		}
	});
	var xmlbid = jQuery("#tbody_add textarea[name='cyjhjqk']");
	xmlbid.each(function(i) {
		if (jQuery(this).val().length > 200) {
			alertError("参与及获奖情况不能大于200个字符！");
			xmlbid.focus();
     		 return false;
		}
	});
	return true;
}
//检测是否为空
function check() {
	var check = true;
	var xh=jQuery("#xh").val();
	if(xh==""){
		return false;
	}
	var xmlbid = jQuery("#tbody_add select[name='xmlbid']");
	xmlbid.each(function(i) {
		if (jQuery(this).val() == "") {
			check = false;
		}
	});
	var xmlbid = jQuery("#tbody_add input[name='sj']");
	xmlbid.each(function(i) {
		if (jQuery(this).val() == "") {
			check = false;
		}
	});
	var xmlbid = jQuery("#tbody_add input[name='dd']");
	xmlbid.each(function(i) {
		if (jQuery(this).val() == "") {
			check = false;
		}
	});
	var xmlbid = jQuery("#tbody_add textarea[name='hdnr']");
	xmlbid.each(function(i) {
		if (jQuery(this).val() == "") {
			check = false;
		}
	});
	var xmlbid = jQuery("#tbody_add textarea[name='cyjhjqk']");
	xmlbid.each(function(i) {
		if (jQuery(this).val() == "") {
			check = false;
		}
	});
	var xmlbid = jQuery("textarea[name='zpxx']");
	xmlbid.each(function(i) {
		if (jQuery(this).val() == "") {
			check = false;
		}
	});
	return check;
}
