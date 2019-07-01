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


// �鿴ѧ���춯��Ϣ
function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='xjydXsInfoCk(\""+row["xh"]+"\")'>"+cellValue+"</a>";
}

function xyfmt(cellValue,row){
	if("12866"==jQuery("#xxdm").val()){
		var str = cellValue;
		if(str.length > 8){
			str = str.substring(0,8)+"...";
		}
		return "<span title='"+cellValue+"'>"+str+"</span>";
	}
	return cellValue;
}


//�鿴ѧ���춯��Ϣ
function xjydXsInfoCk(xh){
	showDialog("�鿴ѧ��ѧ���춯��Ϣ",800, 500,"xjydjg.do?method=xjydXsInfoCk&xh="+xh,null);
}
//�鿴����ѧ���춯��Ϣ
function ckYdjg(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlert("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	}  else{
		showDialog("ѧ���춯��Ϣ",800, 500,'xjydjg.do?method=xjydjgCk&xjydjgid='+rows[0]["xjydjgid"]);
	}
}

//����
function addYdjg() {
		var url ="xjydjg.do?method=xjydjgAdd";
		var title = "ѧ���춯����";
		showDialog(title, 800, 500, url);
}

//�޸�
function updYdjg() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlert("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}  else{
		var sjly = rows[0]["sjly"];
		if(sjly=="1"){
			showAlert("�������ݲ����޸ģ�");
			return false;
		}
		showDialog("ѧ���춯��Ϣ",810, 500,'xjydjg.do?method=xjydjgUpd&xjydjgid='+rows[0]["xjydjgid"]);
	}
}

function delYdjg(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlert("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("�������ݲ���ɾ����");
				return false;
			}
		}
		
		confirmInfo("��ȷ��Ҫɾ��"+ids.length +"����¼��?",function(ty){
			if(ty=="ok"){
				jQuery.post("xjydjg.do?method=xjydjgDel",{values:ids.toString()},function(data){
					showAlert(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
	}
}


//��ѯ
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function exportConfig() {
	customExport("xjyd_xjydjg.do", exportData,690,500);
}
// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xjydjg.do?method=exportData&dcclbh=xjyd_xjydjg.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ��ʾѧ���Ͱ༶��Ϣ
 * @return
 */
function initShow(){
	var xxdm = jQuery("#xxdm").val();
	var xjlbdm = jQuery("#ydlbdm").val();
	if(xjlbdm == ""){
		jQuery("#xjlbmc").html("");
		jQuery("#sfyxj").html("");
		jQuery("#sfzx").html("");
		jQuery("#tzbj").hide();
		jQuery("#lrqzsj").hide();
		jQuery("#xzpd").hide();
		jQuery("#xxpd").hide();
	}else{
		jQuery.post("xjyd.do?method=xjydlbShpzGet",{values:xjlbdm},function(data){
			if(data != null){
				jQuery("#xjlbmc").html(data["xjlbmc"]);
				jQuery("#sfyxj").html(data["sfyxjmc"]);
				jQuery("#sfzx").html(data["sfzxmc"]);

				if(data["lrqzsj"]=='1'){
					jQuery("#lrqzsj").show();
				}else{
					jQuery("#lrqzsj").hide();
					jQuery("#sqkssj").val("");
					jQuery("#sqjssj").val("");
					
				}
				
				if(data["sftjbj"]=='0'){
					jQuery("#tzbj").show();
				}else{
					jQuery("#tzbj").hide();
					jQuery("#nj").val("");
					jQuery("#xydm").val("");
					jQuery("#zydm").val("");
					jQuery("#bjdm").val("");
				}		
				if("10511" == xxdm) {
					if(data["xzsfkq"]=='1'){
						jQuery("#xzpd").show();
					}else{
						jQuery("#xzpd").hide();
						jQuery("#xz").val("");
					}
					if(data["xxsfkq"]=='1') {
						jQuery("#xxpd").show();
					}else{
						jQuery("#xxpd").hide();
						jQuery("#lyqxxxdm").val("");				
					}
				}
			}
		},'json');
	}
}

/**
 * ����ѧ���춯
 * @return
 */
function plXjyd(){
	showDialog("����ѧ���춯",800, 570,'xjydjg.do?method=xjydPlcl');
}

function selectStudent(){
	var path = jQuery("#path").val();	
	var xzxsKey = jQuery("#xzxsKey").val();
	showDialog("����ѡ��ѧ��",800, 600,'xsxx_xsgl.do?method=selectStudentsNew&goto='+path+'&xzxsKey='+xzxsKey);
}