
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
//��������ת
function dcmcLink(cellValue, rowObject) {
	var zyszid = rowObject["zyszid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + zyszid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//�鿴��Ϣ
function ckxx(zyszid) {
	var query=jQuery("#query").val();
	var url = "zyszpjwh.do?method=showView&query="+query+"&zyszid=" + zyszid;
	var title = "ְҵ����������Ϣ";
	showDialog(title, 800, 525, url);
}
//��ӡ
function dy() {
	var rows = jQuery("#dataTable").getSeletRow();
	var url = 'zyszpjwh.do?method=print';
	if (rows.length <= 0) {
		showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
	} else {
		var zyszid = "";
		for ( var i = 0; i < rows.length; i++) {
			zyszid += rows[i]["zyszid"] + ",";
		}
		url = url + "&zyszid=" + zyszid;
		window.open(url);
	}
}
//��ѯ
function query() {
	var map = {};
	map["tbgxxslbmc"] = jQuery("#tbgxxslbmc").val();
	jQuery("#dataTable").reloadGrid(map);
}
//����
function add() {
	jQuery.post("zyszpjwh.do?method=isCanAdd", {
		values : ""
	}, function(data) {
		if(data["success"]=="true"){
			var xh = jQuery("#xh").val();
			var url = "zyszpjwh.do?method=add&xh=" + xh;
			var title = "��д�����";
			showDialog(title, 760, 525, url);
			jQuery("#dataTable").reloadGrid();
		}else{
			showAlertDivLayer(data["message"]);
		}
	}, 'json');
}
//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var hpzt=rows[0]["hpzt"];
		if(hpzt=="�ѻ���"){
			showAlertDivLayer("�ѱ��������������޸ģ�");
			return false;
		}
		
		var spzt=rows[0]["spzt"];
		if(spzt=="��ʦ��"){
			showAlertDivLayer("�ѱ�ʦ�����������޸ģ�");
			return false;
		}
		var url = 'zyszpjwh.do?method=update&pjlx=br&zyszid=' + rows[0]["zyszid"];
		var title = "�޸�ְҵ����������Ϣ";
		showDialog(title, 760, 525, url);
		jQuery("#dataTable").reloadGrid();
	}
}
//ͬѧ����
function txhp() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var url = 'zyszpjwh.do?method=zyszpj&pjlx=tx&zyszid=' + rows[0]["zyszid"];
		var title = "��ӻ�����Ϣ";
		showDialog(title, 800, 525, url);
		jQuery("#dataTable").reloadGrid();
	}
}
//ʦ��
function sp() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫʦ���ļ�¼��");
	} else {
		var url = 'zyszpjwh.do?method=zyszpj&pjlx=ls&zyszid=' + rows[0]["zyszid"];
		var title = "���ʦ����Ϣ";
		showDialog(title, 800, 525, url);
		jQuery("#dataTable").reloadGrid();
	}
}
//ɾ��
function del() {
	var rows=jQuery("#dataTable").getSeletRow();
	var candel=true;
	jQuery.each(rows,function(e){
		var hpzt=rows[e]["hpzt"];
		var spzt=rows[e]["spzt"];
		if(hpzt=="�ѻ���"||spzt=="��ʦ��"){
			candel=false;
			return false;
		}
	});
	if(!candel){
		showAlertDivLayer("�����Ѿ�������ʦ�������ݲ���ɾ����");
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("zyszpjwh.do?method=del", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
//���һ������Ŀ��д����
function addTr() {
	jQuery('#tbody_add').append(jQuery('#addstr').html());
	return false;
}
//ɾ��һ������Ŀ��д����
function delTr() {
	var obj = jQuery("input[name=zxm]:checked");
	if (obj.length <= 0) {
		return alertError("��ѡ��Ҫɾ�������ݣ�");
	}
	jQuery("input[name=zxm]:checked").each(function() {
		jQuery(this).parent().parent().remove();
	});
}
//��ⳤ��
function checkLength(obj,len){
	var str=obj.value;
     	if(str.replace(/[^\u0000-\u00ff]/g, "**").length > len){	         
     		alertError("����ܴ���"+len+"���ַ���");
      		 return false;
   		 }
}
//��ⳤ��
function checkLength(){
	var xmlbid = jQuery("#tbody_add input[name='dd']");
	xmlbid.each(function(i) {
		alert(jQuery(this).val().length );
		if (jQuery(this).val().length > 30) {
			alertError("�ص㲻�ܴ���30���ַ���");
			xmlbid.focus();
     		 return false;
		}
	});
	var xmlbid = jQuery("#tbody_add textarea[name='hdnr']");
	xmlbid.each(function(i) {
		if (jQuery(this).val().length > 100) {
			alertError("����ݲ��ܴ���100���ַ���");
			xmlbid.focus();
     		 return false;
		}
	});
	var xmlbid = jQuery("#tbody_add textarea[name='cyjhjqk']");
	xmlbid.each(function(i) {
		if (jQuery(this).val().length > 200) {
			alertError("���뼰��������ܴ���200���ַ���");
			xmlbid.focus();
     		 return false;
		}
	});
	return true;
}
//����Ƿ�Ϊ��
function check() {
	var check = true;
	var xh=jQuery("#xh").val();
	if(xh==""){
		return false;
	}
	var xmlbid = jQuery("#tbody_add select[name='xmlbid']");
	xmlbid.each(function(i) {
		if (jQuery(this).val() == "") {
			check = false;
		}
	});
	var xmlbid = jQuery("#tbody_add input[name='sj']");
	xmlbid.each(function(i) {
		if (jQuery(this).val() == "") {
			check = false;
		}
	});
	var xmlbid = jQuery("#tbody_add input[name='dd']");
	xmlbid.each(function(i) {
		if (jQuery(this).val() == "") {
			check = false;
		}
	});
	var xmlbid = jQuery("#tbody_add textarea[name='hdnr']");
	xmlbid.each(function(i) {
		if (jQuery(this).val() == "") {
			check = false;
		}
	});
	var xmlbid = jQuery("#tbody_add textarea[name='cyjhjqk']");
	xmlbid.each(function(i) {
		if (jQuery(this).val() == "") {
			check = false;
		}
	});
	var xmlbid = jQuery("textarea[name='zpxx']");
	xmlbid.each(function(i) {
		if (jQuery(this).val() == "") {
			check = false;
		}
	});
	return check;
}
