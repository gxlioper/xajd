/**¶¨Òå¹«¹²±äÁ¿*/
var sort_col = null;//ÅÅÐòÁÐ£¨¶ÔÏó£©
var curr_row = null;//Ñ¡ÖÐÐÐ£¨¶ÔÏó£©
var cur_bgc = "#ffdead";//Ñ¡ÖÐÐÐ±³¾°£¨×Ö·û´®£©
var cookieValue = "skin1";//Æ¤·ôÄ¿Â¼£¨×Ö·û´®£©
var maxRowToSort = 200;//×î´óÅÅÐòÐÐ£¨Êý×Ö£©
var lastScrollY = 0;
var flyLayerHeight = 36;
var $;
var P$;
/**JavaScript°æ±¾¼ì²â*/
//var ver = Number(ScriptEngineMajorVersion() + "." + ScriptEngineMinorVersion());
var ver = 10;
//if(ver < 5.5){
//	location.href = "errMsg.do?errMsg=asdfasdf";
//}
/**¶¨Òå¹«ÓÃ·½·¨:²éÕÒ¶ÔÏó(ID)*/
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

/**¶¨ÒåÒ³Ãæ³õÊ¼»¯¹«ÓÃ·½·¨*/
function initCSS() {
	document.title = "Ñ§Éú¹¤×÷¹ÜÀíÐÅÏ¢ÏµÍ³";
	if (window.dialogArguments){
		document.title = document.title + "------(ESC¹Ø±Õ´°¿Ú)";
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

/**Ò³Ãæ³õÊ¼»¯*/
initCSS();

/**½ûÓÃÑ¡Ôñ¡¢¸´ÖÆÒÔ¼°ÓÒ¼ü
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

/**¶¨Òå¹«ÓÃÀà£¨·½·¨£©:Ç¿ÖÆ¸Ä±ä´°¿Ú*/
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
/**¶¨Òå¹«ÓÃ·½·¨:¹Ø±Õ´°¿Ú*/
function Close() {
	
	if (frameElement){
		frameElement.api.close();
	} else {
		window.close();
	}
	
}
/**¶¨Òå¹«ÓÃ·½·¨:ÅÐ¶Ï¶ÔÏóÊÇ·ñÔÚÊý×éÖÐ*/
function inArray(str, array) {
	for (tmp = 0; tmp < array.length; tmp++) {
		if (str == array[tmp]) {
			return true;
		}
	}
	return false;
}
/**¶¨Òå¹«ÓÃ·½·¨:ÅÅÐò*/
function judge_CN(char1, char2, mode) {
	var charPYStr = "°¡°¢°£°¤°¥°¦°§°¨°©°ª°«°¬°­°®°¯°°°±°²°³°´°µ°¶°·°¸°¹°º°»°¼°½°¾°¿°À°Á°Â°Ã°Ä°Å°Æ°Ç°È°É°Ê°Ë°Ì°Í°Î°Ï°Ð°Ñ°Ò°Ó°Ô°Õ°Ö°×°Ø°Ù°Ú°Û°Ü°Ý°Þ°ß°à°á°â°ã°ä°å°æ°ç°è°é°ê°ë°ì°í°î°ï°ð°ñ°ò°ó°ô°õ°ö°÷°ø°ù°ú°û°ü°ý°þ±¡±¢±£±¤±¥±¦±§±¨±©±ª±«±¬±­±®±¯±°±±±²±³±´±µ±¶±·±¸±¹±º±»±¼±½±¾±¿±À±Á±Â±Ã±Ä±Å±Æ±Ç±È±É±Ê±Ë±Ì±Í±Î±Ï±Ð±Ñ±Ò±Ó±Ô±Õ±Ö±×±Ø±Ù±Ú±Û±Ü±Ý±Þ±ß±à±á±â±ã±ä±å±æ±ç±è±é±ê±ë±ì±í±î±ï±ð±ñ±ò±ó±ô±õ±ö±÷±ø±ù±ú±û±ü±ý±þ²¡²¢²£²¤²¥²¦²§²¨²©²ª²«²¬²­²®²¯²°²±²²²³²´²µ²¶²·²¸²¹²º²»²¼²½²¾²¿²À²Á²Â²Ã²Ä²Å²Æ²Ç²È²É²Ê²Ë²Ì²Í²Î²Ï²Ð²Ñ²Ò²Ó²Ô²Õ²Ö²×²Ø²Ù²Ú²Û²Ü²Ý²Þ²ß²à²á²â²ã²ä²å²æ²ç²è²é²ê²ë²ì²í²î²ï²ð²ñ²ò²ó²ô²õ²ö²÷²ø²ù²ú²û²ü²ý²þ³¡³¢³£³¤³¥³¦³§³¨³©³ª³«³¬³­³®³¯³°³±³²³³³´³µ³¶³·³¸³¹³º³»³¼³½³¾³¿³À³Á³Â³Ã³Ä³Å³Æ³Ç³È³É³Ê³Ë³Ì³Í³Î³Ï³Ð³Ñ³Ò³Ó³Ô³Õ³Ö³×³Ø³Ù³Ú³Û³Ü³Ý³Þ³ß³à³á³â³ã³ä³å³æ³ç³è³é³ê³ë³ì³í³î³ï³ð³ñ³ò³ó³ô³õ³ö³÷³ø³ù³ú³û³ü³ý³þ´¡´¢´£´¤´¥´¦´§´¨´©´ª´«´¬´­´®´¯´°´±´²´³´´´µ´¶´·´¸´¹´º´»´¼´½´¾´¿´À´Á´Â´Ã´Ä´Å´Æ´Ç´È´É´Ê´Ë´Ì´Í´Î´Ï´Ð´Ñ´Ò´Ó´Ô´Õ´Ö´×´Ø´Ù´Ú´Û´Ü´Ý´Þ´ß´à´á´â´ã´ä´å´æ´ç´è´é´ê´ë´ì´í´î´ï´ð´ñ´ò´ó´ô´õ´ö´÷´ø´ù´ú´û´ü´ý´þµ¡µ¢µ£µ¤µ¥µ¦µ§µ¨µ©µªµ«µ¬µ­µ®µ¯µ°µ±µ²µ³µ´µµµ¶µ·µ¸µ¹µºµ»µ¼µ½µ¾µ¿µÀµÁµÂµÃµÄµÅµÆµÇµÈµÉµÊµËµÌµÍµÎµÏµÐµÑµÒµÓµÔµÕµÖµ×µØµÙµÚµÛµÜµÝµÞµßµàµáµâµãµäµåµæµçµèµéµêµëµìµíµîµïµðµñµòµóµôµõµöµ÷µøµùµúµûµüµýµþ¶¡¶¢¶£¶¤¶¥¶¦¶§¶¨¶©¶ª¶«¶¬¶­¶®¶¯¶°¶±¶²¶³¶´¶µ¶¶¶·¶¸¶¹¶º¶»¶¼¶½¶¾¶¿¶À¶Á¶Â¶Ã¶Ä¶Å¶Æ¶Ç¶È¶É¶Ê¶Ë¶Ì¶Í¶Î¶Ï¶Ð¶Ñ¶Ò¶Ó¶Ô¶Õ¶Ö¶×¶Ø¶Ù¶Ú¶Û¶Ü¶Ý¶Þ¶ß¶à¶á¶â¶ã¶ä¶å¶æ¶ç¶è¶é¶ê¶ë¶ì¶í¶î¶ï¶ð¶ñ¶ò¶ó¶ô¶õ¶ö¶÷¶ø¶ù¶ú¶û¶ü¶ý¶þ·¡·¢·£·¤·¥·¦·§·¨·©·ª·«·¬·­·®·¯·°·±·²·³·´·µ·¶···¸·¹·º·»·¼·½·¾·¿·À·Á·Â·Ã·Ä·Å·Æ·Ç·È·É·Ê·Ë·Ì·Í·Î·Ï·Ð·Ñ·Ò·Ó·Ô·Õ·Ö·×·Ø·Ù·Ú·Û·Ü·Ý·Þ·ß·à·á·â·ã·ä·å·æ·ç·è·é·ê·ë·ì·í·î·ï·ð·ñ·ò·ó·ô·õ·ö·÷·ø·ù·ú·û·ü·ý·þ¸¡¸¢¸£¸¤¸¥¸¦¸§¸¨¸©¸ª¸«¸¬¸­¸®¸¯¸°¸±¸²¸³¸´¸µ¸¶¸·¸¸¸¹¸º¸»¸¼¸½¸¾¸¿¸À¸Á¸Â¸Ã¸Ä¸Å¸Æ¸Ç¸È¸É¸Ê¸Ë¸Ì¸Í¸Î¸Ï¸Ð¸Ñ¸Ò¸Ó¸Ô¸Õ¸Ö¸×¸Ø¸Ù¸Ú¸Û¸Ü¸Ý¸Þ¸ß¸à¸á¸â¸ã¸ä¸å¸æ¸ç¸è¸é¸ê¸ë¸ì¸í¸î¸ï¸ð¸ñ¸ò¸ó¸ô¸õ¸ö¸÷¸ø¸ù¸ú¸û¸ü¸ý¸þ¹¡¹¢¹£¹¤¹¥¹¦¹§¹¨¹©¹ª¹«¹¬¹­¹®¹¯¹°¹±¹²¹³¹´¹µ¹¶¹·¹¸¹¹¹º¹»¹¼¹½¹¾¹¿¹À¹Á¹Â¹Ã¹Ä¹Å¹Æ¹Ç¹È¹É¹Ê¹Ë¹Ì¹Í¹Î¹Ï¹Ð¹Ñ¹Ò¹Ó¹Ô¹Õ¹Ö¹×¹Ø¹Ù¹Ú¹Û¹Ü¹Ý¹Þ¹ß¹à¹á¹â¹ã¹ä¹å¹æ¹ç¹è¹é¹ê¹ë¹ì¹í¹î¹ï¹ð¹ñ¹ò¹ó¹ô¹õ¹ö¹÷¹ø¹ù¹ú¹û¹ü¹ý¹þº¡º¢º£º¤º¥º¦º§º¨º©ºªº«º¬º­º®º¯º°º±º²º³º´ºµº¶º·º¸º¹ººº»º¼º½º¾º¿ºÀºÁºÂºÃºÄºÅºÆºÇºÈºÉºÊºËºÌºÍºÎºÏºÐºÑºÒºÓºÔºÕºÖº×ºØºÙºÚºÛºÜºÝºÞºßºàºáºâºãºäºåºæºçºèºéºêºëºìºíºîºïºðºñºòºóºôºõºöº÷ºøºùºúºûºüºýºþ»¡»¢»£»¤»¥»¦»§»¨»©»ª»«»¬»­»®»¯»°»±»²»³»´»µ»¶»·»¸»¹»º»»»¼»½»¾»¿»À»Á»Â»Ã»Ä»Å»Æ»Ç»È»É»Ê»Ë»Ì»Í»Î»Ï»Ð»Ñ»Ò»Ó»Ô»Õ»Ö»×»Ø»Ù»Ú»Û»Ü»Ý»Þ»ß»à»á»â»ã»ä»å»æ»ç»è»é»ê»ë»ì»í»î»ï»ð»ñ»ò»ó»ô»õ»ö»÷»ø»ù»ú»û»ü»ý»þ¼¡¼¢¼£¼¤¼¥¼¦¼§¼¨¼©¼ª¼«¼¬¼­¼®¼¯¼°¼±¼²¼³¼´¼µ¼¶¼·¼¸¼¹¼º¼»¼¼¼½¼¾¼¿¼À¼Á¼Â¼Ã¼Ä¼Å¼Æ¼Ç¼È¼É¼Ê¼Ë¼Ì¼Í¼Î¼Ï¼Ð¼Ñ¼Ò¼Ó¼Ô¼Õ¼Ö¼×¼Ø¼Ù¼Ú¼Û¼Ü¼Ý¼Þ¼ß¼à¼á¼â¼ã¼ä¼å¼æ¼ç¼è¼é¼ê¼ë¼ì¼í¼î¼ï¼ð¼ñ¼ò¼ó¼ô¼õ¼ö¼÷¼ø¼ù¼ú¼û¼ü¼ý¼þ½¡½¢½£½¤½¥½¦½§½¨½©½ª½«½¬½­½®½¯½°½±½²½³½´½µ½¶½·½¸½¹½º½»½¼½½½¾½¿½À½Á½Â½Ã½Ä½Å½Æ½Ç½È½É½Ê½Ë½Ì½Í½Î½Ï½Ð½Ñ½Ò½Ó½Ô½Õ½Ö½×½Ø½Ù½Ú¾¥¾¦¾§¾¨¾©¾ª¾«¾¬¾­¾®¾¯¾°¾±¾²¾³¾´¾µ¾¶¾·¾¸¾¹¾º¾»¾¼¾½¾¾¾¿¾À¾Á¾Â¾Ã¾Ä¾Å¾Æ¾Ç¾È¾É¾Ê¾Ë¾Ì¾Í¾Î¾Ï¾Ð¾Ñ¾Ò¾Ó¾Ô¾Õ¾Ö¾×¾Ø¾Ù¾Ú¾Û¾Ü¾Ý¾Þ¾ß¾à¾á¾â¾ã¾ä¾å¾æ¾ç¾è¾é¾ê¾ë¾ì¾í¾î¾ï¾ð¾ñ¾ò¾ó¾ô½Û½Ü½Ý½Þ½ß½à½á½â½ã½ä½å½æ½ç½è½é½ê½ë½ì½í½î½ï½ð½ñ½ò½ó½ô½õ½ö½÷½ø½ù½ú½û½ü½ý½þ¾¡¾¢¾£¾¤¾õ¾ö¾÷¾ø¾ù¾ú¾û¾ü¾ý¾þ¿¡¿¢¿£¿¤¿¥¿¦¿§¿¨¿©¿ª¿«¿¬¿­¿®¿¯¿°¿±¿²¿³¿´¿µ¿¶¿·¿¸¿¹¿º¿»¿¼¿½¿¾¿¿¿À¿Á¿Â¿Ã¿Ä¿Å¿Æ¿Ç¿È¿É¿Ê¿Ë¿Ì¿Í¿Î¿Ï¿Ð¿Ñ¿Ò¿Ó¿Ô¿Õ¿Ö¿×¿Ø¿Ù¿Ú¿Û¿Ü¿Ý¿Þ¿ß¿à¿á¿â¿ã¿ä¿å¿æ¿ç¿è¿é¿ê¿ë¿ì¿í¿î¿ï¿ð¿ñ¿ò¿ó¿ô¿õ¿ö¿÷¿ø¿ù¿ú¿û¿ü¿ý¿þÀ¡À¢À£À¤À¥À¦À§À¨À©ÀªÀ«À¬À­À®À¯À°À±À²À³À´ÀµÀ¶À·À¸À¹ÀºÀ»À¼À½À¾À¿ÀÀÀÁÀÂÀÃÀÄÀÅÀÆÀÇÀÈÀÉÀÊÀËÀÌÀÍÀÎÀÏÀÐÀÑÀÒÀÓÀÔÀÕÀÖÀ×ÀØÀÙÀÚÀÛÀÜÀÝÀÞÀßÀàÀáÀâÀãÀäÀåÀæÀçÀèÀéÀêÀëÀìÀíÀîÀïÀðÀñÀòÀóÀôÀõÀöÀ÷ÀøÀùÀúÀûÀüÀýÀþÁ¡Á¢Á£Á¤Á¥Á¦Á§Á¨Á©ÁªÁ«Á¬Á­Á®Á¯Á°Á±Á²Á³Á´ÁµÁ¶Á·Á¸Á¹ÁºÁ»Á¼Á½Á¾Á¿ÁÀÁÁÁÂÁÃÁÄÁÅÁÆÁÇÁÈÁÉÁÊÁËÁÌÁÍÁÎÁÏÁÐÁÑÁÒÁÓÁÔÁÕÁÖÁ×ÁØÁÙÁÚÁÛÁÜÁÝÁÞÁßÁàÁáÁâÁãÁäÁåÁæÁçÁèÁéÁêÁëÁìÁíÁîÁïÁðÁñÁòÁóÁôÁõÁöÁ÷ÁøÁùÁúÁûÁüÁýÁþÂ¡Â¢Â£Â¤Â¥Â¦Â§Â¨Â©ÂªÂ«Â¬Â­Â®Â¯Â°Â±Â²Â³Â´ÂµÂ¶Â·Â¸Â¹ÂºÂ»Â¼Â½Â¾Â¿ÂÀÂÁÂÂÂÃÂÄÂÅÂÆÂÇÂÈÂÉÂÊÂËÂÌÂÍÂÎÂÏÂÐÂÑÂÒÂÓÂÔÂÕÂÖÂ×ÂØÂÙÂÚÂÛÂÜÂÝÂÞÂßÂàÂáÂâÂãÂäÂåÂæÂçÂèÂéÂêÂëÂìÂíÂîÂïÂðÂñÂòÂóÂôÂõÂöÂ÷ÂøÂùÂúÂûÂüÂýÂþÃ¡Ã¢Ã£Ã¤Ã¥Ã¦Ã§Ã¨Ã©ÃªÃ«Ã¬Ã­Ã®Ã¯Ã°Ã±Ã²Ã³Ã´ÃµÃ¶Ã·Ã¸Ã¹ÃºÃ»Ã¼Ã½Ã¾Ã¿ÃÀÃÁÃÂÃÃÃÄÃÅÃÆÃÇÃÈÃÉÃÊÃËÃÌÃÍÃÎÃÏÃÐÃÑÃÒÃÓÃÔÃÕÃÖÃ×ÃØÃÙÃÚÃÛÃÜÃÝÃÞÃßÃàÃáÃâÃãÃäÃåÃæÃçÃèÃéÃêÃëÃìÃíÃîÃïÃðÃñÃòÃóÃôÃõÃöÃ÷ÃøÃùÃúÃûÃüÃýÃþÄ¡Ä¢Ä£Ä¤Ä¥Ä¦Ä§Ä¨Ä©ÄªÄ«Ä¬Ä­Ä®Ä¯Ä°Ä±Ä²Ä³Ä´ÄµÄ¶Ä·Ä¸Ä¹ÄºÄ»Ä¼Ä½Ä¾Ä¿ÄÀÄÁÄÂÄÃÄÄÄÅÄÆÄÇÄÈÄÉÄÊÄËÄÌÄÍÄÎÄÏÄÐÄÑÄÒÄÓÄÔÄÕÄÖÄ×ÄØÄÙÄÚÄÛÄÜÄÝÄÞÄßÄàÄáÄâÄãÄäÄåÄæÄçÄèÄéÄêÄëÄìÄíÄîÄïÄðÄñÄòÄóÄôÄõÄöÄ÷ÄøÄùÄúÄûÄüÄýÄþÅ¡Å¢Å£Å¤Å¥Å¦Å§Å¨Å©ÅªÅ«Å¬Å­Å®Å¯Å°Å±Å²Å³Å´ÅµÅ¶Å·Å¸Å¹ÅºÅ»Å¼Å½Å¾Å¿ÅÀÅÁÅÂÅÃÅÄÅÅÅÆÅÇÅÈÅÉÅÊÅËÅÌÅÍÅÎÅÏÅÐÅÑÅÒÅÓÅÔÅÕÅÖÅ×ÅØÅÙÅÚÅÛÅÜÅÝÅÞÅßÅàÅáÅâÅãÅäÅåÅæÅçÅèÅéÅêÅëÅìÅíÅîÅïÅðÅñÅòÅóÅôÅõÅöÅ÷ÅøÅùÅúÅûÅüÅýÅþÆ¡Æ¢Æ£Æ¤Æ¥Æ¦Æ§Æ¨Æ©ÆªÆ«Æ¬Æ­Æ®Æ¯Æ°Æ±Æ²Æ³Æ´ÆµÆ¶Æ·Æ¸Æ¹ÆºÆ»Æ¼Æ½Æ¾Æ¿ÆÀÆÁÆÂÆÃÆÄÆÅÆÆÆÇÆÈÆÉÆÊÆËÆÌÆÍÆÎÆÏÆÐÆÑÆÒÆÓÆÔÆÕÆÖÆ×ÆØÆÙÆÚÆÛÆÜÆÝÆÞÆßÆàÆáÆâÆãÆäÆåÆæÆçÆèÆéÆêÆëÆìÆíÆîÆïÆðÆñÆòÆóÆôÆõÆöÆ÷ÆøÆùÆúÆûÆüÆýÆþÇ¢Ç£Ç¤Ç¥Ç¦Ç§Ç¨Ç©ÇªÇ«Ç¬Ç­Ç®Ç¯Ç°Ç±Ç²Ç³Ç´ÇµÇ¶Ç·Ç¸Ç¹ÇºÇ»Ç¼Ç½Ç¾Ç¿ÇÀÇÁÇÂÇÃÇÄÇÅÇÆÇÇÇÈÇÉÇÊÇËÇÌÇÍÇÎÇÏÇÐÇÑÇÒÇÓÇÔÇÕÇÖÇ×ÇØÇÙÇÚÇÛÇÜÇÝÇÞÇßÇàÇáÇâÇãÇäÇåÇæÇçÇèÇéÇêÇëÇìÇíÇîÇïÇðÇñÇòÇóÇôÇõÇöÇ÷ÇøÇùÇúÇûÇüÇýÇþÈ¡È¢È£È¤È¥È¦È§È¨È©ÈªÈ«È¬È­È®È¯È°È±È²È³È´ÈµÈ¶È·È¸È¹ÈºÈ»È¼È½È¾È¿ÈÀÈÁÈÂÈÃÈÄÈÅÈÆÈÇÈÈÈÉÈÊÈËÈÌÈÍÈÎÈÏÈÐÈÑÈÒÈÓÈÔÈÕÈÖÈ×ÈØÈÙÈÚÈÛÈÜÈÝÈÞÈßÈàÈáÈâÈãÈäÈåÈæÈçÈèÈéÈêÈëÈìÈíÈîÈïÈðÈñÈòÈóÈôÈõÈöÈ÷ÈøÈùÈúÈûÈüÈýÈþÉ¡É¢É£É¤É¥É¦É§É¨É©ÉªÉ«É¬É­É®É¯É°É±É²É³É´ÉµÉ¶É·É¸É¹ÉºÉ»É¼É½É¾É¿ÉÀÉÁÉÂÉÃÉÄÉÅÉÆÉÇÉÈÉÉÉÊÉËÉÌÉÍÉÎÉÏÉÐÉÑÉÒÉÓÉÔÉÕÉÖÉ×ÉØÉÙÉÚÉÛÉÜÉÝÉÞÉßÉàÉáÉâÉãÉäÉåÉæÉçÉèÉéÉêÉëÉìÉíÉîÉïÉðÉñÉòÉóÉôÉõÉöÉ÷ÉøÉùÉúÉûÉüÉýÉþÊ¡Ê¢Ê£Ê¤Ê¥Ê¦Ê§Ê¨Ê©ÊªÊ«Ê¬Ê­Ê®Ê¯Ê°Ê±Ê²Ê³Ê´ÊµÊ¶Ê·Ê¸Ê¹ÊºÊ»Ê¼Ê½Ê¾Ê¿ÊÀÊÁÊÂÊÃÊÄÊÅÊÆÊÇÊÈÊÉÊÊÊËÊÌÊÍÊÎÊÏÊÐÊÑÊÒÊÓÊÔÊÕÊÖÊ×ÊØÊÙÊÚÊÛÊÜÊÝÊÞÊßÊàÊáÊâÊãÊäÊåÊæÊçÊèÊéÊêÊëÊìÊíÊîÊïÊðÊñÊòÊóÊôÊõÊöÊ÷ÊøÊùÊúÊûÊüÊýÊþË¡Ë¢Ë£Ë¤Ë¥Ë¦Ë§Ë¨Ë©ËªË«Ë¬Ë­Ë®Ë¯Ë°Ë±Ë²Ë³Ë´ËµË¶Ë·Ë¸Ë¹ËºË»Ë¼Ë½Ë¾Ë¿ËÀËÁËÂËÃËÄËÅËÆËÇËÈËÉËÊËËËÌËÍËÎËÏËÐËÑËÒËÓËÔËÕËÖË×ËØËÙËÚËÛËÜËÝËÞËßËàËáËâËãËäËåËæËçËèËéËêËëËìËíËîËïËðËñËòËóËôËõËöË÷ËøËùËúËûËüËýËþÌ¡Ì¢Ì£Ì¤Ì¥Ì¦Ì§Ì¨Ì©ÌªÌ«Ì¬Ì­Ì®Ì¯Ì°Ì±Ì²Ì³Ì´ÌµÌ¶Ì·Ì¸Ì¹ÌºÌ»Ì¼Ì½Ì¾Ì¿ÌÀÌÁÌÂÌÃÌÄÌÅÌÆÌÇÌÈÌÉÌÊÌËÌÌÌÍÌÎÌÏÌÐÌÑÌÒÌÓÌÔÌÕÌÖÌ×ÌØÌÙÌÚÌÛÌÜÌÝÌÞÌßÌàÌáÌâÌãÌäÌåÌæÌçÌèÌéÌêÌëÌìÌíÌîÌïÌðÌñÌòÌóÌôÌõÌöÌ÷ÌøÌùÌúÌûÌüÌýÌþÍ¡Í¢Í£Í¤Í¥Í¦Í§Í¨Í©ÍªÍ«Í¬Í­Í®Í¯Í°Í±Í²Í³Í´ÍµÍ¶Í·Í¸Í¹ÍºÍ»Í¼Í½Í¾Í¿ÍÀÍÁÍÂÍÃÍÄÍÅÍÆÍÇÍÈÍÉÍÊÍËÍÌÍÍÍÎÍÏÍÐÍÑÍÒÍÓÍÔÍÕÍÖÍ×ÍØÍÙÍÚÍÛÍÜÍÝÍÞÍßÍàÍáÍâÍãÍäÍåÍæÍçÍèÍéÍêÍëÍìÍíÍîÍïÍðÍñÍòÍóÍôÍõÍöÍ÷ÍøÍùÍúÍûÍüÍýÍþÎ¡Î¢Î£Î¤Î¥Î¦Î§Î¨Î©ÎªÎ«Î¬Î­Î®Î¯Î°Î±Î²Î³Î´ÎµÎ¶Î·Î¸Î¹ÎºÎ»Î¼Î½Î¾Î¿ÎÀÎÁÎÂÎÃÎÄÎÅÎÆÎÇÎÈÎÉÎÊÎËÎÌÎÍÎÎÎÏÎÐÎÑÎÒÎÓÎÔÎÕÎÖÎ×ÎØÎÙÎÚÎÛÎÜÎÝÎÞÎßÎàÎáÎâÎãÎäÎåÎæÎçÎèÎéÎêÎëÎìÎíÎîÎïÎðÎñÎòÎóÎôÎõÎöÎ÷ÎøÎùÎúÎûÎüÎýÎþÏ¡Ï¢Ï£Ï¤Ï¥Ï¦Ï§Ï¨Ï©ÏªÏ«Ï¬Ï­Ï®Ï¯Ï°Ï±Ï²Ï³Ï´ÏµÏ¶Ï·Ï¸Ï¹ÏºÏ»Ï¼Ï½Ï¾Ï¿ÏÀÏÁÏÂÏÃÏÄÏÅÏÆÏÇÏÈÏÉÏÊÏËÏÌÏÍÏÎÏÏÏÐÏÑÏÒÏÓÏÔÏÕÏÖÏ×ÏØÏÙÏÚÏÛÏÜÏÝÏÞÏßÏàÏáÏâÏãÏäÏåÏæÏçÏèÏéÏêÏëÏìÏíÏîÏïÏðÏñÏòÏóÏôÏõÏöÏ÷ÏøÏùÏúÏûÏüÏýÏþÐ¡Ð¢Ð£Ð¤Ð¥Ð¦Ð§Ð¨Ð©ÐªÐ«Ð¬Ð­Ð®Ð¯Ð°Ð±Ð²Ð³Ð´ÐµÐ¶Ð·Ð¸Ð¹ÐºÐ»Ð¼Ð½Ð¾Ð¿ÐÀÐÁÐÂÐÃÐÄÐÅÐÆÐÇÐÈÐÉÐÊÐËÐÌÐÍÐÎÐÏÐÐÐÑÐÒÐÓÐÔÐÕÐÖÐ×ÐØÐÙÐÚÐÛÐÜÐÝÐÞÐßÐàÐáÐâÐãÐäÐåÐæÐçÐèÐéÐêÐëÐìÐíÐîÐïÐðÐñÐòÐóÐôÐõÐöÐ÷ÐøÐùÐúÐûÐüÐýÐþÑ¡Ñ¢Ñ£Ñ¤Ñ¥Ñ¦Ñ§Ñ¨Ñ©ÑªÑ«Ñ¬Ñ­Ñ®Ñ¯Ñ°Ñ±Ñ²Ñ³Ñ´ÑµÑ¶Ñ·Ñ¸Ñ¹ÑºÑ»Ñ¼Ñ½Ñ¾Ñ¿ÑÀÑÁÑÂÑÃÑÄÑÅÑÆÑÇÑÈÑÉÑÊÑËÑÌÑÍÑÎÑÏÑÐÑÑÑÒÑÓÑÔÑÕÑÖÑ×ÑØÑÙÑÚÑÛÑÜÑÝÑÞÑßÑàÑáÑâÑãÑäÑåÑæÑçÑèÑéÑêÑëÑìÑíÑîÑïÑðÑñÑòÑóÑôÑõÑöÑ÷ÑøÑùÑúÑûÑüÑýÑþÒ¡Ò¢Ò£Ò¤Ò¥Ò¦Ò§Ò¨Ò©ÒªÒ«Ò¬Ò­Ò®Ò¯Ò°Ò±Ò²Ò³Ò´ÒµÒ¶Ò·Ò¸Ò¹ÒºÒ»Ò¼Ò½Ò¾Ò¿ÒÀÒÁÒÂÒÃÒÄÒÅÒÆÒÇÒÈÒÉÒÊÒËÒÌÒÍÒÎÒÏÒÐÒÑÒÒÒÓÒÔÒÕÒÖÒ×ÒØÒÙÒÚÒÛÒÜÒÝÒÞÒßÒàÒáÒâÒãÒäÒåÒæÒçÒèÒéÒêÒëÒìÒíÒîÒïÒðÒñÒòÒóÒôÒõÒöÒ÷ÒøÒùÒúÒûÒüÒýÒþÓ¡Ó¢Ó£Ó¤Ó¥Ó¦Ó§Ó¨Ó©ÓªÓ«Ó¬Ó­Ó®Ó¯Ó°Ó±Ó²Ó³Ó´ÓµÓ¶Ó·Ó¸Ó¹ÓºÓ»Ó¼Ó½Ó¾Ó¿ÓÀÓÁÓÂÓÃÓÄÓÅÓÆÓÇÓÈÓÉÓÊÓËÓÌÓÍÓÎÓÏÓÐÓÑÓÒÓÓÓÔÓÕÓÖÓ×ÓØÓÙÓÚÓÛÓÜÓÝÓÞÓßÓàÓáÓâÓãÓäÓåÓæÓçÓèÓéÓêÓëÓìÓíÓîÓïÓðÓñÓòÓóÓôÓõÓöÓ÷ÓøÓùÓúÓûÓüÓýÓþÔ¡Ô¢Ô£Ô¤Ô¥Ô¦Ô§Ô¨Ô©ÔªÔ«Ô¬Ô­Ô®Ô¯Ô°Ô±Ô²Ô³Ô´ÔµÔ¶Ô·Ô¸Ô¹ÔºÔ»Ô¼Ô½Ô¾Ô¿ÔÀÔÁÔÂÔÃÔÄÔÅÔÆÔÇÔÈÔÉÔÊÔËÔÌÔÍÔÎÔÏÔÐÔÑÔÒÔÓÔÔÔÕÔÖÔ×ÔØÔÙÔÚÔÛÔÜÔÝÔÞÔßÔàÔáÔâÔãÔäÔåÔæÔçÔèÔéÔêÔëÔìÔíÔîÔïÔðÔñÔòÔóÔôÔõÔöÔ÷ÔøÔùÔúÔûÔüÔýÔþÕ¡Õ¢Õ£Õ¤Õ¥Õ¦Õ§Õ¨Õ©ÕªÕ«Õ¬Õ­Õ®Õ¯Õ°Õ±Õ²Õ³Õ´ÕµÕ¶Õ·Õ¸Õ¹ÕºÕ»Õ¼Õ½Õ¾Õ¿ÕÀÕÁÕÂÕÃÕÄÕÅÕÆÕÇÕÈÕÉÕÊÕËÕÌÕÍÕÎÕÏÕÐÕÑÕÒÕÓÕÔÕÕÕÖÕ×ÕØÕÙÕÚÕÛÕÜÕÝÕÞÕßÕàÕáÕâÕãÕäÕåÕæÕçÕèÕéÕêÕëÕìÕíÕîÕïÕðÕñÕòÕóÕôÕõÕöÕ÷ÕøÕùÕúÕûÕüÕýÕþÖ¡Ö¢Ö£Ö¤Ö¥Ö¦Ö§Ö¨Ö©ÖªÖ«Ö¬Ö­Ö®Ö¯Ö°Ö±Ö²Ö³Ö´ÖµÖ¶Ö·Ö¸Ö¹ÖºÖ»Ö¼Ö½Ö¾Ö¿ÖÀÖÁÖÂÖÃÖÄÖÅÖÆÖÇÖÈÖÉÖÊÖËÖÌÖÍÖÎÖÏÖÐÖÑÖÒÖÓÖÔÖÕÖÖÖ×ÖØÖÙÖÚÖÛÖÜÖÝÖÞÖßÖàÖáÖâÖãÖäÖåÖæÖçÖèÖéÖêÖëÖìÖíÖîÖïÖðÖñÖòÖóÖôÖõÖöÖ÷ÖøÖùÖúÖûÖüÖýÖþ×¡×¢×£×¤×¥×¦×§×¨×©×ª×«×¬×­×®×¯×°×±×²×³×´×µ×¶×·×¸×¹×º×»×¼×½×¾×¿×À×Á×Â×Ã×Ä×Å×Æ×Ç×È×É×Ê×Ë×Ì×Í×Î×Ï×Ð×Ñ×Ò×Ó×Ô×Õ×Ö×××Ø×Ù×Ú×Û×Ü×Ý×Þ×ß×à×á×â×ã×ä×å×æ×ç×è×é×ê×ë×ì×í×î×ï×ð×ñ×ò×ó×ô×õ×ö×÷×ø×ù";
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

/**¶¨Òå¹«ÓÃ·½·¨:Ìî³äselectÄ¬ÈÏÖµ*/
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
		BatAlert.MyAlert("ÓÐ´íÎó³öÏÖ£ºÔ¶³ÌÊý¾Ý¶ÁÈ¡Ê§°Ü£¡");
	}
}

/**
 * @describe Õâ¸ö·½·¨¶ÔSELECTµÄ³õÊ¼»¯±ÈinitTjList(data)Õâ¸öº¯Êý¸ü¼ÓÍ¨ÓÃ£¬½¨ÒéÔÚÒ³ÃæÖÐ¶àÓÃÕâ¸öº¯Êý
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
		BatAlert.MyAlert("ÓÐ´íÎó³öÏÖ£ºÔ¶³ÌÊý¾Ý¶ÁÈ¡Ê§°Ü£¡");
	}
}


/**
 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
 * @describe ÊµÏÖ¶ÔselectµÄÉÏµ÷²Ù×÷
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
 * @describe ÊµÏÖ¶ÔselectµÄÏÂµ÷²Ù×÷
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
 * @describe ½«fromSelectÖÐÑ¡ÖÐµÄÑ¡ÏîÒÆµ½toSelectÖÐ
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
		if (!confirm("µ±Ç°±íµÄÐÐÊý³¬¹ý" + maxRowToSort + "ÐÐ,ÅÅÐò½«ºÄ·Ñ±È½Ï³¤µÄÊ±¼ä,È·¶¨ÒªÅÅÐòÂð?")) {
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
	window.status = " (ÅÅÐòºÄÊ±: " + (new Date - start) + "ms)";
}
/**¶¨Òå¹«ÓÃ·½·¨:»ñÈ¡¸¸¶ÔÏó*/
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
/**¶¨Òå¹«ÓÃ·½·¨:ÐÐµã»÷*/
function rowOnClick(objTr) {
	if (curr_row != null && curr_row.tagName.toLowerCase() == "tr") {
		curr_row.style.backgroundColor = obj_bgc;
	}
	curr_row = objTr;
	obj_bgc = curr_row.style.backgroundColor;
	curr_row.style.backgroundColor = cur_bgc;
}
/**¶¨Òå¹«ÓÃ·½·¨:Ìæ»»×Ö·û´®*/
function replaceChar(conversionString, inChar, outChar) {
	var convertedString = conversionString.split(inChar);
	convertedString = convertedString.join(outChar);
	return convertedString;
}
/**¶¨Òå¹«ÓÃ·½·¨:µ¯³öÎÞÄ£Ê½¶Ô»°¿ò*/
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
/**¶¨Òå¹«ÓÃ·½·¨:±íµ¥Ìá½»*/
function refreshForm(url) {
	url = url.replace("/xgxt/","");
	document.forms[0].action = url;
	document.forms[0].submit();
}
/**¶¨Òå¹«ÓÃ·½·¨:¼ì²â×Ó´°¿Ú*/
function checkWinType() {
	if (window.dialogArguments == null || window.parent == null) {
		alert("·Ç·¨·ÃÎÊ£¬´°¿Ú¼´½«¹Ø±Õ£¡");
		Close();
		return false;
	}
}
/**¶¨Òå¹«ÓÃ·½·¨:Êý×Ö¸ñÊ½¼°±ß½çÅÐ¶Ï*/
function numFormatChk(obj,minV,maxV) {	
	if(obj.value == "")obj.value="0";
	var reg1 = /^\d+[.]?\d+$/g;
	if(obj.value.length == 1){
		reg1 = /^\d+$/g;
	}
	var str = obj.value;
	var r1 = str.match(reg1);
	if(r1 == null){
		alert("ÄúÊäÈëµÄÊý×Ö²»ºÏ·¨£¬ÇëÖØÐÂÊäÈë£¡");
		obj.select();
		obj.focus();
		return false;
	}
	var tmpV = parseFloat(str);
	if(minV != null && tmpV < minV){
		alert("ÄúÊäÈëµÄÖµ¹ýÐ¡£¨×îÐ¡Îª£º" + minV + "£©£¬ÇëÖØÐÂÊäÈë£¡");
		obj.select();
		obj.focus();
		return false;
	}
	if(maxV != null && tmpV > maxV){
		alert("ÄúÊäÈëµÄÖµ¹ý´ó£¨×î´óÎª£º" + maxV + "£©£¬ÇëÖØÐÂÊäÈë£¡");
		obj.select();
		obj.focus();
		return false;
	}
	return true;
}
/**¶¨Òå¹«ÓÃ·½·¨:Ñ§ÔºÎÞÐ§¶ÔÏó*/
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
/**¶¨Òå¹«ÓÃ·½·¨:ÏÔÊ¾ÌáÊ¾ÐÅÏ¢*/
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
/**¶¨Òå¹«ÓÃ·½·¨:ÅÐ¶Ïµ±Ç°ÊÇ·ñÑ¡ÖÐÐÐ*/
function chkCurrRow(){
	if(curr_row == null){
		alert("´Ë²Ù×÷ÐèÒªÓÐÑ¡ÖÐµÄÐÐ£¬ÇëÑ¡Ôñ£¨µ¥»÷£©Òª²Ù×÷µÄÐÐ£¡");
		return false;
	}
	return true;
}
/**¶¨Òå¹«ÓÃ·½·¨:×éºÏÈÕÆÚÊ±¼ä*/
function unionDateTime(ids){
	var d = $(ids + "1").value;
	var h = $(ids + "2").value;
	var m = $(ids + "3").value;
	var s = $(ids + "4").value;
	$(ids).value = replaceChar(d,"-","") + h + m + s;
}
