/**
 * 输入框onblur自动保存
 * @return
 */
function saveXdsq2(type){
	var xdly = jQuery("#xdlys").val();
	if (jQuery.trim(xdly) == ""){
		showAlertDivLayer("续贷理由不可为空！");
		return false;
	}
	/*加入字数限制判断*/
	if(xdly.length > 400){
		showAlertDivLayer("续贷理由不能超过400字！");
		return false;
	}
	var url = "zxdkDkjg.do?method=updatedkxx&type="+type;
	ajaxSubFormWithFun("xdsqForm",url,function(data){
		if (data["message"] == "保存成功！") {
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				document.location.href=document.location;
			}});
		}else{
			showAlertDivLayer(data["message"]);
			return false;
		}
	});
}

/**
 * 提交
 */
function submitBusi() {
	var selectkey = jQuery("[name='id']:checked");
	if (selectkey.length != 1){
		showAlertDivLayer("请选择一条您要提交的记录！");
	}else{
		var selectrow = jQuery(selectkey[0]).parent().parent();
		var shzt = jQuery(selectrow).find("[name='shzt']").val();
		if (shzt != "0" && shzt != "3") {
			showAlertDivLayer("未提交或已退回的数据才能被提交！");
			return false;
		}
		var ids = jQuery(selectkey[0]).val();
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("zxdkDkjg.do?method=submitBusi", {
					values : ids
				}, function(data) {
					showAlertDivLayer(data["message"], {}, {
						"clkFun" : function() {
						   document.location.href=document.location;
						}
					});
				}, 'json');
			}
		});
	}
}

//撤销
function cancel() {
	var selectkey = jQuery("[name='id']:checked");
	if (selectkey.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (selectkey.length > 1) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var selectrow = jQuery(selectkey[0]).parent().parent();
		var shzt = jQuery(selectrow).find("[name='shzt']").val();
		if (shzt != '5') {
			showAlertDivLayer("只有审核中的记录才能被撤销！");
			return false;
		}
		var splc = jQuery(selectrow).find("[name='splc']").val();
		var ids = jQuery(selectkey[0]).val();
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("zxdkDkjg.do?method=cancelXdsq", {
					values : ids,
					splcid : splc
				}, function(data) {
					showAlertDivLayer(data["message"], {}, {
						"clkFun" : function() {
						   document.location.href=document.location;
						}
					});
				}, 'json');
			}
		});
	}

}

//流程跟踪
function lcgz() {
	var selectkey = jQuery("[name='id']:checked");
	if (1 != selectkey.length) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
		return false;
	} else {
		var selectrow = jQuery(selectkey[0]).parent().parent();
		var shzt = jQuery(selectrow).find("[name='shzt']").val();
		var id = jQuery(selectkey[0]).val();
		var splc = jQuery(selectrow).find("[name='splc']").val();
		if ("0" == shzt) {
			showAlertDivLayer("无相关流程跟踪信息！");
			return false;
		}
		showDialog("审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ id + "&splc=" + splc);
	}
}

//删除
function del() {
	var selectkey = jQuery("[name='id']:checked");
	var ids = "";
	if (selectkey.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	}else{
		var selectrow = jQuery(selectkey).parent().parent();
		var flag = true;
		for(var i=0;i<selectrow.length;i++){
			var shzt = jQuery(selectrow[i]).find("[name='shzt']").val();
			var id = jQuery(selectkey[i]).val();
			if(shzt != "0" && shzt != "3"){
				flag = false;
				break;
			}
			ids +=id;
			if(i != selectrow.length-1){
				ids +=",";
			}
		}
		if(!flag){
			showAlertDivLayer("只能删除未提交的记录！");
			return false;
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("zxdkDkjg.do?method=delXdxx",{values:ids},function(data){
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function() {
					   document.location.href=document.location;
					}
				});
			},'json');
		}});
	}
}

function selectAll(obj){
	if(obj.checked){
		jQuery("[name='id']").attr("checked","checked");
	}else{
		jQuery("[name='id']").removeAttr("checked");
	}
}

function update(){
	var selectkey = jQuery("[name='id']:checked");
	if (1 != selectkey.length) {
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} 
	var selectrow = jQuery(selectkey[0]).parent().parent();
	var shzt = jQuery(selectrow).find("[name='shzt']").val();
	if (shzt != '0' && shzt != '3') {
		showAlertDivLayer("审核中的数据不能被修改！");
		return false;
	}
	var id = jQuery(selectrow).find("[name='id']").val();
	var xdxn = jQuery(selectrow).find("[name='ysqxn']").val();
	var xdje = jQuery(selectrow).find("[name='yxdje']").text();
	var xdly = jQuery(selectrow).find("[name='yxdly']").val();
	jQuery("#dqxn").text(xdxn);
	jQuery("#id2").val(id);
	jQuery("#mnje").text(xdje);
	jQuery("#xdlys").val(xdly);
	jQuery("#xdsqTable2").show();
	
}
