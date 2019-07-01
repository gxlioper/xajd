function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 检查项Link
 * @return
 */
function jcxLink(cellValue, rowObject){
	var jcxmc = "";
	if(rowObject['wsjc'] == "1"){
		jcxmc +="卫生检查、";
	}
	if(rowObject['aqjc'] == "2"){
		jcxmc +="安全检查、";
	}
	if(rowObject['jljc'] == "3"){
		jcxmc +="纪律检查、";
	}
	return "<font color='blue'>"+jcxmc.substring(0,jcxmc.length-1)+"</font>";
}

/**
 * 增加
 * @return
 */
function addpage(){
	url="gyjc_ccrcsz.do?method=add";
	showDialog("增加日程", 770, 550, url,{close:function(){
		jQuery("#dataTable").reloadGrid();
	}});
	
}

/**
 * 修改
 * @return
 */
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	document.location.href="gyjc_ccrcsz.do?method=update&ccid="+rows[0]["ccid"];
}

function updatejc(){
    var rows = jQuery("#dataTable").getSeletRow();
    url="gyjc_wsjc.do?method=update&jcrq="+rows[0]["jcrq"]+"&lddm="+rows[0]["lddm"]+"&qsh="+rows[0]["qsh"];
    var title = "修改检查结果";
    showDialog(title, 770, 552, url);
}

/**
 * 计算上限百分比
 * @param obj
 * @return
 */
function calBfbOver(obj){
	var inputBfb = parseFloat(jQuery(obj).val());
	var ztxbl = parseFloat(jQuery(obj).parent().find("[name='ztxbl']").val());
	var isUpate = jQuery("#guid").length == 1 ? true :false;
	if(isUpate){
		var bcjcbl = parseFloat(jQuery(obj).parent().find("[name='bcjcbl']").val());
		ztxbl = parseFloat(ztxbl)-parseFloat(bcjcbl);
	}
	var sxBfb =parseFloat((parseFloat(100) - parseFloat(ztxbl)));
	if(!isUpate && ztxbl == 100){
		sxBfb = 100;
	}
	if((sxBfb < parseFloat(inputBfb) && (ztxbl != parseFloat(100))) || inputBfb > 100){
		showAlert("超过可输入百分比上限"+sxBfb+"%",{},{"clkFun":function(){
				obj.focus();
		}});
	}else{
		return;
	}
}

/**
 * 保存检查日程
 * @return
 */
function saveCcrc(){
	var ids = "jcrq";
	if(!checkNotNull(ids)){
		return showAlert("请将带<font class='red'>*</font>的项目填写完整！");
	}
	var url = "gyjc_ccrcsz.do?method=saveCcRc";
	ajaxSubFormWithFun("CcrcForm", url, function(data) {
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
 * 只修改日期
 * @return
 */
function saveCcrcUpdate(){
	var url = "gyjc_ccrcsz.do?method=saveCcrcUpdate";
	ajaxSubFormWithFun("CcrcForm", url, function(data) {
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
 * 删除
 * @return
 */
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("gyjc_ccrcsz.do?method=delCcrc",{rcid:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//分配寝室
function fpqs(){
	var ccid = jQuery("#ccid").val();
	var url = "gyjc_ccrcsz.do?method=fenpei&ccid="+ccid;
	showDialog("寝室列表", 770, 550, url,{close:function(){
	}});
}

//分配抽查人
function fpccr(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ccid = jQuery("#ccid").val();
	if (rows.length == 0){
		showAlertDivLayer("请选择您要分配的寝室！");
	}else{
	var lddms = new Array();
	var qshs = new Array();
	for(var i =0;i<rows.length;i++){
		lddms.push(rows[i]['lddm']);
		qshs.push(rows[i]['qsh']);
	}
	document.location.href = "xg_gyjc_ccry.do?qshs="+qshs+"&lddms="+lddms+"&ccid="+ccid;
	
	}
}

//抽查人查看
function ccrLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject['ccid'] + "\",\""+rowObject['zgh']+ "\");'>" + cellValue
			+ "</a>";
}

//寝室查看
function qsLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='ViewQs(\""
	+ rowObject['ccid'] + "\",\""+rowObject['zgh']+ "\");'>" + cellValue
	+ "</a>";
}

//抽查人修改
function View(ccid,zgh) {
	showDialog("选择抽查人", 900, 450, "gyjc_ccrcsz.do?method=updateCcrList&ccid="+ccid+"&xgzgh="+zgh);
}
//已选寝室跳转
function ViewQs(ccid,zgh) {
	showDialog("选择寝室", 900, 450, "gyjc_ccrcsz.do?method=updateQsList&ccid="+ccid+"&zgh="+zgh);
}

/**
 * 检查结果查看
 * @param obj
 * @return
 */
function jcjgck(){
    var rows = jQuery("#dataTable").getSeletRow();
    if(rows.length != 1){
        return showAlertDivLayer("请选择一条记录！");
    }
    var url = 'gyjc_wsjc.do?method=ckWsjc&jcrq='+rows[0]["jcrq"]+"&lddm="+rows[0]["lddm"]+"&qsh="+rows[0]["qsh"];
    var title = "检查结果查看";
    showDialog(title, 770, 552, url);
}



function ccjgck(){
    var rows = jQuery("#dataTable").getSeletRow();
    if(rows.length != 1){
        return showAlertDivLayer("请选择一条记录！");
    }
    var url = 'gyjc_wscc.do?method=ckWscc&ccid='+rows[0]["ccid"]+"&lddm="+rows[0]["lddm"]+"&qsh="+rows[0]["qsh"];
    var title = "检查结果查看";
    showDialog(title, 770, 552, url);
}

function fhjcsd(){
	document.location.href = "xg_gyjc_ccrcsz.do";
}


/**
 * 页签选择
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj, sffp) {
	jQuery("#sffp").val(sffp);
	if (sffp == "not") {
		jQuery("#li_fp").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["sffp"]="not";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_fp").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["sffp"]="";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

/**
 * 保存人员分配
 * @return
 */
function saveFyfp(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length == 0){
		return showAlertDivLayer("请先选择人员！");
	}
	var zgh = rows[0]['zgh'];
	
	var para = {
				zgh:zgh,
				ccid:jQuery("#ccid").val()
				
				
				};
	jQuery.post("gyjc_ccrcsz.do?method=saveRyFp",para,function(data){
		 if(data["message"]=="保存成功！"){
    		 showAlert(data["message"],{},{"clkFun":function(){
    			 if (parent.window){
    					var api = frameElement.api,W = api.opener;
    					W.jQuery("#dataTable").reloadGrid();
    					closeDialog();
    				}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		
	},'json');
}

//保存修改的分配人员updateFyfp
function updateFyfp(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length == 0){
		return showAlertDivLayer("请先选择人员！");
	}
	var zgh = rows[0]['zgh'];
	
	var para = {
				zgh:zgh,
				ccid:jQuery("#ccid").val(),
				xgzgh:jQuery("#xgzgh").val()
				};
	jQuery.post("gyjc_ccrcsz.do?method=updateRyFp",para,function(data){
		 if(data["message"]=="保存成功！"){
    		 showAlert(data["message"],{},{"clkFun":function(){
    			 if (parent.window){
    					var api = frameElement.api,W = api.opener;
    					W.jQuery("#dataTable").reloadGrid();
    					closeDialog();
    				}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
	},'json');
}



function selectTab(obj, sffp) {
	jQuery("#sffp").val(sffp);
	if (sffp == "not") {
		jQuery("#li_fp").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["sffp"]="not";
	
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_fp").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["sffp"]="";
	
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}



function updateQs(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length == 0){
		return showAlertDivLayer("请选择寝室！");
	}
	var lddms = new Array();
	var qshs = new Array();
	for(var i =0;i<rows.length;i++){
		lddms.push(rows[i]['lddm']);
		qshs.push(rows[i]['qsh']);
	}
	var para = {
				zgh:jQuery("#zgh").val(),
				ccid:jQuery("#ccid").val(),
				lds:lddms,
				qss:qshs
				
				
				};
	jQuery.post("gyjc_ccrcsz.do?method=insertQs",para,function(data){
		 if(data["message"]=="保存成功！"){
    		 showAlert(data["message"],{},{"clkFun":function(){
    			 if (parent.window){
    					var api = frameElement.api,W = api.opener;
    					W.jQuery("#dataTable").reloadGrid();
    					closeDialog();
    				}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
	},'json');
}


/**
 * 取消分配
 * @return
 */
function cancelFp(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length == 0){
		return showAlertDivLayer("请先选择寝室！");
	}
	var lddms = new Array();
	var qshs = new Array();
	for(var i =0;i<rows.length;i++){
		lddms.push(rows[i]['lddm']);
		qshs.push(rows[i]['qsh']);
	}
	var para = {
			ccid:jQuery("#ccid").val(),
			lds:lddms,
			qss:qshs
	};
	jQuery.post("gyjc_ccrcsz.do?method=cancelRyfp",para,function(data){
		showAlertDivLayer(data["message"]);
		 if (parent.window){
				var api = frameElement.api,W = api.opener;
				W.jQuery("#dataTable").reloadGrid();
			}
		jQuery("#dataTable").reloadGrid();
	},'json');
}

function addConMoreUpdateTr(flszid) {

    var trHtml = '';
    jQuery("#headTr").append(trHtml);
}



function saveWsjc() {
    var ztpjs = new Array();
    var grpjs = new Array();
    var cwhs = new Array();
    jQuery("select[name='ztpj']").each(function () {
        ztpjs.push(jQuery(this).val());

    });
    var nary = ztpjs.sort();
    for (var i = 0; i < ztpjs.length; i++) {

        if (nary[i] == nary[i + 1]) {

            // alert("数组重复内容：" + nary[i]);
            return showAlertDivLayer("有重复的寝室整体评价！");
        }
    }
        var cwh = null;
        jQuery("input[name='a']").each(function () {
            cwh = jQuery(this).val();


            var y = "grpj_" + cwh;

            jQuery("select[name=" + y + "]").each(function () {
                var gtpj = cwh + "-" + jQuery(this).val();
                grpjs.push(gtpj)
            });
            cwhs.push(cwh)

        });
        var narygr = grpjs.sort();

        for (var i = 0; i < grpjs.length; i++) {

            if (narygr[i] == narygr[i + 1]) {

                // alert("数组重复内容：" + narygr[i]);
                return showAlertDivLayer("有重复的个人评价！");

            }
        }


            var para = {
                jcrq: jQuery("#jcrq").val(),
                qsh: jQuery("#qsh").val(),
                lddm: jQuery("#lddm").val(),
                cwhs: cwhs,
                grpjs: grpjs,
                ztpjs: ztpjs

            };
            jQuery.post("gyjc_wsjc.do?method=updateWsjc", para, function (data) {
                if (data["message"] == "保存成功！") {
                    showAlert(data["message"], {}, {
                        "clkFun": function () {
                            if (parent.window) {
                                var api = frameElement.api, W = api.opener;
                                closeDialog();
                            }
                        }
                    });
                } else {
                    showAlert(data["message"]);
                }
            }, 'json');
        }


        function updatecc() {
            var rows = jQuery("#dataTable").getSeletRow();
            url = "gyjc_wscc.do?method=update&ccid=" + rows[0]["ccid"] + "&lddm=" + rows[0]["lddm"] + "&qsh=" + rows[0]["qsh"];
            var title = "修改抽查结果";
            showDialog(title, 770, 552, url);
        }

        function saveWscc() {
            var ztpjs = new Array();
            var grpjs = new Array();
            var cwhs = new Array();
            jQuery("select[name='ztpj']").each(function () {
                ztpjs.push(jQuery(this).val());
            });
            var nary = ztpjs.sort();
            for (var i = 0; i < ztpjs.length; i++) {

                if (nary[i] == nary[i + 1]) {
                    // alert("数组重复内容：" + nary[i]);
                    return showAlertDivLayer("有重复的寝室整体评价！");
                }
            }
            var cwh = null;
            jQuery("input[name='a']").each(function () {
                cwh = jQuery(this).val();
                var y = "grpj_" + cwh;
                jQuery("select[name=" + y + "]").each(function () {
                    var gtpj = cwh + "-" + jQuery(this).val();
                    grpjs.push(gtpj)
                });
                cwhs.push(cwh)
            });
            var narygr = grpjs.sort();
            for (var i = 0; i < grpjs.length; i++) {
                if (narygr[i] == narygr[i + 1]) {
                    // alert("数组重复内容：" + narygr[i]);
                    return showAlertDivLayer("有重复的个人评价！");
                }
            }
            var para = {
                ccid: jQuery("#ccid").val(),
                qsh: jQuery("#qsh").val(),
                lddm: jQuery("#lddm").val(),
                cwhs: cwhs,
                grpjs: grpjs,
                ztpjs: ztpjs

            };
            jQuery.post("gyjc_wscc.do?method=updateWscc", para, function (data) {
                // showAlertDivLayer(data["message"]);
                if (data["message"] == "保存成功！") {
                    showAlert(data["message"], {}, {
                        "clkFun": function () {
                            if (parent.window) {
                                var api = frameElement.api, W = api.opener;
                                closeDialog();
                            }
                        }
                    });
                } else {
                    showAlert(data["message"]);
                }
            }, 'json');
        }

        function bbExport() {
            var url="gyjc_bbsc.do?method=bbExport";

            // var xnLength=jQuery("a[name=a_name_xn]").length;
            // var xmmcLength=jQuery("a[name=a_name_xmmc]").length;
            setSearchTj();
            url = addSuperSearchParams(url);
            document.forms[0].action = url;
            document.forms[0].submit();

        }










