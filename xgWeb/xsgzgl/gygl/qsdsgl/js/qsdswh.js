jQuery(function() {
	var xxdm = jQuery("#xxdm").val();
	var colList = [
					{ label : 'key', name : 'pk', index : 'pk', key : true,
						hidden : true },
					{ label : 'ѧ��', name : 'xn', index : 'xn', width : '10%', hidden : true },
					{ label : 'ѧ��', name : 'xq', index : 'xq', width : '10%', hidden : true },
					{ label : '¥������', name : 'ldmc', index : 'ldmc', width : '10%' },
					{ name : 'lddm', index : 'lddm', hidden : true },
					{ label : '���Һ�', name : 'qsh', index : 'qsh', formatter : qshLink, width : '7%' },
					{ label : '�����Ա�', name : 'xb', index : 'xb', width : '6%' },
					{ label : '�����꼶', name : 'nj', index : 'nj', width : '7%' },
					{ label : '����'+jQuery("#xbmc").val(), name : 'bmmc', index : 'bmmc', width : '13%' },
					{ label : '��ʦ����', name : 'dsxm', index : 'dsxm', width : '8%' },
					{ label : '��λ', name : 'dw', index : 'dw', width : '13%' },
					{ label : '��ϵ�绰', name : 'lxdh', index : 'lxdh', width : '8%' },
					{ label : '���ҳ�', name : 'qszxm', index : 'qszxm', width : '8%' },
					{ label : '���ҳ���ϵ��ʽ', name : 'qszlxdh', index : 'qszlxdh', width : '8%' } ];
	if(xxdm == "10351"){
		colList.push({ label : 'ѧ������Ա', name : 'xqfdyxm', index : 'xqfdyxm', width : '8%' });
		colList.push({ label : '����Ա��ϵ�绰', name : 'xqfdylxdh', index : 'xqfdylxdh', width : '8%' });
	}
	var gridSetting = {
			caption : "���ҵ�ʦά���б�",
			pager : "pager",
			url : "gygl_qsdswh.do?method=qsdswhManage&type=query",
			colList : colList,
			sortname : "ldmc", sortorder : "asc" };
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

function showFdysNotF5CallBackAnother(rowData) {
	jQuery("#xqfdyxm").val(rowData["xm"]);
	jQuery("#xqfdylxdh").val(rowData["lxdh"])
	jQuery("td[name=xqfdylxdh]").html(rowData["lxdh"]);
}


/**
 * ��������
 * 
 * @return
 */
function addUpdateQsds(action) {
	var height = 420;
	var xxdm = jQuery("#xxdm").val();
	if(xxdm == "10351"){
		height = 450;
	}
	if("add" === action){
		showDialog('�������ҵ�ʦ', 780, height, 'gygl_qsdswh.do?method=qsdswhAdd');
	}else if("update" === action){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			return false;
		}
		showDialog('�������ҵ�ʦ', 780, height, 'gygl_qsdswh.do?method=qsdswhUpdate&lddm=' + rows[0]['lddm'] + '&qsh=' + rows[0]['qsh'] + '&xn=' + rows[0]['xn'] + '&xq=' + rows[0]['xq']);
	}
}


/**
 * ����
 * 
 * @return
 */
function addQsdsxx() {
	var bz = jQuery('#bz').val();
	var lddm = jQuery('#lddm').val();
	var qsh = jQuery('#qsh').val();
	var zgh = jQuery('#zgh').val();
	
	
	if ((lddm==null || jQuery.trim(lddm) == '') || (qsh == null || jQuery.trim(qsh) == '') || (zgh==null || jQuery.trim(zgh) == '')) {
		showAlertDivLayer("�뽫��<font color=\"red\">*</font>����Ŀ��д������");
		return false;
	}

	if (bz.length > 100) {
		showAlertDivLayer("��ע�������������" + 100 + ",��ȷ�ϣ�");
		return false;
	}

	var url = "gygl_qsdswh.do?method=qsdswhAddAction";
	jQuery.post("gygl_qsdswh.do?method=qsdsIsExist",{lddm:lddm,qsh:qsh},function(data){
		if(data != null && data["message"] != null){
			showConfirmDivLayer(data["message"],{"okFun":function(){
				ajaxSubFormWithFun("sqdswhForm", url, function(data) {
					showAlertDivLayer(data["message"], {}, { "clkFun" : function() {
						if (parent.window) {
							refershParent();
						}
					} });
				});
			}});
		} else {
			ajaxSubFormWithFun("sqdswhForm", url, function(data) {
				showAlertDivLayer(data["message"], {}, { "clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				} });
			});
		}
	},'json');
}
	
/**
 * ����
 * 
 * @return
 */
function updateQsdsxx() {
	var bz = jQuery('#bz').val();
	var zgh = jQuery('#zgh').val();
	
	if (zgh==null || jQuery.trim(zgh)== '') {
		showAlertDivLayer("�뽫��<font color=\"red\">*</font>����Ŀ��д������");
		return false;
	}

	if (bz.length > 100) {
		showAlertDivLayer("��ע�������������" + 100 + ",��ȷ�ϣ�");
		return false;
	}
	
	
	var url = "gygl_qsdswh.do?method=qsdswhUpdateAction";
	ajaxSubFormWithFun("sqdswhForm", url, function(data) {
		showAlertDivLayer(data["message"], {}, { "clkFun" : function() {
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
		jQuery.post("gygl_qsdswh.do?method=qsdswhDeleteAction",{pks:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}


/************************************************************/

function loadLdxx() {
	if(jQuery('#lddm').val() == ''){
		/********/
		jQuery('#ldxb').text('');
		jQuery('#ldcs').text('');
		jQuery('#qsch').text('');
		jQuery('#sfhlc').text('');
		/********/
		jQuery('#ch').empty();
		jQuery('#ch').append("<option value=''>--��ѡ��--</option>");
		
		jQuery('#qsh').empty();
		jQuery('#qsh').append("<option value=''>--��ѡ��--</option>");
		
		return false;
	}
	
	jQuery.post('gyglnew_qsgl.do?method=loadLdxx', { lddm : jQuery('#lddm')
			.val() }, function(data) {
		var qsch = parseInt(data.qsch);
		var ldcs = parseInt(data.ldcs);
		var sfhlc = data.sfhlc;
		var ldxb = data.ldxb;
		var count = 0;
		
		/********/
		jQuery('#ldxb').text(ldxb);
		jQuery('#ldcs').text(ldcs);
		jQuery('#qsch').text(qsch);
		jQuery('#sfhlc').text(sfhlc);
		/********/
		jQuery('#ch').empty();
		jQuery('#ch').append("<option value=''>--��ѡ��--</option>");
		while (count < ldcs) {
			var chdm;
			var chmc;
			if ('��' == sfhlc) {

				if ((qsch + count) >= 0) {
					chdm = qsch > 0 ? qsch + count : qsch + count + 1;
					chmc = chdm + "��";
				} else {
					chdm = qsch + count;
					chmc = "B" + Math.abs(chdm) + "��";
				}

			} else {
				chdm = qsch + count;
				chmc = chdm < 0 ? "B" + Math.abs(chdm) + "��" : chdm + "��";
			}

			var option = "<option value=\"" + chdm + "\">" + chmc + "</option>"
			jQuery('#ch').append(option);

			count++;
		}
		loadQs();
	}, 'json');
}
function loadQs() {
	jQuery.getJSON('gyglnew_cwgl.do?method=getQshForLddm', {
		lddm : jQuery('#lddm').val(), ch : jQuery('#ch').val() },
			function(data) {
				jQuery('#qsh').empty();
				jQuery('#qsh').append("<option value=''>--��ѡ��--</option>");
				if (data != null && data.length != 0) {
					for ( var i = 0; i < data.length; i++) {
						var option = "<option value=\"" + data[i].qsh + "\">"
								+ data[i].qsh + "</option>";
						jQuery('#qsh').append(option);
					}
				}
			});
}

function loadQsdh() {
	jQuery.getJSON('gyglnew_cwgl.do?method=getQszInfoForLddm', {
		lddm : jQuery('#lddm').val(), qsh : jQuery('#qsh').val() },
			function(data) {
				jQuery('#qszxm').html('');
				jQuery('#qszlxdh').html('');
				if (data != null && data['qszxm'] != null) {
					jQuery('#qszxm').html(data['qszxm']);
					jQuery('#qszlxdh').html(data['qszlxdh']);
				}
			});
	//var obj = new Object;
	//obj.lddm = jQuery('#lddm').val();
	//obj.ch = jQuery('#ch').val();
	//obj.qsh = jQuery('#qsh').val();
	//jQuery.getJSON('gygl_qsdswh.do?method=loadQsdsxx', obj, function(data) {
	//	var dsxm = data.dsxm;
	//	var lxdh = data.lxdh;
	//	var dw = data.dw;
	//	var bz = data.bz;
	//	jQuery('#dsxm').val(dsxm);
	//	jQuery('#lxdh').val(lxdh);
	//	jQuery('#dw').val(dw);
	//	jQuery('#bz').val(bz);
	//});
}

/*************************************************************/

/**
 * �߼���ѯ
 * 
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

var DCCLBH = "gygl_qsdswhgl.do";// dcclbh,�������ܱ��

// �Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "gygl_qsdswh.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ����
 */
function importConfig() {
	toImportData("IMPORT_N381901");
	return false;
}

//������ϸ
function exportXx() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "gygl_qsdswh.do?method=exportDskh";
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ѧ������
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */

function xhLink(cellValue, rowObject) {
	var onclickfn = "onclick=\""
			+ "showDialog('���ҵ�ʦ����' , 720 , 350 , 'rcsw_zdzm_sqgl.do?method=viewZdzmSq&zdzmsqid="
			+ rowObject['zdzmsqid'] + "')" + "\"";

	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
			+ "</a>";

	return el;
}
/**
 * ���Һ�����
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */

function qshLink(cellValue, rowObject) {
	var xxdm = jQuery("#xxdm").val();
	if(xxdm == "12861"){
		var onclickfn = "onclick=\""
			+ "showDialog('��������' , 720 , 322 , 'gygl_qsdswh.do?method=qsdswhQsxxView&lddm="
			+ rowObject['lddm'] + "&qsh="
			+ rowObject['qsh'] + "')" + "\"";
		
		var href = "href = 'javascript:void(0);'";
		
		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
		+ "</a>";
		
		return el;
	}else if(xxdm == "10351"){
		var qshstr = rowObject['qsh'] + '';
		var onclickfn = "onclick=\""
			+ "showDialog('��������' , 720 , 322 , 'gygl_qsdswh.do?method=qsdswhQsxxView&lddm="
			+ rowObject['lddm'] + "&qsh="
			+ rowObject['qsh'] + "')" + "\"";
		
		var href = "href = 'javascript:void(0);'";
		
		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
		+ "</a>";
		
		return el;
	}else{
		return cellValue;
	}
}