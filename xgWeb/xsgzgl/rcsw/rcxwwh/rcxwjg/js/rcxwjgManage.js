var xxdm;

jQuery(function() {
	xxdm = jQuery("#xxdm").val();
	var gridSetting = {
			caption:"�ճ���Ϊ����б�",
	pager:"pager",
	params:getSuperSearch(),
	url:"rcsw_rcxwwh_rcxwjggl.do?method=rcxwjgManage&type=query",
	colList:[
	   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
	   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
	   {label:'����',name:'xm', index: 'xm',width:'7%'},
	   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
	   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'12%'},
	   {label:'�༶',name:'bjmc', index: 'bjmc',width:'12%'},
	   {label:'��Ϊ����',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'10%'},
	   {label:'��Ϊ���',name:'rcxwlbmc', index: 'rcxwlbmc',width:'10%'},
	   {label:'����ʱ��',name:'fssj', index: 'fssj',width:'10%'},
	   {label:'������ֵ',name:'fz', index: 'fz',width:'8%'},
	   {label:'ѧ��ѧ��',name:'xnxq', index: 'xnxq',width:'15%'},
	   {label:'�ճ���Ϊ�������',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
	   {label:'��Ϊ������',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
	   {label:'������Դ',name:'sjly', index: 'sjly',hidden:true}
	],
	sortname: "rcxwjlsj",
 	sortorder: "desc"
}
	var gridSetting1 = {
			caption:"�ճ���Ϊ����б�",
	pager:"pager",
	params:getSuperSearch(),
	url:"rcsw_rcxwwh_rcxwjggl.do?method=rcxwjgManage&type=query",
	colList:[
	   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
	   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
	   {label:'����',name:'xm', index: 'xm',width:'7%'},
	   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
	   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'12%'},
	   {label:'�༶',name:'bjmc', index: 'bjmc',width:'12%'},
	   {label:'��Ϊ���',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'10%'},
	   {label:'����ʱ��',name:'fssj', index: 'fssj',width:'10%'},
	   {label:'������ֵ',name:'fz', index: 'fz',width:'8%'},
	   {label:'ѧ��ѧ��',name:'xnxq', index: 'xnxq',width:'15%'},
	   {label:'�ճ���Ϊ�������',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
	   {label:'��Ϊ������',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
	   {label:'������Դ',name:'sjly', index: 'sjly',hidden:true}
	],
	sortname: "rcxwjlsj",
 	sortorder: "desc"
}
    var gridSetting2 = {
        caption:"�ӷ��������б�",
        pager:"pager",
        params:getSuperSearch(),
        url:"rcsw_rcxwwh_rcxwjggl.do?method=rcxwjgManage&type=query",
        colList:[
            {label:'key',name:'id', index: 'id',key:true ,hidden:true},
            {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
            {label:'����',name:'xm', index: 'xm',width:'7%'},
            {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
            {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'12%'},
            {label:'�༶',name:'bjmc', index: 'bjmc',width:'12%'},
            {label:'�ӷִ���',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'10%'},
            {label:'�ӷ����',name:'rcxwlbmc', index: 'rcxwlbmc',width:'10%'},
            {label:'����ʱ��',name:'fssj', index: 'fssj',width:'10%'},
            {label:'������ֵ',name:'fz', index: 'fz',width:'8%'},
            {label:'ѧ��ѧ��',name:'xnxq', index: 'xnxq',width:'15%'},
            {label:'�ӷִ������',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
            {label:'�ӷ�������',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
            {label:'������Դ',name:'sjly', index: 'sjly',hidden:true}
        ],
        sortname: "rcxwjlsj",
        sortorder: "desc"
    }
	if(xxdm == '12867'){
		jQuery("#dataTable").initGrid(gridSetting1);
	}else if(xxdm == "13431"){
		jQuery("#dataTable").initGrid(gridSetting2);
	}else{
        jQuery("#dataTable").initGrid(gridSetting);
	}
	

});
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add() {
	var url = "rcsw_rcxwwh_rcxwjggl.do?method=addXwjg";
	var title = "�����ճ���Ϊ�����Ϣ";
	if(xxdm=="13815"){
		title = "��������ѧ�ֽ����Ϣ";
	}
    if(xxdm=="13431"){
        title = "���Ӽӷֽ����Ϣ";
    }
	showDialog(title, 950, 460, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	var sjly = rows[0]["sjly"];

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else if (sjly == '1') {
		showAlertDivLayer("�������ݼ�¼������������������޸ģ�");
	} else {
		var url = 'rcsw_rcxwwh_rcxwjggl.do?method=updateXwjg&id='
				+ rows[0]["id"] + '&xh=' + rows[0]["xh"] + '&rcxwlbdldm='
				+ rows[0]["rcxwlbdldm"] + '&rcxwlbdm=' + rows[0]["rcxwlbdm"]
				+ '&xn=' + rows[0]["xn"] + '&xq=' + rows[0]["xq"];
		var title = "�޸��ճ���Ϊ�����Ϣ";
		if(xxdm=="13815"){
			title = "�޸�����ѧ�ֽ����Ϣ";
		}
        if(xxdm=="13431"){
            title = "�޸ļӷֽ����Ϣ";
        }
		showDialog(title, 720, 460, url);
	}

}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var sjly = rows[0]["sjly"];

	if (ids.length == 0) {
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['sjly'] == '1') {
				showAlertDivLayer("�������ݲ���ɾ����");
				return false;
			}
		}

		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("rcsw_rcxwwh_rcxwjggl.do?method=delXwjg", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}

function xwjgView(id, xh) {
	var title = "�ճ���Ϊ�����Ϣ�鿴";
	if(xxdm=="13815"){
		title = "����ѧ�ֽ����Ϣ�鿴";
	}
    if(xxdm=="13431"){
        title = "�ӷֽ����Ϣ�鿴";
    }
	showDialog(title, 720, 520,
			"rcsw_rcxwwh_rcxwjggl.do?method=viewXwjg&id=" + id + "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='xwjgView(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_rcxwwh_rcxwjg.do";// dcclbh,�������ܱ��

// �Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, rcxwjgExportData);
}

// ��������
function rcxwjgExportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "rcsw_rcxwwh_rcxwjggl.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�´�����Ʒ��ʵ������
function xsPxsjDc(){
	var url ="rcsw_rcxwwh_rcxwjggl.do?method=xsPxsjDc";
	var xqLength=jQuery("a[name=a_name_xq]").length;
	var xnLength=jQuery("a[name=a_name_xn]").length;
	var yqLength=jQuery("a[name=a_name_yqdm]").length;
	if(xqLength != "1"){
		showAlertDivLayer("��ѡ��һ��ѧ�ڲ�ѯ������");
		return false;
	}
	if(xnLength != "1"){
		showAlertDivLayer("��ѡ��һ��ѧ���ѯ������");
		return false;
	}
	if(yqLength != "1"){
		showAlertDivLayer("��ѡ��һ��ѧ����ѯ������");
		return false;
	}
	setSearchTj();
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].submit();
	
}

function rcxwsjDc() {
	var url ="rcsw_rcxwwh_rcxwjggl.do?method=rcxwsjDc";
	var xnLength=jQuery("a[name=a_name_xn]").length;
	if(xnLength != "1"){
		showAlertDivLayer("��ѡ��һ��ѧ���ѯ������");
		return false;
	}
	setSearchTj();
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].submit();
}
//�ճ���Ϊ�ֵܷ���
function rcxwtjbDc() {
	var url ="rcsw_rcxwwh_rcxwjggl.do?method=rcxwtjbDc";
	var xnLength=jQuery("a[name=a_name_xn]").length;
	if(xnLength != "1"){
		showAlertDivLayer("��ѡ��һ��ѧ���ѯ������");
		return false;
	}
	setSearchTj();
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].submit();
}



// ���뷽��
function importData() {
	toImportData("IMPORT_N730204");
	return false;
}

//����
function importConfig(){
	toImportDataNew("IMPORT_RCSW_ZJLY");
	return false;
}

//�ൺ����ѧԺ˼��Ʒ�¿γɼ����ܵ���
function sxpdcjhzDc(){
	var url ="rcsw_rcxwwh_rcxwjggl.do?method=sxpdcjhzDc";
	
	var xnLength=jQuery("a[name=a_name_xn]").length;
	var xqLength=jQuery("a[name=a_name_xq]").length;
	var xyLength=jQuery("a[name=a_name_xy]").length;
	
	if(xnLength != "1"){
		showAlertDivLayer("��ѡ��һ��ѧ���ѯ������");
		return false;
	}
	if(xqLength != "1"){
		showAlertDivLayer("��ѡ��һ��ѧ�ڲ�ѯ������");
		return false;
	}
	if(xyLength != "1"){
		showAlertDivLayer("��ѡ��һ��ѧԺ��ѯ������");
		return false;
	}
	setSearchTj();
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].submit();
}

/**
 * �㽭����ְҵѧԺ�������Ի���������֪ͨ��
 * @return
 */
function printDykptzd(){
	/*ѡ��ļ�¼*/
	var rows = jQuery("#dataTable").getSeletRow();
	/*��ѡ��¼*/
	var ids = jQuery("#dataTable").getSeletIds();
	/*ѡ���¼�����������ȣ�*/
	var len = ids.length;
	if(0 == len ){/*ѡ��0����ʾ*/
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	}else if(1 == len){/*ѡ��һ����¼*/
		var url = "rcsw_rcxwwh_rcxwjggl.do?method=getDykptzdOne&xh="+rows[0]["xh"]+"&id="+rows[0]["id"];
		window.open(url);
	}else{
		var url = "rcsw_rcxwwh_rcxwjggl.do?method=getDykptzdZip&value="+ids;
		window.open(url);
	}
}

/**
 * �㽭����ְҵѧԺ�������Ի�Υ�ʹ���
 * @return
 */
function printWjcld(){
	/*ѡ��ļ�¼*/
	var rows = jQuery("#dataTable").getSeletRow();
	/*��ѡ��¼*/
	var ids = jQuery("#dataTable").getSeletIds();
	/*ѡ���¼�����������ȣ�*/
	var len = ids.length;
	if(0 == len ){/*ѡ��0����ʾ*/
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	}else if(1 == len){/*ѡ��һ����¼*/
		var url = "wjcf_cfjg.do?method=getWjcldOne"+"&id="+rows[0]["id"];
		window.open(url);
	}else{
		var url = "wjcf_cfjg.do?method=getWjcldZip&value="+ids;
		window.open(url);
	}
}

/**
 * �㽭����ְҵѧԺ��������������
 * @return
 */
function printJlspb(){
	/*ѡ��ļ�¼*/
	var rows = jQuery("#dataTable").getSeletRow();
	/*��ѡ��¼*/
	var ids = jQuery("#dataTable").getSeletIds();
	/*ѡ���¼�����������ȣ�*/
	var len = ids.length;
	if(0 == len ){/*ѡ��0����ʾ*/
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	}else if(1 == len){/*ѡ��һ����¼*/
		var url = "rcsw_rcxwwh_rcxwjggl.do?method=getJlspbOne"+"&id="+rows[0]["id"];
		window.open(url);
	}else{
		var url = "rcsw_rcxwwh_rcxwjggl.do?method=getJlspbZip&value="+ids;
		window.open(url);
	}
}