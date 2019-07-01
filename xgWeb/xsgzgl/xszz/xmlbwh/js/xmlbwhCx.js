var gridSetting = {
	caption:"项目类别列表",
	pager:"pager",
	url:"xszz_xmlbwh.do?method=xmlbwhCx&type=query",
	colList:[
	   {label:'类别代码',name:'lbdm', index: 'lbdm',key:true,hidden:true},
	   {label:'类别名称',name:'lbmc', index: 'lbmc',width:'35%'},
	   {label:'项目说明',name:'lbsm', index: 'lbsm',width:'65%'}
	],
	sortname: "lbmc",
 	sortorder: "asc"
}


jQuery(function(){
	jQuery("#dataTable").initGrid(gridSetting);
});

function query(){
	var map = {};
	map["lbmc"] = jQuery.trim(jQuery("#lbmc").val());
	jQuery("#dataTable").reloadGrid(map);
}

function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xszz_xmlbwh.do?method=xmlbwhXg&lbdm='+rows[0]["lbdm"];
		var title = "项目类别代码修改";
		showDialog(title,390,230,url);
	}
}

function add(){
	var url = "xszz_xmlbwh.do?method=xmlbwhZj";
	var title = "项目类别代码增加";
	showDialog(title,390,230,url);
}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("是否确定删除勾选的记录？",{"okFun":function(){
			var url = "xszz_xmlbwh.do?method=xmlbwhSc";
			jQuery.post(url,{values:ids.toString()},function(data){
				if(data["success"] == false){
					showAlertDivLayer(data["message"]);
				}else{
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}
			},'json');
		}});
	}
}
