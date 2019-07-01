/*
 * 喻鑫源 2016-04-26
 * 
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function bjrsLink(cellValue, rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='bjrsview(\""
	+ rowObject["bjid"] + "\",\"" + rowObject["bjdm"] + "\");'>" + cellValue
	+ "</a>";
}

function bjrsview(bjid, bjdm) {
	showDialog("查看", 770, 510, "cxdd_pysb.do?method=getXsView&bjdms="+bjdm+"&bjid="+bjid);
}

/*
 * 流程跟踪
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (1 != rows.length) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
		return false;
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" == shzt) {
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		showDialog("流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['bjid'] + "&splc=" + rows[0]['splc']);
	}
}

//提交
function bjtj() {
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		if ("0" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		showAlertDivLayer("请选择一条您要提交的记录！");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if ('3'!=rows[0]["shzt"] && "0" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
			showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
			return false;
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("cxdd_pysb.do?method=submitBusi", {
					values : ids.toString(),bjdm : rows[0]["bjdm"]
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

//撤销
function bjtjcx() {
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("0" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length > 1) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}

		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("cxdd_pysb.do?method=cancelZssq", {
					values : ids.toString(),
					splcid : rows[0]['splc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}

function whpy(){
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("0" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		if ("0" == isopen){
			showAlertDivLayer("不在上报期间内，不能进行评语维护！");
			return false;
		}
		showAlertDivLayer("请选择一条您要维护的记录！");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
			showAlertDivLayer("请选择未提交或已退回的记录！");
			return false;
		}
		var url = "cxdd_pysb.do?method=getXsPageList&bjdms="+rows[0]['bjdm']+"&bjid="+ids;
		document.location.href = url;
	}
}

/**
 * 综测分录入、查看--查询
 */
function doQuery(){
	var map = {};
	map['bjdms']=jQuery("#bjdms").val();
	map['xh']=jQuery('#xh').val();
	jQuery("#dataTable").reloadGrid(map);
}	

//评语
function pyLink(cellValue,rowObject){
	var html = "<textarea name='py'  data-xsid ='"+rowObject["xsid"]+"' data-bjdm ='"+rowObject["bjdm"]+"' onblur='saveThisRow(this)' data-xh='"+rowObject["xh"]+"' style='width:98%;height:30px' />";
	var obj = jQuery(html);
	jQuery(obj).text(cellValue);
	return obj;
}

//评价
function pjLink(cellValue,rowObject){
	var html = "<select name='pj' data-bjdm ='"+rowObject["bjdm"]+"'  data-xsid ='"+rowObject["xsid"]+"' style='width:80%' data-xh='"+rowObject["xh"]+"'  onchange='saveThisRow(this)' >"+jQuery("select[id='pjhtml']").html()+"</select>";
	var obj = jQuery(html);
	jQuery(obj).find("[value='"+cellValue+"']").attr('selected','selected');
	return obj;
}

//保存数据
function saveThisRow(obj){
	var py = "";
	var pj = "";
	var xh = jQuery(obj).attr("data-xh");
	var bjdm = jQuery(obj).attr("data-bjdm");
	var xsid = jQuery(obj).attr("data-xsid");
	var bjid = jQuery("#bjid").val();
	var nameflag = obj.name;
	if(nameflag == 'py'){
		py = obj.value;
		pj = jQuery(obj).parent().parent().find("[name='pj']").val();
		if(jQuery.trim(py).length < 80 || jQuery.trim(py).length > 120){
			showAlertDivLayer("评语控制在80字-120字之间！");
			return false;
		}
		if(jQuery.trim(pj) == ""){
			showAlertDivLayer("评价不能为空！");
			return false;
		}
	
	}else if(nameflag == 'pj'){
		pj = obj.value;
		py = jQuery(obj).parent().parent().find("[name='py']").val();
		if(jQuery.trim(pj) == ""){
			showAlertDivLayer("评价不能为空！");
			return false;
		}
//		if(jQuery.trim(py).length < 80 || jQuery.trim(py).length > 120){
////			showAlertDivLayer("评语控制在80字-120字之间！");
//			return false;
//		}
	}
	if((jQuery.trim(py).length < 80 || jQuery.trim(py).length > 120) && jQuery.trim(py).length != 0){
		showAlertDivLayer("评语控制在80字-120字之间！");
		return false;
	}
	jQuery.post("cxdd_pysb.do?method=saveData", {
		xh : xh,bjdm : bjdm,xsid:xsid,py:py,pj:pj,bjid:bjid
	}, function(data) {
		//showAlertDivLayer(data["message"]);
		if(data['message'] == '保存成功！'){
			jQuery("#dataTable").reloadGrid();
		}
	}, 'json');
	
}

//提交
function submittj(){
			jQuery.post("cxdd_pysb.do?method=submitBusi", {
				values : jQuery("#bjid").val(),bjdm : jQuery("#bjdms").val()
			}, function(data) {
				showAlertDivLayer(data.message,{},{"clkFun":function(){
					if(data.message == '提交成功！'){
						document.location.href='xsxx_cxdd_pysb.do';
					}else{
						//jQuery("#dataTable").reloadGrid();
					}
					
				}});
				
			}, 'json');
	
}
