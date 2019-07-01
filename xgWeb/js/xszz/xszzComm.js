var count = 1;
var max = 0;

//增加级别
function trAdd(the_tab,type,numAdd){
	dwr.engine.setAsync(false);
	var len = document.getElementById(the_tab).rows.length+1;
	var num = $(numAdd).value;
	var mklx = $("mklx").value;

	var cellfu = new Array();

	if( mklx == "sq" || mklx == "xg" || mklx == "sh" || mklx == "jg"){
		cellfu = getJtcy();
	}else{
		//是否涉及金额
		var sfje = getSfje();
		//金额类型
		var jelx = getJelx();
		
		cellfu = getCellfu(sfje,jelx);
	}
	
	count=len;  
	
	if(type=='add'){       
		max++;
		DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
	}else{
		if(num==""||num==null){	
			return false;
		}
		for(i=count;i<=num;i++){        
			max++;
			DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
			count++;
		}
		$(numAdd).value = "";
	}    
	dwr.engine.setAsync(true);
}

//删除所选TR
function trDel(the_tab,delRow){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    
    var checkbox = document.getElementsByName(delRow);
    var checkArray = new Array();
    var size = 0
    for(i=0;i<checkbox.length;i++){
    	if(checkbox[i].checked == true){
    		checkArray[size++] = eval(checkbox[i].value);
    	}	
    }
    
    if(checkArray.length == 0){
    	alert('您没有选中要操作的记录，请选择！');
    	return false;
    }
    	
   
    if(confirm('确定要删除选中的记录？')){
    	for(i=0;i<checkArray.length;i++){
    		tabobj.deleteRow(checkArray[i]-1);
    		for(j=0;j<checkArray.length;j++){
    			checkArray[j] += -1;
    		}
   	 }
    
   	 for(i=0;i<checkbox.length;i++){
    		checkbox[i].value=i+1;
  	  }
    }
}

//删除复数TR
function trDelAll(the_tab,numDel){
	var tabobj = document.getElementById(the_tab);
	var length = tabobj.rows.length;
	var num = $(numDel).value; 
	if(length==0){
		$(numDel).value = "";
		return false;     
	}
	if(num==""||num==null){	
		return false;
	}
	if(num>length){
		num = length;
	}
	if(confirm("确定要删除最后"+num+"行？")){ 
		for(i=1;i<=num;i++){           
			length--;
		}
		for(i=1;i<=num;i++){
			length--;
			tabobj.deleteRow(tabobj.rows.length-1);
		}
	}
	$(numDel).value = "";
}	

//生成TR
function getCellfu(sfje,jelx){
	var cellfu = new Array();
	if(sfje == '不需要'){
		cellfu =[		
		function(data){
			var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow1' value='"+count+"' />"
			return htmlText;
		},
		function(data){	    
			var htmltext = "<input type='text' style='width:100%' name='nojemc' id='jbmc1" + max + "' maxlength='25'/>";
	 
	    	return htmltext;
	    }
		];
	}else{
		if(jelx == "确定值"){
			cellfu =[		
			function(data){
				var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
				htmlText += "<input type='checkbox' name='delRow2' value='"+count+"' />"
				return htmlText;
			},
			function(data){	    
				var htmltext = "<input type='text' style='width:100%' name='qdjemc' id='jbmc2" + max + "' maxlength='25'/>";
		 
		    	return htmltext;
		    },
		    function(data){	    
				var htmltext = "<input type='text' style='width:100%' name='fjqdje' id='je" + max + "' ";
		 			htmltext+= " onkeydown='return onlyNum(this,5)' style='width:30%;ime-mode:disabled'";
		 			htmltext+= " onmousedown='return onlyNum(this,5)'/>";
		    	return htmltext;
		    }
			];
		}else{
			cellfu =[		
			function(data){
				var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
				htmlText += "<input type='checkbox' name='delRow3' value='"+count+"' />"
				return htmlText;
			},
			function(data){	    
				var htmltext = "<input type='text' style='width:100%' name='qjjemc' id='jbmc3" + max + "' maxlength='25'/>";
		 
		    	return htmltext;
		    },
		    function(data){	    
				var htmltext = "<input type='text' style='width:100%' name='fjxxje' id='jexx" + max + "' ";
					htmltext+= " onkeydown='return onlyNum(this,5)' style='width:30%;ime-mode:disabled'";
		 			htmltext+= " onmousedown='return onlyNum(this,5)'/>";
		    	return htmltext;
		    },
		    function(data){	    
				var htmltext = "<input type='text' style='width:100%' name='fjsxje' id='jesx" + max + "' ";
		 			htmltext+= " onkeydown='return onlyNum(this,5)' style='width:30%;ime-mode:disabled'";
		 			htmltext+= " onmousedown='return onlyNum(this,5)'/>";
		    	return htmltext;
		    }
			];
		}
	}

	return cellfu;
}

//是否涉及金额
function getSfje(){
	var je = $("je");
	var bje = $("bje");
	var sfje = "";
	
	if(je.checked){
		sfje = je.value;
	}else{
		sfje = bje.value;
	}
	return sfje;
}

//是否分级
function getSfFj(){
	var fj = $("fj");
	var bfj = $("bfj");
	var sffj = "";
	
	if(fj.checked){
		sffj = fj.value;
	}else{
		sffj = bfj.value;
	}
	return sffj;
}

//金额类型
function getJelx(){
	var qdz = $("qdz");
	var qj = $("qj");
	var jelx = "";
	
	if(qdz.checked){
		jelx = qdz.value;
	}else{
		jelx = qj.value;
	}
	return jelx;
}

//showidArr 要显示元素的id集合；hiddenidArr 要隐藏元素的id集合
function changeDisplay(showidArr,hiddenidArr){
	for(var i=0; i<showidArr.length; i++){
		if($(showidArr[i])){
			$(showidArr[i]).style.display = "";
		}
	}
	for(var i=0; i<hiddenidArr.length; i++){
		if($(hiddenidArr[i])){
			$(hiddenidArr[i]).style.display = "none";
		}
	}
}

//根据选择判断选择显示哪些TR
function showXmwh(){

	var showEle = [];
	var hiddenEle = [];
	
	//是否需要分级
	if(getSfFj() == "分级"){
		//是否需要金额
		if(getSfje() == "需要"){		
			//金额是否确定值
			if(getJelx() == "确定值"){
				showEle = ["jelxTr","fjTr","qdje"];	
				hiddenEle = ["qdjeTr","qjTr","noje","qjje"];		
			}else{
				showEle = ["jelxTr","fjTr","qjje"];	
				hiddenEle = ["qdjeTr","qjTr","noje","qdje"];	
			}
		}else{
			showEle = ["fjTr","noje"];	
			hiddenEle = ["jelxTr","qdjeTr","qjTr","qdje","qjje"];		
		}	
	}else{
		//是否需要金额
		if(getSfje() == "需要"){
			
			//金额是否确定值
			if(getJelx() == "确定值"){
				showEle = ["jelxTr","qdjeTr"];	
				hiddenEle = ["qjTr","fjTr","noje","qjje","qdje"];		
			}else{
				showEle = ["jelxTr","qjTr"];	
				hiddenEle = ["qdjeTr","fjTr","noje","qjje","qdje"];	
			}
			
		}else{
			hiddenEle = ["jelxTr","qdjeTr","qjTr","fjTr","noje","qjje","qdje"];		
		}
	}

	changeDisplay(showEle,hiddenEle);
}

//困难生条件
function checkKns(){
	var kns = document.getElementsByName("knsCk");
	var iskns = "";
	
	for(var i=0;i<kns.length;i++){
		if(kns[i].checked==true){
			iskns +=kns[i].value + "!!@@!!";
		}
	}
	
	if($("iskns")){
		$("iskns").value = iskns;
	}
}

//页面初始化
function onshow(){
	//根据选择判断选择显示哪些TR
	showXmwh();
	//是否显示审核级别
	showShjb();
	//是否显示人数限制级别
	showRsjb();
	//显示控制级别
	showKzjb();
	//控制checkBox
	kjChecked();
	//设置级别列表
	showJbList();
	//显示困难生条件
	showKnstj();
	//显示综合分条件
	setZhfTj();
}

//是否需要困难生
function setKnsJb(){

	var iskns = "";
	var knsSelect = $("knsSelect").value;
	
	if($("iskns")){
		iskns = $("iskns").value;
	}	

	var kns = document.getElementsByName("knsCk");
	
	for(var i=0;i<kns.length;i++){
		if(knsSelect != "无限制"){
			$(kns[i]).disabled = false;
		}else{
			$(kns[i]).disabled = true;
		}
	}
	
	if($("iskns") && knsSelect == "无限制"){
		$("iskns").value = "";
	}
}

//设置综合分条件
function setZhfTj(){
	var zyf = "";
	
	if($("zyf")){
		zyf = $("zyf").value;
		if(zyf != "无限制"){
			$("zyftj").value = zyf.substr(0,1);
			$("zyfz").value = zyf.substr(1,zyf.length-1);
		}
	}
	
	var zhf = "";
	
	if($("zhf")){
		zhf = $("zhf").value;
		if(zhf != "无限制"){
			$("zhftj").value = zhf.substr(0,1);
			$("zhfz").value = zhf.substr(1,zhf.length-1);
		}
	}
	
	
	//------------平均成绩 2010.12.7 qph----------------------
	var pjcj = "";
	
	if($("pjcj")){
		pjcj = $("pjcj").value;
		if(pjcj != "无限制"){
			$("pjcjtj").value = pjcj.substr(0,1);
			$("pjcjz").value = pjcj.substr(1,pjcj.length-1);
		}
	}
	//-------------平均成绩 end--------------------------------
	
	//-------------加权平均分、加权平均绩点 qph 2012.3.8-----------
	var jqpjf = "";
	
	if($("jqpjf")){
		jqpjf = $("jqpjf").value;
		if(jqpjf != "无限制"){
			$("jqpjftj").value = jqpjf.substr(0,1);
			$("jqpjfz").value = jqpjf.substr(1,jqpjf.length-1);
		}
	}
	
	
	var jqpjjd = "";
	
	if($("jqpjjd")){
		jqpjjd = $("jqpjjd").value;
		if(jqpjjd != "无限制"){
			$("jqpjjdtj").value = jqpjjd.substr(0,1);
			$("jqpjjdz").value = jqpjjd.substr(1,jqpjjd.length-1);
		}
	}
	//---------------加权平均分、加权平均绩点 end----------------------
}

//显示困难生条件
function showKnstj(){
	if($("iskns")){
	
		var iskns = $("iskns").value;
		
		if(iskns != ""){
			$("knsSelect").value = "是";
			var knsjb = iskns.split("!!@@!!");

			for(var i=0 ;i< knsjb.length;i++){
				var id = knsjb[i];	
				if($(id)){
					$(id).checked = true;
				}
			} 
			var kns = document.getElementsByName("knsCk");
			for(var i=0;i<kns.length;i++){
				$(kns[i]).disabled = false;
			}
		}
	}
}

//判断班级审核是否被选中
function clickBjsh(){

	var bjsh = $("bjsh");
	
	if(bjsh.checked){
		$("bzrsh").disabled = false;
		$("fdysh").disabled = false;
		$("fdysh").checked = true;
	}else{
		$("bzrsh").disabled = true;
		$("fdysh").disabled = true;
		$("bzrsh").checked = false;
		$("fdysh").checked = false;
	}
}

//是否显示审核级别
function showShjb(){
	var shjb=$("shjb").value;
	if(shjb != "无需审核"){
		$("shjbTr").style.display = "";
	}else{
		$("shjbTr").style.display = "none";
	}
}

//是否显示人数限制级别
function showRsjb(){
	var rsjb=$("rskz").value;
	if(rsjb == "需要"){
		$("rsjbTr").style.display = "";
	}else{
		$("rsjbTr").style.display = "none";
	}
}

//显示控制级别
function showKzjb(){
	var rsjb=$("rskz").value;
	if(rsjb == "需要"){
		$("kzjb").disabled = false;
	}else{
		$("kzjb").disabled = true;
	}
}

//保存项目维护
function saveXmwh(){
	if(saveXzt() && checkShjb() && isFhtj() && isPkNull('xmmc')){
		if (confirm("确定保存以上设置?")) {
			saveUpdate('/xgxt/commXszz.do?method=xmwhUpdate&doType=save','xmmc');
		}
	}
}

//判断项目条件是否符合规范
function isFhtj(){
	//是否困难生
	if($("knsSelect") && $("knsSelect").value == "是"){
		var obj = document.getElementsByName("knsCk");
		var flag = false;
		for(var i=0;i<obj.length;i++){
			if(obj[i].checked){
				flag = true;
			}
		}
		if(!flag){
			alert("本项目设置为需要困难生级别控制，请选择具体的级别！");
			return false;
		}
	}
	//智育分条件
	if($("zyftj")){
		if($("zyftj").value != "" && $("zyfz").value == ""){
			alert("请输入智育分的条件值");
			return false;
		}
		if($("zyftj").value != "" && $("zyfz").value != ""){
			$("zyf").value = $("zyftj").value + $("zyfz").value;
		}else{
			$("zyf").value = "无限制";
		}
	}
	//综合分条件
	if($("zhftj")){
		if($("zhftj").value != "" && $("zhfz").value == ""){
			alert("请输入智育分的条件值");
			return false;
		}
		if($("zhftj").value != "" && $("zhfz").value != ""){
			$("zhf").value = $("zhftj").value + $("zhfz").value;
		}else{
			$("zhf").value = "无限制";
		}
	}
	
	
	//平均成绩条件-------2010.12.7 qph ---------------
	if($("pjcjtj")){
		if($("pjcjtj").value != "" && $("pjcjz").value == ""){
			alert("请输入平均成绩的条件值");
			return false;
		}
		if($("pjcjtj").value != "" && $("pjcjz").value != ""){
			$("pjcj").value = $("pjcjtj").value + $("pjcjz").value;
		}else{
			$("pjcj").value = "无限制";
		}
	}
	//------------平均成绩 end--------------------------
	
	//--加权平均分、加权平均绩点---qph---2012.3.8-----------------------
	if($("jqpjftj")){
		if($("jqpjftj").value != "" && $("jqpjfz").value == ""){
			alert("请输入平均成绩的条件值");
			return false;
		}
		if($("jqpjftj").value != "" && $("jqpjfz").value != ""){
			$("jqpjf").value = $("jqpjftj").value + $("jqpjfz").value;
		}else{
			$("jqpjf").value = "无限制";
		}
	}
	
	if($("jqpjjdtj")){
		if($("jqpjjdtj").value != "" && $("jqpjjdz").value == ""){
			alert("请输入平均成绩的条件值");
			return false;
		}
		if($("jqpjjdtj").value != "" && $("jqpjjdz").value != ""){
			$("jqpjjd").value = $("jqpjjdtj").value + $("jqpjjdz").value;
		}else{
			$("jqpjjd").value = "无限制";
		}
	}
	//--加权平均分、加权平均绩点---qph---2012.3.8--end---------------------
	
	return true;
}

//判断审核级别是否符合
function checkShjb(){
	var shjb = $("shjb").value;
	
	if(shjb == "无需审核"){
		return true;
	}
	var bjsh = $("bjsh");
	var xysh = $("xysh");
	var xxsh = $("xxsh");
	
	var n = 0;

	if(bjsh.checked){
		n++;
	}
	
	if(xysh.checked){
		n++;
	}
	
	if(xxsh.checked){
		n++;
	}
	
	if(shjb == "一级审核" && n>1){
		alert("审核级别为一级,最多只能勾选一个审核级别，请确认！");
		return false;
	}
	
	if(shjb == "两级审核" && n>2){
		alert("审核级别为两级,最多只能勾选两个审核级别，请确认！");
		return false;
	}

	return true;
}

//勾选班级审核
function clickBzrFdy(){
	var bzrsh = $("bzrsh");
	var fdysh = $("fdysh");
	if(bzrsh.checked || fdysh.checked ){
		$("bjsh").checked = true;
	}
	
}

//处理分级情况
function saveXzt(){
	
	//需要金额
	var sfje = "";
	if($("je").checked){
		sfje = $("je").value;
	}else{
		sfje = $("bje").value;
	}
	//是否需要分级
	var sffj = "";
	if($("fj").checked){
		sffj = $("fj").value;
	}else{
		sffj = $("bfj").value;
	}
	//金额类型
	var jelx = "";
	if($("qdz").checked){
		jelx = $("qdz").value;
	}else{
		jelx = $("qj").value;
	}
	
	if(sfje == "需要"){		//需要金额
		if(sffj == "不分级"){		//不分级
			if(jelx == "确定值"){ //金额确定值
				var je = $("nofjje").value;
				if(je == ""){
					alert("金额不能都为空，请确定!");
					$("nofjje").focus();
					return false;
				}
			}else if(jelx == "区间"){//金额区间值
				var xxje = $("nofjxx").value;
				var sxje = $("nofjsx").value;
				
				if(xxje != "" && sxje != "" && parseInt(sxje) < parseInt(xxje)){
					alert("上限金额不能小于下限金额，请确认！");
					return false;
				}
				
				if(xxje == "" && sxje == ""){
					alert("金额上下限不能都为空，请确定!");
					return false;
				}
			}
		}else{//需要分级
			var the_tab = "";
			
			if(jelx == "确定值"){
				the_tab = "qdnr";
			}else{
				the_tab = "qjnr";
			}
			var tabobj = document.getElementById(the_tab);
			var rowLen = max;
			var nrLen = tabobj.rows.length;
			
			if(nrLen == 0){
				alert("已经选择分级，级别内容不能为空，请确认！");
				return false;
			}
			
			for(var i=0;i<=rowLen;i++){
			
				if(jelx == "确定值"){//金额确定值
				
					var mcId = "jbmc2"+i;
					var jeId = "je"+i;
					
					if($(mcId)){
						if($(mcId).value == ""){
							alert("级别名称为空，请确定!");
							$(mcId).focus();
							return false;
						}
					}
						
					if($(jeId)){
						if($(jeId).value == ""){
							alert("金额为空，请确定!");
							$(jeId).focus();
							return false;
						}
						num=$(jeId).value;
						if(num.length>1 && num.substring(0,1)=="0"){
							alert("金额输入不正确，请确定!");
							$(jeId).focus();
							return false;
						}
					}
				}else{//金额区间值
				
					var mcId = "jbmc3"+i;
					var xxId = "jexx"+i;
					var sxId = "jesx"+i;

					if($(mcId)){
						if($(mcId).value == ""){
							alert("级别名称为空，请确定!");
							$(mcId).focus();
							return false;
						}
					}
						
					if($(xxId) && $(sxId)){
						var xxje = $(xxId).value;//下限金额
						var sxje = $(sxId).value;//上限金额
						if(xxje == "" && sxje == ""){
							alert("金额上下限不能都为空，请确定!");
							$(xxId).focus();
							return false;
						}
						if(xxje != "" && sxje != "" && parseInt(sxje) < parseInt(xxje)){
							alert("上限金额不能小于下限金额，请确认！");
							$(xxId).focus();
							return false;
						}
					}
				}
			}
		}
	}else{//不需要金额
		if(sffj == "分级"){	//需要分级
		
			var the_tab = "jbnr";
			var tabobj = document.getElementById(the_tab);
			var rowLen = max;
			var nrLen = tabobj.rows.length;
			
			if(nrLen == 0){
				alert("已经选择分级，级别内容不能为空，请确认！");
				return false;
			}
			
			for(var i=0;i<=rowLen;i++){
				var mcId = "jbmc1"+i;
				
				if($(mcId)){
					if($(mcId).value == ""){
						alert("级别名称为空，请确定!");
						$(mcId).focus();
						return false;
					}
				}
			}
		}
	}
	
	return true;
}

//checked控件
function kjChecked(){

	//学院审核checkbox
	var xyshCheck = $("xyshCheck").value;

	if(xyshCheck == "是"){
		$("xysh").checked = true;
	}
	
	//学校审核checkbox
	var xxshCheck = $("xxshCheck").value;

	if(xxshCheck == "是"){
		$("xxsh").checked = true;
	}
	
	//班级审核checkbox
	var bzrshCheck = $("bzrshCheck").value;
	var fdyshCheck = $("fdyshCheck").value;

	if(bzrshCheck == "是" || fdyshCheck == "是"){
		$("bjsh").checked = true;
	}
	
	if(bzrshCheck == "是" ){
		$("bzrsh").checked = true;
	}
	
	if(fdyshCheck == "是" ){
		$("fdysh").checked = true;
	}	
	
	//学院控制checkbox
	var xykzCheck = $("xykzCheck").value;

	if(xykzCheck == "是"){
		$("xykz").checked = true;
	}
	
	//学校控制checkbox
	var xxkzCheck = $("xxkzCheck").value;

	if(xxkzCheck == "是"){
		$("xxkz").checked = true;
	}
	
	//班级控制checkbox
	var bzrkzCheck = $("bzrkzCheck").value;
	var fdykzCheck = $("fdykzCheck").value;

	if(bzrkzCheck == "是" || fdykzCheck == "是"){
		$("bjkz").checked = true;
	}
}

//设置级别列表
function showJbList(){

	var xmdm = $("xmdm").value;
	//需要金额
	var sfje = "";
	if($("je").checked){
		sfje = $("je").value;
	}else{
		sfje = $("bje").value;
	}
	//是否需要分级
	var sffj = "";
	if($("fj").checked){
		sffj = $("fj").value;
	}else{
		sffj = $("bfj").value;
	}
	//金额类型
	var jelx = "";
	if($("qdz").checked){
		jelx = $("qdz").value;
	}else{
		jelx = $("qj").value;
	}
	
	if(xmdm != ""){
	
		dwr.engine.setAsync(false);
		
		var tableName="xszz_xmfjqkb";
		var colList =["fjmc","fjxxje","fjsxje","fjqdje"];
		var pk = "xmdm";
		var pkValue = xmdm;
		var query =" ";
		
		getOtherData.getTableListInfo(tableName, colList,pk, pkValue,query,function(data){
			if( data != null && data.length > 0){
				if(sfje == "需要"){		//需要金额
					if(sffj == "不分级"){ //不分级
						if(jelx == "确定值"){ //金额确定值
							var quje = data[0].fjqdje;
							if(quje != null){
								$("nofjje").value = quje;
							}
						}else if(jelx == "区间"){//金额区间值
							var xxje = data[0].fjxxje;
							var sxje = data[0].fjsxje;
							if(xxje != null){
								$("nofjxx").value = xxje;
							}
							if(sxje != null){
								$("nofjsx").value = sxje;
							}
						}
					}else{//需要分级
						var btnId = "";
						var tbId = "";
						if(jelx == "确定值"){
							btnId = "qdnrAdd";
							tbId = "qdnr";
						}else{
							btnId = "qjnrAdd";
							tbId = "qjnr";
						}
						
						var rowLen = data.length;
						$(btnId).value = rowLen;
						trAdd(tbId,'madd',btnId);		
						
						for(var i=1;i<=rowLen;i++){
							if(jelx == "确定值"){//金额确定值
							
								var mcId = "jbmc2"+i;
								var jeId = "je"+i;
								
								var jbmc = data[i-1].fjmc;
								var qdje = data[i-1].fjqdje;
								
								//级别名称
								if($(mcId)){
									if(jbmc != null){
										$(mcId).value = jbmc;
									}
								}
								
								//确定金额
								if($(jeId)){
									if(qdje != null){
										$(jeId).value = qdje;
									}
								}
							}else{//金额区间值
								
								var mcId = "jbmc3"+i;
								var xxId = "jexx"+i;
								var sxId = "jesx"+i;
				
								var jbmc = data[i-1].fjmc;
								var xxje = data[i-1].fjxxje;
								var sxje = data[i-1].fjsxje;
									
								//级别名称
								if($(mcId)){
									if(jbmc != null){
										$(mcId).value = jbmc;
									}
								}
								//下限金额
								if($(xxId)){
									if(xxje != null){
										$(xxId).value = xxje;
									}
								}
								//上限金额
								if($(sxId)){
									if(sxje != null){
										$(sxId).value = sxje;
									}
								}
							}
						}
					}
				}else{//不需要金额
					if(sffj == "分级"){	//需要分级
						var tbId = "jbnr";
						var btnId = "jbnrAdd";
						var rowLen = data.length;
						$(btnId).value = rowLen;
						trAdd(tbId,'madd',btnId);		
						
						for(var i=1;i<=rowLen;i++){
	
							var mcId = "jbmc1"+i;
							var jbmc = data[i-1].fjmc;
							
							if($(mcId)){
								if(jbmc != null){
									$(mcId).value = jbmc;
								}
							}
						}
					}	
				}
			}
		});
			
		dwr.engine.setAsync(true);
		
	}
}

//生成问卷回答回答情况DIV
function showRsszDiv(){
	
//	var d_width = document.body.clientWidth;
//	var d_height = document.body.clientHeight ;
//	var d_left = 0;
//	var d_top = 0;
//	var d_color = "#EEF4F9";
//	var d_width_top = 250;
//	var d_height_top = 120;
//	var d_left_top = (d_width - d_width_top) / 2.5;
//	var d_top_top = (d_height - d_height_top) / 3.5;
//	var d_color_top = "#EEF4F9";
//	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
//	dd_html += "</div>";
//	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
//	dd_html += "<table width='280' class='tbstyle'>";
//	dd_html += "<thead>";
//	dd_html += "<tr height='30'>";
//	dd_html += "<td colspan='2' align='center'>";
//	dd_html += "-----------------------输入值----------------------";
//	dd_html += "</td>";
//	dd_html += "</tr>";
//	dd_html += "</thead>";
//	dd_html += "<tbody>";
	
	var dd_html="";
	dd_html += "<div class=\"tab\">";
	dd_html += "<table class=\"formlist\">";
	dd_html += "<thead>";
	dd_html += "<tr>";
	dd_html += "<th colspan='2'>";
	dd_html += "<span> 输入值 </span>";
	dd_html += "</th>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	
	dd_html += "<tbody>";
	
	
	//分配方式为比例
	if(getFpfs()){
		dd_html += "<tr height='30'>";
		dd_html += "<td align='left' colspan='2'><div align='left' width='100%'>";
		dd_html += "注：被分配部门人数值为：被分配部门总人数*比例";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<th style='width:20%'>";
		dd_html += "比例";
		dd_html += "</th>";
		dd_html += "<td >";
		dd_html += "<input type='text' name='bl' id='bl' onblur='chMax(this,100)'";
		dd_html += "onkeypress='return sztzNumInputValue(this,5,event)' style='width:30%;ime-mode:disabled'/>%";
		dd_html += "</td>";
		dd_html += "</tr>";
	}else{
		dd_html += "<tr height='30'>";
		dd_html += "<td align='left' colspan='2'><div align='left' width='100%'>";
		dd_html += "注：将分配人数平均分配给所选部门如无所选部门则<br/>";
		dd_html += "平均分配给所有部门。当输入人数小于分配"+jQuery("#xbmc").val()+"的总<br/>";
		dd_html += "数或平均分配值小于1时，默认分配1人</div>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<th style='width:20%'>";
		dd_html += "分配人数";
		dd_html += "</th>";
		dd_html += "<td>";
		dd_html += "<input type='text' name='fprs' id='fprs'";
		dd_html += "onkeypress='return onlyNum(this,5)' style='width:30%;ime-mode:disabled'/>";
		dd_html += "</td>";
		dd_html += "</tr>";
		
		
	}
	dd_html +=" </tbody>";
	dd_html +=" <tfoot>";
	dd_html +="<tr>";
	dd_html +="<td colspan=\"2\">";
	dd_html +="<div class=\"btn\">";
	
	dd_html +="<button type='button' onclick='saveRssz();'>";
	dd_html +="确定";
	dd_html +="</button>";
	dd_html += "<button type='button'  class='button2' onclick='hiddenMessage(true,true);'>取消</button>";
	dd_html +="</div>";
	dd_html +="</td>";
	dd_html +="</tr>";
	dd_html +="</tfoot>";
	dd_html += "</table>";
	
	
	if(!$("tsxxDiv")){
		var tmp1 = document.createElement("div");
		tmp1.innerHTML=dd_html;
		tmp1.id="tsxxDiv";
		document.forms[0].appendChild(tmp1);
		tmp1.style.display="none";	
	}else{
		$("tsxxDiv").innerHTML=dd_html;
	}
	viewTempDiv("比例设置","tsxxDiv",300,200);
}

//获得分配方式
function getFpfs(){
	var blRad = $("blRad");
	var rsRad = $("rsRad");
	
	if(blRad.checked == true){
		return true;
	}else{
		return false;
	}	
}

//保存人数设置
function saveRssz(){
	var kzjb = $("kzjb").value;
	var rssx = jQuery("#rssx").value;
	var bl = "";
	var fprs = "";
	
	if($("bl")){
		bl=$("bl").value;
	}
	
	if($("fprs")){
		fprs=$("fprs").value;
	}
	
	if(getFpfs()){
		if(bl == ""){
			alert("比例不能不能为空，请确认！！");
			return false;
		}
	}else{
		if(fprs == ""){
			alert("分配人数不能为空，请确认！！");
			return false;
		}
		if(rssx != "" && parseInt(fprs) > parseInt(rssx)){
			alert("分配人数不能超过上限人数，请确认！！");
			return false;
		}
	}
	
	//控制级别为学院
	if(kzjb == jQuery("#xbmc").val()){
		saveXyrs(bl,fprs);
	}
	//控制级别为专业
	else if(kzjb == "专业"){
		saveZyrs(bl,fprs);
	}
	//控制级别为班级
	else if(kzjb == "班级"){
		saveBjrs(bl,fprs);
	}
}

//保存学院人数
function saveXyrs(bl,fprs){
	
	var szxy = $("xyR");	
	var msg = "";
	var url = "/xgxt/commXszz.do?method=rsszUpdate&doType=save";
	
	if(szxy.options.length == "0"){
		if(bl != ""){
			msg = "将全体院系的该资助项目人数比例设置为" + bl + "%,请确认";
		}else{
			msg = "将"+fprs+"人平均分配给全体院系,请确认";
		}
	}else if(szxy.options.length != "0"){
		if(bl != ""){
			msg = "将所选院系的该资助项目人数比例设置为" + bl + "%,请确认";
		}else{
			msg = "将"+fprs+"人平均分配给所选院系,请确认";
		}
	}
	
	if (confirm(msg)) {
		for(var i = 0 ; i <szxy.options.length; i++){
			var tmp = document.createElement("input");
			tmp.type = "hidden";
			tmp.name = "bmdm";
			tmp.value = szxy.options[i].value;
			document.forms[0].appendChild(tmp);
		}
		saveUpdate(url,'');
	}	
}

//保存专业人数
function saveZyrs(bl,fprs){
	
	var szzy = $("zyR");
	var msg = "";
	var url = "/xgxt/commXszz.do?method=rsszUpdate&doType=save";
	
	var nj =  $("nj").value;
	var xy =  $("xy");
	var xymc =  "";
	
	for(var i=0;i<xy.options.length;i++){
		if(xy.options[i].selected){
			xymc=xy.options[i].text;
		}
	}
	
	msg = "将";
	if(fprs != ""){
		msg += fprs+"人平均分配给";
	}
	if(nj != ""){
		msg += nj +"级";
	}else{
		msg += "所有年级";
	}
	if(xymc != ""){
		msg += xymc;
	}else{
		msg += "所有"+jQuery("#xbmc").val();
	}
	if(szzy.options.length != "0"){
		msg += "所选专业";
	}else{
		msg += "所有专业";
	}
	if(bl != ""){
		msg +="该资助项目人数比例设置为" + bl + "%";
	}
	msg += "，请确认！"
	
	if (confirm(msg)) {
		for(var i = 0 ; i <szzy.options.length; i++){
			var tmp = document.createElement("input");
			tmp.type = "hidden";
			tmp.name = "bmdm";
			tmp.value = szzy.options[i].value;
			document.forms[0].appendChild(tmp);
		}
		saveUpdate(url,'');
	}	
}

//保存班级人数
function saveBjrs(bl,fprs){
	
	var szbj = $("bjR");
	var msg = "";
	var url = "/xgxt/commXszz.do?method=rsszUpdate&doType=save";
	
	var nj =  $("nj").value;
	var xy =  $("xy");
	var zy =  $("zy");
	var xymc =  "";
	var zymc =  "";
	
	for(var i=0;i<xy.options.length;i++){
		if(xy.options[i].selected){
			xymc=xy.options[i].text;
		}
	}
	for(var i=0;i<zy.options.length;i++){
		if(zy.options[i].selected){
			if(zy.options[i].value != ""){
				zymc=zy.options[i].text;
			}
		}
	}
	
	msg = "将";
	
	if(fprs != ""){
		msg += fprs+"人平均分配给";
	}
	if(nj != ""){
		msg += nj +"级";
	}else{
		msg += "所有年级";
	}
	if(xymc != ""){
		msg += xymc;
	}else{
		msg += "所有"+jQuery("#xbmc").val();
	}
	if(zymc != ""){
		msg += zymc;
	}else{
		msg += "所有专业";
	}
	if(szbj.options.length != "0"){
		msg += "所选班级";
	}else{
		msg += "所有班级";
	}
	if(bl != ""){
		msg +="该资助项目人数比例设置为" + bl + "%";
	}
	msg += "，请确认！"
	
	if (confirm(msg)) {
		for(var i = 0 ; i <szbj.options.length; i++){
			var tmp = document.createElement("input");
			tmp.type = "hidden";
			tmp.name = "bmdm";
			tmp.value = szbj.options[i].value;
			document.forms[0].appendChild(tmp);
		}
		saveUpdate(url,'');
	}	
}

//设置下拉列表空值
function selectListNull(){

	dwr.engine.setAsync(false);

	var kzjb = $("kzjb").value;

	if(kzjb == "专业"){
	
		initZyList();
		
		var zy = $("zy");

		for(var i = 0 ; i <zy.options.length; i++){
			var zydm = zy.options[i].value;
			if(zydm == null || zydm == ""){	
				zy.options[i] = null;
			}
		}
	}else if(kzjb == "班级"){
		
		initBjList();
		
		var bj = $("bj");

		for(var i = 0 ; i <bj.options.length; i++){
			var bjdm = bj.options[i].value;
			if(bjdm == null || bjdm == ""){	
				bj.options[i] = null;
			}
		}
	}
	
	dwr.engine.setAsync(true);
}

//设置初始值
function setXmshSelectList(){
	
	dwr.engine.setAsync(false);
	
	setXmsqSelectList();
	
	var tableName = $("xmb").value; 
	var pk = "xh||sqsj||xmdm";
	var pkValue = $("xh").value + $("sqsj").value + $("xmdm").value;

	var colList = ["xmzzjb","xmzzje","shzt1yj","shzt2yj","shzt3yj"]	
	
	getXszzInfo.getXszzInfo(tableName,pk,pkValue,colList,function(data){
		if(data!=null){
			if($("xmzzjb")){
				$("xmzzjb").value = data.xmzzjb==null ? "" : data.xmzzjb;
				if(data.xmzzjb!=null){
					setJeqk(data.xmzzjb);
				}
			}
			if($("xmzzje")){
				$("xmzzje").value = data.xmzzje==null ? "" : data.xmzzje;
			}
			if($("shzt1yj")){
				$("shzt1yj").value = data.shzt1yj==null ? "" : data.shzt1yj;
			}
			if($("shzt2yj")){
				$("shzt2yj").value = data.shzt2yj==null ? "" : data.shzt2yj;
			}
			if($("shzt3yj")){
				$("shzt3yj").value = data.shzt3yj==null ? "" : data.shzt3yj;
			}
		}
	});	
	
	dwr.engine.setAsync(true);
}

//保存项目申请
function saveXmsq(){

	//验证必须录入字段
	var bxlr = document.getElementsByName("bxlr");
	
	var msg = "";
	
	for(var i=0;i<bxlr.length;i++){
		var nr = bxlr[i].value.split("!!@@!!");	
		var zdm = nr[0];
		var id =  nr[1];
		if($(id) && $(id).value == ""){
			msg+=zdm+"为必填项，不可以为空\n";
		}
	}
	
	if(msg != ""){
		msg+="请确认！";
		alert(msg);
		return false;
	}
	
	var mklx = $("mklx").value;
	var url = "/xgxt/commXszz.do?method=xmsqUpdate&doType=save";
	url += "&mklx="+mklx;
	if (confirm("确定以上申请内容?")) {	
		saveUpdate(url,'');	
	}
}


//资助续办
function saveZzxb(){

	//验证必须录入字段
	var bxlr = document.getElementsByName("bxlr");
	
	var msg = "";
	
	for(var i=0;i<bxlr.length;i++){
		var nr = bxlr[i].value.split("!!@@!!");	
		var zdm = nr[0];
		var id =  nr[1];
		if($(id) && $(id).value == ""){
			msg+=zdm+"为必填项，不可以为空\n";
		}
	}
	
	if(msg != ""){
		msg+="请确认！";
		alert(msg);
		return false;
	}
	
	var url = "/xgxt/commXszz.do?method=zzxbUpdate&doType=save";
	
	if (confirm("确定以上申请内容?")) {	
		saveUpdate(url,'');	
	}
}



//设置只读项目
function setKzReadOnly(){

	dwr.engine.setAsync(false);
	
	var tableName = "xszz_xmnrzdb"; 
	
	var dm = "zd"; 
	var mc = "zd";
	var msg = "";
	var pk = "xmdm";
	var pkValue = "";
	var mrxm = $("mrxm").value;

	if(mrxm == "否"){
		pkValue = "无";
	}else{
		pkValue = $("xmdm").value;
	}
	
	getXszzInfo.getXszzList(tableName, dm, mc, msg, pk, pkValue,function(data){
		
		if(data!=null && data.length >0 ){
			for(var i=0;i<data.length;i++){
				var id = data[i].dm;
				if($(id) && id != "xh" && id != "xmzzjb" && id != "xmzzje"){
					$(id).disabled = true;
				}
			}
		}
	});
	
	dwr.engine.setAsync(true);
}

//项目审核页面初始化
function onShow_xmsh(){
	var xmb = $("xmb").value;
	//初始化下拉列表值
	setXmshSelectList();
	//只读
	setKzReadOnly();
	//设置家庭成员信息
	setJtcyInfo();	
	
	if($("jtcyCtrl")){
		$("jtcyCtrl").style.display = "none";
	}
	if($('tTb')){
		$('tTb').disabled=true;
	}
	
	//设置意见空值
	setShyj();
}

//项目结果页面初始化
function onShow_xmjg(){

	var xmb = $("xmb").value;
	
	//初始化下拉列表值
	setXmshSelectList();
	
	if(xmb != "jtqkdcb"){
		//只读
		//setKzReadOnly();
		setJtcyReadOnly();
	}
	if(xmb != "xsqtxxb"){
		setQtxxReadOnly();
	}
	//设置家庭成员信息
	setJtcyInfo();	
	
	if($("jtcyCtrl") && xmb != "jtqkdcb" && (xmb != "xszz_knsb" 
		&& ("yes" != $('iskns').value || "true" != $('knsdl').value || "true" != $('jtqkdc').value))){
		$("jtcyCtrl").style.display = "none";
	}
	
	//设置意见空值
	setShyj();
}

//判断金额是否超过上下限
function checkJe(obj){
	
	var xmzzje = obj.value;

	var xxje = "";
	if($("xxje")){
		xxje = $("xxje").value;
	}

	var sxje = "";
	if($("sxje")){
		sxje = $("sxje").value;
	}

	var qdje = "";
	if($("qdje")){
		qdje = $("qdje").value;
	}

	if(xxje != "" && parseInt(xxje) > parseInt(xmzzje)){
		alert("不能超过下限金额，请确定！");
		obj.focus();
	}

	if(sxje != "" && parseInt(sxje) < parseInt(xmzzje)){
		alert("不能超过上限金额，请确定！");
		obj.focus();
	}

	if(qdje != "" && parseInt(qdje) != parseInt(xmzzje)){
		alert("该项目金额固定为"+qdje+"，不可修改！");
		obj.value=qdje;
		obj.focus();
	}
}

//设置金额情况
function setJeqk(fjmc){
	
	var tableName = "xszz_xmfjqkb"; 
	var pk = "xmdm||fjmc";
	var pkValue = $("xmdm").value + fjmc;
	var colList = ["fjxxje","fjsxje","fjqdje"]	
	
	dwr.engine.setAsync(false);
			
	getXszzInfo.getXszzInfo(tableName,pk,pkValue,colList,function(data){
		if(data!=null){
			if($("xxje") && data.fjxxje != "无"){
				$("xxje").value = data.fjxxje;
			}
			if($("sxje") && data.fjsxje != "无"){
				$("sxje").value = data.fjsxje;
			}
			if($("qdje") && data.fjqdje != "无"){
				$("qdje").value = data.fjqdje;
				$("xmzzje").value = data.fjqdje;
			}
			if(data.fjxxje == "无" && data.fjsxje == "无" && data.fjqdje == "无"){
				if($("xmzzje")){
				$("xmzzje").value = "不涉及金额";
				$("xmzzje").readOnly = true;}
			}
		}
	});	
	
	dwr.engine.setAsync(true);
}

//项目审核
function xmsh(shzt){

	var jdkz = true;
	
	jQuery.ajaxSetup({async:false});
	if(shzt == "tg"){
		//兼得控制---2012.3.9 qph--------
		var xh = jQuery('#xh').val();
		var xmdm = jQuery('#xmdm').val();
		jQuery.post('xszzAjax.do?method=checkBkjdxm',{xh:xh,xmdm:xmdm},function(data){
			var bkjdxm = data.xmdm;
			
			if (bkjdxm != '' && bkjdxm != null){
				alert('已存在不可兼得项目:{项目代码:'+data.xmdm+',项目名称:'+data.xmmc+'}');
				jdkz = false;
			}
		},'json');
	}


	if (jdkz && confirm("确定审核状态?")) {
		var url = "/xgxt/commXszz.do?method=xmshUpdate&doType=save";
			url+= "&shzt="+shzt;
			
		if(shzt == "tg"){
			shzt = "通过";
		}else{
			shzt = "不通过";
		}
		
		var lx = $("lx").value;
		var shjb = $("shjb").value;

		if(shjb == "一级审核"){
			$("shzt1").value = shzt;
		}else if(shjb == "两级审核"){
		
			var jbdm1 = $("jbdm1").value;
			var jbdm2 = $("jbdm2").value;
			var jb = $("jb").value;

			if(jb == "Lv1"){
				if(jbdm1 != ""){
					var arrJb = jbdm1.split("-");
					for(var i=0;i<arrJb.length;i++){
						var yhsh = lx+"sh";
						if(arrJb[i] == yhsh){
							$("shzt1").value = shzt;
						}else if(lx == "jd" && (arrJb[i] == "fdysh" || arrJb[i] == "bzrsh")){
							$("shzt1").value = shzt;
						}			
					}
				}
			}else{
				if(jbdm2 != ""){
					var arrJb = jbdm2.split("-");
					for(var i=0;i<arrJb.length;i++){
						var yhsh = lx+"sh";
						if(arrJb[i] == yhsh){
							$("shzt2").value = shzt;
						}else if(lx == "jd" && (arrJb[i] == "fdysh" || arrJb[i] == "bzrsh")){
							$("shzt2").value = shzt;
						}			
					}
				}
			}
		}else if(shjb == "三级审核"){
		
			var jbdm1 = $("jbdm1").value;
			var jbdm2 = $("jbdm2").value;
			var jbdm3 = $("jbdm3").value;
			var jb = $("jb").value;

			if(jb == "Lv1"){
				if(jbdm1 != ""){
					var arrJb = jbdm1.split("-");
					for(var i=0;i<arrJb.length;i++){
						var yhsh = lx+"sh";
						if(arrJb[i] == yhsh){
							$("shzt1").value = shzt;
						}else if(lx == "jd" && (arrJb[i] == "fdysh" || arrJb[i] == "bzrsh")){
							$("shzt1").value = shzt;
						}			
					}
				}
			}else if(jb == "Lv2"){
				if(jbdm2 != ""){
					var arrJb = jbdm2.split("-");
					for(var i=0;i<arrJb.length;i++){
						var yhsh = lx+"sh";
						if(arrJb[i] == yhsh){
							$("shzt2").value = shzt;
						}else if(lx == "jd" && (arrJb[i] == "fdysh" || arrJb[i] == "bzrsh")){
							$("shzt2").value = shzt;
						}			
					}
				}
			}else{
				if(jbdm3 != ""){
					var arrJb = jbdm3.split("-");
					for(var i=0;i<arrJb.length;i++){
						var yhsh = lx+"sh";
						if(arrJb[i] == yhsh){
							$("shzt3").value = shzt;
						}else if(lx == "jd" && (arrJb[i] == "fdysh" || arrJb[i] == "bzrsh")){
							$("shzt3").value = shzt;
						}			
					}
				}
			}
		}
		
		if(lx == "bzr"){
			if($("bzrsh")){$("bzrsh").disabled = false;}
			if($("bzrsh")){$("bzrsh").value = shzt;}
		}else if(lx == "fdy"){
			if($("fdysh")){$("fdysh").disabled = false;}
			if($("fdysh")){$("fdysh").value = shzt;}
		}else if(lx == "jd"){
			if($("bzrsh")){$("bzrsh").disabled = false;}
			if($("fdysh")){$("fdysh").disabled = false;}
			if($("bzrsh")){$("bzrsh").value = shzt;}
			if($("fdysh")){$("fdysh").value = shzt;}
		}else if(lx == "xy"){
			if($("xysh")){$("xysh").disabled = false;}
			if($("xysh")){$("xysh").value = shzt;}
		}else if(lx == "xx"){
			if($("xxsh")){$("xxsh").disabled = false;}
			if($("xxsh")){$("xxsh").value = shzt;}
		}
		saveUpdate(url,"xmzzjb-xmzzje-fj");
	}
	jQuery.ajaxSetup({async:true});
}

//设置意见的空值
function setShyj(){

	var qdje = "";
	if($("qdje")){
		qdje = $("qdje").value;
	}
	if($("xmzzje") && $("xmzzje").value == "null" ){
		$("xmzzje").value = qdje;
	}
	if($("shzt1yj") && $("shzt1yj").value == "null" ){
		$("shzt1yj").value = "";
	}
	if($("shzt2yj") && $("shzt2yj").value == "null" ){
		$("shzt2yj").value = "";
	}
	if($("shzt3yj") && $("shzt3yj").value == "null" ){
		$("shzt3yj").value = "";
	}
}

//设置家庭成员只读
function setJtcyReadOnly(){
	var tableName = "xszz_xmnrzdb"; 
	
	var dm = "zd"; 
	var mc = "zd";
	var msg = "";
	var pk = "xmdm||lyb";
	var pkValue = "";
	var mrxm = $("mrxm").value;
	
	if(mrxm == "否"){
		pkValue = "无jtqkdcb";
	}else{
		pkValue = $("xmdm").value+"jtqkdcb";
	}

	getXszzInfo.getXszzList(tableName, dm, mc, msg, pk, pkValue,function(data){
		if(data!=null && data.length >0 ){
			for(var i=0;i<data.length;i++){
				var id = data[i].dm;
				if($(id)){
					$(id).disabled = true;
				}
			}
		}
	});
}

//设置其他信息只读
function setQtxxReadOnly(){
	var tableName = "xszz_xmnrzdb"; 
	
	var dm = "zd"; 
	var mc = "zd";
	var msg = "";
	var pk = "xmdm||lyb";
	var pkValue = "";
	var mrxm = $("mrxm").value;
	
	if(mrxm == "否"){
		pkValue = "无xsqtxxb";
	}else{
		pkValue = $("xmdm").value+"xsqtxxb";
	}

	getXszzInfo.getXszzList(tableName, dm, mc, msg, pk, pkValue,function(data){
		if(data!=null && data.length >0 ){
			for(var i=0;i<data.length;i++){
				var id = data[i].dm;
				if($(id)){
					$(id).disabled = true;
				}
			}
		}
	});
}

//项目申请页面初始化
function onShow_xmsq(){
	var xmb = $("xmb").value;
	//申请项目不是家庭情况调查
	if(xmb != "jtqkdcb"){
		if (!(xmb == "xszz_knsb" && "yes" == $('iskns').value && "true" != $('knsdl').value && "true" != $('jtqkdc').value)){
			if($("jtcyCtrl")){
				$("jtcyCtrl").style.display = "none";
			}
			setJtcyReadOnly();
		}
		//setKzReadOnly();
	}
	if(xmb != "xsqtxxb"){
		setQtxxReadOnly();
	}
	//设置下拉列表信息
	setXmsqSelectList();
	//设置家庭成员信息
	setJtcyInfo();	
}

function time(id){
	return showCalendar(id,'y-mm-dd');
}


//导入数据
function impInfo(){

	var xmdm = $("xmdm").value;
	
	if(xmdm == ""){
		alert("请选择需要导入的项目");
		return false;
	}else{
		
		dwr.engine.setAsync(false);
		
		var tableName="xszz_zzxmb";
		var colList =["xmb"];
		var pk = "xmdm";
		var pkValue = xmdm;

		getXszzInfo.getXszzInfo(tableName,pk,pkValue,colList,function(data){
			if(data != null){
				$("realTable").value=data.xmb;
			}	
		});
		
		dwr.engine.setAsync(true);
		
		impAndChkData()
	}
}

//导出数据
function expInfo(){

	var xmdm = $("xmdm").value;
	
	if(xmdm == ""){
		alert("请选择需要导出的项目");
		return false;
	}else{
		wjcfDataExport('commXszz.do?method=jgcxManage&doType=exp&xmdm='+xmdm);
	}
}

//个性化查询条件
function getTjsz(){

	var xmdm = $("xmdm").value;
	
	if(xmdm != ""){
	
		dwr.engine.setAsync(false);
		
		var tableName = "xszz_zzxmb";
		var colList = ["sffj","shjb","sqzq"];
		var pk = "xmdm";
		var pkValue = xmdm;
		var query = "";
	
		getOtherData.getTableInfo(tableName, colList,pk, pkValue,query,function(data){
			if( data != null && data.length > 0){
				//是否分级
				var sffj = data[0];
				//审核级别
				var shjb = data[1];
				//申请周期
				var sqzq = data[2];
				
				if (sffj == "分级"){
					$("xmzzjb").disabled = false;
					$("jbId").style.display = "";
					$("jbVId").style.display = "";
					setXmjbList();
				}else{
					$("xmzzjb").disabled = true;
					$("jbId").style.display = "none";	
					$("jbVId").style.display = "none";
				}
				
				if (shjb == "一级审核"){
					$("shzt1").disabled = false;
					$("shzt2").disabled = true;
					$("shzt3").disabled = true;
					
					$("zt1Id").style.display = "";
					$("zt2Id").style.display = "none";
					$("zt3Id").style.display = "none";
					
					$("zt1VId").style.display = "";
					$("zt2VId").style.display = "none";
					$("zt3VId").style.display = "none";
					
				}else if (shjb == "两级审核"){
					$("shzt1").disabled = false;
					$("shzt2").disabled = false;
					$("shzt3").disabled = true;
					
					$("zt1Id").style.display = "";
					$("zt2Id").style.display = "";
					$("zt3Id").style.display = "none";
					
					$("zt1VId").style.display = "";
					$("zt2VId").style.display = "";
					$("zt3VId").style.display = "none";
					
				}else if (shjb == "三级审核"){
					$("shzt1").disabled = false;
					$("shzt2").disabled = false;
					$("shzt3").disabled = false;
					
					$("zt1Id").style.display = "";
					$("zt2Id").style.display = "";
					$("zt3Id").style.display = "";
					
					$("zt1VId").style.display = "";
					$("zt2VId").style.display = "";
					$("zt3VId").style.display = "";
					
				}else{
					
					$("shzt1").value = "";
					$("shzt2").value = "";
					$("shzt3").value = "";
					
					$("shzt1").disabled = true;
					$("shzt2").disabled = true;
					$("shzt3").disabled = true;
					
					$("zt1Id").style.display = "none";
					$("zt2Id").style.display = "none";
					$("zt3Id").style.display = "none";
					
					$("zt1VId").style.display = "none";
					$("zt2VId").style.display = "none";
					$("zt3VId").style.display = "none";
				}
				
				if (sqzq == "学年"){
					$("xn").disabled = false;
					$("xq").disabled = true;
					$("nd").disabled = true;
				}else if (sqzq == "学期"){
					$("xn").disabled = false;
					$("xq").disabled = false;
					$("nd").disabled = true;
				}else if (sqzq == "年度"){
					$("xn").disabled = true;
					$("xq").disabled = true;
					$("nd").disabled = false;
				}else{
					$("xn").disabled = true;
					$("xq").disabled = true;
					$("nd").disabled = true;
				}
			}
		});
		
		dwr.engine.setAsync(true);
	}else{
	
		if($("jbId")){$("jbId").style.display = "none";}
	
		if($("zt1Id")){$("zt1Id").style.display = "none";}
		if($("zt2Id")){$("zt2Id").style.display = "none";}
		if($("zt3Id")){$("zt3Id").style.display = "none";}
					
		$("xn").disabled = false;
		$("xq").disabled = false;
		$("nd").disabled = false;
	}
}

//设置分级列表值
function setXmjbList(){

	dwr.engine.setAsync(false);
	
	var tableName = "xszz_xmfjqkb";
	var colList = ["fjmc"];
	var pk = "xmdm";
	var pkValue = $("xmdm").value;
	var xmjb = $("xmjb").value;
	var query = "";
	
	var options = new Array();
	var n = 0;
	options[n]={dm:"",mc:"----请选择----"};
	n++;
				
	getOtherData.getTableListInfo(tableName,colList,pk,pkValue,query,function(data) {
		if(data!=null&&data.length>0){
			for(var i=0;i<data.length;i++){
				options[n]={dm:data[i].fjmc,mc:data[i].fjmc};
				n++;
			}
		}
		options[n]={dm:"未指定",mc:"未指定"};
		n++;
	});
					
	var id = "xmzzjb";
	DWRUtil.removeAllOptions(id);
	DWRUtil.addOptions(id,options,"dm","mc");
	
	if(xmjb != ""){
		$(id).value = xmjb;
	}
	dwr.engine.setAsync(true);
		
}

//修改项目类别
function chXmlb(){
	var tableName = "xszz_zzxmb"; 
	var dm = "xmdm"; 
	var mc = "xmmc";
	var msg = "----请选择----";
	var pk = "xmlb";
	var pkValue = $("xmlb").value;
	var id = "xmdm";
		
	if(pkValue == ""){
		pk = "";
	}
			
	getXszzInfo.getXszzList(tableName, dm, mc, msg, pk, pkValue,function(data){
		if(data!=null){
			DWRUtil.removeAllOptions(id);
			DWRUtil.addOptions(id,data,"dm","mc");
			$(id).options[0].value = "";
		}
	});
}

//展现添加页面
function showAddJgJsp(){

	var xmdm = $("xmdm").value;

	if(xmdm == ""){
		alert("请选择希望添加的资助项目！");
		return false;
	}
	var iskns = $('iskns').value;
	var url = "/xgxt/commXszz.do?method=jgcxUpdate&iskns="+iskns;
		url+= "&xmdm="+xmdm;
		
	showOpenInfo(url,'add','','800','600');
}

//文件上传
function fileUpload(url) {
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 400;
	var d_height_top = 250;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 0.5;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px;'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<iframe name='mainFrame' style='height:100%; width:100%; ' marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' src='";
	dd_html += url;
	dd_html += "'></iframe>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
}	



var array = new Array();

//检测是否有勾中记录,如果是勾中的放入数组供批量操作用
function isChecked(tagName) {
	var checkBoxArr = document.getElementsByName(tagName);
	var flag = false;
	var n = 0;
	for (var i = 0; i < checkBoxArr.length; i++) {
		if (checkBoxArr[i].checked == true) {
			flag = true;
			array[n] = checkBoxArr[i];
			n++;
		}
	}
	if (flag) {
		if (confirm("\u786e\u5b9a\u8981\u6279\u91cf\u8bbe\u7f6e\u6240\u9009\u62e9\u7684\u6570\u636e\u5417\uff1f")) {
			return true;
		}
	} else {
		alert("\u6ca1\u6709\u9009\u62e9\u76f8\u5e94\u8bb0\u5f55\uff0c\u8bf7\u9009\u62e9\u4e4b\u540e\u518d\u8fdb\u884c\u64cd\u4f5c!");
		return false;
	}
}

//判断字符是否已经
function checkZs(){
	num=$("rssx").value;
	if(num.length>1 && num.substring(0,1)=="0"){
		alert("人数上限必须为整数!");
		$("rssx").focus();
		return false;
	}
	
	//平均成绩
	num=$("pjcjz").value;
	//不带小数
	if($("pjcjtj").value!="" && num.indexOf(".")=="-1" 
		&& num.length>1 && num.substring(0,1)=="0"){
		
		alert("平均成绩输入格式不正确!");
		$("pjcjz").focus();
		return false;
	}else if($("pjcjtj").value!="" && !isDouble(num)){
		alert("平均成绩输入格式不正确!");
		$("pjcjz").focus();
		return false;
	}
	
	//不及格门次
	num=$("bjgkms").value;
	if(num.length>1 && num.substring(0,1)=="0"
		&& !$('bjgkm').checked){
		alert("不及格门次必须为整数!");
		$("bjgkms").focus();
		return false;
	}
	saveXmwh();
}







