/** ===============================层操作==================================*/

//设置弹出层隐藏域值
function setPlszHiddenValue(lx,value){
	var id = "sz_"+lx+"_value";
	$(id).value = value;
}
		
//显示增加层
function showZjDiv(){
	viewTempDiv("增加显示区","zjDiv",350,200);
}

//显示单行操作Div
function showSzDiv(){
	var cur_bgc = "#ffdead";
	var num = $("xsqNr").getElementsByTagName('tr').length;
	var flag = true;
	
	for(var i=0;i<num;i++){
		var trObj = $("xsqNr").getElementsByTagName('tr')[i];
		//选中行
		if(trObj.style.backgroundColor == cur_bgc){
			var div_id = trObj.id.replace("tr","div");
			$("checked_tr").value = div_id;	
			flag = false;		
			break;
		}
	}
	
	if(flag){
		alert("请选中需要操作的行\n(注：照片所在行不可操作)");
		return false;
	}
	
	var xsq = div_id.split("_")[0];//显示区
	var sch = div_id.split("_")[1];//所在行
	
	var szh = "szhs"+xsq.replace("xsq","");
	
	//不可删除非最后一行
	if(sch!=$(szh).value){
		$("last_tr").value= "no";
	}else{
		$("last_tr").value= "yes";
	}
	
	viewTempDiv("单行操作","czDiv",300,100);
	
	//是否最后行
	var last_tr = $("last_tr").value;
	
	if(last_tr == "yes"){
		$("czDiv_zjh").style.display = "";
		$("czDiv_sch").style.display = "";
	}else{
		$("czDiv_zjh").style.display = "none";
		$("czDiv_sch").style.display = "none";
	}
}
/** ===============================层操作 end==================================*/

/** ===============================单行操作==================================*/

//合并显示区Tr
function hbXsqTr(){
	
	var div_id = $("checked_tr").value;
	var xsq = div_id.split("_")[0];//显示区
	var xsqdm = xsq.replace("xsq","");//显示区代码
	var hs = div_id.split("_")[1];//行数

	var p_left_id = xsq+"_"+hs+"_left_p";
	var p_right_id = xsq+"_"+hs+"_right_p";
	var text_left_id = xsq+"_"+hs+"_left_text";
	var text_right_id = xsq+"_"+hs+"_right_text";
	
	//退还待分配字段
	returnDfpZd();
	
	if($(div_id)){
		var divHtml = "<table width=\"100%\">";
			divHtml += "<tr>";
			divHtml += "<td width=\"20%\">";
			divHtml += "<p id=\""+xsq+"_"+hs+"_left_p\"></p>"
			divHtml += "<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\""+xsq+"_"+hs+"_left_text\" value=\"\"/>";
			divHtml += "<input type=\"hidden\" name=\"hbh_szxsq\" value=\""+xsqdm+"\"/>";
			divHtml += "<input type=\"hidden\" name=\"hbh\" value=\""+hs+"\"/>";
			divHtml += "</td>";
			divHtml += "<td colspan=\"3\">";
			divHtml += "<button onclick=\"clickXsqBtn(this)\" id=\""+xsq+"_"+hs+"_left\">应用</button>";
			divHtml += "</td>";
			divHtml += "</tr>";
			divHtml += "</table>";
					
		$(div_id).innerHTML = divHtml;
	}
	
	hiddenMessage(true,true);
}

//取消显示区Tr
function qxXsqTr(){
	
	var div_id = $("checked_tr").value;
	var xsq = div_id.split("_")[0];//显示区
	var xsqdm = xsq.replace("xsq","");//显示区代码
	var hs = div_id.split("_")[1];//行数
	
	//退还待分配字段
	returnDfpZd();
	
	if($(div_id)){
		var divHtml = "<table width=\"100%\">";
			divHtml += "<tr>";
			divHtml += "<td width=\"20%\">";
			divHtml += "<p id=\""+xsq+"_"+hs+"_left_p\"></p>"
			divHtml += "<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\""+xsq+"_"+hs+"_left_text\" value=\"\"/>";
			divHtml += "</td>";
			divHtml += "<td width=\"30%\">";
			divHtml += "<button onclick=\"clickXsqBtn(this)\" id=\""+xsq+"_"+hs+"_left\">应用</button>";
			divHtml += "</td>";
			divHtml += "<td width=\"20%\">";
			divHtml += "<p id=\""+xsq+"_"+hs+"_right_p\"></p>";
			divHtml += "<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\""+xsq+"_"+hs+"_right_text\" value=\"\"/>";
			divHtml += "</td>";
			divHtml += "<td width=\"30%\">";
			divHtml += "<button onclick=\"clickXsqBtn(this)\" id=\""+xsq+"_"+hs+"_right\">应用</button>";
			divHtml += "</td>";
			divHtml += "</tr>";
			divHtml += "</table>";
					
		$(div_id).innerHTML = divHtml;
	}
	
	hiddenMessage(true,true);
}

//删除显示区Tr
function delXsqTr(){
	
	var div_id = $("checked_tr").value;
	
	//退还待分配字段
	returnDfpZd();
	
	var xsq = div_id.split("_")[0];//显示区
	var sch = div_id.split("_")[1];//删除行
	
	var szh = "szhs"+xsq.replace("xsq","");
	var syhs = parseInt($(szh).value) - 1;//剩余行数
	
	//不可删除非最后一行
	if(sch!=$(szh).value){
		alert("只能够删除显示区的最后一行，请确认！");
		return false;
	}
	
	//仅剩一行的时候不可删除
	if(syhs !=0){
		$(szh).value = syhs;
	}else{
		alert("已经是该显示区的最后一行，不可删除！\n注：如果想删除整个显示区的话，请勾\n    选该显示区，并执行删除操作。");
		return false;
	}
	
	if($(div_id)){
		$(div_id).innerHTML = "";
	}

	
	hiddenMessage(true,true);
}

//增加显示区Trv
function addXsqTr(){

	var tr_id = $("checked_tr").value;
	var xsq = tr_id.split("_")[0];//显示区
	var czh = tr_id.split("_")[1];//操作行
	var xsqdm = xsq.replace("xsq","");//显示区代码
	var szh = "szhs"+xsqdm;
	var newhs = parseInt($(szh).value) + 1;//新行数
	
	var xsq_div_id = xsq+"_div";
	var xsqHtml = $(xsq_div_id).innerHTML;
	
	$(xsq_div_id).innerHTML = "";
	
	xsqHtml+="<table class=\"formlist\" border=\"0\" align=\"center\" style=\"width: 90%\">";
	xsqHtml+="<tbody><tr onclick=\"rowOnClick(this);\" id=\""+xsq+"_"+newhs+"_tr\"><td>";
	xsqHtml+="<div id=\""+xsq+"_"+newhs+"_div\">";
	xsqHtml+="<table width=\"100%\">";
	xsqHtml+="<tr>";
	
	xsqHtml+="<td width=\"20%\">";
	xsqHtml+="<p id=\"xsq"+xsqdm+"_"+newhs+"_left_p\"></p>";
	xsqHtml+="<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\"xsq"+xsqdm+"_"+newhs+"_left_text\"/>";
	xsqHtml+="</td>";
	
	xsqHtml+="<td width=\"30%\">";
	xsqHtml+="<button onclick=\"clickXsqBtn(this)\" id=\"xsq"+xsqdm+"_"+newhs+"_left\">应用</button>";
	xsqHtml+="</td>";
	
	xsqHtml+="<td width=\"20%\">";
	xsqHtml+="<p id=\"xsq"+xsqdm+"_"+newhs+"_right_p\"></p>";
	xsqHtml+="<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\"xsq"+xsqdm+"_"+newhs+"_right_text\"/>";
	xsqHtml+="</td>";
	
	xsqHtml+="<td width=\"30%\">";
	xsqHtml+="<button onclick=\"clickXsqBtn(this)\" id=\"xsq"+xsqdm+"_"+newhs+"_right\">应用</button>";
	xsqHtml+="</td>";
	xsqHtml+="</tr>";
	xsqHtml+="</table>";
	xsqHtml+="</div>";
	xsqHtml+="</table>";
	
	$(xsq_div_id).innerHTML = xsqHtml;
	
	hiddenMessage(true,true);
	
	//清空背景颜色
	var num = $(xsq_div_id).getElementsByTagName("tr").length;
	for(var i=0;i<num;i++){
		var obj = $(xsq_div_id).getElementsByTagName("tr")[i];
		obj.style.backgroundColor = "";
	}
	
	$("szhs"+xsqdm).value = newhs;//行数
}

/** ===============================单行操作 end==================================*/

/** ===============================待分配字段==================================*/
//退还待分配字段
function returnDfpZd(){

	var div_id = $("checked_tr").value;
	var xsq = div_id.split("_")[0];//显示区
	var xsqdm = xsq.replace("xsq","");//显示区代码
	var hs = div_id.split("_")[1];//行数

	var p_left_id = xsq+"_"+hs+"_left_p";
	var p_right_id = xsq+"_"+hs+"_right_p";
	var text_left_id = xsq+"_"+hs+"_left_text";
	var text_right_id = xsq+"_"+hs+"_right_text";

	var zd = new Array();
	var zdm = new Array();
	var n = 0;
	
	//左字段
	if($(p_left_id) && $(text_left_id) && $(p_left_id).innerHTML !="" && $(text_left_id).value != "" ){
		zd[n] = $(text_left_id).value;
		zdm[n] = $(p_left_id).innerHTML;
		n++;
	}
	
	//右字段
	if($(p_right_id) && $(text_right_id) && $(p_right_id).innerHTML !="" && $(text_right_id).value != "" ){
		zd[n] = $(text_right_id).value;
		zdm[n] = $(p_right_id).innerHTML;
		n++;
	}
	

	//将字段返回待分配
	var m = $("qyzd").options.length;
	for(var j=0;j<n;j++){
		var value=zd[j];
		var text=zdm[j];
		$("qyzd").options[m] = new Option(text,value);
		m++;
	}
}
/** ===============================待分配字段 end==================================*/

/** ===============================显示区操作==================================*/

//保存显示区
function saveYmsz(){
	if (confirm("将要保存所设置的显示区，请确认！")) {
		//创建需要保存的字段信息
		creatZdInfo();
		//执行保存操作
		saveUpdate('/xgxt/xsxxJbsz.do?method=ymjbsz&doType=save','');
	}
}

//根据设置生成新的显示区
function setXsqBySz(){

	//显示区名称
	var xsqmc = $("sz_xsqmc_value").value;
	//所占行数
	var szhs = $("sz_szhs_value").value;
	//照片显示
	var zpxs = $("sz_zpxs_value").value;
	//照片位置
	var zpwz = $("sz_zpwz_value").value;
	//照片所占行
	var zpszhs = $("sz_zpszhs_value").value;
	//显示区
	var xsqNum = $("xsqNum").value;
	
	if(xsqmc == ""){
		alert("显示区名称不可为空，请确认！");
		return false;
	}

	if(szhs == ""){
		alert("所占行数不可为空，请确认！");
		return false;
	}
	
	if(zpxs == "是"){
	
		if(szhs < 6){
			alert("如果需要显示照片的话，所占行数不可小于6行，请确认！");
			return false;
		}
		
		if(zpszhs == ""){
			alert("照片所占行数不可为空，请确认！");
			return false;
		}

		if(zpszhs > szhs){
			alert("照片所占行不能大于显示区所占行，请确认！");
			return false;
		}
	}
	
	var xsqHtml = $("xsqNr").innerHTML;	
	var xsqdm = parseInt(xsqNum)+1;
		
	xsqHtml +="<table class=\"formlist\" border=\"0\" align=\"center\" style=\"width: 90%\" id=\"xsq"+xsqdm+"\">";
	xsqHtml +="<thead><tr><th colspan=\"4\"><span><a href=\"#\" onclick=\"hiddenXsq('"+xsqdm+"')\">"+xsqmc+"</span>";
	xsqHtml +="<input type=\"hidden\" name=\"xsqdm\" value=\""+xsqdm+"\"/>";//显示区代码
	xsqHtml +="<input type=\"hidden\" name=\"xsqmc\" value=\""+xsqmc+"\"/>";//显示区名称
	xsqHtml +="<input type=\"hidden\" name=\"szhs\" id=\"szhs"+xsqdm+"\" value=\""+szhs+"\"/>";//所占行数
	xsqHtml +="<input type=\"hidden\" name=\"zpxs\" value=\""+zpxs+"\"/>";//照片显示
	xsqHtml +="<input type=\"hidden\" name=\"zpwz\" value=\""+zpwz+"\"/>";//照片位置
	xsqHtml +="<input type=\"hidden\" name=\"zpszhs\" value=\""+zpszhs+"\"/>";//照片所占行
	xsqHtml +="<input type=\"hidden\" name=\"xssx\" value=\""+xsqdm+"\"/>";//显示顺序
	xsqHtml +="<input type=\"checkbox\" id=\"xsq"+xsqdm+"_checkbox\" name=\"xsq_checkbox\" value=\""+xsqdm+"\"/>"
	xsqHtml +="选择本显示区";
										
	xsqHtml +="</th></tr></thead>";
	xsqHtml +="<tbody id=\"xsq"+xsqdm+"_tb\">";
	
	//起始行
	var qsh = 1;
		
	//需要显示照片
	if(zpxs == "是"){
		
		xsqHtml +="<tr id=\"xsq"+xsqdm+"_1_tr\">";
		xsqHtml +="<td colspan=\"4\">";
		xsqHtml +="<div id=\"xsq"+xsqdm+"_1_div\">";	
		xsqHtml +="<table width=\"100%\">";
			
		if(zpwz == "右"){
			
			//左
			xsqHtml +="<tr><td width=\"20%\">";
			xsqHtml +="<p id=\"xsq"+xsqdm+"_1_left_p\"></p>";
			xsqHtml +="<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\"xsq"+xsqdm+"_1_left_text\"/>";
			xsqHtml +="</td>";
			
			xsqHtml +="<td width=\"30%\">";
			xsqHtml +="<button onclick=\"clickXsqBtn(this)\" id=\"xsq"+xsqdm+"_1_left\">应用</button>";
			xsqHtml +="</td>"
			//右
			xsqHtml +="<td width=\"20%\" rowspan=\"6\">学</br>生</br>照</br>片</td>";
			xsqHtml +="<td width=\"30%\" rowspan=\"6\">";
			xsqHtml +="<img src=\"/xgxt/images/type_pic.gif\"/>";
			xsqHtml +="</td></tr>";
			
			for(var i=2;i<=zpszhs;i++){
				//左
				xsqHtml +="<tr><td width=\"20%\">";
				xsqHtml +="<p id=\"xsq"+xsqdm+"_"+i+"_left_p\"></p>";
				xsqHtml +="<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\"xsq"+xsqdm+"_"+i+"_left_text\" value=\"left\"/>";
				xsqHtml +="</td>";
				
				xsqHtml +="<td width=\"30%\">";
				xsqHtml +="<button onclick=\"clickXsqBtn(this)\" id=\"xsq"+xsqdm+"_"+i+"_left\">应用</button>";
				xsqHtml +="</td>";
			}
			
		}else{
			//左
			xsqHtml +="<tr><td width=\"20%\" rowspan=\"6\">学</br>生</br>照</br>片</td>";
			xsqHtml +="<td width=\"30%\" rowspan=\"6\">";
			xsqHtml +="<img src=\"/xgxt/images/type_pic.gif\"/>";
			xsqHtml +="</td>";
			
			//右
			xsqHtml +="<td width=\"20%\">";
			xsqHtml +="<p id=\"xsq"+xsqdm+"_1_right_p\"></p>";
			xsqHtml +="<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\"xsq"+xsqdm+"_1_right_text\"/>";
			xsqHtml +="</td>";
			
			xsqHtml +="<td width=\"30%\">";
			xsqHtml +="<button onclick=\"clickXsqBtn(this)\" id=\"xsq"+xsqdm+"_1_right\">应用</button>";
			xsqHtml +="</td></tr>"
			
			for(var i=2;i<=zpszhs;i++){
				//左
				xsqHtml +="<tr><td width=\"20%\">";
				xsqHtml +="<p id=\"xsq"+xsqdm+"_"+i+"_right_p\"></p>";
				xsqHtml +="<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\"xsq"+xsqdm+"_"+i+"_right_text\" value=\"right\"/>";
				xsqHtml +="</td>";
				
				xsqHtml +="<td width=\"30%\">";
				xsqHtml +="<button onclick=\"clickXsqBtn(this)\" id=\"xsq"+xsqdm+"_"+i+"_right\">应用</button>";
				xsqHtml +="</td>";
			}
		}
			
		xsqHtml +="</table>";			
		xsqHtml +="</div>";
		xsqHtml +="</td>";
		xsqHtml +="</tr>";
			
		qsh = parseInt(zpszhs)+1;
	}
	
	
	for(var i=qsh;i<=szhs;i++){
		xsqHtml +="<tr onclick=\"rowOnClick(this);\" id=\"xsq"+xsqdm+"_"+i+"_tr\">";
		xsqHtml +="<td colspan=\"4\">";
		xsqHtml +="<div id=\"xsq"+xsqdm+"_"+i+"_div\">";	
		xsqHtml +="<table width=\"100%\"><tr>";
		//左
		xsqHtml +="<td width=\"20%\">";
		xsqHtml +="<p id=\"xsq"+xsqdm+"_"+i+"_left_p\"></p>";
		xsqHtml +="<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\"xsq"+xsqdm+"_"+i+"_left_text\"/>";
		xsqHtml +="</td>";
		
		xsqHtml +="<td width=\"30%\">";
		xsqHtml +="<button onclick=\"clickXsqBtn(this)\" id=\"xsq"+xsqdm+"_"+i+"_left\">应用</button>";
		xsqHtml +="</td>";
		
		//右
		xsqHtml +="<td width=\"20%\">";
		xsqHtml +="<p id=\"xsq"+xsqdm+"_"+i+"_right_p\"></p>";
		xsqHtml +="<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\"xsq"+xsqdm+"_"+i+"_right_text\"/>";
		xsqHtml +="</td>";
		
		xsqHtml +="<td width=\"30%\">";
		xsqHtml +="<button onclick=\"clickXsqBtn(this)\" id=\"xsq"+xsqdm+"_"+i+"_right\">应用</button>";
		xsqHtml +="</td>";
		
		xsqHtml +="</tr></table>";									
		xsqHtml +="</div>";
		xsqHtml +="</td>";
		xsqHtml +="</tr>";
	}
		
	xsqHtml +="</tbody>";
	xsqHtml +="</table>";
	xsqHtml +="</br>";
	
	$("xsqNr").innerHTML = "";
	$("xsqNr").innerHTML = xsqHtml;
		
	$("xsqNum").value = xsqdm;
	
	hiddenMessage(true,true);
}

//隐藏显示区
function hiddenXsq(xsqid){
	var id = "xsq"+xsqid+"_tb";
	if($(id)){
		if($(id).style.display == "none"){
			$(id).style.display = "";
		}else{
			$(id).style.display = "none";
		}
	}
}

//删除显示区
function delXsq(){

	var num = document.getElementsByName("xsq_checkbox").length;
	var flag = true;
	
	for(var i=0;i<num;i++){
		var obj = document.getElementsByName("xsq_checkbox")[i];
		if(obj.checked){
			flag = false;
		}
	}
	
	if(flag){
		alert("请勾选希望删除的显示区！");
		return false;
	}
	
	if (confirm("将要删除所勾选的显示区，请确认！\n(注：删除完后需要执行保存操作才能真正删除)")) {
		for(var i=(num-1);i>=0;i--){
			var obj = document.getElementsByName("xsq_checkbox")[i];
			var xsqdm = obj.value;
			if(obj.checked){
				var id = "xsq"+xsqdm;
				if($(id)){
				
					var zdCount = document.getElementsByName("yyzd"+xsqdm).length;
					var zdmCount = $(id).getElementsByTagName("p").length;
					
					var zd = new Array();
					var zdm = new Array();
					
					var n=0;

					for(var j=0;j<zdCount;j++){
						var zdObj = document.getElementsByName("yyzd"+xsqdm)[j];
						if(zdObj.value != ""){
							zd[n]=zdObj.value;
							n++;
						}
					}
					
					n = 0;
					
					for(var j=0;j<zdmCount;j++){
						var zdmObj = $(id).getElementsByTagName("p")[j];
						if(zdmObj.innerHTML != ""){
							zdm[n]=zdmObj.innerHTML;
							n++;
						}
					}
					
					var m = $("qyzd").options.length;
					for(var j=0;j<n;j++){
						var value=zd[j];
						var text=zdm[j];
						$("qyzd").options[m] = new Option(text,value);
						m++;
					}
					
					$(id+"_div").outerHTML = "";
				}
			}
		}
	}
}

//点击显示区按钮
function clickXsqBtn(obj){
	//btnID
	var btn_Id = obj.id;
	var btn_nr = obj.innerHTML;
	//字段列表
	var optionSize = $("qyzd").options.length;

	if(btn_nr == "应用"){
		if(optionSize != "0"){
			obj.innerHTML = "取消";
			yyBtn(btn_Id);
		}else{
			alert("待分配字段列表已经为空，请确认！");
			return false;
		}
	}else{
		obj.innerHTML = "应用";
		qxBtn(btn_Id);
	}
}

//应用
function yyBtn(btn_Id){
	//启用字段
	var qyzd = $("qyzd");
	var selectNum = qyzd.selectedIndex;
	var selectEd = selectNum;
	var optionSize = qyzd.options.length;

	if(selectEd == optionSize - 1){
		selectEd = parseInt(selectNum) - 1;
	}
	
	var text = qyzd.options[selectNum].text;
	var value = qyzd.options[selectNum].value;

	qyzd.options[selectNum]=null;
	if(optionSize != 1 ){
		qyzd.options[selectEd].selected = "true";
	}
	
	//P_ID
	var p_Id = btn_Id+"_p";
	//Text_ID
	var text_Id = btn_Id+"_text";
	if($(p_Id)){
		$(p_Id).innerHTML = text;
	}
	
	if($(text_Id)){
		$(text_Id).value = value;
	}
}

//取消
function qxBtn(btn_Id){
	
	//P_ID
	var p_Id = btn_Id+"_p";
	var text = $(p_Id).innerHTML;
	$(p_Id).innerHTML = "";

	//Text_ID
	var text_Id = btn_Id+"_text";
	var value = $(text_Id).value;
	$(text_Id).value = "";
	
	//启用字段
	var qyzd = $("qyzd");
	qyzd.options[qyzd.options.length] = new Option(text,value);
	qyzd.options[qyzd.options.length-1].selected = "true";
	
}

/** ===============================显示区操作 end==================================*/

/** ===============================其他操作==================================*/
//点击单选框
function clickRadioValue(num,mk,value){
	var id = "hid_"+mk+"_"+num;
	$(id).value = value;
}

//checked是否显示照片
function checkedZpxs(zpxs){
	if(zpxs == "是"){
		$("zpwz_tr").style.display = "";
		$("zpszhs_tr").style.display = "";
	}else{
		$("zpwz_tr").style.display = "none";
		$("zpszhs_tr").style.display = "none";
	}
}

//设置弹出层得值
function setDivHiddenValue(lx,value){
	var id = "sz_"+lx+"_value";
	$(id).value = value;
}

//创建需要保存的字段信息
function creatZdInfo(){
	var xsq_num = document.getElementsByName("xsqdm").length;
	//显示区
	for(var i=0;i<xsq_num;i++){
	
		var xsq_obj = document.getElementsByName("xsqdm")[i];
		var xsqdm = xsq_obj.value;
		var zdid = "yyzd"+xsqdm;
		
		var zd_num = document.getElementsByName(zdid).length;
		
		//字段
		for(var j=0;j<zd_num;j++){
			var zd_obj = document.getElementsByName(zdid)[j];
			if(zd_obj.value != ""){
				var id = zd_obj.id;
				var zd_szxsq = xsqdm;//字段所在显示区
				var zd = zd_obj.value;//字段
				var szh = id.split("_")[1];//所在行
				var szl = id.split("_")[2];//所在列

				if(szl == "left"){
					szl = "1";
				}else{
					szl = "3";
				}
				//创建需要保存的字段信息
				var xsq_obj = document.createElement("input");
					xsq_obj.type = "text";
					xsq_obj.name = "zd_szxsq";
					xsq_obj.value = zd_szxsq;
				
				var zd_obj = document.createElement("input");
					zd_obj.type = "text";
					zd_obj.name = "zd";
					zd_obj.value = zd;
					
				var szh_obj = document.createElement("input");
					szh_obj.type = "text";
					szh_obj.name = "szh";
					szh_obj.value = szh;
				
				var szl_obj = document.createElement("input");
					szl_obj.type = "text";
					szl_obj.name = "szl";
					szl_obj.value = szl;
					
				document.forms[0].appendChild(xsq_obj);
				document.forms[0].appendChild(zd_obj);
				document.forms[0].appendChild(szh_obj);
				document.forms[0].appendChild(szl_obj);
			}
		}
	}
}

//设置启用字段值
function setQyzdValue(){

	var qyzd = $("qyzd");
	var optionSize = qyzd.options.length;
	
	if(optionSize > 0 ){
		qyzd.options[0].selected = "true";
	}
}
/** ===============================其他操作 end==================================*/