//ҳ������д
function add() {
		var url ="rcsw_xscxqybgl.do?method=XscxqybAdd";
		var title = "�±���д";
		showDialog(title, 800, 500, url);
	}
	
//��д��ɵı��淽��
function saveForm(type){
	var yf = jQuery("#yf").val();
	var bygzkzqk = jQuery("#bygzkzqk").val();
	var xsgzrd = jQuery("#xsgzrd").val();
	var xssxdt = jQuery("#xssxdt").val();
	var xstsjgzjy = jQuery("#xstsjgzjy").val();
	if (jQuery.trim(yf) == ""){
		showAlert("�·ݲ���Ϊ�գ�");
		return false;
	}
	if (jQuery.trim(bygzkzqk) == ""){
		showAlert("���¹�����չ�������Ϊ�գ�");
		return false;
	}	
	if (bygzkzqk.length > 1000){
		showAlert("���¹�����չ������1000�֣�");
		return false;
	}	
	if (jQuery.trim(xsgzrd) == ""){
		showAlert("ѧ����ע�ȵ㲻��Ϊ�գ�");
		return false;
	}	
	if (xsgzrd.length > 1000){
		showAlert("ѧ����ע�ȵ����1000�֣�");
		return false;
	}	
	if (jQuery.trim(xssxdt) == ""){
		showAlert("ѧ��˼�붯̬����Ϊ�գ�");
		return false;
	}	
	if (xssxdt.length > 1000){
		showAlert("ѧ��˼�붯̬���1000�֣�");
		return false;
	}	
	if (jQuery.trim(xstsjgzjy) == ""){
		showAlert("ѧ�����󼰹������鲻��Ϊ�գ�");
		return false;
	}	
	if (xstsjgzjy.length > 1000){
		showAlert("ѧ�����󼰹����������1000�֣�");
		return false;
	}	
	var url = "rcsw_xscxqybgl.do?method=XscxqybAdd&type="+type;
  	ajaxSubFormWithFun("xscxqybForm",url,function(data){
	 if(data["message"]=="����ɹ���" ){
		 showAlert(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
	 }else{
		 showAlert(data["message"]);
	 }});
	}

//Listҳ���޸İ�ť
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var selTxr = rows[0]["txr"];//���ݿ���д�˼�¼����
		var dqUser = jQuery("#userName").val();//��ǰ������
		if(selTxr != dqUser) {	
			alertInfo("ѡ���¼�а�������д�˲��ǵ�ǰ�û���������ѡ��");
			return false;
		}
		showDialog('�±��޸�',750,490,'rcsw_xscxqybgl.do?method=XscxqybUpdate&jgid='+rows[0]["jgid"]);
	}
}

//�޸���ɺ�ı��淽��
function saveUpdate(){
var yf=jQuery("#yf").val();
var bygzkzqk=jQuery("#bygzkzqk").val();
var xsgzrd=jQuery("#xsgzrd").val();
var xssxdt=jQuery("#xssxdt").val();
var xstsjgzjy=jQuery("#xstsjgzjy").val();
if(yf==null||yf==""||bygzkzqk==null||bygzkzqk==""||xsgzrd==null||xsgzrd==""||xssxdt==null||xssxdt==""||xstsjgzjy==null||xstsjgzjy==""){
	showAlert("����д��*�ŵ��ֶ�");
	return false;
}
var url="rcsw_xscxqybgl.do?method=XscxqybUpdate&type=save";
ajaxSubFormWithFun("xscxqybForm",url,function(data){
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

//ɾ������
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var dqUser = jQuery("#userName").val();//��ǰ������
	if (ids.length == 0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	}else {
		var flg = false;
		for(var i=0;i<ids.length;i++){
			if(rows[i]["txr"]!=dqUser){	//rows[i]["txr"]����ΪҪ�ö���txr����
				flg = true;
				break;
			}
		}		
		if(flg == true) {
			alertInfo("ѡ���¼�а�������д�˲��ǵ�ǰ�û���������ѡ��");
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("rcsw_xscxqybgl.do?method=XscxqybDel",{values:ids.toString()},function(data){
			alertInfo(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	}
}

//����
function exportConfig(){     //����
	var DCCLBH='rcsw_xscxqybgl.do';
	customExport(DCCLBH, exportData);
	}
function exportData(){
	var DCCLBH='rcsw_xscxqybgl.do';
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_xscxqybgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����·ݲ鿴��ϸ��Ϣ
function XscxqybView(jgid,cellValue){
	showDialog('�±��鿴',700,350,'rcsw_xscxqybgl.do?method=XscxqybView&jgid='+jgid);
}