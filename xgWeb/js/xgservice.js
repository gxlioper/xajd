/**
 *   ָ������ѧ���ֶ����ݳ���
 *   luojw
 *   2010-6-28
 */
 
function sendXx(){
	//ҳ���������ָ������ѯ���ݳ��������磨<input type="hidden" id="tableName" name="tableName" value="view_ybdyxx"/>��
	var tableName=$("tableName").value;
	//����Դ��������չʾjsp�ı�ͷ�����磨<input type="hidden" id="lx" name="lx" value="Ԥ����Ա"/>��
	var lx="";
	//����Դ����������������:
	//<input type="hidden" id="zd" name="zd" value="sfty-xxsh"/>
	//<input type="hidden" id="va" name="va" value="��-ͨ��"/>
	var zd="";
	var va="";
	var mklx="";
	var url = "commXgInfo.do?method=xsxxManage";
	
	if($("lx")){
		lx=$("lx").value
	};
	if($("zd")){
		zd=$("zd").value
	};
	if($("va")){
		va=$("va").value
	};
	if($("mklx")){
		mklx=$("mklx").value
	};
	
	url+="&tableName="+tableName;
	if(lx !=""){
		url+="&lx="+lx;
	}
	if(zd !=""){
		url+="&zd="+zd;
	}
	if(va !=""){
		url+="&va="+va;
	}
	if(mklx !=""){
		url+="&mklx="+mklx;
	}
	//alert(22);
	//showDialog("", 800, 600, url);
	showTopWin(url,800,600);
}

//��ѡһ����¼�޸�
//checkboxName �� ��ѡ������
//currPage :true-ˢ�±�ҳ�棨�ǵ������ڣ�
//message:����ѡ���ѡ��������ʾ��Ϣ
//���ߣ������
function showUpdateWindow(checkboxName,url,width,height,currPage,message){
	var pkValues = document.getElementsByName(checkboxName);
	var tempArr=new Array(),n=0 ;
	
	for (var i = 0 ; i < pkValues.length ; i++){
		
		if (pkValues[i].checked){
			if (pkValues[i].alt=='disabled'){
				alertInfo('��ѡ�����Ŀ��������˻������<br/>�����޸�,��ȷ��');
				return false;
			} else{
				tempArr[n] = pkValues[i];
				n++;
			}
		}
	}
	
	if (tempArr.length != 1){
		if (null == message){
			alertInfo("�빴ѡһ����Ҫ�޸ĵ�����");
		} else {
			alertInfo(message);
		}
	
		return false;
	} else if (currPage){
		url+="&pkValue="+tempArr[0].value;
		refreshForm(url);
	} else {
		url+="&pkValue="+tempArr[0].value;
		showTopWin(url,width,height);
	}
}


function showViewWindow(checkboxName,url,width,height,currPage,message){
	var pkValues = document.getElementsByName(checkboxName);
	var tempArr=new Array(),n=0 ;
	
	for (var i = 0 ; i < pkValues.length ; i++){
		
		if (pkValues[i].checked){
			tempArr[n] = pkValues[i];
			n++;
		}
	}
	
	if (tempArr.length != 1){
		if (null == message){
			alertInfo("�빴ѡһ����Ҫ�鿴������");
		} else {
			alertInfo(message);
		}
	
		return false;
	} else if (currPage){
		url+="&pkValue="+tempArr[0].value;
		refreshForm(url);
	} else {
		url+="&pkValue="+tempArr[0].value;
		showTopWin(url,width,height);
	}
}





//���ܣ����
//ʹ��˵������ѡһ����¼�����������ҳ��;��ѡ��������������˲㡣
//			����õ�������plsh();
//ע���:�����ж�����˵ļ�¼���˹��ˣ�����ѡ�ļ�¼��������ˣ���ͨ�������ߡ���ͨ�������ļ�¼
//     �ᵯ����ʾ��Ϣ����ֹ���������˷���������checkbox�ϵ�alt���ԣ�
//     ��������ֵΪdisabled����˵����Ϊ����˵ļ�¼��
//���ߣ������
function showAuditingWindow(checkboxName,oneAuditingUrl,width,height){
			
	var pkValues = document.getElementsByName(checkboxName);
	var tempArr=new Array(),n=0 ;
	
	for (var i = 0 ; i < pkValues.length ; i++){
		
		 if (pkValues[i].checked){
		 
		 	if (pkValues[i].alt=="disabled" || pkValues[i].alt=="th"){
				alertInfo("����ѡ�ļ�¼��������˻��˻ص����ݣ���ȷ��");
				return false;
			} 
		 
			tempArr[n] = pkValues[i];
			n++;
		}
	}
	
	if (tempArr.length == 0){
		alertInfo("�빴ѡ��Ҫ��˵ļ�¼!");
		return false;
	} else if (tempArr.length == 1){
		oneAuditingUrl+="&pkValue="+tempArr[0].value;
		showTopWin(oneAuditingUrl,width,height);
	} else {
		plsh();
	}
}


function showPrintWindow(checkboxName,url,message){
	var pkValues = document.getElementsByName(checkboxName);
	var tempArr=new Array(),n=0 ;
	
	for (var i = 0 ; i < pkValues.length ; i++){
		if (pkValues[i].checked){
			tempArr[n] = pkValues[i];
			n++;
		}
	}
	
	if (tempArr.length != 1){
		if (null == message){
			alertInfo("�빴ѡһ����Ҫ�޸ĵ�����");
		} else {
			alertInfo(message);
		}
	
		return false;
	} else{
		document.forms[0].action = url+"&pkValue="+tempArr[0].value;
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	} 
}






//���ܣ���ѯ��¼�б�˫����һ�м�¼��ʾ������¼����ϸ��Ϣ
//ע���:��¼�еĵ�һ�б��������ص������ı�
//���ߣ�lr
function showDetailWindow(url,width,height,self,message){
	var pk="";		
	message = message == null || message == '' ? "��ѡ��һ�м�¼��\n����һ�м���!" : message; 
	if(curr_row == null ){
		alert(message);
		return false;
	} 		
	pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
	url += "&pkValue="+pk;	
	
	self == true ? refreshForm(url) :  showTopWin(url, width, height);
}

//���ܣ���ѯ��¼����������
//���ߣ�lr
function bachAction(url,tagName,confirmMsg) {
	var checkBoxArr = document.getElementsByName(tagName);
	var flag = false;
	confirmMsg = confirmMsg == null || confirmMsg == '��ȷ������ѡ��ļ�¼��' ? '' : confirmMsg;
	
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){		
		if(confirm(confirmMsg)){
			refreshForm(url);
		}
		return true;
	}else{
		alert("û��ѡ����Ӧ��¼����ѡ���¼���ٽ��в���!");
		return false;
	}
}

//���ܣ���ʾ����в���
//���ߣ�lr
function confirmAction(url, confirmMsg){
	if(confirm(confirmMsg)){
		refreshForm(url);
	}else{
		return false;
	}
}

var array = [];	
//����Ƿ��й��м�¼,����ǹ��еķ������鹩����������
function isChecked(tagName) {
	array = new Array();

	var checkBoxArr = document.getElementsByName(tagName);
	var flag = false;
	var n = 0;
	for (var i = 0; i < checkBoxArr.length; i++) {
		if (checkBoxArr[i].checked == true) {
			flag = true;
			array[n] = checkBoxArr[i].value;
			n++;
		}
	}
	if (flag) {
		return true;
	} else {
		alertInfo("\u6ca1\u6709\u9009\u62e9\u76f8\u5e94\u8bb0\u5f55\uff0c\u8bf7\u9009\u62e9\u4e4b\u540e\u518d\u8fdb\u884c\u64cd\u4f5c!");
		return false;
	}
}


//���������Ƿ�Ϊ��
function checkBtx(id){

	var key = new Array();
	if(id!=""){
		key=id.split("-");
	}

	if(key.length > 0){
		for(var i=0;i<key.length;i++){
			if($(key[i])){
				if($(key[i]).value == ""){
					alert('��"*"���Ϊ�գ���ȷ��');
					return false;
				}
			}
		}
	}
	
	return true;
}


function doDel(checkboxName,url){
	var checkbox = jQuery('input[name='+checkboxName+']:checked');
	
	if(checkbox.length == 0){
		alertInfo('��ѡ����Ҫɾ��������');
		return false;
	} else {
		for (var i = 0 ; i < checkbox.length ; i++){
		 	if (jQuery(checkbox[i]).attr('alt')=="disabled"){
				alertInfo("����ѡ�ļ�¼����������˻������<br/>�����ݣ���ȷ��");
				return false;
			} 
		}
	}
	batchData(checkboxName,url,'del');	
}
