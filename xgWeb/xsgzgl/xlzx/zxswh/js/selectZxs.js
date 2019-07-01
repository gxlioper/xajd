var gridSetting = {
	caption:"咨询师列表",
	pager:"pager",
	url:"xlzx_zxs.do?method=getZxsInfo&doType=query",
	colList:[
	   {label:'职工号',name:'zgh', index: 'zgh',key:true},
	   {label:'姓名',name:'xm', index: 'xm'},
	   {label:'性别',name:'xb', index: 'xb'},
	   {label:'年龄',name:'age', index: 'age'},
	   {label:'联系电话',name:'lxdh', index: 'lxdh'},
	   {label:'部门',name:'bmmc', index: 'bmmc'},
	   {label:'操作',name:'zgh', index: '',noSort:true,formatter:function(cell,rowObject){
		   return "<label class='btn_01' onclick=\"selectZxs('"+cell+"');\">选择</label>";
	   }}
	],
	sortname: "zgh",
 	sortorder: "asc",
 	multiselect:false,
 	radioselect:true
};

jQuery(function(){
	jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs(){
	var map = getSuperSearch();

	jQuery("#dataTable").reloadGrid(map);
}


function selectZxs(zgh){
	var gotoPath = jQuery("#gotoPath").val();
	if (gotoPath.split("?").length > 1){
		gotoPath = gotoPath+"&doType=add&zgh="+zgh;
	} else {
		gotoPath = gotoPath+"?doType=add&zgh="+zgh;
	}
	var api = frameElement.api;
	if (api){
		if (api.get('childDialog')){
			api.reload(api.get('parentDialog') ,gotoPath);
		} else {
			var W = api.opener;
			W.location=gotoPath;			
		}
	} else if (parent.window){
		parent.window.document.location=gotoPath;						
	}
	
	iFClose();
}
			