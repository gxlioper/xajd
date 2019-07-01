function dycjAdd() {
	var url = 'pjpy_nblg_dycjadd.do?params=';
	var types = document.getElementById('realTable').value;
	url += types;
	showTopWin(url,'550','400')
}

function dycjUpdate(val) {
	var types = document.getElementById('realTable').value;
	var url = 'pjpy_nblg_dycjmodi.do?params=';
	url += types;
	url += '&act=';
	url += val;
	url += '&pkValue=';
	modiAndDel(url,'modi','550','400')
}

function autoCount() {
	var xn = document.getElementById('xn').value;
	var xydm = document.getElementById('xy').value;
	if (xn=='' ||xydm=='') {
		alert("查询条件中学年,"+jQuery("#xbmc").val()+"为必选项!");
	} else {
		if (confirm("自动计算将以学年,"+jQuery("#xbmc").val()+"为单位计算综合素质测评分,要继续吗?")) {
			refreshForm('pjpy_nblg_zhszcpwh.do?act=autocount');
			BatAlert.showTips('正在操作，请等待...');
		} 
		return;
	}
}

function zhszcpDataExp() {
	var xn = document.getElementById('xn').value;
	var bjdm = document.getElementById('bj').value;
	if (xn=='' || bjdm=='') {
		alert('查询条件中,学年,班级为必选!');
		return;
	} else {
		var url = 'pjpy_nblg_zhszcpexp.do?xn=';
		url += xn;
		url += '&bjdm=';
		url += bjdm;
		window.open(url);
		//document.forms[0].action = url;
		//document.forms[0].target = "_blank";
		//document.forms[0].submit();
		return;
	}
}

function jxjqryres() {
	var jxjdm = document.getElementById('jxjdm').value;
	var bjdm = document.getElementById('bjdm').value;
	if (jxjdm=='' ||jxjdm==null || bjdm=='' ||bjdm==null) {
		alert('查询条件中奖学金,班级为必选!');
		return;
	} else {
		var userType = document.getElementById('userType').value;
		if (userType=='xy') {
			if (confirm('该次查询将按条件设置中设置的基本条件进行数据过滤!')) {
				refreshForm('pjpy_nblg_jxjsh.do?act=query');
			}		
		} else {
			refreshForm('pjpy_nblg_jxjsh.do?act=query');
		}
	}
}

function rychqryres() {
	var rychdm = document.getElementById('rychdm').value;
	var bjdm = document.getElementById('bjdm').value;
	if (rychdm=='' || bjdm=='' ||rychdm==null || bjdm==null) {
		alert('查询条件中荣誉称号,班级为必选项!');
	} else {
		refreshForm('pjpy_nblg_rychsh.do?act=query');
		document.getElementById('search_go').disabled=true;
	}
}


function queryOne() {
	if((curr_row == null) || (curr_row == "")){
		return false;
	}
	var xh = curr_row.getElementsByTagName("input")[1].value;
	var url = "/xgxt/stu_info_details.do?xh="+xh;
	showOpenWindow(url, 800, 500);
}