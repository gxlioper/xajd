
function defaultSee(the_tab){
var sum=document.getElementById(the_tab).rows.length;
var my_JxAarry = ["jxm"];

for(i=1;i<=sum-1;i++)
{  
   if($(my_JxAarry[0]+i).value!="")
   {
   document.getElementById(i).style.display='';
   }  
 }
 
}


function addTab() {
	var szlx = document.getElementById('tmp').value;
	if (szlx=='0') {
		showTopWin('pjpy_nbzy_zyszfAdd.do?act=0','610','500');
	} else if (szlx == '1') {
		showTopWin('pjpy_nbzy_zyszfAdd.do?act=1','610','500');
	} else if (szlx == '2') {
		showTopWin('pjpy_nbzy_zyszfAdd.do?act=2','610','500');
	} else {
		alert('����ʧ���������Ա��ϵ��');
		return;
	}
}

function modiTab() {
	if (curr_row==null || curr_row=='') {
		alert('��ѡ��Ҫ�����������У�');
		return;
	} 
	var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
	if (pk.substr(0,1)=='x') {
		alert('������δ��¼�κη����������޸ģ�');
		return;
	}
	var szlx = document.getElementById('tmp').value;
	if (szlx=='0') {
		modiAndDel('pjpy_nbzy_zysyfmodi.do?act=0&pkValue=','modi','550','420');
	} else if (szlx == '1') {
		modiAndDel('pjpy_nbzy_zysyfmodi.do?act=1&pkValue=','modi','550','420')
	} else if (szlx == '2') {
		modiAndDel('pjpy_nbzy_zysyfmodi.do?act=2&pkValue=','modi','550','420')
	} else {
		//alert('����ʧ���������Ա��ϵ��');
	}
}

function delTab() {
	var szlx = document.getElementById('tmp').value;
	if (szlx=='0') {
		deldata('pjpy_nbzy_szfdel.do?act=0');
	} else if (szlx == '1') {
		deldata('pjpy_nbzy_szfdel.do?act=1');
	} else if (szlx == '2') {
		deldata('pjpy_nbzy_szfdel.do?act=2');
	} else {
		alert('����ʧ���������Ա��ϵ��');
	}
}


function add2(the_tab){
var sum=document.getElementById(the_tab).rows.length;
	for(var i=1;i<=sum-1;i++)
	{
	   if(document.getElementById(i).style.display=='none')
	   {
	      document.getElementById(i).style.display='';
		  break;
	   }
	}
}
function decrease2(the_tab){
var sum=document.getElementById(the_tab).rows.length;
var my_JxAarry;

	my_JxAarry = ["lr", "fs", "zt"];
	for(var i=sum-1;i>0;i--)
{
   if(document.getElementById(i).style.display=='')
   {
      document.getElementById(i).style.display='none';
      document.getElementById(my_JxAarry[0]+i).value='';
      document.getElementById(my_JxAarry[1]+i).value='';
      document.getElementById(my_JxAarry[2]+i).value='';
	  break;
   }
}
}

function nbzy_autocount(){
	var xn = document.getElementById('xn').value;
	var xydm = document.getElementById('xy').value;
	if (xn=='' || xydm=='') {
		alert("��ѡ��Ҫ�Զ������ѧ��,"+jQuery("#xbmc").val()+"!");
		return;
	} else {
		if (confirm("�ò�������ѧ��,"+jQuery("#xbmc").val()+"Ϊ��λ�Զ������ۺϲ����ܷ�,���ܺķѽϳ�ʱ��,Ҫ������?")) {
			refreshForm('pjpy_nbzy_zhszcpcount.do?xn='+xn+'&xydm='+xydm);
			BatAlert.showTips('���ڲ�������ȴ�...');
		} 
		return;
	}
}

//����ɾ��
function deldata1(url) {
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


function shSubmit1(url,res){
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		document.forms[0].action=url+res;
		document.forms[0].submit();
		BatAlert.showTips('���ڲ�������ȴ�...');
	}else{
		alert("û��ѡ����Ӧ��¼����ѡ��֮����ȷ������");
	}
}

function savetj() {
	var xn = document.getElementById('xn').value;
	var jxjdm = document.getElementById('jxjdm').value;
	var tj = document.getElementById('tj').value;
	var bl = document.getElementById('bl').value;
	if (xn==''||jxjdm==''||tj==''||bl=='') {
		alert('������������Ϊ��,��ȷ��!');
		return;	
	} else {
		refreshForm('pjpy_nbzy_tjszsave.do');
	}
}