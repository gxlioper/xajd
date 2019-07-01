jQuery(function() {
	var gridSetting = {
			caption : "���ҵ�ʦ�����б�",
			pager : "pager",
			url : "gygl_qsdskh.do?method=qsdskhManage&type=query",
			colList : [
					{ label : 'key', name : 'pk', index : 'pk', key : true,
						hidden : true },
					{ label : 'ѧ��', name : 'xn', index : 'xn', width : '10%' },
					{ label : 'ѧ��', name : 'xq', index : 'xq', width : '12%', hidden : true },
					{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '6%' },
					{ label : '���', name : 'nd', index : 'nd', width : '6%' },
					{ label : 'ְ����', name : 'zgh', index : 'zgh', width : '15%' },
					{ label : '��ʦ����', name : 'dsxm', index : 'dsxm', width : '12%' },
					{ label : '���˳ɼ�', name : 'cj', index : 'cj', width : '10%' },
					{ label : '��λ', name : 'dw', index : 'dw', width : '24%' },
					{ label : '��ϵ�绰', name : 'lxdh', index : 'lxdh', width : '15%' }],
			sortname : "zgh", sortorder : "asc" };
	gridSetting["params"] = getSuperSearch();
	jQuery("#dataTable").initGrid(gridSetting);

});

// ѡ���ʦ�󣬻ص�����
function showFdysNotF5CallBack(rowData) {
	jQuery("#zgh").val(rowData["zgh"]);
	jQuery("#dsxm").html(rowData["xm"]);
	jQuery("#dslxdh").html(rowData["lxdh"]);
	jQuery("#dsbmmc").html(rowData["bmmc"]);
}

/**
 * ��������
 * 
 * @return
 */
function addUpdateQsds(action) {
	if("add" === action)
		showDialog('�������ҵ�ʦ����', 680, 200, 'gygl_qsdskh.do?method=qsdskhAdd');
	else if("update" === action){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			return false;
		}
		showDialog('�������ҵ�ʦ����', 680, 200, 'gygl_qsdskh.do?method=qsdskhUpdate&zgh=' + rows[0]['zgh'] + '&xn=' + rows[0]['xn'] + '&xq=' + rows[0]['xq']);
	}
}


/**
 * ����
 * 
 * @return
 */
function addQsdsxx() {
	var nd = jQuery('#nd').val();
	var xn = jQuery('#xn').val();
	var xq = jQuery('#xq').val();
	var zgh = jQuery('#zgh').val();
	var cj = jQuery('#cj').val();
	
	if ((nd == null || jQuery.trim(nd) == '') || (xn == null || jQuery.trim(xn) == '') || (xq == null || jQuery.trim(xq) == '') || (zgh==null || jQuery.trim(zgh) == '') || (cj==null || jQuery.trim(cj) == '')) {
		showAlertx("��<font color=\"red\">*</font>����Ŀ����");
		return false;
	}
	
	jQuery.post('gygl_qsdskh.do?method=qsdskhAddCheckAction',{xn:xn,xq:xq,zgh:zgh},function(data){
		if(data.message != ""){
			showAlertx(data.message);
			return false;
		}
		var url = "gygl_qsdskh.do?method=qsdskhAddAction";
		ajaxSubFormWithFun("sqdswhForm", url, function(data) {
			showAlertx(data["message"], {}, { "clkFun" : function() {
				if (parent.window) {
					refershParent();
				}
			} });
		});
	},'json');
}

/**
 * ����
 * 
 * @return
 */
function updateQsdsxx() {
	var cj = jQuery('#cj').val();
	
	if ((cj==null || jQuery.trim(cj) == '')) {
		showAlertx("��<font color=\"red\">*</font>����Ŀ����");
		return false;
	}
	
	var url = "gygl_qsdskh.do?method=qsdskhUpdateAction";
	ajaxSubFormWithFun("sqdswhForm", url, function(data) {
		showAlertx(data["message"], {}, { "clkFun" : function() {
			if (parent.window) {
				refershParent();
			}
		} });
	});
}

/**
 * ɾ��
 * @return
 */
function deleteQsds(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ��һ����Ҫɾ���ļ�¼��");
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
		jQuery.post("gygl_qsdskh.do?method=qsdskhDeleteAction",{pks:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}

/**
 * �߼���ѯ
 * 
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

var DCCLBH = "gygl_qsdskhgl.do";// dcclbh,�������ܱ��

// �Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "gygl_qsdskh.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ����
 */
function importConfig() {
	toImportData("IMPORT_N381902");
	return false;
}
