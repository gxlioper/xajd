
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
	
}

function add(){
	showDialog("����",790,550,"rcpy_rcpyjg.do?method=zjRcpyjg");
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

//����
function save(url){
	var checkId ="xh-xmmc-pylb-khzb-xztj";
	if(!postfixCheck()){
		return showAlert("���ϴ�֧�ֵĸ�����ʽ��");
	}
	if(!check(checkId)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	
	ajaxSubFormWithFun("rcpyjgForm", url, function(data) {
		if (data["message"] == "����ɹ���") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		}
	});
}


//�޸�
function saveJg(url){
	var checkId ="xh-xmmc-pylb-khzb-xztj";
	if(!postfixCheck()){
		return showAlert("���ϴ�֧�ֵĸ�����ʽ��");
	}
	if(!check(checkId)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	
	ajaxSubFormWithFun("rcpyjgForm", url, function(data) {
		if (data["message"] == "����ɹ���") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		}
	});
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






function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	var sjly = rows[0]["sjly"];
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else if(sjly == '1'){
		showAlertDivLayer("��������ݣ������޸ģ�");
	}else {
		var url = 'rcpy_rcpyjg.do?method=xgRcpyjg&xh='+rows[0]["xh"]+'&jgid=' + rows[0]["jgid"];
		showDialog("�޸�", 790,550, url);
	}
}


//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("rcpy_rcpyjg.do?method=delRcpyjg", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="���������������ɾ��!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}


function ckRcpyjg(jgid,xh) {
	showDialog("�鿴", 720, 520, "rcpy_rcpyjg.do?method=ckRcpyjg&jgid=" + jgid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='ckRcpyjg(\""
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


function dr(){
	toImportDataNew("IMPORT_N712704_RCPYJG");
	return false;
}



var DCCLBH = "xsxx_rcpy_rcpyjg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportData);
}

// ��������
function ExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcpy_rcpyjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}