//��ѯ�ʾ���Ϣ
function searchWj(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcWjManage";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkWjManage";
	}
	//ʵϰ��״������
	if (mklx == "sxszk"){
		url+="sxszkWjManage";
	}
	
	//����ʱ���Ƿ�Ϸ�
	if(checkSearchTj('querygreaterequal_jlsj','querylessequal_jlsj')){
		allNotEmpThenGo(url);
	}
}

//����ʾ�
function addWj(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcWjUpdate";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkWjUpdate";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkWjUpdate";
	}
	
	showTopWin(url,'800','600');
}

//��ʾ�ʾ�
function showWj(doType){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcWjUpdate";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkWjUpdate";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkWjUpdate";
	}
	
	showInfo(url,doType,'800','600')
}

//ɾ���ʾ�
function delWj(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcWjManage";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkWjManage";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkWjManage";
	}
	sumitInfo(url,'del')
}

//�����ʾ�
function saveWj(){
	
	var kgkq = $("kgkq");
	var kggb = $("kggb");
	
	if(!kgkq.checked && !kggb.checked){
		alert("��ȷ���ʾ������!");
		return false;
	}
	
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";
	
	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcWjUpdate";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkWjUpdate";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkWjUpdate";
	}
	
	url+="&doType=save";
	saveUpdate(url,'wjmc');
}

//��ѯ������Ϣ
function searchSt(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcStManage";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkStManage";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkStManage";
	}
	
	//����ʱ���Ƿ�Ϸ�
	if(checkSearchTj('querygreaterequal_jlsj','querylessequal_jlsj')){
		allNotEmpThenGo(url);
	}
}

//�������
function addSt(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcStUpdate";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkStUpdate";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkStUpdate";
	}
	
	showTopWin(url,'800','600');
}

function saveWjfp(){

	var nj = $("nj").value;
	var xydm = $("xydm").value;
	var zydm = $("zydm").value;
	var id = $("id").value;
	
	var xymc = "";
	var zymc = "";
	var wjmc = "";
	
	var msg = "";
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";
	
	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcWjfpUpdate";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkWjfpUpdate";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkWjfpUpdate";
	}
	url+="&doType=save";
	
	if(id == ""){
		alert("�ʾ���Ϊ�գ���ȷ�ϣ���");
		return false;
	}
	
	for(var i=0;i<$("xy").options.length;i++){
		if($("xy").options[i].selected){
			xymc=$("xy").options[i].text;
		}
	}
	
	for(var i=0;i<$("zy").options.length;i++){
		if($("zy").options[i].selected){
			zymc=$("zy").options[i].text;
		}
	}
	
	for(var i=0;i<$("id").options.length;i++){
		if($("id").options[i].selected){
			wjmc=$("id").options[i].text;
		}
	}	
	
	if(nj =="" && xydm == "" && zydm == "" && $("bjR").options.length == "0"){
		msg = "���ʾ�'" + wjmc + "'�����ȫУ�༶��";
	}else if(nj !="" && xydm == "" && zydm == "" && $("bjR").options.length == "0"){
		msg = "���ʾ�'" + wjmc + "'�����" + nj + "ȫ��༶��";
	}else if(xydm != "" && zydm == "" && $("bjR").options.length == "0"){
		msg = "���ʾ�'" + wjmc + "'�����";
		if(nj != ""){
			msg += nj + "�꼶";
		}
		msg += xymc + "ȫ��༶?";
	}else if(zydm != "" && $("bjR").options.length == "0"){
		msg = "���ʾ�'" + wjmc + "'�����";
		if(nj != ""){
			msg += nj + "�꼶";
		}
		if(xymc != ""){
			msg += xymc;
		}
		msg += zymc + "ȫ��༶?";
	}else if($("bjR").options.length != "0"){
		msg = "���ʾ�'" + wjmc + "'����������ð༶��";
	}
	
	if (confirm(msg)) {
	
		for(var i = 0 ; i < $("bjR").options.length; i++){
			var tmp = document.createElement("input");
			tmp.type = "hidden";
			tmp.name = "fpbj";
			tmp.value = $("bjR").options[i].value;
			document.forms[0].appendChild(tmp);
		}
		
		saveUpdate(url,'lx');
	}
}

//��ʾ����
function showSt(doType){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcStUpdate";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkStUpdate";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkStUpdate";
	}
	showInfo(url,doType,'800','600')
}
//ɾ������
function delSt(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcStManage";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkStManage";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkStManage";
	}
	sumitInfo(url,'del')
}

//��ѯ������Ϣ
function searchFp(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcWjfpManage";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkWjfpManage";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkWjfpManage";
	}
	
	allNotEmpThenGo(url);

}

//���÷���
function szFp(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcWjfpUpdate";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkWjfpUpdate";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkWjfpUpdate";
	}
	showTopWin(url,'800','500')
}

//��������
function delFp(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcWjfpManage";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkWjfpManage";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkWjfpManage";
	}
	sumitInfo(url,'del')
}

//��������
function expFp(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcWjfpManage";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkWjfpManage";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkWjfpManage";
	}
	
	url+="&doType=exp";
	wjcfDataExport(url)
}

//��ѯ�ش���Ϣ
function searchHd(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcHdwjManage";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkHdwjManage";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkHdwjManage";
	}
	
	allNotEmpThenGo(url);
}
	
//��ʾ�ش�
function showHd(doType){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcHdwjUpdate";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkHdwjUpdate";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkHdwjUpdate";
	}
	showInfo(url,doType,'800','600')
}

//�ش��ʾ�
function hdwj(){
	if(curr_row == null){
		alert('��ѡ��Ҫ�ش���ʾ�');
		return false;
	}
	
	var stxx = curr_row.cells[5].innerText;
	if("δ��� " == stxx){
		alert("���ʾ�δ���������ȷ�ϣ�");
		return false;
	}
	
	dwr.engine.setAsync(false);
	var hdqk = curr_row.cells[7].innerText;
	
	var tableName = "wjdc_wjxxb";
	var pk = "id";
	var pkValue = curr_row.getElementsByTagName('input')[0].value; 
	var colList = ["kyxg","dawk"];
	
	var kyxg = "";
	getPjpyInfo.getPjpyInfo(tableName,pk,pkValue,colList,function(data){
		if(data != null){
			kyxg=data.kyxg;
		}
	});
	
	dwr.engine.setAsync(true);
	if(kyxg == "��" && hdqk == "�ѻش� "){
		alert("���ʾ��ύ�󲻿����޸ģ��޷���������");
		return false;
	}
	
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcHdwjUpdate";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkHdwjUpdate";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkHdwjUpdate";
	}
	
	url+="&lx=hd";
	showInfo(url,'update','800','600');
}

//��ѯ�ʾ���Ϣ
function searchTj(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcHdtjManage";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkHdtjManage";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkHdtjManage";
	}
	allNotEmpThenGo(url);
}

//��ѯ�ش���
function searchJg(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcHdjgManage";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkHdjgManage";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkHdjgManage";
	}
	
	allNotEmpThenGo(url);
}

function expJg(){

	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?doType=exp&method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcHdjgManage";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkHdjgManage";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkHdjgManage";
	}
	
	expData(url);
}



//��ʾ���
function showJg(doType){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcHdjgUpdate";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkHdjgUpdate";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkHdjgUpdate";
	}
	
	if(isOverWj()){
		showInfo(url,doType,'800','600');
	}
}

//���������Ϣ
function saveZjInfo(){
		
	var id = $("id").value;
	var wjmc = "";
	
	if(id == ""){
		alert("�ʾ���Ϊ�գ���ȷ�ϣ���");
		return false;
	}
	
	for(var i=0;i<$("id").options.length;i++){
		if($("id").options[i].selected){
		wjmc=$("id").options[i].text;
		}
	}
	
	var url = "/xgxt/wjdc.do?method=";
	var mklx = $("mklx").value;
	
	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcZjManage";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkZjManage";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkZjManage";
	}
	
	url+="&doType=save";
	
	if (confirm("��ҪΪ'"+wjmc+"'�����������ȷ����ѡ���⣿")) {
	
		for(var i = 0 ; i < $("xyRight").options.length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "fpbh";
		tmp.value = $("xyRight").options[i].value;
		document.forms[0].appendChild(tmp);
		
		var sx = document.createElement("input");
		sx.type = "hidden";
		sx.name = "zjsx";
		sx.value = i;
		document.forms[0].appendChild(sx);
		}
		
		saveUpdate(url,"id");
	}
}

//��ʾ������Ϣ
function showStInfo(pk){
	var url = "/xgxt/wjdc.do?method=";
	var mklx = $("mklx").value;
		
	//�����ղ�
	if(mklx == "xlpc"){
		url+="xlpcStUpdate";
	}
	//˼��״������
	if(mklx == "sxzk"){
		url+="sxzkStUpdate";
	}
	//ʵϰ��״������
	if(mklx == "sxszk"){
		url+="sxszkStUpdate";
	}
		
	url+="&doType=view";
	url+="&pk="+pk;
	showTopWin(url,'600','480');
}

	function startTj(){
	
		var nj = $("nj").value;
		var xy = $("xy").value;
		var zy = $("zy").value;
		var bj = $("bj").value;
		var xb = $("xb").value;
		var zzmm = $("zzmm").value;
		var mklx = $("mklx").value;
		var url = "/xgxt/wjdc.do?method=";
		//�����ղ�
		if(mklx == "xlpc"){
			url+="xlpcHdtjUpdate";
		}
		//˼��״������
		if(mklx == "sxzk"){
			url+="sxzkHdtjUpdate";
		}
		//ʵϰ��״������
		if(mklx == "sxszk"){
			url+="sxszkHdtjUpdate";
		}
		url+="&nj="+nj;
		url+="&xy="+xy;
		url+="&zy="+zy;
		url+="&bj="+bj;
		url+="&xb="+xb;
		url+="&zzmm="+zzmm;
		
		showInfo(url,'view','800','600');
		
		dd_html = "";
		tmpdiv1.innerHTML = dd_html;
	}