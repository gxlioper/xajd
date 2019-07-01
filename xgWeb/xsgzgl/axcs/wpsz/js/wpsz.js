var tjszDialog;
var gridSetting = {
	caption : "��Ŀ�б�",
	pager : "pager",
	url : "axcsWpsz.do?method=wpszList&type=query",
	colList : [ {
		label : '��Ŀ����',
		name : 'xmdm',
		index : 'xmdm',
		key : true,
		hidden : true
	}, {
		label : 'xxxxjs',
		name : 'xmxxjs',
		index : 'xmxxjs',
		hidden : true
	},{
		label : 'ר����Ʒ����',
		name : 'xmmc',
		index : 'xmmc',
		width : '12%',
		formatter:xmdmLink
	},{
		label : 'ѧ��',
		name : 'xn',
		index : 'xn',
		width : '12%'
	
	},{
		label : '���',
		name : 'lbmc',
		index : 'lbmc',
		width : '12%'
	}, {
		label : 'ͼƬ',
		name : 'xmtp',
		index : 'xmtp',
		width : '12%',
		formatter : setTp
	}, {
		label : '��ϸ����',
		name : 'xmjs',
		index : 'xmjs',
		width : '24%',
		formatter : setXxjs
	}, {
		label : '��������',
		name : 'jbsz',
		index : 'jbsz',
		width : '14%',
		formatter : setJbsz
	}, {
		label : '��������',
		name : 'tjsz',
		index : 'tjsz',
		width : '14%',
		formatter : setTjsz
	},
	{label : '���뿪ʼʱ��',name : 'sqkssj',index : 'sqkssj',width : '8%',hidden : true},
	{label : '�������ʱ��',name : 'sqjssj',index : 'sqjssj',width : '8%',hidden : true},
	{label : '��˿�ʼʱ��',name : 'shkssj',index : 'shkssj',width : '8%',hidden : true},
	{label : '���뿪��',name : 'sqkg',index : 'sqkg',width : '8%',hidden : true},
	{label : '��˿���',name : 'shkg',index : 'shkg',width : '8%',hidden : true},
	{label : '��˽���ʱ��',name : 'shjssj',index : 'shjssj',width : '8%',hidden : true}
	],
	sortname : "xmdm",
	sortorder : "asc"
}

jQuery(function() {
	gridSetting["params"]={"xn":jQuery("#xn").val()};
	jQuery("#dataTable").initGrid(gridSetting);
});
function xmdmLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='wpCk(\""+rowObject["xmdm"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}
function setXxjs(cellValue,rowObject){
return "<span title='"+rowObject["xmxxjs"]+"'>"+cellValue+"</span>";
}

function setColor(value, status) {
	var color;
	if (status == '1') {
		color = "#004400";
	} else {
		color = "red";
	}
	return value = "<font color='" + color + "'>" + value + "</font>";
}

/*
 * ��������
 */
function setJbsz(cellValue, rowObject) {
	var xmdm = rowObject.xmdm;
	var value = "δ����";
	var status = '0';
	var color;
	var currDate = jQuery("#currDate").val();
	if (((null == rowObject.sqkssj || currDate >= rowObject.sqkssj)
			&& (null == rowObject.sqjssj || currDate <= rowObject.sqjssj)&&'1'==rowObject.sqkg)
			||((null == rowObject.shkssj || currDate >= rowObject.shkssj)
					&& (null == rowObject.shjssj || currDate <= rowObject.shjssj)&&'1'==rowObject.shkg)) {
		value = "������";
		status = '1';
	}
	value = setColor(value, status);
	value = "<a  href='javascript:void(0);' onclick='return jbsz(\"" + xmdm
			+ "\");' >" + value + "</a>";
	return value;
}

function setTp(cellValue, rowObject) {
	var xmdm = rowObject.xmdm;
	value = "<img style='width:100%;height:68px;margin:0;float:left;padding-right: 0px;' id='axwptp' src='axcsWpsz.do?method=showPhoto&xmdm="+xmdm+"&type=view'>";
	return value;
}

/*
 * ��������
 */
function jbsz(xmdm) {
	if (xmdm == null) {// �����ť
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
			return false;
		}
		xmdm = rows[0]["xmdm"];
	}
	var url = 'axcsWpsz.do?method=wpJbsz&xmdm=' + xmdm;
	var title = "��������";
	showDialog(title, 680, 350, url);
}
function jesfkt(cellValue, rowObject) {
	var jesfxssq = rowObject.jesfxssq;
	var value = '';
	if (jesfxssq == '1') {
		value = cellValue + "<font color='red'>(��)</font>";
	} else {
		value = cellValue;
	}
	return value;
}

/*
 * ��������
 */
function setTjsz(cellValue, rowObject) {
	var xmdm = rowObject.xmdm;
	
	var value;
	if (cellValue=="1") {
		value = "������";
	} else {
		value = "δ����";
	}
	value = setColor(value, cellValue);
	value = "<a  href='javascript:void(0);' onclick='return tjsz(\"" + xmdm + "\");'>" + value + "</a>";
	return value;
}


function query() {
	var map = {};
	map["xmmc"] = jQuery.trim(jQuery("#xmmc").val());
	map["xn"] = jQuery.trim(jQuery("#xn").val());
	jQuery("#dataTable").reloadGrid(map);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'axcsWpsz.do?method=updateWp&xmdm=' + rows[0]["xmdm"];
		var title = "��Ʒ�޸�";
		showDialog(title, 600, 300, url);
	}
}
function wpCk(xmdm){
	showDialog("��Ʒ��ѯ",600,300,"axcsWpsz.do?method=ckWp&xmdm="+xmdm);
}

function add() {
	var url = "axcsWpsz.do?method=addWp";
	var title = "����ר�����Ʒ";
	showDialog(title, 600, 300, url);
}
function showZpZjDiv() {
	tipsWindown("ϵͳ��ʾ", "id:addPic", "380", "130", "true", "", "true", "id");
}

// ��Ƭ�ϴ�
function uploadWpPic() {

	jQuery.ajaxSetup( {
		async : false,
		dataType : 'text'
	});

	var xmdm = jQuery("#xmdm").val();

	jQuery.ajaxFileUpload( {
		url : 'axcs_wpsz_ajax.do?method=uploadWpPic&xmdm=' + xmdm,
		secureuri : false,
		fileElementId : 'xmtp',
		success : function(data, type) {
			if (type == 'success') {
				jQuery("#axwptp").attr(
						"src",
						"axcsWpsz.do?method=showPhoto&xmdm=" + xmdm + "&tt="
								+ new Date());
				// jQuery('#zpsfcz').attr("value", "true");
				alertInfo(data);
			}
		}
	});
}

/* �������� */
function tjsz(xmdm) {
	if (xmdm == null) {// �����ť
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
			return false;
		}
		xmdm = rows[0]["xmdm"];
	}
	var url = 'axcsWpsz.do?method=wpTjsz';
	url += "&xmdm=" + xmdm;
	var title = "��������";
	tjszDialog = showDialog(title, 750, 340, url, {
		close : function() {
			query();
		}
	});
}
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��", {
			"okFun" : function() {
				var url = "axcsWpsz.do?method=delWp";
				jQuery.post(url, {
					values : ids.toString()
				}, function(data) {
					if (data["success"] == false) {
						showAlertDivLayer(data["message"]);
					} else {
						showAlertDivLayer(data["message"], {}, {
							"clkFun" : function(tag) {
								jQuery("#dataTable").reloadGrid();
							}
						});
					}
				}, 'json');
			}
		});
	}
}

/* ��Ŀ���� */
function wpfz() {
	var url = 'axcsWpsz.do?method=wpFz';
	var title = "��Ʒ����";
	showDialog(title, 390, 150, url);
}

function saveWpFz() {
	var wpfzxn = jQuery("#xn").val();
	if(wpfzxn == null || wpfzxn == ""){
		showAlert("��ѡ������Դѧ�꣡");
		return false;
	}
	
	var url = 'axcsWpsz.do?method=wpFz&type=save';
	ajaxSubFormWithFun("WpszForm", url, function(data) {
		if (data["success"] != undefined && (data["success"] == false || data["success"] == "false" )) {
			showAlert(data["message"]);
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function(tag) {
					if (tag == "ok") {
						refershParent();
					}
				}
			});
		}
	});


}
