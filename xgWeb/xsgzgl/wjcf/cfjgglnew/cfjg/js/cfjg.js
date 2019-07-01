function setDivHeight() {
	if ($("table_rs")) {
		jQuery("#div_rs").height(jQuery("#table_rs").height() + 20);
	}
}

function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='zxsxxView(\""+rowObject["xh"]+"\");'>"+cellValue+"</a>";
}
function zxsxxView(xh) {
	showDialog("学生信息查询", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh
			+ "&xs");
}
// 处分结果修改
function cfjgXg() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 1) {
		var sjly = rows[0]["sjly"];
		var cfid = rows[0]["cfid"];
		var url = "wjcf_cfjg.do?method=cfjgXg";
		url += "&cfid=" + cfid+"&xh="+rows[0]["xh"]+"&sjly="+sjly;
		jQuery.ajaxSetup( {
			async : false
		});
		var count = 0;
		jQuery.post("wjcf_cfjg.do?method=cfsssjCx", {
			cfid : cfid
		}, function(result) {
			var json = eval(result);
			if (json[0].sswh != "" && json[0].sswh != null) {
				count = 1;
				showAlertDivLayer("该处分已申诉不能再进行修改！");
				return false;
			} else if (json[0].zzwh != "" && json[0].zzwh != null) {
				count = 1;
				showAlertDivLayer("该处分已终止不能再进行修改！");
				return false;
			} else if (json[0].jcwh != "" && json[0].jcwh != null) {
				count = 1;
				showAlertDivLayer("该处分已" + jQuery("#text").val() + "不能再进行修改！");
				return false;
			} else if (json[0].jclc != "0" && json[0].jclc != null) {
				count = 1;
				showAlertDivLayer("该处分" + jQuery("#text").val()
						+ "审核中，不能再进行修改！");
				return false;
			} else if (json[0].sslc != "0" && json[0].sslc != null) {
				count = 1;
				showAlertDivLayer("该处分申述审核中，不能再进行修改！");
				return false;
			}
		});
		jQuery.ajaxSetup( {
			async : true
		});
		if (count == 0) {
			showDialog("修改处分结果", 800, 500, url);
		}
	} else {
		showAlertDivLayer("请勾选一条需要修改的记录！");
		return false;
	}
}
function showCfjg(cfid){
	showDialog("处分结果查看",800,500,"wjcf_cfjg.do?method=cfjgCk&cfid="+cfid);
}
function showView() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 1) {
		var cfid = rows[0]["cfid"];
		var url = "wjcf_cfjg.do?method=cfjgCk&cfid=";
		url += cfid;
		showDialog("查看处分结果", 800, 500, url);
	} else {

		showAlertDivLayer("请勾选一条需要查看的记录！");

		return false;
	}
}
// 处分删除
function cfjgSc() {

	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var blog = true;
	if (rows.length > 0) {
		var count = 0;
		for ( var i = 0; i < rows.length; i++) {
			var sjly = rows[i]["sjly"];
			if (sjly == "1") {
				count = count + 1;
			}
		}
		if (count > 0) {
			showAlertDivLayer("选择的数据记录中已有流程数据，不能删除！");
			return false;
		}
		showConfirmDivLayer("该操作将会删除处分信息，是否确定继续操作？", {
			"okFun" : function() {
				var url = "wjcf_cfjg.do?method=cfjgSc";
				jQuery.post(url, {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	} else {
		showAlertDivLayer("请勾选需要删除的数据！");
		return false;
	}
}
// 处分申诉
function cfsscl() {
	var rows = jQuery("#dataTable").getSeletRow();
	;
	if (rows.length == 1) {
		jQuery.ajaxSetup( {
			async : false
		});
		var count = 0;
		jQuery.post("wjcf_cfjg.do?method=cfsssjCx", {
			cfid : rows[0]["cfid"]
		}, function(result) {
			var json = eval(result);
			if (json[0].jcwh != "" && json[0].jcwh != null) {
				alertInfo("该处分已" + jQuery("#text").val() + "不能再进行申诉！");
				count = 1;
				return false;
			}
			if (json[0].zzwh != "" && json[0].zzwh != null) {
				alertInfo("该处分已终止不能再进行申诉！");
				count = 1;
				return false;
			}
			if (json[0].ssjg == '更改处分') {
				jQuery("#cfggw").css("display","block");
			}
		});

		if (count == 0) {
			var url = "wjcf_cfjg.do?method=cfjgSs&&cfid=" + rows[0]["cfid"];
			showDialog("", 450, 280, url);
		}
	} else {
		alertInfo("请勾选一条需要操作的记录！");
		return false;
	}

}

// 处分解除
function cfjccl() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 1) {
		jQuery.ajaxSetup( {
			async : false
		});
		count = 0;
		var cfid = rows[0]["cfid"];

		jQuery.post("wjcf_cfjg.do?method=cfsssjCx", {
			cfid : cfid
		}, function(result) {
			var json = eval(result);
			if (json[0].zzwh != "" && json[0].zzwh != null) {
				showAlertDivLayer("该处分已终止不能再进行" + jQuery("#text").val() + "！");
				count = 1;
				return false;
			}
		});
		jQuery.ajaxSetup( {
			async : true
		});
		if (count == 0) {
			var url = "wjcf_cfjg.do?method=cfjgJc&cfid=" + cfid;
			showDialog("", 400, 240, url);
		}

	} else {

		showAlertDivLayer("请勾选一条需要操作的记录！");

		return false;
	}
}
function cfzzcl() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 1) {
		var cfid = rows[0]["cfid"];
		jQuery.ajaxSetup( {
			async : false
		});
		var count = 0;
		jQuery.post("wjcf_cfjg.do?method=cfsssjCx", {
			cfid : cfid
		}, function(result) {
			var json = eval(result);
			if (json[0].jcwh != "" && json[0].jcwh != null) {
				showAlertDivLayer("该处分已" + jQuery("#text").val() + "不能再进行终止！");
				count = 1;
				return false;
			}
			if (!sfkzzFlag(json[0])) {
				count = 1;
				return false;
			}
		}, 'json');

		jQuery.ajaxSetup( {
			async : true
		});
		if (count == 0) {
			var url = "wjcf_cfjg.do?method=cfjgZz&cfid=" + cfid;
			showDialog("", 400, 240, url);
		}
	} else {

		showAlertDivLayer("请勾选一条需要操作的记录！");

		return false;
	}
}

function sfkzzFlag(cfInfo) {
	var flag = true;
	jQuery.ajaxSetup( {
		async : false
	});
	jQuery.post("wjcf_cfjg.do?method=getKzzFlag", {
		cfsj : cfInfo.cfsj,
		zzsj : cfInfo.zzsj,
		cflbmc : cfInfo.cflbmc
	}, function(data) {
		if (data["message"] != null && data["message"] != "") {
			showAlertDivLayer(data["message"]);
			flag = false;
		}
	}, 'json');
	jQuery.ajaxSetup( {
		async : true
	});
	return flag;
}

/**
 * 下载申请表格
 * 
 * @return
 */
function getWord() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条记录！");
		return false;
	}

	var jcwh = rows[0]["jcwh"];
	if (jcwh == null || jQuery.trim(jcwh) == '') {
		showAlertDivLayer("请选择一条" + jQuery("#text").val() + "处分记录！");
		return false;
	}
	var cfid = rows[0]["cfid"];
	var url = "wjcf_cfjg.do?method=doPrint&&cfid=" + cfid;
	window.open(url);
}
/**
 * 违纪处分通知书打印
 * 
 * @return
 */
function getWjCfWord() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条记录！");
		return false;
	}
	var cfid = rows[0]["cfid"];
	var url = "wjcf_cfjg.do?method=doPrintWjcfWord&&cfid=" + cfid;
	window.open(url);

}
/**
 * 浙江商业职业技术学院个性化：学生处分决定书下载
 * 
 * @return
 */
function getCfjdWord() {
	var cfid="";
	var url=null;
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length ==0) {
		showAlertDivLayer("请选择需要打印的记录！");
		return false;
	}else if(rows.length ==1){
	cfid = rows[0]["cfid"];
	var url = "wjcf_cfjg.do?method=doPrintCfjdWord&&cfid=" + cfid;
	}
	else{
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				cfid +=rows[i]["cfid"];
			}else{
				cfid +=rows[i]["cfid"]+",";
			}
		}
		url = "wjcf_cfjg.do?method=doPrintCfjdWordZip&&cfid=" + cfid;
	}
	window.open(url);

}

function initCfwh(nd){
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var cfid= jQuery("#cfid").val();
	if(xn==null||xn==""){
		showAlertDivLayer("请先选择学年！");
		return false;
	}
	if(xq==null||xq==""){
		showAlertDivLayer("请先选择学期！");
		return false;
	}
	
	jQuery.post("wjcf_cfjg.do?method=initCfwh",{nd:nd,xn:xn,xq:xq,cfid:cfid},function(data){
			jQuery("#cfwh").val(data["message"]);
	},"json");
	
}

//初始化处分到期时间，获得处分到期时间默认值，处分时间+处分期限（如果没有处分期限则不显示处分到期时间）
function defaultCfdqsj(){
	var cfsj = jQuery("#cfsj").val();
	var cflbdm = jQuery("#cflbdm").val();
	
	jQuery.post("wjcf_cfsh.do?method=defaultCfdqsj",{cfsj:cfsj,cflbdm:cflbdm},function(data){
		//jQuery("#cfdqsj").val(data["message"]);
		if(data["message"]!="hidden"){
			var html = "<th><font color=\"red\">*</font>处分到期时间</th><td colspan=\"3\"><input name=\"cfdqsj\" id=\"cfdqsj\" "
			+" style=\"cursor:hand;\" onclick=\"return showCalendar('cfdqsj','y-mm-dd',false,'cfsj');\" value=\""+data["message"]+"\"/></td>";
			jQuery("#cffw_tr3").html(html);
		}else{
			jQuery("#cffw_tr3").html("");
		}
		
	},'json');
}

/**
 * 处分文件下载
 * 处分决定书下载（乌海职业技术学院：同类别同文号多条记录合并为单个文件）
 * 处分审批表下载（乌海职业技术学院：需要取审核流程信息）
 */
function cfwjDownload(url) {
	var rows = jQuery("#dataTable").getSeletRow();
	var cfids = jQuery("#dataTable").getSeletIds();

	if (rows.length == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	}

	for (var i = 0; i < rows.length; i++) {
		if (rows[i]["fwjg"] != '处分成立') {
			showAlertDivLayer("请选择处分成立的记录！");
			return false;
		}
	}
	
	var url = url + "&cfids=" + cfids;
	window.open(url);
}

/**
 * 浙江警官职业学院——学生处分审批表
 * @return
 */
function printXscfspb(){
	/*选择的记录*/
	var rows = jQuery("#dataTable").getSeletRow();
	/*多选记录*/
	var ids = jQuery("#dataTable").getSeletIds();
	/*选择记录的条数（长度）*/
	var len = ids.length;
	if(0 == len ){/*选择0条提示*/
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	}else if(1 == len){/*选择一条记录*/
		var url = "wjcf_cfjg.do?method=getXscfspbOne"+"&id="+rows[0]["cfid"];
		window.open(url);
	}else{
		var url = "wjcf_cfjg.do?method=getXscfspbZip&value="+ids;
		window.open(url);
	}
}