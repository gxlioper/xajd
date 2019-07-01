//��ѯ
function searchRs() {
	var map = getSuperSearch();
	var flag = jQuery("#flag").val();
	if (null!=flag&&flag != "") {
		map["flag"] = flag;
	}else{
		map["flag"] = "wsq";
	}
	jQuery("#dataTable").reloadGrid(map);
}

//��ǩҳѡ�
function selectTab(obj, flag) {
	jQuery("#flag").val(flag);
	if (flag == "wsq") {
		var map = getSuperSearch();
		map["flag"]="wsq";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		var map = getSuperSearch();
		map["flag"]="ysq";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='RtsqView(\""
			+ rowObject["rtid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


//�鿴ѧ��ajaxurl��ת
function RtsqView(id, xh) {
	showDialog("���ų�Ա��ϸ��ѯ", 770, 450, "stglRtsq.do?method=Rtsqck&rtid="
			+ id + "&xh=" + xh);
}

//ɾ��������Ϣ
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer(jQuery("#lable_wjt_sc").val());
		return false;
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("stglRtsq.do?method=delSqxx",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "stgl_rtgl_rtsq.do";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, RtsqExportData);
}

//��������
function RtsqExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "stglRtsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importConfig(){
	toImportDataNew("IMPORT_RTSQ");
	return false;
}


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
		if ('3' != rows[0]['shzt'] && "0" == rows[0]['sqkg']) {
			showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("stglRtsq.do?method=submitBusi", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

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
				jQuery.post("stglRtsq.do?method=cancelRtsq", {
					values : ids.toString(),
					splcid : rows[0]['splc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var sqkg = rows[0]['sqkg'];
		if ('3' != rows[0]['shzt'] && "0" == sqkg) {
			showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
			return false;
		}
		if ("0" != rows[0]['shzt']&&"3" !=rows[0]['shzt']) {
			showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
			return false;
		}
		var url = 'stglRtsq.do?method=editRtsq&rtid=' + rows[0]["rtid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "ѧ����������";
		showDialog(title, 770, 552, url);
	}
}

//����
function add() {
	var url = "stglRtsq.do?method=add";
	var title = "ѧ����������";
    showDialog(title, 770, 552, url);
}

//��֤����
function checkzs(obj){
	if(jQuery(obj).val().length > 100){
		showAlert("�������Ϊ100�����Ѿ���������ȷ�ϣ�");
		return false;
	}
}

//����������stclsj��lxdh��ssbm��zdlslxfs��zdlszc��stsm��stclsj��sthjqk
function setSqxs(rows) {
	var jcxf=jQuery("#jcxf").val();
	var api = frameElement.api;
	//��ȡ��ҳ��body�������ڸ�ֵ
	var bodyobj = api.get('parentDialog').jQuery("#stxm_body");	
	var toggle = api.get('parentDialog').jQuery("#tbody_toggle");

    jQuery(bodyobj).find("input[name='stxmmc']").val(rows[0]["stxmmc"]);
    jQuery(bodyobj).find("input[name='stid']").val(rows[0]["stid"]);
    jQuery(bodyobj).find("input[name='splc']").val(rows[0]["splc"]);
    jQuery(bodyobj).find("input[name='xmlbdm']").val(rows[0]["xmlbdm"]);
    jQuery(bodyobj).find("td[name='stlb']").text(rows[0]["stlbmc"]);
    jQuery(bodyobj).find("td[name='gkdw']").text(rows[0]["gkdw"]);
    jQuery(bodyobj).find("td[name='xmlb']").text(rows[0]["xmlbmc"]);
    jQuery(bodyobj).find("td[name='xn']").text(rows[0]["xn"]);
    /*
    jQuery(bodyobj).find("td[name='kssj']").text(rows[0]["kssj"]);
    jQuery(bodyobj).find("td[name='jssj']").text(rows[0]["jssj"]);
    */
    jQuery(toggle).find("td[name='stfzrxm']").text(rows[0]["stfzrxm"]);
    jQuery(toggle).find("td[name='fzrlb']").text(rows[0]["fzrlb"]);
    jQuery(toggle).find("td[name='jtr']").text(rows[0]["jtr"]);
    jQuery(toggle).find("td[name='zdlsxm']").text(rows[0]["zdlsxm"]);
    jQuery(toggle).find("td[name='zdlszc']").text(rows[0]["zdlszc"]);
    jQuery(toggle).find("td[name='zdlslxfs']").text(rows[0]["zdlslxfs"]);
    jQuery(toggle).find("td[name='ssbm']").text(rows[0]["ssbm"]);
    jQuery(toggle).find("td[name='lxdh']").text(rows[0]["lxdh"]);
    jQuery(toggle).find("td[name='stclsj']").text(rows[0]["stclsj"]);
    jQuery(toggle).find("td[name='stsm']").text(rows[0]["stsm"]);
    jQuery(toggle).find("td[name='sthjqk']").text(rows[0]["sthjqk"]);
    jQuery(toggle).find("td[name='sqsj']").text(rows[0]["sqsj"]);
	iFClose();
}

//���ӽ������
function saveRtSq(type){
	var ids = "stxmmc"+"-"+"sqly"+"-"+"tc";
	if(checkNotNull(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	if(checkzs(jQuery("#sqly")) == false || checkzs(jQuery("#tc")) == false ) {
		return false;
	}
	var url = "stglRtsq.do?method=saveRtsq&type=" + type;
	ajaxSubFormWithFun("RtsqForm", url, function(data) {
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

/*
 * ���̸���
 */
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
		showDialog("ѧ�����������������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['rtid'] + "&splc=" + rows[0]['splc']);
	}
}

//toggle����չ��
function showPfzmx(obj){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";
	jQuery(obj).attr("class",newClass);
	jQuery("#tbody_toggle").toggle();

	//ָ����ʦ��Ϣ
	if(className=='down')
	{
        document.getElementById("zdlsthead").style.display = "";
        document.getElementById("zdlstbody").style.display = "";
	}
	else{
        document.getElementById("zdlsthead").style.display = "none";
        document.getElementById("zdlstbody").style.display = "none";
	}
    //�첽ƴ���
	var stid = jQuery("#stid").val();
    jQuery('.aa').remove();
    jQuery.post("stglRtsq.do?method=getZdlsInfo",{"stid":stid},function(data){
        var liHtml="";
        for (var i = 0 ; i < data.length ; i++){
            liHtml+="<tr class='aa'>";
            liHtml += "<td><label name = 'xm'>"+data[i]["xm"]+"</label></td>";
            liHtml += "<td><label name = 'bmmc'>"+data[i]["bmmc"]+"</label></td>";
            liHtml += "<td><label name = 'lxdh'>"+data[i]["lxdh"]+"</label></td>";
            liHtml += "<td><label name = 'zcmc'>"+data[i]["zcmc"]+"</label></td>";
            liHtml+="</tr>";
        }

        jQuery("#nr").append(liHtml);
    },"json");
}


