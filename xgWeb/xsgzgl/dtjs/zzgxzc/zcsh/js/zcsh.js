function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (null!=shzt&&shzt != "") {
		map["shzt"] = shzt;
	}else{
		map["shzt"] = "dsh";
	}
	jQuery("#dataTable").reloadGrid(map);
}

function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);
	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["shzt"]="ysh";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

/**
 * ���̸���
 * @return
 */
function splcInfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (1!=ids.length){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splcid']);
	}
}

//�������
function khsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��")
		return false;
	}
	if (shzt == "ysh") {
		showAlertDivLayer("�Ѵ����¼�����ٴ���ˣ�");
		return false;
	} else if (rows.length == 1) {
		var url = "dzzgxsh.do?method=Dgsh&sqid=" + rows[0]["sqid"] + '&xh='
				+ rows[0]["xh"] + '&shid=' + rows[0]["shid"]+"&gwid="+rows[0]["gwid"]+"&splcid="+rows[0]["splcid"];
		showDialog("���", 770, 550, url);
	}
}

//��˳���
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		var splc = rows[0]["splcid"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["sqid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("dzzgxsh.do?method=cxshnew",{shlc:splc,shid:shid,sqid:sqid},function(data){
				// �ж��Ƿ����һ������(1:���һ�������ɹ���
				if("1" == data["cancelFlg"]){
					jQuery.post("dzzgxsh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
						showAlertDivLayer(result["message"],{},{"clkFun":function(){
							jQuery("#dataTable").reloadGrid();
						}});
					},'json');
				}else{
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}
			
		},'json');
		}});
	}
}

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='sqView(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

/**
 * �鿴
 * @param sqid
 * @param xh
 * @return
 */
function sqView(sqid, xh) {
	showDialog("��Ϣ��˲鿴", 770, 450, "dzzgxsq.do?method=cksq&sqid="
			+ sqid + "&xh=" + xh);
}

var DCCLBH = "dtjs_dzzgxsh.do";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, shExportData);
}

//��������
function shExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "dzzgxsh.do?method=exportData&dcclbh=" + DCCLBH+"&shzt="+ jQuery("#shzt").val();//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

