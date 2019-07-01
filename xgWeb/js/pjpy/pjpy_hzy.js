function initSels(){
	var sels = document.getElementsByName("bj");
	var queryStr = document.getElementById("queryStr").value;
	var list = GetListData.getBjList(queryStr);
	for(var i=0;i<sels.length;i++){
		for(var j=0;j<list.length;j++){
			var option = document.createElement("option");
			option.value = list[j].dm;
			var txt = document.createTextNode(list[j].mc);
			option.appendChild(txt);
			sels[i].appendChild(option);
		}
	}
}

function getStus(obj){
	var bjdm = obj.value;
	var no = parseInt(obj.id);
	getBjStus.getBjStus(bjdm,function(list){
		var stuList = obj.parentNode.parentNode.getElementsByTagName("select")[no];
		for(var j=0;j<list.length;j++){
				var option = document.createElement("option");
				option.value = list[j].dm;
				var txt = document.createTextNode(list[j].mc);
				option.appendChild(txt);
				stuList.appendChild(option);
		}
	});
}

function checkAllInput(inputStr){
	var input = inputStr.split("!!");
	for(var i=0;i<input.length;i++){
		var temp = document.getElementById(input[i]);
		if(temp==null || temp.value==null || temp.value.trim()==""){
			alert("请将必填信息，填写完整！");
			return false;
		}
	}
	return true;
}

function initDate(){
	var date = new Date();
    var datearr = document.getElementsByName("date");
    var year = date.getYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    for(var i=0;i<datearr.length;i++){
    	datearr[i].innerText = datearr[i].innerText.replace('year',year);
    	datearr[i].innerText = datearr[i].innerText.replace('month',month);
    	datearr[i].innerText = datearr[i].innerText.replace('day',day);
    }  
}

function hzy_changePage(defaultId,onLoad){//切换页面
	var title = defaultId.id.substr(0,defaultId.id.length-1);
	var titleName;
	var mainTitle = $("mainTitle");
	var xxmc = '';
	if ($('xxmc')) {
		xxmc = $('xxmc').value;
	}
	var anotherName = new Array("xjbj","wmbj","xjtzb","xjtzz");
	var mkType = "";
	for(i=0;i<anotherName.length;i++){
	    title==anotherName[0];
	    titleName = title;	    
		$("titName").value = title;				
		$(titleName+"l").className = "xxk_on_l";
		$(titleName+"m").className = "xxk_on_m";
		$(titleName+"r").className = "xxk_on_r";
		for(j=0;j<anotherName.length;j++){
		    if(anotherName[j]!=title){
		        if($(anotherName[j]+"l")){$(anotherName[j]+"l").className = "xxk_off_l"};
		        if($(anotherName[j]+"l")){$(anotherName[j]+"m").className = "xxk_off_m"};
		        if($(anotherName[j]+"l")){$(anotherName[j]+"r").className = "xxk_off_r"};	
		    }	
		}
		if(title == "xjbj"){
		  mainTitle.innerText = xxmc + "先进班级推荐表";
		}else if(title == "wmbj"){
		  mainTitle.innerText = xxmc + "文明班级推荐表";
		}
	}
	if(onLoad!="true"){
		refreshForm('/xgxt/pjpy_hzy_xjbjandwmbj.do?method=xjbjAndWmbjSq&mkType='+title);
	}
}
function hzy_onLaodChgPage(){//OnLaod切换页面
	    var title = document.getElementById('defaultTitle');
	    var anotherName = new Array("xjbj","wmbj","xjtzb","xjtzz");	
		document.getElementById("titName").value = title;				
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		for(i=0;i<anotherName.length;i++){
		    if(anotherName[i]!=title){
		        document.getElementById(anotherName[i]+"l").className = "xxk_off_l";
		        document.getElementById(anotherName[i]+"m").className = "xxk_off_m";
		        document.getElementById(anotherName[i]+"r").className = "xxk_off_r";	
		    }	
		}
		//mainTitle.innerText = xxmc + "先进班级推荐表";
	 
}
/*
 显示年级、学院、专业、班级列表 
*/
function showItems(){
	var items = document.getElementById("items");
//	items.style.left = (screen.availWidth)/2;
//	items.style.top = (screen.availHeigh)/2;
	items.style.display = "block";
	if ($("userType").value == "xy") {
		var tmp = 'xy';
		///document.getElementById('xy').disabled = true;
	}
}
/*
 隐藏年级、学院、专业、班级列表 
*/
function hideItems(){
	var items = document.getElementById("items");
	//items.style.left = (screen.availWidth)/2;
	//items.style.top = (screen.availHeigh)/2;
	items.style.display = "none";
	document.getElementById("bjdm").value = document.getElementById("bj").value;
	refreshForm('pjpy_hzy_xjbjandwmbj.do?method=xjbjAndWmbjSq');
}

function printFun(){
	var requestPath = '/xgxt/pjpy_hzy_xjbjandwmbj_print.do?method=xjbjAndWmbjSqPrint&';
	var bjdm = document.getElementById("bjdm").value;
	requestPath += "bjdm="+bjdm;
	var titName = document.getElementById("titName");
	requestPath += "&titName="+titName.value;
	//alert(requestPath);
	window.open(requestPath);
}

function selectAllCheckBox(){
	var checkBoxArr = document.getElementsByName("checkval");
	for(var i=0;i<checkBoxArr.length;i++){
		checkBoxArr[i].checked="true";
	}
}

function shtg(){
	var checkBoxArr = document.getElementsByName("checkval");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
			var url = "/xgxt/dispatch.do?method=saveShResult&shjg=shtgY";
			refreshForm(url);
	}else{
		alert("没有选择相应记录，请选择之后再确定！！");
	}
}

function shbtg(){
var checkBoxArr = document.getElementsByName("checkval");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
			var url = "/xgxt/dispatch.do?method=saveShResult&shjg=shtgN";
			refreshForm(url);

	}else{
		alert("没有选择相应记录，请选择之后再确定！！");
	}
	
}