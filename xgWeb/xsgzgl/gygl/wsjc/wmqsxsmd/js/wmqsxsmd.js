jQuery(function(){
	var gridSetting = {
		caption : "ѧ��������",
		pager : "pager",
		url : "gyglnew_wmqsxsmd_12688.do?method=wmqsxsmdManage&type=query",
		colList : [
			{label:'id',name:'id',index :'id',key:true,hidden:true},
			{label:'ѧ��',name:'xh',index:'xh',width:'10%', formatter : xhLink},
			{label:'����',name:'xm',index:'xm',width:'10%'},
			{label:'�꼶',name:'nj',index:'nj',width:'5%'},
			{label:'ѧԺ',name:'xymc',index:'xymc',width:'15%'},
			{label:'רҵ',name:'zymc',index:'zymc',width:'15%'},
			{label:'�༶',name:'bjmc',index:'bjmc',width:'15%'},
			{label:'У������',name:'xjfs',index:'xjfs',width:'7%', formatter : xjfsLink},
			{label:'Ժ������',name:'yjfs',index:'yjfs',width:'7%', formatter : yjfsLink},
			{label:'����ѧ��',name:'xn',index:'xn',width:'8%'},
			{label:'����ѧ��',name:'xqmc',index:'xqmc',width:'7%'}
		],
	}
	var map = getSuperSearch();
	gridSetting["params"] = map;
	jQuery("#dataTable").initGrid(gridSetting);
})
//ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewWmqsxs(\""+rowObject["id"]+"\");'>"+cellValue+"</a>";
}
function yjfsLink(cellValue, rowObject) {
	var temp = cellValue.split(".");
	if(temp.length == 1){
		cellValue += ".00"
	}
	return cellValue;
}
function xjfsLink(cellValue, rowObject) {
	var temp = cellValue.split(".");
	if(temp.length == 1){
		cellValue += ".00"
	}
	return cellValue;
}
function viewWmqsxs(id){
	showDialog("�鿴��������ѧ����Ϣ", 700, 450, "gyglnew_wmqsxsmd_12688.do?method=viewWmqsxsmd&id="+id);
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function add(){
	showDialog('��������ѧ������',600,450,'gyglnew_wmqsxsmd_12688.do?method=wmqsxsmdAdd');
}

function edit(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog('ѧ���������޸�',600,450,'gyglnew_wmqsxsmd_12688.do?method=wmqsxsmdEdit&id=' + rows[0]["id"]);
	}
}
function save(type){
	var xh = jQuery("#xh").val();
	var yjfs = jQuery("#yjfs").val();
	var xjfs = jQuery("#xjfs").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	if("add" == type){
		if(xh == ""){
			alertInfo("��ѡ��ѧ�ţ�");
			return false;
		}
		if(xn == "" || xn==null){
			alertInfo("��ѡ��ѧ�꣡");
			return false;
		}
		if(xq == "" || xq==null){
			alertInfo("��ѡ��ѧ�ڣ�");
			return false;
		}
	}
	if(yjfs == "" || yjfs==null){
		alertInfo("����дԺ��������");
		return false;
	}
	if(xjfs == "" || xjfs==null){
		alertInfo("����дУ��������");
		return false;
	}
	
	var url = "gyglnew_wmqsxsmd_12688.do?method=wmqsxsmdSave&type="+type;
	ajaxSubFormWithFun("wmqsxsmdForm", url, function(data) {
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
function changeFs(obj){
		var fs = jQuery(obj).val();
		if(fs != ""){
			fs = fs/2;
			jQuery(obj).val(fs.toFixed(2))
		}
}

function printWmqs(){
	showDialog('����������������',350,200,'gyglnew_wmqsxsmd_12688.do?method=printWmqs');
}
function saveXsmd(){
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	if(xn == ""){
		alertInfo("��ѡ��ѧ�꣡");
		return false;
	}
	if(xq == ""){
		alertInfo("��ѡ��ѧ�ڣ�");
		return false;
	}
	var url = "gyglnew_wmqsxsmd_12688.do?method=saveXsmd";
	ajaxSubFormWithFun("wmqsxsmdForm", url, function(data) {
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



var DCCLBH = "gyglnew_wmqsxsmd_wmqsxsmdgl.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportDatas);
}

//��������
function ExportDatas() {
	setSearchTj();//���ø߼���ѯ����
	var url = "gyglnew_wmqsxsmd_12688.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}