 
function searchRs() {
		var map = getSuperSearch();
		var xhzt = jQuery("#xhzt").val();
	if (xhzt != "") {
		map["xhzt"] = xhzt;
	}
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
// �л�
function selectTab(obj, xhzt) {
	if (xhzt == "wbxh") {
		window.location.href="fbglbxh.do?method=list"; 
	} else {
		window.location.href="fbglbxh.do?method=jglist"; 
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}
function ckgz(cellValue, rowObject) {
	var pzgzid = rowObject["bxhgz"];
	if(pzgzid==""||pzgzid==null||pzgzid=="null"){
		return "";
	}
	return "<a href='javascript:void(0);' onclick=\"ckpzgz('" + pzgzid
			+ "')\" class='name'>" + cellValue + "</a>";
}
function ckpzgz(pzgzid){
	var url ="fbglgzpztj.do?method=showView&pzgzid=" + pzgzid;
	var cktitle ="�鿴������Ϣ";
	showDialog(cktitle, 800, 500, url);
}
function scxh(xhzt) {
	//alert(convertDate("20130927"));
	//�����ڶ�Ӧ��ѧ�Ź�����Ϣ
	if (!isHaveGzxx("BXHGZ")) {
		return showAlertDivLayer("��ѧ�Ź���δ�趨��δ���ã�");
	}
	var ids = jQuery("#dataTable").getRowCount();
	if(ids<=0){
		return showAlertDivLayer("������ѧ����Ϣ��");
	}
	var url ="fbglbxh.do?method=scxh&bxhzt="+xhzt;
	//����ǽ���б�����
	if(xhzt!="wbxh"){
		 url ="fbglbxh.do?method=jgscxh&bxhzt="+xhzt;
	}
	var title = "��ѧ�Ź���";
	var ids = jQuery("#dataTable").getSeletIds();
	url += "&pk=" + ids.toString();
	showDialog(title, 500, 280, url);
}
/**
 * ����ѧ�ű���
 * @return
 */
function scxhSave(){
	var pzgzid=jQuery("#pzgzid").val();
	var bxhzt=jQuery("#bxhzt").val();
	var pk=jQuery("#pk").val();
	var barkey="scxh"+pk;
	var wbxh=jQuery("#wbxh").text();
	if(jQuery.trim(wbxh)=="0"){
		return showAlert("������δ��ѧ��ѧ����");
	}
	lock();
 	jQuery.ajax({
		url:"fbglbxh.do?method=scxh&type=save",
		data:{pzgzid:pzgzid,pk:pk,barkey:barkey,bxhzt:bxhzt},
		type:"post",
		dataType:"json",
		success:function(data){
			if(null!=data["message"]&&"null"!=data["message"]){
				showAlert(data["message"],{},{"clkFun":function(){
	 				if (parent.window){
	 					refershParent();
	 				}
	 			}});
			}
		}
	});	
 	var nowValue=parseInt(jQuery("#ybxh").text());
 	//���ؽ�����
 	loadBar(barkey,function(data){
 		jQuery("#ybxh").text(data.now+nowValue);
 		jQuery("#wbxh").text(data.all-data.now);
 		if(data.finish){
 			showAlert("��ѧ�����,��������δ�ְ�ѧ�����򲻱�ѧ�ţ�",{},{"clkFun":function(){
 				if (parent.window){
 					refershParent();
 				}
 			}});
 		}
 		return true;
 	});
}
//ɾ��
function delxh(){
	var ids = jQuery("#dataTable").getRowCount();
	if(ids<=0){
		return showAlertDivLayer("������ѧ����Ϣ��");
	}
	var xhzt=jQuery("#xhzt").val();
	if(xhzt=="ybxh"){
		return delYbxh();
	}
	var ids = jQuery("#dataTable").getSeletIds();
	var title="��ȷ��Ҫɾ��ѡ��ı�ѧ�ż�¼��";
	if (ids.length == 0) {
		title="�Ƿ�ɾ�����б�ѧ�ż�¼��";
	} 
	var rows = jQuery("#dataTable").getSeletRow();
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ı�ѧ�ż�¼��", {
		"okFun" : function() {
			jQuery.post("fbglbxh.do?method=del", {
				values : ids.toString()
			}, function(data) {
				var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["successDel"]+"&nbsp;</font>��ѧ��";
				mes+="</br>";
				showAlertDivLayer(mes);
				jQuery("#dataTable").reloadGrid();
				}
			, 'json');
		}
	});
}
/**
 * ɾ���ѱ�ѧ��
 */
function delYbxh(){
	var ids = jQuery("#dataTable").getSeletIds();
	var title="��ȷ��Ҫɾ��ѡ��ı�ѧ�ż�¼��";
	if (ids.length == 0) {
		title="�Ƿ�ɾ�����б�ѧ�ż�¼��";
	}
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ���ѧ�ż�¼��", {
			"okFun" : function() {
				jQuery.post("fbglbxh.do?method=del", {
					values : ids.toString(),type:'ybxh'
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["successDel"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["errorObject"]!="-1"){
						var list=data["errorObject"];
						for(var i=0;i<list.length;i++){
							var messageObj=list[i];
							mes+="<font color='blue'>"+messageObj["nj"]+messageObj["xymc"]+messageObj["bjmc"]+messageObj["xm"]+"</font>";
							mes+="������ѧ�ţ�";
							mes+="</br>";
						}
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
}