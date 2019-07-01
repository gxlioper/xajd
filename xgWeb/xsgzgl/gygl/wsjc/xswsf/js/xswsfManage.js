jQuery(function(){
	var gridSetting = {
		caption : "ѧ��������",
		pager : "pager",
		url : "gyglnew_xswsf_12688.do?method=xswsfManage&type=query",
		colList : [
			{label:'guid',name:'guid',index :'guid',key:true,hidden:true},
			{label:'rcid',name:'rcid',index :'rcid',hidden:true},
			{label:'ѧ��',name:'xh',index:'xh',width:'10%', formatter : xhLink},
			{label:'����',name:'xm',index:'xm',width:'10%'},
			{label:'�꼶',name:'nj',index:'nj',width:'7%'},
			{label:'ѧԺ',name:'xymc',index:'xymc',width:'15%'},
			{label:'רҵ',name:'zymc',index:'zymc',width:'15%'},
			{label:'����ճ�',name:'jcrcxx',index:'jcrcxx',width:'20%'},
			{label:'����',name:'fs',index:'fs',width:'7%'}
		],
	}
	var map = getSuperSearch();
	gridSetting["params"] = map;
	jQuery("#dataTable").initGrid(gridSetting);
})
//ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewWmqsxs(\""+rowObject["guid"]+"\");'>"+cellValue+"</a>";
}
function viewWmqsxs(id){
	showDialog("�鿴ѧ����������Ϣ", 600, 450, "gyglnew_xswsf_12688.do?method=viewXswsf&guid="+id);
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function edit(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else {
		showDialog('ѧ���������޸�',600,450,'gyglnew_xswsf_12688.do?method=xswsfEdit&guid=' + rows[0]["guid"]);
	}
}
function save(){
	
	var fs = jQuery("#fs").val();
	if(fs == "" || fs==null){
		alertInfo("����д������");
		return false;
	}
	var url = "gyglnew_xswsf_12688.do?method=xswsfSave";
	ajaxSubFormWithFun("xswsfForm", url, function(data) {
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
		var fs = jQuery("#fs").val();
		if(fs != ""){
			fs = fs/2;
			jQuery("#fs").val(fs.toFixed(2))
		}
}

function printWmqs(){
	showDialog('����������������',350,200,'gyglnew_xswsf_12688.do?method=printWmqs');
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
	var url = "gyglnew_xswsf_12688.do?method=saveXsmd";
	ajaxSubFormWithFun("xswsfForm", url, function(data) {
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



var DCCLBH = "gyglnew_xswsf_xswsfgl.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportDatas);
}

//��������
function ExportDatas() {
	setSearchTj();//���ø߼���ѯ����
	var url = "gyglnew_xswsf_12688.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}