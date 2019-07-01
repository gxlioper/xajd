
//打开新窗口
function showInfo(url,doType,w,h){
	if(doType == "update"){
		if(curr_row == null){
			alertInfo('请选择要修改的数据！');
			return false;
		}
	}else if(doType == "sh"){
		if(curr_row == null){
			alertInfo('请选择审核的数据！');
			return false;
		}
	}else if(doType == "view"){
		if(curr_row == null){
			alertInfo('请选择要查看的数据！');
			return false;
		}
	}else{
		if(curr_row == null){
			alertInfo('请选择要操作的数据！');
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
	var checkBoxArr = document.getElementsByName("checkVal");
	var flag = false;
	
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	
	if(flag){
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
		alertInfo("请勾选要处理的数据");
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

//打开新窗口
function showOpenInfo(url,doType,mklx,w,h){

	var pk = "";
	
	if(doType == "update"){
		if(curr_row == null){
			alertInfo('请选择要修改的数据！');
			return false;
		}
	}else if(doType == "sh"){
		if(curr_row == null){
			alertInfo('请选择审核的数据！');
			return false;
		}
	}else if(doType == "sb"){
		if(curr_row == null){
			alertInfo('请选择要申报的数据！');
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