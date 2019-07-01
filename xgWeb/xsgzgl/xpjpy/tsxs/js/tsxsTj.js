var tjszDialog ;
var gridSetting = {
	caption : "项目列表",
	pager : "pager",
	url : "xpj_tsxs.do?method=viewTsxsTj&type=query",
	colList : [
  	    {label : '学期代码',name : 'xq',index : 'xq',hidden : true},
	    {label : '类型代码',name : 'lxdm',index : 'lxdm',hidden : true},
		{label : '学年',name : 'xn',index : 'xn',width : '30%'}, 
		{label : '学期',name : 'xqmc',index : 'xqmc',width : '20%'},
		{label : '特殊类型',name : 'lxmc',index : 'lxmc',width : '30%'},
		{label : '总人数',name : 'zrs',index : 'zrs',width : '20%',formatter:setZrs}
		],
	sortname : "xn,xq,lxdm",
	sortorder : "desc"
}

jQuery(function() {
	jQuery("#dataTable").initGrid(gridSetting);
});


function setColor(value,status){
	var color = "#0000ff";
	return value = "<font color='"+color+"'>" + value + "</font>";
}

/*
 *总人数
 */
function setZrs(cellValue,rowObject){
	var xn = rowObject.xn;
	var xq = rowObject.xq;
	var lxdm = rowObject.lxdm;
	var value = rowObject.zrs;
//	value = setColor(value,status);//
	value = "<a  href='javascript:void(0);' class='name' onclick='return tsxsXx(\""+xn+"\",\""+xq+"\",\""+lxdm+"\");'>"+value+"</a>";
	return value;
}

/*
 * 特殊学生信息
 */
function tsxsXx(xn,xq,lxdm){
	var url = 'xpj_tsxs.do?method=tsxsXx';
	url += "&xn=" + xn;
	url += "&xq=" + xq;
	url += "&lxdm=" + lxdm;
	var title = "特殊学生信息";
	showDialog(title, 800, 505, url,{close:function(){query();}});
}

function query(){
	var map = {};
	map["xn"] = jQuery("#xn").val();
	map["xq"] = jQuery("#xq").val();
	jQuery("#dataTable").reloadGrid(map);
}

function add(mklx) {
	
	var url = "xpj_tsxs.do?method=tsxsZj&mklx="+mklx;
	var title = "特殊学生增加";
	showDialog(title, 800,550, url,{close:function(){query();}});
}

function del() {
	var result = false;// jQuery跳出循环
	var xn = "";
	var xq = "";
	var lxdm = "";
	var flag = false;

	var rows = jQuery("#dataTable").getSeletRow();
	var json = "{data:[";
	for ( var i = 0; i < rows.length; i++) {
		if (flag) {
			json += ",";
		} else {
			flag = true;
		}
		json += "{";
		json += "xn:'" + rows[i].xn + "'";
		json += ",xq:'" + rows[i].xq + "'";
		json += ",lxdm:'" + rows[i].lxdm + "'";
		json += "}";
	}
	json += "]}";
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("是否确定删除勾选的记录？",{"okFun":function(){
			var url = "xpj_tsxs.do?method=tsxsScForLx";
			jQuery.post(url, {
				json : json
			}, function(data) {
				if(data["success"] == false){
					showAlertDivLayer(data["message"]);
				}else{
					showAlertDivLayer(data["message"], {},{"clkFun": function(tag) {
						jQuery("#dataTable").reloadGrid();
					}});
				}
			}, 'json');
		}});
	}
}

//导入
function dr() {
	// 调用通用的导入function，参数是导入功能模块代码。
	toImportDataNew("IMPORT_TSXSDR_10787");
	return false;

}

