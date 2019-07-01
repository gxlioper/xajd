 var gridSetting = {
	caption:"参保状况列表",
	pager:"pager",
	url:"rcsw_dxsylbx_ylbxwhgl.do?method=cbzklxListManage&type=query",
	colList:[
	   {label:'参保状况代码',name:'cbzkdm', index: 'cbzkdm',key:true,width:'50%'},
	   {label:'参保状况名称',name:'cbzkmc', index: 'cbzkmc',width:'50%'}
	],
	sortname: "cbzkdm",
 	sortorder: "asc"
} 

jQuery(function(){
	jQuery("#dataTable").initGrid(gridSetting);
});

function query(){
	var map = {};
	var cbzkmc = jQuery("#cbzkmc").val();
	map["cbzkmc"] = jQuery.trim(cbzkmc);
	jQuery("#dataTable").reloadGrid(map);
}

function delCbzklx() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("rcsw_dxsylbx_ylbxwhgl.do?method=delCbzklx", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="的补助人员类型已经使用不能删除!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}




function newChgCode(obj){
	allNotEmpThenGo(obj.id);
}