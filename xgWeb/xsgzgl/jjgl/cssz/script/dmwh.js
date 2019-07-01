var gridSetting = {
				caption:"",
				pager:"pager",
				url:"jjgl_cssz.do?method=queryDataList&qryType=jjxk",
				radioselect:true,
				colList:[
				   {label:'学科代码',name:'jjxkdm', index: 'jjxkdm',width:'40%',key:true},
				   {label:'学科名称',name:'jjxkmc', index: 'jjxkmc',width:'60%'},
				   {name:'jjxkdm', index: 'dm',hidden:true}
				   
				],
				sortname: "jjxkdm",
			 	sortorder: "asc"
			};


function dcmcLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
}


/**
 * 页签切换
 * @return
 */
function selectTab(obj,query_type){
	jQuery('#hiddenQryType').val(query_type);
	gridSetting['url'] =  "jjgl_cssz.do?method=queryDataList&qryType=" + query_type;
	if (query_type == "jjxk"){
		gridSetting['sortname'] = 'jjxkdm';
		gridSetting['sortorder'] = 'asc';
		gridSetting['colList'] = [
			 {label:'学科代码',name:'jjxkdm', index: 'jjxkdm',width:'40%',key:true},
			 {label:'学科名称',name:'jjxkmc', index: 'jjxkmc',width:'60%'},
			 {name:'jjxkdm', index: 'dm',hidden:true}
		];  
		
		jQuery("#dataTable").initGrid(gridSetting);
	} else if(query_type == "jjnj"){
		gridSetting['sortname'] = 'jjnjdm';
		gridSetting['sortorder'] = 'asc';
		gridSetting['colList'] = [
			 {label:'年级代码',name:'jjnjdm', index: 'jjnjdm',width:'40%',key:true},
			 {label:'年级名称',name:'jjnjmc', index: 'jjnjmc',width:'60%'},
			 {name:'jjnjdm', index: 'dm',hidden:true}
		]; 
		jQuery("#dataTable").initGrid(gridSetting);
	} else if(query_type == "sfbz"){
		gridSetting['sortname'] = 'jjxkdm';
		gridSetting['sortorder'] = 'asc';
		gridSetting['colList'] = [
			 {name:'id', index: 'id',key:true,hidden:true},
			 {label:'学科名称',name:'jjxkmc', index: 'jjxkmc',width:'40%'},
			 {label:'年级名称',name:'jjnjmc', index: 'jjnjmc',width:'40%'},
			 {label:'收费标准',name:'sfbz', index: 'sfbz',width:'20%'},
			 {name:'id', index: 'dm',hidden:true}
		]; 
		jQuery("#dataTable").initGrid(gridSetting);
	} else if(query_type == "fbzg"){
		gridSetting['sortname'] = 'fbzgdm';
		gridSetting['sortorder'] = 'asc';
		gridSetting['colList'] = [
			 {label:'代码',name:'fbzgdm', index: 'fbzgdm',width:'10%',key:true},
			 {label:'名称',name:'fbzgmc', index: 'fbzgmc',width:'20%'},
			 {label:'费用设定',name:'fy', index: 'fy',width:'10%'},
			 {label:'描述',name:'fbzgms', index: 'fbzgms',width:'60%'},
			 {name:'fbzgdm', index: 'dm',hidden:true}
		]; 
		jQuery("#dataTable").initGrid(gridSetting);
	}
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

//查询
function query(){
	var map = {};
	map["ffxmmc"] = jQuery("#ffxmmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	
	var url = "jjgl_cssz.do?method=add&qryType=" + jQuery('#hiddenQryType').val();
	var title = "增加";
	showDialog(title,500,250,url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var type = jQuery('#hiddenQryType').val();
		var url = "jjgl_cssz.do?method=update&qryType=" + jQuery('#hiddenQryType').val() + '&dm='+jQuery("#dataTable").getSeletIds()[0];
		var title = "修改";
		showDialog(title,500,250,url);
	}
}


//删除
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var type = jQuery('#hiddenQryType').val();
	var url = '';
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("jjgl_" + type + ".do?method=delete",{delIds:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}