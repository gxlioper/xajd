function dispconf(eID) {
	var obj = document.getElementById(eID);
	if (obj.value == "bj") {
		//dispZy.style.display = "";
		document.getElementById("zy").disabled = false;
		//dispBj.style.display = "";
		document.getElementById("bj").disabled = false;
	} else {
		if (obj.value == "zy") {
		//dispZy.style.display = "";
			document.getElementById("zy").disabled = false;
		//dispBj.style.display = "none";
			document.getElementById("bj").disabled = true;
		} else {
			if (obj.value == "xy") {
		//dispZy.style.display = "none";
				document.getElementById("zy").disabled = true;
		//dispBj.style.display = "none";
				document.getElementById("bj").disabled = true;
			}
		}
	}
}
	function changeJxj(url) {
		var lb = document.getElementById('jxjlb').value;
		refreshForm(url);
	}
	function baseDataInit(lb) {
	if (confirm("初始化操作将会清空当前学年、学期的所有数据，并重新生成。\n可能耗费较长时间，确定要初始化吗?")) {
		var dd_html = "";
		dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>正在初始化数据，请稍候......<br><br>";
		dd_html += "<span class='roll_tip'></span>";
		dd_html += "</td></tr></table>";
		for (i = 1; i < document.getElementsByTagName("table").length; i++) {
			document.getElementsByTagName("table")[i].style.display = "none";
		}
		showDiv(dd_html, 300, 120);
		refreshForm("pjpy_zjcm_baseDataInit.do?lb="+lb);
		return true;
	}
	return false;
}	

function datahz() {
		var xydm = document.getElementById('xy').value;
		if (xydm == null || xydm=='') {
			alert("请选择要汇总的学院!");
			return false;
		}
		var xymc = document.getElementById('xy').options[document.getElementById('xy').selectedIndex].text;
		if (confirm('该操作将会统计'+$('xn').value+'学年'+$('xq').value+'学期,'+xymc+'奖学金比例分配名额数据,\n可能耗费较长时间，要继续吗？')) {
			wjcfDataExport('pjpy_zjcm_jxjblFpb.do?xymc='+xymc);
		}
	}

//判断是否选择有复选框	
function isSelect(name) {
	var checkBoxArr = document.getElementsByName(name);
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (!flag) {
		alert("没有选择相应记录，请选择之后再进行操作！");
		return false;
	} else {
		return true;
	}
}