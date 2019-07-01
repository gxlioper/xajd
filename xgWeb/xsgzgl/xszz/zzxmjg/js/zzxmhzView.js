var DCCLBH = "xszz_zzxmjg.do?method=zzxmhzList";//dcclbh,导出功能编号 

var gridSetting = {
	caption:"资助结果列表",
	pager:"pager",
	url:"xszz_zzxmjg.do?method=zzxmhzView&type=query",
	colList:[
       {label:'guid',name:'guid', index: 'guid',width:'2%',key:true,hidden:true},
	   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:xhLink},
	   {label:'姓名',name:'xm', index: 'xm',width:'13%'},
	   {label:'性别',name:'xb', index: 'xb',width:'6%'},
	   {label:'年级',name:'nj', index: 'nj',width:'7%'},
	   {label:'班级',name:'bjmc', index: 'bjdm',width:'19%'},
	   {label:'学年',name:'xn', index: 'xn',width:'10%'},
	   {label:'学期',name:'xqmc', index: 'xqmc',width:'6%'},
	   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'15%'},
	   {label:'金额',name:'je', index: 'je',width:'11%'},
	   {label:'sjly',name:'sjly', index: 'sjly',hidden:true}
	 
	],
	multiselect:false,
	sortname: "xh",
 	sortorder: "asc"
	};

   /**
	* 资助项目单个结果调查--查看
	* @param xh
	* @return
	*/
   function zzjgView(guid,xh){
		showDialog("资助结果查询",550,400,"xszz_zzxmjg.do?method=zzxmjgView&guid="+guid+"&xh="+xh);
   }
   
	function xhLink(cellValue,rowObject){
		return "<a href='javascript:void(0);' class='name' onclick='zzjgView(\""+rowObject["guid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
	}
	
	jQuery(function(){
		gridSetting["params"]={"lbdm":jQuery("#lbdm").val(),"xn":jQuery("#xn").val(),"xq":jQuery("#xq").val(),"xmmc":jQuery("#xmmc").val()};
		jQuery("#dataTable").initGrid(gridSetting);
	});
	
	
	function reBack(){
		refreshForm("/xgxt/xszz_zzxmjg.do?method=zzxmhzList");
	}
	
	
	// 导出配置 功能
	function exportConfig() {
		//DCCLBH导出功能编号,执行导出的函数
		customExport(DCCLBH, exportData);
	}
	
	// 导出方法
	function exportData() {
		var lbdm = jQuery("#lbdm").val();
		var xn = jQuery("#xn").val();
		var xq = jQuery("#xq").val();
		var xmmc = jQuery("#xmmc").val();
		var url = "xszz_zzxmjg.do?method=exportDataSx&dcclbh=" + DCCLBH + "&lbdm=" + lbdm + "&xn=" + xn + "&xq="+ xq + "&xmmc=" + xmmc;//dcclbh,导出功能编号
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
