

jQuery(function(){
	var gridSetting = null;
	var xxdm = jQuery("#xxdm").val();
	if("11318" == xxdm) {
		gridSetting = {
			caption:"�𳵳˳�������д�б�",
			pager:"pager",
			url:"rcsw_hcyhk_hcccqjtxgl.do?method=hcccqjtxManage&type=query",
			params:getSuperSearch(),
			colList:[
			   {label:'key',name:'ccqjtxid', index: 'ccqjtxid',key:true ,hidden:true},
			   {label:'��������',name:'splc', index: 'splc',hidden:true},
			   {label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
			   {label:'����',name:'xm', index: 'xm',width:'8%'},
			   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
			   {label:'�༶',name:'bjmc', index: 'bjdm',width:'15%'},
			   {label:'ѧ��',name:'xn', index: 'xn',width:'8%'},
			   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'8%'},
			   {label:'��ʼվ',name:'ccqdz', index: 'ccqdz',width:'8%'},
			   {label:'�յ�վ',name:'cczdz', index: 'cczdz',width:'8%'},
			   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'6%'},
			   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true}
			],
			sortname: "txsj",
			sortorder: "desc"
		}
		
	}else {
	    gridSetting = {
			caption:"�𳵳˳�������д�б�",
			pager:"pager",
			url:"rcsw_hcyhk_hcccqjtxgl.do?method=hcccqjtxManage&type=query",
			params:getSuperSearch(),
			colList:[
			   {label:'key',name:'ccqjtxid', index: 'ccqjtxid',key:true ,hidden:true},
			   {label:'��������',name:'splc', index: 'splc',hidden:true},
			   {label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
			   {label:'����',name:'xm', index: 'xm',width:'8%'},
			   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
			   {label:'�༶',name:'bjmc', index: 'bjdm',width:'15%'},
			   {label:'ѧ��',name:'xn', index: 'xn',width:'8%'},
			   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'8%'},
			   {label:'�𳵳˳�����',name:'hcccqjmc', index: 'hcccqjmc',width:'13%'},
			   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'6%'},
			   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true}
			],
			sortname: "txsj",
		 	sortorder: "desc"
		}
	}
	jQuery("#dataTable").initGrid(gridSetting);
	
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		jQuery("#prompt_isopen").show();
		jQuery("#prompt_null_isopen").show();
		return false;
	}
	if ("false" == isopen){
		jQuery("#prompt_isopen").show();
		jQuery("#prompt_false_isopen").show();
		return false;
	}
});

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
	
}

function add(){
	
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var url = "rcsw_hcyhk_hcccqjtxgl.do?method=addHcccqjtx";
	var title = "��д�˳�����";
	showDialog(title,790,450,url);
	
}



function update() {
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		
		var shzt = rows[0]["shzt"];
		if (shzt=='0'||shzt=='3'){
			var url = 'rcsw_hcyhk_hcccqjtxgl.do?method=updateHcccqjtx&ccqjtxid=' + rows[0]["ccqjtxid"] + '&xh=' + rows[0]["xh"];
			showDialog("�޸���д�˳�����", 720, 450, url);
		}else{
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
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
				jQuery.post("rcsw_hcyhk_hcccqjtxgl.do?method=delHcccqjtx", {
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

function viewHcccqjtx(ccqjtxid,xh) {
	showDialog("���Żݿ������ѯ", 720, 520, "rcsw_hcyhk_hcccqjtxgl.do?method=viewHcccqjtx&ccqjtxid=" + ccqjtxid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewHcccqjtx(\""
			+ rowObject["ccqjtxid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}



var DCCLBH = "rcsw_hcyhk_hcccqjtx.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, rcxwxxwhExportData);
}

// ��������
function rcxwxxwhExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_hcyhk_hcccqjtxgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}