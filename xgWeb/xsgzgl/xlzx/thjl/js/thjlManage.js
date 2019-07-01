
jQuery(function() {
	var gridSetting = {
			caption:"̸����¼�б�",
			pager:"pager",
			url:"xlzx_thjl.do?method=thjlManage&doType=query",
			colList:[
			   {label:'id',name:'id', index: 'id',hidden:true,key:true},
			   {label:'ѧ��',name:'xh', index: 'xh',formatter:xhLink},
			   {label:'����',name:'xsxm', index: 'xsxm'},
			   {label:'�Ա�',name:'xsxb', index: 'xsxb'},
			   {label:'�꼶',name:'nj', index: 'nj'},
			   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc'},
			   {label:'רҵ',name:'zymc', index: 'zymc'},
			   {label:'�༶',name:'bjmc', index: 'bjmc'},
			   {label:'̸��ʱ��',name:'thsj', index: 'thsj',formatter:getNewDate},
			   {label:'̸����ʦ',name:'jsxm', index: 'jsxm'}
			],
			sortname: "",
		 	sortorder: ""
		};
	jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs() {
	var map = getSuperSearch();

	jQuery("#dataTable").reloadGrid(map);
}

function getNewDate(cellValue) {
	var newDate = cellValue.substring(0, 10);
	return newDate;
}

function xhLink(cellValue, rowObject) {
	return "<a href=\"javascript:void(0);\" class=\"name\" onclick=\"showDetail('"
			+ rowObject["xh"] + "');\">" + cellValue + "</a>";
}

function showDetail(xh) {
	showDialog("̸����¼����", 700, 500, "xlzx_thjl.do?method=thjlDetailByXh&xh="
			+ xh);
}

function viewThjl() {
	var id = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
		return false;
	} else {
		id = rowsValue[0]["id"];
	}
	showDialog("�鿴̸����¼", 700, 500,
			"xlzx_thjl.do?method=thjlDetail&doType=view&id=" + id);
}

function addThjl() {
	showDialog("����̸����¼", 700, 510, "xlzx_thjl.do?method=thjlDetail&doType=add");
}

function updateThjl() {
	var id = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else {
		id = rowsValue[0]["id"];
	}
	showDialog("�޸�̸����¼", 700, 510,
			"xlzx_thjl.do?method=thjlDetail&doType=update&id=" + id);
}

function deleteThjl() {
	var dealThjl = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		for ( var i = 0; i < rowsValue.length; i++) {
			if (i == (rowsValue.length - 1)) {
				dealThjl += rowsValue[i]["id"];
			} else {
				dealThjl += rowsValue[i]["id"] + ",";
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.ajaxSetup( {
					async : false
				});
				jQuery.post("xlzx_thjl.do?method=deleteThjlInfo", {
					dealThjl : dealThjl
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
				jQuery.ajaxSetup( {
					async : true
				});
			}
		});
	}
}

function exportThjl() {
	customExport("xlzx_thjl_thjl.do", exportThjlData, 700, 500);
}

// ��������
function exportThjlData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "xlzx_thjl.do?method=exportThjlData&dcclbh="
			+ "xlzx_thjl_thjl.do";// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("#form").eq(0).attr("action", url);
	jQuery("#form").eq(0).submit();
}

//�°浼��
function dr() {
	toImportDataNew("IMPORT_XLZX_THJL");
	return false;
}