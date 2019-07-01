/**
 * 志愿服务申请相关js
 */

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 学号格式化
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='zyfwSqShow(\""
			+ rowObject["fwid"]+"\");'>" + cellValue
			+ "</a>";
}

/**
 * 服务地点过长截取
 */
function fwddSubString(cellValue, rowObject){
	var cellValueNotNull = cellValue ? cellValue : "";
	var cellValueNew = cellValueNotNull.length > 20 ? cellValue.substring(0,20)+"..." : cellValueNotNull;
	return "<span title='"+cellValueNotNull+"' >" + cellValueNew + "</span>";
}


/**
 * 新增的保存
 */
var checkId = 'hdmc-hdzt-hdrq-bjmc-hdfzrxm-hdfzrlxdh';

function bjhdJgSave() {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}

	var url = "bjhdgl_bjhdjg.do?method=bjhdJgSave";
	ajaxSubFormWithFun("BjhdJgForm", url, function(data) {
		 if(data["message"]=="保存成功！"||data["message"]=="提交成功！"){
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
 * 编辑的保存
 */
function bjhdJgSaveForEdit() {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	var url = "bjhdgl_bjhdjg.do?method=bjhdJgSaveForEdit";
	ajaxSubFormWithFun("BjhdJgForm", url, function(data) {
		 if(data["message"]=="保存成功！"||data["message"]=="提交成功！"){
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


function bjhdJgSaveForUpload() {

    var url = "bjhdgl_bjhdjg.do?method=bjhdJgSaveForEdit";
    ajaxSubFormWithFun("BjhdJgForm", url, function(data) {
        if(data["message"]=="保存成功！"||data["message"]=="提交成功！"){
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


function rdjpbjhd() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("请选择一条您要认定精品班级活动的记录！");
    }
    if (rows.length == 1) {
        var url = 'bjhdgl_bjhdjg.do?method=rdjpbjhd&jgid=' + rows[0]["jgid"];
        var title = "认定示范性主题班会";
        showDialog(title, 400, 250, url);
    }
    else {
        showDialog("批量认定", 500, 250, "bjhdgl_bjhdjg.do?method=bjhdJgPlrd");
    }


}

function saveForPlRd(sfjp) {

    var rows = jQuery("#dataTable").getSeletRow();
    var jgids = new Array();

    jQuery.each(rows, function(i, row) {
        jgids.push(row["jgid"]);
    });
    jQuery.post("bjhdgl_bjhdjg.do?method=saveForPlRd", {
        jgids : jgids,
        sfjp : sfjp
    }, function(data) {
        showAlertDivLayer(data["message"], {}, {
            "clkFun" : function() {
                jQuery("#dataTable").reloadGrid();
            }
        });
    }, 'json');
}




function saveSfjp() {

    var url = "bjhdgl_bjhdjg.do?method=bjhdJgSaveForEdit";
    ajaxSubFormWithFun("BjhdJgForm", url, function(data) {
        if(data["message"]=="保存成功！"||data["message"]=="提交成功！"){
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
 * 申请弹框页面
 */
function add() {
	var url = "bjhdgl_bjhdjg.do?method=bjhdJgAdd";
	showDialog("班级活动添加", 800, 550, url);
}

/**
 * 修改弹框页面
 */
function edit() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
    } else {

        var sjly = rows[0]["sjly"];
        if ("1" == sjly) {
            showAlertDivLayer("审核流程过来的记录不能修改！");
            return false;
        }

        var url = 'bjhdgl_bjhdjg.do?method=bjhdJgEdit&jgid=' + rows[0]["jgid"];
        var title = "班级活动结果修改";
        showDialog(title, 800, 550, url);
    }

}


/*
上传资料
 */
function upload() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要上传资料的记录！");
    } else {
        var url = 'bjhdgl_bjhdjg.do?method=bjhdJgUpload&jgid=' + rows[0]["jgid"];
        var title = "班级活动资料上传";
        showDialog(title, 800, 550, url);
    }

}

/**
 * 删除
 */
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
        return false;
    }
    for(var i=0;i<rows.length;i++){
        if (rows[i]["sjly"] == "1") {
            showAlertDivLayer("审核流程过来的记录不能删除！");
            return false;
        }
    }

    showConfirmDivLayer("您确定要删除选择的记录吗？", {
        "okFun" : function() {
            jQuery.post("bjhdgl_bjhdjg.do?method=bjhdJgDel", {
                    values : ids.toString()
                },
                function(data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
        }
    });
}


//dcglbh,导出功能编号
var DCGLBH = "sxzzjy_bjhdgl_bjhdjg.do";

//自定义导出 功能
function exportConfig() {
    //DCCLBH导出功能编号,执行导出函数
    customExport(DCGLBH, exprotData);
}

//导出方法
function exprotData() {
    setSearchTj();//设置高级查询条件
    var url = "bjhdgl_bjhdjg.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}







