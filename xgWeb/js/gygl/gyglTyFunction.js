
//���´���
function showInfo(url,doType,w,h){
	if(doType == "update"){
		if(curr_row == null){
			alert('��ѡ��Ҫ�޸ĵ����ݣ�');
			return false;
		}
	}else if(doType == "sh"){
		if(curr_row == null){
			alert('��ѡ����˵����ݣ�');
			return false;
		}
	}else if(doType == "view"){
		if(curr_row == null){
			alert('��ѡ��Ҫ�鿴�����ݣ�');
			return false;
		}
	}else{
		if(curr_row == null){
			alert('��ѡ��Ҫ���������ݣ�');
			return false;
		}
	}
	var pk = curr_row.getElementsByTagName('input')[0].value;
	url+="&doType="+doType;
	url+="&pk="+pk;
	showTopWin(url,w,h);
}

//�����ύ
function sumitInfo(url,doType){
	var len=jQuery("input:checkbox[name=primarykey_checkVal]:checked").length;
	if(len > 0){
		if(doType=="del"){
			url+="&doType="+doType;
			if (confirm("ȷ��Ҫɾ������ѡ������?")) {
				showTips('���������У���ȴ�......');
				refreshForm(url);
			}
		}else if(doType=="sh"){
			url+="&doType="+doType;
			if (confirm("ȷ����ѡ���ݵ����״̬?")) {
				showTips('���������У���ȴ�......');
				refreshForm(url);
			}
		}else if(doType=="tf"){
			url+="&doType="+doType;
			if (confirm("ȷ���Թ�ѡѧ�����˷�����?")) {
				showTips('���������У���ȴ�......');
				refreshForm(url);
			}
		}
	}else{
		alert("�빴ѡҪ���������");
		return false;
	}
}

function setYqList(){
	var xqdm = $("xqdm").value;
	var objId = "yqdm";
	getGyglDAO.getXqYqLdList("yq",xqdm,"","",function(data){
		if(data !=null && data.length >0){
			DWRUtil.removeAllOptions(objId);			
			DWRUtil.addOptions(objId,data,"dm","mc");
			$(objId).options[0].value="";
		}
	});
}

function setYqLdList(){
	var xqdm = $("xqdm").value;
	var yqdm = $("yqdm").value;
	var objId = "lddm";
	getGyglDAO.getXqYqLdList("ld",xqdm,yqdm,"",function(data){
		if(data !=null && data.length >0){
			DWRUtil.removeAllOptions(objId);			
			DWRUtil.addOptions(objId,data,"dm","mc");
			$(objId).options[0].value="";
		}
	});
}

function setLdList(){
	var userName = $("userName").value;
	var xqdm = $("xqdm").value;
	var objId = "lddm";
	getGyglDAO.getXqLdList("ld",xqdm,"",userName,function(data){
		if(data !=null && data.length >0){
			DWRUtil.removeAllOptions(objId);			
			DWRUtil.addOptions(objId,data,"dm","mc");
			$(objId).options[0].value="";
		}
	});
}

function setXqYqList(){
	var yqdm = $("yqdm").value;
	var objId = "xqdm";
	getGyglDAO.getXqYqLdList("xq","",yqdm,"",function(data){
		if(data !=null && data.length >0){
			for(var i=0;i<$(objId).options.length;i++){
				if(yqdm !=""){
					if($(objId).options[i].value == data[1].dm)
					{
						$(objId).options[i].selected = "true";
					}
				}else{
					$(objId).options[0].selected = "true";
				}
			}
		}
	});
}

function setLdYqList(){
	var lddm = $("lddm").value;
	var objId = "yqdm";
	getGyglDAO.getXqYqLdList("yq","","",lddm,function(data){
		if(data !=null && data.length >0){
			for(var i=0;i<$(objId).options.length;i++){
				if(lddm !=""){
					if($(objId).options[i].value == data[1].dm)
					{
						$(objId).options[i].selected = "true";
					}
				}else{
					$(objId).options[0].selected = "true";
				}
			}
		}
	});
}

function setXqList(){
	var userName = $("userName").value;
	var lddm = $("lddm").value;
	var objId = "xqdm";
	getGyglDAO.getXqLdList("xq","",lddm,userName,function(data){
		if(data !=null && data.length >0){
			for(var i=0;i<$(objId).options.length;i++){
				if(lddm !=""){
					if($(objId).options[i].value == data[1].dm)
					{
						$(objId).options[i].selected = "true";
					}
				}else{
					$(objId).options[0].selected = "true";
				}
			}
		}
	});
}

function setCsList(){
	var lddm = $("lddm").value;
	var objId = "cs";
	getGyglDAO.getCsList(lddm,function(data){
		if(data !=null && data.length >0){
			if(data !=null && data.length >0){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"dm","mc");
				$(objId).options[0].value="";
			}
		}
	});
}

function setQsList(){
	var tableName = "";
	var lddm = $("lddm").value;
	var cs = $("cs").value;
	var blbj = "";
	var fplx = "";
	var xydm = "";
	var objId = "qsh";
	var lx = "";
	
	if($("lx")){
		lx = $("lx").value;
	}
	
	if($("fplx")){
		fplx = $("fplx").value;
	}
	if($("xydm")){
		xydm = $("xydm").value;
	}
	
	getGyglDAO.getQsList(lddm,cs,"",function(data){
		if(data !=null && data.length >0){
			if(data !=null && data.length >0){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"dm","mc");
				$(objId).options[0].value="";
			}
		}
	});
}

function setCwList(){
	var lddm = $("lddm").value;
	var cs = $("cs").value;
	var qsh = $("qsh").value;
	var xh =  $("xh").value;
	var objId = "cwh";
	
	getGyglDAO.getCwList(lddm,cs,qsh,xh,function(data){
		if(data !=null && data.length >0){
			if(data !=null && data.length >0){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"dm","mc");
				$(objId).options[0].value="";
			}
		}
	});
}

function isCz(pk,pkValue){
	var flag = false;
	var tableName = $("realTable").value;
	var url = $("url").value;
	
	dwr.engine.setAsync(false);
	getGyglDAO.isCz(tableName,pk,pkValue,function(data){
		if(data){
			flag = true;
		}
	});
	dwr.engine.setAsync(true);
	
	if(flag){
		url+=pkValue;
		refreshForm(url);
	}
}

//���´���
function showOpenInfo(url,doType,mklx,w,h){

	var pk = "";
	
	if(doType == "update"){
		if(curr_row == null){
			alert('��ѡ��Ҫ�޸ĵ����ݣ�');
			return false;
		}
	}else if(doType == "sh"){
		if(curr_row == null){
			alert('��ѡ����˵����ݣ�');
			return false;
		}
	}else if(doType == "sb"){
		if(curr_row == null){
			alert('��ѡ��Ҫ�걨�����ݣ�');
			return false;
		}
	}
	
	if(doType != "add"){
		pk = curr_row.getElementsByTagName('input')[0].value;
	}
	
	url+="&doType="+doType;
	url+="&pk="+pk;
	url+="&mklx="+mklx;
	
	showOpenWindow(url,w,h);
}