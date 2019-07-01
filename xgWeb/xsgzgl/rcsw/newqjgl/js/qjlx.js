var action="qjlx.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
//����
function add() {
		var url =action+"?method=add";
		var title = "�����������";
		showDialog(title, 400, 200, url);
		jQuery("#dataTable").reloadGrid();
}
function save(url,checkId){
	if(!check(checkId)){
		return alertError("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	var qjlxmc=jQuery("#qjlxmc").val();
	var qjlxid=jQuery("#qjlxid").val();
	jQuery.post("qjlx.do?method=isCanAdd", {
		qjlxmc:qjlxmc,qjlxid:qjlxid
	}, function(data) {
		if(data["success"]=="true"){
		 	jQuery("#form").ajaxSubmit({
				url:url,
				type:"post",
				dataType:"json",
				success:function(data){
			 		 if(data["message"]=="����ɹ���"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    			}});
			    	 }else{
			    		 showAlert(data["message"]);
			    	 }
				},
				contentType:"application/x-www-form-urlencoded;charset=utf-8"
			});	
		}else{
			showAlert("�Ѿ����ڴ��������!");
		}
	}, 'json');
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
//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
				var url = action+'?method=update&qjlxid=' + rows[0]["qjlxid"];
				var title = "�޸��������";
				showDialog(title, 400, 200, url);
				jQuery("#dataTable").reloadGrid();
	}
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
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
						mes+="</br>";
						if(data["nodel"]!="-1"){
							mes+="������ʹ���Ϊ<font color='red'>["+data["nodel"]+"]</font>";
							mes+="����������Ѿ���ʹ�ò���ɾ��!";
						}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}

//��������
function openLink(cellValue, rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='linkClick(\""
	+ rowObject["qjlxid"] + "\");'>" + cellValue
	+ "</a>";
}

//������ӵ���
function linkClick(qjlxid){
	showDialog("�����رտ���", 400, 200, "qjlx.do?method=openZt&qjlxid="
			+ qjlxid);
}