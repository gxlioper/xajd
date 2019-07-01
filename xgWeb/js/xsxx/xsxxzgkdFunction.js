function checkDoubleItem(){
	var tmp = "";	
	for (i = 0; i < document.forms[0].mappingList.options.length; i++) {
		tmp = document.forms[0].mappingList.options[i].value;
		if (document.forms[0].xsxxzd.options[document.forms[0].xsxxzd.selectedIndex].value == tmp) {
			return true;
		}
	}
}

function checkDoubleJtxx(){
	var tmp = "";	
	for (i = 0; i < document.forms[0].jtxxMappingList.options.length; i++) {
		tmp = document.forms[0].jtxxMappingList.options[i].value;
		if (document.forms[0].jtxxzd.options[document.forms[0].jtxxzd.selectedIndex].value == tmp) {
			return true;
		}
	}
}

/** 
 * 添加一个学生信息字段
 */
function addOneItemList() {
	var oracleListIndex = document.getElementById("xsxxzd").selectedIndex;
	if (oracleListIndex < 0 || document.forms[0].mappingList.options.length == document.forms[0].xsxxzd.options.length || checkDoubleItem()){
		return false;
	}	
	var addOracleV = document.forms[0].xsxxzd.options[oracleListIndex].value;
	var addOracleT = document.forms[0].xsxxzd.options[oracleListIndex].text;
	document.forms[0].mappingList.options[document.forms[0].mappingList.options.length] = new Option(addOracleT, addOracleV);
}

/**
*删除一个学生信息字段
*/
function deleteItemList() {
	var mappingListIndex = document.forms[0].mappingList.selectedIndex;
	if (mappingListIndex < 0) {
		return false;
	}
	document.forms[0].mappingList.options[mappingListIndex] = null;
}

/** 
 * 默认添加所有的学生信息字段
 */
function defaultAllItemList() {
	
	if (document.forms[0].mappingList.options.length > 0) {
		var obj = document.forms[0].mappingList;
		for(i=0;i<obj.options.length;i++){
			obj.options[i]=null;
			i--; 
		} 
	}
	var len = document.forms[0].xsxxzd.options.length;
	var i;
	var addOracleV;
	var addOracleT;
	for (i = 0; i < len; i++) {
		addOracleV = document.forms[0].xsxxzd.options[i].value;
		addOracleT = document.forms[0].xsxxzd.options[i].text;
		document.forms[0].mappingList.options[i] = new Option(addOracleT, addOracleV);
	}
}

/**
*删除所有的学生信息字段
**/
function clearList() {
	document.forms[0].mappingList.options.length = 0;
}

/** 
 * 添加一个学生家庭信息字段
 */
function addOneJtxxList() {
	var oracleListIndex = document.getElementById("jtxxzd").selectedIndex;
	if (oracleListIndex < 0 || document.forms[0].jtxxMappingList.options.length == document.forms[0].jtxxzd.options.length || checkDoubleJtxx()){
		return false;
	}	
	var addOracleV = document.forms[0].jtxxzd.options[oracleListIndex].value;
	var addOracleT = document.forms[0].jtxxzd.options[oracleListIndex].text;
	document.forms[0].jtxxMappingList.options[document.forms[0].jtxxMappingList.options.length] = new Option(addOracleT, addOracleV);
}

/**
*删除一个学生家庭信息字段
*/
function deleteJtxxList() {
	var mappingListIndex = document.forms[0].jtxxMappingList.selectedIndex;
	if (mappingListIndex < 0) {
		return false;
	}
	document.forms[0].jtxxMappingList.options[mappingListIndex] = null;
}

/** 
 * 默认添加所有的学生家庭信息字段
 */
function defaultAllJtxxList() {
	if (document.forms[0].jtxxMappingList.options.length > 0) {
		var obj = document.forms[0].jtxxMappingList;
		for(i=0;i<obj.options.length;i++){
			obj.options[i]=null;
			i--; 
		} 
	}
	var len = document.forms[0].jtxxzd.options.length;
	var i;
	var addOracleV;
	var addOracleT;
	for (i = 0; i < len; i++) {
		addOracleV = document.forms[0].jtxxzd.options[i].value;
		addOracleT = document.forms[0].jtxxzd.options[i].text;
		document.forms[0].jtxxMappingList.options[i] = new Option(addOracleT, addOracleV);
	}
}

/**
*删除所有的学生家庭信息字段
**/
function clearJtxxList() {
	document.forms[0].jtxxMappingList.options.length = 0;
}


function changeList(){
	var yhjs = document.getElementById("yhjs").value;
	if(document.getElementById("btn_add")){
		document.getElementById("btn_add").disabled = false;
	}
	//加载学生信息字段
	xsxxZgkd.getWhzdByYh(yhjs,"xsxxb",function(data){
		DWRUtil.removeAllOptions("mappingList");			
		DWRUtil.addOptions("mappingList",data,"en","cn");
	});
	
	//加载学生家庭信息字段	
	xsxxZgkd.getWhzdByYh(yhjs,"xsfzxxb",function(data){
		DWRUtil.removeAllOptions("jtxxMappingList");			
		DWRUtil.addOptions("jtxxMappingList",data,"en","cn");
	});
}

/**
*权限分配保存
**/
function zdwhCommit(){
	var xsxxzdLen = document.getElementById("mappingList").options.length;
	var jtxxzdLen = document.getElementById("jtxxMappingList").options.length;
 	var xsxxzd = "";
 	var jtxxzd = "";
 	var yhjs = document.getElementById("yhjs").value;
 	document.getElementById('btn_add').disabled=true;
 	if(yhjs==null || yhjs==""){
 		alert('请选择用户！');
 		return false;
 	}
 	for(var i=0; i<xsxxzdLen; i++){
 		xsxxzd += document.getElementById("mappingList").options[i].value + "!!";
 	}
 	for(var i=0; i<jtxxzdLen; i++){
 		jtxxzd += document.getElementById("jtxxMappingList").options[i].value + "!!";
 	}
 	refreshForm("xsxx_zgkd.do?method=powerSave&xsxxzd="+xsxxzd+"&jtxxzd="+jtxxzd);
}

function showColumns(){
	var zdList = document.getElementById("zdList").value;
	var zdColumn = zdList.split("!!");
	for(var i=0; i<zdColumn.length-1; i++){	
		if(document.getElementById(zdColumn[i])){
			document.getElementById(zdColumn[i]).disabled=false;
		}
				
		if(zdColumn[i]=="xb"){
			document.getElementById('xbn').disabled=false;
			document.getElementById('xbv').disabled=false;
		}
		if(zdColumn[i]=='xydm'){
			document.getElementById("xy").disabled=false;			
		}
		if(zdColumn[i]=='zydm'){
			document.getElementById("zy").disabled=false;
		}
		if(zdColumn[i]=='bjdm'){
			document.getElementById("bj").disabled=false;
		}			
	}
	if(document.getElementById('jt1')){
		document.getElementById('jt1').style.display="none";
	}
	if(document.getElementById('jt2')){
		document.getElementById('jt2').style.display="none";
	}
	if(document.getElementById('jt3')){
		document.getElementById('jt3').style.display="none";
	}
}


function initItem(){
	var xh = document.getElementById('xh').value;
	var str = "";
	//家庭信息
	var colList = ["xh"];
	getStuDetails.getAllOfInfo(xh,'view_xsjtxx',colList,function(data){
		if(data != null && data.length>0){
			document.getElementById('divJtxx').style.display = "";
			str += "jtxx-";
			document.getElementById('notnulltext').value = str;
		}else{			
			document.getElementById('divJtxx').style.display = "none";
		}
	});
	
	//-----------------党团建设-----------------divDtjs
		//党员信息
	getStuDetails.getDyxx(xh,function(data){
		if(data != null && data.length>0){
			document.getElementById('tbDyxx').style.display = "";
			str += "dyxx-";
			document.getElementById('notnulltext').value = str;
		}else{
			document.getElementById('tbDyxx').style.display = "none";
		}
	});
	
	var tableNames = ["view_zgkd_dyxx","view_zgkd_rdsq"];	
	getStuDetails.checkDisplay(tableNames,xh,function(data){
		if(data == true){
			document.getElementById('divDtjs').style.display = "";
			str += "dtjs-";
			document.getElementById('notnulltext').value = str;
		}else{
			document.getElementById('divDtjs').style.display = "none";
		}
	});
	
	//-------------评奖评优---------------divPjpy
	//奖学金记录
	getStuDetails.getAllOfInfo(xh,'view_xsjxjb',colList,function(data){
		if(data != null && data.length>0){
			document.getElementById('tbJxjjl').style.display = "";
			str += "jxj-";
			document.getElementById('notnulltext').value = str;
		}else{
			document.getElementById('tbJxjjl').style.display = "none";
		}
	});

	//荣誉称号记录
	getStuDetails.getAllOfInfo(xh,'view_xsrychb',colList,function(data){
		if(data != null && data.length>0){
			document.getElementById('tbRychjl').style.display = "";
			str += "rych-";
			document.getElementById('notnulltext').value = str;
		}else{
			document.getElementById('tbRychjl').style.display = "none";
		}
	});
	
	
	//综合素质测评
	getStuDetails.getAllOfInfo(xh,'view_zgkd_zhszcp',colList,function(data){
		if(data != null && data.length>0){
			document.getElementById('tbZhszcpjl').style.display = "";
			str += "zhszcp-";
			document.getElementById('notnulltext').value = str;
		}else{
			document.getElementById('tbZhszcpjl').style.display = "none";
		}
	});
	
	var tableNames = ["view_xsjxjb","view_xsrychb","view_zgkd_zhszcp"];	
	getStuDetails.checkDisplay(tableNames,xh,function(data){
		if(data == true){
			document.getElementById('divPjpy').style.display = "";
			str += "pjpy-";
			document.getElementById('notnulltext').value = str;
		}else{
			document.getElementById('divPjpy').style.display = "none";
		}
	});
	
	//------------------对外交流---------------
	getStuDetails.getAllOfInfo(xh,'view_dwjlsq',colList,function(data){
		if(data != null && data.length>0){		
			document.getElementById('divDwjl').style.display = "";
			str += "dwjl-";
			document.getElementById('notnulltext').value = str;
		}else{
			document.getElementById('divDwjl').style.display = "none";
		}
	});
	
	
	//-----------------学生资助------------
	getStuDetails.checkDisOfXszz(xh,function(data){
		if(data == true){		
			document.getElementById('divXszz').style.display = "";
			document.getElementById('divXszzxx').style.display = "";
			str += "xszz-";			
			document.getElementById('notnulltext').value = str;
		}else{
			document.getElementById('divXszz').style.display = "none";
			document.getElementById('divXszzxx').style.display = "none";
		}
	});
	
	//-----------------勤工助学------------
	//勤工助学记录
	getStuDetails.getAllOfInfo(xh,'view_xsgwxx',colList,function(data){
		if(data != null && data.length>0){		
			str += "qgzxjl-";
			document.getElementById('tbQgzxjl').style.display = "";
			document.getElementById('notnulltext').value = str;
		}else{
			document.getElementById('tbQgzxjl').style.display = "none";
		}
	});
	
	//酬金发放记录
	getStuDetails.getAllOfInfo(xh,'view_xscjff',colList,function(data){
		if(data != null && data.length>0){		
			document.getElementById('tbCjffjl').style.display = "";
			str += "cjff-";
			document.getElementById('notnulltext').value = str;
		}else{
			document.getElementById('tbCjffjl').style.display = "none";
		}
	});
	
	var tableNames = ["view_xsgwxx","view_xscjff"];	
	getStuDetails.checkDisplay(tableNames,xh,function(data){
		if(data == true){
			document.getElementById('divQgzx').style.display = "";
			str += "qgzx-";
			document.getElementById('notnulltext').value = str;
		}else{
			document.getElementById('divQgzx').style.display = "none";
		}
	});
	
	
    //----------------心理健康------------
    //心理测试结果记录
    if($('userType').value !='stu'){
	    getStuDetails.getAllOfInfo(xh,'view_xlcsjg',colList,function(data){
			if(data != null && data.length>0){
				document.getElementById('tbXlcsjgjl').style.display = "";
				str += "xlcs-";
				document.getElementById('notnulltext').value = str;
			}else{
				document.getElementById('tbXlcsjgjl').style.display = "none";
			}
		});
	}
	
	//特殊学生记录
	if($('userType').value !='stu'){
		getStuDetails.getAllOfInfo(xh,'view_xytbgxxsxx',colList,function(data){
			if(data != null && data.length>0){		
				document.getElementById('tbTsxxjl').style.display = "";
				str += "tsxs-";
				document.getElementById('notnulltext').value = str;
			}else{
				document.getElementById('tbTsxxjl').style.display = "none";
			}
		});
	}
//	
//	var tableNames = ["view_xlcsjg","view_xytbgxxsxx"];	
//	if($('divXljk')){
//		getStuDetails.checkDisplay(tableNames,xh,function(data){
//			if(data == true){
//				document.getElementById('divXljk').style.display = "";
//			}else{
//				document.getElementById('divXljk').style.display = "none";
//			}
//		});
//	}
	
	
	//---------------学生军训-----------
	//军训团队获奖记录
	getStuDetails.getAllOfInfo(xh,'view_xsjxhj',colList,function(data){
		if(data != null && data.length>0){		
			document.getElementById('tbJxtdhj').style.display = "";
			str += "jxtdhj-";
			document.getElementById('notnulltext').value = str;
		}else{
			document.getElementById('tbJxtdhj').style.display = "none";
		}
	});
	
	//军训成绩记录
	getStuDetails.getAllOfInfo(xh,'view_jxcjxx',colList,function(data){
		if(data != null && data.length>0){		
			document.getElementById('tbJxcjjl').style.display = "";
			str += "jxcj-";
			document.getElementById('notnulltext').value = str;
		}else{
			document.getElementById('tbJxcjjl').style.display = "none";
		}
	});
	
	var tableNames = ["view_xsjxhj","view_jxcjxx"];	
	getStuDetails.checkDisplay(tableNames,xh,function(data){
		if(data == true){
			str += "xsjx-";
			document.getElementById('divXsjx').style.display = "";
			document.getElementById('notnulltext').value = str;
		}else{
			document.getElementById('divXsjx').style.display = "none";
		}
	});
	
	//------------违纪处分-----------------
	//违纪处分记录
	getStuDetails.getAllOfInfo(xh,'view_wjcf',colList,function(data){
		if(data != null && data.length>0){		
			document.getElementById('divWjcf').style.display = "";
			str += "wjcf-";
			document.getElementById('notnulltext').value = str;
		}else{
			document.getElementById('divWjcf').style.display = "none";
		}
	});
	
	//---------------其他数据--------------
	//学生保险记录
	getStuDetails.getAllOfInfo(xh,'view_xsbxxx',colList,function(data){
		if(data != null && data.length>0){		
			document.getElementById('tbXsbxjl').style.display = "";
			str += "xsbx-";
			document.getElementById('notnulltext').value = str;
		}else{
			document.getElementById('tbXsbxjl').style.display = "none";
		}
	});

	//伙食消费记录
	getStuDetails.getAllOfInfo(xh,'view_xshsxf',colList,function(data){
		if(data != null && data.length>0){		
			document.getElementById('tbHsxfjl').style.display = "";
			str += "hsxf-";
			document.getElementById('notnulltext').value = str;
		}else{
			document.getElementById('tbHsxfjl').style.display = "none";
		}
	});
	
	var tableNames = ["view_xsbxxx","view_xshsxf"];	
	getStuDetails.checkDisplay(tableNames,xh,function(data){
		if(data == true){
			document.getElementById('divQtsj').style.display = "";
			str += "qtsj-";
			document.getElementById('notnulltext').value = str;
		}else{
			document.getElementById('divQtsj').style.display = "none";
		}
	});
	document.getElementById('notnulltext').value = str;
}


function getJtxxFoKydxInfo(){
	var xh = document.getElementById('xh').value;
	getStuDetails.getXsjtxx(xh,function(data){		
		document.getElementById("jtdz").innerText = data.jtdz;
		document.getElementById("jtyb").innerText = data.jtyb;
		document.getElementById("jtjjqk").innerText = data.jtjjqk;
		document.getElementById("jtcy1_xm").innerText = data.jtcy1_xm;
		document.getElementById("jtcy1_gx").innerText = data.jtcy1_gx;
		document.getElementById("jtcy1_csrq").innerText = data.jtcy1_csrq;
		document.getElementById("jtcy1_sfzh").innerText = data.jtcy1_sfzh;
		document.getElementById("jtcy1_mzmc").innerText = data.jtcy1_mzmc;
		document.getElementById("jtcy1_zzmmmc").innerText = data.jtcy1_zzmmmc;
		document.getElementById("jtcy1_zy").innerText = data.jtcy1_zy;
		document.getElementById("jtcy1_zw").innerText = data.jtcy1_zw;
		document.getElementById("jtcy1_lxdh1").innerText = data.jtcy1_lxdh1;
		document.getElementById("jtcy1_lxdh2").innerText = data.jtcy1_lxdh2;
		document.getElementById("jtcy1_gzdz").innerText = data.jtcy1_gzdz;
		document.getElementById("jtcy2_xm").innerText = data.jtcy2_xm;
		document.getElementById("jtcy2_gx").innerText = data.jtcy2_gx;
		document.getElementById("jtcy2_csrq").innerText = data.jtcy2_csrq;
		document.getElementById("jtcy2_sfzh").innerText = data.jtcy2_sfzh;
		document.getElementById("jtcy2_mzmc").innerText = data.jtcy2_mzmc;
		document.getElementById("jtcy2_zzmmmc").innerText = data.jtcy2_zzmmmc;
		document.getElementById("jtcy2_zy").innerText = data.jtcy2_zy;
		document.getElementById("jtcy2_zw").innerText = data.jtcy2_zw;
		document.getElementById("jtcy2_lxdh1").innerText = data.jtcy2_lxdh1;
		document.getElementById("jtcy2_lxdh2").innerText = data.jtcy2_lxdh2;
		document.getElementById("jtcy2_gzdz").innerText = data.jtcy2_gzdz;
		document.getElementById("jtcy3_xm").innerText = data.jtcy3_xm;
		document.getElementById("jtcy3_gx").innerText = data.jtcy3_gx;
		document.getElementById("jtcy3_csrq").innerText = data.jtcy3_csrq;
		document.getElementById("jtcy3_sfzh").innerText = data.jtcy3_sfzh;
		document.getElementById("jtcy3_mzmc").innerText = data.jtcy3_mzmc;
		document.getElementById("jtcy3_zzmmmc").innerText = data.jtcy3_zzmmmc;
		document.getElementById("jtcy3_zy").innerText = data.jtcy3_zy;
		document.getElementById("jtcy3_zw").innerText = data.jtcy3_zw;
		document.getElementById("jtcy3_lxdh1").innerText = data.jtcy3_lxdh1;
		document.getElementById("jtcy3_lxdh2").innerText = data.jtcy3_lxdh2;
		document.getElementById("jtcy3_gzdz").innerText = data.jtcy3_gzdz;
	});
}


//function printOne(){
//	var the_content = "<html>\n";
//	the_content += "<style>.noPrin{\n\n";
//	the_content += "display:block;}\n\n";
//	the_content += "</style>\n\n";
//	the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n\n";
//	the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n\n";
//	the_content += document.getElementById('htmltext').value;
//	the_content += "<script>document.getElementById('buttonSave').style.display='none';document.getElementById('buttonClose').style.display='none';</sctipt>\n";
//	the_content = the_content.replace("当前位置：学生详细信息查询","");
//	the_content = the_content.replace("</BODY>","");
//	the_content = the_content.replace("<DIV class=buttontool align=center><BUTTON class=button2 id=buttonSave style=\"WIDTH: 80px\" onclick=printOne()>打 印</BUTTON> &nbsp;&nbsp;&nbsp;&nbsp;<BUTTON class=button2 id=buttonClose style=\"WIDTH: 80px\" onclick=Close()>关 闭</BUTTON> </DIV>",'');
//	the_content += "<div class='noPrin'>\n";
//    the_content +="<input type='button' class='button2' value='页面设置' onclick=\"WebBrowser.ExecWB(8,1)\">\n";
//	the_content +="<input type='button' class='button2' value='打印预览' onclick=\"WebBrowser.ExecWB(7,1)\">\n";
//	the_content +="<input type='button' class='button2' value='直接打印' onclick=\"WebBrowser.ExecWB(6,6)\">\n";
//	the_content +="</div>\n";	
//	the_content += "</body></html>\n";
//	
//	var newwin = window.open("about:blank", "_blank", "");
//	newwin.document.open();
//	newwin.document.write(the_content);
//	document.getElementById('title_m').style.display = "none";
//	newwin.document.close();
//	newwin = null;
//}

function printOne(){
	var notnulltext = document.getElementById('notnulltext').value;
	var xh = document.getElementById('xh').value;
	
	showOpenWindow('xsxxgl.do?method=printXsxxxx&item=' + notnulltext + "&xh="+xh,800,600);
}