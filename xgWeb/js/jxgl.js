
function SaveArmy(url,table){
	var values=document.forms[0].notnull.value;
	var xh=document.forms[0].xh.value;
	var tvalue=values.split("-");
	for(var i=0;i<tvalue.length;i++){
		if(document.getElementById(tvalue[i]).value==""){
		alert("�뽫�����ŵ���Ŀ��д������");
		return false;
		}
	}
	if(table=="xsjxbzb"){
		url+="&bzh=";
		url+=document.forms[0].bzh.value;
	}
	if(table=="xsjxcfb"){
		url+="&cfh=";
		url+=document.forms[0].cfh.value;
	}	
	getXjydInfo.getColumnEx("view_xsjbxx","xh",xh,function(data){				
		if(data==false){
			alert('ѧ�Ų����ڣ�');
			return false;
		}
		document.forms[0].action=url;
		document.forms[0].submit();			
		alert("����ɹ���");	
		window.close();
		window.dialogArguments.document.getElementById("search_go").click();					
	});		
}

function refreshPage(url,page){
	if(page=="jx_data"){
		url+="act=";
		url+=document.forms[0].act.value;
	}		
	document.forms[0].action=url;
	document.forms[0].submit();
}

function ResultCount(){
	var xlcj = 0;
	var kscj = 0;
	var xlcjbl = parseInt(document.getElementById("xlcjbl").value)/100;
	var kscjbl = parseInt(document.getElementById("kscjbl").value)/100;
	if(xlcjbl == "" || xlcjbl == null || kscjbl == "" || kscjbl == null){
		alert("��������Ϊ��!");
		return false;
	}
	if(xlcjbl + kscjbl != 1){
		alert("����֮����Ϊ100!");
	}
	var xlcjArray = document.all("xlcjtext");
	var kscjArray = document.all("kscjtext");
	var cj = document.all("text");
	for(i=0; i<cj.length; i++){
		if(xlcjArray[i].value!="" && xlcjArray[i].value != null){
			xlcj = parseInt(xlcjArray[i].value);
		}else{
			xlcj = 0;
		}
		if(kscjArray[i].value!="" && kscjArray[i].value != null){
			kscj = parseInt(kscjArray[i].value);
		}else{
			kscj = 0;
		}
		cj[i].value = (xlcj * xlcjbl + kscj * kscjbl).toFixed(1);
	}
}