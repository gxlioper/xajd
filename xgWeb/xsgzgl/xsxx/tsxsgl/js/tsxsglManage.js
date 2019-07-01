var xslxdms = "";
var xslxmcs = "";
function init(){
				var xslxdm = $("xslxdm").value;
				var xslxList = xslxdm.split(",");
				for(var i=0;i<xslxList.length;i++){
					jQuery("input[type='checkbox'][name=xslxBoxList][value='"+xslxList[i]+"']").attr("checked",true);
				}	
			}

jQuery(function() {
	var gridSetting = {
		caption : "特殊学生列表",
		pager : "pager",
		url : "tsxs_tsxswh.do?method=tsxsglManage&doType=query",
		colList : [ {
			label : '学号',
			name : 'xh',
			index : 'xh',
			key:true,
			formatter : xhLink
		}, {
			label : '姓名',
			name : 'xm',
			index : 'xm'
		}, {
			label : '性别',
			name : 'xb',
			index : 'xb'
		}, {
			label : '年级',
			name : 'nj',
			index : 'nj'
		}, {
			label : jQuery("#xbmc").val(),
			name : 'xymc',
			index : 'xymc'
		}, {
			label : '行政班级',
			name : 'bjmc',
			index : 'bjmc'
		}, {
            label : '专业班级',
            name : 'zybjmc',
            index : 'zybjmc'
        },{
			label : '录入时间',
			name : 'lrsj',
			index : 'lrsj'
		}, {
			label : '学生类型',
			name : 'xslxdm',
			index : 'xslxdm',
			formatter : getXslxmc
		}, {
			label : '关注状态',
			name : 'gzztmc',
			index : 'gzztmc'
		} , {
            label : '书院',
            name : 'symc',
            index : 'symc'
        } ],
		sortname : "lrsj",
		sortorder : "desc"
	};
	jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs() {
	var map = getSuperSearch();

	jQuery("#dataTable").reloadGrid(map);
}
function xhLink(cellValue, rowObject) {
	return "<a href=\"javascript:void(0);\" class=\"name\" onclick=\"showDetail('"
			+ rowObject["xh"] + "');\">" + cellValue + "</a>";
}


function showDetail(xh) {
	showDialog("特殊学生详情", 700, 400,
			"tsxs_tsxswh.do?method=tsxsView&xh=" + xh);
}

function addTsxs() {
	showDialog("新增特殊学生", 700, 400, "tsxs_tsxswh.do?method=tsxsAdd");

}

function updateTsxs() {
	var xh = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length != 1) {
		showAlert("请选择一条您要修改的记录！");
		return false;
	} else {
		xh = rowsValue[0]["xh"];
	}
	showDialog("修改特殊学生", 700, 400,
			"tsxs_tsxswh.do?method=tsxsUpdate&xh=" + xh);
}

function deleteTsxs() {
	var  ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlert("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("tsxs_tsxswh.do?method=delTsxs",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
function save(){
 	if( jQuery("#xh").val()=="" ||jQuery("#clcs").val()==""  ||jQuery("#gzzt").val()=="" ||
 			jQuery("input[type='checkbox'][name='xslxBoxList']:checked").size()==0 || getXslx()==""){
 		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
 	}
	
	
	var url = "tsxs_tsxswh.do?method=tsxsSave";
	

	ajaxSubFormWithFun("TsxsglForm", url, function(data) {
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
function getXslx(){
	 var size = jQuery("input[type='checkbox'][name='xslxBoxList']:checked").size();
	 if(size == 0){
		showAlert('请选择学生类型！');
		return "";
	}
	
	var flag = false;
	jQuery("input[type='checkbox'][name=xslxBoxList]:checked").each(function(index){
		if(flag){
			xslxdms += ",";
			xslxmcs += ",";
		}else{
			flag = true;
		}
	
		xslxmcs += jQuery(this).parent().children().eq(-1).html();
		xslxdms += jQuery(this).val();
	});
	jQuery("#xslxdm").val(xslxdms);
	jQuery("#xslxmc").val(xslxmcs);
}
function getXslxmc(cellValue, rowObject) {
	var xslxmc = '';
	jQuery.ajaxSetup( {
		async : false
	});
	jQuery.post("tsxs_xslxwh.do?method=getXslxmc", {
		xslxdm : cellValue
	}, function(data) {
		xslxmc = data;
	}, '');
	jQuery.ajaxSetup( {
		async : true
	});
	return xslxmc;
}

function delValidate(rowsValue) {
	var flag = false;
	for ( var i = 0; i < rowsValue.length; i++) {
		jQuery.ajaxSetup( {
			async : false
		});
		jQuery.post("xlzx_thjl.do?method=haveThjlFlagByXh", {
			xh : rowsValue[i]["xh"]
		}, function(data) {
			flag = data;
		}, '');
		jQuery.ajaxSetup( {
			async : true
		});
		if (flag == "true") {
			break;
		}
	}
	return flag;
}

function setTsxsGzzt() {
	
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlert("请选择您要设置的记录！");
	} else {
		showDialog("特殊学生状态设置", 380, 200,
				"tsxs_tsxswh.do?method=setTsxsGzzt&ids=" + ids);
	}
}
var dcclbh="tsxsgl_tsxswh.do";
function exportTsxsList() {
	customExport(dcclbh, exportTsxsData, 700, 500);
}

// 导出方法
function exportTsxsData() {
	setSearchTj();// 设置高级查询条件
	var url = "tsxs_tsxswh.do?method=exportTsxsData&dcclbh="
			+ dcclbh;// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//导入
function drxx(){
	toImportDataNew("IMPORT_XSXX_TSXS");
	return false;
}

