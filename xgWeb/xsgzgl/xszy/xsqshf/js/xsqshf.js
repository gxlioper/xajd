function searchRs() {
	var map = getSuperSearch();
	var fpzt = jQuery("#fpzt").val();
	if (null!=fpzt&&fpzt != "") {
		map["fpzt"] = fpzt;
	}else{
		map["fpzt"] = "dfp";
	}
	jQuery("#dataTable").reloadGrid(map);
}



//切换Tab页
function selectTab(obj, fpzt) {
	jQuery("#fpzt").val(fpzt);
	if (fpzt == "dfp") {
		jQuery("#li_plfp").css("display", "");
		jQuery("#li_qxfp").css("display", "none");
		jQuery("#li_fpxq").css("display", "none");
		jQuery("#li_fptj").css("display", "none");
		var map = getSuperSearch();
		map["fpzt"]=fpzt;
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	}else if (fpzt == "yth") {
		jQuery("#li_plfp").css("display", "");
		jQuery("#li_qxfp").css("display", "none");
		jQuery("#li_fpxq").css("display", "none");
		jQuery("#li_fptj").css("display", "none");
		var map = getSuperSearch();
		map["fpzt"]=fpzt;
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting3);
	} else{
		jQuery("#li_plfp").css("display", "none");
		jQuery("#li_qxfp").css("display", "");
		jQuery("#li_fpxq").css("display", "");
		jQuery("#li_fptj").css("display", "");
		var map = getSuperSearch();
		map["fpzt"]="yfp";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

//手工分配
function sdfpLink(cellValue, rowObject) {
	return "<label class='btn_01' onclick='fpcz(\""
			+ rowObject["lddm"] + "\",\"" + rowObject["qsgxid"] + "\",\"" + rowObject["qsh"] + "\",\"" + rowObject["nj"] + "\",\"" + rowObject["id"] + "\",\"" +1+ "\");'>"
			+ "手工分配</label>";
}
//调整
function tzfpLink(cellValue, rowObject) {
	return "<label class='btn_01' onclick='fpcz(\""
			+ rowObject["lddm"] + "\",\"" + rowObject["qsgxid"] + "\",\"" + rowObject["qsh"] + "\",\"" + rowObject["nj"] + "\",\"" + rowObject["id"] + "\",\"" +2+ "\");'>"
			+ "调整</label>";
}
function fpcz(lddm,qsgxid,qsh,nj,id,czlx) {
	if("2"==czlx){
		if(null==qsgxid||""==qsgxid||"null"==qsgxid){
			showDialog("分配调整", 450, 200, "xszyXsqshf.do?method=fpcz&id="+id+"&type="+"update");
		}else{
			showAlertDivLayer("该寝室已匹配新生之友，不允许调整！如若调整，请先清空匹配的新生之友！");
			return false;
		}
		
	}else{
		showDialog("手动分配", 450, 200, "xszyXsqshf.do?method=fpcz&lddm="
			+ lddm + "&qsh=" + qsh+"&nj="+nj+"&type="+"save");
	}
}
function plfp(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length==0){
		showAlertDivLayer("请选择您要批量分配的寝室！");
		return false;
	}
	var qsh = new Array();
    var sfhhqs = new Array();
    var qsxb = new Array();
	jQuery.each(rows, function(i, row) {
		
		qsh.push(row["qsh"]);
		sfhhqs.push(null==row["sfhhqs"]?'否':row["sfhhqs"]);
		qsxb.push(row["qsxb"]);
	});
	showDialog("批量分配", 500, 200, "xszyXsqshf.do?method=plfp&qsh=" + qsh+"&sfhhqs="+sfhhqs+"&qsxb="+qsxb);
}

//批量分配保存
function savePlfp(ssyxdm) {
	var rows = jQuery("#dataTable").getSeletRow();
	var lddm = new Array();
    var qsh = new Array();
	jQuery.each(rows, function(i, row) {
		if("是"!=row["sfhhqs"]){
			lddm.push(row["lddm"]);
			qsh.push(row["qsh"]);
		}
	});
	jQuery.post("xszyXsqshf.do?method=savePlfp&ssyxdm="+ssyxdm, {
		lddm : lddm.join(","),
		qsh : qsh.join(",")
	}, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}
//分配取消 
function qxFp(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var qshs="";
	if (ids.length == 0){
		showAlertDivLayer("请选择您要取消的分配记录！");
		return false;
	} 
	jQuery.each(rows, function(i, row) {
		if(null!=row["qsgxid"]&&""!=row["qsgxid"]){
			if(i!=0){
				qshs+=",";
			}
			qshs+=row["qsh"];
		}
	});
	if(""!=qshs){
		showAlertDivLayer("寝室"+qshs+"已分配新生之友无法取消！");
		return false;
	}
	showConfirmDivLayer("您确定要取消选择的记录吗？",{"okFun":function(){
		jQuery.post("xszyXsqshf.do?method=qxFp",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	
}

//
