
function sendStuInfo2() {
	var vel = window.dialogArguments.document.forms[0].xh;
	vel.focus();
	vel.value = replaceChar(curr_row.cells[1].innerText," ","");
	window.dialogArguments.autoFillStuInfo3(13, vel);
	window.close();
}

function autoFillStuInfo3(cod, obj) {
	if (cod == 13) {
		//if (obj.value == "" || obj.value.length < 6) {
			//alert("ѧ��λ������ȷ!");
			//return false;
		//}  else 
		if(document.getElementById("realTable").value == "hzjysqb"){
			document.forms[0].action = "/xgxt/hzjy_xxsq.do?from=" + document.forms[0].action;
			document.forms[0].submit();
		}
	}
}

function onSelRow(url){
	//�������
	var pkValue=curr_row.getElementsByTagName('input')[0].value;
	if(url.search('hzjy')>=0){
		url += "?tableName=hzjysqb"
	}
	url += "&pkValue="+pkValue+"&sh=sh";//���ѧ����Ϣ��־
	w=480;
	h=300;
	showTopWin(url,w,h);
}

function dateFormat(obj){
	//alert(obj.value);
	obj.value=obj.value.replace('-','').replace('-','');
}
function chkVal(){
	var userType = document.getElementById("userType").value;
	var sqkssj = document.getElementById("sqkssj").value;
	var sqjssj = document.getElementById("sqjssj").value;
	var bgkssj = document.getElementById("bgkssj").value;
	var bgjssj = document.getElementById("bgjssj").value;
	var xsazsqjssj = document.getElementById("xsazsqjssj").value;
	var xsazsqkssj = document.getElementById("xsazsqkssj").value;
	if(userType == 'xy'){
		var sqkssj1 = document.getElementById("sqkssj1").value;
		var sqjssj1 = document.getElementById("sqjssj1").value;
		var bgkssj1 = document.getElementById("bgkssj1").value;
		var bgjssj1 = document.getElementById("bgjssj1").value;
		var xsazsqjssj1 = document.getElementById("xsazsqjssj1").value;
		var xsazsqkssj1 = document.getElementById("xsazsqkssj1").value;
	}
	if(sqkssj==null ||sqkssj==""||sqjssj==null ||sqjssj==""||
	   bgkssj==null ||bgkssj==""||bgjssj==null ||bgjssj==""||
	   xsazsqjssj==null ||xsazsqjssj==""||xsazsqkssj==null ||xsazsqkssj=="") {
		alert("�п�ֵ������д������");
		    return false;
	}    
	if(sqkssj > sqjssj){
		alert("���������������ʱ�����ڿ�ʼʱ�䲻�Ϸ������������룡");
		return false;
	}
	if(bgkssj > bgjssj){
		alert("����������������������ʱ�����ڿ�ʼʱ�䲻�Ϸ������������룡");
		return false;
	}
	if(xsazsqkssj > xsazsqjssj){
		alert("��������ѧ�����õǼ��������ʱ�����ڿ�ʼʱ�䲻�Ϸ������������룡");
		return false;
	}
	if(userType == 'xy'){
	if(sqkssj < sqkssj1){
		alert('�����õĺ����������뿪ʼʱ������ѧУ���õ����뿪ʼʱ�䣡���������룡');
		return false;
	}
	if(sqjssj > sqjssj1){
		alert('�����õĺ��������������ʱ������ѧУ���õ��������ʱ�䣡���������룡');
		return false;
	}
	if(bgkssj < bgkssj1){
		alert('�����õı���������뿪ʼʱ������ѧУ���õ����뿪ʼʱ�䣡���������룡');
		return false;
	}
	if(bgjssj > bgjssj1){
		alert('�����õı�������������ʱ������ѧУ���õ��������ʱ�䣡���������룡');
		return false;
	}
	if(xsazsqkssj < xsazsqkssj1){
		alert('�����õ�ѧ�����õǼ����뿪ʼʱ������ѧУ���õ����뿪ʼʱ�䣡���������룡');
		return false;
	}
	if(xsazsqjssj > xsazsqjssj1){
		alert('�����õ�ѧ�����õǼ��������ʱ������ѧУ���õ��������ʱ�䣡���������룡');
		return false;
	}
	}
	return true;
}

function pageLoadBbsq(){//��������
	var bblx = document.forms[0].bblx.value;
	document.getElementById(bblx+"l").className = "xxk_on_l";
	document.getElementById(bblx+"m").className = "xxk_on_m";
	document.getElementById(bblx+"r").className = "xxk_on_r";
	document.getElementById(bblx).style.display = '';
}
function onclickPage(obj){//ת������Ӧҳ��
	var bblx = obj.id.substr(0,obj.id.length-1);
	document.forms[0].action = 'bbsqb.do?bblx='+bblx;
	document.forms[0].submit();
}

function chkNull2(){//only for ��������
	var bblx = document.getElementById("bblx").value;
	var xhArr = document.forms[0].xh;
	var xh = (bblx == "xsz" ? xhArr[0] : ((bblx=="ykt") ? xhArr[1] : xhArr[2]));
	if(xh.value == "" || xh == null){
	   alert("������ѧ�ţ�����");
	   return false;
	}   
}

function getValue(){
	var  rqvar = new Date();
	var rq =  rqvar.getYear()+"-"+rqvar.getMonth()+"-"+rqvar.getDay();
	var fzrq = prompt("   ���ڸ�ʽΪxxxx-xx-xx\n\n\n�����뷢֤���ڣ�",rq);
	var bfrq = prompt("   ���ڸ�ʽΪxxxx-xx-xx\n\n\n�����뷢֤���ڣ�",rq);
	if(fzrq != null ){
	document.getElementById("fzrq").innerText = fzrq;
	}
	document.getElementById("bfrq").innerText = bfrq;
	
	var xh = opener.curr_row.cells[1].innerText;
	var xm = opener.curr_row.cells[2].innerText;
	var xb = opener.curr_row.cells[3].innerText;
	var xymc = opener.curr_row.cells[4].innerText;
	var bjmc = opener.curr_row.cells[5].innerText;
	var xz = opener.curr_row.cells[6].innerText;
	var rxny = opener.curr_row.cells[7].innerText;
	
	document.getElementById("xm").innerText=xm;
	document.getElementById("xh").innerText=xh;
	document.getElementById("xb").innerText=xb;
	document.getElementById("xymc").innerText=xymc;
	document.getElementById("bjmc").innerText=bjmc;
	document.getElementById("xz").innerText=xz;
	document.getElementById("rxny").innerText=rxny;
	
}
//������Ϣѧ��֤��ӡ ��Ϣ���
function getValueForSzxx(){
	var  rqvar = new Date();
	var rq =  rqvar.getYear()+"-"+rqvar.getMonth()+"-"+rqvar.getDay();
	var fzrq = prompt("   �����뷢֤���ڣ�(���ڸ�ʽΪxxxx-xx-xx)",rq);
	var bfrq = prompt("   �����벹�����ڣ�(���ڸ�ʽΪxxxx-xx-xx)",rq);
	var yxrq = prompt("   ��������Ч������(���ڸ�ʽΪxxxx-xx-xx)",rq);
	if(fzrq != null ){
	//document.getElementById("fzrq").innerText = fzrq;
	}
	//document.getElementById("bfrq").innerText = bfrq;
	
	var xh = opener.curr_row.cells[1].innerText;
	var xm = opener.curr_row.cells[2].innerText;
	var xb = opener.curr_row.cells[3].innerText;
	var zymc = opener.curr_row.cells[4].innerText;
	var csrq = opener.curr_row.cells[8].innerText;	
	var jg = opener.curr_row.cells[7].innerText;
	var rxny = opener.curr_row.cells[6].innerText;
	
	document.getElementById("xm").innerText=xm;
	document.getElementById("xh").innerText=xh;
	document.getElementById("xb").innerText=xb;
	document.getElementById("jg").innerText=jg;
	document.getElementById("zymc").innerText=zymc;
	
	if(fzrq!=null){		
		document.getElementById("fzy").innerText=fzrq.substring(0,4);
		document.getElementById("fzm").innerText=fzrq.substring(fzrq.indexOf("-")+1,fzrq.lastIndexOf("-"));
		document.getElementById("fzd").innerText=fzrq.substring(fzrq.lastIndexOf("-")+1,fzrq.length);
		}
	if(bfrq!=null){
		document.getElementById("bfy").innerText=bfrq.substring(0,4);
		document.getElementById("bfm").innerText=bfrq.substring(bfrq.indexOf("-")+1,bfrq.lastIndexOf("-"));
		document.getElementById("bfd").innerText=bfrq.substring(bfrq.lastIndexOf("-")+1,bfrq.length);
		}
	if(yxrq!=null){
		document.getElementById("yxy").innerText=yxrq.substring(0,4);
		document.getElementById("yxm").innerText=yxrq.substring(yxrq.indexOf("-")+1,yxrq.lastIndexOf("-"));
		document.getElementById("yxd").innerText=yxrq.substring(yxrq.lastIndexOf("-")+1,yxrq.length);
		}
	if(rxny!=null){
		document.getElementById("rxy").innerText=rxny.substring(0,4);
		document.getElementById("rxm").innerText=rxny.substring(4,6);
		document.getElementById("rxd").innerText=rxny.substring(6,10);
	}
	if(csrq!=null){
		document.getElementById("csy").innerText=csrq.substring(0,4);
		document.getElementById("csm").innerText=csrq.substring(csrq.indexOf("-")+1,csrq.lastIndexOf("-"));
		document.getElementById("csd").innerText=csrq.substring(csrq.lastIndexOf("-")+1,csrq.length);
	}
}

//����ʦ��ѧ��֤���ӡ ��Ϣ���
function getValueForHbsf(){
	var  rqvar = new Date();
	var rq =  rqvar.getYear()+"-"+rqvar.getMonth()+"-"+rqvar.getDay();
	var fzrq = prompt("   �����뷢֤���ڣ�(���ڸ�ʽΪxxxx-xx-xx)",rq);
	var yxrq = prompt("   ��������Ч������(���ڸ�ʽΪxxxx-xx-xx)",rq);
	if(fzrq != null ){
	//document.getElementById("fzrq").innerText = fzrq;
	}
	//document.getElementById("bfrq").innerText = bfrq;
	
	var xh = opener.curr_row.cells[1].innerText;
	var xm = opener.curr_row.cells[2].innerText;
	var xb = opener.curr_row.cells[3].innerText;
	var csrq = opener.curr_row.cells[4].innerText;
	var jg = opener.curr_row.cells[5].innerText;
	var nj = opener.curr_row.cells[6].innerText;
	var xymc = opener.curr_row.cells[7].innerText;
	var zymc = opener.curr_row.cells[8].innerText;
	
	
	//var zymc = opener.curr_row.cells[4].innerText;
	//var csrq = opener.curr_row.cells[8].innerText;	
	//var jg = opener.curr_row.cells[7].innerText;
	//var rxny = opener.curr_row.cells[6].innerText;
	
	document.getElementById("xm").innerText=xm;
	document.getElementById("xh").innerText=xh;
	document.getElementById("xb").innerText=xb;
	document.getElementById("jg").innerText=jg;
	document.getElementById("xymc").innerText=xymc;
	document.getElementById("zymc").innerText=zymc;
	document.getElementById("nj").innerText=nj;
	
	if(fzrq!=null){		
		document.getElementById("fzy").innerText=fzrq.substring(0,4);
		document.getElementById("fzm").innerText=fzrq.substring(fzrq.indexOf("-")+1,fzrq.lastIndexOf("-"));
		document.getElementById("fzd").innerText=fzrq.substring(fzrq.lastIndexOf("-")+1,fzrq.length);
		}
	if(yxrq!=null){
		document.getElementById("yxy").innerText=yxrq.substring(0,4);
		document.getElementById("yxm").innerText=yxrq.substring(yxrq.indexOf("-")+1,yxrq.lastIndexOf("-"));
		document.getElementById("yxd").innerText=yxrq.substring(yxrq.lastIndexOf("-")+1,yxrq.length);
		}
	if(csrq!=null){
		document.getElementById("csy").innerText=csrq.substring(0,4);
		document.getElementById("csm").innerText=csrq.substring(4,6);
		document.getElementById("csd").innerText=csrq.substring(6,8);
	}
}














function getValueForShgc(){
	var  rqvar = new Date();
	var rq =  rqvar.getYear()+"-"+rqvar.getMonth()+"-"+rqvar.getDay();
	var rxrq = prompt("   ��������ѧ���ڣ�(���ڸ�ʽΪxxxx-xx-xx)",rq);
	var blrq = prompt("   ������������ڣ�(���ڸ�ʽΪxxxx-xx-xx)",rq);
	var nj = prompt("   ������ѧ���ڶ��꼶��","���꼶");
	var jbrxm = prompt("   �����뾭����������","xx");
	
	if(jbrxm!=""){
		document.getElementById("jsr").innerText=jbrxm;
	}
	if(rxrq!=""){
		document.getElementById("rxn").innerText=rxrq.substring(0,4);
		document.getElementById("rxy").innerText=rxrq.substring(rxrq.indexOf("-")+1,rxrq.lastIndexOf("-"));
	}
	if(blrq!=""){
		document.getElementById("year").innerText=blrq.substring(0,4);
		document.getElementById("month").innerText=blrq.substring(blrq.indexOf("-")+1,blrq.lastIndexOf("-"));
		document.getElementById("day").innerText=blrq.substring(blrq.lastIndexOf("-")+1,blrq.length);
	}
	var xh=opener.curr_row.cells[0].innerText;
	var xm=opener.curr_row.cells[1].innerText;
	var xb=opener.curr_row.cells[2].innerText;
	var xy=opener.curr_row.cells[4].innerText;
	var zy=opener.curr_row.cells[5].innerText;
	document.getElementById("xm").innerText=xm;
	document.getElementById("xb").innerText=xb;
	document.getElementById("xh").innerText=xh;
	document.getElementById("xy").innerText=xy;
	document.getElementById("zy").innerText=zy;	
}

function getValueForZxZdZm(){
	var  rqvar = new Date();
	var rq =  rqvar.getYear()+"-"+rqvar.getMonth()+"-"+rqvar.getDay();
	var nj = prompt("   ������ѧ�������꼶:","���꼶");
	var blrq = prompt("   ������������ڣ�(���ڸ�ʽΪxxxx-xx-xx)",rq);
	var year="";
	var month="";
	var day="";
	if(blrq!=""){
		year=blrq.substring(0,4);
		month=blrq.substring(blrq.indexOf("-")+1,blrq.lastIndexOf("-"));
		day=blrq.substring(blrq.lastIndexOf("-")+1,blrq.length);
	}
	var xh=opener.curr_row.cells[0].innerText;
	var xm=opener.curr_row.cells[1].innerText;
	var xy=opener.curr_row.cells[4].innerText;
	var zy=opener.curr_row.cells[5].innerText;
	var bj=opener.curr_row.cells[6].innerText;
	
	document.getElementById("xm").innerText=xm;
	document.getElementById("xm2").innerText=xm;
	document.getElementById("xh").innerText=xh;
	document.getElementById("xy").innerText=xy;
	document.getElementById("zy").innerText=zy;	
	document.getElementById("bj").innerText=bj;
	document.getElementById("year").innerText=year;
	document.getElementById("month").innerText=month;
	document.getElementById("day").innerText=day;
	document.getElementById("nj").innerText=nj;
	}
//��ֹ���������ֵ
function delNaN(){
	var content = this.value;//��ñ������value
	var regex = /[^\d]/g;
	this.value = content.replace(regex,'');
	
}

var currentrownum = 1;
function getCurrentRowNum(){
	var tabrows = window.dialogArguments.document.getElementById("rsTable").rows;
	for(i=1;i<tabrows.length;i++){
		var cell = tabrows[i].cells[0];
		if(cell.innerText== $("xh").innerText){
			currentrownum = i;	
			break;
		}
	}
}

function getNext(doType){
	var xh = document.getElementById("xh").innerText;
	var tabrows = window.dialogArguments.document.getElementById("rsTable").rows;
	if(currentrownum + 1 > tabrows.length-1){
		return false;
	}
	var rsStr="";
	if(doType == "jsxx"){
		var cols = ["jthdbx","skxxbx","ldwsbx","xjxgbx","qtbx","zzxx","xxtd","ddxy","shhd","fzgn","gbrzjf","xfxfjf","jjsjjf","glbsjf","wjcljf","psbxhj","zhpjhj","fjfhj"];
	}else if(doType == "zjjmzyjsxy"){
		var cols = ["ryjf","rzjf","bsjf","fkjf","kccjbjgkf","tybsjf","pjljf","cpcq","stsz","dlqk","hdqk","yydjjianf","jsjjianf","kyjf","zxksjf","yydjjf","jsjjf","qtjf","sxpdbx"];
	}else{
		var cols = ["ryjf","rzjf","bsjf","qsbsjf","cjhdjf","wjkf","jttbkf","qtjjfxx"];
	}
	document.getElementById("xh").innerText = tabrows[currentrownum+1].cells[0].innerText;
	document.getElementById("xm").innerText = tabrows[currentrownum+1].cells[1].innerText;
	
	var nextXh = tabrows[currentrownum+1].cells[0].innerText;
	var nextXn = tabrows[currentrownum+1].cells[2].innerText;
	var nextXq = tabrows[currentrownum+1].cells[3].innerText;
	
	getStuDyInfo.getStuDyInfo(nextXh+nextXn+nextXq,function(str){
		rsStr = str;
		var rsArr = rsStr.split("!!");
		for(i=0;i<cols.length;i++){
			document.getElementById(cols[i]).value = (rsArr[i]==undefined)?" ":rsArr[i];
		}
	});
	currentrownum++;
}

function trim(str){
	var regex = /^\s(.*)\s+$/g;
	str.replace(regex,"$1");
}
//�������ż����趨���水ť�õ�
function pjpy_jdsd_save(){
	document.forms[0].action = "pjpy_jxjdmb_jdsd.do?doType=save";
	document.forms[0].submit();
}

//////////////////sq �����ת����/////////////////////////////////////////
function mark(isType){
	if(curr_row == null){
		alert("��ѡ��Ҫ�������У�");
		return false;
	}
	var url = "";
	if(isType == "dyszjfwh"){
		url ='/xgxt/dyszwh.do?xh='+curr_row.cells[0].innerText+'&xn='+curr_row.cells[2].innerText+"&xq="+curr_row.cells[3].innerText;
	} else if(isType="hzjyazb"){
		url = "/xgxt/put_Info.do?pkValue="+curr_row.cells[1].innerText+curr_row.cells[3].innerText+curr_row.cells[4].innerText;
	}
//	alert(url);
	showTopWin(url,600,550);
}
/////////////////////sq����ϼƷ���/////////////////////////
function countHj(){
	var jthdbx = $("jthdbx").value;
	var skxxbx = $("skxxbx").value;
	var ldwsbx = $("ldwsbx").value;
	var xjxgbx = $("xjxgbx").value;
	var qtbx = $("qtbx").value;
	var zzxx = $("zzxx").value;
	var xxtd = $("xxtd").value;
	var ddxy = $("ddxy").value;
	var shhd = $("shhd").value;
	var fzgn = $("fzgn").value;
	var gbrzjf = $("gbrzjf").value;
	var xfxfjf = $("xfxfjf").value;
	var jjsjjf = $("jjsjjf").value;
	var glbsjf = $("glbsjf").value;
	var wjcljf = $("wjcljf").value;
	var dyzf = $("dyzf").value;
	if(dyzf == null || dyzf == ""){
		dyzf = "0";
		$("dyzf").value = "0";
	}
	if(jthdbx == null || jthdbx == ""){
		jthdbx = "0";
		$("jthdbx").value = "0";
	}
	if (skxxbx == null || skxxbx == ""){
		skxxbx = "0";
		$("skxxbx").value = "0";
	}
	if (ldwsbx == null || ldwsbx == ""){
		ldwsbx = "0";
		$("ldwsbx").value = "0";
	}
	if (xjxgbx == null || xjxgbx == ""){
		xjxgbx = "0";
		$("xjxgbx").value = "0";
	}
	if (qtbx == null || qtbx == ""){
		qtbx = "0";
		$("qtbx").value = "0";
	}
	if (zzxx == null || zzxx == ""){
		zzxx = "0";
		$("zzxx").value = "0";
	}
	if (xxtd == null || xxtd == ""){
		xxtd = "0";
		$("xxtd").value = "0";
	}
	if (ddxy == null || ddxy == ""){
		ddxy = "0";
		$("ddxy").value = "0";
	}
	if (shhd == null || shhd == ""){
		shhd = "0";
		$("shhd").value = "0";
	}
	if (fzgn == null || fzgn == ""){
		fzgn = "0";
		$("fzgn").value = "0";
	}
	if (gbrzjf == null || gbrzjf == ""){
		gbrzjf = "0";
		$("gbrzjf").value = "0";
	}
	if (xfxfjf == null || xfxfjf == ""){
		xfxfjf = "0";
		$("xfxfjf").value = "0";
	}
	if (jjsjjf == null || jjsjjf == ""){
		jjsjjf = "0";
		$("jjsjjf").value = "0";
	}
	if (glbsjf == null || glbsjf == ""){
		glbsjf = "0";
		$("glbsjf").value = "0";
	}
	if (wjcljf == null || wjcljf == ""){
		wjcljf = "0";
		$("wjcljf").value = "0";
	}
	$("psbxhj").value = (parseInt(jthdbx) + parseInt(skxxbx) + parseInt(ldwsbx) + parseInt(xjxgbx) + parseInt(qtbx));
	$("zhpjhj").value = (parseInt(zzxx) + parseInt(xxtd) + parseInt(ddxy) + parseInt(shhd) + parseInt(fzgn));
	$("fjfhj").value = (parseInt(gbrzjf) + parseInt(xfxfjf) + parseInt(jjsjjf) + parseInt(glbsjf) + parseInt(wjcljf));
	if(dyzf == "0" || dyzf == "" || dyzf == null){
		$("dyzf").value = parseInt($("psbxhj").value) + parseInt($("zhpjhj").value) + parseInt($("fjfhj").value);
	}
}

//��ʾҪ����Ľ�ѧ��
function showTop(){	
	var inner_html = "";
	getJxjList.getJxjList("aa",function(str){		
		inner_html += "<center>";
		inner_html += "<table class='tbstyle' align='center'>";
		inner_html += "<tr>";
		inner_html += "<td>";
		inner_html += "��ѡ��������Ľ�ѧ��";
		inner_html += "</td>";
		inner_html += "<td>";
		var records = str.split("@@");
		var rsArr = new Array(str.length);
		for(i=0;i<records.length;i++){
			rsArr[i] = new Array(2);
			var subArr = records[i].split("!!");
			for(j=0;j<2;j++){				
				rsArr[i][j] = subArr[j];
			}
		}
		inner_html += "<select id='selId'>";
		//inner_html += "<option value=''></option>";	
		for(i=0;i<records.length;i++){
			inner_html += "<option value='"+rsArr[i][0]+"'>"+rsArr[i][1]+"</option>";						
		}
		inner_html += "</select>";
		inner_html += "</td>";
		inner_html += "</tr>";
		inner_html += "<tfoot>";
		inner_html += "<tr>";
		inner_html += "<td align='center' colspan='2'>";
		inner_html += "<button class='button2' onclick='applyForPriseSubmit()'>ȷ��</button>";
		inner_html += "<button class='button2' onclick='closeAdd()'>ȡ��</button>";
		inner_html += "</td>";
		inner_html += "</tr>";
		inner_html += "</tfoot></table>";
		showDiv(inner_html, 300, 100);		
	});
	
}
//�Ϻ����̽�ѧ������ǰ�Ĳ������ʾ�������б�ѡ������Ӧҳ��
function applyForPriseSubmit(){
	var jxjdm = document.getElementById('selId').value;
	//�������㡢��ǿ��ѧ��ҳ�棬�������صȽ���һ���������Ƚ������¼ס��ң���ǿ�ף���
	if(jxjdm=='0000000001'||jxjdm=='0000000002'||jxjdm=='0000000003'||
			jxjdm=='0000000003'||jxjdm=='0000000004'||jxjdm=='0000000005'||jxjdm=='0000000006'||jxjdm=='0000000007'||jxjdm=='0000000008'){
		window.location="pjpy_apply.do?haveRequested=yes&jxjdm="+jxjdm;
	} else if(jxjdm=="0000000009"){//���ֽ�ѧ��ҳ��
		//alert("baogang="+jxjdm);
		window.location = "pjpy_apply_bg.do?jxjdm="+jxjdm;
	} else if(jxjdm=="0000000010"){//������ѧ��
		window.location = "pjpy_shanglianPrise_apply.do?jxjdm="+jxjdm;
	} else if(jxjdm=="0000000011"){//���άѧ��
		//alert("sony="+jxjdm);
		window.location = "pjpy_sonyprise_apply.do?jxjdm="+jxjdm;
	} else if(jxjdm=="0000000012"){//���˽�ѧ��
		window.location = "pjpy_jiaoyunPrise_apply.do?jxjdm="+jxjdm;
	} else if(jxjdm=="0000000013"){//������ѧ��
		window.location = "pjpy_apply_dt.do?jxjdm="+jxjdm;
	}
	closeAdd();	
}

////////////////////////////sq �ж����ָ�ʽ/////////////////////////////
function dataFormat(){
	if($("psbxhj").value.match(/^\d+\.{0,1}\d{0,3}$/)==null || $("psbxhj").value>60){
		alert("ƽʱ�������ݸ�ʽ������Ѿ�������߷֣���ȷ��");
		return false;
	}else if($("zhpjhj").value.match(/^\d+\.{0,1}\d{0,3}$/)==null || $("zhpjhj").value>40){
		alert("�ۺ��������ݸ�ʽ������Ѿ�������߷֣���ȷ��");
		return false;
	}else if($("fjfhj").value.match(/^\d+\.{0,1}\d{0,3}$/)==null){
		alert("���ӷ����ݸ�ʽ������ȷ��");
		return false;
	}else{
		return true;
	}
}
//////////////////////////sq �жϱ����ֶ�///////////////////////////////
function needData(){
	if($("xn")){
		if($("xn").value == "" || $("xn").value == null){
			alert("�������ֶβ���Ϊ��");
			return false;
		}
	}
	if($("xq")){
		if($("xq").value == "" || $("xq").value == null){
			alert("�������ֶβ���Ϊ��");
			return false;
		}
	}
	if($("xtydm")){
		if($("xtydm").value == "" || $("xtydm").value == null){
			alert("�������ֶβ���Ϊ��");
			return false;
		}
	}
	if($("xtyxm")){
		if($("xtyxm").value == "" || $("xtyxm").value == null){
			alert("�������ֶβ���Ϊ��");
			return false;
		}
	}
	
	return true;
}

//////////////////////sq �жϵ�ǰ���Ƿ�ѡ��///////////////////////
function selRow(){
	if(curr_row==null){
		alert("��ѡ��Ҫ�������У�");
		return false;
	}
	return true;
}
////////////////////////sq �ɼ�����///////////////////////////
function cjSave(url){
	var rs;
	var rsTable = document.getElementById("rsTables").rows;
	var rsTableLength = document.getElementById("rsTables").rows.length;
	var cj = document.all("text");
	for(i=0; i<cj.length; i++){
		if(cj[i].value.match(/^\d+\.{0,1}\d{0,3}$/)==null){
			alert("�ɼ���ʽ����Ϊ����,����!");
			return false;
		}
	}
	document.forms[0].action = url;
	document.forms[0].submit();
}

//�Ϻ����̴�ѧѧ�����㽱ѧ��	�ύ����
function shgcPriseApplication(){
	var jxjlx = document.forms[0].jxjlx.value;
	var jxjdm = '';	//��ѧ�����
	
	var action = "";
	if(jxjlx=="excellentstu"){//����ѧ��
		/*alert(document.forms[0].excellentStu.outerHTML);
		if (document.forms[0].excellentStu!=null && document.forms[0].excellentStu.value.trim()!='') {
			jxjdm += document.forms[0].excellentStu.value.trim();	//�ء�һ���������Ƚ�
		}
		if (document.forms[0].innovationfirst!=null && document.forms[0].innovationfirst.value.trim()!='') {
			jxjdm += "-"+document.forms[0].innovationfirst.value.trim();	//���¼׵Ƚ�
		}
		if (document.forms[0].innovationsecond!=null && document.forms[0].innovationsecond.value.trim()!='') {
			jxjdm += "-"+document.forms[0].innovationsecond.value.trim();	//�����ҵȽ�
		}
		if (document.forms[0].zqPrise!=null && document.forms[0].zqPrise.value.trim()!='') {
			jxjdm += "-"+document.forms[0].zqPrise.value.trim();	//��ǿ��ѧ��
		}*/
		action = "pjpy_apply.do?doType=save&haveRequested=yes";			
	} else if(jxjlx=="baosteel"){//���ֽ�ѧ��
		jxjdm = document.forms[0].jxjdm.value.trim();
		action = "pjpy_apply_bg.do?doType=save&jxjdm="+jxjdm;
	} else if(jxjlx=="sony"){	//���άѧ��
		jxjdm = document.forms[0].jxjdm.value.trim();
		action = "pjpy_apply_sony.do?doType=save&jxjdm="+jxjdm;
	} else if (jxjlx == "detie") {	//�Ϻ�����������
		jxjdm = document.forms[0].jxjdm.value.trim();
		action = "pjpy_apply_dt.do?doType=save&jxjdm="+jxjdm;
	} else if (jxjlx == "jiaoyun") {	//���˽�ѧ��
		jxjdm = document.forms[0].jxjdm.value.trim();
		action = "pjpy_jiaoyunPrise_apply.do?doType=save&jxjdm="+jxjdm;
	} else if (jxjlx == "shanglian") {	//������ѧ��
		jxjdm = document.forms[0].jxjdm.value.trim();
		action = "pjpy_shanglianPrise_apply.do?doType=save&jxjdm="+jxjdm;
	}
	
	document.forms[0].action = action;
	document.forms[0].submit();
}

function shgcPriseAppicationPrint(){
	document.forms[0].action = "pjpy_apply_print.do";
	document.forms[0].submit();
}

function hzjyCoorInfo(doType){
	var page = document.forms[0].titName.value;
	var pkValue = "";
	var pk = document.forms[0].pk.value;
	var url = "/xgxt/coorDetail.do?act=";
	url += doType;
	url += "&tabName=";
	url += page;
	url += "&pk=";
	url += pk;
	url += "&pkValue=";
	
	if (doType == "modi" && curr_row == null) {
		alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
		return false;
	}
	
	if (doType == "del"){
		if(curr_row == null){
			alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
			return false;
		}else{
			pkValue = curr_row.cells[0].innerText;
			url += pkValue;
			if(confirm("ȷ��Ҫɾѡ�м�¼��")){
				refreshForm(url);
				return true;
			}else{
				return false;
			}
		}
	}
	if(doType != "add"){
		pkValue = curr_row.cells[0].innerText;
	}
	
	url += pkValue;
	showTopWin(url, 600, 450);
}

function selCoorInfo(){
	var xty = curr_row.cells[0].innerText;
	var xtyxm = curr_row.cells[1].innerText;
	var xtysjh = curr_row.cells[4].innerText;
	window.dialogArguments.document.getElementById("xty").value = xty;
	window.dialogArguments.document.getElementById("xtyxm").value = xtyxm;
	window.dialogArguments.document.getElementById("xtysjh").value = xtysjh;
	Close();
}

function chkInput(obj){
	var key = event.keyCode;
	if((key>=48&&key<=57)||key==46||key==127||key==8||(key>=96&&key<=105)||key==110){
	}
	else{
		var data=obj.value;
		var reg = /^\d+\.{0,1}\d{0,3}$/;
		var role = data.match(reg);
		if(role==null&&data!=""){
			alert("������Ӣ��״̬�µ�����!")
            obj.value="0";
            return false;
        }
	}
}

function changeSqzl(){
	var sqzl = document.getElementById("sqzl").value;
	if(sqzl == "B"){
		document.getElementById("lztj").style.display = "";
	}else{
		document.getElementById("lztj").style.display = "none";
	}
}
function SaveApply(mustFill,url){
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("�뽫��\"*\"�ŵ���Ŀ����������");
			return false;
		}
	}
	document.forms[0].action = url;
	document.forms[0].submit();
}

function SaveCheck(url){
	document.forms[0].action = url;
	document.forms[0].submit();
}

//�鿴ѧ��ѧҵ�ɼ�
function ShowStuCjInfo(objTr){
	var tn = '';
	if ($('tableName')) {
		tn = document.getElementById('tableName').value;
	}
	curr_row = objTr;
	var url;
	if (tn == 'view_zhszcp') {
		url = "/xgxt/stu_cj_details.do?xh=" + curr_row.cells[3].innerText;
	} else {
		url = "/xgxt/stu_cj_details.do?xh=" + curr_row.cells[2].innerText;
	}
	showTopWin(url, 800, 600);
}

//ȫ��ɾ������
function Alldel(){
	if(confirm("ȷ��Ҫȫ��ɾ����ʾ��¼��")){
		if(confirm("ȫ��ɾ���󣬽��޷��ָ���ȷ��Ҫȫ��ɾ����")){
			document.forms[0].action = "/xgxt/del_All_data.do";
			document.forms[0].submit();
		}else{
			return false;
		}
	}else{
		return false;
	}
}

//ͳһȫ�����ɽ�ʦ����
function showAllTeaUser() {
	if (confirm("ȷʵҪ��ʼ�����н�ʦ�û�������?      \n ִ�иò����Ժ���˲���������������û����뽫������Ϊ'888888'")) {
		showTips();
		document.forms[0].action = "AllTeaUser.do";
		document.forms[0].submit();
		return true;
	}
}

function showOneTeaUser(){
	var yhm = document.getElementById('topGroup').value;
	if (confirm("ȷʵҪ���ø��û�������?      \n ���ú��û�������Ϊ'888888'")) {
		document.forms[0].action = "chagTeaPass.do?userName="+yhm;
		document.forms[0].submit();
		return true;
	}
}

//�û���ѯ
function userQuerry() {
	var yhm = document.getElementById("yhm").value;
	var xm = document.getElementById("xm").value;
	var zdm = document.getElementById("zdm").value;
	var szbm = document.getElementById("xydm").value;
	var purview = document.getElementById("purview").value;
	var querry="";
	if(yhm == "" || yhm == null){
		yhm = "%";
	}else{
		yhm = "%" + yhm + "%";
				}
	if(xm == "" || xm == null){
		xm = "%";
			} else {
		xm = "%" + xm + "%";
			}
	if(zdm == "" || zdm == null){
		zdm = "%";
		} else {
		zdm = "%" + zdm + "%";
		}
	if(szbm == "" || szbm == null){
		szbm = "%";
	} else{
		szbm = "%" + szbm + "%";
	}
	if(purview == "" || purview == null){
		purview = "%";
	} else{
		purview = "%" + purview + "%";
	}
	querry += yhm;
	querry += "!!-!!";
	querry += xm;
	querry += "!!-!!";
	querry += zdm;
	querry += "!!-!!";
	querry += szbm;
	querry += "!!-!!";
	querry += purview;
	GetListData.getYhInfo(querry,TjQuUserInfo);
	}

//�ۺ����ʲ�����Ӧѧ�������б�
function genNdList(obj){
	var xn = obj.value; 
	var opt = document.createElement("option");
	var opt1 = document.createElement("option");
	var ndlist = document.getElementById("ndList");
	opt.value = xn.substr(0,4);
	opt.appendChild(document.createTextNode(xn.substr(0,4)));
	ndlist.appendChild(opt);
	opt1.value = xn.substr(5,4);
	opt1.appendChild(document.createTextNode(xn.substr(5,4)));
	ndlist.appendChild(opt1);
}

//��ʾ�б������ƶ�
function moveIndexV(flag){
	var couIndex = document.getElementById("titList").options.length;
	var selIndex;
	var tmpV;
	var tmpT;
	var colsV = document.getElementById("cols").value;
	if(colsV != ""){
		alert("�����ƺ�λ֮����ѡ����Ҫ��ʾ���ֶ�!");
		return false;
	}
	if(flag == "Vup"){
		selIndex = document.getElementById("titList").selectedIndex;
		if(selIndex<0){
			alert("��ѡ��Ҫ�ƶ����ֶ�!");
			return false;
		}else if(selIndex==0){
			alert("��ǰ�ѵ���һλ!");
			return false;
		}else{
			tmpV = document.getElementById("titList").options[selIndex - 1].value;
			tmpT = document.getElementById("titList").options[selIndex - 1].text;
			document.getElementById("titList").options[selIndex - 1].value = document.getElementById("titList").options[selIndex].value;
			document.getElementById("titList").options[selIndex - 1].text = document.getElementById("titList").options[selIndex].text;
			document.getElementById("titList").options[selIndex].value = tmpV;
			document.getElementById("titList").options[selIndex].text = tmpT;
			document.getElementById("titList").selectedIndex = selIndex - 1;
		}
	}else if(flag=="Vdown"){
		selIndex = document.getElementById("titList").selectedIndex;
		if(selIndex<0){
			alert("��ѡ��Ҫ�ƶ����ֶ�!");
			return false;
		}else if(selIndex==(couIndex-1)){
			alert("��ǰ�ѵ����λ!");
			return false;
		}else{
			tmpV = document.getElementById("titList").options[selIndex + 1].value;
			tmpT = document.getElementById("titList").options[selIndex + 1].text;
			document.getElementById("titList").options[selIndex + 1].value = document.getElementById("titList").options[selIndex].value;
			document.getElementById("titList").options[selIndex + 1].text = document.getElementById("titList").options[selIndex].text;
			document.getElementById("titList").options[selIndex].value = tmpV;
			document.getElementById("titList").options[selIndex].text = tmpT;
			document.getElementById("titList").selectedIndex = selIndex + 1;
		}
	}
}

//��ʼ��ͳ��cols
function InitCols(){
	var Vcol = document.forms[0].cols.value.split("!!SplitSignOne!!");
	var couIndex = document.getElementById("titList").options.length;
	var colsV;
	for(i=0; i<Vcol.length; i++){
		for(j=0; j<couIndex; j++){
			colsV = document.getElementById("titList").options[j].value;
			if(Vcol[i]==colsV){
				document.getElementById("titList").options[j].text = document.getElementById("titList").options[j].text.replace("\xd7", "��");
				break;
			}
		}
	}
}

function displayDe(){
	document.getElementById("DetailInfo").style.display="";
	var jlsj = curr_row.cells[0].innerText.trim();
	var cfsj = curr_row.cells[1].innerText.trim();
	var xh = document.getElementById("xh").value;
	var querry = "";
	if(xh == "" || xh == null){
		xh = "%";
	}else{
		xh = xh;
	}
	if(jlsj == "" || jlsj == null){
		jlsj = "%";
	}else{
		jlsj = jlsj;
	}
	if(cfsj == "" || cfsj == null){
		cfsj = "%";
	}else{
		cfsj = cfsj;
	}
	querry += xh;
	querry += "!!-!!";
	querry += jlsj;
	querry += "!!-!!";
	querry += cfsj;
	GetListData.getDykpDeInfo(querry,TjStuDykpDeInfo);
}

//��ʾ�û�Ȩ��
function disUserPower(){

	var querry = "";
	var yhm = document.getElementById("topGroup").value;
	var sUserName = document.getElementById("sUserName").value;
	if(yhm == "" || yhm == null){
		yhm = "%";
	}else{
		yhm = yhm;
	}
	if(sUserName == "" || sUserName == null){
		sUserName = "%";
	}else{
		sUserName = sUserName;
	}
	querry += yhm;
	querry += "!!-!!";
	querry += sUserName;

	if (powerChanged) {
		if (confirm("��ǰ���Ȩ�޷����˱仯��Ҫ������\n���\"ȷ��\"���������ݣ����\"ȡ��\"�����������ģ�")) {
			if (document.forms[0].groupPower.options.length <= 0) {
				alert("���Ȩ�޲�����Ϊ�գ�����");
				return false;
			}
			showTips();
			document.forms[0].action = "saveUserPower.do";
			var groupPower = new Array();
			for (i = 0; i < document.forms[0].groupPower.options.length; i++) {
				p = document.forms[0].groupPower.options[i].value.substring(0, 1);
				q = document.forms[0].groupPower.options[i].value.substring(1, document.forms[0].groupPower.options[i].value.length);
				groupPower[groupPower.length] = p + ":" + q;
			}
			document.forms[0].power.value = groupPower;
			document.forms[0].submit();
		} else {
			powerChanged = false;
			GetListData.getUserPower(querry,TjUserPower);
			//������ϵͳ����ģ��
			GetListData.getSubList(querry,TjUnAllotSubList);
			//���ص�ǰ��ϵͳ����ģ��
			GetListData.getUnAllotPower(querry,TjUnAllotPower);
		}
	} else {
		GetListData.getUserPower(querry,TjUserPower);
		//������ϵͳ����ģ��
		GetListData.getSubList(querry,TjUnAllotSubList);
		//���ص�ǰ��ϵͳ����ģ��
		GetListData.getUnAllotPower(querry,TjUnAllotPower);
	}
}

// ����������ģ��
function dataStencilExport() {
	document.forms[0].action = "expDataStencil.do?method=stencil";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}


function selRadioIndex(obj,flag){
	document.getElementById(obj).value = flag;
}

function updaterychxx(typ) {
				if (curr_row==null || curr_row=='') {
					alert('����ѡ��Ҫ������������,����һ�м���!');
					return;
				} else {
					if (typ == 'modi') {
						var fdysh = curr_row.cells[5].innerText;
						if (fdysh == 'ͨ��') {
							alert('����ز�����˲���ͨ��,�������޸�!');
							return;
						} else {
							var pk = curr_row.getElementsByTagName("input")[0].value;
							showTopWin('/xgxt/credit_apply.do?act=modi&pkValue=' + pk+ '&xh='+curr_row.cells[2].innerText, 800, 650);
						}
					} else {
						var fdysh = curr_row.cells[5].innerText;
						if (fdysh == 'ͨ��') {
							alert('����ز�����˲���ͨ��,�������޸�!');
							return;
						} else {
							if (confirm('ȷ��Ҫɾ����ѡ��������?')) {
								refreshForm('sturychdel.do?pkValue=' + curr_row.getElementsByTagName("input")[0].value + '&xh='+curr_row.cells[2].innerText);
							}
							return;
						}
					}
				}
			}


function chec(){
      for(i=0;i<document.getElementsByName("pkV").length;i++){
    	if(document.getElementsByName("pkV")[i].disabled == false){
    		document.getElementsByName("pkV")[i].checked=document.getElementsByName("all")[0].checked;
    	}
      }
    }