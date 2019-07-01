document.write("<script defer=\"defer\" language=\"javascript\" src=\"js/My97DatePicker/WdatePicker.js\"></script>");
var oldLink = null;
// code to change the active stylesheet
function setActiveStyleSheet(link, title) {
  var i, a, main;
  for(i=0; (a = document.getElementsByTagName("link")[i]); i++) {
    if(a.getAttribute("rel").indexOf("style") != -1 && a.getAttribute("title")) {
      a.disabled = true;
      if(a.getAttribute("title") == title) a.disabled = false;
    }
  }
  if (oldLink) oldLink.style.fontWeight = 'normal';
  oldLink = link;
  link.style.fontWeight = 'bold';
  return false;
}

// This function gets called when the end-user clicks on some date.
function selected(cal, date) {
  cal.sel.value = date; // just update the date in the input field.
  if (cal.dateClicked && (cal.sel.id == "sel1" || cal.sel.id == "sel3"))
    // if we add this call we close the calendar on single-click.
    // just to exemplify both cases, we are using this only for the 1st
    // and the 3rd field, while 2nd and 4th will still require double-click.
    cal.callCloseHandler();
}

// And this gets called when the end-user clicks on the _selected_ date,
// or clicks on the "Close" button.  It just hides the calendar without
// destroying it.
function closeHandler(cal) {
  cal.hide();                        // hide the calendar
}

/**
* 第一个参数：控件ID 一般传：this.id
* 第二个参数：当前时间控件FORMAT(ymmdd、y-mm-dd、y-MM、yyyyMMdd、yyyy-MM-dd、yyyy-MM)
* 第三个参数：当前时间控件是否指定最大/最小日期 （false[有最小日期];true[有最大日期];空[无最大最小日期]）	
* 第四个参数：指定最大/最小日期的控件ID（初始值：空）
* 第五个参数：控件的位置的left,不要加px  注意:坐标单位是px,是相对当前框架坐标(不受滚动条影响)
* 第六个参数：控件的位置的top,不要加px   注意:坐标单位是px,是相对当前框架坐标(不受滚动条影响)
 * @return
 */
function showCalendar() {																
	
	  //当前时间控件ID																
	  var id = arguments[0];																
	  																
	  //当前时间控件FORMAT																
	  var format = arguments[1];
																	
	  //当前时间控件是否指定最大/最小日期																
	  //（false[有最小日期];true[有最大日期];空[无最大最小日期]）																
	  var bolMax = "";																
	  if(arguments[2]) bolMax = arguments[2];																
	  																
	  //指定最大/最小日期的控件ID（初始值：空）																
	  var idTo = "";																
	  if(arguments[3]) idTo = arguments[3];	
	  										
	  //控件的位置的left（初始值：空）																
	  var positionLeft = "";																
	  if(arguments[4]) positionLeft = arguments[4];
	  										
	  //控件的位置的left（初始值：空）										
	  var positionTop = "";																
	  if(arguments[5]) positionTop = arguments[5];
	  
	  //position设置
      var positionParam = {};
	  if(positionLeft != "" ){
		  positionParam.left = parseInt(positionLeft);
	  }
	  if(positionTop !=""){
		  positionParam.top = parseInt(positionTop);
	  }
	  	  
	  if(document.getElementById(id).onblur!=null){																
		  															
		var format = format.split("-");															
		format = format.join("");															
	  }																
	  if(format.toLowerCase()==="ymmdd"){																
	  	format='yyyyMMdd';															
	  }																
	  if(format.toLowerCase()==="y-mm-dd"){																
	  	format='yyyy-MM-dd';															
	  }																
	  if(format.toLowerCase()==="y-MM"){																
		format='yyyy-MM';															
	  }				

	  var dateParams = {el:id,skin:'whyGreen',lang:'zh-cn',startDate:'08:00:00',dateFmt:format,errDealMode:1};
	  dateParams.position = positionParam;
	  dateParams.onpicked = arguments[6];
	  
	  // 指定最大/最小日期的控件ID存在																
	  if (idTo != "")																
	  {																
	    // 取得指定控件ID																
	    var getIdToDate = "#F{$dp.$D(\'"+idTo +"\')}";																
																	
	    // 限制指定控件ID为最大日期																
	    if(bolMax) {	
	    	dateParams.maxDate = getIdToDate;													
																	
	 	// 限制指定控件ID为最小日期															
	    }else{		
	    	dateParams.minDate = getIdToDate;													
	 	}															
	  }
	   WdatePicker(dateParams);	   																
	   return false;																
	}																


var MINUTE = 60 * 1000;
var HOUR = 60 * MINUTE;
var DAY = 24 * HOUR;
var WEEK = 7 * DAY;

// If this handler returns true then the "date" given as
// parameter will be disabled.  In this example we enable
// only days within a range of 10 days from the current
// date.
// You can use the functions date.getFullYear() -- returns the year
// as 4 digit number, date.getMonth() -- returns the month as 0..11,
// and date.getDate() -- returns the date of the month as 1..31, to
// make heavy calculations here.  However, beware that this function
// should be very fast, as it is called for each day in a month when
// the calendar is (re)constructed.
function isDisabled(date) {
  var today = new Date();
  return (Math.abs(date.getTime() - today.getTime()) / DAY) > 10;
}

function flatSelected(cal, date) {
  var el = document.getElementById("preview");
  el.innerHTML = date;
}

