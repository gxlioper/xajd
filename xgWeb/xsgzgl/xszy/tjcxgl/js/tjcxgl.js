//����ͳ����Ϣ����
function qstjExport() {
	var url = "qsppgl.do?method=qstjExport";
	setSearchTj();
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].submit();
}

//����ͳ����Ϣ�����ŵ���
function qstjExportGroup() {
	var url = "qsppgl.do?method=qstjExportGroup";
	setSearchTj();
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].submit();
}

function fwExport(){
	var url = "qsppgl.do?method=fwExport";
	setSearchTj();
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].submit();
}

//��ϵ����ӡ
function lxkPrint(){
	var rows = jQuery("#dataTable").getSeletRow();
	var lddm = "";
	var qsh = "";
	var nj = "";
	var dl = "";
	var count=0;
	 if(rows.length == 1){
		if(null==rows[0]["qsgxid"]){
			showAlertDivLayer("������δ��������֮�ѣ������ӡ��");
			return false;
		}
		lddm = rows[0]["lddm"];
		qsh = rows[0]["qsh"];
		nj = rows[0]["nj"];
		dl = rows[0]["dl"];
	
		var url = "qsppgl.do?method=lxkPrint&lddm="+lddm+"&qsh="+qsh+"&nj="+nj+"&dl="+encodeURI(encodeURI(dl));
		window.open(url);
	}
	else{
		for ( var i = 0; i < rows.length; i++) {
		 if(null!=rows[0]["qsgxid"]){
			if(i==rows.length-1){
				lddm +=rows[i]["lddm"];
				qsh +=rows[i]["qsh"];
				nj +=rows[i]["nj"];
				dl += rows[i]["dl"];
			}else{
				lddm +=rows[i]["lddm"]+",";
				qsh +=rows[i]["qsh"]+",";
				nj +=rows[i]["nj"]+",";
				dl += rows[i]["dl"]+",";
			}
		 }else{
			count++; 
		 }
		}
	 if(0!=count){
		 showAlertDivLayer("��ѡ����δ��������֮�ѣ������ӡ��");
			return false; 
	 }
		var url ="qsppgl.do?method=lxkPrintZip";
		setSearchTj();
		url = addSuperSearchParams(url);
		document.forms[0].action = url;
		document.forms[0].submit();
	}
	
}
//����֤���ӡ
function ryzsPrint(){
	var rows = jQuery("#dataTable").getSeletRow();
	var zgh = "";
	var xm = "";
	var nj = "";
	var count=0;
	if(rows.length ==0){
		showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
		return false;
	}
	
	else if(rows.length == 1){
		if(null==rows[0]["qsgxid"]){
			showAlertDivLayer("������δ��������֮�ѣ������ӡ��");
			return false;
		}
		zgh = rows[0]["zgh"];
		xm = rows[0]["xm"];
		nj = rows[0]["nj"];
		var url = "qsppgl.do?method=ryzsPrint&xm="+xm+"&nj="+nj+"&zgh="+zgh;
		window.open(url);
	}
	else{
		for ( var i = 0; i < rows.length; i++) {
		 if(null!=rows[i]["qsgxid"]){
			if(i==rows.length-1){
				zgh += rows[i]["zgh"];
				xm += rows[i]["xm"];
				nj += rows[i]["nj"];
			}else{
				zgh += rows[i]["zgh"]+",";
				xm += rows[i]["xm"]+",";
				nj += rows[i]["nj"]+",";
				
			}
		 }else{
			count++; 
		 }
		}
	 if(rows.length==count){
		 showAlertDivLayer("��ѡ����δ��������֮�ѣ������ӡ��");
			return false; 
	 }
		var url ="qsppgl.do?method=ryzsPrintZip&xm="+xm+"&nj="+nj+"&zgh="+zgh;
		window.open(url);
	}
	
}