 var gridSetting = {
	caption:"�α�״���б�",
	pager:"pager",
	url:"rcsw_dxsylbx_ylbxwhgl.do?method=cbzklxListManage&type=query",
	colList:[
	   {label:'�α�״������',name:'cbzkdm', index: 'cbzkdm',key:true,width:'50%'},
	   {label:'�α�״������',name:'cbzkmc', index: 'cbzkmc',width:'50%'}
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
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("rcsw_dxsylbx_ylbxwhgl.do?method=delCbzklx", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="�Ĳ�����Ա�����Ѿ�ʹ�ò���ɾ��!";
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