//����֤���ӡ

var mb = 0;
function colorOn(){	
	for(i = 0;i<mbT.rows.length;i++){	
		for(j = 0;j<mbT.rows[i].cells.length;j++){
			document.all.mbT.rows[i].cells[j].style.backgroundColor = "#FFFFFF";			
			document.all.mbT.rows[i].cells[mb].style.backgroundColor = "#ffdead";
		}
	}
}

function printzzdxZS(mod){
	if(mb >3 || mb <0){
		mb = 0;
		colorOn();
	}
	if (mod=='one'){//������ӡ
		var url ='zzdxzsprint.do?xh=';
		var hjxn;
		var hjxjmc;
		var hjny;
		if (curr_row==null || curr_row==''){
			alert('��ѡ��Ҫ������ӡ��������!');
			return;
			//hjxn = prompt("   ����������ѧ��ȣ�(���ڸ�ʽΪ:xxxx-xxxx)","");
			//hjxjmc = prompt("   �������������ƣ�(��ʽ����:�����ҵ��)","");
			//hjny = prompt("   ���������������գ�(���ڸ�ʽΪ:xxxx-xx-xx)","");
		}else{
			hjxn = prompt("   ����������ѧ��ȣ�(���ڸ�ʽΪ:xxxx-xxxx)","");
			hjxjmc = prompt("   �������������ƣ�(��ʽ����:�����ҵ��)","");
			hjny = prompt("   ���������������գ�(���ڸ�ʽΪ:xxxx-xx-xx)","");
			url += curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += '&xm='
			url += curr_row.cells[0].getElementsByTagName("input")[1].value;
		}
		var ull = '&hjxn='+hjxn+'&hjxjmc='+hjxjmc+'&hjny='+hjny;
			url += ull;
		window.open(url);
		return;
	}
	if (mod=='test') {//��ӡ����ҳ
		var hjxn = prompt("   ����������ѧ��ȣ�(���ڸ�ʽΪ:xxxx-xxxx)","");
		var hjxjmc = prompt("   �������������ƣ�(��ʽ����:�����ҵ��)","");
		var hjny = prompt("   ���������������գ�(���ڸ�ʽΪ:xxxx-xx-xx)","");
		var url = 'zzdxzsprint.do?xm=';
		if (curr_row!=null && curr_row!='') {
			url += curr_row.cells[0].getElementsByTagName("input")[1].value;
		}
		url += '&hjxn='+hjxn+'&hjxjmc='+hjxjmc+'&hjny='+hjny;
		window.open(url);
		return;
	}
	if (mod=='more') {//����
		var hjxn = prompt("   ����������ѧ��ȣ�(���ڸ�ʽΪ:xxxx-xxxx)","");
		var hjxjmc = prompt("   �������������ƣ�(��ʽ����:�����ҵ��)","");
		var hjny = prompt("   ���������������գ�(���ڸ�ʽΪ:xxxx-xx-xx)","");
		var url = '&hjxn='+hjxn+'&hjxjmc='+hjxjmc+'&hjny='+hjny;
		if (confirm("ȷ��Ҫ���˲�����?")){
    	var xm;
		if($("tabPri").rows.length > 1){
			rowOnClick($("tabPri").rows[0]);
			xm=$("tabPri").rows[0].cells[3].innerText.trim();
			window.open("zzdxzsprintmore.do?xm="+xm+url);
			BatAlert.closeTips();
		 } else{
		    BatAlert.MyAlert("û�пɴ�ӡ�����ݣ�");
			return false;
		 }
		 return true;
		}
    	 else{
	     return false;
		}   
		return;
	}
}