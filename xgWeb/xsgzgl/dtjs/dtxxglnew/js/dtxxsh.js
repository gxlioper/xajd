var action="dtxxsh.do";


//�л�������/�Ѵ���ҳ��
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);

	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		jQuery("#title").html("���Ŵ�����б�");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		jQuery("#title").html("����������б�");
		var map = getSuperSearch();
		map["shzt"]="ysh";
		gridSetting["params"] = map;
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
function reload() {
	jQuery("#dataTable").reloadGrid();
}
// ��������ת
function dcmcLink(cellValue, rowObject) {
	var dtxxsqid = rowObject["dtxxsqid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + dtxxsqid
			+ "')\" class='name'>" + cellValue + "</a>";
}
// �鿴��Ϣ
function ckxx(dtxxsqid) {
	var query = jQuery("#query").val();
	var url = action + "?method=showView&dtxxsqid=" + dtxxsqid;
	var title = "����������Ϣ";
	showDialog(title, 700, 440, url);
}
/**
 * ������˲���
 * @param shzt
 * @param message
 * @return
 */
function save_sh_bak(shzt,message){
	jQuery("#shzt").val(shzt);
	
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("����д��������");
		return false;
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("���������ܳ���200��");
		return false;
	}
	var text="ͨ��";
	if(shzt=="2"){
		text="��ͨ��";
	}else if(shzt=="3"){
		text="�˻�";
		zx(shzt);
		return false;
	}
	//�ύ���
	showConfirmDivLayer("��ȷ��<font color='red'>[" + text + "]</font>��������",{"okFun":function(){
			zx(shzt,text);
		}});
	
}
function zx(shzt,text){
	var url = "dtxxsh.do?method=dtxxsh&type=save&tt="+new Date();
	/*if(shzt=="3"||shzt==3){
		var shid = jQuery("#shid").val();
		var splc = jQuery("#splc").val();
		var shyj = jQuery("#shyj").val();
		splc_th(shid,splc,shyj);
		return false;
	}*/
	lock();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data["message"] == "����ɹ���") {
				showAlert("<font color='red'>["+text+"]</font>�����ɹ���", {}, {
					"clkFun" : function() {
						if (parent.window) {
							refershParent();
						}
					}
				});
			} else {
				showAlert("<font color='red'>[+"+text+"]</font>����ʧ�ܣ�");
			}
			unlock();
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
		});
	
}
/**
 * ��֤�Ƿ���ڿ���
 * 
 * @param ids
 *            Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids) {
	var id = ids.split("-");
	for ( var i = 0; i < id.length; i++) {
		var lddm = jQuery("#" + id[i]).val();
		if (lddm == null || "" == lddm) {
			// alert(id[i]);
			return false;
		}
	}
	return true;
}
//���
function dtxxsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	
	
	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ��˼�¼��");
	} else if (rows.length == 1){
		var xh = rows[0]["xh"];
		var url = action + '?method=dtxxsh&xh=' + xh + '&dtxxsqid='+ rows[0]["dtxxsqid"]+"&gwid="+rows[0]["gwid"];
		showDialog("������Ϣ���",700,480,url);
	} else {
		
		showDialog("������Ϣ����������",500,220,"dtxxsh.do?method=dtxxPlsh");
	}
	
	
	/*if (rows.length != 1) {
		showAlert("��ѡ��һ����Ҫ��˵ļ�¼��");
	} else {
		var xh = rows[0]["xh"];
		var url = action + '?method=dtxxsh&xh=' + xh + '&dtxxsqid='
		+ rows[0]["dtxxsqid"]+"&gwid="+rows[0]["gwid"];
		showDialog("������Ϣ���",700,480,url);
	}*/
	
}
//�������
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("dtxxsh.do?method=cancelSh",
				{
				 qjsqid:rows[0]["qjsqid"],
				 gwid:rows[0]["gwid"],
				 shr:rows[0]["shr"],
				 splcid:rows[0]["splcid"]
				},
				function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},
			'json');
		}});
	}
}
function rcxwshLcinfo() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {
		showDialog("�����������̸���", 600, 400, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['dtxxsqid'] + "&splc=" + rows[0]['splc']);
	}
}
//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport('dtxxshbase.do',exportData);
}

// ��������
function exportData(dcclbh) {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//���ø߼���ѯ����
	var url = "dtxxsh.do?method=exportData&dcclbh=" + dcclbh + "&shlx=" + shlx;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ������˱���
 * @param shzt
 * @return
 */
function savePlsh(shzt,shyj){
	var rows = jQuery("#dataTable").getSeletRow();
	var dtxxsqid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var splcs = new Array();
	
	//�޸�bug
	var jddms = new Array();
	
	jQuery.each(rows,function(i,row){
		dtxxsqid.push(row["dtxxsqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
		splcs.push(row["splc"]);
		//�޸�bug
		jddms.push(row["jddm"]);
	});
	jQuery.post(
		"dtxxsh.do?method=savePlsh",
		{
		 shzt:shzt,
		 ids:dtxxsqid,
		 gwids:gwid,
		 xhs:xhs,
		 splcs:splcs,
		 //�޸�bug
		 jddms:jddms,
		 shyj:shyj
		},function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}
