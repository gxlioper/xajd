
var DCCLBH = "sspy_sq.do";//dcclbh,�������ܱ��
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function getSscyxx(){
	jQuery("#xsList").empty();
	var ldqsxx=jQuery('#lddm').val()+jQuery('#qsh').val();
	var html = "";
	jQuery.post("sspysq.do?method=getCwxx", {
		ldqsxx : ldqsxx
	}, function(data) {
		for(var i =0;i<data.length;i++){
			html+="<tr><td align='center'>"+data[i]["xh"]+"</td><td align='center'>"+data[i]["xm"]+"</td><td align='center'>"+data[i]["xsbjmc"]+"</td><td align='center'>"+data[i]["cwh"]+"</td></tr>";
		}
		jQuery("#xsList").append(html);
	}, 'json');
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
	var url = "sspysq.do?method=add";
	var title = "����";
	showDialog(title,790,550,url);
	
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

/**
 * ���Һ�����
 * @return
 */
function qshLink(cellValue, rowObject){
	return "<a hrer='javascript:void(0);' class='name' onclick='sspysqView(\""+rowObject["sqid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}

function sspysqView(sqid){
	var url = "sspysq.do?method=view&sqid="+sqid;
	var title = "������������鿴";
	showDialog(title,790,550,url);
}
//����--����ݸ���ύ
function save(url){
	var checkId ="pyxmdm-sqly";
	if(!postfixCheck()){
		return showAlert("���ϴ�֧�ֵĸ�����ʽ��");
	}
	if(!check(checkId)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	ajaxSubFormWithFun("sspysqForm", url, function(data) {
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
	var checkId ="pyxmdm-sqly";
	if(!postfixCheck()){
		return showAlert("���ϴ�֧�ֵĸ�����ʽ��");
	}
	if(!check(checkId)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	ajaxSubFormWithFun("sspysqForm", url, function(data) {
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
			var url = 'sspysq.do?method=update&xh='+rows[0]["xh"]+'&sqid=' + rows[0]["sqid"];
			showDialog("�޸�", 790, 550, url);
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
				jQuery.post("sspysq.do?method=del", {
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

//�ύ
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
		var url = "sspysq.do?method=submit";
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
			jQuery.post("sspysq.do?method=cancel",
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
		showDialog("���������������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}










//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportData);
}

// ��������
function ExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "sspysq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}