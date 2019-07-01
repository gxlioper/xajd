  function modiInfo(url,w,h){
	   if(curr_row == null){
	   	alert('请选择要修改的记录行！');
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
  			alert('请将带\*号的项目填写完整!');
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
		alert("请选择要操作的记录！");
		return false;
	}
		
	if (!confirm("确定要操作所选记录？")){
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
 		alert('请指定要计算的学年学期！！');
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
 		alert('请将带\*号的项目输入完整！');
 		return false;
 	}
 	
 	if(xxjl != null){
		if(xxjl.replace(/[^\u0000-\u00ff]/g, "**").length > 400){	         
          		 alert("学习简历不能大于400个字符");
          		 return false;
       	}
	}
 	if(kyxm != null){
		if(kyxm.replace(/[^\u0000-\u00ff]/g, "**").length > 400){	         
          		 alert("科研项目不能大于400个字符");
          		 return false;
       	}
	}
 	if(sqly != null){
		if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 800){	         
          		 alert("申请理由不能大于800个字符");
          		 return false;
       	}
	}
 	
 	var xmmc = '奖学金';
 	pjpyZjsyzy.checkIsHmd(xh,xmmc,function(data){
 		if(data==true){ 
 			//判断是否可申请奖学金
 			pjpyZjsyzy.pjpyTj_validate(jxjdm,jxjmc,xh,nd,xn,xq,function(data){
 				if(data == true){//符合条件
 					flag = true;
 		 			dataDoSaveApply1('/xgxt/applySave.do','jxjdm','jxj','xh-jxjdm')
 				}else{
 					alert('你还不符合申请所选奖学金的条件，暂时不能申请该奖学金！');
 					flag = false;
 				}
 			});
 			
 		}else{
 			alert('你已经被列入奖学金申请黑名单，暂时还不能申请奖学金！');
 			flag = false;
 		}
 		return flag;
 	});
 	
 	return flag;
 }
 
  function commitRych(){
 	var flag = false;
 	var xh = document.getElementById("xh").value;
 	var xmmc = '荣誉称号';
 	pjpyZjsyzy.checkIsHmd(xh,xmmc,function(data){
 		if(data==true){ 			
 			flag = true;
     		dataDoSaveApply1('/xgxt/applySave.do','rychdm','rych');
 		}else{
 			alert('你已经被列入荣誉称号申请黑名单，暂时还不能申请荣誉称号！');
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
				    BatAlert.showTips('正在操作，请等待...');
 		} else {
 			alert('该生单科成绩或平均成绩未达到申请条件！');
 			return;
 		}
 	});
 }
