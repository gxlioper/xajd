//ҳ���ʼ����ֵ����Դ��
function showLybList(){
	var num = document.getElementsByName("lrxs").length;
	
	for(var i=0;i<num;i++){
		var obj = document.getElementsByName("lrxs")[i];
		var id = obj.id;
		
		if(obj.value!=""){
			setLybList(id.replace("lrxs",""));
		}
	}
}

//��ѯ���
function searchRs(){
	allNotEmpThenGo('/xgxt/xsxxJbsz.do?method=xtzdsz');
}

//������Դ���б�
function setLybList(num){

	dwr.engine.setAsync(false);
	
	//¼����ʽ
	var lrxs_id = "lrxs"+num;
	var lrxs = $(lrxs_id).value;
	
	//�ֶ�
	var zd_id = "zd"+num;
	var zd = $(zd_id).value;
	
	//��Դ��
	var lyb_id = "lyb"+num;
	
	//�������������¼����ʽ�Ļ�������ѡ����Դ��
	if(lrxs == "�����б�" || lrxs == "��ѡ��" || lrxs == "�����ʽ"){
		var tableName = "xg_xsxx_zdlyb";
		var colList = ["lyb","lybm"];
		var pk = "zd";
		var pkValue = zd;
		var query = "";
		
		getOtherData.getTableListInfo(tableName,colList,pk,pkValue,query,function(data){
			if(data != null && data.length > 0){
				if($(lyb_id)){
					DWRUtil.removeAllOptions(lyb_id);
					DWRUtil.addOptions(lyb_id,data,"lyb","lybm");
				}
			}
		});
	}else{
		if($(lyb_id)){
			DWRUtil.removeAllOptions(lyb_id);
			var options = [{dm:"",mc:"����ѡ��¼����ʽ"}];
			DWRUtil.addOptions(lyb_id,options,"dm","mc");
		}
	}
	
	dwr.engine.setAsync(true);
}

//�����ֶ�����
function saveZdsz(){
	var hadRs = $("hadRs").value;
	if(hadRs == "yes"){
		if (confirm("����ҳ����ʾ���ֶ����ã���ȷ�ϣ�")) {
			saveUpdate('/xgxt/xsxxJbsz.do?method=xtzdsz&doType=save','');
		}
	}else{
		alert("���Ȳ�ѯ����Ҫ���õ��ֶΣ���ִ�иò�����");
	}
}

//��������ͼ
function creatNewView(){
	if (confirm("��Ҫ����Ŀǰ�ֶ����������\n�����µ�ѧ����Ϣ��ͼ��\n��ȷ���Ƿ�ִ�иò�����")) {
		saveUpdate('/xgxt/xsxxJbsz.do?method=xtzdsz&doType=create','');
	}
}

//�����������ò�
function showPlszDiv(){

	var num = document.getElementsByName("primarykey_checkVal").length;
	var flag = false;
	
	for(var i=0;i<num;i++){
		var obj = document.getElementsByName("primarykey_checkVal")[i];
		if(obj.checked == true){
			flag = true;
			break;
		}
	}
	
	if(flag){
		viewTempDiv("�ֶ�����","tmpdiv1",350,300);
		//var divHtml = $("tmpdiv1").innerHTML;
		//createOtherDiv("�ֶ�����",divHtml,"plszDIV",350,350);
	}else{
		alert("�빴ѡ��Ҫ���õ��ֶΣ�");
	}
}

//������������
function setPlsz(){
	var num = document.getElementsByName("primarykey_checkVal").length;
	var flag = false;
	
	//ҳ����ʾ 
	var ymxs = $("sz_ymxs_value").value;
	//����Դ
	var sjy = $("sz_sjy_value").value;
	//ѧ��Ϊ׼
	var xgwz = $("sz_xgwz_value").value;
	//¼������
	var lrxz = $("sz_lrxz_value").value;
	//Ϊ������
	var wkxz = $("sz_wkxz_value").value;
	//¼����ʽ
	var lrxs = $("sz_lrxs_value").value;
	//�Ƿ�����
	var sfqy = $("sz_sfqy_value").value;
	
	for(var i=0;i<num;i++){
		var obj = document.getElementsByName("primarykey_checkVal")[i];
		if(obj.checked == true){
			//ҳ����ʾ 
			if(ymxs != "������"){
				$("xsmc"+i).value = $("zdm"+i).value;
			}
			//����Դ
			if(sjy != "������"){
				$("sjy"+i).value = sjy;
			}
			//ѧ��Ϊ׼
			if(xgwz != "������"){
				for(var j=0;j<document.getElementsByName("xgwz"+i).length;j++){
					if(document.getElementsByName("xgwz"+i)[j].value == xgwz){
						document.getElementsByName("xgwz"+i)[j].checked = true;
						break;
					}
				}
				$("hid_xgwz_"+i).value=xgwz;
			}
			//¼������
			if(lrxz != "������"){
				$("lrxz"+i).value = lrxz;
			}
			//Ϊ������
			if(wkxz != "������"){
				$("wkxz"+i).value = wkxz;
			}
			//¼����ʽ
			if(lrxs != "������"){
				$("lrxs"+i).value = lrxs;
			}
			//�Ƿ�����
			if(sfqy != "������"){
				for(var j=0;j<document.getElementsByName("sfqy"+i).length;j++){
					if(document.getElementsByName("sfqy"+i)[j].value == sfqy){
						document.getElementsByName("sfqy"+i)[j].checked = true;
						break;
					}
				}
				$("hid_sfqy_"+i).value=sfqy;
			}
		}
	}
	
	hiddenMessage(true,true);
}

//ѡ������Դ
function selectSjy(num){

	var sjy_id = "sjy"+num;
	var isxg = false;
	var xgwz = $("hid_xgwz_"+num).value;

	if($(sjy_id).value == "ѧ��"){
		isxg = true;
	}

	for(var i=0;i<document.getElementsByName("xgwz"+num).length;i++){
	
		var obj = document.getElementsByName("xgwz"+num)[i];

		//ѧ��Ϊ׼
		if(isxg){
			if(obj.value == "��"){
				obj.checked = true;
				xgwz = obj.value;
			}else{
				obj.disabled = true;
			}
		}else{
			if(xgwz.value == "��"){
				obj.checked = true;
			}else{
				obj.disabled = false;
			}		
		}
	}
	
	$("hid_xgwz_"+num).value=xgwz;
}

//ҳ���ʼ����ֵ��ѧ��Ϊ׼��
function showXgwz(){
	var num = document.getElementsByName("sjy").length;

	for(var i=0;i<num;i++){
		var obj = document.getElementsByName("sjy")[i];
		var id = obj.id;
		if(obj.value!=""){
			selectSjy(id.replace("sjy",""));
		}
	}
}