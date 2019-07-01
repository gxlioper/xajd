

jQuery(function() {
	var gridSetting = {
			caption : "ѧ����Ϣ�޸�����б�",
			pager : "pager",
			url : "xsxx_xsxxxg.do?method=xgjg&type=query",
			colList : [ {
				label : 'key',
				name : 'sqid',
				index : 'sqid',
				key : true,
				hidden : true
			}, {
				label : 'ѧ��',
				name : 'xh',
				index : 'xh',
				width : '11%',
				formatter : setXh
			}, {
				label : '����',
				name : 'xm',
				index : 'xm',
				width : '8%'
			}, {
				label : '�Ա�',
				name : 'xb',
				index : 'xb',
				width : '5%'
			}, {
				label : jQuery("#xbmc").val(),
				name : 'xymc',
				index : 'xydm',
				width : '13%'
			}, {
				label : '�༶',
				name : 'bjmc',
				index : 'bjdm',
				width : '13%'
			}, {
				label : '����ʱ��',
				name : 'xgsj',
				index : 'xgsj',
				width : '15%'
			}, {
				label : '���״̬',
				name : 'shjg',
				index : 'shjg',
				width : '15%',
				formatter : setShjg
			}, {
				label : '���id',
				name : 'spclid',
				index : 'spclid',
				hidden : true
			}, {
				label : 'lcid',
				name : 'lcid',
				index : 'lcid',
				hidden : true
			}, {
				label : 'ywid',
				name : 'ywid',
				index : 'ywid',
				hidden : true
			} ],
			params : {
			// shzt : "dsh"
			},// Ĭ�ϴ����
			sortname : "xgsj",
			sortorder : "desc"
		}
	var map = getSuperSearch();
	gridSetting["params"] = map;
	jQuery("#dataTable").initGrid(gridSetting);
});

function setXh(cellValue, rowObject) {
	var xh = rowObject.xh;
	if (xh == null) {
		xh = "";
	}
	cellValue = "<a  href='javascript:void(0);'  class='name'  onclick='xgjgCkDt(\""
			+ xh
			+ "\",\""
			+ rowObject.sqid
			+ "\" );return false;' >"
			+ xh
			+ "</a>";
	return cellValue;
}


function setShjg(cellValue, rowObject) {
	if(cellValue == "0" || cellValue == "wsh" ){
		cellValue = "δ���" ;
	}else if(cellValue == "1" || cellValue == "tg" ){
		cellValue = "ͨ��" ;
	}else if(cellValue == "2" || cellValue == "btg" ){
		cellValue = "��ͨ��" ;
	}else if(cellValue == "3" || cellValue == "th" ){
		cellValue = "�˻�" ;
	}else {
		cellValue = "�����" ;
	}
	return cellValue;
}


function shlcInfo() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {
		var shlc = rows[0]["splcid"];
		if (shlc == "") {
			showAlertDivLayer("������������ˣ������������Ϣ��");
			return false;
		}
		showDialog("ѧ����Ϣ�޸��������̸���", 600, 400, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['ywid'] + "&splc=" + rows[0]['lcid']);
	}
}


/**
 * 
 * 
 * @return
 */
function xgjgCk() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	} else {
		var url = "xsxx_xsxxxg.do?method=xgjgCk";
		url += "&xh=" + rows[0]["xh"];
		url += "&sqid=" + rows[0]["sqid"];
		showDialog("ѧ����Ϣ�޸Ľ���鿴", 750, 500, url);
	}
}

function xgjgCkDt(xh,sqid) {

	var url = "xsxx_xsxxxg.do?method=xgjgCk";
	url += "&xh=" + xh;
	url += "&sqid=" + sqid;
	showDialog("ѧ����Ϣ�޸Ľ���鿴", 750, 500, url);
}

function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();

	if (shzt != "") {
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}

function exportConfigSh() {	
	var DCCLBH = "xsxx_xsxxxg_xgjg.do";//dcclbh,�������ܱ�ţ�ִ�е������� 
	customExport(DCCLBH, exportDataSh);
}

function exportDataSh() {
	setSearchTj();//���ø߼���ѯ����
	var DCCLBH = "xsxx_xsxxxg_xgjg.do";
	var url = "xsxx_xsxxxg.do?method=exportDataJg&dcclbh=" + DCCLBH ;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}