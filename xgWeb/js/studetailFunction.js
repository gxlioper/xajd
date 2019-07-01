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


//��ѵ
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//�뵳��������
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
		alert("������!!");
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//Ԥ����Ա
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//����ѧԺԤ����Ա
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��Ա
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��Ա
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//������������������˼�������������������
function getSxjyInfo(){
	var xh = document.getElementById("xh").value;
	var xxdm = document.getElementById("xxdm").value;
	if(xxdm == "10290"){//�й���ҵ��ѧ		
		//��Ա��Ϣ
		getStuDetails.getDyxx(xh,getDyxxInfo);
	}else{
		//ѧ����ѵ��Ϣ
		var colList = ["xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "pxcs"];	
		getStuDetails.getAllOfInfo(xh,'view_czxx_dkpxmdb',colList,getPxxxInfo);
		
		//�뵳��������
		colList = ["nd", "xn", "xqmc", "xh", "xm", "nj", "bjmc","kssj"];
		getStuDetails.getAllOfInfo(xh,'view_rdjjfzxx',colList,getRdjjfzInfo);
		
		//Ԥ����Ա
		colList = ["nd", "xn", "xqmc", "xh", "xm", "nj", "bjmc","kssj", "jssj"];
		if(xxdm == "11122"){//����ѧԺ 
			colList = ["nd", "xn", "xqmc", "xh", "xm", "nj", "bjmc","zzlx","kssj", "jssj"];
			getStuDetails.getAllOfInfo(xh,'view_ybdyxx',colList,getSjxyYbdyInfo);
		}else{
			getStuDetails.getAllOfInfo(xh,'view_ybdyxx',colList,getYbdyInfo);
		}
		
		//��Ա
		colList = ["nd", "xn", "xqmc", "xh", "xm", "nj", "bjmc","rdsj"];
		if(xxdm == "11122"){//����ѧԺ 
			colList = ["xh", "xm", "nj", "bjmc","rdsj","zzsj","zzlx","zzdw"];
			getStuDetails.getAllOfInfo(xh,'view_dyxx',colList,getSjxyDyInfo);
		}else{
			getStuDetails.getAllOfInfo(xh,'view_dyxx',colList,getDyInfo);
		}
	}
}


//��ѧ��
function getJxjInfo(data){
	var xxdm = document.getElementById("xxdm").value;
//	if(xxdm == "11551" || xxdm == "10878"){
//		//����Ƽ�ѧԺ�����ս�����ҵѧԺ���Ӹ���Ա���
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
//	} else if (xxdm=='13022') {//������
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
				if(data.pjxq=='��'){
					pjxn+="";
				}else{
					pjxn+=data.pjxq;
				}
				if(data.pjnd=='��'){
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//�����ƺ�
function getRychInfo(data){
	var xxdm = document.getElementById("xxdm").value;
	if(xxdm == "11551" || xxdm == "10878" || xxdm =="10827" || xxdm == "10419"){
		//����Ƽ�ѧԺ�����ս�����ҵѧԺ���Ӹ���Ա���
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
	}else if (xxdm=='13022') {//������
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��ʵ�ۺϿ�����¼
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}
var divHtml;
var datanum;
//�ۺ����ʲ���
function getZhszcpInfo(data){
	var xh = document.getElementById("xh").value;
	dwr.engine.setAsync(false);
	
	if (data != null && typeof data == 'object') {
		if ($("zhszcp").tagName.toLowerCase() =="thead"){
			divHtml = "<div id=\"zhszcp_div\">"
				divHtml+= "<span class=\"formbox\">";
			for(var i=0;i<data.length;i++){
					divHtml+= "<table class=\"dateline\" width=\"100%\" id=\"showDiary\">";
					
					//-------------����-------------
					divHtml+= "<thead>";
					divHtml+= "<tr>";
					divHtml+= "<td colspan=\"40\">"+data[i].xn.toString()+"ѧ��"+"</td>";
					divHtml+= "</tr>";
					divHtml+= "<tr>";
					divHtml+= "<td>ѧ��</td>";
					colList = ["xmmc"];
					//��ȡ��Ŀ����
					getStuDetails.getAllOfInfo(data[i].xn.toString(),'xg_pjpy_zcxmb',colList,getZhszcpInfoxn);
					divHtml+= "<td>�༶����</td>";
					divHtml+= "</tr>";
					divHtml+= "</thead>";
					//-------------����-------------
					divHtml+= "<tbody>";
					//colList2 = ["xq","nd","zd1","zd2","zd3","zd4","zd5","zcfbjpm"];
					var colList2=["xq","zcfbjpm"];
					//��ȡ��Ŀ����
					var str = xh+","+data[i].xn.toString();
					getStuDetails.getAllOfInfo(str,'xg_pjpy_zhcpb2',colList2,getZhszcpInfosj);
					divHtml+= "</tbody>";
					divHtml+= "</table>";
					divHtml+= "<br></br>";
					
			}
			$("zhszcp_div").outerHTML=divHtml;
		}
	}else{
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
	
	dwr.engine.setAsync(true);
}
//�ۺ����ʲ���
function getZhszcpInfoxn(data){
	if (data != null && typeof data == 'object') {
		datanum=data.length;
		if(data.length==0){
			divHtml+= "<td>�۲��ܷ�</td>";
			divHtml+= "<td>������</td>";
			divHtml+= "<td>������</td>";
			divHtml+= "<td>������</td>";
			divHtml+= "<td>�������ʷ�</td>";
		}else{
			for(var i=0;i<data.length;i++){
				//alert(data[i].xn.toString());
				divHtml+= "<td>"+data[i].xmmc.toString()+"</td>";
			}
		}
		
	}else{
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
	
}

//ѧ�����������
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//����������
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//ѧ���������
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}
//-----------------��������---------------
function getPjpyInfo(){
	var xh = document.getElementById("xh").value;
	var xxdm = document.getElementById("xxdm").value;
	//��ѧ��
//	if(xxdm == "11551" || xxdm == "10878" || xxdm == "12764"){
//		//����Ƽ�ѧԺ�����ս�����ҵѧԺ,�Ĵ�����ְҵ����ѧԺ���Ӹ���Ա���
//		var colList = ["nd", "xn", "xh", "xm", "bjmc", "jxjmc","jlje","fdysh","xysh", "xxsh"];
//	} else if (xxdm=='13022') {//������
//		var colList = ["xn", "xh", "xm", "bjmc", "jxjmc","jlje","xysh", "xxsh"];
//	} else if(xxdm=='12872'){//����ְҵ����ѧԺ
//		var colList = ["nd", "xn", "xh", "xm", "bjmc", "jxjmc","jlje","xysh"];
//	}else{	
		var colList = [ "pjxn", "pjxq","pjnd","xmmc","xmje", "sqsj"];		
//	}
	//ԭ����
//	getStuDetails.getAllOfInfo(xh,'view_xsjxjb',colList,getJxjInfo);
	getStuDetails.getAllOfInfo(xh,'xg_view_pjpy_xstgjl',colList,getJxjInfo);
	//�����ƺ�
//	var table = "view_xsrychb";
//	if(xxdm == "11551" || xxdm == "10878" || xxdm =="10827" || xxdm == "10419"){
//		//����Ƽ�ѧԺ�����ս�����ҵѧԺ���Ӹ���Ա���
//		colList = ["nd", "xn", "xh", "xm", "bjmc", "rychmc","fdysh","xysh", "xxsh"];
//	} else if (xxdm=='13022') {//������
//		colList = ["xn", "xh", "xm", "bjmc", "rychmc","xysh", "xxsh"];
//	} else if(xxdm=='12872'){//����ְҵ����ѧԺ
//		colList = ["nd", "xn", "xh", "xm", "bjmc", "rychmc","xysh"];
//	} else{	
//		colList = ["nd", "xn", "xh", "xm", "bjmc", "rychmc","xysh", "xxsh"];
//	}
//	if(xxdm == "13742"){//������һ
//		table = "view_nbty_xsrychb";
//	}
//	
//	getStuDetails.getAllOfInfo(xh,table,colList,getRychInfo);
//	
//	//��ʵ�ۺϿ�����¼
//	if($('jszhkp')){
//		if(xxdm!="12872"){
//			//�Ǻ���ְҵ����ѧԺ		
//			colList = ["nd","xn","xq","xh","xm","sxpdpj","zssppj","xynlpj","nlpj","szpj","xf"];
//			getStuDetails.getAllOfInfo(xh,'view_jszhkp',colList,getJszhkpInfo);
//		}
//	}
	
	//�ۺ����ʲ���
	if($('zhszcp')){
//		if(xxdm=="10822"){//�㶫����ѧԺ
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
//					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
//				}
//			});
//		}else if(xxdm=="12861"){//�㽭����
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
//					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
//				}
//			});
//		}else if(xxdm=="12872"){//����ְҵ
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
//					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
//				}
//			});
//		}else if(xxdm == '10863'){
//		    //����ְҵ
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
//					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
//				}
//			});
//		}else if(xxdm == '10290'){//�й���ҵ��ѧ
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
//					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
//				}
//			});
//		}else if(xxdm=="12809"){//���ݹ�ҵ԰��ְҵ����ѧԺ
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
//					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
//				}
//			});
//		}else{		//ԭ�ۺ�	
//			colList = ["nd", "xn", "xq", "xh", "xm", "bjmc","dcj","zcj","tcj","znszcj","zhszcpzf","bjpm","njpm"];
//			getStuDetails.getAllOfInfo(xh,'view_zhszcp',colList,getZhszcpInfo);
		colList = ["xn"];
		getStuDetails.getAllOfInfo(xh,'xg_pjpy_zhcpb',colList,getZhszcpInfo);
		
//		}
	}
	//���ս�������ѧϰ�������
//	if(xxdm=="10878"){
//		colList = ["xn","nd","xh","xm","hjsj","xxjsmc","xxjsdf"];
//		getStuDetails.getAllOfInfo(xh,'view_xsxxjs',colList,getXxjshjInfo);		
//	}
//	
//	//�й���Ժ
//	if(xxdm == "10355"){	
//		//����������
//		colList = ["xn","xq","tydb","bz"];
//		getStuDetails.getAllOfInfo(xh,'pjpy_tydbqkb',colList,getTydbqk);
//		
//		//������Ϣ
//		colList = ["xn","xq","kksj","kkdd","kkjl"];
//		getStuDetails.getAllOfInfo(xh,'pjpy_xskqb',colList,getXskqb);	
//		
//	}
}

//-----------------����ѧ������---------------
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

function getXskccj(data){
	var xxdm = document.getElementById("xxdm").value;
	var cellMuster = [];
	if(xxdm=='10649'){
		//��ɽʦ��ѧԺ
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//ѧ���γ̳ɼ�
function getXskccjInfo(){
	var xh = document.getElementById("xh").value;
	var xxdm = document.getElementById("xxdm").value;
	var colList = [];
	if(xxdm=='10649'){
		colList = ["xn", "xq", "kcmc", "kclx", "bkcj", "cxcj","cj"];
	}else{
	//�޶��γ̳ɼ���Ϣ
		colList = ["xn", "xq", "kcmc", "kclx", "bkcj2", "cxcj2","zpcj2"];
	}
	getStuDetails.getAllOfInfo(xh,'cjb',colList,getXskccj);
}


//���⽻������ѧ��
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//---------------���⽻��--------------
function getDwjlInfo(){
	var xh = document.getElementById("xh").value;
	var xxdm = document.getElementById("xxdm").value;
	//���⽻������ѧ��
	var colList = ["nd", "xn", "xq", "xh", "xm", "dwjlxmmc","sqsj", "xysh", "xxsh", "xxzs", "jxj"];
	if(xxdm == "12872"){//����ְҵ����ѧԺ
		colList = ["nd", "xn", "xq", "xh", "xm", "dwjlxmmc","sqsj", "xysh", "xxzs", "jxj"];
	}
	getStuDetails.getAllOfInfo(xh,'view_dwjlsq',colList,getDwjljjxjInfo);
}

//������
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}
var maxlength = 1;
//---------------ѧ������--------------
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
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
		});	
	});
}

//---------------��ҵ����--------------
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
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
		});	
	});
}


//�ڹ���ѧ
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}
//ѧ����𷢷�
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}
//--------------�ڹ���ѧ-----------------
function getQgzxInfo(){
	var xh = document.getElementById("xh").value;
	//�ڹ���ѧ
	var colList = ["nd", "xn", "xq", "xh", "xm", "bjmc", "gwdm","sqsj"];
	getStuDetails.getAllOfInfo(xh,'view_xsgwxx',colList,getXsqgzxInfo);	
	//��𷢷�
	var colList = ["nd", "xn", "xq", "yf","xh", "xm","bjmc","gwdm","cjje","ffsj"];
	getStuDetails.getAllOfInfo(xh,'view_xscjff',colList,getXscjffInfo);
}

//�������
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
	if(xxdm == "10290"){//�й���ҵ
		cellMuster=[
		function(data){return data.xh},
		function(data){return data.xm},
		function(data){return data.xlcsxmmc},
		function(data){return data.xlcsjgmc},
		function(data){return data.cssj}
	];
	}
	if(xxdm == "10344"){//�㽭��ҽҩ��ѧ
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//����ѧ��
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//--------------������------------------
function getXljkInfo(){
		var xxdm = document.getElementById("xxdm").value;
	var xh = document.getElementById("xh").value;
	//�������
	var colList = ["xh", "xm","xlcsxmmc","xlcsjgmc", "cssj", "fsbj"];
	if(xxdm == "10290"){//�й���ҵ��ѧ 
		colList = ["xh", "xm","xlcsxmmc","xlcsjgmc", "cssj"];
	}else if(xxdm == "10344"){//�㽭��ҽҩ��ѧ
		colList = ["xn", "xqmc","sbsj","zywt","brth","lxjs","bz"];
	}
	if(xxdm == "10344"){//�㽭��ҽҩ��ѧ
		getStuDetails.getAllOfInfo(xh,'XG_XLJK_ZJZYY_TSXSXXB',colList,getXlcsInfo);
	}else{//���㽭��ҽҩ��ѧ
		getStuDetails.getAllOfInfo(xh,'view_xlcsjg',colList,getXlcsInfo);
	
		//����ѧ��
		colList = ["xh", "xm", "tbgxxslbmc"];
		getStuDetails.getAllOfInfo(xh,'view_xytbgxxsxx',colList,getTsxsInfo);
	}
}

//��ѵ��
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��ѵ�ɼ�
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//----------------ѧ����ѵ---------------
function getXsjxInfo(){
	var xh = document.getElementById("xh").value;
	//��ѵ��
	var colList = ["nd", "xn", "xq", "xh", "xm", "bjmc", "jxmc","hjsj"];
	getStuDetails.getAllOfInfo(xh,'view_xsjxhj',colList,getJxhjInfo);
	//��ѵ�ɼ�
	var colList = ["xh","xm","nd","bjmc","mzmc","xlcj","kscj","cj","lrsj","lrr"];
	getStuDetails.getAllOfInfo(xh,'view_jxcjxx',colList,getJxcjInfo);
}

//----------------����ʦ��----------------
function getXsjxInfoByHzsfxy(){
	var xh = document.getElementById("xh").value;
	//��������
	var colList = ["xn", "mc1", "mc2", "mc3", "bjmc"];
	getStuDetails.getAllOfInfo(xh,'view_jxgl_jxmdb',colList,getSsldInfo);
	//��������
	var colList = ["xn", "grrymc"];
	getStuDetails.getAllOfInfo(xh,'view_xg_jxgl_hzsf_grryhjb',colList,getGrryInfo);
	//�Ŷ�����
	var colList = ["xn", "bzmc", "bzjbmc", "tdrymc"];
	getStuDetails.getAllOfInfo(xh,'view_xg_jxgl_tdryhjb',colList,getTdryInfo);
}

//��������
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��������
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//�Ŷ�����
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}




//Υ�ʹ���
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}



//�й���ҵ��ѧ
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//----------------Υ�ʹ���---------------
function getWjcfInfo(){	
	var xh = document.getElementById("xh").value;
	var xxdm = document.getElementById("xxdm").value;
	//Υ�ʹ���
	var colList = ["xh", "xm", "xn", "nd", "xq", "cflbmc","cfyymc","cfsj", "cfwh", "ssjg", "cxsj"];
	if(xxdm == "10290"){
		getStuDetails.getAllOfInfo(xh,'view_wjcf',colList,getXswjcfInfoOfZgkd);	
	}else{
		getStuDetails.getAllOfInfo(xh,'view_wjcf',colList,getXswjcfInfo);	
	}
}



//������Ϣ
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��ʳ����
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//-----------------��������--------------
function getQtsjInfo(){
	var xxdm = document.getElementById("xxdm").value;
	var xh = document.getElementById("xh").value;
	//����
	var colList = ["nd","xn","xq", "xh", "xm", "bxgsmc", "bxxzmc", "tbsj", "tuibsj", "tbbj"];
	getStuDetails.getAllOfInfo(xh,'view_xsbxxx',colList,getBxxxInfo);	
	if(xxdm !="12872"){
		//����ְҵ����ѧԺ
		//��ʳ����
		colList = ["xh", "xm", "nf", "yf", "xfje"];
		getStuDetails.getAllOfInfo(xh,'view_xshsxf',colList,getHsxfInfo);
	}
}

//��ͥ��Ϣ
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//-----------------��ͥ��Ϣ---------------
function getJtxxInfo(){
	var xh = document.getElementById("xh").value;
	//��ͥ��Ϣ
	var colList = ["xh", "xm", "jtcy1_xm", "jtcy1_gx","jtcy2_xm", "jtcy2_gx", "jtcy3_xm", "jtcy3_gx"];
	getStuDetails.getAllOfInfo(xh,'view_xsjtxx',colList,getXsjtxxInfo);
}

//-----------------ע�����---------------
function getZcqkInfo(){
	var xh = document.getElementById("xh").value;
	//��ͥ��Ϣ
	var colList = ["xn","xqmc","zczt"];
	getStuDetails.getAllOfInfo(xh,'xg_view_xsxx_zcqkb',colList,getXszcqkInfo);
}

//-----------------����������---------------
function getBzrpyInfo(){
	var xh = document.getElementById("xh").value;
	//��ͥ��Ϣ
	var colList = ["xn","pyyj","pyxx","pyrxm","pysj"];
	getStuDetails.getAllOfInfo(xh,'xg_view_xsxx_bzrpycx',colList,getBzrpyqkInfo);
}

//-----------------ѧ���춯---------------
function getXjydInfo(){
	var xh = document.getElementById("xh").value;
	var colList = ["xn","ydlx","xymc","bjmc","ydhxymc","ydhbjmc","xjzt","sfzx","sfyby"];
	getStuDetails.getAllOfInfo(xh,'view_xsxx_xjyd',colList,getXjydqkInfo);
}

//-----------------��Ṥ��---------------
function getShgzInfo(){
	var xh = document.getElementById("xh").value;
	var colList = ["xn","zzmc","zzjb","gbmc"];
	getStuDetails.getAllOfInfo(xh,'xg_view_szdw_txgb',colList,getTxgbInfo);
}

//-----------------�������---------------
function getRwglInfo(){
	var xh = document.getElementById("xh").value;
	var colList = ["rwsj","rwd","rwzh","twsj"];
	getStuDetails.getAllOfInfo(xh,'view_xg_rwgl_rwxxb',colList,getRwxxInfo);
}

//�������-�������
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��Ṥ��-��ѧ�ɲ�
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//ѧ���ɼ�
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//-----------------ѧҵ�ɼ���-------------
function getXycjdInfo(){
	var xh = document.getElementById("xh").value;
	//ѧ���ɼ���
	var colList = ["xn","xq","kcmc","kcxz","cj","xf","jd"];
	getStuDetails.getAllOfInfo(xh,'view_cjb',colList,getXycjdTrInfo);
}

//��չ�༶��ҵ
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//-----------------������չ--------------
function getSztzInfo(){
	var xh = document.getElementById("xh").value;
	//��չ�༶��ҵ
	var colList = ["xn", "xqmc", "mc","jysj", "jyfs","sfjy"];
	getStuDetails.getAllOfInfo(xh,'view_sztz_bjjyxx',colList,getTzbjjyInfo);
}

//��������
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//ס�޼�����Ϣ
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//-----------------������������-------------
function getXsgyInfo(){
	var xh = document.getElementById("xh").value;
	
	if($('wmqs')){
		//��������
		getStuDetails.getWmqspbInfo(xh,getWmqsInfo);
	}
	
	//ס�޼�����Ϣ
	var colList = ["xn", "xq", "ssbh","qsh", "wjlbmc","wjsy","cljg","wjsj","bz"];
	if($('zsjl')){
		getStuDetails.getAllOfInfo(xh,"view_zsjlxx",colList,getZsjlInfo);
	}
	
}


//-----------------�㽭��ҵְҵѧ���ɼ�--------
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
			showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
		}
});
}

//������/����Ա��Ϣ
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}
//----------------����ѧԺ�����θ���Ա��Ϣ-----------
function getBDinfo(){
	var xh = document.getElementById("xh").value;
	//������/����Ա
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}
//��ְԺ�շ���Ϣ ����ס�޷��շ���Ϣ 
function getChargeInfo(){
	var xh = document.getElementById("xh").value;
	var colList = ["xh","rzrq","jzrq","zsf","dsjssf"];
	getStuDetails.getZsf(xh,colList,getZsfInfo)
}

//���ս�����ҵѧԺѧ���ɼ� �ͽ������ӿ�

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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//���ս���ѧ���ɼ���Ϣ
function getAhjgXscjInfo(){
	var xh = document.getElementById("xh").value;
	var colList = ["xh","xn","xq","kcmc","kcxz","cj","xf","jd"];
	getStuDetails.getXscj(xh,colList,getAhjgXscj);
}

//------������Ϣ������չ��֤��Ϣ-------------
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//------����ְҵ������չ-------------
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}
//----------------���������ѧ������չ�༶��ҵ��Ϣ-----------
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//���������ѧ������չ�༶��ҵ��Ϣ
function getTzBjJy(){
	var xh = document.getElementById("xh").value;
	var colList = ["xn","xqmc","mc","jysj","jyfs"];
	getStuDetails.getTzBjJy(xh,colList,getTzBjJyInfo);
}

//������Ϣ������չ��֤��Ϣ
function getTzRzXx(){
	var xh = document.getElementById("xh").value;
	var colList = ["nd","xn","xq","xh","xm","xmmc","kmm","xf"];
	getStuDetails.getAllOfInfo(xh,'view_sztzcj',colList,getTzRzInfo);
}

//����ְҵ������չ
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



//�ڹ���ѧ�������ż�¼
function getPjpyOfQgzx(){
	var xh = document.getElementById("xh").value;
	//��ѧ��	
	var colList = ["nd", "xn", "xh", "xm", "bjmc", "jxjmc","xysh", "xxsh", "tjflag"];	
	getStuDetails.getAllOfInfo(xh,'view_xsjxjb',colList,getJxjInfo);
	
	//�����ƺ�
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//�ڹ���ѧ���޿β������¼
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

//------------ѧ���ɼ�--------------------
function getXscjInfo(){	
	var xh = document.getElementById("xh").value;
	//ѧ���ɼ�
	var colList = ["xn", "xq", "kcmc", "kcxz","xf","cj","bkcj","cxcj"];
	getStuDetails.getAllOfInfo(xh,'view_cjb',colList,getCjInfo);	
	
	//�ȼ�����
	var colList = ['xn','xq',"djksmc", "cj"];
	getStuDetails.getAllOfInfo(xh,'xsdjksb',colList,getDjksInfo);	
	
}

//ѧ���ɼ�
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//�ȼ����Գɼ�
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//------------�ɷ����--------------------
function getXsjfqkInfo(){	
	var xh = document.getElementById("xh").value;
	//ѧ���ɼ�
	var colList = ["xh","xm","xn", "yjf", "sjf","qf"];
	
	getStuDetails.getAllOfInfo(xh,'view_xsxx_xsxfxx',colList,getJfInfo);	
	
}

//�ɷ����
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}


//------------������ѧ��ѧϰ����--------------------
function getXsxxjlInfo(){	
	var xh = document.getElementById("xh").value;
	//ѧ���ɼ�
	var colList = ["xh","qssj","zzsj", "dwmc", "jzr","bz"];
	
	getStuDetails.getAllOfInfo(xh,'xsxx_cslg_xxjlb',colList,getXxjlInfo);	
	
}

//------------������ѧ��ѧϰ����--------------------
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

function getJyglInfo(){
	var xh = document.getElementById("xh").value;
	//��ҵ����Ϣ
	var colList = [ "xh", "xm", "rxnf", "bynf"];
	getStuDetails.getAllOfInfo(xh,'VIEW_JY_BYSXXB',colList,getBysxxInfo);	
	//��ҵЭ��
	var colList = ["xh", "xm", "dwmc", "dwxzmc"];
	getStuDetails.getAllOfInfo(xh,'VIEW_JY_JYXY',colList,getJyxyInfo);
	//��ҵЭ����ȡ�ǼǱ�
	var colList = ["xh", "xm", "jyxybh", "lqqk"];
	getStuDetails.getAllOfInfo(xh,'VIEW_JYGL_JYXYSLQDJB',colList,getJyxylqInfo);
	//��ҵЭ�鲹������
	var colList = ["xh", "xm", "sqsj", "bblb"];
	getStuDetails.getAllOfInfo(xh,'view_jygl_jyxyblsq',colList,getJyxybbInfo);
}

//��ȡ��ҵ����Ϣ
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��ȡ��ҵ��Ϣ��Ϣ
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��ȡ��ҵЭ����ȡ��Ϣ
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��ȡ��ҵЭ�鲹����Ϣ
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��ͥ��Ϣ
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��ͥ��Ϣ
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

