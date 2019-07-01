
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function yfLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='yxybjgView(\""
			+ rowObject["jgid"]+"\");'>" + cellValue
			+ "</a>";
}
function yxybjgView(jgid) {
	showDialog("Ժ���±��鿴", 800, 600, "yxybgl_jg.do?method=viewYxybjg&jgid="+jgid);
}


function saveYxybjg(type) {
	var flg=true;
	var ids = null;
	if(type=='save'||type=='submit'){
		ids = 'yf-xydm-bygzkzqk-xsgzrd-xssxdt-xstsjgzjy';
	}else{
		ids = 'bygzkzqk-xsgzrd-xssxdt-xstsjgzjy';		
	}
	if(check(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	var url = "yxybgl_jg.do?method=saveYxybjg&type=" + type;
	ajaxSubFormWithFun("yxybjgForm", url, function(data) {
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

function add() {
	var url = "yxybgl_jg.do?method=addYxybjg";
	var title = "�±���д";
	showDialog(title, 800, 600, url);
}

function update() {
	var sqkg = jQuery("#sqkg").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else if(rows[0]['sjly']=='1'){
		showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
	}else {
		var url = 'yxybgl_jg.do?method=editYxybjg&jgid=' + rows[0]["jgid"];
		var title = "Ժ���±��޸�";
		showDialog(title, 800, 600, url);
	}

}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��", {
		"okFun" : function() {
		var url = "yxybgl_jg.do?method=delYxybjg";
		jQuery.post(url, {
			values : ids.toString()
		}, function(data) {
			if (data["success"] == false) {
				showAlertDivLayer(data["message"]);
			} else {
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function(tag) {
						jQuery("#dataTable").reloadGrid();
					}
				});
			}
		}, 'json');
	
}});
}
}


var DCCLBH = "rcsw_yxybgl_jg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, yxybjgExportData);
}

//��������
function yxybjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "yxybgl_jg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
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

//�ϲ�����
function hbdc() {
	setSearchTj();//���ø߼���ѯ����
	var url = "yxybgl_jg.do?method=hbdc";
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}