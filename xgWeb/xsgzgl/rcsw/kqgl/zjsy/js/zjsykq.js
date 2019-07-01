function addKq(){
	var url = "zjsy_kqgl.do?method=addKqdj";
	var title = "���ӿ��ڼ�¼��Ϣ";
	showDialog(title,800,430,url);
}

function cshKq(){
	var url = "zjsy_kqgl.do?method=cshKqdj";
	var title = "��ʼ��������Ϣ";
	showDialog(title,400,200,url);
}

function modKq(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var url = "zjsy_kqgl.do?method=updateKqdj&id="+rows[0]["id"];
		var title = "�޸Ŀ��ڼ�¼��Ϣ";
		showDialog(title,800,430,url);
	}
}

function saveForm(method,type){

	 if (!checkNull("xn-xq-zc-yf-cqrs")) {
		 return false;
	 }
	 var url = "zjsy_kqgl.do?method="+method+"&type="+type;
    ajaxSubFormWithFun("ZjsyKqForm",url,function(data){ 	  
   	  if (data["success"] == "false"){
   		  showAlert("�ð༶�Ŀ��ڼ�¼�ڱ��±����Ѽ�¼!" );
   	  } else {
   		  showAlert(data["message"],{},{"clkFun":function(){
       			if (parent.window){
    				 refershParent();
       			}
     		  }});
   	  }
    });
}

function saveKqInfo(type) {
	 if (!checkNull("xn-xq-zc-yf-cqrs-bjcs-sjcs-kkjs")) {
		 return false;
	 }

	var objArr= [];
	jQuery.each(jQuery("#tbody_qqryxx tr"),function(i,n){
		var obj = {};
	    var xh= jQuery(n).find("td").eq(1).text();
		var bjcs = jQuery(n).find("input[name=bjcs]").val();
		var sjcs = jQuery(n).find("input[name=sjcs]").val();
		var kkjs = jQuery(n).find("input[name=kkjs]").val();
		obj.xh=xh;
		obj.bjcs=bjcs;
		obj.sjcs=sjcs;
		obj.kkjs=kkjs;
		objArr.push(obj);
	});
	var objStr = JSON.stringify(objArr);
	jQuery("#objStr").val(objStr);
	var url = "zjsy_kqgl.do?method=updateKqdj&type=" + type;
	ajaxSubFormWithFun("ZjsyKqForm", url, function(data) {
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

//ɾ��
function delKq(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("zjsy_kqgl.do?method=delKqdj",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function viewKq(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	}else {
		var url = "zjsy_kqgl.do?method=viewKqdj&id="+rows[0]["id"];
		var title = "�鿴���ڼ�¼��Ϣ";
		showDialog(title,800,430,url);
	}
}

function exportWithMonthAndXyData(){
	setSearchTj();//���ø߼���ѯ����
	if(!checkSearchWithXy()){
		return false;
	}
	if(checkSearch()){
		var url = "zjsy_kqgl.do?method=exportData&type=monthwithxy";//dcclbh,�������ܱ��
		url = addSuperSearchParams(url);//���ø߼���ѯ����
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
}

function exportWithMonthAndXxData(){
	setSearchTj();//���ø߼���ѯ����
	if(checkSearch()){
		var url = "zjsy_kqgl.do?method=exportData&type=monthwithxx";//dcclbh,�������ܱ��
		url = addSuperSearchParams(url);//���ø߼���ѯ����
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
}
function exportWithZcAndXyData(){
	setSearchTj();//���ø߼���ѯ����
	if(!checkSearchWithXy()){
		return false;
	}
	if(checkSearch('zc')){
		var url = "zjsy_kqgl.do?method=exportData&type=zcwithxy";//dcclbh,�������ܱ��
		url = addSuperSearchParams(url);//���ø߼���ѯ����
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
}
function exportWithZcAndXxData(){
	setSearchTj();//���ø߼���ѯ����
	if(checkSearch('zc')){
		var url = "zjsy_kqgl.do?method=exportData&type=zcwithxx";//dcclbh,�������ܱ��
		url = addSuperSearchParams(url);//���ø߼���ѯ����
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
}

//����ɷ񵼳�
function checkSearch(type){
	var flag = true;
	var xn_num =  jQuery("a[name=a_name_xn]").length;
	var xq_num =  jQuery("a[name=a_name_xq]").length;
	var yf_num = jQuery("a[name=a_name_yf]").length;
	var zc_num = jQuery("a[name=a_name_zjsyzc]").length;
	if(xn_num != "1" || xq_num !="1"){
		alertError("��ѡ��һ��ѧ���ѧ��!");
		flag = false;
		return flag;
	}else if(yf_num !=1){
		alertError("��ѡ��һ���·ݣ�");
		flag = false;
		return flag;
	}
	if(type=="zc"){
		if(zc_num !=1){
			alertError("��ѡ��һ���ܴΣ�");
			flag = false;
			return flag;
		}
	}
	return flag;
}

//����ɷ񵼳�
function checkSearchWithXy(){
	var flag = true;
	var xy_num =  jQuery("a[name=a_name_xy]").length;
	if(xy_num != 1){
			alertError("��ѡ����Ҫ������ѧԺ��");
			flag = false;
			return flag;
	}
	return flag;
}

//������
function addQqxs() {
	var bjdm = jQuery("#bjdm").val();
	if ("" == bjdm || null == bjdm) {
		showAlert("����ѡ��༶��");
		return false;
	}
	var xhArr = new Array();
	jQuery.each(jQuery("#tbody_qqryxx tr"),function(i,n){
			var xh= jQuery(n).find("td").eq(1).text();
			xhArr.push(xh);
	});
	var url = 'zjsy_kqgl.do?method=getStu&bjdm=' + bjdm+'&xhArr='+xhArr;
	showDialog('', 800, 550, url);
	return false;
}

function setQqxs(rows) {

	var html = "";
	var qqxsTrLen = jQuery("#tbody_qqryxx tr").length;
	for ( var int = 0; int < rows.length; int++) {
		html += "<tr><td><input type='checkbox' id='checkbox_" + int
				+ "'/></td>";
		html += "<td name='xh'>" + rows[int]["xh"] + "</td>";
		html += "<td name='xm'>" + rows[int]["xm"] + "</td>";
		html += "<td><input id='bjcs_" + int
				+ "' type='text' name='bjcs'";
		html += "maxlength='2' onkeyup=\"value=value.replace(/[^\\d]/g,\'\');\"  onblur=\"if(value != '') {value=parseInt(value,10)}\" value='0' /></td>";
		html += "<td><input id='sjcs_" + int
		+ "' type='text' name='sjcs'";
		html += "maxlength='2' onkeyup=\"value=value.replace(/[^\\d]/g,\'\');\" onblur=\"if(value != '') {value=parseInt(value,10)}\" value='0' /> </td>";
		html += "<td><input id='kkjs_" + int
				+ "' type='text' name='kkjs'";
		html += "maxlength='2' onkeyup=\"value=value.replace(/[^\\d]/g,\'\');\" onblur=\"if(value != '') {value=parseInt(value,10)}\"   value='0'/></td></tr>";
		jQuery("#tbody_qqryxx").append(html);
	}
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
	W.qqxszj(html);

	iFClose();
}

function qqxsZj() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("������ѡ��һ��ѧ����");
		return false;
	}
   setQqxs(rows);
}

function delQqxs() {
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
function selectAll(obj) {
	jQuery('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
}