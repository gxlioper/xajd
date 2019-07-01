//计算综合素质测评总分
function countZf(val1,val2,val3,val4,val5) {
	var sfzss = document.getElementById('sfzss').value;
	if (sfzss == '' || sfzss == null) {
		alert('没有选择学生或该生不在住宿生和走读生范围内,请确认!');
		document.getElementById('xydykpf').value='';
		document.getElementById('gydykpf').value='';
		document.getElementById('zcj').value='';
		document.getElementById('tcj').value='';
		document.getElementById('gzxxcx').value='';
		document.getElementById('zhszcpzf').value='';
		return;
	}
	var df1 = document.getElementById(val1).value;
	var df2 = document.getElementById(val2).value;
	var df3 = document.getElementById(val3).value;
	var df4 = document.getElementById(val4).value;
	var df5 = document.getElementById(val5).value;
	var type;
	if (df1 == null || df1 == '' || df1 == ' ') {
		df1 = 0;
		document.getElementById(val1).value='';
		type = val1;
	}
	if (df2 == null || df2 == '' || df2 == ' ') {
		df2 = 0;
		document.getElementById(val2).value='';
		type = val2;
	}
	if (df3 == null || df3 == '' || df3 == ' ') {
		df3 = 0;
		document.getElementById(val3).value='';
		type = val3;
	}
	if (df4 == null || df4 == '' || df4 == ' ') {
		df4 = 0;
		document.getElementById(val4).value='';
		type = val4;
	}
	if (df5 == null || df5 == '' || df5 == ' ') {
		df5 = 0;
		document.getElementById(val5).value='';
		type = val5;
	}
		hzyZhszcpf.countZhszcpzfByXydyf(sfzss,df1,df2,df3,df4,df5,type,function ctzf(data){
		document.getElementById('zhszcpzf').value = data;
		});
}

//保存前检查数据是否有修改
function chksavedata() {
	if (document.getElementById('oldxn').value == document.getElementById('xn').value
	&& document.getElementById('oldxq').value == document.getElementById('xq').value
	&& document.getElementById('oldnd').value == document.getElementById('nd').value
	&& document.getElementById('oldxydykpf').value == document.getElementById('xydykpf').value
	&& document.getElementById('oldzcj').value == document.getElementById('zcj').value
	&& document.getElementById('oldtcj').value == document.getElementById('tcj').value
	&& document.getElementById('oldgydykpf').value == document.getElementById('gydykpf').value
	&& document.getElementById('oldgzxxcx').value == document.getElementById('gzxxcx').value
	&& document.getElementById('oldzhszcpzf').value == document.getElementById('zhszcpzf').value
	&& document.getElementById('oldzhszcpcjpm').value == document.getElementById('zhszcpcjpm').value
	&& document.getElementById('oldbz').value == document.getElementById('bz').value ) {
		return true;
	} else {
		return false;
	}
}