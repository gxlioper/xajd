//����ѧ����Ѫ��Ϣ
function saveForm(method){
	if(yzForm()){
		var url = "rcsw_xsxxgl.do?method="+method+"&type=save";
		ajaxSubFormWithFun("demoForm",url,function(data){
			 showAlert(data["message"],{},{"clkFun":function(){
 				if (parent.window){
 					refershParent();
 				}
 			}});

		});
	}
}
//��֤���ύ��Ϣ
function yzForm(){
	var result =false;
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	var xxsj = jQuery("#xxsj").val();
	var bz = jQuery("#bz").val();
	if(xh==null||""==xh){
		alertInfo("ѧ�Ų��ܿ�");
	}if(xn==null||""==xn){
		alertInfo("��ѡ��ѧ��");
	}else if(xxsj==null||""==xxsj){
		alertInfo("��Ѫʱ�䲻�ܿ�");
	}else if(bz.length>2000){
		alertInfo("��עֻ������1000��!");
	}else{
		result =true;
	}
	return result;
}

function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='ckXsxxgl(\""+rowObject["xxgldm"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}
function add(){
	//showWindow("���ѧ����Ѫ��Ϣ",680,600,'rcsw_xsxxgl.do?method=zjXxgl',null);

	showDialog("���ѧ����Ѫ��Ϣ",680,420,'rcsw_xsxxgl.do?method=zjXxgl');
	//showTopWin("rcsw_xsxxgl.do?method=zjXxgl",800,600);
	//refreshForm("rcsw_xsxxgl.do?method=zjXxgl");
}
function ckXsxxgl(xxgldm,cellValue){
	showDialog("�鿴ѧ����Ѫ��Ϣ",680,350,'rcsw_xsxxgl.do?method=ckXxgl&xxgldm='+xxgldm+"&xh="+cellValue,null);
	//showWindow("�鿴ѧ����Ѫ��Ϣ",680,450,'rcsw_xsxxgl.do?method=ckXxgl&xxgldm='+xxgldm+"&xh="+cellValue,null);

}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function btn_close(){

	iFClose();
}
//���ز�ѯ���
function query(){
	var map ={};
	var array = jQuery("#myForm").serializeArray();
		jQuery(array).each(function(index) {
			map[jQuery(this).attr("name")] = jQuery(this).val();
	 });
	jQuery("#dataTable").reloadGrid(map);
}
//�޸�һ����¼
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		//refreshForm('rcsw_xsxxgl.do?method=xgXxgl&xxgldm='+rows[0]["xxgldm"]);
		//showWindow("�޸�ѧ����Ѫ��Ϣ",680,450,'rcsw_xsxxgl.do?method=xgXxgl&xxgldm='+rows[0]["xxgldm"]+"&xh="+rows[0]["xh"],null);
		showDialog("�޸�ѧ����Ѫ��Ϣ",680,450,'rcsw_xsxxgl.do?method=xgXxgl&xxgldm='+rows[0]["xxgldm"]+"&xh="+rows[0]["xh"],null);

	}
}
//ɾ����¼
function deletes(){
	var ids = jQuery("#dataTable").getSeletIds();
	
	if (ids.length == 0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		confirmInfo("��ȷ��Ҫɾ����Щ��¼��",function(tp){
			if(tp=="ok"){
				jQuery.post("rcsw_xsxxgl.do?method=scXxgl",{values:ids.toString()},function(data){
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
		
	}
}
function checkStudent(){
	showWindow('��ѡ��һ��ѧ��',680,450,'xsxx_xsgl.do?method=showStudents&goto=rcsw_xsxxgl.do?method=zjXxgl');
}

function xsxxxxwhExportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport("rcsw_xsxxgl_xsxxxxwh.do", xsxxxxwhExportData);
}
	

	
// ��������
function xsxxxxwhExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_xsxxgl.do?method=xsxxxxwhExportData&dcclbh=" + "rcsw_xsxxgl_xsxxxxwh.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}