function chgshxm() {
	if (document.getElementById('shxm').value=='' 
	|| document.getElementById().value == '先进班级') {
		document.getElementById('xh').style.display = "none";
	} else {
		document.getElementById('xh').style.display = "block";
	}
}


function countZf() {
	var sxzzddszf = document.getElementById('sxzzddszf').value;
	var kxwhszf = document.getElementById('kxwhszf').value;
	var sjlxcxf = document.getElementById('sjlxcxf').value;
	var sxlxszf = document.getElementById('sxlxszf').value;
	if (sxzzddszf == null || sxzzddszf =='' || sxzzddszf == ' ') {
		sxzzddszf = '0';
	}
	if (kxwhszf == null || kxwhszf =='' || kxwhszf == ' ') {
		kxwhszf = '0';
	}
	if (sjlxcxf == null || sjlxcxf =='' || sjlxcxf == ' ') {
		sjlxcxf = '0';
	}
	if (sxlxszf == null || sxlxszf =='' || sxlxszf == ' ') {
		sxlxszf = '0';
	}

	var zf = parseFloat(sxzzddszf)*20/100 + parseFloat(kxwhszf)*50/100
		+ parseFloat(sjlxcxf)*20/100 + parseFloat(sxlxszf)*10/100;
	if(parseFloat(zf) == zf) {
		zf = Math.round(zf * 100) / 100; 
	}
	document.getElementById('zhszcpzf').value = zf;
}


function isNotSee(ele){
    var doT = document.forms[0].doType.value;
    if(doT == "view"){ 
    var tmp = ele.split("-");      
	  for (i = 0; i < tmp.length; i++) {
			document.getElementById(tmp[i]).style.display = "none";
		}		
    }
}
function add(the_tab){
var sum=document.getElementById(the_tab).rows.length;
if (the_tab == 'tTb') {
	for(var i=1;i<=sum-1;i++)
	{
	   if(document.getElementById(i).style.display=='none')
	   {
	      document.getElementById(i).style.display='';
		  break;
	   }
	}
} else {
	for(var i=1;i<=sum-1;i++)
{
   if(document.getElementById('a'+i).style.display=='none')
   {
      document.getElementById('a'+i).style.display='';
	  break;
   }
}
}

}
function decrease(the_tab){
var sum=document.getElementById(the_tab).rows.length;
var my_JxAarry;
if (the_tab == 'tTb') {
	my_JxAarry = ["kcmc","cj","kclx","sfbxk"];
	for(var i=sum-1;i>0;i--)
{
   if(document.getElementById(i).style.display=='')
   {
      document.getElementById(i).style.display='none';
      document.getElementById(my_JxAarry[0]+i).value='';
      document.getElementById(my_JxAarry[1]+i).value='';
      document.getElementById(my_JxAarry[2]+i).value='';
      document.getElementById(my_JxAarry[3]+i).value='';
      document.getElementById(my_JxAarry[0]+8+i).value='';
      document.getElementById(my_JxAarry[1]+8+i).value='';
      document.getElementById(my_JxAarry[2]+8+i).value='';
      document.getElementById(my_JxAarry[3]+8+i).value='';
	  break;
   }
}
} else {
	my_JxAarry = ["fbkw","lwhzpmc","hjjb","hjdj"];
		for(var i=sum-1;i>0;i--)
{
   if(document.getElementById('a'+i).style.display=='')
   {
      document.getElementById('a'+i).style.display='none';
      document.getElementById(my_JxAarry[0]+i).value='';
      document.getElementById(my_JxAarry[1]+i).value='';
      document.getElementById(my_JxAarry[2]+i).value='';
      document.getElementById(my_JxAarry[3]+i).value='';
	  break;
   }
}
}

}

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

function chgXh(){
	if (document.getElementById('xh').value=='' || document.getElementById('xh').value==null) {
		alert('请先选择学号!');
		document.getElementById('jxjdm').selectedIndex = 0;
		return false;
	}
	return true;
}

function shJxj(url) {
	if (curr_row == '' || curr_row == null) {
		alert('请选择要操作的数据行!');
		return;
	} else {
		url += curr_row.getElementsByTagName("input")[0].value;
		showTopWin(url,'650','500');
	}
}

function bbPrint(url) {
	if (curr_row == '' || curr_row == null) {
		if (confirm('您没有选择任何数据单击一行即可,确定要打印吗?'))
		{
			window.open(url);
			return;
		} else {
			return;
		}
	} else {
		url += curr_row.getElementsByTagName("input")[0].value;
		window.open(url);
	}
}

function addTab() {
	var szlx = document.getElementById('tmp').value;
	if (szlx=='0') {
		showTopWin('ynys_sxzzddszfAdd.do','610','500');
	} else if (szlx == '1') {
		showTopWin('ynys_kxwhszfAdd.do','610','500');
	} else if (szlx == '2') {
		showTopWin('ynys_sxlxszfAdd.do','610','500');
	} else {
		showTopWin('ynys_sjlxcxfAdd.do','610','500');
	}
}


function add1(the_tab){
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
function decrease1(the_tab){
var sum=document.getElementById(the_tab).rows.length;
var my_JxAarry;

	my_JxAarry = ["jcxm","fs","lr"];
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

function modiTab() {
	var szlx = document.getElementById('tmp').value;
	if (szlx=='0') {
		modiAndDel('ynys_sxzzddfview.do?pkValue=','modi','610','500')
	} else if (szlx == '1') {
		modiAndDel('ynys_kxwhszfview.do?pkValue=','modi','610','500');
	} else if (szlx == '2') {
		modiAndDel('ynys_sxlxszfview.do?pkValue=','modi','610','500');
	} else {
		modiAndDel('ynys_sjlxcxfview.do?pkValue=','modi','610','500')
	}
}

function delTab() {
	var szlx = document.getElementById('tmp').value;
	if (szlx=='0') {
		deldata('ynys_szfdel.do');
	} else if (szlx == '1') {
		deldata('ynys_sxszfDel.do');
	} else if (szlx == '2') {
		deldata('ynys_sxllfDel.do');
	} else {
		deldata('ynys_sjfdel.do');
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

	my_JxAarry = ["kcmc", "cj", "kclx"];
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
