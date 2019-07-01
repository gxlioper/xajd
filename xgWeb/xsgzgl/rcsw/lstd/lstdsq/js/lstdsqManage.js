

jQuery(function(){
	var  gridSetting = {
			caption:"֤�����������б�",
			pager:"pager",
			url:"rcsw_lstd_sqgl.do?method=lstdsqManage&type=query",
			colList:[
			   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
			   {label:'��������',name:'splc', index: 'splc',hidden:true},
			   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
			   {label:'����',name:'xm', index: 'xm',width:'8%'},
			   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
			   {label:'�꼶',name:'nj', index: 'nj',width:'5%'},
			   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'10%'},
			   {label:'רҵ',name:'zymc', index: 'zymc',width:'13%'},
			   {label:'�༶',name:'bjmc', index: 'bjdm',width:'13%'},
			   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'10%'},
			   {label:'��������',name:'lxmc', index: 'lxmc',width:'8%'},
			   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'6%'},
			   {label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
			   {label:'�������ɴ���',name:'lxdm', index: 'lxdm',hidden:true}
			],
			sortname: "sqsj",
		 	sortorder: "desc"
		}
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
		showAlertDivLayer("��ǰδ������ɫͨ�����룬����ϵ����Ա��");
		return false;
	}
	var url = "rcsw_lstd_sqgl.do?method=addLstdsq";
	var title = "����";
	showDialog(title,680,380,url);
}



function update() {
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
		return false;
	}
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
		
		var url = 'rcsw_lstd_sqgl.do?method=updateLstdsq&sqid=' + rows[0]["sqid"]
		+ '&xh=' + rows[0]["xh"] +'&lxdm=' + rows[0]["lxdm"];
		var title = "�޸�";
		showDialog(title, 680,380, url);
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
				jQuery.post("rcsw_lstd_sqgl.do?method=delLstdsq", {
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

function viewLstdsq(sqid, xh) {
	showDialog("��ɫͨ�������ѯ", 680,450, "rcsw_lstd_sqgl.do?method=viewLstdsq&sqid=" + sqid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewLstdsq(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_lstd_sq.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, rcxwxxwhExportData);
}

// ��������
function rcxwxxwhExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_lstd_sqgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}