function addTab() {
	var realTable = document.getElementById('realTable').value;
	var url = 'pjpy_zjcm_szAdd.do?tableName=';
	url += realTable;
	showTopWin(url,'600','400');
}

function modiTab(tmp) {
	if (curr_row==null || curr_row=='') {
		alert("����ѡ��Ҫ������������!");
		return;
	}
	var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
	var url = 'pjpy_zjcm_szfmodi.do?pkValue=';
	url += pk;
	url += '&tableName=';
	url += document.getElementById('realTable').value;
	url += '&szlx=';
	url += document.getElementById('szlx').value;
	url += '&act=';
	url += tmp;
	showTopWin(url,600,400);
}

//����ɾ��
function delTab(url) {
	var realTable = document.getElementById('realTable').value;
	url += '?act=del&realTable=';
	url += realTable;
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		if (confirm('ȷ��Ҫɾ����ѡ���������')){
			document.forms[0].action = url;
			document.forms[0].submit();
			BatAlert.showTips('���ڲ�������ȴ�...');
		}
	}else{
		alert("û��ѡ����Ӧ��¼����ѡ��֮����ȷ������");
	}
}

function autocount(url) {
	var xn = document.getElementById('xn').value;
	var xq = document.getElementById('xq').value;
	var xydm = document.getElementById('xy').value;
	chgZhcpbl.chgZhszcpBlExists(function (data){
		if (!data) {
			alert('���������ۺ����ʲ����ֵļ������!');
			return;
		} else {
					if (xn==null||xn==''||xq==null||xq==''||xydm==null||xydm=='') {
				alert('����ѡ����ѧ��,ѧ��,ԺϵΪ��ѡ��!');
				return;
			} else {
				if (confirm('�ò�������ѧ��,ѧ��,ԺϵΪ��λ�Զ������ۺϲ����ɼ�,���ܺķѽϳ�ʱ��,Ҫ������?')) {
					refreshForm(url);
					BatAlert.showTips('���ڲ���,��ȴ�...');
				}
				return;
	}
		}
	});
}
