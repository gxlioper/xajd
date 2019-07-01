function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#sqzt").val();
	if (null!=shzt&&shzt != "") {
		map["sqzt"] = shzt;
	}else{
		map["sqzt"] = "wsq";
	}
	jQuery("#dataTable").reloadGrid(map);
}
function jxLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='jxView(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}
function jxView(id) {
	showDialog("��������鿴", 820, 500, "jxgl_xnjxsq.do?method=viewJx&id="+id);
}

//����
function add() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ����ļ�¼��");
	}
	if(rows[0]["shzt"] == '0' || rows[0]["shzt"] == '4'){
		showAlertDivLayer("��ѡ��һ������״̬δ����ļ�¼��");
		return false;
	}
	var url = "jxgl_xsxnjxsq.do?method=xsxnjxsqAdd&jgid="+rows[0]["jgid"];
	var title = "����";
    showDialog(title, 770, 520, url);	
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if ("0" != rows[0]['shzt']&&"3" !=rows[0]['shzt']) {
			showAlertDivLayer("ֻ������״̬Ϊδ�ύ�����˻صļ�¼�����޸ģ�");
			return false;
		}
		var url = 'jxgl_xsxnjxsq.do?method=editSqjg&id=' + rows[0]["id"]
				+ '&xh=' + rows[0]["xh"];
		var title = "���������޸�";
		showDialog(title, 770, 500, url);
	}
}

//���ӱ�����
function saveSqjg(type){
	var ids = "jxid";
	if(check(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	var url = "jxgl_xsxnjxsq.do?method=saveSqjg&type=" + type;
	ajaxSubFormWithFun("XsxnjxsqForm", url, function(data) {
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

// �л�Tabҳ
function selectTab(obj, shzt) {
	jQuery("#sqzt").val(shzt);
	if (shzt == "wsq") {
		jQuery("#li_sq").css("display", "");
		jQuery("#li_xg").css("display", "");
		jQuery("#li_sc").css("display", "");
		jQuery("#li_tj").css("display", "");
		jQuery("#li_qx").css("display", "none");
		jQuery("#li_gz").css("display", "none");
		var map = getSuperSearch();
		map["sqzt"]="wsq";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sq").css("display", "none");
		jQuery("#li_xg").css("display", "none");
		jQuery("#li_sc").css("display", "none");
		jQuery("#li_tj").css("display", "none");
		jQuery("#li_qx").css("display", "");
		jQuery("#li_gz").css("display", "");
		var map = getSuperSearch();
		map["sqzt"]="ysq";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

//������¼
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length > 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}

		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("jxgl_xnjxsq.do?method=cancelXmsq", {
					values : rows[0]['id'],
					splcid : rows[0]['shlc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}


function splcInfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (1!=ids.length){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['shlc']);
	}
}

var DCCLBH = "xmsbXmsh.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, khshExportData);
}

//��������
function khshExportData() {
	setSearchTj();//���ø߼���ѯ����
	var shzt = jQuery("#shzt").val();
	var url = "xmsbXmsh.do?method=exportData&shzt="+shzt+"&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function showxmxx(obj){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#t_xmxx").toggle();
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

/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function submitBusi() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
		return false;
	}
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
		return false;
	} else {
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("jxgl_xnjxsq.do?method=submitBusi", {
					values : rows[0]["id"]
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (1 != rows.length) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
		return false;
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" == shzt) {
			showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
			return false;
		}
		showDialog("У�ڽ��������������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['id'] + "&splc=" + rows[0]['shlc']);
	}
}

//ɾ�����
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
		return false;
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		var sqids = new Array();
		for(var i=0;i<rows.length;i++){
			sqids.push(rows[i]['id']);
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("jxgl_xnjxsq.do?method=delSqjl",{values:sqids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function xmmcLink(cellValue, rowObject) {
	if('1'==rowObject["sfrm"]){
		cellValue+="<font color='red'>�����š�</font>";
	}
	return "<a href='javascript:void(0);' class='name' onclick='XmsbjgView(\""
			+ rowObject["jgid"] + "\");'>" + cellValue + "</a>";
}


