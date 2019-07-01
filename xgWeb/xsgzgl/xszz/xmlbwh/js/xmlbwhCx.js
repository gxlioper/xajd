var gridSetting = {
	caption:"��Ŀ����б�",
	pager:"pager",
	url:"xszz_xmlbwh.do?method=xmlbwhCx&type=query",
	colList:[
	   {label:'������',name:'lbdm', index: 'lbdm',key:true,hidden:true},
	   {label:'�������',name:'lbmc', index: 'lbmc',width:'35%'},
	   {label:'��Ŀ˵��',name:'lbsm', index: 'lbsm',width:'65%'}
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
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xszz_xmlbwh.do?method=xmlbwhXg&lbdm='+rows[0]["lbdm"];
		var title = "��Ŀ�������޸�";
		showDialog(title,390,230,url);
	}
}

function add(){
	var url = "xszz_xmlbwh.do?method=xmlbwhZj";
	var title = "��Ŀ����������";
	showDialog(title,390,230,url);
}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��",{"okFun":function(){
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
