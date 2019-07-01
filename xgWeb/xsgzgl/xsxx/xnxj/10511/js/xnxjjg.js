var DCCLBH = "xsxx_xnxj_xjjg.do";//dcclbh,导出功能编号
var gridSetting = {
	caption : "学年小结结果列表",
	pager : "pager",
	url : "xsxx_xnxj_xjjggl.do?method=viewXnxjjgList&type=query",
	colList : [ {
		label : 'id',
		name : 'id',
		index : 'id',
		width : '10%',
		key : true,
		hidden : true
	}, {
		label : '学号',
		name : 'xh',
		index : 'xh',
		width : '15%',
	    formatter:xhLink
	}, {
		label : '姓名',
		name : 'xm',
		index : 'xm',
		width : '15%'
	},{
		label : '班级',
		name : 'bjmc',
		index : 'bjdm',
		width : '20%'
	}, {
		label : '学年',
		name : 'xn',
		index : 'xn',
		width : '10%'
	}, {
		label : 'sjly',
		name : 'sjly',
		index : 'sjly',
		hidden : true
	},{
		label : '填写时间',
		name : 'txsj',
		index : 'txsj',
		width : '10%'
	}

	],
	sortname : "txsj",
	sortorder : "asc"
}



/**
 * 学号链接
 * @param cellValue
 * @param rowObject
 * @return
 */

function xhLink(cellValue,rowObject){
	//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
	if(rowObject['id'] == null || rowObject['id'] == '' || rowObject['id'] == undefined){
		return cellValue;
	}
	var onclickfn = "onclick=\"" + "showDialog('学生详细信息' , 720 , 480 , 'xsxx_xnxj_xjjggl.do?method=xnxjjdckAll&xh=" + cellValue + "')" + "\"";
	
	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
	
	return el;
}

jQuery(function() {
	gridSetting["params"]=getSuperSearch();
	jQuery("#dataTable").initGrid(gridSetting);
});

function add() {
	var url = "xsxx_xnxj_xjjggl.do?method=xnxjjdadd";
	var title = "填写学年小结";
	showDialog(title, 720, 500, url);
}

function saveXnxj(){
	var xh = jQuery('#xh').val();
	var xn = jQuery('#xn').val();
	var xjnr = jQuery('#xjnr').val();

	if(xh == '' || xn == '' || xjnr == ''){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}

	//小结内容不得超过2000字符
	var obj = document.getElementById("xjnr");
	if(obj.value.length > 2000){
		showAlertDivLayer("小结内容最大字长度为"+2000+",现已经超过，请确认！");
		obj.focus();
		return false;
	}
	
	var url = "xsxx_xnxj_xjjggl.do?method=doXnxjjgtx";
	
	ajaxSubFormWithFun("xnxjjgForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
	
}

function updateXnxj(){
	var xh = jQuery('#xh').val();
	var xjnr = jQuery('#xjnr').val();

	if(xh == '' || xjnr == ''){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}

	//小结内容不得超过2000字符
	var obj = document.getElementById("xjnr");
	if(obj.value.length > 2000){
		showAlertDivLayer("小结内容最大字长度为"+2000+",现已经超过，请确认！");
		obj.focus();
		return false;
	}
	var url = "xsxx_xnxj_xjjggl.do?method=doXnxjjgxg";
	
	ajaxSubFormWithFun("xnxjjgForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
	
}

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(rows[0]["sjly"]=='1'){
			showAlertDivLayer("您选择的记录为审核过来的记录，不能修改，请重新选择！");
			return false;
		}
		
		var url = 'xsxx_xnxj_xjjggl.do?method=xnxjjgupdate&id=' + rows[0]["id"]
		var title = "修改学年小结信息";
		showDialog(title, 720, 480, url);
	}

}
function del() {
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']!='0'){
				showAlertDivLayer("所选的记录中包含审核过来的记录，不能删除，请重新选择！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xsxx_xnxj_xjjggl.do?method=doXnxjjgsc", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

function ck(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要查看的记录！");
		return false;
	} 
		
		var url = 'xsxx_xnxj_xjjggl.do?method=xnxjjdck&xh=' + rows[0]["xh"]+'&xn=' + rows[0]["xn"]
		var title = "查看学年小结信息";
		showDialog(title, 720, 480, url);
}

// 打印报表
function printXnxjSq(url) {
	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <=0) {
		showAlertDivLayer("请选择一条记录！");
	} else {
		var id = jQuery("#dataTable").getSeletIds();
		var url = url + "&id=" +id;
		window.open(url);
	}
}

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "xsxx_xnxj_xjjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
