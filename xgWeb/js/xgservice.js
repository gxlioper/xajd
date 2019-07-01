/**
 *   指定增加学号字段数据出处
 *   luojw
 *   2010-6-28
 */
 
function sendXx(){
	//页面存隐藏域，指定所查询数据出处，例如（<input type="hidden" id="tableName" name="tableName" value="view_ybdyxx"/>）
	var tableName=$("tableName").value;
	//数据源的描述，展示jsp的表头，例如（<input type="hidden" id="lx" name="lx" value="预备党员"/>）
	var lx="";
	//数据源的特殊条件，例如:
	//<input type="hidden" id="zd" name="zd" value="sfty-xxsh"/>
	//<input type="hidden" id="va" name="va" value="是-通过"/>
	var zd="";
	var va="";
	var mklx="";
	var url = "commXgInfo.do?method=xsxxManage";
	
	if($("lx")){
		lx=$("lx").value
	};
	if($("zd")){
		zd=$("zd").value
	};
	if($("va")){
		va=$("va").value
	};
	if($("mklx")){
		mklx=$("mklx").value
	};
	
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
	if(mklx !=""){
		url+="&mklx="+mklx;
	}
	//alert(22);
	//showDialog("", 800, 600, url);
	showTopWin(url,800,600);
}

//勾选一条记录修改
//checkboxName ： 复选框名字
//currPage :true-刷新本页面（非弹出窗口）
//message:若不选或多选弹出的提示消息
//作者：屈朋辉
function showUpdateWindow(checkboxName,url,width,height,currPage,message){
	var pkValues = document.getElementsByName(checkboxName);
	var tempArr=new Array(),n=0 ;
	
	for (var i = 0 ; i < pkValues.length ; i++){
		
		if (pkValues[i].checked){
			if (pkValues[i].alt=='disabled'){
				alertInfo('您选择的项目因正在审核或已审核<br/>不能修改,请确认');
				return false;
			} else{
				tempArr[n] = pkValues[i];
				n++;
			}
		}
	}
	
	if (tempArr.length != 1){
		if (null == message){
			alertInfo("请勾选一条您要修改的数据");
		} else {
			alertInfo(message);
		}
	
		return false;
	} else if (currPage){
		url+="&pkValue="+tempArr[0].value;
		refreshForm(url);
	} else {
		url+="&pkValue="+tempArr[0].value;
		showTopWin(url,width,height);
	}
}


function showViewWindow(checkboxName,url,width,height,currPage,message){
	var pkValues = document.getElementsByName(checkboxName);
	var tempArr=new Array(),n=0 ;
	
	for (var i = 0 ; i < pkValues.length ; i++){
		
		if (pkValues[i].checked){
			tempArr[n] = pkValues[i];
			n++;
		}
	}
	
	if (tempArr.length != 1){
		if (null == message){
			alertInfo("请勾选一条您要查看的数据");
		} else {
			alertInfo(message);
		}
	
		return false;
	} else if (currPage){
		url+="&pkValue="+tempArr[0].value;
		refreshForm(url);
	} else {
		url+="&pkValue="+tempArr[0].value;
		showTopWin(url,width,height);
	}
}





//功能：审核
//使用说明：勾选一条记录弹出单个审核页面;勾选多条弹出批量审核层。
//			需调用单独方法plsh();
//注意点:方法中对已审核的记录做了过滤，若勾选的记录中有已审核（“通过”或者“不通过”）的记录
//     会弹出提示消息并中止操作。过滤方法：根据checkbox上的alt属性，
//     若其属性值为disabled，则说明其为已审核的记录。
//作者：屈朋辉
function showAuditingWindow(checkboxName,oneAuditingUrl,width,height){
			
	var pkValues = document.getElementsByName(checkboxName);
	var tempArr=new Array(),n=0 ;
	
	for (var i = 0 ; i < pkValues.length ; i++){
		
		 if (pkValues[i].checked){
		 
		 	if (pkValues[i].alt=="disabled" || pkValues[i].alt=="th"){
				alertInfo("您勾选的记录中有已审核或退回的数据，请确认");
				return false;
			} 
		 
			tempArr[n] = pkValues[i];
			n++;
		}
	}
	
	if (tempArr.length == 0){
		alertInfo("请勾选您要审核的记录!");
		return false;
	} else if (tempArr.length == 1){
		oneAuditingUrl+="&pkValue="+tempArr[0].value;
		showTopWin(oneAuditingUrl,width,height);
	} else {
		plsh();
	}
}


function showPrintWindow(checkboxName,url,message){
	var pkValues = document.getElementsByName(checkboxName);
	var tempArr=new Array(),n=0 ;
	
	for (var i = 0 ; i < pkValues.length ; i++){
		if (pkValues[i].checked){
			tempArr[n] = pkValues[i];
			n++;
		}
	}
	
	if (tempArr.length != 1){
		if (null == message){
			alertInfo("请勾选一条您要修改的数据");
		} else {
			alertInfo(message);
		}
	
		return false;
	} else{
		document.forms[0].action = url+"&pkValue="+tempArr[0].value;
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	} 
}






//功能：查询记录列表双击击一行记录显示该条记录的详细信息
//注意点:记录行的第一列必须是隐藏的主键文本
//作者：lr
function showDetailWindow(url,width,height,self,message){
	var pk="";		
	message = message == null || message == '' ? "请选择一行记录！\n单击一行即可!" : message; 
	if(curr_row == null ){
		alert(message);
		return false;
	} 		
	pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
	url += "&pkValue="+pk;	
	
	self == true ? refreshForm(url) :  showTopWin(url, width, height);
}

//功能：查询记录的批量操作
//作者：lr
function bachAction(url,tagName,confirmMsg) {
	var checkBoxArr = document.getElementsByName(tagName);
	var flag = false;
	confirmMsg = confirmMsg == null || confirmMsg == '您确定操作选择的记录吗？' ? '' : confirmMsg;
	
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){		
		if(confirm(confirmMsg)){
			refreshForm(url);
		}
		return true;
	}else{
		alert("没有选择相应记录，请选择记录后再进行操作!");
		return false;
	}
}

//功能：提示后进行操作
//作者：lr
function confirmAction(url, confirmMsg){
	if(confirm(confirmMsg)){
		refreshForm(url);
	}else{
		return false;
	}
}

var array = [];	
//检测是否有勾中记录,如果是勾中的放入数组供批量操作用
function isChecked(tagName) {
	array = new Array();

	var checkBoxArr = document.getElementsByName(tagName);
	var flag = false;
	var n = 0;
	for (var i = 0; i < checkBoxArr.length; i++) {
		if (checkBoxArr[i].checked == true) {
			flag = true;
			array[n] = checkBoxArr[i].value;
			n++;
		}
	}
	if (flag) {
		return true;
	} else {
		alertInfo("\u6ca1\u6709\u9009\u62e9\u76f8\u5e94\u8bb0\u5f55\uff0c\u8bf7\u9009\u62e9\u4e4b\u540e\u518d\u8fdb\u884c\u64cd\u4f5c!");
		return false;
	}
}


//检测必填项是否为空
function checkBtx(id){

	var key = new Array();
	if(id!=""){
		key=id.split("-");
	}

	if(key.length > 0){
		for(var i=0;i<key.length;i++){
			if($(key[i])){
				if($(key[i]).value == ""){
					alert('带"*"项不能为空，请确认');
					return false;
				}
			}
		}
	}
	
	return true;
}


function doDel(checkboxName,url){
	var checkbox = jQuery('input[name='+checkboxName+']:checked');
	
	if(checkbox.length == 0){
		alertInfo('请选择您要删除的数据');
		return false;
	} else {
		for (var i = 0 ; i < checkbox.length ; i++){
		 	if (jQuery(checkbox[i]).attr('alt')=="disabled"){
				alertInfo("您勾选的记录中有正在审核或已审核<br/>的数据，请确认");
				return false;
			} 
		}
	}
	batchData(checkboxName,url,'del');	
}
