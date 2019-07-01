function dtjsCommonSave(url,yzdz,yzcd,mustFill){
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("请将带\"*\"号的项目输入完整！");
			return;
		}
	}
	var splitDz = yzdz.split("-");
	var splitCd = yzcd.split("-");
	if(splitDz[0]!=""){
	for (i = 0; i < splitDz.length; i++) {
		var dzsjcd = $(splitDz[i]).cells[1].getElementsByTagName('textarea')[0].value;
		var dzsjmc = $(splitDz[i]).cells[0].innerHTML;
		if (dzsjcd.length>splitCd[i]) {
			alert(dzsjmc+"不能超过"+splitCd[i]+"个字！");
			return;
		}
	}
	}
	document.forms[0].action = url;
	document.forms[0].submit();
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
		//if(doType=="del"){
			url+="&doType="+doType;
			if (confirm("确定要删除所勾选的数据?")) {
				showTips('处理数据中，请等待......');
				refreshForm(url);
			}
		//}else{
			
		//}
	}else{
		alert("请勾选要处理的数据");
		return false;
	}
}

 /**
 *   指定增加学号字段数据出处
 *   luojw
 *   2010-6-28
 */
function sendXx(){
	//页面存隐藏域，指定所查询数据出处，例如（<input type="hidden" id="tableName" name="tableName" value="view_ybdyxx"/>）
	var tableName=$("tableName").value;
	//数据源的描述，展示jsp的表头，例如（<input type="hidden" id="lx" name="lx" value="预备党员"/>）
	var lx="";
	//数据源的特殊条件，例如:
	//<input type="hidden" id="zd" name="zd" value="sfty-xxsh"/>
	//<input type="hidden" id="va" name="va" value="是-通过"/>
	var zd="";
	var va="";
	var url = "/xgxt/czxxDtjsDyxx.do?method=xsxxManage";
	
	if($("lx")){
		lx=$("lx").value
	};
	if($("zd")){
		zd=$("zd").value
	};
	if($("va")){
		va=$("va").value
	};
	
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
/**
*左边框的值得添加到右边框，添加成功同时，删除原有值得
*luojw
*20100628
*/
function addBjP(){

	dwr.engine.setAsync(false);
	//左边框空间命名为bj(可以考虑传入参数)
	var fromIndx = $("bj").selectedIndex;
	//右边框空间命名为bjP(可以考虑传入参数)
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

/**
*右边框的值得添加到右边框，添加成功同时，删除原有值得
*luojw
*20100628
*/
function delBjP(){

	dwr.engine.setAsync(false);
	//右边框空间命名为bjP(可以考虑传入参数)
	var toIndx = $("bjP").selectedIndex;
	//左边框空间命名为bj(可以考虑传入参数)
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

function setXylist(){

	var nj = $("nj").value;
	var objId = "xydm";
	
	getPjpyInfo.getXyList(nj,function(data){
		if(data !=null && data.length >0){
			if(data !=null && data.length >0){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"dm","mc");
			}
		}
	});
}

/**
*左边框的值得添加到右边框，添加成功同时，删除原有值得
*luojw
*20100628
*/
function addRightFrame(left,right){

	dwr.engine.setAsync(false);
	
	var fromIndx = $(left).selectedIndex;
	var toIndx = $(right).options.length;

	if(fromIndx < 0){
		return false;
	}

	if($(left).options[fromIndx].value == "" || $(left).options[fromIndx].value == null || $(left).options[fromIndx].value == "null"){
		return false;
	}
	
	for(var i=0;i<toIndx;i++){
		if($(left).options[fromIndx].value == $(right).options[i].value){
			return false;
		}
	}
	
	$(right).options[$(right).options.length] = new Option($(left).options[fromIndx].text,$(left).options[fromIndx].value);
	$(left).options[fromIndx] = null;
	if($(left).options.length > 0){
		$(left).options[0].selected = true;
		//$("delBj").disabled = false;
	}else{		
		//$("addBj").disabled = true;
	}
	if($(right).options.length > 0){
		$(right).options[0].selected = true;
	}
	
	dwr.engine.setAsync(true);
}

/**
*右边框的值得添加到左边框，添加成功同时，删除原有值得
*luojw
*20100628
*/
function addLeftFrame(left,right){

	dwr.engine.setAsync(false);
	
	var toIndx = $(right).selectedIndex;
	var fromIndx = $(left).options.length;
	if(toIndx < 0){
		return false;
	}
	for(var i=0;i<fromIndx;i++){
		if($(right).options[toIndx].value == $(left).options[i].value){
			$(right).options[toIndx] = null;
			return false;
		}
	}
	$(left).options[$(left).options.length] = new Option($(right).options[toIndx].text,$(right).options[toIndx].value);
	$(right).options[toIndx] = null;

	if($(left).options.length > 0){
		$(left).options[0].selected = true;
	}
	
	dwr.engine.setAsync(true);
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
		alert('请在至少选择一行数据！');
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

function printZhszcphzb(){
	var xxdm = val("xxdm");
	var url = "";
	if(xxdm == "10352"){//丽水学院
		url = "pjpyLsxy.do?method=printZhszcphzb";
		if(val('xn') == ""){
			alert("请选择学年！");
			return false;
		}
		if(val('xq') == ""){
			alert("请选择学期！");
			return false;
		}
		if(val('bj') == ""){
			alert("请选择班级！");
			return false;
		}
		//打印
		url += "&xn=" 
		url += val("xn");
		url += "&xq=" 
		url += val("xq");
		url += "&xqmc=" 
		url += selText("xq");
		url += "&bjdm=" 
		url += val("bj");
		url += "&bjmc=" 
		url += selText("bj");
		
		showOpenWindow(url,800,600);
	}
}

/**
 * 变换选项卡
 * @param obj
 * */
function changeCard(obj){
	this.parentNode.className = 'ha';
}


/**
 * 显示第一个选项卡
 * */
function loadFirstCard(){
	var tab = document.getElementById("firstCard").value;
	ele(tab).parentNode.className = 'ha';
}

function zhcpjxjsb(){
	var checkBoxArr = document.getElementsByName("primarykey_cbv");
	var flag = false;
	var pkValues = [];
	var num = 0;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			pkValues[num] = checkBoxArr[i].value;
			num +=1;
			flag = true;
			
		}
	}
	if (flag){
		//检测学生是否已经上报过奖学金
		zhcpjxjDWR.checkJxjsb(pkValues,function(data){
			if(data){
				viewTempDiv('奖学金上报','jxjDiv',300, 150);
			}else{
				alert("您选择的学生中部分已经上报了奖学金，重新上报请先取消上报！");
				return false;
			}			
		});
				
	}else{
		alert("没有选择相应记录，请选择之后再进行操作！！");
	}
}

function zhcpjxjsbCommit(url){
	var checkBoxArr = document.getElementsByName("primarykey_cbv");
	var flag = false;
	var pkValues = [];
	var num = 0;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
			pkValues[num++] = checkBoxArr[i].value;
		}
	}
	
	if (flag){
		//检测学生是否已经上报过奖学金
		zhcpjxjDWR.checkJxjsb(pkValues,function(data){
			if(data){
				if (confirm('确定要上报所选择的学生吗？')){
					document.forms[0].action = url;
					document.forms[0].submit();
					hiddenMessage(true,true);
					if ($("pt")) {
						BatAlert.showTips('正在操作，请等待...');
					}
				}
			}else{
				alert("您选择的学生中部分已经上报了奖学金，重新上报请先取消上报！");
				return false;
			}			
		});		
	}else{
		alert("没有选择相应记录，请选择之后再进行操作！！");
	}
}

function zhcpqxjxjsb(url){	
	var checkBoxArr = document.getElementsByName("primarykey_cbv");
	var flag = false;
	var pkValues = [];
	var num = 0;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
			pkValues[num++] = checkBoxArr[i].value;
		}
	}
	
	if (flag){
		//检测奖学金上报是否通过学校审核		
		zhcpjxjDWR.checkQxJxjsb(pkValues,function(data){
			if(data){
				if (confirm('确定要取消上报所选择的学生吗？')){
					document.forms[0].action = url;
					document.forms[0].submit();
					if ($("pt")) {
						BatAlert.showTips('正在操作，请等待...');
					}
				}
			}else{
				alert("您选择的部分学生上报奖学金已经通过学校审核，暂时不可取消上报！");
				return false;
			}			
		});		
	}else{
		alert("没有选择相应记录，请选择之后再进行操作！！");
	}
}

function jxjsb(){
	var checkBoxArr = document.getElementsByName("primarykey_cbv");
	var flag = false;
	var pkValues = [];
	var num = 0;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			pkValues[num] = checkBoxArr[i].value;
			num +=1;
			flag = true;
			
		}
	}
	setVal('sbNum',num);
	//判断是否选择了学院
	if(val('xy') == '' || val('xn')=='' || val('nj') == ''){
		alert("请选择学年、年级和"+jQuery("#xbmc").val()+"！");
		return false;
	}
	if (flag){
		//检测学生是否已经上报过奖学金
		var paramArr = {xn:val('xn'), nd:val('nd'), xq:val('xq')};
		jxjDWR.checkJxjsb(pkValues,paramArr,function(data){
			if(data){
				setVal('select_jxjdm','');
				viewTempDiv('奖学金上报','jxjDiv',300, 150);
			}else{
				alert("您选择的学生中部分已经上报了奖学金，重新上报请先取消上报！");
				return false;
			}			
		});
		//TODO 检测奖学金人数是否超过限制		
	}else{
		alert("没有选择相应记录，请选择之后再进行操作！！");
	}
}

function jxjsbCommit(url){
	if(ele('jxjjeTr').style.display != 'none' && val('jxjje')==''){
		alert('请填写奖学金金额！');
		return false;
	}
	if(isNaN(val('jxjje'))){
		alert('奖学金金额必须输入数字！');
		return false;
	}
	//判断人数是否超限制
	var input = {nj:val('nj'),xydm:val('xy'),jxjdm:val('select_jxjdm'),xn:val('xn'),jxjje:val('jxjje')};
	var num = 0;
	num = val('sbNum');
	jxjDWR.checkXyjxjsbrs(input,num,function(data){
		if(data>=0){
			//判断奖学金金额是否超限制
			if(val('jxjje')!= ''){
				jxjDWR.checkJxjje(input,num,function(data){
					if(data<0){
						alert("奖学金金额超出了"+jQuery("#xbmc").val()+"的总金额，超出：" + Math.abs(data) +"元");
						return false;
					}else{
							if (confirm('确定要上报所选择的学生吗？')){		
								url += "&sb_jxjdm=" + val('select_jxjdm');
								url += "&sb_jxjje=" + val('jxjje');
								
								document.forms[0].action = url;
								document.forms[0].submit();
								hiddenMessage(true,true);
								if ($("pt")) {
									BatAlert.showTips('正在操作，请等待...');
								}
							}
					}
				});
			}else{
				if (confirm('确定要上报所选择的学生吗？')){		
					url += "&sb_jxjdm=" + val('select_jxjdm');
					url += "&sb_jxjje=" + val('jxjje');
					
					document.forms[0].action = url;
					document.forms[0].submit();
					hiddenMessage(true,true);
					if ($("pt")) {
						BatAlert.showTips('正在操作，请等待...');
					}
				}
			}
			
			
		}else{
			alert(jQuery("#xbmc").val()+"人数超出了限制人数，共超过：" + Math.abs(data) + "人！");
			return false;
		}
	});	
}

function qxjxjsb(url){	
	var checkBoxArr = document.getElementsByName("primarykey_cbv");
	var flag = false;
	var pkValues = [];
	var num = 0;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
			pkValues[num++] = checkBoxArr[i].value;
		}
	}
	if (flag){
		//检测奖学金上报是否通过学校审核
		if(val('xn') == ''){
			alert('请选择学年！');
			return false;
		}
		var paramArr = {xn:val('xn'), nd:val('nd'), xq:val('xq')};
		jxjDWR.checkQxJxjsb(pkValues,paramArr,function(data){
			if(data){
				if (confirm('确定要取消上报所选择的学生吗？')){
					document.forms[0].action = url;
					document.forms[0].submit();
					if ($("pt")) {
						BatAlert.showTips('正在操作，请等待...');
					}
				}
			}else{
				alert("您选择的部分学生上报奖学金已经通过最终审核，暂时不可取消上报！");
				return false;
			}			
		});		
	}else{
		alert("没有选择相应记录，请选择之后再进行操作！！");
	}
}

function jxjjesz(){
	if(val('xn') == ''){
		alert('请选择学年！');
		return false;
	}
	//判断是否有数据
	if(ele('rsTable') == null || ele('rsTable') == undefined){
		alert('没有可保存的数据！');
		return false;
	}
	refreshForm('pjpyTybXysz.do?method=jxjjesz&act=save');
}
