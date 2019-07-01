

jQuery(function(){
	var gridSetting = null;
	var xxdm = jQuery("#xxdm").val();
	if("11318" == xxdm) {
		gridSetting = {
			caption:"�𳵳˳�������д����б�",
			pager:"pager",
			url:"rcsw_hcyhk_hcccqjjggl.do?method=hcccqjjgManage&type=query",
			params:getSuperSearch(),
			colList:[
			   {label:'key',name:'ccqjjgid', index: 'ccqjjgid',key:true ,hidden:true},
			   {label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
			   {label:'����',name:'xm', index: 'xm',width:'6%'},
			   {label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
			   {label:'�����༶',name:'bjmc', index: 'bjdm',width:'8%'},
                {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'8%'},
			   {label:'ѧ��',name:'xn', index: 'xn',width:'8%'},
			   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'8%'},
			   {label:'��ʼվ',name:'ccqdz', index: 'ccqdz',width:'8%'},
			   {label:'�յ�վ',name:'cczdz', index: 'cczdz',width:'8%'},
			   {label:'��дʱ��',name:'txsj', index: 'txsj',width:'6%'},
			   {label:'������Դ',name:'sjly', index: 'sjly',hidden:true}
			],
			sortname: "txsj",
			sortorder: "desc"
		}
	}else if("13011" == xxdm){
		gridSetting = {
				caption:"�𳵳˳�������д����б�",
				pager:"pager",
				url:"rcsw_hcyhk_hcccqjjggl.do?method=hcccqjjgManage&type=query",
				params:getSuperSearch(),
				colList:[
				    {label:'key',name:'ccqjjgid', index: 'ccqjjgid',key:true ,hidden:true},
					{label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
					{label:'����',name:'xm', index: 'xm',width:'6%'},
					{label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
                    {label:'�����༶',name:'bjmc', index: 'bjdm',width:'8%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'8%'},
					{label:'ѧ��',name:'xn', index: 'xn',width:'8%'},
				    {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'8%'},
				    {label:'�𳵳˳�����',name:'hcccqjmc', index: 'hcccqjmc',width:'13%'},
				    {label:'��дʱ��',name:'txsj', index: 'txsj',width:'6%'},
				    {label:'������ʱ��',name:'shwcsj', index: 'shwcsj',width:'6%'},
					{label:'������Դ',name:'sjly', index: 'sjly',hidden:true}
				],
				sortname: "txsj",
			 	sortorder: "desc"
			}
	}
	else {
	    gridSetting = {
			caption:"�𳵳˳�������д����б�",
			pager:"pager",
			url:"rcsw_hcyhk_hcccqjjggl.do?method=hcccqjjgManage&type=query",
			params:getSuperSearch(),
			colList:[
			    {label:'key',name:'ccqjjgid', index: 'ccqjjgid',key:true ,hidden:true},
				{label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
				{label:'����',name:'xm', index: 'xm',width:'6%'},
				{label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
                {label:'�����༶',name:'bjmc', index: 'bjdm',width:'8%'},
                {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'8%'},
				{label:'ѧ��',name:'xn', index: 'xn',width:'8%'},
			    {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'8%'},
			    {label:'�𳵳˳�����',name:'hcccqjmc', index: 'hcccqjmc',width:'13%'},
			    {label:'��дʱ��',name:'txsj', index: 'txsj',width:'6%'},
				{label:'������Դ',name:'sjly', index: 'sjly',hidden:true}
			],
			sortname: "txsj",
		 	sortorder: "desc"
		}
	} 
	jQuery("#dataTable").initGrid(gridSetting);			
});

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add(){
	var url = "rcsw_hcyhk_hcccqjjggl.do?method=addHcccqjjg";
	var title = "���ӻ𳵳˳���������Ϣ";
	showDialog(title,790,460,url);
}



function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	var sjly = rows[0]["sjly"];
	
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else if(sjly == '1'){
		showAlertDivLayer("��������ݣ������޸ģ�");
	}else {
		var url = 'rcsw_hcyhk_hcccqjjggl.do?method=updateHcccqjjg&ccqjjgid='+ rows[0]["ccqjjgid"]
		+ '&xh=' + rows[0]["xh"];
		var title = "�޸Ļ𳵳˳���������Ϣ";
		showDialog(title, 720, 460, url);
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
				jQuery.post("rcsw_hcyhk_hcccqjjggl.do?method=delHcccqjjg", {
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

function viewHcccqjjg(ccqjjgid, xh) {
	showDialog("�𳵳˳���������ѯ", 720, 440, "rcsw_hcyhk_hcccqjjggl.do?method=viewHcccqjjg&ccqjjgid=" + ccqjjgid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewHcccqjjg(\""
			+ rowObject["ccqjjgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_hcyhk_hcccqjjg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, xszbbjgExportData);
}

// ��������
function xszbbjgExportData() {
	
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_hcyhk_hcccqjjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
	
}