//��ѯ
function searchRs(){
	var map = {};
	jQuery("#dataTable").reloadGrid(map);
}

/*ȫѡ*/
function selectAll(obj){ 
	jQuery('input[type=checkbox]').attr('checked',jQuery(obj).attr('checked'));
}

/*����������ذ�ť����ֹʱ���������������*/
function displayQzsj(){
	var pjkg = jQuery(":radio:checked").val();
	if (pjkg == 1){
		//����
		jQuery("#qzsjTr").show();
		jQuery(".toolbox").css("display","block");
	} else {
		//�ر�
		jQuery("#qzsjTr").hide();
		jQuery(".toolbox").hide();
	}
}

jQuery(function(){
	displayQzsj();
	jQuery(":radio").bind("click",function(){
		saveCssz("pjkg",jQuery(this).val());
	});
	
	jQuery("#xn").bind("change",function(){
		jQuery.ajaxSetup({async:false});
		if (jQuery(this).val() == ""){
			return ;
		}
		saveCssz("xn",jQuery(this).val());
		refreshForm("xpjpy_jbsz_cssz.do");
	});
});

/*�����������*/
function saveCssz(zdKey,zdValue){
	if (zdValue != null){
		jQuery.post("xpjpy_cssz.do?method=saveCssz",{"zdKey":zdKey,"zdValue":zdValue},function(data){
			if(data["message"] != null){
				alert(data["message"]);
			}
		});
	}
}

/*�������������۲���Ŀ*/
function add(){
	var url = "xpjpy_cssz.do?method=addZcxm";
	var title = "�����۲���Ŀ";
	var zcxmCount = jQuery("#zcxmCount").val();
	if(zcxmCount > 0){
		showAlertDivLayer("�۲���Ŀ�ѱ�ʹ���޷����ӣ�");
		return false;
	}
	showDialog(title,440,360,url);
}

/*��Ŀ����radio����¼�*/
function changeZcxm(){
	var xmlx = jQuery("input[name='xmlx']:checked").val();
	if (xmlx == "0"){
		//�ȼ�
		jQuery("#zxfzTr").hide();//������С��ֵTR
		jQuery("#zxfz").val('');//��С��ֵ�ÿ�
		jQuery("#zdfzTr").hide();//��������ֵTR
		jQuery("#zdfz").val('');//����ֵ�ÿ�
		jQuery("#pxTr").hide();//��������TR
		jQuery("#px").val('');//�����ÿ�
		jQuery("#xmdjTable").show();
	}else{
		//��ֵ
		jQuery("#zxfzTr").show();
		jQuery("#zdfzTr").show();
		jQuery("#pxTr").show();
		jQuery("#xmdjTable").hide();
		jQuery("#tbody_zcxmLx").html(jQuery("#tbody_zcxmLx").html());
	}
}

/*�۲���Ŀ����_��Ŀ����_�ȼ���������*/
function addRow() {
	var html = "";
	var qqxsTrLen = jQuery("#tbody_zcxmLx tr").length;
	html += "<tr><td><input type='checkbox' id='checkbox_" + qqxsTrLen
			+ "'/></td>";
	html += "<td name='mc'><input type='text' maxlength='10' name='mc' id='mc'/></td>";
	html += "<td><input type='text' name='px' id='px' maxlength='3' onkeyup=\"value=value.replace(/[^\\d]/g,\'\');\"/></td></tr>";
	jQuery("#tbody_zcxmLx").append(html);
}

/*�۲���Ŀ����_��Ŀ����_�ȼ���ɾ����*/
function delRow() {
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length == 0) {
		showAlert("��ѡ����Ҫɾ�������ݣ�");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
		jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	jQuery('input[type=checkbox][name=th]').attr('checked', false);
}

/*���ӱ���*/
function saveForAdd(){
	var zxfz = jQuery("#zxfz").val();
	var zdfz = jQuery("#zdfz").val();
	if (check()){
		djToJson();// ��װ������Ϣ
		if(parseInt(zxfz)> parseInt(zdfz)){
			showAlert("��С��ֵ���ܴ�������ֵ��");
			return false;
		}
		var url = "xpjpy_cssz.do?method=saveForAdd";
		ajaxSubFormWithFun("zjdxCsszForm", url, function(data) {
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
}

/*����ʱ����*/
function check() {
	var flag=true;
	var checkId = 'xmmc-fjdm';
	var id = checkId.split("-");
	for ( var i = 0; i < id.length; i++) {
		var lddm = jQuery("#" + id[i]).val();
		if (lddm == null || "" == lddm) {
			flag= false;
		}
	}
	var xmlx=jQuery("input[name='xmlx']:checked").val();
	if(xmlx == ''||xmlx == null){
		flag=false;
	}
	if("0" == xmlx){
		jQuery.each(jQuery("#tbody_zcxmLx tr"), function(i, n) {
			var mc = jQuery(n).find("input[name=mc]").val();
			if(mc==null||mc==""){
				flag=false;
			}
		});
	}
	if("1" == xmlx){
		if(jQuery("#zxfz").val()==''){
			flag=false;
		}
		if( jQuery("#zdfz").val()==''){
			flag=false;
		}
	}
	if(!flag){
		showAlert("�뽫��*����д������");
		flag= false;
		return flag;
	}
	return flag;
}


//��װ����ȼ��¼�ֵ
function djToJson() {
	var xmlx = jQuery("input[name='xmlx']:checked").val();
	if ("0" == xmlx) {
		var objArr = [];
		jQuery.each(jQuery("#tbody_zcxmLx tr"), function(i, n) {
			var obj = {};
			var mc = jQuery(n).find("input[name=mc]").val();
			var px = jQuery(n).find("input[name=px]").val();
			obj.mc = mc;
			obj.px = px;
			objArr.push(obj);
		});
		var objStr = JSON.stringify(objArr);
		jQuery("#objStr").val(objStr);
	}
}

function del(){
//	var len = jQuery("input:[name='nextOne']:checked").length;//ѡ��CheckBox�ĸ���
//	var trLen = jQuery("input:[name='nextOne']").length;//���ж���ΪnextOne���ܸ���
//	var tr = document.getElementsByName("nextOne");//ѡ������name="'nextOne'"�Ķ��󣬷�������
//	var ids = "";
//	if(len == 0 ){
//		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
//		return false;
//	}
//	for(i=0;i<trLen;i++){
//		if(tr[i].checked == true){
//			ids+=tr[i].value+",";
//		 }
//	}
//	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
//		jQuery.post("xpjpy_cssz.do?method=delZcxm",{values:ids.toString()},function(data){
//			showAlertDivLayer(data["message"]);
//			refreshForm("xpjpy_jbsz_cssz.do");
//		},'json');
//	}});
	var zcxmCount = jQuery("#zcxmCount").val();
	if(zcxmCount > 0){
		showAlertDivLayer("�۲���Ŀ�ѱ�ʹ���޷�ɾ����");
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("xpjpy_cssz.do?method=delZcxm",{values:ids.toString()},function(data){
			alertInfo(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	}
}

/*
 *�޸ķ���
 */
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	var zcxmCount = jQuery("#zcxmCount").val();
	if(zcxmCount > 0){
		showAlertDivLayer("�۲���Ŀ�ѱ�ʹ���޷��޸ģ�");
		return false;
	}
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	}else{
		var url = 'xpjpy_cssz.do?method=updateZcxm&xmdm='+rows[0]["xmdm"];
		var title = "�޸��۲���Ŀ";
		showDialog(title,440,360,url);
	}
}

/*�޸ı���*/
function saveForUpdate(){
	var zxfz = jQuery("#zxfz").val();
	var zdfz = jQuery("#zdfz").val();
	if (check()){
		djToJson();// ��װ������Ϣ
		if(parseInt(zxfz)> parseInt(zdfz)){
			showAlert("��С��ֵ���ܴ�������ֵ��");
			return false;
		}
		var url = "xpjpy_cssz.do?method=saveForUpdate";
		ajaxSubFormWithFun("zjdxCsszForm", url, function(data) {
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
}
