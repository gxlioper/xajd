function qsxxView(lddm,qsh,nj,sfhhqs) {
	showDialog("寝室信息查看", 800, 600, "xszyXsqshf.do?method=qsxxView&lddm=" + lddm
			+ "&qsh=" + qsh+"&nj="+nj+"&sfhhqs="+sfhhqs);
}

function rzxsView(lddm,qsh,nj) {
	showDialog("入住学生信息查看", 800, 600, "xszyXsqshf.do?method=rzxsView&lddm=" + lddm
			+ "&qsh=" + qsh+"&nj="+nj);
}

function qshLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='qsxxView(\""
			+ rowObject["lddm"] + "\",\"" + cellValue + "\",\""
			+rowObject["nj"] + "\", \""+rowObject["sfhhqs"] + "\");'>" + cellValue+"</a>";
}

function rzrsLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='rzxsView(\""
			+ rowObject["lddm"] + "\",\"" + rowObject["qsh"] + "\",\""
			+ rowObject["nj"] + "\");'>" + cellValue
			+ "</a>";
}