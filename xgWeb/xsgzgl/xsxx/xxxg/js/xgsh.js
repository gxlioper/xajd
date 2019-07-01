var gridSetting = {
	caption : "ѧ����Ϣ�޸�����б�",
	pager : "pager",
	url : "xsxx_xsxxxg.do?method=xgsh&type=query",
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
		width : '12%',
		formatter : setXh
	}, {
		label : '����',
		name : 'xm',
		index : 'xm',
		width : '10%'
	}, {
		label : '�Ա�',
		name : 'xb',
		index : 'xb',
		width : '5%'
	}, {
		label : jQuery("#xbmc").val(),
		name : 'xymc',
		index : 'xydm',
		width : '20%'
	}, {
		label : '�༶',
		name : 'bjmc',
		index : 'bjdm',
		width : '20%'
	}, {
		label : '����ʱ��',
		name : 'xgsj',
		index : 'xgsj',
		width : '18%'
	}, {
		label : 'gwid',
		name : 'gwid',
		index : 'gwid',
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
	}, {
		label : '��˸�λ',
		name : 'mc',
		index : 'mc',
		hidden : true
	}, {
		label : '���id',
		name : 'guid',
		index : 'guid',
		hidden : true
	}, {
		label : '���״̬',
		name : 'shzt',
		index : 'shzt',
		width : '15%',
		formatter : setShzt
	} ],
	params : {
		shzt : "dsh"
	},// Ĭ�ϴ����
	sortname : "xgsj",
	sortorder : "desc"
}
var gridSetting2 = {
	caption : "ѧ����Ϣ�޸�����б�",
	pager : "pager",
	url : "xsxx_xsxxxg.do?method=xgsh&type=query",
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
		width : '12%',
		formatter : setXh
	}, {
		label : '����',
		name : 'xm',
		index : 'xm',
		width : '10%'
	}, {
		label : '�Ա�',
		name : 'xb',
		index : 'xb',
		width : '5%'
	}, {
		label : jQuery("#xbmc").val(),
		name : 'xymc',
		index : 'xydm',
		width : '15%'
	}, {
		label : '�༶',
		name : 'bjmc',
		index : 'bjdm',
		width : '20%'
	}, {
		label : '���ʱ��',
		name : 'shsj',
		index : 'shsj',
		width : '18%'
	}, {
		label : 'gwid',
		name : 'gwid',
		index : 'gwid',
		hidden : true
	}, {
		label : '���id',
		name : 'guid',
		index : 'guid',
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
	}, {
		label : '��˸�λ',
		name : 'mc',
		index : 'mc',
		hidden : true
	}, {
		label : '���״̬',
		name : 'shzt',
		index : 'shzt',
		width : '15%',
		formatter : setShzt

	} ],
	params : {
		shzt : "ysh"
	},// �����
	sortname : "shsj",
	sortorder : "desc"
};
jQuery(function() {
	var map = getSuperSearch();
	map["shzt"] = "dsh";
	gridSetting["params"] = map;
	jQuery("#dataTable").initGrid(gridSetting);
});

function setXh(cellValue, rowObject) {
	var xh = rowObject.xh;
	if (xh == null) {
		xh = "";
	}
	cellValue = "<a  href='javascript:void(0);'  class='name'  onclick='xgjgCk(\""
			+ xh
			+ "\",\""
			+ rowObject.ywid
			+ "\",\""
			+ rowObject.lcid
			+ "\" );return false;' >" + xh + "</a>";
	return cellValue;

}

function setShzt(cellValue, rowObject) {
	var mc = rowObject.mc;
	var value;
	if (cellValue == '0') {
		value = "�����";
	} else if (cellValue == '1') {
		value = "ͨ��";
	} else if (cellValue == '2') {
		value = "��ͨ��";
	} else if (cellValue == '3') {
		value = "�˻�";
	} else if (cellValue == '4') {
		value = "������";
	}
	value = mc + "[" + value + "]";
	return value;
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

function xgjgCk(xh, ywid, lcid) {
	if (lcid == "") {
		showAlertDivLayer("������������ˣ������������Ϣ��");
		return false;
	}
	var url = "xsxx_xsxxxg.do?method=xgjgCk";
	url += "&xh=" + xh;
	url += "&sqid=" + ywid;
	showDialog("ѧ����Ϣ�޸Ĳ鿴", 750, 500, url);
}

/**
 * ���
 * 
 * @return
 */
function xgshZj() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shkg = jQuery("#shkg").val();
	if(shkg=="n"){
		showAlertDivLayer("��ǰδ������ˣ�����ϵ����Ա��");
		return false;
	}
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ��һ���������Ҫ�޸ĵļ�¼��");
	} else if (rows.length == 1) {
		var url = "xsxx_xsxxxg.do?method=xgshZj";
		url += "&timestamp=" + new Date().getTime();
		url += "&xh=" + rows[0]["xh"];
		url += "&gwid=" + rows[0]["gwid"];
		url += "&ywid=" + rows[0]["ywid"];
		url += "&shid=" + rows[0]["guid"];
		url += "&lcid=" + rows[0]["lcid"];
		showDialog("ѧ����Ϣ�޸����", 750, 500, url);
	} else {
		var dataJson = "";
		var url = "xsxx_xsxxxg.do?method=xgshPlzj";
//		for ( var i = 0; i < rows.length; i++) {
//			var row = rows[i];
//			if (dataJson != "") {
//				dataJson += ",";
//			}
//			dataJson += "{'xh':'" + row["xh"];
//			dataJson += "','gwid':'" + row["gwid"];
//			dataJson += "','ywid':'" + row["ywid"];
//			dataJson += "','shid':'" + row["guid"];
//			dataJson += "','lcid':'" + row["lcid"];
//			dataJson += "'}";
//		}
//		if (dataJson != "") {
//			dataJson = "[" + dataJson + "]";
//		}
		url += "&params=" + dataJson + "&timestamp=" + new Date().getTime();
		showDialog("ѧ����Ϣ�޸��������", 550, 200, url);
	}

}

/**
 * ������˱���
 * @return
 */
function savePlsh(shjg,thgw,shyj){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var dataJson = "";
	for ( var i = 0; i < rows.length; i++) {
		var row = rows[i];
		if (dataJson != "") {
			dataJson += ",";
		}
		dataJson += "{'xh':'" + row["xh"];
		dataJson += "','gwid':'" + row["gwid"];
		dataJson += "','ywid':'" + row["ywid"];
		dataJson += "','shid':'" + row["guid"];
		dataJson += "','lcid':'" + row["lcid"];
		dataJson += "'}";
	}
	if (dataJson != "") {
		dataJson = "[" + dataJson + "]";
	}
	jQuery.post(
			"xsxx_xsxxxgsh.do?method=savePlshlc",
			{
			 shjg:shjg,
			 dataJson:dataJson,
			 shyj:shyj,
			 thgw:thgw
			},function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					jQuery("#dataTable").reloadGrid();
				}});
			},
			'json'
		);
}

/**
 * �л������롢δ����
 * 
 * @param obj
 * @param tabId
 * @return
 */
function selectSqxm(obj, tabId) {

	jQuery("#comp_title li").removeClass("ha");
	jQuery(obj).parent().addClass("ha");

	jQuery("#xmList tbody").css("display", "none");
	jQuery("#" + tabId).css("display", "");

	if (tabId == "ysq") {
		jQuery("#titleTr td").last().css("display", "none");
	} else {
		jQuery("#titleTr td").last().css("display", "");
	}
}

/**
 * �л����ҳtabҳ
 * 
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);

	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");

		var map = getSuperSearch();
		map["shzt"] = "dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");

		var map = getSuperSearch();
		map["shzt"] = "ysh";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}

	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}

function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();

	if (shzt != "") {
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}

/*
 * ����
 */
function cxshnew() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		alertInfo("��ѡ��һ����Ҫ������˵ļ�¼��");
	} else {
		splc_cx_new(rows[0]["lcid"], rows[0]["guid"], rows[0]["ywid"]);
	}
}
/*
 * �������̳��� shid ���id splc ��������id
 */
function splc_cx_new(splc, shid, ywid) {
	// ���һ��������˺���õ�·��
	var url = "xsxx_xsxxxg.do?method=thRecordForStu";
	confirmInfo("��ȷ��Ҫ����������?", function(ty) {
		if (ty == "ok") {
			jQuery.post("xsxx_xsxxxg.do?method=updateSqzt", {
				sqid : ywid,
				shlc : splc,
				shid : shid
			}, function(data) {
				// �ж��Ƿ����һ������(1:���һ�������ɹ���
					if ("1" == data["cancelFlg"]) {
						jQuery.post(url, {
							sqid : ywid
						}, function(result) {
							showAlertDivLayer(data["message"], {}, {
								"clkFun" : function() {
									jQuery("#dataTable").reloadGrid();
								}
							});
						}, 'json');
					} else {
						showAlertDivLayer(data["message"], {}, {
							"clkFun" : function() {
								jQuery("#dataTable").reloadGrid();
							}
						});
					}

				}, 'json');
		}
	});
}

//�Զ��嵼�����ܣ���ˣ�
function exportConfigSh() {	
	var DCCLBH = "xsxx_xsxxxg_xgsh.do";//dcclbh,�������ܱ�ţ�ִ�е������� 
	customExport(DCCLBH, exportDataSh);
}

//������������ˣ�
function exportDataSh() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//���ø߼���ѯ����
	var DCCLBH = "xsxx_xsxxxg_xgsh.do";
	var url = "xsxx_xsxxxg.do?method=exportDataSh&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
