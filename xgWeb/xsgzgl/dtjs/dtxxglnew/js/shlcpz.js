var action="shlcpz.do";
var gridSetting = {
		caption:"��������б�",
		pager:"pager",
		url:"shlcpz.do?method=list&type=query",
		colList:[
		   {label:'�׶δ���',name:'jddm', index: 'jddm',key:true,hidden:true},
		   {label:'�׶�����',name:'jdmc', index: 'jdmc',width:'15%'},
		   {label:'�������id',name:'shlc', index: 'shlc',hidden:true},
		   {label:'�������',name:'lcxx', index: 'lcxx',width:'40%'},
		   {label:'�����뿪��',name:'ksqkgxx', index: 'ksqkgxx',width:'15%'},
		   {label:'��ֹʱ��',name:'qzsj', index: 'qzsj',width:'30%'}
		],
		sortname: "to_number(jddm)",
	 	sortorder: "asc"
	}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
function query(){
	var map = {};
	map["jdmc"]=jQuery("#jdmc").val();
	jQuery("#dataTable").reloadGrid(map);
}
//����
function add() {
		var url =action+"?method=add";
		var title = "���������������";
		showDialog(title, 600, 370, url);
		jQuery("#dataTable").reloadGrid();
}
function save(url,checkId,keyid){
	if(!check(checkId)){
		return alertError("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	if(!checkother()){
		return false;
	}
	var select=jQuery("input[name='ksqkg']:checked").val();
	if(select=="0"){
		jQuery("#ksqkssj").val("");
		jQuery("#ksqjssj").val("");
	}
	lock();
 	jQuery("#form").ajaxSubmit({
		url:url,
		type:"post",
		dataType:"json",
		success:function(data){
	 		 if(data["message"]=="����ɹ���"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
	    	 }else{
	    		 showAlert(data["message"]);
	    	 }
	 		 unlock();
		},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
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
//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlert("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = action+'?method=update&jddm=' + rows[0]["jddm"];
		var title = "�޸������������";
		showDialog(title, 600, 370, url);
		jQuery("#dataTable").reloadGrid();
	}
}
function checkother(){
	return true;
}
//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="����Ϊ<font color='red'>["+data["nodel"]+"]</font>";
						mes+="����ٹ����Ѿ���ʹ�ò���ɾ��!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
