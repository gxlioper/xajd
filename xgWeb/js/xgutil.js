//根据元素ID获取元素的值
function val(id){
	if(id === undefined || document.getElementById(id) === undefined || document.getElementById(id)===null || typeof id != "string"){
		return "";
	}
	var value = document.getElementById(id).value;
	value = value === null || value === "" || value === "null" ? "" : trim(value);
	
	return value;
}

function radioValue(name){
	var arr = document.getElementsByName(name);
	for(var i=0; i<arr.length; i++){
		if(arr[i].checked){
			return arr[i].value;
			break;
		}
	}
	return "";
}

//根据下拉列表ID获取下拉列表当前选中选项的文本值
function selText(id){
	if(id === undefined || document.getElementById(id) === undefined || typeof id != "string"){
		return "";
	}
	var text = "";
	if(document.getElementById(id).selectedIndex>=0){
		text = document.getElementById(id).options[document.getElementById(id).selectedIndex].text;
	}
	text = text === null || text === "" || text === "null" ? "" : text;
	
	return text;
}

//根据元素ID设置元素的值
function setVal(id,val){
	if(val === undefined){
		val = "";
	}
	
	if(id === undefined || document.getElementById(id) === undefined || typeof id != "string"){
		return false;
	}
	
	return document.getElementById(id).value = val;
}

//检测必填字段是否为空
function filedNotNull(paramter,splitStr){
	var result = true;
	var array = []
	if(typeof paramter == "string"){
		array = paramter.split(splitStr);
	}else{
		array = paramter;
	}
	for(var i=0; i<array.length;i++){
		if (ele(array[i]) && val(array[i]) === "") {
			return false;
		}
	}
	return result;
}

//根据元素id获取元素
function ele(id){
	if(id==undefined){
		return null;
	}else{
		return document.getElementById(id);
	}
}

/**
 * 去除字符串空格
 */
function trim(str) { 
	return str.replace(/(^\s*)|(\s*$)/g, "");   
}

/**
 * 检测数组中的元素是否有重复
 */
function checkArrayEleRepeat(array,splitStr){
	if(typeof array == "string"){
		array = array.split(splitStr);
	}
	for(var i=0; i<array.length; i++){
		for(var j=i+1; j<array.length-i+2; j++){
			if(array[i] == array[j]){
				return true;
			}
		}
	}
	return false;
}

//number: 一行显示的字符个数
function formatContentWidth(content,number){
	var len = content.length;
	var contentArray = [];
	var count = 0;
	for(i=1;i<=len;i++){
		contentArray.push(content.substring(i-1,i));
		count++;
		if(count == parseInt(number)){
			contentArray.push('<br/>');
			count = 0;
		}
	}
	return contentArray.join('');
}

//charactor:一个换行标记（即遇到该符号就换行）
function formatContentBySign(content,charactor){
		var len = content.length,contentArray = [],c;
		for(i=1;i<=len;i++){
		    c = content.substring(i-1,i);
			contentArray.push(c);
			if(c == charactor){
				contentArray.push('<br/>');
			}
		}
		return contentArray.join('');
}

//将一个下拉列表中的内容复制到另一个下拉列表
function copySelect(hid,nid){
	var len = document.getElementById(hid).options.length;			
	for(var i=0; i<len; i++){
			document.getElementById(nid).options[i] = new Option(document.getElementById(hid).options[i].text,document.getElementById(hid).options[i].value)
	}
}

/**
  *增加必填标识，操作的控件的父节点必须是td,且控件的描述必须在该控件父节点的前一个td中。eg:<td>控件描述：</td><td><input type="text" id="input1">控件</td>
  *arry 要增加必填标识的控件id,可以是数组或字符串
  *splitStr 当array是字符串时，分割字符串的分割符
  */
function appendNotNullFlag(array,splitStr){
			var parentType = "";
			if(!isArray(array)){//非数组转换成数组
				array = array.split(splitStr);
			}
			
			for(var i=0; i<array.length; i++){
				var operObj = document.getElementById(array[i]);
				if(operObj){
					parentType = operObj.parentNode.tagName;			
					if(parentType != "TD" && parentType != "TH"){
						return false;
					}else{
						operObj = operObj.parentNode;
						if(operObj.previousSibling){	
							var txt = operObj.previousSibling.innerHTML;
							operObj.previousSibling.innerHTML = "<font color=red>*</font>" + txt;
						}	
					}
				}
			}
}

/**
 *判断是否有必填标识
 *arry 要判断必填标识的控件id,可以是数组或字符串
 *splitStr 当array是字符串时，分割字符串的分割符
 */
function elementNotNull(array,splitStr){
			var parentType = "";
			var result = true;
			if(!isArray(array)){//非数组转换成数组
				array = array.split(splitStr);
			}
			for(var i=0; i<array.length; i++){
				var operObj = document.getElementById(array[i]);
				parentType = operObj.parentNode.tagName;			
				if(parentType != "TD" && parentType != "TH"){	
					alert("failed");
					return false;
				}else{
					operObj = operObj.parentNode;
					if(operObj.previousSibling){	
						var txt = operObj.previousSibling.innerHTML;
						if(txt.substring(0,24) != "<FONT color=red>*</FONT>"){
							result = false;
							break;
						}
					}	
				}
			}
			return result;
}

/**删除增加的必填，操作的控件的父节点必须是td,且控件的描述必须在该控件父节点的前一个td中。eg:<td>控件描述：</td><td><input type="text" id="input1">控件</td>
 *arry 要删除必填标识的控件id,可以是数组或字符串
 *splitStr 当array是字符串时，分割字符串的分割符
 */
function cancleNotNullFlag(array,splitStr){
	var parentType = "";
		if(!isArray(array)){//非数组转换成数组
				array = array.split(splitStr);
			}
			
			for(var i=0; i<array.length; i++){
				var operObj = document.getElementById(array[i]);
					if(operObj){
					parentType = operObj.parentNode.tagName;			
					if(parentType != "TD"){
						return false;
					}else{
						operObj = operObj.parentNode;
						if(operObj.previousSibling){					
							var txt = operObj.previousSibling.innerHTML;
							operObj.previousSibling.innerHTML = txt.substring(24);
						}	
					}
				}
			}
	}
		
/**
 *判断对象是否是数组
 *obj 要判断的对象
 */
function isArray(obj){
	return Object.prototype.toString.call(obj) == "[object Array]"
}

/**
 * 查找元素的父元素,父节点的节点名称（必须大写） 
 * @param elem 基础对象
 * @param parentNodeName 父节点的节点名称
 */
function findParents( elem, parentNodeName ){
        var matched = [], cur = elem["parentNode"];
        while ( cur && cur != document ) {          
          if ( cur.nodeName == parentNodeName )
            matched.push( cur );
          cur = cur["parentNode"];
        }
        return matched[0];
};

/**
 * 获得指定表的指定字段,如果一张页面上的数据要查询两次以上的数据库的,统一调用该方法
 */
function getTableInfo(tableName,colList,pk,pkValue,query){

	dwr.engine.setAsync(false);
			
	getOtherData.getTableInfo(tableName, colList, pk, pkValue,query,function(data){
		if( data != null && data.length > 0){
			for (i = 0; i < colList.length; i++) {
				var id = colList[i];
				if($(id)){
					if(data[i] != "" && data[i] != null){
						$(id).value = data[i];
					}else{
						$(id).value = "";
					}
				}
			}
		}
	});
	dwr.engine.setAsync(true);
}

//重置查询条件
function czSearchCond(zd){
	showConfirmDivLayer("是否重置全部查询条件?",{"okFun":function(){
		var id = zd.split("-");
		for (i = 0; i < id.length; i++) {
			if($(id[i])){
				if($(id[i]).value != "" && $(id[i]).disabled == false){
					$(id[i]).value = "";
				}
			}
			
			var jQuery_id = '#'+id[i];
			if(jQuery(jQuery_id) && $(id[i]).title=="可录入"){
				jQuery(jQuery_id).combobox('setValue',"");
			}
		}
		
		if($("searchTjDiv")){
			$("searchTjDiv").innerHTML = "";
		}	
	}});
	
	if($("search_nj")){
		$("search_nj").value = "";
	}
	
	if($("search_xy")){
		$("search_xy").value = "";
	}
	
	if($("search_zy")){
		$("search_zy").value = "";
	}
	
	if($("search_bj")){
		$("search_bj").value = "";
	}
}

//构造日期
//参数：value
//参数说明：年-月-日 格式或 年-月-日 时间：分：秒
//作者：转载自互联网
//日期：2010-10-13
 function Date_Ex(value){
 	var strDate = value;
 	if (strDate.length == 0)
 		 return false;
	//先判断是否为短日期格式：YYYY-MM-DD，如果是，将其后面加上00:00:00，转换为YYYY-MM-DD hh:mm:ss格式
	var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})/;   //短日期格式的正则表达式
	var r = strDate.match(reg);

	if (r != null)   //说明strDate是短日期格式，改造成长日期格式
	  strDate = strDate + " 00:00:00";
 
 	reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})/;
 	r = strDate.match(reg);
 	if (r == null){
  	 alert("你输入的日期格式有误，正确格式为：2004-12-01 或 2004-12-01 12:23:45");
	 return false;
	}

   var d = new Date(r[1], r[3]-1,r[4],r[5],r[6],r[7]);
   if (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]&&d.getHours()==r[5]&&d.getMinutes()==r[6]&&d.getSeconds()== r[7])
   {
	  return d;
	}else{
    alert("你输入的日期或时间超出有效范围，请仔细检查！");
    return false;
   }
}
	
	
//返回指定日期(格式：yyyy-mm-dd)的相差的日期
//参数说明
//date:指定日期
//days:相差的天数（正参或负参，负参返回指定日期向前days天，正参返回指定日期向后days天）
//2010-10-13
 function getDate(date,days){
   var now=Date_Ex(date);   
   now=new Date(now.getTime()+86400000*days);
    
   var yyyy=now.getFullYear(),
   	   mm=(now.getMonth()+1).toString(),
   	   dd=now.getDate().toString();   
   if(mm.length==1){mm='0'+mm;} if(dd.length==1){dd='0'+dd;}
   return (yyyy+'-'+mm+'-'+dd);        
  }
	 
	
/**页面一行只能显示固定长度的字符 
 * @param eleArr 要操作的页面标签id数组
 * @param size 固定长度值
 * @return
 */
 function stringFormat(eleArr,size){
		for(var i=0; i<eleArr.length; i++){
			if(ele(eleArr[i])){
				ele(eleArr[i]).innerHTML = formatContentWidth(ele(eleArr[i]).innerText,size);
			}
		}
	}
/**
 * 选择导出字段
 * @return
 */
 function choiceFields(){
 	var tableName = $('tableName').value;
 	



 	//showDialog('',1000,600,'configureExportData.do?method=choiceExportFields&tableName='+tableName)
 	showOpenWindow('configureExportData.do?method=choiceExportFields&tableName='+tableName,1000,600);
 }

 /**
  * 根据导出字段导出数据
  * @return
  */
 function configureExportData(){
	var tabName = $('tableName').value;
	var isConfig;
	
	dwr.engine.setAsync(false);
	exportData.isConfigure(tabName,function(data){
		isConfig = data;
	});
	dwr.engine.setAsync(true);
	
	if(!isConfig){
		if(confirm("还未对导出字段进行设置，将会导出空的Excel，仍需导出吗？")){
		 	var url = "configureExportData.do?method=exportData";
		 	document.forms[0].action = url;
		 	document.forms[0].target = "_blank";
		 	document.forms[0].submit();
		 	document.forms[0].target = "_self";
		}
	}else{
		var url = "configureExportData.do?method=exportData";
	 	document.forms[0].action = url;
	 	document.forms[0].target = "_blank";
	 	document.forms[0].submit();
	 	document.forms[0].target = "_self";
	}
 }
 
 /**
  * 根据导出字段导出数据
  * @return
  */
 function configureExportDataZdy(id){
	 
	var url = "configureExportData.do?method=exportDataZdy";
	url += "&exportId=" + id;
 	document.forms[0].action = url;
 	document.forms[0].target = "_blank";
 	document.forms[0].submit();
 	document.forms[0].target = "_self";
	
 }
 
 function hiddenTr(obj){
	var hidTr = $('searchTab').getElementsByTagName("tr");
	
	for (var i = 3 ; i < hidTr.length ; i++){
		if (obj.checked){
			hidTr[i].style.display="";
		} else {
			hidTr[i].style.display="none";
		}
	}
}
 
 /**
  * 检查是否存在
  * @param tableName
  * @param pk
  * @param obj
  * @param buttonId
  * @param spanId
  * @return
  * @author sjf
  */
function checkExists(tableName, pk, obj, buttonId, spanId){
	var pkV = obj.value;
	dwr.engine.setAsync(false);
	
	systemFunction.checkExists(tableName,pk,pkV,function(data){
		if(data){
			if($(spanId)){
				$(spanId).style.display = "";
			}
			if($(buttonId)){
				$(buttonId).disabled = "disabled";
			}
		}else{
			if($(buttonId)){
				$(buttonId).disabled = "";
			}
			
			if($(spanId)){
				$(spanId).style.display = "none";
			}
		}
	});
	dwr.engine.setAsync(true);
}

/**
 * 获取指定class的对象数组
 * @param searchClass 查询用class   
 * @param node 对象
 * @param tag  想要的标签
 * @return class数组
 * @author sjf
 */
function getElementsByClass(searchClass,node,tag) {
    var classElements = new Array();
    if ( node == null )
            node = document;
    if ( tag == null )
            tag = '*';
    var els = node.getElementsByTagName(tag);
    var elsLen = els.length;
    var pattern = new RegExp("(^|\\s)"+searchClass+"(\\s|$)");
    for (i = 0, j = 0; i < elsLen; i++) {
            if ( pattern.test(els[i].className) ) {
                    classElements[j] = els[i];
                    j++;
            }
    }
    return classElements;
}
//该方法只能在ie下使用，由于导出页面上表格的数据
function toExcel(tableid){//整个表格拷贝到EXCEL中
	var curTbl = document.getElementById(tableid);
	var oXL = new ActiveXObject("Excel.Application");
	//创建AX对象excel
	var oWB = oXL.Workbooks.Add();
	//获取workbook对象
	var oSheet = oWB.ActiveSheet;
	//激活当前sheet
	var sel = document.body.createTextRange();
	sel.moveToElementText(curTbl);
	//把表格中的内容移到TextRange中
	sel.select();
	//全选TextRange中内容
	sel.execCommand("Copy");
	//复制TextRange中内容
	oSheet.Paste();
	//粘贴到活动的EXCEL中
	oXL.Visible = true;
	//设置excel可见属性
}

function winClose(){
	window.close();
	if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
		window.dialogArguments.document.getElementById('search_go').click();
	}
}

function modi(url,h,w){
	if(curr_row != null){
		showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
		return true;
	}else{
		alertInfo('请选择要操作的数据行！');
		return false;
	}
}

function disXy(){
	if($("userType") && $("userType").value == "xy"){
		$("xy").disabled = true;
		var xydm = $("xydm").value;
		
//		jQuery("#xy>option[value='" + xydm + "']").attr("selected", "true");
	}
}