function check_user()
	{
		var user=document.all['userType'].value;
		if("xy"==user)
		{
			if($('xydm')){
				document.getElementById('xydm').disabled=true;
			}
		}
		else if("xx"==user)
		{
			if($('xydm')){
				document.getElementById('xydm').disabled=false;
			}
		}
	}

function ljsdaUpdate(url,w,h){
	var pk="";
	if(curr_row == null ){
			alert("��ѡ��һ�м�¼��\n����һ�м���!");
			return false;
		} 		
	pk= curr_row.cells[0].getElementsByTagName("input")[0].value;	
	url+=pk;
	
	if(w==""||w==null){
		w = 800;
}
	if(h==""||h==null){
		h = 700;
	}
	showTopWin(url,w,h);		
}



function update(url,w,h){
	if(curr_row == null ){
		alert("��ѡ��һ�м�¼��\n����һ�м���!");
		return false;
	} 	
	var xh=curr_row.cells[1].getElementsByTagName("input")[0].value;
	var ydxh=curr_row.cells[0].getElementsByTagName("input")[0].value;	
	url+=xh;
	url+="&ydxh=";
	url+=ydxh;
	showTopWin(url,w,h);
}

function familyUpdate(url,w,h){		
	var pk="";		
	if(curr_row == null ){
			alert("��ѡ��һ�м�¼��\n����һ�м���!");
			return false;
	} 		
	pk= curr_row.cells[0].getElementsByTagName("input")[0].value;
	url+=pk;	
	showTopWin(url,w,h);	
}

function stuinfoDel(url){
	if(curr_row==null){
		alert("��ѡ����Ҫɾ���ļ�¼!\n����һ�м���!");
		return false;
	}
	if(confirm('��ȷ��ɾ����')){	
	var pk=curr_row.cells[0].getElementsByTagName("input")[0].value;	
	url+=pk;
	document.forms[0].action=url;
	document.forms[0].submit();		
	}
}


function ljsdaSave(url){
	var type=document.forms[0].oper.value;		
	url+=type;
	document.forms[0].action = url;
	document.forms[0].submit();
	}
	
function stuinfoSave(url,family){
	var type=document.forms[0].oper.value;
	var values=document.forms[0].notnull.value;
	var tvalue=values.split("-");
	for(var i=0;i<tvalue.length;i++){
		if(document.getElementById(tvalue[i]).value==""){
		alert("�뽫�����ŵ���Ŀ��д������");
		return false;
		}
	}
	if($("dzyx")){//����������
		var dzyx = document.getElementById('dzyx').value;
		if(!isEmail(dzyx) && dzyx!=""){
			alert("��������ȷ�ĵ�������!");
			return false;
		}
	}
	if($("jjzk")){
		var jjzk = document.getElementById("jjzk").value;
		if(jjzk){
		   if(jjzk.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
		       alert("��ͥ����״�����ܴ���100���ַ�");
		       return false;
		   }
		}
	}
	if (document.forms[0].oper.value == "add") {	
		var xh=document.forms[0].xh.value;
			if(family){
				var xxdm = val("xxdm");
				var result = true;
				dwr.engine.setAsync(false);
				var yhjs = val('yhjs');
				if(yhjs == "stu"){
					yhjs = "student";
				}
				if(xxdm == "10491"){//�й����ʴ�ѧ
					xsxxZgkd.getBtzdByYh(yhjs,"xsfzxxb",function(data){
						if(data != null){
							for(var i=0; i<data.length; i++){
								if(ele(data[i].en) && ele(data[i].en).value == ""){
									alert(data[i].cn + "����Ϊ�գ�");
									result = false;
									break;
								}
							}
						}				
					});
				}
				if(result){
					getXjydInfo.getColumnEx("view_xsjbxx","xh",xh,function(data){				
						if(data==false){
							alert('ѧ�Ų����ڣ�');
							return false;
						}
						url+=type;	
						refreshForm(url);
					});	
				}
				dwr.engine.setAsync(true);
			} else{
//				getXjydInfo.getColumnEx("xsxxb","xh",xh,function(data){			
//						if(data==true){
//							alert('ѧ���Ѿ����ڣ�');
//							return false;
//						}
						
						//document.forms[0].action = url;
						//document.forms[0].submit();	
						//$("buttonSave").disabled = true;						
//				});
				url+=type;
				refreshForm(url);
			}
	}else{	
		if(family){
			var xxdm = val("xxdm");
			var result = true;
			dwr.engine.setAsync(false);			
			if(xxdm == "10491"){//�й����ʴ�ѧ
				var yhjs = val('yhjs');
				if(yhjs == "stu"){
					yhjs = "student";
				}
				if(yhjs == "admin"){
					yhjs = "xx";
				}
				xsxxZgkd.getBtzdByYh(yhjs,"xsfzxxb",function(data){
					if(data != null){
						for(var i=0; i<data.length; i++){
							if(ele(data[i].en) && ele(data[i].en).value == ""){
								alert(data[i].cn + "����Ϊ�գ�");
								result = false;
								break;
							}
						}
					}				
				});
			}
			if(result){
			url+=type;	
			refreshForm(url);	
		}
		dwr.engine.setAsync(true);
	}else{
		url+=type;	
		refreshForm(url);	
	}
		
	}
}

function ljsdaDel(url){
	var pk="";
	var xh=curr_row.cells[0].getElementsByTagName("input")[0].value;	
	if(curr_row==null){
		alert("��ѡ����Ҫɾ���ļ�¼!\n����һ�м���!");
		return false;
	}
	if(confirm('��ȷ��ɾ����')){				
		url+=xh;		
		document.forms[0].action=url;
		document.forms[0].submit();		
	}
}

function dataExport1(url) {
	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}

function change(url){
	var xydm=document.forms[0].xydm.value;
	var zydm=document.forms[0].zydm.value;
	var bjdm=document.forms[0].bjdm.value;
	var nj=document.forms[0].ydqnj.value;
	var xz=document.forms[0].ydqxz.value;
	var type=document.forms[0].oper.value;
	url+=type;
	url=url+"&xydm="+xydm+"&zydm="+zydm+"&bjdm="+bjdm+"&nj="+nj+"&xz="+xz;
	document.forms[0].action=url;
	document.forms[0].submit();
}

function checkNull(url){
	var values=document.forms[0].notnull.value;
	var nj=document.forms[0].nj.value;
	var zy=document.forms[0].zy.value;
	var xy=document.forms[0].xy.value;
	var bj=document.forms[0].bj.value;
	var xz=document.forms[0].xz.value;
//	var xj=document.forms[0].xjzt.value;
	var ydqnj = document.getElementById("ydqnj").value;
	var ydqxymc = document.getElementById("ydqxymc").value;
	var ydqzymc = document.getElementById("ydqzymc").value;
	var ydqbjmc = document.getElementById("ydqbjmc").value;
	var ydqxz = document.getElementById("ydqxz").value;
	var ydqxj = document.getElementById("ydqxj").value;
	
	var tvalue=values.split("-");
	for(var i=0;i<tvalue.length;i++){
		if(document.getElementById(tvalue[i]).value==""){
		alert("�뽫�����ŵ���Ŀ��д������");
		return false;
		}
	}		
	if(selText('ydlbdm') == '��ѧ'){
		if(val('ydjzrq') == ""){
			alert('��ѧ������д�춯��ֹ���ڣ�');
			return false;
		}
	}
	var result1=false;
	if (document.forms[0].oper.value == "add") {
		var ydxh=document.forms[0].ydxh.value;
		var xh=document.forms[0].xh.value;	
		getXjydInfo.getColumnEx("view_xjydjbxx","ydxh",ydxh,function(data){
					result1=data;	
					if(result1==true){
						alert('�춯����Ѿ����ڣ�');
						return false;
					}	
					var type=document.forms[0].oper.value;
					url=url+type;	
					url+="&bjdm=";
					url+=bj;
					url+="&xydm=";
					url+=xy;
					url+="&zydm=";
					url+=zy;
					url+="&nj=";
					url+=nj;
					url+="&xz=";
					url+=xz;
//					url+="&xj=";
//					url+=xj;
					url+="&ydqnj=";
					url+=ydqnj;
					url+="&ydqxymc=";
					url+=ydqxymc;
					url+="&ydqzymc=";
					url+=ydqzymc;
					url+="&ydqbjmc=";
					url+=ydqbjmc;
					url+="&ydqxz=";
					url+=ydqxz;
					url+="&ydqxj=";
					url+=ydqxj;
					document.forms[0].action=url;
					document.forms[0].submit();							
			});		
	}else{
		var type=document.forms[0].oper.value;
		url=url+type;	
		url+="&bjdm=";
		url+=bj;
		url+="&xydm=";
		url+=xy;
		url+="&zydm=";
		url+=zy;
		url+="&nj=";
		url+=nj;
		url+="&xz=";
		url+=xz;
		//url+="&xj=";
		//url+=xj;
		url+="&ydqnj=";
		url+=ydqnj;
		url+="&ydqxymc=";
		url+=ydqxymc;
		url+="&ydqzymc=";
		url+=ydqzymc;
		url+="&ydqbjmc=";
		url+=ydqbjmc;
		url+="&ydqxz=";
		url+=ydqxz;
		url+="&ydqxj=";
		url+=ydqxj;
		document.forms[0].action=url;
		document.forms[0].submit();			
	}
}

function changeType(url){
	var type=document.forms[0].oper.value;
	url+=type;
	document.forms[0].action=url;
	document.forms[0].submit();
}

function archiveSave(url){
	var type=document.forms[0].oper.value;
	var xydm=document.forms[0].xydm.value;
	var zydm=document.forms[0].zydm.value;
	var bjdm=document.forms[0].bjdm.value;
	var xm=document.forms[0].xm.value;
	var xh=document.forms[0].xh.value;	
	var values=document.forms[0].notnull.value;

	var tvalue=values.split("-");
	for(var i=0;i<tvalue.length;i++){
		if(document.getElementById(tvalue[i])){
			if(document.getElementById(tvalue[i]).value==""){
				alert("�뽫�����ŵ���Ŀ��д������");
				return false;
			}
		}
	}	
	if (type == "add") {
	getXjydInfo.getColumnEx("view_stu_archives","xh",xh,function(data){
		if(data==true){
			alert("��ѧ���Ѿ����ڵ�����Ϣ��");
			return false;
		}	
		url=url+type+"&xydm="+xydm+"&zydm="+zydm+"&bjdm="+bjdm;
		document.forms[0].action=url;
		document.forms[0].submit();		
	});	
	}else{
		url=url+type+"&xydm="+xydm+"&zydm="+zydm+"&bjdm="+bjdm+"&xm="+xm;
		document.forms[0].action=url;
		document.forms[0].submit();	
	}
}

function historyArchivesSave(url){	
	var values=document.forms[0].notnull.value;
	var type=document.forms[0].oper.value;
	var xh=document.forms[0].xh.value;
	var xxdm = val("xxdm");
	
	if(xxdm == ""){
		
	}
	var tvalue=values.split("-");
	for(var i=0;i<tvalue.length;i++){
		if(document.getElementById(tvalue[i]).value==""){
			alert("�뽫�����ŵ���Ŀ��д������");
			return false;
		}
	}	
	if (type == "add") {
	getXjydInfo.getColumnEx("stu_history_lab","xh",xh,function(data){
		if(data==true){
			alert("��ѧ���Ѿ����ڵ�����Ϣ��");
			return false;
		}	
		url=url+type;
		document.forms[0].action=url;
		document.forms[0].submit();				
	});
	}else{
		url=url+type;
		document.forms[0].action=url;
		document.forms[0].submit();
	}
}

function showGdzlInfo(w,h){
	var rsStr="";
	var titleStr="";
	getXjydInfo.getGdzlxx("",function (rs){
		titleStr=rs.title;
		rsStr=rs.data;
	var records = rsStr.split("@@"); 
	var showHtml = "<center>";
	showHtml  += "<table width='98%' class='tbstyle'>";	
	showHtml  += "<thead>";
	showHtml  += "<tr><td colspan='6'><center><b>δ�ύ�鵵���ϵ�ѧ����Ϣ</b></center></td></tr>";
	showHtml  += "<tr>";	
	var titleArr = titleStr.split("!!");
	for(var i=0;i<titleArr.length;i++){
		showHtml += "<td>";
		showHtml += titleArr[i];
		showHtml += "</td>";
	}
	showHtml += "</tr></thead>";	
	showHtml += "<tbody>";
	for(var i=0;i < records.length; i++){
		var singleRecord = records[i].split("!!");
		showHtml += "<tr>";
		for(var j=0;j<singleRecord.length;j++){
			showHtml += "<td>";
			showHtml += singleRecord[j];
			showHtml += "</td>";
		}	
		showHtml += "</tr>";	
	}
	showHtml += "<tr><td  colspan='3'><center><div class='buttontool'>";
	showHtml += "<button class='button2' onclick='closeAdd()' style='width:80px'>";
	showHtml += "ȷ��";
	showHtml += "</button></td><td colspan='3'>";
	showHtml += "<div class='buttontool'>";
	showHtml += "<button class='button2' onclick='dataExport3()' style='width:80px'>";
	showHtml += "��������";
	showHtml += "</button></td></tr>";
	showHtml += "</tbody></table>";
	showDiv(showHtml,w,h);	
	});
}

function dataExport3() {
	document.forms[0].action = "/xgxt/expData.do?realTable=stu_gdzlb&tableName=view_stu_gdzlb";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}



function getYdlbInfo(){	
	var ydlbdm=document.forms[0].ydlbdm.value;
	var result=false;
	getXjydInfo.getYdlbStyle(ydlbdm,function(data){
		result=data;		
		if(result==true){			
			document.forms[0].nj.disabled=false;			
			document.forms[0].xy.disabled=false;
			document.forms[0].zy.disabled=false;
			document.forms[0].bj.disabled=false;
			document.forms[0].xz.disabled=false;
//			document.forms[0].xjzt.disabled=false;
			if($("xszt")){
				document.forms[0].xszt.disabled=false;
			}
		}
		if(result==false){			
			document.forms[0].nj.disabled=true;
			document.forms[0].xy.disabled="true";
			document.forms[0].zy.disabled="true";
			document.forms[0].bj.disabled="true";
			document.forms[0].xz.disabled="true";
//			document.forms[0].xjzt.disabled="true";
			if($("xszt")){
				document.forms[0].xszt.disabled="true";
			}
		}
	});		
}

function doSaveConf(url,col){
	var kssj=document.forms[0].kssj.value.replace("-","").replace("-","")+document.forms[0].kssjH.value+document.forms[0].kssjM.value+document.forms[0].kssjS.value;
	var jssj=document.forms[0].jssj.value.replace("-","").replace("-","")+document.forms[0].jssjH.value+document.forms[0].jssjM.value+document.forms[0].jssjS.value;	
	var colNum=col.split("-");
	for(var i=0;i<colNum.length;i++){
		if(document.getElementById(colNum[i]).value==""){
			alert("�뽫��*�ŵ���Ŀ��д������");
			return false;
		}
	}
	if(kssj>jssj){
		alert("����ʱ��Ӧ���ڿ�ʼʱ�䣡");
		return false;
	}
	document.forms[0].action=url;
	document.forms[0].submit();
}
function commSave(url,columns){
	var tvalue=columns.split("-");
	for(var i=0;i<tvalue.length;i++){
		if(document.getElementById(tvalue[i]).value==""){
		alert("�뽫��\*�ŵ���Ŀ��д������");
		return false;
		}
	}	
	//�ı��򳤶��ж�	
	if($('sqly')){
		var sqly = document.getElementById('sqly').value;
		if(sqly != null){
		   if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 150){	         
		       alert("�������ɲ��ܴ���150���ַ�");
		       return false;
		   }
		}
	}
	
	document.forms[0].action=url;
	document.forms[0].submit();
}

function commUpdate(url,w,h){		
	var pk="";		
	if(curr_row == null ){
			alert("��ѡ��һ�м�¼��\n����һ�м���!");
			return false;
		} 		
	pk= curr_row.cells[1].getElementsByTagName("input")[0].value;
	url+=pk;	
	showTopWin(url,w,h);	
}

function commDel(url){
	var pk="";
	if(curr_row==null){
		alert("��ѡ����Ҫɾ���ļ�¼!\n����һ�м���!");
		return false;
	}
	if(confirm('��ȷ��ɾ����')){
		pk=curr_row.cells[1].getElementsByTagName("input")[0].value;
		var xm = curr_row.cells[0].getElementsByTagName("input")[0].value;			
		url+=pk;
		url += "&xm=";
		url += xm;
		document.forms[0].action=url;
		document.forms[0].submit();	
	}
}

function viewAdd(url,doType){
	var realTable=document.forms[0].realTable.value;	
	if(doType=='add'){
		if(realTable=='xscgsqb'){
			url="stu_cgcj.do?act=stu_cgcj_sq";
			window.location.href=url;
		}else if(realTable=='stu_txsqb'){
			url="stu_cgcj.do?act=stu_tx_sq";
			window.location.href=url;
		}else if(realTable=='xscgsqsh' || realTable=='stu_txsqsh'){
			var xh = document.forms[0].xh.value;
			var nd = document.forms[0].nd.value;
			var yesNo = document.forms[0].yesNo.value;
			url+= "&xh="+xh;
			url+= "&nd="+nd;	
			url+= "&yesNo="+yesNo;	
			document.forms[0].action=url;
			document.forms[0].submit();			
		}else if(realTable=='stu_archives_apply'){
			url="stu_archives_apply.do";
			window.location.href=url;
		}else if(realTable=="stu_archives_auditing"){			
			url="stu_archives_apply.do?doType=auditing";			
			document.forms[0].action=url;
			document.forms[0].submit();		
		}else if(realTable=='stu_zdzmsq'){
			window.location.href=url;
		}else if(realTable=='stu_zdzmsqsh'){
			var xh=document.forms[0].xh.value;
			var nd=document.forms[0].nd.value;
			var yesNo=document.forms[0].yesNo.value;		
			url="prove_query.do?doType=auditing&type=save&xh=";
			url+=xh;
			url+="&nd="+nd;	
			url+="&yesNo="+yesNo;
			document.forms[0].action=url;
			document.forms[0].submit();	
		}
	}
	if(doType=="modi"){
		if(curr_row == null){
			alert('��ѡ����Ҫ���������ݣ�');
			return false;
		}
		if(realTable=='xscgsqb'){
			url="stu_cgcj.do?act=stu_cgcj_sq&doType=view&xh=";
			url+=curr_row.cells[1].innerText
			url+="&nd="
			url+=curr_row.cells[0].innerText;
			showTopWin(url,800,600);
			return true;
		}else if(realTable=='stu_txsqb'){
			url="stu_cgcj.do?act=stu_tx_sq&doType=view&xh=";
			url+=curr_row.cells[1].innerText
			url+="&nd="
			url+=curr_row.cells[0].innerText;
			showTopWin(url,700,500);
			return true;
		}else if(realTable=="xscgsqsh"){
			url="abroad_query.do?act=cgsq_sh&doType=view&xh=";
			url+=curr_row.cells[1].innerText;
			url+="&nd="
			url+=curr_row.cells[0].innerText;
			showTopWin(url,800,600);
			return true;
		}else if(realTable=="stu_txsqsh"){
			url="abroad_query.do?act=txsq_sh&doType=view&xh=";
			url+=curr_row.cells[1].innerText;
			url+="&nd="
			url+=curr_row.cells[0].innerText;
			showTopWin(url,800,600);
			return true;
		}else if(realTable=='stu_archives_apply'){
			url="archives_apply_query.do?doType=view&xh=";
			url+=curr_row.cells[0].innerText
			url+="&nd="
			url+=curr_row.cells[1].innerText;	
		}else if(realTable=='stu_archives_auditing'){
			url="archives_apply_query.do?doType=viewAuditing&xh=";
			url+=curr_row.cells[0].innerText
			url+="&nd="
			url+=curr_row.cells[1].innerText;
			showTopWin(url,800,600);
			return true;
		}else if(realTable=='stu_zdzmsq'){
			url="prove_query.do?doType=view&xh=";
			url+=curr_row.cells[0].innerText;
			url+="&nd=";
			url+=curr_row.cells[1].innerText;
			//showTopWin(url,600,400);
		}else if(realTable=='stu_zdzmsqsh'){
			url="prove_query.do?doType=auditing&type=view&xh=";
			url+=curr_row.cells[0].innerText;
			url+="&nd=";
			url+=curr_row.cells[1].innerText;
			showTopWin(url,800,600);
			return true;
		}
	}
	if(doType=="del"){	
		if(realTable=='xscgsqb'){
			url="abroad_query.do?act=cgsq_query&doType=del&xh=";
		}else if(realTable=='stu_txsqb'){
			url="abroad_query.do?act=txsq_query&doType=del&xh=";
			}
			url+=curr_row.cells[1].innerText
			url+="&nd="
			url+=curr_row.cells[0].innerText;					
		if(realTable=="stu_archives_apply"){
			url="archives_apply_query.do?doType=del&xh=";
			url+=curr_row.cells[0].innerText
			url+="&nd="
			url+=curr_row.cells[1].innerText;				
		}else if(realTable=="stu_zdzmsq"){
			url="prove_query.do?doType=del&xh=";
			url+=curr_row.cells[0].innerText;
			url+="&nd=";
			url+=curr_row.cells[1].innerText;
		}
				
	}
	document.forms[0].action=url;
	document.forms[0].submit();
}	

function checkArchivesExist(show){
	var xh=document.forms[0].xh.value;
	if(xh==""){
		alert("������ѧ��");
		return false;
	}
	getXjydInfo.checkArchivesExist(xh,function(data){
		if(data==""){
		//���ӹ鵵�����жϣ�����û���ύ�����Ĳ���ת��
			if(show==""){
				alert ("��ϲ������ת����");
			}else{
				archiveSave('/xgxt/stu_archives_now.do?doType=saveArchives&type=');
			}
			return true;
		}else{
			alert(data);
			return false;
		}	
	});
}

function noticePrint(){
	var  rqvar = new Date();
	var xxdm = document.getElementById("xxdm").value;
	var rq =  rqvar.getYear()+"-"+rqvar.getMonth()+"-"+rqvar.getDay();
	if(xxdm=="12957"){//������Ϣ
		var fzrq = prompt("   �����뷢֤���ڣ�(���ڸ�ʽΪxxxx-xx-xx)","");
		var bfrq = prompt("   �����벹�����ڣ�(���ڸ�ʽΪxxxx-xx-xx)","");
		var yxrq = prompt("   ��������Ч������(���ڸ�ʽΪxxxx-xx-xx)","");
	}
    BatAlert.MyAlert("ȷ��Ҫ���˲����𣿴˲�����������ӡ��ʾ��ѧ������","",function(){
 	   BatAlert.showTips("���ݲ����У����Ժ�...");
    	var xh;
		if($("tabPri").rows.length > 1){
			rowOnClick($("tabPri").rows[0]);
			xh=$("tabPri").rows[0].cells[1].innerText.trim();
			if(xxdm=="12957"){//������Ϣ
				window.open("noticePrintOne.do?xh="+xh+"&fzrq=" + fzrq + "&bfrq=" + bfrq + "&yxrq=" + yxrq);
			}
			BatAlert.closeTips();
		 } else{
		    BatAlert.MyAlert("û�пɴ�ӡ�����ݣ�");
			return false;
		 }
	},true);
	return false;   
}


function certificatePrint(){	
	d_html = "<table class='formlist'>";
	d_html += "<tbody>";
	d_html +="<tr><td>�˲�����������ӡ��ʾ��ѧ������,ȷ��Ҫ���˲�����</td></tr>";
	d_html += "</tbody>";
	d_html += "<tfoot>";
	d_html +="<tr><td align='center'><button onclick='exePrint()'>ȷ��</button><button onclick=\"$('tempDiv').style.display='none';return false;\">ȡ��</button></td></tr>";
	d_html += "</tfoot>";
	d_html +="</table>";
	showTempDivForJs("��ʾ��Ϣ",d_html, 350, 100);
}

function exePrint(){	
	var mb=document.getElementById("mb").value;
	var xh = "";
	var zmlx = "";
	if($("rsTable") && $("rsTable").rows.length > 1){	
		rowOnClick($("tabPri").rows[0]);
		xh = $("rsTable").rows[1].cells[0].innerText;
		zmlx = $("rsTable").rows[1].cells[7].innerText;
		window.open("certificatePrintAll.do?xh="+xh+"&mb="+mb + "&zmlx=" + zmlx);			
		$('tempDiv').style.display='none';
		hiddenMessage(true,true);
	 } else{
		$('tempDiv').style.display='none';
		hiddenMessage(true,true);
	    alert("û�пɴ�ӡ�����ݣ�");	   
		return false;
	 }
}

function delMore(url){//����ɾ��
	var RowsStr="!!SplitOneSplit!!";   										//���������ַ���			
		if (Rows.length==0){  													//ԭ���ж�curr_row == null
			alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
			return false;
		} else {
			for (i=0; i<Rows.length; i++){ 										//�����ַ���
    			RowsStr+=Rows[i].getElementsByTagName("input")[0].value+"!!SplitOneSplit!!";
			}
			if(url=="/xgxt/archives_apply_query.do?doType=del&pkValue="){
				RowsStr = "";
				for(i=0; i<Rows.length; i++){
					RowsStr+=Rows[i].getElementsByTagName("input")[0].value+Rows[i].getElementsByTagName("input")[1].value+"!!SplitOneSplit!!";
				}
			}
			if (confirm("ȷ��Ҫɾ��ѡ���������")) {
				pkValue = RowsStr; 												//��������
				url += pkValue;				
				refreshForm(url);
				return true;
			} else {
				return false;
			}
  	}
}

function   isInteger(str)   {   
  if   (/[^\d]+$/.test(str)){   
  return   false;   
  }   
  return   true;   
  }   

 
 function isEmail(sEmail){
	      //sEmail = sEmail.trim();
 	      var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	      return p.test(sEmail);
 }
 
 /** 
 * Ĭ�϶�Ӧ���е��ֶ�
 */
function defaultAllItemList() {
	if (document.forms[0].mappingList.options.length > 0) {
		return false;
	}
	var len = document.forms[0].excelList.options.length;
	var i;
	var addExcelV;
	var addExcelT;
	for (i = 0; i < len; i++) {
		addExcelV = document.forms[0].excelList.options[i].value;
		addExcelT = document.forms[0].excelList.options[i].text;
		document.forms[0].mappingList.options[i] = new Option(addExcelT, addExcelV);
	}
}
 
 
 function addOneItemList() {
	var excelListIndex = document.forms[0].excelList.selectedIndex;
	if (excelListIndex < 0 || document.forms[0].mappingList.options.length == document.forms[0].excelList.options.length) {
		return false;
	}
	var addExcelV = document.forms[0].excelList.options[excelListIndex].value;
	var addExcelT = document.forms[0].excelList.options[excelListIndex].text;
	document.forms[0].mappingList.options[document.forms[0].mappingList.options.length] = new Option(addExcelT , addExcelV);
}

function deleteItemList() {
	var mappingListIndex = document.forms[0].mappingList.selectedIndex;
	if (mappingListIndex < 0) {
		return false;
	}
	document.forms[0].mappingList.options[mappingListIndex] = null;
}

function clearList() {
	document.forms[0].mappingList.options.length = 0;
}


function submitList(){
	var i;
	document.forms[0].mappingItem.value = "";//���mappingItems�е�ֵ
	if (document.forms[0].mappingList.options.length <= 0) {
		alert("�����ֶβ���Ϊ��!");
		return false;
	}
	var showConfirmText = "��ȷ��������ѡ���ֶ���";
	var showRunningText;
	
    if (confirm(showConfirmText)) {
    	for (i = 0; i < document.forms[0].mappingList.options.length; i++) {
    		document.forms[0].mappingItem.value = document.forms[0].mappingItem.value + "!!SplitOne!!";
    		document.forms[0].mappingItem.value = document.forms[0].mappingItem.value + document.forms[0].mappingList.options[i].value;
    	}
    	document.forms[0].mappingItem.value = DWRUtil.byId("mappingItem").value.substr("!!SplitOne!!".length);//���е��ַ���ǰ���"!!SplitOne"
    	//var Items = document.getElementById("mappingItem").value;
    	var whereSql = document.getElementById("whereSql").value;
    	//document.getElementById("mappingItem").value = Items;
    	wjcfDataExport("xsxxgl.do?method=expData&sql=" + whereSql);
	}
    
	return false;
}

function nextModi(){
	var url = "about:blank";
	var sfty = document.getElementById('xsyj').checked;
	if(sfty)	{
		url = "stu_info_add.do?method=modiStuInfo&type=modi";	
	}
	refreshForm(url);
}

//ת������α��ѯҳ��
function turnSearch(url,userName,time){
	var sty = "directories=no";
	getStuDetails.getTurnJwUrl(userName,time,function(data){
		url += "?verify=" + data + "&un=" + userName + "&time=" + time + "&xh=" + userName;
		//window.open(url);
		window.open(url,'',sty);
	});
}
function $(elem){
    return document.getElementById(elem);
}
 
function showOperDiv(divId,isbackDiv){
  if($('floatDiv')==null){
   var floatDiv = document.createElement('div');
   floatDiv.id = "floatDiv";
   floatDiv.style.position = "absolute";
   floatDiv.style.width = "400px";
   floatDiv.style.height = "150px";
   floatDiv.style.backgroundColor = "#C7DEFC";
   var x_pixel = 200;
   var y_pixel = 125;
  floatDiv.style.left = (document.documentElement.scrollWidth/2 + document.documentElement.scrollLeft - x_pixel) +"px";
  floatDiv.style.top = (document.documentElement.scrollHeight/2 + document.documentElement.scrollTop - 100) +"px";
   floatDiv.style.zIndex  = 1001;
   //
   floatDiv.innerHTML = $(divId).innerHTML;
   $(divId).innerHTML = "";
   document.body.appendChild(floatDiv);
  }
  else{
   $('floatDiv').style.display = "block";
  }
  if($('backDiv')==null && isbackDiv ==true){
   var backDiv = document.createElement('div');
   backDiv.id = "backDiv";
   backDiv.style.backgroundColor = "Black";
   backDiv.style.filter = "alpha(opacity=70)";
   backDiv.style.MozOpacity = "0.70";
   backDiv.style.position = "absolute";
   backDiv.style.left = "0px";
   backDiv.style.top = "0px";
   backDiv.style.width = Math.max(document.body.scrollWidth, document.body.clientWidth) +"px";
   backDiv.style.height = Math.max(document.body.scrollHeight, document.body.clientHeight)+"px";
   document.body.appendChild(backDiv);
   $('backDiv').style.zIndex = 1000;
  }
  else if(isbackDiv ==true){
   $('backDiv').style.display = "block";
  }  
 }
 
 
 function moveDiv(event, elem)
 {
  $('floatDiv').style.filter = "alpha(opacity=50)";
  var oObj = $(elem); 
  oObj.onmousemove = mousemove;
  oObj.onmouseup = mouseup;
  oObj.setCapture ? oObj.setCapture() : function(){};
  oEvent = window.event ? window.event : event;
  var dragData = {x : oEvent.clientX, y : oEvent.clientY};
  var backData = {x : parseInt(oObj.style.top), y : parseInt(oObj.style.left)};
  function mousemove()
  {
   var oEvent = window.event ? window.event : event;
   var iLeft = oEvent.clientX - dragData["x"] + parseInt(oObj.style.left);
   var iTop = oEvent.clientY - dragData["y"] + parseInt(oObj.style.top);
   oObj.style.left = iLeft;
   oObj.style.top = iTop;
   dragData = {x: oEvent.clientX, y: oEvent.clientY};
  }
  
  function mouseup()
  {
   var oEvent = window.event ? window.event : event;
   oObj.onmousemove = null;
   oObj.onmouseup = null;
   $('floatDiv').style.filter = "";
   if(oEvent.clientX < 1 || oEvent.clientY < 1)
   {
    oObj.style.left = backData.y;
    oObj.style.top = backData.x;
   }
    oObj.releaseCapture ? oObj.releaseCapture() : function(){};
  }
 }
 
 function CloseFloatDiv(){
  $('floatDiv').style.display = "none";
  if($('backDiv')){
   $('backDiv').style.display = "none";
  }
 }

function printCertificate(){
	var xh = document.getElementById('xh').value;
	var zmlx = document.getElementById('zmlx').value;
	
	var mb = "";
	if(xh == null || xh==''){
		alert('������ѧ�ţ�');
		return false;
	}
	if(zmlx == null || zmlx==''){
		alert('��ѡ��֤�����ͣ�');
		return false;
	}
	
	window.open("certificatePrintAll.do?doType=printone&xh="+xh+"&mb="+mb + "&zmlx="+zmlx);
}

function copySelect(hid,nid){
	var len = document.getElementById(hid).options.length;			
	for(var i=0; i<len; i++){
			document.getElementById(nid).options[i] = new Option(document.getElementById(hid).options[i].text,document.getElementById(hid).options[i].value)
	}
}

function showConfByny(){
	var dd_html = "";
	dd_html += "<table width='350' class='formlist'>";
	dd_html += "<thead>";
	dd_html += "<tr>";
	dd_html += " <th align='center'>";
	dd_html += "  ��ѡ���ʼ����ʽ";
	dd_html += " </th>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr>";
	dd_html += "<td>";
	dd_html += "<input type='radio' name='configtype' value='0'checked>&nbsp;��ʼ��ȫ��&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<input type='radio' name='configtype' value='2'>&nbsp;��������ʼ��&nbsp;&nbsp;&nbsp;&nbsp;";		
	dd_html += "<input type='radio' name='configtype' value='1'>&nbsp;��ʼ��ѡ�е�����";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</tbody>";
	dd_html += "<thead>";
	dd_html += "<tr>";
	dd_html += " <th>";
	dd_html += "  �����ҵ�·ݺ�����";
	dd_html += " </th>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr>";
	dd_html += "<td>";
	dd_html += "<input type='text' id='yf' name='yf' value='' style='width:80px' maxlength='2' onkeyup=\"value=value.replace(/[^\\d]/g,'')\">��<input type='text' id='rq' name='rq' value='' maxlength='2' style='width:80px' onkeyup=\"value=value.replace(/[^\\d]/g,'')\">��";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</tbody>";
	dd_html += "</tfoot>";
	dd_html += "<tr>";
	dd_html += "<td>";
	dd_html += "<div class='btn'>";
	dd_html += "<button class='button2' onclick='dataConfig()'>ȷ��</button>&nbsp;&nbsp;";//szGroup()
	dd_html += "<button class='button2' onclick='closeStuDiv()'>ȡ��</button>";//2010.11.17 edit by great luo
	dd_html += "</div>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</tfoot>";
	dd_html += "</table>";
	dd_html += "";
//	viewTempDiv("��ҵʱ������","bysjDiv",400,300);
	showTempDivForJs("��ҵʱ������",dd_html,400,300);
}

function closeStuDiv(){
	$('tempDiv').style.display='none';
	i = document.getElementsByTagName("select").length;

	for (j = 0; j < i; j++) {
		document.getElementsByTagName("select")[j].style.visibility = "";
		document.getElementsByTagName("select")[j].style.display = "";
	}
}
function showBycl(){
	var xxdm = val('xxdm');
	var dd_html = "";
	dd_html += "<table class='formlist'>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<th>";
	dd_html += "��ѡ���޸ķ�ʽ��";
	dd_html += "</th>";
	dd_html += "<td>";
	//dd_html += "<input type='radio' name='configtype' value='0' checked>&nbsp;��ʼ��ȫ��&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<input type='radio' name='configtype' value='2' checked>&nbsp;�������޸�(���ڲ�ѯ����ѡ���ѧ������)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";		
	dd_html += "</br><input type='radio' name='configtype' value='1'>&nbsp;�޸�ѡ�е�����(���ڲ�ѯ����б��й�ѡ������)";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</tbody>"
	
	dd_html += "<tbody>";		
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<th colspan='2'>";
	dd_html += "<span>ѧ����ҵ�����Ϣ</span>";
	dd_html += "</th>";
	dd_html += "</tr>";
	dd_html += "</thead>";	
	dd_html += "<tr height='30'>";
	dd_html += "<th>";
	dd_html += "ѧ��״̬��";
	dd_html += "</th>";
	dd_html += "<td>";
	dd_html += "<select id='newxjztm' name='xjzt'></select>";
	dd_html += "</td>";
	dd_html += "</tr>";
	if(xxdm=="10491"){//�й����ʴ�ѧ
		dd_html += "<tr height='30'>";
		dd_html += "<th>";
		dd_html += "�Ƿ���У��";
		dd_html += "</th>";
		dd_html += "<td>";
		dd_html += "<select id='newsfzx' name='sfzx'><option></option><option value='��У'>��У</option><option value='����У'>����У</option></select>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<th>";
		dd_html += "�ܷ��ҵ��";
		dd_html += "</th>";
		dd_html += "<td>";
		dd_html += "<select id='newnfby' name='nfby'><option></option><option value='��'>��</option><option value='��'>��</option></select>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<th>";
		dd_html += "�Ƿ��ѱ�ҵ��";
		dd_html += "</th>";
		dd_html += "<td>";
		dd_html += "<select id='newsfyby' name='sfyby'><option></option><option value='��'>��</option><option value='��'>��</option></select>";
		dd_html += "</td>";
		dd_html += "</tr>";
	}
	dd_html += "<tr height='30'>";
	dd_html += "<th>";
	dd_html += "�Ƿ��ҵ����";
	dd_html += "</th>";
	dd_html += "<td>";
	dd_html += "<select id='newsfbys' name='sfbys'><option></option><option value='��'>��</option><option value='��'>��</option></select>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<th>";
	dd_html += "��ҵʱ�䣺";
	dd_html += "</th>";
	dd_html += "<td>";
	dd_html += "<input type='text' id='newbyny' name='byny' onclick=\"return showCalendar('newbyny','y-mm-dd');\"/>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</tbody>";
	dd_html += "<tfoot>";
	dd_html += "<tr height='30'>";
	dd_html += "<td aligin='right' colspan='2'> <div class='btn'>";
	dd_html += "<span class='red'>���������ѧ����ҵ��ص���Ϣ,�����ز�����</span>";
	dd_html += "<button onclick='byxxConfig()'>ȷ��</button>&nbsp;&nbsp;";//szGroup()
	dd_html += "<button onclick='closeStuDiv()'>ȡ��</button>";
	dd_html += "</div></td>";
	dd_html += "</tr>";
	dd_html += "</tfoot>";
	dd_html += "</table>";
	dd_html += "";
//	showDiv(dd_html, 400, 170);
//	alert("enter ...");
	showTempDivForJs("��ҵ��Ϣ����",dd_html,450,500);
	copySelect("xjztm", "newxjztm");
}

function showConfBys(){
	var dd_html = "<div>";
	dd_html += "<center><br><table width='350' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='center'>";
	dd_html += "��ѡ���ʼ����ʽ��";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='center'>";
	dd_html += "<input type='radio' name='configbys' value='1'>&nbsp;��������ʼ��&nbsp;&nbsp;&nbsp;&nbsp;";		
	dd_html += "<input type='radio' name='configbys' value='2'>&nbsp;��ʼ��ѡ�е�����";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30' bgcolor='EEF4F9'>";
	dd_html += "<td align='center'>";
	dd_html += "<button class='button2' onclick='configBys()'>ȷ��</button>&nbsp;&nbsp;";//szGroup()
	dd_html += "<button class='button2' onclick='closeAdd()'>ȡ��</button>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "</table></center>";
	dd_html += "</div>";
	
	showDiv(dd_html, 400, 170);
}



function hzy_cjbgdld(){
	//ѧ��ѧ�ڱ���ѡ��
	xn = val('newxn');
	xq = val('newxq');
	
	if(xn == "" || xq == ""){
		alert('����ѡ��ѧ��ѧ�ڣ�');
		return false;
	}
	if($("rsTable").rows.length > 1){
		rowOnClick($("rsTable").rows[1]);
		var xh1 = "";
		var xh2 = "";			
		var curr_R = window.curr_row.rowIndex;
		if(xh1 == "" && curr_R <= window.$("rsTable").rows.length){
			rowOnClick($("rsTable").rows[1]);				
			xh1 = $("rsTable").rows[1].cells[1].innerText.trim();
		}
		if(xh2=="" && curr_R <= window.$("rsTable").rows.length){
			window.rowOnClick(window.$("rsTable").rows[curr_R+1]);//������һ��
			xh2 = window.curr_row.cells[1].innerText.trim();			
		}
		window.open("xsxxgl.do?method=cjbgdlxdy&xh1="+xh1+"&xh2="+xh2+"&xn="+xn+"&xq="+xq);
		BatAlert.closeTips();
	 } else{
	    BatAlert.MyAlert("û�пɴ�ӡ�����ݣ�");
		return false;
	 }
}

function showCjbgddytj(){
	var dd_html = "<div>";
	dd_html += "<center><br><table width='350' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='center'>";
	dd_html += "��ѡ��Ҫ��ӡ��ѧ��ѧ�ڣ�";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='center'>";
	dd_html += "ѧ�꣺<select id='newxn' name='newxn' style='width:120px'></select>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr>";
	dd_html += "<td align='center'>";
	dd_html += "ѧ�ڣ�<select id='newxq' name='newxq' style='width:120px'></select>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30' bgcolor='EEF4F9'>";
	dd_html += "<td align='center'>";
	dd_html += "<button class='button2' onclick='hzy_cjbgdld()'>ȷ��</button>&nbsp;&nbsp;";//szGroup()
	dd_html += "<button class='button2' onclick='closeAdd()'>ȡ��</button>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "</table></center>";
	dd_html += "</div>";
	dd_html += "<script>";
	dd_html += "initXnList()";
	dd_html += "</script>";
	showDiv(dd_html, 400, 170);
	copySelect("xn", "newxn");
	copySelect("xq", "newxq");
}

function dataConfig(){
	var tmp_type = document.getElementsByName("configtype2");
	var tmp_type2 = document.getElementsByName("configtype");
	
	var yf = document.getElementById("yf").value;
	var rq = document.getElementById("rq").value;
	var type = "";
	var url = "";

	for(var i=0;i<tmp_type.length;i++){
		if(tmp_type[i].checked){
			type = tmp_type[i].value;
		}
	}
	
	for(var i=0;i<tmp_type2.length;i++){
		if(tmp_type2[i].checked){
			type = tmp_type2[i].value;
		}
	}
	
	if(yf == "" || rq == ""){
		alert("�·ݺ����ڱ�����д��");
		return false;
	}
	
	if(toInt(yf)>12 || toInt(yf<=0)){
		alert("��������ȷ���·ݣ�");
		return false;
	}
	if(toInt(rq)>31 || toInt(rq<=0)){
		alert("��������ȷ�����ڣ�");
		return false;
	}
	if(type == '2'){//��������ʼ��
//		var nj = document.getElementById('nj').value;
//		var xydm = document.getElementById('xy').value;
//		var zydm = document.getElementById('zy').value;
//		var bjdm = document.getElementById('bj').value;
//		var xz = document.getElementById('xz').value;
//		var condition = "";
//		
//		if(nj != null && nj != ''){
//			if(condition == ''){
//				condition += "�꼶��" + nj;
//			}else{
//				condition += ",�꼶��" + nj;
//			}
//		}
//		
//		if(xydm != null && xydm != ''){
//			if(condition == ''){
//				condition += "ѧԺ��" + document.getElementById('xy').options[document.getElementById('xy').selectedIndex].text;;
//			}else{
//				condition += ",ѧԺ��" + document.getElementById('xy').options[document.getElementById('xy').selectedIndex].text;;
//			}
//		}
//		
//		if(zydm != null && zydm != ''){
//			if(condition == ''){
//				condition += "רҵ��" + document.getElementById('zy').options[document.getElementById('zy').selectedIndex].text;
//			}else{
//				condition += ",רҵ��" + document.getElementById('zy').options[document.getElementById('zy').selectedIndex].text;
//			}
//		}
//		
//		if(bjdm != null && bjdm != ''){
//			if(condition == ''){
//				condition += "�༶��" + document.getElementById('bj').options[document.getElementById('bj').selectedIndex].text;
//			}else{
//				condition += ",�༶��" + document.getElementById('bj').options[document.getElementById('bj').selectedIndex].text;
//			}
//		}
//		
//		if(condition == ""){
//			condition = "ȫ��";
//		}
		
		if(confirm("��ʼ���ı�ҵ������꼶��ѧ�����ɣ���ȷ��������������ʼ����")){
			//�޸ı�ҵ���
			refreshForm('xsxxgl.do?method=configBynyByCondition&yf='+yf+"&rq="+rq);
		}
	}
	if(type == '1'){//��ʼ��ѡ�������		
		var RowsStr="!!";		
		for (i=0; i<document.getElementsByName("pk").length; i++){
		   if(document.getElementsByName("pk")[i].checked){
		   		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
		    }
		}
		if (RowsStr=="!!"){
			alert("��ѡ��Ҫ���������ļ�¼��");
			return false;
		}
		if(confirm('��ʼ���ı�ҵ������꼶��ѧ�����ɣ���ȷ����ѡ������ݽ��г�ʼ��������')){
			refreshForm('xsxxgl.do?method=configBynyByData&pk='+RowsStr+'&yf='+yf+'&rq='+rq);
		}
	}
	if(type == '0'){//��ʼ��ȫ��
		if(confirm('��ʼ���ı�ҵ������꼶��ѧ�����ɣ���ȷ����ʼ��ȫ��������')){
			refreshForm('xsxxgl.do?method=configBynyByCondition&type=all'+'&yf='+yf+'&rq='+rq);
		}
	}
}

function byxxConfig(){
	var tmp_type = document.getElementsByName("configtype");	
	var type = "";
	var url = "";
	for(var i=0;i<tmp_type.length;i++){
		if(tmp_type[i].checked){
			type = tmp_type[i].value;
		}
	}
	
	var zdz = "";
	var tj = "";
	var xxdm=$("xxdm").value;
	var column =new Array();
	if(xxdm=="10491"){
		column = ["select_newxjztm", "select_newsfzx", "select_newnfby", "select_newsfbys", "select_newsfyby", "newbyny"];
	}else{
		column = ["select_newxjztm","select_newsfbys","newbyny","newxjztm","newsfbys"];
	}
	//2010.11.17 edit by great luo
	for(var i=0; i<column.length; i++){
		if($(column[i])){
			zdz += val(column[i]);
			tj += "&" + column[i] + "=" + val(column[i]);
		}	
	}
	
	if(zdz == ""){
		alert("��û����дҪ�޸ĵ���Ϣ��");
		return false;
	}	

	if(type == '2'){//��������ʼ��
		var nj;
		var xydm;
		var zydm;
		var bjdm;
		var xz;
		
		if($('nj')){
			nj = document.getElementById('nj').value;
		}
		if($('xydm')){
			xydm = document.getElementById('xy').value;
		}
		if($('zydm')){
			zydm = document.getElementById('zy').value;
		}
		if($('bjdm')){
			bjdm = document.getElementById('bj').value;
		}
		if($('xz')){
			xz = document.getElementById('xz').value;
		}
		
		var condition = "";
		
		if(nj != null && nj != ''){
			if(condition == ''){
				condition += "�꼶��" + nj;
			}else{
				condition += ",�꼶��" + nj;
			}
			tj += "&nj=" + nj;
		}
		
		if(xydm != null && xydm != ''){
			if(condition == ''){
				condition += jQuery("#xbmc").val()+"��" + document.forms[0].xy.options[document.forms[0].xy.selectedIndex].text;;
			}else{
				condition += ","+jQuery("#xbmc").val()+"��" + document.forms[0].xy.options[document.forms[0].xy.selectedIndex].text;;
			}
			tj += "&xydm=" + xydm;
		}
		
		if(zydm != null && zydm != ''){
			if(condition == ''){
				condition += "רҵ��" + document.forms[0].zy.options[document.forms[0].zy.selectedIndex].text;
			}else{
				condition += ",רҵ��" + document.forms[0].zy.options[document.forms[0].zy.selectedIndex].text;
			}
			tj += "&zydm=" + zydm;
		}
		
		if(bjdm != null && bjdm != ''){
			if(condition == ''){
				condition += "�༶��" + document.forms[0].bj.options[document.forms[0].bj.selectedIndex].text;
			}else{
				condition += ",�༶��" + document.forms[0].bj.options[document.forms[0].bj.selectedIndex].text;
			}
			tj += "&bjdm=" + bjdm;
		}
		
		if(condition == ""){
			condition = "ȫ��";
		}
		
		if(confirm('�ò���������ѧ�������޸����������Ϣ��\n' + condition+"��\n���������أ���ȷ�����иò�����")){
			//�޸ı�ҵ���
			refreshForm('xsxxZgdzdx.do?method=bycl'+tj);
		}
	}
	if(type == '1'){//��ʼ��ѡ�������		
		var RowsStr="!!";		
		for (i=0; i<document.getElementsByName("pk").length; i++){
		   if(document.getElementsByName("pk")[i].checked){
		   		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
		   }
		}
		if (RowsStr=="!!"){
			alert("��ѡ��Ҫ���������ļ�¼��");
			return false;
		}
		setVal('delPk',RowsStr);
		
		if(confirm('�ò���������ѡ��������޸����������Ϣ�����������أ�\n��ȷ����ѡ������ݽ����޸Ĳ�����')){
			refreshForm('xsxxZgdzdx.do?method=bycl&type=haveCondition&pk='+RowsStr + tj);
		}
	}
	if(type == '0'){//��ʼ��ȫ��
		if(confirm('�ò���ֻ�޸����������Ϣ����ȷ���޸�ȫ��������')){
			refreshForm('xsxxZgdzdx.do?method=bycl&type=all'+tj);
		}
	}
	if(type == ''){
		alert("��ѡ���޸ķ�ʽ��");
		return false;
	}
}

function configBys(){
	var tmp_type = document.forms[0].configbys;
	var type = "";
	var url = "";
	
	for(var i=0;i<tmp_type.length;i++){
		if(tmp_type[i].checked){
			type = tmp_type[i].value;
		}
	}
	if(type == '1'){//��������ʼ��
		var nj = document.getElementById('nj').value;
		var xydm = document.getElementById('xy').value;
		var zydm = document.getElementById('zy').value;
		var bjdm = document.getElementById('bj').value;
		var xz = document.getElementById('xz').value;
		var condition = "";
		
		if(nj != null && nj != ''){
			if(condition == ''){
				condition += "�꼶��" + nj;
			}else{
				condition += ",�꼶��" + nj;
			}
		}
		
		if(xydm != null && xydm != ''){
			if(condition == ''){
				condition += jQuery("#xbmc").val()+"��" + document.forms[0].xy.options[document.forms[0].xy.selectedIndex].text;;
			}else{
				condition += ","+jQuery("#xbmc").val()+"��" + document.forms[0].xy.options[document.forms[0].xy.selectedIndex].text;;
			}
		}
		
		if(zydm != null && zydm != ''){
			if(condition == ''){
				condition += "רҵ��" + document.forms[0].zy.options[document.forms[0].zy.selectedIndex].text;
			}else{
				condition += ",רҵ��" + document.forms[0].zy.options[document.forms[0].zy.selectedIndex].text;
			}
		}
		
		if(bjdm != null && bjdm != ''){
			if(condition == ''){
				condition += "�༶��" + document.forms[0].bj.options[document.forms[0].bj.selectedIndex].text;
			}else{
				condition += ",�༶��" + document.forms[0].bj.options[document.forms[0].bj.selectedIndex].text;
			}
		}
		
		if(condition == ""){
			condition = "ȫ��";
		}
		
		if(confirm('��ʼ����ѧ����Ϊ��ҵ������ȷ��������ѧ��������ʼ����\n' + condition)){
			//�޸ı�ҵ���
			refreshForm('xsxxgl.do?method=configBysByCondition');
		}
	}
	if(type == '2'){//��ʼ��ѡ�������		
		var RowsStr="!!";		
		for (i=0; i<document.getElementsByName("pk").length; i++){
		   if(document.getElementsByName("pk")[i].checked){
		   		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
		    }
		}
		
		if (RowsStr=="!!"){
			alert("��ѡ��Ҫ���������ļ�¼��");
			return false;
		}
		
		if(confirm('��ʼ����ѧ����Ϊ��ҵ������ȷ����ѡ������ݽ��г�ʼ��������')){
			refreshForm('xsxxgl.do?method=configBysByData&pk='+RowsStr);
		}
	}	
}

function printNewReport(url){
	if(curr_row == null){
		alert('��ѡ��һ����Ҫ��ӡ�����ݣ�');
		return false;
	}
	var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
	showOpenWindow(url + pk,800,600);
}

function nextOrUp(oper){
	var obj = opener;

	var cu_R = obj.curr_row.rowIndex;
	if(oper == "N" && cu_R < obj.$('rsTable').rows.length){//��һҳ
		obj.rowOnClick(obj.$("rsTable").rows[cu_R+1]);//������һ��
	}else if(cu_R>1){
		obj.rowOnClick(obj.$("rsTable").rows[cu_R-1]);//������һ��
	}
	stuInfoWin(obj.curr_row,true);
}

function showButton(){
	if(window.dialogArguments.curr_row){
		var cu_R = window.dialogArguments.curr_row.rowIndex;
		if(cu_R >= window.dialogArguments.$("rsTable").rows.length-1){
			if(ele('buttonNext')){
				ele('buttonNext').disabled = true;
			}
		}	
		if(cu_R <= 1){
			if(ele('buttonUp')){
				ele('buttonUp').disabled = true;
			}
		}
	}
}

//ѧ����Ϣά��ҳ�����ӱ����ֶ�
function addNotNullFiled(){
	var xxdm = val('xxdm');
	if(xxdm == "12104"){
		//����ְҵ����ѧԺ		
		var strNotNull = val('notnull');
		var thisFiled = "mz-zzmm-hkszd-sfzh-sjhm-syd-dzyx-lxdh-yhdm-yhkh";
		var appendFiled = thisFiled;
		
		if(ele('hkshen')){
			//��ַ��Ϣȡ����
			thisFiled = "mz-zzmm-hkshen-sfzh-sjhm-syshen-dzyx-lxdh-yhdm-yhkh";
			appendFiled = "mz-zzmm-hkshen-hkshi-hkxian-sfzh-sjhm-syshen-syshi-syxian-dzyx-lxdh-yhdm-yhkh";
		}
		strNotNull += "-"+appendFiled;		
		
		setVal('notnull',strNotNull);
		appendNotNullFlag(thisFiled,"-");
	}
}

//ѧ����ͥ��Ϣά��ҳ�����ӱ����ֶ�
function addJtxxNotNullFiled(){
	var xxdm = val('xxdm');
	if(xxdm == "12104"){
		//����ְҵ����ѧԺ		
		var strNotNull = val('notnull');
		var thisFiled = "jtszd-jtyb-lxdh1-xm-ch-jtcy1_mz-jtcy1_zzmm-jtcy1_zy-jtcy1_lxdh2-jtcy1_lxdh1-jtcy1_gzdz-jtcy2_xm-jtcy2_gx-jtcy2_lxdh1-jtcy2_gzdz";
		var appendFiled = thisFiled;
		
		strNotNull += "-"+appendFiled;		
		
		setVal('notnull',strNotNull);
		appendNotNullFlag(thisFiled,"-");
	}
}

function saveConfigInfo(){
	var xxdm = val('xxdm');
	var isSz = val("isSz");
	var kssj = val('kssj').replace('-','').replace('-','').replace('-','')+val('kssjH')+val('kssjM')+val("kssjS");
	var jssj =  val('jssj').replace('-','').replace('-','').replace('-','')+val('jssjH')+val('jssjM')+val("jssjS");;
	var bzrKssj ="";
	var bzrJssj = "";
	
	if(ele('bzrkssj')){
		bzrKssj = val('bzrkssj').replace('-','').replace('-','').replace('-','')+val('bzrkssjH')+val('bzrkssjM')+val('bzrkssjS');
		bzrJssj = val('bzrjssj').replace('-','').replace('-','').replace('-','')+val('bzrjssjH')+val('bzrjssjM')+val('bzrjssjS');
	}
	if(kssj != "" && jssj!='' && kssj>jssj && isSz == "yes"){
		alert("�޸Ŀ�ʼʱ�����ڽ���ʱ�䣡");
		return false;
	}
	if(xxdm == "13429"){
		//�ϲ���ѧ��ѧ����ѧԺ
		if(bzrKssj != '' && bzrJssj !='' && bzrKssj<bzrJssj){
			doSaveConf('/xgxt/studentMessage_conf.do?doType=save','nd-xn-xq-kssj-kssjH-kssjM-kssjS-jssj-jssjH-jssjM-jssjS-bzrkssj-bzrkssjH-bzrkssjM-bzrkssjS-bzrjssj-bzrjssjH-bzrjssjM-bzrjssjS');
		}else{
			alert("�������޸Ŀ�ʼʱ�����ڽ���ʱ�䣡");
			return false;
		}
	}else{
		doSaveConf('/xgxt/studentMessage_conf.do?doType=save','nd-xn-xq-kssj-kssjH-kssjM-kssjS-jssj-jssjH-jssjM-jssjS');
	}
}

function checkXjydcxTime(){
	var result = true;
	var ydrqks = val('ydrqks').replace('-','').replace('-','').replace('-','');
	var ydrqjs = val('ydrqjs').replace('-','').replace('-','').replace('-','');
	var ydjzrqks = val('ydjzrqks').replace('-','').replace('-','').replace('-','');
	var ydjzrqjs = val('ydjzrqjs').replace('-','').replace('-','').replace('-','');
	
	if(ydrqks != '' && ydrqjs !='' && ydrqjs<ydrqks){
		alert('�춯���ڿ�ʼʱ�����ڽ���ʱ�䣡');
		result = false;
	}
	if(ydjzrqks != '' && ydjzrqjs !='' && ydjzrqjs<ydjzrqks){
		alert('�춯��ֹ���ڿ�ʼʱ�����ڽ���ʱ�䣡');
		result = false;
	}
		
    return result;
}