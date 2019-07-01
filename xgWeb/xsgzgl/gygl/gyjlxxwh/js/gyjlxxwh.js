
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}



function add(){
	var url = "gyjl_gyjlwh.do?method=gyjlZj";
	showDialog("���ӹ�Ԣ��¼��Ϣά��", 800, 480, url);
}

function update() {
	var data = jQuery("#dataTable").getSeletRow();
	var sjly = data[0]["sjly"];
	
	if (data.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else if(sjly == '1'){
		showAlertDivLayer("�������ݲ����޸ģ�");
	}else {
		var url = 'gyjl_gyjlwh.do?method=gyjlXg&wjid='+ data[0]["wjid"]
		showDialog("�޸Ĺ�Ԣ��¼��Ϣά��", 800, 480, url);
	}

}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var data = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	}
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
		"okFun" : function() {
			jQuery.post("gyjl_gyjlwh.do?method=gyjlSc", {
				values : ids.toString()
			}, function(data) {
				var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
				showAlertDivLayer(mes);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
	});		
	
}
//������
function addWjxs() {
	var cxzd = jQuery("#cxzd").val();
	var qsxx = jQuery("#qsxx").val();
	var wjsj=jQuery("#wjsj").val();
	var sftq = jQuery("input[name=tqs]:checked").val();
	if (("" == cxzd || null == cxzd)&&(""==qsxx||null==qsxx)) {
		showAlert("������ѧ����������Ϣ��");
		return false;
	}
	if ("" == wjsj || null == wjsj) {
		showAlert("����дΥ��ʱ�䣡");
		return false;
	}
	
	var xhArr = new Array();
	var xswjsj = jQuery("#wjsj").val();
	var xsjldldm = jQuery("#jldldm").val();
	var xsjllbdm = jQuery("#jllbdm").val();
	jQuery.each(jQuery("#tbody_wjxs tr"),function(i,n){
			var xh= jQuery(n).find("td").eq(1).text();
			var wjsj=jQuery(n).find("td[name='wjsj']").find("input[name='wjsj']").val();
			var jldldm=jQuery(n).find("td[name='wjlb']").find("input[name='jldldm']").val();
			var jllbdm=jQuery(n).find("td[name='wjlb']").find("input[name='jllbdm']").val();
			xhArr.push(xh+wjsj+jldldm+jllbdm);
	});
	getWjxs(cxzd,qsxx,sftq,xhArr,xswjsj,xsjldldm,xsjllbdm)//ajax����Υ��ѧ��
	return false;
}

function delWjxs() {
	var checkbox = jQuery('input[type=checkbox]:checked[name=xsCheckbox]');
	if (checkbox.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ��ѧ����");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
		jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	jQuery('input[type=checkbox][name=th]').attr('checked', false);
}

function getWjxs(cxzd,qsxx,sftq,xhArr,xswjsj,xsjldldm,xsjllbdm){
	var jldldm = jQuery("#jldldm").val();
	var jllbdm = jQuery("#jllbdm").val();
	var wjsj =jQuery("#wjsj").val();
	jQuery.post("gyjl_gyjlwh.do?method=getWjxsList", {wjsj:xswjsj,jldldm:xsjldldm,jllbdm:jllbdm,cxzd:cxzd,qsxx:qsxx,sftq:sftq,xhArr:xhArr.toString()}, function(data) {
		setWjxs(data);
	}, 'json');
}
function setWjxs(data){
	if(data.length==0){
		showAlertDivLayer("δ�鵽��ѧ����ס����Ϣ���ѧ������ӣ�");
		return false;
	}
	var html="";
	var czr = jQuery("#username").val();
	for ( var int = 0; int < data.length; int++) {
		html += "<tr><td><input type='checkbox' name='xsCheckbox' id='checkbox_" + int
				+ "'/></td>";
		html += "<td>" + data[int].xh + "</td>";
		html += "<td>" + data[int].xm + "</td>";
		html += "<td>"+ data[int].xb + "</td>";
		html += "<td>"+ data[int].bjmc + "</td>";
		html += "<td>"+ data[int].zsqs + "</td>";
		html += "<td name='wjsj'><input name='wjsj' type='hidden' value='"+jQuery("#wjsj").val()+"'/>"+ jQuery("#wjsj").val() + "</td>";
		html += "<td name='wjlb'><input name='jldldm' type='hidden' value='"+jQuery("#jldldm").val()+"'/><input name='jllbdm' type='hidden' value='"+jQuery("#jllbdm").val()+"'/>"+ jQuery("#jldldm").find("option:selected").text()
		+"("+jQuery("#jllbdm").find("option:selected").text()+")"+ "</td>";
		html += "<td>"+ czr + "</td></tr>";
	}
	jQuery("#tbody_wjxs").append(html);
}
function gyjlxxwhCk(wjid,xh) {
	showDialog("�����ѯ", 680,500, "gyjl_gyjlwh.do?method=gyjlxxwhCk&wjid=" + wjid+"&xh="+xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='gyjlxxwhCk(\""
			+ rowObject["wjid"] + "\",\"" + rowObject["xh"] + "\");'>" + cellValue
			+ "</a>";
}
//ȫѡ
function selectAll(obj){ 
	jQuery('input[type=checkbox][name=xsCheckbox]').attr('checked',jQuery(obj).attr('checked'));
}

//����
function saveWjxx(type) {
	var url = "gyjl_gyjlwh.do?method=saveWjxx&type="+type;
	var objArr= [];
   if(checkNulls()){
	   jQuery.each(jQuery("#tbody_wjxs tr"),function(i,n){
		   		var obj = {};
				var xh= jQuery(n).find("td").eq(1).text();
				var wjsj=jQuery(n).find("td[name='wjsj']").find("input[name='wjsj']").val();
				var jldldm=jQuery(n).find("td[name='wjlb']").find("input[name='jldldm']").val();
				var jllbdm=jQuery(n).find("td[name='wjlb']").find("input[name='jllbdm']").val();
				obj.xh=xh;
				obj.wjsj=wjsj;
				obj.jldldm=jldldm;
				obj.jllbdm=jllbdm;
				objArr.push(obj);
			
			});
		
	   var objStr = JSON.stringify(objArr);
		jQuery("#objStr").val(objStr);
	ajaxSubFormWithFun("gyjlwhForm", url, function(data) {
		
		if(data["message"]=="����ɹ���"){
			var api = frameElement.api,W = api.opener;
			jQuery(W.document).find('#search_go').click();
    		 showAlert(data["message"]);
   	 }else{
   		 showAlert("�����ظ���ӣ�");
   		}
    		
		});
   }
}
function checkNulls() {
	if(jQuery("#tbody_wjxs tr").length==0){
		showAlert("�������һ��ѧ����");
		return false;
	}
	if (jQuery("#wjxn").val() == "" || jQuery("#wjxn").val() == null
			|| jQuery("#wjxq").val() == "" || jQuery("#wjxq").val() == null
			|| jQuery("#jldldm").val() == "" || jQuery("#jldldm").val() == null
			|| jQuery("#jllbdm").val() == "" || jQuery("#jllbdm").val() == ""||
			jQuery("#wjsj").val() == "" || jQuery("#wjsj").val() == "") {
		showAlert("�뽫<font color='red'>*</font>��������д������");
		return false;
	}
	return true;
}

var DCCLBH = "gygl_gyjlglnew_gyjlxxwh_new.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, wjxxExportData);
}
function dr() {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_NN380808_XSWJXX");
	return false;

}

// ��������
function wjxxExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "gyjl_gyjlwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
