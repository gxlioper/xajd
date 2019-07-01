//��������ת
function dcmcLink(cellValue, rowObject) {
	var gypyid = rowObject["gypyid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + gypyid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//�鿴��Ϣ
function ckxx(gypyid) {
	var url = "gypy.do?method=detail&gypyid=" + gypyid;
	var title = "����ѧ����Ϣ";
	showDialog(title, 650, 330, url);
}
function add() {
	var url = "gypy.do?method=add&pylx=3";
	var title = "��������ѧ��";
	showDialog(title, 650, 330, url);
	jQuery("#dataTable").reloadGrid();
}
//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlert("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'gypy.do?method=update&gypyid=' + rows[0]["gypyid"];
		var title = "�޸����㸨��Ա";
		showDialog(title,  650, 330,  url);
		//jQuery("#dataTable").reloadGrid();
	}
}
//ɾ��
function del() {
	var rows=jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlert("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("gypy.do?method=del", {
					values : ids.toString()
				}, function(data) {
					showAlert(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
function checkChar(obj){
	if (obj.value.match(/[\u4e00-\u9fa5]/g)) {
		alertInfo("ֻ�������ַ�,������������!",function (){obj.value="";});

		//return false;
	 }	
}
function save(url,checkId){
	if(!check(checkId)){
		return alertError("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	if(!checkData("�Ѿ����ڴ�����ѧ����")){
		return false;
	}
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
		},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});	
}
//��ⳤ��
function checkLength(obj,len){
	var str=obj.value;
     	if(str.replace(/[^\u0000-\u00ff]/g, "**").length > len){	         
     		alertError("����ܴ���"+len+"���ַ���");
      		 return false;
   		 }
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
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
function exportConfig() {
	customExport("gypyyxxs.do", exportData);
}
// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "gypy.do?method=export&pylx=3&dcclbh=" + "gypyyxxs.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
/**
 * ��֤��������ʾ
 * @param gypyid ��֤����id
 * @param mes	��ʾ��Ϣ
 * @return
 */
function checkData(mes){
	var isCanDo=true;
	if(mes==""||null==mes){
		mes="�Ѿ��������ݣ�";
	}
 	jQuery("#form").ajaxSubmit({
		url:"gypy.do?method=checkData",
		type:"post",
		async:false,
		dataType:"json",
		success:function(data){
	 		if(data["message"]=="false"||data["message"]==false){
				showAlert(mes);
				isCanDo=false;
			}
		},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});	
 	return isCanDo;
}