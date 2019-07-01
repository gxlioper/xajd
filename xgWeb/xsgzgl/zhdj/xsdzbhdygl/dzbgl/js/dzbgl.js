function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}


/**
 * 增加
 * @return
 */
function addDzb() {
    url = "dzdy_dzbgl.do?method=add";
    showDialog("增加党支部", 770, 550, url, {
        close: function () {
            jQuery("#dataTable").reloadGrid();
        }
    });

}

/**
 * 修改
 * @return
 */
function update() {
    var rows = jQuery("#dataTable").getSeletRow();
    document.location.href = "gyjc_ccrcsz.do?method=update&ccid=" + rows[0]["ccid"];
}

function updateDzb() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("请选择一个记录！");
        return false;
    }
    var url = "dzdy_dzbgl.do?method=updateDzb&dzbhjid=" + rows[0]["dzbhjid"];
    var title = "修改党支部信息";
    showDialog(title, 770, 552, url);
}


function hjDzb() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("请选择一个记录！");
        return false;
    }
    var url = "dzdy_dzbgl.do?method=hjDzb&dzbhjid=" + rows[0]["dzbhjid"];
    var title = "党支部换届";
    showDialog(title, 770, 552, url);
}


function ljDzb() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("请选择一个记录！");
        return false;
    }
    var url = "dzdy_dzbgl.do?method=ljDzb&dzbhjid=" + rows[0]["dzbhjid"];
    var title = "历届信息";
    showDialog(title, 770, 552, url);
}


/**
 * 只修改日期
 * @return
 */
function saveCcrcUpdate() {
    var url = "gyjc_ccrcsz.do?method=saveCcrcUpdate";
    ajaxSubFormWithFun("CcrcForm", url, function (data) {
        if (data["message"] == "保存成功！") {
            showAlert(data["message"], {}, {
                "clkFun": function () {
                    if (parent.window) {
                        refershParent();
                    }
                }
            });
        } else {
            showAlert(data["message"]);
        }
    });
}

/**
 * 删除
 * @return
 */
function delDzb() {
    var row = jQuery("#dataTable").getSeletRow();
    if (row.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
    } else {
        var para = {
            dzbid: row[0]["dzbid"]

        };
        showConfirmDivLayer("您确定要删除选择的记录吗？", {
            "okFun": function () {
                jQuery.post("dzdy_dzbgl.do?method=delDzb", para, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });
    }
}


jQuery(function () {
    onShow();
});

function onShow() {
    var url = "dzdy_dzbgl.do?method=add&type=query";
    jQuery.post(url, {}, function (data) {
        initTslx(data);//初始化特殊类型
    }, 'json');
}

//初始化特殊类型
function initTslx(data) {
    if (data == null || data.length == 0) {
        return;
    }
    var sHtml = "";

    for (var i = 0; i < data.length; i++) {
        var o = data[i];
        var dm = o.dm;
        var mc = o.mc;
        if (dm != null && dm != "") {
            sHtml += "<option value='" + dm + "'>" + mc + "</option>";
        }
    }
    jQuery("#jcdwdm").html(sHtml);

    jQuery("#jcdwdm").change(function () {//
        var jcdwdm = jQuery(this).val();
        if (jcdwdm === "") {
            jQuery("#symc").html("");

        }
        for (var i = 0; i < data.length; i++) {
            var o = data[i];
            var dm = o.dm;
            var symc = o.symc;
            if (dm != null && dm != "" && jcdwdm === dm) {
                jQuery("#symc").html(symc);
            }
        }
    });
    jQuery("#jcdwdm").change();
    
}


function choseZg(aaa) {
	var rows = jQuery("#dataTable").getSeletRow();
	var number = aaa.value;
    if (rows.length == 0) {
        showAlertDivLayer("请选择一个职工！");
        return false;
    }
    var api = frameElement.api;
    var parentsW = api.get('parentDialog');
    parentsW.jQuery("#jzgmc"+number).val(rows[0]['xm']);
    parentsW.jQuery("#lxrzgh"+number).val(rows[0]['zgh']);
    parentsW.jQuery("#jzgdh"+number).val(rows[0]['lxdh']);
    closeDialog();
}

function choseXx(bbb) {
    var rows = jQuery("#dataTable").getSeletRow();
	var number = bbb.value;
    if (rows.length == 0) {
        showAlertDivLayer("请选择一个学生！");
        return false;
    }
    var api = frameElement.api;
    var parentsW = api.get('parentDialog');
    parentsW.jQuery("#xsmc"+number).val(rows[0]['xm']);
    parentsW.jQuery("#xh"+number).val(rows[0]['xh']);
    parentsW.jQuery("#xsdh"+number).val(rows[0]['lxdh']);
    closeDialog();
}


function saveDzb(dzblx,jgcount,xscount) {
	var ids = null;
	var value = dzblx.value;
	var length = jgcount.value;
	var lengthN = xscount.value;
	if(value == "教工党支部"){
		if(length ==0){
			ids = "dzbmc" + "-" + "jcdwdm" + "-" + "clsj" + "-" + "dzblx";
			if (!checkNotNull(ids)) {
		        return showAlert("请将带<font color='red'>*</font>项目填写完整！");
		    }   
		}else{
			for(var i=0;i<length;i++){
				ids = "dzbmc" + "-" + "jcdwdm" + "-" + "clsj" + "-" + "jgzwmc"+i+ "-" + "jzgdh"+i;
				if (!checkNotNull(ids)) {
			        return showAlert("请将带<font color='red'>*</font>项目填写完整！");
			    }   
			}
		}
	}else if(value == "学生党支部"){
		if(lengthN ==0){
			ids = "dzbmc" + "-" + "jcdwdm" + "-" + "clsj" + "-" + "dzblx"; 
			if (!checkNotNull(ids)) {
		        return showAlert("请将带<font color='red'>*</font>项目填写完整！");
		    }   
		}else{
			for(var i=0;i<lengthN;i++){
				ids = "dzbmc" + "-" + "jcdwdm" + "-" + "clsj" + "-" + "zwmc"+i + "-" + "xsdh"+i;
				if (!checkNotNull(ids)) {
			        return showAlert("请将带<font color='red'>*</font>项目填写完整！");
			    }   
			}	
		}

	}
     
    var url = "dzdy_dzbgl.do?method=saveDzb";
    ajaxSubFormWithFun("DzbglForm", url, function (data) {
        if (data["message"] == "保存成功！") {
            showAlert(data["message"], {}, {
                "clkFun": function () {
                    var api = frameElement.api, W = api.opener;
                    W.jQuery("#dataTable").reloadGrid();
                    closeDialog();
                }
            });
        } else {
            showAlert(data["message"]);
        }
    });
}


function updateSaveDzb(dzblx,jgcount,xscount) {
	
	var ids = null;
	var value = dzblx.value;
	var length = jgcount.value;
	var lengthN = xscount.value;
	length=0;
	lengthN=0;
	if(value == "教工党支部"){
		if(length ==0){
			ids = "";
			if (!checkNotNull(ids)) {
		        return showAlert("请将带<font color='red'>*</font>项目填写完整！");
		    }
		}else{
			for(var i=0;i<length;i++){
				ids = "jgzwmc"+i+ "-" + "jzgdh"+i;
				if (!checkNotNull(ids)) {
			        return showAlert("请将带<font color='red'>*</font>项目填写完整！");
			    }
			}
		}

	}else if(value == "学生党支部"){
		if(lengthN ==0){
			ids = "";
			if (!checkNotNull(ids)) {
		        return showAlert("请将带<font color='red'>*</font>项目填写完整！");
		    }
		}else{
			for(var i=0;i<lengthN;i++){
				ids = "zwmc"+i + "-" + "xsdh"+i;
				if (!checkNotNull(ids)) {
			        return showAlert("请将带<font color='red'>*</font>项目填写完整！");
			    }
			}
		}
	}
    var url = "dzdy_dzbgl.do?method=updateSaveDzb";
    ajaxSubFormWithFun("DzbglForm", url, function (data) {
        if (data["message"] == "保存成功！") {
            showAlert(data["message"], {}, {
                "clkFun": function () {
                    var api = frameElement.api, W = api.opener;
                    W.jQuery("#dataTable").reloadGrid();
                    closeDialog();
                }
            });
        } else {
            showAlert(data["message"]);
        }
    });
}


function hjAddDzb(dzblx,jgcount,xscount) {
    var hjsjOld = jQuery("#hjsjOld").val();
    var hjsj = jQuery("#hjsj").val();
    if (hjsjOld == hjsj) {
        showAlertDivLayer("不能跟之前换届时间相同！");
        return false;
    }
    var ids = null;
	var value = dzblx.value;
	var length = jgcount.value;
	var lengthN = xscount.value;
	if(value == "教工党支部"){
		if(length ==0){
			ids = "1";
			if (!checkNotNull(ids)) {
		        return showAlert("请将带<font color='red'>*</font>项目填写完整！");
		    }   
		}else{
			for(var i=0;i<length;i++){
				ids = "jgzwmc"+i+ "-" + "jzgdh"+i;
				if (!checkNotNull(ids)) {
			        return showAlert("请将带<font color='red'>*</font>项目填写完整！");
			    }   
			}
		}
		
	}else if(value == "学生党支部"){
		if(lengthN ==0){
			ids = "1"; 
			if (!checkNotNull(ids)) {
		        return showAlert("请将带<font color='red'>*</font>项目填写完整！");
		    }   
		}else{

			for(var i=0;i<lengthN;i++){
				ids = "zwmc"+i + "-" + "xsdh"+i;
				if (!checkNotNull(ids)) {
			        return showAlert("请将带<font color='red'>*</font>项目填写完整！");
			    }   
			}
		}
	}
    var url = "dzdy_dzbgl.do?method=hjAddDzb";
    ajaxSubFormWithFun("DzbglForm", url, function (data) {
        if (data["message"] == "保存成功！") {
            showAlert(data["message"], {}, {
                "clkFun": function () {
                    var api = frameElement.api, W = api.opener;
                    W.jQuery("#dataTable").reloadGrid();
                    closeDialog();
                }
            });
        } else {
            showAlert(data["message"]);
        }
    });
}


//抽查人查看
function dzbLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='View(\""
        + rowObject['dzbid'] + "\",\"" + rowObject['dzbhjid'] + "\");'>" + cellValue
        + "</a>";
}

//抽查人修改
function View(dzbid, dzbhjid) {
    showDialog("党支部信息", 900, 450, "dzdy_dzbgl.do?method=getDzbInfo&dzbid=" + dzbid + "&dzbhjid=" + dzbhjid);
}



//dcglbh,导出功能编号
var DCGLBH = "zhdj_dzdy_dzbgl.do";

//自定义导出 功能
function exportConfig() {
    //DCCLBH导出功能编号,执行导出函数
    customExport(DCGLBH, exprotData);
}

//导出方法
function exprotData() {
    setSearchTj();//设置高级查询条件
    var url = "dzdy_dzbgl.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}



//导入
function importConfig(){
    toImportDataNew("IMPORT_DZBXX");
    return false;
}


/**
 * 调用新框架导入功能入口
 *
 * @param drmkdm 导入功能代码
 * @return
 */
function toImportDataNew(drmkdm){
    var _SSO_DR_PATH = 'out_access.do?gnbh=import&toPage=/xgweb/dr/out_login.html';
    if(drmkdm == null || drmkdm == undefined || jQuery.trim(drmkdm) == ""){
        alert("导入模块参数未配置!");
        return false;
    }
    var url = _SSO_DR_PATH + "&drmkdm=" + drmkdm;
    showDialog('导入',720,580,url,{close:function(){
        if (jQuery("#search_go")){
            jQuery("#search_go").click();
        }
    }});
    return false;
}



