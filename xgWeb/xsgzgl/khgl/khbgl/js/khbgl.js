jQuery(function(){
	var gridSetting = {
		caption : "考核表列表",
	pager : "pager",
	url : "khglKhbgl.do?method=getKhbglList&type=query",
	colList : [ 
	   {label : 'khbid',name : 'khbid',index : 'khbid',key : true,hidden:true},
	   {label : 'pflx',name : 'pflx',index : 'pflx',hidden:true}, 
	   {label : 'sfnz',name : 'sfnz',index : 'sfnz',hidden:true}, 
	   {label : 'sfqy',name : 'sfqy',index : 'sfqy',hidden:true},
	   {label : '考核表名称',name : 'khbmc',index : 'khbmc',width : '20%'},
	   {label : '考核对象',name : 'khdxmc',index : 'khdxmc',width : '15%'},
	   {label : '创建时间',name : 'cjsj',index : 'cjsj',width : '12%'},
	   {label : '修改时间',name : 'xgsj',index : 'xgsj',width : '12%'},
	   {label : '试题数',name : 'sts',index : 'sts',width : '8%'},
	   {label : '是否启用',name : 'sfqymc',index : 'sfqymc',width : '8%', formatter:setColor}
	   ]
}
	var map = getSuperSearch();
	gridSetting["params"] = map;
	jQuery("#dataTable").initGrid(gridSetting);
})


function setColor(cellValue, rowObject) {
	var color;
	if (rowObject.sfqy == '1') {
		color = "have";
	} else {
		color = "non";
	}
	return value = "<font class='" + color + "'>" + cellValue + "</font>";
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add() {
	var url = "khglKhbgl.do?method=addKhb";
	var title = "增加考核表";
	showDialog(title, 350, 200, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer(jQuery("#lable_one_xg").val());
		return false;
	} 
	var url = 'khglKhbgl.do?method=updateKhb&khbid=' + rows[0]["khbid"];
	var title = "修改考核表";
	showDialog(title, 350, 200, url);
	
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer(jQuery("#lable_some_sc").val());
		return false;
	}
	showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
			"okFun" : function() {
				jQuery.post("khglKhbgl.do?method=delKhb", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});	
}

function khnrwh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您需要操作的记录！");
		return false;
	}
	var khbid=rows[0]["khbid"];
	var url="khglKhnrgl.do?method=getKhnrglList&khbid="+khbid;
	document.location.href=url;
}
//考核内容预览
function khnryl(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您需要操作的记录！");
		return false;
	}
	var khbid=rows[0]["khbid"];
	var khbmc=rows[0]["khbmc"];
	var url="khglKhbgl.do?method=khnryl&khbid="+khbid+"&khbmc="+khbmc;
	showDialog('考核内容预览',800,550,url);
}

function khbfz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您需要操作的记录！");
		return false;
	}
	var khbid=rows[0]["khbid"];
	var url="khglKhbgl.do?method=khbfz&khbid="+khbid;
	showDialog('考核表复制',350,200,url);
}
//考核表启用设置
function qySz(qyzt) {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var czmc=null;
	if (ids.length == 0) {
		showAlertDivLayer("请选择您需要操作的记录！");
		return false;
	}
	if("1"==qyzt){
		czmc="启用";
	}else{
		czmc="停用";
	}
	showConfirmDivLayer("您确定"+czmc+"选择的考核表吗？", {
			"okFun" : function() {
				jQuery.post("khglKhbgl.do?method=qySz", {
					values : ids.toString(),
					sfqy:qyzt
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});	
}
