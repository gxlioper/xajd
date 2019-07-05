
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='hdblsqView(\""
			+ rowObject["sqid"]+"\");'>" + cellValue
			+ "</a>";
}

function hdblsqView(sqid) {
	showDialog("活动补录申请查看", 800, 550, "hdgl_hdblsq.do?method=viewHdblsq&sqid="+sqid);
}


function saveHdblsq(type) {
	var download = jQuery(".MultiFile-label").length;
	var hdxs = jQuery("#hdxs").val();
	var jzlx = jQuery("#jzlx").val();
	var hdlx = jQuery("#hdlx").val();

	var ids = "xh-hdmc-hdsj-hdxs-hdlx-zbf-xsxxlx-jzjb";
    if("讲座" == hdxs){
        ids += "-zjrxm-zjrdw-zjrzc-zjrzw";
    }
    if("活动"==hdxs &&"4"==hdlx){//志愿服务类活动
    	ids += "-zyxss";
	}
	if(!check(ids)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}

    var nlbqLen = jQuery("[name='nlbqs']:checked").length;
    if(nlbqLen > 3){
        showAlert("能力标签最多只能选三个，请确认！");
        return false;
    }
    if("课程" == hdxs){
        if(jzlx == null || jzlx == ''){
            showAlert("请选择课程级别！");
            return false;
        } else {
            var zxkclx = jQuery("#zxkclx").val();
            if(zxkclx == ""){
                showAlert("请选择自选课程类型！");
                return false;
            }
        }
    }
	if(download < 1){
		showAlert("请上传附件");
		return false;
	}
	
	var url = "hdgl_hdblsq.do?method=saveSq&type=" + type;
	ajaxSubFormWithFun("hdblsqshForm", url, function(data) {
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

function add() {
	var url = "hdgl_hdblsq.do?method=addHdblsq";
	var title = "新增活动补录申请";
	showDialog(title, 800, 550, url);
}

function update() {
	//var sqkg = jQuery("#sqkg").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else {
		var shzt = rows[0]["shzt"];
//		if ('3' != rows[0]['shzt'] && "0" == sqkg) {
//			showAlertDivLayer("当前申请已关闭，请与管理员联系！");
//			return false;
//		}
		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer("只有未提交和已退回的记录才能修改！");
			return false;
		}

		var url = 'hdgl_hdblsq.do?method=updateHdblsq&sqid=' + rows[0]["sqid"];
		var title = "活动补录申请修改";
		showDialog(title, 800, 550, url);
	}

}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("只能删除未提交或者已退回的记录！");
		return false;
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("hdgl_hdblsq.do?method=delHdblsq", {
					values : ids.toString()
				},
						function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}

// 提交
function submitBusi() {
	var flg=true;
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	//var sqkg = jQuery("#sqkg").val();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要提交的记录！");
		return false;
	}
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("请选择未提交或者已退回的记录！");
		return false;
	}
//	if ('3' != rows[0]['shzt'] && "0" == sqkg) {
//		showAlertDivLayer("当前申请已关闭，请与管理员联系！");
//		return false;
//	}
	showConfirmDivLayer("您确定要提交选择的记录吗？", {
		"okFun" : function() {
			jQuery.post("hdgl_hdblsq.do?method=submitHdblsq", {
				values : ids.toString()
			}, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
	});
	
	

}
// 撤销
function cancel() {
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
				jQuery.post("hdgl_hdblsq.do?method=cancelHdblsq", {
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
/*
 * 流程跟踪
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (1 != rows.length) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
		return false;
	} 
	var shzt = rows[0]["shzt"];
	if ("0" == shzt) {
			showAlertDivLayer("该记录为未提交状态，请先提交！");
			return false;
		}
		showDialog("活动补录申请审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
}

/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

function changeHdxs(){
    var hdxs = jQuery("#hdxs").val();
    if("课程" == hdxs){
        jQuery("#jzlxTr").show();
        jQuery("tr[name='zjrxx_tr']").hide();
        jQuery("#zysc").hide();
        JzInfoEmpty();
    }else if("讲座" == hdxs){

        jQuery("tr[name='zjrxx_tr']").show();
        jQuery("#lx_span").html("具体类型");
        jQuery("#con_span").html("讲座介绍");

        jQuery("#jzlxTr").hide();
        jQuery("#jzlx").val("");
        jQuery("#zxkclx").val("");

        jQuery("#zysc").hide();
    }else{
        jQuery("tr[name='zjrxx_tr']").hide();
        JzInfoEmpty();
        jQuery("#jzlxTr").hide();
        jQuery("#jzlx").val("");
        jQuery("#zxkclx").val("");
        var hdlx = jQuery("#hdlx").val();
        if ("活动"==hdxs && "4"==hdlx){
            jQuery("#zysc").show();
		}else {
            jQuery("#zysc").hide();
		}

    }
}
function changeHdlx() {
    var hdxs = jQuery("#hdxs").val();
    var hdlx = jQuery("#hdlx").val();
    if("活动"==hdxs && "4"==hdlx){
    	jQuery("#zysc").show();
	}else {
        jQuery("#zysc").hide();
	}
}
function JzInfoEmpty(){
    var tr = jQuery("tr[name='zjrxx_tr']");
    tr.hide();
    tr.find("input").val("");
    tr.find("textarea").val("");
    tr.find("select").val("");
    jQuery("#lx_span").html("活动类型");
    jQuery("#con_span").html("活动内容及心得");
}
/**
 * 课程级别change
 */
function kcjbChange(){
    var v = jQuery("#jzlx").val();
    if("003" == v){
        jQuery("#zxkclxTh").removeAttr("style");
        jQuery("#zxkclxTd").removeAttr("style");
    } else {
        jQuery("#zxkclxTh").attr("style","display:none");
        jQuery("#zxkclxTd").attr("style","display:none");
    }
}