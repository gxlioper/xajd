var DCCLBH='xshjgl_list.do';

function add(){
	showDialog('����',800,360,'rcsw_xshjgl.do?method=Xshjgladd');
}
function save(){
	var xh=jQuery("#xh").val();
	var qrzt=jQuery("#qyzt").val();
	var qysj=jQuery("#qysj").val();
	if(xh==null||xh==""||qrzt==null||qrzt==""||qysj==null||qysj==""){
		showAlert("����д��*�ŵ��ֶ�");
		return false;
	}
	
	var url="rcsw_xshjgl.do?method=Xshjgladd&type=save";
	ajaxSubFormWithFun("xshjglForm",url,function(data){
   	 if(data["message"]=="����ɹ���"){
   		 showAlert(data["message"],{},{"clkFun":function(){
   				if (parent.window){
   					refershParent();
   				}
   			}});
   	 }else if(data["success"] == "false"){
   		if("0" == qrzt) {
   			showAlert("��ѧ���Ѵ���Ǩ���¼!");
   	   		return false;
   		}else if ("1" == qrzt){
   			showAlert("��ѧ���Ѵ���Ǩ����¼!");
   			return false;
   		}
   	 }else{
   		showAlert(data["message"]);
   	 }
	});
		
}
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog('�޸�ѧ��������Ϣ',800,340,'rcsw_xshjgl.do?method=Xshjgledit&hjglid='+rows[0]["hjglid"]);
	}
}

function view(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	} else {
		showDialog('�鿴ѧ��������Ϣ',800,340,'rcsw_xshjgl.do?method=Xshjglview&hjglid='+rows[0]["hjglid"]);
	}
}
function viewXshjgl(hjglid,cellValue){
	
	showDialog('�鿴ѧ��������Ϣ',800,340,'rcsw_xshjgl.do?method=Xshjglview&hjglid='+hjglid+"&xh="+cellValue,null);
}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("rcsw_xshjgl.do?method=Xshjgldel",{values:ids.toString()},function(data){
			alertInfo(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	}
}

function saveUpdate(){
	var qyzt=jQuery("#qyzt").val();
	var qysj=jQuery("#qysj").val();
	if(qyzt==null||qyzt==""||qysj==null||qysj==""){
		showAlert("����д��*�ŵ��ֶ�");
		return false;
	}
	
	var url="rcsw_xshjgl.do?method=Xshjgledit&type=save";
	ajaxSubFormWithFun("xshjglForm",url,function(data){
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

function exportConfig(){     //����
	customExport(DCCLBH, exportData);
}
function exportData(){
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_xshjgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function importConfig(){
	toImportDataNew("IMPORT_RCSW_XSHJGL");
	return false; //��������
}