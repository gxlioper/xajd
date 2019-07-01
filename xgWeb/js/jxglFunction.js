function Savedata(mustFill,url){
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if($(eles[i])){
			if (document.getElementById(eles[i]).value == "") {
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
	}
		
	document.forms[0].action = url;
	document.forms[0].submit();
	if($("buttonSave")){
	   $("buttonSave").disabled=true;
	}
}

function showDataFrame(url,doType,w,h,zzlb){
	url += "?doType=";
	url += doType;
	url += "&pkValue=";
	
	if (doType == "modi" && curr_row == null) {
		alert("请选择要修改的数据！\n（单击相应的行）");
		return false;
	}
	
	if (doType == "del"){
		if(curr_row == null){
			alert("请选择要修改的数据！\n（单击相应的行）");
			return false;
		}else{
			if(confirm("确定要删选中记录吗？")){
				url += curr_row.getElementsByTagName("input")[0].value;
				refreshForm(url);
				return true;
			}else{
				return false;
			}
		}
	}

	if(doType!="add"){
		url += curr_row.getElementsByTagName("input")[0].value;
	}
	if(zzlb!=null && zzlb !=""){
			url += "&zzlb="+zzlb;
	}

	showTopWin(url, w, h);
}

function pdsjks(){
	var hh = document.getElementById("kssjH").value;
	var mi = document.getElementById("kssjM").value;
	var ss = document.getElementById("kssjH").value;
	var jhh = document.getElementById("jssjH").value;
	var jmi = document.getElementById("jssjM").value;
	var jss = document.getElementById("jssjH").value;
	if(hh>24 || hh.match(/^\d/)==null){
		alert("时间格式非法");
		document.getElementById("kssjH").value = "";
		return false;
	}
	if(mi>60 || mi.match(/^\d+\.{0,1}\d{0,3}$/)==null){
		alert("时间格式非法");
		document.getElementById("kssjM").value = "";
		return false;
	}
	if(ss>60 || ss.match(/^\d+\.{0,1}\d{0,3}$/)==null){
		alert("时间格式非法");
		document.getElementById("kssjS").value = "";
		return false;
	}
	if(jhh>24 || jhh.match(/^\d+\.{0,1}\d{0,3}$/)==null){
		alert("时间格式非法");
		document.getElementById("jssjH").value = "";
		return false;
	}
	if(jmi>60 || jmi.match(/^\d+\.{0,1}\d{0,3}$/)==null){
		alert("时间格式非法");
		document.getElementById("jssjM").value = "";
		return false;
	}
	if(jss>60 || jss.match(/^\d+\.{0,1}\d{0,3}$/)==null){
		alert("时间格式非法");
		document.getElementById("jssjS").value = "";
		return false;
	}
	return true;
}

function chkTeacherCode(obj) {
	var inputV = document.getElementById(obj).value;
	if(inputV.match(/^\d+\.{0,1}\d{0,3}$/)==null){
		alert("代码必需为数字！");
		return false;
	}
	return true;
}

function getContInfo(){
	if($("ldxmList").options.length < 1 || $("ldxmList").selectedIndex < 0){
		return false;
	}
	$('ldbh').value=$("ldxmList").value;
	refreshForm('jxgljz_ynys.do?method=jxglldfp');
}

function initSetLdBj(){		
	if($("bjFlist").options.length > 0){
		$("bjFlist").options[0].selected = true;
	}
	if($("bjTlist").options.length > 0){
		$("bjTlist").options[0].selected = true;
	}
	if($("ldbh").value == ""){
		$("addLdBjB").disabled = true;
		$("delLdBjB").disabled = true;
		$("bjFlist").disabled = true;
		$("bjTlist").disabled = true;
	}
	if($("bjFlist").options.length < 1){
		$("addLdBjB").disabled = true;
	}
	if($("bjTlist").options.length < 1){
		$("delLdBjB").disabled = true;
	}
}	

function addLdBj(){
	var ldIndx = $("ldxmList").selectedIndex;
	var fromIndx = $("bjFlist").selectedIndex;
	if(ldIndx < 0){
		alert("请选择（双击）连队名称！");
		return false;
	}
	if(fromIndx < 0){
		return false;
	}
	$("bjTlist").options[$("bjTlist").options.length] = new Option($("bjFlist").options[fromIndx].text,$("bjFlist").options[fromIndx].value);
	$("bjFlist").options[fromIndx] = null;
	if($("bjFlist").options.length > 0){
		$("bjFlist").options[0].selected = true;
		$("delLdBjB").disabled = false;
	}else{		
		$("addLdBjB").disabled = true;
	}
	if($("bjTlist").options.length > 0){
		$("bjTlist").options[0].selected = true;
	}
}	
function delLdBj(){
	var ldIndx = $("ldxmList").selectedIndex;
	var toIndx = $("bjTlist").selectedIndex;
	if(ldIndx < 0){
		alert("请选择（双击）连队名称！");
		return false;
	}
	if(toIndx < 0){
		return false;
	}
	$("bjFlist").options[$("bjFlist").options.length] = new Option($("bjTlist").options[toIndx].text,$("bjTlist").options[toIndx].value);
	$("bjTlist").options[toIndx] = null;
	if($("bjTlist").options.length > 0){
		$("bjTlist").options[0].selected = true;
		$("addLdBjB").disabled = false;
	}else{		
		$("delLdBjB").disabled = true;
	}	
	if($("bjFlist").options.length > 0){
		$("bjFlist").options[0].selected = true;
	}
}

function saveLdBj(){
	setEleDisable("button");
	showTips("处理数据中，请等待......");
	for(var i = 0 ; i < $("bjTlist").length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "bjdmList";
		tmp.value = $("bjTlist").options[i].value;
		document.forms[0].appendChild(tmp);
	}
	refreshForm("jxgljz_ynys.do?method=jxglldfp&act=save");
}

function jsghInfoTo(){
	var url = "/xgxt/jxgl.do?method=jxghSearch";
	var doType = document.getElementById('doType').value;
	var pkValue = document.getElementById('pkValue').value;
	
	url += "&doType=";
	url += doType;
	url += "&pkValue=";
	url += pkValue;
	showTopWin(url,700,500);
}

function sendJsghInfo() {
	var jsid = replaceChar(curr_row.cells[0].innerText," ","");	
	var doType = document.getElementById('doType').value;
	var pkValue = document.getElementById('pkValue').value;
	var vel = window.dialogArguments.document.forms[0].jsdm;
	vel.focus();
	vel.value = replaceChar(curr_row.cells[0].innerText," ","");	
	var str = "/xgxt/jxgl.do?method=forwardPage&jsid="+jsid + "&doType=" + doType + "&pkValue=" + pkValue;					
	window.dialogArguments.document.forms[0].action = str;
	window.dialogArguments.document.forms[0].submit(); 	
	window.close();	
}

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
  
 function disableElement(mustDis){
  	var doType = document.getElementById('doType').value;
  	if(doType == "modi"){
  		var eles = mustDis.split("-");
		for (i = 0; i < eles.length; i++) {
			if($(eles[i])){
				document.getElementById(eles[i]).disabled = true;
			}
		}
  	}  
  }
  
  //批量选中
  function check(){
       for(i=0;i<document.getElementsByName("checkOne").length;i++){
      	   document.getElementsByName("checkOne")[i].checked=document.getElementsByName("checkAll")[0].checked;
        }
 }
 
 // 批量删除
 function batch(url){          
	var RowsStr="!!";		  
	for (i=0; i<document.getElementsByName("checkOne").length; i++){
		if(document.getElementsByName("checkOne")[i].checked){
		    RowsStr+=document.getElementsByName("checkOne")[i].value+"!!";
		 }
	}
		   
	document.forms[0].delPk.value = RowsStr;		   
	if (RowsStr=="!!"){
		alert("请选择要删除的记录！\n(单击每条记录前复选框)");
		return false;
	}
		
	if (!confirm("确定要删除所选记录？")){
		return false;
	}   
	refreshForm(url);          
 }
 	
 function searchLs(obj){
	jxglNblg.getJsInfo(obj.value,function(data){
			if(data!=null){
				document.getElementById("xm").value=data[0];
				document.getElementById("xb").value=data[1];
				for(var i=0;i<document.getElementById("mz").length;i++){
					if(data[2] == document.getElementById("mz")[i].value){
						document.getElementById("mz")[i].selected = true;
					}
				}
				for(var i=0;i<document.getElementById("xy").length;i++){
					if(data[3] == document.getElementById("xy")[i].text){
						document.getElementById("xy")[i].selected = true;
					}
				}
			}
		});
}
function jxztrcsz(){
	   var url = "/xgxt/jxgl_jhzy.do?method=jxztrcsz";	  
	   showTopWin(url,420,340);
 }