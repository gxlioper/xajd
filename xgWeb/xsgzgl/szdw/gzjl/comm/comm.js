var tsHtml="�����빤��ժҪ��������50�����ϡ�";
jQuery(function(){
	initParam();
		});
//��������
function getLbdm(obj){
	jQuery("#lbbh").val(obj.value);
}

function initParam(){
	if(""==jQuery("#gzzy").val()||null==jQuery("#gzzy").val()){
		jQuery("#gzzy").val(tsHtml);
		jQuery("#gzzy").css("color", "#7D7D7D");	
	}
	
	jQuery("#gzzy").focus(function() {
		if (tsHtml== jQuery("#gzzy").val()) {
			jQuery("#gzzy").val("");
			jQuery("#gzzy").css("color", "");
		}
	});
	jQuery("#gzzy").blur(function() {
		if ("" == jQuery("#gzzy").val()) {
			jQuery("#gzzy").val(tsHtml);
			jQuery("#gzzy").css("color", "#7D7D7D");
		}
	});
}

/**
 * ������֤
 * @return
 */
function checkZdz(){
	var zgh = jQuery("#zgh").val();
	var flag=true;
	var zgh = jQuery("#zgh").val();
	if (jQuery("#gzsj").val() == ""||jQuery("#gzsj").val() == null||jQuery("#gzzy").val() == ""||jQuery("#gzzy").val() == null||
			jQuery("#lbdm").val() == "" ||jQuery("#lbdm").val() == null || zgh == "") {
		showAlert("�뽫��������д������");
		flag= false;
		return flag;
	}
	if (jQuery("#gzzy").val().length<50) {
		showAlert("����ժҪ��������50�֣�");
		flag= false;
		return flag;
	}
	if (jQuery("#gzzy").val().length>1000) {
		showAlert("����ժҪ�������1000�֣�");
		flag= false;
		return flag;
	}
	if(jQuery("#xxdm").val() == '11842'){
		var lks = jQuery("#lks").val();
		if(lks == null || lks == "") {
			showAlertDivLayer("�뽫��������д������");
			flag = false;
			return flag;
		}
		var objArr = "";
		jQuery.each(jQuery("#tbody_dhryxx tr"),function(i,n){
			var xh= jQuery(n).find("td").eq(1).text();
			objArr += xh + ",";			
		});
		jQuery("#objStr").val(objArr);
	}
	return flag;
}

//����̸������
function addThxs() {
	var xhArr = new Array();
	jQuery.each(jQuery("#tbody_dhryxx tr"),function(i,n){
			var xh= jQuery(n).find("td").eq(1).text();
			xhArr.push(xh);
	});
	var url = 'gzjlsq.do?method=getStu&&xhArr='+xhArr;
	showDialog('��ѡ��ѧ��', 800, 550, url);
	return false;	
}

//ɾ��̸������
function delThxs() {
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ��ѧ����");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
		jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	jQuery('input[type=checkbox][name=th]').attr('checked', false);
}

function thxsZj() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("������ѡ��һ��ѧ����");
		return false;
	}
	setQqxs(rows);	
}

function setQqxs(rows) {

	var html = "";
	/*�����ԣ�������δ������� by yxy,ע����*/
	//var qqxsTrLen = jQuery("#tbody_qqryxx tr").length;
	var W;
	var api = frameElement.api;
	if (api) {
		if (api.get('childDialog')) {
			W = api.get('parentDialog')
		} else {
			W = api.opener;
		}
	} else if (parent.window) {
		W = parent.window;
	}
	for ( var int = 0; int < rows.length; int++) {
		html += "<tr><td><input type='checkbox' id='checkbox_" + int
				+ "'/></td>";
		html += "<td name='xh'>" + rows[int]["xh"] + "</td>";
		html += "<td name='xm'>" + rows[int]["xm"] + "</td>";
		html += "<td name='xb'>" + rows[int]["xb"] + "</td>";
		html += "<td name='xymc'>" + rows[int]["xymc"] + "</td>"; 
		html += "<td name='zymc'>" + rows[int]["zymc"] + "</td>"; 
		html += "<td name='bjmc'>" + rows[int]["bjmc"] + "</td>"; 
		/*�����ԣ�������δ������� by yxy,ע����*/
		//jQuery("#tbody_qqryxx").append(html);
	}
	
	W.thxszj(html);

	iFClose();
}

function selectAll(obj) {
	jQuery('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
}