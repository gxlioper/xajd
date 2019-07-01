var action="qjgz.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
//��������ת
function dcmcLink(cellValue, rowObject) {
	var kssj = rowObject["kssj"];
	var jssj = rowObject["jssj"];
	return getShow(kssj,jssj);
}
/**
 * �Զ���ʾ��ʾ
 * @return
 */
function autoShowLable(){
	var kssj=jQuery("#kssj").val();
	var jssj=jQuery("#jssj").val();
	if(kssj!=""&&jssj!=""){
		jQuery("#lable").html(getShow(kssj,jssj));
		jQuery("#div_help").show();
	}else{
		jQuery("#div_help").hide();
	}
}
function getShow(kssj,jssj){
	return "����<font color=\"#FF8040\">"+kssj+"</font>��С�ڵ���<font color=\"#FF8040\">"+jssj+"</font>��&nbsp;(<font color=\"red\">������"+kssj+"��</font>)";
}
//����
function add() {
		var url =action+"?method=add";
		var title = "������ٹ���";
		showDialog(title, 400, 250, url);
		jQuery("#dataTable").reloadGrid();
}
function save(url,checkId,keyid){
	if(!check(checkId)){
		return alertError("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	if(!checkother()){
		return false;
	}
	var kssj=jQuery("#kssj").val();
	var jssj=jQuery("#jssj").val();
	var id=jQuery("#"+keyid).val(); 
    var qjlxid = jQuery("#qjlxid").val();
    var ssxydm = jQuery("#ssxydm").val();
	jQuery.post("qjgz.do?method=isCanAdd", {
		kssj:kssj,jssj:jssj,qjgzid:id,qjlxid:qjlxid,ssxydm:ssxydm
	}, function(data) {
		if(data["success"]=="true"){
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
		}else{
			if(data["message"]=="-1"){
				showAlert("�˹����Ѿ���ʹ�ã������޸�!");
			}else{
				showAlert("����������&nbsp;<font color='red'>["+data["message"]+"]</font>&nbsp;����������ͻ��");
			}
		}
	}, 'json');
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
    var userXy = jQuery("#userXy").val();
    var userXymc = jQuery("#userXymc").val();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else if(rows[0]["ssxydm"] != userXy){
		if("xx" == userXy){
            showAlertDivLayer("ֻ���޸�����ѧԺΪȫУ�ļ�¼��");
		}else{
            showAlertDivLayer("ֻ���޸�����ѧԺΪ"+userXymc+"�ļ�¼��");
		}

	}else{
		var url = action+'?method=update&qjgzid=' + rows[0]["qjgzid"];
		var title = "�޸���ٹ���";
		showDialog(title, 400, 250, url);
		jQuery("#dataTable").reloadGrid();
	}
}
function checkother(){
	var kssj=jQuery("#kssj").val();
	var jssj=jQuery("#jssj").val();
	if(kssj<0||jssj<0){
		showAlert("������䲻�ܸ�������!");
		return false;
	}
	if(chkNumInput("kssj")||chkNumInput("jssj")){
		showAlert("������䲻��Ϊ�Ƿ���������!");
		return false;
	}
	if(kssj.indexOf(".")!=-1||jssj.indexOf(".")!=-1){
		showAlert("����������Ϊ����!");
		return false;
	}
	if(checkNum()){
		showAlert("����д��ȷ��������,��ǰΪ�Ƿ�����!");
		return false;
	}
	if(parseInt(jssj)<=parseInt(kssj)){
		showAlert("����д��ȷ�����䣬��������Ӧ�ô��ڿ�ʼ����!");
		return false;
	}
	if(parseInt(jssj)<=0){
		showAlert("����д��ȷ�����䣬��������Ӧ�ô���0");
		return false;
	}
	return true;
}
function checkNum(){
	var kssj=jQuery("#kssj").val();
	var str=kssj.substring(0,1);//��ȡ��һλ
	if(parseInt(str)<=0&&kssj.length>1){
		return true;
	}
	var jssj=jQuery("#jssj").val();
	str=jssj.substring(0,1);//��ȡ��һλ
	if(parseInt(str)<=0&&jssj.length>1){
		return true;
	}
	return false; 
}
//ɾ��
function del() {
    var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
    var userXy = jQuery("#userXy").val();
    var userXymc = jQuery("#userXymc").val();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else if(rows[0]["ssxydm"] != userXy){
        if("xx" == userXy){
            showAlertDivLayer("ֻ���޸�����ѧԺΪȫУ�ļ�¼��");
        }else{
            showAlertDivLayer("ֻ���޸�����ѧԺΪ"+userXymc+"�ļ�¼��");
        }

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

//�鿴��Ŀ����
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["qjgzid"] + "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ��ajaxurl��ת
function View() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
		return false;
	}
	showDialog("��ٹ���鿴", 400, 200, "qjgz.do?method=ckQjgz&qjgzid="
			+ rows[0]["qjgzid"]);
}


//��������
function openLink(cellValue, rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='linkClick(\""
	+ rowObject["qjgzid"] + "\");'>" + cellValue
	+ "</a>";
}

//������ӵ���
function linkClick(qjgzid){
	showDialog("�����رտ���", 400, 200, "qjgz.do?method=openZt&qjgzid="
			+ qjgzid);
}
