var gridSetting = {
	caption:"��ѯ���",
	pager:"pager",
	url:"xtwh_yhsjfw.do?method=yhsjfwCx&type=query",
	colList:[
	   {label:'�û���',name:'yhm', index: 'yhm',width:'8%',key : true},
	   {label:'����',name:'xm', index: 'xm',width:'10%'},
	   {label:'������',name:'zmc', index: 'zmc',width:'10%'},
	   {label:'��������',name:'bmmc', index: 'bmmc',width:'15%'},
	   {label:'����Ա',name:'fdynum', index: 'fdynum',width:'3%',formatter:setSffdyBzr},
	   {label:'������',name:'bzrnum', index: 'bzrnum',width:'3%',formatter:setSffdyBzr},
	   {label:'����Ȩ��Χ',name:'sjfwmc', index: 'sjfwmc',noSort:true}
	],
	sortname: "yhm",
 	sortorder: "asc"
}


jQuery(function(){
	jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs(){
	var map = {};
	map["yhm"] = jQuery.trim(jQuery("#yhm").val());
	map["xm"] = jQuery.trim(jQuery("#xm").val());
	map["zdm"] = jQuery("#zdm").val();
	map["szbm"] = jQuery("#szbm").val();
	map["sffdy"] = jQuery("#sffdy").val();
	map["sfbzr"] = jQuery("#sfbzr").val();
	jQuery("#dataTable").reloadGrid(map);
}

/*
 * �����Ƿ񸨵�Ա��������
 */
function setSffdyBzr(cellValue,rowObject){
	var value;
	if(cellValue == null || cellValue == ""){
		value = "��";
	}else{
		value = "��";
	}
	return value;
}

function yhsjfwSq(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	}else{
		var url = "xtwh_yhsjfw.do?method=yhsjfwSq";
		url += "&ids=" + ids.toString();
		var title = "�û����ݷ�Χ��Ȩ";
		showDialog(title,815,550,url);
	}
}
