//�����ѯҳ��
var gridSetting = {
    pager:"pager",
	url:"kqgl_kqjl.do?method=viewKqjlList&type=query",
	colList:[
	    {label:'ѧ��',name:'xn', index: 'xn',width:'11%'},
	    {label:'ѧ��',name:'xq', index: 'xq',width:'10%'},	
		{label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
		{label:'����',name:'xm', index: 'xm',width:'8%'},
		{label:'¥��',name:'ldmc', index: 'ldmc',width:'10%'},
		{label:'���Һ�',name:'qsh', index: 'qsh',width:'10%'},
		{label:'����ʱ��',name:'kqsj', index: 'kqsj',width:'16%'},
		{label:'�������',name:'kqlb', index: 'kqlb',width:'10%'},
		{label:'��״̬',name:'dkzt', index: 'dkzt',width:'10%'}
	],
	sortname: "kqsj",
 	sortorder: "desc"
};

//ѧ������
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='KqjgView(\""+rowObject["kqsj"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

function KqjgView(kqsj,xh){
	showDialog("���ڽ����ѯ",800,450,"kqgl_kqjl.do?method=viewKqjl&kqsj="+kqsj+"&xh="+xh);
}
//����
function exportConfig(){
	var DCCLBH='rcsw_kqgl_kqjl.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH='rcsw_kqgl_kqjl.do';
	setSearchTj();//���ø߼���ѯ����
	var url = "kqgl_kqjl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

