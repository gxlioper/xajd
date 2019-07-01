function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//新生之友手工匹配
function sgpp() {
	var rows = jQuery("#dataTable").getSeletRow();
	if(0==rows.length){
		showAlertDivLayer("请选择您要操作的记录！");
		return false;
	}
	if(1!=rows.length){
		showAlertDivLayer("请选择一条您要操作的记录！");
		return false;
	}
	var lddm = rows[0]["lddm"];
	var qsh = rows[0]["qsh"];
	var nj = rows[0]["nj"];
	var ldmc = rows[0]["ldmc"];
	var qsxb = rows[0]["qsxb"];
	var rzrs = rows[0]["rzrs"];
		showDialog("手工匹配", 800, 500, "qsppgl.do?method=getSgppXszyList&lddm="
			+ lddm + "&qsh=" + qsh+"&nj="+nj+"&ldmc="+ldmc+"&qsxb="+qsxb+"&rzrs="+rzrs);
}

function zdpp() {
	var qsNum = jQuery("#dataTable tbody tr").length;
	if(0==qsNum){
		showAlertDivLayer("学院未分配寝室，请先分配寝室再匹配新生之友！");
		return false;
	}
	showDialog("自动匹配", 550, 200, "qsppgl.do?method=zdpp");
}


//新生之友匹配保存
function saveSdpp(zgh,nj){ 
	var rows = jQuery("#dataTable").getSeletRow();
	jQuery.post("qsppgl.do?method=saveSdpp", { 
		lddm:rows[0]["lddm"],
		qsh:rows[0]["qsh"],
		zgh : zgh,
		nj : nj
	}, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}

//自动匹配保存
function saveZdpp() {
	var rows = jQuery("#dataTable").getSeletRow();
	
	jQuery.post("qsppgl.do?method=saveZdpp", {
		
	}, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}

//清空匹配结果
function qkppjg(){ 
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = new Array();
	if(0==rows.length){
		showAlertDivLayer("请选择您要清空的记录！");
		return false;
	}
	for ( var i = 0; i < rows.length; i++) {
		if(null!=rows[i]["qsgxid"]&&""!=rows[i]["qsgxid"]){
			ids.push(rows[i]["qsgxid"]);
		}
	}
	if(null==ids||""==ids){
		showAlertDivLayer("请选择已匹配新生之友的寝室！");
		return false;
	}
	showConfirmDivLayer("是否确定清空勾选的记录？", {
		"okFun" : function() {
	jQuery.post("qsppgl.do?method=qkppjg", { 
		id:ids.join(",")
	}, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
	}});
}

//退回至学园
function qsppTh(){ 
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = new Array();
	var lddms = new Array();
	var qshs= new Array();
	var ssyxdms = new Array();
	var ppshs="";
	if(0==rows.length){
		showAlertDivLayer("请选择您要退回的记录！");
		return false;
	}
	for ( var i = 0; i < rows.length; i++) {
		if(null!=rows[i]["qsfpid"]&&""!=rows[i]["qsfpid"]){
			ids.push(rows[i]["qsfpid"]);
			lddms.push(rows[i]["lddm"]);
			qshs.push(rows[i]["qsh"]);
			ssyxdms.push(rows[i]["ssyxdm"]);
		}
		if(null!=rows[i]["qsgxid"]&&""!=rows[i]["qsgxid"]){
			if(i!=0){
				ppshs+=",";
			}
			ppshs+=rows[i]["qsh"];
		}
	}
   
   if(""!=ppshs){
	   showAlertDivLayer("寝室"+ppshs+"已匹配新生之友，无法退回！如需退回，请先清空匹配结果！");
		return false;   
   }
	showConfirmDivLayer("确定要将此寝室退回到学园吗？退回到学园后，将由学园重新分配到院系",{"okFun":function(){
		jQuery.post("qsppgl.do?method=qsppTh",{values:ids.toString(),lddms:lddms.toString(),qshs:qshs.toString(),ssyxdms:ssyxdms.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}