/**
 * ��ӡԤ��
 */
function Preview() 
{
	//alert("klafj");
	if (document.all.eprint.defaultPrinterName.length==0){
		alert("���Ȱ�װ��ӡ������ִ�д˹��ܣ�");
		return;
	}
  document.all.eprint.Preview();//��ӡԤ��
}

/**
 * ��ӡ
 */
function Print() {  
	if (document.all.eprint.defaultPrinterName.length==0){
		alert("���Ȱ�װ��ӡ������ִ�д˹��ܣ�");
		return;
	}
  document.all.eprint.InitPrint();
  document.all.eprint.SetMarginMeasure(1);//1mm��default, 2 inch
  document.all.eprint.Print(true);//ֱ�Ӵ�ӡ
}
/**
 * ����WEBPRINT��ʽ�ı����ӡʱ��������߾�,���߾�,���,�߶ȵķ���
 */
function setPrintSize(left, topValue, right, bottom) {
	document.all.eprint.marginTop=topValue;
 	document.all.eprint.marginLeft=left;
	document.all.eprint.marginRight=right;
    document.all.eprint.marginBottom=bottom;
}

/**
 * ����WEBPRINT��ʽ�ı����ӡʱ������ҳͷ��ҳβ
 */
function setPrintHFer(header, footer) {
	//alert("chenhuamao");
	document.all.eprint.header=header;
 	document.all.eprint.footer=footer;
}

/**
 * ����Ҫ��ӡ�ı������߾�,���߾�,���,�߶�
 */
function setGridPosition(grid, left, topValue, width, height) {
	grid.left = left;
	grid.top = topValue;
	grid.width = width;
	grid.height = height;
}

/**
 * ����Ҫ��ӡ�ı������߾�,���߾�,�ұ߾�,�ױ߾�
 */
function setGridSize(grid, left, topValue, right, bottom) {
	grid.prn.tabLeft = left;
	grid.prn.tabTop = topValue;
	grid.prn.tabRight = right;
	grid.prn.tabBottom = bottom;
}

/**
 * ���幫�÷���:����WebPrint���ɱ���
 * �ӱ��beginRow�п�ʼ���ж�col�е�ǰ�����е�ֵ�Ƿ���ȡ�������ȵĻ����ͷ�ҳ
 * orient��ʾ��ӡ����1Ϊ����2Ϊ����,Ĭ��Ϊ����
 */
function expTabWebPrint(the_table, tabTit, titSpan, mar, beginRow, col, orient) {
	if($(the_table).tagName.toLowerCase() == "table" && $(the_table).rows.length < 1){
		BatAlert.MyAlert("��ǰҳ��û�пɴ�ӡ�����ݣ�");
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
	the_content += "	setPrintHFer('', \"&bҳ��:&p/&P\");";
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
	the_content += "<center><div class='noPrin'><input type='button' class='button2' value='��ӡԤ��' onclick=\"Preview()\">";
	the_content += "<input type='button' class='button2' value='ֱ�Ӵ�ӡ' onclick=\"Print()\"></div>";
	the_content += "<h3><b>";
	the_content += table_title;
	the_content += "</b></h3>";
	var tempTable = $(the_table);
	if (beginRow != null) {		//�Ƿ���з����ӡ��ͨ���˲��������ж�
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