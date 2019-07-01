function getDwjljxjInfo(){
	var xh = document.getElementById('xh').value;
	dwjlgl.getDwjljxjHisinfo(xh,function(data){
		var cellMuster=[
			function(data){return data.nd},
			function(data){return data.xn},
			function(data){return data.xq},
			function(data){return data.dwjlxmmc},
			function(data){return data.dwjlfsmc},
			function(data){return data.dwjllbmc},
			//function(data){return data.zzpzje}   litao update-time 090116
			function(data){return data.sqje}	
		];
		if ($("dwjljxjInfo").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("dwjljxjInfo");
			DWRUtil.addRows("dwjljxjInfo",data,cellMuster);
		}
	});
}

function cglxsq(notnull){
	var url= 'dwjl.do?method=saveCglxsq';
	//非空判断
	var ele = notnull.split('-');
	for(var i=0; i<ele.length; i++){
		if(document.getElementById(ele[i]).value==''){
			alert("请将带\*号的项目填写完整！");
			return false;
		}
	}
	
	//字段长度判断
	var jtdz = document.getElementById('jtdz').value;
	if(jtdz != null){
		if(jtdz.replace(/[^u0000-\u00ff]/g,'**').length>100){
			alert('家长住址不能大于100个字符！');
			return false;
		}
	}
	
	var xx = document.getElementById('xx').value;
	if(xx != null){
		if(xx.replace(/[^u0000-\u00ff]/g,'**').length>50){
			alert('留学意向院校不能大于50个字符！');
			return false;
		}
	}
	
	var sfzzlxxx = document.getElementById('sfzzlxxx').value;
	if(sfzzlxxx != null){
		if(sfzzlxxx.replace(/[^u0000-\u00ff]/g,'**').length>50){
			alert('是否自主联系国外院校不能大于50个字符！');
			return false;
		}
	}
	
	var lxdh = document.getElementById('lxdh').value;
	if(lxdh != null){
		if(lxdh.replace(/[^u0000-\u00ff]/g,'**').length>50){
			alert('联系方式不能大于50个字符！');
			return false;
		}
	}
	
	refreshForm(url);
}

function printCglxsqb(){
	var url = "dwjl.do?method=printCglxsqb";
	var ele = ["xh","xl","jdxx","cet","tem","toeft","jzxm","jzgzdw","jtdz","gj","xx","qqh","dzyx","lxdh","sfzzlxxx"];
	
	for(var i=0; i<ele.length; i++){
		url += "&" + ele[i] + "=" + document.getElementById(ele[i]).value;
	}		
	window.open(url);
}

//出国留学修改
function cglxModi(doType){
	var url = "dwjl.do?method=cglxck";
	if(curr_row == null){
		alert('修改信息请选择要修改的记录行！');
		return false;
	}
	var xh = curr_row.cells[1].innerText;
	var sqrq = curr_row.cells[8].innerText;
	
	url += "&xh=";
	url += xh;
	url += "&sqrq=";
	url += sqrq;
	url += "&doType=";
	url += doType;
	
	showTopWin(url,800,600);
}

 function batch(){
    var url = "dwjl.do?method=cglxsqsc"; 
	var RowsStr="!!";		  
	for (i=0; i<document.getElementsByName("pkV").length; i++){
	   if(document.getElementsByName("pkV")[i].checked){
	   	   RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	   }
	}
	document.forms[0].delPk.value = RowsStr;	
	if (RowsStr=="!!"){
		alert("请选择要删除的记录！\n(单击每条记录前复选框)");
		return false;
	}	
	if (!confirm("确定要删除所选记录？")){
		return false;
	}else{
		refreshForm(url);
	}	         
}      

//出国留学审核
function cglxsh(){
	var url = "dwjl.do?method=cglxshck";	
	var xh = curr_row.cells[1].innerText;
	var sqrq = curr_row.cells[8].innerText;
	
	url += "&xh=";
	url += xh;
	url += "&sqrq=";
	url += sqrq;
	
	showTopWin(url,700,500);
}

//保存出国留学审核信息
function saveCglxsh(){
	var shjg = document.getElementById('shjg').value;
	var yesNo = document.getElementById('yesNo').value;
	if(shjg == yesNo){
		alert('审核结果未改变！');
		return false;
	}	
	refreshForm('dwjl.do?method=saveCglxsh');
}

function cglxsqplsh(yesNo){
	var url = "dwjl.do?method=cglxsqplsh"; 
	var RowsStr="!!";		  
	for (i=0; i<document.getElementsByName("pkV").length; i++){
	   if(document.getElementsByName("pkV")[i].checked){
	   	   RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	   }
	}
	document.forms[0].delPk.value = RowsStr;	
	url += "&yesNo=";
	url += yesNo;
	
	if (RowsStr=="!!"){
		alert("请选择要操作的记录！\n(单击每条记录前复选框)");
		return false;
	}	
	if (!confirm("确定要操作所选记录？")){
		return false;
	}else{
		refreshForm(url);
	}	         
}

// 加载对外交流项目信息
function loadJlxm(){
	var xn = "";
	var nd = "";
	var xq = "";
	if(document.getElementById('xn')){
		xn = document.getElementById('xn').value;
	}
	if(document.getElementById('nd')){
		nd = document.getElementById('nd').value;
	}
	if(document.getElementById('xq')){
		xq = document.getElementById('xq').value;
	}
	dwjlgl.queryDwjlxmList(xn,nd,xq,function(data){
		DWRUtil.removeAllOptions("dwjlxmdm");
		DWRUtil.addOptions("dwjlxmdm",[{jlxmdm:'',jlxmmc:''}],"jlxmdm","jlxmmc");
		DWRUtil.addOptions("dwjlxmdm",data,"jlxmdm","jlxmmc");
	});
}
