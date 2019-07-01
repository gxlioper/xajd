/**
 * 打印预览
 */
function Preview() 
{
	//alert("klafj");
	if (document.all.eprint.defaultPrinterName.length==0){
		alert("请先安装打印机，再执行此功能！");
		return;
	}
  document.all.eprint.Preview();//打印预览
}

/**
 * 打印
 */
function Print() {  
	if (document.all.eprint.defaultPrinterName.length==0){
		alert("请先安装打印机，再执行此功能！");
		return;
	}
  document.all.eprint.InitPrint();
  document.all.eprint.SetMarginMeasure(1);//1mm是default, 2 inch
  document.all.eprint.Print(true);//直接打印
}
/**
 * 采用WEBPRINT形式的报表打印时，设置左边距,顶边距,宽度,高度的方法
 */
function setPrintSize(left, topValue, right, bottom) {
	document.all.eprint.marginTop=topValue;
 	document.all.eprint.marginLeft=left;
	document.all.eprint.marginRight=right;
    document.all.eprint.marginBottom=bottom;
}

/**
 * 采用WEBPRINT形式的报表打印时，设置页头和页尾
 */
function setPrintHFer(header, footer) {
	//alert("chenhuamao");
	document.all.eprint.header=header;
 	document.all.eprint.footer=footer;
}

/**
 * 设置要打印的报表的左边距,顶边距,宽度,高度
 */
function setGridPosition(grid, left, topValue, width, height) {
	grid.left = left;
	grid.top = topValue;
	grid.width = width;
	grid.height = height;
}

/**
 * 设置要打印的报表的左边距,顶边距,右边距,底边距
 */
function setGridSize(grid, left, topValue, right, bottom) {
	grid.prn.tabLeft = left;
	grid.prn.tabTop = topValue;
	grid.prn.tabRight = right;
	grid.prn.tabBottom = bottom;
}

/**
 * 定义公用方法:采用WebPrint生成报表
 * 从表的beginRow行开始，判断col列的前后两行的值是否相等。若不相等的话，就分页
 * orient表示打印方向，1为竖向，2为横向,默认为横向
 */
function expTabWebPrint(the_table, tabTit, titSpan, mar, beginRow, col, orient) {
	if($(the_table).tagName.toLowerCase() == "table" && $(the_table).rows.length < 1){
		BatAlert.MyAlert("当前页面没有可打印的数据！");
		return false;
	}
	if(mar){		
		try{
			var mars = mar.split("-");
			PageSetup(mars[0],mars[1],mars[2],mars[3],mars[4],mars[5]);
		}catch(e){
			
		}
	}
	var table_title = (titSpan == null || titSpan == "") ? tabTit : $(titSpan).innerText;
	var the_content = '';
	the_content += "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"
	the_content += "<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"GBK\">"
	the_content += "<head>";
	the_content += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\" />";
	the_content += "<meta http-equiv=\"Content-Language\" content=\"GBK\" />";
	the_content += "<style media='print'>\n";
	the_content += ".noPrin{\n";
	the_content += "display:none;}\n.noPrint{	page-break-after:always;}\n .fenPage{page-break-after:always;}\n";
	the_content += "</style>\n";
	the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
	the_content += "<script type=\"text/javascript\" src=\"js/webPrint.js\"></script>";
	the_content += "<script type=\"text/javascript\">";
	the_content += "window.onload = function () {";
	the_content += "	setPrintSize(10,5,10,15);";
	the_content += "	setPrintHFer('', \"&b页码:&p/&P\");";
	//the_content += "	alert(document.getElementById('"+the_table+"').outerHTML);";
	if (orient) {
		the_content += "	document.all.eprint.orientation = 2;";
	} else {
		the_content += "	document.all.eprint.orientation = 1;";
	}
	the_content += "};";
	the_content += "</script>";
	the_content += "<object id=eprint classid=\"clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441\" codebase=\"images/webprint.cab\" viewasext> </object>\n";
	the_content += "</head>";
	the_content += "<body>";
	the_content += "<center><div class='noPrin'><input type='button' class='button2' value='打印预览' onclick=\"Preview()\">";
	the_content += "<input type='button' class='button2' value='直接打印' onclick=\"Print()\"></div>";
	the_content += "<h3><b>";
	the_content += table_title;
	the_content += "</b></h3>";
	var tempTable = $(the_table);
	if (beginRow != null) {		//是否进行分组打印，通过此参数进行判断
		for (var i = beginRow; i<tempTable.rows.length-1; i++) {
			if (tempTable.rows[i+1]!=null && tempTable.rows[i]!=null && tempTable.rows[i+1].cells[col-1]!=null && tempTable.rows[i].cells[col-1]!=null && tempTable.rows[i+1].cells[col-1].innerHTML!=tempTable.rows[i].cells[col-1].innerHTML){
				tempTable.rows[i+1].style.pageBreakBefore = "always";
			}
		}
	}
	the_content += tempTable.outerHTML;
	/*the_content = the_content.replace(/( *)(style=\")(.*)(\")/gi, "");
	the_content = the_content.replace(/(<td.*<)(input|button)(.*)(checkbox|text|button)(.*\/td>)/gi, "");
	the_content = the_content.replace(/( *)(on)([dbl]*)(click=\")(.*)(\")/gi, "");
	the_content = the_content.replace(/(<span)(.*)(<\/span>)/gi, "");
	the_content += "\n<br>";*/
	the_content += "</body>";
	the_content += "</html>";
	var newwin = window.open("about:blank", "_blank", "");
	newwin.document.open();
	newwin.document.write(the_content);
	newwin.document.close();
	newwin = null;
}