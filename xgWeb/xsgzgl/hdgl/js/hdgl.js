/**
 * ������б�ҳ��js
 */

/**
 * ����
 */
function searchRs(){
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

/**
 * ���������ҳ����ʾ
 */
function hdShow(hdid) {
    var url = "hdgl_hdgl.do?method=hdglView&hdid="+hdid;
    document.forms[0].action=url;
    document.forms[0].submit();
}

/**
 * ר���ų�Ա����
 */
function zjtcysz() {

    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length > 0) {
        var str = "";
        for(i=0;i<ids.length;i++){
            blog=true;
            str+="&ids="+ids[i];
        }
        var url = "hdgl_hdgl.do?method=zjtcysz"+str;
        var title = "ר���ų�Ա����";
        showDialog(title, 800, 500, url);
    }else {
        showAlertDivLayer("��ѡ����Ҫ���õļ�¼��");
    }
}

/**
 * �׶γ�Ա����,���ڻ������׶������ԱȨ��
 * @return
 */
function jdcysz(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	}else{
		var url = "hdgl_hdgl.do?method=jdcysz&hdid="+rows[0]["hdid"];
        var title = "�׶γ�Ա����";
        showDialog(title, 800, 500, url);
	}
}

