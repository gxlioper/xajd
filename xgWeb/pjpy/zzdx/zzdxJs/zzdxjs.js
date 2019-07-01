//荣誉证书打印

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
	if (mod=='one'){//单个打印
		var url ='zzdxzsprint.do?xh=';
		var hjxn;
		var hjxjmc;
		var hjny;
		if (curr_row==null || curr_row==''){
			alert('请选择要单个打印的数据行!');
			return;
			//hjxn = prompt("   请输入评奖学年度：(日期格式为:xxxx-xxxx)","");
			//hjxjmc = prompt("   请输入评奖名称：(格式例如:优秀毕业生)","");
			//hjny = prompt("   请输入评奖年月日：(日期格式为:xxxx-xx-xx)","");
		}else{
			hjxn = prompt("   请输入评奖学年度：(日期格式为:xxxx-xxxx)","");
			hjxjmc = prompt("   请输入评奖名称：(格式例如:优秀毕业生)","");
			hjny = prompt("   请输入评奖年月日：(日期格式为:xxxx-xx-xx)","");
			url += curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += '&xm='
			url += curr_row.cells[0].getElementsByTagName("input")[1].value;
		}
		var ull = '&hjxn='+hjxn+'&hjxjmc='+hjxjmc+'&hjny='+hjny;
			url += ull;
		window.open(url);
		return;
	}
	if (mod=='test') {//打印测试页
		var hjxn = prompt("   请输入评奖学年度：(日期格式为:xxxx-xxxx)","");
		var hjxjmc = prompt("   请输入评奖名称：(格式例如:优秀毕业生)","");
		var hjny = prompt("   请输入评奖年月日：(日期格式为:xxxx-xx-xx)","");
		var url = 'zzdxzsprint.do?xm=';
		if (curr_row!=null && curr_row!='') {
			url += curr_row.cells[0].getElementsByTagName("input")[1].value;
		}
		url += '&hjxn='+hjxn+'&hjxjmc='+hjxjmc+'&hjny='+hjny;
		window.open(url);
		return;
	}
	if (mod=='more') {//连打
		var hjxn = prompt("   请输入评奖学年度：(日期格式为:xxxx-xxxx)","");
		var hjxjmc = prompt("   请输入评奖名称：(格式例如:优秀毕业生)","");
		var hjny = prompt("   请输入评奖年月日：(日期格式为:xxxx-xx-xx)","");
		var url = '&hjxn='+hjxn+'&hjxjmc='+hjxjmc+'&hjny='+hjny;
		if (confirm("确定要做此操作吗?")){
    	var xm;
		if($("tabPri").rows.length > 1){
			rowOnClick($("tabPri").rows[0]);
			xm=$("tabPri").rows[0].cells[3].innerText.trim();
			window.open("zzdxzsprintmore.do?xm="+xm+url);
			BatAlert.closeTips();
		 } else{
		    BatAlert.MyAlert("没有可打印的数据！");
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