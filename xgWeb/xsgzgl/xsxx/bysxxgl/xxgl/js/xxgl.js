var action="xsxx_bysxx_xxgl.do";
var gndm = "xsxx_update";

jQuery(function() {
	onShow();
	xsGkPic();
});
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
//��������ת
function dcmcLink(cellValue, rowObject) {
	var xh = rowObject.xh;
	if (xh == null) {
		xh = "";
	}
	cellValue = "<a  href='javascript:void(0);'  class='name'  onclick='bysXxCk(\""
			+ xh
			+ "\" );return false;' >" + xh + "</a>";
	return cellValue;
}
//����ʦ���߿���Ƭ���Ի�
function xsGkPic() {
	if ("10511" != jQuery("#xxdm").val()) {
		jQuery("#addGkPic").css("display", "none");
		jQuery("#stuGkImg").css("display", "none");
		jQuery("#gkzpscbtn").css("display", "none");
	}

}
//�鿴��Ϣ
function bysXxCk(xh) {
	
	var url = action+"?method=bysXxCk&xh=" + xh;
	var title = "��ҵ����ϸ��Ϣ";
	showDialog(title, 850, 550, url);
}
function onShow() {
	var url = "xsxx_bysxx_xxgl.do?method=bysXxXg&type=query";
	var xxdm = jQuery("#xxdm").val();
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			xh : jQuery("#xh").val()
		},
		dataType : "json",
		success : function(data) {
			var xm = data.xm;
			jQuery("#xmView").html(xm);
			zdybdInit(gndm, data);
		}
	});
}
//����
function bysXxAdd() {
	showDialog('���ɱ�ҵ��',800,500,'xsxx_bysxx_xxgl.do?method=showStudents');
}

function bysXxSave(){
	var rows = jQuery("#dataTable").getSeletRow();	
	var counts = jQuery("#rowConut").html();
	if (rows.length == 0){
		var map = getSuperSearch();
		var url = "xsxx_bysxx_xxgl.do?method=bysXxAdd";			
		url +="&selected=all";
		
		confirmInfo("��ȷ��Ҫѡ��<font color='red'>"+counts+"</font>����¼��?",function(ty){
			if(ty=="ok"){
				jQuery.post(url,map,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						  if (parent.window){
						     refershParent();
						  }
					 }});
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});	

	}else {
		var ids = jQuery("#dataTable").getSeletIds();	 
		jQuery.post(action+"?method=bysXxAdd", {
			values : ids.toString()
		}, function(data) {
			  showAlert(data["message"],{},{"clkFun":function(){
				  if (parent.window){
				     refershParent();
				  }
			 }});
		},"json");
	}

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
			return false;
		}
	}
	return true;
}
//�޸�
function bysXxEdit() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_bysxx_xxgl.do?method=bysXxXg";
		url += "&xh=" + ids;
		showDialog("��ҵ����Ϣ�޸�", 850, 550, url);
	} else {
		showAlertDivLayer("��<font color='blue'>��ѡһ��</font>��ϣ���޸ĵļ�¼��");
		return false;
	}
}
function saveForm() {
	if (!zdybdCheck()) {
		return;
	}

	var sfbt = jQuery('#zpsfbt').val();
	var sfcz = jQuery('#zpsfcz').val();
	if (sfbt == "y" && "false" == sfcz) {
		alertError("�����ϴ�һ����Ƭ��")
		return false;
	}
	initParam();
	var url = "xsxx_bysxx_xxgl.do?method=bysXxXgBc";
	ajaxSubFormWithFun("form1", url, function(data) {
		if (data["success"] != undefined && !data["success"]) {
			showAlert(data["message"]);
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function(tag) {
					if (tag == "ok") {
						refershParent();
					}
				}
			});
		}
	});

}
//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
function rcxwshLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("����������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['qjsqid']+"&splc="+rows[0]['splcid']);
	}
}
//���صǼǱ�
function printByjdb(url){
	var xh="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("������ѡ��һ����¼��");
	}
	else if (rows.length == 1) {
		
		url += "&xh=" + rows[0]["xh"];
		window.open(url);
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				xh +=rows[i]["xh"];
			}else{
				xh +=rows[i]["xh"]+",";
			}
		}
		var url = "xsxx_bysxx_xxgl.do?method=printByjdbZip" + "&xh=" +xh;
		window.open(url);
	}
}

//ɾ��
function bysXxDel() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();	
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xsxx_bysxx_xxgl.do?method=bysXxDel", {
					values : ids.toString()
				},
						function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}


