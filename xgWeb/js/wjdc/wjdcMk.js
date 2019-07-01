//查询问卷信息
function searchWj(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcWjManage";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkWjManage";
	}
	//实习生状况调查
	if (mklx == "sxszk"){
		url+="sxszkWjManage";
	}
	
	//建立时间是否合法
	if(checkSearchTj('querygreaterequal_jlsj','querylessequal_jlsj')){
		allNotEmpThenGo(url);
	}
}

//添加问卷
function addWj(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcWjUpdate";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkWjUpdate";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkWjUpdate";
	}
	
	showTopWin(url,'800','600');
}

//显示问卷
function showWj(doType){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcWjUpdate";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkWjUpdate";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkWjUpdate";
	}
	
	showInfo(url,doType,'800','600')
}

//删除问卷
function delWj(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcWjManage";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkWjManage";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkWjManage";
	}
	sumitInfo(url,'del')
}

//保存问卷
function saveWj(){
	
	var kgkq = $("kgkq");
	var kggb = $("kggb");
	
	if(!kgkq.checked && !kggb.checked){
		alert("请确定问卷开关与否!");
		return false;
	}
	
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";
	
	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcWjUpdate";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkWjUpdate";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkWjUpdate";
	}
	
	url+="&doType=save";
	saveUpdate(url,'wjmc');
}

//查询试题信息
function searchSt(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcStManage";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkStManage";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkStManage";
	}
	
	//建立时间是否合法
	if(checkSearchTj('querygreaterequal_jlsj','querylessequal_jlsj')){
		allNotEmpThenGo(url);
	}
}

//添加试题
function addSt(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcStUpdate";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkStUpdate";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkStUpdate";
	}
	
	showTopWin(url,'800','600');
}

function saveWjfp(){

	var nj = $("nj").value;
	var xydm = $("xydm").value;
	var zydm = $("zydm").value;
	var id = $("id").value;
	
	var xymc = "";
	var zymc = "";
	var wjmc = "";
	
	var msg = "";
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";
	
	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcWjfpUpdate";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkWjfpUpdate";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkWjfpUpdate";
	}
	url+="&doType=save";
	
	if(id == ""){
		alert("问卷不能为空，请确认！！");
		return false;
	}
	
	for(var i=0;i<$("xy").options.length;i++){
		if($("xy").options[i].selected){
			xymc=$("xy").options[i].text;
		}
	}
	
	for(var i=0;i<$("zy").options.length;i++){
		if($("zy").options[i].selected){
			zymc=$("zy").options[i].text;
		}
	}
	
	for(var i=0;i<$("id").options.length;i++){
		if($("id").options[i].selected){
			wjmc=$("id").options[i].text;
		}
	}	
	
	if(nj =="" && xydm == "" && zydm == "" && $("bjR").options.length == "0"){
		msg = "将问卷'" + wjmc + "'分配给全校班级？";
	}else if(nj !="" && xydm == "" && zydm == "" && $("bjR").options.length == "0"){
		msg = "将问卷'" + wjmc + "'分配给" + nj + "全体班级？";
	}else if(xydm != "" && zydm == "" && $("bjR").options.length == "0"){
		msg = "将问卷'" + wjmc + "'分配给";
		if(nj != ""){
			msg += nj + "年级";
		}
		msg += xymc + "全体班级?";
	}else if(zydm != "" && $("bjR").options.length == "0"){
		msg = "将问卷'" + wjmc + "'分配给";
		if(nj != ""){
			msg += nj + "年级";
		}
		if(xymc != ""){
			msg += xymc;
		}
		msg += zymc + "全体班级?";
	}else if($("bjR").options.length != "0"){
		msg = "将问卷'" + wjmc + "'分配给所设置班级？";
	}
	
	if (confirm(msg)) {
	
		for(var i = 0 ; i < $("bjR").options.length; i++){
			var tmp = document.createElement("input");
			tmp.type = "hidden";
			tmp.name = "fpbj";
			tmp.value = $("bjR").options[i].value;
			document.forms[0].appendChild(tmp);
		}
		
		saveUpdate(url,'lx');
	}
}

//显示试题
function showSt(doType){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcStUpdate";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkStUpdate";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkStUpdate";
	}
	showInfo(url,doType,'800','600')
}
//删除试题
function delSt(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcStManage";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkStManage";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkStManage";
	}
	sumitInfo(url,'del')
}

//查询分配信息
function searchFp(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcWjfpManage";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkWjfpManage";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkWjfpManage";
	}
	
	allNotEmpThenGo(url);

}

//设置分配
function szFp(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcWjfpUpdate";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkWjfpUpdate";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkWjfpUpdate";
	}
	showTopWin(url,'800','500')
}

//撤销分配
function delFp(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcWjfpManage";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkWjfpManage";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkWjfpManage";
	}
	sumitInfo(url,'del')
}

//导出分配
function expFp(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcWjfpManage";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkWjfpManage";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkWjfpManage";
	}
	
	url+="&doType=exp";
	wjcfDataExport(url)
}

//查询回答信息
function searchHd(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcHdwjManage";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkHdwjManage";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkHdwjManage";
	}
	
	allNotEmpThenGo(url);
}
	
//显示回答
function showHd(doType){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcHdwjUpdate";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkHdwjUpdate";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkHdwjUpdate";
	}
	showInfo(url,doType,'800','600')
}

//回答问卷
function hdwj(){
	if(curr_row == null){
		alert('请选择要回答的问卷！');
		return false;
	}
	
	var stxx = curr_row.cells[5].innerText;
	if("未组卷 " == stxx){
		alert("该问卷还未进行组卷，请确认！");
		return false;
	}
	
	dwr.engine.setAsync(false);
	var hdqk = curr_row.cells[7].innerText;
	
	var tableName = "wjdc_wjxxb";
	var pk = "id";
	var pkValue = curr_row.getElementsByTagName('input')[0].value; 
	var colList = ["kyxg","dawk"];
	
	var kyxg = "";
	getPjpyInfo.getPjpyInfo(tableName,pk,pkValue,colList,function(data){
		if(data != null){
			kyxg=data.kyxg;
		}
	});
	
	dwr.engine.setAsync(true);
	if(kyxg == "否" && hdqk == "已回答 "){
		alert("该问卷提交后不可再修改，无法继续作答");
		return false;
	}
	
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcHdwjUpdate";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkHdwjUpdate";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkHdwjUpdate";
	}
	
	url+="&lx=hd";
	showInfo(url,'update','800','600');
}

//查询问卷信息
function searchTj(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcHdtjManage";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkHdtjManage";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkHdtjManage";
	}
	allNotEmpThenGo(url);
}

//查询回答结果
function searchJg(){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcHdjgManage";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkHdjgManage";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkHdjgManage";
	}
	
	allNotEmpThenGo(url);
}

function expJg(){

	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?doType=exp&method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcHdjgManage";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkHdjgManage";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkHdjgManage";
	}
	
	expData(url);
}



//显示结果
function showJg(doType){
	var mklx = $("mklx").value;
	var url = "/xgxt/wjdc.do?method=";

	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcHdjgUpdate";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkHdjgUpdate";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkHdjgUpdate";
	}
	
	if(isOverWj()){
		showInfo(url,doType,'800','600');
	}
}

//保存组卷信息
function saveZjInfo(){
		
	var id = $("id").value;
	var wjmc = "";
	
	if(id == ""){
		alert("问卷不能为空，请确认！！");
		return false;
	}
	
	for(var i=0;i<$("id").options.length;i++){
		if($("id").options[i].selected){
		wjmc=$("id").options[i].text;
		}
	}
	
	var url = "/xgxt/wjdc.do?method=";
	var mklx = $("mklx").value;
	
	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcZjManage";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkZjManage";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkZjManage";
	}
	
	url+="&doType=save";
	
	if (confirm("将要为'"+wjmc+"'进行试题组卷，确认所选试题？")) {
	
		for(var i = 0 ; i < $("xyRight").options.length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "fpbh";
		tmp.value = $("xyRight").options[i].value;
		document.forms[0].appendChild(tmp);
		
		var sx = document.createElement("input");
		sx.type = "hidden";
		sx.name = "zjsx";
		sx.value = i;
		document.forms[0].appendChild(sx);
		}
		
		saveUpdate(url,"id");
	}
}

//显示试题信息
function showStInfo(pk){
	var url = "/xgxt/wjdc.do?method=";
	var mklx = $("mklx").value;
		
	//心理普查
	if(mklx == "xlpc"){
		url+="xlpcStUpdate";
	}
	//思想状况调查
	if(mklx == "sxzk"){
		url+="sxzkStUpdate";
	}
	//实习生状况调查
	if(mklx == "sxszk"){
		url+="sxszkStUpdate";
	}
		
	url+="&doType=view";
	url+="&pk="+pk;
	showTopWin(url,'600','480');
}

	function startTj(){
	
		var nj = $("nj").value;
		var xy = $("xy").value;
		var zy = $("zy").value;
		var bj = $("bj").value;
		var xb = $("xb").value;
		var zzmm = $("zzmm").value;
		var mklx = $("mklx").value;
		var url = "/xgxt/wjdc.do?method=";
		//心理普查
		if(mklx == "xlpc"){
			url+="xlpcHdtjUpdate";
		}
		//思想状况调查
		if(mklx == "sxzk"){
			url+="sxzkHdtjUpdate";
		}
		//实习生状况调查
		if(mklx == "sxszk"){
			url+="sxszkHdtjUpdate";
		}
		url+="&nj="+nj;
		url+="&xy="+xy;
		url+="&zy="+zy;
		url+="&bj="+bj;
		url+="&xb="+xb;
		url+="&zzmm="+zzmm;
		
		showInfo(url,'view','800','600');
		
		dd_html = "";
		tmpdiv1.innerHTML = dd_html;
	}