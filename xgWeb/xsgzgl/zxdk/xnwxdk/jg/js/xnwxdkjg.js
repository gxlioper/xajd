

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add() {
	var isopen = jQuery("#sqkg").val();
	var cfbz = jQuery("#cfbz").val();
	if( "1"  == cfbz){
		showAlertDivLayer("��ѧ���ڵ�ǰѧ�����������¼�������ظ���д��");
		return false;
	}
	var url = "xnwxdk_jg.do?method=add";
	var title = "У����Ϣ����������";
	showDialog(title, 770, 552, url);
}

//�����޸����뱣��
function saveDkjg(type){
	var ids = ""
	if( type == "update"){
	   ids = "jttg"+"-"+"zxj"+"-"+"jxj"+"-"+"qgzxsr"+"-"+"xnwxjk"+"-"+"qtsr"+"-"+"zxdksqje"+"-"+"zxdksqsj"+"-"+"ffje"+"-"+"ffsj"+"-"+"sqje"+"-"+"sqly";
	}else{
		ids ="xh"+"-"+ "jttg"+"-"+"zxj"+"-"+"jxj"+"-"+"qgzxsr"+"-"+"xnwxjk"+"-"+"qtsr"+"-"+"zxdksqje"+"-"+"zxdksqsj"+"-"+"ffje"+"-"+"ffsj"+"-"+"sqje"+"-"+"sqly";
	}
	if(check(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	if(parseInt(jQuery("#sqje").val()) > parseInt(jQuery("#jesx").val())){
		showAlert("���������������"+jQuery("#jesx").val()+"Ԫ��");
        return false;
	}
	var url = "xnwxdk_jg.do?method=saveDkjg&type=" + type;
	ajaxSubFormWithFun("XnwxdkJgModel", url, function(data) {
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

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='DkjgView(\""
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function DkjgView(sqbh, xh) {
	showDialog("У����Ϣ�������鿴", 770, 450, "xnwxdk_jg.do?method=DkjgView&jgid="
			+ sqbh + "&xh=" + xh);
}

//ɾ����������
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}  else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xnwxdk_jg.do?method=delDkjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
			return false;
		}
		var url = 'xnwxdk_jg.do?method=editDkjg&jgid=' + rows[0]["jgid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "У����Ϣ�������޸�";
		showDialog(title, 770, 552, url);
	}
}

var DCCLBH = "zxdk_xnwxdk_jg.do";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, xyzsjgExportData);
}

//��������
function xyzsjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xnwxdk_jg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}



//�ֻ��绰��֤ 
function phonecheck(obj){
	  var phone = obj.val();
	  var tel = /(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)|(13\d{9}$)|(15[0135-9]\d{8}$)|(18[267]\d{8}$)/;
	  if(phone != "") {
	   if (!tel.exec(phone)) {
		showAlertDivLayer("�绰�����ʽ���ԣ���ȷ��ʽ���£�\n�������룺����-�绰����(��)\n�ֻ����룺13635456878");
		obj.focus();
		return false;
	   }
	  }
}


//У����Ϣ�������������
function printsqb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xnwxdk_jg.do?method=getXnwxdksq";
		url += "&jgid=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "xnwxdk_jg.do?method=gettXnwxdkTy";
		url += "&value=" + ids;
		window.open(url);
	}
}

function printHzb(){
	setSearchTj();//���ø߼���ѯ����
	var url = "xnwxdk_jg.do?method=getHzbexcel";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//����
function importConfig(){
	toImportDataNew("IMPORT_XNWXDK");
	return false;
}


