var gridSetting = {
	caption:"ѧ��֤���������б�",
	pager:"pager",
	url:"rcsw_xszbb_bbsqgl.do?method=xszbbsqManage&type=query",
	colList:[
	   {label:'key',name:'bbsqid', index: 'bbsqid',key:true ,hidden:true},
	   {label:'��������',name:'splc', index: 'splc',hidden:true},
	   {label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
	   {label:'����',name:'xm', index: 'xm',width:'8%'},
	   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
	   {label:'�༶',name:'bjmc', index: 'bjdm',width:'13%'},
	   {label:'��������',name:'xszbblxmc', index: 'xszbblxmc',width:'8%'},
	   {label:'�Ƿ񲹰��Żݿ�',name:'sfbbhcyhk', index: 'sfbbhcyhk',width:'8%'},
	   {label:'���벹��ʱ��',name:'sqsj', index: 'sqsj',width:'15%'},
	   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'6%'},
	   {label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
	   {label:'�������ʹ���',name:'xszbblxdm', index: 'xszbblxdm',hidden:true}
	],
	sortname: "sqsj",
 	sortorder: "desc"
}

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
		showAlertDivLayer("��ǰδ����ѧ��֤�������룬����ϵ����Ա��");
		return false;
	}
	var url = "rcsw_xszbb_bbsqgl.do?method=addXszbbsq";
	var title = "���벹��";
	showDialog(title,790,450,url);
}



function update() {
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer("��ǰδ����ѧ��֤�������룬����ϵ����Ա��");
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
		
		var url = 'rcsw_xszbb_bbsqgl.do?method=updateXszbbsq&bbsqid=' + rows[0]["bbsqid"]
		+ '&xh=' + rows[0]["xh"] +'&xszbblxdm=' + rows[0]["xszbblxdm"];
		var title = "�޸����벹��";
		showDialog(title, 720, 450, url);
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
				jQuery.post("rcsw_xszbb_bbsqgl.do?method=delXszbbsq", {
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

function viewXszbbsq(bbsqid, xh) {
	showDialog("ѧ��֤���������ѯ", 720, 520, "rcsw_xszbb_bbsqgl.do?method=viewXszbbsq&bbsqid=" + bbsqid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewXszbbsq(\""
			+ rowObject["bbsqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_xszbb_bbsq.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, rcxwxxwhExportData);
}

// ��������
function rcxwxxwhExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_xszbb_bbsqgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}