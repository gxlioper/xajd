var gridSetting = {
		caption:"ҽ�Ʊ��������б�",
		pager:"pager",
		url:"rcsw_dxsylbx_ylbxjggl.do?method=ylbxjgManage&type=query",
		colList:[      
		   {label:'key',name:'yljgid', index: 'yljgid',key:true ,hidden:true},
		   {label:'��������',name:'splc', index: 'splc',hidden:true},
		   {label:'ѧ��',name:'xh', index: 'xh',width:'7%',formatter:xhLink},
		   {label:'����',name:'xm', index: 'xm',width:'5%'},
		   {label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
		   {label:'�༶',name:'bjmc', index: 'bjdm',width:'15%'},
		   {label:'ѧ��',name:'xn', index:'xn',width:'6%'},
		   {label:'ѧ��',name:'xqmc', index:'xqmc',width:'5%'},
//		   {label:'�α�״��',name:'cbzkmc', index: 'cbzkmc',width:'6%',formatter:cbzkLink},
		   {label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
		   {label:'�α�״������',name:'cbzkdm', index: 'cbzkdm',hidden:true},
		   {label:'ѧ��',name:'xq', index: 'xq',hidden:true},
		   {label:'��������',name:'czqebzdm', index: 'czqebzdm',hidden:true},
		   {label:'������Դ',name:'sjly', index: 'sjly',hidden:true}
		],
		sortname: "cbsj",
	 	sortorder: "desc"
}
//{label:'�α�ʱ��',name:'cbsj', index: 'cbsj',width:'8%'},
jQuery(function(){
	jQuery("#dataTable").initGrid(gridSetting);			
	
});

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add(){
	var url = "rcsw_dxsylbx_ylbxjggl.do?method=addYlbxjg";
	var title = "���Ӵ�ѧ��ҽ�Ʊ��ս����Ϣ";
	showDialog(title,790,520,url);
}



function update() {
	
	var rows = jQuery("#dataTable").getSeletRow();
	var sjly = rows[0]["sjly"];
	
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else if(sjly == '1'){
		showAlertDivLayer("��������ݣ������޸ģ�");
	}else {
		var url = 'rcsw_dxsylbx_ylbxjggl.do?method=updateYlbxjg&yljgid='+ rows[0]["yljgid"]
		+ '&xh=' + rows[0]["xh"];
		var title = "�޸Ĵ�ѧ��ҽ�Ʊ��ս����Ϣ";
		showDialog(title, 790, 520, url);
	}

}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("rcsw_dxsylbx_ylbxjggl.do?method=delYlbxjg", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="���������������ɾ��!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});		
	}
}

function ylbxjgView(yljgid, xh) {
	showDialog("��ѧ��ҽ�Ʊ��ս���鿴", 720, 460, "rcsw_dxsylbx_ylbxjggl.do?method=viewYlbxjg&yljgid=" + yljgid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='ylbxjgView(\""
			+ rowObject["yljgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function viewCbzk(yljgid,xm) {
	showDialog("�α�״���鿴", 450, 220, "rcsw_dxsylbx_ylbxjggl.do?method=viewCbzk&yljgid=" + yljgid +"&xm="+xm);
}

function cbzkLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewCbzk(\""
			+ rowObject["yljgid"] + "\",\""+ rowObject["xm"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_dxsylbx_ylbxjg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ylbxjgExportData);
}

// ��������
function ylbxjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_dxsylbx_ylbxjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//ѧ����Ƭ��������
function photoDownload() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_dxsylbx_ylbxjggl.do?method=photoDownload";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}