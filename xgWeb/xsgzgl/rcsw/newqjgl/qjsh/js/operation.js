var action="qjsh.do";

//�л�������/�Ѵ���ҳ��
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);
	var xxdm = $("xxdm").value;
	if("12303" == xxdm){
		if (shzt == "dsh") {
			jQuery("#li_sh").css("display", "");
			jQuery("#li_qx").css("display", "none");
			jQuery("#title").html("��ٴ�����б�");
			var map = getSuperSearch();
			map["shzt"]="dsh";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting4);
		} else {
			jQuery("#li_sh").css("display", "none");
			jQuery("#li_qx").css("display", "");
			jQuery("#title").html("���������б�");
			var map = getSuperSearch();
			map["shzt"]="ysh";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting5);
		}
	}else{
		if (shzt == "dsh") {
			jQuery("#li_sh").css("display", "");
			jQuery("#li_qx").css("display", "none");
			jQuery("#title").html("��ٴ�����б�");
			var map = getSuperSearch();
			map["shzt"]="dsh";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		} else {
			jQuery("#li_sh").css("display", "none");
			jQuery("#li_qx").css("display", "");
			jQuery("#title").html("���������б�");
			var map = getSuperSearch();
			map["shzt"]="ysh";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting2);
		}
	}
	reload();
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
	var qjsqid = rowObject["qjsqid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + qjsqid
			+ "')\" class='name'>" + cellValue + "</a>";
}
// �鿴��Ϣ
function ckxx(qjsqid) {
	var query = jQuery("#query").val();
	var url = action + "?method=showView&qjsqid=" + qjsqid;
	var title = "���������Ϣ";
	showDialog(title, 800, 500, url);
}
// ����
function add() {
	var url = action + "?method=add";
	var title = "�������";
	showDialog(title, 800, 500, url);
	jQuery("#dataTable").reloadGrid();
}
/**
 * ������˲���
 * @param shzt
 * @param message
 * @return
 */
function save_sh(){
	var shzt=jQuery("#shjg").val();
	jQuery("#shzt").val(shzt);
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("����д��������");
		return false;
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("���������ܳ���200��");
		return false;
	}
	var text=jQuery("#shjg").find("option:selected").text();
	//�ύ���
	showConfirmDivLayer("��ȷ��" + text + "��������",{"okFun":function(){
			zx(shzt,text);
		}});
	
}
function zx(shzt,text){
	var url = "qjsh.do?method=qjsh&type=save&tt="+new Date();
/*	if(shzt=="3"||shzt==3){
		var shid = jQuery("#shid").val();
		var splc = jQuery("#splc").val();
		var shyj = jQuery("#shyj").val();
		splc_th(shid,splc,shyj);
		return false;
	}*/
	
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data["message"] == "����ɹ���") {
				showAlert("�����ɹ���", {}, {
					"clkFun" : function() {
						if (parent.window) {
							refershParent();
						}
					}
				});
			} else {
				showAlert("����ʧ�ܣ�");
			}
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
		});
}
function save(url, checkId) {
	if (!check(checkId)) {
		return alertError("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	
	var shzt=jQuery("#shzt").val();
	if(shzt=="3"){//�˻ز���
		var shid = jQuery("#shid").val();
		var splc = jQuery("#splcid").val();
		var shyj = jQuery("#shyj").val();
		splc_th(shid,splc,shyj);
		return false;
	}
	
	var text=jQuery("#shzt option:selected").text();
	showConfirmDivLayer("��ȷ��" + text + "��������",{"okFun":function(){
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
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
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
		});
	}});
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
// �޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlert("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var xh = rows[0]["xh"];
		jQuery.post("qjsq.do?method=isCanAdd", {}, function(data) {
			if (data["success"] == "true") {
				var url = action + '?method=update&xh=' + xh + '&qjsqid='
						+ rows[0]["qjsqid"];
				var title = "�޸��������";
				showDialog(title, 700, 500, url);
				jQuery("#dataTable").reloadGrid();
			} else {
				showAlert("�Ѿ����ڴ��������!");
			}
		}, 'json');
	}
}
// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlert("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action + "?method=del", {
					values : ids.toString()
				}, function(data) {
					showAlert(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
//���
function qjsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlert("��ѡ��һ����Ҫ��˵ļ�¼��");
	} else if(rows.length == 1){
		var xh = rows[0]["xh"];
		var url = action + '?method=qjsh&xh=' + xh + '&qjsqid='
		+ rows[0]["qjsqid"]+"&gwid="+rows[0]["gwid"];
		showDialog("������",800,500,url);
	}else {
		showDialog("����������",500,300,action + '?method=toPlsh');
	}
	
	
	
	/**
	 * ��ʾ���ҳ��
	 */
	function viewJxsh(){
		var rows = jQuery("#dataTable").getSeletRow();

		if (rows.length ==0){
			showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
		}else if (rows.length == 1){
			var sqid = rows[0]["sqid"];
			var shid = rows[0]["shid"];
			var gwid = rows[0]["gwid"];
			
			showDialog("�������",700,500,"xpj_sqsh.do?method=viewJxsh&sqid="+sqid+"&shid="+shid+"&gwid="+gwid);
		}else {
			showDialog("�������",500,300,"xpj_sqsh.do?method=toPlsh");
		}
	}
}
//�������
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		var xjzt = rows[0]["xjzt"];
		if(xjzt=="1"){
			showAlertDivLayer("�������Ѿ����٣����ܳ�����");
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("qjsh.do?method=cancelSh",
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
		showDialog("����������̸���", 600, 400, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['qjsqid'] + "&splc=" + rows[0]['splcid']);
	}
}
//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport('qjshbase.do',exportData);
}

// ��������
function exportData(dcclbh) {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//���ø߼���ѯ����
	var url = "qjsh.do?method=exportData&dcclbh=" + dcclbh + "&shlx=" + shlx;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
