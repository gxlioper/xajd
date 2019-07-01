
function SelectrwOption(a, b) {
	document.forms[0].tname.value = a;
	document.forms[0].action = b;
	document.forms[0].submit();
}
function thoughtLoad(defaultid) {
	if (document.forms[0].tname.value != "") {
		document.getElementById(document.forms[0].tname.value + "l").className = "xxk_on_l";
		document.getElementById(document.forms[0].tname.value).className = "xxk_on_m";
		document.getElementById(document.forms[0].tname.value + "r").className = "xxk_on_r";
	}
}
var array = new Array();

//检测是否有勾中记录,如果是勾中的放入数组供批量操作用
function isChecked(tagName) {
	var checkBoxArr = document.getElementsByName(tagName);
	var flag = false;
	var n = 0;
	for (var i = 0; i < checkBoxArr.length; i++) {
		if (checkBoxArr[i].checked == true) {
			flag = true;
			array[n] = checkBoxArr[i];
			n++;
		}
	}
	if (flag) {
		if (confirm("\u786e\u5b9a\u8981\u6279\u91cf\u8bbe\u7f6e\u6240\u9009\u62e9\u7684\u6570\u636e\u5417\uff1f")) {
			return true;
		}
	} else {
		alertInfo("\u6ca1\u6709\u9009\u62e9\u76f8\u5e94\u8bb0\u5f55\uff0c\u8bf7\u9009\u62e9\u4e4b\u540e\u518d\u8fdb\u884c\u64cd\u4f5c!");
		return false;
	}
}

//审核级别DIV
function showShjbList() {
	if (!isChecked("primarykey_cbv")) {
		return false;
	}
	viewTempDiv('审核级别设置','plszDiv',350,200);
//	$("tempDiv").style.display = "";
//	var d_width = document.body.clientWidth;
//	var d_height = document.body.clientHeight;
//	var d_left = 0;
//	var d_top = 0;
//	var d_color = "#EEF4F9";
//	var d_width_top = 350;
//	var d_height_top = 200;
//	var d_left_top = (d_width - d_width_top) / 2;
//	var d_top_top = (d_height - d_height_top) / 2;
//	var d_color_top = "#EEF4F9";
//	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
//	dd_html += "</div>";
//	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
//	dd_html += "<br/><br/>";
//	dd_html += "<table width='300' class='tbstyle'>";
//	dd_html += "<thead>";
//	dd_html += "<tr height='30'>";
//	dd_html += "<td colspan='2'>";
//	dd_html += "--------------------\u8bf7\u9009\u62e9\u5ba1\u6838\u7ea7\u522b--------------------";
//	dd_html += "</td>";
//	dd_html += "</tr>";
//	dd_html += "</thead><tbody>";
//	dd_html += "<tr><td align='right'>\u5ba1\u6838\u7ea7\u522b:</td><td><div id='shjbDiv'><div></td></tr>";
//	dd_html += "<tr><td align='center' colspan='2'><button class='button2'  onclick='plszShjb();'>\u786e&nbsp;&nbsp;\u5b9a</button> &nbsp;&nbsp;&nbsp;&nbsp;<button class='button2' onclick='closeDiv();'>\u53d6&nbsp;&nbsp;\u6d88</button></td></tr></tbody></table>";
//	tempDiv.innerHTML = dd_html;
//	shjbDiv.innerHTML = tempDiv2.innerHTML;
}

//批量设置
function plszShjb() {
	if ("" == $("shjb").value) {
		alert("\u8bf7\u9009\u62e9\u5ba1\u6838\u7ea7\u522b!");
		return false;
	}
	if (array.length > 0) {
		for (var i = 0; i < array.length; i++) {
			array[i].parentNode.parentNode.getElementsByTagName("select")[0].value = $("select_shjb").value;
		}
	}
	hiddenMessage(true,true);
}

//关闭DIV
function closeDiv() {
	$("tempDiv").style.display = "none";
}
function saveAll(url) {
	if (!isChecked("primarykey_cbv")) {
		return false;
	}
	if(document.getElementById("cb")){
		document.getElementById("cb").checked = true;
	}
	selectAllCbv();
	refreshForm(url);
}
function checkUpdate(url, doType, width, height) {
	if (null == curr_row) {
		alert("\u8bf7\u9009\u62e9\u4e00\u884c");
		return false;
	}
	var checkBox = curr_row.getElementsByTagName("input")[0].disabled;
	if (true == checkBox) {
		alert("\u4e0a\u7ea7\u90e8\u95e8\u5ba1\u6838\u901a\u8fc7\uff0c\u4e0d\u80fd\u64cd\u4f5c!");
	} else {
		showInfo("/xgxt/guizhdx.do?method=xjbjOne", "sh", "800", "600");
	}
}
function display() {
	if('12645' == $('xxdm').value) {
		if ($("lb1").checked == true) {
			$("1").style.display = "";
			$("2").style.display = "";
		} else {
			$("1").style.display = "none";
			$("2").style.display = "none";
			$("hjdj").value = "";
			$("zbdw").value = "";
			$("bfdw").value = "";
		}
		if ($("lb2").checked == true) {
			$("3").style.display = "";
			$("4").style.display = "";
		} else {
			$("3").style.display = "none";
			$("4").style.display = "none";
			$("kwmckh").value = "";
			$("zzpm").value = "";
			$("fbsj").value = "";
		}
		if ($("lb3").checked == true) {
			$("5").style.display = "";
		} else {
			$("5").style.display = "none";
			$("cg").value = "";
			$("cgjb").value = "";
		}
		if ($("lb4").checked == true) {
			$("6").style.display = "";
		} else {
			$("6").style.display = "none";
			$("zysj").value = "";
		}
	}
}

function setSqsj(){
	var xh = $('xh').value;
	var xn = $('xn').value;
	var jxjdm = $('jxjdm').value;
	var text = DWRUtil.getText('jxjdm');
		
	if('12645' == $('xxdm').value) {
		if ('素质与能力拓展成就奖学金'==text){
			$('sqsj').value = $('now').value;
			$('szynltz').style.display='';
			$('nbcsjxj').style.display='none';
			$('gjlzjxj').style.display='none';
		}else if('国家励志奖学金'==text){
			$('szynltz').style.display='none';
			$('nbcsjxj').style.display='none';
			$('gjlzjxj').style.display='';
		} else {
			$('nbcsjxj').style.display='';
			$('szynltz').style.display='none';
			$('gjlzjxj').style.display='none';
		}
	}
}

function selectAllCbv(){
	var checkBoxArr = document.getElementsByName("cbv");
	var selall = document.getElementById('cb');
	if(selall.checked==true){
		for(var i=0;i<checkBoxArr.length;i++){
			if (checkBoxArr[i].disabled==true) {
				checkBoxArr[i].checked = false;	
			} else {
				checkBoxArr[i].checked = true;
			}
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
		}
	}
}