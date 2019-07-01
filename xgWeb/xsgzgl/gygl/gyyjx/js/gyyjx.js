var gridSetting_dmwh = {
	caption : "��������б�",
	pager : "pager",
	url : "gygl_gyyjxdmwh.do?method=listDmwh&type=query",
	colList : [
			/*{ name : 'yjfldm', index : 'yjfldm', key : true , hidden : true},*/
			{ label : '����������', name : 'yjfldm', index : 'yjfldm',  width : '30%', key : true },
			{ label : '�����������', name : 'yjflmc', index : 'yjflmc', width : '60%' },
			{name : 'txsmc', index : 'txsmc', hidden: true }],
	sortname : "yjfldm", sortorder : "asc" };


function add_dmwh() {
	var url = "gygl_gyyjxdmwh.do?method=actionNavi&type=add";
	var title = "�����������";
	showDialog(title, 400, 200, url);
}

function add_stu(){
	
	var userType = jQuery("#userType").val();
	if("stu" == userType){
		jQuery.post('gyglnew_gybxgl.do?method=viewXsxx',{},function(data){
			var lddm = data.lddm;
			
			if(!lddm){
				showAlertDivLayer("����δ��ס��Ԣ���ң��޷�������Ԣ�����");
			}else{
				var url = "gygl_gyyjxstu.do?method=actionNavi&type=add";
				var title = "�����";
				showDialog(title, 800, 520, url);
			}					
		},'json');
	}else{
		var url = "gygl_gyyjxstu.do?method=actionNavi&type=add";
		var title = "�����";
		showDialog(title, 800, 520, url);
	}

	
}

function update_dmwh() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'gygl_gyyjxdmwh.do?method=actionNavi&type=update&yjfldm=' + rows[0]["yjfldm"];
		var title = "�޸��������";
		showDialog(title, 400, 200, url);
	}
}

function update_stu() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		
		if(rows[0]["fkqk"] == '1'){
			showAlertDivLayer("������ѱ������������޸ģ�");
			return false;
		}
		
		var url = 'gygl_gyyjxstu.do?method=actionNavi&type=update&gyyjid=' + rows[0]["gyyjid"];
		var title = "�޸����";
		showDialog(title, 800, 520, url);
	}
}

function fk_gl(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����������");
	} else {
		var url = 'gygl_gyyjxstu.do?method=actionNavi&type=yjfk&gyyjid=' + rows[0]["gyyjid"];
		var title = "�������";
		showDialog(title, 800, 590, url);
	}
}

/**
 * �ύ
 * @param type
 * @return
 */
function submitAction_dmwh(type){
	
	var yjflmc = jQuery('#yjflmc').val();
	
	
	if (yjflmc==null || jQuery.trim(yjflmc) == '') {
		showAlertDivLayer("�뽫��<font color=\"red\">*</font>����Ŀ��д������");
		return false;
	}
	
	var url = "gygl_gyyjxdmwh.do?method=" + type;
	ajaxSubFormWithFun("gyyjxForm", url, function(data) {
		if(data['repeat'] && data['repeat'] == 'Y'){
			showAlertDivLayer(data['message']);
		}else{
			refershParent();
		}
		
	});
}

/**
 * �ύ
 * @param type
 * @return
 */
function submitAction_stu(type){
	var xh = jQuery("input[name='xh']").val();
	var yjfldm = jQuery('#yjfldm').val();
	var yjms = jQuery('#yjms').val();
	
	if ((yjfldm==null || jQuery.trim(yjfldm) == '')  || (yjms==null || jQuery.trim(yjms) == '')) {
		showAlertDivLayer("�뽫��<font color=\"red\">*</font>����Ŀ��д������");
		return false;
	}
	
	if( type == 'add' && (xh==null || jQuery.trim(xh) == '')){
		showAlertDivLayer("��ѡ��һ��ѧ����");
		return false;
	}
	
	if(yjms.length > 500){
		showAlertDivLayer("����������ܳ���500�֣�");
		return false;
	}
	var url = "gygl_gyyjxstu.do?method=" + type;
	ajaxSubFormWithFun("gyyjxForm", url, function(data) {
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}
/**
 * �ύ
 * @return
 */
function submitAction_gl(type){
	var yjms = jQuery.trim(jQuery('#fknr').val());
	if(yjms.length ==0){
		showAlertDivLayer("����д���������");
		return false;
	}
	if(yjms.length > 500){
		showAlertDivLayer("����������ܳ���500�֣�");
		return false;
	}
	
	var url = "gygl_gyyjxstu.do?method=" + type;
	ajaxSubFormWithFun("gyyjxForm", url, function(data) {
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}

function del_dmwh() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else if(ids.length==1 && rows[0]['txsmc']=='y'){
		showAlertDivLayer("�����������ʹ�ã�����ɾ����");
	}else {		
		
		for(i=0;i<rows.length;i++){
			if(rows[i]['txsmc']=='y'){
				showAlertDivLayer("��ѡ������ʹ���������,����ɾ������ȷ�ϣ�");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", { "okFun" : function() {
			jQuery.post("gygl_gyyjxdmwh.do?method=del", { pks : ids
					.toString() }, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		} });

	}
}

function del_stu() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}
	else if(ids.length==1 && rows[0]['fkqk']=='1'){
		showAlertDivLayer("������ѱ�����������ɾ����");
	}else {
		
		for(i=0;i<rows.length;i++){
			if(rows[i]['fkqk']=='1'){
				showAlertDivLayer("��ѡ��¼�����ѷ�����Ԣ�����������Ԣ�������ɾ������ȷ�ϣ�");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", { "okFun" : function() {
			jQuery.post("gygl_gyyjxstu.do?method=del", { pks : ids
					.toString() }, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		} });

	}
}

/**
 * �߼���ѯ
 * 
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ѧ������
 * @param cellValue
 * @param rowObject
 * @return
 */

function xhLink_stu(cellValue,rowObject){
	var onclickfn = "onclick=\"" + "showDialog('��Ԣ�����Ϣ' , 800 , 550 , 'gygl_gyyjxstu.do?method=query&gyyjid=" + rowObject['gyyjid'] + "&xh=" + rowObject['xh'] + "')" + "\"";
	
	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
	
	return el;
}

var DCCLBH = "gygl_jxjggl.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "gygl_gyyjxstu.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

