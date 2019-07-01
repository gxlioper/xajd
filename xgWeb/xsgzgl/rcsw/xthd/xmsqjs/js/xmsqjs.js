
function show(cellValue, rowObject){
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + rowObject["xmdm"]+ "')\" class='name'>" + cellValue + "</a>";
}
function ckxx(xmdm){
	var url ="rcsw_txhd_xmszCx.do?method=showView&xmdm=" + xmdm;
	showDialog("活动信息", 800, 386, url);
}
function ckSqb(sqid){
	var url ="rcsw_txhd_xmsq.do?method=showView&sqid=" + sqid;
	showDialog("活动申请信息", 800, 386, url);
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function auotSetCanCheck(){
	jQuery("tr[name=checkxm]").each(function(){
		var syme=jQuery(this).find("td[name=syme]").text();
		syme=jQuery.trim(syme);
		if(parseInt(syme,10)<1){
			jQuery(this).find("[name=checkbox]").attr("disabled",true);
		}
	});
}



/**
 * 填写申请表
 */
function editSqb(){
	showDialog("填写申请表",800,418,"rcsw_txhd_xmsq.do?method=txhdXmsq");
}


/**
 * 选择资助项目
 * @return
 */
function showXmxz(){
	var xh = jQuery("#xh").val();
	if (jQuery.trim(xh) != ""){
		showDialog("选择活动项目",500,350,"rcsw_txhd_xmsq.do?method=getXmsqInfo&xh="+xh);
	} else {
		showAlertDivLayer("请先选择学生！");
	}
}

/**
 * 选择项目页，切换已申请、未申请
 * @param obj
 * @param tabId
 * @return
 */
function selectSqxm(obj,tabId){

	jQuery("#comp_title li").removeClass("ha");
	jQuery(obj).parent().addClass("ha");

	jQuery("#xmList tbody").css("display","none");
	jQuery("#"+tabId).css("display","");
	
	if (tabId == "ysq"){
		jQuery("#titleTr td").last().css("display","none");
	} else {
		jQuery("#titleTr td").last().css("display","");
	}
}


/**
 * 确定选择项目
 * @return
 */
function selectXm(){
	var api = frameElement.api;
	var selectBox = jQuery("#wsq input:checkbox:checked");
	
	var tbody = jQuery(api.get('parentDialog').document).find("#xmInfo");
		tbody.find("tr").remove();
		
	jQuery.each(selectBox,function(i,e){
			var trHtml = "<tr>";
			trHtml+="<td style='width: 20%'>";
			trHtml+="<input type='hidden' name='xmdmArray' value='"+jQuery(e).val()+"'/>";
			trHtml+=jQuery(e).parents("tr").eq(0).find("td").eq(1).html();
			trHtml+="</td>";
			trHtml+="<td style='width: 20%'>";
			trHtml+=jQuery(e).parents("tr").eq(0).find("td").eq(2).html();
			trHtml+="</td>";
			trHtml+="<td style='width: 20%'>";
			trHtml+=jQuery(e).parents("tr").eq(0).find("td").eq(3).html();
			trHtml+="</td>";
			trHtml+="</tr>";
			tbody.append(trHtml);
	});
	iFClose();
}


/**
 * 新增保存
 * @param type
 * @return
 */
function saveXmsq(type){
	var xh = jQuery("#xh").val();
	var xmdmArray = jQuery("input[name=xmdmArray]");
	
	if (xh == ""){
		showAlert("请先选择学生！");
		return false;
	}
	
	if (xmdmArray.length == 0){
		showAlert("请先选择学生要申请的活动项目！",{},{"clkFun":function(){
			showDialog("选择活动项目",500,350,"rcsw_txhd_xmsq.do?method=getXmsqInfo&xh="+xh);
		}});
		return false;
	}
	
	if(!checkNull("sqly")){
		return false;
	}
	
	var url = "rcsw_txhd_xmsq.do?method=saveXmsq&type="+type;
	ajaxSubFormWithFun("TxhdXmsqJsForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}


/**
 * 修改申请 
 * @return
 */
function updateXmsq(){

	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		
		var shzt = rows[0]["shzt"];
		
		if ("0" != shzt&&"3" != shzt){
			showAlertDivLayer("只能修改未提交或者已退回的记录！");
			return false;
		}
		showDialog("活动项目修改",800,428,"rcsw_txhd_xmsq.do?method=updateXmsq&sqid="+rows[0]["sqid"]);
	}
}


/**
 * 保存修改申请
 * @return
 */
function saveSqxg(type){
	if (!checkNull("sqly")){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	
	var url = "rcsw_txhd_xmsq.do?method=saveSqxg&type="+type;
	ajaxSubFormWithFun("TxhdXmsqJsForm",url,function(data){
		 if (data["message"] == "保存成功！" || data["message"] == "提交成功！") {
			showAlertDivLayer(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlertDivLayer(data["message"]);
		}
	});
}


/**
 * 取消申请
 * 
 * @return
 */
function xmsqDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要取消申请的记录！");
	} else {
		
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer("只能删除未提交或者已退回的记录！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要取消该申请吗？",{"okFun":function(){
			jQuery.post("rcsw_txhd_xmsq.do?method=delXmsq",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 提交申请
 * @return
 */
function submitBusi(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要提交的记录！");
	} else if (ids.length >1 ) {
		showAlertDivLayer("请选择一条您要提交的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
		}
		
		var shlcidnew = rows[0]['shlc'];
		
		if(rows[0]["shzt"]=="3"){
			shlcidnew = rows[0]['splc']
		}
		
		
		jQuery.post("rcsw_txhd_xmsq.do?method=sfksq",{xmdm:rows[0]['xmdm']},function(data){
			var message = data["message"];
			
			if(data["message"]=="保存成功！"||data["message"]=="提交成功！"){
		     showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("rcsw_txhd_xmsq.do?method=subBusi", {
					values : ids.toString(),
					lcid : shlcidnew,
					xh : rows[0]['xh'],
					xmdm : rows[0]['xmdm']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
			}
			else{
				showAlertDivLayer(data["message"]);
			}
	},'json');
}
}




//撤销
function cancle(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length >1 ) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='5'){
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("rcsw_txhd_xmsq.do?method=cancle", {
					values : ids.toString(),
					lcid : rows[0]['splc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}


/**
 * 流程跟踪
 * @return
 */
function xmsqLcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	} else {
		if(rows[0]["shzt"]=="0"){
			showAlertDivLayer("无相关流程信息！");
			return false;
		}
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}

var DCCLBH = "rcsw_txhd_xmsq_js.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号，执行导出的函数
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_txhd_xmsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

