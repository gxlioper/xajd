/**
 * 志愿服务申请相关js
 */

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


/**
 * 新增的保存
 */
var checkId = 'hdzt-hdrq-bhmd-bhyc-bhsj-dd';

function ztbhJgSave() {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}

	var url = "ztbhgl_bzrztbhjg.do?method=ztbhJgSave";
	ajaxSubFormWithFun("ZtbhJgForm", url, function(data) {
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
function ztbhJgSaveForEdit() {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	var url = "ztbhgl_bzrztbhjg.do?method=ztbhJgSaveForEdit";
	ajaxSubFormWithFun("ZtbhJgForm", url, function(data) {
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


function ztbhJgSaveForupload() {

    var url = "ztbhgl_bzrztbhjg.do?method=ztbhJgSaveForEdit";
    ajaxSubFormWithFun("ZtbhJgForm", url, function(data) {
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


function rdsfxztbh() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("请选择一条您要认定示范性主题班会的记录！");
    }
    if (rows.length == 1) {
        var url = 'ztbhgl_bzrztbhjg.do?method=rdsfxztbh&jgid=' + rows[0]["jgid"];
        var title = "认定示范性主题班会";
        showDialog(title, 400, 250, url);
    }
    else {
        showDialog("批量认定", 500, 250, "ztbhgl_bzrztbhjg.do?method=ztbhJgPlrd");
    }

}



function saveSfsfx() {

    var url = "ztbhgl_bzrztbhjg.do?method=ztbhJgSaveForEdit";
    ajaxSubFormWithFun("ZtbhJgForm", url, function(data) {
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


function saveForPlRd(sfsfx) {

    var rows = jQuery("#dataTable").getSeletRow();
    var jgids = new Array();

    jQuery.each(rows, function(i, row) {
        jgids.push(row["jgid"]);
    });
    jQuery.post("ztbhgl_bzrztbhjg.do?method=saveForPlRd", {
        jgids : jgids,
        sfsfx : sfsfx
    }, function(data) {
        showAlertDivLayer(data["message"], {}, {
            "clkFun" : function() {
                jQuery("#dataTable").reloadGrid();
            }
        });
    }, 'json');
}


/**
 * 申请弹框页面
 */
function add() {
	var url = "ztbhgl_bzrztbhjg.do?method=ztbhJgAdd";
	showDialog("主题班会结果添加", 800, 550, url);
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

        var url = 'ztbhgl_bzrztbhjg.do?method=ztbhJgEdit&jgid=' + rows[0]["jgid"];
        var title = "主题班会结果修改";
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

        // var sjly = rows[0]["sjly"];
        // if ("1" == sjly) {
        //     showAlertDivLayer("审核流程过来的记录不能修改！");
        //     return false;
        // }

        var url = 'ztbhgl_bzrztbhjg.do?method=ztbhJgUpload&jgid=' + rows[0]["jgid"];
        var title = "主题班会资料上传";
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
            jQuery.post("ztbhgl_bzrztbhjg.do?method=ztbhJgDel", {
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
var DCGLBH = "sxzzjy_ztbhgl_bzrztbhjg.do";

//自定义导出 功能
function exportConfig() {
    //DCCLBH导出功能编号,执行导出函数
    customExport(DCGLBH, exprotData);
}

//导出方法
function exprotData() {
    setSearchTj();//设置高级查询条件
    var url = "ztbhgl_bzrztbhjg.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

var i = 0;//行号
var clickRowId;//点击的行号
var table;
function onShow(type){
    table = jQuery("#shlccx_table");

    if("update"==type){
        jQuery.post("ztbhgl_bzrztbhjg.do?method=getBjxxUpdate",{jgid:jQuery("#jgid").val()},function(data){
            if(data.length > 0){
                for(var a=0;a<data.length;a++){
                    addTr();

                    jQuery("#bjmc"+a).val(data[a].bjmc);
                    jQuery("#bjdm"+a).val(data[a].bjdm);
                    jQuery("#ydrs"+a).val(data[a].ydrs);
                    jQuery("#sdrs"+a).val(data[a].sdrs);
                    jQuery("#qqrs"+a).val(data[a].qqrs);
                    jQuery("#symc"+a).html(data[a].xymc);
                    jQuery("#fdyxm"+a).html(data[a].fdyxm);
                }
            }
        },'json');
    }
}
function addTr(){
    var tr;
    tr += "<tr id='"+i+"'>";
    tr += "<td><input type='text' id='bjmc"+i+"' readonly='readonly' class='text_nor'>";
    tr += "<input type='hidden' name='bjxxs["+i+"].bjdm' id='bjdm"+i+"' />";
    tr += "<button class=\"btn_01\" type=\"button\" onclick=\"butClick(this)\">选择</button>";
    tr += "</td>";
    tr += "<td><input name='bjxxs["+i+"].ydrs' id='ydrs"+i+"' maxlength='4' style='width: 50px;'></td>";
    tr += "<td><input name=\"bjxxs["+i+"].sdrs\" id='sdrs"+i+"' maxlength=\"4\" style=\"width: 50px;\"></td>";
    tr += "<td><input name=\"bjxxs["+i+"].qqrs\" id='qqrs"+i+"' maxlength=\"4\" style=\"width: 50px;\"></td>";
    tr += "<td><span id='symc"+i+"'></span></td>";
    tr += "<td><span id='fdyxm"+i+"'></span></td>";
    tr += "<td><a href='javascript:void(0);' onclick='delTr(this);return false;'>删除</a></td>";
    tr += "</tr>";
    table.append(tr);
    i++;
}
function butClick(tar){
    clickRowId = jQuery(tar).parent().parent().attr("id");
    showDialog('请选择一个班级',800,500,'ztbhgl_bzrztbhjg.do?method=getBj');
}
function delTr(td) {
    jQuery(td).parent().parent().remove();
    i--;
}

function setBjxxCallBack(row){
    jQuery("#bjmc"+clickRowId).val(row[0]['bjmc']);
    jQuery("#bjdm"+clickRowId).val(row[0]['bjdm']);
    jQuery("#ydrs"+clickRowId).val(row[0]['bjrs']);
    jQuery("#symc"+clickRowId).html(row[0]['xymc']);
    jQuery("#fdyxm"+clickRowId).html(row[0]['fdyxm']);
}


