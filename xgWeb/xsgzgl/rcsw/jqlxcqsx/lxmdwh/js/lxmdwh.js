/**
 * ��ѯ
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["id"] + "\",\""
			+ rowObject["xh"] + "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ��ajaxurl��ת
function View(id,xh) {
	showDialog("��У�����鿴", 770, 450, "jqlx_lxmdwh.do?method=ckMdwh&id="
			+ id+"&xh="+xh);
}

/**
 * ����ά��
 * @return
 */
function add(){
	showDialog("������У����ά��", 770, 500, "jqlx_lxmdwh.do?method=plMdwh"
			);
}


//��������
function savePlwh(){
	var url = "jqlx_lxmdwh.do?method=savePlMdwh";
	//��������
	if(!checkNotNull("lxqksm")){
		 showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������");
		 return false;
	}
	if(jQuery("[name='xh']").length == 0){
		 showAlert("������ѡ��һ��ѧ����");
		 return false;
	}
	if(jQuery("#lxqksm").val().length > 500){
		 showAlert("��У���˵�����ܴ���500�֣�");
		 return false;
	}
	ajaxSubFormWithFun("LxmdwhForm", url, function(data) {
		 if(data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		 return false;
    		}
		});
}

//ɾ��ά�����
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("jqlx_lxmdwh.do?method=delWhjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "xg_rcsw_cqsx_jqlx_lxmdwh.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, xyzsjgExportData);
}

//��������
function xyzsjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "jqlx_lxmdwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importConfig(){
	toImportDataNew("IMPORT_LXMDWH");
	return false;
}


/**
 * �����޸�
 * @return
 */
function saveXg(){
	//����������֤
	if((!checkNotNull("lxqksm"))){
		 showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������");
		 return false;
	}
	if(jQuery("#lxqksm").val().length > 500){
		 showAlert("��У���˵�����ܴ���500�֣�");
		 return false;
	}
	var url = "jqlx_lxmdwh.do?method=xgMdwh&type=save";
	ajaxSubFormWithFun("LxmdwhForm", url, function(data) {
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

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else {
		var url = 'jqlx_lxmdwh.do?method=xgMdwh&id=' + rows[0]['id'];
		var title = "�޸���У����ά��";
		showDialog(title, 700, 450, url);
	}
}

//��ѡ��ѡ��
function selectAll(obj){
	if(obj.checked){
		jQuery("[name='chk']").attr("checked","checked");
	}else{
		jQuery("[name='chk']").removeAttr("checked");
	}
}

/**
 * ��У��Ŀ������ֵ�ı�����
 * @return
 */
function changeXmmc(obj){
	var objvalue = obj.value;
	document.location.href = "jqlx_lxmdwh.do?method=plMdwh&xmid="+objvalue;
}

/**
 * ���ѧ��
 * @return
 */
function addRowDialog(){
	if(jQuery("#xmid").val() == "" || jQuery("#xmid").val() == null){
		  showAlert("����ѡ����Ŀ��");
		  return false;
	}
	var xhs = getxhs();
	 var xmid= jQuery("#xmid").val();
    var url = "jqlx_lxmdwh.do?method=getCanAddUserList&xhs="+xhs+"&xmid="+xmid;
    var title = "ѧ��ѡ��";
	showDialog(title, 800, 550, url);
}

//�õ�����ӵ�ѧ���ַ���
function getxhs(){
	var xhs = "";
	var xhobj = jQuery("[name='xh']");
	jQuery(xhobj).each(function(i){
		xhs +=this.value;
		if(xhobj.length-1 != i){
			xhs +=",";
		}
		
	});
	return xhs;
}

//ɾ����
function delRow(){
	var obj = jQuery("[name='chk']:checked").parent().parent();
	if(obj.length == 0){
		showAlert("����ѡ���Ա��Ϣ���ٽ���ɾ��������");
		return false;
	}
	jQuery(obj).remove();
	jQuery("[name='chkall']").removeAttr("checked");
}



