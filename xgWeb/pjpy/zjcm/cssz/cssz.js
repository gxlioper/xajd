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
	if (confirm("��ʼ������������յ�ǰѧ�ꡢѧ�ڵ��������ݣ����������ɡ�\n���ܺķѽϳ�ʱ�䣬ȷ��Ҫ��ʼ����?")) {
		var dd_html = "";
		dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>���ڳ�ʼ�����ݣ����Ժ�......<br><br>";
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
			alert("��ѡ��Ҫ���ܵ�ѧԺ!");
			return false;
		}
		var xymc = document.getElementById('xy').options[document.getElementById('xy').selectedIndex].text;
		if (confirm('�ò�������ͳ��'+$('xn').value+'ѧ��'+$('xq').value+'ѧ��,'+xymc+'��ѧ�����������������,\n���ܺķѽϳ�ʱ�䣬Ҫ������')) {
			wjcfDataExport('pjpy_zjcm_jxjblFpb.do?xymc='+xymc);
		}
	}

//�ж��Ƿ�ѡ���и�ѡ��	
function isSelect(name) {
	var checkBoxArr = document.getElementsByName(name);
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (!flag) {
		alert("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�����");
		return false;
	} else {
		return true;
	}
}