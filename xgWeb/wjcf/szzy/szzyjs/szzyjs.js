/**
*将页面获取的星期转换
*/
function chgxqjval(val)
{
			var xqjv = document.getElementById(val).value;
			if (xqjv=='星期一'){
				xqjv='1';
			}else if (xqjv=='星期二') {
				xqjv='2';
			}else if (xqjv=='星期日') {
				xqjv='7';
			}else if (xqjv=='星期三') {
				xqjv='3';
			}else if (xqjv=='星期四') {
				xqjv='4';
			}else if (xqjv=='星期五') {
				xqjv='5';
			}else if (xqjv=='星期六') {
				xqjv='6';
			}else {
				xqjv='';
			}
}

/**
*增加考勤信息页面跳转
*/
function chkPriseOne5(url, w, h) {
	if (w == null) {
		w = 700;
	}
	if (h == null) {
		h = 500;
	}	
	if (curr_row == null) {
		alert("请选择要操作的行！");
		return false;
	} else {		
		var pk = '';
		var val = curr_row.cells[0].getElementsByTagName("input")[0].value;		
		url += val;
		url += '&pk=';
		pk = curr_row.cells[0].getElementsByTagName("input")[1].value;
		if (pk != null && pk !=''){
			url += pk;
		}else{
			pk = '';
			url += pk;
		}
		showTopWin(url, w, h);
}
}

/**
*保存考勤信息跳转
*/
function savedata5(mustFill,url)
{
	var pkVal = document.getElementById("pkValue").value;
	url+=pkVal;
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if($(eles[i])){
			if (document.getElementById(eles[i]).value == "") {
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
	}
	var ydrs = parseInt(document.getElementById("ydrs").value);
	var sdrs = parseInt(document.getElementById("sdrs").value);
	var qjrs = parseInt(document.getElementById("qjrs").value);
	var bjsj;
	if (document.getElementById("bjsj").value==''){
		bjsj=0;
	}else{
		bjsj = parseInt(document.getElementById("bjsj").value);
	}
	var gj;
	if (document.getElementById("gj").value==''){
		gj = 0;
	}else{
		gj = parseInt(document.getElementById("gj").value);
	}
	if(sdrs+qjrs > ydrs){
		alert("实到人数与请假人数不符合!");
		return false;
	}
	var kssj=document.getElementById("kssj").value;
	var jssj=document.getElementById("jssj").value;
	var arry1=kssj.split(":");
	var arry2=jssj.split(":");
	
	for(i=0;i<2;i++)
	{
		var temp1=parseInt(arry1[i]);
		var temp2=parseInt(arry2[i]);
		if(temp1>temp2)
		{
			alert("开始时间不能大于结束时间！");
			return false;
		}
	}
	if (bjsj+gj > qjrs){
		alert("请假人数不符合!");
		return false;
	}
	document.forms[0].action = url;
	document.forms[0].submit();
}

function expTab5(the_table, tabTit, titSpan) {	
	/*var HKEY_Root="HKEY_CURRENT_USER";
	var HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	var Wsh=new ActiveXObject("WScript.Shell");
	var HKEY_Key="header";
	    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
	    HKEY_Key="footer";
	    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); */   
	var table_title = (titSpan == null || titSpan == "") ? tabTit : document.getElementById(titSpan).outerHTML;	
	var the_content = "<style media='print'>\n";
	the_content += ".noPrin{\n";
	the_content += "display:none;}\n";
	the_content += "</style>\n";
	the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
	the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
	the_content += "<script language='javascript' src='js/function.js'>";
	the_content += "</sc";
	the_content += "ript>\n";
	the_content += "<center><h3><b>";
	the_content += table_title;
	the_content += "</b></h3>";
	the_content += "</center>";	
	the_content += "抽查时间：";
	the_content += document.getElementById("kssj").value;
	the_content += "-";
	the_content += document.getElementById("jssj").value;
	the_content += "<center>";	
	the_content += document.all(the_table).outerHTML;		
	the_content = the_content.replace(/ style=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ mode=\"(false|true)"/g, "");
	the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
	the_content = the_content.replace(/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
	the_content += "</center>";
	the_content += "填表人：学生处";
	the_content += "<center>";
	the_content += "\n<br><div class='noPrin'><input type='button' class='button2' value='页面设置' onclick=\"WebBrowser.ExecWB(8,1)\">";
	the_content += "<input type='button' class='button2' value='打印预览' onclick=\"WebBrowser.ExecWB(7,1)\">";
	the_content += "<input type='button' class='button2' value='直接打印' onclick=\"WebBrowser.ExecWB(6,6)\">";
	the_content += "<\/div>";
	//confirm(the_content);
	var newwin = window.open("about:blank", "_blank", "");
	newwin.document.open();
	newwin.document.write(the_content);
	newwin.document.close();
	newwin = null;
}

