 
var action = "fbgl.do";
var title = "�ְ����";
function searchRs() {
	var map = getSuperSearch();
	var fbzt = jQuery("#fbzt").val();
	if (fbzt != "") {
		map["fbzt"] = fbzt;
	}
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
// �л�
function selectTab(obj, bbzt) {
	if (bbzt == "wfb") {
		window.location.href="fbgl.do?method=list"; 
	} else {
		window.location.href="fbgl.do?method=yfblist"; 
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	// searchRs();
}
// �鿴����
function ckgz(cellValue, rowObject) {
	var pzgzid = rowObject["fbgz"];
	if(pzgzid==""||pzgzid==null||pzgzid=="null"){
		return "";
	}
	return "<a href='javascript:void(0);' onclick=\"ckpzgz('" + pzgzid
			+ "')\" class='name'>" + cellValue + "</a>";
}
// �鿴������Ϣ
function ckpzgz(pzgzid){
	var url ="fbglgzpztj.do?method=showView&pzgzid=" + pzgzid;
	var cktitle ="�鿴������Ϣ";
	showDialog(cktitle, 800, 500, url);
}
// �ְ�
function fb() {
	// û�зְ����
	var map = getSuperSearch();
	if (!isHaveGzxx("FBGZ")) {
		return showAlertDivLayer("�ְ����δ�趨��δ���ã�");
	}
	var ids = jQuery("#dataTable").getRowCount();
	if(ids<=0){
		return showAlertDivLayer("������ѧ����Ϣ��");
	}
	var url = action + "?method=fb";
	var title = "�ְ����";
	var ids = jQuery("#dataTable").getSeletIds();
	url += "&pk=" + ids.toString();
	showDialog(title, 500, 300, url);
}
//����ְ�
function saveFb() {
	var pzgzid = jQuery("#pzgzid").val();
	var pk = jQuery("#pk").val();
	if(jQuery("#wfbxs").val()=="0"){
		return showAlert("������δ�ְ�ѧ����");
	}
	lock();
	// ���ؽ�����
 	var nowValue=parseInt(jQuery("#yfb").text());
 	loadBar("fb"+pzgzid,function(data){
 		jQuery("#yfb").text(data.now+nowValue);
 		jQuery("#wfb").text(data.all-data.now);
 		if(data.finish&&data.message=="-1"){
 			showAlert("�ְ���ɣ�",{},{"clkFun":function(){
 				if (parent.window){
 					refershParent();
 				}
 			}});
 		}
 		if(data.message!="-1"){
 			showAlert(data.message,{},{"clkFun":function(){
 				if (parent.window){
 					refershParent();
 				}
 			}});
 			return false;
 		}
 		return true;
 	},100);
	jQuery.ajax( {
		url : "fbgl.do?method=createFb",
		data : {
			pzgzid : pzgzid,
			pk : pk
		},
		type : "post",
		dataType : "json",
		success : function(data) {
			unlock();
		}
	});
}
// ȡ���ְ�
function qxfb(){
	var ids = jQuery("#dataTable").getRowCount();
	if(ids<=0){
		return showAlertDivLayer("������ѧ����Ϣ��");
	}
		var fbzt=jQuery("#fbzt").val();
		var ids = jQuery("#dataTable").getSeletIds();
		var title="��ȷ��Ҫȡ��ѡ��ķְ��¼��";
		if (ids.length == 0) {
			title="��ȷ��Ҫȡ�����еķְ��¼��";
		}
		showConfirmDivLayer(title, {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString(),fbzt:fbzt
				}, function(data) {
					var mes="�ɹ�ȡ����<font color='green'>&nbsp;"+data["successDel"]+"&nbsp;</font>���ְ�";
					mes+="</br>";
					alertMessage(mes);
				}, 'json');
			}
		});
}
// �����ְ�
function tzbj(){
	var rows = jQuery("#dataTable").getSeletRow();
	var zydm="";
	jQuery.each(rows,function(i){
		//���Ϊ�ջ���zydm�������¼רҵ���루ͨ����
		if(zydm==""||rows[i]["zydm"]==zydm){
			zydm=rows[i]["zydm"];
		}else{//��ͬרҵ����
			zydm="-1";
			return false;
		}
	});
	if(zydm=="-1"){
		showAlertDivLayer("��ѡ��ͬһרҵ��ѧ����");
	}else{
		var ids = jQuery("#dataTable").getSeletIds();
		if (ids.length == 0) {
			showAlertDivLayer("��ѡ����Ҫ�����ķְ��¼��");
		} else {
			var url = action + "?method=tzbj&values="+ids.toString()+"&zydm="+zydm;
			showDialog("�ְ����", 428, 184, url);
		}
	}
}
// �������
function saveTb(){
	var yxxs=jQuery("#yxxs").val();
	var bjdm=jQuery("#bjdm").val();
	var bjmc=jQuery("#bjdm option:selected").text();
	var ids=jQuery("#ids").val();
	showConfirm("ȷ�Ͻ���<font color='red'>"+yxxs+"</font>��ѧ��������<font color='blue'>"+bjmc+"</font>����", {
		"okFun" : function() {
			jQuery.post(action+"?method=tzbj&type=save", {
				values : ids.toString(),bjdm:bjdm,bjmc:bjmc
			}, function(data) {
				var message;
				if(data["message"]=="true"){
					message="����ɹ���";
				}else{
					message="����ʧ�ܣ�";
				}
				showAlert(message, {}, {
					"clkFun" : function() {
						var api = frameElement.api;
						api.reload();
					}
				});
			}, 'json');
		}
	});
}