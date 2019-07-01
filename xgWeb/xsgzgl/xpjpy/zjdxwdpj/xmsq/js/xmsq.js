/**
 * 查询
 * @return
 */
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 申请页面
 */
function xmsqAdd(){
	
	/*获取参数设置的开关状态*/
	var isopen = jQuery("#isopen").val();
	
	/*评奖开关为空时*/
	if(isopen == null || isopen == ""){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		showAlertDivLayer();
		return false;
	}
	
	/*评奖开关有效时间内，按钮不屏蔽，提示：“当前未开放申请，请联系管理员！”*/
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}

	var url = "xpjpy_xmsq.do?method=xmsqAdd";
	var title = "项目申请增加";
	showDialog(title,800,525,url);
}

/**
 * 奖项申请修改
 * @return
 */
function xmsqUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else{
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3"){
			showAlertDivLayer("只能修改未提交或者已退回的记录！");
			return false;
		}
		var title = "项目申请修改";
		var url = "xpjpy_xmsq.do?method=xmsqUpdate&id="+rows[0]["id"];
		showDialog(title,800,510,url);
	}
}

/**
 * 切换已申请、未申请
 * @param obj
 * @param tabId
 * @return
 */
function selectSqxm(obj,tabId){
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	jQuery(".con_overlfow table").css("display","none");
	jQuery("#"+tabId).css("display","");
	
}


/**
 * 选择评奖项目
 * @return
 */
function selectXm(){
	var pjxm = jQuery("input[name=xmdm]:checked");
	if (pjxm.size() == 0){
		showAlert("请选择您要申请的奖项！");
		return false;
	}
	var api = frameElement.api;
	var parentTbody = jQuery(api.get('parentDialog').document).find("#sqjx");
	jQuery("tr",parentTbody).remove();
	var xxdmdiv = jQuery(api.get('parentDialog').document).find("#xxdm");
	var xxdm = xxdmdiv.val();
	var parent = jQuery(api.get('parentDialog').document).find("#fjxxid");
	var djb = jQuery(api.get('parentDialog').document).find("#djb");

    jQuery.ajaxSetup({async: false});
	jQuery.each(pjxm,function(i,n){
		var tr = jQuery("<tr></tr>");
		var xmmcTd = jQuery("<td></td>");
		var xmjeTd = jQuery("<td></td>");
		var sqtsTd = jQuery("<td></td>");
        var xmdybTd = jQuery("<td></td>");
        var xmdybDiv = jQuery("<div id=xmdybDiv"+i+"></div>");
		
		var xmdm = jQuery("<input type='hidden' name='xmdms' value='"+jQuery(n).val()+"'/>");
		var xmmc = jQuery(n).parent().next().text();
		var xmje = jQuery(n).parent().next().next().text();
		var kgbz= jQuery(n).parent().next().next().next().text();
		var xmdyb = jQuery(n).parent().next().next().next().next().text();
		
		xmjeTd.append(xmje);
		xmmcTd.append(xmmc);
		xmmcTd.append(xmdm);
		xmdybTd.append(xmdybDiv);

		sqtsTd.append(jQuery("<p></p>").append(kgbz));
		tr.append(xmmcTd).append(xmjeTd).append(xmdybTd).append(sqtsTd);
		parentTbody.append(tr);

		//调用附件
		// initFj("#xmdybDiv"+i,xmdyb,"xmdyb");
       jQuery(xmdybDiv).multiUploader_q({
            gid : xmdyb,
            uid : "xmdyb"+i,
		    isParent:1
        });
	});
    jQuery.ajaxSetup({async: true});
	api.close();
}


/**
 * 申请保存
 * @param type
 * @return
 */
function saveXmsq(type){
	var xh = jQuery("#xh").val();
	
	/*控制只能选择一个奖项*/
	if (jQuery("#sqjx tr").size() != 1){
		showAlertDivLayer("您只能选择一项奖项保存或提交！");
		return false;
	}
	/*必须选择申请的奖项*/
	if (jQuery("#sqjx tr").size() == 0){
		showAlert("请选择您要申请的奖项",{},{"clkFun":function(){
			showDialog("选择申请奖项",500,400,"xpjpy_xmsq.do?method=selectPjxm&xh="+xh);
		}});
		return false;
	}
	var ids = "xh-wysp-sqly";
	/*必填项*/
	if(!checkNotNull(ids)){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	var url = "xpjpy_xmsq.do?method=saveXmsq&type="+type;
	ajaxSubFormWithFun("xmsqForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}


/**
 * 删除
 * @return
 */
function xmsqDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	}else{
		/*循环选中记录是否有审核状态不为0的数据*/
		for(var i=0; i <ids.length; i++){
			if(rows[i]["shzt"] != "0"){
				showAlertDivLayer("只能删除未提交的记录！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xpjpy_xmsq.do?method=xmsqDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


/**
 * 项目申请修改保存
 * @param type
 * @return
 */
function saveJxsqXg(type){
	
	var ids = "sqly-wysp";
	/*必填项*/
	if(!checkNotNull(ids)){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	var url = "xpjpy_xmsq.do?method=saveJxsqXg&type="+type;
	ajaxSubFormWithFun("xmsqForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}


/**
 * 学号连接
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='xmsqView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}


/**
 * 点击链接查看详细信息
 * @param id
 * @param xh
 * @return
 */
function xmsqView(id) {
	var title = "项目申请查看";
	var url = "xpjpy_xmsq.do?method=xmsqView&id="+id;
	showDialog(title,800,525,url);
}


/**
 * 提交
 * @return
 */
function xmsqSubmit(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择未提交或者已退回的记录！");
		return false;
	} else {
		for(var i=0;i<rows.length;i++){
			if ("0" != rows[i]["shzt"] && "3" != rows[i]["shzt"]){
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
			if('3' != rows[i]['shzt'] && "false" == rows[i]['isopen']){
				showAlertDivLayer("申请项目时间已关闭，请与管理员联系！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
			var tips = submitLoading();
			/*解决：先增加，关闭增加窗口，再提交时等待条无法关闭*/
			try{ 
				tips.show();
			}catch(e){
			}
			jQuery.post("xpjpy_xmsq.do?method=xmsqSubmit",{values:ids.toString()},function(data){
				tips.close();
				showAlertDivLayerSize(data["message"], 340, 200);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


/**
 * 项目申请撤销
 * @return
 */
function xmsqRevoke(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length >1 ) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
				if(rows[i]["shzt"]!='5'){
					showAlertDivLayer("只有审核中的记录才能被撤销！");
					return false;
			}
		}
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {"okFun" : function(){
				jQuery.post("xpjpy_xmsq.do?method=xmsqRevoke", {
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
 * 项目流程跟踪
 * @return
 */
function xmsqTrack(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = rows[0]["shzt"];
	if (rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	} else {
		if(shzt == "0"){
			showAlertDivLayer("无相关流程信息！");
			return false;
		}
		showDialog("流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splc']);
	}
}


/**
 * 导出
 * @return
 */
var DCCLBH = 'xpjpy_wdpj_pjsq.do';
function xmsqExport(){
	customExport(DCCLBH, exportData);
}
function exportData(){
	setSearchTj();//设置高级查询条件
	var url = "xpjpy_xmsq.do?method=xmsqExportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();

}

/**
 * 登记表打印
 * @return
 */
function xmsqDownload() {
	/*选择的记录*/
	var rows = jQuery("#dataTable").getSeletRow();
	/*多选记录*/
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	/*判断打印*/
	if(0 == len){
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	}else if(1 == len){
		var url = "xpjpy_xmsq.do?method=getWordForid&id="+rows[0]["id"];
		window.open(url);
	}else{
		var url = "xpjpy_xmsq.do?method=getDjbZip&value="+ids;
		window.open(url);
	}
}

//导入
function importPjjg(){
    toImportDataNew("IMPORT_N540301_XMSQ");
    return false;
}