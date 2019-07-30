
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='hdbljgView(\""
			+ rowObject["jgid"]+"\");'>" + cellValue
			+ "</a>";
}

function hdbljgView(jgid) {
	showDialog("�����鿴", 800, 550, "hdgl_hdbljg.do?method=viewHdbljg&jgid="+jgid);
}


function save(type) {
	var download = jQuery(".MultiFile-label").length;
	var hdxs = jQuery("#hdxs").val();
	var jzlx = jQuery("#jzlx").val();
	//var checkboxs = jQuery("[name='hdbqs']:checked").length;
	var ids = null;
	if(type=='save'){
		ids = "xh-hdmc-hdsj-hdxs-hdlx-zbf";
	}else{
		ids = "hdmc-hdsj-hdxs-hdlx-zbf";
	}
    if("����" == hdxs){
       ids += "-zjrxm-zjrdw-zjrzc-zjrzw-jzjb";
    }
	if(!checkNotNull(ids)){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	if(download < 1){
		showAlert("���ϴ�����");
		return false;
	}
	var nlbqLen = jQuery("[name='nlbqs']:checked").length;
	if(nlbqLen > 3){
        showAlert("������ǩ���ֻ��ѡ��������ȷ�ϣ�");
        return false;
	}
//	if(checkboxs < 1){
//		showAlert("������ѡ��һ�����ǩ��");
//		return false;
//	}
	
	if("�γ�" == hdxs){
		if(jzlx == null || jzlx == ''){
			showAlert("��ѡ��γ̼���");
			return false;
		} else {
			var zxkclx = jQuery("#zxkclx").val();
			if(zxkclx == ""){
                showAlert("��ѡ����ѡ�γ����ͣ�");
                return false;
			}
		}
	}


	
	var url = "hdgl_hdbljg.do?method=saveJg&type=" + type;
	ajaxSubFormWithFun("hdbljgForm", url, function(data) {
		 if(data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		});
	
}

function add(){
	var url = "hdgl_hdbljg.do?method=addHdbljg";
	var title = "��������";
	showDialog(title, 800, 550, url);
}

/**
 * ���ʽonchang�¼�
 * @return
 */
function changeHdxs(){
	var hdxs = jQuery("#hdxs").val();
	if("�γ�" == hdxs){
		jQuery("#jzlxTr").show();
        jQuery("tr[name='zjrxx_tr']").hide();
        JzInfoEmpty();
	}else if("����" == hdxs){

        jQuery("tr[name='zjrxx_tr']").show();
        jQuery("#lx_span").html("��������");
        jQuery("#con_span").html("��������");

        jQuery("#jzlxTr").hide();
        jQuery("#jzlx").val("");
        jQuery("#zxkclx").val("");
	}else{
        jQuery("tr[name='zjrxx_tr']").hide();
        JzInfoEmpty();
        jQuery("#jzlxTr").hide();
        jQuery("#jzlx").val("");
        jQuery("#zxkclx").val("");

        var hdlx = jQuery("#hdlx").val();
        if ("�"==hdxs && "4"==hdlx){
            jQuery("#zysc").show();
        }else {
            jQuery("#zysc").hide();
        }

	}
}

function changeHdlx() {
    var hdxs = jQuery("#hdxs").val();
    var hdlx = jQuery("#hdlx").val();
    if("�"==hdxs && "4"==hdlx){
        jQuery("#zysc").show();
    }else {
        jQuery("#zysc").hide();
    }
}
function JzInfoEmpty(){
	var tr = jQuery("tr[name='zjrxx_tr']");
	tr.hide();
    tr.find("input").val("");
    tr.find("textarea").val("");
    tr.find("select").val("");
    jQuery("#lx_span").html("�����");
    jQuery("#con_span").html("����ݼ��ĵ�");
}
/**
 * �γ̼���change
 */
function kcjbChange(){
	var v = jQuery("#jzlx").val();
	if("003" == v){
        jQuery("#zxkclxTh").removeAttr("style");
        jQuery("#zxkclxTd").removeAttr("style");
	} else {
        jQuery("#zxkclxTh").attr("style","display:none");
        jQuery("#zxkclxTd").attr("style","display:none");
	}
}
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else if(rows[0]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
	}else{
		var url = 'hdgl_hdbljg.do?method=updateHdbljg&jgid=' + rows[0]["jgid"];
		var title = "�����޸�";
		showDialog(title, 800, 550, url);
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
		var url = "hdgl_hdbljg.do?method=delHdbljg";
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


var DCCLBH = "hdgl_hdbl_hdbljg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, hdjgExportData);
}

//��������
function hdjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "hdgl_hdbljg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

//����
function dr() {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_XAJT_HDJG");
	return false;

}
//���Ի�����
function hdbljgImport(){
    var drmkdm = 'IMPORT_XAJT_HDJG';
    var url = "hdgl_hdbljg.do?method=hdbljgImport" + "&drmkdm=" + drmkdm;
    showDialog('����',720,580,url,{close:function(){
        if (jQuery("#search_go")){
            jQuery("#search_go").click();
        }
    }});
    return false;
}