var action="jxkqjg.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
//��������ת
function xhLink(cellValue, rowObject) {
	var kqid = rowObject["kqid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + kqid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//�鿴��Ϣ
function ckxx(kqid) {
	var query=jQuery("#query").val();
	var url = action+"?method=showView&kqid=" + kqid;
	var title = "��ѵ������Ϣ";
	showDialog(title, 800, 400, url);
}
//����
function add() {
		var url =action+"?method=add";
		var title = "��ѵ��������";
		showDialog(title, 800, 420, url);
		jQuery("#dataTable").reloadGrid();
}
//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var xh=rows[0]["xh"];
			var url = action+'?method=update&xh='+xh+'&kqid=' + rows[0]["kqid"];
			var title = "�޸ľ�ѵ������Ϣ";
			showDialog(title, 800, 420, url);
			jQuery("#dataTable").reloadGrid();

	}
}
function save(url,checkId,sfjc){
	if(!check(checkId)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	if(jQuery("#kqqk").val().length>500){
		return showAlert("��������������500�֣�");
		
	}
	lock();
	ajaxSubFormWithFun("JxkqjgForm", url, function(data) {
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
function chkNumInputForThis(obj){
	//�����ǹ���js ����ͬҳ�� ��Щ���󲻴��ڵ�����
	if(null==obj||null==$(obj)){
		return false;
	}
	return chkNumInput(obj);
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
			//alert(id[i]);
			return false;
		}
	}
	return true;
}

//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
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
function dr() {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_N450501_JXKQJG");
	return false;

}
//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport('jxgl_jxkqgl_kqjggl.do',exportData);
}

// ��������
function exportData(dcclbh) {
	setSearchTj();//���ø߼���ѯ����
	var url = "jxkqjg.do?method=exportData&dcclbh=" + dcclbh;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}




