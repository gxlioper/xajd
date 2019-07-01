//����Ԫ��ID��ȡԪ�ص�ֵ
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

//���������б�ID��ȡ�����б�ǰѡ��ѡ����ı�ֵ
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

//����Ԫ��ID����Ԫ�ص�ֵ
function setVal(id,val){
	if(val === undefined){
		val = "";
	}
	
	if(id === undefined || document.getElementById(id) === undefined || typeof id != "string"){
		return false;
	}
	
	return document.getElementById(id).value = val;
}

//�������ֶ��Ƿ�Ϊ��
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

//����Ԫ��id��ȡԪ��
function ele(id){
	if(id==undefined){
		return null;
	}else{
		return document.getElementById(id);
	}
}

/**
 * ȥ���ַ����ո�
 */
function trim(str) { 
	return str.replace(/(^\s*)|(\s*$)/g, "");   
}

/**
 * ��������е�Ԫ���Ƿ����ظ�
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

//number: һ����ʾ���ַ�����
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

//charactor:һ�����б�ǣ��������÷��žͻ��У�
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

//��һ�������б��е����ݸ��Ƶ���һ�������б�
function copySelect(hid,nid){
	var len = document.getElementById(hid).options.length;			
	for(var i=0; i<len; i++){
			document.getElementById(nid).options[i] = new Option(document.getElementById(hid).options[i].text,document.getElementById(hid).options[i].value)
	}
}

/**
  *���ӱ����ʶ�������Ŀؼ��ĸ��ڵ������td,�ҿؼ������������ڸÿؼ����ڵ��ǰһ��td�С�eg:<td>�ؼ�������</td><td><input type="text" id="input1">�ؼ�</td>
  *arry Ҫ���ӱ����ʶ�Ŀؼ�id,������������ַ���
  *splitStr ��array���ַ���ʱ���ָ��ַ����ķָ��
  */
function appendNotNullFlag(array,splitStr){
			var parentType = "";
			if(!isArray(array)){//������ת��������
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
 *�ж��Ƿ��б����ʶ
 *arry Ҫ�жϱ����ʶ�Ŀؼ�id,������������ַ���
 *splitStr ��array���ַ���ʱ���ָ��ַ����ķָ��
 */
function elementNotNull(array,splitStr){
			var parentType = "";
			var result = true;
			if(!isArray(array)){//������ת��������
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

/**ɾ�����ӵı�������Ŀؼ��ĸ��ڵ������td,�ҿؼ������������ڸÿؼ����ڵ��ǰһ��td�С�eg:<td>�ؼ�������</td><td><input type="text" id="input1">�ؼ�</td>
 *arry Ҫɾ�������ʶ�Ŀؼ�id,������������ַ���
 *splitStr ��array���ַ���ʱ���ָ��ַ����ķָ��
 */
function cancleNotNullFlag(array,splitStr){
	var parentType = "";
		if(!isArray(array)){//������ת��������
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
 *�ж϶����Ƿ�������
 *obj Ҫ�жϵĶ���
 */
function isArray(obj){
	return Object.prototype.toString.call(obj) == "[object Array]"
}

/**
 * ����Ԫ�صĸ�Ԫ��,���ڵ�Ľڵ����ƣ������д�� 
 * @param elem ��������
 * @param parentNodeName ���ڵ�Ľڵ�����
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
 * ���ָ�����ָ���ֶ�,���һ��ҳ���ϵ�����Ҫ��ѯ�������ϵ����ݿ��,ͳһ���ø÷���
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

//���ò�ѯ����
function czSearchCond(zd){
	showConfirmDivLayer("�Ƿ�����ȫ����ѯ����?",{"okFun":function(){
		var id = zd.split("-");
		for (i = 0; i < id.length; i++) {
			if($(id[i])){
				if($(id[i]).value != "" && $(id[i]).disabled == false){
					$(id[i]).value = "";
				}
			}
			
			var jQuery_id = '#'+id[i];
			if(jQuery(jQuery_id) && $(id[i]).title=="��¼��"){
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

//��������
//������value
//����˵������-��-�� ��ʽ�� ��-��-�� ʱ�䣺�֣���
//���ߣ�ת���Ի�����
//���ڣ�2010-10-13
 function Date_Ex(value){
 	var strDate = value;
 	if (strDate.length == 0)
 		 return false;
	//���ж��Ƿ�Ϊ�����ڸ�ʽ��YYYY-MM-DD������ǣ�����������00:00:00��ת��ΪYYYY-MM-DD hh:mm:ss��ʽ
	var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})/;   //�����ڸ�ʽ��������ʽ
	var r = strDate.match(reg);

	if (r != null)   //˵��strDate�Ƕ����ڸ�ʽ������ɳ����ڸ�ʽ
	  strDate = strDate + " 00:00:00";
 
 	reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})/;
 	r = strDate.match(reg);
 	if (r == null){
  	 alert("����������ڸ�ʽ������ȷ��ʽΪ��2004-12-01 �� 2004-12-01 12:23:45");
	 return false;
	}

   var d = new Date(r[1], r[3]-1,r[4],r[5],r[6],r[7]);
   if (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]&&d.getHours()==r[5]&&d.getMinutes()==r[6]&&d.getSeconds()== r[7])
   {
	  return d;
	}else{
    alert("����������ڻ�ʱ�䳬����Ч��Χ������ϸ��飡");
    return false;
   }
}
	
	
//����ָ������(��ʽ��yyyy-mm-dd)����������
//����˵��
//date:ָ������
//days:�������������λ򸺲Σ����η���ָ��������ǰdays�죬���η���ָ���������days�죩
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
	 
	
/**ҳ��һ��ֻ����ʾ�̶����ȵ��ַ� 
 * @param eleArr Ҫ������ҳ���ǩid����
 * @param size �̶�����ֵ
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
 * ѡ�񵼳��ֶ�
 * @return
 */
 function choiceFields(){
 	var tableName = $('tableName').value;
 	



 	//showDialog('',1000,600,'configureExportData.do?method=choiceExportFields&tableName='+tableName)
 	showOpenWindow('configureExportData.do?method=choiceExportFields&tableName='+tableName,1000,600);
 }

 /**
  * ���ݵ����ֶε�������
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
		if(confirm("��δ�Ե����ֶν������ã����ᵼ���յ�Excel�����赼����")){
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
  * ���ݵ����ֶε�������
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
  * ����Ƿ����
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
 * ��ȡָ��class�Ķ�������
 * @param searchClass ��ѯ��class   
 * @param node ����
 * @param tag  ��Ҫ�ı�ǩ
 * @return class����
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
//�÷���ֻ����ie��ʹ�ã����ڵ���ҳ���ϱ�������
function toExcel(tableid){//������񿽱���EXCEL��
	var curTbl = document.getElementById(tableid);
	var oXL = new ActiveXObject("Excel.Application");
	//����AX����excel
	var oWB = oXL.Workbooks.Add();
	//��ȡworkbook����
	var oSheet = oWB.ActiveSheet;
	//���ǰsheet
	var sel = document.body.createTextRange();
	sel.moveToElementText(curTbl);
	//�ѱ���е������Ƶ�TextRange��
	sel.select();
	//ȫѡTextRange������
	sel.execCommand("Copy");
	//����TextRange������
	oSheet.Paste();
	//ճ�������EXCEL��
	oXL.Visible = true;
	//����excel�ɼ�����
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
		alertInfo('��ѡ��Ҫ�����������У�');
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