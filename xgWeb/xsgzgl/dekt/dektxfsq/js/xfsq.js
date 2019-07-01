function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

var DCCLBH = "dekt_xfsq_list.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportDatas);
}

//导出方法
function ExportDatas() {
	setSearchTj();//设置高级查询条件
	var url = "dekt_xfsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//删除
function del() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	 }
	var flag = false;
	var ids = jQuery("#dataTable").getSeletIds();
	showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("qgzx_jtff.do?method=jtffDel",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	
}

//增加页面跳转
function add(){
	showDialog('津贴发放增加',600,450,'qgzx_jtff.do?method=jtffAdd');
}


//编辑页面跳转
function edit(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	} else {
		showDialog('津贴发放修改',600,450,'qgzx_jtff.do?method=jtffEdit&id=' + rows[0]["id"]);
	}
}

//保存
function save(url,checkId) {
	if (!checkNull(checkId)) {
		return false;
	}
	lock();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data["success"] == "false"){
				showAlert(data["message"]);
			} else {
				showAlert(data["message"], {}, {	"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}});
			}
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
	unlock();
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_JTFF");
	return false;
}

//个性化导入
function xfsqZdyImport(){
    var drmkdm = 'IMPORT_DEKT_XFSQ';
    var url = "dekt_xfsq.do?method=xfsqZdyImport" + "&drmkdm=" + drmkdm;
    showDialog('导入',720,580,url,{close:function(){
        if (jQuery("#search_go")){
            jQuery("#search_go").click();
        }
    }});
    return false;
}

/**
 * 提交
 */
function submitBusi() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length != 1) {
        showAlertDivLayer("请选择一条您要提交的记录！");
        return false;
    }
    var rows = jQuery("#dataTable").getSeletRow();
    var xh = rows[0]["xh"];
    if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
        showAlertDivLayer("请选择未提交或者已退回的记录！");
        return false;
    }

    showConfirmDivLayer("您确定要提交选择的记录吗？", {
        "okFun" : function() {
            jQuery.post("dekt_xfsq.do?method=xfSqSubmit", {
                sqid : ids.toString(),
				xh:xh
            }, function(data) {
                showAlertDivLayer(data["message"]);
                jQuery("#dataTable").reloadGrid();
            }, 'json');
        }
    });
}

function loadRdnrbz(){
	jQuery.ajaxSetup( {
		async : false
	});
	jQuery("#rdnrbz").empty();
	jQuery("#dj").empty();
	var lx=jQuery('#lx').val();
	var rdxm=jQuery('#rdxm').val();
	var option = "<option value=''></option>";
	if(rdxm!=null&&rdxm!=""){
		jQuery.post('dekt_xfsq.do?method=getRdnrbzList', {lx:lx,rdxm:rdxm},	function(data) {
			if (data != null && data.length != 0) {
				for ( var i = 0; i < data.length; i++) {
					option +="<option value='"+data[i].rdnrbz+"'>"	+data[i].rdnrbz+"</option>";
				}
			}
		},'json');
	}
	jQuery('#rdnrbz').append(option);
	jQuery.ajaxSetup( {
		async : true
	});
}

function loadDj() {
	jQuery.ajaxSetup( {
		async : false
	});
	jQuery("#dj").empty();
	var lx=jQuery('#lx').val();
	var rdxm=jQuery('#rdxm').val();
	var rdnrbz=jQuery('#rdnrbz').val();
	var option = "<option value=''></option>";
	if(rdnrbz!=null&&rdnrbz!=""){
		jQuery.post('dekt_xfsq.do?method=getDjList', {lx:lx,rdxm:rdxm,rdnrbz:rdnrbz},	function(data) {
			if (data != null && data.length != 0) {
				for ( var i = 0; i < data.length; i++) {
					option +="<option value='"+data[i].dj+"'>"+data[i].dj+"</option>";
				}
			}
		},'json');
	}
	jQuery('#dj').append(option);
	jQuery.ajaxSetup( {
		async : true
	});
}


function viewXfsq(sqid, xh) {
	showDialog("审核查询", 700, 480, "dekt_xfsq.do?method=xfsqView&sqid=" + sqid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewXfsq(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue	+ "</a>";
}

function shLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (1!=ids.length){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {		
		showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}