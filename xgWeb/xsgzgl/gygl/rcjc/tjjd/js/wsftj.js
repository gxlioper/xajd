function getGridSettiong(){
	
	var link = jQuery(".ha>a").attr("link");
	
	var gridSetting = {
		caption:"ͳ�Ʊ���",
		pager:"pager",
		url:"gyglnew_tjjdwsf.do?method=viewWsftjList&type=query",
		params:tjMap(),
		multiselect:false
	};
	
	if("ld"==link){
		var colList=[
		   {label:'key',name:'lddm', index: 'lddm',key:true ,hidden:true},
		   {label:'¥��',name:'ldmc', index: 'xn',width:'20%'},
		   {label:'90������',name:'jsf', index: 'xqmc',width:'20%'},
           {label:'80~89��',name:'bsf', index: 'xh',width:'20%'},
		   {label:'60~79��',name:'qsf', index: 'xm',width:'20%'},
		   {label:'60������',name:'lsf', index: 'xb',width:'20%'}
		];
	}else{
		var colList=[
		   {label:'key',name:'xydm', index: 'xydm',key:true ,hidden:true},
		   {label:'ѧԺ',name:'xymc', index: 'xn',width:'20%'},
           {label:'�Ѽ�����',name:'yjqs', index: 'xh',width:'16%'},
		   {label:'������',name:'yxqs', index: 'xm',width:'16%'},
		   {label:'������',name:'yxl', index: 'xb',width:'16%'},
		   {label:'���ϸ���',name:'bhg', index: 'xymc',width:'16%'},
		   {label:'���ϸ���',name:'bhgl', index: 'bjdm',width:'16%'}
		];
	}
		
	gridSetting["colList"] = colList;
	return gridSetting;				
}

//����map
function tjMap(){
	var tjMap = {};
	tjMap.link = jQuery(".ha>a").attr("link");
	tjMap.kssj = jQuery("#kssj").val();
	tjMap.jssj = jQuery("#jssj").val();
	
	return tjMap;
}

//ҳǩ�л�
function checkTab(obj){
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	query();
}

//��ѯ
function query(){
	var gridSetting = getGridSettiong();
	jQuery("#dataTable").initGrid(gridSetting);
}

//��������
function exportData(){
	
	//dcclbh,�������ܱ��
	var DCCLBH='tjjd_wsftj_ld.do';
	var link = jQuery(".ha>a").attr("link");
	
	if("xy"==link){
		DCCLBH='tjjd_wsftj_xy.do';
	}
	var map = tjMap();//��������
	var url = "gyglnew_tjjdwsf.do?method=exportData&dcclbh=" + DCCLBH+"&link="+map.link+"&kssj="+map.kssj+"&jssj="+map.jssj;
	
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}