function isPkNull(pk){
	var key = new Array();
	if(pk!=""){
		key=pk.split("-");
	}

	if(key.length > 0){
		for(var i=0;i<key.length;i++){
			if($(key[i])){
				if($(key[i]).value == ""){
					alert("带\"*\"项目不能为空，请确认");
					return false;
				}
			}
		}
	}
	
	return true;
}

//打开新窗口
function showInfo(url,doType,w,h){
	if(doType == "update"){
		if(curr_row == null){
			alert('请选择要修改的数据！');
			return false;
		}
	}else if(doType == "sh"){
		if(curr_row == null){
			alert('请选择审核的数据！');
			return false;
		}
	}else if(doType == "sb"){
		if(curr_row == null){
			alert('请选择要申报的数据！');
			return false;
		}
	}else if(doType == "cz"){
		if(curr_row == null){
			alert('请选择要操作的数据！');
			return false;
		}
	}else if(doType == "view"){
		if(curr_row == null){
			alert('请选择要查看的数据！');
			return false;
		}
	}
	var pk = curr_row.getElementsByTagName('input')[0].value;
	url+="&doType="+doType;
	url+="&pk="+pk;
	showTopWin(url,w,h);
}

//打开新窗口
function showOpenInfo(url,doType,mklx,w,h){

	var pk = "";
	
	if(doType == "update"){
		if(curr_row == null){
			alert('请选择要修改的数据！');
			return false;
		}
	}else if(doType == "sh"){
		if(curr_row == null){
			alert('请选择审核的数据！');
			return false;
		}
	}else if(doType == "sb"){
		if(curr_row == null){
			alert('请选择要申报的数据！');
			return false;
		}
	}
	
	if(doType != "add"){
		pk = curr_row.getElementsByTagName('input')[0].value;
	}
	
	url+="&doType="+doType;
	url+="&pk="+pk;
	if(mklx != ""){
		url+="&mklx="+mklx;
	}
	
	showOpenWindow(url,w,h);
}

//打开新页面
function openInfo(url){
	if(curr_row == null){
		alert('请选择要操作的数据！');
		return false;
	}
	var pk = curr_row.getElementsByTagName('input')[0].value;
	url+="&pk="+pk;
	window.open(url);
}

//批量提交
function sumitInfo(url,doType){
	var len=jQuery("input:checkbox[name=primarykey_checkVal]:checked").length;
	if(len > 0){
		if(doType=="del"){
			url+="&doType="+doType;
			if (confirm("确定要删除所勾选的数据?")) {
				showTips('处理数据中，请等待......');
				refreshForm(url);
			}
		}else if (doType=="sh"){
			url+="&doType="+doType;
			if (confirm("确定所勾选数据的审核状态?")) {
				showTips('处理数据中，请等待......');
				refreshForm(url);
			}
		}else if (doType=="xmDel"){
			url+="&doType="+doType;
			var msg = "确定要删除所勾选的项目?";
				msg+="\n";
				msg+="\n注：删除项目的同时将删除该项目的历年学生申请信息，"
				msg+="\n    请及时备份相关数据,若仅希望本次不让学生申请，";
				msg+="\n    只需要将项目开关设置为'项目关闭'即可。";
			if (confirm(msg)) {
				showTips('处理数据中，请等待......');
				refreshForm(url);
			}
		}
	}else{
		alert("请勾选要处理的数据");
		return false;
	}
}

function sendXx(){
	var tableName=$("tableName").value;
	var lx="";if($("lx")){lx=$("lx").value};
	var zd="";if($("zd")){zd=$("zd").value};
	var va="";if($("va")){va=$("va").value};
	var url = "/xgxt/czxxDtjsDyxx.do?method=xsxxManage";
	url+="&tableName="+tableName;
	if(lx !=""){
		url+="&lx="+lx;
	}
	if(zd !=""){
		url+="&zd="+zd;
	}
	if(va !=""){
		url+="&va="+va;
	}
	showTopWin(url,800,600);
}

function checkNull(obj){
	if(obj.value == ""){
		obj.value = "0";
	}
}

function addBjP(){

	dwr.engine.setAsync(false);

	var fromIndx = $("bj").selectedIndex;
	var toIndx = $("bjP").options.length;
	if(fromIndx < 0){
		return false;
	}

	if($("bj").options[fromIndx].value == "" || $("bj").options[fromIndx].value == null || $("bj").options[fromIndx].value == "null"){
		return false;
	}
	
	for(var i=0;i<toIndx;i++){
		if($("bj").options[fromIndx].value == $("bjP").options[i].value){
			return false;
		}
	}
	$("bjP").options[$("bjP").options.length] = new Option($("bj").options[fromIndx].text,$("bj").options[fromIndx].value);
	$("bj").options[fromIndx] = null;
	if($("bj").options.length > 0){
		$("bj").options[0].selected = true;
		//$("delBj").disabled = false;
	}else{		
		//$("addBj").disabled = true;
	}
	if($("bjP").options.length > 0){
		$("bjP").options[0].selected = true;
	}
	
	dwr.engine.setAsync(true);
}

function delBjP(){

	dwr.engine.setAsync(false);
	
	var toIndx = $("bjP").selectedIndex;
	var fromIndx = $("bj").options.length;
	if(toIndx < 0){
		return false;
	}
	for(var i=0;i<fromIndx;i++){
		if($("bjP").options[toIndx].value == $("bj").options[i].value){
			$("bjP").options[toIndx] = null;
			return false;
		}
	}
	$("bj").options[$("bj").options.length] = new Option($("bjP").options[toIndx].text,$("bjP").options[toIndx].value);
	$("bjP").options[toIndx] = null;

	if($("bj").options.length > 0){
		$("bj").options[0].selected = true;
	}
	
	dwr.engine.setAsync(true);
}

//设置只读
function setReadonly(){
	var userType = $("userType").value;
	var mklx = $("mklx").value;
	var realTable = $("realTable").value;
	
	//学生资助_经济困难生
	if(realTable == "hndx_xszz_jjknsrd"){
	
	var isBjsh = $("isBjsh").value;
	
		//审核
		if(mklx == "sh"){
			
			//班级
			if(isBjsh == "true"){
				$("xyshyj").readOnly = true;
				$("xxshyj").readOnly = true;
			}//学校
			else if(userType == "xx" || userType == "admin"){
				$("xyshyj").readOnly = true;
				$("bjshyj").readOnly = true;
			}
			//学院
			else if(userType == "xy"){
				$("xxshyj").readOnly = true;
				$("bjshyj").readOnly = true;
			}
		}
		//结果
		else if(mklx == "jg"){
			$("bjshyj").readOnly = true;
			$("xyshyj").readOnly = true;
			$("xxshyj").readOnly = true;
		}
	}
}

//审核数据
function shInfo(url,doType,pk,shjg,zd){

	var key = new Array();
	var pkValue = "";
	
	if(pk!=""){
		key=pk.split("-");
	}
	
	if(key.length > 0){
		for(var i=0;i<key.length;i++){
			if($(key[i])){
				pkValue += $(key[i]).value;
			}
		}
	}
	
	var shzd = $("shzd").value;
	var id = "save_"+shzd;
	$(id).value = shjg;
	
	url+="&doType="+doType;
	url+="&pk="+pk;
	url+="&pkValue="+pkValue;
	
	var msg = "确定该审核状态？";
	if (confirm(msg)) {
		saveUpdate(url,zd);
	}
}

var array = new Array();

function isChecked(tagName) {
	var checkBoxArr = document.getElementsByName(tagName);
	var flag = false;
	var n=0;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
			array[n]=checkBoxArr[i];
			n++;
		}
	}
	if (flag){
		if (confirm('确定要批量设置所选择的数据吗？')){
			return true;
		}
	}else{
		alert("没有选择相应记录，请选择之后再进行操作!");
		return false;
	}
}

function showCalen(id){
	return showCalendar(id,'y-mm-dd');
}

function showPlszDiv() {

	if (!isChecked("primarykey_cbv")){
		return false;
	}

	$('tempDiv').style.display='';
	
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 340;
	var d_height_top = 180;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
					
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<table width='300' class='formlist'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td colspan='2'>";
	dd_html += "时间设置";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead><tbody>";
	dd_html += "<tr><td align='right'><font color='red'>*</font>开始时间:</td><td><input type='text' id='startTime' onclick='showCalen(this.id)' name='kssj' onblur='dateFormatChg(this)'/></td></tr>";
	dd_html += "<tr><td align='right'><font color='red'>*</font>结束时间:</td><td><input type='text' id='overTime' onclick='showCalen(this.id)' name='jssj' onblur='dateFormatChg(this)'/></td></tr></tbody>";
	dd_html += "<tfoot><tr><td align='center' colspan='2'><div class='btn'><button class='button2'  onclick='plsz();'>确 定</button><button class='button2' onclick='closeDiv();'>取 消</button></div></td></tr></tfoot></table>";

	tempDiv.innerHTML = dd_html;
}


//关闭临时DIV
function closeDiv() {
	$('tempDiv').style.display='none';
}

function plsz() {
	var kssj = document.getElementById('startTime').value;
	var jssj = document.getElementById('overTime').value;
	
	if (''==kssj || ''==jssj) {
		alert('请把带*项填写完整!');
		return false;
	}
	refreshForm('/xgxt/zgdzdx_xszz.do?method=xssjsz&doType=batchSave');
	closeDiv();
}

/**
*左边框的值得添加到右边框，添加成功同时，删除原有值得
*luojw
*20100628
*/
function addAllRightFrame(left,right){

	dwr.engine.setAsync(false);

	var obj =  document.getElementById(left);
	
	var count = 0; 
	
	for(var i=0;i<obj.options.length;i++){
		if(obj.options[i].selected){
			count++;
		}
	}
    
	if(count==0){
		alert('请至少选择一行数据！');
		return false;
	}
	
	var leftV = "";
	var leftT = "";   
    var n = $(right).options.length;

	for(i=0;i<obj.options.length;i++){
	
		if(obj.options[i].selected){

			leftV=obj.options[i].value;
			leftT=obj.options[i].text;

			if(leftV == ""){
				continue;
			}
			
			var flag = true;
			
			for(var j=0;j<n;j++){
				if(leftV == $(right).options[j].value){
					flag = false;
				}
			}
			
			if(flag){
				$(right).options[$(right).options.length] = new Option(leftT,leftV);
				$(left).options[i]=null;
				i--; 
			}                
		}
	}   
	dwr.engine.setAsync(true);
}

/**
*右边框的值得添加到左边框，添加成功同时，删除原有值得
*luojw
*20100628
*/
function addAllLeftFrame(left,right){

	dwr.engine.setAsync(false);

	var obj =  document.getElementById(right);
	
	var count = 0; 
	
	for(var i=0;i<obj.options.length;i++){
		if(obj.options[i].selected){
			count++;
		}
	}
    
	if(count==0){
		alert('请在至少选择一行数据！');
		return false;
	}
	
	var rightV = "";
	var rightT = "";  
	 
    var n = $(left).options.length;

	for(i=0;i<obj.options.length;i++){
	
		if(obj.options[i].selected){

			rightV=obj.options[i].value;
			rightT=obj.options[i].text;

			if(rightV == ""){
				continue;
			}
			
			var flag = true;
			
			for(var j=0;j<n;j++){
				if(rightV == $(left).options[j].value){
					flag = false;
				}
			}
			
			if(flag){
				$(left).options[$(left).options.length] = new Option(rightT,rightV);
				$(right).options[i]=null;
				i--; 
			}                
		}
	}   
	dwr.engine.setAsync(true);
}

function addAllOfRightFrame(left,right){

	dwr.engine.setAsync(false);

	var obj =  document.getElementById(left);
	
	var count = obj.options.length; 
	
	var leftV = "";
	var leftT = "";   
    var n = $(right).options.length;

	for(i=0;i<obj.options.length;i++){
	

			leftV=obj.options[i].value;
			leftT=obj.options[i].text;

			if(leftV == ""){
				continue;
			}
			
			var flag = true;
			
			for(var j=0;j<n;j++){
				if(leftV == $(right).options[j].value){
					flag = false;
				}
			}
			
			if(flag){
				$(right).options[$(right).options.length] = new Option(leftT,leftV);
				$(left).options[i]=null;
				i--; 
			}                
		
	}   
	dwr.engine.setAsync(true);
}

function addAllOfLeftFrame(left,right){

	dwr.engine.setAsync(false);

	var obj =  document.getElementById(right);
	
	var count = obj.options.length; 
	
	if(count==0){
		alert('请在至少选择一行数据！');
		return false;
	}
	
	var rightV = "";
	var rightT = "";  
	 
    var n = $(left).options.length;

	for(i=0;i<obj.options.length;i++){

			rightV=obj.options[i].value;
			rightT=obj.options[i].text;

			if(rightV == ""){
				continue;
			}
			
			var flag = true;
			
			for(var j=0;j<n;j++){
				if(rightV == $(left).options[j].value){
					flag = false;
				}
			}
			
			if(flag){
				$(left).options[$(left).options.length] = new Option(rightT,rightV);
				$(right).options[i]=null;
				i--; 
			}                
	}   
	dwr.engine.setAsync(true);
}