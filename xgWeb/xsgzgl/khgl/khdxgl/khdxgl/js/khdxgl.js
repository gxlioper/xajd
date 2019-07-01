var gridSetting = {
	caption : "考核对象列表",
	pager : "pager",
	url : "khglKhdxgl.do?method=getKhdxglList&type=query",
	colList : [ 
	   {label : 'khdxid',name : 'khdxid',index : 'khdxid',key : true,hidden:true},
	   {label : 'khlx',name : 'khlx',index : 'khlx',hidden:true}, 
	   {label : 'sfnz',name : 'sfnz',index : 'sfnz',hidden:true}, 
	   {label : '考核对象名称',name : 'khdxmc',index : 'khdxmc',width : '25%'}, 
	   {label : '类型',name : 'khlxmc',index : 'khlxmc',width : '25%'}, 
	   {label : '用户数',name : 'yhs',index : 'yhs',width : '25%',formatter:yhsLink},
	   {label : '是否内置',name : 'sfnzmc',index : 'sfnzmc',width : '10%'} 
	   ],
	sortname : "sfnz",
	sortorder : "desc"
}

function yhsLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='yhck(\""+rowObject["khdxid"]+"\",\""+rowObject["khlx"]+"\",\""+rowObject["sfnz"]+"\",\""+rowObject["khdxmc"]+"\");'>"+cellValue+"</a>";
}
//考核对象详情查看
function yhck(khdxid,khlx,sfnz,khdxmc) {
	showDialog("考核对象查看", 750, 550, "khglKhdxgl.do?method=viewKhdxList&khdxid="
			+ khdxid + "&khlx=" + khlx+"&sfnz="+sfnz+"&khdxmc="+khdxmc);
}

jQuery(function() {
	jQuery("#dataTable").initGrid(gridSetting);
});

function query() {
	var map = {};
	map["khdxmc"] = jQuery("#khdxmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

function add() {
	var url = "khglKhdxgl.do?method=addKhdx";
	var title = "增加考核对象";
	showDialog(title, 350, 200, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	var sfqy="1";
	if (rows.length != 1) {
		showAlertDivLayer(jQuery("#lable_one_xg").val());
		return false;
	} 
	//内置对象不允许修改
	if ('2'==rows[0]["sfnz"]) {
		showAlertDivLayer("内置对象不允许修改！");
		return false;
	}
	var yhs=rows[0]["yhs"];
	if("0"==yhs){
		sfqy="0";
	}
	var url = 'khglKhdxgl.do?method=updateKhdx&khdxid=' + rows[0]["khdxid"]+"&sfqy="+sfqy;
	var title = "修改考核对象";
	showDialog(title, 350, 200, url);
	
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer(jQuery("#lable_some_sc").val());
		return false;
	}
	for ( var int = 0; int < rows.length; int++) {
		if('2'==rows[int]["sfnz"]){
			showAlertDivLayer("内置对象不允许删除！");
			return false;
		}
	}
		showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
			"okFun" : function() {
				jQuery.post("khglKhdxgl.do?method=delKhdx", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});	
}
function khdxwh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您需要操作的记录！");
		return false;
	}
	//内置对象不允许维护
	if ('2'==rows[0]["sfnz"]) {
		showAlertDivLayer("内置对象不允许修改人员！");
		return false;
	}
	var khlx=rows[0]["khlx"];
	var khdxid=rows[0]["khdxid"];
	var url="khglKhdxgl.do?method=showKhdx&khlx="+khlx+"&khdxid="+khdxid;
	showDialog('考核对象维护',800,600,url);
}
