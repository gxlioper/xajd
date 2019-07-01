function modiOneConf(){
	if(chkCurrRow()){
		if(curr_row.getElementsByTagName("input") == null){
			alert("系统错误,未取到数据！\n请刷新页面后重试！");
		}else{
			var bmdm = curr_row.getElementsByTagName("input")[0].value;
			var src = "/xgxt/modiConf.do?doType=one&bmdm=" + bmdm;
			var w = 500;
			var h = 370;
			showTopWin(src,w,h,'a');
		}
	}
}
function modiAConf(){
	if(chkCurrRow()){
		if(curr_row.getElementsByTagName("input") === null){
			alert("系统错误,未取到数据！\n请刷新页面后重试！");
		}else{
			var bmdm = curr_row.getElementsByTagName("input")[0].value;
			var src = "/xgxt/modiZzConf.do?doType=one&bmdm=" + bmdm;
			var w = 500;
			var h = 370;
			showTopWin(src,w,h,'a');
		}
	}
}
function showXyConfInfo(){
	var oldRsxz = $("rsTable");
	var totRsxz = 0;
	var i = 0;
	for(i = 2;i < oldRsxz.rows.length - 1;i++){
		totRsxz += parseFloat(oldRsxz.rows[i].cells[1].innerText);
	}
	var maxRsxz = parseFloat(oldRsxz.rows[i].cells[1].innerText);
	var infoMsg = "<div align='left'><br>";
	infoMsg += "<font color='red'>此表的最后一行为学校设置的限定条件";
	if(!(window.dialogArguments) || (P$("userType") && P$("userType").value == "xy")){
		infoMsg += "，所有修改不得超出限定范围";
	}
	infoMsg += "。</font><br>";
	infoMsg += "<font color='blue'>当前设置信息：共有困难生指标";
	infoMsg += "<font color='red'>" + maxRsxz + "</font>，";
	infoMsg += "已经分配";
	infoMsg += "<font color='red'>" + totRsxz + "</font>，";
	infoMsg += "空余指标";
	infoMsg += "<font color='red'>" + (maxRsxz - totRsxz) + "</font>。</font></div>";
	document.write(infoMsg);
}
function saveConf(){
	/**检测人数限制*/
	if($("userType").value == "xy"){
		var sType = $("bmdm").value;//判断是否为批量设置的依据
		var xyrs = parseFloat($("xyrs").value);//获取学院人数
		var rsxz = parseFloat($("rsxz").value);//获取人数限制
		var oldRsxz = P$("rsTable");//获取父页面表
		var totRsxz = 0;
		var i = 0;
		for(i = 2;i < oldRsxz.rows.length - 1;i++){
			totRsxz += parseFloat(oldRsxz.rows[i].cells[1].innerText);
		}//汇总父页面各子单位‘人数限制’总和
		var maxRsxz = parseFloat(oldRsxz.rows[i].cells[1].innerText);//获取人数限制
		var blRs = xyrs*rsxz/100;
		if(sType == "all" && blRs > maxRsxz){
			alert("修改后的总人数（" + blRs + "）将超过限制（" + maxRsxz + "），请检测输入！");
			$("rsxz").select();
			$("rsxz").focus();
			return false;
		}else if(sType != "all"){
			totRsxz -= parseFloat(window.dialogArguments.curr_row.cells[1].innerText);
			totRsxz += rsxz;
			if(totRsxz > maxRsxz){
				alert("修改后的总人数（" + totRsxz + "）将超过限制（" + maxRsxz + "），请检测输入！");
				$("rsxz").select();
				$("rsxz").focus();
				return false;
			}
		}
	}
	/**检测时间限制*/
	unionDateTime("kssqsja");
	unionDateTime("jssqsja");
	unionDateTime("kssqsjb");
	unionDateTime("jssqsjb");
	unionDateTime("kssqsjc");
	unionDateTime("jssqsjc");
	unionDateTime("kssqsjd");
	unionDateTime("jssqsjd");
	unionDateTime("kssqsje");
	unionDateTime("jssqsje");
	unionDateTime("kssqsjf");
	unionDateTime("jssqsjf");
	unionDateTime("kssqsjg");
	unionDateTime("jssqsjg");
	unionDateTime("kssqsjh");
	unionDateTime("jssqsjh");
	var newKsA = $("kssqsja").value;
	var newJsA = $("jssqsja").value;
	var newKsB = $("kssqsjb").value;
	var newJsB = $("jssqsjb").value;
	var newKsC = $("kssqsjc").value;
	var newJsC = $("jssqsjc").value;
	var newKsD = $("kssqsjd").value;
	var newJsD = $("jssqsjd").value;
	var newKsE = $("kssqsje").value;
	var newJsE = $("jssqsje").value;
	var newKsF = $("kssqsjf").value;
	var newJsF = $("jssqsjf").value;
	var newKsG = $("kssqsjg").value;
	var newJsG = $("jssqsjg").value;
	var newKsH = $("kssqsjh").value;
	var newJsH = $("jssqsjh").value;
	if($("userType").value == "xy"){
		var maxKsA = P$("maxKsA").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsA = P$("maxJsA").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsB = P$("maxKsB").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsB = P$("maxJsB").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsC = P$("maxKsC").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsC = P$("maxJsC").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsD = P$("maxKsD").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsD = P$("maxJsD").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsE = P$("maxKsE").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsE = P$("maxJsE").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsF = P$("maxKsF").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsF = P$("maxJsF").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsG = P$("maxKsG").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsG = P$("maxJsG").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsH = P$("maxKsH").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsH = P$("maxJsH").innerHTML.replace(/(-|:|<br>)/gi,"");
		if(newKsA < maxKsA){
			alert("您所设置的“困难生申请开始时间”早于学校规定的时间，请重新设置！");
			return false;
		}
		if(newKsB < maxKsB){
			alert("您所设置的“国家助学贷款申请开始时间”早于学校规定的时间，请重新设置！");
			return false;
		}
		if(newKsC < maxKsC){
			alert("您所设置的“国家奖学金申请开始时间”早于学校规定的时间，请重新设置！");
			return false;
		}
		if(newKsD < maxKsD){
			alert("您所设置的“国家助学金申请开始时间”早于学校规定的时间，请重新设置！");
			return false;
		}
		if(newKsE < maxKsE){
			alert("您所设置的“省政府奖学金申请开始时间”早于学校规定的时间，请重新设置！");
			return false;
		}
		if(newKsF < maxKsF){
			alert("您所设置的“省政府助学金申请开始时间”早于学校规定的时间，请重新设置！");
			return false;
		}
		if(newKsG < maxKsG){
			alert("您所设置的“助学贷款国家助学金申请开始时间”早于学校规定的时间，请重新设置！");
			return false;
		}
		if(newKsH < maxKsH){
			alert("您所设置的“校内贷学金申请申请开始时间”早于学校规定的时间，请重新设置！");
			return false;
		}
		if(newJsA > maxJsA){
			alert("您所设置的“困难生申请结束时间”晚于学校规定的时间，请重新设置！");
			return false;
		}
		if(newJsB > maxJsB){
			alert("您所设置的“国家助学贷款申请结束时间”晚于学校规定的时间，请重新设置！");
			return false;
		}
		if(newJsC > maxJsC){
			alert("您所设置的“国家奖学金申请结束时间”晚于学校规定的时间，请重新设置！");
			return false;
		}
		if(newJsD > maxJsD){
			alert("您所设置的“国家助学金申请结束时间”晚于学校规定的时间，请重新设置！");
			return false;
		}
		if(newJsE > maxJsE){
			alert("您所设置的“省政府奖学金申请结束时间”晚于学校规定的时间，请重新设置！");
			return false;
		}
		if(newJsF > maxJsF){
			alert("您所设置的“省政府助学金申请结束时间”晚于学校规定的时间，请重新设置！");
			return false;
		}
		if(newJsG > maxJsG){
			alert("您所设置的“助学贷款国家助学金申请结束时间”晚于学校规定的时间，请重新设置！");
			return false;
		}
		if(newJsH > maxJsH){
			alert("您所设置的“校内贷学金申请申请结束时间”晚于学校规定的时间，请重新设置！");
			return false;
		}
	}
	if(newKsA > newJsA){
		alert("困难生申请开始时间晚于结束时间，请重新设置！");
		return false;
	}
	if(newKsB > newJsB){
		alert("国家助学贷款申请开始时间晚于结束时间，请重新设置！");
		return false;
	}
	if(newKsC > newJsC){
		alert("国家奖学金申请开始时间晚于结束时间，请重新设置！");
		return false;
	}
	if(newKsD > newJsD){
		alert("国家助学金申请开始时间晚于结束时间，请重新设置！");
		return false;
	}
	if(newKsE > newJsE){
		alert("省政府奖学金申请开始时间晚于结束时间，请重新设置！");
		return false;
	}
	if(newKsF > newJsF){
		alert("省政府助学金申请开始时间晚于结束时间，请重新设置！");
		return false;
	}
	if(newKsG > newJsG){
		alert("助学贷款国家助学金申请开始时间晚于结束时间，请重新设置！");
		return false;
	}
	if(newKsH > newJsH){
		alert("校内贷学金申请开始时间晚于结束时间，请重新设置！");
		return false;
	}
	refreshForm("/xgxt/modiConf.do?act=save");
}
function saveZzConf(){
	/**检测人数限制*/
	if($("userType").value == "xy"){
		var sType = $("bmdm").value;//判断是否为批量设置的依据
		var xyrs = parseFloat($("xyrs").value);//获取学院人数
		var rsxz = parseFloat($("rsxz").value);//获取人数限制
		var oldRsxz = P$("rsTable");//获取父页面表
		var totRsxz = 0;
		var i = 0;
		for(i = 2;i < oldRsxz.rows.length - 1;i++){
			totRsxz += parseFloat(oldRsxz.rows[i].cells[1].innerText);
		}//汇总父页面各子单位‘人数限制’总和
		var maxRsxz = parseFloat(oldRsxz.rows[i].cells[1].innerText);//获取人数限制
		var blRs = xyrs*rsxz/100;
		if(sType == "all" && blRs > maxRsxz){
			alert("修改后的总人数（" + blRs + "）将超过限制（" + maxRsxz + "），请检测输入！");
			$("rsxz").select();
			$("rsxz").focus();
			return false;
		}else if(sType != "all"){
			totRsxz -= parseFloat(window.dialogArguments.curr_row.cells[1].innerText);
			totRsxz += rsxz;
			if(totRsxz > maxRsxz){
				alert("修改后的总人数（" + totRsxz + "）将超过限制（" + maxRsxz + "），请检测输入！");
				$("rsxz").select();
				$("rsxz").focus();
				return false;
			}
		}
	}
	/**检测时间限制*/
	unionDateTime("kssqsja");
	unionDateTime("jssqsja");
	unionDateTime("kssqsjb");
	unionDateTime("jssqsjb");
	unionDateTime("kssqsjc");
	unionDateTime("jssqsjc");
	unionDateTime("kssqsjd");
	unionDateTime("jssqsjd");
	unionDateTime("kssqsje");
	unionDateTime("jssqsje");
	unionDateTime("kssqsjf");
	unionDateTime("jssqsjf");
	unionDateTime("kssqsjg");
	unionDateTime("jssqsjg");
	unionDateTime("kssqsjh");
	unionDateTime("jssqsjh");
	var newKsA = $("kssqsja").value;
	var newJsA = $("jssqsja").value;
	var newKsB = $("kssqsjb").value;
	var newJsB = $("jssqsjb").value;
	var newKsC = $("kssqsjc").value;
	var newJsC = $("jssqsjc").value;
	var newKsD = $("kssqsjd").value;
	var newJsD = $("jssqsjd").value;
	var newKsE = $("kssqsje").value;
	var newJsE = $("jssqsje").value;
	var newKsF = $("kssqsjf").value;
	var newJsF = $("jssqsjf").value;
	var newKsG = $("kssqsjg").value;
	var newJsG = $("jssqsjg").value;
	var newKsH = $("kssqsjh").value;
	var newJsH = $("jssqsjh").value;
	if($("userType").value == "xy"){
		var maxKsA = P$("maxKsA").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsA = P$("maxJsA").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsB = P$("maxKsB").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsB = P$("maxJsB").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsC = P$("maxKsC").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsC = P$("maxJsC").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsD = P$("maxKsD").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsD = P$("maxJsD").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsE = P$("maxKsE").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsE = P$("maxJsE").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsF = P$("maxKsF").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsF = P$("maxJsF").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsG = P$("maxKsG").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsG = P$("maxJsG").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsH = P$("maxKsH").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsH = P$("maxJsH").innerHTML.replace(/(-|:|<br>)/gi,"");
		if(newKsA < maxKsA){
			alert("您所设置的“心平基金贷款申请开始时间”早于学校规定的时间，请重新设置！");
			return false;
		}
		if(newKsB < maxKsB){
			alert("您所设置的“学费补助申请开始时间”早于学校规定的时间，请重新设置！");
			return false;
		}
		if(newKsC < maxKsC){
			alert("您所设置的“困难补助申请开始时间”早于学校规定的时间，请重新设置！");
			return false;
		}
		if(newKsD < maxKsD){
			alert("您所设置的“外设助学金申请开始时间”早于学校规定的时间，请重新设置！");
			return false;
		}
		if(newKsE < maxKsE){
			alert("您所设置的“龙元建设助学金申请开始时间”早于学校规定的时间，请重新设置！");
			return false;
		}
		if(newKsF < maxKsF){
			alert("您所设置的“绿色通道申请开始时间”早于学校规定的时间，请重新设置！");
			return false;
		}
		if(newKsG < maxKsG){
			alert("您所设置的“学生学费减免申请开始时间”早于学校规定的时间，请重新设置！");
			return false;
		}
		if(newKsH < maxKsH){
			alert("您所设置的“关爱基金申请开始时间”早于学校规定的时间，请重新设置！");
			return false;
		}
		if(newJsA > maxJsA){
			alert("您所设置的“心平基金贷款申请结束时间”晚于学校规定的时间，请重新设置！");
			return false;
		}
		if(newJsB > maxJsB){
			alert("您所设置的“学费补助申请结束时间”晚于学校规定的时间，请重新设置！");
			return false;
		}
		if(newJsC > maxJsC){
			alert("您所设置的“困难补助申请结束时间”晚于学校规定的时间，请重新设置！");
			return false;
		}
		if(newJsD > maxJsD){
			alert("您所设置的“外设补助申请结束时间”晚于学校规定的时间，请重新设置！");
			return false;
		}
		if(newJsE > maxJsE){
			alert("您所设置的“龙元建设助学金申请结束时间”晚于学校规定的时间，请重新设置！");
			return false;
		}
		if(newJsF > maxJsF){
			alert("您所设置的“绿色通道申请结束时间”晚于学校规定的时间，请重新设置！");
			return false;
		}
		if(newJsG > maxJsG){
			alert("您所设置的“学生学费减免申请结束时间”晚于学校规定的时间，请重新设置！");
			return false;
		}
		if(newJsH > maxJsH){
			alert("您所设置的“关爱基金申请结束时间”晚于学校规定的时间，请重新设置！");
			return false;
		}
	}
	if(newKsA > newJsA){
		alert("心平基金贷款申请开始时间晚于结束时间，请重新设置！");
		return false;
	}
	if(newKsB > newJsB){
		alert("学费补助申请开始时间晚于结束时间，请重新设置！");
		return false;
	}
	if(newKsC > newJsC){
		alert("困难补助申请开始时间晚于结束时间，请重新设置！");
		return false;
	}
	if(newKsD > newJsD){
		alert("外设补助申请开始时间晚于结束时间，请重新设置！");
		return false;
	}
	if(newKsE > newJsE){
		alert("龙元建设助学金申请开始时间晚于结束时间，请重新设置！");
		return false;
	}
	if(newKsF > newJsF){
		alert("绿色通道申请开始时间晚于结束时间，请重新设置！");
		return false;
	}
	if(newKsG > newJsG){
		alert("学生学费减免申请开始时间晚于结束时间，请重新设置！");
		return false;
	}
	if(newKsH > newJsH){
		alert("关爱基金申请开始时间晚于结束时间，请重新设置！");
		return false;
	}
	refreshForm("/xgxt/modiZzConf.do?act=save");
}
function chkModiType(){
	var sType = $("bmdm").value;
	var sXyrs = $("xyrs").value;
	var tmpNum = $("rsTable").rows[2].cells[1].innerText;
	if(sType == "all"){
		$("rsTable").rows[2].cells[0].innerText = "困难生人数限制（比例）:";
		$("rsTable").rows[2].cells[1].innerHTML = "<input onblur=\"numFormatChk(this,0,100)\" type=\"text\" name=\"rsxz\" id=\"rsxz1\" value=\"" +tmpNum + "\" />%";
	}else{
		$("rsTable").rows[2].cells[0].innerText = "困难生人数限制（绝对数）:";
		$("rsTable").rows[2].cells[1].innerHTML = "<input onblur=\"numFormatChk(this,0," + sXyrs + ")\" type=\"text\" name=\"rsxz\" id=\"rsxz1\" value=\"" +tmpNum + "\" onkeyup=\"value=value.replace(/[^\\d]/g,'')\" onbeforepaste=\"clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))\" />人";
	}
}
function viewXyConf(){
	var src = "/xgxt/assisConf.do?xyToView=";
	if(chkCurrRow()){
		src += curr_row.getElementsByTagName("input")[0].value;
		showTopWin(src,760,400,1);
	}
}
function viewXyConfig(){
	var src = "/xgxt/zzConfig.do?xyToView=";
	if(chkCurrRow()){
		src += curr_row.getElementsByTagName("input")[0].value;
		showTopWin(src,760,400,1);
	}
}

function changePage(defaultId){//切换页面
	var title = defaultId.id.substr(0,defaultId.id.length-1);
	var titleName,anotherName;
	if (title == "gjjxj"){
		titleName = "gjjxj";
		document.getElementById("titName").value = "gjjxj";				
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "gjzxj";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
	} else if (title == "gjzxj") {
		titleName = "gjzxj";
		document.getElementById("titName").value = "gjzxj";		
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "gjjxj";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		//document.forms[0].action = "";
		//document.forms[0].submit();
	}else if (title == "bzs"){
		titleName = "bzs";
		document.getElementById("titName").value = "bzs";				
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "yjs";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
	} else if (title == "yjs") {
	    document.forms[0].action = url;
	    document.forms[0].submit();
		titleName = "yjs";
		document.getElementById("titName").value = "yjs";		
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "bzs";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
	}else if (title == "szfjxj"){
		titleName = "szfjxj";
		document.getElementById("titName").value = "szfjxj";
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "szfzxj";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		//document.forms[0].action = "";
		//document.forms[0].submit();
	} else if (title == "szfzxj"){
		titleName = "szfzxj";
		document.getElementById("titName").value = "szfzxj";
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "szfjxj";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		//document.forms[0].action = "";
		//document.forms[0].submit();
	}else if (title == "zxdksq"){
		titleName = "zxdksq";
		document.getElementById("titName").value = "zxdksq";
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "txsq";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		//document.forms[0].action = "";
		//document.forms[0].submit();
	} else if (title == "txsq"){
		titleName = "txsq";
		document.getElementById("titName").value = "txsq";
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "zxdksq";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		//document.forms[0].action = "";
		//document.forms[0].submit();
	} else if (title == "xscfb"){
		titleName = "xscfb";
		document.getElementById("titName").value = "xscfb";
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "xsjlb";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		document.forms[0].action = "/xgxt/gydykp.do";
		document.forms[0].submit();
	} else {
		titleName = "xsjlb";
		document.getElementById("titName").value = "xsjlb";
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "xscfb";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		document.forms[0].action = "/xgxt/gydykp.do";
		document.forms[0].submit();
	}
}

function xssqgjjxjzxj(kind){
	var xh = document.getElementById("xh").value;
	if ((xh != "")){	
	if (kind == "txsq"){
		document.forms[0].action = "/xgxt/txsq.do?jxjlbType=txsq&doType=add&titName=" + kind;
		document.forms[0].submit();
		}
 	}else { 
 		alert("学号不得为空！");
 		return false;
 	}
 }
 	
function toPrintOut(titName){//输出相应的打印页面
	if(titName == "shszfzxjxjsq" ){
		document.forms[0].action = "/xgxt/shszfzxjxjsqb.do";
		document.forms[0].submit();
	} else if(titName == "txsq" ){
		document.forms[0].action = "/xgxt/txsqb.do";
		document.forms[0].submit();
	} else if(titName == "hkxy" ){
		document.forms[0].action = "/xgxt/hkxysqb.do";
		document.forms[0].submit();
	} else if(titName == "zlqrs" ){
		document.forms[0].action = "/xgxt/zlqrs.do";
		document.forms[0].submit();
	}
}

function knbzChange(Obj){
	var url = "/xgxt/knbz.do?bzlb=";
	if(Obj.id == "lsknbz"){
		Obj.checked = true;
		url += "lsknbz";
	} else {
		Obj.checked = true;
		url += "zxbz";
	}
	document.forms[0].action = url;
	document.forms[0].submit();
}

function knbzChange2(obj){
	var url = "/xgxt/knbz.do?bzlb=";
	if(obj.id == "a"){
		document.getElementById("lsknbz").checked = true;
		url += "lsknbz";
	} else {
		document.getElementById("zxbz").checked = true;
		url += "zxbz";
	}
	document.forms[0].action = url;
	document.forms[0].submit();
}

function loadPage(){//国家/省政府页签页面加载
	var tab = document.getElementById("titName").value;
	document.getElementById(tab+"l").className = "xxk_on_l";
	document.getElementById(tab+"m").className = "xxk_on_m";
	document.getElementById(tab+"r").className = "xxk_on_r";
}
function knbzLoadPage(){//困难补助页面加载
	var bzlb = document.getElementById("bzlb").value;
	document.getElementById(bzlb).checked = true;
	if(bzlb == "lsknbz"){
		//alert(document.forms[0].zxbzdm);
		document.forms[0].zxbzdm.disabled = true;
	}
}
function changePage1(){
	document.forms[0].action = "assis_result_t.do";
	document.forms[0].submit();
}
function printThePage(){
	var newwin = window.open(location.href,"_blank","");
}
function checkInputData(obj){
	obj.value = obj.value.replace(/[^\d]/g,'');
}