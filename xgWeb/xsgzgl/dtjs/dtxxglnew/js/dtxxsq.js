var action="dtxxsq.do";

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
function dcmcLink(cellValue, rowObject) {
	var dtxxsqid = rowObject["dtxxsqid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + dtxxsqid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//�鿴��Ϣ
function ckxx(dtxxsqid) {
	var url = action+"?method=showView&dtxxsqid=" + dtxxsqid;
	var title = "����������Ϣ";
	showDialog(title, 700, 380, url);
}
//����
function add() {
	var url =action+"?method=add";
	var title = "������Ϣ����";
	showDialog(title, 700, 430, url);
	jQuery("#dataTable").reloadGrid();
}
function save(url,checkId,keyid){
	if(!check(checkId)){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	if(!checkother()){
		return false;
	}
	var xh=jQuery("#xh").val();
	var dtxxsqid=jQuery("#"+keyid).val();
	lock();
	jQuery.post("dtxxsq.do?method=isCanAdd", {
		xh:xh,dtxxsqid:dtxxsqid,jddm:jQuery("#jddm").val()
	}, function(data) {
		if(data["success"]=="true"){
			 	jQuery("#form").ajaxSubmit({
					url:url,
					type:"post",
					dataType:"json",
					success:function(data){
				 		 if(data["message"]=="����ɹ���" ||data["message"]=="�ύ�ɹ���" ){
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
			showAlert(data["message"]);
		}
		unlock();
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
		var shzt=rows[0]["shzt"];
		if(shzt!="0"&&shzt!="3"){
			showAlert("��ѡ��δ�ύ�������˻صļ�¼��");
			return false;
		}
		var url = action+'?method=update&dtxxsqid=' + rows[0]["dtxxsqid"];
		var title = "�޸ĵ�����Ϣ����";
		showDialog(title, 700, 430, url);
		jQuery("#dataTable").reloadGrid();
	}
}
function checkother(){
	return true;
}
function checkNum(){
	var kssj=jQuery("#kssj").val();
	var str=kssj.substring(0,1);//��ȡ��һλ
	if(parseInt(str)<=0&&kssj.length>1){
		return true;
	}
	var jssj=jQuery("#jssj").val();
	str=jssj.substring(0,1);//��ȡ��һλ
	if(parseInt(str)<=0&&jssj.length>1){
		return true;
	}
	return false; 
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
						mes+="<font color='red'>["+data["nodel"]+"]</font>";
						mes+="�ѽ���������̲���ɾ��!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
function dtxxsqLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	
	var shzt = rows[0]["shztmc"];

	if ("δ�ύ" == shzt){
				showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
				return false;
	}
	
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("���Ź����������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['dtxxsqid']+"&splc="+rows[0]['splc']);
	}
}
//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(action,exportData);
}

// ��������
function exportData(dcclbh) {
	setSearchTj();//���ø߼���ѯ����
	var url = action+"?method=exportData&dcclbh=" + dcclbh;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function showRdzys(){
	var jdmc=jQuery("#jddm option:selected").text().trim();
	if("Ԥ����Ա"==jdmc){
		jQuery("#jddm").parent().removeAttr("colspan");
		jQuery("[name='rdzys'").show();
	}else{
		jQuery("#jddm").parent().attr("colspan","3");
		jQuery("[name='rdzys'").hide();
	}
}