
//�������ŵ�id�������ŵ���ϸ��Ϣ
function updatetheinfo(){
	if(curr_row != null){
		var rid = curr_row.getElementsByTagName("input")[0].value;
		var url = "/xgxt/syltmoreinfo.do?method=syltmoreinfo&rid=" + rid; 
		//showTopWin(url, 680, 480);
		//alert(rid);
	}
}

//������̳�������µ�������Ϣ
function getTalkInfo(url){
	if(curr_row != null){
		var userName = curr_row.getElementsByTagName("input")[0].value;
		var currUrl = url + "&userName=" + userName;
		//refreshForm(currUrl);
	}
}

 //�������ŵ�id�������ŵ���ϸ��Ϣ
function updatetheinfo(){
	if(curr_row != null){
		//checkPower
		//var userName = document.getElementById("userName").value;
		if(checkUserAccessPower()){
			getNoteMoreInfo();
		}
	}
}

//�������ӵ���ϸ��Ϣ
function getNoteMoreInfo(){
	var rid = curr_row.getElementsByTagName("input")[0].value;
	var bk = document.getElementById("bk").value;
	var url = "/xgxt/syltaddnew.do?method=syltaddnew&doType=view&rid=" + rid + "&bk=" + bk; 
	showTopWin(url, 680, 480);
}

//�����û���Ȩ��
function  checkUserAccessPower(userName){
/*	dssyFun.checkUserPower(userName,function(data){
		if(data == "1" || data == "2"){
			getNoteMoreInfo();
		}
	});
*/
	var power = document.getElementById("power").value;
	if(power == "yes"){
		return true;
	}
	return false;
}

//�������
 function addnew(){
 /*
 	if(!checkUserAccessPower()){
		//getNoteMoreInfo();
		return;
	}
*/	
	var bk =document.getElementById("bk").value;
	var url = "syltaddnew.do?method=syltaddnew&bk=" + bk;
	showTopWin(url, 680, 480);
}
		  

//ɾ������		  
function delmess(objTr){
	if(!checkUserAccessPower()){
		//getNoteMoreInfo();
		return;
	}
	if (curr_row != null && curr_row.tagName.toLowerCase() == "tr") {
		if(confirm("ȷ��Ҫɾ����?") == false){
		 	return;
		 }
		 var newsid = curr_row.getElementsByTagName('input')[0].value;
		 underDealWith();
		 refreshForm("sylt.do?method=delNewByRID&newsid=" + newsid);
	}	
}


//�������ö�
function noteZd(obj){
	if(!checkUserAccessPower()){
		//getNoteMoreInfo();
		return;
	}
	if (curr_row != null && curr_row.tagName.toLowerCase() == "tr") {
		if(confirm("ȷ��Ҫ�ö���?") == false){
		 	return;
		 }
		 var newsid = curr_row.getElementsByTagName('input')[0].value;
		 underDealWith();
		 refreshForm("sylt.do?method=noteZd&newsid=" + newsid);
	}	
}


/**
	����û��ͨ����û����˵�����
*/
function getNoPassNoteList(){
	var bk = document.getElementById("bk").value;
	showTopWin('/xgxt/sylt.do?method=noPassNoteList&bk=' + bk,600,450);
}

/**
	������ͨ��/��ͨ��/ɾ��
*/
function operateNote(doType){
	if(curr_row != null){
		if(doType == "del" && !confirm("ȷ��Ҫɾ����?")){
			return;
		}else{
		 	var rid = curr_row.getElementsByTagName('input')[0].value;
		 	refreshForm('/xgxt/sylt.do?method=noPassNoteList&doType=' + doType + '&rid=' + rid);
		 }	
	}
}


function refreshtheweb(){
	var bk = document.getElementById("bk").value;
	refreshForm("/xgxt/syltbklb.do?method=syltbklb&bk=" + bk);
}

/**
  ����ǰ��������˳��
*/
function bkSortSave(){
	var bkStr = "";
	for (i = 0; i < document.forms[0].bklb.options.length; i++) {
		if (bkStr != "") {
			bkStr = bkStr + "-";
		}
		bkStr = bkStr + document.forms[0].bklb.options[i].value;
	}
	underDealWith();
	syltDao.bkSortSave(bkStr,function(data){
		if(data){			
			alert("����ɹ���");
			window.dialogArguments.document.getElementById("refreshThis").click();
			Close();
		}else{
			alert("����ʧ�ܣ�");
			document.getElementById("tmpdiv").style.display = "none"; //no visable
		}
	});
}

/**����ö�*/
function cancleZd(obj){
	if(curr_row == null){
		alert("��ѡ��Ҫ����ö�������");
		return;
	}
	if(!checkUserAccessPower()){
		return;
	}
	if (curr_row != null && curr_row.tagName.toLowerCase() == "tr") {
		if(confirm("ȷ��Ҫ����ö���?") == false){
		 	return;
		 }
		 var newsid = curr_row.getElementsByTagName('input')[0].value;
		 underDealWith();
		 refreshForm("sylt.do?method=cancleZd&newsid=" + newsid);
	}	
}

/**���������ȼ�*/
function notedj(param){
	if(!checkUserAccessPower()){
		return;
	}
	if (curr_row != null && curr_row.tagName.toLowerCase() == "tr") {
		 var newsid = curr_row.getElementsByTagName('input')[0].value;
		 underDealWith();
		 refreshForm("sylt.do?method=noteDj&newsid=" + newsid+ "&dj=" + param);
	}	
}

function getNoteDj(param){
	if(curr_row == null){
		return;
	}
	if(!checkUserAccessPower()){
		return;
	}
	//if (curr_row != null && curr_row.tagName.toLowerCase() == "tr") {
	//	 var newsid = curr_row.getElementsByTagName('input')[0].value;
	//	 underDealWith();
	//	 refreshForm("sylt.do?method=noteDj&newsid=" + newsid + "&dj=" + param);
	//}
	/*
	var options = document.forms[0].dj.options;
	var length = options.length;
	var rowid = curr_row.getElementsByTagName('input')[0].value;
	for(var i=0;i<length;i=i+1){
		if(options[i].selected){
			options[i].selected = false;
		}	
	}
	syltDao.getNoteDj(rowid,function(data){		
		for(var i=0;i<length;i=i+1){
			var value = options[i].value;
			if(data == value){
				options[i].selected = true;
			}
		}
	});	
	*/
	document.getElementById('items').style.display= '';
}


//�����Ѿ����ڵ�ѡ���¼
function lrh_updateStList(flag) {
		var curIndex = document.forms[0].bklb.selectedIndex;
		var tmp;
		if (curIndex < 0) {
			alert("����ѡ����!");
			return false;
		}
		if (flag == "up") {
			if (curIndex == 0) {
				return false;
			}
			tmp = document.forms[0].bklb.options[curIndex - 1].value; //currentOption -1
			tmpText = document.forms[0].bklb.options[curIndex - 1].text;
			document.forms[0].bklb.options[curIndex - 1].value = 
				document.forms[0].bklb.options[curIndex].value;
			document.forms[0].bklb.options[curIndex - 1].text = 
				document.forms[0].bklb.options[curIndex].text;
			document.forms[0].bklb.options[curIndex].value = tmp;
			document.forms[0].bklb.options[curIndex].text = tmpText;
			document.forms[0].bklb.selectedIndex = curIndex - 1;
		}
		if (flag == "down") {
			if (curIndex >= document.forms[0].bklb.options.length - 1) {
				return false;
			}
			tmp = document.forms[0].bklb.options[curIndex + 1].value;
			tmpText = document.forms[0].bklb.options[curIndex + 1].text;
			document.forms[0].bklb.options[curIndex + 1].value = 
				document.forms[0].bklb.options[curIndex].value;
			document.forms[0].bklb.options[curIndex + 1].text = 
				document.forms[0].bklb.options[curIndex].text;
			document.forms[0].bklb.options[curIndex].value = tmp;
			document.forms[0].bklb.options[curIndex].text = tmpText;
			document.forms[0].bklb.selectedIndex = curIndex + 1;
		}
}

//����С�����ҿ�ʼ���ֲ���Ϊ0���������ּ����������¼�����
function NumInputValue1(obj, maxLen) {
	var key = event.keyCode;
	var numLen = obj.value.length;
	  if (((key >= 48 && key <= 57)) && obj.value.length < maxLen ) {						
		if(numLen == 0&& event.keyCode==48){
		   return false;
		}else{
		    return true;
		}			
	  } else {
		return false;
	  }	
}
//����С�����������ּ����������¼�����
function NumInputValue2(obj, maxLen) {
	var key = event.keyCode;
	var numLen = obj.value.length;
	  if (((key >= 48 && key <= 57)) && obj.value.length < maxLen ) {						
		return true;					
	  } else {
		return false;
	  }	
}

function hmsCheck(obj) {
	if(obj.value.length==1){
	   obj.value="0"+obj.value;
	}
}

