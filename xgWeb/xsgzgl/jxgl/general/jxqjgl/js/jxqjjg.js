

var action="jxqjjg.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
//��������ת
function dcmcLink(cellValue, rowObject) {
	var qjid = rowObject["qjid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + qjid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//�鿴��Ϣ
function ckxx(qjid) {
	var query=jQuery("#query").val();
	var url = action+"?method=showView&qjid=" + qjid;
	var title = "��ѵ�����Ϣ";
	showDialog(title, 800, 500, url);
}
//����
function add() {
		var url =action+"?method=add";
		var title = "��ѵ�������";
		showDialog(title, 800, 500, url);
		jQuery("#dataTable").reloadGrid();
}
//������ʽ��֤
function postfixCheck(){
	var wjm=jQuery("#formfile").val();
	if(wjm==""||wjm==null){
		return true;
	}
	var wjms=wjm.split(".");
	var hz=",bmp,jpg,jpeg,gif,png,pdf,doc,BMP,JPG,JPEG,GIF,PNG,PDF,DOC";
	if(hz.indexOf(wjms[wjms.length-1])<0){
		return false;
	}
	return true;
}
function save(url,checkId,sfjc){
	var jxkssj=jQuery("#jxkssj").val();
	var jxjssj=jQuery("#jxjssj").val();
	if(!check(checkId)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	if(!checkQjsj(jxkssj,jxjssj)){
		return showAlert("���ʱ�䲻�ھ�ѵʱ��"+jxkssj+"-"+jxjssj+"�ڣ�");
	}
	if(!postfixCheck()){
		return showAlert("���ϴ�֧�ֵĸ�����ʽ��");
	}
	

	lock();
	var qjts=jQuery("#qjts").val();

	ajaxSubFormWithFun("JxqjjgForm", url, function(data) {
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
//�ж����ʱ���Ƿ��ھ�ѵ�ڼ�
function checkQjsj(jxkssj,jxjssj){
	var qjkssj=jQuery("#qjkssj").val().replace(/-/g,"");
	var qjjssj=jQuery("#qjjssj").val().replace(/-/g,"");
	var jxkssj=jQuery("#jxkssj").val().replace(/-/g,"");
	var jxjssj=jQuery("#jxjssj").val().replace(/-/g,"");
	if(null!=jxkssj&&""!=jxkssj&&""!=jxjssj&&null!=jxjssj&&(qjkssj<jxkssj||qjjssj>jxjssj)){
		return false;
	}
	return true;
	
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
//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var xh=rows[0]["xh"];
		var sjly=rows[0]["sjly"];
		if(1==sjly||"1"==sjly){
			showAlertDivLayer("�������ݲ����޸�!");
		}else{
			var url = action+'?method=update&xh='+xh+'&qjid=' + rows[0]["qjid"];
			var title = "�޸ľ�ѵ�����Ϣ";
			showDialog(title, 800, 500, url);
			jQuery("#dataTable").reloadGrid();
		}
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
	toImportDataNew("IMPORT_N450401_JXQJJG");
	return false;

}
//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport('jxgl_jxqjgl_qjjggl.do',exportData);
}

// ��������
function exportData(dcclbh) {
	setSearchTj();//���ø߼���ѯ����
	var url = "jxqjjg.do?method=exportData&dcclbh=" + dcclbh;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//��ӡ�����
function printjxqjjgb(url){
	var qjid="";
	var xh="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("������ѡ��һ����¼��");
		return false;
	} else {
		if("14073" == jQuery("#xxdm").val()) {
			if(rows.length == 1){
				if(rows[0]["qjlxmc"] == "����") {
					showAlertDivLayer("�����ز��ٵ���");
					return false;
				}
			}else {
				for (var i = 0; i < rows.length; i++) {
					if(rows[i]["qjlxmc"] == "����"){
						showAlertDivLayer("�����ز��ٵ���");
						return false;			
					}
				}	
			}
		}
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				qjid +=rows[i]["qjid"];
				xh+=+rows[i]["xh"];
			}else{
				qjid +=rows[i]["qjid"]+",";
				xh+=+rows[i]["xh"]+",";
			}
		}
		var url = url + "&qjid=" +qjid+"&xh="+xh;
		window.open(url);
	}
}

//��ӡ�����
function printjxqjBjd(url){
	var qjid="";
	var xh="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("������ѡ��һ����¼��");
		return false;
	} else {
		
		if(rows.length == 1){
			if(rows[0]["qjlxmc"] != "����") {
				showAlertDivLayer("�ǲ��٣��޷����ز��ٵ���");
				return false;
			}
		}else {
			for (var i = 0; i < rows.length; i++) {
				if(rows[i]["qjlxmc"] != "����"){
					showAlertDivLayer("�ǲ��٣��޷����ز��ٵ���");
					return false;			
				}
			}	
		}
		
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				qjid +=rows[i]["qjid"];
				xh+=+rows[i]["xh"];
			}else{
				qjid +=rows[i]["qjid"]+",";
				xh+=+rows[i]["xh"]+",";
			}
		}
		var url = url + "&qjid=" +qjid+"&xh="+xh;
		window.open(url);
	}
}

