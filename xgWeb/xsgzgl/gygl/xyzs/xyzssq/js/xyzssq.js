/**
 * @author ����Դ 
 * @����:ס�޽����js
 * @develop-date:2015-05-25
 */


function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add() {
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("0" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var cfbz = jQuery("#cfbz").val();
	if( "1"  == cfbz){
		showAlertDivLayer("��ѧ���ڵ�ǰѧ�����������¼�������ظ���д��");
		return false;
	}
	var url = "gygl_xyzssqgl.do?method=add";
	var title = "У��ס������";
	showDialog(title, 900, 552, url);
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
		showAlert("У��ס��������ʼ���ڲ��ܴ�����ֹ���ڣ���ѡ��1��");
		return false;
	}
	var url = "gygl_xyzssqgl.do?method=saveZsjg&type=" + type;
	ajaxSubFormWithFun("XyzsSqForm", url, function(data) {
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

function ZsjgView(sqbh, xh) {
	showDialog("У��ס������鿴", 900, 480, "gygl_xyzssqgl.do?method=ZsjgView&sqbh="
			+ sqbh + "&xh=" + xh);
}

//ɾ��ס�޽��
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
			jQuery.post("gygl_xyzssqgl.do?method=delZsjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
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
		showAlert("У��ס��������ʼ���ڲ��ܴ�����ֹ���ڣ���ѡ��1��");
		return false;
	}
}

//�޸�
function update(){
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if ("0" != rows[0]['shzt']&&"3" !=rows[0]['shzt']) {
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
		var url = 'gygl_xyzssqgl.do?method=editZsjg&sqbh=' + rows[0]["sqbh"]
				+ '&xh=' + rows[0]["xh"];
		var title = "У��ס�������޸�";
		showDialog(title, 900, 552, url);
	}
}

var DCCLBH = "gygl_xyzssq.do";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, xyzsjgExportData);
}

//��������
function xyzsjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "gygl_xyzssqgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�ύ
function submitBusi() {
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		if ("0" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if ('3'!=rows[0]["shzt"] && "0" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
			showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("gygl_xyzssqgl.do?method=submitBusi", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

//����
function cancel() {
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("0" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
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
				jQuery.post("gygl_xyzssqgl.do?method=cancelZssq", {
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
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		showDialog("У��ס�������������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqbh'] + "&splc=" + rows[0]['splc']);
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

//У��ס���������
function printXyzsjgsqb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "gygl_xyzsjggl.do?method=getXyzsjgsqb"+"&flag=sq";
		url += "&sqbh=" + ids+"&flag=sq";
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "gygl_xyzsjggl.do?method=getXyzsjgsqbTy";
		url += "&value=" + ids+"&flag=sq";
		window.open(url);
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
