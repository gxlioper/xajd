function dtjsCommonSave(url,yzdz,yzcd,mustFill){
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("请将带\"*\"号的项目输入完整！");
			return;
		}
	}
	var splitDz = yzdz.split("-");
	var splitCd = yzcd.split("-");
	if(splitDz[0]!=""){
	for (i = 0; i < splitDz.length; i++) {
		var dzsjcd = $(splitDz[i]).cells[1].getElementsByTagName('textarea')[0].value;
		var dzsjmc = $(splitDz[i]).cells[0].innerHTML;
		if (dzsjcd.length>splitCd[i]) {
			alert(dzsjmc+"不能超过"+splitCd[i]+"个字！");
			return;
		}
	}
	}
	document.forms[0].action = url;
	document.forms[0].submit();
}

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
	}
	var pk = curr_row.getElementsByTagName('input')[0].value;
	url+="&doType="+doType;
	url+="&pk="+pk;
	showTopWin(url,w,h);
}

//打开新页面
function openInfo(url){
	if(curr_row == null){
		alert('请选择要操作的数据！');
		return false;
	}
	var pk = curr_row.getElementsByTagName('input')[0].value;
	url+="&pk="+pk;
	window.open(url);
}

//批量提交
function sumitInfo(url,doType){
	var len=jQuery("input:checkbox[name=primarykey_checkVal]:checked").length;
	if(len > 0){
		//if(doType=="del"){
			url+="&doType="+doType;
			if (confirm("确定要删除所勾选的数据?")) {
				showTips('处理数据中，请等待......');
				refreshForm(url);
			}
		//}else{
			
		//}
	}else{
		alert("请勾选要处理的数据");
		return false;
	}
}

function sendXx(){
	var tableName=$("tableName").value;
	var lx="";if($("lx")){lx=$("lx").value};
	var zd="";if($("zd")){zd=$("zd").value};
	var va="";if($("va")){va=$("va").value};
	var url = "/xgxt/czxxDtjsDyxx.do?method=xsxxManage";
	url+="&tableName="+tableName;
	if(lx !=""){
		url+="&lx="+lx;
	}
	if(zd !=""){
		url+="&zd="+zd;
	}
	if(va !=""){
		url+="&va="+va;
	}
	showTopWin(url,800,600);
}

//获得社团社情相关信息
function getStsqInfo(){

	dwr.engine.setAsync(false);
	
	var stdm = $("stdm").value;

	if(stdm !=""){
			
		var tableName="view_stsqdj";
		var colList = ["stmc","clsj","lbdm","zsn1st","zsm1st","zsn2st","zsm2st","zsn3st","zsm3st","xshyj","ytwyj","lxfs","yhm","shzt","xshyj","ytwyj","grjl","hdnr"]
		var pk = "stdm";
		var pkValue = stdm;
	
		getShgzTyDAO.getShgzInfoArr(tableName, pk, pkValue,colList,function(data){
			if( data != null && data.length > 0){
				for (i = 0; i < colList.length; i++) {
					var id = colList[i];
					if($(id)){
						if(data[i] != "" && data[i] != null){
							$(id).value = data[i];
						}else{
							$(id).value = "";
						}
					}
				}
			}
		});
	}
	dwr.engine.setAsync(true);
}

//获得社团社情相关信息
function getStdm(){
	dwr.engine.setAsync(false);
	var stmc=$("stmc").value;
	if(stmc !=""){
		var tableName="view_stsqdj";
		var colList =["stdm"];
		var pk = "stmc";
		var pkValue = stmc;
		getShgzTyDAO.getShgzInfoMap(tableName, pk, pkValue,colList,function(data){
			if(data!=null){
				if(data.stdm !=""){
					$("stdm").value = data.stdm;
				}else{
					$("stdm").value = "";
				}
			}
		});
	}
	dwr.engine.setAsync(true);
}