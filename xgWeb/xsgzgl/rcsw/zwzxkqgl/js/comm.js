jQuery(function() {
	jQuery.each(jQuery("#tbody_qqryxx tr"), function(i, n) {
		var qqlx = jQuery(n).find("select[name=qqlxdm] option:selected").val();
		if ("001" != qqlx) {
			jQuery("#kkjs_" + i).val("");
			jQuery("#kkjs_" + i).attr("readonly", true);
		}
		else{
			jQuery("#kkjs_" + i).attr("readonly", false);
		}
	});
	
	// =========== 根据菜单自动更新字段名称 begin =============
	var pWindow = getParentWindow();
	var gnmkmc = jQuery("#gnmkmc", pWindow.document).val();
	if(gnmkmc){
		jQuery("#gnmkmc_prompt_span").html(gnmkmc); // 子页面标题
		if(gnmkmc.indexOf("考勤") >= 0){
			jQuery("#ccrq_span").html("抽查日期");
			jQuery("#cclx_span").html("抽查类型");
			jQuery("#jlr_span").html("填写人");
		}else{
			jQuery("#ccrq_span").html("填报日期");
			jQuery("#cclx_span").html("填报事项");
			jQuery("#jlr_span").html("填报人");
			// ====== 隐藏纪律分 begin ======
			jQuery("#jlf_th").hide();
			jQuery("#jlf_td").hide();
			jQuery("#jlr_td").attr("colspan", "3");
			// ====== 隐藏纪律分 end ======
		}
	}
	// =========== 根据菜单自动更新字段名称 end =============
});

function qqxsZj() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("请至少选择一个学生！");
		return false;
	}
	jQuery.post("zwzxkqKqjg.do?method=getQqlxList", {}, function(data) {
		dataObj = data;
		setQqxs(rows);
	}, 'json');
}
function setQqxs(rows) {

	var html = "";
	/*经测试，下面这段代码无用 by yxy,注销掉*/
	//var qqxsTrLen = jQuery("#tbody_qqryxx tr").length;
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
	for ( var int = 0; int < rows.length; int++) {
		html += "<tr><td><input type='checkbox' id='checkbox_" + int
				+ "'/></td>";
		html += "<td name='xh'>" + rows[int]["xh"] + "</td>";
		html += "<td name='xm'>" + rows[int]["xm"] + "</td>";
		
			if(W.jQuery("#xxdm").val() != '12970'){
				html += "<td><select id ='qqlxdm_" + int
				+ "' name='qqlxdm' onchange='changeQqlx(" + int + ")'>";
				html += "<option value=''></option>"
				for ( var i = 0; i < dataObj.length; i++) {
					var o = dataObj[i];
					html += "<option value='" + o.qqlxdm + "'>" + o.qqlxmc
							+ "</option>";
				}
				html += "</select></td>";
				html += "<td><input id='kkjs_" + int
						+ "' type='text' name='kkjs'";
				html += "maxlength='2' onkeyup=\"value=value.replace(/[^\\d]/g,\'\');\"/></td>";
			}							
			html += "<td>	<input type='text' name='ylzd1'  id='ylzd1'  onblur='chLeng(this,500)'/></td>";
		
		/*经测试，下面这段代码无用 by yxy,注销掉*/
		//jQuery("#tbody_qqryxx").append(html);
	}
	
	W.qqxszj(html);

	iFClose();
}
function changeQqlx(index) {

	if ("001" != jQuery("#qqlxdm_" + index).val()) {
		jQuery("#kkjs_" + index).val("");
		jQuery("#kkjs_" + index).attr("readonly", true);
	}
	else{
		jQuery("#kkjs_" + index).attr("readonly", false);
	}
}
function bjSelect(path) {
	showDialog('请选择一个班级', 800, 480,
			'zwzxkqKqjg.do?method=showBjList&goto=' + path);

}

// 计算缺勤人数
function computeQqrs() {
	var ydrs = jQuery("#ydrs").val();
	var sdrs = jQuery("#sdrs").val();
	jQuery("#ydrs").val(ydrs);
	jQuery("#sdrs").val(sdrs);
	if(""==ydrs){
		ydrs=0;
	}
	if(""==sdrs){
		sdrs=0;
	}
	jQuery("#qqrs").val(parseInt(ydrs) - parseInt(sdrs));
}
function checkKqxx(flg,type) {
	var qqxsTr = jQuery("#tbody_qqryxx tr");
	if (!flg || jQuery("#ccrq").val() == "" || jQuery("#ccrq").val() == null
			|| jQuery("#cclxdm").val() == "" || jQuery("#cclxdm").val() == null
			|| jQuery("#bjdm").val() == "" || jQuery("#bjdm").val() == null
			|| jQuery("#ydrs").val() == "" || jQuery("#sdrs").val() == "") {
		showAlert("请将必填项填写完整！");
		flg = false;
		return flg;
	}
	if(parseInt(jQuery("#sdrs").val())>parseInt(jQuery("#ydrs").val())){
		showAlert("实到人数大于应到人数！");
		flg = false;
		return flg;
	}
	if (jQuery("#bz").val().length > 500) {
		showAlert("备注最多输入500字！");
		flg = false;
		return flg;
	}
	
	if("save"==type){
		return true;
	}

	if (jQuery("#qqrs").val() != qqxsTr.length) {
		showAlert('缺勤人数为' + jQuery("#qqrs").val() + '人，您添加了' + qqxsTr.length
				+ '条记录！');
		flg = false;
		return flg;
	}

	return flg;

}

//表单验证四川信息职业技术学院
//function checkKqxx_13815(flg,type){
//	if (!flg || jQuery("#ccrq").val() == "" || jQuery("#ccrq").val() == null
//			|| jQuery("#cclxdm").val() == "" || jQuery("#cclxdm").val() == null
//			|| jQuery("#bjdm").val() == "" || jQuery("#bjdm").val() == null) {
//		showAlert("请将必填项填写完整！");
//		flg = false;
//		return flg;
//	}
//	if (jQuery("#bz").val().length > 500) {
//		showAlert("备注最多输入500字！");
//		flg = false;
//		return flg;
//	}
//	
//	if("save"==type){
//		return true;
//	}
//	return flg;
//}
//全选
function selectAll(obj) {
	jQuery('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
}

// 增加行
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
	var url = 'zwzxkqKqjg.do?method=getStu&bjdm=' + bjdm+'&xhArr='+xhArr;

	showDialog('请选择学生', 800, 550, url);
	return false;
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

function checkJlf(obj){
	if(obj.value<0||obj.value>5){
		showAlertDivLayer("纪律分范围为0-5分！",{},{"clkFun":function(){
			obj.focus();
		}});
	}
	
}
