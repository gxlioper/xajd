function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
function setCjbl(cellValue, rowObject) {
	var id = rowObject["id"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('"+id+"')\" class='name'>����</a>";
}
//�鿴��Ϣ
function ckxx(id) {
	var url = "dtjs_dxbmgl_dxpxglSz.do?id=" + id;
	var title = "�γ̷�����������";
	showDialog(title, 700, 250, url);
}
//����
function add() {
		var url ="dtjs_dxbmgl_dxpxglZj.do";
		var title = "���ӵ�У��ѵ��Ϣ";
		showDialog(title, 700, 260, url);
		jQuery("#dataTable").reloadGrid();
}
function formatStr(str){
	if(str==""||null==str){
		return "-1";
	}
	return str;
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
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var sjly=rows[0]["sjly"];
		if(sjly=="1"){
			showAlertDivLayer("������У��ѵ��Ϣ��<font color='red'>��ѧ������</font>,�����޸ģ�");
			return false;
		}
		var url = 'dtjs_dxbmgl_dxpxglXg.do?id=' + rows[0]["id"];
		var title = "�޸ĵ�У��ѵ��Ϣ";
		showDialog(title, 700, 250, url);
		jQuery("#dataTable").reloadGrid();
	}
}
//ɾ��
function del() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var ids='';
		var sjyls=0;
		var gxts=rows.length;
		for(var i=0;i<gxts;i++){
			if(rows[i]["sjly"]=='0'){
				ids+=rows[i]["id"];
			}else{
				sjyls++;
			}
		}
		if(sjyls==gxts){//��ѡ������ɾ��
			showAlertDivLayer("��ѡ��Ŀ�о���ѧ������,����ɾ����");
			return false;
		}else if(sjyls>0&&sjyls<gxts){
			showConfirmDivLayer("��ѡ��Ŀ�а���ѧ��������,ȷ��ֻɾ��δ�����ļ�¼��", {
				"okFun" : function() {
				jQuery.post("dtjs_dxbmgl_dxpxglSc.do", {values : ids}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data+"&nbsp;</font>������";
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
			});
		}else{
			showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
				"okFun" : function() {
				jQuery.post("dtjs_dxbmgl_dxpxglSc.do", {values : ids}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data+"&nbsp;</font>������";
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
			});
		}

	}
}