//����������Ϣ
function savesqinfo(par1,par2,url){
	if (document.getElementById(par1).value == '' || document.getElementById(par2).value == '') {
		alert('�뽫��*�ŵ�������д');
	}
	else{
		refreshForm(url);
		if ($('btn_save')) {
			document.getElementById('btn_save').disabled = true;
		}
	}
}

function cxmodi(url) {
	if (curr_row==null||curr_row=='') {
		alert('����ѡ��Ҫ������������!');
		return;	
	} else {
		var pk = curr_row.getElementsByTagName("input")[0].value;
		url += pk;
		showTopWin(url, 650, 500);
	}
}