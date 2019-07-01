 var count = 1;
 var da = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
 
 function trAdd(the_tab,type){
 	var stlx = $("stlx").value;
 	var btnId = "";
	//oneChoose : 单选题
	//allChoose ：多选题
	if(stlx == "oneChoose"){
		btnId = "one_numAdd";
	}else if(stlx == "allChoose"){
		btnId = "all_numAdd";
	}
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $(btnId).value;
    var cellfu = new Array();
    
    if(len > 26){
    	return false;
    }
    
    if(num > 26){
    	num = 26;
    }
	
    count=len;  
          
		cellfu =[
		function(data){
			return "<input type='hidden' style='width:100%'  name='dabh"+stlx+"' id='dabh"+count+"' value='"+da[count-1]+"'>"+da[count-1];
		},
	    function(data){
	    	var htmltext = "<input type='text' style='width:100%'  name='damc"+stlx+"' id='damc" + stlx + count + "'";
	    		htmltext += "onblur='chLeng(this,500)'/>";	
	    	return htmltext;
	    },
	    function(data){
	    	if(stlx == "oneChoose"){
		    	var htmltext = "<input type='radio' name='bzda"+stlx+"' id='bzda" + count + "' value='"+da[count-1]+"'/>";	
		    	return htmltext;
	    	}else if(stlx == "allChoose"){
				var htmltext = "<input type='checkbox' name='bzda"+stlx+"' id='bzda" + count + "' value='"+da[count-1]+"'/>";	
		    	return htmltext;
			}
	    }
		];

	if(type=='add'){
       DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
    }else{
       if(num==""||num==null){	
           return false;
       }
       for(i=count;i<=num;i++){          
          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
          count++;
       }
       $(btnId).value = "";
    }    
}

function trDel(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    
    //var the_table = getPapaElement(the_td, "tbody");
	//alert(the_table.rows.length);
   
   if(confirm("确定要删除第"+(length)+"行？")){
    tabobj.deleteRow(tabobj.rows.length-1);      
   	}
}
function trDelAll(the_tab){

	var stlx = $("stlx").value;
 	var btnId = "";
	//oneChoose : 单选题
	//allChoose ：多选题
	if(stlx == "oneChoose"){
		btnId = "one_numDel";
	}else if(stlx == "allChoose"){
		btnId = "all_numDel";
	}
	
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;
    var num = $(btnId).value; 
    if(length==0){
       $(btnId).value = "";
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
    $(btnId).value = "";
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
		
function showStDiv(){

	var showEle = [];
	var hiddenEle = [];
	var stlx = $("stlx").value;
	
	if(stlx == "oneChoose"){//oneChoose : 单选题
	
		showEle = ["oneChoose"];
		hiddenEle = ["allChoose","questions"];		
			
	}else if(stlx == "allChoose"){//allChoose ：多选题
	
		showEle = ["allChoose"];	
		hiddenEle = ["oneChoose","questions"];	
			
	}else if(stlx == "questions"){//questions ：问答题
	
		showEle = ["questions"];	
		hiddenEle = ["oneChoose","allChoose"];		
		
	}else{
	
		hiddenEle = ["oneChoose","allChoose","questions"];		
			
	}
	changeDisplay(showEle,hiddenEle);
}

function onShow(){

	var stlx = $("stlx").value;
	var stbh = $("stbh").value;
	var btnId = "";
	var tbId = "";
	//oneChoose : 单选题
	//allChoose ：多选题
	if(stlx == "oneChoose"){
		btnId = "one_numAdd"
		tbId = "one_flag";
	}else if(stlx == "allChoose"){
		btnId = "all_numAdd";
		tbId = "all_flag";
	}
	
	showStDiv();
	
	if(stbh != ""){
	
		dwr.engine.setAsync(false);
		
		var tableName="wjdc_stdab";
		var colList =["dabh","damc","bzda"];
		var pk = "stbh";
		var pkValue = stbh;
		var query =" ";
		
		getOtherData.getTableListInfo(tableName, colList,pk, pkValue,query,function(data){
			if( data != null && data.length > 0){
				if(stlx == "questions"){//问答题	
					var ckda = data[0].damc
					if( ckda != null){
						$("damc").value = ckda;;
					}
				}else{
					$(btnId).value=data.length;
					trAdd(tbId,'madd');		
					for(var i=1;i<=data.length;i++){
						if($("damc"+stlx+i)){
							var damc = data[i-1].damc;
							if(damc == null){
								damc = "";
							}
							$("damc"+stlx+i).value = damc;
						}
						if($("bzda"+i)){
							var bzda = data[i-1].bzda;
							if(bzda == "yes")						
							$("bzda"+i).checked = true;
						}
					}	
				}
			}	
		});
			
		dwr.engine.setAsync(true);
		
	}
}

function saveSt(){
	var stlx = $("stlx").value;
	if( stlx == "questions"){//问答题
		saveUpdate('/xgxt/nbcsPjpy.do?method=stSave&doType=save','stmc-stlx');
	}else{//选择题
		saveXzt();
	}
}

//保存选择题
function saveXzt(){

	var the_tab = "";
	var stlx = $("stlx").value;
	var stbh = $("stbh").value;
	var stmc = $("stmc").value;
	//oneChoose : 单选题
	//allChoose ：多选题
	if(stlx == "oneChoose"){
		the_tab = "one_flag";
	}else if(stlx == "allChoose"){
		the_tab = "all_flag";
	}else if(stlx == ""){
		alert("请确认试题类型!");
		return false;
	}
	
	if(stmc == ""){
		alert("请确认试题名称!");
		return false;
	}
	
	var tabobj = document.getElementById(the_tab);
	var rowLen = tabobj.rows.length;
	var nullCout = 0;
	
	if(rowLen == 0){
		alert("答案数目不能为空，请确认！");
		return false;
	}
	for(var i=1;i<=rowLen;i++){
		if($("damc"+stlx+i)){
			if($("damc"+stlx+i).value == ""){
				alert("第"+i+"行答案名称为空，请确定");
				return false;
			}
		}
	}
	saveUpdate('/xgxt/nbcsPjpy.do?method=stSave&doType=save','stmc-stlx');
}
	
	function getWjList(){
		
		var xn = "";
		
		if($("xn")){
			xn= $("xn").value;
		}
		
		var xq = $("xq").value;
		var nd = $("nd").value;
		//var jlsj = $("jlsj").value;
		var objId = "id";
		var wjmc = $(objId).value;
		
		if($(objId)){
			dwr.engine.setAsync(false);
	
			getPjpyNbcsDAO.getWjmcList(xn,nd,xq,function(data){
				if( data != null && data.length > 0){	
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"id","wjmc");
					$(objId).options[0].value="";
				}else{
					DWRUtil.removeAllOptions(objId);
				}
			});
				
			dwr.engine.setAsync(true)
		}
		
		if(wjmc != ""){
			$(objId).value = wjmc;
		}
	}
	
	function saveZjInfo(){
		
		var id = $("id").value;
		var wjmc = "";
		
		if(id == ""){
			alert("问卷不能为空，请确认！！");
			return false;
		}
		
		for(var i=0;i<$("id").options.length;i++){
			if($("id").options[i].selected){
				wjmc=$("id").options[i].text;
			}
		}
			
		for(var i = 0 ; i < $("xyRight").options.length; i++){
			var tmp = document.createElement("input");
			tmp.type = "hidden";
			tmp.name = "fpbh";
			tmp.value = $("xyRight").options[i].value;
			document.forms[0].appendChild(tmp);
		}
		
		if (confirm("将要为'"+wjmc+"'进行试题组卷，确认所选试题？")) {
			saveUpdate("/xgxt/nbcsPjpy.do?method=zjManage&doType=save","id");
		}
	}
	
	function setTkList(){
		
		dwr.engine.setAsync(false);
			
		var wjmc=$("id").value;
		var stlx=$("stlx").value;
		var stss=$("stss").value;
		
		var inputValue = [wjmc,stlx,stss];
		var objId = "xydm";
		
		getPjpyNbcsDAO.getTkxxList(inputValue,function(data){
			if( data != null && data.length > 0){	
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"dm","mc");
				$(objId).options[0]=null;
			}else{
				DWRUtil.removeAllOptions(objId);
			}
		});
			
		dwr.engine.setAsync(true)
	}
	
	function setWjstList(){
	
		dwr.engine.setAsync(false);
			
		var wjmc=$("id").value;;
		var objId = "xh";
		
		getPjpyNbcsDAO.getWjstList(wjmc,function(data){
			if( data != null && data.length > 0){	
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"dm","mc");
			}else{
				DWRUtil.removeAllOptions(objId);
			}
		});
			
		dwr.engine.setAsync(true)
	}
	
	function showStInfo(pk){
		var url = "/xgxt/nbcsPjpy.do?method=stUpdate";
		url+="&doType=view";
		url+="&pk="+pk;
		showTopWin(url,'600','480');
	}
	
	//统计问卷
	function tjwj(){
		if(curr_row == null){
			alert("请选择要统计的问卷");
			return false;
		}else{
			showTjtjDiv('hdqk');
		}
	}
	
	//生成问卷回答回答情况DIV
	function showHdqkDiv(){
		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight ;
		var d_left = 0;
		var d_top = 0;
		var d_color = "#EEF4F9";
		var d_width_top = 250;
		var d_height_top = 120;
		var d_left_top = (d_width - d_width_top) / 2;
		var d_top_top = (d_height - d_height_top)/ 3;
		var d_color_top = "#EEF4F9";
		dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
		dd_html += "</div>";
		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
		dd_html += "<table width='350' class='tbstyle'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "--------------------------统计范围-------------------------";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "年级:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='njTj' id ='njTj' onchange='getZyList();getBjList();'>" 
		dd_html += $('nj').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "院系:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='xyTj' id ='xyTj' onchange='getZyList();getBjList()'>" 
		dd_html += $('xy').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "专业:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='zyTj' id ='zyTj' onchange='getBjList();'>" 
		dd_html += $('zy').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "班级:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='bjTj' id ='bjTj'>" 
		dd_html += $('bj').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "性别:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='xbTj' id ='xbTj'>" 
		dd_html += $('xb').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "政治面貌:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='zzmmTj' id ='zzmmTj'>" 
		dd_html += $('zzmm').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button class='button2' onclick='startTj()';>统计</button>";
		dd_html += "&nbsp;&nbsp;";
		dd_html += "<button class='button2' onclick='jgtjToExcel()';>导出</button>";
		dd_html += "&nbsp;&nbsp;";
		dd_html += "<button class='button2' onclick='closeAdd1()'>取消</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		
		tmpdiv1.innerHTML = dd_html;
	}
	
	function getZyList(){
			
		var tableName = "view_njxyzybj"; 
		var dm = "zydm"; 
		var mc = "zymc";
		var msg = "----请选择----";
		var pk = "nj||xydm";
		var pkValue = $("njTj").value + $("xyTj").value;
		var obId = "zyTj";
		if(pkValue == ""){
			pk = "";
		}
			
		getPjpyInfo.getPjpyList(tableName, dm, mc, msg, pk, pkValue,function(data){
			if(data!=null){
				DWRUtil.removeAllOptions(obId);
				DWRUtil.addOptions(obId,data,"dm","mc");
				$(obId).options[0].value = "";
			}
		});
	}
	
	function getBjList(){
			
		var tableName = "view_njxyzybj"; 
		var dm = "bjdm"; 
		var mc = "bjmc";
		var msg = "----请选择----";
		var pk = "nj||xydm||zydm";
		var pkValue = $("njTj").value + $("xyTj").value + $("zyTj").value;
		var obId = "bjTj";
		if(pkValue == ""){
			pk = "";
		}
			
		getPjpyInfo.getPjpyList(tableName, dm, mc, msg, pk, pkValue,function(data){
			if(data!=null){
				DWRUtil.removeAllOptions(obId);
				DWRUtil.addOptions(obId,data,"dm","mc");
				$(obId).options[0].value = "";
			}
		});
	}
	
	function startTj(){
	
		var nj = $("njTj").value;
		var xy = $("xyTj").value;
		var zy = $("zyTj").value;
		var bj = $("bjTj").value;
		var xb = $("xbTj").value;
		var zzmm = $("zzmmTj").value;
		
		var url = "/xgxt/nbcsPjpy.do?method=hdtjUpdate";
		url+="&nj="+nj;
		url+="&xy="+xy;
		url+="&zy="+zy;
		url+="&bj="+bj;
		url+="&xb="+xb;
		url+="&zzmm="+zzmm;
		
		showInfo(url,'view','800','600');
		
		dd_html = "";
		tmpdiv1.innerHTML = dd_html;
	}
	
	//生成统计条件DIV
	function showTjtjDiv(lx){
	
		$("lx").value=lx;
		
		if(lx == "hdqk"){//回答情况
			showHdqkDiv();
		}else if(lx == "hsqk"){//回收情况
			showHsqkDiv();
		}
	}
	
	//生成回收情况DIV
	function showHsqkDiv(){
		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight ;
		var d_left = 0;
		var d_top = 0;
		var d_color = "#EEF4F9";
		var d_width_top = 250;
		var d_height_top = 120;
		var d_left_top = (d_width - d_width_top) / 2;
		var d_top_top = (d_height - d_height_top)/ 2;
		var d_color_top = "#EEF4F9";
		dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
		dd_html += "</div>";
		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
		dd_html += "<table width='350' class='tbstyle'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "--------------------------统计范围-------------------------";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "学年:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='xnTj' id ='xnTj' onchange='getWjtjList()'>" 
		dd_html += $('xn').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "年度:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='ndTj' id ='ndTj' onchange='getWjtjList()'>" 
		dd_html += $('nd').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "学期:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='xqTj' id ='xqTj' onchange='getWjtjList()'>" 
		dd_html += $('xq').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "问卷:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='idTj' id ='idTj'>" 
		dd_html += $('id').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button class='button2' onclick='wjtjToExcel()';>确定</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button class='button2' onclick='closeAdd1()'>取消</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		tmpdiv1.innerHTML = dd_html;
	}
	
	function getWjtjList(){
		
		var objId = "idTj";
		var xn = $("xnTj").value;
		var nd = $("ndTj").value;
		var xq = $("xqTj").value;
		
		dwr.engine.setAsync(false);

		getPjpyNbcsDAO.getWjmcList(xn,nd,xq,function(data){
			if( data != null && data.length > 0){	
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"id","wjmc");
				$(objId).options[0].value="";
			}else{
				DWRUtil.removeAllOptions(objId);
			}
		});
			
		dwr.engine.setAsync(true)
		
	}
	
	function wjtjToExcel(){	
		
		var id = $("idTj").value;

		if(id == ""){
			alert("请确定要统计的问卷");
			return false;
		}
		
		var url = "/xgxt/nbcsPjpy.do?method=bbtjManage";
			url += "&id="+id;
			
		document.forms[0].action = url;
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}
	
	function jgtjToExcel(){	
		
		var nj = $("njTj").value;
		var xy = $("xyTj").value;
		var zy = $("zyTj").value;
		var bj = $("bjTj").value;
		var xb = $("xbTj").value;
		var zzmm = $("zzmmTj").value;
		var pk = curr_row.getElementsByTagName('input')[0].value;

		var url = "/xgxt/nbcsPjpy.do?method=bbtjManage";
		url+="&lx=jgtj"	
		url+="&pk="+pk;
		url+="&nj="+nj;
		url+="&xy="+xy;
		url+="&zy="+zy;
		url+="&bj="+bj;
		url+="&xb="+xb;
		url+="&zzmm="+zzmm;
		
		document.forms[0].action = url;
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}
	
	function getWjInfo(){
	
		var xn = $("xn").value;
		var xq = $("xq").value;
		var nd = $("nd").value;
		var wjmc = $("wjmc").value;
		var jlsj = $("jlsj").value;
		
		var tableName = "view_wjdc_wjxx";
		var colList = ["id","xqmc", "xn", "nd", "xq", "wjmc", "jlsj", "jlsjmc", "bz"];
		var pk = "xn||xq||nd||wjmc||jlsj";
		var pkValue = xn+xq+nd+wjmc+jlsj;
		var query = "and rownum = 1";
		
		if(xn != "" && xq != "" && nd != "" && wjmc != "" && jlsj!=""){
			getTableInfo(tableName,colList,pk,pkValue,query);
		}
	}
	
	//回答问卷
	function hdwj(){
		var oneSs = $("oneSs").value;
		var allSs = $("allSs").value;
		if(oneSs !=""){
			setOneChooseDa(oneSs);
		}
		if(allSs != ""){
			setAllChooseDa(allSs);
		}
		
		if (confirm("确定所回答的答案?")) {
			saveUpdate('/xgxt/nbcsPjpy.do?method=daSave&doType=save','');
		}
	}
	
	//单选题答案
	function setOneChooseDa(oneSs){

		if(oneSs != ""){
			for(var i=0;i<oneSs;i++){
			
				var stnumId = "oneStNum"+ i;
				var num = $(stnumId).value;
				
				for(var j=0;j<num;j++){
								
					var stId = "one_da" + i + j;
					var st = document.getElementsByName(stId);
					
					for(var k=0;k<st.length;k++){
					
						var daId = stId + (k + 1);

						if($(daId)){
							if($(daId).checked == true){
								var tmp1 = document.createElement("input");
								tmp1.type = "hidden";
								tmp1.name = "oneChooseDa";
								tmp1.value = st[k].value;
								document.forms[0].appendChild(tmp1);
							}
						}	
					}
				}
			}
		}
	}
	
	//单选题答案
	function setAllChooseDa(allSs){
	
		if(allSs != ""){
		
			for(var i=0;i<allSs;i++){
			
				var stnumId = "allStNum"+ i;
				var num = $(stnumId).value;

				for(var j=0;j<num;j++){
					var stId = "all_da"+i+j;
					var st = document.getElementsByName(stId);
					var flag = false;
					var da = "";
					for(var k=0;k<st.length;k++){
						var daId = stId + (k + 1);
						if($(daId)){
							if($(daId).checked == true){
								da+=$(daId).value+"!!@@!!";
								flag = true;
							}
						}	
					}
					
					if(flag){
						var tmp1 = document.createElement("input");
							tmp1.type = "hidden";
							tmp1.name = "allChooseDa";
							tmp1.value = da;
							document.forms[0].appendChild(tmp1);
					}
				}
			}
		}
	}
	
	function setWjList(){
	
		var id = ""
		
		if($("id")){
			id = $("id").value;
		}

		getWjList();

		if($("id")){
			$("id").value = id;
		}	
	}
	
//验证开始结束时间
function checkSearchTj(kssjId,jssjId){

	var kssj = $(kssjId).value;
	var jssj = $(jssjId).value;
	
	if(kssj != "" && jssj != "" && kssj > jssj){
		alert("开始时间不能大于结束时间，请确认");
		return false;
	}
	return true;
	
}