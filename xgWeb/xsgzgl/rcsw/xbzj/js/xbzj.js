var DCCLBH='rcsw_xbzj.do';

function add(){
	showDialog('��������֧��ѧ��',800,360,'rcsw_xbzj.do?method=addXbzjxs');
}
function save(){
	var xh=jQuery("#xh").val();
	var xn=jQuery("#xn").val();
	var xq=jQuery("#xq").val();
	var zjsj=jQuery("#zjsj").val();
	if(xh==null||xh==""||xn==null||xn==""||xq==null||xq==""||zjsj==null||zjsj==""){
		showAlert("����д��*�ŵ��ֶ�");
		return false;
	}
	
	var url="rcsw_xbzj.do?method=addXbzjxs&type=save";
	ajaxSubFormWithFun("xbzjForm",url,function(data){
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
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog('�޸�����֧��ѧ����Ϣ',800,340,'rcsw_xbzj.do?method=updateXbzjxs&id='+rows[0]["id"]);
	}
}

function view(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	} else {
		showDialog('�鿴����֧��ѧ����Ϣ',800,340,'rcsw_xbzj.do?method=viewXbzjxs&id='+rows[0]["id"]);
	}
}

function viewXbzjxs(id){
	
	showDialog('�鿴����֧��ѧ����Ϣ',800,340,'rcsw_xbzj.do?method=viewXbzjxs&id='+id);
}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("rcsw_xbzj.do?method=delXbzjxs",{values:ids.toString()},function(data){
			alertInfo(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	}
}

function saveUpdate(){
	var zjsj=jQuery("#zjsj").val();
	if(zjsj==null||zjsj==""){
		showAlert("����д��*�ŵ��ֶ�");
		return false;
	}
	
	var url="rcsw_xbzj.do?method=updateXbzjxs&type=save";
	ajaxSubFormWithFun("xbzjForm",url,function(data){
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

function exportConfig(){
	customExport(DCCLBH, exportData);
}
function exportData(){
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_xbzj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
