var DCCLBH='yrgl_list.do';

function add(){
	showDialog('�������������Ϣ',800,360,'xsxx_yrgl.do?method=Yrgladd');
}
function save(){
	var xh=jQuery("#xh").val();
	var xn = jQuery('#xn').val();
	if(xh==null||xh==""||xn==null||xn==""){
		showAlert("����д��*�ŵ��ֶ�");
		return false;
	}	
	var url="xsxx_yrgl.do?method=Yrgladd&type=save";
	ajaxSubFormWithFun("yrglForm",url,function(data){
   	 if(data["message"]=="����ɹ���"){
   		 showAlert(data["message"],{},{"clkFun":function(){
   				if (parent.window){
   					refershParent();
   				}
   			}});
   	 }else if (data["success"] == "false"){
		  showAlert("��ѧ���ڵ�ǰѧ���Ѵ��ڣ�");
	  } else {
		  showAlert(data["message"],{},{"clkFun":function(){
  			if (parent.window){
				 refershParent();
  			}
		  }});
   	 }
	});		
}
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog('�޸����������Ϣ',800,340,'xsxx_yrgl.do?method=Yrgledit&guid='+rows[0]["guid"]);
	}
}

function view(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	} else {
		showDialog('�鿴���������Ϣ',800,340,'xsxx_yrgl.do?method=Yrglview&guid='+rows[0]["guid"]);
	}
}
function Yrglview(guid,cellValue){
	showDialog('�鿴���������Ϣ',800,340,'xsxx_yrgl.do?method=Yrglview&guid='+guid+"&xh="+cellValue,null);
}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("xsxx_yrgl.do?method=Yrgldel",{values:ids.toString()},function(data){
			alertInfo(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	}
}

function saveUpdate(){
	var xh=jQuery("#xh").val();
	var xn = jQuery('#xn').val();
	if(xh==null||xh==""||xn==null||xn==""){
		showAlert("����д��*�ŵ��ֶ�");
		return false;
	}
	
	var url="xsxx_yrgl.do?method=Yrgledit&type=save";
	ajaxSubFormWithFun("yrglForm",url,function(data){
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
	var url = "xsxx_yrgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function importConfig(){
	toImportDataNew("IMPORT_N711901_YRGL");
	return false; //��������
}