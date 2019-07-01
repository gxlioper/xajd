var compartStatus = false;
var scrollDivWidth="";//结果集滚动条的宽度
var Rows=new Array();	//所有选中的行对象
var ShiftStartRow="";		//Shift多选时存储开始行对象
var cur_bgc = "#ffdead";//选中行背景（字符串）
var cur_bgc2 ="";//选中背景颜色为无色
var sort_col = null;
var curr_row = null;
var cur_bgc = "#ffdead";
var obj_bgc = "#FFFFFF";
var splitSignOne = "!!SplitSignOne!!";
var splitSignTwo = "!!SplitSignTwo!!";
var hidFlag = false;
var cookieValue = "skin1";
var lastScrollY = 0;
var flyLayerHeight = 35;
var columns = "";
var var_w =0;

//弹出层要用的
var temp_divId;
var temp_divHtml;

function initCSS() {
	try{
		if(document.getElementById('csss')){
			if(document.getElementById('csss').href.indexOf(cookieValue)<0){
				document.getElementById('csss').href = cookieValue + "/" + document.getElementById('csss').href;
			}
		}
	}
	catch(e){
	
	}
}
initCSS();


document.title = "学生工作管理信息系统";

if (window.dialogArguments) document.title = document.title + "------(ESC关闭窗口)";
if (window.dialogArguments) {
	document.onkeypress = function () {
	if (event.keyCode == 27) {
			if (window.dialogArguments) {
				window.close();
			}else if(window.parent.changeWin()) {
				window.parent.changeWin();
			}
		}
	}
}

//获取滚动条位置等信息
function getScroll() {
    var t=0, l=0, w=0, h=0;
	if(window.pageYOffset){
		t = document.body.scrollTop;
        l = document.body.scrollLeft;
        w = document.body.scrollWidth;
        h = document.body.scrollHeight;
	}else if(document.documentElement.scrollTop ){
		t = document.documentElement.scrollTop;
        l = document.documentElement.scrollLeft;
        w = document.documentElement.scrollWidth;
        h = document.documentElement.scrollHeight;
	}else if(document.body.scrollTop){
		t = document.body.scrollTop;
        l = document.body.scrollLeft;
        w = document.body.scrollWidth;
        h = document.body.scrollHeight;
	}		
    return { t: t, l: l, w: w, h: h };
}


//刷新父窗件，直接调用search_go方法，并关闭当前窗件
function refreshParent2(){
	if(window.opener == undefined){
		if(window.dialogArguments && window.dialogArguments.document.getElementById('search_go')){
			window.dialogArguments.document.getElementById('search_go').click();
		}
	}else{
		if(	window.opener && window.opener.document.getElementById('search_go')){
			window.opener.document.getElementById('search_go').click();		
		}
	}
	Close();
}

function initCookieValue() {//
	var search = "style=";
	if (document.cookie.length > 0) {
		offset = document.cookie.indexOf(search);
		if (offset != -1) {
			offset += search.length;
			end = document.cookie.indexOf(";", offset);
			if (end == -1) {
				end = document.cookie.length;
			}
			cookieValue = unescape(document.cookie.substring(offset, end));
		}
	}
}
var $;
if (!$ && document.getElementById) {
  $ = function() {
    var elements = new Array();
    for (var i = 0; i < arguments.length; i++) {
      var element = arguments[i];
      if (typeof element == 'string') {
    	if(element != "" && element != undefined){
    		element = document.getElementById(element);
    	}
      }
      if (arguments.length == 1) {
        return element;
      }
      elements.push(element);
    }
    return elements;
  };
} else if (!$ && document.all) {
  $ = function() {
    var elements = new Array();
    for (var i = 0; i < arguments.length; i++) {
      var element = arguments[i];
      if (typeof element == 'string') {
        element = document.all[element];
      }
      if (arguments.length == 1) {
        return element;
      }
      elements.push(element);
    }
    return elements;
  };
}

function InitTopMenu(menuList) {
	var i = 1;
	var j = 1;
	var gnmk = new Array();
	var printOut = "";
	gnmk = menuList.split(splitSignOne);
	for (i = 1; i < gnmk.length; i++) {
		gnmkcf = gnmk[i].split(splitSignTwo);
		printOut += "<a href='initMenu.do?act=left&menuTop=";
		printOut += gnmkcf[1];
		printOut += "' target='midFrame' title='";
		printOut += gnmkcf[2];
		printOut += "'>";
		printOut += gnmkcf[2];
		printOut += "</a>";
		if (i < gnmk.length - 1) {
			printOut += "&nbsp;|&nbsp;";
		}
	}
	document.write(printOut);
}


/**
 * 加载js或css文件
 * */

function GetHttpRequest() {
    if ( window.XMLHttpRequest ){
        return new XMLHttpRequest() ;
    }else if ( window.ActiveXObject ){
        return new ActiveXObject("MsXml2.XmlHttp") ;
    }
} 

function AjaxPage( url){ 
    var oXmlHttp = GetHttpRequest() ; 
    oXmlHttp.OnReadyStateChange = function(){ 
        if ( oXmlHttp.readyState == 4 ) {
            if ( oXmlHttp.status == 200 || oXmlHttp.status == 304 ){
                IncludeJS(url, oXmlHttp.responseText );
            }else { 
                alert( 'XML request error: ' + oXmlHttp.statusText + ' (' + oXmlHttp.status + ')' ) ; 
            } 
        } 
    } 
    oXmlHttp.open('GET', url, true); 
    oXmlHttp.send(null); 
} 



function IncludeJS( fileUrl, source) { 
    if (  source != null  ){ 
        var oHead = document.getElementsByTagName('HEAD').item(0); 
        var oScript = document.createElement( "script" ); 
        oScript.language = "javascript"; 
        oScript.type = "text/javascript"; 
        oScript.defer = true; 
        oScript.text = source; 
        oHead.appendChild( oScript ); 
    } 
}

function onoff(taga,sty) {
	var hd = taga.parentNode;
	var menu = document.getElementById(hd.id + "_child");
	if (menu.style.display == "none") {
		if(!sty)taga.className = "on";
		menu.style.display = "";
		setTimeout("slowon(\"" + menu.id + "\")", 10);
	} else {
		if(!sty)taga.className = "off";
		setTimeout("slowoff(\"" + menu.id + "\")", 10);
	}
}

function slowoff(id) {
	id = document.getElementById(id);
	var h = parseInt(id.style.height);
	if (isNaN(h)) {
		h = id.offsetHeight;
	}
	if (h - sp > 0) {	    
		id.style.height = (h - sp) + "px";		
		setTimeout("slowoff(\"" + id.id + "\")", 10);
	} else {
		id.style.height = "0px";
		id.style.display = "none";
	}
}
function slowon(id) {
	var cld;
	id = document.getElementById(id);
	for (i = 0; i < id.childNodes.length; i++) {
		if (id.childNodes[i].nodeType == 1) {
			cld = id.childNodes[i];
			break;
		}
	}
	var h = id.offsetHeight;
	var h1 = cld.offsetHeight;
	if(isNaN(id.style.height)){
	  id.style.height = parseInt(id.style.height) + sp + "px";
	}
	if (h1 == 0 || h < h1) {
		setTimeout("slowon(\"" + id.id + "\")", 10);
	} else {
		id.style.height = "auto";
	}
}
function hidesth() {
	var arr = [2, 3, 4, 5, 6, 7];
	for (i = 0; i < arr.length; i++) {
		if (document.getElementById("menu" + arr[i] + "_a")) {
			document.getElementById("menu" + arr[i] + "_a").onclick();
		}
	}
}
function ForceWindow(sTarget) {//
	if (sTarget == null) {
		sTarget = "mainFrame";
	}
	this.r = document.documentElement;
	this.f = document.createElement("FORM");
	this.f.target = sTarget;
	this.f.method = "post";
	this.r.insertBefore(this.f, this.r.childNodes[0]);
}
ForceWindow.prototype.open = function (sUrl) {//
	this.f.action = sUrl;
	this.f.submit();
};
function resetDIV() {
	with (document.getElementsByTagName("DIV")[0].style) {
		left = (document.body.offsetWidth - 508) / 2;
	}
//	if (document.forms[0].userName){
//		document.forms[0].userName.focus();
//	}
}
function Close() {
	if (frameElement && frameElement.api){
		frameElement.api.close();
	} else {
		window.close();
	}
}
function chgRight(sUrl, sTarget) {
	if (sUrl == null) {
		sUrl = "about:blank";
	}
	if (sTarget == null) {
		sTarget = "mainFrame";
	}
	var myWindow = new ForceWindow(sTarget);
	myWindow.open(sUrl);
}

//加载未授予用户的权限
function loadSubPower() {
	document.forms[0].powerSub.options.length = 0;	
	var currentPower = document.forms[0].powerTop.value;
	var querry = "";
	var yhm = document.getElementById("topGroup").value;
	var sUserName = document.getElementById("sUserName").value;
	if(yhm == "" || yhm == null){
		yhm = "%";
	}else{
		yhm = yhm;
	}
	if(sUserName == "" || sUserName == null){
		sUserName = "%";
	}else{
		sUserName = sUserName;
	}
	querry += yhm;
	querry += "!!-!!";
	querry += sUserName;
	if (currentPower == "all") {
		//加载当前子系统功能模块
		GetListData.getUnAllotPower(querry,TjUnAllotPower);
		return true;
	}
	//加载当前子系统未分配给用户的功能模块
	GetListData.getUnAllotPowerById(querry,currentPower,TjUnAllotPower);
	if(document.forms[0].powerSub.options[0]){
		document.forms[0].powerSub.options[0].selected = true;
	}
}
//加载未授予组的权限列表
function loadGroupSubPower() {
	document.forms[0].powerSub.options.length = 0;
	var currentPower = document.forms[0].powerTop.value;
	var querry = "";
	var zdm = document.getElementById("topGroup").value;
	var sUserName = document.getElementById("sUserName").value;
	var gnmkdm = document.getElementById('powerTop').value; 
	
	if(zdm == "" || zdm == null){
		zdm = "%";
	}else{
		zdm = zdm;
	}
	if(gnmkdm == "" || gnmkdm == null || gnmkdm== 'null'){
		gnmkdm = "%";
	}else{
		gnmkdm = gnmkdm;
	}
	if(sUserName == "" || sUserName == null){
		sUserName = "%";
	}else{
		sUserName = sUserName;
	}
	querry += gnmkdm;
	querry += "!!-!!";
	querry += sUserName;
	
	if (currentPower == "all") {
		//加载当前子系统功能模块
		GetListData.getGroupSubPower(querry,zdm,TjUnAllotPower);
		return true;
	}
	//加载当前子系统未分配给用户的功能模块
	GetListData.getGroupSubPower(querry,zdm,TjUnAllotPower);
	if(document.forms[0].powerSub.options[0]){
		document.forms[0].powerSub.options[0].selected = true;
	}
}


//加载字系统列表
function loadSubList() {
	document.forms[0].powerSub.options.length = 0;
	var currentPower = document.forms[0].powerTop.value;
	var querry = "";
	var yhm = document.getElementById("topGroup").value;
	var sUserName = document.getElementById("sUserName").value;
	if(yhm == "" || yhm == null){
		yhm = "%";
	}else{
		yhm = yhm;
	}
	if(sUserName == "" || sUserName == null){
		sUserName = "%";
	}else{
		sUserName = sUserName;
	}
	querry += yhm;
	querry += "!!-!!";
	querry += sUserName;	
	
	//加载当前子系统未分配给用户的功能模块
	GetListData.getSubList(querry,TjUnAllotPowerOfG);
}

function submitForm() {
	if (powerChanged) {
		if (confirm("当前组的权限发生了变化，要保存吗？\n点击\"确定\"，保存数据；点击\"取消\"，将放弃更改！")) {
			if (document.forms[0].groupPower.options.length < 0) {
				alert("组的权限不允许为空！！！");
				return false;
			}
			document.forms[0].action = "saveGroupPower.do";
		} else {
			document.forms[0].action = "group_man.do";
		}
	} else {
		document.forms[0].action = "group_man.do";
	}
	var groupPower = new Array();
	for (i = 0; i < document.forms[0].groupPower.options.length; i++) {
		p = document.forms[0].groupPower.options[i].value.substring(0, 1);
		q = document.forms[0].groupPower.options[i].value.substring(1, document.forms[0].groupPower.options[i].value.length);
		groupPower[groupPower.length] = p + ":" + q;
	}
	document.forms[0].power.value = groupPower;
	document.forms[0].submit();
}
function beforSubmitForm() {
	var zmc = document.getElementById('zmc');
	if (zmc.value == "0001"&&powerChanged) {
		powerChanged = false;
	}
}
function showDiv(d_html, w, h) {
	i = document.getElementsByTagName("select").length;
	for (j = 0; j < i; j++) {
		document.getElementsByTagName("select")[j].style.visibility = "hidden";
	}
	var d_width = document.body.clientWidth - 5;
	var d_height = document.body.clientHeight - 10;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = w;
	var d_height_top = h;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	if((d_height - d_height_top)<0){
		d_top_top=d_height/2-50;
	}
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<br>";
	dd_html += d_html;
	dd_html += "<br>";
	dd_html += "</div>";
	tmpdiv.innerHTML = dd_html;
}
function checkZMCLength(){
	var zmcLength = document.getElementById("newGName").value.length;
	if(zmcLength > 30){
		alert("组名不能超过30个字符/15个汉字");
		return false;
	}
	return true;
}
function showAddUser() {

	var xxdm = document.getElementById("xxdm").value;
	
	if(document.getElementById('flag')){
		document.getElementById("newUserUnit").onchange=function(){
			getSpecialDep(this);
		}
	}
	initList("allGroup", "newUserGroup", "");
	initList("allPart", "newUserPart", "");
	
	
	if(xxdm != "10338"){//非浙江理工大学
		initList("allUnit", "newUserUnit", "");
	}
	viewTempDiv('添加新用户','addUser',400, 360);
}

function fillText(obj,id){
	document.getElementById(id).value = obj.options[obj.selectedIndex].text;
}

//根据不同的单位代码刷新部门列表
function getSpecialDep(obj){
	var dwdm = obj.value;
	var reg = new RegExp('!!SplitSignTwo!!'+dwdm+'[0-9]*!!SplitSignTwo!![\u4E00-\u9FA5]*');
	var change = document.getElementById('globlVar');
	change.value = "";
	msgArray = new Array();
	msgArray = document.getElementById('allPart').value.split('!!SplitSignOne!!');
	while(msgArray.length){
		var a =  msgArray.shift().match(reg);
		if(a != null){
			change.value += '!!SplitSignOne!!' + a;
		}
	}
//	change.value = document.getElementById('allPart').value.match(reg);
	initList("globlVar", "newUserPart", "");
	change.value = "";
}

function showModi() {
	var text = document.forms[0].topGroup.options[document.forms[0].topGroup.selectedIndex].text;
	if (text == "学生") {
		alert("当前组\"学生\"组是系统固有的，不可修改！");
		return false;
	} else if ($('topGroup').value == "" ){
		alert("请选择用户组！");
		return false;
	} else {
		$('newGName2').value = text;
		if($("yymc")){
			$('yymc').value = text;
		}	
	}
	
	viewTempDiv('修改组','modiGroup',320, 140);
}
function showDel() {
	if(!confirm("删除组后，将全部删除隶属于该组的所有用户及权限!\n　　　　　　　确定要删除吗？")){
		return false;
	}
	if (document.forms[0].topGroup.options[document.forms[0].topGroup.selectedIndex].text == "学生") {
		alert("当前组\"学生\"组是系统固有的，不可删除！");
		return false;
	}
	viewTempDiv("删除组","delGroup",350,140);
}

function getSfxsG(){
	if (document.forms[0].topGroup.options[document.forms[0].topGroup.selectedIndex].text == "---请选择---") {
		return false;
	}
	var zmc=$("topGroup").value;
	zzxmTj.getSfxsG(zmc,function getSfData(data){
	if(data=='0'){
		document.getElementsByName('sfxsG')[1].checked="true";
	
	}else{
		document.getElementsByName('sfxsG')[0].checked="true";
	}
});
}


function showSz() {
	if (document.forms[0].topGroup.options[document.forms[0].topGroup.selectedIndex].text == "---请选择---") {
		alert("请选择需要设置的用户组!");
		return false;
	}
	
	viewTempDiv('设置组','szGroup',300, 140)
}

function inArray(str, array) {//
	for (tmp = 0; tmp < array.length; tmp++) {
		if (str == array[tmp]) {
			return true;
		}
	}
	return false;
}
function addAllPower() {
	if (document.forms[0].topGroup.selectedIndex < 0) {
		alert("请选择组（用户）");
		return false;
	}
	var groupPower = new Array();
	var tmp;
	j = document.forms[0].powerSub.options.length;
	for (i = 0; i < document.forms[0].groupPower.options.length; i++) {
		groupPower[groupPower.length] = document.forms[0].groupPower.options[i].value.substring(1, document.forms[0].groupPower.options[i].value.length);
	}
	for (i = 0; i < j; i++) {
		tmp = document.forms[0].powerSub.options[i].value;
		if (!inArray(tmp, groupPower)) {
			addPowerById(tmp);
		}
	}
	//sort();
}
function sort() {
	var p, q;
	for (p = document.forms[0].groupPower.options.length - 1; p >= 0; p--) {
		for (q = 0; q < p; q++) {
			if (document.forms[0].groupPower.options[q + 1].value.substring(1, document.forms[0].groupPower.options[q + 1].value.length) < document.forms[0].groupPower.options[q].value.substring(1, document.forms[0].groupPower.options[q].value.length)) {
				var tempV = document.forms[0].groupPower.options[q].value;
				var tempT = document.forms[0].groupPower.options[q].text;
				document.forms[0].groupPower.options[q].value = document.forms[0].groupPower.options[q + 1].value;
				document.forms[0].groupPower.options[q].text = document.forms[0].groupPower.options[q + 1].text;
				document.forms[0].groupPower.options[q + 1].value = tempV;
				document.forms[0].groupPower.options[q + 1].text = tempT;
			}
		}
	}
}
function addPower() {
	if (document.forms[0].topGroup.selectedIndex < 0) {
		alert("请选择组（用户）");
		return false;
	}
	var addId = document.forms[0].powerSub.selectedIndex;
	if (addId < 0) {
		return false;
	}
	var addV = document.forms[0].powerSub.options[addId].value;
	var addT = document.forms[0].powerSub.options[addId].text;
	var groupPower = new Array();
	///sq 增加判断功能是否已授权
	for(j = 0; j < document.forms[0].groupPower.options.length; j++){
		if(addV == document.forms[0].groupPower.options[j].value.substring(1, document.forms[0].groupPower.options[j].value.length)){
			alert("所选权限已经授于该组!");
			return false;
		}
	}
	for (i = 0; i < document.forms[0].groupPower.options.length; i++) {
		groupPower[groupPower.length] = document.forms[0].groupPower.options[i].value.substring(1, document.forms[0].groupPower.options[i].value.length);
	}
	if (addV.length == 7) {
		if (!inArray(addV.substring(0, 3), groupPower)) {
			addPowerById(addV.substring(0, 3));
		}
		if (!inArray(addV.substring(0, 5), groupPower)) {
			addPowerById(addV.substring(0, 5));
		}
		if (!inArray(addV, groupPower)) {
			addPowerById(addV);
		}
	} else {
		if (addV.length == 5) {
			if (!inArray(addV.substring(0, 3), groupPower)) {
				addPowerById(addV.substring(0, 3));
			}
			addSubPower(addV);
		} else {
			if (addV.length == 3) {
				addSubPower(addV);
			}
		}
	}
	//document.forms[0].groupPower.style.tabindex=document.forms[0].groupPower.length-1;
	//sort();
}
function addSubPower(powerId) {
	powerChanged = true;
	if (powerId.length == 3) {
		for (j = 0; j < document.forms[0].powerSub.options.length; j++) {
			if (document.forms[0].powerSub.options[j].value.substring(0, 3) == powerId) {
				addPowerById(document.forms[0].powerSub.options[j].value);
				j--;
			}
		}
	} else {
		if (powerId.length == 5) {
			for (j = 0; j < document.forms[0].powerSub.options.length; j++) {
				if (document.forms[0].powerSub.options[j].value.substring(0, 5) == powerId) {
					addPowerById(document.forms[0].powerSub.options[j].value);
					j--;
				}
			}
		}
	}
}
function addPowerById(powerId) {
	powerChanged = true;
	var tmp_txt;
	var tmp_value;
	for (k = 0; k < document.forms[0].powerSub.options.length; k++) {
		if (document.forms[0].powerSub.options[k].value == powerId) {
			tmp_txt = document.forms[0].powerSub.options[k].text;
			tmp_txt = tmp_txt.substring(0, 7) + " | " + "只读" + tmp_txt.substring(7, tmp_txt.length);
			tmp_value = document.forms[0].powerSub.options[k].value;
			document.forms[0].groupPower.options[document.forms[0].groupPower.options.length] = new Option(tmp_txt, "0" + tmp_value);
		}
	}
	for (t = 0; t < document.forms[0].gnmkdm.options.length;t++){
		if (document.forms[0].gnmkdm.options[t].value == powerId) {
			document.forms[0].gnmkdm.options[t]=null;
		}
	}
}
function delAllPower() {
	powerChanged = true;
	document.forms[0].groupPower.options.length = 0;
}
function delPower() {
	powerChanged = true;
	i = document.forms[0].groupPower.selectedIndex;
	if (i < 0) {
		return false;
	}
	var delPower = document.forms[0].groupPower.options[i].value;
	var k = document.forms[0].groupPower.options.length;
	var opensValues =  Array();
	var opensTexts =  Array();	
	var opensLen = 0;
	if (delPower.length == 8) {
		opensValues[opensLen]=document.forms[0].groupPower.options[i].value.substring('1');
        opensTexts[opensLen]=document.forms[0].groupPower.options[i].text.replace('可写 | ','').replace('只读 | ','');
        opensLen++;
		document.forms[0].groupPower.options[i].value = "del";
		document.forms[0].groupPower.options[i].text = "del";
	} else {
		if (delPower.length == 6) {
			for (j = 0; j < k; j++) {
				if (document.forms[0].groupPower.options[j].value.substring(1, 6) == delPower.substring(1, 6)) {
					opensValues[opensLen]=document.forms[0].groupPower.options[j].value.substring('1');
        			opensTexts[opensLen]=document.forms[0].groupPower.options[j].text.replace('可写 | ','').replace('只读 | ','');
       				opensLen++;
					document.forms[0].groupPower.options[j].value = "del";
					document.forms[0].groupPower.options[j].text = "del";
				}
			}
		} else {
			if (delPower.length == 4) {
				for (j = 0; j < k; j++) {
					if (document.forms[0].groupPower.options[j].value.substring(1, 4) == delPower.substring(1, 6)) {
						opensValues[opensLen]=document.forms[0].groupPower.options[j].value.substring('1');
        				opensTexts[opensLen]=document.forms[0].groupPower.options[j].text.replace('可写 | ','').replace('只读 | ','');
       					opensLen++;
						document.forms[0].groupPower.options[j].value = "del";
						document.forms[0].groupPower.options[j].text = "del";
					}
				}
			}
		}
	}
	while (k > 0) {
		if (document.forms[0].groupPower.options[k - 1].value == "del") {
			document.forms[0].groupPower.options[k - 1] = null;
		}
		k--;
	}
	var gnmkLeng = document.forms[0].gnmkdm.length;
	for (t = 0; t < opensValues.length;t++){
		document.forms[0].gnmkdm.options[gnmkLeng+t] = new Option(opensTexts[t], opensValues[t]);
	}
}
function writeAll() {
	for (i = 0; i < document.forms[0].groupPower.options.length; i++) {
		document.forms[0].groupPower.options[i].value = document.forms[0].groupPower.options[i].value.replace("0N", "1N");
		document.forms[0].groupPower.options[i].text = document.forms[0].groupPower.options[i].text.replace("只读", "可写");
	}
}
function readOnlyAll() {
	for (i = 0; i < document.forms[0].groupPower.options.length; i++) {
		document.forms[0].groupPower.options[i].value = document.forms[0].groupPower.options[i].value.replace("1N", "0N");
		document.forms[0].groupPower.options[i].text = document.forms[0].groupPower.options[i].text.replace("可写", "只读");
	}
}
function chgR() {
	powerChanged = true;
	i = document.forms[0].groupPower.selectedIndex;
	
	if (document.forms[0].groupPower.value==''){
		alert("请选择当前用户所拥有的功能模块!");
		return false;
	}
	
	opValue = document.forms[0].groupPower.options[i].value;
	
	if (i < 0) {
		return false;
	}
	if(opValue.length==4){
    	for(var j = 0;j<document.forms[0].groupPower.length;j++){
    		if(document.forms[0].groupPower.options[j].value.substring(0,4)==opValue){
    			document.forms[0].groupPower.options[j].value = document.forms[0].groupPower.options[j].value.replace("1N", "0N");
				document.forms[0].groupPower.options[j].text = document.forms[0].groupPower.options[j].text.replace("可写", "只读");
    		}
    	}
    }else if (opValue.length==6){
    	for(var j = 0;j<document.forms[0].groupPower.length;j++){
    	if(document.forms[0].groupPower.options[j].value.substring(0,6)==opValue){
    			document.forms[0].groupPower.options[j].value = document.forms[0].groupPower.options[j].value.replace("1N", "0N");
				document.forms[0].groupPower.options[j].text = document.forms[0].groupPower.options[j].text.replace("可写", "只读");
    		}
    	}
    }else{
    	document.forms[0].groupPower.options[i].value = document.forms[0].groupPower.options[i].value.replace("1N", "0N");
		document.forms[0].groupPower.options[i].text = document.forms[0].groupPower.options[i].text.replace("可写", "只读");
    }
}
function chgW() {
	powerChanged = true;
	i = document.forms[0].groupPower.selectedIndex;
	
	if (document.forms[0].groupPower.value==''){
		alert("请选择当前用户所拥有的功能模块!");
		return false;
	}
	
    opValue = document.forms[0].groupPower.options[i].value;
    if (i < 0) {
		return false;
	}
	
    if(opValue.length==4){
    	for(var j = 0;j<document.forms[0].groupPower.length;j++){
    		if(document.forms[0].groupPower.options[j].value.substring(0,4)==opValue){
    			document.forms[0].groupPower.options[j].value = document.forms[0].groupPower.options[j].value.replace("0N", "1N");
				document.forms[0].groupPower.options[j].text = document.forms[0].groupPower.options[j].text.replace("只读", "可写");
    		}
    	}
    }else if (opValue.length==6){
    	for(var j = 0;j<document.forms[0].groupPower.length;j++){
    	if(document.forms[0].groupPower.options[j].value.substring(0,6)==opValue){
    			document.forms[0].groupPower.options[j].value = document.forms[0].groupPower.options[j].value.replace("0N", "1N");
				document.forms[0].groupPower.options[j].text = document.forms[0].groupPower.options[j].text.replace("只读", "可写");
    		}
    	}
    }else{
    	document.forms[0].groupPower.options[i].value = document.forms[0].groupPower.options[i].value.replace("0N", "1N");
		document.forms[0].groupPower.options[i].text = document.forms[0].groupPower.options[i].text.replace("只读", "可写");
    }
}
function addGroup() {
	var tmp_newName = document.getElementById('newGName1').value;
	j = document.forms[0].topGroup.options.length;
	var tmp = new Array;
	for (i = 0; i < j; i++) {
		tmp[tmp.length] = document.forms[0].topGroup.options[i].text;
	}
	if (tmp_newName == "") {
		alert("组名称不能为空！");
		document.getElementById('newGName1').focus();
		return false;
	}
	if (inArray(tmp_newName, tmp) || tmp_newName == "请选择组（用户）") {
		alert("名称为\"" + tmp_newName + "\"的组已经存在！");
		document.getElementById('newGName1').value = "";
		document.getElementById('newGName1').focus();
	} else {
		document.forms[0].newGroupName.value = tmp_newName;
		document.forms[0].action = "addGroup.do";
		document.forms[0].submit();
	}
}
function modiGroup() {
	var tmp_newName = document.getElementById('newGName2').value;
	j = document.forms[0].topGroup.options.length;
	var tmp = new Array;
	for (i = 0; i < j; i++) {
		tmp[tmp.length] = document.forms[0].topGroup.options[i].text;
	}
	if (tmp_newName == "") {
		alert("组名称不能为空！");
		document.getElementById('newGName2').focus();
		return false;
	}
	
	var yymc = "";
	var flag = false;
	
	if($("yymc")){
		yymc = $("yymc").value;
	}
	
	if (inArray(tmp_newName, tmp)) {
		flag = true;
		if(yymc == tmp_newName){
			flag = false;
		}
	}
	
	if (flag) {
		alert("名称为\"" + tmp_newName + "\"的组已经存在！");
		document.forms[0].newGName.value = "";
//		document.getElementById('newGName').focus();
	} else {
		var tmp_sfxsArr = document.getElementsByName("sfxsG");
		var returnVal;
		for(var i=0;i<tmp_sfxsArr.length;i++){
			if(tmp_sfxsArr[i].checked){
				returnVal = tmp_sfxsArr[i].value;
			}
		}
		jQuery("#sfxsGroup").val(returnVal);
		jQuery("#newGroupName").val(tmp_newName);
		document.forms[0].action = "modiGroup.do";
		document.forms[0].submit();
	}
}
function szGroup() {
	var tmp_sfxsArr = document.getElementsByName("sfxsG");
	var returnVal;
	for(var i=0;i<tmp_sfxsArr.length;i++){
		if(tmp_sfxsArr[i].checked){
			returnVal = tmp_sfxsArr[i].value;
		}
	}
	alert(returnVal);
	document.forms[0].sfxsGroup.value = returnVal;
	document.forms[0].action = "szGroup.do";
	document.forms[0].submit();
}
function delGroup() {
	var tmp_newName = document.getElementById('delNewGName').value;
	j = document.forms[0].topGroup.options.length;
	if (tmp_newName != document.forms[0].topGroup.options[document.forms[0].topGroup.selectedIndex].text) {
		alert("您输入的组名称与当前组\"" + document.forms[0].topGroup.options[document.forms[0].topGroup.selectedIndex].text + "\"不符，操作被禁止！\n您只能删除当前组!");
		document.forms[0].delNewGName.focus();
		return false;
	} else {
		document.forms[0].topGroup.value = document.forms[0].topGroup.options[0].value;
		document.forms[0].newGroupName.value = tmp_newName;
		document.forms[0].action = "delGroup.do";
		document.forms[0].submit();
	}
}
function closeAdd() {
	i = document.getElementsByTagName("select").length;
	for (j = 0; j < i; j++) {
		document.getElementsByTagName("select")[j].style.visibility = "";
	}
	dd_html = "";
	tmpdiv.innerHTML = dd_html;
}
function closeAdd1() {
	i = document.getElementsByTagName("select").length;
	for (j = 0; j < i; j++) {
		document.getElementsByTagName("select")[j].style.visibility = "";
	}
	dd_html = "";
	tmpdiv1.innerHTML = dd_html;
}
function reload() {
	window.location.reload();
}
function getStr(str, num) {
	tmp = str.split("/");
	return tmp[num];
}

function initList(hideID, fillID, def) {
	var tmp1 = document.getElementById(hideID).value.split(splitSignOne);
	var tmp2 = new Array;
	for (i = 1; i < tmp1.length; i++) {
		tmp2[tmp2.length] = tmp1[i].split(splitSignTwo);
	}
	if (document.getElementById(fillID)){
		document.getElementById(fillID).options.length = 0;
		
		document.getElementById(fillID).options[0] = new Option("","");
		
		for (i = 0; i < tmp2.length; i++) {
			document.getElementById(fillID).options[document.getElementById(fillID).options.length] = new Option(tmp2[i][2], tmp2[i][1]);
		}
		for (i = 1; i < document.getElementById(fillID).options.length; i++) {
			if (document.getElementById(fillID).options[i].text == def || document.getElementById(fillID).options[i].value == def) {
				document.getElementById(fillID).options[i].selected = true;
				return;
			}
		}
	}
	
	
	
}

function userChg() {
	if (powerChanged) {
		if (confirm("当前组的权限发生了变化，要保存吗？\n点击\"确定\"，保存数据；点击\"取消\"，将放弃更改！")) {
			if (document.forms[0].groupPower.options.length <= 0) {
				alert("组的权限不允许为空！！！");
				return false;
			}
			showTips();
			document.forms[0].action = "saveUserPower.do";
		} else {
			showTips();
			document.forms[0].action = "user_man.do";
		}
	} else {
		showTips();
		document.forms[0].action = "user_man.do";
	}
	var groupPower = new Array();
	for (i = 0; i < document.forms[0].groupPower.options.length; i++) {
		p = document.forms[0].groupPower.options[i].value.substring(0, 1);
		q = document.forms[0].groupPower.options[i].value.substring(1, document.forms[0].groupPower.options[i].value.length);
		groupPower[groupPower.length] = p + ":" + q;
	}
	document.forms[0].power.value = groupPower;
	document.forms[0].submit();
}
function listAll() {
	document.forms[0].powerSub.options.length = 0;
	for (i = 1; i < dpd.length; i++) {
		if ((dpd[i][1]).length == 3) {
			document.forms[0].powerSub.options[document.forms[0].powerSub.options.length] = new Option(dpd[i][1] + "____ | " + dpd[i][2], dpd[i][1]);
		} else {
			if ((dpd[i][1]).length == 5) {
				document.forms[0].powerSub.options[document.forms[0].powerSub.options.length] = new Option(dpd[i][1] + "__ | " + dpd[i][2], dpd[i][1]);
			} else {
				document.forms[0].powerSub.options[document.forms[0].powerSub.options.length] = new Option(dpd[i][1] + " | " + dpd[i][2], dpd[i][1]);
			}
		}
	}
	document.forms[0].powerSub.options[0].selected = true;
}
function save(url) {
	var groupPower = new Array();
	if($("xxdm")){var xxdm = document.getElementById("xxdm").value;}
	if(xxdm != "14136"){
		if (document.forms[0].groupPower.options.length <= 0) {
			if(url=="saveUserPower.do"){
				alert("用户权限不允许为空！！！");
			}else{
				alert("组的权限不允许为空！！！");
			}
			return false;
		}
	}
	for (i = 0; i < document.forms[0].groupPower.options.length; i++) {
		p = document.forms[0].groupPower.options[i].value.substring(0, 1);
		q = document.forms[0].groupPower.options[i].value.substring(1, document.forms[0].groupPower.options[i].value.length);
		groupPower[groupPower.length] = p + ":" + q;
	}
	
	
	if(document.getElementById("topGroup").value==null || document.getElementById("topGroup").value.trim().length==0 ){
		alert("请选择相应的组！！");
		return false;
	}
	
	document.forms[0].power.value = groupPower;
	document.forms[0].action = url;
	document.forms[0].submit();
	var tmpBut = document.getElementsByTagName("button");
	for (i = 0; i < tmpBut.length; i++) {
		tmpBut[i].disabled = true;
	}
}
function addUser() {
	if ($('modiUser')){
		$('modiUser').innerHTML="";
	}

	userID = document.getElementById('newUserIDd').value;
	userPassword = document.getElementById('newPasswordd').value;
	userPassword2 = document.getElementById('newPassword2d').value;
	
	userGroup = document.getElementById('select_newUserGroup').value;
	userName = document.getElementById('newUserName2').value;
	userPart = document.getElementById('select_newUserPart').value;
	userUnit = "";
	xxdm = document.getElementById("xxdm").value;
	if(document.getElementById('dwText1')){
		userUnit = ""== document.getElementById('dwText1').value ? document.getElementById('select_newUserUnit').value : document.getElementById('dwText1').value;
	}
	j = document.forms[0].topGroup.options.length;
	var tmp = new Array;
	for (i = 0; i < j; i++) {
		tmp[tmp.length] = document.forms[0].topGroup.options[i].value;
	}
	if (userID == "") {
		alert("用户名不能为空！");
		document.getElementById('newUserIDd').focus();
		return false;
	} else {
		if (inArray(userID, tmp)) {
			alert("名称为\"" + userID + "\"的用户已经存在！");
			document.getElementById('newUserIDd').value = "";
			document.getElementById('newUserIDd').focus();
			return false;
		} else {
			if (userPassword.length < 6) {
				alert("口令长度不能小于6！");
				document.getElementById('newPasswordd').value = "";
				document.getElementById('newPassword2d').value = "";
				document.getElementById('newPasswordd').focus();
				return false;
			} else {
				if (userPassword != userPassword2) {
					alert("两次输入的口令不一致！");
					document.getElementById('newPasswordd').value = "";
					document.getElementById('newPassword2d').value = "";
					document.getElementById('newPasswordd').focus();
					return false;
				} else {
					if(userGroup == ""){
						alert("用户所属组不能为空！");
						document.getElementById('select_newUserGroup').focus();
						return false;
					}else if(userName == ""){
						alert("用户姓名不能为空！");
						document.getElementById('newUserName2').focus();
						return false;
					}else if(userUnit == "" && xxdm != "10338"){//非浙江理工大学
						alert("用户单位不能为空！");
						document.getElementById('dwText1').focus();
						return false;
					}else if(userPart == ""){
						alert("用户部门不能为空！");
						document.getElementById('select_newUserPart').focus();
						return false;
					}else{
					document.forms[0].action = "addUser.do";
					document.forms[0].submit();
				}
			}
		}
	}
}
}

function showModiUser() {

	var xxdm = document.getElementById("xxdm").value;
	if (document.forms[0].topGroup.selectedIndex < 0) {
		alert("请选择组（用户）");
		return false;
	}
	purview = getStr(document.forms[0].topGroup.options[document.forms[0].topGroup.selectedIndex].text, 5);
	document.getElementById('userID').value = getStr(document.forms[0].topGroup.options[document.forms[0].topGroup.selectedIndex].text, 0);
	document.getElementById('modiUserName').value = getStr(document.forms[0].topGroup.options[document.forms[0].topGroup.selectedIndex].text, 1);
	
	
	if(document.getElementById('flag')){
		document.getElementById("select_userUnit2").onchange=function(){
			getSpecialDep(this);
		}
	}
	group = getStr(document.forms[0].topGroup.options[document.forms[0].topGroup.selectedIndex].text, 2);
	part = getStr(document.forms[0].topGroup.options[document.forms[0].topGroup.selectedIndex].text, 3);
	unit = getStr(document.forms[0].topGroup.options[document.forms[0].topGroup.selectedIndex].text, 4);
	initList("allGroup", "select_userGroup", group);
	initList("allPart", "select_userPart", part);
	if(xxdm != "10338"){//非浙江理工大学
		initList("allUnit", "select_userUnit2", unit);
		fillText(document.getElementById('select_userUnit2'),'modiDwText');
	}	
	viewTempDiv('修改用户','modiUser',350, 300);
}

function modiUser() {
	if ($('addUser')){
		$('addUser').innerHTML="";
	}
	userGroup = document.getElementById('select_userGroup').value;
	userName = document.getElementById('modiUserName').value;
	userPart = document.getElementById('newUserPart').value;
	userUnit = "";
	xxdm = document.getElementById("xxdm").value;
	if(document.getElementById('modiDwText')){
		userUnit = ""== document.getElementById('modiDwText').value ? document.getElementById('select_userUnit2').value : document.getElementById('modiDwText').value;
	}
	if(userGroup == ""){
		alert("用户所属组不能为空！");
		document.getElementById('select_userGroup').focus();
		return false;
	} else if(userName == ""){
		alert("用户姓名不能为空！");
		document.getElementById('modiUserName').focus();
		return false;
	}else if(userUnit == "" && xxdm != "10338"){//非浙江理工大学
		alert("用户单位不能为空！");
		document.getElementById('modiDwText').focus();
		return false;
	}else if(userPart == ""){
		alert("用户部门不能为空！");
		document.getElementById('newUserPart').focus();
		return false;
	}else{
		document.forms[0].action = "modiUser.do";
		document.forms[0].submit();
	}
	if($("buttonSave")){
	   $("buttonSave").disabled=true;
	}
}


function showDelUser() {
	if (document.forms[0].topGroup.selectedIndex < 0) {
		alert("没有用户被选中，请选择要删除的用户！");
	} else {
		if (confirm("确实要删除当前用户吗？")) {
			document.forms[0].power.value = document.forms[0].userName.value;
			document.forms[0].userName.value = "";
			document.forms[0].action = "delUser.do";
			document.forms[0].submit();
			return true;
		}
		return false;
	}
}
function viewPassword() {
	if (document.forms[0].topGroup.selectedIndex < 0) {
		alert("没有用户被选中，请选择要查询的用户！");
		return false;
	}
	document.forms[0].action = "/xgxt/viewUserPassword.do";
	document.forms[0].submit();
}
function cols(objTr) {
	if (document.forms[0].dm.value != "") {
		document.getElementById(document.forms[0].dm.value).style.background = "#FFFFFF";
		document.forms[0].dm.value = objTr.id;
	}
	objTr.style.background = "#ffdead";
	document.forms[0].dm.value = objTr.id;
}
function colss(na) {
	document.all.a.style.background = "#D0E0EE";
	document.all.b.style.background = "#D0E0EE";
	document.all.c.style.background = "#D0E0EE";
	var tt = document.getElementById(na);
	tt.style.background = "#ffdead";
}
function chgCode(objTr) {
	document.forms[0].tname.value = objTr.id;
	document.forms[0].action = "/xgxt/code_man.do?act=" + document.forms[0].codeType.value;
	document.forms[0].submit();
}

function pageCardOn(defaultid) {
	if (document.forms[0].tname.value != "") {
		document.getElementById(document.forms[0].tname.value + "l").className = "xxk_on_l";
		document.getElementById(document.forms[0].tname.value).className = "xxk_on_m";
		document.getElementById(document.forms[0].tname.value + "r").className = "xxk_on_r";
	} else {
		document.getElementById(defaultid + "l").className = "xxk_on_l";
		document.getElementById(defaultid).className = "xxk_on_m";
		document.getElementById(defaultid + "r").className = "xxk_on_r";
		document.forms[0].tname.value = defaultid;
	}
}
function judge_CN(char1, char2, mode) {//
	var charPYStr = "啊阿埃挨哎唉哀皑癌蔼矮艾碍爱隘鞍氨安俺按暗岸胺案肮昂盎凹敖熬翱袄傲奥懊澳芭捌扒叭吧笆八疤巴拔跋靶把耙坝霸罢爸白柏百摆佰败拜稗斑班搬扳般颁板版扮拌伴瓣半办绊邦帮梆榜膀绑棒磅蚌镑傍谤苞胞包褒剥薄雹保堡饱宝抱报暴豹鲍爆杯碑悲卑北辈背贝钡倍狈备惫焙被奔苯本笨崩绷甭泵蹦迸逼鼻比鄙笔彼碧蓖蔽毕毙毖币庇痹闭敝弊必辟壁臂避陛鞭边编贬扁便变卞辨辩辫遍标彪膘表鳖憋别瘪彬斌濒滨宾摈兵冰柄丙秉饼炳病并玻菠播拨钵波博勃搏铂箔伯帛舶脖膊渤泊驳捕卜哺补埠不布步簿部怖擦猜裁材才财睬踩采彩菜蔡餐参蚕残惭惨灿苍舱仓沧藏操糙槽曹草厕策侧册测层蹭插叉茬茶查碴搽察岔差诧拆柴豺搀掺蝉馋谗缠铲产阐颤昌猖场尝常长偿肠厂敞畅唱倡超抄钞朝嘲潮巢吵炒车扯撤掣彻澈郴臣辰尘晨忱沉陈趁衬撑称城橙成呈乘程惩澄诚承逞骋秤吃痴持匙池迟弛驰耻齿侈尺赤翅斥炽充冲虫崇宠抽酬畴踌稠愁筹仇绸瞅丑臭初出橱厨躇锄雏滁除楚础储矗搐触处揣川穿椽传船喘串疮窗幢床闯创吹炊捶锤垂春椿醇唇淳纯蠢戳绰疵茨磁雌辞慈瓷词此刺赐次聪葱囱匆从丛凑粗醋簇促蹿篡窜摧崔催脆瘁粹淬翠村存寸磋撮搓措挫错搭达答瘩打大呆歹傣戴带殆代贷袋待逮怠耽担丹单郸掸胆旦氮但惮淡诞弹蛋当挡党荡档刀捣蹈倒岛祷导到稻悼道盗德得的蹬灯登等瞪凳邓堤低滴迪敌笛狄涤翟嫡抵底地蒂第帝弟递缔颠掂滇碘点典靛垫电佃甸店惦奠淀殿碉叼雕凋刁掉吊钓调跌爹碟蝶迭谍叠丁盯叮钉顶鼎锭定订丢东冬董懂动栋侗恫冻洞兜抖斗陡豆逗痘都督毒犊独读堵睹赌杜镀肚度渡妒端短锻段断缎堆兑队对墩吨蹲敦顿囤钝盾遁掇哆多夺垛躲朵跺舵剁惰堕蛾峨鹅俄额讹娥恶厄扼遏鄂饿恩而儿耳尔饵洱二贰发罚筏伐乏阀法珐藩帆番翻樊矾钒繁凡烦反返范贩犯饭泛坊芳方肪房防妨仿访纺放菲非啡飞肥匪诽吠肺废沸费芬酚吩氛分纷坟焚汾粉奋份忿愤粪丰封枫蜂峰锋风疯烽逢冯缝讽奉凤佛否夫敷肤孵扶拂辐幅氟符伏俘服浮涪福袱弗甫抚辅俯釜斧脯腑府腐赴副覆赋复傅付阜父腹负富讣附妇缚咐噶嘎该改概钙盖溉干甘杆柑竿肝赶感秆敢赣冈刚钢缸肛纲岗港杠篙皋高膏羔糕搞镐稿告哥歌搁戈鸽胳疙割革葛格蛤阁隔铬个各给根跟耕更庚羹埂耿梗工攻功恭龚供躬公宫弓巩汞拱贡共钩勾沟苟狗垢构购够辜菇咕箍估沽孤姑鼓古蛊骨谷股故顾固雇刮瓜剐寡挂褂乖拐怪棺关官冠观管馆罐惯灌贯光广逛瑰规圭硅归龟闺轨鬼诡癸桂柜跪贵刽辊滚棍锅郭国果裹过哈骸孩海氦亥害骇酣憨邯韩含涵寒函喊罕翰撼捍旱憾悍焊汗汉夯杭航壕嚎豪毫郝好耗号浩呵喝荷菏核禾和何合盒貉阂河涸赫褐鹤贺嘿黑痕很狠恨哼亨横衡恒轰哄烘虹鸿洪宏弘红喉侯猴吼厚候后呼乎忽瑚壶葫胡蝴狐糊湖弧虎唬护互沪户花哗华猾滑画划化话槐徊怀淮坏欢环桓还缓换患唤痪豢焕涣宦幻荒慌黄磺蝗簧皇凰惶煌晃幌恍谎灰挥辉徽恢蛔回毁悔慧卉惠晦贿秽会烩汇讳诲绘荤昏婚魂浑混豁活伙火获或惑霍货祸击圾基机畸稽积箕肌饥迹激讥鸡姬绩缉吉极棘辑籍集及急疾汲即嫉级挤几脊己蓟技冀季伎祭剂悸济寄寂计记既忌际妓继纪嘉枷夹佳家加荚颊贾甲钾假稼价架驾嫁歼监坚尖笺间煎兼肩艰奸缄茧检柬碱硷拣捡简俭剪减荐槛鉴践贱见键箭件健舰剑饯渐溅涧建僵姜将浆江疆蒋桨奖讲匠酱降蕉椒礁焦胶交郊浇骄娇嚼搅铰矫侥脚狡角饺缴绞剿教酵轿较叫窖揭接皆秸街阶截劫节茎睛晶鲸京惊精粳经井警景颈静境敬镜径痉靖竟竞净炯窘揪究纠玖韭久灸九酒厩救旧臼舅咎就疚鞠拘狙疽居驹菊局咀矩举沮聚拒据巨具距踞锯俱句惧炬剧捐鹃娟倦眷卷绢撅攫抉掘倔爵桔杰捷睫竭洁结解姐戒藉芥界借介疥诫届巾筋斤金今津襟紧锦仅谨进靳晋禁近烬浸尽劲荆兢觉决诀绝均菌钧军君峻俊竣浚郡骏喀咖卡咯开揩楷凯慨刊堪勘坎砍看康慷糠扛抗亢炕考拷烤靠坷苛柯棵磕颗科壳咳可渴克刻客课肯啃垦恳坑吭空恐孔控抠口扣寇枯哭窟苦酷库裤夸垮挎跨胯块筷侩快宽款匡筐狂框矿眶旷况亏盔岿窥葵奎魁傀馈愧溃坤昆捆困括扩廓阔垃拉喇蜡腊辣啦莱来赖蓝婪栏拦篮阑兰澜谰揽览懒缆烂滥琅榔狼廊郎朗浪捞劳牢老佬姥酪烙涝勒乐雷镭蕾磊累儡垒擂肋类泪棱楞冷厘梨犁黎篱狸离漓理李里鲤礼莉荔吏栗丽厉励砾历利傈例俐痢立粒沥隶力璃哩俩联莲连镰廉怜涟帘敛脸链恋炼练粮凉梁粱良两辆量晾亮谅撩聊僚疗燎寥辽潦了撂镣廖料列裂烈劣猎琳林磷霖临邻鳞淋凛赁吝拎玲菱零龄铃伶羚凌灵陵岭领另令溜琉榴硫馏留刘瘤流柳六龙聋咙笼窿隆垄拢陇楼娄搂篓漏陋芦卢颅庐炉掳卤虏鲁麓碌露路赂鹿潞禄录陆戮驴吕铝侣旅履屡缕虑氯律率滤绿峦挛孪滦卵乱掠略抡轮伦仑沦纶论萝螺罗逻锣箩骡裸落洛骆络妈麻玛码蚂马骂嘛吗埋买麦卖迈脉瞒馒蛮满蔓曼慢漫谩芒茫盲氓忙莽猫茅锚毛矛铆卯茂冒帽貌贸么玫枚梅酶霉煤没眉媒镁每美昧寐妹媚门闷们萌蒙檬盟锰猛梦孟眯醚靡糜迷谜弥米秘觅泌蜜密幂棉眠绵冕免勉娩缅面苗描瞄藐秒渺庙妙蔑灭民抿皿敏悯闽明螟鸣铭名命谬摸摹蘑模膜磨摩魔抹末莫墨默沫漠寞陌谋牟某拇牡亩姆母墓暮幕募慕木目睦牧穆拿哪呐钠那娜纳氖乃奶耐奈南男难囊挠脑恼闹淖呢馁内嫩能妮霓倪泥尼拟你匿腻逆溺蔫拈年碾撵捻念娘酿鸟尿捏聂孽啮镊镍涅您柠狞凝宁拧泞牛扭钮纽脓浓农弄奴努怒女暖虐疟挪懦糯诺哦欧鸥殴藕呕偶沤啪趴爬帕怕琶拍排牌徘湃派攀潘盘磐盼畔判叛乓庞旁耪胖抛咆刨炮袍跑泡呸胚培裴赔陪配佩沛喷盆砰抨烹澎彭蓬棚硼篷膨朋鹏捧碰坯砒霹批披劈琵毗啤脾疲皮匹痞僻屁譬篇偏片骗飘漂瓢票撇瞥拼频贫品聘乒坪苹萍平凭瓶评屏坡泼颇婆破魄迫粕剖扑铺仆莆葡菩蒲埔朴圃普浦谱曝瀑期欺栖戚妻七凄漆柒沏其棋奇歧畦崎脐齐旗祈祁骑起岂乞企启契砌器气迄弃汽泣讫掐洽牵扦钎铅千迁签仟谦乾黔钱钳前潜遣浅谴堑嵌欠歉枪呛腔羌墙蔷强抢橇锹敲悄桥瞧乔侨巧鞘撬翘峭俏窍切茄且怯窃钦侵亲秦琴勤芹擒禽寝沁青轻氢倾卿清擎晴氰情顷请庆琼穷秋丘邱球求囚酋泅趋区蛆曲躯屈驱渠取娶龋趣去圈颧权醛泉全痊拳犬券劝缺炔瘸却鹊榷确雀裙群然燃冉染瓤壤攘嚷让饶扰绕惹热壬仁人忍韧任认刃妊纫扔仍日戎茸蓉荣融熔溶容绒冗揉柔肉茹蠕儒孺如辱乳汝入褥软阮蕊瑞锐闰润若弱撒洒萨腮鳃塞赛三叁伞散桑嗓丧搔骚扫嫂瑟色涩森僧莎砂杀刹沙纱傻啥煞筛晒珊苫杉山删煽衫闪陕擅赡膳善汕扇缮墒伤商赏晌上尚裳梢捎稍烧芍勺韶少哨邵绍奢赊蛇舌舍赦摄射慑涉社设砷申呻伸身深娠绅神沈审婶甚肾慎渗声生甥牲升绳省盛剩胜圣师失狮施湿诗尸虱十石拾时什食蚀实识史矢使屎驶始式示士世柿事拭誓逝势是嗜噬适仕侍释饰氏市恃室视试收手首守寿授售受瘦兽蔬枢梳殊抒输叔舒淑疏书赎孰熟薯暑曙署蜀黍鼠属术述树束戍竖墅庶数漱恕刷耍摔衰甩帅栓拴霜双爽谁水睡税吮瞬顺舜说硕朔烁斯撕嘶思私司丝死肆寺嗣四伺似饲巳松耸怂颂送宋讼诵搜艘擞嗽苏酥俗素速粟僳塑溯宿诉肃酸蒜算虽隋随绥髓碎岁穗遂隧祟孙损笋蓑梭唆缩琐索锁所塌他它她塔獭挞蹋踏胎苔抬台泰酞太态汰坍摊贪瘫滩坛檀痰潭谭谈坦毯袒碳探叹炭汤塘搪堂棠膛唐糖倘躺淌趟烫掏涛滔绦萄桃逃淘陶讨套特藤腾疼誊梯剔踢锑提题蹄啼体替嚏惕涕剃屉天添填田甜恬舔腆挑条迢眺跳贴铁帖厅听烃汀廷停亭庭挺艇通桐酮瞳同铜彤童桶捅筒统痛偷投头透凸秃突图徒途涂屠土吐兔湍团推颓腿蜕褪退吞屯臀拖托脱鸵陀驮驼椭妥拓唾挖哇蛙洼娃瓦袜歪外豌弯湾玩顽丸烷完碗挽晚皖惋宛婉万腕汪王亡枉网往旺望忘妄威巍微危韦违桅围唯惟为潍维苇萎委伟伪尾纬未蔚味畏胃喂魏位渭谓尉慰卫瘟温蚊文闻纹吻稳紊问嗡翁瓮挝蜗涡窝我斡卧握沃巫呜钨乌污诬屋无芜梧吾吴毋武五捂午舞伍侮坞戊雾晤物勿务悟误昔熙析西硒矽晰嘻吸锡牺稀息希悉膝夕惜熄烯溪汐犀檄袭席习媳喜铣洗系隙戏细瞎虾匣霞辖暇峡侠狭下厦夏吓掀锨先仙鲜纤咸贤衔舷闲涎弦嫌显险现献县腺馅羡宪陷限线相厢镶香箱襄湘乡翔祥详想响享项巷橡像向象萧硝霄削哮嚣销消宵淆晓小孝校肖啸笑效楔些歇蝎鞋协挟携邪斜胁谐写械卸蟹懈泄泻谢屑薪芯锌欣辛新忻心信衅星腥猩惺兴刑型形邢行醒幸杏性姓兄凶胸匈汹雄熊休修羞朽嗅锈秀袖绣墟戌需虚嘘须徐许蓄酗叙旭序畜恤絮婿绪续轩喧宣悬旋玄选癣眩绚靴薛学穴雪血勋熏循旬询寻驯巡殉汛训讯逊迅压押鸦鸭呀丫芽牙蚜崖衙涯雅哑亚讶焉咽阉烟淹盐严研蜒岩延言颜阎炎沿奄掩眼衍演艳堰燕厌砚雁唁彦焰宴谚验殃央鸯秧杨扬佯疡羊洋阳氧仰痒养样漾邀腰妖瑶摇尧遥窑谣姚咬舀药要耀椰噎耶爷野冶也页掖业叶曳腋夜液一壹医揖铱依伊衣颐夷遗移仪胰疑沂宜姨彝椅蚁倚已乙矣以艺抑易邑屹亿役臆逸肄疫亦裔意毅忆义益溢诣议谊译异翼翌绎茵荫因殷音阴姻吟银淫寅饮尹引隐印英樱婴鹰应缨莹萤营荧蝇迎赢盈影颖硬映哟拥佣臃痈庸雍踊蛹咏泳涌永恿勇用幽优悠忧尤由邮铀犹油游酉有友右佑釉诱又幼迂淤于盂榆虞愚舆余俞逾鱼愉渝渔隅予娱雨与屿禹宇语羽玉域芋郁吁遇喻峪御愈欲狱育誉浴寓裕预豫驭鸳渊冤元垣袁原援辕园员圆猿源缘远苑愿怨院曰约越跃钥岳粤月悦阅耘云郧匀陨允运蕴酝晕韵孕匝砸杂栽哉灾宰载再在咱攒暂赞赃脏葬遭糟凿藻枣早澡蚤躁噪造皂灶燥责择则泽贼怎增憎曾赠扎喳渣札轧铡闸眨栅榨咋乍炸诈摘斋宅窄债寨瞻毡詹粘沾盏斩辗崭展蘸栈占战站湛绽樟章彰漳张掌涨杖丈帐账仗胀瘴障招昭找沼赵照罩兆肇召遮折哲蛰辙者锗蔗这浙珍斟真甄砧臻贞针侦枕疹诊震振镇阵蒸挣睁征狰争怔整拯正政帧症郑证芝枝支吱蜘知肢脂汁之织职直植殖执值侄址指止趾只旨纸志挚掷至致置帜峙制智秩稚质炙痔滞治窒中盅忠钟衷终种肿重仲众舟周州洲诌粥轴肘帚咒皱宙昼骤珠株蛛朱猪诸诛逐竹烛煮拄瞩嘱主著柱助蛀贮铸筑住注祝驻抓爪拽专砖转撰赚篆桩庄装妆撞壮状椎锥追赘坠缀谆准捉拙卓桌琢茁酌啄着灼浊兹咨资姿滋淄孜紫仔籽滓子自渍字鬃棕踪宗综总纵邹走奏揍租足卒族祖诅阻组钻纂嘴醉最罪尊遵昨左佐柞做作坐座";
	var charSet = charPYStr;
	for (var n = 0; n < (char1.length > char2.length ? char1.length : char2.length); n++) {
		if (char1.charAt(n) != char2.charAt(n)) {
			if (mode) {
				return (charSet.indexOf(char1.charAt(n)) > charSet.indexOf(char2.charAt(n)) ? 1 : -1);
			} else {
				return (charSet.indexOf(char1.charAt(n)) < charSet.indexOf(char2.charAt(n)) ? 1 : -1);
			}
			break;
		}
	}
	return (0);
}
function tableSort(the_td) {//
	arrowUp = document.createElement("SPAN");
	arrowUp.innerHTML = "5";
	arrowUp.id = 'arrowUp';
	arrowUp.style.cssText = "PADDING-RIGHT: 0px; MARGIN-TOP: -3px; PADDING-LEFT: 0px; FONT-SIZE: 10px; MARGIN-BOTTOM: 2px; PADDING-BOTTOM: 2px; OVERFLOW: hidden; WIDTH: 10px; COLOR: blue; PADDING-TOP: 0px; FONT-FAMILY: webdings; HEIGHT: 11px";
	arrowDown = document.createElement("SPAN");
	arrowDown.innerHTML = "6";
	arrowDown.id = 'arrowDown';
	arrowDown.style.cssText = "PADDING-RIGHT: 0px; MARGIN-TOP: -3px; PADDING-LEFT: 0px; FONT-SIZE: 10px; MARGIN-BOTTOM: 2px; PADDING-BOTTOM: 2px; OVERFLOW: hidden; WIDTH: 10px; COLOR: blue; PADDING-TOP: 0px; FONT-FAMILY: webdings; HEIGHT: 11px";

	if(the_td.mode=='true'){
		the_td.mode=false;
	}else{
		the_td.mode=true;
	}
	var cur_col = the_td.cellIndex;
	var the_table = getPapaElement(the_td, "table");
	if (the_table.rows.length > 200) {
		if (!confirm("当前表的行数超过200行,排序将耗费比较长的时间,确定要排序吗?")) {
			return false;
		}
	}
	if (sort_col != null) {
		with (the_table.rows[0].cells[sort_col]) {
			removeChild(lastChild);
		}
	}
	with (the_table.rows[0].cells[cur_col]) {
		appendChild(the_td.mode ? arrowUp : arrowDown);
	}
	 
	//alert(the_td.mode);
	sort_tab(the_table, cur_col, the_td.mode);
	sort_col = cur_col;	
	
	
}
//该方法用于扩展前面有隐藏列的情况
function tableSort_hc(the_td,num) {//
	arrowUp = document.createElement("SPAN");
	arrowUp.innerHTML = "5";
	arrowUp.id = 'arrowUp';
	arrowUp.style.cssText = "PADDING-RIGHT: 0px; MARGIN-TOP: -3px; PADDING-LEFT: 0px; FONT-SIZE: 10px; MARGIN-BOTTOM: 2px; PADDING-BOTTOM: 2px; OVERFLOW: hidden; WIDTH: 10px; COLOR: blue; PADDING-TOP: 0px; FONT-FAMILY: webdings; HEIGHT: 11px";
	arrowDown = document.createElement("SPAN");
	arrowDown.innerHTML = "6";
	arrowDown.id = 'arrowDown';
	arrowDown.style.cssText = "PADDING-RIGHT: 0px; MARGIN-TOP: -3px; PADDING-LEFT: 0px; FONT-SIZE: 10px; MARGIN-BOTTOM: 2px; PADDING-BOTTOM: 2px; OVERFLOW: hidden; WIDTH: 10px; COLOR: blue; PADDING-TOP: 0px; FONT-FAMILY: webdings; HEIGHT: 11px";

	if(the_td.mode=='true'){
		the_td.mode=false;
	}else{
		the_td.mode=true;
	}
	var cur_col = the_td.cellIndex+num;
	var the_table = getPapaElement(the_td, "table");
	if (the_table.rows.length > 200) {
		if (!confirm("当前表的行数超过200行,排序将耗费比较长的时间,确定要排序吗?")) {
			return false;
		}
	}
	if (sort_col != null) {
		with (the_table.rows[0].cells[sort_col]) {
			removeChild(lastChild);
		}
	}
	with (the_table.rows[0].cells[cur_col]) {
		appendChild(the_td.mode ? arrowUp : arrowDown);
	}
	 
	//alert(the_td.mode);
	sort_tab(the_table, cur_col, the_td.mode);
	sort_col = cur_col;	
}


function sort_tab(the_tab, col, mode) {//
	var tab_arr = new Array();
	var i;
	var start = new Date;
	var rowLen=the_tab.rows.length;
	var tableHTML="<table summary='' id='rsTable' width='100%' class='"+the_tab.className+"'>";
	tableHTML+="<thead><tr>" +the_tab.rows[0].innerHTML+"</tr></thead>";
	var rowLen=1;
	var maxLen=the_tab.rows.length;
	var tdLen=the_tab.getElementsByTagName('tr')[0].getElementsByTagName('td').length;
	
	//判断是否有CHECKBOX存在
	for(i=1;i<maxLen;i++){
		//判断是否存在CHECKBOX
		var blog=true;
		if(the_tab.getElementsByTagName('tr')[i].innerHTML.toLowerCase().indexOf("checkbox")!=-1){
			if(the_tab.getElementsByTagName('tr')[i].getElementsByTagName('input')[0].value!="" 
			&& the_tab.getElementsByTagName('tr')[i].getElementsByTagName('input')[0].value!=null
			&& the_tab.getElementsByTagName('tr')[i].getElementsByTagName('input')[0].value!="on"){
				rowLen++;
			}
		}else{
			for(j=0;j<tdLen;j++){
				var tdValue=the_tab.getElementsByTagName('tr')[i].getElementsByTagName('td')[j].innerHTML;
				if(jQuery.trim(tdValue)=="&nbsp;"){
					blog=false;
				}
			}
			if(blog){
				rowLen++;
			}
		}
	}

	for (i = 1; i < rowLen; i++) {
		tab_arr.push(new Array(jQuery(the_tab.rows[i].cells[col]).text().toLowerCase(), the_tab.rows[i]));
	}
	
	function SortArr(mode) {
		return function (arr1, arr2) {
			var flag;
			var a, b;
			a = arr1[0];
			b = arr2[0];
			if (/^[+\-]?\d+(.\d + )?$/.test(trim(a)) && /^[+\-]?\d+(.\d + )?$/.test(trim(b))) {
				a = eval(a);
				b = eval(b);
				flag = mode ? (a > b ? 1 : (a < b ? -1 : 0)) : (a < b ? 1 : (a > b ? -1 : 0));
			} else {
				a = a.toString();
				b = b.toString();
				if (a.charCodeAt(0) >= 19968 && b.charCodeAt(0) >= 19968) {
					flag = judge_CN(a, b, mode);
				} else {
					flag = mode ? (a > b ? 1 : (a < b ? -1 : 0)) : (a < b ? 1 : (a > b ? -1 : 0));
				}
			}
			return flag;
		};
	}
	tab_arr.sort(SortArr(mode));
	tableHTML+="<tbody>";
	
	for (i = 0; i < tab_arr.length; i++) {
		tableHTML+= tab_arr[i][1].outerHTML;
	}
	
	if(maxLen!=rowLen){
	for(i=rowLen;i<maxLen;i++){
			tableHTML+=the_tab.rows[i].outerHTML;
	}
	}
	tableHTML+="</tbody></table>";
	
	tableHTML=tableHTML.replace("#ffdead",'');
	the_tab.outerHTML=tableHTML;
	window.status = " (Time spent: " + (new Date - start) + "ms)";
}

function getPapaElement(the_ele, the_tag) {//
	the_tag = the_tag.toLowerCase();
	if (the_ele.tagName.toLowerCase() == the_tag) {
		return the_ele;
	}
	while (the_ele = the_ele.offsetParent) {
		if (the_ele.tagName.toLowerCase() == the_tag) {
			return the_ele;
		}
	}
	return (null);
}
function rowOnClick(objTr) {//
	if (curr_row != null && curr_row.tagName.toLowerCase() == "tr") {
		curr_row.style.backgroundColor = obj_bgc;
	}
	curr_row = objTr;
	if(document.getElementById("ycxh")){
		document.getElementById("ycxh").value=jQuery(curr_row.cells[1]).text().trim();
	}
	obj_bgc = curr_row.style.backgroundColor;
	curr_row.style.backgroundColor = cur_bgc;
}

function rowOnClick2(objTr) {//
	if (curr_row != null && curr_row.tagName.toLowerCase() == "tr") {
		curr_row.style.backgroundColor = obj_bgc;
	}
	curr_row = objTr;
	obj_bgc = curr_row.style.backgroundColor;
	curr_row.style.backgroundColor = cur_bgc2;
}

function subWinOnLoad() {
	var arrowUpText = '';
	var arrowDownText = '';
	if(window.dialogArguments.document.getElementById('arrowUp')){
		arrowUpText = '5';
		window.dialogArguments.document.getElementById('arrowUp').innerText='';
	}
	if(window.dialogArguments.document.getElementById('arrowDown')){
		arrowDownText = '6';	
		window.dialogArguments.document.getElementById('arrowDown').innerText='';		
	}
	
	
	checkWinType();
	var tip = window.dialogArguments.title_m.innerText;
	title_m.innerText = tip;
	var d_html = window.dialogArguments.document.forms[0].htmlContent.value;
	tmpdiv.innerHTML = d_html;
	var def_1 = "";
	var def_2 = "";
	var def_3 = "";
	var def_4 = "";
	var def_5 = "";
	var tmpTname = window.dialogArguments.document.forms[0].tname.value;
	document.forms[0].codeType.value = window.dialogArguments.document.forms[0].codeType.value;
	document.forms[0].tName.value = tmpTname;
	document.forms[0].key.value = window.dialogArguments.rsTable.rows[0].cells[1].id;
	if (document.getElementsByTagName("select").length == 2) {
		if (window.dialogArguments.curr_row != null) {
			if( document.getElementById('tname').value=='zgkd_hdnrsxb'){
			def_1 = window.dialogArguments.curr_row.cells[2].innerText;
			def_2 = window.dialogArguments.curr_row.cells[3].innerText;
			}else if(document.getElementById('tname').value=='jxjdmb'&&window.dialogArguments.document.forms[0].xxdm.value=="10338"){
				def_1 = window.dialogArguments.curr_row.cells[3].innerText;
				def_2 = window.dialogArguments.curr_row.cells[6].innerText;
			}else if(document.getElementById('tname').value=='zjlg_wjkfb'){
				def_1 = window.dialogArguments.curr_row.cells[1].innerText;
				def_2 = window.dialogArguments.curr_row.cells[2].innerText;
			}else if(document.getElementById('tname').value=='xmlg_jxjdmb'){
			   def_1 = window.dialogArguments.curr_row.cells[3].innerText;
			   def_2 = window.dialogArguments.curr_row.cells[5].innerText;
			}else if( document.getElementById('tname').value=='xszz_fsbzffb'){
				def_1 = window.dialogArguments.curr_row.cells[2].innerText;
				def_2 = window.dialogArguments.curr_row.cells[3].innerText;
			}else{
			   def_1 = window.dialogArguments.curr_row.cells[3].innerText;
			   def_2 = window.dialogArguments.curr_row.cells[4].innerText;
			}
		}
		document.forms[0].tmpSel_1.value = window.dialogArguments.document.forms[0].selToInit1.value;
		document.forms[0].tmpSel_2.value = window.dialogArguments.document.forms[0].selToInit2.value;
		initList("tmpsel_1", "sel1", def_1);
		initList("tmpsel_2", "sel2", def_2);
	} else if(document.getElementsByTagName("select").length == 3){
		if (window.dialogArguments.curr_row != null) {
			if( document.getElementById('tname').value=='zgkd_xlcdsxb'){
			def_1 = window.dialogArguments.curr_row.cells[2].innerText;
			def_2 = window.dialogArguments.curr_row.cells[3].innerText;
			def_3 = window.dialogArguments.curr_row.cells[4].innerText;
			}else if( document.getElementById('tname').value=='yrdwdmb'){
			def_1 = window.dialogArguments.curr_row.cells[5].innerText;
			def_2 = window.dialogArguments.curr_row.cells[6].innerText;
			def_3 = window.dialogArguments.curr_row.cells[7].innerText;
			}else if( document.getElementById('tname').value=='zjcm_xwjxjrs'){
			def_1 = window.dialogArguments.curr_row.cells[2].innerText;
			def_2 = window.dialogArguments.curr_row.cells[3].innerText;
			def_3 = window.dialogArguments.curr_row.cells[4].innerText;
			}
			else{
			def_1 = window.dialogArguments.curr_row.cells[4].innerText;
			def_2 = window.dialogArguments.curr_row.cells[5].innerText;
			def_3 = window.dialogArguments.curr_row.cells[6].innerText;
			}
		}
		document.forms[0].tmpSel_1.value = window.dialogArguments.document.forms[0].selToInit1.value;
		document.forms[0].tmpSel_2.value = window.dialogArguments.document.forms[0].selToInit2.value;
		document.forms[0].tmpSel_3.value = window.dialogArguments.document.forms[0].selToInit3.value;
		initList("tmpsel_1", "sel1", def_1);
		initList("tmpsel_2", "sel2", def_2);
		initList("tmpsel_3", "sel3", def_3);
	}else if(document.getElementsByTagName("select").length == 4){
	    if (window.dialogArguments.curr_row != null) {
			def_1 = window.dialogArguments.curr_row.cells[3].innerText;
			def_2 = window.dialogArguments.curr_row.cells[4].innerText;
			if( document.getElementById('tname').value=='sztz_xmdmb'){
			   def_3 = window.dialogArguments.curr_row.cells[7].innerText;
			}else{
			   def_3 = window.dialogArguments.curr_row.cells[5].innerText;
			}
			def_4 = window.dialogArguments.curr_row.cells[6].innerText;
		}
		document.forms[0].tmpSel_1.value = window.dialogArguments.document.forms[0].selToInit1.value;
		document.forms[0].tmpSel_2.value = window.dialogArguments.document.forms[0].selToInit2.value;
		document.forms[0].tmpSel_3.value = window.dialogArguments.document.forms[0].selToInit3.value;
		document.forms[0].tmpSel_4.value = window.dialogArguments.document.forms[0].selToInit4.value;
		initList("tmpsel_1", "sel1", def_1);
		initList("tmpsel_2", "sel2", def_2);
		initList("tmpsel_3", "sel3", def_3);
		initList("tmpsel_4", "sel4", def_4);
	}else if(document.getElementsByTagName("select").length == 5){
		if (window.dialogArguments.curr_row != null) {
			def_1 = window.dialogArguments.curr_row.cells[2].innerText;
			def_2 = window.dialogArguments.curr_row.cells[3].innerText;
			def_3 = window.dialogArguments.curr_row.cells[5].innerText;
			def_4 = window.dialogArguments.curr_row.cells[6].innerText;
			def_5 = window.dialogArguments.curr_row.cells[7].innerText;
		}		
		document.forms[0].tmpSel_1.value = window.dialogArguments.document.forms[0].selToInit1.value;
		document.forms[0].tmpSel_2.value = window.dialogArguments.document.forms[0].selToInit2.value;
		document.forms[0].tmpSel_3.value = window.dialogArguments.document.forms[0].selToInit3.value;
		document.forms[0].tmpSel_4.value = window.dialogArguments.document.forms[0].selToInit4.value;
		document.forms[0].tmpSel_5.value = window.dialogArguments.document.forms[0].selToInit5.value;
		initList("tmpsel_1", "sel1", def_1);
		initList("tmpsel_2", "sel2", def_2);
		initList("tmpsel_3", "sel3", def_3);
		initList("tmpsel_4", "sel4", def_4);
		initList("tmpsel_5", "sel5", def_5);
	}else {
		if (document.getElementsByTagName("select").length == 1) {
			if (window.dialogArguments.curr_row != null) {
				var tname = document.getElementById('tname').value;
				if(tname=='sjzfpyb'){
					def_1 = window.dialogArguments.curr_row.cells[1].innerText;
				}else if(tname=='zgkd_hdnrb'){
					def_1 = window.dialogArguments.curr_row.cells[2].innerText;
				}else if(tname=='nblg_jxgl_jxzw'){
					def_1 = window.dialogArguments.curr_row.cells[2].innerText;
				}else if(tname=='nblg_jxgl_jxcxgb'){
					def_1 = window.dialogArguments.curr_row.cells[2].innerText;
				}else if(tname=='zjlg_zbmc'){
					def_1 = window.dialogArguments.curr_row.cells[2].innerText;
				}else if(tname=='cflbdmb'){
				    def_1 = window.dialogArguments.curr_row.cells[4].innerText;
				}else if(tname=='xmlg_szdw_aqbgmc'){
				    def_1 = window.dialogArguments.curr_row.cells[2].innerText;
				}else if(tname=='zjjt_cxf_dj3'){
				    def_1 = window.dialogArguments.curr_row.cells[2].innerText;
				}else if(tname=='bjxh_stdygbb' || tname=='bjxh_sttygbb'){
				    def_1 = window.dialogArguments.curr_row.cells[2].innerText;
				}else{
					def_1 = window.dialogArguments.curr_row.cells[3].innerText;
				}
			}
			document.forms[0].tmpSel_1.value = window.dialogArguments.document.forms[0].selToInit1.value;
			initList("tmpsel_1", "sel1", def_1);
		}
	}
	if(window.dialogArguments.document.getElementById('arrowDown')){
		window.dialogArguments.document.getElementById('arrowDown').innerText = arrowDownText;
	}
	if(window.dialogArguments.document.getElementById('arrowUp')){
		window.dialogArguments.document.getElementById('arrowUp').innerText = arrowUpText;
	}
}
function chkCodeConf(obj) {
	var inputV = document.getElementsByName("newCode");
	var tN = document.forms[0].tName.value;
	var array= document.getElementsByTagName('tr');
	var xxdm = $("xxdm").value;
	if(xxdm=='10290'&&tN=="sztz_kmdmb"){//中国矿业大学科目设立
	     for (i = 0; i < inputV.length-1; i++) {
		  if (inputV[i].value == "") {
			alert("前三项输入不得为空！");
			return false;
		  }
	     }	     
	}else{
	    for (i = 0; i < inputV.length; i++) {
		  if (inputV[i].value == "") {
			alert("所有的输入不得为空！");
			return false;
		  }
	    }
	}
	//?ì???ú??±?????????
	var result = checkForm(tN,inputV[0].value);
	if(result == false) return false;
	
	if(xxdm !="12702"){
		if(inputV[0].value.match(/^\d+\.{0,1}\d{0,3}$/)==null){
			alert("代码必需为数字！");
			return false;
		}
	}
	if (document.forms[0].doType.value == "add" || document.forms[0].doType.value == "modi") {
		for (i = 1; i < window.dialogArguments.rsTable.rows.length; i++) {
			if(tN=='sjzfpyb'){
				if (replaceChar(inputV[0].value, " ", "") == replaceChar(window.dialogArguments.rsTable.rows[i].cells[1].innerText, " ", "")&&
				    replaceChar(array[2].cells[1].getElementsByTagName('input')[0].value, " ", "") == replaceChar(window.dialogArguments.rsTable.rows[i].cells[2].innerText, " ", "")) {
					alert("该试卷的在该分值范围已有评语！");
					return false;
				}
			}else if(tN =="zjlg_wjkfb"){
				if ((replaceChar(inputV[0].value, " ", "") == replaceChar(window.dialogArguments.rsTable.rows[i].cells[1].innerText, " ", ""))
				&&(replaceChar(inputV[1].value, " ", "") == replaceChar(window.dialogArguments.rsTable.rows[i].cells[2].innerText, " ", ""))) {
					alert("该学年已设置该项扣分");
					return false;
				}
			}else{
			  if (document.forms[0].doType.value == "add") {
				if (replaceChar(inputV[0].value, " ", "") == replaceChar(window.dialogArguments.rsTable.rows[i].cells[1].innerText, " ", "")) {
				    alert("代码已经存在！");
					return false;
				}
			  }	
			}
		}
		if(tN=="sztz_xmdmb"){
			var ksrq = document.getElementById('sqkssj').value.replace(" ","");
	        var jsrq = document.getElementById('sqjssj').value.replace(" ","");
	       if(ksrq>jsrq){
		     alert('申请开始时间晚于结束时间！');
		     return false;
		    }
	    }
	}
	if(obj!=null){
		obj.disabled=true;
	}
	document.forms[0].submit();
}
function replaceChar(conversionString, inChar, outChar) {
	var convertedString = conversionString.split(inChar);
	convertedString = convertedString.join(outChar);
	return convertedString;
}
function showTopWin(url, w, h,scrollbar) {
	url = url.replace("/xgxt/","");
	//var info = "dialogWidth:"+w+"px;dialogHeight:"+h+"px";
	
	var info = "Status:YES;dialogWidth:" + w + "px;dialogHeight:" + h + "px;help:no;";
	if (scrollbar != null){
		showModalDialog(url, window, info,scrollbar);
		return false;
	}
	showModalDialog(url, window, info);
}

//如果要在页面把文本框变成下拉列表
//1.找到查询页面中所有列的数量,包含行号列,既num==?所在判断语句里面
//2.找到要改成下拉列表字段列所在位置，既rsTable.rows[0].cells[?].innerText;语句前
//3.做表名和学校判断，加入下拉列表语句
function codeConf(tag) {
	if(document.getElementById('arrowDown')){
		document.getElementById('arrowDown').innerText='';
	}
	if(document.getElementById('arrowUp')){
		document.getElementById('arrowUp').innerText='';
	}
	var url = "/xgxt/codeConf.do?tName=";
	var codeByDept = document.getElementById('codeByDept').value;
	var userName = document.getElementById('userName').value;
	var realTable = document.getElementById("realTable").value;
	var dm = "";
	
	if(curr_row != null){
		dm = curr_row.cells[1].innerText;
	}
	if($("xxdm")){
		xxdm=document.forms[0].xxdm.value;
	}
	var tab_name = document.getElementById("tableName").value;
	url += document.forms[0].tname.value;
	if (tag == "modi" && curr_row == null) {
		alert("请选择要修改的记录！\n（单击相应的行）");
		return false;
	}
	if (tag == "del") {
		if (curr_row == null) {
			alert("请选择要删除的记录！\n（单击相应的行）");
			return false;
		} else {
			//?ì????????????·?????????????????·???
			var value = replaceChar(curr_row.cells[2].innerText," ","");
			var key = replaceChar(curr_row.cells[1].innerText," ","");			
			var canDelete = checkCanOper(realTable,key,value,"delete");
			if(canDelete == false)	return false;
			
			if(codeByDept != null && codeByDept == "true"){
				//判断是否可以删除
				var pkValue = dm+tab_name;
				systemFunction.userIsOperator(userName,pkValue,function(data){
					if(data != null && data == true){
						if (confirm("（请勿轻易删除该行）\n确定要删除选中的行吗？")) {
							document.forms[0].delV.value = replaceChar(curr_row.cells[1].innerText," ","");
							document.forms[0].delKey.value = rsTable.rows[0].cells[1].id;
							if(document.getElementById("realTable").value=="bjdydygxb"){
								document.forms[0].delV.value = replaceChar(curr_row.cells[1].innerText," ","")+replaceChar(curr_row.cells[2].innerText," ","");
								document.forms[0].delKey.value = rsTable.rows[0].cells[1].id+"!!"+rsTable.rows[0].cells[2].id;
							}
							document.forms[0].action = url + "&doType=del";
							document.forms[0].submit();
							return true;
						} else {
							return false;
						}
					}else{
						alert("该条记录不是您添加的，您不能删除！");
						return false;
					}
				});
			}else{
				if (confirm("（请勿轻易删除该行）\n确定要删除选中的行吗？")) {
					document.forms[0].delV.value = replaceChar(curr_row.cells[1].innerText," ","");
					document.forms[0].delKey.value = rsTable.rows[0].cells[1].id;					
					if(document.getElementById("realTable").value=="bjdydygxb"){
						document.forms[0].delV.value = replaceChar(curr_row.cells[1].innerText," ","")+replaceChar(curr_row.cells[2].innerText," ","");
						document.forms[0].delKey.value = rsTable.rows[0].cells[1].id+"!!"+rsTable.rows[0].cells[2].id;
					}if(document.getElementById("realTable").value=="sjzfpyb"){
						document.forms[0].delV.value = replaceChar(curr_row.cells[1].innerText," ","")+replaceChar(curr_row.cells[2].innerText," ","");
						document.forms[0].delKey.value = rsTable.rows[0].cells[1].id+"||"+rsTable.rows[0].cells[2].id;						
					}					
					document.forms[0].action = url + "&doType=del";
					document.forms[0].submit();
					return true;
				} else {
					return false;
				}
			}
		}
		return;
	}
	
	if(tag == "modi"){
		//?ì????????????·?????????????????·???
		var value = replaceChar(curr_row.cells[2].innerText," ","");
		var key = replaceChar(curr_row.cells[1].innerText," ","")
		var canDelete = checkCanOper(realTable,key,value,"modi");
		if(canDelete == false)	return false;
	}
	
	var num = rsTable.rows[0].cells.length;
	var dd_html = "<br>";
	var w = 0;
	var h = 0;
	if(codeByDept != null && codeByDept == "true"){//只能添加人操作
	    num = num-3;
	}
	if (tag == "modi") {
		dd_html += "<table width='98%' align='center' class='tbstyle'><thead><tr align='center'><td colspan='2'> * 修 改 代 码 * <input type='hidden' name='doType' value='modi'></td></tr></thead>";
	} else {
		dd_html += "<table width='98%' align='center' class='tbstyle'><thead><tr align='center'><td colspan='2'> * 添 加 代 码 * <input type='hidden' name='doType' value='add'></td></tr></thead>";
	}
	if (num == 3) {
		dd_html += "<tr><td align='right' width='35%'>";
		dd_html += rsTable.rows[0].cells[1].innerText;
		dd_html += "：</td><td align='left' width='65%'><input type='text' maxlength='30' style='width:98%;cursor:hand;color:#999999' name='newCode' value='";
		if (tag == "modi") {
			dd_html += replaceChar(curr_row.cells[1].innerText, " ", "");
			dd_html += "' readonly='true";
		}
		dd_html += "'></td></tr><tr><td align='right' width='35%'>";
		dd_html += rsTable.rows[0].cells[2].innerText;
		if(document.forms[0].tName.value=="cfyydmb" ){
			dd_html += "：</td><td width='65%'><textarea cols='40' rows='10' style='width:98%' name='newCode'>";
			if (tag == "modi") {
				dd_html += replaceChar(curr_row.cells[2].innerText, " ", "");
			}
			dd_html += "</textarea></td></tr>";
			w = 490;
			h = 440;
		} else {
			dd_html += "：</td><td align='left' width='65%'><input type='text' maxlength='30' style='width:98%' name='newCode' value='";
			if (tag == "modi") {
				dd_html += replaceChar(curr_row.cells[2].innerText, " ", "");
			}
			if(xxdm!=null && xxdm!="" && xxdm=="11551" && document.forms[0].tName.value == "gwmcdmb"){
       			dd_html += "' onkeyup=\"value=value.replace('-','')\"></td></tr>";			
			}else{
				dd_html += "'></td></tr>";
			}
			w = 450;
			h = 270;
		}
		
	} else {
		if (num == 4) {	
			dd_html += "<tr><td align='right' width='35%'>";
			dd_html += rsTable.rows[0].cells[1].innerText;
			if(document.forms[0].tName.value=="sjzfpyb"){
				dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr>";
				h = 290;
			}else if(document.forms[0].tName.value=="zjlg_wjkfb"){
				dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr>";
				h = 290;
			}else if(document.forms[0].tName.value=="nwwsdjdmb"){
				dd_html += "：</td><td align='left'><input type='text' maxlength='20' style='width:98%;cursor:hand;color:#999999' name='newCode' value='";
				if (tag == "modi") {
				dd_html += replaceChar(curr_row.cells[1].innerText, " ", "");
				dd_html += "' readonly='true";
				}
				dd_html += "'></td></tr>";
			}else{
			dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%;cursor:hand;color:#999999' name='newCode' value='";
			if (tag == "modi") {
				dd_html += replaceChar(curr_row.cells[1].innerText, " ", "");
				dd_html += "' readonly='true";
			}
			dd_html += "'></td></tr>";
			}
			dd_html += "<tr><td align='right'>";
			dd_html += rsTable.rows[0].cells[2].innerText;
//			if(document.forms[0].tName.value=="nwwsdjdmb"){
//				dd_html += "：</td><td align='left'><input type='text' maxlength='5' style='width:98%;cursor:hand;' name='newCode' value='";
//				if (tag == "modi") {
//				dd_html += replaceChar(curr_row.cells[2].innerText, " ", "");
//				dd_html += "'";
//				}
//				dd_html += "></td></tr>";
//			}else{

			if(document.forms[0].tName.value=="zgkd_hdnrb"){
				dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:70%;'></select>";
				dd_html +="</td></tr>";
			}else if(document.forms[0].tName.value=="zjlg_zbmc"){
				dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr>";
				h = 290;
			}else if(document.forms[0].tName.value=="xmlg_szdw_aqbgmc"){
				dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";	
			}else if(document.forms[0].tName.value == "zjlg_wjkfb"){
				dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";				
			}else if(document.forms[0].tName.value == "nblg_jxgl_jxzw"){
				dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";				
			}else if(document.forms[0].tName.value == "nblg_jxgl_bzzwb"){
				dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";				
			}else if(document.forms[0].tName.value == "zjjt_cxf_dj3"){
				dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";				
			}else if(document.forms[0].tName.value == "bjxh_stdygbb" || document.forms[0].tName.value == "bjxh_sttygbb"){
				dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";				
			}else if(document.forms[0].tName.value=="gdby_dtjs_ydlxdmb"){
				dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr>";
				h = 290;
			}else{
				dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value='";
				if (tag == "modi") {
					dd_html += replaceChar(curr_row.cells[2].innerText, " ", "");				
				}
				dd_html += "'></td></tr>";
			}
//			}	
			dd_html += "<tr><td align='right'>";
			dd_html += rsTable.rows[0].cells[3].innerText;
			
			if (document.forms[0].tName.value!="xmlg_szdw_aqbgmc" && (document.forms[0].codeType.value == "other" || document.forms[0].codeType.value == "fdyxx")) {
				dd_html += "：</td><td align='left'><textarea name='newCode' style='width:98%;' rows='6'>";
				if (tag == "modi") {
					dd_html += replaceChar(curr_row.cells[3].innerText, " ", "");
				}
				dd_html += "</textarea></td></tr>";
				h = 350;
			}else if (document.forms[0].tName.value=="rychdmb"&&xxdm=="10338"){
				dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:70%;'></select>";
				dd_html +="</td></tr>";
				h = 300;
			}else if (document.forms[0].tName.value=="gdzllbb"&&xxdm=="11122"){
				dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:70%;'></select>";
				dd_html +="</td></tr>";
				h = 300;
			}else if (document.forms[0].tName.value=="stu_gdzlb"&&xxdm=="11122"){
				dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:70%;'></select>";
				dd_html +="</td></tr>";
				h = 300;
			}else if (document.forms[0].tName.value=="zbrydmb"){
				dd_html += rsTable.rows[0].cells[3].innerText;
				dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:70%;'></select>";
				dd_html +="</td></tr>";
				h = 300;
			}else {
				if (document.forms[0].tName.value=="dm_ydlb") {					
					dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr>";
					h = 290;					
				} else {
//				  if(document.forms[0].tName.value=="nwwsdjdmb"){
//						dd_html += "：</td><td align='left'><input type='text' maxlength='4' style='width:98%;cursor:hand;' name='newCode' value='";
//						if (tag == "modi") {
//						dd_html += replaceChar(curr_row.cells[3].innerText, " ", "");
//						dd_html += "' onkeypress='return sztzNumInputValue(this,6,event)'";
//						}
//						dd_html += "></td></tr>";
//				  }else{
				  	dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' " ;
					if(document.forms[0].tName.value=="qslbdmb"
					  ||document.forms[0].tName.value=="sztz_kmdmb"
					  ||document.forms[0].tName.value=="xsjldmb"
					  ||document.forms[0].tName.value=="xscfdmb"){
						dd_html +="onkeypress='return sztzNumInputValue(this,3,event)'";
					}
					dd_html += "value='";					
					if (tag == "modi") {
						dd_html += replaceChar(curr_row.cells[3].innerText, " ", "");
					}					
					dd_html += "'></td></tr>";
//				  }
					h = 290;
				}
			}
			w = 450;
		} else {
			if (num == 5) {
				dd_html += "<tr><td align='right' width='35%'>";
				dd_html += rsTable.rows[0].cells[1].innerText;
				dd_html += "：</td><td align='left'><input type='text' id='sztzCode' maxlength='30' style='width:98%;cursor:hand;color:#999999' name='newCode' value='";
					if (tag == "modi") {
						dd_html += replaceChar(curr_row.cells[1].innerText, " ", "");
						dd_html += "' readonly='true";
					}
				dd_html += "'></td></tr><tr><td align='right'>";
				dd_html += rsTable.rows[0].cells[2].innerText;
				if(document.forms[0].tName.value == "sztz_bjdmb"){
					dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";				
				}else if(document.forms[0].tName.value=="zgkd_hdnrsxb"){
				dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:70%;' onchange='chkm();'></select></td></tr><tr><td align='right'>";
				}else if(document.forms[0].tName.value == "xszz_fsbzffb"){
				dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";
				}else{					
				dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value='";
				if (tag == "modi") {
					dd_html += replaceChar(curr_row.cells[2].innerText, " ", "");
				}
				dd_html += "'></td></tr><tr><td align='right'>";
				}
				dd_html += rsTable.rows[0].cells[3].innerText;
				h = 320;
				w = 450;
				if (document.forms[0].codeType.value == "comm") {
					dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";
					dd_html += rsTable.rows[0].cells[4].innerText;
					dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:99%;'></select></td></tr>";		
				}else if(document.forms[0].tName.value=="zgkd_hdnrsxb"){
					dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";	
					dd_html += rsTable.rows[0].cells[4].innerText;
					dd_html += "：<br></td><td align='left'><input type='text' name='newCode' style='width:98%;' value='";
					if (tag == "modi") {
					dd_html += replaceChar(curr_row.cells[4].innerText, " ", "");				
					}
					dd_html += "'></input></td></tr>";
				}else if(document.forms[0].tName.value == "xszz_fsbzffb"){
					dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:99%;'></select></td></tr>";	
					dd_html += "<tr><td align='right'>";
					dd_html += rsTable.rows[0].cells[4].innerText;
					dd_html += "：</td><td align='left'><input type='text' onkeypress='return sztzNumInputValue(this,4,event)' name='newCode' style='width:98%;' value='";	
					if (tag == "modi") {
						dd_html += replaceChar(curr_row.cells[4].innerText, " ", "");
					}
					dd_html += "'></input></td></tr>";	
				}else {
					if (document.forms[0].codeType.value == "health" || document.forms[0].codeType.value == "sztz" ) {
						if(document.forms[0].tName.value == "sztz_hjjbdmb"||document.forms[0].tName.value == "sztz_bjdmb"){
							if(document.forms[0].tName.value == "sztz_bjdmb"){
								dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";								
							    dd_html += rsTable.rows[0].cells[4].innerText;
							    dd_html += "：<br></td><td align='left'><input type='text' name='newCode' style='width:98%;' value='";							    
							}else{
							dd_html += "：</td><td align='left'><select id='sel1' onchange=\"AutoFill(this,'";
							dd_html += tag;
							dd_html += "')\" name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";
							dd_html += rsTable.rows[0].cells[4].innerText;
							dd_html += "：<br></td><td align='left'><input type='text' onkeypress='return sztzNumInputValue(this,4,event)' name='newCode' style='width:98%;' value='";
							}					
							if (tag == "modi") {
								dd_html += replaceChar(curr_row.cells[4].innerText, " ", "");
							}
							dd_html += "'></input></td></tr>";
							h = 400;
							w = 450;
						}else{
						    if(document.forms[0].tName.value == "sztz_kmdmb"){
						       dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' ";
						       dd_html +=" onkeypress='return sztzNumInputValue(this,6,event)' ";
						       dd_html += "value='";					
					           if (tag == "modi") {
						          dd_html += replaceChar(curr_row.cells[3].innerText, " ", "");       
					           }					
					           dd_html += "'></td></tr><tr><td align='right'>";
					           dd_html += rsTable.rows[0].cells[4].innerText;
							   dd_html += "：<br>(400字以内)</td><td align='left'><textarea name='newCode' style='width:98%;' rows='6'>";							
							   if (tag == "modi") {
								dd_html += replaceChar(curr_row.cells[4].innerText, " ", "");
							   }
							   dd_html += "</textarea></td></tr>";	
						    }else{
						    	var xlname = document.getElementById("tName").value;
						    	if(xlname == "xlcsjgdmb"){
						    		dd_html += "：</td><td align='left'><select id='sel1'";
							   		dd_html += " name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";
						    	}else{
						    		dd_html += "：</td><td align='left'><select id='sel1' onchange=\"AutoFill(this,'";
							   		dd_html += tag;
							  		dd_html += "')\" name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";
						    	}
							   dd_html += rsTable.rows[0].cells[4].innerText;
							   dd_html += "：<br>(400字以内)</td><td align='left'><textarea name='newCode' style='width:98%;' rows='6'>";						
							   if (tag == "modi") {
								dd_html += replaceChar(curr_row.cells[4].innerText, " ", "");
							   }						
							   dd_html += "</textarea></td></tr>";							
							}
							h = 400;
							w = 450;
						}
					}else {
							if(document.forms[0].tName.value=="dm_ydlb" ){
								dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:40%;'></select></td></tr>";
								dd_html += "<tr><td align='right'>";
							}else{
								dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value='";
								if(tag == "modi"){
									dd_html += replaceChar(curr_row.cells[3].innerText, " ", "");
								}
							dd_html += "'></td></tr><tr><td align='right'>";
							}
							dd_html += rsTable.rows[0].cells[4].innerText;
							
							if(document.forms[0].codeType.value=="student" && document.forms[0].tName.value != "dm_ydlb"){
								dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr>";
								h=290;
							}else if(document.forms[0].tName.value=="dm_ydlb"){
								dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:99%;'></select></td></tr>";
							}else if(document.forms[0].tName.value=="cflbdmb"){
								dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr>";
							}else{
							dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value='";
								if (tag == "modi") {
									dd_html += replaceChar(curr_row.cells[4].innerText, " ", "");
								}
								dd_html += "'></td></tr>";
							}
						}
					}
			} else {
				if (num == 6) {//奖学金代码在这里修改
					dd_html += "<tr><td align='right' width='35%'>";
					dd_html += rsTable.rows[0].cells[1].innerText;
					dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%;cursor:hand;color:#999999' name='newCode' value='";
					if (tag == "modi") {
						dd_html += replaceChar(curr_row.cells[1].innerText, " ", "");
						dd_html += "' readonly='true";
					}
					dd_html += "'></td></tr><tr><td align='right'>";
					dd_html += rsTable.rows[0].cells[2].innerText;
					if(document.forms[0].tName.value=="zgkd_xlcdsxb"){
						dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:70%;' onchange='chkm();getSx();'></select></td></tr><tr><td align='right'>";
					}else if(document.forms[0].tName.value=="zjcm_xwjxjrs"){
						dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:70%;'></select></td></tr><tr><td align='right'>";
					}else{
					dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%;' name='newCode' value='";
					if (tag == "modi") {
						dd_html += replaceChar(curr_row.cells[2].innerText, " ", "");
					}
					dd_html += "'></td></tr><tr><td align='right'>";
					}
					
					dd_html += rsTable.rows[0].cells[3].innerText;
					if(document.forms[0].codeType.value == "dorm"&&xxdm!='10491'){//中国地质大学除外
				         dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";
					     dd_html += rsTable.rows[0].cells[4].innerText;
					     dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:30%;'></select></td></tr><tr><td align='right'>";
	                     dd_html += rsTable.rows[0].cells[5].innerText;										     
					     if (tag != "modi"){						
							dd_html += "：</td><td align='left'><input onkeypress='chkonlynum()' onblur='onlyNumInput(this)'  type='text' maxlength='2' style='width:98%' name='newCode' value=''>";						
					     }else if (tag == "modi") {
						 	dd_html +=	"：</td><td align='left'><input onkeypress='chkonlynum()' onblur='onlyNumInput(this)'  type='text' maxlength='2' style='width:98%' name='newCode' value="+curr_row.cells[5].innerText+">"
						 }
							//dd_html += replaceChar(curr_row.cells[5].innerText, " ", "");					
					     dd_html += "</td></tr>";
	                }else if(document.forms[0].tName.value=="zgkd_xlcdsxb"){
	                 dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:99%;' onchange='getSx();'></select></td></tr><tr><td align='right'>";
					     dd_html += rsTable.rows[0].cells[4].innerText;
					     dd_html += "：</td><td align='left'><select id='sel3' name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";
	                     dd_html += rsTable.rows[0].cells[5].innerText;										     
					     if (tag != "modi"){						
							dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%;' name='newCode' value='";						
					     }else if (tag == "modi") {
						 	dd_html +=	"：</td><td align='left'><input type='text' maxlength='30' style='width:98%;' name='newCode' value='"+curr_row.cells[5].innerText;
						 }
							//dd_html += replaceChar(curr_row.cells[5].innerText, " ", "");					
					     dd_html += "'></td></tr>";
	                }else{
	                  if(document.forms[0].tName.value=="xmlg_jxjdmb"){
						dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:70%;'></select></td></tr><tr><td align='right'>";					
					  }else if (document.forms[0].tName.value=='jxjdmb' && xxdm=='11647') {//浙江传媒学院奖学金代码  奖学金类别代码下拉列表设置
					  	dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:70%;'></select></td></tr><tr><td align='right'>";
					  }else if(document.forms[0].tName.value=="zjcm_xwjxjrs"){
						dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:70%;'></select></td></tr><tr><td align='right'>";
					  }else if (document.forms[0].tName.value=='jxjdmb' && xxdm!='11647') {//其它学校奖学代代金
					  	dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:70%;'></select></td></tr><tr><td align='right'>";					
					  }else if(document.forms[0].tName.value=="dwjlxmdmb"){
						dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:70%;'></select></td></tr><tr><td align='right'>";
					  }else{
						dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value='";
						if (tag == "modi") {
							dd_html += replaceChar(curr_row.cells[3].innerText, " ", "");
						}
						dd_html += "'></td></tr><tr><td align='right'>";
					  }
						dd_html += rsTable.rows[0].cells[4].innerText;
				    if(document.forms[0].tName.value=="zjcm_xwjxjrs"){
						dd_html += "：</td><td align='left'><select id='sel3' name='newCode' style='width:70%;'></select></td></tr><tr><td align='right'>";
					}else if(document.forms[0].tName.value=="dwjlxmdmb"){
						   dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:70%;'></select></td></tr><tr><td align='right'>";
					}else{
					    if(document.forms[0].tName.value=="xmlg_jxjdmb"){
						   dd_html += "：</td><td align='left'><input type='text' maxlength='30'  onblur='onlyNumInput(this)' onkeypress='return sztzNumInputValue(this,8,event)' style='width:98%' name='newCode' value='";
					    }else{
						   dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value='";
					    }
						if (tag == "modi") {
							dd_html += replaceChar(curr_row.cells[4].innerText, " ", "");
						}
						dd_html += "'></td></tr><tr><td align='right'>";
					}
					dd_html += rsTable.rows[0].cells[5].innerText;					
					  if(document.forms[0].tName.value=="xmlg_jxjdmb"){
						dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:70%;'></select></td></tr><tr><td align='right'>";					
				      }else if(document.forms[0].tName.value=="sxjy_bjgbzlb"){
						dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:70%;'></select></td></tr><tr><td align='right'>";
					  }else{
					     if (tag != "modi"){
					     	if (document.forms[0].tName.value=="jxjdmb") {
					     	dd_html += "：</td><td align='left'><input type='text' maxlength='6' style='width:98%' onblur='onlyNumInput(this)' name='newCode' value=''>";	
					     	} else {
					     		dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value=''>";	
					     	}	 										
					     }else if (tag == "modi") {
					     	if (document.forms[0].tName.value=="jxjdmb") {
					     		dd_html +=	"：</td><td align='left'><input type='text' maxlength='6' style='width:98%' name='newCode' onblur='onlyNumInput(this)' value="+curr_row.cells[5].innerText+">"
					     	} else {
								 dd_html +=	"：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value="+curr_row.cells[5].innerText+">"
					     	}
						 }
								
					    dd_html += "</td></tr>";
					  }
					}
					h = 370;
					w = 450;
				}
				if (num == 7) {
					dd_html += "<tr><td align='right' width='35%'>";
					dd_html += rsTable.rows[0].cells[1].innerText;
					if(tab_name == "bjdydygxb"){
						dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%;cursor:hand;color:#999999' name='newCode' value='";
						if (tag == "modi") {
							dd_html += replaceChar(curr_row.cells[1].innerText, " ", "");
							dd_html += "' readonly='true";
						}
					} else {
						dd_html += "：</td><td align='left'><input type='text' id='sztzCode' maxlength='30' style='width:98%;cursor:hand;color:#999999' name='newCode' value='";
						if (tag == "modi") {
							dd_html += replaceChar(curr_row.cells[1].innerText, " ", "");
							dd_html += "' readonly='true";
						}
					}
					dd_html += "'></td></tr><tr><td align='right'>";
					dd_html += rsTable.rows[0].cells[2].innerText;
					dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%;' name='newCode' value='";
					if (tag == "modi") {
						dd_html += replaceChar(curr_row.cells[2].innerText, " ", "");
					}
					dd_html += "'></td></tr><tr><td align='right'>";
					if(document.forms[0].codeType.value == "other"){
					     dd_html += rsTable.rows[0].cells[3].innerText;
					     dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:70%;'></select></td></tr><tr><td align='right'>";
					     dd_html += rsTable.rows[0].cells[4].innerText;
					     dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:30%;'></select></td></tr>";
					     dd_html += "<tr><td align='right'>";
					     dd_html += rsTable.rows[0].cells[5].innerText;
						 dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:30%' name='newCode' value='";
						 if (tag == "modi") {
							  dd_html += replaceChar(curr_row.cells[5].innerText, " ", "");
						 }
						 dd_html += "'>(元)</td></tr><tr><td align='right'>";
						 dd_html += rsTable.rows[0].cells[6].innerText;
					     dd_html += "：</td><td align='left'><textarea name='newCode' style='width:98%;' rows='4'>";
						 if (tag == "modi") {
							 dd_html += replaceChar(curr_row.cells[6].innerText, " ", "");
						 }
						 dd_html += "</textarea></td></tr>";
						 h = 430;
						 w = 450;
					}else if(document.forms[0].codeType.value == "dorm"&&xxdm=='10491'){//中国地质大学
					     dd_html += rsTable.rows[0].cells[3].innerText;
					     dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";
					     dd_html += rsTable.rows[0].cells[4].innerText;
					     dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:30%;'></select></td></tr><tr><td align='right'>";
	                     dd_html += rsTable.rows[0].cells[5].innerText;										     
					     if (tag != "modi"){						
							dd_html += "：</td><td align='left'><input onkeypress='chkonlynum()' onblur='onlyNumInput(this)'  type='text' maxlength='2' style='width:98%' name='newCode' value=''>";						
					     }else if (tag == "modi") {
						 	dd_html +=	"：</td><td align='left'><input onkeypress='chkonlynum()' onblur='onlyNumInput(this)'  type='text' maxlength='2' style='width:98%' name='newCode' value="+curr_row.cells[5].innerText+">"
						 }							
					     dd_html += "'</td></tr><tr><td align='right'>";
					     dd_html += rsTable.rows[0].cells[6].innerText;
					     dd_html += "：</td><td align='left'><select id='sel3' name='newCode' style='width:99%;'></select>";					     
					     dd_html += "</td></tr>";
					     h = 370;
					     w = 450;					     					     
					} else if(document.forms[0].codeType.value == "dorm"&&xxdm!='10491'){
						 dd_html += rsTable.rows[0].cells[3].innerText;
						 dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";
					     dd_html += rsTable.rows[0].cells[4].innerText;
					     dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:30%;'></select></td></tr><tr><td align='right'>";
	                     dd_html += rsTable.rows[0].cells[5].innerText;										     
					     if (tag != "modi"){						
							dd_html += "：</td><td align='left'><input onkeypress='chkonlynum()' onblur='onlyNumInput(this)'  type='text' maxlength='2' style='width:98%' name='newCode' value=''>";						
					     }else if (tag == "modi") {
						 	dd_html +=	"：</td><td align='left'><input onkeypress='chkonlynum()' onblur='onlyNumInput(this)'  type='text' maxlength='2' style='width:98%' name='newCode' value="+curr_row.cells[5].innerText+">"
						 }
					     dd_html += "</td></tr><tr><td align='right'>";
					     dd_html += rsTable.rows[0].cells[6].innerText;
						 dd_html += "：</td><td align='left'><select id='sel3' name='newCode' style='width:30%;'></select></td></tr><tr>";					
					     h = 370;
					     w = 450;	
					}else{
						dd_html += rsTable.rows[0].cells[3].innerText;
					    if(document.forms[0].codeType.value == "sztz"){
						dd_html += "：</td><td align='left'><select id='sel1' name='newCode' onchange=\"AutoFill(this,'";
						dd_html += tag;
						dd_html += "')\" style='width:98%;'></select></td></tr><tr><td align='right'>";
						dd_html += rsTable.rows[0].cells[4].innerText;
						dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:98%;'></select></td></tr><tr><td align='right'>";	
						}else if(document.forms[0].tName.value == "jxjdmb"){
							dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:70%;'></select></td></tr><tr><td align='right'>";
							dd_html += rsTable.rows[0].cells[4].innerText;
							dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value='";
							if (tag == "modi") {
								dd_html += replaceChar(curr_row.cells[4].innerText, " ", "");
							}
							dd_html += "'></td></tr><tr><td align='right'>";
						}else {
						dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value='";
						if (tag == "modi") {
							dd_html += replaceChar(curr_row.cells[3].innerText, " ", "");
						}
						dd_html += "'></td></tr><tr><td align='right'>";
						dd_html += rsTable.rows[0].cells[4].innerText;
						dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value='";
						if (tag == "modi") {
							dd_html += replaceChar(curr_row.cells[4].innerText, " ", "");
						}
					dd_html += "'></td></tr><tr><td align='right'>";
					  }
					dd_html += rsTable.rows[0].cells[5].innerText;
					if(tab_name == "jxjdmb" &&xxdm == "10338"){
						dd_html += "：</td><td align='left'><input type='text' name='newCode' style='width:98%' value='";
						if(tag == "modi"){
								dd_html += replaceChar(curr_row.cells[5].innerText, " ", "");
							}
						dd_html += "'></input></td></tr><tr><td align='right'>";		
						dd_html += rsTable.rows[0].cells[6].innerText;
						dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:60%;'></select></td></tr>";
					}else{
						if((tab_name != null && tab_name!="") && (tab_name == "GdNZZY_xszz_jzjdmb" || tab_name == "bjdydygxb" ||document.forms[0].codeType.value == "sztz")){
							dd_html += "：</td><td align='left'><input type='text' name='newCode' style='width:98%' value='";
							if(tag == "modi"){
								dd_html += replaceChar(curr_row.cells[5].innerText, " ", "");
							}		
							dd_html += "'></input></td></tr><tr><td align='right'>";
							dd_html += rsTable.rows[0].cells[6].innerText;
							if(document.forms[0].codeType.value == "sztz"){
							dd_html += "：</td><td align='left'><input onkeypress='return sztzNumInputValue(this,4,event)' type='text' name='newCode' style='width:30%' value='" 	
						   		if(tag == "modi"){
								dd_html += replaceChar(curr_row.cells[6].innerText, " ", "");
						    	}else{
						    	dd_html +="0";	
								}
							}else {
							dd_html += "：</td><td align='left'><input type='text' name='newCode' style='width:98%' value='" 
							if(tag == "modi"){
								dd_html += replaceChar(curr_row.cells[6].innerText, " ", "");
								}		
							}							
							dd_html += "'></input></td></tr><tr><td align='right'>";
							} else if (tab_name=='rgysyzdmb' || tab_name=='zcbzpyb') {
								dd_html += "：</td><td align='left'><input type='text' name='newCode' style='width:98%' maxLength='50' value='";
								if(tag == "modi"){
									dd_html += replaceChar(curr_row.cells[5].innerText, " ", "");
								}
								dd_html += "'></input></td></tr><tr><td align='right'>";
								dd_html += rsTable.rows[0].cells[6].innerText;
								dd_html += "：</td><td align='left'><input type='text' name='newCode' style='width:98%' maxlength='1' onkeypress='chkonlynum()' onblur='onlyNumInput(this)' value='";
								if(tag == "modi"){
									dd_html += replaceChar(curr_row.cells[6].innerText, " ", "");
								}
								dd_html += "'></input></td></tr><tr><td align='right'>";
							} else if (tab_name == "jxjdmb"){
								
								dd_html += "：</td><td><input type='text' name='newCode' style='width:98%' value='";
								if(tag == "modi"){
									dd_html += replaceChar(curr_row.cells[5].innerText, " ", "");
								}		
								dd_html += "'></input></td></tr><tr><td align='right'>";
								dd_html += rsTable.rows[0].cells[6].innerText;
								dd_html += "：</td><td><select id='sel2' name='newCode'></select></td></tr>" 
								
							}else {
								dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:20%;'></select></td></tr><tr><td align='right'>";
								dd_html += rsTable.rows[0].cells[6].innerText;
								dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:60%;'></select></td></tr>";
							}
					}					
					h = 370;
					w = 450;
					}
				}
				if (num == 8) {
					dd_html += "<tr><td align='right' width='35%'>";
					dd_html += rsTable.rows[0].cells[1].innerText;
					if(tab_name == "bjdydygxb"){
						dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%;cursor:hand;color:#999999' name='newCode' value='";
						if (tag == "modi") {
							dd_html += replaceChar(curr_row.cells[1].innerText, " ", "");
							dd_html += "' readonly='true";
						}
					} else {
						dd_html += "：</td><td align='left'><input type='text' id='sztzCode' maxlength='30' style='width:98%;cursor:hand;color:#999999' name='newCode' value='";
						if (tag == "modi") {
							dd_html += replaceChar(curr_row.cells[1].innerText, " ", "");
							dd_html += "' readonly='true";
						}
					}
					dd_html += "'></td></tr><tr><td align='right'>";
					dd_html += rsTable.rows[0].cells[2].innerText;
					dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%;' name='newCode' value='";
					if (tag == "modi") {
						dd_html += replaceChar(curr_row.cells[2].innerText, " ", "");
					}
					dd_html += "'></td></tr><tr><td align='right'>";
					if(document.forms[0].codeType.value == "other"){
					     dd_html += rsTable.rows[0].cells[3].innerText;
					     dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:70%;'></select></td></tr><tr><td align='right'>";
					     dd_html += rsTable.rows[0].cells[4].innerText;
					     dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:30%;'></select></td></tr>";
					     dd_html += "<tr><td align='right'>";
					     dd_html += rsTable.rows[0].cells[5].innerText;
						 dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:30%' name='newCode' value='";
						 if (tag == "modi") {
							  dd_html += replaceChar(curr_row.cells[5].innerText, " ", "");
						 }
						 dd_html += "'>(元)</td></tr><tr><td align='right'>";
						 dd_html += rsTable.rows[0].cells[6].innerText;
					     dd_html += "：</td><td align='left'><textarea name='newCode' style='width:98%;' rows='4'>";
						 if (tag == "modi") {
							 dd_html += replaceChar(curr_row.cells[6].innerText, " ", "");
						 }
						 dd_html += "</textarea></td></tr>";
						 h = 430;
						 w = 450;
					}else if(document.forms[0].codeType.value == "dorm"&&xxdm=='10491'){//中国地质大学
					     dd_html += rsTable.rows[0].cells[3].innerText;
					     dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:99%;'></select></td></tr><tr><td align='right'>";
					     dd_html += rsTable.rows[0].cells[4].innerText;
					     dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:30%;'></select></td></tr><tr><td align='right'>";
	                     dd_html += rsTable.rows[0].cells[5].innerText;										     
					     if (tag != "modi"){						
							dd_html += "：</td><td align='left'><input onkeypress='chkonlynum()' onblur='onlyNumInput(this)'  type='text' maxlength='2' style='width:98%' name='newCode' value=''>";						
					     }else if (tag == "modi") {
						 	dd_html +=	"：</td><td align='left'><input onkeypress='chkonlynum()' onblur='onlyNumInput(this)'  type='text' maxlength='2' style='width:98%' name='newCode' value="+curr_row.cells[5].innerText+">"
						 }							
					     dd_html += "'</td></tr><tr><td align='right'>";
					     dd_html += rsTable.rows[0].cells[6].innerText;
					     dd_html += "：</td><td align='left'><select id='sel3' name='newCode' style='width:99%;'></select>";					     
					     dd_html += "</td></tr>";
					     h = 370;
					     w = 450;					     					     
					}else{
						dd_html += rsTable.rows[0].cells[3].innerText;
					    if(document.forms[0].codeType.value == "sztz"){
						dd_html += "：</td><td align='left'><select id='sel1' name='newCode' onchange=\"AutoFill(this,'";
						dd_html += tag;
						dd_html += "')\" style='width:98%;'></select></td></tr><tr><td align='right'>";
						dd_html += rsTable.rows[0].cells[4].innerText;
						dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:98%;'></select></td></tr><tr><td align='right'>";	
						}else if(document.forms[0].tName.value == "jxjdmb"){
							dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:70%;'></select></td></tr><tr><td align='right'>";
							dd_html += rsTable.rows[0].cells[4].innerText;
							dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value='";
							if (tag == "modi") {
								dd_html += replaceChar(curr_row.cells[4].innerText, " ", "");
							}
							dd_html += "'></td></tr><tr><td align='right'>";
						}else {
						dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value='";
						if (tag == "modi") {
							dd_html += replaceChar(curr_row.cells[3].innerText, " ", "");
						}
						dd_html += "'></td></tr><tr><td align='right'>";
						dd_html += rsTable.rows[0].cells[4].innerText;
						dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value='";
						if (tag == "modi") {
							dd_html += replaceChar(curr_row.cells[4].innerText, " ", "");
						}
					dd_html += "'></td></tr><tr><td align='right'>";
					  }
					dd_html += rsTable.rows[0].cells[5].innerText;
					if((tab_name != null && tab_name!="") && (tab_name == "GdNZZY_xszz_jzjdmb" || tab_name == "bjdydygxb" || tab_name == "jxjdmb" ||document.forms[0].codeType.value == "sztz")){
						dd_html += "：</td><td align='left'><input type='text' name='newCode' style='width:98%' value='";
						if(tag == "modi"){
							dd_html += replaceChar(curr_row.cells[5].innerText, " ", "");
						}		
						dd_html += "'></input></td></tr><tr><td align='right'>";
						dd_html += rsTable.rows[0].cells[6].innerText;
						if(document.forms[0].codeType.value == "sztz"){
						dd_html += "：</td><td align='left'><input onkeypress='return sztzNumInputValue(this,4,event)' type='text' name='newCode' style='width:30%' value='" 	
						   if(tag == "modi"){
							dd_html += replaceChar(curr_row.cells[6].innerText, " ", "");
						    }else{
						    dd_html +="0";	
						}
						}else {
						dd_html += "：</td><td align='left'><input type='text' name='newCode' style='width:98%' value='" 
						if(tag == "modi"){
							dd_html += replaceChar(curr_row.cells[6].innerText, " ", "");
						}		
						}							
						dd_html += "'></input></td></tr><tr><td align='right'>";
					} else {
						dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:20%;'></select></td></tr><tr><td align='right'>";
						dd_html += rsTable.rows[0].cells[6].innerText;
						dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:60%;'></select></td></tr>";
					}
					//if(xxdm=="10338"&&document.forms[0].tName.value == "jxjdmb")	{
						//dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:20%;'></select></td></tr><tr><td align='right'>";
						//dd_html += rsTable.rows[0].cells[6].innerText;
						//dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:60%;'></select></td></tr>";
					//}
//					dd_html += "<tr><td align='right' width='35%'>";
					dd_html += rsTable.rows[0].cells[7].innerText;
					
					dd_html += "：</td><td align='left'><input type='text'  name='newCode' maxlength='30' style='width:98%'; value='";
					if (tag == "modi") {
						dd_html += replaceChar(curr_row.cells[7].innerText, " ", "");
						
					}			
					dd_html += "'></td></tr>";
					h = 370;
					w = 450;
					}
				}
				if(num == 9){
					dd_html += "<tr><td align='right' width='35%'>";
					dd_html += rsTable.rows[0].cells[1].innerText;
					dd_html += "：</td><td align='left'><input type='text' id='sztzCode' maxlength='30' style='width:98%;cursor:hand;color:#999999' name='newCode' value='";
					if (tag == "modi") {
						dd_html += replaceChar(curr_row.cells[1].innerText, " ", "");
						dd_html += "' readonly='true";
					}
					dd_html += "'></td></tr><tr><td align='right'>";					
					dd_html += rsTable.rows[0].cells[2].innerText;
					if(xxdm!="" && xxdm=="11417"){
						dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:60%;'  onchange='changeCode()'></select></td></tr><tr><td align='right'>";
					}else{
					dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%;' name='newCode' value='";
					if (tag == "modi") {
						dd_html += replaceChar(curr_row.cells[2].innerText, " ", "");
					}
					dd_html += "'></td></tr><tr><td align='right'>";
					}
//					 if(document.forms[0].codeType.value == "sztz"){
//					     dd_html += rsTable.rows[0].cells[3].innerText;
//					     dd_html += "：</td><td align='left'><select id='sel1' name='newCode' onchange=\"AutoFill(this,'"+tag+"')\" style='width:60%;'></select></td></tr><tr><td align='right'>";					     
//					     dd_html += rsTable.rows[0].cells[4].innerText;
//					     dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:60%;'></select></td></tr><tr><td align='right'>";					     					      
//					     dd_html += rsTable.rows[0].cells[5].innerText;
//					     dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value='";
//						 if (tag == "modi") {
//							dd_html += replaceChar(curr_row.cells[5].innerText, " ", "");
//						 }
//						 dd_html += "'></td></tr><tr><td align='right'>";
//					     dd_html += rsTable.rows[0].cells[6].innerText;
//					     dd_html += "：</td><td align='left'><input onkeypress='return sztzNumInputValue(this,4,event)' type='text' name='newCode' style='width:30%' value='" 	
//						 if(tag == "modi"){
//							dd_html += replaceChar(curr_row.cells[6].innerText, " ", "");
//						 }else{
//						    dd_html +="0";	
//						 }
//						 dd_html += "'></td></tr><tr><td align='right'>";
//						 dd_html += rsTable.rows[0].cells[7].innerText;
//					     dd_html += "：</td><td align='left'><select id='sel3' name='newCode' style='width:60%;' ></select></td></tr><tr><td align='right'>";					     
//                         dd_html += rsTable.rows[0].cells[8].innerText;
//					     dd_html += "：</td><td align='left'><select id='sel4' name='newCode' style='width:98%;'></select></td></tr><tr><td align='right'>";					     					   					
//					 }else{					
					     //所属部门
					     if(xxdm!="" && xxdm=="11417"){	
					     dd_html += rsTable.rows[0].cells[7].innerText;
					     dd_html += "：</td><td align='left'><select id='sel5' name='newCode' style='width:70%;' onchange='changeCode()'></select></td></tr><tr><td align='right'>";	
					     }	
					     //联系人			
					     dd_html += rsTable.rows[0].cells[3].innerText;
					     if(xxdm!="" && xxdm=="11417"){
						    dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:60%;')' onchange='changeInfo()'></select></td></tr><tr><td align='right'>";
					     }else{
					        dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value='";
					        if (tag == "modi") {
						       dd_html += replaceChar(curr_row.cells[3].innerText, " ", "");
					        }
					      dd_html += "'></td></tr><tr><td align='right'>";
					     }
					     dd_html += rsTable.rows[0].cells[4].innerText;
					     dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value='";
					     if (tag == "modi") {
						     dd_html += replaceChar(curr_row.cells[4].innerText, " ", "");
					     }
					     dd_html += "'></td></tr><tr><td align='right'>";
					     dd_html += rsTable.rows[0].cells[5].innerText;
					     if(xxdm!="" && xxdm=="11417"){
						     dd_html += "：</td><td align='left'><select id='sel3' name='newCode' style='width:20%;'></select></td></tr><tr><td align='right'>";
						     dd_html += rsTable.rows[0].cells[6].innerText;					
						     dd_html += "：</td><td align='left'><select id='sel4' name='newCode' style='width:60%;'></select></td></tr>";				
						
					     }else{
					         dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:20%;'></select></td></tr><tr><td align='right'>";
					         dd_html += rsTable.rows[0].cells[6].innerText;					
					         dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:60%;'></select></td></tr><tr><td align='right'>";				
					         dd_html += rsTable.rows[0].cells[7].innerText;
					         dd_html += "：</td><td align='left'><select id='sel3' name='newCode' style='width:70%;'></select></td></tr>";	
						}
					    dd_html += "<tr><td align='right' width='35%'>";					
					    dd_html += rsTable.rows[0].cells[8].innerText;
					    dd_html += "：</td><td align='left'><input type='text' id='dlmCode' maxlength='30' style='width:98%;cursor:hand;color:' name='newCode' value='";	
					    if (tag == "modi") {
						   dd_html += replaceChar(curr_row.cells[8].innerText, " ", "");
						   dd_html += "' ";
					    }
					    dd_html += "'></td></tr><tr><td align='right'>";
//					}
					h = 400;
					w = 450;
				}
				if(num == 10){
					dd_html += "<tr><td align='right' width='35%'>";
					dd_html += rsTable.rows[0].cells[1].innerText;
					dd_html += "：</td><td align='left'><input type='text' id='sztzCode' maxlength='30' style='width:250px;cursor:hand;color:#999999' name='newCode' value='";
					if (tag == "modi") {
						dd_html += replaceChar(curr_row.cells[1].innerText, " ", "");
						dd_html += "' readonly='true";
					}
					dd_html += "'></td></tr><tr><td align='right'>";					
					dd_html += rsTable.rows[0].cells[2].innerText;
					dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:250px' name='newCode' value='";
					if (tag == "modi") {
						dd_html += replaceChar(curr_row.cells[2].innerText, " ", "");
					}
					dd_html += "'></td></tr><tr><td align='right'>";
					//联系人			
					dd_html += rsTable.rows[0].cells[3].innerText;
				    dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:250px' name='newCode' value='";
				    if (tag == "modi") {
					    dd_html += replaceChar(curr_row.cells[3].innerText, " ", "");
				    }
					dd_html += "'></td></tr><tr><td align='right'>";
					dd_html += rsTable.rows[0].cells[4].innerText;
					if(tab_name == 'yrdwdmb'){//用人单位联系电话
						dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:250px' name='newCode' onkeyup=\"value=value.replace(/[^\\d|-]/g,'')\" value='";
					}else{
						dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:250px' name='newCode' value='";
					}
					
					if (tag == "modi") {
						dd_html += replaceChar(curr_row.cells[4].innerText, " ", "");
					}
					dd_html += "'></td></tr><tr><td align='right'>";
					dd_html += rsTable.rows[0].cells[5].innerText;
					dd_html += "：</td><td align='left'><select id='sel1' name='newCode' style='width:20%;'></select></td></tr><tr><td align='right'>";
					dd_html += rsTable.rows[0].cells[6].innerText;					
					dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:60%;'></select></td></tr><tr><td align='right'>";				
					dd_html += rsTable.rows[0].cells[7].innerText;
					dd_html += "：</td><td align='left'><select id='sel3' name='newCode' style='width:70%;'></select></td></tr>";	
					dd_html += "<tr><td align='right' width='35%'>";					
				    dd_html += rsTable.rows[0].cells[8].innerText;
				    dd_html += "：</td><td align='left'><input type='text' id='dlmCode' maxlength='30' style='width:250px;cursor:hand;color:' name='newCode' value='";	
				    if (tag == "modi") {
					   dd_html += replaceChar(curr_row.cells[8].innerText, " ", "");
				    }
				    dd_html += "'></td></tr><tr><td align='right'>";
				    dd_html += rsTable.rows[0].cells[9].innerText;
				    dd_html += "：</td><td align='left'><input type='text' id='dlmCode' maxlength='50' style='width:250px;cursor:hand;color:' name='newCode' value='";	
				    if (tag == "modi") {
					   dd_html += replaceChar(curr_row.cells[9].innerText, " ", "");
				    }
				    dd_html += "'></td></tr>"
//					}
					h = 450;
					w = 450;
				}
				if(num==11){				    
					dd_html += "<tr><td align='right' width='35%'>";
					dd_html += rsTable.rows[0].cells[1].innerText;
					dd_html += "：</td><td align='left'><input type='text' id='sztzCode' maxlength='30' style='width:98%;cursor:hand;color:#999999' name='newCode' value='";
					if (tag == "modi") {
						dd_html += replaceChar(curr_row.cells[1].innerText, " ", "");
						dd_html += "' readonly='true";
					}
					dd_html += "'></td></tr><tr><td align='right'>";					
					dd_html += rsTable.rows[0].cells[2].innerText;				
					dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%;' name='newCode' value='";
					if (tag == "modi") {
						dd_html += replaceChar(curr_row.cells[2].innerText, " ", "");
					}
					dd_html += "'></td></tr><tr><td align='right'>";					
					dd_html += rsTable.rows[0].cells[3].innerText;
					dd_html += "：</td><td align='left'><select id='sel1' name='newCode' onchange=\"AutoFill(this,'"+tag+"')\" style='width:60%;'></select></td></tr><tr><td align='right'>";					     
					dd_html += rsTable.rows[0].cells[4].innerText;
					dd_html += "：</td><td align='left'><select id='sel2' name='newCode' style='width:60%;'></select></td></tr><tr><td align='right'>";					     					      
					dd_html += rsTable.rows[0].cells[5].innerText;
					dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value='";
					if (tag == "modi") {
						dd_html += replaceChar(curr_row.cells[5].innerText, " ", "");
					}
					dd_html += "'></td></tr><tr><td align='right'>";
					dd_html += rsTable.rows[0].cells[6].innerText;
					dd_html += "：</td><td align='left'><input onkeypress='return sztzNumInputValue(this,4,event)' type='text' name='newCode' style='width:30%' value='" 	
					if(tag == "modi"){
						dd_html += replaceChar(curr_row.cells[6].innerText, " ", "");
					}else{
						dd_html +="0";	
					}
					dd_html += "'></td></tr><tr><td align='right'>";
					dd_html += rsTable.rows[0].cells[7].innerText;
					dd_html += "：</td><td align='left'><select id='sel3' name='newCode' style='width:60%;' ></select></td></tr><tr><td align='right'>";					     
                    dd_html += rsTable.rows[0].cells[8].innerText;
					dd_html += "：</td><td align='left'><select id='sel4' name='newCode' style='width:98%;'></select></td></tr><tr><td align='right'>";					     					   					
					dd_html += rsTable.rows[0].cells[9].innerText;
					dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' id='sqkssj'" 
					dd_html +="onblur='dateFormatChg(this)'  style='cursor:hand;' readonly='true' onclick='return showCalendar(this.id,\"y-mm-dd\");' value='";
					if (tag == "modi") {
						dd_html += replaceChar(curr_row.cells[9].innerText, " ", "");
					}
					dd_html += "'></td></tr><tr><td align='right'>";
					dd_html += rsTable.rows[0].cells[10].innerText;
					dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' id='sqjssj'" 
					dd_html +="onblur='dateFormatChg(this)'  style='cursor:hand;' readonly='true' onclick='return showCalendar(this.id,\"y-mm-dd\");' name='newCode' value='";
					if (tag == "modi") {
						dd_html += replaceChar(curr_row.cells[10].innerText, " ", "");
					}
					dd_html += "'></td></tr>";				
					h = 500;
					w = 450;
				}
			}
		}
	}
	dd_html += "</table><br>";
	document.forms[0].htmlContent.value = dd_html;
	if((tab_name != null && tab_name!="") && tab_name == "bjdydygxb"){//班级党员对应关系表
		url = "/xgxt/bjdydygxb.do";
		if(tag =="add"){
			url += "?act=add";//+"&codeType="+window.parent.document.getElementById("codeType").value;
			url += "&codeType="+document.getElementById("codeType").value;
		} else if(tag == "modi"){
			url += "?act=modi&values=";
			url += curr_row.cells[1].innerText.replace(" ","");
			for (var i=2; i < curr_row.cells.length; i++){
				url += "!!"+curr_row.cells[i].innerText.replace(" ","");
			}
			url += "&codeType="+document.getElementById("codeType").value;
		}
	}
	if(tag == "modi"){
		if(codeByDept != null && codeByDept == "true"){
				//判断是否可以删除
				var pkValue = dm+tab_name;
				systemFunction.userIsOperator(userName,pkValue,function(data){
					if(data != null && data == true){
						showTopWin(url, w, h);
					}else{
						alert("该条记录不是您添加的，您不能修改！");
						return false;
					}
				});
			}else{
				showTopWin(url, w, h);
			}
	}else{
		showTopWin(url, w, h);
	}
}

function changeCode(){
	var dwCode=document.getElementById("sel1").value;
	var bmCode=document.getElementById("sel5").value;
	document.getElementById("sztzCode").value=bmCode;
	var str="";
	getXjydInfo.getLxr(dwCode,bmCode ,function(data){
		str=data;	
		document.getElementById("tmpsel_2").value=str;
		initList("tmpsel_2", "sel2", "");
	});
}

function changeInfo(){
	var lxr=document.getElementById("sel2").value;
	var dwdm="";
	var bmdm="";
	getXjydInfo.getLxrInfo(lxr,function(data){
	dwdm=data.split('!!#!!')[0];
	bmdm=data.split("!!#!!")[1];
	document.getElementById("sel1").value=dwdm;
	document.getElementById("sel5").value=bmdm;	
	});
}

function refreshForm(url) {
	url = url.replace("/xgxt/","");
	document.forms[0].action = url;
	document.forms[0].submit();
	//dataLoad(true);
	var tmpBut = document.getElementsByTagName("button");
	for (i = 0; i < tmpBut.length; i++) {
		tmpBut[i].disabled = true;
	}
}

function refresh(url) {
	var type=document.getElementsByTagName("input")[0].value;	
	document.forms[0].action = url+type;
	document.forms[0].submit();
}
function checkWinType() {
	if (window.dialogArguments == null || window.parent == null) {
		alert("非法访问，窗口即将关闭！");
		Close();
		return false;
	}
}
function dataManLoad() {
	var tmpSel = document.getElementsByTagName("select");
	var tmpHid = document.getElementsByTagName("input");
	var tmpOth = document.getElementsByTagName("textarea");
	var tmpBut = document.getElementsByTagName("button");
	/*
	 for(i = 0;i<tmpSel.length;i++){
	 	for(j = 0;j<tmpHid.length;j++){
	 		if(tmpSel[i].id == tmpHid[j].id){
	 			for(k = 0;k<tmpSel[i].options.length;k++){
	 				if(tmpSel[i].options[k].value == tmpHid[j].value || tmpSel[i].options[k].text == tmpHid[j].value){
	 					tmpSel[i].selectedIndex = k;
	 				}
	 			}
	 		}
	 	}
	 }
	 */
	if($("doType")){
		if (document.forms[0].doType.value == "view") {
			if($("xh")){dataCanModi(true)};
			document.forms[0].buttonModi.disabled = false;
			document.forms[0].buttonSave.disabled = false;
			document.forms[0].buttonClose.disabled = false;
			if($("xh")){document.forms[0].xh.readOnly = true};
			if($("buttonModi")){document.forms[0].buttonModi.style.display = "none"};
			if($("buttonSave")){document.forms[0].buttonSave.style.display = "none"};		
		}
		if (document.forms[0].doType.value == "modi") {
			if(jQuery("#xh")){dataCanModi(true)};
			if(jQuery("#xh")){document.forms[0].xh.readOnly = true};
			if(jQuery("#xh")){document.forms[0].xh.blur()};
		}
		if (document.forms[0].doType.value == "add") {
			if(jQuery("#xh")){dataCanModi(true)};
			if(jQuery("#xh") && jQuery("#userOnLine").val()!="student"){
				jQuery("#xh").attr('readonly',false);
			}else{
				jQuery("#xh").attr('readonly',false);
			}
			if(jQuery("#buttonFindStu")){jQuery("#buttonFindStu").css('display','')};
			if(jQuery("#buttonModi")){jQuery("#buttonModi").css('display','none')};
			if(jQuery("#xh")){jQuery("#xh").focus()};
		}
	}
}
function dataCanModi(flag) {
	var tmpSel = document.getElementsByTagName("select");
	var tmpHid = document.getElementsByTagName("input");
	var tmpOth = document.getElementsByTagName("textarea");
	var tmpBut = document.getElementsByTagName("button");
	
	var disableElem = document.getElementById("disableEle").value.split("-");
	var readonlyElem = document.getElementById("readonlyEle").value.split("-");
	for (i = 0; i < tmpSel.length; i++) {
		tmpSel[i].disabled = !flag;
	}
	for (i = 0; i < tmpHid.length; i++) {
		tmpHid[i].disabled = !flag;
	}
	for (i = 0; i < tmpOth.length; i++) {
		tmpOth[i].disabled = !flag;
	}
	for (i = 0; i < tmpBut.length; i++) {
		tmpBut[i].disabled = !flag;
	}
	
	if (flag) {
		jQuery('#buttonModi').css('display','none');
		jQuery('#buttonSave').css('display','');
	} else {
		jQuery('#buttonModi').css('display','');
		jQuery('#buttonSave').css('display','none');
	}
	for (i = 0; i < disableElem.length; i++) {
		if (disableElem[i] != null && disableElem[i] != "") {
			document.getElementById(disableElem[i]).disabled = true;
		}
	}
	for (i = 0; i < readonlyElem.length; i++) {
		if (readonlyElem[i] != null && readonlyElem[i] != "") {
			document.getElementById(readonlyElem[i]).readOnly = true;
		}
	}
}
function dateFormatChg(obj) {
	if (obj.tagName.toLowerCase() == "input") {
		obj.value = replaceChar(obj.value, "-", "");
	}
	s = obj.value; 
	var p = /^\d{8}$/; 
	if(!p.test(s)&&s!=""){
		alert("不符合要求的日期格式!");
		obj.focus();
		return false;
	}
}

function dateSplitFormatChg(obj) {	
	s = obj.value; 
	var p = /^\d{4}|-|\d{2}|-|\d{2}$/; 
	if(!p.test(s)&&s!=""){
		alert("不符合要求的日期格式!");
		obj.focus();
		return false;
	}
}

function checkXnNd(xn, nd) {
	if (document.getElementById(xn).tagName.toLowerCase() == "select" && document.getElementById(nd).tagName.toLowerCase() == "select") {
		if (document.getElementById(xn).value == "") {
			alertInfo("请选择学年、年度!",function(){});
			return false;
		}
		if (!inArray(document.getElementById(nd).value, document.getElementById(xn).value.split("-"))) {
			alertInfo("学年、年度不一致,请检查您要保存的数据！",function(){});
			return false;
		}
	}
	return true;
}
function sztzCheckXnNdXq(xn, nd, xq) {
	var elements = new Array();
	elements = document.getElementById(xn).value.split("-");	
	var xq_val = document.getElementById(xq).value;
	var nd_val = document.getElementById(nd).value;
	var xn_val = document.getElementById(xn).value;
	if (document.getElementById(xn).tagName.toLowerCase() == "select" && document.getElementById(nd).tagName.toLowerCase() == "select") {
		if (xn_val == "") {
			alert("请选择学年、年度!");
			return false;
		}
		if (!inArray(nd_val, elements)) {
			alert("学年、年度不一致,请检查您要保存的数据！");
			return false;
		}
		/*
        if ((xq_val == "2" || xq_val == "02") && (nd_val != elements[0])){
       	    alert("学期、学年、年度不一致,请检查您要保存的数据！");
       	    return false;
        }
        if ((xq_val == "1" || xq_val == "01" ) && (nd_val != elements[1])){
        	alert("学期、学年、年度不一致,请检查您要保存的数据！");
       	    return false;
        } 
        */    
	}
	return true;
}
function sztzCheckTj(xn,nd,xq){
	 var elements = new Array();
	 elements = document.getElementById(xn).value.split("-");
	 var njnum	= document.forms[0].xsnj.value;
	 var xq_val = document.getElementById(xq).value;
	 var nd_val = document.getElementById(nd).value;
	 var xn_val = document.getElementById(xn).value;
	 if (document.getElementById(xn).tagName.toLowerCase() == "select" && document.getElementById(nd).tagName.toLowerCase() == "select") {
		if (xn_val == "") {
			alert("请选择学年、年度!");
			return false;
		}
		if (!inArray(nd_val, elements)) {
			alert("学年、年度不一致,请检查您要保存的数据！");
			return false;
		}
		/*
        if (( xq_val == "2"|| xq_val == "02") && (nd_val != elements[0])){
       	    alert("学期、学年、年度不一致,请检查您要保存的数据！");
       	    return false;
        }
        if ((xq_val == "1" || xq_val == "01") && (nd_val != elements[1])){
        	alert("学期、学年、年度不一致,请检查您要保存的数据！");
       	    return false;
        } 
        */
        if(njnum!=""){
     	if(nd_val < njnum){
			alert("年度小于入学年级,请检查您要保存的数据！");
			return false;
		}
//		else if(nd_val >= (njnum - 0 + 5) ){					
//			alert("只可添加年度大于入学年级四年内的项目!");
//			return false;
//		}
      }	          
	}
	return true;	
}
function autoFillStuInfo(cod, obj,redirect,variable) {
	if (cod == 13) {
		if (obj.value == "" || obj.value.length < 6||document.forms[0].url==undefined) {
			//alert("学号位数不正确!");
			return false;
		} else if(document.forms[0].url.value == "/xszxjxjhzb.do"){
			document.forms[0].action = "stu_info_xx.do?from=" + document.forms[0].url.value + "&getStuInfo=" + document.forms[0].getStuInfo.value;
			document.forms[0].submit();
		} else if(document.forms[0].url.value=="/shgc/stu_info/stu_info_modify.jsp"){
			document.forms[0].action = "stu_info_xx.do?from=" + document.forms[0].url.value +"&xh="+obj.value+"&oper="+document.forms[0].oper.value;
			document.forms[0].submit();
		}else if(document.forms[0].url.value=="/sjcz/stu_info_modify.jsp"){
			document.forms[0].action = "stu_info_xx.do?from=" + document.forms[0].url.value +"&xh="+obj.value+"&oper="+document.forms[0].oper.value+"&page=stuinfo";
			document.forms[0].submit();
		}else if(document.forms[0].url.value=="/hzjy/hzjy_xssq.jsp"){
		    document.forms[0].action = "stu_info_xx.do?from=" + document.forms[0].url.value +"&xh="+obj.value;
		    document.forms[0].submit();
		}else if (document.forms[0].url.value=="/cqkj_xszbb_input.do"){
		    document.forms[0].action = "stu_info_xx.do?from=" + document.forms[0].url.value +"&xh="+obj.value;
		    document.forms[0].submit();
		}else if(document.forms[0].url.value=="/gygl/gygl_lczxxb.jsp"){
			if(document.forms[0].ghhxh){
				var ghhxh = document.forms[0].ghhxh.value;
			}
			if(ghhxh != null && ghhxh != ""){
				getStuLczInfo();
			}else{
				var str = "stu_info_xx.do?from=" + document.forms[0].url.value +"&xh="+obj.value;
				if(redirect){
					str += "&redirect=true";	
				}
				if(variable){
					str += "&variable="+variable.value;
				}
				document.forms[0].action = str;
				document.forms[0].submit();
			}
		}else{		    
			var str = "stu_info_xx.do?from=" + document.forms[0].url.value +"&xh="+obj.value;
			if(redirect){
				str += "&redirect=true";	
			}
			if(variable){
				str += "&variable="+variable.value;
			}          
			document.forms[0].action = str;
			document.forms[0].submit();
		}
	}
}
function autoFillStuInfo2(obj) {
		if (obj.value == "" || obj.value.length < 6) {
			alert("学号位数不正确!");
		    return false;
		} else {
			document.forms[0].action = "/xgxt/stu_info_xx.do?from=" + document.forms[0].url.value;
			document.forms[0].submit();
		}
		return false;
}
function sendStuInfo() {	
	var modiTag="1";
	if($("modiTag")){
		modiTag=document.getElementById("modiTag").value;
	}
	var xxdm = '';
	var pkval = '';
	var tab = '';
	if ($("xxdm")) {
		xxdm = document.getElementById('xxdm').value;
	}
	if ($("xxname")) {
		tab = document.getElementById('xxname').value;
	}
	
	var vel = window.dialogArguments.document.forms[0].xh;
	
	//vel.focus();
	
	if(modiTag=="1"){
		vel.value = replaceChar(curr_row.cells[0].innerText," ","");
	}else if(modiTag=="0"){
		vel.value = replaceChar(curr_row.cells[1].innerText," ","");
	}
	var redirect = window.dialogArguments.document.getElementById("redirect");
	var variable = window.dialogArguments.document.getElementById("variable");
	
	if($("zdmc") && $("zdmc").value=="stu"){
		vel.value=vel.value+"&zdmc="+$("zdmc").value;
	}
	if (xxdm=='11641' && $('jq') && document.getElementById('jq').value=='jq') {
		if (curr_row != null && curr_row!='') {
			var xh = curr_row.cells[0].innerText;
			var xn = curr_row.cells[6].innerText;
			var xq = curr_row.cells[7].innerText;
			var pkval = xn+xq+xh;
			var fm = '/gygl/gygl_jqlxxxb.jsp';
			window.dialogArguments.document.forms[0].action = "/xgxt/stu_info_xx.do?from="+fm+"&pk="+vel.value+"&pkval="+pkval;
		    window.dialogArguments.document.forms[0].submit();
		    window.close();
		    return true;
		}
	}
	
	if (xxdm=='11049' && tab=='hygxy') {
		if (curr_row != null && curr_row!='') {
			pkval = curr_row.getElementsByTagName("input")[0].value;
			var fm = '/wjcf/hygxy/gzjygl/add.jsp';
			window.dialogArguments.document.forms[0].action = "/xgxt/stu_info_xx.do?from="+fm+"&xh="+vel.value+"&pkval="+pkval;
		    window.dialogArguments.document.forms[0].submit();
		    window.close();
		    return true;
		}
	}
	if(xxdm=='10628#' && window.dialogArguments.document.forms[0].action=='/xgxt/xcxyXszz.do?method=getXmsq'){
		window.dialogArguments.document.forms[0].submit();
		window.close();
		return false;
	}
	if(xxdm=='11122' && (window.dialogArguments.document.forms[0].action=='/xgxt/xshkgl.do?method=addhkxx' ||
		window.dialogArguments.document.forms[0].action=='/xgxt/xssfzbb.do?method=xssfzbbsqb')){
		window.dialogArguments.document.forms[0].submit();
		window.close();
		return false;
	}
	if(xxdm=='11122' && window.dialogArguments.document.forms[0].action=='/xgxt/stu_archives_apply.do'){
		window.dialogArguments.document.forms[0].submit();
		window.close();
		return false;
	}
	if(xxdm=='11078' && (window.dialogArguments.document.forms[0].action=='/xgxt/zbrygl.do')){
		window.dialogArguments.document.forms[0].action='/xgxt/zbrygl.do?method=operateZbryxx&act=add&pkVStr='+vel.value;
		window.dialogArguments.document.forms[0].submit();
		window.close();
		return false;
	}
	if(xxdm=='11078' && (window.dialogArguments.document.forms[0].action=='/xgxt/xsgly.do')){
		window.dialogArguments.document.forms[0].action='/xgxt/xsgly.do?method=operateXsglyxx&act=add&pkVStr='+vel.value;
		window.dialogArguments.document.forms[0].submit();
		window.close();
		return false;
	}
	if(xxdm=='10344' && (window.dialogArguments.document.forms[0].action=='/xgxt/xljk_tsxswh.do')){
		window.dialogArguments.document.forms[0].action='/xgxt/xljk_tsxswh.do?method=tsxswhEdit&doType=add&act=hx';
		window.dialogArguments.document.forms[0].submit();
		window.close();
		return false;
	}
	
	if(redirect && variable){
		//alert("variable");
		window.dialogArguments.autoFillStuInfo(13, vel,redirect,variable);
	} else if(redirect){
		//alert("redirect");
		window.dialogArguments.autoFillStuInfo(13, vel,redirect);
	} else {
		//alert("else");
		window.dialogArguments.autoFillStuInfo(13, vel);
	}
	window.close();
}
function dataDoSave(mustFill) {
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
	    if($(eles[i])!=null||$(eles[i])){
		    if ($(eles[i]).value == "") {
			  alertInfo("请将带\"*\"号的项目输入完整！",function(){});
			  return false;
		   }
		}
	}
	var url = "/xgxt/modiData.do?realTable=";
	url += window.dialogArguments.document.forms[0].realTable.value;
	var tmpTable = window.dialogArguments.document.forms[0].realTable.value;
	url += "&doType=save";
	url += "&tableName=";
	url += window.dialogArguments.document.forms[0].tableName.value;
	url += "&pk=";
	url += window.dialogArguments.document.forms[0].pk.value;
	url += "&pkValue=";
	url += document.forms[0].pkValue.value;
	url += "&from=";
	url += window.dialogArguments.document.forms[0].act.value;
	document.forms[0].action = url;
	document.forms[0].submit();
	
	var url = "";
	if($("url")){
		showTips('处理数据中，请等待......');
		url = $("url").value;
	}
	
	if(url == ""){
		alertInfo("保存成功！");
		Close();
		window.dialogArguments.document.getElementById("search_go").click();
	}
}

function waitTime() {
	var_w++;
	if (var_w>=2) {
		alert("保存成功！");
		Close();
		window.dialogArguments.document.getElementById("search_go").click();
	}
}

function sztzDataSave(mustFill){
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		}
	}
   var act=document.getElementById("doType").value;
    if(act=="viewOne"){
    	act="modi";
    }
    if (act=="add"){
    	act="save";
    }
    var url = "/xgxt/sztz_modi_data.do?realTable=";
	url += window.dialogArguments.document.forms[0].realTable.value;
	url += "&doType="+act;
	url += "&tableName=";
	url += window.dialogArguments.document.forms[0].tableName.value;
	url += "&pk=";
	url += window.dialogArguments.document.forms[0].pk.value;
	url += "&pkValue=";
	url += document.forms[0].pkValue.value;
	url += "&from=";
	url += window.dialogArguments.document.forms[0].act.value;
	document.forms[0].action = url;
	document.forms[0].submit();
	//alert("保存成功！");
	Close();
	window.dialogArguments.document.getElementById("search_go").click();
	
}
function initXnNdSel() {
	var xnHtml = window.dialogArguments.document.getElementById("xn").outerHTML;
	var ndHtml = window.dialogArguments.document.getElementById("nd").value;
	var xqHtml = window.dialogArguments.document.getElementById("xq").value;
	xnArea.innerHTML = xnHtml;
	//ndArea.innerHTML = ndHtml;
	document.getElementById("xn").options[0] = null;
	//document.getElementById("nd").options[0] = null;
	document.getElementById("xn").disabled = false;
	//document.getElementById("nd").disabled = false;
	document.getElementById('nd').value=ndHtml;
	document.getElementById('xq').value=xqHtml;
}
function showZyList(obj) {
	if (obj.checked) {
		document.getElementById("dispZy").style.display = "";
		document.getElementById("dispXy").style.display = "none";
		document.getElementById("bmlb").value = "zydm";
	} else {
		document.getElementById("dispXy").style.display = "";
		document.getElementById("dispZy").style.display = "none";
		document.getElementById("bmlb").value = "xydm";
	}
}
function chkPriseBat() {
	var jxjMsg = document.getElementById("jxjdm").options[document.getElementById("jxjdm").selectedIndex].text;
	var jxjV = document.getElementById("jxjdm").value;
	var xyMsg = document.getElementById("xy").options[document.getElementById("xy").selectedIndex].text;
	var xyV = document.getElementById("xy").value;
	var zyMsg = document.getElementById("zy").options[document.getElementById("zy").selectedIndex].text;
	var zyV = document.getElementById("zy").value;
	var bjMsg = document.getElementById("bj").options[document.getElementById("bj").selectedIndex].text;
	var bjV = document.getElementById("bj").value;
	jxjMsg = (jxjMsg == "") ? "全部" : jxjMsg;
	xyMsg = (xyMsg == "") ? "全部" : xyMsg;
	zyMsg = (zyMsg == "") ? "全部" : zyMsg;
	bjMsg = (bjMsg == "") ? "全部" : bjMsg;
	var msg = "\"批量设置\"将统一处理符合下列条件的所有数据:\n \n";
	msg += "  "+jQuery("#xbmc").val()+":  " + xyMsg + "\n";
	msg += "  专业:  " + zyMsg + "\n";
	msg += "  班级:  " + bjMsg + "\n";
	msg += "奖学金:  " + jxjMsg + "\n ";
	msg += "\n确定要继续吗?";
	if (confirm(msg)) {
		showTopWin("/xgxt/chgPriseTime.do?xy=" + xyV + "&zy=" + zyV + "&bj=" + bjV + "&jxj=" + jxjV, 470, 520);
		return true;
	} else {
		return false;
	}
}
function unableAllSel(excep) {
	if (excep == "") {
		excep = "1-1";
	}
	var tmp = document.getElementsByTagName("select");
	for (i = 0; i < tmp.length; i++) {
		if (!inArray(tmp[i].id, excep.split("-"))) {
			tmp[i].disabled = true;
		}
	}
}
function ableAllSel(excep) {
	if (excep == "") {
		excep = "1-1";
	}
	var tmp = window.dialogArguments.document.getElementsByTagName("select");
	for (i = 0; i < tmp.length; i++) {
		if (!inArray(tmp[i].id, excep.split("-"))) {
			tmp[i].disabled = false;
		}
	}
}
function listPriseConf(url) {
	var xxdm = "";
	if($('xxdm')){
		xxdm = document.getElementById("xxdm").value;
	}
	var rychMsg = document.getElementById('jxjdm').value;
	var njMsg = document.getElementById("nj").value;
	var jxjMsg = document.getElementById("jxjdm").value;
	var bmMsg = document.getElementById("xy").value;
	if (document.getElementById("chgMode").checked) {
		bmMsg = document.getElementById("zy").value;
	}
	if ((xxdm=='13022' && njMsg=='') || (xxdm=='13022' && rychMsg=='')) {
		alert('查询条件中年级与荣誉称号为必选，请确认！');
		return false;
	}
	if ((njMsg + bmMsg + jxjMsg) == "") {
		if (confirm("您没有选择任何条件,查询将耗费较长时间.\n确定要继续吗?")) {
			document.forms[0].go.value = "go";
			refreshForm(url);
			return true;
		} else {
			return false;
		}
	} else {
		document.forms[0].go.value = "go";
		refreshForm(url);
		return true;
	}
}
function sztzlistPriseConf(url) {
//	var kmMsg = document.getElementById("kmdm").value;	
//	var bjMsg = document.getElementById("bjdm").value;
//	var bmMsg = document.getElementById("xy").value;
//	if (document.getElementById("chgMode").checked) {
//		bmMsg = document.getElementById("zy").value;
//	}
//	if ((kmMsg + bjMsg + bmMsg) == "") {
//		if (confirm("您没有选择任何条件,查询将耗费较长时间.\n确定要继续吗?")) {
//			document.forms[0].go.value = "go";
//			refreshForm(url);
//			return true;
//		} else {
//			return false;
//		}
//	} else {
        
		document.forms[0].go.value = "go";
		refreshForm(url);
		if($("search_go")){
		   $("search_go").disabled=true;
		}
		return true;
		
//	}

}
function sztzConf(url) {
	var njMsg = document.getElementById("nj").value;
	var sztzMsg = document.getElementById("sztzdm").value;
	var bmMsg = document.getElementById("xy").value;
	if ((njMsg + bmMsg + sztzMsg) == "") {
		if (confirm("您没有选择任何条件,查询将耗费较长时间.\n确定要继续吗?")) {
			document.forms[0].go.value = "go";
			refreshForm(url);
			return true;
		} else {
			return false;
		}
	} else {
		document.forms[0].go.value = "go";
		refreshForm(url);
		return true;
	}
}
function listPriseConfJxj(url) {
	var jxjMsg = document.getElementById("jxjdm").value;
	var xyMsg = document.getElementById("xy").value;
	var zyMsg = document.getElementById("zy").value;
	var bjMsg = document.getElementById("bj").value;
	if ((xyMsg + zyMsg + jxjMsg + bjMsg) == "") {
		document.forms[0].go.value = "go";
		refreshForm(url);
		return true;
	} else {
		document.forms[0].go.value = "go";
		refreshForm(url);
		return true;
	}
}
function numInputValue(obj, maxLen,evt) {
	evt=evt?evt:(window.event?window.event:null);
	var key = evt.keyCode;
	if(key == 0){//////针对firefox
		key = evt.which;
	}else if(key == 8){
		return true;
	}
	
	if (((key >= 48 && key <= 57) || key == 46) && obj.value.length < maxLen) {
		return true;
	} else {
		return false;
	}
}
//带小数点限制数字及长度输入事件方法
function sztzNumInputValue(obj, maxLen,evt) {
	evt=evt?evt:(window.event?window.event:null);
	var key = evt.keyCode;
	if(key == 0){//////针对firefox
		key = evt.which;
	}else if(key == 8){
		return true;
	}
	var numLen = obj.value.length;
	var num = 0;
	var str = '.';
//	var strV = '0';
//	var numV = 0;
	  for(i = 0;i <numLen;i++){	   
	    if(obj.value.indexOf(str)!=-1){
	  	  num =num+2;
	    }
//	    if(obj.value.indexOf(strV)!=-1){
//	      numV=numV+2;
//	    }
	  }
	  if (((key >= 48 && key <= 57) || key == 46) && obj.value.length < maxLen ) {						
		if((num > 0 || numLen == 0) && key == 46 ){
		   return false;
		}else{
		    return true;
		}
//		if((numV > 0|| numLen == 0) && event.keyCode == 48 ){
//		    return false;
//		}			
	  } else {
		return false;

	  }	
}
//不带小数点限制数字及长度输入事件方法
function onlyNum(obj,maxLen) {

	var key = event.keyCode;
	var mouse = event.button;
	
	//禁止Shift
	if (key == 16){
		alertInfo("该控件不可以使用Shift键，请谅解^_^");
		return false;
	}
	
	//退格，delete，上下左右，放行
	if ((key == 8 || key == 46 
		|| key == 37 || key == 48 
		|| key == 39 || key == 40 ) 
		|| mouse == 1){
		return true;
	}
	
	//禁止鼠标右键
	if (mouse == 2){
		alertInfo("该控件不可以使用鼠标右键，请谅解^_^");
		return false;
	}
	
	//首数字不能是0
	if(obj.value.length > 0){
		var numValue = obj.value.substring(0,1);
		if(numValue == "0"){
//			alert("数字不能以0开头，请确认！");
//			obj.focus;
//			return false;
		}
	}

	//只能输入数字
	if (((key >= 48 && key <= 57) || (key >= 96 && key <= 105))&& obj.value.length < maxLen) {				
		return true;					
	} else {
		return false;
	}	
}
function chkonlynum() {
	if ((event.keyCode >=48) && (event.keyCode<=57)) {
		event.returnValue=true;
	}
	else {
		event.returnValue=false;
	}
}


function numJc(obj, maxLen){
	if (obj.value.length >= maxLen){
		return false;
	} else{
		return true;
	}
}
function getSelValByTxt(selObj, txt) {
	for (i = 0; i < selObj.options.length; i++) {
		if (selObj.options[i].text == txt) {
			return selObj.options[i].value;
		}
		if (selObj.options[i].value == txt) {
			return selObj.options[i].text;
		}
	}
	return txt;
}
function getSelValByTxt111(selObj, txt) {
	for (i = 0; i < selObj.options.length; i++) {
		if (selObj.options[i].text == replaceChar(txt," ","")) {
			return selObj.options[i].value;
		}
	}
	return txt;
}
function initSetPriseOne() {
	var xnMsg = window.dialogArguments.document.getElementById("xn").value;
	var ndMsg = window.dialogArguments.document.getElementById("nd").value;
	var jxjMsg = window.dialogArguments.curr_row.cells[3].innerText;
	var selTmp = window.dialogArguments.document.getElementById("jxjdm");
	var jxjVal = getSelValByTxt111(selTmp, jxjMsg);
	var bmMsg = window.dialogArguments.curr_row.cells[1].innerText;
	selTmpXy = window.dialogArguments.document.getElementById("xy");
	selTmpZy = window.dialogArguments.document.getElementById("zy");
	selTmpBj = window.dialogArguments.document.getElementById("bj");
	if(window.dialogArguments.document.getElementById("xqmod")){
		var xqMsg= window.dialogArguments.document.getElementById("xq").options[window.dialogArguments.document.getElementById("xq").selectedIndex].text;
		$('xqtr').style.display = "";
		document.forms[0].xq.value = xqMsg;
	}
	var bmVal = getSelValByTxt111(selTmpXy, bmMsg);
	if (bmVal == bmMsg) {
		bmVal = getSelValByTxt111(selTmpZy, bmMsg);
	}
	if (bmVal == bmMsg) {
		bmVal = getSelValByTxt111(selTmpBj, bmMsg);
	}
	var jyrs = window.dialogArguments.curr_row.cells[5].innerText;
	var rstz = window.dialogArguments.curr_row.cells[7].innerText;
	var nj = window.dialogArguments.curr_row.cells[2].innerText;
	var jsje = window.dialogArguments.curr_row.cells[8].innerText;
	document.forms[0].xn.value = xnMsg;
	document.forms[0].nd.value = ndMsg;
	document.forms[0].bmT.value = bmMsg;
	document.forms[0].bm.value = bmVal;
	document.forms[0].jxjdmT.value = jxjMsg;
	document.forms[0].jxjdm.value = jxjVal;
	document.forms[0].jyrs.value = jyrs;
	document.forms[0].rstz.value = rstz;
	document.forms[0].njT.value = nj;
	document.forms[0].nj.value = nj;
	document.forms[0].jsje.value=jsje;
}
function checkTime(sqB, sqE, shB, shE) {
	var a1 = document.all(sqB + "1").value;
	var a2 = (document.all(sqB + "2").value != "" && document.all(sqB + "2").value.length != 2) ? ("0" + document.all(sqB + "2").value) : document.all(sqB + "2").value;
	var a3 = (document.all(sqB + "3").value != "" && document.all(sqB + "3").value.length != 2) ? ("0" + document.all(sqB + "3").value) : document.all(sqB + "3").value;
	var a4 = (document.all(sqB + "4").value != "" && document.all(sqB + "4").value.length != 2) ? ("0" + document.all(sqB + "4").value) : document.all(sqB + "4").value;
	var b1 = document.all(sqE + "1").value;
	var b2 = (document.all(sqE + "2").value != "" && document.all(sqE + "2").value.length != 2) ? ("0" + document.all(sqE + "2").value) : document.all(sqE + "2").value;
	var b3 = (document.all(sqE + "3").value != "" && document.all(sqE + "3").value.length != 2) ? ("0" + document.all(sqE + "3").value) : document.all(sqE + "3").value;
	var b4 = (document.all(sqE + "4").value != "" && document.all(sqE + "4").value.length != 2) ? ("0" + document.all(sqE + "4").value) : document.all(sqE + "4").value;
	var c1 = document.all(shB + "1").value;
	var c2 = (document.all(shB + "2").value != "" && document.all(shB + "2").value.length != 2) ? ("0" + document.all(shB + "2").value) : document.all(shB + "2").value;
	var c3 = (document.all(shB + "3").value != "" && document.all(shB + "3").value.length != 2) ? ("0" + document.all(shB + "3").value) : document.all(shB + "3").value;
	var c4 = (document.all(shB + "4").value != "" && document.all(shB + "4").value.length != 2) ? ("0" + document.all(shB + "4").value) : document.all(shB + "4").value;
	var d1 = document.all(shE + "1").value;
	var d2 = (document.all(shE + "2").value != "" && document.all(shE + "2").value.length != 2) ? ("0" + document.all(shE + "2").value) : document.all(shE + "2").value;
	var d3 = (document.all(shE + "3").value != "" && document.all(shE + "3").value.length != 2) ? ("0" + document.all(shE + "3").value) : document.all(shE + "3").value;
	var d4 = (document.all(shE + "4").value != "" && document.all(shE + "4").value.length != 2) ? ("0" + document.all(shE + "4").value) : document.all(shE + "4").value;
	a1 = (a1 == "") ? "1900-01-01" : a1;
	a2 = (a2 == "" || isNaN(parseInt(a2)) || parseInt(a2) > 24 || parseInt(a2) < 0) ? "00" : a2;
	a3 = (a3 == "" || isNaN(parseInt(a3)) || parseInt(a3) > 60 || parseInt(a3) < 0) ? "00" : a3;
	a4 = (a4 == "" || isNaN(parseInt(a4)) || parseInt(a4) > 60 || parseInt(a4) < 0) ? "00" : a4;
	b1 = (b1 == "") ? "1900-01-01" : b1;
	b2 = (b2 == "" || isNaN(parseInt(b2)) || parseInt(b2) > 24 || parseInt(b2) < 0) ? "00" : b2;
	b3 = (b3 == "" || isNaN(parseInt(b3)) || parseInt(b3) > 60 || parseInt(b3) < 0) ? "00" : b3;
	b4 = (b4 == "" || isNaN(parseInt(b4)) || parseInt(b4) > 60 || parseInt(b4) < 0) ? "00" : b4;
	c1 = (c1 == "") ? "1900-01-01" : c1;
	c2 = (c2 == "" || isNaN(parseInt(c2)) || parseInt(c2) > 24 || parseInt(c2) < 0) ? "00" : c2;
	c3 = (c3 == "" || isNaN(parseInt(c3)) || parseInt(c3) > 60 || parseInt(c3) < 0) ? "00" : c3;
	c4 = (c4 == "" || isNaN(parseInt(c4)) || parseInt(c4) > 60 || parseInt(c4) < 0) ? "00" : c4;
	d1 = (d1 == "") ? "1900-01-01" : d1;
	d2 = (d2 == "" || isNaN(parseInt(d2)) || parseInt(d2) > 24 || parseInt(d2) < 0) ? "00" : d2;
	d3 = (d3 == "" || isNaN(parseInt(d3)) || parseInt(d3) > 60 || parseInt(d3) < 0) ? "00" : d3;
	d4 = (d4 == "" || isNaN(parseInt(d4)) || parseInt(d4) > 60 || parseInt(d4) < 0) ? "00" : d4;
	var a = a1 + a2 + a3 + a4;
	var b = b1 + b2 + b3 + b4;
	var c = c1 + c2 + c3 + c4;
	var d = d1 + d2 + d3 + d4;
	if (a > b) {
		alert("申请开始时间晚于结束时间，设置错误！");
		return false;
	} else {
		if (c > d) {
			alert("审核开始时间晚于结束时间，设置错误！");
			return false;
		} else {
			if (a > c) {
				alert("审核开始时间早于申请开始时间，设置错误！");
				return false;
			} else {
				document.getElementById(sqB).value = replaceChar(a, "-", "");
				document.getElementById(sqE).value = replaceChar(b, "-", "");
				document.getElementById(shB).value = replaceChar(c, "-", "");
				document.getElementById(shE).value = replaceChar(d, "-", "");
				return true;
			}
		}
	}
}
function initSetPriseBat() {
	var xnMsg = window.dialogArguments.document.getElementById("xn").value;
	var ndMsg = window.dialogArguments.document.getElementById("nd").value;
	var njMsg = window.dialogArguments.document.getElementById("nj").options[window.dialogArguments.document.getElementById("nj").selectedIndex].text;
	var njVal = window.dialogArguments.document.getElementById("nj").options[window.dialogArguments.document.getElementById("nj").selectedIndex].value;
	var jxjMsg = window.dialogArguments.document.getElementById("jxjdm").options[window.dialogArguments.document.getElementById("jxjdm").selectedIndex].text;
	var jxjVal = window.dialogArguments.document.getElementById("jxjdm").options[window.dialogArguments.document.getElementById("jxjdm").selectedIndex].value;
	var bmMsg = window.dialogArguments.document.getElementById("xy").options[window.dialogArguments.document.getElementById("xy").selectedIndex].text;
	var bmVal = window.dialogArguments.document.getElementById("xy").options[window.dialogArguments.document.getElementById("xy").selectedIndex].value;
	if (window.dialogArguments.document.getElementById("chgMode").checked) {
		bmMsg = window.dialogArguments.document.getElementById("zy").options[window.dialogArguments.document.getElementById("zy").selectedIndex].text;
		bmVal = window.dialogArguments.document.getElementById("zy").options[window.dialogArguments.document.getElementById("zy").selectedIndex].value;
	}
	njMsg = (njMsg == "") ? "全部" : njMsg;
	jxjMsg = (jxjMsg == "") ? "全部" : jxjMsg;
	bmMsg = (bmMsg == "") ? "全部" : bmMsg;
	document.forms[0].xn.value = xnMsg;
	document.forms[0].nd.value = ndMsg;
	document.forms[0].njT.value = njMsg;
	document.forms[0].nj.value = njVal;
	document.forms[0].bmT.value = bmMsg;
	document.forms[0].bm.value = bmVal;
	document.forms[0].jxjdmT.value = jxjMsg;
	document.forms[0].jxjdm.value = jxjVal;
}
function checkFile() {
	var tmp = document.getElementById("file").value;
	if (tmp.value == "" || tmp.substring(tmp.length - 4, tmp.length).toLowerCase() != ".xls") {
		alert("您选择的文件不合法，请重新选择！");
		document.getElementById("file").value = "";
		return false;
	} else {
		document.forms[0].action = "/xgxt/uploadFile.do?tabName=" + window.dialogArguments.document.forms[0].realTable.value;
		if($("moditag"))
		document.forms[0].action +="&moditag="+document.getElementById("moditag").value; 
		document.forms[0].submit();
		return true;
	}
}

function chgPassCheck() {
	newP1 = document.forms[0].newP1.value;
	newP2 = document.forms[0].newP2.value;
	var hintCheck = document.getElementById("hintCheck");
	
	
	if (document.forms[0].oldP.value == "") {
		alert("请输入旧口令！");
		document.forms[0].oldP.value = "";
		document.forms[0].oldP.focus();
	} else {
		
		if (newP1.length < 6) {
			
			//alert("口令过短，不能少于6位！");
			document.forms[0].newP1.value = "";
			document.forms[0].newP1.value = "";
			document.forms[0].newP1.focus();
		} else {
			if (newP1 != newP2) {
				//alert("两次输入的口令不一致！");
				hintCheck.style.display="block";
				//document.forms[0].newP1.value = "";
				document.forms[0].newP2.value = "";
				//document.forms[0].newP1.focus();
			} else {
				var url = "chgPass.do";
				refreshForm(url);
			}
		}
	}
}
function subList() {
	var i;
	if (document.forms[0].mappingList.options.length <= 0) {
		alert("对应字段不能为空!");
		return false;
	}
	for (i = 0; i < document.forms[0].mappingList.options.length; i++) {
		document.forms[0].mappingItems.value = document.forms[0].mappingItems.value + "!!SplitSignOne!!!!SplitSignTwo!!";
		document.forms[0].mappingItems.value = document.forms[0].mappingItems.value + document.forms[0].mappingList.options[i].value;
	}
	if (confirm("确定要导入数据吗？")) {
		var dd_html = "";
		dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>正在导入数据，请稍候......<br><br>";
		dd_html += "<img src='images/loading.gif' width='180' height='20' border='0'>";
		dd_html += "</td></tr></table>";
		for (i = 1; i < document.getElementsByTagName("table").length; i++) {
			document.getElementsByTagName("table")[i].style.display = "none";
		}
		showDiv(dd_html, 300, 120);
		document.forms[0].submit();
		return true;
	}
	return false;
}

function addItemList() {
	var excelListIndex = document.forms[0].excelList.selectedIndex;
	var oracleListIndex = document.forms[0].oracleList.selectedIndex;
	if (excelListIndex < 0 || oracleListIndex < 0 || document.forms[0].mappingList.options.length == document.forms[0].oracleList.options.length || checkDoubleItem()) {
		return false;
	}
	var addExcelV = document.forms[0].excelList.options[excelListIndex].value;
	var addExcelT = document.forms[0].excelList.options[excelListIndex].text;
	var addOracleV = document.forms[0].oracleList.options[oracleListIndex].value;
	var addOracleT = document.forms[0].oracleList.options[oracleListIndex].text;
	document.forms[0].mappingList.options[document.forms[0].mappingList.options.length] = new Option(addExcelT + "------" + addOracleT, addExcelV + "!!SplitSignTwo!!" + addOracleV);
}
function deleteItemList() {
	var mappingListIndex = document.forms[0].mappingList.selectedIndex;
	if (mappingListIndex < 0) {
		return false;
	}
	document.forms[0].mappingList.options[mappingListIndex] = null;
}
function defaultItemList() {
	var defType = document.getElementById("defType").value;
	if(defType == 1){
		if (document.forms[0].mappingList.options.length > 0) {
			alert("只有对应字段列表框为空的状态下才可以进行默认对应！");
			return false;
		}
		var len = document.forms[0].oracleList.options.length > document.forms[0].excelList.options.length ? document.forms[0].excelList.options.length : document.forms[0].oracleList.options.length;
		var i;
		var addExcelV;
		var addExcelT;
		var addOracleV;
		var addOracleT;
		for (i = 0; i < len; i++) {
			addExcelV = document.forms[0].excelList.options[i].value;
			addExcelT = document.forms[0].excelList.options[i].text;
			addOracleV = document.forms[0].oracleList.options[i].value;
			addOracleT = document.forms[0].oracleList.options[i].text;
			document.forms[0].mappingList.options[i] = new Option(addExcelT + "------" + addOracleT, addExcelV + "!!SplitSignTwo!!" + addOracleV);
		}
	}else{
		if (document.forms[0].mappingList.options.length > 0) {
			alert("只有对应字段列表框为空的状态下才可以进行默认对应！");
			return false;
		}
		var excelLen = document.forms[0].excelList.options.length;
		var oracleLen = document.forms[0].oracleList.options.length;
		var i;
		var j;
		var len=0;
		var addExcelV;
		var addExcelT;
		var addOracleV;
		var addOracleT;
		for(i = 0; i < excelLen; i++){
			for(j = 0; j < oracleLen; j++){
				if(document.forms[0].excelList.options[i].text == document.forms[0].oracleList.options[j].text){					
					addExcelV = document.forms[0].excelList.options[i].value;
					addExcelT = document.forms[0].excelList.options[i].text;
					addOracleV = document.forms[0].oracleList.options[j].value;
					addOracleT = document.forms[0].oracleList.options[j].text;
					document.forms[0].mappingList.options[len] = new Option(addExcelT + "------" + addOracleT, addExcelV + "!!SplitSignTwo!!" + addOracleV);
					len++;
					break;
				}
			}
		}
	}
}
function clearList() {
	document.forms[0].mappingList.options.length = 0;
}
function checkDoubleItem() {
	var tmp = new Array();
	for (i = 0; i < document.forms[0].mappingList.options.length; i++) {
		tmp = document.forms[0].mappingList.options[i].value.split("!!SplitSignTwo!!");
		if (document.forms[0].oracleList.options[document.forms[0].oracleList.selectedIndex].value == tmp[1]) {
			return true;
		}
	}
}
function checkDoubleItemT() {
	var tmp = new Array();
	for (i = 0; i < document.forms[0].mappingList.options.length; i++) {
		tmp = document.forms[0].mappingList.options[i].value.split("!!SplitTwo!!");
		if (document.forms[0].oracleList.options[document.forms[0].oracleList.selectedIndex].value == tmp[1]) {
			return true;
		}
	}
}

function expTab(the_table, tabTit, titSpan) {
	/*var HKEY_Root="HKEY_CURRENT_USER";
	var HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	var Wsh=new ActiveXObject("WScript.Shell");
	var HKEY_Key="header";
	    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
	    HKEY_Key="footer";
	    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); */ 
	var table_title = (titSpan == null || titSpan == "") ? tabTit : document.getElementById(titSpan).outerHTML;	
	var table_content=((the_table != null || the_table != "")&&$(the_table)) ? document.all(the_table).outerHTML:"未找到任何记录！" ;
	var the_content = "<style media='print'>\n";
	the_content += ".noPrin{\n";
	the_content += "display:none;}\n";
	the_content += "</style>\n";
	//the_content += "<link rel='stylesheet' href='shgcjyweb/style/public.css'  type='text/css' media='all'/>";
	//the_content += "<link rel='stylesheet' href='shgcjyweb/style/module.css' type='text/css' media='all' />";
	//the_content += "<link rel=''stylesheet' href='style/main.css' type='text/css' media='all' />";
	the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
	the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
	//the_content += "<script language='javascript' src='js/function.js'>";
	//the_content += "</script>\n";
	the_content += "<center><div><font size='10'>";
	the_content += table_title;
	the_content += "</font></div>";	
	the_content += table_content;			
	the_content = the_content.replace(/ style=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ mode=\"(false|true)"/g, "");	
	the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
	the_content = the_content.replace(/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
	the_content += "\n<br><div class='noPrin btn'><button onclick=\"try{WebBrowser.ExecWB(8,1)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}\">页面设置</button>";
	the_content += "<button onclick=\"try{WebBrowser.ExecWB(7,1)} catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}\">打印预览</button>";
	the_content += "<button onclick=\"try{WebBrowser.ExecWB(6,6)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}\">直接打印</button>";
	the_content += "<\/div>";		
	var newwin = window.open("about:blank", "_blank", "");
	newwin.document.open();
	newwin.document.write(the_content);
	newwin.document.close();
	newwin = null;
}

function expTjTab(the_table, tabTit, titSpan) {
	/*var HKEY_Root="HKEY_CURRENT_USER";
	var HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	var Wsh=new ActiveXObject("WScript.Shell");
	var HKEY_Key="header";
	    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
	    HKEY_Key="footer";
	    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); */ 
	var table_title = (titSpan == null || titSpan == "") ? tabTit : document.getElementById(titSpan).outerHTML;	
	var table_content=((the_table != null || the_table != "")&&$(the_table)) ? "":"未找到任何记录！" ;
	var table_pj="<table summary=\"\" class=\"dateline\" align=\"\" width=\"100%\" id=\"rsTable\">";
	
	var obj = document.all(the_table);
	var table = document.createElement("table");
	document.body.appendChild(table);
	
	for(var i=0;i<document.all(the_table).getElementsByTagName("tr").length;i++){
		var chbox=document.getElementById("chbox"+(i));
		
		if(i==0){
				table_pj+="<TABLE  summary=\"\" class=\"dateline\" width=\"100%\"><THEAD>";
				var tr = obj.getElementsByTagName("TR")[i];
				table_pj+="<tr>";
				for(var j=1;j<tr.getElementsByTagName("td").length;j++){
					table_pj+=tr.getElementsByTagName("td")[j].outerHTML;
				}
				table_pj+="</tr></THEAD><TBODY>";
		}else{
				var tr = obj.getElementsByTagName("TR")[i];
				table_pj+="<tr>";
				
				var flag = false;
				for(var j=0;j<tr.getElementsByTagName("td").length;j++){
					if(j == 0){
						var ch = tr.getElementsByTagName("td")[0].getElementsByTagName("input");
						if($("chbox"+(i-1)) && ($("chbox"+(i-1)).checked || document.getElementById("all").checked)){
							flag= true;
						}
					}
					if(flag&& j!=0){
						table_pj+=tr.getElementsByTagName("td")[j].outerHTML;
					}
				}
				table_pj+="</tr>";
		}
	}
	table_pj+="</TBODY></TABLE>";
	//table_content=table_pj;
	var the_content = "<style media='print'>\n";
	the_content += ".noPrin{\n";
	the_content += "display:none;}\n";
	the_content += "</style>\n";
	the_content += "<link rel='stylesheet' href='" + stylePath + "/css/public.css'  type='text/css' media='all'/>";
	the_content += "<link rel='stylesheet' href='" + stylePath + "/css/module.css' type='text/css' media='all' />";
	the_content += "<link rel=''stylesheet' href='" + stylePath + "/css/skin_blue.css'type='text/css' media='all' />";
	//the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
	the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
	//the_content += "<script language='javascript' src='js/function.js'>";
	//the_content += "</script>\n";
	the_content += "<center><div><font size='10'>";
	the_content += table_title;
	the_content += "</font></div>";	
	the_content += table_pj;			
	the_content = the_content.replace(/ style=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ mode=\"(false|true)"/g, "");	
	the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
	the_content = the_content.replace(/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
	the_content += "\n<br><div class='noPrin btn'><button onclick=\"try{WebBrowser.ExecWB(8,1)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}\">页面设置</button>";
	the_content += "<button onclick=\"try{WebBrowser.ExecWB(7,1)} catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}\">打印预览</button>";
	the_content += "<button onclick=\"try{WebBrowser.ExecWB(6,6)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}\">直接打印</button>";
	the_content += "<\/div>";	
	var newwin = window.open("about:blank", "_blank", "");
	newwin.document.open();
	newwin.document.write(the_content);
	newwin.document.close();
	newwin = null;
}

function expAppTab(the_table, tabTit, titSpan) {
	var table_title = (titSpan == null || titSpan == "") ? tabTit : document.getElementById(titSpan).outerHTML;
	var the_content = "<style media='print'>\n";
	the_content += ".noPrin{\n";
	the_content += "display:none;}\n";
	the_content += "</style>\n";
	the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
	the_content += "<link rel='stylesheet' href='" + stylePath + "/css/public.css'  type='text/css' media='all'/>";
	the_content += "<link rel='stylesheet' href='" + stylePath + "/css/module.css' type='text/css' media='all' />";
	the_content += "<link rel=''stylesheet' href='" + stylePath + "/css/skin_blue.css' type='text/css' media='all' />";
	the_content += "<script language='javascript' src='js/prototype-1.6.0.3.js'>";
	the_content += "<script language='javascript'>";
	the_content +="var HKEY_Root,HKEY_Path,HKEY_Key;" ;
	the_content +="HKEY_Root='HKEY_CURRENT_USER';";
	the_content +="HKEY_Path='\\Software\\Microsoft\\Internet Explorer\\PageSetup\\';";
	the_content +="var Wsh=new ActiveXObject('WScript.Shell');";
	the_content +="HKEY_Key='header';";
	the_content +="Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,'');";
	the_content +="HKEY_Key='footer';";
	the_content +="Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,'');";	     
	the_content += "</sc";
	the_content += "ript>\n";
	the_content += "<center><b><font size='6'>";
	the_content += table_title;
	the_content += "</font></b>";
	the_content += "<table class='theTable'><tr><td>";
	the_content += document.all(the_table).outerHTML;
	the_content += "</td></tr></table>";
	//the_content = the_content.replace(/ style=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ mode=\"(false|true)"/g, "");
	the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
	the_content = the_content.replace(/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
	the_content = the_content.replace(/\*/g, " ");
	the_content += "\n<br><div class='noPrin btn'><button name='btn_print' onclick=\"try{WebBrowser.ExecWB(8,1)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}\">页面设置</button>";
	the_content += "<button name='btn_print' onclick=\"try{WebBrowser.ExecWB(7,1)} catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}\">打印预览</button>";
	the_content += "<button name='btn_print' onclick=\"try{WebBrowser.ExecWB(6,6)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}\">直接打印</button></div>";
//	the_content = the_content.replace(/\s*<TFOOT>.*\n*.*<\/TFOOT>\s*/gi, "");
	the_content = the_content.replace(/\n*/gi,"").replace(/<tfoot>.*<\/TFOOT>/gi,'');
	
	var newwin = window.dialogArguments ? window.dialogArguments.document.open("about:blank", "_blank", "") : window.open("about:blank", "_blank", "");
	newwin.document.open();
	newwin.document.write(the_content);
	newwin.document.getElementById(the_table).getElementsByTagName("tr")[0].style.display = "none";
	
	var inps = newwin.document.getElementById(the_table).getElementsByTagName("INPUT");
	var sels = newwin.document.getElementsByTagName("select");
	var txts = newwin.document.getElementsByTagName("textarea");
	var buts = newwin.document.getElementsByTagName("button");
	var trs = newwin.document.getElementsByTagName("tr");
	var divs = newwin.document.getElementsByTagName("div");
	var checkboxs = newwin.document.getElementsByTagName("checkbox");
	for (i = inps.length - 1; i >= 0; i--) {
		if(inps[i].type=="hidden"){
			inps[i],outerHTML = "";
		}else if(inps[i].type!="checkbox"){
			inps[i].outerHTML = inps[i].value;
		}
	}
	for (i = sels.length - 1; i >= 0; i--) {
		sels[i].outerHTML = sels[i].options[sels[i].selectedIndex].text;
	}
	for (i = txts.length - 1; i >= 0; i--) {
		var s = txts[i].value.length;
		var f=0;
		var newTextArea = new Array();
		for(var t=0;40*(t+1)<s;t++){
			if(s>t*40+39){
				f=t*40+39;
			}else{
				f=s;
			}
			newTextArea[i]=txts[i].value.substring(t*40,f)+"</br>";
		}
		txts[i].outerHTML = txts[i].value;
	}
	for (i = buts.length - 1; i >= 0; i--) {
		if(buts[i].name != "btn_print"){
			buts[i].style.display = "none";
		}
	}	
	newwin.document.close();
	newwin = null;
}
function xyDisabled(ele) {
   if($("userType")){
	if ($("userType").value == "xy" || $("userType").value == "fdy" ) {
		if(!($("isFdy") && $("isFdy").value == "true")){
			var tmp = ele.split("-");
			for (i = 0; i < tmp.length; i++) {				
				if(document.getElementById(tmp[i])){
				   document.getElementById(tmp[i]).disabled = true;
				}
			}
		}
	}
   }  
}

function fdyDisabled(ele) {
if($("isFdy")){
	   if($("isFdy").value == "true" || $("isFdy").value == true){
		   var tmp = ele.split("-");
			for (i = 0; i < tmp.length; i++) {
				if(document.getElementById(tmp[i])){
				   document.getElementById(tmp[i]).disabled = true;
				}
			}
	   }
}
}

function chkPriseOne(url, w, h) {
	var xxdm = "";
	if($('xxdm')){
		xxdm = document.getElementById("xxdm").value;
	}
	if (w == null) {
		w = 700;
	}
	if (h == null) {
		h = 500;
	}	
	if (curr_row == null) {
		alert("请选择要操作的行！");
		return false;
	} else {		
		var val = curr_row.cells[0].getElementsByTagName("input")[0].value;		
		url += val;
		var tab='';
		if ($('realTable')) {
				tab = document.getElementById('realTable').value;
			}
		if((xxdm=='11551' && tab=='xsjxjb') || ((xxdm=='11551') && tab=='xsrychb')){
			url += "&xh=";
			url += curr_row.cells[5].innerText;
		}
		
		//if ((xxdm=='13022' && tab=='xsjxjb') || ((xxdm=='13022') && tab=='xsrychb')) {
					
		//	url += '&jqfpm=';
		//	url += curr_row.cells[0].getElementsByTagName("input")[1].value;
		//	url += '&zhszcpzfpm=';
		//	url += curr_row.cells[0].getElementsByTagName("input")[2].value;
		//}
		showTopWin(url, w, h);
   }
}

function updateOrViewOne(url, w, h) {
	var xxdm = "";
	if($('xxdm')){
		xxdm = document.getElementById("xxdm").value;
	}
	if (w == null) {
		w = 700;
	}
	if (h == null) {
		h = 500;
	}	
	if (curr_row == null) {
		alert("请选择要操作的行！");
		return false;
	} else {		
		var val = curr_row.cells[0].getElementsByTagName("input")[0].value;		
		url += val;
		showTopWin(url, w, h);
	}
}

function sztzChkPriseOne(url, w, h) {
	if (w == null) {
		w = 700;
	}
	if (h == null) {
		h = 500;
	}
	if (curr_row == null) {
		alert("请选择要操作的行！");
		return false;
	} else {
		var val = curr_row.cells[0].getElementsByTagName("input")[0].value;
		url+=val;
		url += "&tableName=";
	    url += document.forms[0].tableName.value;
		showTopWin(url, w, h);	
	}	
}
function allSelEmpty() {
	var sel = document.getElementsByTagName("select");
	if($("KShdsj")){
		if($("KShdsj").value != null && $("KShdsj").value != ""){
			return false;
		}
	}
	if($("JShdsj")){
		if($("JShdsj").value != null && $("JShdsj").value != ""){
			return false;
		}
	}
	for (i = 0; i < sel.length; i++) {
		if (sel[i].value != null && sel[i].value != "") {
			return false;
		}
	}
	return true;
}

function allNotEmpThenGo(url) {
	
	url = url.replace("/xgxt/","");
	//if($("xtydm")){
	//	if($("xtydm").value == null || $("xtydm").value == ""){
	//		alert("请输入协调员代码！");
	//		return false;
	//	}
	//}	
	
	//if(url == "/xgxt/Army.do?"){
	//	url += "act=";
	//	url += $("act").value;
	//}
//	if (allSelEmpty()) {
//		if (confirm("您没有选择任何条件，此次操作将返回全部数据，可能会耗费相当长的时间。确定要继续吗？")){ 
//		    $('search_go').disabled=true;
//			document.forms[0].go.value = "go";
//			refreshForm(url);
//			return true;			
//		} else {
//			return false;
//		}
//	} else {
		if($("search_go")){
			$('search_go').disabled=true;
			//根据事件坐标和按钮坐标的比对确定是否保持当前页
				/*
			var e = window.event;     
			var mp=getMousePos(e);
			var ep=getElementPos($("search_go"));
			if(ep.x>mp.x||mp.x>ep.x+65||ep.y>mp.y||mp.y>ep.y+20){//$("search_go")button宽度65高度20 该处判断对小概率事件无法避免
				if($("isPage")){
					$("isPage").value = "yes";
			    }
			}
			*/
			//alert(mp.x+"      "+mp.y);
			//alert(ep.x+"      "+ep.y);
		}
	 
	    if(!$("isPage")||$("isPage").value==""){
	    	if($("currentPage")){
	    		$("currentPage").value = "1";
	    	}
	    }
	    if (document.forms[0].go) {
			document.forms[0].go.value = "go";	    	
	    }
		refreshForm(url);
		
		return true;
//	}
	
}

function allNotEmpThenGoForNews(url) {	
		if($("#search_go")){
			$('#search_go').disabled=true;
			//根据事件坐标和按钮坐标的比对确定是否保持当前页
				/*
			var e = window.event;     
			var mp=getMousePos(e);
			var ep=getElementPos($("#search_go"));
			if(ep.x>mp.x||mp.x>ep.x+65||ep.y>mp.y||mp.y>ep.y+20){//$("search_go")button宽度65高度20 该处判断对小概率事件无法避免
				if($("#isPage")){
					$("#isPage").value = "yes";
			    }
			}
			*/
		}
	 
	    if(!$("#isPage")||$("#isPage").value==""){
	    	if($("#currentPage")){
	    		$("#currentPage").value = "1";
	    	}
	    }
	    if (document.forms[0].go) {
			document.forms[0].go.value = "go";	    	
	    }
		refreshForm(url);
		return true;
}

function getElementPos(e){
	var e=e;
	var x = e.offsetLeft;
	var y = e.offsetTop;
	while(e = e.offsetParent){
		x += e.offsetLeft;
		y += e.offsetTop;
	}
	return {x:x,y:y};
}

function getMousePos(e){ 
	var e = e || window.event;
	return { 
		x:e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft,
		y:e.clientY + document.body.scrollTop + document.documentElement.scrollTop 
	};
}


function dataExport() {
	document.forms[0].action = "expData.do";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
function sztzDataExport(){
	document.forms[0].action = "sztzExpData.do";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
function allFilled(pkFilled) {
	var inp = document.getElementsByTagName("input");
	var sel = document.getElementsByTagName("select");
	var area = document.getElementsByTagName("textarea");
	if(pkFilled !=null && pkFilled !=""){
		var pk = pkFilled.split("-");
		for(var i=0;i<pk.length;i++){
			if(document.getElementById(pk[i]).value==""){
				alert("请将带\*号的项目填写完整！");
				return false;
			}
		}
	}else{
		for (i = 0; i < inp.length; i++) {
			if (inp[i].value == "" || inp[i].value == null) {
				alert("所有选项均不得为空！");
				//alert(inp[i].id);
				return false;
			}
		}
		for (i = 0; i < sel.length; i++) {
			if (sel[i].value == "" || sel[i].value == null) {
				alert("所有选项均不得为空！");
				//alert(sel[i].id);
				return false;
			}
		}
		for (i = 0; i < area.length; i++) {
			if (area[i].value == "" || area[i].value == null) {
				alert("所有选项均不得为空！");
				//alert(area[i].id);
				return false;
			}
		}
	}
	return true;
}
function priseAutoChk(url) {
	if (document.forms[0].xy.value == "") {
		alert("请选择要自动审核的"+jQuery("#xbmc").val()+"！");
		return false;
	} else {
		var confirmTxt = "自动审核将以";
		var chkType = document.getElementById("dispFalg").value;
		if (chkType == "bjdm") {
			confirmTxt += "\"班级\"为单位进行审核，审核依据为您在\"参数设置\"中设置的各个\"班级\"的人数上限。";
		} else {
			if (chkType == "zydm") {
				confirmTxt += "\"专业\"为单位进行审核，审核依据为您在\"参数设置\"中设置的各个\"专业\"的人数上限。";
			} else {
				confirmTxt += "\""+jQuery("#xbmc").val()+"\"为单位进行审核，审核依据为您在\"参数设置\"中设置的各个\""+jQuery("#xbmc").val()+"\"的人数上限。";
			}
		}
		confirmTxt += "\n若要改变审核方式，请点击\"取消\"，并修改\"显示方式\"。";
		confirmTxt += "\n\n自动审核将耗费较长的时间，确定要开始自动审核吗？";
		if (confirm(confirmTxt)) {
			var dd_html = "";
			dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>正在自动审核，请稍候......<br><br>";
			dd_html += "<span class='roll_tip'></span>";
			dd_html += "</td></tr></table>";
			for (i = 1; i < document.getElementsByTagName("table").length; i++) {
				document.getElementsByTagName("table")[i].style.display = "none";
			}
			showDiv(dd_html, 300, 120);
			alert("点击\"确定\"后开始审核！");
			refreshForm(url);
			return true;
		} else {
			return false;
		}
	}
}
function priseDataInit() {
	if (confirm("初始化操作将会清空当前学年、学期的所有数据，并重新生成。\n确定要初始化吗?")) {
		var dd_html = "";
		dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>正在初始化数据，请稍候......<br><br>";
		dd_html += "<span class='roll_tip'></span>";
		dd_html += "</td></tr></table>";
		for (i = 1; i < document.getElementsByTagName("table").length; i++) {
			document.getElementsByTagName("table")[i].style.display = "none";
		}
		showDiv(dd_html, 300, 120);
		refreshForm("/xgxt/initBaseData.do");
		return true;
	}
	return false;
}
function dataDoSavePub(url, pkFields) {
	if (allFilled(pkFields)) {
		if(url.search('doType=save&tableName=view_dwjlxx')){
			var pcsj = document.getElementById("pcsj").value;
			var fhsj = document.getElementById("fhsj").value;
			if(pcsj > fhsj){
				alert("返回时间小于派出时间！！");
				return false;
			} 
		}
		if(document.getElementById('jlxx')){
			var jlxx = document.getElementById('jlxx').value;
			if(jlxx != null){
				if(jlxx.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
		          		 alert("交流详细信息不能大于500个字符");
		          		 return false;
		       		 }
	        }
		}
		if(document.getElementById('jltj')){
			var jltj = document.getElementById('jltj').value;
			if(jltj != null){
				if(jltj.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
		          		 alert("交流生条件不能大于500个字符");
		          		 return false;
		       		 }
	        }
		}	
		var eles = pkFields.split("-");
		var valu = "";
		for (i = 0; i < eles.length; i++) {
			valu += document.getElementById(eles[i]).value;
		}
		url = url + valu;
		document.forms[0].action = url;
		document.forms[0].submit();
		return true;
		
	}
}
function viewMore(doType,dbl,oper) {
	var xxdm = "";
	if($("xxdm")){
		xxdm=document.getElementById("xxdm").value;
	}
	var pkValue = "";
	var url = "/xgxt/modiData.do?realTable=";
	var tmp = document.forms[0].realTable.value;
	var w = 550;
	var h = 450;
	url += tmp;
	url += "&details=";
	url += oper;
	url += "&doType=";
	url += doType;
	url += "&tableName=";
	url += document.forms[0].tableName.value;
	url += "&pk=";
	url += document.forms[0].pk.value;
	url += "&from=";
	url += document.forms[0].act.value;
	url += "&pkValue=";
	//奖学金类别
	var jxjlb = "";
	if(tmp == "xsjxjb"){
		if(null!=curr_row)
		{
			jxjlb = curr_row.cells[0].getElementsByTagName("input")[1].value;
		}
	}
//	if(tmp == "gwxxb"){
//		alert("return before");
//		return false;
//	}
//	if(tmp == "gwxxb"){
//		alert("return before2222222222");
//		return false;
//	}
//	alert(url);
	
	if (doType == "del") {
		var RowsStr="!!SplitOneSplit!!";   										//定义连接字符串
			
		if (Rows.length==0){  													//原先判断curr_row == null
			alert("请选择要删除的数据！\n（单击相应的行）");
			return false;
		} else {
			var msg = "确定要删除选择的数据吗？";
			for (i=0; i<Rows.length; i++){ 										//连接字符串
				if(tmp=="wjcfb" 
					&& Rows[i].getElementsByTagName("input")[1].value=="disabled"){
					alert("选中的数据中存在已审核或学生\n已确认数据，无法删除请确认！");
					return false;
				}
    			RowsStr+=Rows[i].getElementsByTagName("input")[0].value+"!!SplitOneSplit!!";
			}
			if(tmp == "gwxxb"){
				msg = "删除岗位将删除该岗位上的所有学生信息\n确定删除吗？";
			}
			if (confirm(msg)) {
				
//				pkValue = curr_row.getElementsByTagName("input")[0].value;
				pkValue = RowsStr; 												//建立主键
				url += pkValue;				
				if(tmp == "gdnz_lpxxb_bx"){
					url=url.replace("realTable=gdnz_lpxxb_bx","realTable=gdnz_lpxxb");
					url+="&type=0";					
				}
				if(tmp== "gdnz_lpxxb_sh"){
					url=url.replace("realTable=gdnz_lpxxb_sh","realTable=gdnz_lpxxb");
					url+="&type=1";
				}
				if(tmp == "zxdk_fkxx"){
					url = "/xgxt/zxdk_fkxx.do?doType=add&pk=";
					url += curr_row.getElementsByTagName("input")[0].value;
					url += "&doType2=";
					url += doType;
				}				
				if(tmp== "xsjxbxb" || tmp== "xsjxbzb" || tmp== "xsjxcfb"){
					url="/xgxt/modiArmy.do?doType=del&realTable=";
					url+=tmp;
					url+="&pk=";
					url+=curr_row.getElementsByTagName("input")[0].value;
					url+="&from=";
					url+=document.forms[0].act.value;
					if(tmp=="xsjxbxb"){
						url+="&pk2=";
						url+=curr_row.getElementsByTagName("input")[1].value;
					}
				}
				refreshForm(url);
				return true;
			} else {
				return false;
			}
		}
		return;
	}
	if (doType == "modi" && curr_row == null) {
		alert("请选择要修改的数据！\n（单击相应的行）");
		return false;
	}
	if (doType != "add") {
		pkValue = curr_row.getElementsByTagName("input")[0].value;
	    if(tmp=="ssxxb"&&xxdm=="11407"){//西北第二民族学院
	      pkValue = curr_row.getElementsByTagName("input")[1].value;
	    }
	}
	if(doType == "add" && tmp == "xsjxjb"){
		location.href = "prise_apply.do?tab=qtjxj";
		return false;
	}
	url += pkValue;
	if(doType == "view"){
		//	重庆科技学院
		if(xxdm == "11551" && tmp=="xsgwxxb"){
			url += "&xh=";
			url += curr_row.cells[4].innerText;
		}		
	}
	if (tmp == "xspxxxb" || tmp == "rdjjfzxxb" || tmp == "ybdyxxb" || tmp == "dyxxb" || tmp == "yxjxhjb" || tmp == "zhszcp" || tmp == "nsepxxb" || tmp == "xlcsjgb" || tmp == "xytbgxxsxxb" || tmp == "xshsxfb" || tmp == "xsrychb" || tmp == "stb" || tmp == "rdsqb" || tmp == "sxhbb" || tmp == "xsbzxxb" || tmp == "jxjgb" || tmp == "xsgbxxb" || tmp == "zyzfwdjb" || tmp == "sthdxxb"  || tmp == "xszffb" || tmp == "hcyhkffb" || tmp == "xhffb" || tmp == "hcyhkczb" || tmp == "mrzbjlb" || tmp == "yzzbhzb") {
		w = 700;
		h = 520;
	} else if (tmp == "lpxxb" ||tmp == "jszhkpb" || tmp == "knsshhdxxb" || tmp == "wjcfb" || tmp == "wjcflsb" || tmp == "xszbbb" || tmp == "hcyhkbbb" || tmp == "yktbbb" || tmp == "xhbbb" || tmp == "xsbxb") {
		w = 600;
		h = 500;
		//上海工程技术大学
		if(xxdm!="" && xxdm=="10856" && tmp=="lpxxb"){
			w = 800;
			h = 600;
		}
	}else if( tmp == "xszsxxb"){
		w = 800;
		h = 520;	
	} else if (tmp == "xssztzb") {
		w = 600;
		h = 600;
	} else if (tmp == "knssqb" || tmp == "xxzxdksqb" || tmp == "xsjxjb" ) {
		w = 650;
		h = 550;
	} else if (tmp == "gjzxdksqb" || tmp == "ssydxxb") {
		w = 700;
		h = 600;
	} else  if (tmp == "sjb") {
		w = 550;
		h = 450;
		url += "&buttonxsbj=";
		url += document.forms[0].buttonxsbj.value;
	} else if (tmp == "xxb" || tmp == "ssxxb"|| tmp == "hcccb"|| tmp == "qcccb") {
		w = 550;
		h = 450;
	} else if(tmp =="ssxxb"){
	   	w = 700;
		h = 550;	   
	}else if (tmp == "sjstb") {
		w = 400;
		h = 550;
		url += "&sjlsh=";
		url += document.forms[0].sjlsh.value;
	} else if (tmp == "dwjlsqb" && doType == "modi") {
		//location = "comm_apply.do";
		url= url.replace("/xgxt/modiData.do","/xgxt/comm_apply.do");//"/xgxt/comm_apply.do?"+url.substr(19,url.length-18);
		//alert(url);
		document.forms[0].action = url;
		document.forms[0].submit();
		return false;
	} else if (tmp == "dwjlsqb" && doType == "add"){
		location = "comm_apply.do";
		return false;
	} else if (tmp == "dwjlsqb" && doType == "view"){
		url = url.replace("/xgxt/modiData.do","/xgxt/comm_apply.do");
		url+="&Type=view&dwjlmc=";
		url+=curr_row.cells[5].innerText;
		url+="&xn=";
		url+=curr_row.cells[1].innerText
		url+="&nd="+curr_row.cells[0].innerText
		url+="&xq="+curr_row.cells[2].innerText;
		w = 800;
		h = 600;
	} else if (tmp == "sztz_hdcjsqb" && doType == "modi") {
		//location = "comm_apply.do";
		url= url.replace("/xgxt/modiData.do","/xgxt/sztz_join_apply.do");//"/xgxt/comm_apply.do?"+url.substr(19,url.length-18);
		//alert(url);
		document.forms[0].action = url;
		document.forms[0].submit();
		return false;
	} else if (tmp == "sztz_hdcjsqb" && doType == "view") {
		//location = "comm_apply.do";
		url= url.replace("/xgxt/modiData.do","/xgxt/sztz_join_apply.do");
		//alert(url);
		//document.forms[0].action = url;
		//document.forms[0].submit();
		w = 800;
		h = 600;	
	} else if (tmp == "sztz_hdcjsqb" && doType == "add"){
		location = "sztz_join_apply.do";
		return false;
	} else if (tmp == "xsgwxxb" && doType == "modi") {			
		if(xxdm!="" && xxdm=="11417"){//北京联合大学
			var xh=curr_row.cells[4].innerText;
			url="/xgxt/post_stu_apply.do?type=modi&xh="+xh;
		}else if(xxdm!="" && xxdm=="11551"){//重庆科技学院
			var xh=curr_row.cells[4].innerText;
			url= url.replace("/xgxt/modiData.do","/xgxt/post_stu_apply.do");
			url += "&xh=";
			url += xh;
		}else{
			url= url.replace("/xgxt/modiData.do","/xgxt/post_stu_apply.do");
		}		
		document.forms[0].action = url;
		document.forms[0].submit();
		return false;
	}else if (tmp == "lsbzb") {
		url= url.replace("/xgxt/modiData.do","/xgxt/stu_lsbz_info.do");
	} else if (tmp == "zjys_dkxt") {
		url= url.replace("/xgxt/modiData.do","/xgxt/zjys_dkxx_info.do");
	} else if (tmp == "xsgwxxb" && doType == "add"){
		location = "post_stu_apply.do";
		return false;
	} else if (tmp == "jfhbb") {
		location = "work_outlay_modi.do?pk=" + document.forms[0].pk.value + "&pkValue=" + curr_row.getElementsByTagName("input")[0].value;
		return false;
	} else if (tmp == "dwjlxxb"){
		url=url.replace("/xgxt/modiData.do","/xgxt/comm_pub.do");
		//alert(url);
		w = 800;
		h = 600;
	} else if (tmp == "sztz_xm_xxfbb" && dbl == "yes"){
		url=url.replace("/xgxt/modiData.do","/xgxt/tzhd_pub.do");
		w = 850;
		h = 650;	
	} else if (tmp == "bks_xsszjbxx" && doType == "modi"){
		w=550;
		h=350;	
	} else if (tmp == "zxdk_htxx"){
		w=700;
		h=530;
	} else if (tmp == "zxdk_xsjbxx"){
		w=750;
		h=500;
	} else if( tmp == "xsjxjzxjsqb" ){
		w = 800;
		h = 600;
		var xmlb = document.forms[0].act.value;
		if(xmlb == "gjjxj" ){
			if( doType == "add" ){
			url = url.replace("modiData.do","gjjxjzxj.do");
			url += "&jxjlbType=gjjzxj&titName=gjjxj";
			url = url.replace("doType=");
			} else if(doType == "modi"){
				url = url.replace("modiData.do","modiData2.do");
				url += "&jxjlbType=gjjzxj&titName=gjjxj";
			}
		} else if(xmlb =="gjzxj" ){
			if( doType == "add" ){
			url = url.replace("modiData.do","gjjxjzxj.do");
			url += "&jxjlbType=gjjzxj&titName=gjzxj";
			url = url.replace("doType=");
			} else if(doType == "modi"){
				url = url.replace("modiData.do","modiData2.do");
				url += "&jxjlbType=gjjzxj&titName=gjzxj";
			}
		} else if(xmlb == "szfjxj"){
			if( doType == "add" ){
			url = url.replace("modiData.do","szfjxjzxj.do");
			url += "&jxjlbType=szfjzxj&titName=szfjxj";
			url = url.replace("doType=");
			} else if(doType == "modi"){
				url = url.replace("modiData.do","modiData2.do");
				url += "&jxjlbType=szfjzxj&titName=szfjxj";
			}
		} else if(xmlb == "szfzxj"){
			if( doType == "add" ){
			url = url.replace("modiData.do","szfjxjzxj.do");
			url += "&jxjlbType=szfjzxj&titName=szfzxj";
			url = url.replace("doType=");
			} else if(doType == "modi"){
				url = url.replace("modiData.do","modiData2.do");
				url += "&jxjlbType=szfjzxj&titName=szfzxj";
			}
		}
	} else if( tmp =="zxdk_fkxx" ){
		w = 550;
		h = 350;
		url = "/xgxt/zxdk_fkxx.do?doType=add&pk=";
		if(doType != "add"){
			url += curr_row.getElementsByTagName("input")[0].value;
			url += "&doType2=";
			if(doType=="view"){
				url += "modi";
			}else{
				url += doType;
			}
		}
	} else if( tmp =="fsbzb" ){
		w = 580;
		h = 300;
		url = "/xgxt/fsbz.do?doType=add&pk=";
		if(doType != "add"){
			url += curr_row.getElementsByTagName("input")[0].value;
			url += "&doType2=";
			if(doType=="view"){
				url += "modi";
			}else{
				url += doType;
			}
		}
	} else if( tmp =="xpjjzxdksqb" ){
		w = 800;
		h = 600;
		if( doType == "add" ){
		url = url.replace("modiData.do","xpjjsq.do");
		url = url.replace("doType=");
		} else if(doType == "modi"){
			url = url.replace("modiData.do","modiData2.do");
		}
	} else if( tmp == "xsbzb" ){
		
		w = 800;
		h = 600;
		var xmlb = document.forms[0].act.value;
		if(xmlb == "xfbz"){
			if( doType == "add" ){
			url = url.replace("modiData.do","xfbz.do");
			url = url.replace("doType=");
			} else if(doType == "modi"){
				url = url.replace("modiData.do","modiData2.do");
				url += "&bzlb=xfbz";
		    }
		} else if(xmlb == "lsknbz"){
			if( doType == "add" ){
			url = url.replace("modiData.do","knbz.do");
			url = url.replace("doType=");
			} else if(doType == "modi"){
				url = url.replace("modiData.do","modiData2.do");
				url += "&bzlb=lsknbz";
			}
		} else if(xmlb == "xndxj"){
			//alert("jjjfjdjjdjfjjdjfjdf");
			if(doType == "add"){
				url = url.replace("modiData.do","xndxjsq.do");
				url = url.replace("doType=")
			} else if(doType == "modi"){
				url = url.replace("modiData.do","modiData2.do");
				url += "&bzlb=xndxj"
				}
			}
	} else if( tmp == "wszxjsqb"){
		var wszxjdm = document.forms[0].zxjdm.value;
		var wszxjmc = "";
		w = 800;
		h = 600;
		if( doType == "add" ){
		url = url.replace("modiData.do","wszxj.do");
		url = url.replace("doType=");
		} else if(doType == "modi"){
			url = url.replace("modiData.do","modiData2.do");
			if( wszxjdm == "" || wszxjdm == " " ){
				wszxjmc = curr_row.cells[0].innerText;
				url += "&wszxjmc="+wszxjmc;
			}else{
				url += ("&wszxjdm="+wszxjdm);
			}
		}
	} else if (tmp == "xszxbzb"){
		w=650;
		h=600;
	} else if (tmp == "gwxxb" && dbl == "yes"){
		url=url.replace("/xgxt/modiData.do","/xgxt/comm_pub.do")
		w = 800;
		h = 600;
	} else if (tmp == "lstdb"){
		w = 800;
		h = 600;
	} else if (tmp == "kkqkb"){
		w = 600;
		h = 350;
	} else if (tmp == "xfcjb"){
		w = 600;
		h = 350;
		//alert(xxdm);
		if(xxdm!="" && xxdm=="14136"){
			w = 700;
			h = 600;
			if(doType=="modi" || doType=="view"){
				url="viewXfcj.do?xh="+curr_row.cells[3].innerText
				+"&xn="+curr_row.cells[4].innerText;
			}
		}
	} else if (tmp == "xfhjb"){
		w = 800;
		h = 600;
		if(xxdm!="" && xxdm=="14136"){
			w = 700;
			h = 600;
			if(doType=="modi" || doType=="view"){
				url="viewXfhj.do?xh="+curr_row.cells[3].innerText
				+"&nd="+curr_row.cells[0].innerText;
			}
		}
	} else if (tmp == "gywxglb"){
		w = 600;
		h = 460;
	}else if(tmp == "gywsjcb" && xxdm=="1049701" ){
	    w = 800;
		h = 700;
	}else if (tmp == "gywsjcb" || tmp == "wmqspbb"){
		w = 750;
		h = 550;
	}else if (tmp == "kccjcpb"){
		w = 800;
		h = 600;
	} else if (tmp == "zsjlb"){
		w = 700;
		h = 600;
	} else if (tmp == "xsxlcs"){
		url = url.replace("/xgxt/modiData.do","/xgxt/xlcsstfx.do");
		w = 800;
		h = 600;
	} else if (tmp == "xsjxhjb" || tmp == "bjjxhjb" ){
		var re=/&/g;
		var urlV = url.replace(re,"!!-!!");
		url = url + "&urlV=" + urlV;
		w = 600;
		h = 460;
	}else if (tmp == "tsqtxsdjb"){
		w = 900;
		h = 650;
	}else if (tmp == "bjxzryb"){
		w = 800;
		h = 650;
	}else if(tmp == "gdnz_lpxxb_bx" || tmp == "gdnz_lpxxb_sh"){
	 w=650;
	 h=700;
	} else if (tmp=='wjcfb') {
		w=650;
	 	h=550;
	}
	 if (tmp=='xsrychb') {
	 	if (doType=='add') {
		 	refreshForm('credit_apply.do');
			return;		
	 	} else {
			w=700;
		 	h=650; 	 		
	 	}
	}
	
	if(tmp == "xsjxjb" && doType == "modi" && jxjlb=="外"){
		viewMore2("modi");
		return false;
	}
	else if(tmp== "xsjxbxb" || tmp== "xsjxbzb" || tmp== "xsjxcfb"){		
		url=url.replace("modiData.do","showArmy.do");		
		url+=pkValue;
		url+="&pk=";
		url+=document.forms[0].pk.value;
		if(doType=="modi"){
			url+="&rpkValue=";
			url+=curr_row.getElementsByTagName("input")[0].value;
			if(tmp=="xsjxbxb"){
				url+="!!";
				url+=curr_row.getElementsByTagName("input")[1].value;							
			}
		}
		url+="&doType=";
		url+=doType;				
		w=600;
		h=400;	
		if(tmp=="xsjxcfb"){
			w=700;
			h=500;
		}
	}
	if (tmp=='wjcfb') {
		w=650;
		h=560;
		//浙江理工的违纪处分
		if ('10338'==xxdm && 'wjcfb'==tmp){
			w=800;
			h=600;
		} else if ('11078'==xxdm && 'wjcfb'==tmp) {
			w=700;
			h=600;
		}
		showTopWin(url,w,h);
		return;		
	}
	if(xxdm!="" && xxdm=="10690" && tmp=="xsgwxxb"){
			h = 600;
		}	
	if ("10355"==xxdm&&tmp=='xsrychb') {
		w=750;
		h=700;
	}
	w=800;
	h=600;
	showTopWin(url, w, h);
}

function sztzViewMore(doType){
	var pkValue = "";
	var url = "/xgxt/sztz_join_apply.do?realTable=";
	var tmp = document.forms[0].realTable.value;
	var w = 550;
	var h = 450;
	url += tmp;
	url += "&doType=";
	url += doType;
	url += "&tableName=";
	url += document.forms[0].tableName.value;
	url += "&pk=";
	url += document.forms[0].pk.value;
	url += "&from=";
	url += document.forms[0].act.value;
	url += "&pkValue=";
	if (doType == "del") {
		if (curr_row == null) {
			alert("请选择要删除的数据！\n（单击相应的行）");
			return false;
		} else {
			if (confirm("确定要删除该行数据吗？")) {
				
				if ((tmp == "sztz_hdcjsqb")||(tmp == "sztz_xfsbb")||(tmp == "sztz_xscjxxb")){
				url = url.replace("/xgxt/sztz_join_apply.do","/xgxt/sztz_modiData.do");				
				}				
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;	
				//alert(url);
				refreshForm(url);
				return true;
			} else {
				return false;
			}
		}
		return;
	}
	
	if (doType == "modi" && curr_row == null) {
		alert("请选择要修改的数据！\n（单击相应的行）");
		return false;
	}
	if (doType != "add") {
		pkValue = curr_row.getElementsByTagName("input")[0].value;
	}
	url += pkValue;	
	if (tmp == "sztz_hdcjsqb" && doType == "modi") {
		url = url.replace("/xgxt/sztz_join_apply.do","/xgxt/sztz_modiData.do");
		w = 800;
		h = 600;
	} else if (tmp == "sztz_hdcjsqb" && doType == "viewOne") {
		url = url.replace("/xgxt/sztz_join_apply.do","/xgxt/sztz_modiData.do");
		url=url.replace("doType=viewOne","doType=modi");
	    w = 800;
		h = 600;
	} else if (tmp == "sztz_xscjxxb" && doType == "viewOne") {
		url=url.replace("/xgxt/sztz_join_apply.do","/xgxt/sztz_modi_data.do");
	    w = 800;
		h = 600;
	} else if (tmp == "sztz_xscjxxb" && doType == "modi"){
		url = url.replace("/xgxt/sztz_join_apply.do","/xgxt/sztz_modi_data.do");
		url=url.replace("doType=modi","doType=viewOne");
		w = 800;
		h = 600;			
	} else if (tmp == "sztz_hdcjsqb" && doType == "add"){
		w = 800;
		h = 600;
	}else if(tmp == "sztz_xscjxxb" && doType == "add"){
		url=url.replace("/xgxt/sztz_join_apply.do","/xgxt/sztz_modi_data.do");
		w = 800;
		h = 600;
	}else if(tmp == "sztz_xfsbb" && doType == "add"){
		url=url.replace("/xgxt/sztz_join_apply.do","/xgxt/sztz_xf_sb.do");
		w = 800;
		h = 600;
	}else if(tmp == "sztz_xfsbb" && doType == "viewOne"){
		url=url.replace("/xgxt/sztz_join_apply.do","/xgxt/sztz_modiData.do");
		url=url.replace("doType=viewOne","doType=modi");
		w = 800;
		h = 600;
	}else if(tmp == "sztz_xfsbb" && doType == "modi"){
		url=url.replace("/xgxt/sztz_join_apply.do","/xgxt/sztz_modiData.do");
		w = 800;
		h = 600;
	}else if(tmp == "sztz_xm_xxfbb"){
		url=url.replace("/xgxt/sztz_join_apply.do","/xgxt/sztz_modiData.do");
		w = 800;
		h = 600;
	}
	showTopWin(url, w, h);
}

function viewMore2(doType){
	var pkValue = "";
	var url = "/xgxt/modiData2.do?realTable=";
	var tmp = document.forms[0].realTable.value;
	var w = 550;
	var h = 450;
	url += tmp;
	url += "&doType=";
	url += doType;
	url += "&tableName=";
	url += document.forms[0].tableName.value;
	url += "&pk=";
	url += document.forms[0].pk.value;
	url += "&from=";
	url += document.forms[0].act.value;
	url += "&pkValue=";
	var xxdm = document.getElementById('xxdm').value;
	if (doType == "del") {
		if (curr_row == null) {
			alert("请选择要删除的数据！\n（单击相应的行）");
			return false;
		} else {
			if (confirm("确定要删除该行数据吗？")) {
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;
				refreshForm(url);
				return true;
			} else {
				return false;
			}
		}
		return;
	}
	if(doType == "modi"){
		if(curr_row == null ){
			alert("请选要修改的记录！\n单击一行记录即可");
			return false;
		} else {
			var tableName = document.getElementById("realTable").value;
			if( tableName == "xsjxjb" ){
				var pk = document.getElementById("pk").value;
				var pkValue = (curr_row.cells[0].getElementsByTagName("input"))[0].value;
				var xh = curr_row.cells[2].innerText.replace(/(^\s*)|(\s*$)/g, "");
				url = "prise_apply.do?act=modi&pk="+pk+"&pkValue="+pkValue+"&xh="+xh+"&tab=qtjxj";
				if (xxdm=='12764') {
					url = 'pjpy_scjz_jxjmodi.do?act=view&pkValue='+pkValue;
				}
				h=630;
				w=800;
				showTopWin(url,w,h);
			}
		}
	}
	
}
function xsxxSave(doType){
	var ssbh=document.forms[0].ssbh.value;
	var cwh=document.forms[0].cwh.value
	if(ssbh!=""){
		if(cwh==""){
			alert("床位号不能为空！");
			return false;
		}
	}
	if(cwh!=""){
		if(ssbh==""){
			alert("宿舍编号不能为空！");
			return false;
		}
	}
	var url ="/xgxt/modiData.do?realTable=";
	url += document.getElementById("realTable").value;
	url += "&tableName="; 
	url += document.getElementById("tableName").value;
	url += "&doType=";
	url += doType;
	url += "&pk=";
	url += document.getElementById("pk").value;
	url += "&xh=";
	url += document.getElementById("xh").value;
	url +="&pkValue=";
	url += document.getElementById("xh").value;
	url += "&from=";
	url += document.forms[0].act.value;
	
	document.forms[0].action =url;
	document.forms[0].submit();
	alert("保存成功！");
	window.close();	
	window.dialogArguments.document.all("search_go").click();
	
}

function chkAssisOne(url, act) {
	if (curr_row == null) {
		return false;
	} else {
		url += "?actDo=";
		url += act;
		url +="&act=";
		url += document.getElementById("act").value;
		url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;	
		url += "&tName=";
		url += document.getElementById("realTable").value;
		if(url.search('assisChkOne')){
			url += "&xydm="+document.getElementById("xy").value;
		}
		
		showTopWin(url, 750, 550);
		return true;
	}
}
function dateFormatChgToMonth(obj) {
	if (obj.tagName.toLowerCase() == "input") {
		obj.value = replaceChar(obj.value, "-", "").substring(0, 6);
	}
}
function delPriseCondi() {
	if (curr_row == null) {
		alert("请选择要删除的条件！");
		return false;
	} else {
		if (confirm('确认要删除选择的数据吗？')) {
		var url = "/xgxt/prise_condition.do?pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
		document.forms[0].go.value = "del";
		refreshForm(url);
		}
	}
}

function stuInfoWin(objTr,isDialog) {
	var xxdm = document.getElementById("xxdm").value;
	curr_row = objTr;
	var url = "stu_info_details.do?xh=" + curr_row.cells[1].innerText;
	var width=840;
	var height = 600;
	var left = (screen.width/2) - width/2;
	var top = (screen.height/2) - height/2;
//	width=900;
	var styleStr = 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=yes,width='+width+',height='+height+',left='+left+',top='+top+',screenX='+left+',screenY='+top;
	
	window.open(url,"msgWindow", styleStr);
	
}

function saveTrainConf(sqB, sqE, xn, nd, url) {
	var xxdm = "";
	if($("xxdm")){
		xxdm=document.forms[0].xxdm.value;
	}
	if(xxdm=="10856"){
		refreshForm(url);
		return true;
	}
	var a1 = document.all(sqB + "1").value;
	var a2 = (document.all(sqB + "2").value != "" && document.all(sqB + "2").value.length != 2) ? ("0" + document.all(sqB + "2").value) : document.all(sqB + "2").value;
	var a3 = (document.all(sqB + "3").value != "" && document.all(sqB + "3").value.length != 2) ? ("0" + document.all(sqB + "3").value) : document.all(sqB + "3").value;
	var a4 = (document.all(sqB + "4").value != "" && document.all(sqB + "4").value.length != 2) ? ("0" + document.all(sqB + "4").value) : document.all(sqB + "4").value;
	var b1 = document.all(sqE + "1").value;
	var b2 = (document.all(sqE + "2").value != "" && document.all(sqE + "2").value.length != 2) ? ("0" + document.all(sqE + "2").value) : document.all(sqE + "2").value;
	var b3 = (document.all(sqE + "3").value != "" && document.all(sqE + "3").value.length != 2) ? ("0" + document.all(sqE + "3").value) : document.all(sqE + "3").value;
	var b4 = (document.all(sqE + "4").value != "" && document.all(sqE + "4").value.length != 2) ? ("0" + document.all(sqE + "4").value) : document.all(sqE + "4").value;
	a1 = (a1 == "") ? "1900-01-01" : a1;
	a2 = (a2 == "" || isNaN(parseInt(a2)) || parseInt(a2) > 24 || parseInt(a2) < 0) ? "00" : a2;
	a3 = (a3 == "" || isNaN(parseInt(a3)) || parseInt(a3) > 60 || parseInt(a3) < 0) ? "00" : a3;
	a4 = (a4 == "" || isNaN(parseInt(a4)) || parseInt(a4) > 60 || parseInt(a4) < 0) ? "00" : a4;
	b1 = (b1 == "") ? "1900-01-01" : b1;
	b2 = (b2 == "" || isNaN(parseInt(b2)) || parseInt(b2) > 24 || parseInt(b2) < 0) ? "00" : b2;
	b3 = (b3 == "" || isNaN(parseInt(b3)) || parseInt(b3) > 60 || parseInt(b3) < 0) ? "00" : b3;
	b4 = (b4 == "" || isNaN(parseInt(b4)) || parseInt(b4) > 60 || parseInt(b4) < 0) ? "00" : b4;
	var a = a1 + a2 + a3 + a4;
	var b = b1 + b2 + b3 + b4;
	if($('xwkssqsj')){
		var name1="xwkssqsj";
		var name2="xwjssqsj";
		var q1 = document.all(name1 + "1").value;
		var q2 = (document.all(name1 + "2").value != "" && document.all(name1 + "2").value.length != 2) ? ("0" + document.all(name1 + "2").value) : document.all(name1 + "2").value;
		var q3 = (document.all(name1 + "3").value != "" && document.all(name1 + "3").value.length != 2) ? ("0" + document.all(name1 + "3").value) : document.all(name1 + "3").value;
		var q4 = (document.all(name1 + "4").value != "" && document.all(name1 + "4").value.length != 2) ? ("0" + document.all(name1 + "4").value) : document.all(name1 + "4").value;
		var c1 = document.all(name2 + "1").value;
		var c2 = (document.all(name2 + "2").value != "" && document.all(name2 + "2").value.length != 2) ? ("0" + document.all(name2 + "2").value) : document.all(name2 + "2").value;
		var c3 = (document.all(name2 + "3").value != "" && document.all(name2 + "3").value.length != 2) ? ("0" + document.all(name2 + "3").value) : document.all(name2 + "3").value;
		var c4 = (document.all(name2 + "4").value != "" && document.all(name2 + "4").value.length != 2) ? ("0" + document.all(name2 + "4").value) : document.all(name2 + "4").value;
		q1 = (q1 == "") ? "1900-01-01" : q1;
		q2 = (q2 == "" || isNaN(parseInt(q2)) || parseInt(q2) > 24 || parseInt(q2) < 0) ? "00" : q2;
		q3 = (q3 == "" || isNaN(parseInt(q3)) || parseInt(q3) > 60 || parseInt(q3) < 0) ? "00" : q3;
		q4 = (q4 == "" || isNaN(parseInt(q4)) || parseInt(q4) > 60 || parseInt(q4) < 0) ? "00" : q4;
		c1 = (c1 == "") ? "1900-01-01" : c1;
		c2 = (c2 == "" || isNaN(parseInt(c2)) || parseInt(c2) > 24 || parseInt(c2) < 0) ? "00" : c2;
		c3 = (c3 == "" || isNaN(parseInt(c3)) || parseInt(c3) > 60 || parseInt(c3) < 0) ? "00" : c3;
		c4 = (c4 == "" || isNaN(parseInt(c4)) || parseInt(c4) > 60 || parseInt(c4) < 0) ? "00" : c4;
		var q = q1 + q2 + q3 + q4;
		var c = c1 + c2 + c3 + c4;
		if(q>c){
			alert("校外学生申请岗位开始时间晚于申请结束时间，无法保存！");
			return false;
		}else{
			document.getElementById(name1).value = replaceChar(q,'-','');
			document.getElementById(name2).value = replaceChar(c,'-','');
		}
	}
	if($('shkssj')){
		var name1="shkssj";
		var name2="shjssj";
		var q1 = document.all(name1 + "1").value;
		var q2 = (document.all(name1 + "2").value != "" && document.all(name1 + "2").value.length != 2) ? ("0" + document.all(name1 + "2").value) : document.all(name1 + "2").value;
		var q3 = (document.all(name1 + "3").value != "" && document.all(name1 + "3").value.length != 2) ? ("0" + document.all(name1 + "3").value) : document.all(name1 + "3").value;
		var q4 = (document.all(name1 + "4").value != "" && document.all(name1 + "4").value.length != 2) ? ("0" + document.all(name1 + "4").value) : document.all(name1 + "4").value;
		var c1 = document.all(name2 + "1").value;
		var c2 = (document.all(name2 + "2").value != "" && document.all(name2 + "2").value.length != 2) ? ("0" + document.all(name2 + "2").value) : document.all(name2 + "2").value;
		var c3 = (document.all(name2 + "3").value != "" && document.all(name2 + "3").value.length != 2) ? ("0" + document.all(name2 + "3").value) : document.all(name2 + "3").value;
		var c4 = (document.all(name2 + "4").value != "" && document.all(name2 + "4").value.length != 2) ? ("0" + document.all(name2 + "4").value) : document.all(name2 + "4").value;
		q1 = (q1 == "") ? "1900-01-01" : q1;
		q2 = (q2 == "" || isNaN(parseInt(q2)) || parseInt(q2) > 24 || parseInt(q2) < 0) ? "00" : q2;
		q3 = (q3 == "" || isNaN(parseInt(q3)) || parseInt(q3) > 60 || parseInt(q3) < 0) ? "00" : q3;
		q4 = (q4 == "" || isNaN(parseInt(q4)) || parseInt(q4) > 60 || parseInt(q4) < 0) ? "00" : q4;
		c1 = (c1 == "") ? "1900-01-01" : c1;
		c2 = (c2 == "" || isNaN(parseInt(c2)) || parseInt(c2) > 24 || parseInt(c2) < 0) ? "00" : c2;
		c3 = (c3 == "" || isNaN(parseInt(c3)) || parseInt(c3) > 60 || parseInt(c3) < 0) ? "00" : c3;
		c4 = (c4 == "" || isNaN(parseInt(c4)) || parseInt(c4) > 60 || parseInt(c4) < 0) ? "00" : c4;
		var q = q1 + q2 + q3 + q4;
		var c = c1 + c2 + c3 + c4;
		if(q>c){
			alert("用人单位审核开始时间晚于用人单位审核截至时间，无法保存！");
			return false;
		}else{
			document.getElementById(name1).value = replaceChar(q,'-','');
			document.getElementById(name2).value = replaceChar(c,'-','');
		}
	}
	
	if (a > b) {
		alert("开始时间晚于结束时间，无法保存！");
		return false;
	} else {
		document.getElementById(sqB).value = replaceChar(a, "-", "");
		document.getElementById(sqE).value = replaceChar(b, "-", "");
		if(xxdm!="" && xxdm=="11417"){
			refreshForm(url);
		}else{
		if($('xn')){
			if (checkXnNd(xn, nd)) {
				if (url != null) {
					refreshForm(url);
				}
				return true;
			} else {
				return false;
			}
		}else{
			if (url != null) {
				refreshForm(url);
			}
			return true;
		}
		}
	}
}
function saveShgcConf(sqB, sqE,url) {
	
	var a1 = document.all(sqB + "1").value;
	var a2 = (document.all(sqB + "2").value != "" && document.all(sqB + "2").value.length != 2) ? ("0" + document.all(sqB + "2").value) : document.all(sqB + "2").value;
	var a3 = (document.all(sqB + "3").value != "" && document.all(sqB + "3").value.length != 2) ? ("0" + document.all(sqB + "3").value) : document.all(sqB + "3").value;
	var a4 = (document.all(sqB + "4").value != "" && document.all(sqB + "4").value.length != 2) ? ("0" + document.all(sqB + "4").value) : document.all(sqB + "4").value;
	var b1 = document.all(sqE + "1").value;
	var b2 = (document.all(sqE + "2").value != "" && document.all(sqE + "2").value.length != 2) ? ("0" + document.all(sqE + "2").value) : document.all(sqE + "2").value;
	var b3 = (document.all(sqE + "3").value != "" && document.all(sqE + "3").value.length != 2) ? ("0" + document.all(sqE + "3").value) : document.all(sqE + "3").value;
	var b4 = (document.all(sqE + "4").value != "" && document.all(sqE + "4").value.length != 2) ? ("0" + document.all(sqE + "4").value) : document.all(sqE + "4").value;
	a1 = (a1 == "") ? "1900-01-01" : a1;
	a2 = (a2 == "" || isNaN(parseInt(a2)) || parseInt(a2) > 24 || parseInt(a2) < 0) ? "00" : a2;
	a3 = (a3 == "" || isNaN(parseInt(a3)) || parseInt(a3) > 60 || parseInt(a3) < 0) ? "00" : a3;
	a4 = (a4 == "" || isNaN(parseInt(a4)) || parseInt(a4) > 60 || parseInt(a4) < 0) ? "00" : a4;
	b1 = (b1 == "") ? "1900-01-01" : b1;
	b2 = (b2 == "" || isNaN(parseInt(b2)) || parseInt(b2) > 24 || parseInt(b2) < 0) ? "00" : b2;
	b3 = (b3 == "" || isNaN(parseInt(b3)) || parseInt(b3) > 60 || parseInt(b3) < 0) ? "00" : b3;
	b4 = (b4 == "" || isNaN(parseInt(b4)) || parseInt(b4) > 60 || parseInt(b4) < 0) ? "00" : b4;
	var a = a1 + a2 + a3 + a4;
	var b = b1 + b2 + b3 + b4
	
	if (a > b) {
		alert("开始时间晚于结束时间，无法保存！");
		return false;
	} else {
		document.getElementById(sqB).value = replaceChar(a, "-", "");
		document.getElementById(sqE).value = replaceChar(b, "-", "");
		if (url != null) {
				refreshForm(url);
			}
			return true;
	}
}
function chgCsxmdm() {
	document.forms[0].action = "/xgxt/modiData.do?doType=add&realTable=xlcsjgb&tableName=view_xlcsjg&pk=xh-csxmdm-cssj&xm=" + document.forms[0].xm.value + "&xb=" + document.forms[0].xb.value + "&nj=" + document.forms[0].nj.value + "&xymc=" + document.forms[0].xy.value + "&zymc=" + document.forms[0].zy.value + "&bjmc=" + document.forms[0].bj.value;
	document.forms[0].submit();
}
function chgJxjxmdm() {
	document.forms[0].action = "/xgxt/modiData.do?realTable=xsjxjb&tableName=view_xsjxjb&pk=xn||nd||jxjdm||xh&xm=" + document.forms[0].xm.value + "&xb=" + document.forms[0].xb.value + "&nj=" + document.forms[0].nj.value + "&xymc=" + document.forms[0].xy.value + "&zymc=" + document.forms[0].zy.value + "&bjmc=" + document.forms[0].bj.value;
	document.forms[0].submit();
}
function subloadPost() {
	var xxdm = "";
	if($("xxdm")){
		xxdm=document.forms[0].xxdm.value;
	}
	if (document.forms[0].jcfs.value == "h") {
		if(xxdm!="" && xxdm=="11417"){
			gzsjDw.innerText = "小时";
		}
		jybcbzDw.innerText = "元/小时";
	}
	if (document.forms[0].jcfs.value == "d") {
		if(xxdm!="" && xxdm=="11417"){
			gzsjDw.innerText = "天";
		}
		jybcbzDw.innerText = "元/天";
	}
	if (document.forms[0].jcfs.value == "w") {
		if(xxdm!="" && xxdm =="11417"){
			gzsjDw.innerText = "周";
		}
		jybcbzDw.innerText = "元/周";
	}
	if (document.forms[0].jcfs.value == "m") {
		if(xxdm!="" && xxdm =="11417"){
			gzsjDw.innerText = "月";
		}
		jybcbzDw.innerText = "元/月";
	}
	document.getElementById("jybcbz").readOnly= false;
	if (document.forms[0].jcfs.value == "n") {
		document.getElementById("jybcbz").readOnly= true;
		document.getElementById("jybcbz").value="0";
		jybcbzDw.innerText = "没有报酬";
	} else{
		document.getElementById("jybcbz").value="";
	}	
}

function dataDoSavePubGw(url, pkFields) {	
	//var zjf = document.getElementById("zjf").value;
	var xyrs = document.getElementById("xyrs").value;
	var xyknsrs = document.getElementById("xyknsrs").value;
	var jybcbz = document.getElementById("jybcbz").value;
	var gzsj = document.getElementById("gzsj").value;
	var eles = pkFields.split("-");
	var valu = "";
	var xxdm = "";
	if($("xxdm")){
		xxdm=document.forms[0].xxdm.value;
	}
	//对于长沙民政要判断使用的人数是否低于困难生的固定人数
	if(xxdm == "10827"){
		if(parseInt(xyrs) < parseInt(xyknsrs)){
			alert("使用人数低于困难生固定人数！");
			return false;
		}
	}else{
		//去掉了Math.round(parseFloat(document.forms[0]，因这样即使不准确
		if ((parseFloat(document.forms[0].xyrs.value)*parseFloat(document.forms[0].knsbl.value)/100)>parseFloat(document.forms[0].xyknsrs.value)) {
			alert("使用困难生数低于标准！");
			return false;
		}
		if(parseInt(document.forms[0].xyrs.value)<parseInt(document.forms[0].xyknsrs.value)){
			alert("使用困难生数多于需要人数！");
			return false;
		}
	}
	
	if(xxdm == "13275"){//浙江工业大学之江学院(用于检测必填项)
		eles = (pkFields + "-sqkssj1-sqjssj1").split("-");
	}
	
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {	
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		}
	}
	
	if(document.getElementById("sqkssj1")){//浙江工业大学之江学院(用于检测申请开始结束时间)
		var sqB = "sqkssj";
		var sqE = "sqjssj";
		var a1 = document.all(sqB + "1").value;
		var a2 = (document.all(sqB + "2").value != "" && document.all(sqB + "2").value.length != 2) ? ("0" + document.all(sqB + "2").value) : document.all(sqB + "2").value;
		var a3 = (document.all(sqB + "3").value != "" && document.all(sqB + "3").value.length != 2) ? ("0" + document.all(sqB + "3").value) : document.all(sqB + "3").value;
		var a4 = (document.all(sqB + "4").value != "" && document.all(sqB + "4").value.length != 2) ? ("0" + document.all(sqB + "4").value) : document.all(sqB + "4").value;
		var b1 = document.all(sqE + "1").value;
		var b2 = (document.all(sqE + "2").value != "" && document.all(sqE + "2").value.length != 2) ? ("0" + document.all(sqE + "2").value) : document.all(sqE + "2").value;
		var b3 = (document.all(sqE + "3").value != "" && document.all(sqE + "3").value.length != 2) ? ("0" + document.all(sqE + "3").value) : document.all(sqE + "3").value;
		var b4 = (document.all(sqE + "4").value != "" && document.all(sqE + "4").value.length != 2) ? ("0" + document.all(sqE + "4").value) : document.all(sqE + "4").value;
		a1 = (a1 == "") ? "1900-01-01" : a1;
		a2 = (a2 == "" || isNaN(parseInt(a2)) || parseInt(a2) > 24 || parseInt(a2) < 0) ? "00" : a2;
		a3 = (a3 == "" || isNaN(parseInt(a3)) || parseInt(a3) > 60 || parseInt(a3) < 0) ? "00" : a3;
		a4 = (a4 == "" || isNaN(parseInt(a4)) || parseInt(a4) > 60 || parseInt(a4) < 0) ? "00" : a4;
		b1 = (b1 == "") ? "1900-01-01" : b1;
		b2 = (b2 == "" || isNaN(parseInt(b2)) || parseInt(b2) > 24 || parseInt(b2) < 0) ? "00" : b2;
		b3 = (b3 == "" || isNaN(parseInt(b3)) || parseInt(b3) > 60 || parseInt(b3) < 0) ? "00" : b3;
		b4 = (b4 == "" || isNaN(parseInt(b4)) || parseInt(b4) > 60 || parseInt(b4) < 0) ? "00" : b4;
		var a = a1 + a2 + a3 + a4;
		var b = b1 + b2 + b3 + b4
		var kssj = document.getElementById("kssj").value;
		var jssj = document.getElementById("jssj").value;
		
		if (a > b) {
			alert("申请开始时间晚于申请结束时间，无法保存！");
			return false;
		} else if(replaceChar(a, "-", "")<kssj||replaceChar(b, "-", "")>jssj){
			alert("该岗位的申请时间段不在设定好的申请开始结束时间之内，无法保存！");
			return false;
		}else {
			document.getElementById(sqB).value = replaceChar(a, "-", "");
			document.getElementById(sqE).value = replaceChar(b, "-", "");
		}
		
		var gzkssj = document.getElementById('gzkssj').value;
		var gzjssj = document.getElementById('gzjssj').value;
		if(replaceChar(a1, "-", "")>gzkssj && gzkssj!=''){
			alert('该岗位的申请开始时间在工作开始日期之后，无法保存！');
			return false;
		}
		if(replaceChar(b1, "-", "")>gzjssj){
			alert('该岗位的申请结束时间在工作结束日期之后，无法保存！');
			return false;
		}
	}
	if(xxdm == "12861"){//浙江机电
		var gzsj = document.getElementById('gzsj').value;
		if(gzsj != null){
			if(gzsj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("工作时间不能大于200个字符");
	          		 return false;
	       	}
		}
	}
	for (i = 0; i < eles.length; i++) {
		valu += document.getElementById(eles[i]).value;
	}
	url = url + valu;	
	//if(xxdm!="11417" && xxdm!="11551"){
		//if(gzsj.match(/^\d+\.{0,1}\d{0,3}$/)==null){
			//alert("数据格式错误！");
			//document.getElementById("gzsj").focus();
			//return false;
		//}
	//}
	//alert(url);
	document.forms[0].action = url;
	document.forms[0].submit();
	if(window.dialogArguments){
	window.close();
	window.dialogArguments.document.all("search_go").click();
	}
	return true;
}
function viewMoreWorkPay(doType) {
	if (doType == "add" && curr_row == null) {
		alert("请先选择相应的岗位记录!");
		return false;
	}
	var pkValue = "";
	var url = "/xgxt/modiData.do?realTable=";
	var tmp = document.forms[0].realTable.value;
	var w = 0;
	var h = 0;
	url += tmp;
	url += "&doType=";
	url += doType;
	url += "&tableName=";
	url += document.forms[0].tableName.value;
	url += "&pk=";
	url += document.forms[0].pk.value;
	url += "&from=";
	url += document.forms[0].act.value;
	url += "&gwxz=";
	url += curr_row.cells[6].innerText;
	url += "&yrdw=";
	url += curr_row.cells[5].innerText;
	url += "&pkValue=";
	
	if (doType == "add") {
		pkValue = curr_row.getElementsByTagName("input")[0].value;
	}
	url += pkValue;
	if (tmp == "gwxxb") {
		w = 900;
		h = 600;
	}
	showTopWin(url, w, h);
}
function querySubload() {
	document.forms[0].action = "/xgxt/data_query.do";
	document.forms[0].submit();
}
function addStatisicalAnd() {
	if (document.forms[0].sql.options.length == 0) {
		alert("不合法表达式!");
		return false;
	} else {
		if (document.forms[0].sql.options[document.forms[0].sql.options.length - 1].value == "and" || document.forms[0].sql.options[document.forms[0].sql.options.length - 1].value == "or") {
			alert("不合法表达式!");
			return false;
		}
	}
	document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option("and", "and");
}
function addStatisicalOr() {
	if (document.forms[0].sql.options.length == 0) {
		alert("不合法表达式!");
		return false;
	} else {
		if (document.forms[0].sql.options[document.forms[0].sql.options.length - 1].value == "and" || document.forms[0].sql.options[document.forms[0].sql.options.length - 1].value == "or") {
			alert("不合法表达式!");
			return false;
		}
	}
	document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option("or", "or");
}
function addStatisicalZk() {
	document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option("(", "(");
}
function addStatisicalYk() {
	if (document.forms[0].sql.options.length == 0) {
		alert("不合法表达式!");
		return false;
	} else {
		if (document.forms[0].sql.options[document.forms[0].sql.options.length - 1].value == "(") {
			alert("不合法表达式!");
			return false;
		}
	}
	document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(")", ")");
}
function delcondition() {
	var sqlListIndex = document.forms[0].sql.selectedIndex;
	if (sqlListIndex < 0) {
		return false;
	}
	document.forms[0].sql.options[sqlListIndex] = null;
}
function clearcondition() {
	document.forms[0].sql.options.length = 0;
}
function chk(obj) {
	var tmp = obj.selectedIndex;
	(obj.options[tmp].text.substring(0, 1) == "√") ? obj.options[tmp].text = obj.options[tmp].text.replace("√", "\xd7") : obj.options[tmp].text = obj.options[tmp].text.replace("\xd7", "√");
	obj.selectedIndex = -1;
}
function addQueryCondition() {
	if (document.forms[0].cols.value == "" || document.forms[0].cols.value == null) {
			alert("请选择查询字段!");
			return false;
	}
	if (document.forms[0].sql.options.length != 0) {
		if ((document.forms[0].sql.options[document.forms[0].sql.options.length - 1].value != "and") && (document.forms[0].sql.options[document.forms[0].sql.options.length - 1].value != "or") && (document.forms[0].sql.options[document.forms[0].sql.options.length - 1].value != "(") && (document.forms[0].sql.options[document.forms[0].sql.options.length - 1].value != ")")) {
			alert("不合法表达式!");
			return false;
	     }
		for (i = 0; i < document.forms[0].sql.options.length; i++) {
			if (document.forms[0].sql.options[i].value == (document.all("cols").value + document.forms[0].relation.value + "'" + document.forms[0].val.value + "'")) {
				alert("查询条件重复!");
				return false;
			}
		}
	}
	if (document.forms[0].relation.value == " like ") {
		if (document.forms[0].val.value == "" || document.forms[0].val.value == null) {
			alert("请输入条件值");
			return false;
		}
		if(document.getElementById("mkName").value=="student" && document.getElementById("cxbm").value=="VIEW_XSXXB" && 
		    document.all("cols")[document.all("cols").selectedIndex].text=="是否在校"){
		    document.forms[0].sql.options[document.forms[0].sql.options.length] = 
			new Option(
			document.all("cols")[document.all("cols").selectedIndex].text + 
			document.forms[0].relation.value + "'%" + 
			document.forms[0].val.value + "%'", 
			" exists(select xh from (select xh,xm,decode(nvl((select (select sfzx from dm_ydlb m where b.ydlbdm=m.ydlbm) sfzx from view_xjydjbxx b where  f.xh=b.xh and b.ydxh=(select max(ydxh) from view_xjydjbxx c where b.xh=c.xh) and exists (select c.ydlbmc from dm_ydlb c where  b.ydlbmc=c.ydlbmc)),f.sfzx),null,'在校',nvl((select (select sfzx from dm_ydlb m where b.ydlbdm=m.ydlbm) sfzx from view_xjydjbxx b where  f.xh=b.xh and b.ydxh=(select max(ydxh) from view_xjydjbxx c where b.xh=c.xh) and exists (select c.ydlbmc from dm_ydlb c where  b.ydlbmc=c.ydlbmc)),f.sfzx)) sfzx from view_xsxxb f) f where f.sfzx"+ 
			document.forms[0].relation.value + "'%" + 
			document.forms[0].val.value + "%' and a.xh=f.xh) ");
		}else{
			document.forms[0].sql.options[document.forms[0].sql.options.length] = 
			new Option(
			document.all("cols")[document.all("cols").selectedIndex].text + 
			document.forms[0].relation.value + "'%" + 
			document.forms[0].val.value + "%'", 
			document.all("cols").value + 
			document.forms[0].relation.value + "'%" + 
			document.forms[0].val.value + "%'");	
		}
	} else {
		if (document.forms[0].relation.value == " <> " && document.forms[0].val.value == "") {		
		if(document.getElementById("mkName").value=="student" && document.getElementById("cxbm").value=="VIEW_XSXXB" && 
		    document.all("cols")[document.all("cols").selectedIndex].text=="是否在校"){
				document.forms[0].sql.options[document.forms[0].sql.options.length] = 
				new Option(document.all("cols")[document.all("cols").selectedIndex].text + "  不为空  ", 
				" exists(select xh from (select xh,xm,decode(nvl((select (select sfzx from dm_ydlb m where b.ydlbdm=m.ydlbm) sfzx from view_xjydjbxx b where  f.xh=b.xh and b.ydxh=(select max(ydxh) from view_xjydjbxx c where b.xh=c.xh) and exists (select c.ydlbmc from dm_ydlb c where  b.ydlbmc=c.ydlbmc)),f.sfzx),null,'在校',nvl((select (select sfzx from dm_ydlb m where b.ydlbdm=m.ydlbm) sfzx from view_xjydjbxx b where  f.xh=b.xh and b.ydxh=(select max(ydxh) from view_xjydjbxx c where b.xh=c.xh) and exists (select c.ydlbmc from dm_ydlb c where  b.ydlbmc=c.ydlbmc)),f.sfzx)) sfzx from view_xsxxb f) f where f.sfzx"+ 
				 " is not null and a.xh=f.xh");
			}else{
				document.forms[0].sql.options[document.forms[0].sql.options.length] = 
				new Option(document.all("cols")[document.all("cols").selectedIndex].text + "  不为空  ", 
				document.all("cols").value + " is not null ");
			}
		} else {
			if (document.forms[0].relation.value == " = " && document.forms[0].val.value == "") {
				if(document.getElementById("mkName").value=="student" && document.getElementById("cxbm").value=="VIEW_XSXXB" && 
		    		document.all("cols")[document.all("cols").selectedIndex].text=="是否在校"){
		    		document.forms[0].sql.options[document.forms[0].sql.options.length] = 
					new Option(document.all("cols")[document.all("cols").selectedIndex].text + "  为空  ", 					
		    		" exists(select xh from (select xh,xm,decode(nvl((select (select sfzx from dm_ydlb m where b.ydlbdm=m.ydlbm) sfzx from view_xjydjbxx b where  f.xh=b.xh and b.ydxh=(select max(ydxh) from view_xjydjbxx c where b.xh=c.xh) and exists (select c.ydlbmc from dm_ydlb c where  b.ydlbmc=c.ydlbmc)),f.sfzx),null,'在校',nvl((select (select sfzx from dm_ydlb m where b.ydlbdm=m.ydlbm) sfzx from view_xjydjbxx b where  f.xh=b.xh and b.ydxh=(select max(ydxh) from view_xjydjbxx c where b.xh=c.xh) and exists (select c.ydlbmc from dm_ydlb c where  b.ydlbmc=c.ydlbmc)),f.sfzx)) sfzx from view_xsxxb f) f where f.sfzx"+ 
		    		 + " is null and a.xh=f.xh");
		    	}else{
					document.forms[0].sql.options[document.forms[0].sql.options.length] = 
					new Option(document.all("cols")[document.all("cols").selectedIndex].text + "  为空  ", 
					document.all("cols").value + " is null ");
				}
			} else {
			if(document.getElementById("mkName").value=="student" && document.getElementById("cxbm").value=="VIEW_XSXXB" && 
		    		document.all("cols")[document.all("cols").selectedIndex].text=="是否在校"){
		    		document.forms[0].sql.options[document.forms[0].sql.options.length] = 
					new Option(document.all("cols")[document.all("cols").selectedIndex].text + document.forms[0].relation.value + 
					"'" + document.forms[0].val.value + "'", 
					" exists(select xh from (select xh,xm,decode(nvl((select (select sfzx from dm_ydlb m where b.ydlbdm=m.ydlbm) sfzx from view_xjydjbxx b where  f.xh=b.xh and b.ydxh=(select max(ydxh) from view_xjydjbxx c where b.xh=c.xh) and exists (select c.ydlbmc from dm_ydlb c where  b.ydlbmc=c.ydlbmc)),f.sfzx),null,'在校',nvl((select (select sfzx from dm_ydlb m where b.ydlbdm=m.ydlbm) sfzx from view_xjydjbxx b where  f.xh=b.xh and b.ydxh=(select max(ydxh) from view_xjydjbxx c where b.xh=c.xh) and exists (select c.ydlbmc from dm_ydlb c where  b.ydlbmc=c.ydlbmc)),f.sfzx)) sfzx from view_xsxxb f) f where f.sfzx"+ 
					document.forms[0].relation.value +"'"+ document.forms[0].val.value  + "' and a.xh=f.xh)");
		    	}else{		    		
					document.forms[0].sql.options[document.forms[0].sql.options.length] = 
					new Option(document.all("cols")[document.all("cols").selectedIndex].text + document.forms[0].relation.value + 
					"'" + document.forms[0].val.value + "'", 
					document.all("cols").value + document.forms[0].relation.value + "'" + document.forms[0].val.value + "'");
				}
			}
		}
	}
}
function sendValue() {
	var cols = "";
	var sqlStr = "";
	var titList = document.getElementById("titList");
	for (i = 0; i < document.forms[0].titList.options.length; i++) {
		if (document.forms[0].titList.options[i].text.substring(0, 1) == "√") {
			cols += "!!SplitSignOne!!" + document.forms[0].titList.options[i].value;
		}
	}
	if (cols == "" || cols.length < 2) {
		alert("可视字段为空！");
		return false;
	} else {
		if (allCheck()) {
			var str = "";
			for (i = 0; i < document.forms[0].sql.options.length; i++) {
				sqlStr += " " + document.forms[0].sql.options[i].value + " ";
			}			
			document.forms[0].sql1.value = sqlStr;
			document.forms[0].cols1.value = cols;
			document.forms[0].submit();
			if($("buttonSearch")){
			   $("buttonSearch").disabled=true;
			}
		} else {
			return false;
		}
	}
}
function allCheck() {
	var tmp = new Array();
	if (document.forms[0].sql.options.length == 0) {
		alert("请输入查询条件！");
		return false;
	} else {
		if (document.forms[0].sql.options[document.forms[0].sql.options.length - 1].value == "and" || document.forms[0].sql.options[document.forms[0].sql.options.length - 1].value == "or" || document.forms[0].sql.options[document.forms[0].sql.options.length - 1].value == "(") {
			alert("不合法表达式!");
			return false;
		}
		for (i = 0; i < document.forms[0].sql.options.length; i++) {
			if (document.forms[0].sql.options[i].value == "(") {
				tmp[tmp.length] = "F";
			} else {
				if (document.forms[0].sql.options[i].value == ")") {
					if (tmp.length > 0) {
						tmp.length = tmp.length - 1;
					} else {
						alert("括号匹配有误!");
						return false;
					}
				}
			}
		}
		if (tmp.length == 0) {
			return true;
		} else {
			alert("括号匹配有误!");
			return false;
		}
	}
}





function checkAll(obj){
	
	var input = document.getElementsByTagName("input");
	var checkBoxArr = new Array();
	
	var n=0;
	for (var i = 0 ; i<input.length;i++){
		if (input[i].type=="checkbox" ){
			checkBoxArr[n] = input[i];
			n++;
		}
	}
	
	for(var i=0;i<checkBoxArr.length;i++){
		if (checkBoxArr[i].id != "selectAll" && checkBoxArr[i].id != "turnSelect"){
			if (obj.checked){
				if (!checkBoxArr[i].checked){
					checkBoxArr[i].click();
				}
			} else {
				if (checkBoxArr[i].checked){
					checkBoxArr[i].click();
				}
			}
		}
		
	}
	
}

function turnCheck(obj){
	var input = document.getElementsByTagName("input");
	var checkBoxArr = new Array();
	
	var n=0;
	for (var i = 0 ; i<input.length;i++){
		if (input[i].type=="checkbox"){
			checkBoxArr[n] = input[i];
			n++;
		}
	}


	for(var i=0;i<checkBoxArr.length;i++){
		if (checkBoxArr[i].id != "selectAll" && checkBoxArr[i].id != "turnSelect"){
			checkBoxArr[i].click();
		}
	}

}





function getColumeList(url) {
	var col = document.forms[0].colNameList.value;
	var colCN = document.forms[0].colNameCNList.value;
	var rsInfo = document.forms[0].rsInfo.value;
	var vCols = document.forms[0].vCols.value;
	if (rsInfo.length < 30) {
		rsInfo = "0!!SplitSignOne!!0!!SplitSignOne!!0!!SplitSignOne!!0";
	}
	var vCol = vCols.split("!!SplitSignOne!!");
	var colNameCN = colCN.split("!!SplitSignOne!!");
	var colName = col.split("!!SplitSignOne!!");
	var pageInfo = rsInfo.split("!!SplitSignOne!!");
	var colList = "";
	for (i = 1; i < colName.length; i++) {
		colList += "<input style='border:0px' onclick='chgView(this)' type='checkbox'";
		if (inArray(colName[i], vCol)) {
			colList += " checked ";
		}
		colList += " name='chk" + i + "'>" + colNameCN[i] + "&nbsp;&nbsp;&nbsp;&nbsp;";
	}
	var pageList = "";
	pageList += "（共 <b>" + pageInfo[0];
	pageList += "</b> 条记录，分 <b>" + pageInfo[1];
	pageList += "</b> 页；每页" + "<input type='text' style='width:60px;font-weight:bolder ' class='buton2' name='pageSize' value='" + pageInfo[2] + "'>";
	pageList += "条，当前第 <b>" + pageInfo[3];
	pageList += "</b> 页）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	pStart = 0;
	pEnd = 0;
	pStart = pageInfo[3] - 3;
	pEnd = pageInfo[3] - (-3);
	if (pStart < 1) {
		pStart = 1;
	}
	if (pEnd > pageInfo[1]) {
		pEnd = pageInfo[1];
	}
	for (j = pStart; j <= pEnd; j++) {
		if (pageInfo[3] == j) {
			pageList += "&nbsp;[<font color=red><b>" + j + "</b></font>]";
		} else {
			pageList += "&nbsp;[<span style='cursor:hand' onclick=\"chgPage('" + url + "','" + j + "')\">" + j + "</span>]";
		}
	}
	pageList += "&nbsp;&nbsp;&nbsp;&nbsp;<input name=go_page_num type='text' value='" + pageInfo[3] + "' style='width:35px'><input type='button' class='button2' value='GO' onclick=\"chgPageButonClick('" + url + "')\">";
	
	pageList += "&nbsp;&nbsp;&nbsp;&nbsp;<div id='selectButtion' style='display:none'><input type='checkbox'  id='selectAll' onclick=\"checkAll(this)\"/>全选";
	pageList += "&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox'  id='turnSelect'onclick=\"turnCheck(this)\"/>反选</div>";
	
	
	colListArea.innerHTML = colList;
	tmp = document.createElement("SPAN");
	tmp.innerHTML = pageList;
	resultTitle.appendChild(tmp);
	var k = i;
	for (j = 1; j < k; j++) {
		chkName = "chk" + j;
		chgView(document.getElementsByName(chkName)[0]);
	}
}
function chgView(obj) {
	var tmp = obj.name.substring(3, obj.name.length) - 1;	
	var cols1 = "";
	if(columns!=""){
		cols1 = columns;
	}
	if (obj.checked) {
		for (i = 0; i < rsTable.rows.length; i++) {
			rsTable.rows[i].cells[tmp].style.display = "";			
		}	
		var colName = rsTable.rows[0].cells[tmp].getElementsByTagName("input")[0].value;	
		cols1 = cols1 + "!!SplitSignOne!!" + colName;		
	} else {
		for (i = 0; i < rsTable.rows.length; i++) {
			rsTable.rows[i].cells[tmp].style.display = "none";
		}	
		var colName = rsTable.rows[0].cells[tmp].getElementsByTagName("input")[0].value;
		cols1 = cols1.replace("!!SplitSignOne!!" + colName +"!!SplitSignOne!!","!!SplitSignOne!!");
	}
	columns = cols1;
	document.getElementById('cols1').value = columns;
//	alert(columns);
}

function dataExpForQ(){
	document.getElementById('colName').value=columns;
	document.forms[0].action = 'dataexportforq.do';
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
function chgPageButonClick(url) {
	var tmp = document.all("go_page_num").value;
	chgPage(url, tmp);
}
function chgPage(url, pagenum) {
	document.forms[0].action = url;
	document.forms[0].page.value = pagenum;
	document.forms[0].submit();
}
function showList() {
	if (document.getElementById('lxButton').innerText == "打开列选") {
		document.getElementById('lxButton').innerText = "关闭列选";
		colListArea.style.display = "";
		document.getElementById('selectButtion').style.display="";
		//document.getElementById("btn").style.pixelTop = document.getElementById("btn").style.pixelTop - 160;
	} else {
		document.getElementById('lxButton').innerText = "打开列选";
		colListArea.style.display = "none";
		document.getElementById('selectButtion').style.display="none";
		//document.getElementById("btn").style.pixelTop = document.getElementById("btn").style.pixelTop + 160;
	}
//	if (document.forms[0].showcollist.value == "打开列选") {
//		document.forms[0].showcollist.value = "关闭列选";
//		colListArea.style.display = "";
//		document.getElementById("btn").style.pixelTop = document.getElementById("btn").style.pixelTop - 160;
//	} else {
//		document.forms[0].showcollist.value = "打开列选";
//		colListArea.style.display = "none";
//		document.getElementById("btn").style.pixelTop = document.getElementById("btn").style.pixelTop + 160;
//	}
}
function statisicalSubload(mkName1) {
	saveConditionSql();
	var mkName = window.document.forms[0].mkV.value;
	document.forms[0].action = "/xgxt/statisicalInit.do?mkName=" + mkName;
	document.forms[0].submit();
}

function statisicalSubloader(mkName1,titName) {
	saveConditionSql();
	var mkName = window.document.forms[0].mkV.value;
	document.forms[0].action = "/xgxt/statisicalInit.do?mkName=" + mkName+"&titName="+titName;
	document.forms[0].submit();
}

function subStatisical() {
	saveConditionSql();
	if(allCheck()){
		if (document.forms[0].sql.options.length < 1) {
			alert("请输入统计条件!");
			return false;
		}
		if (document.forms[0].cols.value == null || document.forms[0].cols.value == "") {
			alert("请选择显示字段!");
			return false;
		}
		var mkName = window.document.forms[0].mkV.value;	
		document.forms[0].action = "statisical.do?mkName=" + mkName;
		document.forms[0].submit();	
		var tmpBut = document.getElementsByTagName("button");
		for (i = 0; i < tmpBut.length; i++) {
			tmpBut[i].disabled = true;
		}
		if($("buttonStat")){
		   $("buttonStat").disabled=true;
		}
	}
}
function addStatisicalCondition() {
	if (document.forms[0].zxm.disabled == true) {
		if (document.forms[0].jtxm.value == "" || document.forms[0].jtxm.value == null) {
			alert("请选择具体项目!");
			return false;
		}
	} else {
		if (document.forms[0].zxm.value == "" || document.forms[0].zxm.value == null) {
			alert("请选择子项目!");
			return false;
		}
	}
	if (document.forms[0].sql.options.length != 0) {
		if ((document.forms[0].sql.options[document.forms[0].sql.options.length - 1].value != "and") && (document.forms[0].sql.options[document.forms[0].sql.options.length - 1].value != "or") && (document.forms[0].sql.options[document.forms[0].sql.options.length - 1].value != "(") && (document.forms[0].sql.options[document.forms[0].sql.options.length - 1].value != ")")) {
			alert("不合法表达式!");
			return false;
		}
		for (i = 0; i < document.forms[0].sql.options.length; i++) {
			if (document.forms[0].sql.options[i].value == (document.all("jtxm").value + document.forms[0].relation.value + "'" + document.forms[0].tjtjz.value + "'")) {
				alert("查询条件重复!");
				return false;
			}
		}
	}
	if (document.forms[0].relation.value == " like ") {
		if (document.forms[0].tjtjz.value == "" || document.forms[0].tjtjz.value == null) {
			alert("请输入条件值");
			return false;
		}
		if (document.getElementById("tjtjz").tagName.toLowerCase() == "input"||document.getElementById("tjtjz").tagName.toLowerCase() == "text") {
			if (document.forms[0].zxm.disabled == true) {
				document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(document.all("jtxm")[document.all("jtxm").selectedIndex].text + document.forms[0].relation.value + "'%" + document.forms[0].tjtjz.value + "%'", document.all("jtxm").value + document.forms[0].relation.value + "'%" + document.forms[0].tjtjz.value + "%'");
			} else {
				document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(document.all("zxm")[document.all("zxm").selectedIndex].text + document.forms[0].relation.value + "'%" + document.forms[0].tjtjz.value + "%'", document.all("zxm").value + document.forms[0].relation.value + "'%" + document.forms[0].tjtjz.value + "%'");
			}
		}
		if (document.getElementById("tjtjz").tagName.toLowerCase() == "select") {
			if (document.forms[0].zxm.disabled == true) {
				document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(document.all("jtxm")[document.all("jtxm").selectedIndex].text + document.forms[0].relation.value + "'%" + document.forms[0].tjtjz.options[document.forms[0].tjtjz.selectedIndex].text + "%'", document.all("jtxm").value + document.forms[0].relation.value + "'%" + document.forms[0].tjtjz.value + "%'");
			} else {
				document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(document.all("zxm")[document.all("zxm").selectedIndex].text + document.forms[0].relation.value + "'%" + document.forms[0].tjtjz.options[document.forms[0].tjtjz.selectedIndex].text + "%'", document.all("zxm").value + document.forms[0].relation.value + "'%" + document.forms[0].tjtjz.value + "%'");
			}
		}
	} else {
		if (document.forms[0].relation.value == " <> " && document.forms[0].tjtjz.value == "") {
			if (document.forms[0].zxm.disabled == true) {
				document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(document.all("jtxm")[document.all("jtxm").selectedIndex].text + "  不为空  ", document.all("jtxm").value + " is not null ");
			} else {
				document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(document.all("zxm")[document.all("zxm").selectedIndex].text + "  不为空  ", document.all("zxm").value + " is not null ");
			}
		} else {
			if (document.forms[0].relation.value == " = " && document.forms[0].tjtjz.value == "") {
				if (document.forms[0].zxm.disabled == true) {
					document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(document.all("jtxm")[document.all("jtxm").selectedIndex].text + "  为空  ", document.all("jtxm").value + " is null ");
				} else {
					document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(document.all("zxm")[document.all("zxm").selectedIndex].text + "  为空  ", document.all("zxm").value + " is null ");
				}
			} else {
				if (document.getElementById("tjtjz").tagName.toLowerCase() == "input"||document.getElementById("tjtjz").tagName.toLowerCase() == "text") {
					if (document.forms[0].zxm.disabled == true) {
						document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(document.all("jtxm")[document.all("jtxm").selectedIndex].text + document.forms[0].relation.value + "'" + document.forms[0].tjtjz.value + "'", document.all("jtxm").value + document.forms[0].relation.value + "'" + document.forms[0].tjtjz.value + "'");
					} else {
						document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(document.all("zxm")[document.all("zxm").selectedIndex].text + document.forms[0].relation.value + "'" + document.forms[0].tjtjz.value + "'", document.all("zxm").value + document.forms[0].relation.value + "'" + document.forms[0].tjtjz.value + "'");
					}
				}
				if (document.getElementById("tjtjz").tagName.toLowerCase() == "select") {
					if (document.forms[0].zxm.disabled == true) {
						document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(document.all("jtxm")[document.all("jtxm").selectedIndex].text + document.forms[0].relation.value + "'" + document.forms[0].tjtjz.options[document.forms[0].tjtjz.selectedIndex].text + "'", document.all("jtxm").value + document.forms[0].relation.value + "'" + document.forms[0].tjtjz.value + "'");
					} else {
						document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(document.all("zxm")[document.all("zxm").selectedIndex].text + document.forms[0].relation.value + "'" + document.forms[0].tjtjz.options[document.forms[0].tjtjz.selectedIndex].text + "'", document.all("zxm").value + document.forms[0].relation.value + "'" + document.forms[0].tjtjz.value + "'");
					}
				}
			}
		}
	}
}
function saveConditionSql(){
	var sqlText = new Array();
	var sqlValue = new Array();
	document.forms[0].conditionSqlText.value = "";
	document.forms[0].conditionSqlValue.value = "";
	for (i = 0; i < document.forms[0].sql.options.length; i++) {			
		sqlText[sqlText.length] =  document.forms[0].sql.options[i].text;
		sqlValue[sqlValue.length] = document.forms[0].sql.options[i].value;	
	}
	document.forms[0].conditionSqlText.value = sqlText;
	document.forms[0].conditionSqlValue.value = sqlValue;//alert(sqlValue+"//////////////"+sqlText);
}
function loadConditionSql() {
	var txt = document.forms[0].conditionSqlText.value;
	var txt1 = document.forms[0].conditionSqlValue.value;
	var SplitSignOne = ",";
	if (document.forms[0].conditionSqlValue.value != null && document.forms[0].conditionSqlValue.value != "") {
		var initStrToSplit = txt.split(SplitSignOne);
		var initStrToSplit1 = txt1.split(SplitSignOne);
		document.forms[0].sql.options.length = 0;
		for (i = 0; i < initStrToSplit.length; i++) {
			document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(initStrToSplit[i], initStrToSplit1[i]);
		}
	}
}
function chk1(obj) {
	var tmp = obj.selectedIndex;
	if (obj.options[tmp].text.substring(0, 1) == "√") {
		obj.options[tmp].text = obj.options[tmp].text.replace("√", "\xd7");
		var vCol = document.forms[0].cols.value.split("!!SplitSignOne!!");
		var removeFlag;
		for (i = 0; i < vCol.length; i++) {
			if (vCol[i] == obj.options[tmp].value) {
				removeFlag = i;
			}
		}
		document.forms[0].cols.value = "";
		for (j = 0; j < vCol.length; j++) {
			if (j != removeFlag) {
				if (document.forms[0].cols.value != null && document.forms[0].cols.value != "") {
					document.forms[0].cols.value = document.forms[0].cols.value + "!!SplitSignOne!!";
				}
				document.forms[0].cols.value = document.forms[0].cols.value + vCol[j];
			}
		}
	} else {
		obj.options[tmp].text = obj.options[tmp].text.replace("\xd7", "√");
		if (document.forms[0].cols.value != null && document.forms[0].cols.value != "") {
			document.forms[0].cols.value = document.forms[0].cols.value + "!!SplitSignOne!!";
		}
		document.forms[0].cols.value = document.forms[0].cols.value + obj.options[tmp].value;
	}
	obj.selectedIndex = -1;
}

function analyse() {
	i = document.getElementsByTagName("select").length;
	for (j = 0; j < i; j++) {
		document.getElementsByTagName("select")[j].style.visibility = "hidden";
	}
	var d_width = document.body.clientWidth - 5;
	var d_height = document.body.clientHeight - 10;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 350;
	var d_height_top = 200;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	if(d_top_top>300){
		d_top_top = 250;
	}
	var d_color_top = "#EEF4F9";
//	dd_html = "<br/><br/><div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
//	dd_html += "</div>";
//	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html = "<div class='tab'><table width='300' class='formlist'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<th colspan='2'>";
	dd_html += "<span>分析选项设置</span>";
	dd_html += "</th>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<th width='10%'>";
	dd_html += "分析图表类型";
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += "<select name='tblx' id='select_tblx'> <option value=''>------请选择------</option> <option value='bar'>直方图</option> <option value='bar3D'>3D直方图</option> <option value='pie'>饼图</option> <option value='pie3D'>3D饼图</option> <option value='line'>折线图</option> <option value='area'>区域图</option> </select>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr>";
	dd_html += "<th>";
	dd_html += "分析项目";
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += "<select name='yjxm' id='select_yjxm'></select>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr bgcolor='EEF4F9' align='right'>";
	dd_html += "<td colspan='2'>";
	dd_html += "<button class='button2' onclick='if(allFilled()){refreshForm(\"analyse.do\")}'>确定</button>";
	dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<button class='button2' onclick='hiddenMessage(true,true)'>取消</button>";
	dd_html += "</td>";
	dd_html += "</tr>"; 
	dd_html += "<tbody>";
	dd_html += "</table>";
	dd_html += "</div>";
//	tmpdiv1.innerHTML = dd_html;
	
	createTmpDiv("高级统计",dd_html,350,200);
	
	var dpd = new Array();
	var txt = document.forms[0].cols.value;
	var SplitSignOne = "!!SplitSignOne!!";
	var initStrToSplit = txt.split(SplitSignOne);
	document.forms[0].yjxm.options.length = 0;
	document.forms[0].yjxm.options[document.forms[0].yjxm.options.length] = new Option("------请选择------", "");
	for (i = 0; i < initStrToSplit.length; i++) {
		dpd[i] = initStrToSplit[i].split("-");
		if (dpd[i][0] != "rs" && dpd[i][0] != "bl") {
			document.forms[0].yjxm.options[document.forms[0].yjxm.options.length] = new Option(dpd[i][1], dpd[i][0] + "-" + dpd[i][1]);
		}
	}
	top_layer.click();
}
function dataDoSaveApply1(url, pkFields, tabFlag, pkFields1) {
	if(pkFields1){	
        var eles1 = pkFields1.split("-");
        for (i = 0; i < eles1.length; i++) {
			if (document.getElementById(eles1[i])){
				if(document.getElementById(eles1[i]).value == "") {
					alert("请将带\"*\"号的项目输入完整！");
					return false;
			   	}
		    }  
        } 
	}
	if(url.search('applySave.do')){
		if($("brjl")&&$("brjl")!=null){
	       if($("brjl").value.length>400){
	          alert("本人简历字数过大，限400字内！");
	          return false;
	       }
	    }
	    if($("zysj")&&$("zysj")!=null){	      
	       if($("zysj").value.length>400){
	          alert("主要事迹字数过大，限400字内！");
	          return false;
	       }
	    }
	    if($("hjqk")&&$("hjqk")!=null){	      
	       if($("hjqk").value.length>400){
	          alert("在校期间获奖情况字数过大，限400字内！");
	          return false;
	       }
	    }
		var xh = document.getElementById("xh").value;
		var xmdmObj = document.getElementById("xmdm");
		if((xh == "") ||(xh == " ") ){
			alert("学号为空！！");
			return false;
		}
		if(xmdmObj != null){
			var xmdm = xmdmObj.value;
			if((xmdm == "") ||(xmdm == " ")){
				alert("项目为空！！");
				return false;
			}
		}
	}
		var eles = pkFields.split("-");
		var valu = "";
		for (i = 0; i < eles.length; i++) {
			if (document.getElementById(eles[i])){
				valu += document.getElementById(eles[i]).value;
			}
		}	
		if ($("rychdm")) {
			url = url + "?tab=" + tabFlag + "&pkValue=" + valu+'&rychmc='+document.forms[0].xmdm.options[document.forms[0].xmdm.selectedIndex].text;	
		} else {
			url = url + "?tab=" + tabFlag + "&pkValue=" + valu;
		}
		if(document.getElementById("ylrychdm")){
			var ylrychdm = document.getElementById("ylrychdm").value;
			if( ylrychdm != null && ylrychdm != ""){			
				url = url + "&ylrychdm=" + ylrychdm;
			}
		}
		var pkV;
		if ($('pkValue')) {
			pkV = document.getElementById('pkValue').value;
		}
		url += '&pkV=' + pkV
		document.forms[0].action = url;
		document.forms[0].submit();
		return true;
}

function SztzDataDoSaveApply(url, pkFields, tabFlag, pkFields1) {
	if(pkFields1){
        var eles1 = pkFields1.split("-");
        for (i = 0; i < eles1.length; i++) {
			if (document.getElementById(eles1[i])){
				if(document.getElementById(eles1[i]).value == "") {
					alert("请将带\"*\"号的项目输入完整！");
					return false;
			   	}
		    }  
        } 
	}
	if(url.search('applySave.do')){
		var xh = document.getElementById("xh").value;
		var xmdmObj = document.getElementById("xmdm");
		if((xh == "") ||(xh == " ") ){
			alert("学号为空！！");
			return false;
		}
		if(xmdmObj != null){
			var xmdm = xmdmObj.value;
			if((xmdm == "") ||(xmdm == " ")){
				alert("项目为空！！");
				return false;
			}
		}
	}	
		var eles = pkFields.split("-");
		var valu = "";
		
		for (i = 0; i < eles.length; i++) {
			if (document.getElementById(eles[i])){
				valu += document.getElementById(eles[i]).value;
			}
		}	
		if(url.search('sztz_modiData.do')){
		 url = url + "?doType=save&tab=" + tabFlag + "&pkValue=" + valu;		
		}else{	
		 url = url + "?tab=" + tabFlag + "&pkValue=" + valu;
		}
     	document.forms[0].action = url;
		document.forms[0].submit();		
		if(window.dialogArguments){
		Close();
	    window.dialogArguments.document.getElementById("search_go").click();
	    }
}

function dataDoSaveApply2(url, pkFields, tabFlag) {
	if (allFilled()) {
		var eles = pkFields.split("-");
		var valu = "";
		if (pkFields != null && pkFields != "") {
			for (i = 0; i < eles.length; i++) {
				valu += document.getElementById(eles[i]).value;
			}
		}
		url = url + "?tab=" + tabFlag + "&pkValue=" + valu;
		document.forms[0].action = url;
		document.forms[0].submit();
		return true;
	}
}

function dataDoSaveApply3(url, pkFields, tabFlag, act) {
	var eles = pkFields.split("-");
	var valu = "";
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		}
	}
	if($("bxje")){
		var bxje=document.getElementById("bxje").value;
		if(bxje.match(/^\d+\.{0,1}\d{0,3}$/)==null){
			alert("保险金额必需是数字!");
			return false;
		}
	}
	for (i = 0; i < eles.length; i++) {
		valu += document.getElementById(eles[i]).value;
	}
	url = url + "?tab=" + tabFlag + "&pkValue=" + valu + "&act=" + act;
	document.forms[0].action = url;
	document.forms[0].submit();
	return true;
}
function dataDoSaveApply4(url, pkFields, tabFlag, pkFields1) {
	if(pkFields1){
        var eles1 = pkFields1.split("-");
        for (i = 0; i < eles1.length; i++) {
			if (document.getElementById(eles1[i])){
				if(document.getElementById(eles1[i]).value == "") {
					alert("请将带\"*\"号的项目输入完整！");
					return false;
			   	}
		    }  
        } 
	}
	if(url.search('applySave.do')){
		var xh = document.getElementById("xh").value;
		var xmdmObj = document.getElementById("xmdm");
		if((xh == "") ||(xh == " ") ){
			alert("学号为空！！");
			return false;
		}
		if(xmdmObj != null){
			var xmdm = xmdmObj.value;
			if((xmdm == "") ||(xmdm == " ")){
				alert("项目为空！！");
				return false;
			}
		}
	}
		var eles = pkFields.split("-");
		var valu = "";
		for (i = 0; i < eles.length; i++) {
			if (document.getElementById(eles[i])){
				valu += document.getElementById(eles[i]).value;
			}
		}		
		url = url + "?tab=" + tabFlag + "&pkValue=" + valu;		
		if(document.getElementById("ylrychdm")){
			var ylrychdm = document.getElementById("ylrychdm").value;
			if( ylrychdm != null && ylrychdm != ""){			
				url = url + "&ylrychdm=" + ylrychdm;
			}
		}
		//alert(url);
		document.forms[0].action = url;
		document.forms[0].submit();
		return true;
}

function print() {
}
function changecjje(objTr) {
	var xxdm = document.getElementById("xxdm").value;
	if (objTr.value == null || objTr.value == "") {
		document.getElementById(objTr.id.replace("gzsj", "cjje")).value = "";
	} else {
		if(xxdm!=null && xxdm == "10497"){
		   //武汉理工大学
		   if(parseInt(document.getElementById("ffbcbz").value)>parseInt(document.getElementById("jybcbz").value)){
			alert("发放报酬标准高于审批标准！");
			return false;
		   }else{
			document.getElementById(objTr.id.replace("gzsj", "cjje")).value = objTr.value * document.forms[0].ffbcbz.value;			
		   }
		}else if(xxdm != null && xxdm == "11078"){//广州大学
			
		}else{
			document.getElementById(objTr.id.replace("gzsj", "cjje")).value = objTr.value * document.getElementById("jybcbz").value;
		}
	}
	changezje();
}

function changezje() {
	var sum = 0;
	if($("count")){
		for (i = 0; i <document.forms[0].count.value; i++) {
			if (document.getElementById("cjje" + i).value != null && document.getElementById("cjje" + i).value != "") {
				sum = sum + parseFloat(document.getElementById("cjje" + i).value);
			}
		}
		document.getElementById("zje").innerText = sum;
	}
}

function chgnumformat() {
	if (document.getElementById("rsTable") != null) {
		for (i = 0; i < rsTable.rows.length; i++) {
			for (j = 0; j < rsTable.rows[i].cells.length; j++) {
				if (rsTable.rows[i].cells[j].innerText.substring(0, 1) == ".") {
					rsTable.rows[i].cells[j].innerText = "0" + rsTable.rows[i].cells[j].innerText;
				}
			}
		}
	}
}

function tm(minTop, maxTop) {
	if (hidFlag) {
		if (parseInt(document.getElementById("jxjtmp").style.top) <= minTop) {
			document.getElementById("jxjtmp").style.top = parseInt(document.getElementById("jxjtmp").style.top) + 10;
		}
		if (document.getElementById("rsTable") != null) {
			document.getElementById("rsTable").style.display = "none";
		}
	} else {
		if (parseInt(document.getElementById("jxjtmp").style.top) > -maxTop) {
			document.getElementById("jxjtmp").style.top = parseInt(document.getElementById("jxjtmp").style.top) - 10;
		}
		if (document.getElementById("rsTable") != null) {
			document.getElementById("rsTable").style.display = "";
		}
	}
}
function menuMove(tag) {
	if (tag == 0) {
		menuCont.style.left = parseInt(menuCont.style.left) + 1;
	} else {
		menuCont.style.left = parseInt(menuCont.style.left) - 1;
	}
}
function initBTNTool(obj) {
	var diffY = document.body.scrollTop;
	var percent = 0.1 * (diffY - lastScrollY);
	if (percent > 0) {
		percent = Math.ceil(percent);
	} else {
		percent = Math.floor(percent);
	}
	document.getElementById(obj).style.pixelTop += percent;
	lastScrollY = lastScrollY + percent;
}

function showTopWinTest(url, w, h) {
	url = url.replace("/xgxt/","");
	if (curr_row != null) {
		url = url + "&pkValue=" + curr_row.getElementsByTagName("input")[0].value + "&go=go";
	}
	var info = "Status:NO;dialogWidth:" + w + "px;dialogHeight:" + h + "px";
	showModalDialog(url, window, info);
}
function dataManLoadTest() {
	var tmpSel = document.getElementsByTagName("select");
	var tmpHid = document.getElementsByTagName("input");
	var tmpOth = document.getElementsByTagName("textarea");
	var tmpBut = document.getElementsByTagName("button");
	if (document.forms[0].doType.value == "view") {
		tipFollow.innerText = window.dialogArguments.document.all("title_m").innerText + " - 查看";
		dataCanModi(false);
		document.forms[0].buttonModi.disabled = false;
		document.forms[0].buttonSave.disabled = false;
		document.forms[0].buttonClose.disabled = false;
		document.forms[0].buttonModi.style.display = "none";
		document.forms[0].buttonSave.style.display = "none";
	}
	if (document.forms[0].doType.value == "modi") {
		tipFollow.innerText = window.dialogArguments.document.all("title_m").innerText + " - 修改";
		dataCanModi(true);
	}
	if (document.forms[0].doType.value == "add") {
		tipFollow.innerText = window.dialogArguments.document.all("title_m").innerText + " - 添加";
		dataCanModi(true);
		document.forms[0].buttonModi.style.display = "none";
	}
}
function loadImportInfo() {
	var succInfoStr = document.forms[0].succInfo.value.split("-");
	if (succInfoStr.length > 1) {
		var succInfoNum = succInfoStr.length - 1;
	} else {
		var succInfoNum = 0;
	}
	var failInfoStr = document.forms[0].failInfo.value.split("-");
	if (failInfoStr.length > 1) {
		var failInfoNum = failInfoStr.length - 1;
	} else {
		var failInfoNum = 0;
	}
	var impDetailStr = "总共处理" + document.forms[0].total.value + "条记录," + succInfoNum + "条记录导入成功," + failInfoNum + "条记录导入失败.<br/>其中源数据中第";
	for (i = 1; i < succInfoStr.length; i++) {
		if (i != 1) {
			impDetailStr = impDetailStr + ",";
		}
		impDetailStr = impDetailStr + succInfoStr[i];
	}
	impDetailStr = impDetailStr + "条记录导入成功.<br/>第";
	for (j = 1; j < failInfoStr.length; j++) {
		if (j != 1) {
			impDetailStr = impDetailStr + ",";
		}
		impDetailStr = impDetailStr + failInfoStr[j];
	}
	impDetailStr = impDetailStr + "条记录导入失败.";
	impDetailInfo.innerHTML = impDetailStr;
}
function createStList(yxstlbV) {
	if (yxstlbV != null) {
		for (i = 0; i < document.forms[0].yxstlb.options.length; i++) {
			if (document.forms[0].yxstlb.options[i].value == yxstlbV) {
				alert("该题已存在!");
				return false;
			}
		}
		document.forms[0].yxstlb.options[document.forms[0].yxstlb.options.length] = new Option(yxstlbV + "------------" + (document.forms[0].yxstlb.options.length + 1), yxstlbV);
		count.innerText = document.forms[0].yxstlb.options.length;
	}
}
function updateStList(flag) {
	if (flag == "clear") {
		document.forms[0].yxstlb.options.length = 0;
		count.innerText = document.forms[0].yxstlb.options.length;
	} else {
		var yxstlbListIndex = document.forms[0].yxstlb.selectedIndex;
		var tmp;
		if (yxstlbListIndex < 0) {
			alert("请先选定行!");
			return false;
		}
		if (flag == "del") {
			document.forms[0].yxstlb.options[yxstlbListIndex] = null;
			for (i = 0; i < document.forms[0].yxstlb.options.length; i++) {
				document.forms[0].yxstlb.options[i].text = document.forms[0].yxstlb.options[i].value + "------------" + (i + 1);
			}
			count.innerText = document.forms[0].yxstlb.options.length;
		}
		if (flag == "up") {
			if (yxstlbListIndex == 0) {
				return false;
			}
			tmp = document.forms[0].yxstlb.options[yxstlbListIndex - 1].value;
			document.forms[0].yxstlb.options[yxstlbListIndex - 1].value = document.forms[0].yxstlb.options[yxstlbListIndex].value;
			document.forms[0].yxstlb.options[yxstlbListIndex - 1].text = document.forms[0].yxstlb.options[yxstlbListIndex].value + "------------" + yxstlbListIndex;
			document.forms[0].yxstlb.options[yxstlbListIndex].value = tmp;
			document.forms[0].yxstlb.options[yxstlbListIndex].text = tmp + "------------" + (yxstlbListIndex + 1);
			document.forms[0].yxstlb.selectedIndex = yxstlbListIndex - 1;
		}
		if (flag == "down") {
			if (yxstlbListIndex >= document.forms[0].yxstlb.options.length - 1) {
				return false;
			}
			tmp = document.forms[0].yxstlb.options[yxstlbListIndex + 1].value;
			document.forms[0].yxstlb.options[yxstlbListIndex + 1].value = document.forms[0].yxstlb.options[yxstlbListIndex].value;
			document.forms[0].yxstlb.options[yxstlbListIndex + 1].text = document.forms[0].yxstlb.options[yxstlbListIndex].value + "------------" + (yxstlbListIndex + 2);
			document.forms[0].yxstlb.options[yxstlbListIndex].value = tmp;
			document.forms[0].yxstlb.options[yxstlbListIndex].text = tmp + "------------" + (yxstlbListIndex + 1);
			document.forms[0].yxstlb.selectedIndex = yxstlbListIndex + 1;
		}
	}
}
function StSjDoSave() {
	var yxstStr = "";
	for (i = 0; i < document.forms[0].yxstlb.options.length; i++) {
		if (yxstStr != "") {
			yxstStr = yxstStr + "-";
		}
		yxstStr = yxstStr + document.forms[0].yxstlb.options[i].value;
	}
	var url = "/xgxt/modiData.do?realTable=";
	url += window.dialogArguments.document.forms[0].realTable.value;
	url += "&doType=save";
	url += "&tableName=";
	url += window.dialogArguments.document.forms[0].tableName.value;
	url += "&pk=";
	url += window.dialogArguments.document.forms[0].pk.value;
	url += "&pkValue=";
	url += document.forms[0].pkValue.value;
	url += "&from=";
	url += window.dialogArguments.document.forms[0].act.value;
	url += "&yxstlbStr=";
	url += yxstStr;
	document.forms[0].action = url;
	document.forms[0].submit();
	alert("保存成功！");
	window.dialogArguments.document.getElementById("search_go").click();
	Close();
}
function chgDisp(eID) {
	var obj = document.getElementById(eID);
	var xxdm = document.getElementById('xxdm').value;
	var isFdy = document.getElementById('isFdy').value;
	if (xxdm=='12764' && 'true'==isFdy) {
		obj.value='bjdm';
		obj.disabled=true;
		
	}
	if (obj.value == "bjdm") {
		//dispZy.style.display = "";
		document.getElementById("zy").disabled = false;
		//dispBj.style.display = "";
		document.getElementById("bj").disabled = false;
	} else {
		if (obj.value == "zydm") {
		//dispZy.style.display = "";
			document.getElementById("zy").disabled = false;
		//dispBj.style.display = "none";
			document.getElementById("bj").disabled = true;
		} else {
			if (obj.value == "xydm") {
		//dispZy.style.display = "none";
				document.getElementById("zy").disabled = true;
		//dispBj.style.display = "none";
				document.getElementById("bj").disabled = true;
			}
		}
	}
}
function chgDispconf(eID) {
	var obj = document.getElementById(eID);
	if (obj.value == "bjdm") {
		//dispZy.style.display = "";
		document.getElementById("zy").disabled = false;
		//dispBj.style.display = "";
		document.getElementById("bj").disabled = false;
	} else {
		if (obj.value == "zydm") {
		//dispZy.style.display = "";
			document.getElementById("zy").disabled = false;
		//dispBj.style.display = "none";
			document.getElementById("bj").disabled = true;
		} else {
			if (obj.value == "xydm") {
		//dispZy.style.display = "none";
				document.getElementById("zy").disabled = true;
		//dispBj.style.display = "none";
				document.getElementById("bj").disabled = true;
			}
		}
	}
}
function changeJxbz(){
   if(document.forms[0].jxbz.value!=""){
      document.forms[0].oracleList.options.length = 0;
      for(i=0;i<document.forms[0].excelList.options.length;i++){
		document.forms[0].oracleList.options[document.forms[0].oracleList.options.length]=new Option((i+1)+document.forms[0].jxbz.value,(i+1)+document.forms[0].jxbz.value);
	  }
	  }
	else 
	  return false;
}

function subBzList(){
	var i;
	if(document.forms[0].mappingList.options.length<=0){
		alert("对应字段不能为空!");
		return false;
	}
	for(i=0;i<document.forms[0].mappingList.options.length;i++){
		document.forms[0].mappingItems.value=document.forms[0].mappingItems.value+"!!SplitSignOne!!!!SplitSignTwo!!";
		document.forms[0].mappingItems.value=document.forms[0].mappingItems.value+document.forms[0].mappingList.options[i].value;
	}
    document.forms[0].submit();
}
/*
function initFdyList(fdyInfo){
	var gnmk = new Array();
	var i = 1;
	var j = 1;
	var gnTwo = "";
	gnmk = fdyInfo.split(splitSignOne);
	var printOut = "";
	for (i = 1; i < gnmk.length; i++) {
		gnmkcf = gnmk[i].split(splitSignTwo);
		if (gnmkcf[1] != gnTwo) {
			gnTwo = gnmkcf[1];
			printOut += "</div>";
			printOut += "<div id='menu" + (j) + "'>";
			printOut += "<input onclick='chgCBox(this)' name='topBox' type='checkbox' style='border:0px'><span onclick='onoff(this,1)' style='cursor:hand'>";
			printOut += gnmkcf[1];
			printOut += "</span></div>";
			printOut += "<div id='menu" + (j++) + "_child'> ";
		}
		printOut += "<input name='childBox' value='";
		printOut += gnmkcf[4];
		printOut += "' onclick='chgFBox(this)' type='checkbox' style='border:0px;margin-left:25px;'><span style='margin-left:5px;'>";
		printOut += gnmkcf[2] + "（";
		printOut += gnmkcf[3] + ", " + gnmkcf[5];
		printOut += "）</span><br>";
	}
	document.getElementById("contxt").innerHTML = printOut;
}
function chgCBox(obj){
	var obFName = obj.parentNode.id + "_child";
	var chkB = obj.checked;
	var childsObP = document.getElementById(obFName);
	var childsOb = childsObP.getElementsByTagName("input");
	for(var i = 0 ;i < childsOb.length; i++){
		childsOb[i].checked = chkB;
	}
}
function chgFBox(obj){
	var obCName = obj.parentNode.id;
	var obFName = obCName.replace("_child","");
	var fNod = document.getElementById(obFName).getElementsByTagName("input")[0];
	var childsObP = document.getElementById(obCName);
	var childsOb = childsObP.getElementsByTagName("input");
	fNod.checked = true;
	for(var i = 0 ;i < childsOb.length; i++){
		if(!childsOb[i].checked){
			fNod.checked = false;
			break;
		}
	}
}
*/
    
function addDisDormList(){
	  var comp = "no"; 
      if(document.forms[0].xb.value==null&&document.forms[0].xb.value==""){
        alert("请先选择性别!");
        return false;
      } 
      if(document.forms[0].xydm.value==null||document.forms[0].xydm.value==""){
        alert("请先选择"+jQuery("#xbmc").val()+"!");
        return false;
      }
      if(document.forms[0].xxdm.value == "12872"){//杭州职业技术学院
      	if(document.forms[0].bjdm.value==null||document.forms[0].bjdm.value==""){
        	alert("请先选择班级!");
        	return false;
      	}
      	var oracleListIndex = document.forms[0].oracleList.selectedIndex;
      	var bjIndex = document.forms[0].bj.selectedIndex;
      	var bjV = document.forms[0].bj.options[bjIndex].value;
	  	var bjT = document.forms[0].bj.options[bjIndex].text;
      }else{
      	var oracleListIndex = document.forms[0].oracleList.selectedIndex;
      	var xyIndex = document.forms[0].xy.selectedIndex;
      	var xyV = document.forms[0].xy.options[xyIndex].value;
	  	var xyT = document.forms[0].xy.options[xyIndex].text;
      }
      var addOracleV = document.forms[0].oracleList.options[oracleListIndex].value;
	  var addOracleT = document.forms[0].oracleList.options[oracleListIndex].text;
	  if(document.forms[0].xxdm.value == "12872"){//杭州职业技术学院
	  var clin = "已分配栏中已存在该宿舍编号！";
	   	if(addOracleV!=""){	
	   		for (i = 0; i < document.forms[0].sql.options.length; i++) {
	   			if(document.forms[0].fpfs.value=='ass'){//按宿舍				
		           if(bjT + "/" + addOracleV == document.forms[0].sql.options[i].text){
			        	comp = "yes";
		           }else if(document.forms[0].sql.options[i].text.indexOf(bjT + "/" + addOracleV+"/")!=-1){
		           	    comp = "yes";
		           	    clin = "已分配栏中已\"按床位\"分配该宿舍编号！"; 
		           }	
	   			}else{
	   			   if(bjT + "/" + addOracleV == document.forms[0].sql.options[i].text){
			        comp = "yes";
			        clin = "已分配栏中已存在该宿舍床位！";
		           }else if(bjT+"/"+addOracleV.substr(0,addOracleV.indexOf("/")+addOracleV.substr(addOracleV.indexOf("/")+1,addOracleV.length).indexOf("/")+1)==document.forms[0].sql.options[i].text){
		           	comp = "yes";
		           	clin = "已分配栏中已\"按宿舍\"分配该宿舍编号！";
		           }
	   			}
	        }
	     	if(comp == "yes"){
	        	alert(clin);
	        	return false;
	        }else{	        	          
	   		document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(bjT + "/" + addOracleV, bjV + "/" + addOracleT);  	
	    	if(document.forms[0].fpfs.value=='ass'){//按宿舍		    	       		
	  			totalBed.innerText=parseInt(totalBed.innerText)+parseInt(addOracleV.substring(addOracleV.lastIndexOf("/")+1,addOracleV.length));
      			if(document.forms[0].xb.value=='男'){
       				 boyBed.innerText=parseInt(boyBed.innerText)+parseInt(addOracleV.substring(addOracleV.lastIndexOf("/")+1,addOracleV.length));
      			}else if(document.forms[0].xb.vlaue=='女'){
       		 		girlBed.innerText=parseInt(girlBed.innerText)+parseInt(addOracleV.substring(addOracleV.lastIndexOf("/")+1,addOracleV.length));	  		
      			}
	   	 	}else{//按床位	
	    		totalBed.innerText=parseInt(totalBed.innerText) + 1;
      			if(document.forms[0].xb.value=='男'){
        			boyBed.innerText=parseInt(boyBed.innerText) + 1;
      			}else if(document.forms[0].xb.value=='女'){
        			girlBed.innerText=parseInt(girlBed.innerText) + 1;
      			}		    			
	    	}
	         document.forms[0].oracleList.options[oracleListIndex]=null;
	        }
	   	 }		
	  }else{
	  	if(addOracleV!=""){	
	  	  for (i = 0; i < document.forms[0].sql.options.length; i++) {			
		      if(xyT + "/" + addOracleV == document.forms[0].sql.options[i].text){
			     comp = "yes";
		      }	
	        }	       
	      if(comp == "yes"){
	      	alert("已分配栏中已存在该宿舍编号！");
	      	return false;
	      }else{		  	
              document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(xyT + "/" + addOracleT, xyV + "/" + addOracleV);//alert(xyT + "/" + addOracleT+"////////"+xyV + "/" + addOracleV)	  	      
              totalBed.innerText=parseInt(totalBed.innerText)+parseInt(addOracleV.substring(addOracleV.lastIndexOf("/")+1,addOracleV.length));
              if(document.forms[0].xb.value=='男'){
              boyBed.innerText=parseInt(boyBed.innerText)+parseInt(addOracleV.substring(addOracleV.lastIndexOf("/")+1,addOracleV.length));
              }else if(document.forms[0].xb.value=='女'){
              girlBed.innerText=parseInt(girlBed.innerText)+parseInt(addOracleV.substring(addOracleV.lastIndexOf("/")+1,addOracleV.length));
              }
              document.forms[0].oracleList.options[oracleListIndex]=null;
	      }
	    } 
	  }
      totalBoyGirl(0);
    }

function delDisDormList(){
       var sqlIndex = document.forms[0].sql.selectedIndex;
       var sqlV = document.forms[0].sql.options[sqlIndex].value;
	   var sqlT = document.forms[0].sql.options[sqlIndex].text;
	   var strTem = sqlV.split("/");
	   if(strTem.length=='4'){//按宿舍	     
	      document.forms[0].oracleList.options[document.forms[0].oracleList.options.length] = new Option(sqlV.substring(sqlV.indexOf("/")+1,sqlV.length), sqlT.substring(sqlT.indexOf("/")+1,sqlT.length));	    
          if(parseInt(totalBed.innerText)!=0){
             totalBed.innerText=parseInt(totalBed.innerText)-parseInt(sqlV.substring(sqlV.lastIndexOf("/")+1,sqlV.length));
          }
          if(document.forms[0].xb.value=='男'){
          		if(parseInt(boyBed.innerText)!=0){
            	boyBed.innerText=parseInt(boyBed.innerText)-parseInt(sqlV.substring(sqlV.lastIndexOf("/")+1,sqlV.length));
          		}
	      }else{
	      		if(parseInt(girlBed.innerText)!=0){
             	girlBed.innerText=parseInt(girlBed.innerText)-parseInt(sqlV.substring(sqlV.lastIndexOf("/")+1,sqlV.length));      
          		}
	      }
	   }else if(strTem.length=='5'){//按床位	        
	   	   if(parseInt(totalBed.innerText)!=0){
	   	      totalBed.innerText=parseInt(totalBed.innerText) - 1;
	   	   }
	   	   if(document.forms[0].xb.value=='男'){
	   	  		if(parseInt(boyBed.innerText)!=0){
	   	     	 boyBed.innerText=parseInt(boyBed.innerText) - 1;
	   	   		 }
	       }else{		 
	   	   		if(parseInt(girlBed.innerText)!=0){
	   	     	girlBed.innerText=parseInt(girlBed.innerText) - 1;
	   	  		}
	       }
	   }
       totalBoyGirl(0);
       document.forms[0].sql.options[sqlIndex]=null;
    }
    
function totalBoyGirl(i){
      if(i==0){
         document.forms[0].count.value=totalBed.innerText;
         document.forms[0].boy.value=boyBed.innerText;
         document.forms[0].girl.value=girlBed.innerText;
      }
      else{
         if(document.forms[0].xydm.value!=''){	
            totalBed.innerText=document.forms[0].count.value;
            boyBed.innerText=document.forms[0].boy.value;
            girlBed.innerText=document.forms[0].girl.value;
         }
      }
    }

function addDisBedList(){
	  var comp = "no";
      if(document.forms[0].xb.value==null&&document.forms[0].xb.value==''){
        alert("请先选择性别!");
        return false;
      } 
      if(document.forms[0].oracleList.selectedIndex<0){
        alert("请先选择床位!");
        return false;
      }
      var oracleListIndex = document.forms[0].oracleList.selectedIndex;
      var xhIndex = document.forms[0].xh.selectedIndex;
      var addOracleV = document.forms[0].oracleList.options[oracleListIndex].value;
	  var addOracleT = document.forms[0].oracleList.options[oracleListIndex].text;
	 
	  var xhV = document.forms[0].xh.options[xhIndex].value;
	  var xhT = document.forms[0].xh.options[xhIndex].text;	    
	    for (i = 0; i < document.forms[0].sql.options.length; i++) {
	    	  var sqlValue = document.forms[0].sql.options[i].value;	    	  	
		      if(addOracleV == sqlValue.substr(sqlValue.indexOf("/")+1,sqlValue.length)){
			     comp = "yes";
		      }	
	      }	
	    if(comp=="yes"){
	    	alert("已分配栏中已存在该床位！");
	    	return false;
	    }else{ 
	    compartStatus = true;	  
        document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(xhT + "/" + addOracleT,xhV + "/" + addOracleV);
        totalBed.innerText=parseInt(totalBed.innerText)+1;
        
      	if(document.forms[0].xb.value=='男'){
        	boyBed.innerText=parseInt(boyBed.innerText)+1;
      	}else if(document.forms[0].xb.value=='女'){
        	girlBed.innerText=parseInt(girlBed.innerText)+1;
      	}
      	totalBoyGirl(0);
      	document.forms[0].oracleList.options[oracleListIndex]=null;
      	document.forms[0].xh.options[xhIndex]=null;
	    }
    }
    
function delDisBedList(){
       var sqlIndex = document.forms[0].sql.selectedIndex;
       if(sqlIndex==-1){
          return false;
       }
       var sqlV = document.forms[0].sql.options[sqlIndex].value;
	   var sqlT = document.forms[0].sql.options[sqlIndex].text;
	   var sqlTstr = sqlT.substring(sqlT.indexOf("/")+1,sqlT.length);
	   var sqlTemXm = sqlTstr.substring(0,sqlTstr.indexOf("/"));//姓名
	   var sqlTtemLdmcCwsCwh = sqlTstr.substring(sqlTstr.indexOf("/")+1,sqlTstr.length);//楼栋名称床位数床位号    
	  // return false;
       document.forms[0].oracleList.options[document.forms[0].oracleList.options.length] = 
       new Option(sqlTtemLdmcCwsCwh,sqlV.substring(sqlV.indexOf("/")+1,sqlV.length));
       document.forms[0].xh.options[document.forms[0].xh.options.length] = 
       new Option(sqlT.substring(0,sqlT.indexOf("/"))+"/"+sqlTemXm, sqlV.substring(0,sqlV.indexOf("/")));
      	
       if(parseInt(totalBed.innerText)!=0){
       		 totalBed.innerText=parseInt(totalBed.innerText)-1;
       	}
       	if(document.forms[0].xb.value=='男'){
          	  if(parseInt(boyBed.innerText)!=0){
          		   boyBed.innerText=parseInt(boyBed.innerText)-1;
          		}
       		}else{
       		   if(parseInt(girlBed.innerText)!=0){
          		   girlBed.innerText=parseInt(girlBed.innerText)-1;
       		    }
       		}    
       	totalBoyGirl(0);
       	document.forms[0].sql.options[sqlIndex]=null;
       	compartStatus = true;
    }
    
function defaultDisBedList() {
//	if (document.forms[0].sql.options.length > 0) {
//	    alert("请先重置已分配列表!");
//		return false;
//	}
//	if(document.forms[0].xb.value==""){
//        alert("请先选择性别!");
//        return false;
//    }
	var len = document.forms[0].oracleList.options.length > document.forms[0].xh.options.length ? 
	document.forms[0].xh.options.length : document.forms[0].oracleList.options.length;
	var sqlLen = document.forms[0].sql.options.length;
	var i;
	var j;
	var xhV;
	var xhT;
	var addOracleV;
	var addOracleT;
	for (i = 0; i < len; i++) {
		xhV = document.forms[0].xh.options[i].value;
		xhT = document.forms[0].xh.options[i].text;
		
		addOracleV = document.forms[0].oracleList.options[i].value;
		addOracleT = document.forms[0].oracleList.options[i].text;
		
		document.forms[0].sql.options[sqlLen+i] = 
		new Option(xhT + "/" + addOracleT, xhV + "/" + addOracleV);	
		compartStatus = true;    	
	}
	for (j = 0; j < len; j++) {
	    document.forms[0].oracleList.options[0]=null;
        document.forms[0].xh.options[0]=null;
	}
	
//	totalBed.innerText=parseInt(totalBed.innerText)+len;
//    if(document.forms[0].xb.value=="男")
//        boyBed.innerText=parseInt(boyBed.innerText)+len;
//    else
//        girlBed.innerText=parseInt(girlBed.innerText)+len;
//    totalBoyGirl(0);
}

function clearDisList(url){
    totalBed.innerText=0;
    if(document.forms[0].xb.value=="男")
        boyBed.innerText=0;
    else
        girlBed.innerText=0;
    totalBoyGirl(0);
    refreshForm(url);
}

/**定义公用方法:数字格式及边界判断*/
function numFormatChk(obj,minV,maxV) {	
	if(obj.value == "")obj.value="0";
	var reg1 = /^\d+[.]?\d+$/g;
	if(obj.value.length == 1){
		reg1 = /^\d+$/g;
	}
	var str = obj.value;
	var r1 = str.match(reg1);
	if(r1 == null){
		alert("您输入的数字不合法，请重新输入！");
		obj.select();
		obj.focus();
		return false;
	}
	var tmpV = parseFloat(str);
	if(minV != null && tmpV < minV){
		alert("您输入的值过小（最小为：" + minV + "），请重新输入！");
		obj.select();
		obj.focus();
		return false;
	}
	if(maxV != null && tmpV > maxV){
		alert("您输入的值过大（最大为：" + maxV + "），请重新输入！");
		obj.select();
		obj.focus();
		return false;
	}
	return true;
}
/**当下拉框的选项发生改变时的操作*/
function sel_onChange(obj,thisObj,url){
	document.forms[0].aNd.value=thisObj.options[thisObj.selectedIndex].text;
	request.setAttribute(obj,obj.value);
	refreshForm(url);
}
/**学生活动奖学金信息*/
function stuHdJxjInfoWin(objTr){
	curr_row = objTr;
	var url="/xgxt/stu_archives_info_details.do?xh="+curr_row.cells[1].innerText;
	showTopWin(url,800,600);
}
/*专项奖学金汇总增加操作*/
function hzbMake(doType){
	var pkValue = "";
	var url = "/xgxt/specialprise.do?realTable=";
	var tmp = document.forms[0].realTable.value;
	var w = 550;
	var h = 450;
	url += tmp;
	url += "&act=";
	url += doType;
	if(doType == "newpage"){
		url = url.replace("specialprise","xszxjxjhzb");
		showTopWin(url,800,600);
	} else if(doType == "modipage"){
		if(curr_row == null){
			alert("请选择某一行记录！");
			return false;
		} else {
		url = url.replace("specialprise","xszxjxjhzb");
		url += "&pkValue=";
		url += (curr_row.cells[6].innerText+curr_row.cells[2].innerText+curr_row.cells[1].innerText);
		showTopWin(url,800,600);
		}
	} else if(doType == "save"){
		url += "&pkValue=";
		url += document.forms[0].xh.value;
		url += document.forms[0].zxjxjdm.value;
		url += document.forms[0].lastndfw.value;
		document.forms[0].action = url;
		document.forms[0].submit();
		window.close();
		window.dialogArguments.document.all("search_go").click();
		return false;
	} else if (doType == "del"){
		url += "&pkValue=";
		url += (curr_row.cells[6].innerText+curr_row.cells[2].innerText+curr_row.cells[1].innerText);
		document.forms[0].action = url;
		document.forms[0].submit();
		return false;
	}
}

function removeXnXq(){//if the result is for searching student information
    var tableName = document.getElementById("realTable").value;
	if(tableName == "xsxxb"){
		document.forms[0].xn.disabled = true;
		document.forms[0].xq.disabled = true;
	}
}

 

function checkSj(sj) {
	var eles = sj.split("-");
	var flag = true;
	var len = 0;
	var ykxx = document.getElementById("ykxx").checked;	
	if(ykxx){
		len = 4;
	} else {
		len = 2;
	}	
	var i = 0;
	while(i<len){
		var str = ((i==0||i==2)?"开始时间":"结束时间");
		var str2 = ((i==0||i==1)?"活动":"音控");		
		var sj = document.getElementById(eles[i]).value;		
		var hh = sj.substr(0,2);
		var mi = sj.substr(2,2);
		var ss = sj.substr(4,2);
		if(hh>23||mi>59||ss>59){
			alert(str2+str+"格式非法");
			flag = false;
			break;
		} else if(sj.length<6){
			alert(str2+str+"长度非法");
			flag = false;
			break;
		}
		i++;
	}
	if(flag){		
		if(document.getElementById(eles[0]).value>document.getElementById(eles[1]).value){
			alert("活动开始时间晚于结束时间");
			flag = false;
		}	
		if(ykxx&&flag){
			var hdkssj = document.getElementById(eles[0]).value;
			var hdjssj = document.getElementById(eles[1]).value;
			var ykkssj = document.getElementById(eles[2]).value;
			var ykjssj = document.getElementById(eles[3]).value;
			if(ykkssj>ykjssj&&flag){
				alert("音控开始时间晚于结束时间");
				flag = false;
			}
			if(ykkssj<hdkssj||ykkssj>hdjssj||ykjssj<hdkssj||ykjssj>hdjssj){
				alert("音控时间必须在活动时间内");
				flag = false;
			}
		}		
	}
	return flag;
}

function checkYkxx(){	
	var ykxx = document.getElementById("ykxx").checked;
	if(ykxx){
		if($("yk1")){document.getElementById("yk1").style.display = ""};
		if($("yk2")){document.getElementById("yk2").style.display = ""};
	}else{
		if($("yk1")){document.getElementById("yk1").style.display = "none"};
		if($("yk2")){document.getElementById("yk2").style.display = "none"};
	}
}

function checkLpxx(){	
	var lpxx = document.getElementById("lpxx").checked;
	if(lpxx){
		document.getElementById("lp1").style.display = "";
		document.getElementById("lp2").style.display = "";
		document.getElementById("lp3").style.display = "";
	}else{
		document.getElementById("lp1").style.display = "none";
		document.getElementById("lp2").style.display = "none";
		document.getElementById("lp3").style.display = "none";
	}
}

function listPlayConf(url) {
	document.forms[0].go.value = "go";
    refreshForm(url);
	return true;
//	var jxjMsg = document.getElementById("jxjdm").value;
//	var xyMsg = document.getElementById("xy").value;
//	var zyMsg = document.getElementById("zy").value;
//	var bjMsg = document.getElementById("bj").value;
//	if ((xyMsg + zyMsg + jxjMsg + bjMsg) == "") {
//		if (confirm("您没有选择任何条件,查询将耗费较长时间.\n确定要继续吗?")) {
//			document.forms[0].go.value = "go";
//			refreshForm(url);
//			return true;
//		} else {
//			return false;
//		}
//	} else {
//		document.forms[0].go.value = "go";
//		refreshForm(url);
//		return true;
//	}
}

function chkPlayOne(url, act) {
	if (curr_row == null) {
		return false;
	} else {
		url +="?act=";
		url += act;
	  	url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
	  	showTopWin(url,800,400);
	  	return true;
	}
}



function checkDxzsx(){
	var url = "/xgxt/topic_object.do";
	refreshForm(url);
	return true;
}

function checkRscb(){
	var url = "/xgxt/topic_object.do?act=rscb";
	refreshForm(url);
	return true;
}

function doSel(strYhm,strXm,selName){
	var yhm = new Array();
	var xm = new Array();
	yhm = strYhm.split(splitSignOne);	
	xm = strXm.split(splitSignOne);
	
	len = yhm.length;
	
	document.getElementById(selName).options.length = 0;
	document.getElementById(selName).options.length = len-1;
	
	for (i = 1; i < len; i++) {
		document.getElementById(selName).options[i-1].value=yhm[i];
		document.getElementById(selName).options[i-1].label=xm[i];
	}
}

function doSelAll(fromSelName,toSelName){
	var fromSel = document.getElementById(fromSelName);
	var toSel = document.getElementById(toSelName);
	
	if (fromSel.options.length==0){
		if(fromSelName == "dx"){
			alert("已全部删除");
		} else {
			alert("已全部加入");
		}
		return false;
	}
	
	var len = 0;
	var strYhm = "";
	var strXm = "";
	
	len = toSel.options.length;
	for (i = 0; i < len; i++) {
		strYhm += splitSignOne+toSel.options[i].value;
		strXm += splitSignOne+toSel.options[i].label;
	} 
	
	len = fromSel.options.length;
	for (i = 0; i < len; i++) {
		strYhm += splitSignOne+fromSel.options[i].value;
		strXm += splitSignOne+fromSel.options[i].label;
	}
	
	doSel(strYhm,strXm,toSelName);
	fromSel.options.length = 0;
}


function doSelSome(fromSelName,toSelName){
	var fromSel = document.getElementById(fromSelName);
	var toSel = document.getElementById(toSelName);

	if (fromSel.selectedIndex < 0) {
		if (fromSel.options.length>0){
			alert("请选择用户");
			return false;
		} else {
			if(fromSelName == "dx"){
				alert("已全部删除");
			} else {
				alert("已全部加入");
			}
			return false;
		}
	}
	
	var len = 0;
	var strYhm = "";
	var strXm = "";
	var strUYhm = "";
	var strUXm = "";
	
	len = toSel.options.length;
	for (i = 0; i < len; i++) {
		strYhm += splitSignOne+toSel.options[i].value;
		strXm += splitSignOne+toSel.options[i].label;
	}
	
	len = fromSel.options.length;
	for (i = 0; i < len; i++) {
		if(fromSel.options[i].selected==true){
			strYhm += splitSignOne+fromSel.options[i].value;
			strXm += splitSignOne+fromSel.options[i].label;
			
		} else {
			strUYhm += splitSignOne+fromSel.options[i].value;
			strUXm += splitSignOne+fromSel.options[i].label;			
		}
	}
	
	doSel(strYhm,strXm,toSelName);
	doSel(strUYhm,strUXm,fromSelName); 
}

function saveDxz(){

	var dxzmc = document.getElementById("dxzmc");
	if(dxzmc.value == ""){
		alert("请填写对象组名称");
		return false;
	}
	var dxSel = document.getElementById("dx");
	var len = 0;
	var strYhm = "";
	var strXm = "";
	len = dxSel.options.length;
	for (i = 0; i < len; i++) {
		strYhm += splitSignOne+dxSel.options[i].value;
		strXm += splitSignOne+dxSel.options[i].label;
		
	}
	url = "/xgxt/topic_object.do?dxz=" + strYhm;
	refreshForm(url);
	return true;

}

function saveXfhj(mustFill){
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		}
	}
	url = "/xgxt/xfhj_apply.do?doType=save";
	refreshForm(url);
	return true;
}

function checkGo(url) {
	document.forms[0].go.value = "go";
    refreshForm(url);
	return true;
}

function showWxry(){
	if(document.getElementById("wxsj").value != ""
		&&document.getElementById("wxsj").value != "未维修"){
		document.getElementById("wxry").style.display ="";
	} else {
		document.getElementById("wxry").style.display = "none";
	}
}

function checkWxsj(){
	if(document.getElementById("wxsj").value != ""){
		if(document.getElementById("bxsj").value > document.getElementById("wxsj").value){		
		alert("维修时间早于报修时间");
		document.getElementById("wxsj").value = "";
	}
	showWxry();
	}	
}


function jsys(){
	var str = document.getElementById("lssj").value;
	var time = str.split("#");
	var kssj = new Date(time[0],time[1]-1,time[2],time[3],time[4],time[5],time[6]);
	var now = new Date();
	var time = now.getTime() - kssj.getTime();
	var milli = time%1000;
	var second = ((time - milli)/1000)%60;
	var minute = (time - milli - second*1000)/1000/60%60;
	var hour = (time- milli - second*1000 - minute*1000*60)/1000/60/60%24;
	var day = (time- milli - second*1000 - minute*1000*60 - hour*1000*60*60)/1000/60/60/24;
	
	while(hour.length<2) hour = '0' + hour; 
	while(minute.length<2) minute = '0' + minute; 
	while(second.length<2) second = '0' + second; 
	while(milli.length<3) milli = '0' + milli;
	
	if(document.getElementById){
		if(document.getElementById) 
			var obj = document.getElementById("showdate");
		if(document.all) 
			var obj = document.all("showdate");
		obj.innerHTML = day+"天"+hour+"小时"+minute+"分"+second+"秒"+milli;
		kssj.setMilliseconds(kssj.getMilliseconds()+1);
		var year = kssj.getFullYear();
		var month = kssj.getMonth()+1;
		var day = kssj.getDate();
		var hour = kssj.getHours();
		var minute = kssj.getMinutes();
		var second = kssj.getSeconds();
		var milli = kssj.getMilliseconds();
		document.getElementById("lssj").value = year+"#"+month+"#"+day+"#"+hour+"#"+minute+"#"+second+"#"+milli;
		setTimeout('jsys()',1);
	}
}

function checkZfrq(mustFill){	
//	var jzrq = document.getElementById('jzrq').value.replace(" ","");
//	var rzrq = document.getElementById('rzrq').value.replace(" ","");
//	if(rzrq!=""&&jzrq!=""
//	           &&rzrq>=jzrq){
//		alert('入住日期晚于退房日期');
//	} else {
	    var xh = $("xh").value;
	    var eles = mustFill.split("-");
	    for (i = 0; i < eles.length; i++) {
		   if (document.getElementById(eles[i]).value == "") {
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		   }
	    }	       
	    if(xh!=""){	     
	       getXjydInfo.getColumnEx("xszsxxb","xh",xh,function(data){				
					if(data){					   
					    if(confirm('该生已入住，此操作将修改该生住宿信息，\n确定要提交吗？')){
						    dataDoSave('xh-ssbh-cwh-sslx');
						}else{
						    return false;
						}											     					  
					}else{
					    dataDoSave('xh-ssbh-cwh-sslx');
					}					
				});
	    }	    		 	      	      	
//	}	
}

function countCpzf(){
	var dyjf = document.getElementById('dcj').value;
	var zyjf = document.getElementById('zcj').value;
	var tyjf = document.getElementById('tcj').value;
	var jnjf = document.getElementById('jnf').value;
	if(dyjf == null || dyjf == "" || dyjf == " "){
		dyjf = '0';
		document.getElementById('dcj').value = "";
	}
	if(zyjf == null || zyjf == "" || zyjf == " "){
		zyjf = '0';
		document.getElementById('zcj').value = "";
	}
	if(tyjf == null || tyjf == "" || tyjf == " "){
		tyjf = '0';
		document.getElementById('tcj').value = "";
	}
	if(jnjf == null || jnjf == "" || jnjf == " "){
		jnjf = '0';
		document.getElementById('jnf').value = "";
	}
	if (jnjf > 30){
		jnjf = 30;
		document.getElementById('jnf').value = 30;
	}	
	document.getElementById('cpf').value = (Math.round(parseInt(dyjf) * 0.3) + parseInt(zyjf) * 0.6 + parseInt(tyjf) * 0.1 + parseInt(jnjf)).toFixed(3);
	if(document.getElementById('cpf').value.match(/^\d+\.{0,1}\d{0,3}$/) == null){
		alert("积分格式有误，请确认");
		return false;
	}
}

//预约
function yuyue(bh) {	
		var url="/xgxt/xljk_zxszyAtion.do?act=xljk_zxszygl&doType=Xssq_Zycx&zxxbh="
		url=url+bh
		//alert(url);
		document.forms[0].action = url;
		document.forms[0].submit();
		return true;
}
function judgeChar(){
	if(document.getElementById('xydykpf').value.match(/^\d+\.{0,1}\d{0,3}$/) == null || document.getElementById('xydykpf').value > 100){
		alert(jQuery("#xbmc").val()+"德育考评分格式必需是3位不大于100的有效数字，请确认");
		document.getElementById('xydykpf').focus();
		return false;
	}else
	if(document.getElementById('gydykpf').value.match(/^\d+\.{0,1}\d{0,3}$/) == null || document.getElementById('gydykpf').value > 100){
		alert("公寓德育考评分格式必需是3位不大于100的有效数字，请确认");
		document.getElementById('gydykpf').focus();
		return false;
	}else
	if(document.getElementById('zcj').value.match(/^\d+\.{0,1}\d{0,3}$/) == null || document.getElementById('zcj').value > 100){
		alert("智成绩格式必需是3位不大于100的有效数字，请确认");
		document.getElementById('zcj').focus();
		return false;
	}else
	if(document.getElementById('tcj').value.match(/^\d+\.{0,1}\d{0,3}$/) == null || document.getElementById('tcj').value > 100){
		alert("体成绩格式必需是3位不大于100的有效数字，请确认");
		document.getElementById('tcj').focus();
		return false;
	}else
	if(document.getElementById('gzxxcx').value.match(/^\d+\.{0,1}\d{0,3}$/) == null || document.getElementById('gzxxcx').value > 100){
		alert("创新分格式必需是3位不大于100的有效数字，请确认");
		document.getElementById('gzxxcx').focus();
		return false;
	}else{
		return true;
	}
}

function viewMore_LRH(doType,zxsbh){
		var pkValue = "";
		var zxsbh=zxsbh;
		var url = "";
		//var tmp = document.forms[0].realTable.value;
		var w = 550;
		var h = 450;
		if(doType=="View_Zxsxx"||doType=="View_Xssqzxs")
		{
			url="/xgxt/xljk_zxsxx_view.do?doType="+doType+"&zxsbh="+zxsbh;
			//alert(url);
			showTopWin(url,w,h);
		}
		if (doType == "del") {
			if (curr_row == null) {
				alert("请选择要删除的数据！\n（单击相应的行）");
				return false;
				} 
			else {
					if (confirm("确定要删除该行数据吗？")) {
			//	alert(curr_row.cells[0].innerText);
					pkValue =curr_row.cells[0].innerText;
					url="/xgxt/xljk_zxsxx_del.do?act=xljk_zxsxx&doType="+doType+"&zxsbh="+pkValue;
				//	alert(url);
					refreshForm(url);
					return true;
					} 
					else {
					return false;
					}
				}
			}
		if (doType == "update") {
			if (curr_row == null) {
				alert("请选择要修改的数据！\n（单击相应的行）");
				return false;
				} 
			else {
					if (confirm("确定要修改该行数据吗？")) {
			//	alert(curr_row.cells[0].innerText);
					pkValue =curr_row.cells[0].innerText;
					url="/xgxt/xljk_zxsxx_update.do?act=xljk_zxsxx&zxxbh="+pkValue;
				//	alert(url);
				    showTopWin(url,w,h);
					return true;
					} 
					else {
					return false;
					}
				}
				return;
		}
	}


function AutoAccountCj(url){
	var bjdm = document.getElementById("bj").value;
//	var xn = document.getElementById("xn").value;
//	var xq = document.getElementById("xq").value;
	if(bjdm == "" || bjdm == null){
		alert("请选择要计算的班级！");
		return false;
//	} else if(xn == "" || xn == null || xq == "" || xq==null){
//		alert("请选择要计算的学年学期！");
//		return false;
	}else{
		if(url=='/xgxt/AutoAccount.do?type=drjs'){
			if(confirm("导入后重新计算将会重新计算出当前学年学期当前班级学生的综合素质分\n\n自动计算将耗费较长时间，确定要开始自动计算吗？")){
			var dd_html = "";
			dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>正在自动计算，请稍候......<br><br>";
			dd_html += "<span class='roll_tip'></span>";
			dd_html += "</td></tr></table>";
			showDiv(dd_html, 300, 120);
			alert("点击\"确定\"后开始计算！");
			refreshForm(url);
			return true;
			}else{
				return false;
			}
		}else{
		if(confirm("自动计算将清空当前学年学期当前班级的综合素质测评表中的智育总分与体育总分，\n再以学生成绩表与学生体育成绩表为基表，重新计算出当前学年学期当前班级学生的智育总分与体育成绩,\n\n自动计算将耗费较长时间，确定要开始自动计算吗？")){
			var dd_html = "";
			dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>正在自动计算，请稍候......<br><br>";
			dd_html += "<span class='roll_tip'></span>";
			dd_html += "</td></tr></table>";
			showDiv(dd_html, 300, 120);
			alert("点击\"确定\"后开始计算！");
			refreshForm(url);
			return true;
		}else{
			return false;
		}
		}
	}
}
//function viewData(doType){
//	var page = document.forms[0].titName.value;
//	var pkValue = "";
//	var pk = document.forms[0].pk.value;
//	var url = "/xgxt/detailData.do?act=";
//	url += doType;
//	url += "&tabName=";
//	url += page;
//	url += "&pk=";
//	url += pk;
//	url += "&pkValue=";
//	
//	if (doType == "modi" && curr_row == null) {
//		alert("请选择要修改的数据！\n（单击相应的行）");
//		return false;
//	}
//	
//	if (doType == "del"){
//		if(curr_row == null){
//			alert("请选择要修改的数据！\n（单击相应的行）");
//			return false;
//		}else{
//			pkValue = curr_row.getElementsByTagName("input")[0].value;
//			url += pkValue;
//			if(confirm("确定要删选中记录吗？")){
//				refreshForm(url);
//				return true;
//			}else{
//				return false;
//			}
//		}
//	}
//	if(doType != "add"){
//		pkValue = curr_row.getElementsByTagName("input")[0].value;
//	}
//	
//	url += pkValue;
//	showTopWin(url, 600, 450);
//}

//function dataSave(mustFill){
//	var eles = mustFill.split("-");
//	for (i = 0; i < eles.length; i++) {
//		if (document.getElementById(eles[i]).value == "") {
//			alert("请将带\"*\"号的项目输入完整！");
//			return false;
//		}
//	}
//	var url = "/xgxt/modiData3.do?tabName=";
//	url += document.forms[0].tabName.value;
//	document.forms[0].action = url;
//	document.forms[0].submit();
//	alert("保存成功！");
//	window.dialogArguments.document.getElementById("search_go").click();
//	Close();
//}

function dataExport２(url) {
	document.forms[0].action = "/xgxt/expData.do?realTable=view_stu_info_xjyd_diff&tableName=view_stu_info_xjyd_diff";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}

function showNewStuStatusInfo(w,h){	
	var rsStr="";
	var titleStr="";	
	getXjydInfo.getStuXjYdXx(function (data){	
		titleStr=data.title;
		rsStr = data.source;
	var records = rsStr.split("@@"); 
	var showHtml = "<center>";
	showHtml  += "<table width='98%' class='tbstyle' id='newTable'>";	
	showHtml  += "<thead>";
	showHtml  += "<tr><td colspan='7'><center><b>新的学籍异动信息</b></center></td></tr>";
	showHtml  += "<tr>";	
	var titleArr = titleStr.split("!!");
	for(var i=0;i<titleArr.length;i++){
		showHtml += "<td>";
		showHtml += titleArr[i];
		showHtml += "</td>";
	}
	showHtml += "</tr></thead>";	
	showHtml += "<tbody>";
	for(var i=0;i < records.length; i++){
		var singleRecord = records[i].split("!!");
		showHtml += "<tr>";
		for(var j=0;j<singleRecord.length;j++){
			showHtml += "<td>";
			showHtml += singleRecord[j];
			showHtml += "</td>";
		}	
		showHtml += "</tr>";	
	}
	
	showHtml += "<tr><td  colspan='3'><center><div class='buttontool'>";
	showHtml += "<button class='button2' onclick='closeAdd()' style='width:80px' id='buttonClose'>";
	showHtml += "确定";
	showHtml += "</button></td><td colspan='4'>";
	showHtml += "<div class='buttontool'>";
	showHtml += "<button class='button2' onclick='dataExport２()' style='width:80px'>";
	showHtml += "导出数据";
	showHtml += "</button></td></tr>";
	showHtml += "</tbody></table>";
	showDiv(showHtml,w,h);	
	});
}


function delAllPriseCondi() {
		if (confirm('确定要删除所有的数据吗？')) {
			var url = "/xgxt/prise_condition.do?delALL=1";
			document.forms[0].go.value = "delAll";
			refreshForm(url);
		}
		return;
}

function identifyInt(){
	var identifydata = "";
	var xxdm = "";
	if($("xxdm")){
		xxdm = document.getElementById("xxdm").value;
	}
	if($("hbje")){
		identifydata = document.getElementById("hbje").value;
		if(identifydata.match(/^\d+\.?\d{0,3}$/)==null){			
			alert("数据格式不正确");
			return false;
		}
	}
	if($("rsTable")){
		if(xxdm == "11417"){
			for(var i =2; i<document.getElementById("rsTable").rows.length-1;i++){
				identifydata = document.getElementById("rsTable").rows[i].getElementsByTagName("input")[0].value;
				if(identifydata!=""){
					if(identifydata.match(/^\d+\.{0,1}\d{0,3}$/)==null){				
						alert("数据格式不正确!");
						return false;
					}
				}
			}
		}
		else{
		for(var i =1; i<document.getElementById("rsTable").rows.length-1;i++){
			identifydata = document.getElementById("rsTable").rows[i].getElementsByTagName("input")[0].value;
			if(identifydata!=""){
				if(identifydata.match(/^\d+\.{0,1}\d{0,3}$/)==null){				
					alert("数据格式不正确!");
					return false;
				}
			}
		}
	}
	}
	if($("xtydm")){
		identifydata = document.getElementById("xtydm").value;
		if(identifydata.match(/^\d+\.{0,1}\d{0,3}$/)==null){
			alert("代码必需为数字");
			return false;
		}
	}
	return true;
}

//取选择框文本
function selIndex(obj,othObj){
	if(obj.options[obj.selectedIndex].text == "" || obj.options[obj.selectedIndex].text == null){
		document.getElementById(othObj).value = "";
		return false;
	}
	var optionValue = document.getElementById(othObj).value;
	if(optionValue.search(obj.options[obj.selectedIndex].text) != -1){
		return false;
	}else{
		optionValue += obj.options[obj.selectedIndex].text;
		optionValue += ",";
	}
	document.getElementById(othObj).value = optionValue;
}
//取选择框值1
function selIndex1(obj,othObj){
	if(obj.value == "" || obj.value == null){
		document.getElementById(othObj).value = "";
		return false;
	}
	var optionValue = document.getElementById(othObj).value;
	if(optionValue.search(obj.value) != -1){
		return false;
	}else{
		optionValue += obj.value;
		optionValue += ",";
	}
	document.getElementById(othObj).value = optionValue;
}

//取选择框值2
function selIndex2(obj,othObj){
     
	if(obj.value == "" || obj.value == null){
		document.getElementById(othObj).value = "";
		return false;
	}
	var optionValue = document.getElementById(othObj).value;
	if(optionValue.search(obj.value) != -1){
		return false;
	}else{
		GetListData.getXm(obj.value,function yhxm(data){
           optionValue += data;   
           optionValue += ",";   
         document.getElementById(othObj).value = optionValue;
             
         })   
	}	
}


function identifydata(obj){
	var identifydata = "";
	identifydata = obj.value;
	if(identifydata.match(/^\d+\.{0,1}\d{0,3}$/)==null){
		alert("数据格式不正确");
		obj.value = 0;
		return false;
	}else{
		return true;
	}
}

function IsNoEmpty(mustFill){
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		}
	}
	return true;
}

function showTips(msg) {
	msg = (msg == null)?"数据处理中,请稍候...":msg;
	var dd_html = "";	
	
	
	tipsConv = document.createElement("DIV");
	tipsConv.id = "tipsConv";
	tipsConv.oncontextmenu = function(){return false;};
	tipsConv.onSelectstart = function(){return false;};	
	tipsConv.style.cssText = "background-color:#CCCCCC;position:absolute;z-index:100;filter:alpha(opacity=20);";
	tipsConv.style.width = document.body.clientWidth;
	tipsConv.style.height = document.body.scrollHeight;
	tipsConv.style.pixelTop = 0;
	tipsConv.style.left = 0;
	tipsConv.style.display = "block";
	document.body.appendChild(tipsConv);	
	
	dd_html += "<table border=0 cellpadding=0 cellspacing=1 bgcolor=\"#000000\"";
	dd_html += "width=\"100%\" height=\"100%\"><tr>";
	dd_html += "<td bgcolor=#5C8DBE>";
	dd_html += "<marquee align=\"middle\" behavior=\"alternate\" scrollamount=\"2\" style=\"font-size:9pt\">";
	dd_html += "<font color=yellow>"
	dd_html += msg + "</font>";
	dd_html += "</marquee></td></tr></table>";
	tips = document.createElement("DIV");
	tips.id = "tipDiv";
	tips.innerHTML = dd_html;
	tips.style.cssText = "width:200px;height:30px;position:absolute;z-index:100;filter:alpha(opacity=70);";
	tips.style.pixelTop = lastScrollY + 120;
	tips.style.left = (document.body.clientWidth - 200) / 2;
	tips.style.display = "block";
	document.body.appendChild(tips);
	
	tipsF = document.createElement("IFRAME");
	tipsF.style.cssText = "width:200px;height:30px;position:absolute;filter:alpha(opacity=50);";
	tipsF.scr = "javascript:false;";
	tipsF.scrolling = "no";
	tipsF.id = "tipIFrame";
	tipsF.frameborder = "0";
    tipsF.style.width = tips.offsetWidth;
    tipsF.style.height = tips.offsetHeight;
    tipsF.style.top = tips.style.top;
    tipsF.style.left = tips.style.left;
    tipsF.style.zIndex = tips.style.zIndex - 1;
	document.body.appendChild(tipsF);
}

//统计时增加反选功能
function fx(){
	var num = document.getElementById("titList").options.length;
	var obj = document.getElementById("titList");
	document.forms[0].cols.value = "";
	for(i=0; i<num; i++){
		if(obj.options[i].text.substring(0, 1) == "√"){
			obj.options[i].text = obj.options[i].text.replace("√", "\xd7");
		}else{
			obj.options[i].text = obj.options[i].text.replace("\xd7", "√");
			if (document.forms[0].cols.value != null && document.forms[0].cols.value != "") {
				document.forms[0].cols.value = document.forms[0].cols.value + "!!SplitSignOne!!";
			}
			document.forms[0].cols.value = document.forms[0].cols.value + obj.options[i].value;
		}
	}
}

//统计时增加反选功能
function cxfx(){
	var num = document.getElementById("titList").options.length;
	var obj = document.getElementById("titList");
//	document.forms[0].cols.value = "";
	for(i=0; i<num; i++){
		if(obj.options[i].text.substring(0, 1) == "√"){
			obj.options[i].text = obj.options[i].text.replace("√", "\xd7");
		}else{
			obj.options[i].text = obj.options[i].text.replace("\xd7", "√");
//			if (document.forms[0].cols.value != null && document.forms[0].cols.value != "") {
//				document.forms[0].cols.value = document.forms[0].cols.value + "!!SplitSignOne!!";
//			}
//			document.forms[0].cols.value = document.forms[0].cols.value + obj.options[i].value;
		}
	}
}
//自动审核荣誉称号
function creditAutoChk(url){
	if (document.forms[0].jxjdm.value == "") {
		alert("请选择要自动审核的称号！");
		return false;
	} else{
		var dd_html = "";
		dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>正在自动审核，请稍候......<br><br>";
		dd_html += "<span class='roll_tip'></span>";
		dd_html += "</td></tr></table>";
		for (i = 1; i < document.getElementsByTagName("table").length; i++) {
			document.getElementsByTagName("table")[i].style.display = "none";
		}
		showDiv(dd_html, 300, 120);
		alert("点击\"确定\"后开始审核！");
		refreshForm(url);
	}
}
//自动填充代码
function AutoFill(obj,tag){
	if("add"==tag){
		document.getElementById("sztzCode").value = obj.value;
	}
}

//多选功能
function rowMoreClick(objTr,tag,evt) {
	var iRow;
	curr_row = objTr;
	var evt = evt?evt:(window.event?window.event:null);
	if(evt.srcElement == undefined){
		iRow=evt.target;
	}else{
		iRow=evt.srcElement;
	}
	do{
		iRow=iRow.parentElement;
	}while(iRow && iRow.tagName!= undefined && iRow.tagName!='TR')
	
	//Ctrl多选
	if(evt.ctrlKey){
		var j=-1;
		for(i=0;i<Rows.length;i++){
			if(iRow==Rows[i]){
				j=i;
				break;
			}
		}
		if(j!=-1){
			for(i=j;i<Rows.length-1;i++){
				Rows[i]=Rows[i+1];
			}
			Rows.length=Rows.length-1;
			iRow.style.backgroundColor = "#FFFFFF";
		}else{
			Rows[Rows.length]=iRow;
		}
	}
	else{	
		if (Rows.length!=0){
			for (i=0; i<Rows.length; i++){	
				if (Rows[i]!=null && Rows[i].tagName.toLowerCase() == "tr") {
					Rows[i].style.backgroundColor = obj_bgc;
	    		}
			}
		}
		if(document.getElementById("ycxh")){
			document.getElementById("ycxh").value=curr_row.cells[1].innerText.trim();
		}
		obj_bgc = curr_row.style.backgroundColor;
		curr_row.style.backgroundColor = cur_bgc;
		Rows.length=1;
		Rows[0]=iRow;
	}
	changeColor(iRow);
}

//选中行变色
function changeColor(E){
	for(i=0;i<Rows.length;i++){
		if(Rows[i]){
			Rows[i].style.backgroundColor=cur_bgc;
		}
	}
}//只读
function readOnly() {
	var writeAble = document.forms[0].writeAble.value;
	var tmpSel = document.getElementsByTagName("select");
	var tmpHid = document.getElementsByTagName("input");
	var tmpOth = document.getElementsByTagName("textarea");
	var tmpBut = document.getElementsByTagName("button");
	
	if(writeAble == 'no'){
	   alert("该用户只有读权限！");
	   for (i = 0; i < tmpSel.length; i++) {
		tmpSel[i].disabled = true;
	   }
	   for (i = 0; i < tmpHid.length; i++) {
		tmpHid[i].disabled = true;
	   }
	   for (i = 0; i < tmpOth.length; i++) {
		tmpOth[i].disabled = true;
	   }
	   for (i = 0; i < tmpBut.length; i++) {
		tmpBut[i].disabled = true;
	   }
	}	
}
function timeFormatChk(obj) {	
	sj = obj.value;
	sjList = sj.split("-");
	if(sj!=""){
	var reg1 = /^\d+[.]?\d+$/g;
	for( var i=0;i<sjList.length;i++){
		var r1=sjList[i].match(reg1);
		if(r1==null){
			alert("输入的时间不合法！");
			obj.focus();
		}
	}
	}
	
	}
function selAll(){
	var checkBoxArr = document.getElementsByName("checkVal");
	if(checkBoxArr == undefined || checkBoxArr.length<1){
		checkBoxArr = document.getElementsByName("cbv");
	}
	var selall = document.getElementById('selall');
	if(selall.checked==true){
		for(var i=0;i<checkBoxArr.length;i++){
			if (checkBoxArr[i].disabled==true) {
				//checkBoxArr[i].checked = false;	
			} else {
				checkBoxArr[i].checked = true;
			}
			
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			if (checkBoxArr[i].disabled==true) {
				//checkBoxArr[i].checked = false;	
			} else {
				checkBoxArr[i].checked = false;
			}
		}
	}
}

function submitFromAction(url){
	document.forms[0].action=url;
	document.forms[0].submit();
}

function bzrLoad(){
//   var xg_xxdm = document.getElementById("xxdm").value;
//   var isBzr = document.getElementById("isBzr").value;alert(xg_xxdm)
//   if(xg_xxdm="13429"){//南昌大学科学技术学院
//		if(isBzr=="yes"){
//		   document.getElementById("nj").disabled=true;
//		   document.getElementById("xy").disabled=true;
//		   document.getElementById("zy").disabled=true;
//		   document.forms[0].bj[0].style.display="none";
//		}
//	}
}	

function dctStuXh(){
    var xh = "";
    if($("xh"))xh=$("xh").value;
	if(xh!=""){
		getStuDetails.dctStuXh(xh,function(data){ 
		         if(data){      				       
		         }else{		         	
		            alert("你输入的学号不存在，请重新输入；或\n单击右边按钮查询及选定所要学号！");
		            document.forms[0].xh.value="";
		            document.forms[0].xh.focus();		            
		         }
		});
	}else{
	  return false;
	}	
}

function underDealWith(){
	var dd_html = "";
	dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>数据正在处理中，请稍候......<br><br>";
	dd_html += "<span class='roll_tip'></span>";
	dd_html += "</td></tr></table>";
	if($("buttonSave")){
		document.getElementById("buttonSave").style.display = "none";
	}
	if($("buttonClose")){
		document.getElementById("buttonClose").style.display = "none";
	}
	if($("buttonDelete")){
		document.getElementById("buttonDelete").style.display = "none";
	}
	if($("buttonReset")){
		document.getElementById("buttonReset").style.display = "none";
	}
	if($("buttonUpdate")){
		document.getElementById("buttonUpdate").style.display = "none";
	}
	if($("buttonNormal")){
		document.getElementById("buttonNormal").style.display = "none";
	}
	showDiv(dd_html, 300, 120);
}

/*
数据导入检测
*/	
function impAndChkData(){
	var realTable = "";
	var tableName = "";
	var sty = "toolbar=no,location=no,directories=no,status=yes";
	sty += ",menubar=no,scrollbars=yes,resizable=yes,width=600,height=400,top=100";
	sty += ",left=200";
	if($("realTable")) realTable = document.getElementById("realTable").value;
	if($("tableName")) tableName = document.getElementById("tableName").value;
	url = 'impAndChkData.do?method=importData';
	url += "&realTable=" + realTable;
	url += "&tableName=" + tableName;
	//showTopWin(url,600,500);
	//refreshForm(url);
	showDialog('代码维护导入',600,300,url);
	//window.open(url,'',sty);
}

/*
数据导入检测
*/
function importCheckSubmit(){
	var filepath = document.getElementById('checkFilePath').value;
	document.getElementById('filepath').value = filepath;
	document.forms[0].action="/xgxt/impAndChkData.do?method=showCheckData";
	document.forms[0].submit();	
}

/*
最终数据导入
*/
function importSubmit(){
	var filepath = document.getElementById('impFilePath').value;
	document.getElementById('filepath').value = filepath;
	
	refreshForm("/xgxt/impAndChkData.do?method=showImportData");
	//showTopWin("/xgxt/impAndChkData.do?method=showImportData");
}	

//自定义导入
function importSubmitZdy(){
	var filepath = document.getElementById('impFilePath').value;
	document.getElementById('filepath').value = filepath;
	
	refreshForm("/xgxt/impAndChkData.do?method=showZdyImportData");
	//showTopWin("/xgxt/impAndChkData.do?method=showImportData");
}

function submitList(){
	var i;
	var dzyDdTab = document.getElementById('dzyDdTab').value;
	if (document.forms[0].mappingList.options.length <= 0) {
		alert("对应字段不能为空!");
		return false;
	}
	
	var showConfirmText;
	var showRunningText;
	
	var flag = jQuery("#flag").val();
	if(flag == '1'){//进行数据检测
		showConfirmText = "确定进行数据检测吗？";
		showRunningText = "检测正在进行，请稍候......";
		
	} else if(flag == '2'){//直接导入数据
		showConfirmText = "确定导入数据检测吗？";
		showRunningText = "正在导入数据，请稍候......";
		
	}   
    if (confirm(showConfirmText)) {
    	for (i = 0; i < document.forms[0].mappingList.options.length; i++) {
    		document.forms[0].mappingItems.value = document.forms[0].mappingItems.value + "!!SplitOne!!";
    		document.forms[0].mappingItems.value = document.forms[0].mappingItems.value + document.forms[0].mappingList.options[i].value;
    	}
    	document.forms[0].mappingItems.value = jQuery("#mappingItems").val().substr("!!SplitOne!!".length);
		var dd_html = "";
		dd_html += "<table width='200' class='formlist'><tr><td height='60' align='center'>"+showRunningText+"<br><br>";
		dd_html += "";//<img src='images/loading.gif' width='180' height='20' border='0'>
		dd_html += "</td></tr></table>";
		for (i = 1; i < document.getElementsByTagName("table").length; i++) {
			///document.getElementsByTagName("table")[i].style.display = "none";
		}
		showDiv(dd_html, 300, 120);
		if (''== dzyDdTab) {
			document.forms[0].action = 'impAndChkData.do?method=checkAndImport';
		} else {
			document.forms[0].action = 'impAndChkData.do?method=checkAndImportZdy';
		}
					
		document.forms[0].submit();
		return true;
	}
    
	return false;
}
	
function onlyNumInput(obj){
    var inValue = obj.value;
    if(inValue!=""&&inValue.match(/^\d+\.{0,1}\d{0,3}$/)==null){
			alert("所输入值只能为数字！");
			obj.value="";
			obj.focus();
			return false;
		}
}	

function checkFileFormat(obj){
	if(obj.value.indexOf('.xls') < 0 ){
		alert("请选择excel文件！\n如果是excel 2007文件，请转换成2003版本以前的文件导入！");
		return false;
	}
	return true;
}

/** 
 * 默认对应所有的字段
 */
function defaultAllItemList() {
	//对应字段列表中已经有信息，返回
	if (document.getElementById('mappingList').options.length > 0) {
		return false;
	}
	var len = document.getElementById('oracleList').options.length > document.getElementById('excelList').options.length ? document.getElementById('excelList').options.length : document.getElementById('oracleList').options.length;
	var i;
	var addExcelV;
	var addExcelT;
	var addOracleV;
	var addOracleT;
	var index = 0;
	for (i = 0; i < len; i++) {
		var addOracleV = "";
		var addOracleT = "";
		addExcelV = i;
		addExcelT = document.getElementById('excelList').options[i].text;
		var oracleLen = document.getElementById('oracleList').options.length;
		for(j = 0; j<oracleLen; j++){//按名称对应
			if(addExcelT == document.getElementById('oracleList').options[j].text){
				addOracleV = document.getElementById('oracleList').options[j].value;
				addOracleT = document.getElementById('oracleList').options[j].text;
			}
		}		
		if(addOracleV != "" && addOracleT != ""){
			document.getElementById('mappingList').options[index++] = new Option(addExcelT + "------" + addOracleT, addExcelV + "!!SplitTwo!!" + addOracleV);
		}
	}
}
	
/** 
 * 添加一个对应好的字段
 */
function addOneItemList() {
	var excelListIndex = document.getElementById('excelList').selectedIndex;
	var oracleListIndex = document.getElementById('oracleList').selectedIndex;
	if (excelListIndex < 0 || oracleListIndex < 0 || document.getElementById('mappingList').options.length == document.getElementById('oracleList').options.length || checkDoubleItemT()) {
		return false;
	}
	var addExcelV = excelListIndex;
	var addExcelT = document.getElementById('excelList').options[excelListIndex].text;
	var addOracleV = document.getElementById('oracleList').options[oracleListIndex].value;
	var addOracleT = document.getElementById('oracleList').options[oracleListIndex].text;
	document.forms[0].mappingList.options[document.getElementById('mappingList').options.length] = new Option(addExcelT + "------" + addOracleT, addExcelV + "!!SplitTwo!!" + addOracleV);
}


function addSingleItemList() {
	var excelListIndex = document.getElementById('excelList').selectedIndex;
	var oracleListIndex = document.forms[0].oracleList.selectedIndex;
	if (excelListIndex < 0 || oracleListIndex < 0 || document.getElementById('mappingList').options.length == document.forms[0].oracleList.options.length || checkDoubleItem()) {
		return false;
	}
	var addExcelV = document.getElementById('excelList').options[excelListIndex].value;
	var addExcelT = document.getElementById('excelList').options[excelListIndex].text;
	var addOracleV = document.getElementById('oracleList').options[oracleListIndex].value;
	var addOracleT = document.getElementById('oracleList').options[oracleListIndex].text;
	document.getElementById('mappingList').options[document.getElementById('mappingList').options.length] = new Option(addExcelT + "------" + addOracleT, addExcelV + "!!SplitTwo!!" + addOracleV);
}
//校验金额
function checktype(obj){
	obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
	var inputStr = obj.value;
	if(!inputStr.match(/\d+/g)){		
		alert("请输入数字类型的数据！");
		obj.value = '';
		obj.focus();
		return false;
	}
}
//必须只选一个
function checkoption(){
	var array = document.getElementsByName('pkV');
	var count = 0;
	for(var i=0;i<array.length;i++){
		if(array[i].checked==true){
			count++;
		}
	}
	if(count>1){
		alert('一次只能选择一个宿舍！');
		return false;
	}else if(count==0){
		alert('请选择一个宿舍入住！');
		return false;
	}else{
		document.forms[0].action='zgdzdx_Gygl.do?method=saveDorm';
		document.forms[0].submit();
		document.getElementById('buttonSave').disabled=true;
	}
				
}
//一次只能选择一个
function oneOption(){
	var array = document.getElementsByName('pkV');
	var line = curr_row.cells[0].getElementsByTagName('input')[0];
	if(line.checked==true){
		for(var i=0;i<array.length;i++){
			array[i].checked=false;	
		}
		line.checked=true;
	}
}
function sendinfo(){
	var value = curr_row.cells[0].getElementsByTagName('input')[0].value;
	showTopWin('/xgxt/zgdzdx_Gygl.do?method=getOneSsInfo&ssbh='+value+'&do=no',600,500);
}

/*
安徽建工数据导入检测
*/	
function impAndChkDataByahjg(){
	var realTable = "sjdxspfxzb";
	var tableName = "sjdxspfxzb";
	url = '/xgxt/impAndChkData.do?method=importData';
	url += "&realTable=" + realTable;
	url += "&tableName=" + tableName;
	showTopWin(url,600,500);
}

/**全选*/
function selectAll(id){
	var checkBoxArr = document.getElementsByName("pk");
	if(id!= null && id != ""){
		checkBoxArr = document.getElementsByName(id);
	}
	var selall = document.getElementById('cb');
	if(selall.checked==true){
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = true;
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
		}
	}
}
	
function loadView(){
    var isview = $("isview").value
    if(isview=="true"){
	    var tmpSel = document.getElementsByTagName("select");
	    var tmpHid = document.getElementsByTagName("input");
	    var tmpOth = document.getElementsByTagName("textarea");
	    var tmpBut = document.getElementsByTagName("button");

        for (i = 0; i < tmpSel.length; i++) {
		    tmpSel[i].disabled = true;
	    }
	    for (i = 0; i < tmpHid.length; i++) {
		    tmpHid[i].disabled = true;
	    }
	    for (i = 0; i < tmpOth.length; i++) {
		    tmpOth[i].disabled = true;
	    }
	    for (i = 0; i < tmpBut.length; i++) {
		    tmpBut[i].disabled = true;
	    }
	    if($("buttonSave")){$("buttonSave").style.display = 'none';}
	    if($("buttonClear")){$("buttonClear").style.display = 'none';}
	    $("buttonClose").disabled = false;
    }
}
/*
 * id 为table的id
 * col 为要处理的td所在列
 * num 为指定的字符长度
 * flag 确定是否为超链接
 * tableTop 确定是否有表头
 */
function changeView(id,col,num,flag,tableTop){
	var array = $(id).getElementsByTagName('tr');
	var v = 0;
	if(tableTop=='yes'){
		v = 1;
	}
	for(var i=v;i<array.length;i++){
		if(array[i].cells[col].innerText.length>num){
			if(flag=='yes'){
				array[i].cells[col].getElementsByTagName('a')[0].innerText = array[i].cells[col].innerText.substr(0,num)+'...';
			}else{
				array[i].cells[col].innerText = array[i].cells[col].innerText.substr(0,num)+'...';
			}
		}
	}
}

function textAreaFormat(id,divid){
	var value = document.getElementById(id).value;
	document.getElementById(divid).innerText = value;
}


function bjLimit(str) {
   if($(str)){
	  if ($("isFdy")&&$("isFdy").value == "true") {
	     if($(str).options[0].value==""){
	        $(str).options[0]=null;	      
	     }
	     if($("xydm")){
	     	$("xydm").value="";
	     	$("xydm").disabled=false;
	     }
	     if($("xy")){
	     	$("xy").value="";
	     	$("xy").disabled=false;
	     }
//	     if($("xydm"))$("xydm").disabled=true;
//	     if($("zydm"))$("zydm").disabled=true;
	  }
   }
}	

//验证数据格式是否是数字,该方法用于对输入金额的验证
function ckinpjedata(obj){
		obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;
		
		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g))){
			alert('数据格式不正确，只能是数字！');
			obj.value = '';
			return false;
		}
		return true;
	}

function chkm(){
	var km = document.getElementById("sel1").value;
	getSztzDao.dao_getHdList(km,function(data){
		if (data != null && typeof data == 'object') {
				var objId = "sel2";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"hdnrdm","hdnrmc");			
					if(objId + "V"){
					if($(objId +"V").value != "" && $(objId + "V").value != null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
                           }
						}
					}
					}
				}
			}
		});
}

function getSx(){
	var km = document.getElementById("sel1").value;
	var hd = document.getElementById("sel2").value;
	getSztzDao.dao_getSxList(km,hd,function(data){
		if (data != null && typeof data == 'object') {
				var objId = "sel3";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"sxdm","sxmc");			
					if(objId + "V"){
					if($(objId +"V").value != "" && $(objId + "V").value != null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
                           }
						}
					}
					}
				}
			}
		});
}	
	

function showOpenWindow(url,w,h){
	url = url.replace("/xgxt/","");
	var width=w;
	var height = h;
	var left = (screen.width/2) - width/2;
	var top = (screen.height/2) - height/2;
	var styleStr = 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=yes,width='+width+',height='+height+',left='+left+',top='+top+',screenX='+left+',screenY='+top;
	if(window.dialogArguments){
		window.dialogArguments.open(url,"msgWindow", styleStr);
	}else{
		window.open(url,"msgWindow", styleStr);
	}
}	
	
	
function toInt(str){
    if(typeof(str) === 'string'){
		str = str.replace(/^0*/g,"");
		if (str == ""){
			str = "0";
		}
		return parseInt(str);
	} 
	return  str;
}
function toFloor(str){
    if(typeof(str) === 'string'){
		str = str.replace(/^0*/g,"");
		if (str == ""){
			str = "0";
		}
		return parseFloat(str);
	} 
	return  str;
}
function showMessage(divId,isbackDiv,divColor){
  if($('##floatDiv##')==null){//显示操作区  
   var floatDiv = document.createElement('div');
   floatDiv.id = "##floatDiv##";
   floatDiv.style.position = "absolute";
   floatDiv.style.width = "300px";
   floatDiv.style.height = "150px";
   floatDiv.style.backgroundColor = "#FFFFFF";
   
   var x_pixel = 100;
   var y_pixel = 25;
   floatDiv.style.left = (document.documentElement.scrollWidth/2 + document.documentElement.scrollLeft - x_pixel) +"px";
   floatDiv.style.top = (document.documentElement.scrollHeight/2 + document.documentElement.scrollTop - 10) +"px";
   floatDiv.style.zIndex  = 1001;
   
   
   temp_divId=divId;
   temp_divHtml=document.getElementById(divId).innerHTML;
   floatDiv.innerHTML = document.getElementById(divId).innerHTML;
   document.getElementById(divId).innerHTML = "";
  	document.forms[0].appendChild(floatDiv);
 }
  else{
   $('##floatDiv##').style.display = "block";
  }
  if($('##backDiv##')==null && isbackDiv ==true){//显示背景  
   var backDiv = document.createElement('div');
   backDiv.id = "##backDiv##";
   backDiv.style.backgroundColor = "Black";
   backDiv.style.filter = "alpha(opacity=70)";
   backDiv.style.MozOpacity = "0.70";
   backDiv.style.position = "absolute";
   backDiv.style.left = "0px";
   backDiv.style.top = "0px";
   backDiv.style.width = Math.max(document.body.scrollWidth, document.body.clientWidth) +"px";
   backDiv.style.height = Math.max(document.body.scrollHeight, document.body.clientHeight)+"px";
   document.body.appendChild(backDiv);
   $('##backDiv##').style.xIndex = 1000;
  }
  else if(isbackDiv ==true){
   $('##backDiv##').style.display = "block";
  }  
  	i = document.getElementsByTagName("select").length;
	for (j = 0; j < i; j++) {
		var obj = document.getElementsByTagName("select")[j];
		var id = obj.id;
		var arr;
		var splitName;
		
		if ($(id)){
			arr = id.split('_');
		}
		
		if (null != arr && arr.length>0){
			splitName=arr[0];
		}
		
		if (splitName!="select"){
			obj.style.visibility = "hidden";
		}
		
	}
	
 }	


function getCurrDate(){
   var date = "";
   var d = new Date();// 创建 Date 对象。
   var y = d.getYear();
   var m = d.getMonth() + 1;
   var dt = d.getDate();
   date += y;  // 获取年份。 
   date += (m<10)?"0"+m:m; // 获取月份。
   date += (dt<10)?"0"+dt:dt;// 获取日。
   return date;   
}

function chLeng(obj,leng){
	
	if(obj.value.length > leng){
		//alertInfo("该项目最大字数为"+leng+",现已经 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 超过，请确认！", function(){obj.focus();});
		//showAlertDivLayer("字数过大，限"+leng+"字！");
		alertInfo("该输入项最大字数为"+leng+",现已经超过，请确认！", function(){obj.focus();});
		//obj.focus();
	}
}



function chLengs(obj,leng){
	
	if(obj.value.length > leng){
		
		alertInfo("该输入项最大字数为"+leng+",现已经超过，请确认！！", function(){obj.focus();});

	}
}

function chMax(obj,num){
	if(obj.value > num){
		alert("该项目不能超过"+num+",请确认！！");
		obj.focus();
	}
}

function hiddenMessage(back,float2){

    if(back!=null){//隐藏背景
        if(back==true){
           if($('##backDiv##')!=null){ 
            $('##backDiv##').style.display = "none";
          }         
        }
    }

   //隐藏操作区
        if(float2){
          if($('##floatDiv##')){
               $('##floatDiv##').style.display = "none";
					$('##floatDiv##').parentNode.removeChild($('##floatDiv##'));
				 if($(temp_divId)){
					 $(temp_divId).innerHTML=temp_divHtml;
				 }
          }         
        }

	i = document.getElementsByTagName("select").length;

	for (j = 0; j < i; j++) {
		document.getElementsByTagName("select")[j].style.visibility = "";
		document.getElementsByTagName("select")[j].style.display = "";
	} 
}


//无条件省市县区刷新方法
		function loadShi(){
			var shen = document.getElementById("jgshen").value;	
			getStuDetails.getShiList(shen,function(data){
				if (data.shiList != null) {
						var objId = "jgshi";				
						if($(objId)){
							DWRUtil.removeAllOptions(objId);					
							DWRUtil.addOptions(objId,data.shiList,"shidm","shimc");
						}
					}else{
						showMsgWin("有错误出现：远程数据读取失败！");
					}	
				if (data.xianList !=null){
					var objId = "jgxian";
						if($(objId)){
							DWRUtil.removeAllOptions(objId);					
							DWRUtil.addOptions(objId,data.xianList,"xiandm","xianmc");
						}
				}
			});
		}
 //无条件县区刷新方法
		function loadXian(){
			var shi = document.getElementById("jgshi").value;	
			getStuDetails.getXianList(shi,function(data){
				if (data != null) {
						var objId = "jgxian";
						if($(objId)){
							DWRUtil.removeAllOptions(objId);							
							DWRUtil.addOptions(objId,data,"xiandm","xianmc");
						}
					}else{
						showMsgWin("有错误出现：远程数据读取失败！");
					}		
			});
		}
	
	 //有条件省市县区刷新方法
		function loadShi(shen,shi,xian){
			var shenV = document.getElementById(shen).value;
			if(shenV == null || shenV == ''){
				DWRUtil.removeAllOptions(shi);
				DWRUtil.removeAllOptions(xian);
			}else{
				getStuDetails.getShiList(shenV,function(data){
					if (data.shiList != null) {
							var objId = shi;				
							if($(objId)){
								DWRUtil.removeAllOptions(objId);					
								DWRUtil.addOptions(objId,data.shiList,"shidm","shimc");
							}
						}else{
							showMsgWin("有错误出现：远程数据读取失败！");
						}	
					if (data.xianList !=null){
						var objId = xian;
							if($(objId)){
								DWRUtil.removeAllOptions(objId);					
								DWRUtil.addOptions(objId,data.xianList,"xiandm","xianmc");
							}
					}
				});
			}
		}
 //有条件省市县区刷新方法
		function loadXian(shi,xian){
			var shi = document.getElementById(shi).value;	
			if(shi == null || shi == ''){
				DWRUtil.removeAllOptions(xian);
			}else{
				getStuDetails.getXianList(shi,function(data){
					if (data != null) {
							var objId = xian;
							if($(objId)){
								DWRUtil.removeAllOptions(objId);							
								DWRUtil.addOptions(objId,data,"xiandm","xianmc");
							}
						}else{
							showMsgWin("有错误出现：远程数据读取失败！");
						}		
				});
			}
		}
function allNotSelect(url) {	
	if (allSelEmpty()) {
		if (confirm("您没有选择任何条件，此次操作将返回全部数据，可能会耗费相当长的时间。确定要继续吗？")){ 
		    $('search_go').disabled=true;
			refreshForm(url);
			return true;			
		} else {
			return false;
		}
	} else {
       
        if($("search_go")){
	       $('search_go').disabled=true;
	    }
		refreshForm(url);
		return true;
	}
	
}

function elementVisibility(str) {
	var i = document.getElementsByTagName("select").length;
	var j = document.getElementsByTagName("input").length;
	for (j = 0; j < i; j++) {
		document.getElementsByTagName("select")[j].style.visibility = "hidden";
	}
	for (j = 0; j < i; j++) {
		document.getElementsByTagName("input")[j].style.visibility = "hidden";
	}
}		

function dataLoad(bool){ 
//           i = document.getElementsByTagName("select").length;
//	       for (j = 0; j < i; j++) {
//		     document.getElementsByTagName("select")[j].style.visibility = "hidden";
//	      }			
	    var dd_html = "";
//	    dd_html+="<iframe  src='javascript:false' border=0px  style='position: absolute;visibility: inherit;top: 0px;";
//	    dd_html+="left: 0px;width: 350px;height: 200px;z-index: -999;filter: Alpha(Opacity=0)'></iframe> ";	
	    dd_html += "<img src='images/indicator.gif' width='15' height='17' /><font  color='#000055' size='3' > <b>数据加载中...</b></font>";		
		var tips = document.createElement("DIV");
        tips.id = "##backDiv##";
        tips.style.backgroundColor = "white";
        tips.style.filter = "alpha(opacity=50)";
        tips.style.MozOpacity = "0.70";
        tips.style.position = "absolute";
        tips.style.left = "0px";
        tips.style.top = "0px";
        tips.oncontextmenu="return false"; 
        tips.onselectstart='return false';     
        tips.style.width = Math.max(document.body.scrollWidth, document.body.clientWidth) +"px";
        tips.style.height = Math.max(document.body.scrollHeight, document.body.clientHeight)+"px";
		tips.id = "dataLoading";
		tips.align="center";
		tips.solid="#5E88B8";
		tips.innerHTML=dd_html;		
		if(bool){
		    if(document.getElementById("dataLoading")){
		        var div=document.getElementById("dataLoading");
		        div.style.display="block";
		     }else{
		        document.body.appendChild(tips);	
		     }
		}else{
		    if(document.getElementById("dataLoading")){
		        var div=document.getElementById("dataLoading");
		        div.style.display="none";
			}
		}
}

//?ì????·???????????????????×÷
function checkCanOper(realTable,key,value,type){
	var result = true;
	var message = type == "delete" ? "????" : "????";
	if(realTable == "pjpy_xfjs_wjlxdmb" && key == "001"){
		alert("?????à?????????????????à????????" + message + "??");
		result = false;
	}	
	return result;
}

//±í???ì??
function checkForm(realTable, key){
	var result = true;
	if(realTable == "pjpy_xfjs_kkcfszb"){
		if(key.match(/\d+$/)==null){			
			alert("????????±??è????????");
			result = false;
		}else{
			document.getElementsByName("newCode")[0].value = toInt(key);
		}
	}
	return result;
}
function gyglSendInfo(){
   var vel = window.dialogArguments.document.forms[0].xh;
   vel.value = replaceChar(curr_row.cells[0].innerText," ","");
   window.dialogArguments.gyglAutoFillStuInfo(vel);
   window.close();
}
function gyglAutoFillStuInfo(obj){
     var str = "/xgxt" + document.forms[0].url.value +"&xh="+obj.value;
     document.forms[0].action = str;
     document.forms[0].submit();
}
function isEmail(sEmail){	      
 	 var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	 return p.test(sEmail);
}
function wjcfDataExport(url) {
	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}	
/**
 * 验证输入必须为数字
 * @param obj
 * @return
 */
function chkIsNum(obj){
	obj.value = obj.value.replace(/[^(\d]/g,'');
	return true;
}

//判断输入是否汉字
function checkInputIshz(obj) {
	if (!str.match(/[^\u4e00-\u9fa5]/g)) {
	  alert("只能输入字符,不能输入中文!");
	  return false;
  	}
}

//增加页面中选择学号后，如果再次对学号进行修改那么则清空姓名，性别，年级等信息
function checkXhExists(idArr) {
		if (idArr == null || idArr == '') {
			idArr = ['xm','xb','nj','xymc','zymc','bjmc'];
		} else {
			idArr = idArr.split("-");
		}
		for (var i = 0; i < idArr.length; i++) {
			if ($(idArr[i])) {
				document.getElementById(idArr[i]).value = "";
			}
		}
	}

//判断学生是否存在
function chkIsStu(obj,tableName,zd){
		
	var xh = obj.value;
	var flg = false;	
	
	if(xh !=""){
			
		var colList = zd.split("-");
		var pk = "xh";
		var pkValue = xh;
	
		getDtjsInfo.getStuDefInfo(tableName, pk, pkValue,colList,function(data){
			if( data != null && data.length > 0){
				for (i = 0; i < colList.length; i++) {
					var id = colList[i];
					if($(id)){
						$(id).value = data[i];
					}
				}
			}else{
				alert("该学生不存在，请确认！");
				obj.focus();
			}
		});
	}
}

//限制输入空格
function noSpace(obj){
	var value = obj.value.replace(" ","");
	obj.value=value;
}

//获得指定表的指定字段
function getTableInfo(tableName,colList,pk,pkValue,query){

	dwr.engine.setAsync(false);
			
	getOtherData.getTableInfo(tableName, colList, pk, pkValue,query,function(data){
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
		
	dwr.engine.setAsync(true);
}

/**
 * ????×?·???????
 */
function trim(str) { 
	return str.replace(/(^\s*)|(\s*$)/g, "");   
}



/*
数据导入检测
*/	
function impAndChkDataForZdy(dzyDdTab){
	var realTable = "";
	var tableName = "";
	var sty = "toolbar=no,location=no,directories=no,status=yes";
	sty += ",menubar=no,scrollbars=yes,resizable=yes,width=600,height=500,top=100";
	sty += ",left=200";
	if($("realTable")) realTable = document.getElementById("realTable").value;
	if($("tableName")) tableName = document.getElementById("tableName").value;
	url = '/xgxt/impAndChkData.do?dzyDdTab='
	url += dzyDdTab; 
	url += '&method=importData';
	url += "&realTable=" + realTable;
	url += "&tableName=" + tableName;
	//showTopWin(url,600,500);
	//refreshForm(url);
	window.open(url,'',sty);
}


function ZdyDataExport(zdyTable) {
	document.forms[0].action = "/xgxt/bdsz.do?method=zdyDataExport&zdyTable="+zdyTable;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}

/**
 * 修改页面修改完后父页面的查询结果的页数不变
 * 2010.7.30 鲁宁
 */
function dialogArgumentsQueryChick() {
	if(window.dialogArguments){	
		if(window.dialogArguments.document.getElementById("isPage")){
			window.dialogArguments.document.getElementById("isPage").value="yes";
		}
		if (window.dialogArguments.document.getElementById("search_go")) {
			window.dialogArguments.document.getElementById("search_go").click();
		}
	}else if(window.opener){
		if(window.opener.document.getElementById("isPage")){
			window.opener.document.getElementById("isPage").value="yes";
		}
		if (window.opener.document.getElementById("search_go")) {
			window.opener.document.getElementById("search_go").click();
		}
	}

}

//查询页面进行批量操作
function dataBatch(url){
  	var RowsStr="!!";
  	var mes = "确定要操作所选记录？";
  	jQuery("input[name=primarykey_cbv]").each(function(){
  		if(jQuery(this).is(":checked")){
  			RowsStr+=jQuery(this).val()+"!!";
  		}
  	});
		/*for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	if(document.getElementsByName("pkV")[i].checked){
	    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    	}
		}*/
		if (RowsStr=="!!"){
			alertInfo("请选择要操作的记录！");
			return false;
		}
				
		confirmInfo(mes,function(t){
			if(t!="ok"){
				return false;
			}else{
				url += "&pkValue=";
				url += RowsStr;
				refreshForm(url);
			}
		});
		
}

function modi(url,h,w){
	if(curr_row != null){
		showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
		return true;
	}else{
		alertInfo('请选择要修改的数据行！');
		return false;
	}
}

		//根据class换取对象
		function getElementByClassName(o,tagName,className){
			var elements = o.getElementsByTagName(tagName);
			
			for (var i = 0 ; i < elements.length ; i++){
				if (elements[i].className == className){
					return elements[i];
				}
			}
			return null;
		}
		
		
	function changeWin() {
		if ($("left").className != "hide") {
			$("left").className = "hide";
			$("right").style.width = "100%";
			$("leftBotton").style.display = "none";
			$("rightBotton").style.display = "";
			
			if (jQuery('#ifm div.rightframe04').eq(0)){
				jQuery('#ifm div.rightframe04').eq(0).attr('class','rightframe04_hidden');
			}
		} else {
			$("left").className = "typeleft floatleft";
			$("right").style.width = "803px";
			$("leftBotton").style.display = "";
			$("rightBotton").style.display = "none";
			
			if (jQuery('#ifm div.rightframe04_hidden').eq(0)){
				jQuery('#ifm div.rightframe04_hidden').eq(0).attr('class','rightframe04');
			}
		}
	}

	function loadFrameWindow(){
		var body = jQuery('#ifm body');
		
		if ($("left").className == "hide") {
			if (jQuery('#ifm div.rightframe04').eq(0)){
				jQuery('#ifm div.rightframe04').eq(0).attr('class','rightframe04_hidden');
			}
		} else {
			if (jQuery('#ifm div.rightframe04_hidden').eq(0)){
				jQuery('#ifm div.rightframe04_hidden').eq(0).attr('class','rightframe04');
			}
		}
		
	}



  //select 宽度调整开始
//对于不能自动的，可以使用onmouseover=selectWidthAdd()来实现；
function selectWidthAdd(eventTag){
   var event = eventTag||window.event;     
   var currentKey = event.charCode||event.keyCode;     
   var selectObj =window.event.srcElement||eventTag.target; 
   
	var newSelectObj = document.createElement("select");
	newSelectObj = selectObj.cloneNode(true);
	newSelectObj.selectedIndex = selectObj.selectedIndex;
	newSelectObj.onmouseover = null;
	var e = selectObj;
	var absTop = e.offsetTop;
	var absLeft = e.offsetLeft;
	while(e = e.offsetParent){
		absTop += e.offsetTop;
		absLeft += e.offsetLeft;
	}
	with (newSelectObj.style){
		position = "absolute";
		top = absTop + "px";
		left = absLeft + "px";
		width = "";
	}
	var rollback = function(){ RollbackWidth(selectObj, newSelectObj); };
	newSelectObj.focus();
	newSelectObj.onmouseout=function(){//当移开是就还原
		selectObj.style.visibility = "visible";
		document.body.removeChild(newSelectObj);
		};
	newSelectObj.onclick=function(){//当点击时就移除克隆对象的onmouseout事件句柄
			newSelectObj.onmouseout=null;
		};
	newSelectObj.onblur = rollback;
	newSelectObj.onchange = rollback;
	selectObj.style.visibility = "hidden";
	document.body.appendChild(newSelectObj);
	function RollbackWidth(selectObj, newSelectObj){
		selectObj.selectedIndex = newSelectObj.selectedIndex;
		selectObj.style.visibility = "visible";
		document.body.removeChild(newSelectObj);
		if(selectObj.onchange!=null){
			selectObj.onchange();
		}
	}
}

function selectWidthMiddle(eventTag){
    var event = eventTag||window.event;     
    var currentKey = event.charCode||event.keyCode;     
    var obj =window.event.srcElement||eventTag.target; 
    if(obj.onmouseover==selectWidthAdd){
    	obj.onmouseover();
    	return ;
    }
	var select_width=parseFloat(obj.style.width);
	var o=obj.options;
	var max_length=0;
	for(j=0;null != o && j<o.length;j++){
		var strs=o[j].innerHTML;
		var px_length=0;
		for(a=0;a<strs.length;a++){
			if(strs.charCodeAt(a) >= 19968){
				px_length+=2;
			}else{
				px_length+=1;
			}
		}
		if(max_length<px_length){
			max_length=px_length;
		}
		if(!isNaN(select_width)&&px_length>parseFloat(select_width)/7){
			break;
		}
	}
	if(!isNaN(select_width)&&max_length>parseFloat(select_width)/7){
		if(obj.onmouseover==selectWidthMiddle ){
			obj.onmouseover=selectWidthAdd;
			obj.onmouseover();
			obj.onmouseover=selectWidthMiddle;
		}
	}
}
function selectWidthAuto(){
	 var selectObj= document.getElementsByTagName("select");
	 for(i=0;i<selectObj.length;i++){
		if(selectObj[i].onmouseover==null ){
			selectObj[i].onmouseover=selectWidthMiddle;
		}else{
			selectObj[i].onmouseover+=selectWidthMiddle;
		}
	 }
 }

function selectWidthAutoRecursion(){
	if(document.body){
		if(document.body){
			selectWidthAuto();
		}
	}else{
		setTimeout("selectWidthAutoRecursion();",200);
	}
}
//selectWidthAutoRecursion();
//select 宽度调整结束





//title:临时div的标题
//html :你自己写的字符串
//w    :临时div的宽度
//h    :临时div的高度
function showTempDivForJs(title,html,w, h) {
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight;
	var d_left = 0;
	var d_top = 0;
	var d_width_top = w;
	var d_height_top = h;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var dd_html = "";
	var tempDiv = $('tempDiv');
	
	if ($('tempDiv')){
		$('tempDiv').style.display="";
	}else {
		tempDiv = document.createElement("div");
		tempDiv.id="tempDiv"
		document.body.appendChild(tempDiv);
	}	
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' ";
	dd_html += "style='filter:alpha(opacity=70);opacity:0;position:absolute;align:middle;";
	dd_html += "text-align:center;padding-top:10px;padding-right:0px;border: 1px solid #5E88B8;";
	dd_html += " width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px;"
	dd_html += " left:" + d_left + "px; background-color:#808080'>";
	dd_html += "</div>";
	dd_html += "<div class='tab' style='position:absolute;align:middle;text-align:center;";
	dd_html += "padding-top:0px;border: 1px solid #4271A6; width:" + d_width_top + "px; ";
	dd_html += "height:" + d_height_top + "px; top:" + d_top_top + "px; ";
	dd_html +="left:" + d_left_top + "px; background-color:#FFFFFF'>";
	
	dd_html += "<div id=\"windown-title\" align='left'><h2>"+title+"</h2>";
	dd_html += "<span id=\"windown-close\" onclick=\"$('tempDiv').style.display='none'\">关闭</span></div>";
	dd_html += "<div id=\"open_win\" class=\"open_win01\" style=\"padding-top:5px\">";
	dd_html += html;
	dd_html += "</div>";
	dd_html += "</div>";
	
	i = document.getElementsByTagName("select").length;
	for (j = 0; j < i; j++) {
		var obj = document.getElementsByTagName("select")[j];
		var id = obj.id;
		var arr;
		var splitName;
		
		if ($(id)){
			arr = id.split('_');
		}
		
		if (null != arr && arr.length>0){
			splitName=arr[0];
		}
		
		if (splitName!="select"){
			obj.style.visibility = "hidden";
		}
		
	}
	
	tempDiv.innerHTML = dd_html;	
}

function commonExportData(){
	var realTable = "";
	var viewTable = "";
	
	if(document.getElementById('realTable')){
		if(document.getElementById('realTable').value == ""){
			alert('表名为空!');
			return false;
		}else{
			realTable = document.getElementById('realTable').value;
		}
	}else{
		alert('页面上没有表名！');
		return false;
	}
	
	if(document.getElementById('tableName')){
		if(document.getElementById('tableName').value == ""){
			alert('视图名为空!');
			return false;
		}else{
			viewTable = document.getElementById('tableName').value;
		}
	}else{
		alert('页面上没有视图名！');
		return false;
	}
	refreshForm('xtwhOther.do?method=showExportPage&tableName=' + realTable + "&viewName=" + viewTable);
}





//显示临时DIV
//title:div的标题
//divId:要显示的DIV的id
//width:宽度
//height:高度
//切记：如果你要弹出层的div里面有下拉框，请把这个下拉框加上id并以select_开头
function createTmpDiv(title,divHtml,width,height){

var isbackDiv = true;
if($('##floatDiv##')==null){//显示操作区 
 var floatDiv = document.createElement('div');
 floatDiv.id = "##floatDiv##";
 floatDiv.style.position = "absolute";
 floatDiv.style.width = width;
 floatDiv.style.height = height;
 floatDiv.style.backgroundColor = "#FFFFFF";
 
 var d_width = document.body.clientWidth;
 var d_height = document.body.clientHeight;
 var d_left_top = (d_width - width) / 2 +"px";
 var d_top_top =  "100" + "px";
 
 floatDiv.style.top = d_top_top;
 floatDiv.style.left = d_left_top;
 floatDiv.style.zIndex  = 1001;
 

 //temp_divHtml=divHtml;
 
 var dd_html="";
 dd_html += "<div id=\"windown-title\" align='left'><h2>"+title+"</h2>";
 dd_html += "<span id=\"windown-close\" onclick=\"hiddenMessage(true,true)\">关闭</span></div>";
 dd_html += "<div id=\"open_win\" class=\"open_win01\" style=\"padding-top:5px\">";
 floatDiv.innerHTML = dd_html+divHtml+"</div>";
// document.getElementById(divId).innerHTML = "";
// document.body.removeAttribute($(divId))
 document.forms[0].appendChild(floatDiv);
}
else{
 $('##floatDiv##').style.display = "block";
}
if($('##backDiv##')==null && isbackDiv ==true){//显示背景  
 var backDiv = document.createElement('div');
 backDiv.id = "##backDiv##";
 backDiv.style.backgroundColor = "Black";
 backDiv.style.filter = "alpha(opacity=60)";
 backDiv.style.MozOpacity = "0.70";
 backDiv.style.position = "absolute";
 backDiv.style.left = "0px";
 backDiv.style.top = "0px";
 backDiv.style.width = Math.max(document.body.scrollWidth, document.body.clientWidth);
 var t_height = Math.max(document.body.scrollHeight, document.body.clientHeight);
 if(t_height <800){
	t_height = 800; 
 }
 backDiv.style.height = t_height+"px";
 document.body.appendChild(backDiv);
 $('##backDiv##').style.xIndex = 1000;
}
else if(isbackDiv ==true){
 $('##backDiv##').style.display = "block";
}  
	i = document.getElementsByTagName("select").length;
	for (j = 0; j < i; j++) {
		var obj = document.getElementsByTagName("select")[j];
		var id = obj.id;
		var arr;
		var splitName;
		if(id != null && id!=""){
			if ($(id)){
				arr = id.split('_');
			}
		}
		
		if (null != arr && arr.length>0){
			splitName=arr[0];
		}
		
		if (splitName!="select"){
			obj.style.display = "none";
		}
		
	}
}

function showExportDIV(url){
	//判断是否有视图名称
	var tableName = "";
	if(!document.getElementById("tableName")){
		alert("页面没有视图名！");
		return false;
	}else{
		tableName = document.getElementById("tableName").value;
	}
	var d_html = "";
	d_html += "<table width=\"100%\" border=\"0\" class=\"formlist\" id=\"rsTable\">";
	d_html += "<thead>";
	d_html += "	<tr>";
	d_html += "		<th><span>数据库表字段</span></th>";					
	d_html += "		<th></th>";
	d_html += "		<th><span>需导出的字段</span></th>";
	d_html += "	</tr>";
	d_html += "</thead>";
	d_html += "<tbody>";
	d_html += "<tr>";
	d_html += "	<td align=\"right\">";
	d_html += "		<select name=\"xmdm\" style=\"width:230px;\" size=\"18\"";
	d_html += "				id='select_excelList' ondblclick=\"addOneItemListForExp()\">";
	d_html += "		</select>";
	d_html += "	</td>";
	d_html += "	<td align=\"center\">";
	d_html += "		<div class=\"btn\">";
	d_html += "		<button name=\"button1\"";
	d_html += "				onclick=\"defaultAllItemListForExp()\"";
	d_html += "				style=\"width:50px\">";
	d_html += "				 >> ";
	d_html += "		</button>";
	d_html += "		<br/><br/><br/>";

	d_html += "		<button name=\"button2\"";
	d_html += "				onclick=\"addOneItemListForExp()\"";
	d_html += "				style=\"width:50px\">";
	d_html += "				 > ";
	d_html += "		</button>	";						
	d_html += "		<br/><br/><br/>";

	d_html += "		<button name=\"button3\"";
	d_html += "				onclick=\"deleteItemListForExp()\"";
	d_html += "				style=\"width:50px\">";
	d_html += "				 < ";
	d_html += "		</button>";
	d_html += "		<br/><br/><br/>";

	d_html += "		<button name='button4'";
	d_html += "				onclick='clearListForExp()'";
	d_html += "				style='width:50px'>";
	d_html += "				  << ";
	d_html += "		</button>";
	d_html += "		</div>";
	d_html += "	</td>";
	d_html += "	<td>";
	d_html += "		<select style='width:230px;' size='18'";
	d_html += "				id='select_mappingList' ondblclick='deleteItemListForExp()'>";		
	d_html += "		</select>";
	d_html += "		<input type=\"hidden\" name=\"mappingItems\" id=\"mappingItems\" value=\"\"/>";
	
	d_html += "	</td>";
	d_html += "</tr>";
	d_html += "</tbody>";
	d_html += "<tfoot>";
	d_html += "  <tr>";
	d_html += "    <td colspan='4'>";
	d_html += "      <div class='btn'>";
	d_html += "        <button id='btn_add'";
	d_html += "				onclick='submitColList(\"" + url + "\")'";
	d_html += "				style='width:80px'>";
	d_html += "				数据输出";
	d_html += "		   </button>";
	d_html += "        <button id='btn_add'";
	d_html += "				onclick='hiddenMessage(true, true);'";
	d_html += "				style='width:80px'>";
	d_html += "				取   消";
	d_html += "		   </button>";
	d_html += "      </div>";
	d_html += "    </td>";
	d_html += "  </tr>";
	d_html += "</tfoot>";
	d_html += "</table>";
	createTmpDiv("选择导出字段",d_html,600,400);
	//加载数据
	GetListData.getTableColForExp(tableName,function(data){
		DWRUtil.removeAllOptions("select_excelList");			
		DWRUtil.addOptions("select_excelList",data,"dm","mc");		
	});
}
 
function submitColList(url){
	//获取选择的数据
	var i;
	document.getElementById("mappingItems").value = "";//清空mappingItems中的值
	if (document.getElementById("select_mappingList").options.length <= 0) {
		alert("导出字段不能为空!");
		return false;
	}
	for (i = 0; i < document.getElementById("select_mappingList").options.length; i++) {
		document.getElementById("mappingItems").value = document.getElementById("mappingItems").value + "!!SplitOne!!";
		document.getElementById("mappingItems").value = document.getElementById("mappingItems").value + document.getElementById("select_mappingList").options[i].value;
	}
	var showConfirmText = "您确定导出所选的字段吗？";
	var showRunningText;
	//提交导出	
    if (confirm(showConfirmText)) {
    	var Items = document.getElementById("mappingItems").value;
    	wjcfDataExport(url);
	}
	//关闭层
    hiddenMessage(true, true);
}

/** 
 * 默认对应所有的字段
 */
function defaultAllItemListForExp() {
	if (document.getElementById('select_mappingList').options.length > 0) {
		return false;
	}
	var len = document.getElementById('select_excelList').options.length;
	var i;
	var addExcelV;
	var addExcelT;
	for (i = 0; i < len; i++) {
		addExcelV = document.getElementById('select_excelList').options[i].value;
		addExcelT = document.getElementById('select_excelList').options[i].text;
		document.getElementById('select_mappingList').options[i] = new Option(addExcelT, addExcelV);
	}
}
 
 
 function addOneItemListForExp() {
	var excelListIndex = document.getElementById('select_excelList').selectedIndex;
	if (excelListIndex < 0 || document.getElementById('select_mappingList').options.length == document.getElementById('select_excelList').options.length) {
		return false;
	}
	var addExcelV = document.getElementById('select_excelList').options[excelListIndex].value;
	var addExcelT = document.getElementById('select_excelList').options[excelListIndex].text;
	document.getElementById('select_mappingList').options[document.getElementById('select_mappingList').options.length] = new Option(addExcelT , addExcelV);
}

function deleteItemListForExp() {
	var mappingListIndex = document.getElementById('select_mappingList').selectedIndex;
	if (mappingListIndex < 0) {
		return false;
	}
	document.getElementById('select_mappingList').options[mappingListIndex] = null;
}

function clearListForExp() {
	document.getElementById('select_mappingList').options.length = 0;
}

function getXyzybj(type){
	var url = "";
	var nj = "";
	var xymc = ""; 
	var xydm = "";
	var zymc = "";
	var zydm = "";
	
	if($('nj')){
		nj = $('nj').value;
	}
	
	if($('xymc')){
		xymc = $('xymc').value;
	}
	
	if($('xydm')){
		xydm = $('xydm').value;
	}
	
	if($('zymc') && $('zymc')){
		zymc = $('zymc').value;
	}
	
	if($('xydm') && $('zydm')){
		zydm = $('zydm').value;
	}
	
	if("xy" == type){
		url = "/xgxt/commXgInfo.do?method=choiceXy";
		url += "&nj="+nj;
	} else if("zy" == type){
		url = "/xgxt/commXgInfo.do?method=choiceZy";
		url += "&nj=" + nj;
		url += "&xymc=" + xymc;
		url += "&xydm=" + xydm;
	} else{
		url = "/xgxt/commXgInfo.do?method=choiceBj";
		url += "&nj=" + nj;
		url += "&xymc=" + xymc;
		url += "&zymc=" + zymc;
		url += "&zydm=" + zydm;
	}
	
	showTopWin(url,650,500);
}

function getBj(obj){
	var url = "";
	var nj = "";
	var xymc = ""; 
	var xydm = "";
	var zymc = "";
	var zydm = "";
	
	if($('nj')){
		nj = $('nj').value;
	}
	
	if($('xymc')){
		xymc = $('xymc').value;
	}
	
	if($('xydm')){
		xydm = $('xydm').value;
	}
	
	if($('zymc') && $('zymc')){
		zymc = $('zymc').value;
	}
	
	if($('xydm') && $('zydm')){
		zydm = $('zydm').value;
	}
	
	url = "/xgxt/commXgInfo.do?method=xzBj";
	url += "&nj=" + nj;
	url += "&xydm=" + xydm;
	url += "&zydm=" + zydm;
	url += "&zdpzstr=" + obj;
	//showTopWin(url,750,600);
	showDialog("", 750, 600, url);
	// jQuery.dialog({title:'B窗口',content:'url:/xgxt/commXgInfo.do?method=xzBj',parent:this});
}
function getZyBj(){
    var url = "";
    /*var nj = "";
    var xymc = "";
    var xydm = "";
    var zymc = "";
    var zydm = "";

    if($('nj')){
        nj = $('nj').value;
    }

    if($('xymc')){
        xymc = $('xymc').value;
    }

    if($('xydm')){
        xydm = $('xydm').value;
    }

    if($('zymc') && $('zymc')){
        zymc = $('zymc').value;
    }

    if($('xydm') && $('zydm')){
        zydm = $('zydm').value;
    }*/

    url = "/xgxt/commXgInfo.do?method=xzBj";
    url += "&isZybjXz=true";

    showDialog("", 750, 600, url);

}

function initXyzybj(obj, type){
	$('zydm').value = "";
	$('zymc').value = "";
	
	$('bjdm').value = "";
	$('bjmc').value = "";
}

//保存数据并验证主键是否为空
function saveUpdate(url,pk){
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
	
function purviewControl(){	
	
}

//刷新父页面
function refreshParent(tag){
	if(tag == "ok"){
		if($("refreshParent") && $("refreshParent").value == "yes"){
			if(opener){
				if(opener.document.getElementById("search_go")){
					opener.document.getElementById("search_go").click();
	      			window.close();
				}
			}else if(window.dialogArguments){
				if(window.dialogArguments.document.getElementById("search_go")){
					dialogArgumentsQueryChick();
				}
				window.close();
			}else{
				
			}
		}
	}
}

var confirmInfo = function(message,callback) { 
	var c_width = jQuery(this).width();
	var c_height = jQuery(this).height();
	if (c_height > 440){
	  c_height = 440;
	}
	var m_left = (c_width - 320) / 2;
	var m_top =  (c_height - 200) / 2;
	ymPrompt.confirmInfo({message:message,title:"确认内容",handler:callback,winPos:[m_left,m_top],maskAlpha:0.01});
}

var alertInfo = function(message,handler) { 
	//###########获取滚动条的滚动距离 start################################
	var point = {x:0,y:0};
	// 如果浏览器支持 pageYOffset, 通过 pageXOffset 和 pageYOffset 获取页面和视窗之间的距离
    if(typeof window.pageYOffset != 'undefined') {
        point.x = window.pageXOffset;
        point.y = window.pageYOffset;
        if(window.parent){
        	point.x += window.parent.pageXOffset;
            point.y += window.parent.pageYOffset;
        }
    }
    // 如果浏览器支持 compatMode, 并且指定了 DOCTYPE, 通过 documentElement 获取滚动距离作为页面和视窗间的距离
    // IE 中, 当页面指定 DOCTYPE, compatMode 的值是 CSS1Compat, 否则 compatMode 的值是 BackCompat
    else if(typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat') {
        point.x = document.documentElement.scrollLeft;
        point.y = document.documentElement.scrollTop;
        if(window.parent){
        	point.x += window.parent.document.documentElement.scrollLeft;
            point.y += window.parent.document.documentElement.scrollTop;
        }
    }
    // 如果浏览器支持 document.body, 可以通过 document.body 来获取滚动高度，如果是在ifram里面，用document.body可以获取iframe中滚动条的位置。

    else if(typeof document.body != 'undefined') {
        point.x = document.body.scrollLeft;
        point.y = document.body.scrollTop;
        if(window.parent){
        	point.x += window.parent.document.body.scrollLeft;
            point.y += window.parent.document.body.scrollTop;
        }
    }
	//###########获取滚动条的滚动距离 end################################
	
	var c_width = jQuery(this).width();
	var c_height = jQuery(this).height();
	if (c_height > 440){
	  c_height = 440;
	}
	var m_left = (c_width+point.x - 320) / 2;
	var m_top =  (c_height+point.y - 200) / 2;

	handler = handler==null ? refreshParent : handler;

	ymPrompt.alert({title:'提示信息',message:message,dragOut:true,winPos:[m_left,m_top],maskAlpha:0.01,handler:handler});
}

var alertError = function(message) { 
	var c_width = jQuery(this).width();
	var c_height = jQuery(this).height();
	if (c_height > 440){
	  c_height = 440;
	}
	var m_left = (c_width - 320) / 2;
	var m_top =  (c_height - 200) / 2;
	ymPrompt.errorInfo({title:'提示信息',message:message,winPos:[m_left,m_top],maskAlpha:0.01})
}

//显示其他功能层
function displayDiv(idArr,display){
	for (var i = 0 ; i < idArr.length ; i++){
		$(idArr[i]).style.display=display;
	}
}

//检查是否修改
function checkHadEdit(){

	var flag = true;
	var operation="";
	if($("operation")){
		operation = $("operation").value;
	}
	if($("had_edit") && $("had_edit").value == "yes"){
		if(operation!="no"){
			flag = false;
			saveMethod();
		}
	}

	return flag;
}

//设置是否修改
function setHadEdit(){
	if($("had_edit")){
		$("had_edit").value = "yes"
	}
}
/*
查询条件重置
date 2013-4-17
*/

function searchReset() {

var input = document.getElementsByTagName('input');
var select = document.getElementsByTagName('select');
			
for (var i = 0;i<input.length;i++) {
	if (input[i].type != 'hidden' && input[i].disabled != true 
		&& input[i].type != 'checkbox' 
		&& (!input[i].readOnly||input[i].className=="jssj")
		&& input[i].id !="pageno"
		&& input[i].id !="pagesize")
		input[i].value='';
}
for (var i = 0;i<select.length;i++) {
	if (select[i].disabled != true && select[i].options.length > 0 && select[i].options[0].value == "")
	select[i].value='';
}
}

/*
 * 导出配置方法调用，弹层
 * */
function customExport(dcclbh,exportFun,width,height){
	var w = parseInt(width) || 700;
	var h = parseInt(height) || 500;
	var url = "comm_export.do?method=exportConfig&dcclbh=" + dcclbh;//
	showDialog('自定义导出配置',w,h,url,{
		close:function(){
			jQuery("input[name$=selectCol]").remove();
		},
		data:exportFun
	});
}

function getQuerySetting(gnbz){
	
	var gridSetting = {};
	
	jQuery.post("xtwh_cxpz.do?method=getCxpzInfo",{gnbz:gnbz},function(json){
		gridSetting = json;
	},"json");
	
	return gridSetting;
}

function closeWindown() {
	jQuery.ajaxSetup({async:false});
	if ('' != tempID){
		jQuery('#'+tempID).html(tempHTML);
	}

	jQuery("#windownbg").remove();
	jQuery("#windown-box").fadeOut("slow",function(){jQuery(this).remove();});
	jQuery.ajaxSetup({async:true});
}
function testAlert(object){
	var objStr="";
	for(var p in object){
		objStr+="\n";
		objStr+=p+":"+object[p];
	}
	alert(objStr);
}
String.prototype.trim = function () {
	return this .replace(/^\s\s*/, '' ).replace(/\s\s*$/, '' );
	 }

/**
 * toFixed()四舍五入无法保留精度的问题
 * @param number
 * @param fractionDigits
 * @return
 */
Number.prototype.toFixed=function (d) { 
	                 var s=this+""; 
	                 if(!d)d=0; 
	                 if(s.indexOf(".")==-1)s+="."; 
	                 s+=new Array(d+1).join("0"); 
	                 if(new RegExp("^(-|\\+)?(\\d+(\\.\\d{0,"+(d+1)+"})?)\\d*$").test(s)){
	                     var s="0"+RegExp.$2,pm=RegExp.$1,a=RegExp.$3.length,b=true;
	                     if(a==d+2){
	                         a=s.match(/\d/g); 
	                        if(parseInt(a[a.length-1])>4){
	                          for(var i=a.length-2;i>=0;i--){
	                                a[i]=parseInt(a[i])+1;
	                               if(a[i]==10){
	                                     a[i]=0;
	                                    b=i!=1;
	                                 }else break;
	                             }
	                         }
	                         s=a.join("").replace(new RegExp("(\\d+)(\\d{"+d+"})\\d$"),"$1.$2");
	 
	                     }if(b)s=s.substr(1); 
	                     return (pm+s).replace(/\.$/,"");
	                 }return this+"";
	
	             };

