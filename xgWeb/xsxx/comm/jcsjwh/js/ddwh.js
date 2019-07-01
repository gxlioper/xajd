
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
/**
 * ���Ӵ�Ӵ���
 * @return
 */
function zjDdsj(){
	var url = "zjjcddwh.do?method=ddAdd";
	var title = "���Ӵ������";
	showDialog(title,380,200,url);
}

/**
 * �޸Ĵ�Ӵ���
 * @return
 */
function modDdsj(){
	var rows = jQuery("#dataTable").getSeletRow();
 	if (rows.length != 1){
 		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
 		return false;
 	} 
	var url = "zjjcddwh.do?method=ddUpdate&dddm="+rows[0]["dddm"];
	var title = "�޸Ĵ������";
	showDialog(title,380,200,url);
}

/**
 * ɾ��
 * @return
 */
function delDdsj(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	} 
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("zjjcddwh.do?method=ddDel",{dddm:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
}

/**
 * ����ά��
 * @return
 */
function qdwh(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ����ά���Ĵ�ӣ�");
		return false;
	} 
	var url = "zjjcddwh.do?method=qdwhList&dddm="+ids.toString();
	var title = "����ά��";
	showDialog(title,800,600,url);
}

/**
 * �������
 * @return
 */
function saveQd(){
	var ids = jQuery("#dataTable").getSeletRow();
	var dddm = jQuery("#dddm").val();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ������Ҫ���ӵ����ӣ�");
		return false;
	} 
	var qdAll = new Array();
	for(var i=0;i<ids.length;i++){
	  qdAll[i] = ids[i]["bjdm"];
	}
	jQuery.post("zjjcddwh.do?method=qdAdd",{dddm:dddm,qdAll:qdAll},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
			var api = frameElement.api, W = api.opener;
			W.jQuery("#dataTable").reloadGrid();
		},'json');
}

/**
 * ����ɾ��
 * @return
 */
function delQd(){
	var ids = jQuery("#dataTable").getSeletRow();
	var dddm = jQuery("#dddm").val();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ������Ҫ���ӵ����ӣ�");
		return false;
	} 
	var qdAll = new Array();
	for(var i=0;i<ids.length;i++){
	  qdAll[i] = ids[i]["bjdm"];
	}
	jQuery.post("zjjcddwh.do?method=qdDel",{dddm:dddm,qdAll:qdAll},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
			var api = frameElement.api, W = api.opener;
			W.jQuery("#dataTable").reloadGrid();
		},'json');
}


function setQd(cellValue, rowObject) {
	var dddm = rowObject.dddm;
	if (dddm == null) {
		dddm = "";
	}
	html = "<a  href='javascript:void(0);'  class='name' onclick='viewQd(\"" + dddm
			+ "\");return false;' >" + cellValue + "</a>";
	return html;
}

/**
 * ����ɾ��
 * @return
 */
function viewQd(ids){
	var url = "zjjcddwh.do?method=viewQd&dddm="+ids;
	var title = "����ά��";
	showDialog(title,800,600,url);
}