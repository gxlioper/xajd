//只能输入数字
function chkonlynum() {
	if ((event.keyCode >=48) && (event.keyCode<=57)) {
		event.returnValue=true;
	}
	else {
		event.returnValue=false;
	}
}

//计算思想道德总分
function countsxddzf() {
	var sxddbx = document.getElementById('sxddbx').value;
	var zzllxx = document.getElementById('zzllxx').value;
	var ssjsqk = document.getElementById('ssjsqk').value;
	var shsjhd = document.getElementById('shsjhd').value;
	var gbrzbx = document.getElementById('gbrzbx').value;
	var qttcsj = document.getElementById('qttcsj').value;
	var zpf = document.getElementById('zpf').value;
	if(sxddbx == null || sxddbx == "" || sxddbx == " "){
		sxddbx = '0';
		document.getElementById('dcj').value = "";
	}
	if(zzllxx == null || zzllxx == "" || zzllxx == " "){
		zzllxx = '0';
		document.getElementById('dcj').value = "";
	}
	if(ssjsqk == null || ssjsqk == "" || ssjsqk == " "){
		ssjsqk = '0';
		document.getElementById('dcj').value = "";
	}
	if(shsjhd == null || shsjhd == "" || shsjhd == " "){
		shsjhd = '0';
		document.getElementById('dcj').value = "";
	}
	if(gbrzbx == null || gbrzbx == "" || gbrzbx == " "){
		gbrzbx = '0';
		document.getElementById('dcj').value = "";
	}
	if(qttcsj == null || qttcsj == "" || qttcsj == " "){
		qttcsj = '0';
		document.getElementById('dcj').value = "";
	}
	if(zpf == null || zpf == "" || zpf == " "){
		zpf = '0';
		document.getElementById('dcj').value = "";
	}
	document.getElementById('dcj').value = parseFloat(sxddbx) + parseFloat(zzllxx)
	 + parseFloat(ssjsqk) + parseFloat(shsjhd) + parseFloat(gbrzbx) + parseFloat(qttcsj)
	 + parseFloat(zpf);
	 if(document.getElementById('dcj').value.match(/^\d+\.{0,1}\d{0,3}$/) == null){
		alert("积分格式有误，请确认");
		return false;
	}
	var dcj = document.getElementById('dcj').value;
	var zcj = document.getElementById('zcj').value;
	var tcj = document.getElementById('tcj').value;
	if(dcj == null || dcj == "" || dcj == " "){
		dcj = '0';
		document.getElementById('zhszcpzf').value = "";
	}
	if(zcj == null || zcj == "" || zcj == " "){
		zcj = '0';
		document.getElementById('zhszcpzf').value = "";
	}
	if(tcj == null || tcj == "" || tcj == " "){
		tcj = '0';
		document.getElementById('zhszcpzf').value = "";
	}
	document.getElementById('zhszcpzf').value = parseFloat(dcj)
	 + parseFloat(zcj) + parseFloat(tcj);
}

//计算科学文化素质总分
function countkxwhszzf() {
	var zytz = document.getElementById('zytz').value;
	var yyjn = document.getElementById('yyjn').value;
	var jsjjn = document.getElementById('jsjjn').value;
	var zyjn = document.getElementById('zyjn').value;
	var kxjs = document.getElementById('kxjs').value;
	var cxnl = document.getElementById('cxnl').value;
	var kcjqpfj = document.getElementById('kcjqpfj').value;
	if(zytz == null || zytz == "" || zytz == " "){
		zytz = '0';
		document.getElementById('zcj').value = "";
	}
	if(yyjn == null || yyjn == "" || yyjn == " "){
		yyjn = '0';
		document.getElementById('zcj').value = "";
	}
	if(jsjjn == null || jsjjn == "" || jsjjn == " "){
		jsjjn = '0';
		document.getElementById('zcj').value = "";
	}
	if(zyjn == null || zyjn == "" || zyjn == " "){
		zyjn = '0';
		document.getElementById('zcj').value = "";
	}
	if(kxjs == null || kxjs == "" || kxjs == " "){
		kxjs = '0';
		document.getElementById('zcj').value = "";
	}
	if(cxnl == null || cxnl == "" || cxnl == " "){
		cxnl = '0';
		document.getElementById('zcj').value = "";
	}
	if(kcjqpfj == null || kcjqpfj == "" || kcjqpfj == " "){
		kcjqpfj = '0';
		document.getElementById('zcj').value = "";
	}
	document.getElementById('zcj').value = parseFloat(zytz) + parseFloat(yyjn)
	 + parseFloat(jsjjn) + parseFloat(zyjn) + parseFloat(kxjs) + parseFloat(cxnl)
	 + parseFloat(kcjqpfj);
	 if(document.getElementById('zcj').value.match(/^\d+\.{0,1}\d{0,3}$/) == null){
		alert("积分格式有误，请确认");
		return false;
	}
	var dcj = document.getElementById('dcj').value;
	var zcj = document.getElementById('zcj').value;
	var tcj = document.getElementById('tcj').value;
	if(dcj == null || dcj == "" || dcj == " "){
		dcj = '0';
		document.getElementById('zhszcpzf').value = "";
	}
	if(zcj == null || zcj == "" || zcj == " "){
		zcj = '0';
		document.getElementById('zhszcpzf').value = "";
	}
	if(tcj == null || tcj == "" || tcj == " "){
		tcj = '0';
		document.getElementById('zhszcpzf').value = "";
	}
	document.getElementById('zhszcpzf').value = parseFloat(dcj)
	 + parseFloat(zcj) + parseFloat(tcj);
}

function countsxjkszzf() {
	var tydb = document.getElementById('tydb').value;
	var tyhd = document.getElementById('tyhd').value;
	var tsqk = document.getElementById('tsqk').value;
	var xljkhd = document.getElementById('xljkhd').value;
	var xlszzk = document.getElementById('xlszzk').value;
	if(tydb == null || tydb == "" || tydb == " "){
		tydb = '0';
		document.getElementById('tcj').value = "";
	}
	if(tyhd == null || tyhd == "" || tyhd == " "){
		tyhd = '0';
		document.getElementById('tcj').value = "";
	}
	if(tsqk == null || tsqk == "" || tsqk == " "){
		tsqk = '0';
		document.getElementById('tcj').value = "";
	}
	if(xljkhd == null || xljkhd == "" || xljkhd == " "){
		xljkhd = '0';
		document.getElementById('tcj').value = "";
	}
	if(xlszzk == null || xlszzk == "" || xlszzk == " "){
		xlszzk = '0';
		document.getElementById('tcj').value = "";
	}
	document.getElementById('tcj').value = parseFloat(tydb) + parseFloat(tyhd)
	 + parseFloat(tsqk) + parseFloat(xljkhd) + parseFloat(xlszzk);
	 if(document.getElementById('tcj').value.match(/^\d+\.{0,1}\d{0,3}$/) == null){
		alert("积分格式有误，请确认");
		return false;
	}
	document.getElementById('stszzf').value = parseFloat(tydb) + parseFloat(tyhd)
	 + parseFloat(tsqk);
	 if(document.getElementById('stszzf').value.match(/^\d+\.{0,1}\d{0,3}$/) == null){
		alert("积分格式有误，请确认");
		return false;
	}
	document.getElementById('xlszzf').value = parseFloat(xljkhd) + parseFloat(xlszzk);
	if(document.getElementById('xlszzf').value.match(/^\d+\.{0,1}\d{0,3}$/) == null){
		alert("积分格式有误，请确认");
		return false;
	}
	var dcj = document.getElementById('dcj').value;
	var zcj = document.getElementById('zcj').value;
	var tcj = document.getElementById('tcj').value;
	if(dcj == null || dcj == "" || dcj == " "){
		dcj = '0';
		document.getElementById('zhszcpzf').value = "";
	}
	if(zcj == null || zcj == "" || zcj == " "){
		zcj = '0';
		document.getElementById('zhszcpzf').value = "";
	}
	if(tcj == null || tcj == "" || tcj == " "){
		tcj = '0';
		document.getElementById('zhszcpzf').value = "";
	}
	document.getElementById('zhszcpzf').value = parseFloat(dcj)
	 + parseFloat(zcj) + parseFloat(tcj);
}

function chksszf(){
	var dcj = document.getElementById('dcj').value;
	var sxddbx = document.getElementById('sxddbx').value;
	var zzllxx = document.getElementById('zzllxx').value;
	var ssjsqk = document.getElementById('ssjsqk').value;
	var shsjhd = document.getElementById('shsjhd').value;
	var gbrzbx = document.getElementById('gbrzbx').value;
	var qttcsj = document.getElementById('qttcsj').value;
	var zpf = document.getElementById('zpf').value;
	if(sxddbx == null || sxddbx == "" || sxddbx == " "){
		sxddbx = '0';
		
	}
	if(zzllxx == null || zzllxx == "" || zzllxx == " "){
		zzllxx = '0';
		
	}
	if(ssjsqk == null || ssjsqk == "" || ssjsqk == " "){
		ssjsqk = '0';
		
	}
	if(shsjhd == null || shsjhd == "" || shsjhd == " "){
		shsjhd = '0';
		
	}
	if(gbrzbx == null || gbrzbx == "" || gbrzbx == " "){
		gbrzbx = '0';
		
	}
	if(qttcsj == null || qttcsj == "" || qttcsj == " "){
		qttcsj = '0';
		
	}
	if(zpf == null || zpf == "" || zpf == " "){
		zpf = '0';
		
	}
	var dcj1 = parseFloat(sxddbx) + parseFloat(zzllxx)
	 + parseFloat(ssjsqk) + parseFloat(shsjhd) + parseFloat(gbrzbx) + parseFloat(qttcsj)
	 + parseFloat(zpf);
	 if (parseFloat(dcj1)!=0 && parseFloat(dcj) != 0 && parseFloat(dcj1) != parseFloat(dcj)) {
	 	alert('思想道德素质总分计算有误，请确认！');
	 	document.getElementById('dcj').value=parseFloat(sxddbx) + parseFloat(zzllxx)
	 + parseFloat(ssjsqk) + parseFloat(shsjhd) + parseFloat(gbrzbx) + parseFloat(qttcsj)
	 + parseFloat(zpf);
	 }
	 var dcj2 = document.getElementById('dcj').value;
	var zcj2 = document.getElementById('zcj').value;
	var tcj2 = document.getElementById('tcj').value;
	if(dcj2 == null || dcj2 == "" || dcj2 == " "){
		dcj2 = '0';
		document.getElementById('zhszcpzf').value = "";
	}
	if(zcj2 == null || zcj2 == "" || zcj2 == " "){
		zcj2 = '0';
		document.getElementById('zhszcpzf').value = "";
	}
	if(tcj2 == null || tcj2 == "" || tcj2 == " "){
		tcj2 = '0';
		document.getElementById('zhszcpzf').value = "";
	}
	document.getElementById('zhszcpzf').value = parseFloat(dcj2)
	 + parseFloat(zcj2) + parseFloat(tcj2);
}

function countkxzf(){
	var zcj = document.getElementById('zcj').value;
	var zytz = document.getElementById('zytz').value;
	var yyjn = document.getElementById('yyjn').value;
	var jsjjn = document.getElementById('jsjjn').value;
	var zyjn = document.getElementById('zyjn').value;
	var kxjs = document.getElementById('kxjs').value;
	var cxnl = document.getElementById('cxnl').value;
	var kcjqpfj = document.getElementById('kcjqpfj').value;
	if(zytz == null || zytz == "" || zytz == " "){
		zytz = '0';
		
	}
	if(yyjn == null || yyjn == "" || yyjn == " "){
		yyjn = '0';
		
	}
	if(jsjjn == null || jsjjn == "" || jsjjn == " "){
		jsjjn = '0';
		
	}
	if(zyjn == null || zyjn == "" || zyjn == " "){
		zyjn = '0';
		
	}
	if(kxjs == null || kxjs == "" || kxjs == " "){
		kxjs = '0';
		
	}
	if(cxnl == null || cxnl == "" || cxnl == " "){
		cxnl = '0';
		
	}
	if(kcjqpfj == null || kcjqpfj == "" || kcjqpfj == " "){
		kcjqpfj = '0';
		
	}
	var zcj1 = parseFloat(zytz) + parseFloat(yyjn)
	 + parseFloat(jsjjn) + parseFloat(zyjn) + parseFloat(kxjs) + parseFloat(cxnl)
	 + parseFloat(kcjqpfj);
	 if (parseFloat(zcj1)!=0 && parseFloat(zcj) != 0 && parseFloat(zcj1) != parseFloat(zcj)) {
	 	alert('科学文化素质总分计算有误，请确认！');
	 	document.getElementById('zcj').value=parseFloat(zytz) + parseFloat(yyjn)
	 + parseFloat(jsjjn) + parseFloat(zyjn) + parseFloat(kxjs) + parseFloat(cxnl)
	 + parseFloat(kcjqpfj);
	 }
	  var dcj2 = document.getElementById('dcj').value;
	var zcj2 = document.getElementById('zcj').value;
	var tcj2 = document.getElementById('tcj').value;
	if(dcj2 == null || dcj2 == "" || dcj2 == " "){
		dcj2 = '0';
		document.getElementById('zhszcpzf').value = "";
	}
	if(zcj2 == null || zcj2 == "" || zcj2 == " "){
		zcj2 = '0';
		document.getElementById('zhszcpzf').value = "";
	}
	if(tcj2 == null || tcj2 == "" || tcj2 == " "){
		tcj2 = '0';
		document.getElementById('zhszcpzf').value = "";
	}
	document.getElementById('zhszcpzf').value = parseFloat(dcj2)
	 + parseFloat(zcj2) + parseFloat(tcj2);
}

function countsxzf(){
    var tcj = document.getElementById('tcj').value;
	var tydb = document.getElementById('tydb').value;
	var tyhd = document.getElementById('tyhd').value;
	var tsqk = document.getElementById('tsqk').value;
	var xljkhd = document.getElementById('xljkhd').value;
	var xlszzk = document.getElementById('xlszzk').value;
	if(tydb == null || tydb == "" || tydb == " "){
		tydb = '0';
		
	}
	if(tyhd == null || tyhd == "" || tyhd == " "){
		tyhd = '0';
		
	}
	if(tsqk == null || tsqk == "" || tsqk == " "){
		tsqk = '0';
		
	}
	if(xljkhd == null || xljkhd == "" || xljkhd == " "){
		xljkhd = '0';
		
	}
	if(xlszzk == null || xlszzk == "" || xlszzk == " "){
		xlszzk = '0';
		
	}
	var tcj1 = parseFloat(tydb) + parseFloat(tyhd)
	 + parseFloat(tsqk) + parseFloat(xljkhd) + parseFloat(xlszzk);
	 if (parseFloat(tcj1)!=0 && parseFloat(tcj) != 0 && parseFloat(tcj1) != parseFloat(tcj)) {
	 	alert('身心健康素质总分计算有误，请确认！');
	 	document.getElementById('tcj').value=parseFloat(tydb) + parseFloat(tyhd)
	 + parseFloat(tsqk) + parseFloat(xljkhd) + parseFloat(xlszzk);
	 }
	  var dcj2 = document.getElementById('dcj').value;
	var zcj2 = document.getElementById('zcj').value;
	var tcj2 = document.getElementById('tcj').value;
	if(dcj2 == null || dcj2 == "" || dcj2 == " "){
		dcj2 = '0';
		document.getElementById('zhszcpzf').value = "";
	}
	if(zcj2 == null || zcj2 == "" || zcj2 == " "){
		zcj2 = '0';
		document.getElementById('zhszcpzf').value = "";
	}
	if(tcj2 == null || tcj2 == "" || tcj2 == " "){
		tcj2 = '0';
		document.getElementById('zhszcpzf').value = "";
	}
	document.getElementById('zhszcpzf').value = parseFloat(dcj2)
	 + parseFloat(zcj2) + parseFloat(tcj2);
}

function zhszcpsave(mustFill,url) {
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		}
	}
	refreshForm(url);
}

function zhszcpexp(url){
	if (document.getElementById('xn').selectedIndex <= 0 || document.getElementById('bj').selectedIndex <= 0) {
		alert('导出数据是以学年，班级为单位进行导出，请确认!');
		return;
	} else {
		url += '?xydm='+document.getElementById('xy').value + '&zydm='+document.getElementById('zy').value+'&bjdm='+document.getElementById('bj').value+'&xn='+document.getElementById('xn').value;
		window.open(url);
	}
}

function modidata2(url,w,h) {
	if (curr_row==null ||curr_row=='') {
		alert('请选择所要操作行数据！');
	} else {
		url += curr_row.cells[0].getElementsByTagName("input")[0].value;
		showTopWin(url,w,h);
	}
}