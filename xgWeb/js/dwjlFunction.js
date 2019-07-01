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
	//�ǿ��ж�
	var ele = notnull.split('-');
	for(var i=0; i<ele.length; i++){
		if(document.getElementById(ele[i]).value==''){
			alert("�뽫��\*�ŵ���Ŀ��д������");
			return false;
		}
	}
	
	//�ֶγ����ж�
	var jtdz = document.getElementById('jtdz').value;
	if(jtdz != null){
		if(jtdz.replace(/[^u0000-\u00ff]/g,'**').length>100){
			alert('�ҳ�סַ���ܴ���100���ַ���');
			return false;
		}
	}
	
	var xx = document.getElementById('xx').value;
	if(xx != null){
		if(xx.replace(/[^u0000-\u00ff]/g,'**').length>50){
			alert('��ѧ����ԺУ���ܴ���50���ַ���');
			return false;
		}
	}
	
	var sfzzlxxx = document.getElementById('sfzzlxxx').value;
	if(sfzzlxxx != null){
		if(sfzzlxxx.replace(/[^u0000-\u00ff]/g,'**').length>50){
			alert('�Ƿ�������ϵ����ԺУ���ܴ���50���ַ���');
			return false;
		}
	}
	
	var lxdh = document.getElementById('lxdh').value;
	if(lxdh != null){
		if(lxdh.replace(/[^u0000-\u00ff]/g,'**').length>50){
			alert('��ϵ��ʽ���ܴ���50���ַ���');
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

//������ѧ�޸�
function cglxModi(doType){
	var url = "dwjl.do?method=cglxck";
	if(curr_row == null){
		alert('�޸���Ϣ��ѡ��Ҫ�޸ĵļ�¼�У�');
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
		alert("��ѡ��Ҫɾ���ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
		return false;
	}	
	if (!confirm("ȷ��Ҫɾ����ѡ��¼��")){
		return false;
	}else{
		refreshForm(url);
	}	         
}      

//������ѧ���
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

//���������ѧ�����Ϣ
function saveCglxsh(){
	var shjg = document.getElementById('shjg').value;
	var yesNo = document.getElementById('yesNo').value;
	if(shjg == yesNo){
		alert('��˽��δ�ı䣡');
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
		alert("��ѡ��Ҫ�����ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
		return false;
	}	
	if (!confirm("ȷ��Ҫ������ѡ��¼��")){
		return false;
	}else{
		refreshForm(url);
	}	         
}

// ���ض��⽻����Ŀ��Ϣ
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
