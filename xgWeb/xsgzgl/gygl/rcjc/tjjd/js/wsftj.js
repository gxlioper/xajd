function getGridSettiong(){
	
	var link = jQuery(".ha>a").attr("link");
	
	var gridSetting = {
		caption:"统计报表",
		pager:"pager",
		url:"gyglnew_tjjdwsf.do?method=viewWsftjList&type=query",
		params:tjMap(),
		multiselect:false
	};
	
	if("ld"==link){
		var colList=[
		   {label:'key',name:'lddm', index: 'lddm',key:true ,hidden:true},
		   {label:'楼栋',name:'ldmc', index: 'xn',width:'20%'},
		   {label:'90分以上',name:'jsf', index: 'xqmc',width:'20%'},
           {label:'80~89分',name:'bsf', index: 'xh',width:'20%'},
		   {label:'60~79分',name:'qsf', index: 'xm',width:'20%'},
		   {label:'60分以下',name:'lsf', index: 'xb',width:'20%'}
		];
	}else{
		var colList=[
		   {label:'key',name:'xydm', index: 'xydm',key:true ,hidden:true},
		   {label:'学院',name:'xymc', index: 'xn',width:'20%'},
           {label:'已检总数',name:'yjqs', index: 'xh',width:'16%'},
		   {label:'优秀数',name:'yxqs', index: 'xm',width:'16%'},
		   {label:'优秀率',name:'yxl', index: 'xb',width:'16%'},
		   {label:'不合格数',name:'bhg', index: 'xymc',width:'16%'},
		   {label:'不合格率',name:'bhgl', index: 'bjdm',width:'16%'}
		];
	}
		
	gridSetting["colList"] = colList;
	return gridSetting;				
}

//条件map
function tjMap(){
	var tjMap = {};
	tjMap.link = jQuery(".ha>a").attr("link");
	tjMap.kssj = jQuery("#kssj").val();
	tjMap.jssj = jQuery("#jssj").val();
	
	return tjMap;
}

//页签切换
function checkTab(obj){
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	query();
}

//查询
function query(){
	var gridSetting = getGridSettiong();
	jQuery("#dataTable").initGrid(gridSetting);
}

//导出方法
function exportData(){
	
	//dcclbh,导出功能编号
	var DCCLBH='tjjd_wsftj_ld.do';
	var link = jQuery(".ha>a").attr("link");
	
	if("xy"==link){
		DCCLBH='tjjd_wsftj_xy.do';
	}
	var map = tjMap();//设置条件
	var url = "gyglnew_tjjdwsf.do?method=exportData&dcclbh=" + DCCLBH+"&link="+map.link+"&kssj="+map.kssj+"&jssj="+map.jssj;
	
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}