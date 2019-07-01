function addTab() {
	var realTable = document.getElementById('realTable').value;
	var url = 'pjpy_zjcm_szAdd.do?tableName=';
	url += realTable;
	showTopWin(url,'600','400');
}

function modiTab(tmp) {
	if (curr_row==null || curr_row=='') {
		alert("请先选择要操作的数据行!");
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

//批量删除
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
		if (confirm('确定要删除所选择的数据吗？')){
			document.forms[0].action = url;
			document.forms[0].submit();
			BatAlert.showTips('正在操作，请等待...');
		}
	}else{
		alert("没有选择相应记录，请选择之后再确定！！");
	}
}

function autocount(url) {
	var xn = document.getElementById('xn').value;
	var xq = document.getElementById('xq').value;
	var xydm = document.getElementById('xy').value;
	chgZhcpbl.chgZhszcpBlExists(function (data){
		if (!data) {
			alert('请先设置综合素质测评分的计算比例!');
			return;
		} else {
					if (xn==null||xn==''||xq==null||xq==''||xydm==null||xydm=='') {
				alert('条件选择中学年,学期,院系为必选项!');
				return;
			} else {
				if (confirm('该操作将以学年,学期,院系为单位自动计算综合测评成绩,可能耗费较长时间,要继续吗?')) {
					refreshForm(url);
					BatAlert.showTips('正在操作,请等待...');
				}
				return;
	}
		}
	});
}
