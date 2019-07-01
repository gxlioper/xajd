/**
 * 查询
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 档案转出申请
 * @return
 */
function dazcsqApply(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer('基础设置未初始化，请联系管理员！');
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer("当前未开放学生档案转出申请，请联系管理员！");
		return false;
	}
	var url = "dagl_dazcsq.do?method=dazcsqApply";
	var title = "档案转出申请";
	showDialog(title, 680, 375, url);
}

/**
 * 修改
 */
function dazcsqUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else{
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3"){
			showAlertDivLayer("只能修改未提交或者已退回的记录！");
			return false;
		}
		var title = "档案转出修改";
		var url = "dagl_dazcsq.do?method=dazcsqUpdate&sqid="+rows[0]["sqid"];
		showDialog(title, 680, 375, url);
	}
}

/**
 * 转出方式onchange事件
 * @return
 */
function changeZcfs(){
	var zcfs = jQuery("input[name='zcfs']:checked").val();
	if("1" == zcfs){
		/*隐藏字段*/
		jQuery("#mailedOne").show();
		jQuery("#mailedTwo").show();
		jQuery("#mailedThree").show();
		jQuery("#byoOne").hide();
		
		/*清空值*/
		jQuery("#zddacn").attr("checked", false);
		jQuery("#yqtdrq").val("");
	}else{
		/*隐藏字段*/
		jQuery("#mailedOne").hide();
		jQuery("#mailedTwo").hide();
		jQuery("#mailedThree").hide();
		jQuery("#byoOne").show();
		
		/*清空值*/
		jQuery("#yjdz").val("");
		jQuery("#yzbm").val("");
		jQuery("#sjr").val("");
		jQuery("#sjrdh").val("");
		jQuery("#dwmc").val("");
		jQuery("#dwdz").val("");
	}
}

/**
 * 保存
 * @param saveFlag
 * @return
 */
function dazcsqApplySave(saveFlag){
	
	var xh = jQuery("#xh").val();//学号
	var zcfs = jQuery("input[name='zcfs']:checked").val();//转出方式
	var yjdz = jQuery("#yjdz").val();//邮寄地址
	var yzbm = jQuery("#yzbm").val();//邮政编码
	var sjr = jQuery("#sjr").val();//收件人
	var sjrdh = jQuery("#sjrdh").val();//收件人电话
	var dwmc = jQuery("#dwmc").val();//单位名称
	var dwdz = jQuery("#dwdz").val();//单位地址
	var zddacn = jQuery("input:checkbox[name='zddacn']:checked").val();//自带档案承诺
	var yqtdrq = jQuery("#yqtdrq").val();//预期提档日期
	var ids = null;
	
	if(xh == null || xh == ''){
		showAlert("请您先选择一名学生！");
		return false;
	}
	
	if(zcfs == null || zcfs == ''){
		showAlert("请您选择档案转出方式！");
		return false;
	}
	if("1" == zcfs){
		ids = "yjdz-yzbm-sjr-sjrdh-dwmc-dwdz";
		/*验证必填字段是否未填写*/
		if(!checkNotNull(ids)){
			showAlert("请将带<font class='red'>*</font>的项目填写完整！");
			return false;
		}
	}else{
		if(zddacn == null || zddacn == ''){
			showAlert("请先接受档案转出协议！");
			return false;
		}
		ids = "yqtdrq";
		/*验证必填字段是否未填写*/
		if(!checkNotNull(ids)){
			showAlert("请将带<font class='red'>*</font>的项目填写完整！");
			return false;
		}
	}
	
	var url = "dagl_dazcsq.do?method=dazcsqApplySave&saveFlag=" + saveFlag;
	ajaxSubFormWithFun("dazcsqForm", url, function(data){
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

/**
 * 查看
 * @return
 */
function dazcsqView(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要查看的记录！");
	}else{
		var url = 'dagl_dazcsq.do?method=dazcsqView&sqid='+ rows[0]["sqid"];				
		var title = "档案转出查看";
		showDialog(title, 680, 440, url);
	}
}

/**
 * 学号连接
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='dazcsqViewClick(\""+rowObject["sqid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

function dazcsqViewClick(sqid){
	var title = "档案转出查看";
	var url = "dagl_dazcsq.do?method=dazcsqView&sqid="+sqid;
	showDialog(title, 680, 440, url);
}

/**
 * 删除
 * @return
 */
function dazcsqDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		for(var i=0;i<ids.length;i++){
			if (rows[i]["shzt"] != "0"){
				showAlertDivLayer("只能删除未提交的记录！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("dagl_dazcsq.do?method=dazcsqDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


/**
 * 提交（支持批量）
 * @return
 */
function dazcsqSubmit(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(0 == ids.length){
		showAlertDivLayer("请选择您要提交的记录");
	}else{
		for(var i=0;i<rows.length;i++){
			if("0" != rows[i]["shzt"] && "3" != rows[i]["shzt"]){
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
			var tips = submitLoading();
			try{
				tips.show();
			}catch(e){
				
			}
			jQuery.post("dagl_dazcsq.do?method=dazcsqSubmit",{values:ids.toString()},function(data){
				tips.close();
				showAlertDivLayerSize(data["message"], 340, 200);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 撤销
 * @return
 */
function dazcsqRevoke() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length == 0){
		showAlertDivLayer("请选择您要撤销的记录！");
	}else if (ids.length > 1){
		showAlertDivLayer("只能选择一条记录进行撤销！");
	}else{
		if (rows[0]['shzt'] != '5') {
			showAlertDivLayer("只能撤销审核状态为【审核中】的记录！");
			return false;
		}
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("dagl_dazcsq.do?method=dazcsqRevoke", {
					values : ids.toString(),
					splcid : rows[0]['splcid']
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
function dazcsqTrack(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = rows[0]["shzt"];
	if(rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	}else{
		if(shzt == "0"){
			showAlertDivLayer("无相关流程信息！");
			return false;
		}
		showDialog("流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splcid']);
	}
}

/*dcdlbh,导出功能编号*/
var DCDLBH = "xsxx_dagl_dazcsq.do";

/**
 * 导出
 * @return
 */
function dazcsqExport() {
	/*DCCLBH导出功能编号,执行导出函数*/
	customExport(DCDLBH, dazcsqExportData);
}

/**
 * 导出执行
 */
function dazcsqExportData() {
	/*设置高级查询条件*/
	setSearchTj();
	var url = "dagl_dazcsq.do?method=dazcsqExport&dcclbh=" + DCDLBH;
	/*设置高级查询参数*/
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}