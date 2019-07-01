function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xmmcLink(cellValue, rowObject) {
	if('1'==rowObject["sfrm"]){
		cellValue+="<font color='red'>�����š�</font>";
	}
	return "<a href='javascript:void(0);' class='name' onclick='XmsbjgView(\""
			+ rowObject["jgid"] + "\");'>" + cellValue + "</a>";
}

function xmcxLink(cellValue, rowObject) {
	if('1'==rowObject["sfrm"]){
		cellValue+="<font color='red'>�����š�</font>";
	}
	return "<a href='javascript:void(0);' class='name' onclick='xmcxView(\""
			+ rowObject["jgid"] + "\",\""
			+ rowObject["csms"] + "\");'>" + cellValue + "</a>";
}
function xmcxView(jgid,csms) {
	showDialog("��Ŀ��Ϣ�鿴", 800, 500, "xmsbXmsbjg.do?method=xmcxView&jgid="
			+ jgid+"&csms="+csms);
}
function XmsbjgView(jgid) {
//	showDialog("��Ŀ�걨�鿴", 800, 350, "xmsbXmsbjg.do?method=viewXmsbjg&jgid="
//			+ jgid);
	showDialog("��Ŀ�걨�鿴", 800, 500, "xmsbXmsbjg.do?method=viewXmsbjg&jgid="
			+ jgid);
}
//��Ŀ���ø�ʽ��
function setXmsz(cellValue, rowObject) {
	var jgid = rowObject.jgid;
	var value = "δ����";
	var status = '0';
	var color;
	var currDate = jQuery("#currDate").val();
	if ((null == rowObject.sqkssj || currDate >= rowObject.sqkssj)
			&& (null == rowObject.sqjssj || currDate <= rowObject.sqjssj)&&'1'==rowObject.sqkg) {
		value = "������";
		status = '1';
	}
	value = setColor(value, status);
	value = "<a  href='javascript:void(0);' onclick='return xmsz(\"" + jgid
			+ "\");' >" + value + "</a>";
	return value;
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
 * ��Ŀ��������
 */
function xmsz(jgid) {
	if (jgid == null) {// �����ť
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
			return false;
		}
		jgid = rows[0]["jgid"];
	}
	var url = 'xmsbXmsbjg.do?method=xmsz&jgid=' + jgid;
	var title = "��Ŀ����";
	showDialog(title, 680, 360, url);
}
function saveXmsbjg(type) {
	var flg = true;
	jQuery.ajaxSetup({async:false});
	jQuery.post("xmsbXmsbjg.do?method=sfxg", {xmdm:jQuery("#xmdm").val()}, function(data) {
		if(!data){
			flg = false;					
		}	      		
		}, 'json');
	jQuery.ajaxSetup({async:true});
	if(!flg){
		showAlertDivLayer("����Ŀ��ѧ���϶��������޸�");
		return false;
	}
	if (check()) {
	jxToJson();// ��װ������Ϣ
	var url = "xmsbXmsbjg.do?method=saveXmsbjg&type="+type;
	ajaxSubFormWithFun("XmsbjgForm", url, function(data) {
		if (data["message"] == "����ɹ���") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"]);
		}
	});
	}
}
function add() {
	var sqkg = jQuery("#sqkg").val();
	if ("0" == sqkg) {
		showAlertDivLayer("��ǰ�ѹرգ��������Ա��ϵ��");
		return false;
	}
	var url = "xmsbXmsbjg.do?method=addXmsbjg";
	var title = "��Ŀ�걨";
	showDialog(title, 800, 508, url);
}
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else if ('false' == rows[0]["sfkxg"]) {
		showAlertDivLayer("��ѡ���û������걨����Ŀ��");
	} else if(rows[0]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
	}else {
		var url = 'xmsbXmsbjg.do?method=editXmsbjg&jgid=' + rows[0]["jgid"];
		var title = "��Ŀ�걨�޸�";
		showDialog(title, 800, 508, url);
	}

}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��", {
		"okFun" : function() {
		var url = "xmsbXmsbjg.do?method=delXmsbjg";
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
	
}});
}
}
//�趨����
function setRmxm(value) {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var url = "xmsbXmsbjg.do?method=setRmxm";
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	}  else {
		jQuery.post(url, {
			value : value,
			ids:ids.toString()
		}, function(data) {
			showAlertDivLayer(data["message"], {}, {
				"clkFun" : function(tag) {
					jQuery("#dataTable").reloadGrid();
				}
			});
		},'json');
	}
	
}
/**
 * ����䷢
 * @return
 */
function jxbf() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�䷢�������Ŀ��");
	} else if ('false' == rows[0]["sfkxg"]) {
		showAlertDivLayer("��ѡ���û������걨����Ŀ��");
	} else {
		var url = 'xmsbXmsbjg.do?method=jxbf&jgid=' + rows[0]["jgid"];
		var title = "����䷢";
		showDialog(title, 800, 500, url);
	}

}
//����
function dr() {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_N960205_XMSBWH");
	return false;

}

var DCCLBH = "sztz_xmsbgl_xmwh.do";// dcclbh,�������ܱ��

// �Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, khsqExportData);
}

// ��������
function khsqExportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "xmsbXmsbjg.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//����ѧ�ֲ�ѯ����
function grxfExport() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport("sztz_rzjggl_grxfcx.do", grxfExportData);
}

function grxfExportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "xmsqgl_xmjg.do?method=grxfExportData&dcclbh=" + "sztz_rzjggl_grxfcx.do";// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
/**
 * ������չ������֤�����ѯ��������ѧ�ֲ�ѯ��ѧ��ͳ�Ʊ��ӡ
 */
function dyXftjbCommon(){
			
		var rows = jQuery("#dataTable").getSeletRow();
		
		if (rows.length != 1){
			showAlertDivLayer("��ѡ��һ������Ҫ���صļ�¼��");
			return false;
		} else {
			var url = "xmsqgl_xmjg.do?method=dyXftjbCommon&xh="+ rows[0]["xh"];
			
			window.open(url);
		}
}

function xscjPrint(){
	var rows = jQuery("#dataTable").getSeletRow();
	var xh = "";
	if(rows.length ==0){
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	}
	
	else if(rows.length == 1){
		xh = rows[0]["xh"];
		var url = "xmsqgl_xmjg.do?method=xscjPrint&xh="+xh;
		window.open(url);
	}
	else{
		for ( var i = 0; i < rows.length; i++) {
			if(i==rows.length-1){
				xh += rows[i]["xh"];
			}else{
				xh += rows[i]["xh"]+',';
				
			}
		}
		var url ="xmsqgl_xmjg.do?method=xscjPrintZip&xh="+xh;
		window.open(url);
	}
	
}

/**
 * ����
 */
function jxdr() {
	toImportDataNew("IMPORT_N960501_SZTZJXBF");
	return false;
}

//������Ŀ�׶�
function setXmjd(cellValue, rowObject) {
	var xmdm = rowObject.xmdm;
	var xmmc = rowObject.xmmc;
	var status = cellValue;
	var value = "δ����";
	var color;
	if (status == '1') {
		value = "������";
	}
	value = setColor(value, status);
	value = "<a  href='javascript:void(0);' onclick='return jdsz(\"" + xmdm
			+ "\",\"" + xmmc + "\");' >" + value + "</a>";
	return value;
}

//��Ŀ�׶����õ������
function jdsz(xmdm,xmmc) {
	if (xmdm == null) {// �����ť
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
			return false;
		}
		xmdm = rows[0]["xmdm"];
	}
	var url = 'xmsbXmsbjg.do?method=jdsz&xmdm=' + xmdm+"&xmmc="+xmmc;
	var title = "�׶�����";
	showDialog(title, 680, 360, url);
}

