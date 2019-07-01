//判断分数是否超过100分
function chFs(obj){
	if(obj.value>100){
		alert("分数不能超过100分，请确认！！");
		obj.focus();
	}
}

//评奖评优德育分初始值
function onShow(){
	var xydm = $("xyV").value;
	var zy = $("zyV").value;
	var bj = $("bjV").value;
	var nj = $("njV").value;
	var xn = $("xnV").value;
	var xh = $("xhV").value;
	var xm = $("xmV").value;
		
	for(var i=0;i<$("xy").options.length;i++){
		if($("xy").options[i].value == xydm){
			$("xy").options[i].selected = true;
		}
	}
	for(var i=0;i<$("zy").options.length;i++){
		if($("zy").options[i].value == zy){
			$("zy").options[i].selected = true;
		}
	}
	for(var i=0;i<$("bj").options.length;i++){
		if($("bj").options[i].value == bj){
			$("bj").options[i].selected = true;
		}
	}
	if($("userType").value == "xy" || $("userType").value == "teacher"){
		$("xy").disabled = true;
	}

	$("nj").value = nj;
	$("xn").value = xn 
	$("xh").value = xh 
	$("xm").value = xm;
	
}

//
function pjpyCpzfpzj() {
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var userType = $("userType").value;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 350;
	var d_height_top = 200;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<table width='350' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<th colspan='2'>";
	dd_html += "<span>参评组增加</span>";
	dd_html += "</th>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<th align='right'>";
	dd_html += "所属"+jQuery("#xbmc").val();
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	if(userType=='xy'){
		dd_html += "<select name='cpzxydm' id ='cpzxydm' style='width:150px' disabled='true'>" 
		dd_html += $('xy').innerHTML;
		dd_html += "</select>";
	}else{
		dd_html += "<select name='cpzxydm' id ='cpzxydm' style='width:150px'>" 
		dd_html += $('xy').innerHTML;
		dd_html += "</select>";
	}
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='left' colspan='2'>";
	dd_html += "注：  参评组代码由"+jQuery("#xbmc").val()+"代码加上您所输入的代码生成";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<th align='right'>";
	dd_html += "参评组代码";
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += "<input type='test' id ='zjcpzdm' name = 'zjcpzdm'/>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<th align='right'>";
	dd_html += "参评组名称";
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += "<input type='test' id = 'cpzmc' name = 'cpzmc'/>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "<tfoot><tr><td colspan='2' align='right'>";
	dd_html += "<button onclick='cpzzj()'>确定</button>";
	dd_html += "<button onclick='closeAdd1()'>取消</button>";
	dd_html += "</td></tr></tfoot>"
	dd_html += "</table>";
	dd_html += "<iframe  src='javascript:false' border=0px  style='position: absolute;visibility: inherit;top: 0px;left: 0px;width: 350px;height: 200px;z-index: -999;filter: Alpha(Opacity=0)'></iframe>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
}

function cpzzj(){
	if($('cpzxydm').value==''){
		alert("请选择参评组所在"+jQuery("#xbmc").val());
		return;
	}
	if($('zjcpzdm').value==''){
		alert('请填写参评组代码');
		return;
	}
	if($('cpzmc').value==''){
		alert('请填写参评组名称');
		return;
	}
	refreshForm('zjlgPjpy.do?method=addCpz')
}

function addBjBatchColum(){
		//参评组分配         
        var wfpBj =  document.getElementById("wfpBj");
        var cpz =  document.getElementById("cpz");
        var yfpbj= document.getElementById("yfpbj"); 
        var yfpLength = yfpbj.length;      
        var wfpBjNum = 0;
        var j = 0;
        var bjdms =  Array();
	    var bjmcs =  Array();	
	    
        if(cpz.value==''){
		   alert('请选中参评组后，再进行操作！');
		   return false;
	    }
	    //alert(wfpBj.options.length);
        for(var i=0;i<wfpBj.options.length;i++){
		    if(wfpBj.options[i].selected){
		    	bjdms[j]=wfpBj.options[i].value;
                bjmcs[j]=wfpBj.options[i].text;
                wfpBj.options[i]=null;
			  	wfpBjNum++;
			  	i--;
			  	j++;
		    }
	    }
	    if(wfpBj==0){
		   alert('请在左边\'未分配班级\'列表中，选择一条或多条记录！');
		   return false;
	    }
	    //alert(j);
	    var yfpbjz = Array();
	    for(var i = 0;i<yfpbj.length;i++){
	    	yfpbjz[i] = yfpbj.options[i].value;
	    }
	    
	    for (i = 0; i < j; i++) {     	   
             yfpbj.options[yfpLength++] = new Option(bjmcs[i],bjdms[i]);                                                       
        }	        	
}

function addBjBatchColum(){
		//参评组分配         
        var wfpBj =  document.getElementById("wfpBj");
        var cpz =  document.getElementById("cpz");
        var yfpbj= document.getElementById("yfpbj"); 
        var yfpLength = yfpbj.length;      
        var wfpBjNum = 0;
        var j = 0;
        var bjdms =  Array();
	    var bjmcs =  Array();	
	    
        if(cpz.value==''){
		   alert('请选中参评组后，再进行操作！');
		   return false;
	    }
	    //alert(wfpBj.options.length);
        for(var i=0;i<wfpBj.options.length;i++){	
		    if(wfpBj.options[i].selected){
		    	bjdms[j]=wfpBj.options[i].value;
                bjmcs[j]=wfpBj.options[i].text;
                wfpBj.options[i]=null;
			  	wfpBjNum++;
			  	i--;
			  	j++;
		    }
	    }
	    if(wfpBjNum==0){
		   alert('请在左边\'未分配班级\'列表中，选择一条或多条记录！');
		   return false;
	    }
	    //alert(j);
	    for (i = 0; i < j; i++) {     	   
             yfpbj.options[yfpLength++] = new Option(bjmcs[i],bjdms[i]);                                                       
        }	        	
}

function delBjBatchColum(){
		//参评组分配         
        var wfpBj =  document.getElementById("wfpBj");
        var cpz =  document.getElementById("cpz");
        var yfpbj= document.getElementById("yfpbj"); 
        var wfpLength = wfpBj.length;      
        var yfpBjNum = 0;
        var j = 0;
        var bjdms =  Array();
	    var bjmcs =  Array();	
	    
        if(cpz.value==''){
		   alert('请选中参评组后，再进行操作！');
		   return false;
	    }
	    //alert(wfpBj.options.length);
        for(var i=0;i<yfpbj.options.length;i++){	
		    if(yfpbj.options[i].selected){
		    	bjdms[j]=yfpbj.options[i].value;
                bjmcs[j]=yfpbj.options[i].text;
                yfpbj.options[i]=null;
			  	yfpBjNum++;
			  	i--;
			  	j++;
		    }
	    }
	    if(yfpBjNum==0){
		   alert('请在左边\'已分配班级\'列表中，选择一条或多条记录！');
		   return false;
	    }
	    //alert(j);
	    for (i = 0; i < j; i++) {     	   
             wfpBj.options[wfpLength++] = new Option(bjmcs[i],bjdms[i]);                                                       
        }	        	
}

function saveYfbbj(){
	var bjText = '';
	var bjValue ='';
	for (i = 0; i < $('yfpbj').options.length; i++) {		
		 bjValue += $('yfpbj').options[i].value+'!!';
	}
	$('bjdms').value = bjValue;
	refreshForm('zjlgPjpy.do?method=saveCpzFp');
}

function chkPriseBatForZjlg() {
	var jxjMsg = document.getElementById("jxjdm").options[document.getElementById("jxjdm").selectedIndex].text;
	var xyMsg = document.getElementById("xy").options[document.getElementById("xy").selectedIndex].text;
	var cpzMsg = document.getElementById("cpz").options[document.getElementById("cpz").selectedIndex].text;
	var bjMsg = document.getElementById("bj").options[document.getElementById("bj").selectedIndex].text;
	var userType =$("userType").value;
	var dispFlag = $("dispFlag").value;
	if(userType=="xy"&&dispFlag=="xydm"){
		alert(jQuery("#xbmc").val()+"不能批量设置学校设置的人数");
		return;
	}
	jxjMsg = (jxjMsg == "") ? "全部" : jxjMsg;
	xyMsg = (xyMsg == "") ? "全部" : xyMsg;
	cpzMsg = (cpzMsg == "") ? "全部" : cpzMsg;
	bjMsg = (bjMsg == "") ? "全部" : bjMsg;
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 300;
	var d_height_top = 200;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<div class='tab'>"
	dd_html += "<table width='300' class='formlist'>";
	dd_html += "<thead>";
	dd_html += "<tr>";
	dd_html += "<th colspan='2'>";
	dd_html += "<span>批量设置</span>";
	dd_html += "</th>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr>";
	dd_html += "<th align='right'>";
	dd_html += "所属"+jQuery("#xbmc").val();
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += xyMsg;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr>";
	dd_html += "<th align='right'>";
	dd_html += "参评组";
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += cpzMsg;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr>";
	dd_html += "<th align='right'>";
	dd_html += "班级";
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += bjMsg;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr>";
	dd_html += "<th align='right'>";
	dd_html += "奖学金";
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += jxjMsg;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr>";
	dd_html += "<th align='right'>";
	dd_html += "设定比例:";
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += "<input type='test' id = 'szbl' name = 'szbl' style='width:60px' onkeypress='return numInputValue(this,5,event)' maxlength='5'/>%";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "<tfoot><tr><td colspan='2' align='right'>";
	dd_html += "<button onclick=plszSave('zjlgPjpy.do?method=plszSave')>确定</button>";
	dd_html += "<button onclick='closeAdd1()'>关闭</button>";
	dd_html += "</td></tr></tfoot>"
	dd_html += "</table>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
}

function dgrstz() {
	var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight;
	var userType =$("userType").value;
	var dispFlag = $("dispFlag").value;
	if(userType=="xy"&&dispFlag=="xydm"){
		alert(jQuery("#xbmc").val()+"不能修改学校设置的人数");
		return;
	}
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 300;
	var d_height_top = 120;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<table width='300' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<th colspan='2'>";
	dd_html += "<span>单个调整</span>";
	dd_html += "</th>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<th align='right'>";
	dd_html += "人数调整为";
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += "<input type='test' id = 'jxjtzrs' name = 'jxjrs' style='width:60px' onkeypress='return numInputValue(this,5,event)' maxlength='5'/>人";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr bgcolor='EEF4F9'>";
	dd_html += "<td colspan='2' align='center'>";
	dd_html += "<button class='button2' onclick=if($('jxjtzrs').value==''){alert('调整人数不能为空')}else{refreshForm('zjlgPjpy.do?method=saveJxjRs&pk=";
	dd_html += 	pk;
	dd_html += 	"');}>确定</button>";
	dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<button class='button2' onclick='closeAdd1()'>取消</button>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "<tfoot><tr><td colspan='2' align='right'>";
	dd_html += "</td></tr></tfoot>"
	dd_html += "</table>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
	
}

function plszSave(url){
	if($('szbl').value > 100 || $('szbl').value == ''){
		alert('建议人数比例不符合标准！');
		return;
	}
	refreshForm(url);
}

function priseDataInitForZjlg() {
	if (confirm("初始化操作将会清空当前学年的所有数据，并重新生成。\n确定要初始化吗?")) {
		var dd_html = "";
		dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>正在初始化数据，请稍候......<br><br>";
		dd_html += "<span class='roll_tip'></span>";
		dd_html += "</td></tr></table>";
		for (i = 1; i < document.getElementsByTagName("table").length; i++) {
			document.getElementsByTagName("table")[i].style.display = "none";
		}
		showDiv(dd_html, 300, 120);
		refreshForm("/xgxt/zjlgPjpy.do?method=jxjcsh");
		return true;
	}
	return false;
}

function zjlgPriseConfJxj(url) {
	var jxjMsg = document.getElementById("jxjdm").value;
	var xyMsg = document.getElementById("xy").value;
	var bjMsg = document.getElementById("bj").value;
	if ((xyMsg + jxjMsg + bjMsg) == "") {
		if (confirm("您没有选择任何条件,查询将耗费较长时间.\n确定要继续吗?")) {
			document.forms[0].go.value = "go";
			refreshForm(url);
			return true;
		} else {
			return false;
		}
	} else {
		document.forms[0].go.value = "go";
		refreshForm(url);
		return true;
	}
}

function chgPriseXn() {
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 350;
	var d_height_top = 120;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<table width='300' class='formlist'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td colspan='2'>";
	dd_html += "<span>调整学年</span>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<th align='right'>";
	dd_html += "评奖评优学年调整为";
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += "<select name='jxjxn' id ='jxjxn' style='width:150px'>" 
	dd_html += $('xn').innerHTML;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "<tfoot><tr><td colspan='2' align='right'>"
	dd_html += "<button onclick=refreshForm('zjlgPjpy.do?method=tzjxjxn');>确定</button>";
	dd_html += "<button onclick='closeAdd1()'>取消</button>";
	dd_html += "</td></tr></tfoot>"
	dd_html += "</table>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
}

function chkXjbjBat() {
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 350;
	var d_height_top = 120;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<table width='300' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td colspan='2'>";
	dd_html += "-------------------先进班级批量设置--------------------";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "设定比例:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += "<input type='test' id = 'szbl' name = 'szbl' style='width:60px' onkeypress='return numInputValue(this,5,event)' maxlength='5'/>%";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr bgcolor='EEF4F9'>";
	dd_html += "<td colspan='2' align='center'>";
	dd_html += "<button class='button2' onclick=plszSave('zjlgPjpy.do?method=xjbjPlszSave') >确定</button>";
	dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<button class='button2' onclick='closeAdd1()'>取消</button>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "</table>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
}

function xjbjrstz() {
	var xxdm = $("xxdm").value;
	var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
	var xymc =	curr_row.cells[1].innerText;
	var bjsl =	curr_row.cells[2].innerText;
	var szbl =	curr_row.cells[3].innerText;
	var jsme = bjsl*szbl;
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 300;
	var d_height_top = 120;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<table width='300' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td colspan='2'>";
	dd_html += "------------------------单个调整------------------------";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += jQuery("#xbmc").val()+"名称:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += xymc;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "班级数量:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += bjsl;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "设置比例:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += szbl;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "计算名额:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += Math.floor(jsme,'2');
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	if("10657" == xxdm){//贵州大学
		dd_html += "班级调整个数为:";
	}else{
		dd_html += "人数调整为:";
	}
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += "<input type='test' id = 'tzme' name = 'me' style='width:60px' onkeypress='return numInputValue(this,5,event)' maxlength='5'/>";
	if("10657" == xxdm){//贵州大学
		dd_html += "个";
	}else{
		dd_html += "人:";
	}
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr bgcolor='EEF4F9'>";
	dd_html += "<td colspan='2' align='center'>";
	dd_html += "<button class='button2' onclick=if($('tzme').value==''){alert('调整人数不能为空')}else{refreshForm('zjlgPjpy.do?method=saveXjbjMe&pk=";
	dd_html += 	pk;
	dd_html += 	"');}>确定</button>";
	dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<button class='button2' onclick='closeAdd1()'>取消</button>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "</table>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
	
}

function rychDataInitForZjlg() {
	if (confirm("初始化操作将会清空当前学年的所有数据，并重新生成。\n确定要初始化吗?")) {
		var dd_html = "";
		dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>正在初始化数据，请稍候......<br><br>";
		dd_html += "<span class='roll_tip'></span>";
		dd_html += "</td></tr></table>";
		for (i = 1; i < document.getElementsByTagName("table").length; i++) {
			document.getElementsByTagName("table")[i].style.display = "none";
		}
		showDiv(dd_html, 300, 120);
		refreshForm("/xgxt/zjlgPjpy.do?method=rychcsh");
		return true;
	}
	return false;
}

function chkRychBatForZjlg() {
	var rychMsg = document.getElementById("rychdm").options[document.getElementById("rychdm").selectedIndex].text;
	var xyMsg = document.getElementById("xy").options[document.getElementById("xy").selectedIndex].text;
	var zyMsg = document.getElementById("zy").options[document.getElementById("zy").selectedIndex].text;
	var bjMsg = document.getElementById("bj").options[document.getElementById("bj").selectedIndex].text;
	rychMsg = (rychMsg == "") ? "全部" : rychMsg;
	xyMsg = (xyMsg == "") ? "全部" : xyMsg;
	zyMsg = (zyMsg == "") ? "全部" : zyMsg;
	bjMsg = (bjMsg == "") ? "全部" : bjMsg;
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 300;
	var d_height_top = 200;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<table width='300' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td colspan='2'>";
	dd_html += "------------------------批量设置------------------------";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "所属"+jQuery("#xbmc").val()+":";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += xyMsg;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "专业:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += zyMsg;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "班级:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += bjMsg;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "荣誉称号:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += rychMsg;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "设定比例:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += "<input type='test' id = 'szbl' name = 'szbl' style='width:60px' onkeypress='return numInputValue(this,5,event)' maxlength='5'/>%";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr bgcolor='EEF4F9'>";
	dd_html += "<td colspan='2' align='center'>";
	dd_html += "<button class='button2' onclick=plszSave('zjlgPjpy.do?method=rychPlszSave')>确定</button>";
	dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<button class='button2' onclick='closeAdd1()'>取消</button>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "</table>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
}

function rychrstz() {
	var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 300;
	var d_height_top = 120;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<table width='300' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td colspan='2'>";
	dd_html += "------------------------单个调整------------------------";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "人数调整为:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += "<input type='test' id = 'tzrychrs' name = 'rychrs' style='width:60px' onkeypress='return numInputValue(this,5,event)' maxlength='5'/>人";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr bgcolor='EEF4F9'>";
	dd_html += "<td colspan='2' align='center'>";
	dd_html += "<button class='button2' onclick=if($('tzrychrs').value==''){alert('调整人数不能为空')}else{refreshForm('zjlgPjpy.do?method=saveRychRs&pk=";
	dd_html += 	pk;
	dd_html += 	"');}>确定</button>";
	dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<button class='button2' onclick='closeAdd1()'>取消</button>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "</table>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
	
}

function getJxjList(jxjlb){
	getCpzfp.getJxjList(jxjlb,function(data){
		if (data != null && typeof data == 'object') {
			var objId = "jxjdm";
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"jxjdm","jxjmc");		
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
	});
}

function zjlgChgDispConf(eID) {
	var obj = document.getElementById(eID);
	if (obj.value == "bjdm") {
		//dispZy.style.display = "";
		document.getElementById("cpz").disabled = false;
		//dispBj.style.display = "";
		document.getElementById("bj").disabled = false;
	} else {
		if (obj.value == "cpzdm") {
		//dispZy.style.display = "";
			document.getElementById("cpz").disabled = false;
		//dispBj.style.display = "none";
			document.getElementById("bj").disabled = true;
		} else {
			if (obj.value == "xydm") {
		//dispZy.style.display = "none";
				document.getElementById("cpz").disabled = true;
		//dispBj.style.display = "none";
				document.getElementById("bj").disabled = true;
			}
		}
	}
}
