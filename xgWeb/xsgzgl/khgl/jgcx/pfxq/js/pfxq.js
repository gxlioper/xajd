
var gridSetting = {
		caption : "���˶����б�",
		radioselect:true,
		pager : "pager",
		url : "khglPfxq.do?method=khxqlList&type=query",
		colList : [ 
		   {label : 'xmid',name : 'xmid',index : 'xmid',hidden:true},
		   {label : 'khbid',name : 'khbid',index : 'khbid',hidden:true}, 
		   {label : 'pfr',name : 'pfr',index : 'pfr',hidden:true},
		   {label : 'khdxr',name : 'khdxr',index : 'khdxr',hidden:true},
		   {label : '��Ŀ����',name : 'xmmc',index : 'xmmc',width : '20%'}, 
		   {label : '���˱�',name : 'khbmc',index : 'khbmc',width : '20%'}, 
		   {label : '���˶���',name : 'khdxmc',index : 'khdxmc',width : '10%'},
		   {label : '������',name : 'pfrxm',index : 'pfrxm',width : '10%'},
		   {label : '����ʱ��',name : 'tjsj',index : 'tjsj',width : '15%'},
		   {label : '�ܷ�',name : 'zf',index : 'zf',width : '5%'}
		   ],
		sortname : "tjsj",
		sortorder : "desc"
	}

jQuery(function() {
	jQuery("#dataTable").initGrid(gridSetting);
});

//�߼���ѯ
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
			
/**
 * ��������
 * @return
 */
function viewPf(){
	
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	} else {
		showDialog("�鿴",800,520,"khglKhpf.do?method=viewPf&xmid="+rows[0]["xmid"]+"&khbid="+rows[0]["khbid"]+"&khdxr="+rows[0]["khdxr"]);
	}
	
}


//����
function exportConfig(){
	var DCCLBH='khgl_pfxq.do';
	customExport(DCCLBH, exportData);
}

function exportData(){
	var DCCLBH='khgl_pfxq.do';
	setSearchTj();//���ø߼���ѯ����
	var url = "khglPfxq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}