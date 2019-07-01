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
			alert("请选择一行记录！\n单击一行即可!");
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
		alert("请选择一行记录！\n单击一行即可!");
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
			alert("请选择一行记录！\n单击一行即可!");
			return false;
	} 		
	pk= curr_row.cells[0].getElementsByTagName("input")[0].value;
	url+=pk;	
	showTopWin(url,w,h);	
}

function stuinfoDel(url){
	if(curr_row==null){
		alert("请选中您要删除的记录!\n单击一行即可!");
		return false;
	}
	if(confirm('您确定删除吗？')){	
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
		alert("请将带＊号的项目填写完整！");
		return false;
		}
	}
	if($("dzyx")){//电子邮箱检测
		var dzyx = document.getElementById('dzyx').value;
		if(!isEmail(dzyx) && dzyx!=""){
			alert("请输入正确的电子邮箱!");
			return false;
		}
	}
	if($("jjzk")){
		var jjzk = document.getElementById("jjzk").value;
		if(jjzk){
		   if(jjzk.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
		       alert("家庭经济状况不能大于100个字符");
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
				if(xxdm == "10491"){//中国地质大学
					xsxxZgkd.getBtzdByYh(yhjs,"xsfzxxb",function(data){
						if(data != null){
							for(var i=0; i<data.length; i++){
								if(ele(data[i].en) && ele(data[i].en).value == ""){
									alert(data[i].cn + "不能为空！");
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
							alert('学号不存在！');
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
//							alert('学号已经存在！');
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
			if(xxdm == "10491"){//中国地质大学
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
								alert(data[i].cn + "不能为空！");
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
		alert("请选中您要删除的记录!\n单击一行即可!");
		return false;
	}
	if(confirm('您确定删除吗？')){				
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
		alert("请将带＊号的项目填写完整！");
		return false;
		}
	}		
	if(selText('ydlbdm') == '休学'){
		if(val('ydjzrq') == ""){
			alert('休学必须填写异动截止日期！');
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
						alert('异动序号已经存在！');
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
				alert("请将带＊号的项目填写完整！");
				return false;
			}
		}
	}	
	if (type == "add") {
	getXjydInfo.getColumnEx("view_stu_archives","xh",xh,function(data){
		if(data==true){
			alert("该学生已经存在档案信息！");
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
			alert("请将带＊号的项目填写完整！");
			return false;
		}
	}	
	if (type == "add") {
	getXjydInfo.getColumnEx("stu_history_lab","xh",xh,function(data){
		if(data==true){
			alert("该学生已经存在档案信息！");
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
	showHtml  += "<tr><td colspan='6'><center><b>未提交归档资料的学生信息</b></center></td></tr>";
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
	showHtml += "确定";
	showHtml += "</button></td><td colspan='3'>";
	showHtml += "<div class='buttontool'>";
	showHtml += "<button class='button2' onclick='dataExport3()' style='width:80px'>";
	showHtml += "导出数据";
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
			alert("请将带*号的项目填写完整！");
			return false;
		}
	}
	if(kssj>jssj){
		alert("结束时间应晚于开始时间！");
		return false;
	}
	document.forms[0].action=url;
	document.forms[0].submit();
}
function commSave(url,columns){
	var tvalue=columns.split("-");
	for(var i=0;i<tvalue.length;i++){
		if(document.getElementById(tvalue[i]).value==""){
		alert("请将带\*号的项目填写完整！");
		return false;
		}
	}	
	//文本框长度判断	
	if($('sqly')){
		var sqly = document.getElementById('sqly').value;
		if(sqly != null){
		   if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 150){	         
		       alert("申请理由不能大于150个字符");
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
			alert("请选择一行记录！\n单击一行即可!");
			return false;
		} 		
	pk= curr_row.cells[1].getElementsByTagName("input")[0].value;
	url+=pk;	
	showTopWin(url,w,h);	
}

function commDel(url){
	var pk="";
	if(curr_row==null){
		alert("请选中您要删除的记录!\n单击一行即可!");
		return false;
	}
	if(confirm('您确定删除吗？')){
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
			alert('请选择您要操作的数据！');
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
		alert("请输入学号");
		return false;
	}
	getXjydInfo.checkArchivesExist(xh,function(data){
		if(data==""){
		//增加归档资料判断，资料没有提交完整的不能转档
			if(show==""){
				alert ("恭喜！可以转档！");
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
	if(xxdm=="12957"){//深圳信息
		var fzrq = prompt("   请输入发证日期：(日期格式为xxxx-xx-xx)","");
		var bfrq = prompt("   请输入补发日期：(日期格式为xxxx-xx-xx)","");
		var yxrq = prompt("   请输入有效期至：(日期格式为xxxx-xx-xx)","");
	}
    BatAlert.MyAlert("确定要做此操作吗？此操作将连续打印显示的学生数据","",function(){
 	   BatAlert.showTips("数据操作中，请稍候...");
    	var xh;
		if($("tabPri").rows.length > 1){
			rowOnClick($("tabPri").rows[0]);
			xh=$("tabPri").rows[0].cells[1].innerText.trim();
			if(xxdm=="12957"){//深圳信息
				window.open("noticePrintOne.do?xh="+xh+"&fzrq=" + fzrq + "&bfrq=" + bfrq + "&yxrq=" + yxrq);
			}
			BatAlert.closeTips();
		 } else{
		    BatAlert.MyAlert("没有可打印的数据！");
			return false;
		 }
	},true);
	return false;   
}


function certificatePrint(){	
	d_html = "<table class='formlist'>";
	d_html += "<tbody>";
	d_html +="<tr><td>此操作将连续打印显示的学生数据,确定要做此操作吗？</td></tr>";
	d_html += "</tbody>";
	d_html += "<tfoot>";
	d_html +="<tr><td align='center'><button onclick='exePrint()'>确定</button><button onclick=\"$('tempDiv').style.display='none';return false;\">取消</button></td></tr>";
	d_html += "</tfoot>";
	d_html +="</table>";
	showTempDivForJs("提示信息",d_html, 350, 100);
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
	    alert("没有可打印的数据！");	   
		return false;
	 }
}

function delMore(url){//批量删除
	var RowsStr="!!SplitOneSplit!!";   										//定义连接字符串			
		if (Rows.length==0){  													//原先判断curr_row == null
			alert("请选择要删除的数据！\n（单击相应的行）");
			return false;
		} else {
			for (i=0; i<Rows.length; i++){ 										//连接字符串
    			RowsStr+=Rows[i].getElementsByTagName("input")[0].value+"!!SplitOneSplit!!";
			}
			if(url=="/xgxt/archives_apply_query.do?doType=del&pkValue="){
				RowsStr = "";
				for(i=0; i<Rows.length; i++){
					RowsStr+=Rows[i].getElementsByTagName("input")[0].value+Rows[i].getElementsByTagName("input")[1].value+"!!SplitOneSplit!!";
				}
			}
			if (confirm("确定要删除选择的数据吗？")) {
				pkValue = RowsStr; 												//建立主键
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
 * 默认对应所有的字段
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
	document.forms[0].mappingItem.value = "";//清空mappingItems中的值
	if (document.forms[0].mappingList.options.length <= 0) {
		alert("导出字段不能为空!");
		return false;
	}
	var showConfirmText = "您确定导出所选的字段吗？";
	var showRunningText;
	
    if (confirm(showConfirmText)) {
    	for (i = 0; i < document.forms[0].mappingList.options.length; i++) {
    		document.forms[0].mappingItem.value = document.forms[0].mappingItem.value + "!!SplitOne!!";
    		document.forms[0].mappingItem.value = document.forms[0].mappingItem.value + document.forms[0].mappingList.options[i].value;
    	}
    	document.forms[0].mappingItem.value = DWRUtil.byId("mappingItem").value.substr("!!SplitOne!!".length);//剪切掉字符串前面的"!!SplitOne"
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

//转到教务课表查询页面
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
		alert('请输入学号！');
		return false;
	}
	if(zmlx == null || zmlx==''){
		alert('请选择证明类型！');
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
	dd_html += "  请选择初始化方式";
	dd_html += " </th>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr>";
	dd_html += "<td>";
	dd_html += "<input type='radio' name='configtype' value='0'checked>&nbsp;初始化全部&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<input type='radio' name='configtype' value='2'>&nbsp;按条件初始化&nbsp;&nbsp;&nbsp;&nbsp;";		
	dd_html += "<input type='radio' name='configtype' value='1'>&nbsp;初始化选中的数据";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</tbody>";
	dd_html += "<thead>";
	dd_html += "<tr>";
	dd_html += " <th>";
	dd_html += "  输入毕业月份和日期";
	dd_html += " </th>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr>";
	dd_html += "<td>";
	dd_html += "<input type='text' id='yf' name='yf' value='' style='width:80px' maxlength='2' onkeyup=\"value=value.replace(/[^\\d]/g,'')\">月<input type='text' id='rq' name='rq' value='' maxlength='2' style='width:80px' onkeyup=\"value=value.replace(/[^\\d]/g,'')\">日";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</tbody>";
	dd_html += "</tfoot>";
	dd_html += "<tr>";
	dd_html += "<td>";
	dd_html += "<div class='btn'>";
	dd_html += "<button class='button2' onclick='dataConfig()'>确定</button>&nbsp;&nbsp;";//szGroup()
	dd_html += "<button class='button2' onclick='closeStuDiv()'>取消</button>";//2010.11.17 edit by great luo
	dd_html += "</div>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</tfoot>";
	dd_html += "</table>";
	dd_html += "";
//	viewTempDiv("毕业时间设置","bysjDiv",400,300);
	showTempDivForJs("毕业时间设置",dd_html,400,300);
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
	dd_html += "请选择修改方式：";
	dd_html += "</th>";
	dd_html += "<td>";
	//dd_html += "<input type='radio' name='configtype' value='0' checked>&nbsp;初始化全部&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<input type='radio' name='configtype' value='2' checked>&nbsp;按条件修改(您在查询栏中选择的学籍条件)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";		
	dd_html += "</br><input type='radio' name='configtype' value='1'>&nbsp;修改选中的数据(您在查询结果列表中勾选的数据)";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</tbody>"
	
	dd_html += "<tbody>";		
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<th colspan='2'>";
	dd_html += "<span>学生毕业相关信息</span>";
	dd_html += "</th>";
	dd_html += "</tr>";
	dd_html += "</thead>";	
	dd_html += "<tr height='30'>";
	dd_html += "<th>";
	dd_html += "学籍状态：";
	dd_html += "</th>";
	dd_html += "<td>";
	dd_html += "<select id='newxjztm' name='xjzt'></select>";
	dd_html += "</td>";
	dd_html += "</tr>";
	if(xxdm=="10491"){//中国地质大学
		dd_html += "<tr height='30'>";
		dd_html += "<th>";
		dd_html += "是否在校：";
		dd_html += "</th>";
		dd_html += "<td>";
		dd_html += "<select id='newsfzx' name='sfzx'><option></option><option value='在校'>在校</option><option value='不在校'>不在校</option></select>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<th>";
		dd_html += "能否毕业：";
		dd_html += "</th>";
		dd_html += "<td>";
		dd_html += "<select id='newnfby' name='nfby'><option></option><option value='是'>是</option><option value='否'>否</option></select>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<th>";
		dd_html += "是否已毕业：";
		dd_html += "</th>";
		dd_html += "<td>";
		dd_html += "<select id='newsfyby' name='sfyby'><option></option><option value='是'>是</option><option value='否'>否</option></select>";
		dd_html += "</td>";
		dd_html += "</tr>";
	}
	dd_html += "<tr height='30'>";
	dd_html += "<th>";
	dd_html += "是否毕业生：";
	dd_html += "</th>";
	dd_html += "<td>";
	dd_html += "<select id='newsfbys' name='sfbys'><option></option><option value='是'>是</option><option value='否'>否</option></select>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<th>";
	dd_html += "毕业时间：";
	dd_html += "</th>";
	dd_html += "<td>";
	dd_html += "<input type='text' id='newbyny' name='byny' onclick=\"return showCalendar('newbyny','y-mm-dd');\"/>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</tbody>";
	dd_html += "<tfoot>";
	dd_html += "<tr height='30'>";
	dd_html += "<td aligin='right' colspan='2'> <div class='btn'>";
	dd_html += "<span class='red'>您输入的与学生毕业相关的信息,请慎重操作！</span>";
	dd_html += "<button onclick='byxxConfig()'>确定</button>&nbsp;&nbsp;";//szGroup()
	dd_html += "<button onclick='closeStuDiv()'>取消</button>";
	dd_html += "</div></td>";
	dd_html += "</tr>";
	dd_html += "</tfoot>";
	dd_html += "</table>";
	dd_html += "";
//	showDiv(dd_html, 400, 170);
//	alert("enter ...");
	showTempDivForJs("毕业信息设置",dd_html,450,500);
	copySelect("xjztm", "newxjztm");
}

function showConfBys(){
	var dd_html = "<div>";
	dd_html += "<center><br><table width='350' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='center'>";
	dd_html += "请选择初始化方式：";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='center'>";
	dd_html += "<input type='radio' name='configbys' value='1'>&nbsp;按条件初始化&nbsp;&nbsp;&nbsp;&nbsp;";		
	dd_html += "<input type='radio' name='configbys' value='2'>&nbsp;初始化选中的数据";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30' bgcolor='EEF4F9'>";
	dd_html += "<td align='center'>";
	dd_html += "<button class='button2' onclick='configBys()'>确定</button>&nbsp;&nbsp;";//szGroup()
	dd_html += "<button class='button2' onclick='closeAdd()'>取消</button>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "</table></center>";
	dd_html += "</div>";
	
	showDiv(dd_html, 400, 170);
}



function hzy_cjbgdld(){
	//学年学期必须选择
	xn = val('newxn');
	xq = val('newxq');
	
	if(xn == "" || xq == ""){
		alert('必须选择学年学期！');
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
			window.rowOnClick(window.$("rsTable").rows[curr_R+1]);//单击下一行
			xh2 = window.curr_row.cells[1].innerText.trim();			
		}
		window.open("xsxxgl.do?method=cjbgdlxdy&xh1="+xh1+"&xh2="+xh2+"&xn="+xn+"&xq="+xq);
		BatAlert.closeTips();
	 } else{
	    BatAlert.MyAlert("没有可打印的数据！");
		return false;
	 }
}

function showCjbgddytj(){
	var dd_html = "<div>";
	dd_html += "<center><br><table width='350' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='center'>";
	dd_html += "请选择要打印的学年学期：";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='center'>";
	dd_html += "学年：<select id='newxn' name='newxn' style='width:120px'></select>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr>";
	dd_html += "<td align='center'>";
	dd_html += "学期：<select id='newxq' name='newxq' style='width:120px'></select>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30' bgcolor='EEF4F9'>";
	dd_html += "<td align='center'>";
	dd_html += "<button class='button2' onclick='hzy_cjbgdld()'>确定</button>&nbsp;&nbsp;";//szGroup()
	dd_html += "<button class='button2' onclick='closeAdd()'>取消</button>";
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
		alert("月份和日期必须填写！");
		return false;
	}
	
	if(toInt(yf)>12 || toInt(yf<=0)){
		alert("请输入正确的月份！");
		return false;
	}
	if(toInt(rq)>31 || toInt(rq<=0)){
		alert("请输入正确的日期！");
		return false;
	}
	if(type == '2'){//按条件初始化
//		var nj = document.getElementById('nj').value;
//		var xydm = document.getElementById('xy').value;
//		var zydm = document.getElementById('zy').value;
//		var bjdm = document.getElementById('bj').value;
//		var xz = document.getElementById('xz').value;
//		var condition = "";
//		
//		if(nj != null && nj != ''){
//			if(condition == ''){
//				condition += "年级：" + nj;
//			}else{
//				condition += ",年级：" + nj;
//			}
//		}
//		
//		if(xydm != null && xydm != ''){
//			if(condition == ''){
//				condition += "学院：" + document.getElementById('xy').options[document.getElementById('xy').selectedIndex].text;;
//			}else{
//				condition += ",学院：" + document.getElementById('xy').options[document.getElementById('xy').selectedIndex].text;;
//			}
//		}
//		
//		if(zydm != null && zydm != ''){
//			if(condition == ''){
//				condition += "专业：" + document.getElementById('zy').options[document.getElementById('zy').selectedIndex].text;
//			}else{
//				condition += ",专业：" + document.getElementById('zy').options[document.getElementById('zy').selectedIndex].text;
//			}
//		}
//		
//		if(bjdm != null && bjdm != ''){
//			if(condition == ''){
//				condition += "班级：" + document.getElementById('bj').options[document.getElementById('bj').selectedIndex].text;
//			}else{
//				condition += ",班级：" + document.getElementById('bj').options[document.getElementById('bj').selectedIndex].text;
//			}
//		}
//		
//		if(condition == ""){
//			condition = "全部";
//		}
		
		if(confirm("初始化的毕业年份由年级加学制生成，您确定按以下条件初始化吗？")){
			//修改毕业年份
			refreshForm('xsxxgl.do?method=configBynyByCondition&yf='+yf+"&rq="+rq);
		}
	}
	if(type == '1'){//初始化选择的数据		
		var RowsStr="!!";		
		for (i=0; i<document.getElementsByName("pk").length; i++){
		   if(document.getElementsByName("pk")[i].checked){
		   		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
		    }
		}
		if (RowsStr=="!!"){
			alert("请选择要批量操作的记录！");
			return false;
		}
		if(confirm('初始化的毕业年份由年级加学制生成，您确定按选择的数据进行初始化操作吗？')){
			refreshForm('xsxxgl.do?method=configBynyByData&pk='+RowsStr+'&yf='+yf+'&rq='+rq);
		}
	}
	if(type == '0'){//初始化全部
		if(confirm('初始化的毕业年份由年级加学制生成，您确定初始化全部数据吗？')){
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
		alert("您没有填写要修改的信息！");
		return false;
	}	

	if(type == '2'){//按条件初始化
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
				condition += "年级：" + nj;
			}else{
				condition += ",年级：" + nj;
			}
			tj += "&nj=" + nj;
		}
		
		if(xydm != null && xydm != ''){
			if(condition == ''){
				condition += jQuery("#xbmc").val()+"：" + document.forms[0].xy.options[document.forms[0].xy.selectedIndex].text;;
			}else{
				condition += ","+jQuery("#xbmc").val()+"：" + document.forms[0].xy.options[document.forms[0].xy.selectedIndex].text;;
			}
			tj += "&xydm=" + xydm;
		}
		
		if(zydm != null && zydm != ''){
			if(condition == ''){
				condition += "专业：" + document.forms[0].zy.options[document.forms[0].zy.selectedIndex].text;
			}else{
				condition += ",专业：" + document.forms[0].zy.options[document.forms[0].zy.selectedIndex].text;
			}
			tj += "&zydm=" + zydm;
		}
		
		if(bjdm != null && bjdm != ''){
			if(condition == ''){
				condition += "班级：" + document.forms[0].bj.options[document.forms[0].bj.selectedIndex].text;
			}else{
				condition += ",班级：" + document.forms[0].bj.options[document.forms[0].bj.selectedIndex].text;
			}
			tj += "&bjdm=" + bjdm;
		}
		
		if(condition == ""){
			condition = "全部";
		}
		
		if(confirm('该操作按以下学籍条件修改您输入的信息：\n' + condition+"。\n操作需慎重！您确定进行该操作吗？")){
			//修改毕业年份
			refreshForm('xsxxZgdzdx.do?method=bycl'+tj);
		}
	}
	if(type == '1'){//初始化选择的数据		
		var RowsStr="!!";		
		for (i=0; i<document.getElementsByName("pk").length; i++){
		   if(document.getElementsByName("pk")[i].checked){
		   		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
		   }
		}
		if (RowsStr=="!!"){
			alert("请选择要批量操作的记录！");
			return false;
		}
		setVal('delPk',RowsStr);
		
		if(confirm('该操作根据您选择的数据修改您输入的信息，操作需慎重！\n您确定按选择的数据进行修改操作吗？')){
			refreshForm('xsxxZgdzdx.do?method=bycl&type=haveCondition&pk='+RowsStr + tj);
		}
	}
	if(type == '0'){//初始化全部
		if(confirm('该操作只修改您输入的信息，您确定修改全部数据吗？')){
			refreshForm('xsxxZgdzdx.do?method=bycl&type=all'+tj);
		}
	}
	if(type == ''){
		alert("请选择修改方式！");
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
	if(type == '1'){//按条件初始化
		var nj = document.getElementById('nj').value;
		var xydm = document.getElementById('xy').value;
		var zydm = document.getElementById('zy').value;
		var bjdm = document.getElementById('bj').value;
		var xz = document.getElementById('xz').value;
		var condition = "";
		
		if(nj != null && nj != ''){
			if(condition == ''){
				condition += "年级：" + nj;
			}else{
				condition += ",年级：" + nj;
			}
		}
		
		if(xydm != null && xydm != ''){
			if(condition == ''){
				condition += jQuery("#xbmc").val()+"：" + document.forms[0].xy.options[document.forms[0].xy.selectedIndex].text;;
			}else{
				condition += ","+jQuery("#xbmc").val()+"：" + document.forms[0].xy.options[document.forms[0].xy.selectedIndex].text;;
			}
		}
		
		if(zydm != null && zydm != ''){
			if(condition == ''){
				condition += "专业：" + document.forms[0].zy.options[document.forms[0].zy.selectedIndex].text;
			}else{
				condition += ",专业：" + document.forms[0].zy.options[document.forms[0].zy.selectedIndex].text;
			}
		}
		
		if(bjdm != null && bjdm != ''){
			if(condition == ''){
				condition += "班级：" + document.forms[0].bj.options[document.forms[0].bj.selectedIndex].text;
			}else{
				condition += ",班级：" + document.forms[0].bj.options[document.forms[0].bj.selectedIndex].text;
			}
		}
		
		if(condition == ""){
			condition = "全部";
		}
		
		if(confirm('初始化后学生即为毕业生，您确定按以下学籍条件初始化吗？\n' + condition)){
			//修改毕业年份
			refreshForm('xsxxgl.do?method=configBysByCondition');
		}
	}
	if(type == '2'){//初始化选择的数据		
		var RowsStr="!!";		
		for (i=0; i<document.getElementsByName("pk").length; i++){
		   if(document.getElementsByName("pk")[i].checked){
		   		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
		    }
		}
		
		if (RowsStr=="!!"){
			alert("请选择要批量操作的记录！");
			return false;
		}
		
		if(confirm('初始化后学生即为毕业生，您确定按选择的数据进行初始化操作吗？')){
			refreshForm('xsxxgl.do?method=configBysByData&pk='+RowsStr);
		}
	}	
}

function printNewReport(url){
	if(curr_row == null){
		alert('请选择一条你要打印的数据！');
		return false;
	}
	var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
	showOpenWindow(url + pk,800,600);
}

function nextOrUp(oper){
	var obj = opener;

	var cu_R = obj.curr_row.rowIndex;
	if(oper == "N" && cu_R < obj.$('rsTable').rows.length){//下一页
		obj.rowOnClick(obj.$("rsTable").rows[cu_R+1]);//单击下一行
	}else if(cu_R>1){
		obj.rowOnClick(obj.$("rsTable").rows[cu_R-1]);//单击上一行
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

//学生信息维护页面增加必填字段
function addNotNullFiled(){
	var xxdm = val('xxdm');
	if(xxdm == "12104"){
		//柳州职业技术学院		
		var strNotNull = val('notnull');
		var thisFiled = "mz-zzmm-hkszd-sfzh-sjhm-syd-dzyx-lxdh-yhdm-yhkh";
		var appendFiled = thisFiled;
		
		if(ele('hkshen')){
			//地址信息取代码
			thisFiled = "mz-zzmm-hkshen-sfzh-sjhm-syshen-dzyx-lxdh-yhdm-yhkh";
			appendFiled = "mz-zzmm-hkshen-hkshi-hkxian-sfzh-sjhm-syshen-syshi-syxian-dzyx-lxdh-yhdm-yhkh";
		}
		strNotNull += "-"+appendFiled;		
		
		setVal('notnull',strNotNull);
		appendNotNullFlag(thisFiled,"-");
	}
}

//学生家庭信息维护页面增加必填字段
function addJtxxNotNullFiled(){
	var xxdm = val('xxdm');
	if(xxdm == "12104"){
		//柳州职业技术学院		
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
		alert("修改开始时间晚于结束时间！");
		return false;
	}
	if(xxdm == "13429"){
		//南昌大学科学技术学院
		if(bzrKssj != '' && bzrJssj !='' && bzrKssj<bzrJssj){
			doSaveConf('/xgxt/studentMessage_conf.do?doType=save','nd-xn-xq-kssj-kssjH-kssjM-kssjS-jssj-jssjH-jssjM-jssjS-bzrkssj-bzrkssjH-bzrkssjM-bzrkssjS-bzrjssj-bzrjssjH-bzrjssjM-bzrjssjS');
		}else{
			alert("班主任修改开始时间晚于结束时间！");
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
		alert('异动日期开始时间晚于结束时间！');
		result = false;
	}
	if(ydjzrqks != '' && ydjzrqjs !='' && ydjzrqjs<ydjzrqks){
		alert('异动截止日期开始时间晚于结束时间！');
		result = false;
	}
		
    return result;
}