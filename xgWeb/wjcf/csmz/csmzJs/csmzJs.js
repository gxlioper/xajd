//保存申请信息
function savesqinfo(par1,par2,url){
	if (document.getElementById(par1).value == '' || document.getElementById(par2).value == '') {
		alert('请将带*号的内容填写');
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
		alert('请先选择要操作的数据行!');
		return;	
	} else {
		var pk = curr_row.getElementsByTagName("input")[0].value;
		url += pk;
		showTopWin(url, 650, 500);
	}
}