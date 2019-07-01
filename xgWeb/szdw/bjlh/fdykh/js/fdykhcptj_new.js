/**
 * ����Ա����ͳ�� �°�js
 */
/**
 * 
 * @return
 */
function searchRs(){
	var map = getSuperSearch();
	map["tjlx"] = jQuery("#tjlx").val(); 
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * tab �л�
 * @param obj
 * @param tabId
 * @return
 */
function selecttjlb(obj,tabId){
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	
	jQuery(".con_overlfow table").css("display","none");
	jQuery("#"+tabId).css("display","");
	
	jQuery(".con_overlfow_1 table").css("display","none");
	
	jQuery("#"+tabId+"_1").css("display","");
	
}

/**
 * 
 * @param obj
 * @param tabid
 */
function chageTB(obj,tabid){
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	var grid = undefined;
	var map = getSuperSearch();
	if (tabid=="0"){
		jQuery("#tjlx").val('RS');
		grid = getRSGrid();
		map["tjlx"] = "RS"; 
		grid["params"] = map;
		jQuery("#dataTable").initGrid(grid);
	} else if(tabid == "1"){
		jQuery("#tjlx").val('BJ');
		grid = getBJGrid();
		map["tjlx"] = "BJ"; 
		grid["params"] = map;
		jQuery("#dataTable").initGrid(grid);
	}else{
		jQuery("#tjlx").val('ZZ');
		grid = getZZGrid();
		map["tjlx"] = "ZZ";  
		grid["params"] = map;
		jQuery("#dataTable").initGrid(grid);
	}
}

/**
 * ��ȡͳ�Ʊ��
 * @param type ������� BJ.���༶ͳ�Ʊ��/ZZ.��ְ��ͳ�Ʊ��/RS.������ͳ�Ʊ��
 * @return dataGrid
 */
function getRSGrid(){
	var gridSettingRS = {
			caption:"����Ա����ͳ�ƽ��",
			pager:"pager",
			url:"bjlh_fdykh.do?method=fdykhKhcptjNew&type=query",
			colList:[
			   {label:'key',name:'pk', index: 'pk',key:true ,hidden:true},
			   {label:'ѧ��',name:'xn', index: 'xn',width:'8%'},
			   {label:'����',name:'xm', index: 'xm',width:'8%'},
			   {label:'���ڲ���',name:'bmmc', index: 'bmmc',width:'10%'},
			   {label:'����<br/>����',name:'fdybjs', index: 'fdybjs',width:'2%'},
			   {label:'ѧ��<br/>����<br/>������/<br/>������',name:'xspfqk', index: 'xspfqk',width:'3%' , formatter:xhLink},
			   {label:'��ǰ��',name:'xspjf', index: 'xspjf',width:'2%'},
			   {label:'��Ч��',name:'yxxspjf', index: 'yxxspjf',width:'2%'},
			   {label:'����<br/>С��<br/>����<br/>������/<br/>������',name:'cpxzpfqk', index: 'cpxzpfqk',width:'3%'},
			   {label:'ƽ��<br/>��',name:'cpxzpjf', index: 'cpxzpjf',width:'6%'},
			   {label:'�ܷ�',name:'zf', index: 'zf',width:'6%'}
			],
			sortname: "xm",
			sortorder : "asc"
		}
	return gridSettingRS;
}
/**
 * ��ȡͳ�Ʊ��
 * @param type ������� BJ.���༶ͳ�Ʊ��/ZZ.��ְ��ͳ�Ʊ��/RS.������ͳ�Ʊ��
 * @return dataGrid
 */
function getBJGrid(){
	var gridSettingRS = {
			caption:"����Ա����ͳ�ƽ��",
			pager:"pager",
			url:"bjlh_fdykh.do?method=fdykhKhcptjNew&type=query",
			colList:[
			   {label:'key',name:'pk', index: 'pk',key:true ,hidden:true},
			   {label:'ѧ��',name:'xn', index: 'xn',width:'8%'},
			   {label:'�༶����',name:'bjmc', index: 'bjmc',width:'13%'},
			   {label:'����',name:'xm', index: 'xm',width:'8%'},
			   {label:'���ڲ���',name:'bmmc', index: 'bmmc',width:'10%'},
			   {label:'������/<br/>����Ա',name:'zzlbmc', index: 'zzlbmc',width:'10%'},
			   {label:'ѧ��<br/>����<br/>������/<br/>������',name:'xspfqk', index: 'xspfqk',width:'3%' , formatter:xhLink},
			   {label:'��ǰ��',name:'xspjf', index: 'xspjf',width:'2%'},
			   {label:'��Ч��',name:'yxxspjf', index: 'yxxspjf',width:'2%'},
			   {label:'����<br/>С��<br/>����<br/>������/<br/>������',name:'cpxzpfqk', index: 'cpxzpfqk',width:'3%'},
			   {label:'ƽ��<br/>��',name:'cpxzpjf', index: 'cpxzpjf',width:'6%'},
			   {label:'�ܷ�',name:'zf', index: 'zf',width:'6%'}
			],
			
			sortname: "bjmc",
			sortorder : "asc"
		}
	return gridSettingRS;
}
/**
 * ��ȡͳ�Ʊ��
 * @param type ������� BJ.���༶ͳ�Ʊ��/ZZ.��ְ��ͳ�Ʊ��/RS.������ͳ�Ʊ��
 * @return dataGrid
 */
function getZZGrid(){
	var gridSettingRS = {
			caption:"����Ա����ͳ�ƽ��",
			pager:"pager",
			url:"bjlh_fdykh.do?method=fdykhKhcptjNew&type=query",
			colList:[
			   {label:'key',name:'pk', index: 'pk',key:true ,hidden:true},
			   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
			   {label:'ְ����',name:'zgh', index: 'zgh',width:'10%' ,hidden:true},
			   {label:'����',name:'xm', index: 'xm',width:'9%'},
			   {label:'���ڲ���',name:'bmmc', index: 'bmmc',width:'10%'},
			   {label:'������/<br/>����Ա',name:'zzlbmc', index: 'zzlbmc',width:'10%'},
			   {label:'����<br/>����',name:'bjs', index: 'bjs',width:'2%'},
			   {label:'ѧ��<br/>����<br/>������/<br/>������',name:'xspfqk', index: 'xspfqk',width:'3%' , formatter:xhLink},
			   {label:'��ǰ��',name:'xspjf', index: 'xspjf',width:'2%'},
			   {label:'��Ч��',name:'yxxspjf', index: 'yxxspjf',width:'2%'},
			   {label:'����<br/>С��<br/>����<br/>������/<br/>������',name:'cpxzpfqk', index: 'cpxzpfqk',width:'3%'},
			   {label:'ƽ��<br/>��',name:'cpxzpjf', index: 'cpxzpjf',width:'6%'},
			   {label:'�ܷ�',name:'zf', index: 'zf',width:'6%'}
			],
			sortname: "zzlbmc",
			sortorder : "asc"
		}
	return gridSettingRS;
}

function xhLink(cellValue,rowObject){
	
	var rs = cellValue.split('/');
	
	//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
	var onclickfn = "onclick=\"" + "showDialog('������ϸ' , 600 , 400 , 'bjlh_fdykh.do?method=fdykhKhMxNew&pk=" + rowObject['pk'] + "')" + "\"";
	
	var href = "href = 'javascript:void(0);'";

	if(rs[0] === '0'){
		return rs[0] +"/" + rs[1];
	}else 
		return  "<a " + href + " class='name' " + onclickfn + " >" + rs[0] + "</a>" + "/" + rs[1];
}

var DCCLBH = "";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	
	DCCLBH = "bjlh_fdykh_new_";
	
	var currentTab =  jQuery("#tjlx").val();
	
	DCCLBH = DCCLBH + currentTab + ".do";
	
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "bjlh_fdykh.do?method=exportDataNew&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}