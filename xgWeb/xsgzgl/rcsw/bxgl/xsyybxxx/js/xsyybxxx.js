var action="rcsw_bxgl_xsyybx.do";
var title="";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
	//autoTitleForGrid();
	//setTitle();
}
function reload(){
	jQuery("#dataTable").reloadGrid();
	//autoTitleForGrid();
	//setTitle();
}
function btn_close(){

	iFClose();
}

//����
function add(){
	
	showDialog("��д",710,460,'rcsw_bxgl_xsyybx.do?method=yyxxAdd');
}
//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		
		showDialog("�޸�",710,460,'rcsw_bxgl_xsyybx.do?method=yyxxUpdate&yybxid='+rows[0]["yybxid"]+"&xh="+rows[0]["xh"],null);
	}
}
//ɾ��
function deletes(){

	var ids = jQuery("#dataTable").getSeletIds();
	
	if (ids.length == 0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		confirmInfo("��ȷ��Ҫɾ����Щ��¼��",function(tp){
			if(tp=="ok"){
				jQuery.post("rcsw_bxgl_xsyybx.do?method=yyxxDel",{values:ids.toString()},function(data){
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
		
	}
}
//ѧ�����Ӳ鿴
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='ckYyxx(\""+rowObject["yybxid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}

function ckYyxx(yybxid,cellValue){
	
	showDialog("�鿴",710,350,'rcsw_bxgl_xsyybx.do?method=ckYyxx&yybxid='+yybxid+"&xh="+cellValue,null);

}
function saveForm(method){
	if(yzForm()){
		var url = "rcsw_bxgl_xsyybx.do?method="+method+"&type=save";
		ajaxSubFormWithFun("demoForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
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
	var yysj = jQuery("#yysj").val();
	var blnr = jQuery("#blnr").val();
	if (!checkNull("xh-yysj-blnr")) {
		return false;
	} else if (blnr.length > 250) {
		showAlertDivLayer("��ע�������������" + 250 + ",��ȷ�ϣ�");
		return false;
	} else{
		result =true;
	}
	return result;
}

function exportConfig(){
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport("rcsw_bxgl_xsyybxxx.do", yyxxExportData);
}

//����
function yyxxExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_bxgl_xsyybx.do?method=yyxxExportData&dcclbh=" + "rcsw_bxgl_xsyybxxx.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}