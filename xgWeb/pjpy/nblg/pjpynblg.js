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
		alert("��ѯ������ѧ��,"+jQuery("#xbmc").val()+"Ϊ��ѡ��!");
	} else {
		if (confirm("�Զ����㽫��ѧ��,"+jQuery("#xbmc").val()+"Ϊ��λ�����ۺ����ʲ�����,Ҫ������?")) {
			refreshForm('pjpy_nblg_zhszcpwh.do?act=autocount');
			BatAlert.showTips('���ڲ�������ȴ�...');
		} 
		return;
	}
}

function zhszcpDataExp() {
	var xn = document.getElementById('xn').value;
	var bjdm = document.getElementById('bj').value;
	if (xn=='' || bjdm=='') {
		alert('��ѯ������,ѧ��,�༶Ϊ��ѡ!');
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
		alert('��ѯ�����н�ѧ��,�༶Ϊ��ѡ!');
		return;
	} else {
		var userType = document.getElementById('userType').value;
		if (userType=='xy') {
			if (confirm('�ôβ�ѯ�����������������õĻ��������������ݹ���!')) {
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
		alert('��ѯ�����������ƺ�,�༶Ϊ��ѡ��!');
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