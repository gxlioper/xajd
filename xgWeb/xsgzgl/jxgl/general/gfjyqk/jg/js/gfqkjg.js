

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
	
}

function add(){
	showDialog("����",790,550,"gfjyqk_jg.do?method=add");
}
function change(obj){
	if(obj.value==1){
		jQuery("#bydj").show();
		jQuery("#cjrw").hide();
		jQuery("#twfx").hide();
		jQuery("#pjpy").hide();
		jQuery("#jc").hide();
		jQuery("#cjhd").hide();
	}else if(obj.value==2){
		jQuery("#bydj").hide();
		jQuery("#cjrw").show();
		jQuery("#twfx").hide();
		jQuery("#pjpy").hide();
		jQuery("#jc").hide();
		jQuery("#cjhd").hide();
	}else if(obj.value==3){
		jQuery("#twfx").show();
		jQuery("#bydj").hide();
		jQuery("#cjrw").hide();
		jQuery("#pjpy").hide();
		jQuery("#jc").hide();
		jQuery("#cjhd").hide();
	}else if(obj.value==4){
		jQuery("#pjpy").show();
		jQuery("#twfx").hide();
		jQuery("#bydj").hide();
		jQuery("#cjrw").hide();
		jQuery("#jc").hide();
		jQuery("#cjhd").hide();
	}else if(obj.value==5){
		jQuery("#jc").show();
		jQuery("#pjpy").hide();
		jQuery("#twfx").hide();
		jQuery("#bydj").hide();
		jQuery("#cjrw").hide();
		jQuery("#cjhd").hide();
	}else if(obj.value==6){
		jQuery("#cjhd").show();
		jQuery("#jc").hide();
		jQuery("#pjpy").hide();
		jQuery("#twfx").hide();
		jQuery("#bydj").hide();
		jQuery("#cjrw").hide();
	}else{
		jQuery("#cjhd").hide();
		jQuery("#jc").hide();
		jQuery("#pjpy").hide();
		jQuery("#twfx").hide();
		jQuery("#bydj").hide();
		jQuery("#cjrw").hide();
	}
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
	var checkId ="xh-gfqkfl";
	if(jQuery("#gfqkfl").val()==1){
		checkId+="-bydjsj-bydjdd";
	}else if(jQuery("#gfqkfl").val()==2){
		checkId+="-cjrwsj-rwpzdw";
	}else if(jQuery("#gfqkfl").val()==3){
		checkId+="-twfxsj-twpzdw";
	}else if(jQuery("#gfqkfl").val()==4){
		checkId+="-pjpysj-pjpydw";
	}else if(jQuery("#gfqkfl").val()==5){
		checkId+="-jcsj-jcdw";
	}else if(jQuery("#gfqkfl").val()==6){
		checkId+="-cjhdsj-cjhddd";
	}
	if(!postfixCheck()){
		return showAlert("���ϴ�֧�ֵĸ�����ʽ��");
	}
	if(!check(checkId)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	
	ajaxSubFormWithFun("gfjyjgForm", url, function(data) {
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
	var checkId ="xh-gfqkfl";
	if(jQuery("#gfqkfl").val()==1){
		checkId+="-bydjsj-bydjdd";
	}else if(jQuery("#gfqkfl").val()==2){
		checkId+="-cjrwsj-rwpzdw";
	}else if(jQuery("#gfqkfl").val()==3){
		checkId+="-twfxsj-twpzdw";
	}else if(jQuery("#gfqkfl").val()==4){
		checkId+="-pjpysj-pjpydw";
	}else if(jQuery("#gfqkfl").val()==5){
		checkId+="-jcsj-jcdw";
	}else if(jQuery("#gfqkfl").val()==6){
		checkId+="-cjhdsj-cjhddd";
	}
	if(!postfixCheck()){
		return showAlert("���ϴ�֧�ֵĸ�����ʽ��");
	}
	if(!check(checkId)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	
	ajaxSubFormWithFun("gfjyjgForm", url, function(data) {
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
			var url = 'gfjyqk_jg.do?method=update&xh='+rows[0]["xh"]+'&jgid=' + rows[0]["jgid"];
			showDialog("�޸�", 720, 450, url);
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
				jQuery.post("gfjyqk_jg.do?method=del", {
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


function viewGfjyqk(jgid,xh) {
	showDialog("�鿴", 720, 520, "gfjyqk_jg.do?method=view&jgid=" + jgid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewGfjyqk(\""
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


function dr(){
	toImportDataNew("IMPORT_N450604_GFJYQK");
	return false;
}













var DCCLBH = "gfjy_gfjyqk_jg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportData);
}

// ��������
function ExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "gfjyqk_jg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}