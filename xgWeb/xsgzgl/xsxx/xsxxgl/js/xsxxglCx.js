var cxjgzdpzList;
var DCCLBH = "xsxx_xsxxgl.do";// dcclbh,�������ܱ��

var gridSetting = {
	caption : "ѧ����Ϣ�б�",
	pager : "pager",
	url : "",
	colList : [],
	sortname : "",
	sortorder : ""
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
		jQuery(".toolbox .buttonbox ul li:has(a[id='btn_ck'])").show();
		jQuery(".toolbox .buttonbox ul li:has(a[id='btn_dc'])").show();
        jQuery(".toolbox .buttonbox ul li:has(a[id='btn_xsxxdy'])").show();
        jQuery("#buttonbox2").hide();
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
		if('10352' == jQuery('#xxdm').val() || "10351" == jQuery("#xxdm").val()) {
			gridSetting.sortname = "nj desc nulls last,xydm";
			gridSetting.sortorder = "desc nulls last";
		} else {
			gridSetting.sortname = getSortname();
		}
		gridSetting.url = "xsxx_xsxxgl.do?type=query&method="
				+ jQuery("#method").val();
	}
	gridSetting["params"]=getSuperSearch();
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
		str += ",";
		str += "{";
		str += "label:'" + zdmc + "'";
		str += ",name:'" + zd + "'";
		str += ",index:'" + zd + "'";
		if (zd == "xh") {
			str += ",width:'13%',formatter:setXh";
		}
		if(zd=="nj"){
			str +=",width:'8%'";
		}
		if(zd=="xm"){
			str +=",width:'8%'";
		}
		if(zd=="xb"){
			str +=",width:'4%'";
		}
		str += "}";
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
	var str = "xymc,bjmc,xh";
	return str;
}

// ��ѯ
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

// ����
function showzxsxxAdd() {
	var url = "xsxx_xsxxgl.do?method=xsxxglZj";
	showDialog("����ѧ����Ϣ", 850, 360, url);
}

// �޸�
function showzxsxxEdit() {

	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_xsxxgl.do?method=xsxxglXg";
		url += "&xh=" + ids;
		showDialog("�޸�ѧ����Ϣ", 950, 550, url);
	} else {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	}
}

function ycsjTs(){
	var url = "xsxx_xsxxgl.do?method=ycsjTs";
	showConfirmDivLayer("����ͬ���쳣����", {
		"okFun" : function() {
			jQuery.post(url, {
			}, function(data) {
				alertInfo(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
	});
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
	showDialog("ѧ����Ϣ��ѯ", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh);
}

// ɾ��
function deletezxsxx() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len != 0) {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
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
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	}
}
// �����ʼ��
function mmcsh() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		// ����
		jQuery("#pksPlHidden").val("");
		// ���ݲ�ѯ���������������
		var rowConut = jQuery("#rowConut").html();
		var url = "xsxx_xsxxgl.do?method=mmcshPl&len="+rowConut;
		var title = "�����ʼ��";
		showDialog(title,350,255,url);
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
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getXjkZipTy";
		url += "&value=" + ids;
		window.open(url);
	}
}


//��ӡ�����Ƽ�ʦ����ѧ������ѧ�ǼǱ��㽭��ý�����ǼǱ�
function printRxdjb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=getDjb";
		jQuery("#xh").val(ids);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getDjbZip";
		jQuery("#value").val(ids);
	}
	
	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}


//���ϻ���ְҵ����ѧԺ���Ի��ǼǱ��ӡ
function printDjb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=getXjk&hnjdDjb=djb";
		url += "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getXjkZipTy&hnjdDjb=djb";
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
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	}
}
//��ӡ����֯��ϵ����
function printZzjsx() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=printZzjsx";
		var url = url + "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��<font color='blue'>��ѡ</font>��ϣ�����صļ�¼��");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=printZzjsxZip";
		url += "&value=" + ids;
		window.open(url);
	}
}

function tyqntj() {
		var url = "xsxx_xsxxgl.do?method=tyqntjExport";
		window.open(url);
}

function getWord() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=getXjkWord";
		var url = url + "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getXjkZip";
		url += "&value=" + ids;
		window.open(url);
	}
}
// ����ʦ����ѧ ����ѧ����Ƭ
function getXskpWord() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=getXskpWord";
		var url = url + "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		//�ⲿ�ֻ�û�����
		var url = "xsxx_tygl.do?method=getXskpZip";
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
		alertInfo("��ѡ����Ҫ���صļ�¼��");
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
	url+="&rowConut="+jQuery("#rowConut").html();
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
// �ɰ浼��
function drxx(){
	toImportData("IMPORT_N100101");
	return false;
}
// �°浼��
function drxxNew(){
	toImportDataNew("IMPORT_N100101_NEW");
	return false;
}

//��������
function zjjcXshzExportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "xsxx_xsxxgl.do?method=exportDataZjjc";
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//��������
function zjjcZcfExportData() {
	var ids = jQuery("#dataTable").getSeletRow();
	var len = ids.length;
	var url = "xsxx_xsxxgl.do?method=exportZcfDataZjjc";
	if(len==0){
		showAlertDivLayer("�빴ѡ��Ҫ������ѧ��");
		return false;
	}
	if(len>=100){
		showAlertDivLayer("��ѡ���������𳬹�100��ѧ��");
		return false;
	}
	if(len==1){
		url = url+"&xhs="+ids[0]["xh"];
	}else if(len>1){
		var xhAll = new Array();
		for(var i=0;i<len;i++){
			xhAll.push(ids[i]["xh"]);
		}
		url = url+"&xhs="+xhAll.join(",");
	}
	 
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//��������
function zjjcZhqkExportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "xsxx_xsxxgl.do?method=exportZhqkDataZjjc";
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}



//�������������ɼ�����ӡ
function printCjd() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=printCjd";
		url += "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getCjdZip";
		url += "&value=" + ids;
		window.open(url);
	}
}


//�Ϻ����������ǼǱ�
function printXsdjb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=printXsdjb";
		url += "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getXsdjbZip";
		url += "&value=" + ids;
		window.open(url);
	}
}
/**���������߼�����ѧУѧ����_���Ի���*/
function printHzqcxjk() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=printHzqcxjk";
		var url = url + "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getHzqcxjkZip";
		url += "&value=" + ids;
		window.open(url);
	}
}
/** ����ҽҩѧ����_���Ի� */
function printXzyyXjk() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=printXzyyxjk";
		var url = url + "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getXzyyxjkZip";
		url += "&value=" + ids;
		window.open(url);
	}
}
/**�㽭��ѧ������ѧ�ǼǱ�_���Ի�*/
function printXsrxdjb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=Xsrxdjb";
		var url = url + "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=XsrxdjbZip";
		url += "&value=" + ids;
		window.open(url);
	}
}
/**������ҵ��ѧ������������Ϣ�ǼǱ�_���Ի�*/
function printJbxxdjb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=Jbxxdjb";
		var url = url + "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=JbxxdjbZip";
		url += "&value=" + ids;
		window.open(url);
	}
}







/**��ͨũҵְҵ����ѧԺ_12684ѧ�������Ƴɼ�����Ի�*/
	function printXscjb(){
		var ids = jQuery("#dataTable").getSeletIds();
		var len = ids.length;
		if(len == 1){
			var url = "xsxx_tygl.do?method=getPrintXscjb";
			var url = url + "&xh=" + ids;
			window.open(url);
		} else if (len == 0) {
			showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
			return false;
		} else {
			var url = "xsxx_tygl.do?method=getPrintXscjbZip";
			url += "&value=" + ids;
			window.open(url);
		}
	}
/**��ͨũҵְҵ����ѧԺ_12684ѧ�������Ƴɼ�����Ի�*/
function printXscjbwu(){
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if(len == 1){
		var url = "xsxx_tygl.do?method=getPrintXscjbwu";
		var url = url + "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getPrintXscjbwuZip";
		url += "&value=" + ids;
		window.open(url);
	}
}

/**����ְҵ����ѧԺ_13265��ӡ�༶������*/
function xnzyjsxyhmcExportData() {
	setSearchTj();
	var map = getSuperSearch();	
	var searchTj = map["searchTj"];
	if(searchTj.indexOf("searchModel.search_tj_xy") == -1){
		showAlertDivLayer("��ӡ�༶���������ѡ��һ��ѧԺ!");
		return false;
	}else{
		var url = "xsxx_tygl.do?method=xnzyjsxyhmcExportData"
		url = addSuperSearchParams(url);//���ø߼���ѯ����
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
}


// ��������
function getPrintTY() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xsxx_tygl.do?method=getPrintTY&dcclbh=" + "xsxx_tyxx_cxfzxs.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

	

	
// ��������
function getPrintTGB() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xsxx_tygl.do?method=getPrintTGB&dcclbh=" + "xsxx_tgbxx_cxfzxs.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
/**
 * ѧ�����֤����ӡ�����칤��ְҵ����ѧԺ��
 */
function printSI() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_tygl.do?method=getSI";
		url += "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "xsxx_tygl.do?method=getSIZip";
		url += "&value=" + ids;
		window.open(url);
	}
}

function saveSxzt(zd1) {
	var rows = jQuery("#dataTable").getSeletRow();
	var xhs = new Array();
	zd1 = encodeURI(encodeURI(zd1));
	jQuery.each(rows, function(i, row) {
		xhs.push(row["xh"]);
	});
	jQuery.post("xsxx_xsxxgl.do?method=updateSxzt", {
		zd1 : zd1,
		xhs : xhs
	}, function(data) {
		
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}