/*
�ۺ����ʲ������ݱ���
*/
function zhszcpsave(mustFill,url) {
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("�뽫��\*�ŵ���Ŀ��д������");
			return false;
		}
	}
	refreshForm(url);
}

/*
�ۺ����ʲ������ݵ���
*/
function zhszcpExp(url){
	var xydm = document.getElementById('xy').value;
	var zydm = document.getElementById('zy').value;
	var bjdm = document.getElementById('bj').value;
	var nj = document.getElementById('nj').value;
	var xn = document.getElementById('xn').value;
	var nd = document.getElementById('nd').value;
	var xq = document.getElementById('xq').value;
	var xh = document.getElementById('xh').value;
	
	url += '&xydm='+ xydm + '&zydm='+ zydm +'&bjdm=' + bjdm +'&xn='+xn + '&nd=' + nd + '&xq=' + xq + '&xh=' + xh+ '&nj=' + nj; 
	window.open(url);
}

/*
�ۺ����ʲ������ݵ���
*/
function xjbjExp(url){
	var xydm = document.getElementById('xy').value;
	var zydm = document.getElementById('zy').value;
	var bjdm = document.getElementById('bj').value;
	var nj = document.getElementById('nj').value;
	var xn = document.getElementById('xn').value;
	var xq = document.getElementById('xq').value;
	
	url += '&xydm='+ xydm + '&zydm='+ zydm +'&bjdm=' + bjdm +'&xn='+xn + '&xq=' + xq + '&nj=' + nj; 
	window.open(url);
}

/*
 ��ʾ�꼶��ѧԺ��רҵ���༶�б� 
*/
function showItems(){
	var items = document.getElementById("items");
	items.style.left = (screen.availwidth)/6;
	items.style.top = ((screen.availheight)/6)-20;
	items.style.display = "block";
}

/*
 �����꼶��ѧԺ��רҵ���༶�б� 
*/
function hideItems(){
	var items = document.getElementById("items");
	items.style.display = "none";
	document.getElementById("bjdm").value = document.getElementById("bj").value;
	document.getElementById("bjmc").value = document.getElementById("bj").options[document.getElementById("bj").selectedIndex].text;
}