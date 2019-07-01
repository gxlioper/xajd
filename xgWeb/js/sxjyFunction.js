function sxpub(type){
	if(document.getElementById("title").value == ""){
		alert("请填写标题！");
			document.getElementById("title").focus();
		return false;
	}
	document.getElementById('content1').value = frames('eWebEditor1').getHTML();
	if(document.getElementById("content1").value == ""){
		alert("请填写正文！");
		return false;
	}
	if(type=="xy"){
	if(document.getElementById("saveXxplandm").value == ""){
		alert("请选择所属学校计划！");
			document.getElementById("saveXxplandm").focus();
		return false;
	}
	refreshForm('xy_plan_Save.do?type=xy');
	}else if(type=="xx"){
	refreshForm('xx_plan_Save.do?type=xx');
	}else if(type=="xyzj"){
	refreshForm('xyzj_Save.do?type=xyzj');
	}else if(type=="jyzt"){
	refreshForm('jyzt_Save.do?type=jyzt');
	}else if(type=="hdch"){
	refreshForm('hdch_Save.do?type=hdch');
	}else if(type=="hdzj"){
	refreshForm('hdzj_Save.do?type=hdzj');
	}else if(type=="zzxxcl"){
	refreshForm('zzxxcl_Save.do?type=zzxxcl');
	}else if(type=="szkhwj"){
	refreshForm('szkhwj_Save.do?type=szkhwj');
	}else if(type=="khtz"){
	refreshForm('assess_Inform_save.do?type=khtz');
	}else if(type=="ysjyxx"){
	refreshForm('artEducate_info_save.do?type=ysjyxx');
	}else if(type=="dyjh"){
	refreshForm('research_plan_Save.do?type=dyjh');
	}else if(type=="dyzj"){
	refreshForm('research_summarize_Save.do?type=dyzj');
	}else if(type=="aqjytz"){
	refreshForm('safetyEducation_Inform_save.do?type=aqjytz');
	}else if(type=="xsjyhdtz"){
	refreshForm('sfreshman_educationPloy_save.do?type=xsjyhdtz');
	}else if(type=="fdypx"){
	refreshForm('counsellor_fosterInfo_save.do?type=fdypx');
	}else if(type=="fdykhpy"){
	refreshForm('counsellor_AssessInfo_save.do?type=fdykhpy');
	}else if(type=="fdyzbaptz"){
	refreshForm('counsellor_onDuty_save.do?type=fdyzbaptz');
	}else if(type=="fdyzdxx"){
	refreshForm('counsellor_system_save.do?type=fdyzdxx');
	}else if(type=="bjfc"){
	refreshForm('class_elegant_save.do?type=bjfc');
	}else if(type=="fdygzjl"){
	refreshForm('class_workIntercourse_save.do?type=fdygzjl');
	}else if(type=="yxdsfc"){
	refreshForm('excellenceTeacher_elegant_save.do?type=yxdsfc');
	}else if(type=="001"||type=="002"||type=="003"){
		var jhtype = document.getElementById("type").value;
	refreshForm('xsgbzjfb.do?type=xsgbzjfb&jhtype='+jhtype);	
	}
}

function sxDis(type){
	url = "show_One_Clob.do?type=";
	url += type;
	url += "&ID=";
	var tmp = curr_row.cells[0].getElementsByTagName("input")[0].value;
	var w = 800;
	var h = 600;
	url += tmp;
	window.open(url);
	}

function sxjyReportDel(type){
	if(curr_row == null ){
			alert("请选择要删除的记录！\n单击一行记录即可");
			return false;
		} 
	var tmp = curr_row.cells[0].getElementsByTagName("input")[0].value;
	if(type=="hdzjhb"){
	var	url = "hdzj_report_del.do?realTable=sxjy_hdzjhbb&act=hdzjhb&pk=";
	}else if(type=="zzxxnj"){
	var	url = "zzxx_note_del.do?realTable=sxjy_zzxxtljlb&act=zzxxnj&pk=";
	}else if(type=="xfjpjl"){
	var	url = "xfjp_note_del.do?realTable=sxjy_xfjpjlb&act=xfjpjl&pk=";
	}else if(type=="swCheck"){
	var	url = "studyWind_check_del.do?realTable=sxjy_xfjcjlb&act=swCheck&pk=";
	}else if(type=="shbk"){
	var	url = "penuryHelp_info_del.do?realTable=sxjy_shbkxxgl&act=shbk&pk=";
	}else if(type=="shbkjl"){
	var	url = "penuryHelp_note_del.do?realTable=sxjy_shbkhdjl&act=shbkjl&pk=";
	}else if(type=="ysjyhdjl"){
	var	url = "artEducate_note_del.do?realTable=sxjy_ysjyhdjlb&act=ysjyhdjl&pk=";
	}else if(type=="dyjh"){
	var	url = "research_plan_del.do?realTable=sxjy_dyjhb&act=dyjh&pk=";
	}else if(type=="dyst"){
	var	url = "research_question_del.do?realTable=sxjy_dytkb&act=dyst&pk=";
	}else if(type=="dywj"){
	var	url = "research_testPaper_del.do?realTable=sxjy_dywjb&act=dywj&pk=";
	}else if(type=="ztjzqksb"){
	var	url = "specialChair_note_del.do?realTable=sxjy_ztjzqksbb&act=ztjzqksb&pk=";
	}else if(type=="bjgbsz"){
	var	url = "specialChair_note_del.do?realTable=sxjy_bjgbzlb&act=bjgbsz&pk=";
	}else if(type=="bjzw"){
	var	url = "classDuty_Cadre_del.do?realTable=sxjy_bjgbxxb&act=bjzw&pk=";
	}else if(type=="fdyxxth"){
	var	url = "counsellorAndStuSpeak_note_del.do?realTable=sxjy_fdyxxthjlb&act=fdyxxth&pk=";
	}else if(type=="tfsjjl"){
	var	url = "tfsj_note_del.do?realTable=sxjy_tfsjjlb&act=tfsjjl&pk=";
	}else if(type=="jsgtjl"){
	var	url = "counsellorCommunicate_note_del.do?realTable=sxjy_jsgtjlb&act=jsgtjl&pk=";
	}else if(type=="dsyjx"){
	var	url = "teacherAttitudeBox_del.do?realTable=szdw_dsyjx&go=go&act=dsyjx&pk=";
	}else if(type=="aqfzjyqk"){
	var	url = "aqfzjyqk_del.do?realTable=sxjy_aqfzjyqkb&go=go&act=aqfzjyqk&pk=";
	}else if(type=="xshzw"){
	var	url = "xshgb_del.do?realTable=sxjy_xshgbxxb&act=xshzw&pk=";
	}else if(type=="xyXshzw"){
	var	url = "xyXshgb_del.do?realTable=sxjy_xyxshgbxxb&act=xyXshzw&pk=";
	}else if(type=="xljkXshzw"){
	var	url = "xljkXshgb_del.do?realTable=sxjy_xljkxshgbxxb&act=xljkXshzw&pk=";
	}
	url +=tmp;
	if(confirm("确定要删选中记录吗？")){
				document.forms[0].action = url;
				document.forms[0].submit();
				return true;
			}else{
				return false;
	}
}

function sxjyReportDataDoSave(url,pkFields){
	var eles = pkFields.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i])){
			if(document.getElementById(eles[i]).value == "") {
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
	}
	if($("cont")){
		if($("cont").value.length>2000){
			alert("内容不能超过2000字符/1000汉字");
			return false;
		}
	}
	document.forms[0].action = url;
	document.forms[0].submit();
	alert("保存成功！");
	window.dialogArguments.document.getElementById("search_go").click();
	close();
}

function sxjyReportupdata(type){
	if(type =="hdzjhb"){
	var url = "hdzj_report_updata.do?act=hdzjhb&pk="
	}else if (type =="zzxxnj"){
	var url = "zzxx_note_updata.do?act=zzxxnj&pk="
	}else if (type =="xfjpjl"){
	var url = "xfjp_note_updata.do?act=xfjpjl&pk="
	}else if (type =="tfsjjl"){
	var url = "tfsj_note_updata.do?act=tfsjjl&pk="
	}else if (type =="swCheck"){
	var url = "studyWind_check_updata.do?act=swCheck&pk="
	}else if (type =="shbk"){
	var url = "penuryHelp_info_updata.do?act=shbk&pk="
	}else if (type =="shbkjl"){
	var url = "penuryHelp_note_updata.do?act=shbkjl&pk="
	}else if (type =="ysjyhdjl"){
	var url = "artEducate_note_updata.do?act=ysjyhdjl&pk="
	}else if (type =="dyjh"){
	var url = "research_plan_updata.do?act=dyjh&pk="
	}else if (type =="dywj"){
	var url = "research_testPaper_updata.do?act=dywj&pk="
	}else if (type =="dyst"){
	var url = "research_question_updata.do?act=dyst&pk="
	}else if (type =="jystda"){
	var url = "research_answer_updata.do?act=jystda&pk="
	}else if (type =="dywjlr"){
	var url = "research_testPaper_fillIn.do?pk="
	}else if (type =="ztjzqksb"){
	var url = "specialChair_note_updata.do?act=ztjzqksb&pk="
	}else if (type =="bjgbsz"){
	var url = "classDuty_config_updata.do?act=bjgbsz&pk="
	}else if (type =="bjzw"){
	var url = "classDuty_Cadre_updata.do?act=bjzw&doType=modi&pk="
	}else if (type =="fdyxxth"){
	var url = "counsellorAndStuSpeak_note_updata.do?act=fdyxxth&pk="
	}else if (type =="jsgtjl"){
	var url = "counsellorCommunicate_note_updata.do?act=jsgtjl&pk="
	}else if (type =="dsyjx"){
	var url = "teacherAttitudeBox_updata.do?act=dsyjx&doType=updata&pk="
	}else if (type =="aqfzjyqk"){
	var url = "aqfzjyqk_updata.do?act=aqfzjyqk&doType=updata&pk="
	}else if (type =="xshzw"){
	var url = "xshgb_updata.do?act=xshzw&pk="
	}else if (type =="xyXshzw"){
	var url = "xyXshgb_updata.do?act=xyXshzw&pk="
	}else if (type =="xljkXshzw"){
	var url = "xljkXshgb_updata.do?act=xljkXshzw&pk="
	}else if(type=="bjzwview"){
	var url = "classDuty_Cadre_updata.do?act=bjzw&operation=view&pk="
	}
	
	if(curr_row == null ){
			alert("请选要修改的记录！\n单击一行记录即可");
			return false;
		} 
	var tmp = curr_row.cells[0].getElementsByTagName("input")[0].value;
	url += tmp.toString();
	if($("modiAble")){
		url +="&modiAble="+$("modiAble").value;
	}
	if (type =="shbk"||type =="bjgbsz"||type =="xshzw"||type =="xyXshzw"||type =="xljkXshzw"){
		showOpenWindow(url,560,350);
	}else if(type =="jystda"){
		showOpenWindow(url,800,600);
	}else if(type =="dsyjx"){
		showOpenWindow(url,800,600);
	}else if(type=="bjzw" || type=="bjzwview"){
		showDialog("", 680, 500, url);
		//showTopWin(url,680,500);
	}else {
		showOpenWindow(url,800,600);
	}
}

function sxjyTestPaperSave(url,pkFields,pk){
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
	//alert(url);
	alert("保存成功！");
	url = "research_question_updata.do?act=dyst&pk=";
	url += pk;
	window.dialogArguments.refreshForm(url);
	close();
}

function sxjyTestPaperupdata(type,pkSubsidiary){
	if (type =="jystda"){
	var url = "research_answer_updata.do?act=jystda&pk="
	}
	if(curr_row == null ){
			alert("请选要修改的记录！\n单击一行记录即可");
			return false;
		} 
	var tmp = curr_row.cells[0].getElementsByTagName("input")[0].value;
	url += tmp.toString();
	url += "&pkSubsidiary="
	url += pkSubsidiary;
	if(type =="jystda"){
		showTopWin(url,600,450);
	}else{
		showTopWin(url,800,600);
	}
}

function sxjyTestPaperDel(type,pk){
	if(curr_row == null ){
			alert("请选择要删除的记录！\n单击一行记录即可");
			return false;
		} 
	var tmp = curr_row.cells[0].getElementsByTagName("input")[0].value;
	 if(type=="jystda"){
	var	url = "research_answer_del.do?realTable=sxjy_dytkdab&act=jystda&pkSubsidiary=";
	}else if (type=="jyst"){
	var	url = "research_questionChoose_del.do?realTable=sxjy_dywjxtb&act=jyst&pkSubsidiary=";
	}
	url +=pk;
	url +="&pk="
	url +=tmp;
	if(confirm("确定要删选中记录吗？")){
				document.forms[0].action = url;
				document.forms[0].submit();
				return true;
			}else{
				return false;
	}
}

function getValueSave(type,pkSubsidiary){
	if(type =="jystyz"){
	var url = "research_question_choose.do?type=jystyz&pkSubsidiary="
	url += pkSubsidiary;
	url += "&pk=";
	}
	if(curr_row == null ){
			alert("请选要修改的记录！\n单击一行记录即可");
			return false;
	}
	var tmp = curr_row.cells[0].getElementsByTagName("input")[0].value; 
	url += tmp.toString();
	document.forms[0].action = url;
	document.forms[0].submit();
	alert("添加成功！");
	url = "research_testPaper_updata.do?pk=";
	url += pkSubsidiary;
	window.dialogArguments.refreshForm(url);
	close();
}

function sxjyTestPaperSubmit(url){
	document.forms[0].action = url;
	document.forms[0].submit();
	//alert(url);
	alert("提交成功！");
	url = "research_testPaperFillIn_list.do?act=dywjlr&part=N020903";
	window.dialogArguments.refreshForm(url);
	close();
}

function sxjyStatisticCheck(url,pkFields){
	if(document.getElementById(pkFields).value == "") {
		alert("请选择统计项目！");
		return false;
	}
	if(url=="turnInReport_Statistic.do"){
	if(isNumber(document.getElementById("sum").value) == false) {
		alert("上交数必须为整数！");
		return false;
	}
	}
	document.forms[0].action = url;
	document.forms[0].submit();
}

function getFormatValue(){
   var getSelectValue = document.getElementById("formatChoose").value;
   document.getElementById("format").value = getSelectValue;         
}

function szdwBjMust(url){
	document.forms[0].action = url+"&go=go";
	document.forms[0].submit();
}

function sxjyCommonSave(url,pkFields,pk){
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
	//alert(url);
	alert("保存成功！");
	if(pk == "bjgbsz"){
	url = "show_classDutyConfig_list.do?act=bjgbsz&part=N170304";
	}else if(pk == "bjzw"){
	url = "show_classStudentCadre_list.do?act=bjzw&part=N170302";
	}else if(pk == "xshzw"){
	url = "show_xshgb_list.do?act=xshzw";
	}else if(pk == "xyXshzw"){
	url = "show_xyXshgb_list.do?act=xyXshzw";
	}else if(pk == "xljkXshzw"){
	url = "show_xljkXshgb_list.do?act=xljkXshzw";
	}
	window.dialogArguments.refreshForm(url);
	close();
}

function bjDisabled(){
	
	if(document.getElementById("xyV").value != "") {
		 if(document.getElementById("xyV").value != "")
		 var xyV = document.getElementById("xyV").value;
		 var query = "%"+xyV+"%!!-!!%!!-!!%";
		 GetListData.getBjList(query,getsxjybjValue);
	}
}

function getsxjybjValue(data){
		if (data != null && typeof data == 'object') {
		var objId = data[0].dm;
		if($(objId) && $(objId).tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions(objId);
			DWRUtil.addOptions(objId,data,"dm","mc");
			$(objId).options[0].value = "";
			alert($(objId + "V").value);
			if($(objId +"V").value != "" && $(objId + "V").value != null){
				for(var i = 0;i < $(objId).options.length; i++){
					if($(objId).options[i].value == $(objId +"V").value){
						$(objId).options[i].selected = true;
						return true;
					}
				}
			}
		}
	}
}
	
function sxjyAlert(url,month1xq,month2xq,data){
	var eles1 = month1xq.split("-");
	for (i = 0; i < eles1.length; i++) {
		if (document.getElementById(eles1[i])){
			var month = parseInt(document.getElementById(eles1[i]).value);
			if((month<9&&month>2)||month>12||month<1) {
				alert("输入月份要小于3和大于8！");
				return false;
			}
		}
	}
	var eles2 = month2xq.split("-");
	for (i = 0; i < eles2.length; i++) {
		if (document.getElementById(eles2[i])){
			var month = parseInt(document.getElementById(eles2[i]).value);
			if((month>2&&month<9)||month>12||month<1) {
				alert("输入月份要大于2和小于9！");
				return false;
			}
		}
	}
	var eles3 = data.split("-");
	for (i = 0; i < eles3.length; i++) {
		if (document.getElementById(eles3[i])){
			if(parseInt(document.getElementById(eles3[i]).value) >31) {
				alert("输入日期要小于31！");
				return false;
			}
		}
	}
	document.forms[0].action = url;
	document.forms[0].submit();
}

function getDataList(){
try{
	if(document.getElementById("data") && document.getElementById("data") != null){
		document.getElementById("data").removeAllOptions;
		for(i=1 ;i<32; i++){
			document.getElementById("data").addOptions(i.toString(),i.toString());
		}
	}
	}catch(e){
	   alert(e)
	}
}

function trim(s){
 	return rtrim(ltrim(s)); 
}

function ltrim(s){
 	return s.replace( /^\s*/, ""); 
} 

function rtrim(s){ 
 	return s.replace( /\s*$/, ""); 
}

function isEmail(s){
	s = trim(s); 
 	var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	return p.test(s);
}

function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
}

function isMobile(s){ 
	s = trim(s); 
	var p = /1\d{10}/; 
	return p.test(s);
}

function dyxgxxSave(mustFill) {
	
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		}
	}
	var url = "djxgOne.do?realTable=";
	url += window.dialogArguments.document.forms[0].realTable.value;
	url += "&doType=save";
	url += "&tableName=";
	url += window.dialogArguments.document.forms[0].tableName.value;
	url += "&pk=";
	url += window.dialogArguments.document.forms[0].pk.value;
	url += "&pkValue=";
	url += document.forms[0].pkValue.value;
	url += "&from=";
	url += window.dialogArguments.document.forms[0].act.value;
	document.forms[0].action = url;
	document.forms[0].submit();
	alert("保存成功！");
	window.dialogArguments.document.getElementById("search_go").click();
	Close();
}

function szsxDataDoSave(url,pkFields){
	var eles = pkFields.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i])){
			if(document.getElementById(eles[i]).value == "") {
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
	}
	if(url == "zgkdXssy.do?method=saveSyxgsj"){
		if(document.getElementById("xsdjqsrq").value >document.getElementById("xsdjjsrq").value) {
				alert("开始时间晚于结束时间，设置错误！");
				return false;
		}else if(document.getElementById("xgmbqsrq").value >document.getElementById("xgmbjsrq").value) {
				alert("开始时间晚于结束时间，设置错误！");
				return false;
		}
	}
	document.forms[0].action = url;
	document.forms[0].submit();
}

function msJgDoSave(url){
	var fzs = document.getElementsByName("fz");
	if(document.getElementById("xh").value == "") {
				alert("请选择学生");
				return false;
	}
	for (var i = 1; i < fzs.length; i++) {
		if(fzs[i].value == ""||!isDouble(fzs[i].value)){
			alert("打分项目必须填写完整且只能是数字！");
				return false;
		}	
	}
	document.forms[0].action = url;
	document.forms[0].submit();
}

function stmsJgDoSave(url){
	var fzs = document.getElementsByName("fz");
	if(document.getElementById("stdm").value == "") {
				alert("请选择社团");
				return false;
	}
	for (var i = 1; i < fzs.length; i++) {
		if(fzs[i].value == ""||!isDouble(fzs[i].value)){
			alert("打分项目必须填写完整且只能是数字！");
				return false;
		}	
	}
	document.forms[0].action = url;
	document.forms[0].submit();
}

function methodOne(url,w,h){
	url+="&method=modiOne";
	showTopWin(url, w, h);
}

function addOne(url,w,h){
	url+="?method=modiOne";
	showTopWin(url, w, h);
}

function deleteOne(){
	document.getElementById("method").value="deleteOne";
}

function isDouble(s){
			var p = /^\d+(?=\.{0,1}\d+$|$)/;
			return p.test(s); 
}

function qzConfDoSave(url){
	var qzs = document.getElementsByName("qz");
	for (var i = 1; i < qzs.length; i++) {
		if(qzs[i].value == ""||!isDouble(qzs[i].value)||qzs[i].value>1){
			alert("权重必须填写且不能大于1！");
				return false;
		}	
	}
	document.forms[0].action = url;
	document.forms[0].submit();
}

function getPlanList(){
	var type = document.getElementById("type").value;
	var xh = document.getElementById("xh").value;
	getPlan.getPlanList(xh,type,TjPlanList);
}

function TjPlanList(data){
	var cellfu =[
		function(data){return data.jhbt;},
		function(data){return data.lrrq;},
		function(data){return data.pk;},
		function(data){return data.sfhfpz;}
	];
	if (data != null && typeof data == 'object') {
		if ($("rsTables").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("rsTables");
			addPlanRows("rsTables",data,cellfu);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

function checkbz() {
		if (document.getElementById("bz").value.length >120) {
			alert("备注的长度不能超过120个字符!");
			return false;
		}else{
	return true;
		}
}

function checkBt() {
		if (document.getElementById("title").value.length >200) {
			alert("标题的长度不能超过200个字符!");
			return false;
		}else{
	return true;
		}
}

function fdyzpPub(){
	if(document.getElementById("title").value == ""){
		alert("请填写标题！");
			document.getElementById("title").focus();
		return false;
	}
	document.getElementById('content1').value = frames('eWebEditor1').getHTML();
	if(document.getElementById("content1").value == ""){
		alert("请填写正文！");
		return false;
	}
	var tmp=document.getElementsByName("pjdj");     
  		for   (var   i=0;i<tmp.length;i++){   
      if   (tmp[i].checked){     
            refreshForm('fdypjOther.do?method=saveFdyzp'); 
              return true;
          }
          }
       alert("请您选择自我评价!");
       return false;
}

function checkRadio(){
	var pjdj = document.getElementById("pjdjV").value;
	var tmp=document.getElementsByName("pjdj"); 
  		for   (var   i=0;i<tmp.length;i++){   
      if   (tmp[i].value==pjdj){
            tmp[i].checked=true;
          }
          }
}

addPlanRows = function(ele, data, cellFuncs, options) {
  ele = dwr.util._getElementById(ele, "addRows()");
  if (ele == null) return;
  if (!dwr.util._isHTMLElement(ele, ["table", "tbody", "thead", "tfoot"])) {
    dwr.util._debug("addRows() can only be used with table, tbody, thead and tfoot elements. Attempt to use: " + dwr.util._detailedTypeOf(ele));
    return;
  }
  if (!options) options = {};
  if (!options.rowCreator) options.rowCreator = dwr.util._defaultRowCreator;
  if (!options.cellCreator) options.cellCreator = dwr.util._defaultCellCreator;
  var tr, rowNum;
  if (dwr.util._isArray(data)) {
    for (rowNum = 0; rowNum < data.length; rowNum++) {
      options.rowData = data[rowNum];
      options.rowIndex = rowNum;
      options.rowNum = rowNum;
      options.data = null;
      options.cellNum = -1;
      tr = _addRowInnerText(cellFuncs, options);
      if (tr != null) ele.appendChild(tr);
    }
  }
  else if (typeof data == "object") {
    rowNum = 0;
    for (var rowIndex in data) {
      options.rowData = data[rowIndex];
      options.rowIndex = rowIndex;
      options.rowNum = rowNum;
      options.data = null;
      options.cellNum = -1;
      tr = _addRowInnerText(cellFuncs, options);
      if (tr != null) ele.appendChild(tr);
      rowNum++;
    }
  }

  dwr.util.highlight(ele, options);
};

/**
 * @private Internal function to draw a single row of a table.
 */
_addRowInnerText = function(cellFuncs, options) {
  var tr = options.rowCreator(options);
  if (tr == null) return null;
  for (var cellNum = 0; cellNum < cellFuncs.length; cellNum++) {
    var func = cellFuncs[cellNum];
    if (typeof func == 'function') options.data = func(options.rowData, options);
    else options.data = func || "";
    options.cellNum = cellNum;
    var pk;
    var td = options.cellCreator(options);
    if (td != null) {
      if (cellNum==(cellFuncs.length-2)){
      	td.innerHTML = "<a href=\"#\" onclick=\"location='stuCadre.do?method=xsgbgzjh&plansId="+options.data+"'\">修改</a>/ <a href=\"#\" onclick=\"if(confirm('确实要删除当前计划吗？'))location='stuCadre.do?method=xsgbgzjhDel&pk="+options.data+"'\" >删除</a>";
      	pk = options.data;
      }else if (cellNum==(cellFuncs.length-1)){
      	var tmp = options.data;
      	if(tmp=="true"){
      	td.innerHTML = "<button class=\"button2\" onclick=\"showTopWin('stuCadre.do?method=postilOne&pk="+pk+"',600,480)\" style=\"width:80px\" >查看批注</button>";
      	}else{
      		td.innerHTML = "<button class=\"button2\" onclick=\"showTopWin('stuCadre.do?method=postilOne&pk="+pk+"',600,480)\" style=\"width:80px\" disabled=\"true\" >查看批注</button>";	
      	}
      }else{
      if (options.data != null) {
        if (dwr.util._isHTMLElement(options.data)) td.appendChild(options.data);
        else {
          if (dwr.util._shouldEscapeHtml(options) && typeof(options.data) == "string") {
            td.innerHTML = dwr.util.escapeHtml(options.data);
          }
          else {
            td.innerHTML = options.data;
          }
        }
      }
      }
      tr.appendChild(td);
    }
  }
  return tr;
};

function checkRadio2(){
  var xxArray= new Array();
  var txt=document.forms[0].stStr.value;
  var splitTxt=txt.split("!!SplitSignOne!!");
  var stlxtype;
  for(i=0;i<splitTxt.length;i++){
    xxArray=splitTxt[i].split("!!SplitSignTwo!!");
    var tmp=document.getElementsByName(xxArray[0]); 
    for   (var   j=0;j<tmp.length;j++){   
  	if   (tmp[j].value==xxArray[1]){
            tmp[j].checked=true;
          }
     }
     }
}

function searchFdyhp(){
	if(document.getElementById("xn").value == ""){
		alert("请选择学年！");
			document.getElementById("xn").focus();
		return false;
	}
	if(document.getElementById("nd").value == ""){
		alert("请选择年度！");
			document.getElementById("nd").focus();
		return false;
	}
	if(document.getElementById("xq").value == ""){
		alert("请选择学期！");
			document.getElementById("xq").focus();
		return false;
	}
	allNotEmpThenGo('fdypjOther.do');	
}

function fzjyYzRadio(url,w,h){
	var tmps = document.getElementsByName("tableName");
	var sfxz = false;
	var xzz  = "";
	for(i = 0; i<tmps.length;i++){
		if(tmps[i].checked){
			sfxz = true;
			xzz = tmps[i].value;
		}
	}
	if(sfxz){
		showTopWin(url+xzz,w,h);
		return true;
	}else{
		alert("请选择要操作的表");
		return false;
	}
}