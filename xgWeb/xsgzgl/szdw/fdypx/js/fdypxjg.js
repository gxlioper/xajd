var gridSetting = {
	caption:"����Ա��ѵ����б�",
	pager:"pager",
	url:"szdw_fdypxxmsh.do?method=fdypxjgList&type=query",
	colList:[
	   {label:'ְ����',name:'sqr', index: 'sqr',width:'10%',formatter:zghLink},
	   {label:'����',name:'xm', index: 'xm',width:'10%'},
	   {label:'��������',name:'bmmc', index: 'bmmc',width:'20%'},
	   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'20%'},
	   {label:'��ѵ��Ŀ',name:'xmmc', index: 'xmmc',width:'20%'},
	   {label:'���ʱ��',name:'shsj', index: 'shsj',width:'20%'},
	   {label:'',name:'sqid', index: 'sqid',key:true,hidden:true}
	],
	sortname: "shsj",
 	sortorder: "asc"
}

function zghLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='viewjgz(\""+rowObject["sqr"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

function viewjgz(zgh){
	var url='szdw_dwwh.do?method=ckJzgxx&zgh='+zgh;
	showDialog('', 830, 500, url);
}


function fdyxxwhExportConfig() {
	customExport("szdw_fdypxxmsh.do", fdyxxwhExportData);
}

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

// ��������
function fdyxxwhExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "szdw_fdypxxmsh.do?method=fdypxjgExportData&dcclbh=" + "szdw_fdypxxmsh.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function go_ck(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	}  else{
		showDialog("����Ա��ѵ����鿴",760,500,'szdw_fdypxxmsh.do?method=fdypxxmsh&type=ck&sqid='+rows[0]["sqid"]+"&tt="+new Date().getTime());
	}
}