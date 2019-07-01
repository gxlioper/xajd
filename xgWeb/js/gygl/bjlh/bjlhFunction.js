var TWDM = "0202";//��ί���Ŵ���
var TYDM = "0405";//������ѧ�����Ŵ���
var KYDM = "0110";//���в��Ŵ���
var CJDM = "0117";//�ɽ̲��Ŵ���

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

function setNjList(lx){
	var objId = "nj";
	getBjlhGyglDAO.getNjList(lx,function(data){
		if(data !=null && data.length >0){
			DWRUtil.removeAllOptions(objId);			
			DWRUtil.addOptions(objId,data,"dm","mc");
			$(objId).options[0].value="";
		}
	});
}

function setXyList(lx){
	var objId = "xydm";
	getBjlhGyglDAO.getXyZyBjList("xy",lx,"","","",function(data){
		if(data !=null && data.length >0){
			DWRUtil.removeAllOptions(objId);			
			DWRUtil.addOptions(objId,data,"dm","mc");
			$(objId).options[0].value="";
		}
	});
}

function setZyList(lx){
	var nj = $("nj").value;
	var xydm = $("xydm").value;
	var objId = "zydm";
	getBjlhGyglDAO.getXyZyBjList("zy",lx,nj,xydm,"",function(data){
		if(data !=null && data.length >0){
			DWRUtil.removeAllOptions(objId);			
			DWRUtil.addOptions(objId,data,"dm","mc");
			$(objId).options[0].value="";
		}
	});
}

function setBjList(lx){
	var nj = $("nj").value;
	var xydm = $("xydm").value;
	var zydm = $("zydm").value;
	var objId = "bjdm";
	getBjlhGyglDAO.getXyZyBjList("bj",lx,nj,xydm,zydm,function(data){
		if(data !=null && data.length >0){
			DWRUtil.removeAllOptions(objId);			
			DWRUtil.addOptions(objId,data,"dm","mc");
			$(objId).options[0].value="";
		}
	});
}

function setLdList(){
	var xqdm = $("xqdm").value;
	var objId = "lddm";
	getBjlhGyglDAO.getXqLdList("ld",xqdm,"",function(data){
		if(data !=null && data.length >0){
			DWRUtil.removeAllOptions(objId);			
			DWRUtil.addOptions(objId,data,"dm","mc");
			$(objId).options[0].value="";
		}
	});
}

function setXqList(){
	var lddm = $("lddm").value;
	var objId = "xqdm";
	getBjlhGyglDAO.getXqLdList("xq","",lddm,function(data){
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
	getBjlhGyglDAO.getCsList(lddm,function(data){
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
	if($("tableName")){
		tableName = $("tableName").value;
	}
	
	if(lx=="��ί��"){
		xydm = TWDM;
	}else if (lx=="������"){
		xydm = TYDM;
	}else if (lx=="�о���"){
		xydm = KYDM;
	}else if (lx=="�ɽ���"){
		xydm = CJDM;
	}

	getBjlhGyglDAO.getQsList(lddm,cs,fplx,xydm,tableName,function(data){
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
	
	getBjlhGyglDAO.getCwList(lddm,cs,qsh,xh,function(data){
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
	getBjlhGyglDAO.isCz(tableName,pk,pkValue,function(data){
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
