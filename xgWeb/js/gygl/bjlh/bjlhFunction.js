var TWDM = "0202";//团委部门代码
var TYDM = "0405";//体育教学部部门代码
var KYDM = "0110";//科研部门代码
var CJDM = "0117";//成教部门代码

//打开新窗口
function showInfo(url,doType,w,h){
	if(doType == "update"){
		if(curr_row == null){
			alert('请选择要修改的数据！');
			return false;
		}
	}else if(doType == "sh"){
		if(curr_row == null){
			alert('请选择审核的数据！');
			return false;
		}
	}else if(doType == "view"){
		if(curr_row == null){
			alert('请选择要查看的数据！');
			return false;
		}
	}
	var pk = curr_row.getElementsByTagName('input')[0].value;
	url+="&doType="+doType;
	url+="&pk="+pk;
	showTopWin(url,w,h);
}

//批量提交
function sumitInfo(url,doType){
	var len=jQuery("input:checkbox[name=primarykey_checkVal]:checked").length;
	if(len > 0){
		if(doType=="del"){
			url+="&doType="+doType;
			if (confirm("确定要删除所勾选的数据?")) {
				showTips('处理数据中，请等待......');
				refreshForm(url);
			}
		}else if(doType=="sh"){
			url+="&doType="+doType;
			if (confirm("确定勾选数据的审核状态?")) {
				showTips('处理数据中，请等待......');
				refreshForm(url);
			}
		}else if(doType=="tf"){
			url+="&doType="+doType;
			if (confirm("确定对勾选学生做退房处理?")) {
				showTips('处理数据中，请等待......');
				refreshForm(url);
			}
		}
	}else{
		alert("请勾选要处理的数据");
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
	
	if(lx=="团委生"){
		xydm = TWDM;
	}else if (lx=="体优生"){
		xydm = TYDM;
	}else if (lx=="研究生"){
		xydm = KYDM;
	}else if (lx=="成教生"){
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
