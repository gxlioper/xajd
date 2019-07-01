var flag = 0;
var addRowNum = 0;
//打印报表
function printReport(url){
	if(url == "qgzx_bb_gwsbb.do?gwdm="){
		if($("sqdw")){
			if(document.getElementById("sqdw").value == "" || document.getElementById("sqdw").value == null){
				alert("请选择申请单位！");
				return false;
			}
		}
		url += document.getElementById("gwdm").value;
		url += "&yrdwdm=";
		url += document.getElementById("sqdw").value;
		url += "&xyrs=";
		url += document.getElementById("xyrs").value;
		url += "&gznr=";
		url += document.getElementById("gznr").value;
	}else{
		if($("xmdm")){
			if(document.getElementById("xmdm").value == "" || document.getElementById("xmdm").value == null){
				alert("请选择要打印的岗位！");
				return false;
			}
		}
		url += document.getElementById("xmdm").value;
		url += "&xh=";
		url += document.getElementById("xh").value;
	}
	window.open(url);
}

//获取用人单位信息
function getYrdwInfo(){
	var yrdw = document.getElementById("sqdw").value;
	if(yrdw != null && yrdw != ""){
		getOtherData.getYrdwInfo(yrdw,TjYrdwInfo);
	}
}

//检测数据是否存在
function chkisDataExist(mustFill){
	var eles = mustFill.split("-");
	var isModi = "";
	for (i = 0; i < eles.length; i++) {
		if($(eles[i])){
			if (document.getElementById(eles[i]).value == "") {
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
	}
	if($("noCommend")){
		alert("你还没有通过"+jQuery("#xbmc").val()+"推荐，暂时不能申请岗位！");
		return false;
	}
	if($("gwExists")){
		if(!confirm("你已经有岗位审核通过，继续申请将有可能失去已通过的岗位！")){
			return false;
		}
	}
	if($("isModi")){
		isModi = document.getElementById("isModi").value;
	}
		
	if($("yhtc")){
		var yhtc = document.getElementById("yhtc").value;		
		if(yhtc != null){
	         	if(yhtc.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("特长不能大于100个字符");
	          		 return false;
	       		 }
			}
	}
	if($("sqly")){
		var sqly = document.getElementById("sqly").value;
		if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("申请理由不能大于500个字符");
	          		 return false;
	       		 }
			}
	}
	if($("bz")){
		var bz = document.getElementById("bz").value;
		if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("备注不能大于500个字符");
	          		 return false;
	       		 }
			}
	}
	if(isModi == "modi"){	
		BatAlert.showTips('正在操作，请稍等...');
		dataDoSaveApply3('/xgxt/applySave.do','xmdm','gwsq');
	}else{
		var xh = $("xh").value;
		var gwdmgwsbsj = $("xmdm").value;	
		var query = xh + "-" + gwdmgwsbsj;
		if($("xxdm").value == "10419"){
			//井冈山大学
			getOtherData.IsDataExist(query,jgsTjIsDataExist);
		}else{
			getOtherData.IsDataExist(query,TjIsDataExist);			
		}
	}
}

//中国地质大学申请检测数据是否存在
function zgdzdx_sqgw_chkisDataExist(mustFill){
	
	var eles = mustFill.split("-");
	var isModi = "";
	for (i = 0; i < eles.length; i++) {
		if($(eles[i])){
			if (document.getElementById(eles[i]).value == "") {
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
	}
	if($("gwExists")){
		if(!confirm("你已经有岗位审核通过，继续申请将有可能失去已通过的岗位！")){
			return false;
		}
	}
	if($("isModi")){
		isModi = document.getElementById("isModi").value;
	}
	
	if(        ($("gwdmgwsbsj1").value==$("gwdmgwsbsj2").value && $("gwdmgwsbsj2").value !="")
			||($("gwdmgwsbsj2").value==$("gwdmgwsbsj3").value && $("gwdmgwsbsj2").value !="")
			||($("gwdmgwsbsj1").value==$("gwdmgwsbsj3").value && $("gwdmgwsbsj3").value !="")
		){
		alert("拟申请岗位有重复或无效！");
		return false;
	}
	if($("yhtc")){
		var yhtc = document.getElementById("yhtc").value;		
		if(yhtc != null){
	         	if(yhtc.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("特长不能大于100个字符");
	          		 return false;
	       		 }
			}
	}
	if($("sqly")){
		var sqly = document.getElementById("sqly").value;
		if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 250){	         
	          		 alert("申请理由不能大于250个字符");
	          		 return false;
	       		 }
			}
	}
	if($("bz")){
		var bz = document.getElementById("bz").value;
		if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("备注不能大于200个字符");
	          		 return false;
	       		 }
			}
	}
	dwr.engine.setAsync(false);
	var jcFlag = true;
	for(i=1;i<4;i++){
		if($("gwdmgwsbsj"+i+"")){
			var gwdmgwsbsj = document.getElementById("gwdmgwsbsj"+i+"").value;
			if(gwdmgwsbsj!=""&&gwdmgwsbsj!=null){
				var xh = document.getElementById("xh").value;
				var query = xh + "-" + gwdmgwsbsj;
				getOtherData.zgdzdx_sqgw_IsDataExist(query,getResult);
				function getResult(data){
					var result = data;
					if(result==1){
						if($('doType') && $('doType').value == 'modi'){
							//修改信息
							alert("此岗位已经审核通过，暂时不可修改!")
						}else{
							alert("拟申请岗位"+i+"申请重复或无效!")
						}
						jcFlag = false;
					}
				}
			}
		}
	}	
	if(jcFlag){
		if(isModi == "modi"){
			dataDoSaveApply3('zgdzdx_hg_SaveOne.do','pkValue','gwsq');
		}else{		
			dataDoSaveApply3('zgdzdx_gwsq_SaveOne.do','pkValue','gwsq');
		}
	} else {
		return false;
	}
	dwr.engine.setAsync(true);
}

//中国地质大学换岗检测数据是否存在
function zgdzdx_chkisDataExist(mustFill){
	var eles = mustFill.split("-");
	var isModi = "";
	for (i = 0; i < eles.length; i++) {
		if($(eles[i])){
			if (document.getElementById(eles[i]).value == "") {
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
	}
	if($("noCommend")){
		alert("你还没有通过"+jQuery("#xbmc").val()+"推荐，暂时不能申请岗位！");
		return false;
	}
	if($("gwExists")){
		if(!confirm("你已经有岗位审核通过，继续申请将有可能失去已通过的岗位！")){
			return false;
		}
	}
	if($("isModi")){
		isModi = document.getElementById("isModi").value;
	}
	
	if($("hgdmhgsj1") != undefined){
		if($("hgdmhgsj1").selectedIndex <= 0){
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		}
	}
	if($("gwdmgwsbsj") != undefined && $("hgdmhgsj1") != undefined && $("hgdmhgsj2")!= undefined && $("hgdmhgsj3")!= undefined ){
		if(($("gwdmgwsbsj").value==$("hgdmhgsj1").value)||($("gwdmgwsbsj").value==$("hgdmhgsj2").value)
		||($("gwdmgwsbsj").value==$("hgdmhgsj3").value)){
			alert("岗位重复或无效！");
			return false;
		}
	}
	
	if($("hgdmhgsj1") != undefined && $("hgdmhgsj2")!= undefined && $("hgdmhgsj3")!= undefined ){
		if((($("hgdmhgsj2").selectedIndex > 0) && ($("hgdmhgsj1").value==$("hgdmhgsj2").value))||
		(($("hgdmhgsj2").selectedIndex > 0) && ($("hgdmhgsj2").value==$("hgdmhgsj3").value))){
			alert("岗位重复或无效！");
			return false;
		}
	}
	
	if($("hgdmhgsj1") != undefined && $("hgdmhgsj2")!= undefined && $("hgdmhgsj3")!= undefined ){
		if((($("hgdmhgsj3").selectedIndex > 0) && ($("hgdmhgsj1").value==$("hgdmhgsj3").value))||
		(($("hgdmhgsj3").selectedIndex > 0) && ($("hgdmhgsj2").value==$("hgdmhgsj3").value))){
			alert("岗位重复或无效！");
			return false;
		}
	}
	
	if($("yhtc")){
		var yhtc = document.getElementById("yhtc").value;		
		if(yhtc != null){
	         	if(yhtc.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("特长不能大于100个字符");
	          		 return false;
	       		 }
			}
	}
	if($("sqly")){
		var sqly = document.getElementById("sqly").value;
		if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 250){	         
	          		 alert("申请理由不能大于250个字符");
	          		 return false;
	       		 }
			}
	}
	if($("bz")){
		var bz = document.getElementById("bz").value;
		if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("备注不能大于60个字符");
	          		 return false;
	       		 }
			}
	}
	
	if(isModi == "modi"){
		dataDoSaveApply3('zgdzdx_hg_SaveOne.do','pkValue','gwsq');
	}else{
		var xh = document.getElementById("xh").value;
		var gwdmgwsbsj = document.getElementById("gwdmgwsbsj").value;
		var query = xh + "-" + gwdmgwsbsj;
		getOtherData.zgdzdx_hg_IsDataExist(query,zgdzdxhg_TjIsDataExist);
	}
}

//中国地质大学辞岗检测数据是否存在
function zgdzdx_cg_chkisDataExist(mustFill){
	var eles = mustFill.split("-");
	var isModi="";
	for (i = 0; i < eles.length; i++) {
		if($(eles[i])){
			if (document.getElementById(eles[i]).value == "") {
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
	}
	
	if($("gwdmgwsbsj")){
		var gwdmgwsbsj = document.getElementById("gwdmgwsbsj").value;
		if(gwdmgwsbsj == null){
			alert("无岗位记录，不能申请辞岗");
			return false;
		}
	}
	
	if($("sqly")){
		var sqly = document.getElementById("sqly").value;
		if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 250){	         
	          		 alert("申请理由不能大于250个字符");
	          		 return false;
	       		 }
			}
	}
	if($("bz")){
		var bz = document.getElementById("bz").value;
		if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("备注不能大于60个字符");
	          		 return false;
	       		 }
			}
	}
	
	var xh = document.getElementById("xh").value;
	var query = xh + "-" + gwdmgwsbsj;
	if($("isModi")){
		isModi = document.getElementById("isModi").value;
	}
	if(isModi == "modi"){
		dataDoSaveApply3('zgdzdx_cg_SaveOne.do','gwdmgwsbsj','gwsq');
	}else{
		dataDoSaveApply3('zgdzdx_cg_Save.do','gwdmgwsbsj-sqly','gwsq');
	}
}

/**中国地质大学用人单位更换学生信息申请检测数据是否存在*/
function zgdzdx_yrdwghxssq_chkisDataExist(){
	if($("gwdmgwsbsj")){
		var gwdmgwsbsj = document.getElementById("gwdmgwsbsj").value;
		if(gwdmgwsbsj == null || gwdmgwsbsj==""){
			alert("请选择岗位！");
			return false;
		}
	}
	if($("sqly")){
		var sqly = document.getElementById("sqly").value;
		if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 250){	         
	          		 alert("申请理由不能大于250个字符");
	          		 return false;
	       		 }
			}
	}
	if($("gwyq")){
		var gwyq = document.getElementById("gwyq").value;
		if(gwyq != null){
	         	if(gwyq.replace(/[^\u0000-\u00ff]/g, "**").length > 250){	         
	          		 alert("岗位要求不能大于250个字符");
	          		 return false;
	       		 }
			}
	}
	if(table1.rows.length==0&&table2.rows.length==0){
		alert("没有填写学生数据,不能提交,输入数据后按'+'按钮保存学生数据！");
		return false;
	}
	dataDoSaveApply3('zgdzdx_yrdwghxssq_Save.do','gwdmgwsbsj-sqly','gwsq');
}

function getGwDetInfo(){
	var xn = document.getElementById("xn").value;
	var nd = document.getElementById("nd").value;
	var xq = document.getElementById("xq").value;
	var yrdwdm = document.getElementById("yrdwdm").value;
	getOtherData.getGwDetInfo(xn,nd,xq,yrdwdm,TjGwDetInfo);
}

//获取岗位总人数
function getZrs(obj){
	document.getElementById("sqzrs").innerText = "0";
	for(i=0; i<document.getElementsByName("gwxx").length; i++){
		for(j=0; j<document.getElementsByName("gwxx").length; j++){
			if(document.getElementById("xyrs"+document.getElementsByName("gwxx")[i].value).value == ""){
				document.getElementById("xyrs"+document.getElementsByName("gwxx")[i].value).value = 0;
			}
		}
		if(identifydata(document.getElementById("xyrs"+document.getElementsByName("gwxx")[i].value))){
			document.getElementById("sqzrs").innerText = parseInt(document.getElementById("sqzrs").innerText) + parseInt(document.getElementById("xyrs"+document.getElementsByName("gwxx")[i].value).value);
		}else{
			break;
		}		
	}
}

//勤工助学数据保存
function qgzxSave(url,mustFill){
	if(checkXnNd("xn", "nd")){
		var xxdm = document.getElementById('xxdm').value;
		var eles = mustFill.split("-");
		for (i = 0; i < eles.length; i++) {
			if (document.getElementById(eles[i]).value == "") {
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
		
		if(xxdm == "10491"){//中国地质大学
			url = "cjff.do?method=reWorkPut";	
		}
		document.forms[0].action = url;
		document.forms[0].submit();
	}
}
////////////////////Ajax提交函数//////////////////////////
function TjYrdwInfo(data){
	if($("lxr")){document.getElementById("lxr").value = data[0]};
	if($("lxdh")){
		var lxdh=document.getElementById("lxdh").value;
		if(data[1]==null||data[1]==""){
			document.getElementById("lxdh").value = ""
		return false;}
		document.getElementById("lxdh").value = data[1]
		
	};
}

function TjIsDataExist(data){
	if(data != null){
		if(data == "1"){
			if(confirm("你已经申请此岗位\n此操作将保存你的最新修改，确定要修改吗？")){
				BatAlert.showTips('正在操作，请稍等...');
				dataDoSaveApply3('/xgxt/applySave.do','xmdm','gwsq');
			}else{
				return false;
			}
		}else if( data== "2"){
			alert("你已经申请此岗位\n且已经通过审核，你已不能再申请此岗位！");
			return false;
		}else if(data == '3'){
			alert('你已经申请过岗位\n且已经通过审核，你已不能再申请岗位！');
			return false;
		}else{
			BatAlert.showTips('正在操作，请稍等...');
			dataDoSaveApply3('/xgxt/applySave.do','xmdm','gwsq');
		}
	}else{
		alert("获取数据失败！");
		return false;
	}
}
/**地质大学换岗申请信息保存*/
function zgdzdxhg_TjIsDataExist(data){
	if(data != null){
		if(data == "0"){
			alert("你目前没有在岗，不能申请换岗！");
			return false;
		}else if(data == '1'){
			dataDoSaveApply3('zgdzdx_hg_Save.do','xmdm','gwsq');
		}
	}else{
		alert("获取数据失败！");
		return false;
	}
}

//用于井冈山大学
function jgsTjIsDataExist(data){
	var xh = document.getElementById("xh").value;
	var pkValue = document.getElementById("xmdm").value;
	var varDjsj  = ""
	if($("djsj")){
		if($("djsj").checked == true){
			//表明学生满足“懂计算机这个条件”
			//document.getElementById("djsj").value = "1";
			varDjsj = "1";
		}else{
			//document.getElementById("djsj").value = "0";
			varDjsj = "0";
		}
	}
	if(data != null){
		if(data == "1"){
			if(confirm("你已经申请此岗位\n此操作将保存你的最新修改，确定要修改吗？")){
				dataDoSaveApply3('/xgxt/applySave.do','xmdm','gwsq');
			}else{
				return false;
			}
		}else if( data== "2"){
			alert("你已经申请此岗位\n且已经通过审核，你不能再申请此岗位！");
			return false;
		}else{
			cqkjFunc.isStudentCondOK(xh,pkValue,varDjsj,function(data){
				if(data == "0"){
					//符合条件,直接提交
					document.getElementById("fuhexx").value = "1";
					dataDoSaveApply3('/xgxt/applySave.do','xmdm','gwsq');
				}else{
					if(confirm("同学不符合的标准有： \n" + data + "\n很有可能不会通过，是否继续申请!")){
						//即使不符合,也将信息提交	
						document.getElementById("fuhexx").value = "0";
						dataDoSaveApply3('/xgxt/applySave.do','xmdm','gwsq');
					}else{
						return;
					}
				}
			});
		}
	}else{
		alert("获取数据失败！");
		return false;
	}
}

function TjGwDetInfo(data){
	if(data!=null){
		document.getElementById("rTable").rows[3].cells[1].innerText = data[0];
		document.getElementById("rTable").rows[3].cells[3].innerText = data[1];
		document.getElementById("rTable").rows[4].cells[1].innerText = data[2];
		document.getElementById("rTable").rows[4].cells[3].innerText = data[4];
		document.getElementById("rTable").rows[5].cells[1].innerText = data[5];
		document.getElementById("rTable").rows[5].cells[3].innerText = data[6];
		document.getElementById("gwxzdm").value = data[3];
	}else{
		alert("该岗位未划拨经费！");
		return false;
	}
}


function commitApply(url,nStr){
	var str = nStr.split("-");
	var value = "";
	var isModi = "";
	if($("xmdm")){
		var xmdm = document.getElementById("xmdm").value;
	}
	for (var i = 0 ;i<str.length; i++){
		value = document.getElementById(str[i]).value;
		if(value == ""){
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		}
	}	
	if($("isModi")){
		isModi = document.getElementById("isModi").value;
	}
	if(isModi == "modi"){
		refreshFrom(url+"&xmdm="+xmdm);
	}else{
		var xh = document.getElementById("xh").value;
		var gwdmgwsbsj = document.getElementById("xmdm").value;
		var query = xh + "-" + gwdmgwsbsj;
		getOtherData.IsDataExist(query,function(data){
		if(data != null){
			if(data == "1"){
				if(confirm("你已经申请此岗位\n此操作将保存你的最新修改，确定要修改吗？")){
					refreshForm(url+"&xmdm="+xmdm);
				}else{
					return false;
				}
			}else if( data== "2"){
				alert("你已经申请此岗位\n且已经通过审核，你不能再申请此岗位！");
				return false;
			}else{			
			    refreshForm(url+"&xmdm="+xmdm);
			}
	}else{
		alert("获取数据失败！");
		return false;
	}
		});
	}
}

function getExcel(){
				document.forms[0].target = "_blank";
				refreshForm('xsqgzx.do?method=expRoster');
				document.forms[0].target = "_self";
			}
			
			function modiBzInfo(){
			   if(curr_row==null){
			   	alert('请选择您要修改的记录！');
			   	return false;
			   }
			   var pkValue = curr_row.cells[0].getElementsByTagName('input')[0].value;
			   showTopWin('xsqgzx.do?method=modiXsgwxxRemark&pkValue='+pkValue,400,300);
			}

function dataDel(url){
		var RowsStr="!!";	
		var realTable = document.getElementById('realTable').value;	
		var mes = "确定要操作所选记录？";
		for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	if(document.getElementsByName("pkV")[i].checked){
	    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    	}
		}
		//document.forms[0].delPk.value = RowsStr;
		
		if (RowsStr=="!!"){
			alert("请选择要批量操作的记录！");
			return false;
		}
		
		if(realTable != null && "gwxxb" == realTable){
			mes = "删除岗位将删除岗位下的所有学生信息，确定删除吗？";
		}
		
		if (!confirm(mes)){
			return false;
		}
		
		url += "&pkString=";
		url += RowsStr;
		url += "&page=";
		url += "page";//跳转到岗位查询页面
		refreshForm(url);
}


function print(url, para,paraName){
	var parameter = para.split('-');
	var paraNames = paraName.split('-');
	for(var i=0; i<parameter.length; i++){
		url += "&" + parameter[i] + "=";
		url += document.getElementById(parameter[i]).value;
	}
	for(var i=0 ; i<paraNames.length; i++){
		url += "&" + paraNames[i] + "=";
		url += document.getElementById(paraNames[i]).options[document.getElementById(paraNames[i]).selectedIndex].text;
	}
	window.open(url);
}

//工资补发
function workPay(url){
	if(curr_row == null){
		alert('请选择要发放的岗位！');
		return false;
	}
	
	var pk = curr_row.getElementsByTagName("input")[0].value;
	var gwmc = curr_row.cells[4].innerText;
	url += "&pk=";
	url += pk;
	url += "&gwmc=";
	url += gwmc;
	showTopWin(url,600,400);
}

//浙江经济职业技术学院打印学生勤工助学考核表
function printKhb(url){
	var xh = document.getElementById('xh').value;
	var xm = document.getElementById('xm').value;
	var gwmc = document.getElementById('gwmc').value;
	var xymc = document.getElementById('xymc').value;
	var bjmc = document.getElementById('bjmc').value;
	var khdj = ""
	var bcbz = document.getElementById('spbcbz').value;
	var gzsj = document.getElementById('ysj').value;
	var gzje = document.getElementById('yje').value;
	var yrdwmc = document.getElementById('yrdwmc').value;
	if($('gzbx')){
		khdj = document.getElementById('gzbx').value;
	}
	
	url += "&xh=";
	url += xh;
	url += "&xm=";
	url += xm;
	url += "&xymc=";
	url += xymc;
	url += "&bjmc=";
	url += bjmc;
	url += "&gwmc=";
	url += gwmc;
	url += "&khdj=";
	url += khdj;
	url += "&bcbz=";
	url += bcbz;
	url += "&gzsj=";
	url += gzsj;
	url += "&gzje=";
	url += gzje;
	url += "&yrdwmc=";
	url += yrdwmc;
	
	window.open(url);
}

//工资发放
function fillMonth(url){
	if(curr_row == null){
		alert('请先选择相应的岗位记录!');
		return false;		
	}
	//var pk = document.forms[0].pk.value;
    var pkValue = curr_row.getElementsByTagName("input")[0].value;
	var gwxzmc = curr_row.cells[6].innerText;
	
	//url += "&pk=";
	//url += pk;
	url += "&pkValue=";
	url += pkValue;
	//url += "&gwxzmc=";
	//url += gwxzmc;
	
	cjff.checkFfsj(pkValue,function(data){
		if(data){
			alert('现在不在发放酬金的时间范围之内，暂时不能发放！');
			return false;
		}else{
			refreshForm(url);
		}
	});	
}

function printYgzbb(){
	var title = document.getElementById('title').innerText;	
	document.getElementById('printFlag').value='print';
	expAppTab('rsT',title+'<br/><br/><br/>','');
}

function saveYgzbb(){
	var flag = document.getElementById('printFlag').value;
	if(flag == null || flag == ""){
		alert('必需先打印才可以保存,您还未打印!');
		return false;
	}else{
		document.getElementById('printFlag').value = '';
		refreshForm('cjff.do?method=saveYgzffInfo');
	}
}

//初始化页面的总金额
function initZje(){
	document.getElementById('zje').value = document.getElementById('zje').innerText;
}


function changeCjje(obj){
	var spbcbz = document.getElementById('spbcbz').value;
	var id = obj.id;	
	var rownum = id.split('@@!!')[1];
	var yje = document.getElementById('cjje' + rownum).value;
	var zje = document.getElementById('zje').value;
	var zgcjje = document.getElementById('zgcjje').value;
	var zggzsj = document.getElementById('zggzsj').value;
	if(isNaN(obj.value)){
		alert('请输入数字！');
	}
	if(yje =="" || yje == null){
		yje = "0";
	}
	var je =(parseFloat(spbcbz)*parseFloat(obj.value));
	var sj = parseFloat(obj.value);
	if(parseFloat(sj)>parseFloat(zggzsj)){
		alert("工作时间超过限制的最高工作时间，请重新填写！");
		document.getElementById("gzsj" + rownum).value="";
		return false;
	}
//	document.getElementById("zje").innerText = parseFloat(zje) - parseFloat(yje) + parseFloat(parseFloat(spbcbz)*parseFloat(obj.value));
//	document.getElementById("zje").value = parseFloat(zje) - parseFloat(yje) + parseFloat(parseFloat(spbcbz)*parseFloat(obj.value));
}

function initNext(){
	var count = document.getElementById('count').value;
	var startPage = document.getElementById('startPage').value;
	var pageSize = document.getElementById('pageSize').value;
	var currentPage = document.getElementById('currentPage').value;
	if(parseFloat(currentPage)*parseFloat(pageSize)<count){
		document.getElementById('nextOne').disabled=false;
	}
	if(parseFloat(currentPage)>1){
		document.getElementById('up').disabled=false;
	}
}

function goNext(){
	refreshForm("cjff.do?method=fillMonthNext");
}

function goUp(){
	refreshForm("cjff.do?method=fillMonthUp");
}

function checkRange(obj,IdNumber){
	var zgcjje = document.getElementById('zgcjje').value;
	var value = obj.value;
	var count = document.getElementById('count').value;
	var zcjje = 0;
	zgcjje = document.getElementById('zgcjje'+IdNumber).value;
	if(parseFloat(value)>parseFloat(zgcjje)){
		alert("酬金金额超过限制的最高金额，请重新填写！");
		obj.value = "";
		return false;
	}
	for(var i=0; i<parseFloat(count);i++){
		var cjje = document.getElementById("cjje" + i).value;
		if(cjje == null || cjje == ''){
			cjje = 0;
		}
		zcjje += parseFloat(cjje);
	}
	document.getElementById("zje").innerText = zcjje;
}

function rePut(){	
	var url = "qgzxLogic.do?method=showReTime";
	if(curr_row == null){
		alert('请选择要发放的岗位！');
		return false;
	}
	
	var pkValue = curr_row.getElementsByTagName("input")[0].value;
	var pk = "gwdm||gwsbsj";
	var gwmc = curr_row.cells[3].innerText;
	
	url += "&pk=";
	url += pk;
	url += "&pkValue=";
	url += pkValue;
	url += "&gwmc=";
	url += gwmc;
	
	cjff.checkFfsj(pkValue,function(data){
		if(data){
			alert('现在不在发放酬金的时间范围之内，暂时不能发放！');
			return false;
		}else{
			refreshForm(url);
		}
	});	
	
}

function saveBfgz(){
	var flag = document.getElementById('printFlag').value;
	if(flag == null || flag == ""){
		alert('必需先打印才可以保存,您还未打印!');
		return false;
	}else{
		document.getElementById('printFlag').value = '';
		refreshForm('cjff.do?method=saveGzbfInfo');
	}
}

function changeBfje(obj){
	var id = obj.id;	
	var rownum = id.split('@@!!')[1];
	var ysj = document.getElementById('yssj' + rownum).value;
	var sj = document.getElementById('gzsj@@!!'+rownum).value;
	var zggzsj = document.getElementById('zggzsj').value;
	
	if(isNaN(obj.value)){
		alert('请输入数字！');
	}
	if(parseFloat(sj)>parseFloat(zggzsj)){
		alert('工作时间超过最高工作时间，请重新填写！');
		document.getElementById('gzsj'+rownum).value='';
		return false;
	}
}

function checkBfgzRange(obj){
	var zgcjje = document.getElementById('zgcjje').value;
	var value = obj.value;
	var id = obj.id;
	var number = id.split('@@')[1];
	var ysje = document.getElementById('yscj'+number).value;
	var zcjje = 0;
	var count = document.getElementById('count').value;
	if(ysje == null || ysje == ""){
		ysje = 0;
	}else{
		ysje = parseFloat(ysje);
	}
	
	var zffje = parseFloat(value)+ysje;
	
	if(zffje>parseFloat(zgcjje)){
		alert("酬金金额超过限制的最高金额，最高还可发放金额"+(parseFloat(zgcjje)-ysje)+"元！");
		obj.value = "";
		obj.focus();
		return false;
	}
	for(var i=0; i<parseFloat(count);i++){
		var cjje = document.getElementById("cjje" +i).value;
		if(cjje == null || cjje == ''){
			cjje = 0;
		}
		zcjje += parseFloat(cjje);
	}
	document.getElementById("zje").innerText = zcjje;
	return true;
}

function goBfgzNext(){
	refreshForm("cjff.do?method=rePayNext");
}

function goBfgzUp(){
	refreshForm("cjff.do?method=rePayUp");
}

function deleteCjff(){
	var url = "cjff.do?method=delCjffInfo";
	var pk = "xh||gwdm||sqsj||nd||yf||fflx";	
	var xxdm = document.getElementById('xxdm').value;	
	var RowsStr="!!";	
	for (i=0; i<document.getElementsByName("pkV").length; i++){
    	if(document.getElementsByName("pkV")[i].checked){
    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
    		if(xxdm=="10491"){//中国地质大学
    			url += "&fflx=";
    			url += document.getElementsByName("pkV")[i].parentNode.parentNode.cells[11].innerText;
    		}
    	}
	}				
	if (RowsStr=="!!"){
		alert("请选择要批量操作的记录！");
		return false;
	}	
	
	url += "&pkString=";
	url += pk;
	url += "&pkValue=";
	url += RowsStr;
	if(confirm('您确定删除选中的记录吗？')){
		refreshForm(url);
	}
}

function gwzjfp(){
	var RowsStr="!!";	
	var url = "qgzxZgdzdx.do?method=showGwfpPage";
	var mes = "确定要操作所选记录？";
	for (i=0; i<document.getElementsByName("pkV").length; i++){
	    if(document.getElementsByName("pkV")[i].checked){
	    	RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    }
	}
				
	if (RowsStr=="!!"){
		alert("请选择要批量操作的记录！");
		return false;
	}
	
	for (i=0; i<document.getElementsByName("pkV").length; i++){
	    if(document.getElementsByName("pkV")[i].checked){
	    	 if(document.getElementsByName('sfyg')[i].value=='有岗'){
		    	alert('已经有岗位的学生不能再次分配岗位，请确认您选择的记录全部是没有岗位的学生！');
		    	return false;		    
	    		}
	    }
	}		
	if (!confirm(mes)){
		return false;
	}
		
	url += "&pkString=";
	url += RowsStr;
	showTopWin(url,500,350);
}


function queryOne() {
	if((curr_row == null) || (curr_row == "")){
		return false;
	}
	var xh = curr_row.getElementsByTagName("input")[0].value;
	var url = "/xgxt/stu_info_details.do?xh="+xh;
	showOpenWindow(url, 800, 600);
}

function qgzxAudiBatch(yesNo){
	var pkString = "!!SplitOneSplit!!";
	var yesNo = yesNo;
	var url = "qgzxZgdzdx.do?method=saveQgzxshBatch";
			
	
	for (i=0; i<document.getElementsByName("pk").length; i++){ //连接字符串
		if(document.getElementsByName("pk")[i].checked){
    		pkString += document.getElementsByName("pkV")[i].value +"!!SplitOneSplit!!";
    	}
	}
	
	if(pkString == "!!SplitOneSplit!!"){
		alert('请选择要操作的记录！');
		return false;
	}
	
	url += "&pkString=";
	url += pkString;
	url += "&yesNo=";
	url += yesNo;
	
	if (confirm("确定要批量操作吗？")) {
		refreshForm(url);				
	}
}

function submitList(){
	var i;
	document.forms[0].mappingItems.value = "";//清空mappingItems中的值
	if (document.forms[0].mappingList.options.length <= 0) {
		alert("导出字段不能为空!");
		return false;
	}
	for (i = 0; i < document.forms[0].mappingList.options.length; i++) {
		document.forms[0].mappingItems.value = document.forms[0].mappingItems.value + "!!SplitOne!!";
		document.forms[0].mappingItems.value = document.forms[0].mappingItems.value + document.forms[0].mappingList.options[i].value;
	}
	document.forms[0].mappingItems.value = DWRUtil.byId("mappingItems").value.substr("!!SplitOne!!".length);//剪切掉字符串前面的"!!SplitOne"
	
	var showConfirmText = "您确定导出所选的字段吗？";
	var showRunningText;
	
    if (confirm(showConfirmText)) {
    	var Items = document.getElementById("mappingItems").value;
    	var whereSql = document.getElementById("whereSql").value;
		window.open('qgzxZgdzdx.do?method=expData&mappingItems=' + Items + "&whereSql=" + whereSql);
	}
    
	return false;
}

function saveGwsq(url, notnull){
	var ele = notnull.split('-');
	var xh = document.getElementById('xh').value;
	//必填字段判断
	for(var i=0; i<ele.length; i++){
		if(document.getElementById(ele[i]).value==''){
			alert('请将带\*号的项目填写完整！');
			return false;
		}	
	}
	//字段长度判断
	var yhtc = document.getElementById('yhtc').value;
	if(yhtc != null){
	     if(yhtc.replace(/[^\u0000-\u00ff]/g, "**").length > 250){	         
	         alert("其它特长不能大于250个字符");
	         return false;
	      }
	}
	//提交
	url += "&xh=";
	url += xh;
	refreshForm(url);	
}

function printGwsq(){
	var url = "qgzxZjcm.do?method=printGwsqb";
	var xh = document.getElementById('xh').value;
	var xmdm = document.getElementById('xmdm').value;
	var lxdh = document.getElementById('lxdh').value;
	var sffcfp = document.getElementById('sffcfp1').checked;
	var jsjsp = document.getElementById('jsjsp').value;
	var yhtc = document.getElementById('yhtc').value;
	var mqqgzxqk = document.getElementById('mqqgzxqk').value;
	
	//是否服从分派	
	if(sffcfp){
		sffcfp = "1";
	}else{
		sffcfp = "0";
	}
	
	url += "&xh=";
	url += xh;
	url += "&xmdm=";
	url += xmdm;
	url += "&lxdh=";
	url += lxdh;
	url += "&sffcfp=";
	url += sffcfp;
	url += "&yhtc=";
	url += yhtc;
	url += "&mqqgzxqk=";
	url += mqqgzxqk;
	url += "&jsjsp=";
	url += jsjsp;
		
	window.open(url);
}

function clickAdd(){
	var number = document.getElementById('number').value;
	var xh = document.createElement("input");	    
	var xm = document.createElement("input");
	var gzsj = document.createElement("input");
	var cjje = document.createElement("input");
	var bz = document.createElement("input");
	var bjdm = document.createElement("input");
	var lxdh =	document.createElement("input");
	
	var tr_length = document.getElementById('tr_length').value;
	var count = document.getElementById('count').value;
	var yrdw = document.getElementById('yrdw').value;
	var gwmc = document.getElementById('gwmc').value;
	var start_length = 0;
	var show_length = 0;
	
	if(tr_length == null || tr_length==""){
		tr_length = 0;
	}
	
	if (count == 0){
		flag = 1;
	}

	if(parseInt(tr_length)==0){
		if(flag == 0){
			start_length = parseInt(count) + parseInt(tr_length);
			show_length = start_length + 1;	
		}else{
			start_length = parseInt(count) + parseInt(tr_length)+1;
			show_length = start_length + parseInt(count);
		}
	}else{
		if(flag == 0){
			start_length = parseInt(tr_length)+1;
			show_length = parseInt(start_length) + 1;	
		}else{
			start_length = parseInt(tr_length)+1;
			show_length = parseInt(start_length) + parseInt(count);	
		}
	}
		    
	xh.id="xh" + start_length;
	xh.name="xh" + start_length;
	xh.value = "";
	xh.style.width="80px";
	
	xm.id = "xm" + start_length;
	xm.name="xm" + start_length;
	xm.value = "";
	xm.style.width="80px";
	xm.disabled = true;
	
	bjdm.id = "bjdm" + start_length;
	bjdm.name="bjdm" + start_length;
	bjdm.value = "";
	bjdm.disabled=true;
	
	lxdh.id = "lxdh" + start_length;
	lxdh.name="lxdh" + start_length;
	lxdh.value = "";
	lxdh.disabled=true;
	
	gzsj.style.width="80px";
	gzsj.name="gzsj" + start_length;
	gzsj.value = "";
	gzsj.id = "gzsj" + start_length;
	
	cjje.style.width="40px";
	cjje.name="cjje" + start_length;
	cjje.value = "";
	cjje.id = "cjje"+start_length;
	
	bjdm.style.width="80px";
	lxdh.style.width="80px";
	bz.style.width="80px";
	bz.name="bz" + start_length;
	
	yrdw = document.getElementById('yrdwmc').value;
	gwmc = document.getElementById('gwdm').value;
	if(show_length <= 25){//最大25行
		addRowNum = addRowNum + 1 ;
		document.getElementById('addRowNum').value = addRowNum;
		DWRUtil.addRows('jj',['dd'],[show_length,yrdw,gwmc,bjdm,xh,xm,gzsj,cjje,lxdh,'',bz]);
		$(xh.id).onchange=function(){
			var bjid= "bjdm" + this.id.replace("xh","");
			var xmid= "xm" + this.id.replace("xh","");
			var lxdhid= "lxdh" + this.id.replace("xh","");
			
			cjff.getXsInfo(this.value,function(data){
			if(data!=null){
				$(bjid).value=data[0];
				$(xmid).value=data[1];
				$(lxdhid).value=data[2];
				
			}
		});
		};
		$(gzsj.id).onchange=function(){
			changeBfjeLs(this);
		}
		$(cjje.id).onchange=function(){
			checkBfgzRangeLs(this);
			checkRange(this);
		}
		document.getElementById('tr_length').value = start_length;	
	}
	
	if(number == null || number==''){
		number=0 ;
	}
	document.getElementById('number').value = parseFloat(number) + 1;
}

function changeBfjeLs(obj){
	var spbcbz = document.getElementById('spbcbz').value;
	var id = obj.id;	
	var rownum = id.replace("gzsj","");
	var sj = document.getElementById('gzsj' + rownum).value;
	var zggzsj = document.getElementById('zggzsj').value;
	if(isNaN(obj.value)){
		alert('请输入数字！');
	}
	if(parseFloat(sj) > parseFloat(zggzsj)){
		alert('工作时间超过最高工作时间，请重新填写！');
		document.getElementById('gzsj' + rownum).value = '';
		return false;
	}
}

function checkBfgzRangeLs(obj){
	var zgcjje = document.getElementById('zgcjje').value;
	var start_length = document.getElementById('start_length').value;
	var value = obj.value;
	var id = obj.id;
	var number = id.replace("cjje","");
	var ysje = 0;	
	var zffje = parseFloat(value)+ysje;
	var count = document.getElementById('count').value;
	var number = document.getElementById('number').value;
	var zje = 0;
	
	if(zffje>parseFloat(zgcjje)){
		alert("酬金金额超过限制的最高金额，最高还可发放金额"+(parseFloat(zgcjje)-ysje)+"元！");
		obj.value = "";
		obj.focus();
		return false;
	}
	for(var i=0; i<parseFloat(count); i++){
		var je = 0;
		if(document.getElementById("cjje" + i)){
			je = document.getElementById("cjje" + i).value;
		}
		if(je == null || je == ''){
			je = 0;
		}
		zje += parseFloat(je);
	}
	if(number == null || number==''){
		number = 0;
	}
	if(start_length == null || start_length==''){
		start_length = 0;
	}
	for(var i=0; i<number; i++){
		var je = 0;
		if(document.getElementById("cjje" + (i + parseFloat(count)))){
			je = document.getElementById("cjje" + (i + parseFloat(count))).value;
		}
		if(je == null || je == ''){
			je = 0;
		}
		zje +=  parseFloat(je);
	}
	document.getElementById("zje").innerText = zje;
	
	return true;
}

function clickRemove(){
	var count = document.getElementById('tr_length').value;
	var number = document.getElementById('number').value;
	if(document.getElementById('jj').rows.length != 0){
		addRowNum = addRowNum - 1;
		document.getElementById('addRowNum').value = addRowNum;
		document.getElementById('jj').deleteRow(document.getElementById('jj').rows.length-1);
		document.getElementById('tr_length').value = parseInt(count)-1;		
	}
	
	if(number == null || number==''){
		number = 0;
	}
	document.getElementById('number').value = number - 1;
}

function goLsgzNext(){
	var page = document.getElementById('currentPage').value;
	document.getElementById('currentPage').value = parseInt(page) + 1;
	refreshForm("cjff.do?method=lsgzff");
}

function goLsgzUp(){
	var page = document.getElementById('currentPage').value;
	document.getElementById('currentPage').value = parseInt(page) - 1;
	refreshForm("cjff.do?method=lsgzff");
}

function saveLsgz(){
	var flag = document.getElementById('printFlag').value;
	if(flag == null || flag == ""){
		alert('必需先打印才可以保存,您还未打印!');
		return false;
	}else{
		showTips('处理数据中，请等待......');
		document.getElementById('printFlag').value = '';
		refreshForm('cjff.do?method=saveGzlsInfo');
	}
}

//学生查询岗位申请信息
function stuViewGwsqxx(url){
	var pkValue = curr_row.cells[0].getElementsByTagName('input')[0].value;
	
	url += "&pkValue=" + pkValue;
	showTopWin(url);
}

function loadGwByYrdw(yrdwdm,gwId,gwxzId){
	var gwxz = "";
	if(gwxzId != "" && document.getElementById(gwxzId)){
		gwxz = document.getElementById(gwxzId).value;
	}
	cqkjFunc.getGwmcByYrdw(yrdwdm,gwxz,function(data){
		DWRUtil.removeAllOptions(gwId);			
		DWRUtil.addOptions(gwId,data,"gwdm","gwdm");
	});
}

function showSelectGwDiv(url){ 
	var dd_html = "";
	dd_html += "<table width='250' class='tbstyle'>";
	dd_html += "	<tr>";
	dd_html += "		<td align='right'>岗位：</td>";
	dd_html += "		<td align='center'>"
	dd_html += "			<select id='selGw' name='selGw' onchange='initZdgwmc()'></select>";
	dd_html += "		</td>";
	dd_html += "	</tr>";
	dd_html += "	<tr>";
	dd_html += "		<td align='right'>指定岗位：</td>";
	dd_html += "		<td>"
	dd_html += "			<textarea id='txtGw' name='txtGw' readonly='true' rows=3></textarea>";
	dd_html += "			<input type='hidden' id='hidGw' name='hidGw'/>"
	dd_html += "		</td>";
	dd_html += "	</tr>";
	dd_html += "	<tr>";
	dd_html += "		<td colspan='2'>";
	dd_html += "			<button class='button2' onclick='auditing(\""+url+"\")'>确定</button>&nbsp;&nbsp;<button class='button2' onclick='closeAdd()'>取消</button>";
	dd_html += "		</td>";
	dd_html += "	</tr>";
	dd_html += "</table>";
	//加载列表数据
	var gwxzdm = $("gwxzdm").value;
	var xxdm = $("xxdm").value;
	var xydm = $("xydm").value;
	var userType = $("userType").value;
	//广州大学
	if("11078" == xxdm && "xy" == userType){
		qgzxZgdzdxFunc.getDWRGwGzdxList(gwxzdm,xydm,function(data){
			DWRUtil.removeAllOptions("selGw");			
			DWRUtil.addOptions("selGw",[{dm:'',mc:''}],"dm","mc");
			DWRUtil.addOptions("selGw",data,"gwdmgwsbsj","gwdm");		
		});
	}else{
		qgzxZgdzdxFunc.getDWRGwList(gwxzdm,function(data){
			DWRUtil.removeAllOptions("selGw");			
			DWRUtil.addOptions("selGw",[{dm:'',mc:''}],"dm","mc");
			DWRUtil.addOptions("selGw",data,"gwdmgwsbsj","gwdm");		
		});
	}
	showDiv(dd_html, 300, 120);
}

function initZdgwmc(){
	var value = val("selGw");
	var text = selText("selGw");
	var xmdm = val("hidGw");
	var zdgw = val("txtGw");
	if(value != null && value != ""){
		if(checkArrayEleRepeat(xmdm+"!!" + value,"!!")){
			alert('该岗位已经指定！');
			return false;
		}else{
			if(zdgw == null || zdgw == ""){
				setVal("txtGw", text);
			}else{
				setVal('txtGw',zdgw + "," + text);
			}
			
			setVal('hidGw',xmdm + "!!" + value);
		}
	}
}

//加载计酬标准
function loadJcbz(value){
	var jcbz = "";
	cqkjFunc.loadJcbz(value,function(data){
		if(data == null || data == 'null' || data == ""){
			jcbz = "";
		}else{
			jcbz = data;
		}
		setVal('jybcbz',jcbz);
	});
}

//加载用人单位下的岗位
function loadYrdwGw(yrdwid,gwmcid,gwxzId){
	var yrdwdm = "";
	var gwxzdm = "";
	var userName = "";
	if(ele(yrdwid)){
		yrdwdm = val(yrdwid);
	}
	if(ele(gwxzId)){
		gwxzdm = val(gwxzId);
	}
	if(ele("userName")){
		userName = val("userName");
	}
	cqkjFunc.getYrdwGwList(userName,yrdwdm,gwxzdm,false,function(data){
		DWRUtil.removeAllOptions(gwmcid);			
		DWRUtil.addOptions(gwmcid,[{gwmc:''}],"gwmc","gwmc");
		DWRUtil.addOptions(gwmcid,data,"gwmc","gwmc");		
	});
}

//加载本人发布的岗位
function loadBrfbGw(gwmcid,xnId,ndId,xqId){
	var gwfbr = "";
	if(ele("gwfbr")){
		gwfbr = ele("gwfbr").value;
	}
	cqkjFunc.getGwmcxxList({xn:val(xnId),nd:val(ndId),xq:val(xqId),userName:val('userName'),gwfbr:gwfbr,shjgFlag:true},function(data){
		DWRUtil.removeAllOptions(gwmcid);			
		DWRUtil.addOptions(gwmcid,[{gwmc:''}],"gwmc","gwmc");
		DWRUtil.addOptions(gwmcid,data,"gwmc","gwmc");		
	});
}

function loadGwmcxx(gwmcid,xnId,ndId,xqId){
	var xxdm = '';
	var text = "";
	var gwxz = "";
	var yrdwdm = "";
	var xn = "";
	var nd = "";
	var xq= "";
	var shFlag = "";
	if ( document.getElementById('xxdm')) {
		xxdm=document.getElementById('xxdm').value
	}
	
	if(ele('gwxz')){
		gwxz = val('gwxz');
	}
	if(ele('gwxzdm')){
		gwxz = val('gwxzdm');
	}
	if(ele('yrdwdm')){
		yrdwdm = val('yrdwdm');
	}
	if(ele('yrdw')){
		yrdwdm = val('yrdw');
	}
	if(ele('xn')){
		xn = val('xn');
	}
	if(ele('nd')){
		nd = val('nd');
	}
	if(ele('xq')){
		xq = val('xq');
	}
	if(ele("shFlag")){
		shFlag = "false";
	}
	if ('11078'== xxdm){
		text = 'yes';
	} else {
		text = 'no';
	}
	
	cqkjFunc.getGwmcxxList({xn:xn,nd:nd,xq:xq,gwxzdm:gwxz,yrdwdm:yrdwdm,userName:val('userName'),shFlag:shFlag},text,function(data){
		DWRUtil.removeAllOptions(gwmcid);			
		DWRUtil.addOptions(gwmcid,[{gwmc:''}],"gwmc","gwmc");
		DWRUtil.addOptions(gwmcid,data,"gwmc","gwmc");		
	});
}


function loadFgwfbrGwmcxx(gwmcid,xnId,ndId,xqId){
	var gwxz = "";
	var yrdwdm = "";
	var xn = "";
	var nd = "";
	var xq= "";
	var shFlag = "";
	if(ele('gwxz')){
		gwxz = val('gwxz');
	}
	if(ele('gwxzdm')){
		gwxz = val('gwxzdm');
	}
	if(ele('yrdwdm')){
		yrdwdm = val('yrdwdm');
	}
	if(ele('yrdw')){
		yrdwdm = val('yrdw');
	}
	if(ele('xn')){
		xn = val('xn');
	}
	if(ele('nd')){
		nd = val('nd');
	}
	if(ele('xq')){
		xq = val('xq');
	}
	if(ele("shFlag")){
		shFlag = "false";
	}
	cqkjFunc.getFgwfbrGwmcxxList({xn:xn,nd:nd,xq:xq,gwxzdm:gwxz,yrdwdm:yrdwdm,userName:val('userName'),gwfbr:val('userName'),shFlag:shFlag},"yes",function(data){
		DWRUtil.removeAllOptions(gwmcid);			
		DWRUtil.addOptions(gwmcid,[{gwmc:''}],"gwmc","gwmc");
		DWRUtil.addOptions(gwmcid,data,"gwmc","gwmc");		
	});
}

/**
 * 判断岗位工作时间输入是否合理
 * */
function checkGwgzsjTime(){
	if(ele('gzkssj') && ele('gzjssj')){
		if((val('gzkssj') != '' && val('gzjssj') != '') 
				&& (val('gzkssj')>val('gzjssj'))){
			alert('工作开始日期不能晚于工作结束日期！');
			return false;
		}
	}
	return true;
}

/**
 * 替换特殊字符
 * */
function replaceStr(obj){
	if(obj){
		var value = obj.value;
		ele(obj.id).value = value.replace('+','＋').replace('#','＃')
		                    .replace('%','％').replace('~','～').replace('!','！')
		                    .replace('$','＄').replace('^','＾').replace('^','＾')
		                    .replace('&','＆').replace('*','＊');
		//ele(obj.id).value = value.replace('','');		
	}
}

/**
 * 判断岗位工作时间输入是否合理
 * */
function checkFfsjTime(){
	if(ele('ffsjks') && ele('ffsjjs')){
		if((val('ffsjks') != '' && val('ffsjjs') != '') 
				&& (val('ffsjks').replace('-','')>val('ffsjjs').replace('-',''))){
			alert('发放开始时间不能晚于结束时间！');
			return false;
		}
	}
	return true;
}


function printGzhzb(){
	if(val('xn') == ''){
		alert('请选择学年！');
		return false;
	}
	if(val('xq') == ''){
		alert('请选择学期！');
		return false;
	}
	wjcfDataExport('qgzxcjff.do?method=printCjffhzb');
}

function printYrqkb(){
	wjcfDataExport('qgzxGwgl.do?method=printYrdwyrqkb');
}