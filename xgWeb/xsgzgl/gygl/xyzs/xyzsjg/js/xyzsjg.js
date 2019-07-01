/**
 * @author ����Դ 
 * @����:ס�޽����js
 * @develop-date:2015-05-19
 * @lastupdate-date:2015-05-22
 */


function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add() {
	var url = "gygl_xyzsjggl.do?method=add";
	var title = "У��ס�޽��";
	showDialog(title, 900, 552, url);
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
		var url = 'gygl_xyzsjggl.do?method=editZsjg&sqbh=' + rows[0]["sqbh"]
				+ '&xh=' + rows[0]["xh"];
		var title = "У��ס�޽��";
		showDialog(title, 900, 552, url);
	}
}

//�����޸Ľ������
function saveZsjg(type){
	var ids = "xh"+"-"+"start"+"-"+"end"+"-"+"xxdz"+"-"+"lxdh"+"-"+"parentslxdy"+"-"+"xl";
	if(check(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}else if(checkContentIsNull()== false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	//�������ո��Ի�
	if("13871" == jQuery("#xxdm").val()){
		if (jQuery(".MultiFile-label").length == 0){
			showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
			return false;
		}
	}
	if(checkDataIsExact() == false){
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	if((jQuery("#start").val()>jQuery("#end").val())){
		showAlert("У��ס��������ʼ���ڲ��ܴ�����ֹ���ڣ���ѡ��");
		return false;
	}
	var url = "gygl_xyzsjggl.do?method=saveZsjg&type=" + type;
	ajaxSubFormWithFun("XyzsglForm", url, function(data) {
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
	return "<a href='javascript:void(0);' class='name' onclick='ZsjgView(\""
			+ rowObject["sqbh"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ��ajaxurl��ת
function ZsjgView(sqbh, xh) {
	showDialog("У���������鿴", 900, 480, "gygl_xyzsjggl.do?method=ZsjgView&sqbh="
			+ sqbh + "&xh=" + xh);
}
//ɾ��ס�޽��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("gygl_xyzsjggl.do?method=delZsjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
var DCCLBH = "gygl_xyzsjg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, xyzsjgExportData);
}

//��������
function xyzsjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "gygl_xyzsjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//У��ס�޽���������
function printXyzsjgsqb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "gygl_xyzsjggl.do?method=getXyzsjgsqb";
		url += "&sqbh=" + ids+"&flag=jg";
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "gygl_xyzsjggl.do?method=getXyzsjgsqbTy";
		url += "&value=" + ids+"&flag=jg";
		window.open(url);
	}
}

//�ж�ס�޽����Ҫ�����Ƿ�Ϊ�յĺ���
function checkContentIsNull(){
	 if(jQuery('input[name="zwjzyy"]:checked').val() == null){
		return false;
	}
}

//��֤���ݵ�׼ȷ��
function checkDataIsExact(){
	if(jQuery("#start").val()>jQuery("#end").val()){
		showAlert("У��ס��������ʼ���ڲ��ܴ�����ֹ���ڣ���ѡ��");
		return false;
	}
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

function checkzs(){
	if(jQuery("#bz").val().length > 50){
		showAlertDivLayer("��ע�������Ϊ50�����Ѿ���������ȷ�ϣ���");
		return false;
	}
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



