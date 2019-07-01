var gridSetting = {
	caption:"ҽ�Ʊ��������б�",
	pager:"pager",
	url:"rcsw_dxsylbx_ylbxsqgl.do?method=ylbxsqManage&type=query",
	colList:[      
	   {label:'key',name:'ylsqid', index: 'ylsqid',key:true ,hidden:true},
	   {label:'��������',name:'splc', index: 'splc',hidden:true},
	   {label:'ѧ��',name:'xh', index: 'xh',width:'7%',formatter:xhLink},
	   {label:'����',name:'xm', index: 'xm',width:'5%'},
	   {label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
	   {label:'�༶',name:'bjmc', index: 'bjdm',width:'10%'},
	   {label:'ѧ��',name:'xn', index: 'xn',width:'6%'},
	   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'5%'},
//	   {label:'�α�״��',name:'cbzkmc', index: 'cbzkmc',width:'6%',formatter:cbzkLink},
	   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'6%'},
	   {label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
	   {label:'�α�״������',name:'cbzkdm', index: 'cbzkdm',hidden:true},
	   {label:'��������',name:'czqebzdm', index: 'czqebzdm',hidden:true}
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
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer("��ǰδ�������룬����ϵ����Ա��");
		return false;
	}
	var url = "rcsw_dxsylbx_ylbxsqgl.do?method=addYlbxsq";
	var title = "����ҽ�Ʊ�������";
	showDialog(title,790,520,url);
}



function update() {
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
		return false;
	}
//	if ("false" == isopen){
//		showAlertDivLayer("��ǰδ�������룬����ϵ����Ա��");
//		return false;
//	}
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var shzt = rows[0]["shztmc"];
		if ("�����" == shzt){
			showAlertDivLayer("����Ϣ��¼��������У������޸ģ�");
			return false;
		}
		if ("ͨ��" == shzt){
			showAlertDivLayer("����Ϣ��¼����Ѿ�ͨ���������޸ģ�");
			return false;
		}
		if ("��ͨ��" == shzt){
			showAlertDivLayer("����Ϣ��¼����Ѿ���ͨ���������޸ģ�");
			return false;
		}
		var url = 'rcsw_dxsylbx_ylbxsqgl.do?method=updateYlbxsq&ylsqid=' + rows[0]["ylsqid"]
		+ '&xh=' + rows[0]["xh"] +'&cbzkdm=' + rows[0]["cbzkdm"]+'&czqebzdm=' + rows[0]["czqebzdm"];
		var title = "�޸�ҽ�Ʊ�������";
		showDialog(title,790,520,url);
	}

}


//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("rcsw_dxsylbx_ylbxsqgl.do?method=delYlbxsq", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="�������Ѿ��ύ����ɾ��!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}


function viewYlbxsq(ylsqid, xh) {
	showDialog("ҽ�Ʊ�������鿴", 700, 480, "rcsw_dxsylbx_ylbxsqgl.do?method=viewYlbxsq&ylsqid=" + ylsqid+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewYlbxsq(\""
			+ rowObject["ylsqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}



function viewCbzk(ylsqid,xm) {
	showDialog("�α�״����ѯ", 450, 220, "rcsw_dxsylbx_ylbxsqgl.do?method=viewCbzk&ylsqid=" + ylsqid + "&xm="+xm);
}


function cbzkLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewCbzk(\""
			+ rowObject["ylsqid"] + "\",\""+ rowObject["xm"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_dxsylbx_ylbxsq.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ylbxsqExportData);
}

// ��������
function ylbxsqExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_dxsylbx_ylbxsqgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}