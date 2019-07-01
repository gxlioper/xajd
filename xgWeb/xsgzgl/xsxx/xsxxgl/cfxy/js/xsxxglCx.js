var cxjgzdpzList;
var DCCLBH = "xsxx_xsxxgl.do";// dcclbh,�������ܱ��

var gridSetting = {
	caption : "ѧ����Ϣ�б�",
	pager : "pager",
	url : "",
	colList : [],
	sortname : "",
	sortorder : "asc"
}

jQuery(function() {
	onShow();
});

function onShow() {
	// �б���ʾ�ֶ�����
	var url = "xsxx_xsxxgl.do?method=getCxjgzdpz";
	jQuery.ajax( {
		type : "post",
		// async: false,
		url : url,
		data : {},
		dataType : "json",
		success : function(data) {
			cxjgzdpzList = data;// ��ѯ����ֶ�
		xsxxsj();
	}
	});
	
	if (jQuery("#path").val() == "xsxx_xsxxgl_cxfzxs.do") {// ����У��
		jQuery(".toolbox .buttonbox ul li:not(:has(a[id='btn_ck']))").hide();
	}
}

/**
 * ѧ����Ϣ����
 * 
 * @return
 */
function xsxxsj() {
	if (cxjgzdpzList != null) {
		gridSetting.colList = eval(createColModelJson());
		gridSetting.sortname = getSortname();
		gridSetting.url = "xsxx_xsxxgl.do?type=query&method="
				+ jQuery("#method").val();
	}
	jQuery("#dataTable").initGrid(gridSetting);
}

/*
 * ����colModel��json��ʽ
 */
function createColModelJson() {
	var str = "[";
	str += "{label : '',name : 'xh',index : 'xh',key : true,hidden : true,formatter:setKey}";
	for ( var i = 0; i < cxjgzdpzList.length; i++) {
		var o = cxjgzdpzList[i];
		var zd = o.zd;
		var zdmc = o.zdmc;
		//�������֤�Ų���ʾ
		if(zd!="sfzh"){
			str += ",";
			str += "{";
			str += "label:'" + zdmc + "'";
			str += ",name:'" + zd + "'";
			str += ",index:'" + zd + "'";
			if (zd == "xh") {
				str += ",formatter:setXh";
			}
			str += "}";
		}
	}
	str += "]";
	return str;
}

function setKey(cellValue, rowObject) {
	var xh = rowObject.xh;
	if (xh == null) {
		xh = "";
	}
	cellValue = "<a  href='javascript:void(0);' onclick='zxsxxView(\"" + xh
			+ "\");return false;' >" + xh + "</a>";
	cellValue += "<input type='hidden' name='key_xh' value='" + xh + "' >";
	return cellValue;
}

function setXh(cellValue, rowObject) {
	var xh = rowObject.xh;
	if (xh == null) {
		xh = "";
	}
	cellValue = "<a  href='javascript:void(0);'  class='name'   onclick='zxsxxView(\"" + xh
			+ "\");return false;' >" + xh + "</a>";
	return cellValue;
}

/*
 * ��ȡ������
 */
function getSortname() {
	var str = "";
	for ( var i = 0; i < cxjgzdpzList.length; i++) {
		var o = cxjgzdpzList[i];
		var zd = o.zd;
		var zdmc = o.zdmc;
		if (i > 0) {
			str += ",";
		}
		str += zd;
		if (i > 2) {
			break;
		}
	}
	return str;
}

// ��ѯ
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


// �鿴
function showzxsxxView() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		zxsxxView(ids);
	} else {
		showAlertDivLayer("��<font color='blue'>��ѡһ��</font>��ϣ���鿴�ļ�¼��");
		return false;
	}
}

// �°�鿴������
function zxsxxView(xh) {
	showDialog("ѧ����Ϣ��ѯ", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCkByCfxy&xh=" + xh
			+ "&xs");
}

// ɾ��
function deletezxsxx() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len != 0) {
		showConfirmDivLayer("����ȷ��<font color='blue'>�Ƿ�ɾ��</font>����ѡ�ļ�¼��", {
			"okFun" : function() {
				var url = "xsxx_xsxxgl.do?method=xsxxglSc";
				jQuery.post(url, {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"], {}, {
						"clkFun" : function() {
							jQuery("#dataTable").reloadGrid();
						}
					});
				}, 'json');
			}
		});
	} else {
		showAlertDivLayer("��<font color='blue'>��ѡ</font>��ϣ��ɾ���ļ�¼��");
		return false;
	}
}
// �����ʼ��
function mmcsh() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��<font color='blue'>��ѡ</font>����Ҫ��ʼ�������ݣ�");
	} else {
		showDialog("�����ʼ��", 350, 210, "xsxx_xsxxgl.do?method=mmcsh");
	}
}

// ��ӡͨ��ѧ����
function printTyXjk() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=getXjk";
		url += "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��<font color='blue'>��ѡһ��</font>��ϣ�����صļ�¼��");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getXjkZipTy";
		url += "&value=" + ids;
		window.open(url);
	}
}

// ��ӡ����
function printZxsxx() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=printJsp";
		url += "&xh=" + ids;
		window.open(url);
	} else {
		showAlertDivLayer("��<font color='blue'>��ѡһ��</font>��ϣ���޸ĵļ�¼��");
		return false;
	}
}

function getWord() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=getXjkWord";
		var url = url + "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��<font color='blue'>��ѡһ��</font>��ϣ�����صļ�¼��");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getXjkZip";
		url += "&value=" + ids;
		window.open(url);
	}
}

function printXjk() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len >= 1) {
		jQuery("#xhstr").val(ids);
		var url = "xsxx_tygl.do?method=plPrintJsp";
		document.forms[0].action = url;
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	} else {
		alertInfo("��<font color='blue'>��ѡһ��</font>��ϣ���޸ĵļ�¼��");
		return false;
	}
}

// �Զ��嵼�� ����
function zxsxxExportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, zxsxxExportData, 1000, 500);
}

// ��������
function zxsxxExportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "xsxx_xsxxgl.do?dcclbh=" + DCCLBH + "&method=";
	if (jQuery("#path").val() == "xsxx_xsxxgl_cxfzxs.do") {// ����У��
		url += "fzxsxxExportData";
	} else {
		url += "zxsxxExportData";
	}
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
