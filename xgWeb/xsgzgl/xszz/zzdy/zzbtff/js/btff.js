var gridSetting = {
	caption : "���������б�",
	pager : "pager",
	url : "xszz_zzdyzzbtff.do?method=getBtffList&type=query",
	colList : [ 
	    	   {label : 'id',name : 'id',index : 'id',key : true,hidden:true},
	    	   {label : 'ѧ��',name : 'xh',index : 'xh',width : '15%' ,formatter : xhLink },
	    	   {label : '����',name : 'xm',index : 'xm',width : '8%'},
	    	   {label : 'ѧ��',name : 'pdxn',index : 'pdxn',width : '10%'},
	    	   {label : 'ѧ��',name : 'xqmc',index : 'xqmc',width : '10%'},
	    	   {label : '��Ŀ����',name : 'xmmc',index : 'xmmc',width : '10%'},
	    	   {label : 'ѧԺ',name : 'xymc',index : 'xymc',width : '20%'},
	    	   {label : '�����·�',name : 'ffyf',index : 'ffyf',width : '10%'},
	    	   {label : '���Ž��<Ԫ>',name : 'ffje',index : 'ffje',width : '10%'}
	    	   ]
}

jQuery(function() {
	gridSetting["params"]=getSuperSearch();
	jQuery("#dataTable").initGrid(gridSetting);
});
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewZzmd(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}
function viewZzmd(id) {
	showDialog("�������Ų鿴", 800, 500, "xszz_zzdyzzbtff.do?method=viewBtff&id="
			+ id);
}

function btff() {
	var url = 'xszz_zzdyzzbtff.do?method=btff';
	var title = "��������";
	showDialog(title, 450, 250, url);
}
//�Զ��嵼�� ����
var DCCLBH = "xszz_zzdy_zzbtff.do";
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

//��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xszz_zzdyzzbtff.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

