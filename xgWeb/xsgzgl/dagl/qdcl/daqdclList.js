var gridSetting = {
	caption:"档案清单材料列表",
	pager:"pager",
	url:"daqdcl.do?method=daqdclList&type=query",
	colList:[
	   {label:'档案清单材料编号',name:'daqdcl_id', index: 'daqdcl_id',hidden:true},
	   {label:'档案清单材料名称',name:'daqdcl_mc', index: 'daqdcl_mc'},
	   {label:'绑定模板数',name:'ybdmbs', index: 'ybdmbs'},
	   {label:'绑定学生数',name:'ybdxss', index: 'ybdxss'}
	],
	sortname: "",
 	sortorder: ""
};

jQuery(function(){
	jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs(){
	var map = getSuperSearch();

	jQuery("#dataTable").reloadGrid(map);
}

function addDaqdcl(){
	showDialog("增加档案清单材料",420,180,"daqdcl.do?method=addDaqdcl");			
}

function updateDaqdcl(){
	var daqdclId= '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	 if(rowsValue.length != 1){
		 showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	}else{
		if(rowsValue[0]["ybdmbs"]!=0){
			showAlertDivLayer("您选择的记录已绑定模板，不能修改！");
			return false;
		}
		if(rowsValue[0]["ybdxss"]!=0){
			showAlertDivLayer("您选择的记录已绑定学生，不能修改！");
			return false;
		}
		daqdclId = rowsValue[0]["daqdcl_id"];
	}
	showDialog("修改档案清单材料",420,180,"daqdcl.do?method=updateDaqdcl&daqdcl_id="+daqdclId);
}

function delDaqdcl(){
	var delDaqdclId = '';	
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	}
	for(var i=0;i<rowsValue.length;i++){
		if(rowsValue[i]["ybdmbs"]!=0){
			showAlertDivLayer("您选择的记录已绑定模板，不能删除！");
			return false;
		}
		if(rowsValue[i]["ybdxss"]!=0){
			showAlertDivLayer("您选择的记录已绑定学生，不能删除！");
			return false;
		}
		if(i==(rowsValue.length-1)){
			delDaqdclId += rowsValue[i]["daqdcl_id"];
		}else{
			delDaqdclId += rowsValue[i]["daqdcl_id"]+",";
		}
	}
	confirmInfo("您确定要删除<font color='red'>"+rowsValue.length +"</font>条记录吗?",function(ty){
		if(ty=="ok"){
			jQuery.post("daqdcl.do?method=delDaqdcl",{daqdclIds:delDaqdclId},function(data){
				alertInfo(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}
	});
}

			
