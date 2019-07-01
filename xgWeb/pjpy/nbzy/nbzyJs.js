
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
		alert('操作失误，请与管理员联系！');
		return;
	}
}

function modiTab() {
	if (curr_row==null || curr_row=='') {
		alert('请选择要操作的数据行！');
		return;
	} 
	var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
	if (pk.substr(0,1)=='x') {
		alert('该生尚未记录任何分数，不能修改！');
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
		//alert('操作失误，请与管理员联系！');
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
		alert('操作失误，请与管理员联系！');
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
		alert("请选择要自动计算的学年,"+jQuery("#xbmc").val()+"!");
		return;
	} else {
		if (confirm("该操作将以学年,"+jQuery("#xbmc").val()+"为单位自动计算综合测评总分,可能耗费较长时间,要继续吗?")) {
			refreshForm('pjpy_nbzy_zhszcpcount.do?xn='+xn+'&xydm='+xydm);
			BatAlert.showTips('正在操作，请等待...');
		} 
		return;
	}
}

//批量删除
function deldata1(url) {
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
		BatAlert.showTips('正在操作，请等待...');
	}else{
		alert("没有选择相应记录，请选择之后再确定！！");
	}
}

function savetj() {
	var xn = document.getElementById('xn').value;
	var jxjdm = document.getElementById('jxjdm').value;
	var tj = document.getElementById('tj').value;
	var bl = document.getElementById('bl').value;
	if (xn==''||jxjdm==''||tj==''||bl=='') {
		alert('所有条件不能为空,请确认!');
		return;	
	} else {
		refreshForm('pjpy_nbzy_tjszsave.do');
	}
}