var gridSetting = {
	caption:"��ʦ�б�",
	pager:"pager",
	url:"szdw_thjl.do?method=getJsInfo&doType=query",
	colList:[
	   {label:'ְ����',name:'zgh', index: 'zgh',key:true},
	   {label:'����',name:'xm', index: 'xm'},
	   {label:'�Ա�',name:'xb', index: 'xb'},
	   {label:'����',name:'age', index: 'age'},
	   {label:'��ϵ�绰',name:'lxdh', index: 'lxdh'},
	   {label:'����',name:'bmmc', index: 'bmmc'},
	   {label:'����',name:'zgh', index: '',noSort:true,formatter:function(cell,rowObject){
		   return "<label class='btn_01' onclick=\"selectJs('"+cell+"');\">ѡ��</label>";
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


function selectJs(zgh){
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
			