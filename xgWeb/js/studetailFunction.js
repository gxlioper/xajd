addAllRows = function(ele, data, cellFuncs, options) {
  ele = DWRUtil._getElementById(ele, "addRows()");
  if (ele == null) return;
  if (!DWRUtil._isHTMLElement(ele, ["table", "tbody", "thead", "tfoot"])) {
    DWRUtil._debug("addRows() can only be used with table, tbody, thead and tfoot elements. Attempt to use: " + dwr.util._detailedTypeOf(ele));
    return;
  }
  if (!options) options = {};
  if (!options.rowCreator) options.rowCreator = DWRUtil._defaultRowCreator;
  if (!options.cellCreator) options.cellCreator = DWRUtil._defaultCellCreator;
  var tr, rowNum;
  if (DWRUtil._isArray(data)) {
    for (rowNum = 0; rowNum < data.length; rowNum++) {
      options.rowData = data[rowNum];
      options.rowIndex = rowNum;
      options.rowNum = rowNum;
      options.data = null;
      options.cellNum = -1;
      tr = _addRowInnerText(data[rowNum], options);
      if (tr != null) ele.appendChild(tr);
    }
  }
  else if (typeof data == "object") {
    rowNum = 0;
    for (var rowIndex in data) {
      options.rowData = data[rowIndex];
      options.rowIndex = rowIndex;
      options.rowNum = rowNum;
      options.data = null;
      options.cellNum = -1;
      tr = _addRowInnerText(cellFuncs, options);
      if (tr != null) ele.appendChild(tr);
      rowNum++;
    }
  }

  DWRUtil.highlight(ele, options);
};

/**
 * @private Internal function to draw a single row of a table.
 */
_addRowInnerText = function(data, options) {
  //create  one row  
  var tr = options.rowCreator(options);
  if (tr == null) return null;
  if(data!=null && data!=""){
  	for (var cellNum = 0;cellNum<data.length;cellNum++) {  
		  	var _data = data[cellNum];
		  	if(_data==null || _data=='')   
		  		options.data=' ';
		  	else    
		  		options.data = _data;
		    options.cellNum = cellNum;
		    var td = options.cellCreator(options);
		    if (td != null) {
		      if (options.data != null) {
		        if (DWRUtil._isHTMLElement(options.data)) td.appendChild(options.data);
		        else {
		          if (DWRUtil._shouldEscapeHtml(options) && typeof(options.data) == "string") {
		            td.innerHTML = DWRUtil.escapeHtml(options.data);
		          }
		          else {
		            td.innerHTML = options.data;
		          }
		        }
		      }		      
		      tr.appendChild(td);
	    }    
	} 
}
return tr;
};


//培训
function getPxxxInfo(data){
	var cellMuster=[
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.xb},
		function(data){return data.nj},
		function(data){return data.xymc},
		function(data){return data.zymc},
		function(data){return data.bjmc},
		function(data){return data.pxcs}
	];
	if (data != null && typeof data == 'object') {
		if ($("xspxxx").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("xspxxx");			
			DWRUtil.addRows("xspxxx",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//入党积极分子
function getRdjjfzInfo(data){
	var cellMuster=[
		function(data){return data.nd},
		function(data){return data.xn},
		function(data){return data.xqmc},
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.nj},
		function(data){return data.bjmc},
		function(data){return data.kssj}		
	];
	if (data != null && typeof data == 'object') {
		if ($("rdjjfz").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("rdjjfz");
			DWRUtil.addRows("rdjjfz",data,cellMuster);
		}
	}else{
		alert("出错了!!");
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//预备党员
function getYbdyInfo(data){
	var cellMuster=[
		function(data){return data.nd},
		function(data){return data.xn},
		function(data){return data.xqmc},
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.nd},
		function(data){return data.bjmc},
		function(data){return data.kssj},		
		function(data){return data.jssj}
	];
	if (data != null && typeof data == 'object') {
		if ($("ybdy").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("ybdy");
			DWRUtil.addRows("ybdy",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//三江学院预备党员
function getSjxyYbdyInfo(data){
	var cellMuster=[
		function(data){return data.nd},
		function(data){return data.xn},
		function(data){return data.xq},
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.nd},
		function(data){return data.bjmc},
		function(data){return data.zzlx},
		function(data){return data.kssj},		
		function(data){return data.jssj}
	];
	if (data != null && typeof data == 'object') {
		if ($("ybdy").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("ybdy");
			DWRUtil.addRows("ybdy",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//党员
function getDyInfo(data){
	var cellMuster=[
		function(data){return data.nd},
		function(data){return data.xn},
		function(data){return data.xqmc},
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.nj},
		function(data){return data.bjmc},
		function(data){return data.rdsj}		
	];
	if (data != null && typeof data == 'object') {
		if ($("dy").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("dy");
			DWRUtil.addRows("dy",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//党员
function getSjxyDyInfo(data){
	var cellMuster=[
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.nj},
		function(data){return data.bjmc},
		function(data){return data.rdsj},
		function(data){return data.zzsj},
		function(data){return data.zzlx},
		function(data){return data.zzdw}
	];
	if (data != null && typeof data == 'object') {
		if ($("dy").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("dy");
			DWRUtil.addRows("dy",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

function getDyxxInfo(data){
	var cellMuster=[
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.rdsqsj},
		function(data){return data.qdwjjfzsj},
		function(data){return data.fzdxsj},
		function(data){return data.rdsj},
		function(data){return data.zzsj},
		function(data){return data.xzzt}
	];
	if (data != null && typeof data == 'object') {
		if ($("dyxx").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("dyxx");
			DWRUtil.addRows("dyxx",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//－－－－－－－－－思想教育－－－－－－－
function getSxjyInfo(){
	var xh = document.getElementById("xh").value;
	var xxdm = document.getElementById("xxdm").value;
	if(xxdm == "10290"){//中国矿业大学		
		//党员信息
		getStuDetails.getDyxx(xh,getDyxxInfo);
	}else{
		//学生培训信息
		var colList = ["xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "pxcs"];	
		getStuDetails.getAllOfInfo(xh,'view_czxx_dkpxmdb',colList,getPxxxInfo);
		
		//入党积极分子
		colList = ["nd", "xn", "xqmc", "xh", "xm", "nj", "bjmc","kssj"];
		getStuDetails.getAllOfInfo(xh,'view_rdjjfzxx',colList,getRdjjfzInfo);
		
		//预备党员
		colList = ["nd", "xn", "xqmc", "xh", "xm", "nj", "bjmc","kssj", "jssj"];
		if(xxdm == "11122"){//三江学院 
			colList = ["nd", "xn", "xqmc", "xh", "xm", "nj", "bjmc","zzlx","kssj", "jssj"];
			getStuDetails.getAllOfInfo(xh,'view_ybdyxx',colList,getSjxyYbdyInfo);
		}else{
			getStuDetails.getAllOfInfo(xh,'view_ybdyxx',colList,getYbdyInfo);
		}
		
		//党员
		colList = ["nd", "xn", "xqmc", "xh", "xm", "nj", "bjmc","rdsj"];
		if(xxdm == "11122"){//三江学院 
			colList = ["xh", "xm", "nj", "bjmc","rdsj","zzsj","zzlx","zzdw"];
			getStuDetails.getAllOfInfo(xh,'view_dyxx',colList,getSjxyDyInfo);
		}else{
			getStuDetails.getAllOfInfo(xh,'view_dyxx',colList,getDyInfo);
		}
	}
}


//奖学金
function getJxjInfo(data){
	var xxdm = document.getElementById("xxdm").value;
//	if(xxdm == "11551" || xxdm == "10878"){
//		//重庆科技学院，安徽建筑工业学院增加辅导员审核
//		var cellMuster=[
//			function(data){return data.nd},
//			function(data){return data.xn},
//			function(data){return data.xh},
//			function(data){return data.xm},
//			function(data){return data.bjmc},
//			function(data){return data.jxjmc},
//			function(data){return data.jlje},
//			function(data){return data.fdysh},
//			function(data){return data.xysh},
//			function(data){return data.xxsh}	
//		];
//	} else if (xxdm=='13022') {//宁波理工
//		var cellMuster=[
//			function(data){return data.xn},
//			function(data){return data.xh},
//			function(data){return data.xm},
//			function(data){return data.bjmc},
//			function(data){return data.jxjmc},
//			function(data){return data.jlje},
//			function(data){return data.xysh},
//			function(data){return data.xxsh}	
//		];
//	} else{
		var cellMuster=[
//			function(data){return data.nd},
//			function(data){return data.xn},
//			function(data){return data.xh},
//			function(data){return data.xm},
//			function(data){return data.bjmc},
//			function(data){return data.jxjmc},
//			function(data){return data.jlje},
//			function(data){return data.xysh},
//			function(data){return data.xxsh}
			function(data){
				var pjxn =data.pjxn;
				if(data.pjxq=='无'){
					pjxn+="";
				}else{
					pjxn+=data.pjxq;
				}
				if(data.pjnd=='无'){
					pjxn+="";
				}else{
					pjxn+=data.pjnd;
				}
				return pjxn},
			function(data){return data.xmmc},
			function(data){return data.xmje},
			function(data){return data.sqsj}
		];
//	}
	if (data != null && typeof data == 'object') {
		if ($("jxj").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("jxj");
			DWRUtil.addRows("jxj",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//荣誉称号
function getRychInfo(data){
	var xxdm = document.getElementById("xxdm").value;
	if(xxdm == "11551" || xxdm == "10878" || xxdm =="10827" || xxdm == "10419"){
		//重庆科技学院，安徽建筑工业学院增加辅导员审核
		var cellMuster=[
			function(data){return data.nd},
			function(data){return data.xn},
			function(data){return data.xh},
			function(data){return data.xm},
			function(data){return data.bjmc},
			function(data){return data.rychmc},
			function(data){return data.fdysh},
			function(data){return data.xysh},
			function(data){return data.xxsh}		
		];
	}else if (xxdm=='13022') {//宁波理工
		var cellMuster=[
			function(data){return data.xn},
			function(data){return data.xh},
			function(data){return data.xm},
			function(data){return data.bjmc},
			function(data){return data.rychmc},
			function(data){return data.xysh},
			function(data){return data.xxsh}		
		];
	}else{
		var cellMuster=[
			function(data){return data.nd},
			function(data){return data.xn},
			function(data){return data.xh},
			function(data){return data.xm},
			function(data){return data.bjmc},
			function(data){return data.rychmc},
			function(data){return data.xysh},
			function(data){return data.xxsh}		
		];
	}
	if (data != null && typeof data == 'object') {
		if ($("rych").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("rych");
			DWRUtil.addRows("rych",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//纪实综合考评记录
function getJszhkpInfo(data){
	var cellMuster=[
		function(data){return data.nd},
		function(data){return data.xn},
		function(data){return data.xq},
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.sxpdpj},
		function(data){return data.zssppj},
		function(data){return data.xynlpj},
		function(data){return data.nlpj},
		function(data){return data.szpj},
		function(data){return data.xf}		
	];
	if (data != null && typeof data == 'object') {
		if ($("jszhkp").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("jszhkp");
			DWRUtil.addRows("jszhkp",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}
var divHtml;
var datanum;
//综合素质测评
function getZhszcpInfo(data){
	var xh = document.getElementById("xh").value;
	dwr.engine.setAsync(false);
	
	if (data != null && typeof data == 'object') {
		if ($("zhszcp").tagName.toLowerCase() =="thead"){
			divHtml = "<div id=\"zhszcp_div\">"
				divHtml+= "<span class=\"formbox\">";
			for(var i=0;i<data.length;i++){
					divHtml+= "<table class=\"dateline\" width=\"100%\" id=\"showDiary\">";
					
					//-------------标题-------------
					divHtml+= "<thead>";
					divHtml+= "<tr>";
					divHtml+= "<td colspan=\"40\">"+data[i].xn.toString()+"学年"+"</td>";
					divHtml+= "</tr>";
					divHtml+= "<tr>";
					divHtml+= "<td>学期</td>";
					colList = ["xmmc"];
					//获取项目名称
					getStuDetails.getAllOfInfo(data[i].xn.toString(),'xg_pjpy_zcxmb',colList,getZhszcpInfoxn);
					divHtml+= "<td>班级排名</td>";
					divHtml+= "</tr>";
					divHtml+= "</thead>";
					//-------------主体-------------
					divHtml+= "<tbody>";
					//colList2 = ["xq","nd","zd1","zd2","zd3","zd4","zd5","zcfbjpm"];
					var colList2=["xq","zcfbjpm"];
					//获取项目名称
					var str = xh+","+data[i].xn.toString();
					getStuDetails.getAllOfInfo(str,'xg_pjpy_zhcpb2',colList2,getZhszcpInfosj);
					divHtml+= "</tbody>";
					divHtml+= "</table>";
					divHtml+= "<br></br>";
					
			}
			$("zhszcp_div").outerHTML=divHtml;
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
	
	dwr.engine.setAsync(true);
}
//综合素质测评
function getZhszcpInfoxn(data){
	if (data != null && typeof data == 'object') {
		datanum=data.length;
		if(data.length==0){
			divHtml+= "<td>综测总分</td>";
			divHtml+= "<td>德育分</td>";
			divHtml+= "<td>智育分</td>";
			divHtml+= "<td>体育分</td>";
			divHtml+= "<td>能力素质分</td>";
		}else{
			for(var i=0;i<data.length;i++){
				//alert(data[i].xn.toString());
				divHtml+= "<td>"+data[i].xmmc.toString()+"</td>";
			}
		}
		
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
	
}

function getZhszcpInfosj(data){
	if (data != null && typeof data == 'object') {
		datanum=data.length;
		for(var i=0;i<data.length;i++){
		
			var xn=data[i]["xn"];
			
			divHtml+= "<tr>";
			
			divHtml+= "<td>"+data[i].xq+"</td>";
			
			var colList=new Array();
			
			colList=["xmdm","xmmc"];
			
			getStuDetails.getAllOfInfo(xn,'xg_pjpy_zcxmb',colList,function(result){
				
				for(var j=0;j<result.length;j++){
					
					if(data[i][result[j].xmdm]==null){
						divHtml+= "<td>&nbsp;</td>";
					}else{
						divHtml+= "<td>"+data[i][result[j].xmdm]+"</td>";
					}
				}
			});
			
			divHtml+= "<td>"+data[i]["zcfbjpm"]+"</td>";
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
	
}

//学生竞赛获奖情况
function getXxjshjInfo(data){
	var cellMuster=[
    	function(data){return data.xn},
		function(data){return data.nd},	
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.hjsj},
		function(data){return data.xxjsmc},
		function(data){return data.xxjsdf}
	];
	if (data != null && typeof data == 'object') {
		if ($("xxjshj").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("xxjshj");
			DWRUtil.addRows("xxjshj",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//体育达标情况
function getTydbqk(data){
	var cellMuster=[
    	function(data){return data.xn},
		function(data){return data.xq},	
		function(data){return data.tydb},
		function(data){return data.bz}
	];
	if (data != null && typeof data == 'object') {
		if ($("tydbqk").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("tydbqk");
			DWRUtil.addRows("tydbqk",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//学生考勤情况
function getXskqb(data){
	var cellMuster=[
    	function(data){return data.xn},
		function(data){return data.xq},	
		function(data){return data.kksj},
		function(data){return data.kkdd},
    	function(data){return data.kkjl}
	];
	if (data != null && typeof data == 'object') {
		if ($("xskqb").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("xskqb");
			DWRUtil.addRows("xskqb",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}
//-----------------评奖评优---------------
function getPjpyInfo(){
	var xh = document.getElementById("xh").value;
	var xxdm = document.getElementById("xxdm").value;
	//奖学金
//	if(xxdm == "11551" || xxdm == "10878" || xxdm == "12764"){
//		//重庆科技学院，安徽建筑工业学院,四川建筑职业技术学院增加辅导员审核
//		var colList = ["nd", "xn", "xh", "xm", "bjmc", "jxjmc","jlje","fdysh","xysh", "xxsh"];
//	} else if (xxdm=='13022') {//宁波理工
//		var colList = ["xn", "xh", "xm", "bjmc", "jxjmc","jlje","xysh", "xxsh"];
//	} else if(xxdm=='12872'){//杭州职业技术学院
//		var colList = ["nd", "xn", "xh", "xm", "bjmc", "jxjmc","jlje","xysh"];
//	}else{	
		var colList = [ "pjxn", "pjxq","pjnd","xmmc","xmje", "sqsj"];		
//	}
	//原评奖
//	getStuDetails.getAllOfInfo(xh,'view_xsjxjb',colList,getJxjInfo);
	getStuDetails.getAllOfInfo(xh,'xg_view_pjpy_xstgjl',colList,getJxjInfo);
	//荣誉称号
//	var table = "view_xsrychb";
//	if(xxdm == "11551" || xxdm == "10878" || xxdm =="10827" || xxdm == "10419"){
//		//重庆科技学院，安徽建筑工业学院增加辅导员审核
//		colList = ["nd", "xn", "xh", "xm", "bjmc", "rychmc","fdysh","xysh", "xxsh"];
//	} else if (xxdm=='13022') {//宁波理工
//		colList = ["xn", "xh", "xm", "bjmc", "rychmc","xysh", "xxsh"];
//	} else if(xxdm=='12872'){//杭州职业技术学院
//		colList = ["nd", "xn", "xh", "xm", "bjmc", "rychmc","xysh"];
//	} else{	
//		colList = ["nd", "xn", "xh", "xm", "bjmc", "rychmc","xysh", "xxsh"];
//	}
//	if(xxdm == "13742"){//宁波天一
//		table = "view_nbty_xsrychb";
//	}
//	
//	getStuDetails.getAllOfInfo(xh,table,colList,getRychInfo);
//	
//	//纪实综合考评记录
//	if($('jszhkp')){
//		if(xxdm!="12872"){
//			//非杭州职业技术学院		
//			colList = ["nd","xn","xq","xh","xm","sxpdpj","zssppj","xynlpj","nlpj","szpj","xf"];
//			getStuDetails.getAllOfInfo(xh,'view_jszhkp',colList,getJszhkpInfo);
//		}
//	}
	
	//综合素质测评
	if($('zhszcp')){
//		if(xxdm=="10822"){//广东白云学院
//			colList = ["nd", "xn", "xq", "xh", "xm", "bjmc","cxcj"];
//			getStuDetails.getAllOfInfo(xh,'view_zhszcp',colList,function(data){
//				var cellMuster=[
//					function(data){return data.nd},
//					function(data){return data.xn},
//					function(data){return data.xq},
//					function(data){return data.xh},
//					function(data){return data.xm},
//					function(data){return data.bjmc},
//					function(data){return data.cxcj}
//					];
//				if (data != null && typeof data == 'object') {
//					if ($("zhszcp").tagName.toLowerCase() =="tbody"){
//						DWRUtil.removeAllRows("zhszcp");
//						DWRUtil.addRows("zhszcp",data,cellMuster);
//					}
//				}else{
//					showMsgWin("有错误出现：远程数据读取失败！");
//				}
//			});
//		}else if(xxdm=="12861"){//浙江机电
//			colList = ["nd", "xn", "xq", "xh", "xm", "bjmc","dyzf","zyzf","tyzf","zhszcpzf"];
//			getStuDetails.getAllOfInfo(xh,'view_zjjd_zhszcp',colList,function(data){
//				var cellMuster=[
//					function(data){return data.nd},
//					function(data){return data.xn},
//					function(data){return data.xq},
//					function(data){return data.xh},
//					function(data){return data.xm},
//					function(data){return data.bjmc},
//					function(data){return data.dyzf},
//					function(data){return data.zyzf},
//					function(data){return data.tyzf},
//					function(data){return data.zhszcpzf}
//					];
//				if (data != null && typeof data == 'object') {
//					if ($("zhszcp").tagName.toLowerCase() =="tbody"){
//						DWRUtil.removeAllRows("zhszcp");
//						DWRUtil.addRows("zhszcp",data,cellMuster);
//					}
//				}else{
//					showMsgWin("有错误出现：远程数据读取失败！");
//				}
//			});
//		}else if(xxdm=="12872"){//杭州职业
//			colList = ["nd", "xn", "xq", "xh", "xm", "bjmc","xydykpf","zcj","tcj","gzxxcx","gydykpf","zhszcpzf"];
//			getStuDetails.getAllOfInfo(xh,'view_zhszcp',colList,function(data){
//				var cellMuster=[
//					function(data){return data.nd},
//					function(data){return data.xn},
//					function(data){return data.xq},
//					function(data){return data.xh},
//					function(data){return data.xm},
//					function(data){return data.bjmc},
//					function(data){return data.xydykpf},
//					function(data){return data.zcj},
//					function(data){return data.tcj},
//					function(data){return data.gzxxcx},
//					function(data){return data.gydykpf},
//					function(data){return data.zhszcpzf}
//					];
//				if (data != null && typeof data == 'object') {
//					if ($("zhszcp").tagName.toLowerCase() =="tbody"){
//						DWRUtil.removeAllRows("zhszcp");
//						DWRUtil.addRows("zhszcp",data,cellMuster);
//					}
//				}else{
//					showMsgWin("有错误出现：远程数据读取失败！");
//				}
//			});
//		}else if(xxdm == '10863'){
//		    //宁波职业
//			colList = ["xn", "zysyf", "zyjnsyf", "kcxfzsyf", "zhcpzf","zypm", "zhpm"];
//			getStuDetails.getNbzyZhszcp(xh,colList,function(data){
//				var cellMuster=[
//					function(data){return data.xn},
//					function(data){return data.zysyf},
//					function(data){return data.zyjnsyf},
//					function(data){return data.kcxfzsyf},
//					function(data){return data.zhcpzf},
//					function(data){return data.zypm},
//					function(data){return data.zhpm}
//					];
//				if (data != null && typeof data == 'object') {
//					if ($("zhszcp").tagName.toLowerCase() =="tbody"){
//						DWRUtil.removeAllRows("zhszcp");
//						DWRUtil.addRows("zhszcp",data,cellMuster);
//					}
//				}else{
//					showMsgWin("有错误出现：远程数据读取失败！");
//				}
//			});
//		}else if(xxdm == '10290'){//中国矿业大学
//			colList = ["xn","xh","xm","pdcpdf","kcxxcjdf","kpm","sxcpdf","jbszcpdf","jpm","jbszcpdj","fzszcpdf","fpm"];
//			getStuDetails.getZhszcpOfZgkydx(xh,colList,function(data){
//				var cellMuster=[					
//					function(data){return data.xn},
//					function(data){return data.xh},
//					function(data){return data.xm},
//					function(data){return data.pdcpdf},
//					function(data){return data.kcxxcjdf},
//					function(data){return data.kpm},
//					function(data){return data.sxcpdf},
//					function(data){return data.jbszcpdf},
//					function(data){return data.jpm},
//					function(data){return data.jbszcpdj},
//					function(data){return data.fzszcpdf},
//					function(data){return data.fpm}
//					];
//				if (data != null && typeof data == 'object') {
//					if ($("zhszcp").tagName.toLowerCase() =="tbody"){
//						DWRUtil.removeAllRows("zhszcp");
//						DWRUtil.addRows("zhszcp",data,cellMuster);
//					}
//				}else{
//					showMsgWin("有错误出现：远程数据读取失败！");
//				}
//			});
//		}else if(xxdm=="12809"){//苏州工业园区职业技术学院
//			colList = ["xn", "xqmc", "WSMKF", "SHSJ", "ZZNL","DSHDF","IVTLT","WTHD","YYBDF","XTHD","ZHSZZF","ZHSZPM"];
//			getStuDetails.getAllOfInfo(xh,'view_szgy_zhszcp',colList,function(data){
//				var cellMuster=[
//					function(data){return data.xn},
//					function(data){return data.xqmc},
//					function(data){return data.WSMKF},
//					function(data){return data.SHSJ},
//					function(data){return data.ZZNL},
//					function(data){return data.DSHDF},
//					function(data){return data.IVTLT},
//					function(data){return data.WTHD},
//					function(data){return data.YYBDF},
//					function(data){return data.XTHD},
//					function(data){return data.ZHSZZF},
//					function(data){return data.ZHSZPM}
//					];
//				if (data != null && typeof data == 'object') {
//					if ($("zhszcp").tagName.toLowerCase() =="tbody"){
//						DWRUtil.removeAllRows("zhszcp");
//						DWRUtil.addRows("zhszcp",data,cellMuster);
//					}
//				}else{
//					showMsgWin("有错误出现：远程数据读取失败！");
//				}
//			});
//		}else{		//原综合	
//			colList = ["nd", "xn", "xq", "xh", "xm", "bjmc","dcj","zcj","tcj","znszcj","zhszcpzf","bjpm","njpm"];
//			getStuDetails.getAllOfInfo(xh,'view_zhszcp',colList,getZhszcpInfo);
		colList = ["xn"];
		getStuDetails.getAllOfInfo(xh,'xg_pjpy_zhcpb',colList,getZhszcpInfo);
		
//		}
	}
	//安徽建工增加学习竞赛情况
//	if(xxdm=="10878"){
//		colList = ["xn","nd","xh","xm","hjsj","xxjsmc","xxjsdf"];
//		getStuDetails.getAllOfInfo(xh,'view_xsxxjs',colList,getXxjshjInfo);		
//	}
//	
//	//中国美院
//	if(xxdm == "10355"){	
//		//体育达标情况
//		colList = ["xn","xq","tydb","bz"];
//		getStuDetails.getAllOfInfo(xh,'pjpy_tydbqkb',colList,getTydbqk);
//		
//		//考勤信息
//		colList = ["xn","xq","kksj","kkdd","kkjl"];
//		getStuDetails.getAllOfInfo(xh,'pjpy_xskqb',colList,getXskqb);	
//		
//	}
}

//-----------------西昌学生资助---------------
function getXcxyXszzInfo(){
	var xh = document.getElementById("xh").value;	
	var colList = [ "xn", "xh", "xm", "xymc","zymc","bjmc", "xmmc","zzje"];			
	getStuDetails.getAllOfInfo(xh,'xcxyxszz',colList,getXcxyZzInfo);
}

function getXcxyZzInfo(data){
	var cellMuster=[
		function(data){return data.xn},
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.xymc},
		function(data){return data.zymc},
		function(data){return data.bjmc},
		function(data){return data.xmmc},
		function(data){return data.zzje}
	];
	if (data != null && typeof data == 'object') {
		if ($("xszz").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("xszz");
			DWRUtil.addRows("xszz",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

function getXskccj(data){
	var xxdm = document.getElementById("xxdm").value;
	var cellMuster = [];
	if(xxdm=='10649'){
		//乐山师范学院
		cellMuster=[
    		function(data){return data.xn},
    		function(data){return data.xq},
    		function(data){return data.kcmc},
    		function(data){return data.kclx},
    		function(data){return data.bkcj},
    		function(data){return data.cxcj},
    		function(data){return data.cj}
    	];
	}else{
		cellMuster=[
    		function(data){return data.xn},
    		function(data){return data.xq},
    		function(data){return data.kcmc},
    		function(data){return data.kclx},
    		function(data){return data.bkcj2},
    		function(data){return data.cxcj2},
    		function(data){return data.zpcj2}
    	];
	}
	if (data != null && typeof data == 'object') {
		if ($("xskccj").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("xskccj");
			DWRUtil.addRows("xskccj",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//学生课程成绩
function getXskccjInfo(){
	var xh = document.getElementById("xh").value;
	var xxdm = document.getElementById("xxdm").value;
	var colList = [];
	if(xxdm=='10649'){
		colList = ["xn", "xq", "kcmc", "kclx", "bkcj", "cxcj","cj"];
	}else{
	//限定课程成绩信息
		colList = ["xn", "xq", "kcmc", "kclx", "bkcj2", "cxcj2","zpcj2"];
	}
	getStuDetails.getAllOfInfo(xh,'cjb',colList,getXskccj);
}


//对外交流及奖学金
function getDwjljjxjInfo(data){
	var cellMuster=[
		function(data){return data.nd},
		function(data){return data.xn},
		function(data){return data.xq},
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.dwjlxmmc},
		function(data){return data.sqsj},
		function(data){return data.xysh},
		function(data){return data.xxsh},
		function(data){return data.xxzs},
		function(data){return data.jxj}
	];
	if (data != null && typeof data == 'object') {
		if ($("dwjljjxj").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("dwjljjxj");
			DWRUtil.addRows("dwjljjxj",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//---------------对外交流--------------
function getDwjlInfo(){
	var xh = document.getElementById("xh").value;
	var xxdm = document.getElementById("xxdm").value;
	//对外交流及奖学金
	var colList = ["nd", "xn", "xq", "xh", "xm", "dwjlxmmc","sqsj", "xysh", "xxsh", "xxzs", "jxj"];
	if(xxdm == "12872"){//杭州职业技术学院
		colList = ["nd", "xn", "xq", "xh", "xm", "dwjlxmmc","sqsj", "xysh", "xxzs", "jxj"];
	}
	getStuDetails.getAllOfInfo(xh,'view_dwjlsq',colList,getDwjljjxjInfo);
}

//资助表
function getXszzTab(data){
	var cellMuster=[
		function(data){return data.nd},
		function(data){return data.xn},
		function(data){return data.xq},
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.dwjlxmmc},
		function(data){return data.sqsj},
		function(data){return data.xysh},
		function(data){return data.xxsh},
		function(data){return data.xxzs},
		function(data){return data.jxj}
	];
	if (data != null && typeof data == 'object') {
		if ($("dwjljjxj").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("dwjljjxj");
			DWRUtil.addRows("dwjljjxj",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}
var maxlength = 1;
//---------------学生资助--------------
function getXszzInfo(obj){
	var xh = document.getElementById("xh").value;
	var xxdm = document.getElementById("xxdm").value;
	var index = obj;
	var table = document.getElementById("tab"+index).value;
	var col = null;
	var cellMuster= null;
	var colLen = 0;
	getStuDetails.getXszzInfo(xh,xxdm,table,function(data){		
		colLength = data[0].length;
		getStuDetails.getColOfXszz(xxdm,table,function(colObj){
			col=colObj;			
			if (data != null && typeof data == 'object') {
				if ($("xszz"+index).tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("xszz"+index);
					addAllRows("xszz"+index,data,col);
					if(maxlength<colLength){
						maxlength = colLength;
					}
					
					for(var i=0;i<document.getElementsByName("maxLength")[0].value;i++){
						document.getElementById("td"+i).colSpan = maxlength;		
					}
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
		});	
	});
}

//---------------就业管理--------------
function getJyglInfo(obj){
	var xh = document.getElementById("xh").value;
	var xxdm = document.getElementById("xxdm").value;
	var index = obj;
	var table = document.getElementById("jytab"+index).value;
	var col = null;
	var cellMuster= null;
	var colLen = 0;
	getStuDetails.getXszzInfo(xh,xxdm,table,function(data){		
		colLength = data[0].length;
		getStuDetails.getColOfXszz(xxdm,table,function(colObj){
			col=colObj;			
			if (data != null && typeof data == 'object') {
				if ($("jygl"+index).tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("jygl"+index);
					addAllRows("jygl"+index,data,col);
					if(maxlength<colLength){
						maxlength = colLength;
					}
					
					for(var i=0;i<document.getElementsByName("jymaxLength")[0].value;i++){
						document.getElementById("jygltd"+i).colSpan = maxlength;	
					}
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
		});	
	});
}


//勤工助学
function getXsqgzxInfo(data){
	var cellMuster=[
		function(data){return data.nd},
		function(data){return data.xn},
		function(data){return data.xq},
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.bjmc},
		function(data){return data.gwdm},		
		function(data){return data.sqsj}
	];
	if (data != null && typeof data == 'object') {
		if ($("xsqgzx").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("xsqgzx");
			DWRUtil.addRows("xsqgzx",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}
//学生酬金发放
function getXscjffInfo(data){
	var cellMuster=[
	function(data){return data.nd},
	function(data){return data.xn},
	function(data){return data.xq},
	function(data){return data.yf},
	function(data){return data.xh},
	function(data){return data.xm},
	function(data){return data.bjmc},
	function(data){return data.gwdm},
	function(data){return data.cjje},
	function(data){return data.ffsj}
	];
	if (data != null && typeof data == 'object') {
		if ($("cjff").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("cjff");
			DWRUtil.addRows("cjff",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}
//--------------勤工助学-----------------
function getQgzxInfo(){
	var xh = document.getElementById("xh").value;
	//勤工助学
	var colList = ["nd", "xn", "xq", "xh", "xm", "bjmc", "gwdm","sqsj"];
	getStuDetails.getAllOfInfo(xh,'view_xsgwxx',colList,getXsqgzxInfo);	
	//酬金发放
	var colList = ["nd", "xn", "xq", "yf","xh", "xm","bjmc","gwdm","cjje","ffsj"];
	getStuDetails.getAllOfInfo(xh,'view_xscjff',colList,getXscjffInfo);
}

//心理测试
function getXlcsInfo(data){
	var xxdm = document.getElementById("xxdm").value;
	var cellMuster=[
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.xlcsxmmc},
		function(data){return data.xlcsjgmc},
		function(data){return data.cssj},
		function(data){return data.fsbj}
	];
	if(xxdm == "10290"){//中国矿业
		cellMuster=[
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.xlcsxmmc},
		function(data){return data.xlcsjgmc},
		function(data){return data.cssj}
	];
	}
	if(xxdm == "10344"){//浙江中医药大学
		cellMuster=[
		function(data){return data.xn},
		function(data){return data.xqmc},
		function(data){return data.sbsj},
		function(data){return data.zywt},
		function(data){return data.brth},
		function(data){return data.lxjs},
		function(data){return data.bz}
	];
	}
	if (data != null && typeof data == 'object') {
		if ($("xlcs").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("xlcs");
			DWRUtil.addRows("xlcs",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//特殊学生
function getTsxsInfo(data){
	var cellMuster=[
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.tbgxxslbmc}
	];
	if (data != null && typeof data == 'object') {
		if ($("tsxs").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("tsxs");
			DWRUtil.addRows("tsxs",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//--------------心理健康------------------
function getXljkInfo(){
		var xxdm = document.getElementById("xxdm").value;
	var xh = document.getElementById("xh").value;
	//心理测试
	var colList = ["xh", "xm","xlcsxmmc","xlcsjgmc", "cssj", "fsbj"];
	if(xxdm == "10290"){//中国矿业大学 
		colList = ["xh", "xm","xlcsxmmc","xlcsjgmc", "cssj"];
	}else if(xxdm == "10344"){//浙江中医药大学
		colList = ["xn", "xqmc","sbsj","zywt","brth","lxjs","bz"];
	}
	if(xxdm == "10344"){//浙江中医药大学
		getStuDetails.getAllOfInfo(xh,'XG_XLJK_ZJZYY_TSXSXXB',colList,getXlcsInfo);
	}else{//非浙江中医药大学
		getStuDetails.getAllOfInfo(xh,'view_xlcsjg',colList,getXlcsInfo);
	
		//特殊学生
		colList = ["xh", "xm", "tbgxxslbmc"];
		getStuDetails.getAllOfInfo(xh,'view_xytbgxxsxx',colList,getTsxsInfo);
	}
}

//军训获奖
function getJxhjInfo(data){
	var cellMuster=[
		function(data){return data.nd},
		function(data){return data.xn},
		function(data){return data.xq},
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.bjmc},
		function(data){return data.jxmc},
		function(data){return data.hjsj}
	];
	if (data != null && typeof data == 'object') {
		if ($("jxhj").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("jxhj");
			DWRUtil.addRows("jxhj",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//军训成绩
function getJxcjInfo(data){
	var cellMuster=[
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.nd},
		function(data){return data.bjmc},
		function(data){return data.mzmc},
		function(data){return data.xlcj},
		function(data){return data.kscj},
		function(data){return data.cj},
		function(data){return data.lrrq},
		function(data){return data.lrr}
	];
	if (data != null && typeof data == 'object') {
		if ($("jxcj").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("jxcj");
			DWRUtil.addRows("jxcj",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//----------------学生军训---------------
function getXsjxInfo(){
	var xh = document.getElementById("xh").value;
	//军训获奖
	var colList = ["nd", "xn", "xq", "xh", "xm", "bjmc", "jxmc","hjsj"];
	getStuDetails.getAllOfInfo(xh,'view_xsjxhj',colList,getJxhjInfo);
	//军训成绩
	var colList = ["xh","xm","nd","bjmc","mzmc","xlcj","kscj","cj","lrsj","lrr"];
	getStuDetails.getAllOfInfo(xh,'view_jxcjxx',colList,getJxcjInfo);
}

//----------------湖州师范----------------
function getXsjxInfoByHzsfxy(){
	var xh = document.getElementById("xh").value;
	//所属连队
	var colList = ["xn", "mc1", "mc2", "mc3", "bjmc"];
	getStuDetails.getAllOfInfo(xh,'view_jxgl_jxmdb',colList,getSsldInfo);
	//个人荣誉
	var colList = ["xn", "grrymc"];
	getStuDetails.getAllOfInfo(xh,'view_xg_jxgl_hzsf_grryhjb',colList,getGrryInfo);
	//团队荣誉
	var colList = ["xn", "bzmc", "bzjbmc", "tdrymc"];
	getStuDetails.getAllOfInfo(xh,'view_xg_jxgl_tdryhjb',colList,getTdryInfo);
}

//所属连队
function getSsldInfo(data){
	var cellMuster=[
		function(data){return data.xn},
		function(data){return data.mc1},
		function(data){return data.mc2},
		function(data){return data.mc3},
		function(data){return data.bjmc}
	];
	if (data != null && typeof data == 'object') {
		if ($("ssld").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("ssld");
			DWRUtil.addRows("ssld",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//个人荣誉
function getGrryInfo(data){
	var cellMuster=[
		function(data){return data.xn},
		function(data){return data.grrymc}
	];
	if (data != null && typeof data == 'object') {
		if ($("grry").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("grry");
			DWRUtil.addRows("grry",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//团队荣誉
function getTdryInfo(data){
	var cellMuster=[
		function(data){return data.xn},
		function(data){return data.bzmc},
		function(data){return data.bzjbmc},
		function(data){return data.tdrymc}
	];
	if (data != null && typeof data == 'object') {
		if ($("tdry").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("tdry");
			DWRUtil.addRows("tdry",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}




//违纪处分
function getXswjcfInfo(data){
	
	var cellMuster=[
		function(data){return data.nd},
		function(data){return data.xn},
		function(data){return data.xq},
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.cflbmc},
		function(data){return data.cfyymc},
		function(data){return data.cfsj},
		function(data){return data.cfwh},
		function(data){return data.ssjg},
		function(data){return data.cxsj}
	];
	
	if (data != null && typeof data == 'object') {
		
		if ($("wjcfList").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("wjcfList");
			DWRUtil.addRows("wjcfList",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}



//中国矿业大学
function getXswjcfInfoOfZgkd(data){
	var cellMuster=[
		function(data){return data.nd},
		function(data){return data.xn},
		function(data){return data.xq},
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.cflbmc},
		function(data){return data.cfyymc},
		function(data){return data.cfsj},
		function(data){return data.cfwh}
	];
	if (data != null && typeof data == 'object') {
		if ($("wjcfList").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("wjcfList");
			DWRUtil.addRows("wjcfList",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//----------------违纪处分---------------
function getWjcfInfo(){	
	var xh = document.getElementById("xh").value;
	var xxdm = document.getElementById("xxdm").value;
	//违纪处分
	var colList = ["xh", "xm", "xn", "nd", "xq", "cflbmc","cfyymc","cfsj", "cfwh", "ssjg", "cxsj"];
	if(xxdm == "10290"){
		getStuDetails.getAllOfInfo(xh,'view_wjcf',colList,getXswjcfInfoOfZgkd);	
	}else{
		getStuDetails.getAllOfInfo(xh,'view_wjcf',colList,getXswjcfInfo);	
	}
}



//保险信息
function getBxxxInfo(data){
	var cellMuster=[
		function(data){return data.nd},
		function(data){return data.xn},
		function(data){return data.xq},
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.bxgsmc},
		function(data){return data.bxxzmc},
		function(data){return data.tbsj},
		function(data){return data.tuibsj},
		function(data){return data.tbbj}
	];
	if (data != null && typeof data == 'object') {
		if ($("bxxx").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("bxxx");
			DWRUtil.addRows("bxxx",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//伙食消费
function getHsxfInfo(data){
	var cellMuster=[
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.nf},
		function(data){return data.yf},
		function(data){return data.xfje}
	];
	if (data != null && typeof data == 'object') {
		if ($("hsxf").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("hsxf");
			DWRUtil.addRows("hsxf",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//-----------------其他数据--------------
function getQtsjInfo(){
	var xxdm = document.getElementById("xxdm").value;
	var xh = document.getElementById("xh").value;
	//保险
	var colList = ["nd","xn","xq", "xh", "xm", "bxgsmc", "bxxzmc", "tbsj", "tuibsj", "tbbj"];
	getStuDetails.getAllOfInfo(xh,'view_xsbxxx',colList,getBxxxInfo);	
	if(xxdm !="12872"){
		//杭州职业技术学院
		//伙食消费
		colList = ["xh", "xm", "nf", "yf", "xfje"];
		getStuDetails.getAllOfInfo(xh,'view_xshsxf',colList,getHsxfInfo);
	}
}

//家庭信息
function getXsjtxxInfo(data){
	var cellMuster=[
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.jtcy1_xm},
		function(data){return data.jtcy1_gx},
		function(data){return data.jtcy2_xm},
		function(data){return data.jtcy2_gx},
		function(data){return data.jtcy3_xm},
		function(data){return data.jtcy3_gx}
	];
	if (data != null && typeof data == 'object') {
		if ($("xsjtxx").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("xsjtxx");
			DWRUtil.addRows("xsjtxx",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//-----------------家庭信息---------------
function getJtxxInfo(){
	var xh = document.getElementById("xh").value;
	//家庭信息
	var colList = ["xh", "xm", "jtcy1_xm", "jtcy1_gx","jtcy2_xm", "jtcy2_gx", "jtcy3_xm", "jtcy3_gx"];
	getStuDetails.getAllOfInfo(xh,'view_xsjtxx',colList,getXsjtxxInfo);
}

//-----------------注册情况---------------
function getZcqkInfo(){
	var xh = document.getElementById("xh").value;
	//家庭信息
	var colList = ["xn","xqmc","zczt"];
	getStuDetails.getAllOfInfo(xh,'xg_view_xsxx_zcqkb',colList,getXszcqkInfo);
}

//-----------------班主任评议---------------
function getBzrpyInfo(){
	var xh = document.getElementById("xh").value;
	//家庭信息
	var colList = ["xn","pyyj","pyxx","pyrxm","pysj"];
	getStuDetails.getAllOfInfo(xh,'xg_view_xsxx_bzrpycx',colList,getBzrpyqkInfo);
}

//-----------------学籍异动---------------
function getXjydInfo(){
	var xh = document.getElementById("xh").value;
	var colList = ["xn","ydlx","xymc","bjmc","ydhxymc","ydhbjmc","xjzt","sfzx","sfyby"];
	getStuDetails.getAllOfInfo(xh,'view_xsxx_xjyd',colList,getXjydqkInfo);
}

//-----------------社会工作---------------
function getShgzInfo(){
	var xh = document.getElementById("xh").value;
	var colList = ["xn","zzmc","zzjb","gbmc"];
	getStuDetails.getAllOfInfo(xh,'xg_view_szdw_txgb',colList,getTxgbInfo);
}

//-----------------人武管理---------------
function getRwglInfo(){
	var xh = document.getElementById("xh").value;
	var colList = ["rwsj","rwd","rwzh","twsj"];
	getStuDetails.getAllOfInfo(xh,'view_xg_rwgl_rwxxb',colList,getRwxxInfo);
}

//人武管理-入伍管理
function getRwxxInfo(data){
	var cellMuster=[
		function(data){return data.rwsj},
		function(data){return data.rwd},
		function(data){return data.rwzh},
		function(data){return data.twsj}
	];
	if (data != null && typeof data == 'object') {
		if ($("rwxx").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("rwxx");
			DWRUtil.addRows("rwxx",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//社会工作-团学干部
function getTxgbInfo(data){
	var cellMuster=[
		function(data){return data.xn},
		function(data){return data.zzmc},
		function(data){return data.zzjb},
		function(data){return data.gbmc}
	];
	if (data != null && typeof data == 'object') {
		if ($("txgb").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("txgb");
			DWRUtil.addRows("txgb",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//学生成绩
function getXycjdTrInfo(data){
	var cellMuster=[
		function(data){return data.xn},
		function(data){return data.xq},
		function(data){return data.kcmc},
		function(data){return data.kcxz},
		function(data){return data.cj},
		function(data){return data.xf},		
		function(data){return data.jd}
	];
	if (data != null && typeof data == 'object') {
		if ($("xycjdList").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("xycjdList");
			DWRUtil.addRows("xycjdList",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//-----------------学业成绩单-------------
function getXycjdInfo(){
	var xh = document.getElementById("xh").value;
	//学生成绩单
	var colList = ["xn","xq","kcmc","kcxz","cj","xf","jd"];
	getStuDetails.getAllOfInfo(xh,'view_cjb',colList,getXycjdTrInfo);
}

//拓展班级结业
function getTzbjjyInfo(data){
	var cellMuster=[
		function(data){return data.xn},
		function(data){return data.xqmc},
		function(data){return data.mc},
		function(data){return data.jysj},
		function(data){return data.jyfs},
		function(data){return data.sfjy}
	];
	if (data != null && typeof data == 'object') {
		if ($("tzbjjy").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("tzbjjy");
			DWRUtil.addRows("tzbjjy",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//-----------------素质拓展--------------
function getSztzInfo(){
	var xh = document.getElementById("xh").value;
	//拓展班级结业
	var colList = ["xn", "xqmc", "mc","jysj", "jyfs","sfjy"];
	getStuDetails.getAllOfInfo(xh,'view_sztz_bjjyxx',colList,getTzbjjyInfo);
}

//文明寝室
function getWmqsInfo(data){
	var cellMuster=[
		function(data){return data.xn},
		function(data){return data.xq},
		function(data){return data.pysj},
		function(data){return data.ssbh},
		function(data){return data.pbdj},
		function(data){return data.bz}
	];
	if (data != null && typeof data == 'object') {
		if ($("wmqs").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("wmqs");
			DWRUtil.addRows("wmqs",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//住宿纪律信息
function getZsjlInfo(data){
	var cellMuster=[
		function(data){return data.xn},
		function(data){return data.xq},
		function(data){return data.ssbh},
		function(data){return data.qsh},
		function(data){return data.wjlbmc},
		function(data){return data.wjsy},
		function(data){return data.cljg},
		function(data){return data.wjsj},
		function(data){return data.bz}
	];
	if (data != null && typeof data == 'object') {
		if ($("zsjl").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("zsjl");
			DWRUtil.addRows("zsjl",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//-----------------文明寝室评比-------------
function getXsgyInfo(){
	var xh = document.getElementById("xh").value;
	
	if($('wmqs')){
		//文明寝室
		getStuDetails.getWmqspbInfo(xh,getWmqsInfo);
	}
	
	//住宿纪律信息
	var colList = ["xn", "xq", "ssbh","qsh", "wjlbmc","wjsy","cljg","wjsj","bz"];
	if($('zsjl')){
		getStuDetails.getAllOfInfo(xh,"view_zsjlxx",colList,getZsjlInfo);
	}
	
}


//-----------------浙江商业职业学生成绩--------
function getBjgcjInfo(obj){
	var xh=document.getElementById("xh").value;
	var index=obj;
	var pk = document.getElementById("xq"+index).value;
	getStuDetails.getBjgcjInfo(pk+xh,function(data){
		var cellMuster=[
			function(data){return data.xh},			
			function(data){return data.kcmc},
			function(data){return data.zpcj1},
			function(data){return data.cxcj},			
			function(data){return data.xf}
		];
		if (data != null && typeof data == 'object') {
			if ($("kcxx"+index).tagName.toLowerCase() =="tbody"){
				DWRUtil.removeAllRows("kcxx"+index);
				DWRUtil.addRows("kcxx"+index,data,cellMuster);
			}
		}else{
			showMsgWin("有错误出现：远程数据读取失败！");
		}
});
}

//班主任/辅导员信息
function getBzrFdyInfo(data){
	var cellMuster=[
		function(data){return data.xm},
		function(data){return data.xb},
		function(data){return data.lxdh},
		function(data){return data.bmmc},
		function(data){return data.zw},
		function(data){return data.zyzz}
	];
	if (data != null && typeof data == 'object') {
		if ($("bzr").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("bzr");
			DWRUtil.addRows("bzr",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}
//----------------西昌学院班主任辅导员信息-----------
function getBDinfo(){
	var xh = document.getElementById("xh").value;
	//班主任/辅导员
	var colList = ["xm","xb","lxdh","bmmc","zw","zyzz"];
	getStuDetails.getBzrInfo(xh,colList,getBzrFdyInfo);
	
}

function getZsfInfo(data){
	var cellMuster=[
		function(data){return data.xh},
		function(data){return data.rzrq},
		function(data){return data.jzrq},
		function(data){return data.zsf},
		function(data){return data.dsjssf}
	];
	if (data != null && typeof data == 'object') {
		if ($("zsf").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("zsf");
			DWRUtil.addRows("zsf",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}
//杭职院收费信息 增加住宿费收费信息 
function getChargeInfo(){
	var xh = document.getElementById("xh").value;
	var colList = ["xh","rzrq","jzrq","zsf","dsjssf"];
	getStuDetails.getZsf(xh,colList,getZsfInfo)
}

//安徽建筑工业学院学生成绩 和教务做接口

function getAhjgXscj(data){
	var cellMuster=[
		function(data){return data.xh},
		function(data){return data.xn},
		function(data){return data.xq},
		function(data){return data.kcmc},
		function(data){return data.kcxz},
		function(data){return data.cj},
		function(data){return data.xf},
		function(data){return data.jd}
	];
	if (data != null && typeof data == 'object') {
		if ($("ahjgxscj").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("ahjgxscj");
			DWRUtil.addRows("ahjgxscj",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//安徽建工学生成绩信息
function getAhjgXscjInfo(){
	var xh = document.getElementById("xh").value;
	var colList = ["xh","xn","xq","kcmc","kcxz","cj","xf","jd"];
	getStuDetails.getXscj(xh,colList,getAhjgXscj);
}

//------江苏信息素质拓展认证信息-------------
function getTzRzInfo(data){
	var cellMuster=[
		function(data){return data.xn},
		function(data){return data.nd},
		function(data){return data.xq},
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.xmmc},
		function(data){return data.kmm},
		function(data){return data.xf}
	];
	if (data != null && typeof data == 'object') {
		if ($("tzrzxx").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("tzrzxx");
			DWRUtil.addRows("tzrzxx",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//------宁波职业素质拓展-------------
function getTzHdInfo(data){
	var cellMuster=[
		function(data){return data.xn},
		function(data){return data.xq},
		function(data){return data.xmmc},
		function(data){return data.xmjb},
		function(data){return data.kmmc},
		function(data){return data.jxm},
		function(data){return data.xf},
		function(data){return data.cyjs},
		function(data){return data.zzdw},
		function(data){return data.zbsj}
	];
	if (data != null && typeof data == 'object') {
		if ($("tzhdxx").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("tzhdxx");
			DWRUtil.addRows("tzhdxx",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}
//----------------西南民族大学素质拓展班级结业信息-----------
function getTzBjJyInfo(data){
	var cellMuster=[
		function(data){return data.xn},
		function(data){return data.xqmc},
		function(data){return data.mc},
		function(data){return data.jysj},
		function(data){return data.jyfs}
	];
	if (data != null && typeof data == 'object') {
		if ($("tzbjjy").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("tzbjjy");
			DWRUtil.addRows("tzbjjy",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//西南民族大学素质拓展班级结业信息
function getTzBjJy(){
	var xh = document.getElementById("xh").value;
	var colList = ["xn","xqmc","mc","jysj","jyfs"];
	getStuDetails.getTzBjJy(xh,colList,getTzBjJyInfo);
}

//江苏信息素质拓展认证信息
function getTzRzXx(){
	var xh = document.getElementById("xh").value;
	var colList = ["nd","xn","xq","xh","xm","xmmc","kmm","xf"];
	getStuDetails.getAllOfInfo(xh,'view_sztzcj',colList,getTzRzInfo);
}

//宁波职业素质拓展
function getTzHd(){
	var xh = document.getElementById("xh").value;
	var colList = ["xn","xq","xmmc","xmjb","kmmc","jxm","xf","cyjs","zzdw","zbsj"];
	getStuDetails.getAllOfInfo(xh,"view_tzcgcjxx",colList,getTzHdInfo);
}

function getTjzySztz(){
	var xh = jQuery('#xh').val();
	jQuery.post('sztzAjax.do?method=getSztzInfo',{xh:xh},function(data){
		jQuery('tr',jQuery('#sztzData')).remove();
		for (var i = 0 ; i < data.length; i++){
			var d_html = "<tr><td>"
			     +data[i].kmmc
			     +"</td><td>"
			     +data[i].hxnlmc
			     +"</td><td>"
			     +data[i].xn
			     +"</td><td>"
			     +data[i].xqmc
			     +"</td><td>"
			     +data[i].sdxf
			     +"</td><td>"
			     +data[i].sfcx
			     +"</td></tr>";
			jQuery('#sztzData').append(d_html);
		}
	},'json')
}



//勤工助学评奖评优记录
function getPjpyOfQgzx(){
	var xh = document.getElementById("xh").value;
	//奖学金	
	var colList = ["nd", "xn", "xh", "xm", "bjmc", "jxjmc","xysh", "xxsh", "tjflag"];	
	getStuDetails.getAllOfInfo(xh,'view_xsjxjb',colList,getJxjInfo);
	
	//荣誉称号
	colList = ["nd", "xn", "xh", "xm", "bjmc", "rychmc","xysh", "xxsh"];	
	getStuDetails.getAllOfInfo(xh,'view_xsrychb',colList,getRychInfo);
}


function getBxkcjInfo(data){	
	var cellMuster=[
		function(data){return data.nd},
		function(data){return data.xq},
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.bjmc},
		function(data){return data.kcmc},
		function(data){return data.bkcj},
		function(data){return data.cxcj},
		function(data){return data.cj}
		];	
	if (data != null && typeof data == 'object') {
		if ($("bxkcj").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("bxkcj");
			DWRUtil.addRows("bxkcj",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//勤工助学必修课不及格记录
function getHngydxBxkcj(){
	var xh = document.getElementById("xh").value;
	var colList = ["xq", "xn", "xh", "xm", "bjmc", "kcmc", "bkcj","cxcj", "cj"];
	hngyStuCj.getXscjInfo(xh,getBxkcjInfo);	
}

function getXjydxx(){
	var xh = val('xh');
	getStuDetails.queryXjydxx(xh,function(data){
		if(data != null){
			ele('ydxh').innerText = data.ydxh == null || data.ydxh == 'null ' ? "" : data.ydxh;
			ele('clwh').innerText = data.clwh == null || data.clwh == 'null' ? "" : data.clwh;
			ele('ydyy').innerText = data.ydyy == null || data.ydyy == 'null' ? "" : data.ydyy;
			ele('ydsm').innerText = data.ydsm == null || data.ydsm == 'null' ? "" : data.ydsm;
			ele('ydlbmc').innerText = data.ydlbmc == null || data.ydlbmc == 'null' ? "" : data.ydlbmc;
			ele('ydrq').innerText = data.ydrq == null || data.ydrq == 'null' ? "" : data.ydrq;
			ele('xjztm').innerText = data.xjztm == null || data.xjztm == 'null' ? "" :  data.xjztm;
			ele('sfxx').innerText = data.sfxx == null || data.sfxx == 'null' ? "" :  data.sfxx;
		}
	});
}

//------------学生成绩--------------------
function getXscjInfo(){	
	var xh = document.getElementById("xh").value;
	//学生成绩
	var colList = ["xn", "xq", "kcmc", "kcxz","xf","cj","bkcj","cxcj"];
	getStuDetails.getAllOfInfo(xh,'view_cjb',colList,getCjInfo);	
	
	//等级考试
	var colList = ['xn','xq',"djksmc", "cj"];
	getStuDetails.getAllOfInfo(xh,'xsdjksb',colList,getDjksInfo);	
	
}

//学生成绩
function getCjInfo(data){
	var cellMuster=[
		function(data){return data.xn},
		function(data){return data.xq},
		function(data){return data.kcmc},
		function(data){return data.kcxz},
		function(data){return data.xf},
		function(data){return data.cj},
		function(data){return data.bkcj},
		function(data){return data.cxcj}
	];
	if (data != null && typeof data == 'object') {
		if ($("xscjList").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("xscjList");
			DWRUtil.addRows("xscjList",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//等级考试成绩
function getDjksInfo(data){
	var cellMuster=[
		function(data){return data.xn},
		function(data){return data.xq},
		function(data){return data.djksmc},
		function(data){return data.cj}
	];
	if (data != null && typeof data == 'object') {
		if ($("djksList").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("djksList");
			DWRUtil.addRows("djksList",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//------------缴费情况--------------------
function getXsjfqkInfo(){	
	var xh = document.getElementById("xh").value;
	//学生成绩
	var colList = ["xh","xm","xn", "yjf", "sjf","qf"];
	
	getStuDetails.getAllOfInfo(xh,'view_xsxx_xsxfxx',colList,getJfInfo);	
	
}

//缴费情况
function getJfInfo(data){
	var cellMuster=[
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.xn},
		function(data){return data.yjf},
		function(data){return data.sjf},
		function(data){return data.qf}
	];
	if (data != null && typeof data == 'object') {
		if ($("qfqkList").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("qfqkList");
			DWRUtil.addRows("qfqkList",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}


//------------常熟理工学生学习经历--------------------
function getXsxxjlInfo(){	
	var xh = document.getElementById("xh").value;
	//学生成绩
	var colList = ["xh","qssj","zzsj", "dwmc", "jzr","bz"];
	
	getStuDetails.getAllOfInfo(xh,'xsxx_cslg_xxjlb',colList,getXxjlInfo);	
	
}

//------------常熟理工学生学习经历--------------------
function getXxjlInfo(data){
	var cellMuster=[
		function(data){return data.qssj},
		function(data){return data.zzsj},
		function(data){return data.dwmc},
		function(data){return data.jzr},
		function(data){return data.bz}
	];
	if (data != null && typeof data == 'object') {
		if ($("xsxxjl").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("xsxxjl");
			DWRUtil.addRows("xsxxjl",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

function getJyglInfo(){
	var xh = document.getElementById("xh").value;
	//毕业生信息
	var colList = [ "xh", "xm", "rxnf", "bynf"];
	getStuDetails.getAllOfInfo(xh,'VIEW_JY_BYSXXB',colList,getBysxxInfo);	
	//就业协议
	var colList = ["xh", "xm", "dwmc", "dwxzmc"];
	getStuDetails.getAllOfInfo(xh,'VIEW_JY_JYXY',colList,getJyxyInfo);
	//就业协议领取登记表
	var colList = ["xh", "xm", "jyxybh", "lqqk"];
	getStuDetails.getAllOfInfo(xh,'VIEW_JYGL_JYXYSLQDJB',colList,getJyxylqInfo);
	//就业协议补领申请
	var colList = ["xh", "xm", "sqsj", "bblb"];
	getStuDetails.getAllOfInfo(xh,'view_jygl_jyxyblsq',colList,getJyxybbInfo);
}

//获取毕业生信息
function getBysxxInfo(data){
	var cellMuster=[
	function(data){return data.xh},
	function(data){return data.xm},
	function(data){return data.rxnf},
	function(data){return data.bynf}
	];
	if (data != null && typeof data == 'object') {
		if ($("bysxx").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("bysxx");
			DWRUtil.addRows("bysxx",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//获取就业信息信息
function getJyxyInfo(data){
	var cellMuster=[
	function(data){return data.xh},
	function(data){return data.xm},
	function(data){return data.dwmc},
	function(data){return data.dwxzmc}
	];
	if (data != null && typeof data == 'object') {
		if ($("jyxy").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("jyxy");
			DWRUtil.addRows("jyxy",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//获取就业协议领取信息
function getJyxylqInfo(data){
	var cellMuster=[
	function(data){return data.xh},
	function(data){return data.xm},
	function(data){return data.jyxybh},
	function(data){return data.lqqk}
	];
	if (data != null && typeof data == 'object') {
		if ($("jyxylq").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("jyxylq");
			DWRUtil.addRows("jyxylq",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//获取就业协议补办信息
function getJyxybbInfo(data){
	var cellMuster=[
	function(data){return data.xh},
	function(data){return data.xm},
	function(data){return data.sqsj},
	function(data){return data.bblb}
	];
	if (data != null && typeof data == 'object') {
		if ($("jyxybb").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("jyxybb");
			DWRUtil.addRows("jyxybb",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//家庭信息
function getXszcqkInfo(data){
	var cellMuster=[
		function(data){return data.xn},
		function(data){return data.xqmc},
		function(data){return data.zczt}
	];
	if (data != null && typeof data == 'object') {
		if ($("xszcqk").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("xszcqk");
			DWRUtil.addRows("xszcqk",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//家庭信息
function getBzrpyqkInfo(data){
	var cellMuster=[
		function(data){return data.xn},
		function(data){return data.pyyj},
		function(data){return data.pyrxm},
		function(data){return data.pysj}
	];
	if (data != null && typeof data == 'object') {
		if ($("bzrpyqk").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("bzrpyqk");
			DWRUtil.addRows("bzrpyqk",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
	
	jQuery()
}

function getXjydqkInfo(data){
	var cellMuster=[
		function(data){return data.xn},
		function(data){return data.ydlx},
		function(data){return data.xymc},
		function(data){return data.bjmc},
		function(data){return data.ydhxymc},
		function(data){return data.ydhbjmc},
		function(data){return data.xjzt},
		function(data){return data.sfzx},
		function(data){return data.sfyby}
	];
	if (data != null && typeof data == 'object') {
		if ($("xjydqk").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("xjydqk");
			DWRUtil.addRows("xjydqk",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

