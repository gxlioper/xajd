  function modiInfo(url,w,h){
	   if(curr_row == null){
	   	alert('��ѡ��Ҫ�޸ĵļ�¼�У�');
	   	return false;
	   }
	   var pkValue = curr_row.getElementsByTagName('input')[0].value;
	   url += "&pkValue=";
	   url += pkValue;
	   showTopWin(url,w,h);
  }
  
 function commit(url,str){
  	var column = str.split("-");
  	for(var i=0; i<column.length; i++){
  		if(document.getElementById(column[i]).value==""){
  			alert('�뽫��\*�ŵ���Ŀ��д����!');
  			return false;
  		}
  	}
  	
  	refreshForm(url);
  }
  
  function batch(url,oper){
  	var RowsStr="!!";		
	for (i=0; i<document.getElementsByName("pk").length; i++){
	    if(document.getElementsByName("pk")[i].checked){
	    	RowsStr+=document.getElementsByName("pk")[i].value+"!!";
	    }
	}
	//document.forms[0].delPk.value = RowsStr;
	
	if (RowsStr=="!!"){
		alert("��ѡ��Ҫ�����ļ�¼��");
		return false;
	}
		
	if (!confirm("ȷ��Ҫ������ѡ��¼��")){
		return false;
	}
	if(oper=="del"){
		url += "&pk=";
		url += RowsStr;
		refreshForm(url);
	}else{
		url += "&pk=";
		url += RowsStr;
		showTopWin(url,400,300);
  }
 }
 
 function AutoAccount(url){
 	var xn = document.getElementById("xn").value;
 	var xq = document.getElementById("xq").value;
 	
 	if(xn == '' || xq == '') {
 		alert('��ָ��Ҫ�����ѧ��ѧ�ڣ���');
 		return false;
 	}
// 	url += "&xn=";
// 	url += xn;
// 	url += "&xq=";
// 	url += xq;
 	AutoAccountCj(url);
 }
 
 function commitPrise(){
 	var flag = false;
 	var xh = document.getElementById("xh").value;
 	var xxjl = document.getElementById("xxjl").value;
 	var kyxm = document.getElementById("kyxm").value;
 	var sqly = document.getElementById("sqly").value;
 	var xn = document.getElementById("xn").value;
 	var nd = document.getElementById("nd").value;
 	var xq = document.getElementById("xq").value;
 	var jxjdm = document.getElementById("jxjdm").value;
 	var jxjmc = document.forms[0].jxjdm.options[document.forms[0].jxjdm.selectedIndex].text;
 	if(xh == "" || jxjdm == ""){
 		alert('�뽫��\*�ŵ���Ŀ����������');
 		return false;
 	}
 	
 	if(xxjl != null){
		if(xxjl.replace(/[^\u0000-\u00ff]/g, "**").length > 400){	         
          		 alert("ѧϰ�������ܴ���400���ַ�");
          		 return false;
       	}
	}
 	if(kyxm != null){
		if(kyxm.replace(/[^\u0000-\u00ff]/g, "**").length > 400){	         
          		 alert("������Ŀ���ܴ���400���ַ�");
          		 return false;
       	}
	}
 	if(sqly != null){
		if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 800){	         
          		 alert("�������ɲ��ܴ���800���ַ�");
          		 return false;
       	}
	}
 	
 	var xmmc = '��ѧ��';
 	pjpyZjsyzy.checkIsHmd(xh,xmmc,function(data){
 		if(data==true){ 
 			//�ж��Ƿ�����뽱ѧ��
 			pjpyZjsyzy.pjpyTj_validate(jxjdm,jxjmc,xh,nd,xn,xq,function(data){
 				if(data == true){//��������
 					flag = true;
 		 			dataDoSaveApply1('/xgxt/applySave.do','jxjdm','jxj','xh-jxjdm')
 				}else{
 					alert('�㻹������������ѡ��ѧ�����������ʱ��������ý�ѧ��');
 					flag = false;
 				}
 			});
 			
 		}else{
 			alert('���Ѿ������뽱ѧ���������������ʱ���������뽱ѧ��');
 			flag = false;
 		}
 		return flag;
 	});
 	
 	return flag;
 }
 
  function commitRych(){
 	var flag = false;
 	var xh = document.getElementById("xh").value;
 	var xmmc = '�����ƺ�';
 	pjpyZjsyzy.checkIsHmd(xh,xmmc,function(data){
 		if(data==true){ 			
 			flag = true;
     		dataDoSaveApply1('/xgxt/applySave.do','rychdm','rych');
 		}else{
 			alert('���Ѿ������������ƺ��������������ʱ���������������ƺţ�');
 			flag = false;
 		}
 		return flag;
 	});
 	
 	return flag;
 }
 
 
 function chkjxjsqtj(jxjdm,zdcj,pjcj) {
 	pjpyZjsyzy.jxjsqTj(jxjdm,zdcj,pjcj,function(data){
 		if (data) {
 			if (dataDoSaveApply1('/xgxt/applySave.do','jxjdm','jxj','xh-jxjdm')) 
				    BatAlert.showTips('���ڲ�������ȴ�...');
 		} else {
 			alert('�������Ƴɼ���ƽ���ɼ�δ�ﵽ����������');
 			return;
 		}
 	});
 }
