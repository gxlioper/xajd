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
function rysqAdd(){
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

	var url = "xpjpy_rysq.do?method=rysqAdd";
	var title = "荣誉申请";
	showDialog(title,800,525,url);
}

/**
 * 保存
 */
function saveRysq(type){
	var xh = jQuery("#xh").val();
	/*控制只能选择一个奖项*/
	if (jQuery("#sqjx tr").size() != 1){
		showAlertDivLayer("您只能选择一项荣誉保存或提交！");
		return false;
	}
	
	/*必须选择申请的奖项*/
	if (jQuery("#sqjx tr").size() == 0){
		showAlert("请选择您要申请的奖项",{},{"clkFun":function(){
			showDialog("选择申请奖项",500,400,"xpjpy_rysq.do?method=selectRyxm&xh="+xh);
		}});
		return false;
	}
	
	var ids = "xh-sqly";
	/*必填项*/
	if(!checkNotNull(ids)){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	
	var url = "xpjpy_rysq.do?method=saveRysq&type="+type;
	ajaxSubFormWithFun("rysqForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if(parent.window){
				refershParent();
			}
		}});
	});
}

/**
 * 项目申请修改
 * @return
 */
function rysqUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else{
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3"){
			showAlertDivLayer("只能修改未提交或者已退回的记录！");
			return false;
		}
		var title = "荣誉申请修改";
		var url = "xpjpy_rysq.do?method=rysqUpdate&id="+rows[0]["id"];
		showDialog(title,800,510,url);
	}
}

/**
 * 修改保存
 */
function saveRysqXg(type){
	
	var ids = "sqly";
	/*必填项*/
	if(!checkNotNull(ids)){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	var url = "xpjpy_rysq.do?method=saveRysqXg&type="+type;
	ajaxSubFormWithFun("rysqForm",url,function(data){
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
function rysqDelete(){
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
			jQuery.post("xpjpy_rysq.do?method=rysqDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 学号连接
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='rysqView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}


/**
 * 点击链接查看详细信息
 * @param id
 * @param xh
 * @return
 */
function rysqView(id) {
	var title = "荣誉申请查看";
	var url = "xpjpy_rysq.do?method=rysqView&id="+id;
	showDialog(title,800,525,url);
}

/**
 * 提交
 * @return
 */
function rysqSubmit(){
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
			/*提交的东西太多了，还是跳之前老的方法，没什么太大区别*/
			jQuery.post("xpjpy_xmsq.do?method=xmsqSubmit",{values:ids.toString()},function(data){
				tips.close();
				showAlertDivLayerSize(data["message"], 340, 200);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 荣誉申请撤销
 * @return
 */
function rysqRevoke(){
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
				jQuery.post("xpjpy_rysq.do?method=rysqRevoke", {
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
function rysqTrack(){
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
var DCCLBH = 'xpjpy_wdpj_rysq.do';
function rysqExport(){
	customExport(DCCLBH, exportData);
}
function exportData(){
	setSearchTj();//设置高级查询条件
	var url = "xpjpy_rysq.do?method=rysqExportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();

}