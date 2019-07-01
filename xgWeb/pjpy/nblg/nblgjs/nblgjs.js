function dispDjks(flag) {
	var val='';
	if ($(flag)) {
		val = document.getElementById(flag).value;
	}
	var xxdm = document.getElementById('xxdm').value;
	if (val == 'djksb') {
		if (xxdm=='13022') {
			document.getElementById('djksdm').disabled="false";
		}
	} else {
		if (xxdm=='13022') {
			document.getElementById('djksdm').disabled="true";	
		}
	}
}

function addjxjdm(tmp) {
	var url = 'pjpy_hygxy_jxjdmwh.do';
	if (tmp=='jxjlbdmb') {
		url = 'pjpy_hygxy_jxjlbdmwh.do';
	}
	showTopWin(url, 480, 340);
}

function modijxjdm(tmp) {
	var url = 'pjpy_hygxy_jxjdmmodi.do?pkValue=';
	if (tmp=='jxjlbdmb') {
		url = 'pjpy_hygxy_jxjlbdmmodi.do?pkValue=';
	}
	var dm = curr_row.cells[1].innerText;
	url += dm;
	showTopWin(url, 480, 340);
}
