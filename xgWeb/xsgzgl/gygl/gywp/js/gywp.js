var DCCLBH="gygl_qywpxx.do";

function cxGywpxxList(pk){
	document.forms[0].action="gygl_qywpxx.do?method=cxGywpxxList&pk="+escape(pk);
	document.forms[0].submit();
}
//������Ʒά��
function addGywpxx(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlert("��ѡ����Ҫά����Ʒ�����ң�");
		return false;
	}
	showDialog('���ӹ�Ԣ��Ʒ',800,375,'gygl_qywpxx.do?method=addGywpxx&ids='+escape(ids.toString()));
}
//������Ʒά��
function saveAdd(){
	var result=checkNotNull("wpmc-wpdldm-wplbdm-sl");
	if(!result){
		showAlert("����д��*�ŵ��ֶ�");
		return false;
	}
	var url="gygl_qywpxx.do?method=saveAdd";
	ajaxSubFormWithFun("gywpxxForm",url,function(data){
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
function updateGywpxx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length!=1){
		showAlert("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog('�޸Ĺ�Ԣ��Ʒ��Ϣ',800,375,'gygl_qywpxx.do?method=updateGywpxx&id='+rows[0]["id"]);
	}
}
//�����޸�
function saveUpdate(){
	var result=checkNotNull("wpdldm-wplbdm-sl");
	if(!result){
		showAlert("����д��*�ŵ��ֶ�");
		return false;
	}
	var url="gygl_qywpxx.do?method=saveUpdate";
	ajaxSubFormWithFun("gywpxxForm",url,function(data){
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

//ɾ��
function delGywpxx(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlert("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("gygl_qywpxx.do?method=delGywpxx",{values:ids.toString()},function(data){
				alertInfo(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//�鿴
function viewGywpxx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlert("��ѡ��һ����Ҫ�鿴�ļ�¼��");
		return false;
	} else {
		showDialog('�鿴��Ԣ��Ʒ��Ϣ',800,375,'gygl_qywpxx.do?method=viewGywpxx&id='+rows[0]["id"]);
	}
}

function gywpxxView(id){
	showDialog('�鿴��Ԣ��Ʒ��Ϣ',800,375,'gygl_qywpxx.do?method=viewGywpxx&id='+id);
}

//����
function back(){
	var url="gygl_qywpxx.do?method=cxGywplrxxList";
	refreshForm(url);
}

//�ı��Ƿ�����ֶ�
function changeSfwh(){
	var sfwh=jQuery("#sfwh").val();
	var hhyy=jQuery("#hhyy");
	if(sfwh=="��"){
		hhyy.val("");
		hhyy.attr("readonly",true);
	}else if(sfwh=="��"){
		hhyy.removeAttr("readonly");
	}
	
}

function exportConfig(){
	customExport(DCCLBH, exportData);
}
function exportData(){
	setSearchTj();//���ø߼���ѯ����
	var url = "gygl_qywpxx.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}