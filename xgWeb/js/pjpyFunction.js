function actFun(dotype){
	document.forms[0].action += '?act='+dotype;
	document.forms[0].submit();
}
function checkValue(){
	var xspdkssj = parseInt(document.forms[0].xspdkssj.value);
	var xspdjssj = parseInt(document.forms[0].xspdjssj.value);
	var jspdkssj = parseInt(document.forms[0].jspdkssj.value);
	var jspdjssj = parseInt(document.forms[0].jspdjssj.value);
	var message = "";
	if(xspdjssj<xspdkssj){
		message += "\n学生评定的开始时间晚于结束时间";
	}
	if(jspdkssj>jspdjssj){
		message += "\n教师评定的开始时间晚于结束时间";
	}
	
	if(message.length != 0) {
		alert(message);
		return false;
	}	
	
}

function zhcpZbdxAutoCalc(url){
	if(confirm("自动计算将清空当前学年的综合素质测评表中的信息, 自动计算将耗费较长时间，确定要开始自动计算吗？")){
			var dd_html = "";
			dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>正在自动计算，请稍候......<br><br>";
			dd_html += "<span class='roll_tip'></span>";
			dd_html += "</td></tr></table>";
			showDiv(dd_html, 300, 120);
			alert("点击\"确定\"后开始计算！");
			refreshForm(url);
			return true;
		}else{
			return false;
		}
}


//中北大学学生评议页面重置输入数据
function resetResult(){
	var inputResultArr = document.getElementsByName("xspdcj");
	for(var i=0;i<inputResultArr.length;i++){
		inputResultArr[i].value = "";
	}
}

//中北大学学生评议页面评议结果提交
function submitResult(){
	var inputArr = document.getElementsByName("xspdcj");
	for(var i=0;i<inputArr.length;i++){
		if(inputArr[i].value.trim()==""){
			alert("请输入所有学生的成绩！！");
			return false;
		}
	}
	document.forms[0].action = document.forms[0].action+"?act=save";
	document.forms[0].submit();
}

//中北大学教师评议输入成绩提交
function jsSubmitResult(){
	var inputArr = document.getElementsByName("jspdcj");
	for(var i=0;i<inputArr.length;i++){
		if(inputArr[i].value.trim()==""){
			alert("请输入所有学生的成绩！！");
			return false;
		}
	}
	document.forms[0].action = document.forms[0].action+"?act=save";
	document.forms[0].submit();
}

//中北大学教师评议输入成绩有效性验证及中北大学pjpy_zbdx_tyrwcxsjcjwh功能部分成绩验证
function checkCj(obj){
	if(parseInt(obj.value)>100){
		alert("输入成绩应为百分制！\n请检查后重新输入！");
		obj.value = "";
		return false;
	}
}

//中北大学学生评议页面重置输入数据
function jsResetResult(){
	var inputResultArr = document.getElementsByName("jspdcj");
	for(var i=0;i<inputResultArr.length;i++){
		inputResultArr[i].value = "";
	}
}
//中北大学维护体育、人文及创新
function checkchoice(){
	if(document.getElementById("dataTable").value==""){
		alert("请先选择相应的成绩查询表！");
		return false;
	}
}
//中北大学pjpy_zbdx_tyrwcxsjcjwh
function onLoadfun(){
	var table = document.getElementById("tableName").value;
	if(table!=null){
		document.getElementById("dataTable").value=table;
	}
}
//中北大学pjpy_zbdx_tyrwcxsjcjwh体育、人文及创新保存成绩
function resultSubmit(){
	var realTable = document.forms[0].realTable.value;
	var arr = new Array();
	if(realTable != null){
		switch(realTable){
			case "tycjb": 	arr = document.getElementsByName("stszcpcj"); break;
			case "rwszcjb":		arr = document.getElementsByName("rwcj"); break;
			case "cxysjcjb":    arr = document.getElementsByName("cxrjcj"); break;
		}
		for(var i=0;i<arr.length;i++){
			if(arr[i].value.trim()==""){
				alert("请检查输入所有的成绩！");
				return false;
			}
		}
		
	}
	document.forms[0].action="pjpy_zbdx_tyrwcxsjcjwh.do?act=save";
	document.forms[0].submit();
}

//中北大学pjpy_zbdx_tyrwcxsjcjwh体育、人文及创新重置
function whResetResult(){
	var realTable = document.forms[0].realTable.value;
	var arr = new Array();
	if(realTable != null){
		switch(realTable){
			case "tycjb": 	arr = document.getElementsByName("stszcpcj"); break;
			case "rwszcjb":		arr = document.getElementsByName("rwcj"); break;
			case "cxysjcjb":    arr = document.getElementsByName("cxrjcj"); break;
		}
	}
	for(var i=0;i<arr.length;i++){
		arr[i].value="";
	}
}
//中北大学pjpy_zbdx_tyrwcxsjcjwh体育、人文及创新成绩导出
function dataZbdxExport() {
	document.forms[0].action = "/xgxt/exptoexcel.do";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}

//以下北京林业用删除
function pjpybjlyDelete(url){
	if(curr_row == null || typeof(curr_row) == undefined){
		alert("请选择要删除的记录！！");
		return false;
	}
	document.forms[0].action=url+'?pk='+curr_row.cells[0].getElementsByTagName("input")[0].value;
	document.forms[0].submit();
}

//北京林业单个显示
function pjpyszbjly(url){
	if((curr_row == null)|| (typeof(curr_row) == undefined)){
		alert("请选择要修改的记录！！");
		return false;
	}
	showTopWin(url+'?pk='+curr_row.cells[0].getElementsByTagName("input")[0].value,600,500);
}

function pjpyTableDoSave(url,pkFields){
	var eles = pkFields.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i])){
			if(document.getElementById(eles[i]).value == "") {
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
	}
	document.forms[0].action = url;
	document.forms[0].submit();
	alert("保存成功！");
	window.dialogArguments.document.getElementById("search_go").click();
	close();
}

function pjpybfbTableDoSave(url,pkFields,bfbpd){
	var eles = pkFields.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i])){
			if(document.getElementById(eles[i]).value == "") {
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
	}
	var bfbeles = bfbpd.split("-");
	var bfbzz=0;
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(bfbeles[i])){
			for (i = 0; i < bfbeles.length; i++) {
			var tmp=parseInt(document.getElementById(bfbeles[i]).value);
			bfbzz=bfbzz+tmp;
			}
		}
	}
	if(bfbzz!=100) {
				alert("百分比和必须为100");
				return false;
	}
	var pjksrq=parseInt(document.getElementById("pjksrq").value);
	var pjjsrq=parseInt(document.getElementById("pjjsrq").value);
	var xxpjksrq=parseInt(document.getElementById("xxpjksrq").value);
	var xxpjjsrq=parseInt(document.getElementById("xxpjjsrq").value);
	if(pjksrq<xxpjksrq){
		alert("日期必须符合条件");
		return false;
	}
	if(pjjsrq>xxpjjsrq){
		alert("日期必须符合条件");
		return false;
	}
	document.forms[0].action = url;
	document.forms[0].submit();
	alert("保存成功！");
	window.dialogArguments.document.getElementById("search_go").click();
	close();
}

function bllydelPriseCondi() {
	if (curr_row == null) {
		alert("请选择要删除的条件！");
		return fasle;
	} else {
		var url = "/xgxt/pjpy_bjly_tj.do?pkVal=";
		url += curr_row.cells[1].innerText + curr_row.cells[2].innerText;
		document.forms[0].go.value = "del";
		refreshForm(url);
	}
}

function delAllPriseCondi() {
	if(document.getElementById("jxjdm").value == "") {
				alert("请选择要清空的奖学金!");
				return false;
	}
		var url = "/xgxt/pjpy_bjly_tj.do?delALL=1";
		document.forms[0].go.value = "delAll";
		refreshForm(url);
}
//中北大学加分维护页面的操作
function addCreditDataSave(type){
	
}
//中北大学加分页面默认值检测
function dataCheckExist(obj){
	if(obj.value=='' || obj.value.trim()=='' ||obj.value==null){
		obj.value = "输入所加分值";
	} else if(obj.value.match(/[^0-9]/g)){
		onlyNum(obj);
		alert("只能输入数字！请检查后重新输入！");
		obj.value = "输入所加分值";
	}
}
//中北大学加分页面初始值
function dataCheckInit(obj){
	if(obj.value=="输入所加分值"){
		obj.value='';
	}
}
//中北大学加分页面加分框移出操作
function dblClickOnSelect(obj){
	//后台操作删除这个学生对应的加分记录
	var result = "";
	if(obj.selectedIndex != 0){
	 	var xh = document.getElementById("xh").innerText.trim();
		var awardxmdm = obj.value.trim();
		var xn = document.getElementById("xn").value.trim();
		var awardkind = (obj.id=="tyjf")? "tyjsdm" : ((obj.id=="rwjf")? "zyjsdm":"cxxmdm");
		zbdxZhcpJfwh.zbdxZhcpDeleteAddedData(xh,xn,awardxmdm,awardkind,function(data){
			result = data;
		//移出所选项
		if(result=="true"){//不是第一条记录，并且后台操作也成功
			obj.removeChild(obj.options[obj.selectedIndex]);
			alert("删除成功！");
		}
	});
	}
}

//中北大学加分页面加分框增加操作
function addedCredit(obj){
	var vartext = "";//某种加分项获得分数
	var varaddedsel = "";//要加入项目的select
	var varsel ="";//项目列表select
	if(obj.id=="tyjfb"){
		vartext = "tyjft";
		varaddedsel = "tyjf";
		varsel = "tyjsdm";
	} else if(obj.id=="rwjfb"){
		vartext = "rwjft";
		varaddedsel = "rwjf";
		varsel = "zyjsdm";
	} else {
		vartext = "cxjft";
		varaddedsel = "cxjf";
		varsel = "cxxmdm";
	}
	var objtextvalue = document.getElementById(vartext).value.trim();
	var objaddedselect = document.getElementById(varaddedsel);
	var objselInnerText = document.getElementById(varsel).options[document.getElementById(varsel).selectedIndex].innerText.trim();
	var objselvalue  = document.getElementById(varsel).value.trim();
	var len = objaddedselect.options[0].innerText.length;
	var opt = document.createElement("option");
	var difflength = len-objselInnerText.length-4;
	opt.text = objselInnerText;
	for(var i=0;i<difflength;i++){
		opt.text += "－";
	}
	opt.text += objtextvalue;
	opt.value = objselvalue;
	var result = "";
	var xh = document.getElementById("xh").innerText.trim();
	var xn = document.getElementById("xn").value.trim();
	zbdxZhcpJfwh.zbdxZhcpJfwh(xh,xn,varsel,objselvalue,objtextvalue,function(data){
		result = data;
		if(result != "true"){
			alert("保存失败！请重新操作！");
		} else {
			objaddedselect.add(opt);
		}
	});
}
//获得下个学生的加分信息
function getNextStuJfxx(){
	var rows = window.dialogArguments.document.getElementById("rsTable").rows;
	var xh = document.getElementById("xh").innerText.trim();
	var pos = rows.length;
	for(var i=0;i<pos;i++){
		if(rows[i].cells[0].innerText.trim() == xh){
			pos = i;
		}
	}
	if(pos < rows.length-1){
		document.forms[0].action +="?xh="+rows[pos+1].cells[0].innerText.trim();
	} else {
		document.forms[0].action +="?xh="+rows[rows.length-1].cells[0].innerText.trim();
	}
	document.forms[0].submit();
}
//奖学金申请页面显示数据输入时的提示
function alertMessage(obj){
	var message = document.getElementById("message");
	message.style.top=window.event.clientY;
	message.style.left=window.event.clientX;
	message.style.position="absolute";
	message.style.display="block";
	if(obj.name.indexOf('zhkpzf')>=0){
		message.innerHTML="请输入你的综合考评成绩！包括小数点在内不超过4位长度！";
	} else{
		message.innerHTML="请输入数字！";
	}
}
//check what is very important
function check(str){
	var imp = new Array();
	if(str.indexOf('-') > -1){
		imp = str.split('-');
	} else {
		imp[0]=str;
	}
	for(var i=0;i<imp.length;i++){
		if(document.getElementById(imp[i]).value==null || document.getElementById(imp[i]).value==''){
			alert("请选择学年选项！");
			return false;
		}
	}
	return true;
}
//对于dispatch,合成提交uri 
function gen_submit(xn){//uri,method,query
	//var action = document.forms[0].action;
	//action = uri;
	//action += "?method=";
	//action += method;
	//if(query=="" || query==null){
	//	action += "&"+query;
	//}
	//alert(action);
	document.forms[0].action='/xgxt/save_js_bl.do?method=saveJsBl&xn='+xn;
	document.forms[0].submit();	
}

function showTop(xn){
	var url = '/xgxt/direct_to_bl.do?method=directToPage&xn='+xn;
	showTopWin(url,400,300);
}

function getParentXnList(){
	var xn = document.getElementById("xnPart");
	xn.innerHTML += window.dialogArguments.document.getElementById("xn").outerHTML;	
}

function checkBlSz(){
	var cjbl = document.getElementById('cjbl').value;
	var qtbl = document.getElementById('qtbl').value;	
	if(parseFloat(cjbl) + parseFloat(qtbl)>100){
		alert("输入的比例有问题，请检查后重新输入！");
		return false;
	}
	return true;
}
function checkAll(str){
	var xxdm = "";
	if($('xxdm')){
		xxdm = document.getElementById("xxdm").value;
	}
	var checkBoxArr = document.getElementsByName("checkVal");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if(flag){
		if (xxdm=='13022') {
			var jxjmc = document.getElementById('jmc').value;
			if (str=='pass') {
				if (jxjmc == '优秀学生一等奖学金') {
					if (confirm('该奖学金评定的基本条件为:\n    1.学年内学习加权平均分名次在同专业同年级前10%,\n    2.综合素质测评在班级前10%.\n    3.评奖学年及评审过程中无任何违纪处分.确认所选数据都满足以上条件吗？')) {
						
						document.forms[0].action="/xgxt/prise_check.do?checkall=yes&shzt="+str;
						document.forms[0].submit();	
					} else {
						return;
					}
				}
				if (jxjmc == '优秀学生二等奖学金') {
					if (confirm('该奖学金评定的基本条件为:\n    1.学年内学习加权平均分名次在同专业同年级前25%,\n    2.综合素质测评在班级前25%.\n    3.评奖学年及评审过程中无任何违纪处分.确认所选数据都满足以上条件吗？')) {
						
						document.forms[0].action="/xgxt/prise_check.do?checkall=yes&shzt="+str;
						document.forms[0].submit();	
					} else {
						return;
					}
				}
				if (jxjmc == '优秀学生三等奖学金') {
					if (confirm('该奖学金评定的基本条件为:\n    1.学年内学习加权平均分名次在同专业同年级前40%,\n    2.综合素质测评在班级前40%.\n    3.评奖学年及评审过程中无任何违纪处分.确认所选数据都满足以上条件吗？')) {
						
						document.forms[0].action="/xgxt/prise_check.do?checkall=yes&shzt="+str;
						document.forms[0].submit();	
					} else {
						return;
					}
				}
				if (jxjmc == '学习优秀奖学金') {
					if (confirm('该奖学金评定的基本条件为:\n     1.学年内学习加权平均分名次在同专业同年级前25%,\n    2.综合素质测评在班级前60%.\n    3.评奖学年及评审过程中无任何违纪处分.确认所选数据都满足以上条件吗？')) {
						
						document.forms[0].action="/xgxt/prise_check.do?checkall=yes&shzt="+str;
						document.forms[0].submit();	
					} else {
						return;
					}
				}
				if (jxjmc == '美育奖学金') {
					if (confirm('该奖学金评定的基本条件为:\n    1.学年内学习加权平均分名次在同专业同年级前50%,\n    2.综合素质测评在班级前60%.\n    3.评奖学年及评审过程中无任何违纪处分.确认所选数据都满足以上条件吗？')) {
						
						document.forms[0].action="/xgxt/prise_check.do?checkall=yes&shzt="+str;
						document.forms[0].submit();	
					} else {
						return;
					}
				}
				if (jxjmc == '体育奖学金') {
					if (confirm('该奖学金评定的基本条件为:\n     1.学年内学习加权平均分名次在同专业同年级前50%,\n    2.综合素质测评在班级前60%.\n    3.评奖学年及评审过程中无任何违纪处分.确认所选数据都满足以上条件吗？')) {
						
						document.forms[0].action="/xgxt/prise_check.do?checkall=yes&shzt="+str;
						document.forms[0].submit();	
					} else {
						return;
					}
				}
				if (jxjmc == '社会工作奖学金') {
					if (confirm('该奖学金评定的基本条件为:\n     1.学年内学习加权平均分名次在同专业同年级前50%,\n    2.综合素质测评在班级前60%.\n    3.评奖学年及评审过程中无任何违纪处分.确认所选数据都满足以上条件吗？')) {
						
						document.forms[0].action="/xgxt/prise_check.do?checkall=yes&shzt="+str;
						document.forms[0].submit();	
					} else {
						return;
					}
				}
				if (jxjmc == '创新奖学金') {
					if (confirm('该奖学金评定的基本条件为:\n     1.评奖学年及评审过程中无任何违纪处分.\n确认所选数据都满足以上条件吗？')) {
						
						document.forms[0].action="/xgxt/prise_check.do?checkall=yes&shzt="+str;
						document.forms[0].submit();	
					} else {
						return;
					}
				}
				if (jxjmc == '外语成绩优秀奖学金') {
					if (confirm('该奖学金评定的基本条件为:\n     1.学年内学习加权平均分名次在同专业同年级前50%,\n    2.综合素质测评在班级前60%.\n    3.评奖学年及评审过程中无任何违纪处分.\n    4.英语四级大于等于600分,或者英语六级大于等于425分,或者英语专业四级大于等于85分.\n    确认所选数据都满足以上条件吗？')) {
						
						document.forms[0].action="/xgxt/prise_check.do?checkall=yes&shzt="+str;
						document.forms[0].submit();	
					} else {
						return;
					}
				} else {
						
						document.forms[0].action="/xgxt/prise_check.do?checkall=yes&shzt="+str;
						document.forms[0].submit();	
				}
			} else {
				document.forms[0].action="/xgxt/prise_check.do?checkall=yes&shzt="+str;
				document.forms[0].submit();	
			}
		} else {
			BatAlert.showTips('正在操作，请等待...');	
			document.forms[0].action="/xgxt/prise_check.do?checkall=yes&shzt="+str;
			document.forms[0].submit();
		}
	} else{
		alert("没有选择相应记录，请选择之后再确定！！");
	}
}
//检查漳州师范的综合素质测评录入成绩
function checkInputCj(obj){
	//onlyNumWords(obj);
	if(parseFloat(obj.value)>100){
		alert("您输入的成绩大于100，请检查后重新输入！！");
		return false;
	}
	var znszcj = document.getElementById("znszcj").value.trim();
	var tcj = document.getElementById("tcj").value.trim();
	var zcj = document.getElementById("zcj").value.trim();
	var dcj = document.getElementById("dcj").value.trim();
	var jnjf = document.getElementById("jnjf").value.trim();
	document.getElementById("cpzf").value= parseFloat(znszcj==""?0:znszcj)+parseFloat(tcj==""?0:tcj)+parseFloat(zcj==""?0:zcj)+parseFloat(dcj==""?0:dcj)+parseFloat(jnjf==""?0:jnjf);
	
}
//漳州师范奖学金汇总
function ondblckEv(obj,all){
	var targTabCols = document.getElementById("targTabCols");
	var srcColLength = document.getElementById("srctab").options.length;
	var selArr = new Array();
	var temp = new Array();
	
	if((all != null || all != "") && all=="right"){
		targTabCols.outerHTML = obj.outerHTML;
		return true;
	} else if((all != null || all != "") && all=="left"){
		document.getElementById("tarSel").innerHTML = '<select id=\"targTabCols\" name=\"targTabCols\" ondblclick=\"ondblckEv(this)\"  multiple=\"multiple\"  style=\"width:100%;height:300\"></select>';
		return true;
	}
	for(var i=0;i<obj.options.length;i++){
		if(obj.options[i].selected){
			var subArr = new Array();
			subArr.push(obj.options[i].value);
			subArr.push(obj.options[i].innerText);
			selArr.push(subArr);
			temp.push(obj.options[i]);
		}
	}
	if(obj.id=="srctab"){
		for(var i=0;i<selArr.length;i++){
			var op = document.createElement("option");
			op.value = selArr[i][0];
			var text = document.createTextNode(selArr[i][1]);
			op.appendChild(text);
			targTabCols.appendChild(op);
		}
	} else {
		for(var i=0;i<temp.length;i++){
			targTabCols.removeChild(temp[i]);
		}
	}
}

//漳州师范输出奖学金汇总
function jxjhzOutput(){
	var str = "";
	str += "/xgxt/pjpy_jxjhz_output.do";
	var seldiv = document.getElementById("tarSel");
	var targSel = seldiv.getElementsByTagName("select")[0];
	var actionStr = "?queryStr=";
	if(targSel.options.length==0){
		alert("请选择导出字段！！");
		return false;
	}
	for(var i=0;i<targSel.options.length;i++){
		if(i==targSel.options.length-1){
			actionStr += targSel.options[i].value;
			continue;
		}
		actionStr += targSel.options[i].value+"!!";
	}
	actionStr += "&tableName="+document.getElementById("selectTab").value;
	str += actionStr;
	document.getElementById("base").target='/about:blank';
	document.forms[0].action=str;
	document.forms[0].submit();
}

function initCpzf(){
	if(document.getElementById("cpzf")){
	var cpzf = document.getElementById("cpzf");
	var znszcj = document.getElementById("znszcj").value.trim();
	var tcj = document.getElementById("tcj").value.trim();
	var zcj = document.getElementById("zcj").value.trim();
	var dcj = document.getElementById("dcj").value.trim();
	var jnjf = document.getElementById("jnjf").value.trim();
	cpzf.value= parseFloat(znszcj==""?0:znszcj)+parseFloat(tcj==""?0:tcj)+parseFloat(zcj==""?0:zcj)+parseFloat(dcj==""?0:dcj)+parseFloat(jnjf==""?0:jnjf);
	}
}

function countLhdf() {
	if (document.getElementById('xh').value=='') {
		alert('请选择学号！');
		return;
	}
	var zxq = document.getElementById('zxq').value;
	if (zxq == null || zxq == '') {
		zxq = '1';
	}
	var zgdy = document.getElementById('zgdy').value;
	var zgybdy = document.getElementById('zgybdy').value;
	var xjbj = document.getElementById('xjbj').value;
	var wmss = document.getElementById('wmss').value;
	var xsgb1 = document.getElementById('xsgb1').value;
	var xsgb2 = document.getElementById('xsgb2').value;
	var xsgb3 = document.getElementById('xsgb3').value;
	var xsgb4 = document.getElementById('xsgb4').value;
	var wysp = document.getElementById('wysp').value;
	var jsjsp = document.getElementById('jsjsp').value;
	var xjjxj1 = document.getElementById('xjjxj1').value;
	var xjjxj2 = document.getElementById('xjjxj2').value;
	var xjjxj3 = document.getElementById('xjjxj3').value;
	var gjjl1 = document.getElementById('gjjl1').value;
	var gjjl2 = document.getElementById('gjjl2').value;
	var gjjl3 = document.getElementById('gjjl3').value;
	var sjjl1 = document.getElementById('sjjl1').value;
	var sjjl2 = document.getElementById('sjjl2').value;
	var sjjl3 = document.getElementById('sjjl3').value;
	var xjgr1 = document.getElementById('xjgr1').value;
	var xjgr2 = document.getElementById('xjgr2').value;
	if(zgdy == null || zgdy == "" || zgdy == " "){
		zgdy = '0';
		document.getElementById('zgdy').value = "";
	}
	if(zgybdy == null || zgybdy == "" || zgybdy == " "){
		zgybdy = '0';
		document.getElementById('zgybdy').value = "";
	}
	if(xjbj == null || xjbj == "" || xjbj == " "){
		xjbj = '0';
		document.getElementById('xjbj').value = "";
	}
	if(wmss == null || wmss == "" || wmss == " "){
		wmss = '0';
		document.getElementById('wmss').value = "";
	}
	if(xsgb1 == null || xsgb1 == "" || xsgb1 == " "){
		xsgb1 = '0';
		document.getElementById('xsgb1').value = "";
	}
	if(xsgb2 == null || xsgb2 == "" || xsgb2 == " "){
		xsgb2 = '0';
		document.getElementById('xsgb2').value = "";
	}
	if(xsgb3 == null || xsgb3 == "" || xsgb3 == " "){
		xsgb3 = '0';
		document.getElementById('xsgb3').value = "";
	}
	if(xsgb4 == null || xsgb4 == "" || xsgb4 == " "){
		xsgb4 = '0';
		document.getElementById('xsgb4').value = "";
	}
	if(wysp == null || wysp == "" || wysp == " "){
		wysp = '0';
		document.getElementById('wysp').value = "";
	}
	if(jsjsp == null || jsjsp == "" || jsjsp == " "){
		jsjsp = '0';
		document.getElementById('jsjsp').value = "";
	}
	if(xjjxj1 == null || xjjxj1 == "" || xjjxj1 == " "){
		xjjxj1 = '0';
		document.getElementById('xjjxj1').value = "";
	}
	if(xjjxj2 == null || xjjxj2 == "" || xjjxj2 == " "){
		xjjxj2 = '0';
		document.getElementById('xjjxj2').value = "";
	}
	if(xjjxj3 == null || xjjxj3 == "" || xjjxj3 == " "){
		xjjxj3 = '0';
		document.getElementById('xjjxj3').value = "";
	}
	if(gjjl1 == null || gjjl1 == "" || gjjl1 == " "){
		gjjl1 = '0';
		document.getElementById('gjjl1').value = "";
	}
	if(gjjl2== null || gjjl2 == "" || gjjl2 == " "){
		gjjl2 = '0';
		document.getElementById('gjjl2').value = "";
	}
	if(gjjl3 == null || gjjl3 == "" || gjjl3 == " "){
		gjjl3 = '0';
		document.getElementById('gjjl3').value = "";
	}
	if(sjjl1 == null || sjjl1 == "" || sjjl1 == " "){
		sjjl1 = '0';
		document.getElementById('sjjl1').value = "";
	}
	if(sjjl2 == null || sjjl2 == "" || sjjl2 == " "){
		sjjl2 = '0';
		document.getElementById('sjjl2').value = "";
	}
	if(sjjl3 == null || sjjl3 == "" || sjjl3 == " "){
		sjjl3 = '0';
		document.getElementById('sjjl3').value = "";
	}
	if(xjgr1 == null || xjgr1 == "" || xjgr1 == " "){
		xjgr1 = '0';
		document.getElementById('xjgr1').value = "";
	}
	if(xjgr2 == null || xjgr2 == "" || xjgr2 == " "){
		xjgr2 = '0';
		document.getElementById('xjgr2').value = "";
	}
	var tp = parseFloat(xjbj)+parseFloat(wmss);
	if (parseFloat(tp) >= 1) {
		tp = 1;
	}
	var zf=(parseFloat(zgdy)+parseFloat(tp)+parseFloat(zgybdy)+parseFloat(xsgb1)+parseFloat(xsgb2)+
	parseFloat(xsgb3)+parseFloat(xsgb4)+parseFloat(wysp)+parseFloat(jsjsp)+parseFloat(xjjxj1)+parseFloat(xjjxj2)+parseFloat(xjjxj3)+parseFloat(gjjl1)+
	parseFloat(gjjl2)+parseFloat(gjjl3)+parseFloat(sjjl1)+parseFloat(sjjl2)+parseFloat(sjjl3)+parseFloat(xjgr1)+parseFloat(xjgr2))/parseFloat(zxq)+"";
	var tzf = zf.substr(0,zf.indexOf(".",0)+3);
	document.getElementById('zf').value="";
	document.getElementById('zf').value=tzf;
}

function pjpyprint() {
	var xxdm;
	if ($("xxdm")) {
		xxdm = document.getElementById('xxdm').value;
	}
	if ('11647'==xxdm) {
		var xh = document.getElementById('xh').value;
		if (xh==''||xh==null) {
			alert("学号为必填项!");
			return;
		}
		var url = 'pjpy_zjcm_jxjprint.do?xh=';
		window.open(url += xh);
		return;
	}
	var xn = document.getElementById('pjxn').value;
		var nd = document.getElementById('pjnd').value;
		var xh = document.getElementById('xh').value;
		var jxjdm = document.getElementById('jxjdm').value;
		var pk = xn+nd+xh+jxjdm;
	window.open('jxjpsdjb.do?xh='+document.getElementById('xh').value+'&jxjdm='+document.getElementById('jxjdm').value+'&pkValue='+pk);
}

function plqm(tableName) {
	var userType = document.getElementById('userType').value;
	var isFdy = document.getElementById('isFdy').value;
	if ('xy'!=userType) {
		alert('您无权进行签名操作!');
		return;
	} else {
		var checkBoxArr = document.getElementsByName("checkVal");
		var flag = false;
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		if (flag){
		var cbv = document.getElementsByName('checkVal');
		var val = '';
		for (var j=0;j<cbv.length;j++) {
			if(cbv[j].checked==true){
				val += (cbv[j].value+'@');
			}		
		}
			showTopWin('pjpy_hzzy_plqm.do?tableName='+tableName +'&ks='+val,'450','350');
		}else{
			alert("没有选择相应记录，请选择之后再操作,单击行首的复选框即可!");
			return;
		}
	}
}

function rychplqm(tableName) {
	var userType = document.getElementById('userType').value;
	var isFdy = document.getElementById('isFdy').value;
	if ('xy'!=userType) {
		alert('您无权进行签名操作!');
		return;
	} else {
		var checkBoxArr = document.getElementsByName("cbv");
		var flag = false;
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		if (flag){
		var cbv = document.getElementsByName('cbv');
		var val = '';
		for (var j=0;j<cbv.length;j++) {
			if(cbv[j].checked==true){
				val += (cbv[j].value+'@');
			}		
		}
			showTopWin('pjpy_hzzy_plqm.do?tableName='+tableName +'&ks='+val,'450','350');
		}else{
			alert("没有选择相应记录，请选择之后再操作,单击行首的复选框即可!");
			return;
		}
	}
}

function pjpytjbdy() {
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 350;
	var d_height_top = 150;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
	var userType = document.getElementById('userType').value;
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<br><table width='300' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td colspan='2'>";
	dd_html += "-----------------评奖评优统计报表选择-----------------";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "统计报表类型:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	if(userType == 'xy'){
		dd_html += "<select name='tjblx' id='tjblx' style='width:200'> <option value=''>------请选择------</option> <option value='myzyjxjmx'>每月专业奖学金发放明细表</option> <option value='myzyjxjhz'>每月专业奖学金发放汇总表</option></select>";
	}else{
		dd_html += "<select name='tjblx' id='tjblx' style='width:200'> <option value=''>------请选择------</option> <option value='myzyjxjmx'>每月专业奖学金发放明细表</option> <option value='myzyjxjhz'>每月专业奖学金发放汇总表</option> <option value='yxxsjxjffhz'>优秀学生奖学金发放汇总表</option> <option value='yxxsjxjff'>优秀学生奖学金发放表</option> <option value='ndzyjxjff'>专业奖学金发放表</option> <option value='yxjxjffhz'>优秀学生奖学金发放汇总表</option> </select>";
	}
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr bgcolor='EEF4F9'>";
	dd_html += "<td colspan='2' align='center'>";
	dd_html += "<button class='button2' onclick='xcxydybb()'>确定</button>";
	dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<button class='button2' onclick='closeAdd1()'>取消</button>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "</table>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
}

function pjpy_ChangeRecord(str,url){
	var index = window.dialogArguments.curr_row.rowIndex;
	var length = window.dialogArguments.rsTable.rows.length;
	var obj;
	var view = '';
	var select;
	if(str == 'up' && index >1){
    	obj = window.dialogArguments.rsTable.rows[index-1];
    }else if(str == 'next' && index <length-1){
    	obj = window.dialogArguments.rsTable.rows[index+1];
    }else{
    	return false;
    }    
	window.dialogArguments.curr_row.style.backgroundColor = "FFFFFF";
    window.dialogArguments.curr_row = obj;
    window.dialogArguments.curr_row.style.backgroundColor = '#ffdead';
    index = window.dialogArguments.curr_row.rowIndex;    
    if(index == '1'){
    	view = 'up';
    }else if (index == length-1){
    	view = 'next';
    }
    var line = obj.cells[0].getElementsByTagName('input')[0];
    if(view == ''){
    	select = obj.checked;
    }        
    refreshForm(url+'&pkValue='+line.value+'&view='+view+'&select='+select+'&sel='+line.checked);      
}
function pjpy_Shot(obj){
	window.dialogArguments.curr_row.cells[0].getElementsByTagName('input')[0].checked = obj.checked;
}
function pjpy_initCheck(){ 
    var index = window.dialogArguments.curr_row.rowIndex;
	var length = window.dialogArguments.rsTable.rows.length;
	if(index==1){
	   document.getElementById("up").disabled=true;
	}
	if(index==length-1){
	   document.getElementById("next").disabled=true;
	} 
	if(index==1&&index==length-1){
	   document.getElementById("up").disabled=true;
	   document.getElementById("next").disabled=true;
	}
	document.getElementById("selected").checked=window.dialogArguments.curr_row.cells[0].getElementsByTagName('input')[0].checked;
}

function pjpy_jxjSh_ChangeRecord(str){//浙江理工评奖评优
    var url   = "/xgxt/zjlgPjpy.do?method=jxjCheck";
	var index = window.dialogArguments.curr_row.rowIndex;
	var length = window.dialogArguments.rsTable.rows.length;
	var obj;
	var view = '';
	var select;
	if(str == 'up' && index >1){
    	obj = window.dialogArguments.rsTable.rows[index-1];
    }else if(str == 'next' && index <length-1){
    	obj = window.dialogArguments.rsTable.rows[index+1];
    }else{
    	return false;
    }    
	window.dialogArguments.curr_row.style.backgroundColor = "FFFFFF";
    window.dialogArguments.curr_row = obj;
    window.dialogArguments.curr_row.style.backgroundColor = '#ffdead';
    index = window.dialogArguments.curr_row.rowIndex;    
    if(index == '1'){
    	view = 'up';
    }else if (index == length-1){
    	view = 'next';
    }
    var line = obj.cells[0].getElementsByTagName('input')[0];
    var dm   = obj.cells[0].getElementsByTagName('input')[1];
    if(view == ''){
    	select = obj.checked;
    }        
    refreshForm(url+'&pkValue='+line.value+'&view='+view+'&select='+select+'&sel='+line.checked+'&jxjcxzj='+dm.value);      
}
//验证数据格式是否是数字
function pjpy_ckinpdata(obj){
		if(obj.value==""){		
		   return false;
		}
		obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;

		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g)) ){
			alert('数据格式不正确，只能是数字和小数点！');
			obj.value = '';
			return false;
		}
		return true;
}