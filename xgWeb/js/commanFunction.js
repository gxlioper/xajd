/**���幫������*/
var sort_col = null;//�����У�����
var curr_row = null;//ѡ���У�����
var cur_bgc = "#ffdead";//ѡ���б������ַ�����
var cookieValue = "skin1";//Ƥ��Ŀ¼���ַ�����
var maxRowToSort = 200;//��������У����֣�
var lastScrollY = 0;
var flyLayerHeight = 36;
var $;
var P$;
/**JavaScript�汾���*/
//var ver = Number(ScriptEngineMajorVersion() + "." + ScriptEngineMinorVersion());
var ver = 10;
//if(ver < 5.5){
//	location.href = "errMsg.do?errMsg=asdfasdf";
//}
/**���幫�÷���:���Ҷ���(ID)*/
if (!$ && document.getElementById) {
  $ = function() {
    var elements = new Array();
    for (var i = 0; i < arguments.length; i++) {
      var element = arguments[i];
      if (typeof element == 'string') {
        element = document.getElementById(element);
      }
      if (arguments.length == 1) {
        return element;
      }
      elements.push(element);
    }
    return elements;
  };
} else if (!$ && document.all) {
  $ = function() {
    var elements = new Array();
    for (var i = 0; i < arguments.length; i++) {
      var element = arguments[i];
      if (typeof element == 'string') {
        element = document.all[element];
      }
      if (arguments.length == 1) {
        return element;
      }
      elements.push(element);
    }
    return elements;
  };
}

if (!P$ && document.getElementById) {
  P$ = function() {
    var elements = new Array();
    for (var i = 0; i < arguments.length; i++) {
      var element = arguments[i];
      if (typeof element == 'string') {
        element = window.dialogArguments.document.getElementById(element);
      }
      if (arguments.length == 1) {
        return element;
      }
      elements.push(element);
    }
    return elements;
  };
} else if (!P$ && document.all) {
  P$ = function() {
    var elements = new Array();
    for (var i = 0; i < arguments.length; i++) {
      var element = arguments[i];
      if (typeof element == 'string') {
        element = window.dialogArguments.document.all[element];
      }
      if (arguments.length == 1) {
        return element;
      }
      elements.push(element);
    }
    return elements;
  };
}

/**����ҳ���ʼ�����÷���*/
function initCSS() {
	document.title = "ѧ������������Ϣϵͳ";
	if (window.dialogArguments){
		document.title = document.title + "------(ESC�رմ���)";
	}
	var search = "style=";
	if (document.cookie.length > 0) {
		offset = document.cookie.indexOf(search);
		if (offset != -1) {
			offset += search.length;
			end = document.cookie.indexOf(";", offset);
			if (end == -1) {
				end = document.cookie.length;
			}
			cookieValue = unescape(document.cookie.substring(offset, end));
		}
	}
	if($("csss")){
		if($("csss").href.indexOf(cookieValue)<0){
			$("csss").href = cookieValue + "/" + $("csss").href;
		}
	}
	
}

/**ҳ���ʼ��*/
initCSS();

/**����ѡ�񡢸����Լ��Ҽ�
document.onselectstart = function(){
	return false;
};
document.ondragstart = function(){
	return false;
};
document.onbeforecopy = function(){
	return false;
}; 
document.oncopy = function(){
	document.selection.empty();
};
document.onselect = function(){
	document.selection.empty();
};
document.oncontextmenu = function(){
	return false;
};
*/

document.onkeypress = function () {
	if (event.keyCode == 27) {
		if (window.dialogArguments) {
			window.close();
		}else if(window.parent.changeWin()) {
			window.parent.changeWin();
		}
	}
};

/**���幫���ࣨ������:ǿ�Ƹı䴰��*/
function ForceWindow(sTarget) {
	if (sTarget == null) {
		sTarget = "mainFrame";
	}
	this.r = document.documentElement;
	this.f = document.createElement("FORM");
	this.f.target = sTarget;
	this.f.method = "post";
	this.r.insertBefore(this.f, this.r.childNodes[0]);
}
ForceWindow.prototype.open = function (sUrl) {
	this.f.action = sUrl;
	this.f.submit();
};
function chgRight(sUrl, sTarget) {
	if (sUrl == null) {
		sUrl = "about:blank";
	}
	if (sTarget == null) {
		sTarget = "mainFrame";
	}
	var myWindow = new ForceWindow(sTarget);
	myWindow.open(sUrl);
}
/**���幫�÷���:�رմ���*/
function Close() {
	
	if (frameElement){
		frameElement.api.close();
	} else {
		window.close();
	}
	
}
/**���幫�÷���:�ж϶����Ƿ���������*/
function inArray(str, array) {
	for (tmp = 0; tmp < array.length; tmp++) {
		if (str == array[tmp]) {
			return true;
		}
	}
	return false;
}
/**���幫�÷���:����*/
function judge_CN(char1, char2, mode) {
	var charPYStr = "�������������������������������������������������������������������°ðİŰưǰȰɰʰ˰̰ͰΰϰаѰҰӰ԰հְװذٰڰ۰ܰݰް߰������������������������������������������������������������������������������������������������������������±ñıűƱǱȱɱʱ˱̱ͱαϱбѱұӱԱձֱױرٱڱ۱ܱݱޱ߱������������������������������������������������������������������������������������������������������������²òĲŲƲǲȲɲʲ˲̲ͲβϲвѲҲӲԲղֲײزٲڲ۲ܲݲ޲߲������������������������������������������������������������������������������������������������������������³óĳųƳǳȳɳʳ˳̳ͳγϳгѳҳӳԳճֳ׳سٳڳ۳ܳݳ޳߳������������������������������������������������������������������������������������������������������������´ôĴŴƴǴȴɴʴ˴̴ʹδϴдѴҴӴԴմִ״شٴڴ۴ܴݴ޴ߴ������������������������������������������������������������������������������������������������������������µõĵŵƵǵȵɵʵ˵̵͵εϵеѵҵӵԵյֵ׵صٵڵ۵ܵݵ޵ߵ������������������������������������������������������������������������������������������������������������¶öĶŶƶǶȶɶʶ˶̶Ͷζ϶жѶҶӶԶնֶ׶ضٶڶ۶ܶݶ޶߶������������������������������������������������������������������������������������������������������������·÷ķŷƷǷȷɷʷ˷̷ͷηϷзѷҷӷԷշַ׷طٷڷ۷ܷݷ޷߷������������������������������������������������������������������������������������������������������������¸øĸŸƸǸȸɸʸ˸̸͸θϸиѸҸӸԸոָ׸ظٸڸ۸ܸݸ޸߸������������������������������������������������������������������������������������������������������������¹ùĹŹƹǹȹɹʹ˹̹͹ιϹйѹҹӹԹչֹ׹عٹڹ۹ܹݹ޹߹������������������������������������������������������������������������������������������������������������ºúĺźƺǺȺɺʺ˺̺ͺκϺкѺҺӺԺպֺ׺غٺںۺܺݺ޺ߺ������������������������������������������������������������������������������������������������������������»ûĻŻƻǻȻɻʻ˻̻ͻλϻлѻһӻԻջֻ׻ػٻڻۻܻݻ޻߻������������������������������������������������������������������������������������������������������������¼üļżƼǼȼɼʼ˼̼ͼμϼмѼҼӼԼռּ׼ؼټڼۼܼݼ޼߼������������������������������������������������������������������������������������������������������������½ýĽŽƽǽȽɽʽ˽̽ͽνϽнѽҽӽԽսֽ׽ؽٽھ����������������������������������������������������������¾þľžƾǾȾɾʾ˾̾;ξϾоѾҾӾԾվ־׾ؾپھ۾ܾݾ޾߾����������������������۽ܽݽ޽߽����������������������������������������������������������������������������������������������������������������������������������������¿ÿĿſƿǿȿɿʿ˿̿ͿοϿпѿҿӿԿտֿ׿ؿٿڿۿܿݿ޿߿���������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������¡¢£¤¥¦§¨©ª«¬­®¯°±²³´µ¶·¸¹º»¼½¾¿������������������������������������������������������������������������������������������������������������������������������áâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿ������������������������������������������������������������������������������������������������������������������������������ġĢģĤĥĦħĨĩĪīĬĭĮįİıĲĳĴĵĶķĸĹĺĻļĽľĿ������������������������������������������������������������������������������������������������������������������������������šŢţŤťŦŧŨũŪūŬŭŮůŰűŲųŴŵŶŷŸŹźŻżŽžſ������������������������������������������������������������������������������������������������������������������������������ơƢƣƤƥƦƧƨƩƪƫƬƭƮƯưƱƲƳƴƵƶƷƸƹƺƻƼƽƾƿ������������������������������������������������������������������������������������������������������������������������������ǢǣǤǥǦǧǨǩǪǫǬǭǮǯǰǱǲǳǴǵǶǷǸǹǺǻǼǽǾǿ������������������������������������������������������������������������������������������������������������������������������ȡȢȣȤȥȦȧȨȩȪȫȬȭȮȯȰȱȲȳȴȵȶȷȸȹȺȻȼȽȾȿ������������������������������������������������������������������������������������������������������������������������������ɡɢɣɤɥɦɧɨɩɪɫɬɭɮɯɰɱɲɳɴɵɶɷɸɹɺɻɼɽɾɿ������������������������������������������������������������������������������������������������������������������������������ʡʢʣʤʥʦʧʨʩʪʫʬʭʮʯʰʱʲʳʴʵʶʷʸʹʺʻʼʽʾʿ������������������������������������������������������������������������������������������������������������������������������ˡˢˣˤ˥˦˧˨˩˪˫ˬ˭ˮ˯˰˱˲˳˴˵˶˷˸˹˺˻˼˽˾˿������������������������������������������������������������������������������������������������������������������������������̴̵̶̷̸̡̢̧̨̣̤̥̦̩̪̫̬̭̮̯̰̱̲̳̹̺̻̼̽̾̿������������������������������������������������������������������������������������������������������������������������������ͣͤͥͦͧͨͩͪͫͬͭͮͯ͢͡ͰͱͲͳʹ͵Ͷͷ͸͹ͺͻͼͽ;Ϳ������������������������������������������������������������������������������������������������������������������������������Ρ΢ΣΤΥΦΧΨΩΪΫάέήίΰαβγδεζηθικλμνξο������������������������������������������������������������������������������������������������������������������������������ϡϢϣϤϥϦϧϨϩϪϫϬϭϮϯϰϱϲϳϴϵ϶ϷϸϹϺϻϼϽϾϿ������������������������������������������������������������������������������������������������������������������������������СТУФХЦЧШЩЪЫЬЭЮЯабвгдежзийклмноп������������������������������������������������������������������������������������������������������������������������������ѡѢѣѤѥѦѧѨѩѪѫѬѭѮѯѰѱѲѳѴѵѶѷѸѹѺѻѼѽѾѿ������������������������������������������������������������������������������������������������������������������������������ҡҢңҤҥҦҧҨҩҪҫҬҭҮүҰұҲҳҴҵҶҷҸҹҺһҼҽҾҿ������������������������������������������������������������������������������������������������������������������������������ӡӢӣӤӥӦӧӨөӪӫӬӭӮӯӰӱӲӳӴӵӶӷӸӹӺӻӼӽӾӿ������������������������������������������������������������������������������������������������������������������������������ԡԢԣԤԥԦԧԨԩԪԫԬԭԮԯ԰ԱԲԳԴԵԶԷԸԹԺԻԼԽԾԿ������������������������������������������������������������������������������������������������������������������������������աբգդեզէըթժիլխծկհձղճմյնշոչպջռսվտ������������������������������������������������������������������������������������������������������������������������������ְֱֲֳִֵֶַָֹֺֻּֽ֢֣֤֥֦֧֪֭֮֡֨֩֫֬֯־ֿ������������������������������������������������������������������������������������������������������������������������������סעףפץצקרשת׫׬׭׮ׯװױײ׳״׵׶׷׸׹׺׻׼׽׾׿��������������������������������������������������������������������������������������������������������������������";
	var charSet = charPYStr;
	for (var n = 0; n < (char1.length > char2.length ? char1.length : char2.length); n++) {
		if (char1.charAt(n) != char2.charAt(n)) {
			if (mode) {
				return (charSet.indexOf(char1.charAt(n)) > charSet.indexOf(char2.charAt(n)) ? 1 : -1);
			} else {
				return (charSet.indexOf(char1.charAt(n)) < charSet.indexOf(char2.charAt(n)) ? 1 : -1);
			}
			break;
		}
	}
	return (0);
}

/**���幫�÷���:���selectĬ��ֵ*/
function initTjList(data){	
	alert('fff');
	if (data != null && typeof data == 'object') {
		var objId = data[0].dm;
		if($(objId) && $(objId).tagName.toLowerCase() == "select"){
			BatAjax.removeAllOptions(objId);
			BatAjax.addOptions(objId,data,"dm","mc");
			$(objId).options[0].value = "";
			if($(objId +"V") && $(objId +"V").value != "" && $(objId + "V").value != null){
				for(var i = 0;i < $(objId).options.length; i++){
					if($(objId).options[i].value == $(objId +"V").value){
						$(objId).options[i].selected = true;
						return true;
					}
				}
			}
		}
	}else{
		BatAlert.MyAlert("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

/**
 * @describe ���������SELECT�ĳ�ʼ����initTjList(data)�����������ͨ�ã�������ҳ���ж����������
 */
function initTjList(data, objId){
	if (data != null && typeof data == 'object') {
		
		if($(objId) && $(objId).tagName.toLowerCase() == "select"){
			BatAjax.removeAllOptions(objId);
			BatAjax.addOptions(objId,data,"dm","mc");
			$(objId).options[0].value = "";
			
			if($(objId +"V") && $(objId +"V").value != "" && $(objId + "V").value != null){
				for(var i = 0;i < $(objId).options.length; i++){
					if($(objId).options[i].value == $(objId +"V").value){
						$(objId).options[i].selected = true;
						return true;
					}
				}
			}
		}
	}else{
		BatAlert.MyAlert("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}


/**
 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
 * @describe ʵ�ֶ�select���ϵ�����
 */
function upJust(selectId) {
	var sel = $(selectId);
	var index = sel.selectedIndex;
	if (index == 0) {
		return;
	} else {
		var fromOption = sel.options[index];
		var toOption = sel.options[index-1];
		sel.insertBefore(fromOption, toOption);
	}
}

/**
 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
 * @describe ʵ�ֶ�select���µ�����
 */
function downJust(selectId) {
	var sel = $(selectId);
	var index = sel.selectedIndex;
	if (index == sel.options.length-1) {
		return;
	} else {
		var fromOption = sel.options[index];
		var toOption = sel.options[index+1];
		sel.insertBefore(toOption, fromOption);
	}
}

/**
 * @author ChenHuamao  E-MAIL:chhuma@hotmail.com
 * @describe ��fromSelect��ѡ�е�ѡ���Ƶ�toSelect��
 */
function move(fromSelect, toSelect) {
	var selectedList = [];
	for (var i=0; i<fromSelect.options.length; i++) {
		if (fromSelect.options[i].selected) {
			selectedList[selectedList.length] = fromSelect.options[i];
		}
	}
	
	for (var i=0; i<selectedList.length; i++) {
		toSelect.appendChild(selectedList[i]);
	}
}

function tableSort(the_td) {
	arrowUp = document.createElement("SPAN");
	arrowUp.innerHTML = "5";
	arrowUp.style.cssText = "PADDING-RIGHT: 0px; MARGIN-TOP: -3px; PADDING-LEFT: 0px; FONT-SIZE: 10px; MARGIN-BOTTOM: 2px; PADDING-BOTTOM: 2px; OVERFLOW: hidden; WIDTH: 10px; COLOR: blue; PADDING-TOP: 0px; FONT-FAMILY: webdings; HEIGHT: 11px";
	arrowDown = document.createElement("SPAN");
	arrowDown.innerHTML = "6";
	arrowDown.style.cssText = "PADDING-RIGHT: 0px; MARGIN-TOP: -3px; PADDING-LEFT: 0px; FONT-SIZE: 10px; MARGIN-BOTTOM: 2px; PADDING-BOTTOM: 2px; OVERFLOW: hidden; WIDTH: 10px; COLOR: blue; PADDING-TOP: 0px; FONT-FAMILY: webdings; HEIGHT: 11px";
	the_td.mode = !the_td.mode;
	var cur_col = the_td.cellIndex;
	var the_table = getPapaElement(the_td, "table");
	if (the_table.rows.length > maxRowToSort) {
		if (!confirm("��ǰ�����������" + maxRowToSort + "��,���򽫺ķѱȽϳ���ʱ��,ȷ��Ҫ������?")) {
			return false;
		}
	}
	if (sort_col != null) {
		with (the_table.rows[0].cells[sort_col]) {
			removeChild(lastChild);
		}
	}
	with (the_table.rows[0].cells[cur_col]) {
		appendChild(the_td.mode ? arrowUp : arrowDown);
	}
	sort_tab(the_table, cur_col, the_td.mode);
	sort_col = cur_col;
}
function sort_tab(the_tab, col, mode) {
	var tab_arr = new Array();
	var i;
	var start = new Date;
	for (i = 1; i < the_tab.rows.length; i++) {
		tab_arr.push(new Array(the_tab.rows[i].cells[col].innerText.toLowerCase(), the_tab.rows[i]));
	}
	function SortArr(mode) {
		return function (arr1, arr2) {
			var flag;
			var a, b;
			a = arr1[0];
			b = arr2[0];
			if (/^(\+|-)?\d+($|\.\d+$)/.test(a) && /^(\+|-)?\d+($|\.\d+$)/.test(b)) {
				a = eval(a);
				b = eval(b);
				flag = mode ? (a > b ? 1 : (a < b ? -1 : 0)) : (a < b ? 1 : (a > b ? -1 : 0));
			} else {
				a = a.toString();
				b = b.toString();
				if (a.charCodeAt(0) >= 19968 && b.charCodeAt(0) >= 19968) {
					flag = judge_CN(a, b, mode);
				} else {
					flag = mode ? (a > b ? 1 : (a < b ? -1 : 0)) : (a < b ? 1 : (a > b ? -1 : 0));
				}
			}
			return flag;
		};
	}
	tab_arr.sort(SortArr(mode));
	for (i = 0; i < tab_arr.length; i++) {
		the_tab.lastChild.appendChild(tab_arr[i][1]);
	}
	window.status = " (�����ʱ: " + (new Date - start) + "ms)";
}
/**���幫�÷���:��ȡ������*/
function getPapaElement(the_ele, the_tag) {
	the_tag = the_tag.toLowerCase();
	if (the_ele.tagName.toLowerCase() == the_tag) {
		return the_ele;
	}
	while (the_ele = the_ele.offsetParent) {
		if (the_ele.tagName.toLowerCase() == the_tag) {
			return the_ele;
		}
	}
	return (null);
}
/**���幫�÷���:�е��*/
function rowOnClick(objTr) {
	if (curr_row != null && curr_row.tagName.toLowerCase() == "tr") {
		curr_row.style.backgroundColor = obj_bgc;
	}
	curr_row = objTr;
	obj_bgc = curr_row.style.backgroundColor;
	curr_row.style.backgroundColor = cur_bgc;
}
/**���幫�÷���:�滻�ַ���*/
function replaceChar(conversionString, inChar, outChar) {
	var convertedString = conversionString.split(inChar);
	convertedString = convertedString.join(outChar);
	return convertedString;
}
/**���幫�÷���:������ģʽ�Ի���*/
function showTopWin(url, w, h, scro) {
	url = url.replace("/xgxt/","");
	var info = "";
	if(scro == null){
		info = "Status:YES;dialogWidth:" + w + "px;dialogHeight:" + h + "px;help:no;scroll:no";
	}else{
		info = "Status:YES;dialogWidth:" + w + "px;dialogHeight:" + h + "px;help:no;scroll:yes";
	}
	showModalDialog(url, window, info);
}
/**���幫�÷���:���ύ*/
function refreshForm(url) {
	url = url.replace("/xgxt/","");
	document.forms[0].action = url;
	document.forms[0].submit();
}
/**���幫�÷���:����Ӵ���*/
function checkWinType() {
	if (window.dialogArguments == null || window.parent == null) {
		alert("�Ƿ����ʣ����ڼ����رգ�");
		Close();
		return false;
	}
}
/**���幫�÷���:���ָ�ʽ���߽��ж�*/
function numFormatChk(obj,minV,maxV) {	
	if(obj.value == "")obj.value="0";
	var reg1 = /^\d+[.]?\d+$/g;
	if(obj.value.length == 1){
		reg1 = /^\d+$/g;
	}
	var str = obj.value;
	var r1 = str.match(reg1);
	if(r1 == null){
		alert("����������ֲ��Ϸ������������룡");
		obj.select();
		obj.focus();
		return false;
	}
	var tmpV = parseFloat(str);
	if(minV != null && tmpV < minV){
		alert("�������ֵ��С����СΪ��" + minV + "�������������룡");
		obj.select();
		obj.focus();
		return false;
	}
	if(maxV != null && tmpV > maxV){
		alert("�������ֵ�������Ϊ��" + maxV + "�������������룡");
		obj.select();
		obj.focus();
		return false;
	}
	return true;
}
/**���幫�÷���:ѧԺ��Ч����*/
function xyDisabled(ele) {
	if ($("userType").value == "xy") {
		var tmp = ele.split("-");
		for (i = 0; i < tmp.length; i++) {
			//var tmpObj = document.createElement("SPAN");
			//tmpObj.innerHTML = $(tmp[i]).value;
			//$(tmp[i]).offsetParent.appendChild(tmpObj);
			
			$(tmp[i]).disabled = true;
		}
	}
}
/**���幫�÷���:��ʾ��ʾ��Ϣ*/
function setEleDisable(sTagName){
	var subNum = document.getElementsByTagName(sTagName).length;
	for(var i = 0; i < subNum; i++){
		document.getElementsByTagName(sTagName)[i].disabled = true;
	}
}
function showTips(msg) {
	
	var dd_html = "";	
	dd_html += "<table border=0 cellpadding=0 cellspacing=1 bgcolor=\"#000000\"";
	dd_html += "width=\"100%\" height=\"100%\"><tr>";
	dd_html += "<td bgcolor=#3A6EA5>";
	dd_html += "<marquee align=\"middle\" behavior=\"alternate\" scrollamount=\"2\" style=\"font-size:9pt\">";
	dd_html += "<font color=#FFFFFF>";
	dd_html += msg;
	dd_html += "</font>";
	dd_html += "</marquee></td></tr></table>";
	tips = document.createElement("DIV");
	tips.innerHTML = dd_html;
	tips.style.cssText = "width:200px;height:30px;position:absolute;z-index:100;filter:alpha(opacity=70);";
	tips.style.top = document.body.clientHeight/2;
	if($("btn")){//2010.11.4 Edit byu luojw
		tips.style.top = $("btn").style.pixelTop - document.body.clientHeight/2;
	}
	tips.style.left = (document.body.clientWidth - 200) / 2;
	tips.style.display = "block";
	document.body.appendChild(tips);
	
	tipsF = document.createElement("IFRAME");
	tipsF.style.cssText = "width:200px;height:30px;position:absolute;filter:alpha(opacity=50);";
	tipsF.scr = "javascript:false;";
	tipsF.scrolling = "no";
	tipsF.frameborder = "0";
    tipsF.style.width = tips.offsetWidth;
    tipsF.style.height = tips.offsetHeight;
    tipsF.style.top = tips.style.top;
    tipsF.style.left = tips.style.left;
    tipsF.style.zIndex = tips.style.zIndex - 1;
	document.body.appendChild(tipsF);
}
/**���幫�÷���:�жϵ�ǰ�Ƿ�ѡ����*/
function chkCurrRow(){
	if(curr_row == null){
		alert("�˲�����Ҫ��ѡ�е��У���ѡ�񣨵�����Ҫ�������У�");
		return false;
	}
	return true;
}
/**���幫�÷���:�������ʱ��*/
function unionDateTime(ids){
	var d = $(ids + "1").value;
	var h = $(ids + "2").value;
	var m = $(ids + "3").value;
	var s = $(ids + "4").value;
	$(ids).value = replaceChar(d,"-","") + h + m + s;
}
