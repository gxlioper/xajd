function selectWidgetParentFunc( widgetObj, ActionFun){
	if(widgetObj.id == 'njList'){
		getList.getNjList(ActionFun);
	}
}


function operHcpydk(type){
	var xh=document.getElementById("userName").value;
	if(curr_row==null)	{
		alert("��ѡ��Ҫ��������");
		//return false;
	}	
	var val=curr_row.cells[7].innerText;
	val=val.substring(0,2);	
	if(val=='����'){
		alert("�˴��г���Ʊ�Ѿ�����!");
		return false;
	}else{
		var cc=curr_row.cells[0].innerText;	
		var zdz = curr_row.cells[2].innerText;
		var pj = curr_row.cells[6].innerText;	
		getInsureInfo.AddYdkInfo(cc,xh,zdz,pj,type,function(data){
			if(data=="true"){
				alert("�����ɹ�!");
			}
			if(data=="false"){
				alert("����ʧ��!");
			}
		
		});
	}
	}

function getTrainInfo(){	
	var cc=document.getElementById("cc").value;	
	var info=null;
	var qdz="";
	var zdz="";	
	getInsureInfo.getTrainInfoByCc(cc,function(data){		
		info=data.split(",");		
		qdz=info[0];		
		zdz=info[1];		
		if($("qdz")){
			document.getElementById("qdz").value=qdz;
		}
		if($("zdz")){
			document.getElementById("zdz").value=zdz;
		}
	
	});
}

function getZdzInfo(){
 var xh = document.getElementById("xh").value;
 var cc = document.getElementById("cc").value;
 getInsureInfo.getZdzInfo(xh,cc,function(data){
 	var info = data.split(",");
 	if($("zdz")){
 		document.getElementById("zdz").value = info[0];
 	}
 	if($("pj")){
 		document.getElementById("pj").value = info[1];
 	}
 });
}

function setPK(){
	if(event.button==2){
			
	}
}



//to-do create the function for return the list

//function 
