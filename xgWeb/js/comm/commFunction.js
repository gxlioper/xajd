var temp;
//保存数据并验证主键是否为空
function saveUpdate(url,pk){
	url = url.replace("/xgxt/","");
	var key = new Array();
	if(pk!=""){
		key=pk.split("-");
	}

	if(key.length > 0){
		for(var i=0;i<key.length;i++){
			if($(key[i])){
				if($(key[i]).value == ""){
					alertError("带\"*\"项目不能为空，请确认");
					return false;
				}
			}
		}
	}
	showTips('处理数据中，请等待......');
	refreshForm(url);
}

//update 2013.06.21 兼容问题
function showOpenInfoNew(url,doType,w,h){
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
	}else if(doType == "cz"){
		if(curr_row == null){
			alertInfo('请选择要操作的数据！');
			return false;
		}
	}else if(doType == "view"){
		if(curr_row == null){
			alertInfo('请选择要查看的数据！');
			return false;
		}
	}
	var pk = curr_row.getElementsByTagName('input')[0].value;
	url+="&doType="+doType;
	url+="&pk="+pk;
	showDialog('',w,h,url);
	//showTopWin(url,w,h);
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
	if(mklx != ""){
		url+="&mklx="+mklx;
	}
	
	showOpenWindow(url,w,h);
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
	if(mklx != ""){
		url+="&mklx="+mklx;
	}
	
	showOpenWindow(url,w,h);
}

//打开新页面
function openInfo(url){
	if(curr_row == null){
		alertInfo('请选择要操作的数据！');
		return false;
	}
	var pk = curr_row.getElementsByTagName('input')[0].value;
	url+="&pk="+pk;
	window.open(url);
}

//批量提交
function sumitInfo(url,doType){
	var del_message = "";
	if($("del_message")){
		del_message = $("del_message").value;
	}
	var len=jQuery("input:checkbox[name=primarykey_checkVal]:checked").length;
	if(len > 0){
		if(del_message == ""){
			if(doType=="del"){
				del_message = "确定要删除所勾选的数据?";
			}else if(doType=="sh"){
				del_message = "确定所勾选数据的审核状态?";
			}else if(doType=="submit"){
				del_message = "确定要提交所勾选的记录数据?";
			}
		}
		
		url+="&doType="+doType;
		confirmInfo(del_message,function(tag){
			if(tag=="ok"){
			showTips('处理数据中，请等待......');
			refreshForm(url);
			}
		});
		
	}else{
		alertInfo("请勾选要处理的数据");
		return false;
	}
}

//更多条件
function showGdtj(trId){

	var key = new Array();

	if(trId!=""){
		key=trId.split("-");
	}

	if(key.length > 0){
		for(var i=0;i<key.length;i++){
			var id = "tr"+key[i];
			if($(id)){
				if($("gdtj").checked){
					$(id).style.display="";
				}else{
					$(id).style.display="none";
				}
			}
		}
	}
}

//回车事件
function pressEnter(evt){
	evt=evt?evt:(window.event?window.event:null);
	var key = evt.keyCode;
	//回车
	if(key == "13"){
		return true;
	}else{
		return false;
	}		
}

//固定焦点
function setFocus(id){
	if($(id)){
		$(id).focus();
	}
}

// 检查是否唯一
function checkUnique(tab,obj,id){
	var pkValue = obj.value;
	
	if(pkValue != ""){
		basicOperaDwr.checkExists(tab, pkValue, function(data) {
			if (data) {
				displayMsgDiv(id);
				$('buttonSave').disabled = "disabled";
			} else {
				hideMsgDiv(id);
				$('buttonSave').disabled = "";
			}
		});
	}
}

//显示提示语句
function displayMsgDiv(id){
	if($(id)){
		var msgdiv = $(id);
			msgdiv.className = "msg_prompt";
	}
}
		
//隐藏提示语句
function hideMsgDiv(id){
	if($(id)){
		var msgdiv = $(id);
			msgdiv.className = "hide";
	} 
}

//返回首页
function returnHomPage(lx){
	
	var userType = $("userType").value;
	var url = "";
		
	if("stu" == userType){
		url = "/xgxt/stuPage.jsp";	
	}else{
		url = "/xgxt/teaPage.jsp";
	}
	
	if(lx == "ben"){//本页面
		refreshForm(url);
	}else{
		parent.document.location = url;
	}
}

//高级查询
function showSearchTj(){
	var url = "/xgxt/seachTjManage.do?method=SeachTj";
	if($("path")){
		url+="&path="+$("path").value;
	}
	showTopWin(url,"600","480");
}

//修改显示数目
function updatePageSize() 
{
	var pageNo = parseInt($("pageNo").value);
	var maxPage = parseInt($("maxPage").value);
	var currentPage=parseInt($("currentPage").value);
	var pageSize = $("showNum").value.replace(/[^(\d)]/g,'');
	
	if (pageNo > maxPage) {
		return;
	}else{
		$("currentPage").value =pageNo;
	}
	
	if(pageSize !=""){
		$("pageRsSize").value = pageSize;
		$("editPageSize").value = "yes";
	}
	
	clickLeftMenu();
}

//上一页
function prePage(){

	var currentPage=parseInt($("currentPage").value);
	var maxPage = parseInt($("maxPage").value);

	if (currentPage == 1 || currentPage==0) {
		return;
	}else {
		var newPage = parseInt(currentPage - 1);
		$("currentPage").value = newPage;
		$("pageNum").value = newPage;
	}
	
	clickLeftMenu();
}

//下一页
function nextPage(){
	var currentPage=parseInt($("currentPage").value);
	var maxPage = parseInt($("maxPage").value);
	
	if (maxPage == currentPage) {
		return;
	}else {
		var newPage = parseInt(currentPage + 1);
		$("currentPage").value = newPage;
		$("pageNum").value = newPage;
	}
	
	clickLeftMenu();
}

//首页
function firstPage() {
	var currentPage=parseInt($("currentPage").value);
	if (currentPage == 1 || currentPage == 0) {
		return;
	}
	$("currentPage").value = "1";
	$("pageNum").value = "1";
	
    clickLeftMenu();
}

//末页
function lastPage() {
	var currentPage=parseInt($("currentPage").value);
	var maxPage = parseInt($("maxPage").value);
	if (currentPage == maxPage) {
		return;
	} else {
		$("currentPage").value = maxPage;
		$("pageNum").value = maxPage;
	}
	
	clickLeftMenu();
}


/** 隐藏模块 */
function hiddenMk(id){
	document.getElementById(id).style.display=(document.getElementById(id).style.display=='none')? '':'none'
}

/**
	将焦点定在id为JD处
*/
function jd(){
	if($("jd")){
		$("jd").focus();
	}
}

//设置查询提示层位置
function setSearchMsgWz(left,top){
	
	if($("input_mhcx_msg")){
		$("input_mhcx_msg").style.left=left;
		$("input_mhcx_msg").style.top=top;
	}
}

//点击侧边栏
function setLiClick(num){
	
	var li_num = document.getElementsByTagName('li').length;
	

	for(var i=0;i<li_num;i++){
	
		var obj = document.getElementsByTagName('li')[i];
		
		if(obj.className = "Child"){
			var id = "li_"+i;
			if($(id)){
				$(id).style.background="";

			}
		}
	}
	
	var id = "li_"+num;
	
	if($(id)){
		$(id).style.background="#dce8f8";
		var xmdm = $(id.replace("li_","xmdm_")).value;
	
		if($("shxm")){
			$("shxm").value = xmdm;
		}
	}
}

String.prototype.replaceAll = function(s1,s2) { 
	  return this.replace(new RegExp(s1,"gm"),s2); 
}

//显示隐藏内容
function showHiddenNr(id){
	if($(id)){
		if($(id).style.display == "none"){
			$(id).style.display = "";
		}else{
			$(id).style.display = "none";
		}
	}
}

//生成层	
function showWaitingDiv(time){

	document.getElementById("divWaiting").style.display="";
	document.getElementById("divDisable").style.display="";

	setTimeout("hiddenWaitingDiv()",time);
}

//隐藏提示信息
function hiddenWaitingDiv(){
	document.getElementById("divWaiting").style.display="none";
	document.getElementById("divDisable").style.display="none";
}

//查询结果集
function searchRsByAjax(url,otherValue){
	
	var currentPage = "1";
	if($("currentPage")){
		currentPage = $("currentPage").value;
	}

	var editPageSize = "";
	if($("editPageSize")){
		editPageSize = $("editPageSize").value;	
	}
		
	var pagesize = "";
	if($("pagesize")){
		pagesize = $("pagesize").value;
	}

	//模糊查询
	var input_mhcx = "";
	if($("input_mhcx")){
		input_mhcx = $("input_mhcx").value;
	}
	
	var mhcx_lx = "";
	if($("mhcx_lx")){
		mhcx_lx = $("mhcx_lx").value;
	}
	//点击查询
	var searchLx = new Array();
	var searchTj = new Array();
	var searchTjz = new Array();
	
	var n=0;
	var m=3;
	
	searchLx[0]="xy";
	searchLx[1]="zy";
	searchLx[2]="bj";
	for(var i=0;i<jytj.length;i++){
		searchLx[m]=jytj[i];
		m++;
	}

	var tj_num = $("searchTjDiv").getElementsByTagName('input').length;
		
	for(var j=0;j<tj_num;j++){
		var obj = $("searchTjDiv").getElementsByTagName('input')[j];
		searchTj[n]=obj.name;
		searchTjz[n]=escape(obj.value);
		n++;
	}
	
	//排序
	var arrange = "";
	if($("arrange")){
		arrange = jQuery("#arrange").val();
	}
	
	var fashion = "";
	if($("fashion")){
		fashion = jQuery("#fashion").val();
	}
	
	//其他数据
 	var parameter = {
		"currentPage":currentPage,
		"editPageSize":editPageSize,
		"pagesize":pagesize,
		"input_mhcx":escape(input_mhcx),
		"mhcx_lx":mhcx_lx,
		"searchTj":searchTj.join("!!@@!!"),
		"searchTjz":searchTjz.join("!!@@!!"),
		"searchLx":searchLx.join("!!@@!!"),
		"arrange":arrange,
		"fashion":fashion,
		"otherValue":otherValue.join("!!@@!!")
	};
  
 	$("divWaiting").style.display="";
	$("divDisable").style.display="";
		
	jQuery("#div_rs").load(url,parameter,function(){
		setTimeout("setPageInfo()",500);
		$("divWaiting").style.display="none";
		$("divDisable").style.display="none";
	});

}


function getSuperSearch(){
	
	//模糊查询
	var input_mhcx ,mhcx_lx ,fashion,plcx_xh,plcx_xm;
	if(jQuery("#plcx_xh")){
		plcx_xh = jQuery("#plcx_xh").val();
	}
	if(jQuery("#plcx_xm")){
		plcx_xm = jQuery("#plcx_xm").val();
	}
	if(jQuery("#input_mhcx")){
		input_mhcx = jQuery("#input_mhcx").val();
	}
	
	if(jQuery("#mhcx_lx")){

		mhcx_lx = jQuery("#mhcx_lx").val();
	}

	//点击查询
	var searchLx = new Array();
	var searchTj = new Array();
	var searchTjz = new Array();
	
	searchLx[0]="xy";
	searchLx[1]="zy";
	searchLx[2]="bj";
    searchLx[3]="zybj";

        for (var i = 0; i < jytj.length; i++) {
            searchLx.push(jytj[i]);
        }
    

	var tj_num = jQuery("#searchTjDiv input").length;
	for(var j=0;j<tj_num;j++){
		var obj = jQuery("#searchTjDiv input").eq(j);
		searchTj.push(obj.attr("name"));
		searchTjz.push(obj.val());
	}
	
 	var map = {
 		"plcx_xh":plcx_xh,
 		"plcx_xm":plcx_xm,
		"input_mhcx":input_mhcx,
		"mhcx_lx":mhcx_lx,
		"searchTj":searchTj.join("!!@@!!"),
		"searchTjz":searchTjz.join("!!@@!!"),
		"searchLx":searchLx.join("!!@@!!"),
		"path":jQuery("#path").val()
	};
	return map;
}

//设置高级查询条件，导出方法中使用
function addSuperSearchParams(url){
	var map = getSuperSearch();
	
	if(url.indexOf("?") > -1){
		url += "&";
	}else{
		url += "?";
	}
	url += "path=" + map.path + "&mhcx_lx=" + map.mhcx_lx;
	jQuery("input[name=searchLx],input[name=searchTj],input[name=searchTjz],input[name=input_mhcx]").remove();
	
	var html = "<input type = 'hidden' name='searchLx' value='"+map.searchLx+"'>";
	html += "<input type = 'hidden' name='searchTj' value='"+map.searchTj+"'>";
	html += "<input type = 'hidden' name='searchTjz' value='"+map.searchTjz+"'>";
	if (map.input_mhcx){
		html += "<input type = 'hidden' name='input_mhcx' value='"+map.input_mhcx+"'>";
	}
	jQuery("form").eq(0).append(html);
	return url;
}

//排序
function arrangeRs(obj){
	var id = obj.id;
	var arrange = jQuery("#arrange").val();
	var fashion = jQuery("#fashion").val();
	
	if(id == arrange){
		if(fashion == "asc"){
			jQuery("#fashion").val("desc");
		}else{
			jQuery("#fashion").val("asc");
		}
	}else{
		jQuery("#arrange").val(id);
		jQuery("#fashion").val("asc")
	}
	
	searchRs();
}

function setPageInfo(){
	var max_record = jQuery("#ajax_max_record").val();
	var max_page = jQuery("#ajax_max_page").val();
	var max_current = jQuery("#ajax_max_current").val();
	var page_size = jQuery("#ajax_page_size").val();
	var page_maxpage = jQuery("#ajax_page_maxpage").val();
	
	if($("max_record")){
		jQuery("#max_record").html(max_record);
	}
	
	if($("max_page")){
		jQuery("#max_page").html(max_page);
	}
	
	if($("pageno")){
		jQuery("#pageno").val(max_current);
	}
	
	if($("page_Size")){
		jQuery("#page_Size").val(page_size);
	}
	
	if($("pagesize")){
		jQuery("#pagesize").val(page_size);
	}
	
	if($("maxPage")){
		jQuery("#maxPage").val(page_maxpage);
	}
}

//显示帮助层信息
//指定时间800毫秒后显示
function showHelpDiv(){
	if($("div_help")){
		jQuery(".help").bind("mousedown",function(){
			jQuery('#div_help').show();
		});	
		/**
		jQuery(".help").bind("mouseout",function(){
			jQuery("#div_help").hide();
			window.clearTimeout(temp);
		});
		**/
	}
	return false;
}

//选择文件保存路径
function browseFolder(id) {
    try {
        var Message = "\u8bf7\u9009\u62e9\u6587\u4ef6\u5939"; //选择框提示信息
        var Shell = new ActiveXObject("Shell.Application");
        var Folder = Shell.BrowseForFolder(0, Message, 64, 17);//起始目录为：我的电脑
		//var Folder = Shell.BrowseForFolder(0,Message,0); //起始目录为：桌面
        if (Folder != null) {
            Folder = Folder.items(); // 返回 FolderItems 对象
            Folder = Folder.item(); // 返回 Folderitem 对象
            Folder = Folder.Path;   // 返回路径
            if (Folder.charAt(Folder.length - 1) != "\\") {
                Folder = Folder + "\\";
            }
            $(id).value = Folder;
            return Folder;
        }
    }
    catch (e) {
        alertInfo(e.message);
    }
}

//初始化部门列表
function defaultBmOption(){
	//加载年级
	jQuery(function(){	
		var nj = jQuery('#nj');
		
		var url = "xsxx_ajax.do?method=setBmOption";
			url+="&bmjb=nj";
			
		nj.combobox({
			valueField:'dm',
			textField:'mc',
			url:url,
			data:{url:url},
			panelHeight:"auto",
			onChange:function(){
				setTimeout("setBmList('zydm')",500);
				setTimeout("setBmList('bjdm')",1000);
			}
		});
	});
	
	//加载学院
	jQuery(function(){	
		var xy = jQuery('#xydm');
		
		var url = "xsxx_ajax.do?method=setBmOption";
			url+="&bmjb=xydm";
			
		xy.combobox({
			valueField:'dm',
			textField:'mc',
			url:url,
			data:{url:url},
			panelHeight:"auto",
			onChange:function(){
				setTimeout("setBmList('zydm')",500);
				setTimeout("setBmList('bjdm')",1000);
			}
		});
	});
	
	//加载专业
	jQuery(function(){	
		var zy = jQuery('#zydm');
		
		var url = "xsxx_ajax.do?method=setBmOption";
			url+="&bmjb=zydm";
			
		zy.combobox({
			valueField:'dm',
			textField:'mc',
			url:url,
			data:{url:url},
			panelHeight:"auto",
			onChange:function(){
				setTimeout("setBmList('bjdm')",500);
			}
		});
	});
	
	//加载班级
	jQuery(function(){	
		var bj = jQuery('#bjdm');
		
		var url = "xsxx_ajax.do?method=setBmOption";
			url+="&bmjb=bjdm";
			
		bj.combobox({
			valueField:'dm',
			textField:'mc',
			url:url,
			data:{url:url},
			panelHeight:"auto"
		});
	});
}

//联动
function setBmList(bmjb){
	var nj = jQuery('#nj').combobox('getValue');
	var xy = jQuery('#xydm').combobox('getValue');
	var zy = jQuery('#zydm').combobox('getValue');
	var bj = jQuery('#bjdm').combobox('getValue');
	
	var url = "xsxx_ajax.do?method=setBmOption";
		url+= "&nj="+nj;
		url+= "&xydm="+xy;
		if(bmjb != "zydm"){
			url+= "&zydm="+zy;
		}
		if(bmjb != "bjdm"){
			url+= "&bjdm="+bj;
		}
		url+= "&bmjb="+bmjb;
		
	jQuery(function(){
		var obj = jQuery('#'+bmjb);
			obj.combobox({
			valueField:'dm',
			textField:'mc',
			url:url,
			data:{url:url},
			panelHeight:"auto"
		});
	});
}

//显示错误信息
function showErrMessage(result,id){
	if(result != ""){
		if($("em_"+id)){
			$("em_"+id).innerHTML = result;
		}
		if($("sp_"+id)){
			$("sp_"+id).style.display = "";
		}
	}else{
		if($("em_"+id)){
			$("em_"+id).innerHTML = "";
		}
		if($("sp_"+id)){
			$("sp_"+id).style.display = "none";
		}
	}
}

//前往其他模块
function goOtherGnmk(url){

	showWaitingDiv("30000");
	
	refreshForm(url);
}

//执行成功之后
function doSuccess(result){
	$("divWaiting").style.display="none";
	$("divDisable").style.display="none";
	alertInfo(result);
}

//设置是否修改
function setEditValue(){
	if($("had_edit")){
		$("had_edit").value = "yes";
	}
}

function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+50);	
	}
}

//检测输入值是否存在
function checkInputIsExisting(table_name,column_name,column_value,query){
	
	var flag = false;
	
	jQuery.ajaxSetup({async:false});
	
	var url = "commUtil.do?method=checkInputIsExisting";
	
	// 参数
 	var parameter = {
 		"table_name":table_name,
 		"column_name":column_name,
 		"column_value":escape(column_value),
 		"query":query
	};
	
	jQuery.post(url,
		parameter,
		function(result){
			if(result){
				flag = result;
			}
		}
	);
	
	jQuery.ajaxSetup({async:true});	
	
	return flag;
}

//检测表是否存在
function checkTableIsExisting(table_name){
	
	var flag = false;
	
	jQuery.ajaxSetup({async:false});
	
	var url = "commUtil.do?method=checkTableIsExisting";
	
	// 参数
	var parameter = {
		"table_name":table_name
	};
	
	jQuery.post(url,
		parameter,
		function(result){
			if(result){
				flag = result;
			}
		}
	);
	
	jQuery.ajaxSetup({async:true});	
	
	return flag;
}