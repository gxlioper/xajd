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

/**
 * ����ѧ���춯���
 * @return
 */
function add(){
	var url = "xjyd.do?method=xjydlbAdd";
	var title = "����ѧ���춯���";
	showDialog(title,470,220,url);
}

/**
 * ����ѧ���춯����������
 * @return
 */
function addShpz(){
	var url = "xjyd.do?method=xjydlbShpzAdd";
	var title = "����ѧ���춯����������";
	showDialog(title,580,330,url);
}

/**
 * �޸�ѧ���춯���
 * @return
 */
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else if(rows[0]["sfnz"]=='1') {
		showAlertDivLayer("ϵͳ����������޸ģ�");
		return false;
	}else {
		var url = 'xjyd.do?method=xjydlbUpd&xjlbdm='+rows[0]["xjlbdm"];
		var title = "�޸�ѧ���춯���";
		showDialog(title,470,220,url);
	}
}

/**
 * �޸�ѧ���춯����������
 * @return
 */
function updateShpz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xjyd.do?method=xjydlbShpzUpd&xjlbdm='+rows[0]["xjlbdm"];
		var title = "�޸�ѧ���춯���";
		showDialog(title,580,360,url);
	}
}


/**
 * ɾ��ѧ���춯���
 * @return
 */
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else if(rows[0]["sfnz"]=='1') {
		showAlertDivLayer("ϵͳ���������ɾ����");
		return false;
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("xjyd.do?method=xjydlbDel",{values:ids.toString()},function(data){
					if(data["success"]=="false"){
						showAlertDivLayer("ѡ�з�Χ���д��뱻ʹ�ã�������ɾ��");
					}else{
						showAlertDivLayer(data["message"]);
					}
					
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
	
}


/**
 * ɾ��ѧ���춯���
 * @return
 */
function delShpz(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("xjyd.do?method=xjydlbShpzDel",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}