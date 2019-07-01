
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function saveForm(){
	
	var flg = true;
	
	jQuery.each(jQuery("#dataList tr"),function(i,n){
		if (i != 0 && flg){
			var fldm = jQuery(n).find("select[name=fldmArr] option:selected").val();
			var sbdm = jQuery(n).find("select[name=sbdmArr] option:selected").val();
			var jysj = jQuery(n).find("input[name=jysjArr]").val();
			
			flg = (fldm != "" && sbdm !="" && jysj !="");
		}
	});
	
	if (jQuery.trim(jQuery("#xh").val()) == "" || !flg){
		showAlert("�뽫��������д������");
		return false;
	}
	
	if (jQuery("#dataList tr").size() == 1){
		showAlert("����д���õĹ�����Ʒ��Ϣ��");
		return false;
	}
	
	var url = "xgygl_ggwpjydj.do?method=saveDj";
	ajaxSubFormWithFun("form",url,function(data){
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

function saveFormForUpdate(){
	if (jQuery("#xh").val() == "" || jQuery("#fldm").val() == "" || jQuery("#sbdm").val() == ""){
		showAlert("�뽫��������д������");
		return false;
	}
	
	var url = "xgygl_ggwpjydj.do?method=saveDjForUpdate&type=update";
	ajaxSubFormWithFun("ggwpjyForm",url,function(data){
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

function addRow(){
	var xh = jQuery("#xh").val();
	
	if (xh == ""){
		showAlert("����ѡ��ѧ����");
		return false;
	}
	jQuery("#dataList").append(jQuery("#dataList tr:eq(0)").clone().show());
}

function delRow(obj){
	jQuery(obj).parents("tr:eq(0)").remove();
}

function getSbxx(obj){
	
	var sbdmSelect = jQuery(obj).parents("tr:eq(0)").find("select[name=sbdmArr]");
	var fldm = jQuery("option:selected",jQuery(obj)).val();
	
	sbdmSelect.find("option").remove();
	
	if (fldm == ""){
		return false;
	}
	
	jQuery.getJSON("rcswSbglSbxx.do?method=getSbxxByFldm",{fldm:fldm},function(data){
		
		sbdmSelect.append("<option value=''></option>");
		
		var trs = jQuery("#dataList").find("tr");
		var sbdms = [];
		for(var i = 1;i<trs.length;i++){
			sbdms.push(jQuery(trs[i]).find("select[name=sbdmArr]").val());
		}
		jQuery.each(data,function(i,n){
			var option = jQuery("<option value='"+n["dm"]+"'>"+n["mc"]+"</option>");
			sbdmSelect.append(option);							
		});
		for(var j = 0;j<sbdms.length;j++){
			var options = jQuery(obj).parents("tr:eq(0)").find("select[name=sbdmArr] option");			
			for(var g = 1;g<options.length;g++){
				if(sbdms[j] == jQuery(options[g]).val()){
					jQuery(obj).parents("tr:eq(0)").find("select[name=sbdmArr]").find("option[value='"+sbdms[j]+"']").remove();
				}
			}							
		}
	});
}

function add() {
	var url = "xgygl_ggwpjydj.do?method=addGgwpjy";
	var title = "�Ǽ�";
	showDialog(title, 700, 450, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var url = 'xgygl_ggwpjydj.do?method=editGgwpjy&id=' + rows[0]["id"];
		var title = "�Ǽ��޸�";
		showDialog(title, 700, 450, url);
	}

}

// ɾ��
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ���ü�¼��",{"okFun":function(){
			jQuery.post("xgygl_ggwpjydj.do?method=delete",{ids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "xgygl_ggwpgl_jydj.do";//dcclbh,�������ܱ��

//���� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ggwpjydjExportData);
}

//��������
function ggwpjydjExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xgygl_ggwpjydj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
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

function getSbxxForUpdate(obj){
	
	var sbdmSelect = jQuery("#sbdm");
	var fldm = jQuery("option:selected",jQuery(obj)).val();
	
	sbdmSelect.find("option").remove();
	
	if (fldm == ""){
		return false;
	}
	
	jQuery.getJSON("rcswSbglSbxx.do?method=getSbxxByFldm",{fldm:fldm},function(data){
		
		sbdmSelect.append("<option value=''></option>");
		
		jQuery.each(data,function(i,n){
			var option = jQuery("<option value='"+n["dm"]+"'>"+n["mc"]+"</option>");
			sbdmSelect.append(option);
		});
	});
}

function ghsb(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	}
	var flag = true;
	jQuery.each(rows,function(i,n) {
		if(n['ghzt'] == '1') {
			flag = false;
			return false;						
			}			
		})
	if(!flag) {
		showAlertDivLayer("�ѹ黹��¼�������ٴι黹��");
		return false;
	}else {	
		tipsWindownNew("������Ʒ�黹","id:sbgh",520,320,"",{
			button:[
				{name:"����",focus: true,callback:function(){
					var ghbz = jQuery("#ghbz",this.content.document).val();
					var ghsj = jQuery("#ghsj",this.content.document).val();
					var ghjbr = jQuery("#ghjbr",this.content.document).val();
					
					jQuery.post("xgygl_ggwpjydj.do?method=sbgh",{'ghbz':ghbz,'ghsj':ghsj,'ghjbr':ghjbr,'ids':ids.toString()},function(data){
						searchRs();
					});
					
				}},
				{name:"ȡ��",callback:function(){
					
				}}
			]
		});
	}
}

function view(id){
	showDialog('�鿴',650,400,'xgygl_ggwpjydj.do?method=viewGgwpjy&id='+id);
}

function dr(){
	toImportDataNew("IMPORT_GGWPJYDJ");
	return false;
}
