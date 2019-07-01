

jQuery(function(){
	var gridSetting = {
			caption:"��ɫͨ������б�",
	pager:"pager",
	url:"rcsw_lstd_jggl.do?method=lstdjgManage&type=query",
	colList:[
	   {label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
		{label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
		{label:'ѧ��',name:'xn', index: 'xn',width:'9%'},
		{label:'ѧ��',name:'xqmc', index: 'xqmc',width:'3%'},
		{label:'����',name:'xm', index: 'xm',width:'8%'},
		{label:'�꼶',name:'nj', index: 'nj',width:'5%'},
		{label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'10%'},
		{label:'�༶',name:'bjmc', index: 'bjdm',width:'10%'},
		{label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'16%'},
		{label:'��������',name:'lxmc', index: 'lxmc',width:'15%'},
		{label:'��������',name:'sjly', index: 'sjly',hidden:true}
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
	var url = "rcsw_lstd_jggl.do?method=addLstdsqjg";
	var title = "���ӽ����Ϣ";
	showDialog(title,680,410,url);
}



function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	var sjly = rows[0]["sjly"];
	
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else if(sjly == '1'){
		showAlertDivLayer("�������ݲ����޸ģ�");
	}else {
		var url = 'rcsw_lstd_jggl.do?method=updateLstdjg&jgid='+ rows[0]["jgid"]
		+ '&xh=' + rows[0]["xh"];
		var title = "�޸Ľ����Ϣ";
		showDialog(title, 680,410, url);
	}

}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("�������ݲ���ɾ����");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("rcsw_lstd_jggl.do?method=delLstdjg", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="�������ݲ����޸ģ�";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});		
	}
}

function lstdjgView(jgid, xh) {
	showDialog("�����ѯ", 680,410, "rcsw_lstd_jggl.do?method=viewOneLstdjg&jgid=" + jgid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='lstdjgView(\""
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_lstd_jg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, lstdjgExportData);
}

// ��������
function lstdjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_lstd_jggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

