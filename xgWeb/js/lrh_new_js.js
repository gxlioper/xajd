//按ctrl选中多条记录
 	var   Rows=new   Array(); //所有选中的行对象   
 	var   ShiftStartRow=""; //Shift多选时存储开始行对象   
    var   popUpWin=0;
  //选行主函数   
  function   onfocusit(){   
  		iRow=window.event.srcElement;   
  		do{   
  			iRow=iRow.parentElement; 
  		}	while(iRow.tagName!='TR')   
    
  //Ctrl多选   
  		if(event.ctrlKey){   
  			var   j=-1;   
  			for(i=0;i<Rows.length;i++){   
  			if(iRow==Rows[i]){
  			//	alert("a");
 			//	alert(Rows[i].getElementsByTagName("input")[0].value);   
  				j=i;   
  				break;   
  			}   
  		}   
  		if(j!=-1){   
  			for(i=j;i<Rows.length-1;i++){   
  			Rows[i]=Rows[i+1];
  		//	alert("b");
  		//	alert(Rows[i].getElementsByTagName("input")[0].value);
 			}   
  			Rows.length=Rows.length-1;   
 	 	}	
 	 	else{   
  			Rows[Rows.length]=iRow;   
  		}   
 	 		ShiftStartRow=iRow;   
  	} else{	
		if (Rows.length!=0){
			for (i=0; i<Rows.length; i++){	
				if (Rows[i].tagName.toLowerCase() == "tr") {
					Rows[i].style.backgroundColor = "#FFFFFF";
						// alert("aa");  
						// var kk=document.forms[0].xn_id.value;
						//document.
  	 					//alert(Rows[i].getElementsByTagName("input")[0].value);
	    		}
			}
		}
		Rows.length=1;
		Rows[0]=iRow;
//		ShiftStartRow=iRow;
	}
	 changeColor(iRow);
	}
	
	
function changeColor(E){   
  	for(var i=1;i<E.parentElement.rows.length;i++){   
  	 	E.parentElement.rows(i).style.backgroundColor="#FFFFFF"; 
 	}   
  	for(i=0;i<Rows.length;i++){   
  		Rows[i].style.backgroundColor="#EEEEEE";   
 	}   
 }
  
  
//弹出对话框进行增删改操作
function viewMore_LRH(doType,zxsbh){
		var pkValue = "";
		var zxsbh=zxsbh;
		var url = "";
		var w = 550;
		var h = 450;
		//var w = 650;
		//var h = 500;
		if(doType=="View_Zxsxx"||doType=="View_Xssqzxs"){
			url="/xgxt/xljk_zxsxx_view.do?doType="+doType+"&zxsbh="+zxsbh;
			showTopWin(url,w,h);
		}else if (doType == "del") {
			if (curr_row == null) {
				alert("请选择要删除的数据！\n（单击相应的行）");
				return false;
			}else if (confirm("确定要删除该行数据吗？")) {
				pkValue =curr_row.cells[0].innerText;
				url="/xgxt/xljk_zxsxx_del.do?act=xljk_zxsxx&doType="+doType+"&zxsbh="+pkValue;
				refreshForm(url);
			}
		}else if (doType == "update") {
			if (curr_row == null) {
				alert("请选择要修改的数据！\n（单击相应的行）");
				return false;
			}else if (confirm("确定要修改该行数据吗？")) {
				pkValue =curr_row.cells[0].innerText;
				url="/xgxt/xljk_zxsxx_update.do?act=xljk_zxsxx&zxxbh="+pkValue;
				showTopWin(url,w,h);
			}
		}
	}

//刷新url进行跳转
function refreshForm(url) {
	url = url.replace("/xgxt/","");
	document.forms[0].action = url;
	document.forms[0].submit();
}

//生成一条选项记录
function lrh_createStList(yxstlbV,stV) {
	var flag = false;
	if (stV != null) {
		for (j = 0;j<stV.length;j++){
			for (i = 0; i < document.forms[0].yxstlb.options.length; i++) {
				if (document.forms[0].yxstlb.options[i].value == stV[j]) {
					flag = true;
				}
			}
			if(!flag){
				document.forms[0].yxstlb.options[document.forms[0].yxstlb.options.length] = new Option((document.forms[0].yxstlb.options.length + 1) + "----" + yxstlbV[j], stV[j]);
				flag = false;
			}else{
				flag = false;
			}
		}
		count.innerText = document.forms[0].yxstlb.options.length;
	}
}
//更新已经存在的选项记录
function lrh_updateStList(flag) {
	if (flag == "clear") {
		document.forms[0].yxstlb.options.length = 0;
		count.innerText = document.forms[0].yxstlb.options.length;
	} else {
		var yxstlbListIndex = document.forms[0].yxstlb.selectedIndex;
		var tmp;
		if (yxstlbListIndex < 0) {
			alert("请先选定行!");
			return false;
		}
		var m = 1;
		var size = document.forms[0].yxstlb.options.length;
		if (flag == "del") {
			for(var i = 0;i<size;i++){
				if(document.forms[0].yxstlb.options[i].selected==true){
					document.forms[0].yxstlb.options[i] = null;
					size--;
					i--;
				}else{
					var text = document.forms[0].yxstlb.options[i].text;
					document.forms[0].yxstlb.options[i].text = m + "----" + text.substring(text.indexOf('-')+5,text.length);
					m++;
				}
			}
//			document.forms[0].yxstlb.options[yxstlbListIndex] = null;
//			for (i = 0; i < document.forms[0].yxstlb.options.length; i++) {
//				document.forms[0].yxstlb.options[i].text = document.forms[0].yxstlb.options[i].value + "----" + (i + 1);
//		}
			count.innerText = document.forms[0].yxstlb.options.length;
		}
//		if (flag == "up") {
//			if (yxstlbListIndex == 0) {
//				return false;
//			}
//			tmp = document.forms[0].yxstlb.options[yxstlbListIndex - 1].value;
//			document.forms[0].yxstlb.options[yxstlbListIndex - 1].value = document.forms[0].yxstlb.options[yxstlbListIndex].value;
//			document.forms[0].yxstlb.options[yxstlbListIndex - 1].text = document.forms[0].yxstlb.options[yxstlbListIndex].value + "------------" + yxstlbListIndex;
//			document.forms[0].yxstlb.options[yxstlbListIndex].value = tmp;
//			document.forms[0].yxstlb.options[yxstlbListIndex].text = tmp + "------------" + (yxstlbListIndex + 1);
//			document.forms[0].yxstlb.selectedIndex = yxstlbListIndex - 1;
//		}
//		if (flag == "down") {
//			if (yxstlbListIndex >= document.forms[0].yxstlb.options.length - 1) {
//				return false;
//			}
//			tmp = document.forms[0].yxstlb.options[yxstlbListIndex + 1].value;
//			document.forms[0].yxstlb.options[yxstlbListIndex + 1].value = document.forms[0].yxstlb.options[yxstlbListIndex].value;
//			document.forms[0].yxstlb.options[yxstlbListIndex + 1].text = document.forms[0].yxstlb.options[yxstlbListIndex].value + "------------" + (yxstlbListIndex + 2);
//			document.forms[0].yxstlb.options[yxstlbListIndex].value = tmp;
//			document.forms[0].yxstlb.options[yxstlbListIndex].text = tmp + "------------" + (yxstlbListIndex + 1);
//			document.forms[0].yxstlb.selectedIndex = yxstlbListIndex + 1;
//		}
	}
}

//将生成的选项记录保存到数据库
function lrh_StSjDoSave() {
	var yxstStr = "";
	for (i = 0; i < document.forms[0].yxstlb.options.length; i++) {
		if (yxstStr != "") {
			yxstStr = yxstStr + "-";
		}
		//alert(document.forms[0].yxstlb.options[i].value);
		yxstStr = yxstStr + document.forms[0].yxstlb.options[i].value;
		//alert(yxstStr);
	}
	//var url='/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=sjst_st_add&yxstStr='+yxstStr;
	//document.forms[0].action = url;
	//document.forms[0].submit();
	/*do save*/
	var sjlsh =document.getElementById("sjlsh").value;
	underDealWith();
	sjstProcFun.sjstbBatchAddRecord(sjlsh,yxstStr,function(data){
		if(data){			
			alert("保存成功！");
			window.dialogArguments.document.getElementById("search_go").click();
			Close();
		}else{
			alert("保存失败！");
			document.getElementById("tmpdiv").style.display = "none"; //no visable
		}
	});

}


//检查输入的是数字字符
function chkInput(id){
	var key = event.keyCode;
	var len = document.getElementsByName(id).length;
	if((key>=48&&key<=57)||key==46||key==127||key==8||key==17||(key>=96&&key<=105)||key==110){
	}
	else{
		for(i=0;i<len;i++){
			var qmcj=document.getElementsByName(id)[i].value;
			var reg = /^\d+\.{0,1}\d{0,3}$/g;
			var role = qmcj.match(reg);
			if(role==null&&qmcj!=""){
				alert("请输入英文状态下的数字!")
            	document.getElementsByName(id)[i].value="";
            }
        }
	}
}

function chkInput2(id){
	var key = event.keyCode;
	var len = document.getElementsByName(id).length;
	if((key>=48&&key<=57)||key==46||key==127||key==8||(key>=96&&key<=105)||key==110){
	}else{
		for(i=0;i<len;i++){
			var qmcj=document.getElementsByName(id)[i].value;
			var reg = /^\d+\.{0,1}\d{0,3}$/g;
			var role = qmcj.match(reg);
			if(role==null&&qmcj!=""){
				alert("请输入英文状态下的数字!")
            	document.getElementsByName(id)[i].value="";
            }
        }
	}
}
//
function check_time_type(id){
	var time=document.getElementById(id).value;
	var reg=/[0-23]{2}:[0-59]{2}/g;
	var role=time.Math(reg);
	if(role==null){
		alert(role);
	}else{
		alert(role);
	}
}

function check_File() {
	var tmp = document.getElementById("file").value;
	if (tmp.value == "" || tmp.substring(tmp.length - 4, tmp.length).toLowerCase() != ".xls") {
		alert("您选择的文件不合法，请重新选择！");
		document.getElementById("file").value = "";
		return false;
	} else {
		document.forms[0].action = "/xgxt/lrh_commen_util.do?act=lrh_commen_util&doType=date_inport&tableName=" + document.getElementById("tableName").value;
		document.forms[0].submit();
		return true;
	}
}


function openWin(URLStr, width, height,type){
	if(width == null)width = 400;
	if(height == null)height = 300;
	var left = (BatAlert.W()-width)/2;
	var top = (BatAlert.H()-height)/2;
	if(popUpWin){
		if(!popUpWin.closed) popUpWin.close();
	}
	if(type == null){
		popUpWin = window.open(URLStr, "popUpWin", "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=auto,resizable=no,copyhistory=yes,width=" + width + ",height=" + height + ",left=" + left + ", top=" + top);
	}else{
		popUpWin = window.open(URLStr, "popUpWin", "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=" + width + ",height=" + height + ",left=" + left + ", top=" + top);
	}
}


/**
 * 定义公用方法:采用WebPrint生成报表
 * 从表的beginRow行开始，判断col列的前后两行的值是否相等。若不相等的话，就分页
 */
function expTabWebPrint(the_table, tabTit, titSpan, mar, beginRow, col) {
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
	var the_content = "<style media='print'>\n";
	the_content += ".noPrin{\n";
	the_content += "display:none;}\n.noPrint{	page-break-after:always;}\n .fenPage{page-break-after:always;}\n";
	the_content += "</style>\n";
	the_content += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\" />";
	the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
	the_content += "<script type=\"text/javascript\" src=\"/xgxt/js/webPrint.js\"></script>";
	the_content += "<script type=\"text/javascript\">";
	the_content += "window.onload = function () {";
	the_content += "	setPrintSize(10,5,10,15);";
	the_content += "	setPrintHFer('', \"&b页码:&p/&P\");";
	//the_content += "	alert(document.getElementById('"+the_table+"').outerHTML);";
	the_content += "	document.all.eprint.orientation = 2;";
	the_content += "};";
	the_content += "</script>";
	the_content += "<object id=eprint classid=\"clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441\" codebase=\"/xgxt/images/webprint.cab\" viewasext> </object>\n";
	the_content += "<center><div class='noPrin'><input type='button' class='button2' value='打印预览' onclick=\"Preview()\">";
	the_content += "<input type='button' class='button2' value='直接打印' onclick=\"Print()\"></div>";
	the_content += "<h3><b>";
	the_content += table_title;
	the_content += "</b></h3>";
	var tempTable = $(the_table);
	if (beginRow != null) {	//是否进行分组打印，通过此参数进行判断
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
	var newwin = window.open("about:blank", "_blank", "");
	newwin.document.open();
	newwin.document.write(the_content);
	newwin.document.close();
	newwin = null;
}

function expTab(the_table, tabTit, titSpan) {
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
	the_content += document.all(the_table).outerHTML;
	the_content = the_content.replace(/ style=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ mode=\"(false|true)"/g, "");
	the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
	the_content = the_content.replace(/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
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

/**辅导员保存*/
function fdy_save(doType){
	var fdybh=document.all["fdybh"].value;
	if ( fdybh==""){
		alert ("请填写辅导员编号！");
		document.all["fdybh"].focus();
		return false;
	}
	var xm=document.all["xm"].value;
	if ( xm==""){
		alert ("请填写辅导员姓名！");
		document.all["xm"].focus();
		return false;
	}
	
	var xxdm1=document.all["xxdm1"].value;
	if("10827" != xxdm1){
		var xb=document.all["xb"].value;
		if ( xb==""){
			alert ("请选择性别");
			document.all["xb"].focus();
			return false;
		}
	}
	var xydm=document.all["xydm"].value;
	if ( xydm==""){
		alert ("选择"+jQuery("#xbmc").val()+"!");
		document.all["xydm"].focus();
		return false;
	}
	var rzsj=document.all["rzsj"].value;
	if ( rzsj==""){
		alert ("请选择任职时间!");
		document.all["rzsj"].focus();
		return false;
	}
	var xl=document.all["xl"].value;
		if ( xl==""){
		alert ("请填写学历!");
		document.all["xl"].focus();
		return false;
	}
	var zy=document.all["zy"].value;
	if ( zy==""){
		alert ("请填写专业!");
		document.all["zy"].focus();
		return false;
	}
	var sjhm=document.all["sjhm"].value;
	if ( sjhm==""){
		alert ("请填写手机号码!");
		document.all["sjhm"].focus();
		return false;
	}
	var lxdh=document.all["lxdh"].value;
	if ( lxdh==""){
		alert ("请填写联系电话!");
		document.all["lxdh"].focus();
		return false;
	}
	var byyx=document.all["byyx"].value;
	if ( byyx==""){
		alert ("请填写毕业院校!");
		document.all["byyx"].focus();
		return false;
	}
	var gzjl=document.all["gzjl"].value;
	if ( gzjl==""){
		alert ("请填写工作经历!");
		document.all["gzjl"].focus();
		return false;
	}
	var pxqk=document.all["pxqk"].value;
	if ( pxqk==""){
		alert ("请填写培训情况!");
		document.all["pxqk"].focus();
		return false;
	}
	var lwcg=document.all["lwcg"].value;
	if ( lwcg==""){
		alert ("请填写论文及成果!");
		document.all["lwcg"].focus();
		return false;
	}
	if($("modi_flag")){
		document.all["modi_flag"].value="yes";
	}else if($("add_flag")){
			document.all["add_flag"].value="yes";
	}
	var gzjl=document.all["gzjl"].value;
	var pxqk=document.all["pxqk"].value;
	var lwcg=document.all["lwcg"].value;
	var bz=document.all["bz"].value;
	if(gzjl.length>300){
		alert("工作经历不能超过300个汉字！！");
		return false;
	}
	if(pxqk.length>300){
		alert("培训情况不能超过300个汉字！！");
		return false;
	}
	if(lwcg.length>300){
		alert("科研成果不能超过300个汉字！！");
		return false;
	}
	if(bz.length>200){
		alert("备注不能超过200个汉字！！");
		return false;
	}
	underDealWith();
	refreshForm('/xgxt/xljk_xljkfdy.do?act=xljk_xljkfdy&doType=' + doType);
}

/**验证用户所属部门*/
/**
function usercheck(){
	var userDep=document.all['userDep'].value;
	if(userDep!='010000'){
		document.getElementById('xy').disabled=true;
	}else{
		document.getElementById('xy').disabled=false;
	}
}
*/
function usercheck(){
	var userType=document.all['userType'].value;
	if("xx"==userType||"admin"==userType){
		document.getElementById('xy').disabled=false;
	}else{
		document.getElementById('xy').disabled=true;
	}
}

/**学院心理健康教育活动*/
function xyxljkjyhd_save(doType){
	var zt=document.all["zt"].value;
	if ( zt==""){
		alert ("请填写主题！");
		document.all["zt"].focus();
		return false;
	}
	var xydm=document.all['xydm'].value;
	if(xydm==""){
		alert ("请选择"+jQuery("#xbmc").val()+"！");
		document.all["xydm"].focus();
		return false;
	}
	var hdxs=document.all["hdxs"].value;
	if ( hdxs==""){
//		alert ("请选择教育形式!");
//		document.all["hdxs"].focus();
//		return false;
	}else{
		if(hdxs=="013"){
			var qthdxs=document.all["qthdxs"].value;
			if(qthdxs==""){
				alert ("请填写其他教育活动形式!");
				document.all["qthdxs"].focus();
				return false;
			}
		}
	}
	var dd=document.all["dd"].value;
	if ( dd==""){
		alert ("请填写活动地点！");
		document.all["dd"].focus();
		return false;
	}
			
	var rq=document.all["rq"].value;
	if ( rq==""){
		alert ("请选择活动日期！");
		document.all["rq"].focus();
		return false;
	}
			
	var zcr=document.all["zcr"].value;
	if ( zcr==""){
		alert ("请填写主持人！");
		document.all["zcr"].focus();
		return false;
	}
			
	var xs=document.all["xs"].value;
//	if ( xs==""){
//		alert ("请填写学生！");
//		document.all["xs"].focus();
//		return false;
//	}
			
	var cyxs=document.all["cyxs"].value;
	if ( cyxs==""){
		alert ("请填写参与学生！");
		document.all["cyxs"].focus();
		return false;
	}
			
	var rs=document.all["rs"].value;
	if ( rs==""){
		alert ("请填写参与学生人数！");
		document.all["rs"].focus();
		return false;
	}
			
	var hdjl=document.all["hdjl"].value;
	if ( hdjl==""){
		alert ("请填写教育活动记录！");
		document.all["hdjl"].focus();
		return false;
	}
			
	var hdxg=document.all["hdxg"].value;
	if ( hdxg==""){
		alert ("请填写教育活动效果！");
		document.all["hdxg"].focus();
		return false;
	}
	if($("modi_flag")){
		document.all["modi_flag"].value="yes";
	}else if($("add_flag")){
			document.all["add_flag"].value="yes";
	}		
	var hdjl=document.all["hdjl"].value;
	var hdxg=document.all["hdxg"].value;
	if (hdjl.length>350){
		alert ("教育活动记录不能超过600个汉字！");
		document.all["hdjl"].focus();
		return false;
	}
	if (hdxg.length>350){
		alert ("教育活动效果不能超过600个汉字！");
		document.all["hdxg"].focus();
		return false;
	}
	underDealWith();
	refreshForm('/xgxt/xljk_xyxljkjyhd.do?act=xyxljkjyhd&doType=' + doType);
}
	
function check_qthdxs(){
	var hdxs=document.all["hdxs"].value;
	if(hdxs=="013"){
		document.getElementById('qthdxs').readOnly=false;
	}else{
		document.getElementById('qthdxs').readOnly=true;
		document.all["qthdxs"].value="";
	}
}
	
function lrh_xyDisabled() {
	if (document.forms[0].userType.value == "xx") {
		document.getElementById('xydm').disabled = false;
	}
}	

/**心理健康观察员保存或更新*/
function gcy_save(doType){
	var xh=document.all["xh"].value;
	if ( xh==""){
		alert ("请根据选择按键选择学生信息！");
		document.all["xh"].focus();
		return false;
	}
	var csny=document.all["csny"].value;
//	if ( csny==""){
//		alert ("请填写观察员出生年月");
//		document.all["csny"].focus();
//		return false;
//	}
	var sjhm=document.all["sjhm"].value;
	if ( sjhm==""){
		alert ("请填写会员手机号码");
		document.all["sjhm"].focus();
		return false;
	}
//	var qsdh=document.all["qsdh"].value;
//	if ( qsdh==""){
//		alert ("填写会员寝室电话");
//		document.all["qsdh"].focus();
//		return false;
//	}
//	var gcybh=document.all["gcybh"].value;
//	if ( gcybh==""){
//		alert ("请填写观察员编号");
//		document.all["gcybh"].focus();
//		return false;
//	}
	if($("modi_flag")){
		document.all["modi_flag"].value="yes";
	}else if($("add_flag")){
			document.all["add_flag"].value="yes";
	}		
	underDealWith();
	refreshForm('/xgxt/xljk_xsxlgcy.do?act=xsxlgcy&doType=' + doType);
}
	