function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

var DCCLBH = "rcsw_wsbz_jg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, wsbzExportData);
}

//��������
function wsbzExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "wsbz_yy.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function add(){
	var flag = "";
	jQuery.post("wsbz_yy.do?method=isHaveQx",{type:'add',sqsj:''},function(data){
		if(data["message"] == '1'){
			var url = "wsbz_yy.do?method=add";
			var title = "ԤԼ����";
			showDialog(title, 700, 552, url);
		}else{
			showAlertDivLayer(data["message"]);
			return false;
		}
	},'json');
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	}
    var sqsj = rows[0]['yyrq'];
    var xh = rows[0]['xh'];
    var sqid = rows[0]['sqid'];
	jQuery.post("wsbz_yy.do?method=isHaveQx",{type:'update',sqsj:sqsj},function(data){
		if(data["message"] == '1'){
			var url = "wsbz_yy.do?method=udpate&sqid="+sqid+"&xh="+xh;
			var title = "ԤԼ�����޸�";
			showDialog(title, 700, 552, url);
		}else{
			showAlertDivLayer(data["message"]);
			return false;
		}
	},'json');
}
function fdmcOnchange(){
	jQuery("#fddm").change(function(){
	if(jQuery("#xh").val() == ""){
		jQuery("#fddm").val("");
		showAlert("����ѡ��ѧ����");
		return false;
	}
	var fddm = jQuery("#fddm").val();
	if(jQuery("#input").css('display') == 'block'){
		jQuery("#yyrq").val(jQuery("#yyrqzc").val());
	}else{
		jQuery("#yyrq").val(jQuery("#yyrqday").val());
	}
	var yyrq = jQuery("#yyrq").val();
	if(jQuery.trim(fddm) == ""){
		showAlert("�ֶӲ���Ϊ�գ�");
		return false;
	}
	jQuery.post("wsbz_yy.do?method=fdmcChange",{fddm:fddm,yyrq:yyrq},function(data){
		if(data['hdpl'] == '1'){
			jQuery("#input").show();
			jQuery("#select").hide();
			jQuery("#yyrq").val(jQuery("#yyrqday").val());
			jQuery("#rs").text(data['rs']);
			jQuery("#syrs").text(data['syrs']);
			//jQuery("#flag").val("t");
		}else{
			jQuery("#input").hide();
			jQuery("#select").show();
			jQuery("#yyrq").val(jQuery("#yyrqzc").val());
			jQuery("#rs").text(data['rs']);
			jQuery("#syrs").text(data['syrs']);
			if(data['syrsflag'] == '1'){
				jQuery("#syrs").text(data['syrs']);
			}
			//jQuery("#flag").val("z");
			
		}
		jQuery("#sj").text(data['sj']);
		jQuery("#dd").text(data['dd']);
		jQuery("#rs").text(data['rs']);
		jQuery("#gzzz").text(data['gzzz']);
		jQuery("#fwyq").text(data['fwyq']);
	},'json');})
}

function zcOnChange(){
	jQuery("#yyrqzc").change(function(){
		jQuery("#yyrq").val(jQuery("#yyrqzc").val());
		var syrs = "";
		var fddm = jQuery("#fddm").val();
		var yyrq = jQuery("#yyrq").val();
		if(jQuery.trim(fddm) == ""){
			showAlert("�ֶӲ���Ϊ�գ�");
			return false;
		}
		jQuery.post("wsbz_yy.do?method=zcChange",{fddm:fddm,yyrq:yyrq},function(data){
			jQuery("#syrs").text(data['syrs']);
		},'json');
	});
}

function dayOnChange(){
	jQuery("#yyrqday").change(function(){
		jQuery("#yyrq").val(jQuery("#yyrqday").val());
		var syrs = "";
		var fddm = jQuery("#fddm").val();
		var yyrq = jQuery("#yyrq").val();
		if(jQuery.trim(fddm) == ""){
			showAlert("�ֶӲ���Ϊ�գ�");
			return false;
		}
		jQuery.post("wsbz_yy.do?method=zcChange",{fddm:fddm,yyrq:yyrq},function(data){
			jQuery("#syrs").text(data['syrs']);
		},'json');
	});
}

function saveData(type){
	//var flag = jQuery("#flag").val();
	var ids = "xh"+"-"+"fddm"+"-"+"yyrq";
	if(!checkNotNull(ids)){
		showAlert("�뽫��������д������");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	var url = "wsbz_yy.do?method=saveData&type=" + type;
	ajaxSubFormWithFun("WsbzYyForm", url, function(data) {
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

function checkzs(){
	if(jQuery.trim(jQuery("#sqly").val()).length > 500){
		return false;
	}else{
		return true;
	}
}

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='jgView(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ��ajaxurl��ת
function jgView(sqbh, xh) {
	showDialog("ԤԼ�鿴", 700, 450, "wsbz_yy.do?method=ck&sqid="
			+ sqbh + "&xh=" + xh);
}

//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("wsbz_yy.do?method=del",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * ɾ����ѧ��
 * @return
 */
function delxs() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var sqsj = rows[0]['yyrq'];
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ����¼����ɾ����");
	} else {
	  jQuery.post("wsbz_yy.do?method=isHaveQxDel",{type:'del',sqsj:sqsj},function(data){
		if(data["message"] == 'true'){
			showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("wsbz_yy.do?method=del",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}else{
			showAlertDivLayer(data["message"]);
			return false;
		}
		
		},'json');
	 }
}



