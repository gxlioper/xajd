var DCCLBH='szdw_fdyjtff.do';

function add(){
	showDialog('���Ӹ���Ա��������',800,320,'szdw_fdyjtff.do?method=addFdyjtff');
}

function saveAdd(){
	var zgh=jQuery("#zgh").val();
	var bzlx=jQuery("#bzlx").val();
	var xn=jQuery("#xn").val();
	var xq=jQuery("#xq").val();
	var bzje=jQuery("#bzje").val();
	if(zgh==null||zgh==""||bzlx==null||bzlx==""||xn==null||xn==""||xq==null||xq==""||bzje==null||bzje==""){
		showAlert("����д��*�ŵ��ֶ�");
		return false;
	}
	var url="szdw_fdyjtff.do?method=addFdyjtff&type=save";
	ajaxSubFormWithFun("fdyjtffForm",url,function(data){
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
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog('�޸ĸ���Ա����������Ϣ',800,320,'szdw_fdyjtff.do?method=updateFdyjtff&id='+rows[0]["id"]);
	}
}
function saveUpdate(){
	var zgh=jQuery("#zgh").val();
	var bzlx=jQuery("#bzlx").val();
	var xn=jQuery("#xn").val();
	var xq=jQuery("#xq").val();
	var bzje=jQuery("#bzje").val();
	if(zgh==null||zgh==""||bzlx==null||bzlx==""||xn==null||xn==""||xq==null||xq==""||bzje==null||bzje==""){
		showAlert("����д��*�ŵ��ֶ�");
		return false;
	}
	var url="szdw_fdyjtff.do?method=updateFdyjtff&type=save";
	ajaxSubFormWithFun("fdyjtffForm",url,function(data){
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
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("szdw_fdyjtff.do?method=delFdyjtff",{values:ids.toString()},function(data){
			alertInfo(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	}
}

function view(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	} else {
		showDialog('�鿴����Ա����������Ϣ',800,320,'szdw_fdyjtff.do?method=viewFdyjtff&id='+rows[0]["id"]);
	}
}
function jtffView(id){
	showDialog('�鿴����Ա����������Ϣ',800,320,'szdw_fdyjtff.do?method=viewFdyjtff&id='+id);
}




function changbzlx(){
	// Ϋ��ѧԺ �򲻽���
	if(jQuery("#xxdm").val() == '11067'){
		var kpdj=jQuery("#kpdj");
		kpdj.removeAttr("disabled");
		return;
	}
	var bzlx=jQuery("#bzlx").val();
	if(bzlx=="1"){
		var kpdj=jQuery("#kpdj");
		kpdj.removeAttr("disabled");
		
	}else {
		var kpdj=jQuery("#kpdj");
		kpdj.val("");
		kpdj.attr("disabled",true);
	}
}


function exportConfig(){
	customExport(DCCLBH, exportData);
}
function exportData(){
	setSearchTj();//���ø߼���ѯ����
	var url = "szdw_fdyjtff.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}