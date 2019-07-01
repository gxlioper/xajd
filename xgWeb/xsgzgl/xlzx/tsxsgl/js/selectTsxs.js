
function getKnlxmc(cellValue,rowObject){
	var knlxmc='';
	jQuery.ajaxSetup({async:false});
	jQuery.post("xlzx_tsxs.do?method=getKnlxmc",{knlxdm:cellValue},function(data){
					knlxmc = data;
				},'');
	jQuery.ajaxSetup({async:true});
	return knlxmc;
}
jQuery(function(){
	var gridSetting = {
			caption:"����ѧ���б�",
			pager:"pager",
			url:"xlzx_tsxs.do?method=getTsxsInfo&doType=query",
			colList:[
			   {label:'ѧ��',name:'xh', index: 'xh',key:true},
			   {label:'����',name:'xm', index: 'xm'},
			   {label:'�Ա�',name:'xb', index: 'xb'},
			   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc'},
			   {label:'רҵ',name:'zymc', index: 'zymc'},
			   {label:'�༶',name:'bjmc', index: 'bjmc'},
			   {label:'��������',name:'knlxdm', index: 'knlxdm',formatter:getKnlxmc},
			   {label:'����',name:'xh', index: '',noSort:true,formatter:function(cell,rowObject){
				   return "<label class='btn_01' onclick=\"selectTsxs('"+cell+"');\">ѡ��</label>";
			   }}
			],
			sortname: "xgsj",
		 	sortorder: "desc",
			multiselect:false,
		 	radioselect:true
		};

	jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function getNewDate(cellValue){
	var newDate = cellValue.substring(0,10);
	return newDate;
}

function selectTsxs(xh){
	var gotoPath = jQuery("#gotoPath").val();
	if (gotoPath.split("?").length > 1){
		gotoPath = gotoPath+"&doType=add&xh="+xh;
	} else {
		gotoPath = gotoPath+"?doType=add&xh="+xh;
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