

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
	
}

function add(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var url = "gfjyqk_sq.do?method=add";
	var title = "����";
	showDialog(title,790,550,url);
	
}
function change(obj){
	if(obj.value==1){
		jQuery("#bydj").show();
		jQuery("#cjrw").hide();
		jQuery("#twfx").hide();
		jQuery("#pjpy").hide();
		jQuery("#jc").hide();
		jQuery("#cjhd").hide();
	}else if(obj.value==2){
		jQuery("#bydj").hide();
		jQuery("#cjrw").show();
		jQuery("#twfx").hide();
		jQuery("#pjpy").hide();
		jQuery("#jc").hide();
		jQuery("#cjhd").hide();
	}else if(obj.value==3){
		jQuery("#twfx").show();
		jQuery("#bydj").hide();
		jQuery("#cjrw").hide();
		jQuery("#pjpy").hide();
		jQuery("#jc").hide();
		jQuery("#cjhd").hide();
	}else if(obj.value==4){
		jQuery("#pjpy").show();
		jQuery("#twfx").hide();
		jQuery("#bydj").hide();
		jQuery("#cjrw").hide();
		jQuery("#jc").hide();
		jQuery("#cjhd").hide();
	}else if(obj.value==5){
		jQuery("#jc").show();
		jQuery("#pjpy").hide();
		jQuery("#twfx").hide();
		jQuery("#bydj").hide();
		jQuery("#cjrw").hide();
		jQuery("#cjhd").hide();
	}else if(obj.value==6){
		jQuery("#cjhd").show();
		jQuery("#jc").hide();
		jQuery("#pjpy").hide();
		jQuery("#twfx").hide();
		jQuery("#bydj").hide();
		jQuery("#cjrw").hide();
	}else{
		jQuery("#cjhd").hide();
		jQuery("#jc").hide();
		jQuery("#pjpy").hide();
		jQuery("#twfx").hide();
		jQuery("#bydj").hide();
		jQuery("#cjrw").hide();
	}
}
//������ʽ��֤
function postfixCheck(){
	var wjm=jQuery("#formfile").val();
	if(wjm==""||wjm==null){
		return true;
	}
	var wjms=wjm.split(".");
	var hz=",bmp,jpg,jpeg,gif,png,pdf,doc,BMP,JPG,JPEG,GIF,PNG,PDF,DOC";
	if(hz.indexOf(wjms[wjms.length-1])<0){
		return false;
	}
	return true;
}

//����--����ݸ���ύ
function save(url){
	var checkId ="xh-gfqkfl";
	if(jQuery("#gfqkfl").val()==1){
		checkId+="-bydjsj-bydjdd";
	}else if(jQuery("#gfqkfl").val()==2){
		checkId+="-cjrwsj-rwpzdw";
	}else if(jQuery("#gfqkfl").val()==3){
		checkId+="-twfxsj-twpzdw";
	}else if(jQuery("#gfqkfl").val()==4){
		checkId+="-pjpysj-pjpydw";
	}else if(jQuery("#gfqkfl").val()==5){
		checkId+="-jcsj-jcdw";
	}else if(jQuery("#gfqkfl").val()==6){
		checkId+="-cjhdsj-cjhddd";
	}
	if(!postfixCheck()){
		return showAlert("���ϴ�֧�ֵĸ�����ʽ��");
	}
	if(!check(checkId)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	
	ajaxSubFormWithFun("gfjysqForm", url, function(data) {
		if (data["message"] == "����ɹ���") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		}
	});
}


//�޸�--����ݸ���ύ
function saveXg(url){
	var checkId ="xh-gfqkfl";
	if(jQuery("#gfqkfl").val()==1){
		checkId+="-bydjsj-bydjdd";
	}else if(jQuery("#gfqkfl").val()==2){
		checkId+="-cjrwsj-rwpzdw";
	}else if(jQuery("#gfqkfl").val()==3){
		checkId+="-twfxsj-twpzdw";
	}else if(jQuery("#gfqkfl").val()==4){
		checkId+="-pjpysj-pjpydw";
	}else if(jQuery("#gfqkfl").val()==5){
		checkId+="-jcsj-jcdw";
	}else if(jQuery("#gfqkfl").val()==6){
		checkId+="-cjhdsj-cjhddd";
	}
	if(!postfixCheck()){
		return showAlert("���ϴ�֧�ֵĸ�����ʽ��");
	}
	if(!check(checkId)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	
	ajaxSubFormWithFun("gfjysqForm", url, function(data) {
		if (data["message"] == "����ɹ���") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		}
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






function update() {
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		
		var shzt = rows[0]["shzt"];
		if (shzt=='0'||shzt=='3'){
			var url = 'gfjyqk_sq.do?method=update&xh='+rows[0]["xh"]+'&sqid=' + rows[0]["sqid"];
			showDialog("�޸�", 720, 450, url);
		}else{
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
	}

}


//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("gfjyqk_sq.do?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="�������Ѿ��ύ����ɾ��!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}


function submit(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		if ("false" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if ('3'!=rows[0]["shzt"] && "false" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		var url = "gfjyqk_sq.do?method=submit";
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='3'&& "true"!= isopen){
				showAlertDivLayer("��ǰδ�������룬����ϵ����Ա��");
				return false;
			}
			if(rows[i]['shzt']!='0' && rows[i]['shzt']!='3' ){
				showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
			jQuery.post(url,
				{
				 values:ids.toString(),
				 xh : rows[0]['xh'],
				 splc : rows[0]['splc'],
				 shzt : rows[0]['shzt']
				},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function cancel(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length >1 ) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='5'){
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("gfjyqk_sq.do?method=cancel",
				{
				 values:ids.toString(),
				 splcid : rows[0]['splc'] 
				},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


function Lcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {	
		var shzt = rows[0]["shzt"];
		if ("0" == shzt){
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}	
		showDialog("������������������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}








function viewGfjyqk(sqid,xh) {
	showDialog("�鿴", 720, 520, "gfjyqk_sq.do?method=view&sqid=" + sqid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewGfjyqk(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}



var DCCLBH = "gfjy_gfjyqk_sq.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportData);
}

// ��������
function ExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "gfjyqk_sq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}